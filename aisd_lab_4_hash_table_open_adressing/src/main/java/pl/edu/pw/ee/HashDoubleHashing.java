package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashDoubleHashing(){
        super();
    }

    public HashDoubleHashing(int size){
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        double g = 1 + (key % (m-3));

        int hash = (int) (key % m - i * g) % m;

        hash = hash < 0 ? -hash : hash;
        
        return hash;
    }

}
