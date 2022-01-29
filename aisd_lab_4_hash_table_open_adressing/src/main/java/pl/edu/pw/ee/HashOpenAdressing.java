package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;
    private boolean[] isDeleted;

    HashOpenAdressing() {
        this(2039); 
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.isDeleted = new boolean[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateGivenElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId] != newElem) {
            i= (i + 1) % size;
            if (i == 0)
                doubleResize();
            hashId = hashFunc(key, i);
        }

        if (hashElems[hashId] != newElem){
            nElems++;
            hashElems[hashId] = newElem;
            isDeleted[hashId] = false;
        } else {
            hashElems[hashId] = newElem;
        }
    }

    @Override
    public T get(T elem) {
        validateGivenElem(elem);
        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while(hashElems[hashId] != elem && hashElems[hashId] != null || isDeleted[hashId] == true){
            i++;
            hashId = hashFunc(key, i);
        }
        if(hashElems[hashId] == elem)
            return elem;
        else
            return null;
    }

    @Override
    public void delete(T elem) {
        validateGivenElem(elem);
        if(get(elem) == null){
            return;
        }

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);
    
        while(hashElems[hashId] != elem || isDeleted[hashId] == true){
            i++;
            hashId = hashFunc(key, i);
        }
        hashElems[hashId] = null;
        isDeleted[hashId] = true;
        nElems--;

    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateGivenElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Given elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    int getnElems(){
        return nElems;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        T[] bElems = this.hashElems;
        int bs = this.size;
        this.size *= 2;
        this.nElems = 0;
        this.hashElems = (T[]) new Comparable[this.size];
        this.isDeleted = new boolean[this.size];
        for(int i = 0; i < bs; i++){
            if (bElems[i] != null) {
                put(bElems[i]);
            }
        }
    }
}