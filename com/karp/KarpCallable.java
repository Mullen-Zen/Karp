package com.karp;

import java.util.List;

public interface KarpCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
