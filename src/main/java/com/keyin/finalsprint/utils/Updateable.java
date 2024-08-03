package com.keyin.finalsprint.utils;

public interface Updateable<T extends Updateable.UpdateData> {

    boolean update(T data);

    interface UpdateData {

        boolean isComplete();
    }
}
