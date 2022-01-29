package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithm implements MinSpanningTree {

    List<Elem> Elems = new ArrayList<>();
    List<Node> Trees = new ArrayList<>();

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

    private class Node {
        private String start;
        private Node next;

        Node(String start,  Node next){
            this.start = start;
            this.next = next;
        }

        @Override
        public boolean equals(Object o){
            Node n = (Node) o;
            return n.start.equals(this.start);

        }
        
    }

    @Override
    public String findMST(String pathToFile) {

        if(pathToFile == null){
            throw new IllegalArgumentException("pathToFile cannot be null!");
        }

        String MST = "";

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] w = line.split("\\s+");
                if(w.length == 3){
                    Elems.add(new Elem(w[0], w[1] , Integer.parseInt(w[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read files from given directory\n");
            return null;
        }

        if(Elems.size() == 0){
            throw new IllegalArgumentException("Given file cannot be empty!");
        }

        Collections.sort(Elems);

        for(int i = 0; i < Elems.size(); i++){
            Elem tmp = Elems.get(i);
            Node tree1 = new Node(tmp.start, null);
            Node tree2 = new Node(tmp.end, null);
            if(!Trees.contains(tree1)) Trees.add(tree1);
            if(!Trees.contains(tree2)) Trees.add(tree2);
        }
   
        int n = Elems.size();

        for(int i = 0; i < n; i++){
            Elem tmp = Elems.remove(0);
            int ind1 = findString(tmp.start);
            int ind2 = findString(tmp.end);
            if(ind1 != ind2){
                MST = MST + tmp.start + "_" + String.valueOf(tmp.val) + "_" + tmp.end + "|";
                Node newtree = Trees.get(ind1);
                Node tree = Trees.remove(ind2);

                while(newtree.next!=null)
                    newtree = newtree.next;
                
                newtree.next = tree;
            }
        }
        if(Trees.size()!=1){
            System.out.println("Given tree is not conencted!");
            return null;
        }
        
        return MST;
    }

    private int findString(String s){
        int i;
        for(i = 0; i < Trees.size(); i++){
            Node tree = Trees.get(i);
            while(tree.next != null){
                if(tree.start.equals(s)) return i;
                else
                    tree = tree.next;
            }
            if(tree.start.equals(s)) return i;
        }
        return -1;
    }
    
}