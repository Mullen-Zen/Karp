package com.karp;

class KarpClass implements KarpCallable {
    final String name;
    
    KarpClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        KarpInstance instance = new KarpInstance(this);
        return instance;
    }

    @Override
    public int arity() {
        return 0;
    }
}
