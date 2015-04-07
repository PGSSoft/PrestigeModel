package com.pgssoft.prestige.arena.utils;

/**
 * Created by bstokrocki on 2014-11-19.
 */
public class Pair <K, V> {
    private K key;
    private V value;

    public K getKey() { return key; }

    public V getValue() { return value; }

    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public java.lang.String toString() { return key + "=" + value; }

    public int hashCode() {
        return key.hashCode() * 13 + (value == null ? 0 : value.hashCode());
    }

    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o instanceof Pair) {
                Pair pair = (Pair) o;
                if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
                if (value != null ? !value.equals(pair.value) : pair.value != null) return false;
                return true;
            }
        return false;
    }
}