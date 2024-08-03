package com.keyin.finalsprint.utils;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public abstract class UpdateableService<T extends Updateable<D>, D extends Updateable.UpdateData> {

    protected abstract T create(D data);

    protected abstract ListCrudRepository<T, Long> repository();

    public T get(Long id) {
        if (id == null) return null;
        return repository().findById(id).orElse(null);
    }

    public List<T> get() {
        return repository().findAll();
    }

    public T add(D data) {
        if (!data.isComplete()) return null;
        return repository().save(create(data));
    }

    public T update(Long id, D data) {
        if (id == null) return null;
        T value = get(id);
        if (value == null) return null;
        if (!value.update(data)) return null;
        return repository().save(value);
    }

    public boolean delete(Long id) {
        if (id == null) return false;
        T value = get(id);
        if (value == null) return false;
        repository().deleteById(id);
        return true;
    }
}
