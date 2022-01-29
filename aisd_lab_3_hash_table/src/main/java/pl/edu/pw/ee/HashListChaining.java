package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem<T> nil = null;
    private Elem<T>[] hashElems;
    private int nElem;

    private class Elem<T extends Comparable<T>> {
        private T value;
        private Elem<T> next;

        Elem(T value, Elem<T> nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        if (size<1) {
            throw new IllegalArgumentException("Size should be bigger than 0");
        }
        hashElems = new Elem[size];
        initializeHash();
    }

    @Override
    public void add(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem(value, hashElems[hashId]);
            nElem++;
        }
    }

    public void delete(T value){
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        if(hashElems[hashId] == nil){
            throw new IllegalArgumentException("Cannot delete an element in an empty node");
        }

        Elem<T> elem = hashElems[hashId];

        //Dla pierwszego elementu w kolejce
        if(elem != nil && elem.value.equals(value)){
            hashElems[hashId] = hashElems[hashId].next;
        }
        //Dla kolejnych elementów
        else{
            Elem<T> before = hashElems[hashId];
            elem = elem.next;
            while(elem != nil && !elem.value.equals(value)){
                before = elem;
                elem = elem.next;
            }
            if(elem != nil){
                before.next = elem.next;
                nElem--;
            }
            else{
                throw new IllegalArgumentException("No such element in the table");
            }
        }
    }

    @Override
    public T get(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : null;
    }

    public double countLoadFactor() {
        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {
        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.length;
        return Math.abs(hashCode % n);
    }


}