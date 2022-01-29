package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class HuffmanTest {
    

    @Test 
    public void wrongDirectoryDecompressTest(){
        Huffman huffman = new Huffman();
        String file = "src/test/java/pl/edu/pw/ee/dataTest15/";
        int actual = huffman.huffman(file, false);
        assertEquals(-1, actual);
    }

    @Test 
    public void wrongDirectoryCompressTest(){
        Huffman huffman = new Huffman();
        String file = "src/test/java/pl/edu/pw/ee/dataTest15/";
        int actual = huffman.huffman(file, true);
        assertEquals(-1, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void nullDirectoryTest(){
        Huffman huffman = new Huffman();
        huffman.huffman(null, false);
    }

    @Test
    public void borderValuesTest(){
        Huffman huffman = new Huffman();
        String file = "src/test/java/pl/edu/pw/ee/dataTest2/";
        int compress = huffman.huffman(file, true);
        int decompress = huffman.huffman(file, false);

        assertEquals(96, compress);
        assertEquals(31, decompress);
    }

    @Test 
    public void niemanieTest(){
        Huffman huffman = new Huffman();
        String file = "src/test/java/pl/edu/pw/ee/dataTest1/";
        int compress = huffman.huffman(file, true);
        int decompress = huffman.huffman(file, false);
    
        assertEquals(9280, compress);
        assertEquals(1966, decompress);
    }

    @Test 
    public void _100000_words_Test(){
        Huffman huffman = new Huffman();
        String file = "src/test/java/pl/edu/pw/ee/dataTest3/";
        int compress = huffman.huffman(file, true);
        int decompress = huffman.huffman(file, false);

        assertEquals(3511352, compress);
        assertEquals(816943, decompress);
    }

    @Test
    public void NoDictionaryDecompressTest(){
        Huffman huffman = new Huffman();
        String file = "src/test/java/pl/edu/pw/ee/dataTest4/";
        int actual = huffman.huffman(file, false);
        assertEquals(-1, actual);
    }

    @Test 
    public void NoDataFileCompressTest(){
        Huffman huffman = new Huffman();
        String file = "src/test/java/pl/edu/pw/ee/dataTest5/";
        int actual = huffman.huffman(file, true);
        assertEquals(-1, actual);
    }

    @Test
    public void NoCompressedFileToDecompressTest(){
        Huffman huffman = new Huffman();
        String file = "src/test/java/pl/edu/pw/ee/dataTest6/";
        int actual = huffman.huffman(file, false);
        assertEquals(-1, actual);
    }
    
}
