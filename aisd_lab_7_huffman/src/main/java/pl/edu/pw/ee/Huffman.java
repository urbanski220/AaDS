package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Huffman {
    

    private class Node implements Comparable<Node>{

        private final Node left;
        private final Node right;
        private final int ch;
        private final int weight;
        private String code = "";
        
        public Node(Node left, Node right, int weight){
            this.left = left;
            this.right = right;
            this.ch = -1;
            this.weight = weight;
        }
        
        public Node(int weight, int ch){
            this.weight = weight;
            this.ch = ch;
            this.left = null;
            this.right = null;
        }
        
        public void buildCodes(String[] codes){
            if(left != null && right != null){
                left.setCode(getCode() + "0");
                left.buildCodes(codes);
                right.setCode(getCode() + "1");
                right.buildCodes(codes);
            }else{
                codes[ch] = getCode();
            }
        }
        
        private void setCode(String code){
            this.code = code;
        }
        
        public String getCode(){
            return code;
        }
        
        public int getCh(){
            return ch;
        }
        
        public int getWeigth() {
            return weight;
        }
        
        @Override
        public int compareTo(Node other) {
            if(other == null)
                return -1;
        
            return Integer.compare(getWeigth(), other.getWeigth());
        }
    }


    public int huffman(String pathToRootDir, boolean compress){

        if(pathToRootDir == null){
            throw new IllegalArgumentException("pathToRootDir cannot be null!");
        }

        String data = "data.txt";

        if(compress == true){
            return compressedFile(pathToRootDir,data);
        }

        else{
            return decompressedFile(pathToRootDir,data);
        }
    }

    
    private void buildTree(List<Node> tree){
        if(tree.size() < 2)
            return;

        Collections.sort(tree);

        Node s1 = tree.remove(0);
        Node s2 = tree.remove(0);

        Node parent = new Node(s2, s1, s1.getWeigth()+s2.getWeigth());

        tree.add(0, parent);
    }

    private int compressedFile(String pathToRootDir, String data){
        

        String file = pathToRootDir + data;
        
        int[] counter = new int[400];
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(file), Charset.forName("UTF-8")))) {
            int ch;
            while ((ch = br.read()) != -1) {
                if(ch > counter.length - 1){
                    int[] tmp = new int[ch+1];
                    for(int i = 0; i < counter.length; i++){
                        tmp[i] = counter[i];
                    }
                    counter = tmp;
                }
                counter[ch]++;
            }
        } catch (IOException e) {
            System.out.println("Cannot read files from given directory\n");
            return -1;
        }

        buildDict(pathToRootDir, counter);

        List<Node> tree = new ArrayList<>();
        initiateTree(tree, counter);
        
        Node root = tree.get(0);
        String[] codes = new String[counter.length];
        root.buildCodes(codes);
        int count = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(pathToRootDir + data)))) {
            File save = new File(pathToRootDir + "compressed" + data);
            save.createNewFile();
            try (FileWriter write = new FileWriter( pathToRootDir + "compressed" + data)) {
                int ch;
                int buffer = 0;
                int b;
                int bits = 0;
                while ((ch = br.read()) != -1) { 
                    for(int i = 0; i < codes[ch].length(); i++){
                        if(codes[ch].charAt(i) == '0')
                            b = 0;
                        else
                            b = 1;    
                            
                        buffer = (buffer << 1) | b;
                            
                        bits++;
                        count++;
                        if (bits == 8) {
                            write.write(buffer);
                            buffer = 0;
                            bits = 0;
                            
                        }
                            
                    } 
                }
                while(bits != 0){
                    buffer = (buffer << 1) | 0;
                    bits++;
                    count++;
                        if (bits == 8) {
                            write.write(buffer);
                            buffer = 0;
                            bits = 0;
                            
                        }
                }
                   
            }
        } catch (IOException e) {
            System.out.println("Cannot read files from given directory1\n");
            return -1;
        }

        return count;
    }

    private int decompressedFile(String pathToRootDir, String data){
        int[] counter = new int[400];

        try (BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(pathToRootDir + "dictionary.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] w = line.split("\\s+");
                int w0 = Integer.parseInt(w[0]);
                int w1 = Integer.parseInt(w[1]);
                if(w0 > counter.length - 1){
                    int[] tmp = new int[w0+1];
                    for(int i = 0; i < counter.length; i++){
                        tmp[i] = counter[i];
                    }
                    counter = tmp;
                }
                counter[w0] = w1;
            }
        } catch (IOException e) {
            System.out.println("Couldn't find dictionary.txt file in given directory\n");
            return -1;
        }

        List<Node> tree = new ArrayList<>();
        initiateTree(tree, counter);
        
        Node root = tree.get(0);
        String[] codes = new String[counter.length];
        root.buildCodes(codes);

        int count = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(pathToRootDir + "compressed" + data)))) {
            File save = new File(pathToRootDir + "decompressed" + data);
            save.createNewFile();
            try (FileWriter write = new FileWriter(pathToRootDir + "decompressed" + data)) {
                Node tmp = root;
                int ch;
                String s = "";
                while ((ch = br.read()) != -1) {
                    for(int i = 0; i < 8; i++){
                        if((ch & 1) == 1)
                            s = '1' + s;
                        
                        else
                            s = '0' + s;

                        ch = ch >> 1;  
                    }
                    for(int i = 0; i < s.length(); i++){
                        if(s.charAt(i) == '0')
                            tmp = tmp.left;
                        
                        else
                            tmp = tmp.right;
                        
                        if(tmp.left == null && tmp.right == null){
                            write.write((char)tmp.getCh());
                            tmp = root;
                            count++;
                        }
                    }
                    s = "";
                    
                }
            }
        } catch (IOException e) {
            System.out.println("compressed file is nessesary to decompress file\n");
            return -1;
        }
        return count;
    }

    private void buildDict(String pathToRootDir, int[] counter){
        File save = new File(pathToRootDir + "dictionary.txt");
            try {
                save.createNewFile();
            } catch (IOException e) {
                System.out.println("Couldn't create new file\n");
            }
            try (FileWriter write = new FileWriter( pathToRootDir + "dictionary.txt")) {
                for(int i = 0; i < counter.length; i++){
                    if(counter[i] != 0){
                        write.write(i + " " +counter[i] + "\n");
                    }
                }
            }
            catch (IOException e) {
                System.out.println("Could't write to file\n");
            }
    }

    private void initiateTree(List<Node> tree, int[] counter){
        for (int i = 0; i < counter.length; i++)
            if(counter[i] != 0)
                tree.add(new Node(counter[i], i));
        
        while(tree.size() > 1)
            buildTree(tree);   
    }
    
}

