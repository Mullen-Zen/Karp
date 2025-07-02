package com.karp;

public class KarpInstance {
    private KarpClass klass;

    KarpInstance(KarpClass klass) {
        this.klass = klass;
    }

    @Override
    public String toString() {
        return klass.name + " instance";
    }
}
