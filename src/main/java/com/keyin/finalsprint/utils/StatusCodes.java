package com.keyin.finalsprint.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class StatusCodes {

    private static <T> ResponseEntity<T> create(HttpStatusCode code) {
        return ResponseEntity.status(code).build();
    }

    public static <T> ResponseEntity<T> noContent() {
        return create(HttpStatus.NO_CONTENT);
    }

    public static <T> ResponseEntity<T> created() {
        return create(HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<T> badRequest() {
        return create(HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<T> forbidden() {
        return create(HttpStatus.FORBIDDEN);
    }

    public static <T> ResponseEntity<T> unauthorized() {
        return create(HttpStatus.UNAUTHORIZED);
    }

    public static <T> ResponseEntity<T> serverError() {
        return create(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
