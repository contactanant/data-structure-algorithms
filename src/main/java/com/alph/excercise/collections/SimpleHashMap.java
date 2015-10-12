package com.alph.excercise.collections;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class SimpleHashMap<K, V> implements Map<K, V>, Cloneable, Serializable {

    static final int MAX_CAPACITY = 1 << 30;
    static int INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int size;
    private Node<K, V>[] data;
    private int threshold;
    private Set<Entry<K, V>> entrySet;

    private class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }

        @Override
        public String toString() {
            return "{ key=" + key + ", value=" + value + '}';
        }
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public int size() {
            return size;
        }

        public final void clear() {
            SimpleHashMap.this.clear();
        }
    }

    final class EntryIterator implements Iterator<Entry<K, V>> {

        int index;
        int arrayIndex;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Entry<K, V> next() {
            Node<K, V> node = null;
            while (arrayIndex < data.length && (node = data[arrayIndex++]) == null) {
            }
            index++;
            return node;
        }

        @Override
        public void remove() {
            data[index] = null;
            size--;
        }

        @Override
        public void forEachRemaining(Consumer action) {

        }
    }

    public int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (data == null || data.length == 0) {
            return false;
        }
        Node<K, V> node;
        int h;
        return ((h = hash(key) & (data.length - 1)) <= data.length) && ((node = data[h]) != null) && (Objects.equals(node.key, key) ? true : false);
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        int hash = hash(key);
        Node<K, V> node;
        Node<K, V> next;
        if ((node = data[hash & (data.length - 1)]) != null) {
            if (Objects.equals(node.getKey(), key)) {
                return node.getValue();
            } else {
                while ((next = node.next) != null) {
                    if (Objects.equals(next.getKey(), key)) {
                        return next.getValue();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);

        if (data == null || data.length == 0) {
            resize();
        }

        Node<K, V> node;
        int l = data.length;
        int i;
        if ((node = data[i = ((l - 1) & hash)]) == null) {
            data[i] = new Node<>(hash, key, value, null);
        } else {
                Node<K, V> n;
                for (int binCount = 0; ; binCount++) {
                    if (Objects.equals(node.getKey(), key)) {
                        return replaceValueOfNode(value, node);
                    }
                    if (node !=null && node.next == null) {
                        node.next = new Node<>(hash, key, value, null);
                        break;
                    }
                    node = node.next;
                }
        }

        if (++size > threshold) {
            resize();
        }
        return null;
    }

    private V replaceValueOfNode(V value, Node<K, V> node) {
        if (node != null) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        return null;
    }

    private void resize() {
        Node<K, V>[] oldTab = data;
        int oldCapacity = data == null ? 0 : data.length;
        int oldThres = threshold;
        int newCapacity = oldCapacity * 2;
        int newThresold = oldThres * 2;

        if (oldCapacity >= MAX_CAPACITY) {
            threshold = oldCapacity;
            return;
        }

        if (newCapacity == 0) {
            newCapacity = INITIAL_CAPACITY;
        }
        if (newThresold == 0) {
            newThresold = (int) (DEFAULT_LOAD_FACTOR * newCapacity);
        }

        threshold = newThresold;
        data = new Node[newCapacity];
        if (oldTab != null) {
            System.arraycopy(oldTab, 0, data, 0, size);
        }
    }

    @Override
    public V remove(Object key) {
        if (data != null && data.length > 0) {
            int hash = hash(key);
            Node<K, V> node = data[hash];
            if (node != null) {
                data[hash] = null;
                --size;
                return node.getValue();
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (m == null || m.size() == 0) {
            return;
        }
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        data = null;
        size = 0;
        threshold = 0;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entrySet == null ? (entrySet = new EntrySet()) : entrySet;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {

    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {

    }

    @Override
    public V putIfAbsent(K key, V value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return false;
    }

    @Override
    public V replace(K key, V value) {
        return null;
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return null;
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return null;
    }


    @Override
    public String toString() {

        Iterator<Entry<K, V>> itr = entrySet().iterator();
        if (!itr.hasNext())
            return "{}";

        StringBuilder stringBuilder = new StringBuilder();
        while (itr.hasNext()) {
            Entry<K, V> next = itr.next();
            if (next != null) {
                stringBuilder.append(next);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Map<Integer, String> myMap = new HashMap<>();
        myMap.put(101, "my");
        myMap.put(21, "first");
//        myMap.put(30, "map");
//        myMap.put(3, "impl");
        System.out.println(myMap);
    }
}
