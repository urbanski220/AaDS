package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    private double a,b;

    public HashQuadraticProbing(){
        super();
        this.a = Math.PI;
        this.b = Math.E;
    }

    public HashQuadraticProbing(int size, double a, double b){
        super(size);

        if(a == 0 || b == 0 ){
            throw new IllegalArgumentException("A and B cannot be 0!");
        }

        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (int) (key % m + i * a + i * i * b) % m;

        hash = hash < 0 ? -hash : hash;
        
        return hash;
    }

}
