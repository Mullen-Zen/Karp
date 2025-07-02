package com.karp;

import java.util.HashMap;
import java.util.Map;

public class KarpInstance {
    private KarpClass klass;
    private final Map<String, Object> fields = new HashMap<>();

    KarpInstance(KarpClass klass) {
        this.klass = klass;
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        throw new RuntimeError(name, "Undefined property '" + name.lexeme + "'.");
    }

    @Override
    public String toString() {
        return klass.name + " instance";
    }
}
