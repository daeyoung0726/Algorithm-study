package homework.homework_4;

import java.util.ArrayList;

public class Bucket<K, V> {
    private int bits;
    private int size;
    private K[] keys;
    private V[] values;
    private int hashingAddr;
    private int M;

    public Bucket(int bits, int hashingAddr, int bucketSize) {
        this.bits = bits;
        this.hashingAddr = hashingAddr;
        this.keys = (K[]) new Object[bucketSize];
        this.values = (V[]) new Object[bucketSize];
        this.M = bucketSize;
        size = 0;
    }

    public boolean put(K key, V value) {

        for (int i = 0; i < keys.length; i++) {
            if (key.equals(keys[i])) {
                values[i] = value;
                return true;
            }
        }

        if(size >= keys.length) {
            return false;
        }

        keys[size] = key;
        values[size++] = value;
        return true;
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (key.equals(keys[i])) {
                return values[i];
            }
        }
        return null;
    }

    public void reSizeBucket() {
        this.keys = (K[]) new Object[M];
        this.values = (V[]) new Object[M];
        this.size = 0;
    }

    public Iterable<K> getKeys() {
        ArrayList<K> key = new ArrayList<>();
        if(size == 0)
            return null;

        for (K k : keys) {
            if (k != null)
                key.add(k);
        }
        return key;
    }

    public int getHashingAddr() {
        return hashingAddr;
    }

    public int getBits() {
        return this.bits;
    }
    public void setBits(int bits) {
        this.bits = bits;
    }


    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void show() {
        for(int i = 0; i < keys.length; i++) {
            if(keys[i] != null) {
                System.out.println("    " + keys[i] + " : " + values[i]);
            }
        }
    }
}

