package com.upit.algo.symboltable;

public interface StringSymbolTable<V> {
    void put(String key, V value);

    V get(String key);

    void delete(String key);

    boolean contains(String key);

    Iterable<String> keys();

    Iterable<String> keysWithPrefix(String prefix);

    String longestPrefixOf(String query);
}
