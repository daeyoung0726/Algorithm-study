package homework.homework_4;

import java.util.ArrayList;
import java.util.Collection;

public class ExtensibleHashST<K, V> {

    private int i = 0;
    private int M;
    private Directory[] directories;
    private ArrayList<Bucket<K, V>> buckets = new ArrayList<>();

    public ExtensibleHashST() {
        this(4);
    }

    public ExtensibleHashST(int M) {
        this.M = M;
        buckets.add(new Bucket<>(0, 0, M));
        directories = new Directory[1];
        directories[0] = new Directory();
        directories[0].setBucketNum(0);
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        int num = 0;
        for (Bucket bucket: buckets) {
            num += bucket.getSize();
        }

        return num;
    }

    public Iterable<K> keys() {
        ArrayList<K> keys = new ArrayList<>();
        for(Bucket bucket: buckets) {
            if(bucket.getKeys() != null) {
                keys.addAll((Collection<? extends K>) bucket.getKeys());
            }
        }
        return keys;
    }

    public V get(K key) {
        for (int j = 0; j < buckets.size(); j++) {
            if (buckets.get(j).get(key) != null)
                return buckets.get(j).get(key);
        }
        return null;
    }

    public void put(K key, V value) {
        for(int j = 0; j < buckets.size(); j++) {
            if(buckets.get(j).getHashingAddr() == hash(key, buckets.get(j).getBits())) {
                if(!buckets.get(j).put(key, value)) {
                    addBucket(j);
                    put(key, value);
                }
                break;
            }
        }

    }

    private void addBucket(int index) {
        Bucket<K, V> oldBucket = buckets.get(index);

        int newBucketBits = oldBucket.getBits() + 1;

        ArrayList<K> beforeKeys = new ArrayList<>();
        ArrayList<V> beforeValues = new ArrayList<>();

        if(oldBucket.getKeys() != null) {
            beforeKeys.addAll((Collection<? extends K>) oldBucket.getKeys());
        }

        for(int j = 0; j < beforeKeys.size(); j++) {
            V value = oldBucket.get(beforeKeys.get(j));
            if(value != null)
                beforeValues.add(value);
        }

        boolean check = (i - oldBucket.getBits()) == 0;
        this.i = Math.max(i, newBucketBits);

        K newKey = null;
        for(int j = 0; j < beforeKeys.size(); j++) {

            if(oldBucket.getHashingAddr() != hash(beforeKeys.get(j), newBucketBits)) {
                newKey = beforeKeys.get(j);
                break;
            }
        }

        if(newKey != null) {
            buckets.add(new Bucket<>(newBucketBits, hash(newKey, newBucketBits), M));
        }
        else {
            buckets.add(new Bucket<>(newBucketBits, oldBucket.getHashingAddr() | 1<<(oldBucket.getBits()) , M));
        }

        oldBucket.setBits(newBucketBits);
        oldBucket.reSizeBucket();

        for (int j = 0; j < beforeKeys.size(); j++) {
            for(int k = 0; k < buckets.size(); k++) {
                if (buckets.get(k).getHashingAddr() == hash(beforeKeys.get(j), newBucketBits)) {
                    buckets.get(k).put(beforeKeys.get(j), beforeValues.get(j));
                }

            }
        }

        makeDir(check);
    }


    private void makeDir(boolean check) {
        if (check) {
            int dirLength = directories.length;
            directories = new Directory[dirLength * 2];
        }
        for(int j = 0; j < directories.length; j++) {
            for(int k = 0; k < buckets.size(); k++) {
                int bit = buckets.get(k).getBits();
                String binaryString = Integer.toBinaryString(j);
                String lastBits = binaryString.substring(Math.max(0, binaryString.length() - bit));
                if(lastBits.equals(Integer.toBinaryString(buckets.get(k).getHashingAddr()))) {
                    directories[j] = new Directory();
                    directories[j].setBucketNum(k);
                }
            }
        }
    }

    private int hash(K key, int i) {

        return key.hashCode() & ((1 << i) - 1);
    }

    public void detailInfo() {
        System.out.println("Global i = " + this.i + "비트, (key, value) 쌍의 수 = " + size() + ", 버킷의 수 = " + buckets.size());
        for(int i = 0; i < directories.length; i++) {
            System.out.println("Directory[" + i + "] -> Bucket " + directories[i].getBucketNum());
        }

        for(int i = 0; i < buckets.size(); i++) {
            System.out.println("Bucket " + i + ": size = " + buckets.get(i).getSize() + ", nbits = " + buckets.get(i).getBits() + "비트");
            buckets.get(i).show();
        }

    }

    public void summaryInfo() {
        System.out.println("Global i = " + this.i + "비트, (key, value) 쌍의 수 = " + size() + ", 버킷의 수 = " + buckets.size());
    }
}