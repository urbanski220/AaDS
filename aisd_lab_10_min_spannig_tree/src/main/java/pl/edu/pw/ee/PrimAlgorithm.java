package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {

    private final Elem nil = null;
    private Elem[] Tree;
    private List<Elem> prioTree = new ArrayList<>();
    private int nElem;
    private List<String> used = new ArrayList<>();
    

    private class Elem implements Comparable<Elem>{
        private String start;
        private String end;
        private int val;

        Elem(String start, String end, int val){
            this.start = start;
            this.end = end;
            this.val = val;
        }

        @Override
        public int compareTo(Elem e){
            if(e == null)
                return -1;
            return Integer.compare(this.val, e.val);
        }
    }

    private int initializeTree(String pathToFile){
        int lines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            while ((br.readLine()) != null) {
                lines++;
            }
        } catch (IOException e) {
            System.out.println("Cannot read files from given directory\n");
            return 1;
        }

        if(lines == 0){
            return 1;
        }

        Tree = new Elem[lines];
        nElem = Tree.length;

        for (int i = 0; i < nElem; i++) {
            Tree[i] = nil;
        }
        return 0;
    }

    public String findMST(String pathToFile){
        if(pathToFile == null){
            throw new IllegalArgumentException("pathToFile cannot be null!");
        }

        if(initializeTree(pathToFile) == 1){
            throw new IllegalArgumentException("Given file cannot be empty!");
        }

        String MST = "";

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] w = line.split("\\s+");
                if(w.length == 3){
                    Tree[i] = new Elem(w[0], w[1] , Integer.parseInt(w[2]));
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read files from given directory\n");
            return null;
        }

        String s = Tree[0].start;
        
        int n = countUnique();

        while(n-1!=0){
            for(int i = 0; i < nElem; i++){
                if((Tree[i].start.equals(s) && !used.contains(Tree[i].end) || (Tree[i].end.equals(s) && !used.contains(Tree[i].start)))){
                    prioTree.add(Tree[i]);
                }
            }
            if(prioTree.size() == 0){
                System.out.println("Given tree is not conencted!");
                return null;
            }
            Collections.sort(prioTree);
            Elem tmp = prioTree.remove(0);
            MST = MST + tmp.start + "_" + String.valueOf(tmp.val) + "_" + tmp.end + "|";
            used.add(s);
            if(s.equals(tmp.start))
                s = tmp.end;
            else
                s = tmp.start;
            used.add(s);
            deleteUnused();
            n--;
        }


        return MST;
    }

    private int countUnique(){
        String []values = new String[2*nElem];
        int k = 0;
        for(int i = 0; i < nElem; i++){
            values[k] = Tree[i].start;
            k++;
            values[k] = Tree[i].end; 
            k++;
        }
        int res = 1;
    
        for (int i = 1; i < 2*nElem; i++){
        int j = 0;
        for (j = 0; j < i; j++)
            if (values[i].equals(values[j]))
                break;

        if (i == j)
            res++;
    }
    return res;
    }

    private void deleteUnused(){
        for(int i = 0; i < prioTree.size(); i++){
            Elem tmp = prioTree.get(i);
            if(used.contains(tmp.start) && used.contains(tmp.end)){
                prioTree.remove(i);
            }
        }
    }

}
