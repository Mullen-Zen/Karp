package com.karp;

class Reply extends RuntimeException {
    final Object value;

    Reply(Object value) {
        super(null, null, false, false);
        this.value = value;
    }
}
