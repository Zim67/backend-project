package com.keyin.finalsprint.utils;

import java.util.function.Function;

public class Optionull {

    public static <I, O> O map(I input, Function<I, O> mapper) {
        if (input == null) return null;
        return mapper.apply(input);
    }
}
