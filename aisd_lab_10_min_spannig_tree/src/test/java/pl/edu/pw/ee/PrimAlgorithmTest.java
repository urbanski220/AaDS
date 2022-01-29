package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class PrimAlgorithmTest {
    @Test
    public void MST_class_data_Test(){
        PrimAlgorithm prim = new PrimAlgorithm();
        String mst = prim.findMST("src/test/java/pl/edu/pw/ee/data/class_data.txt");
        assertEquals("f_8_a|f_7_g|g_7_b|f_9_e|f_9_e|e_12_c|c_4_h|", mst);
    }

    @Test
    public void MST_small_data_Test(){
        PrimAlgorithm prim = new PrimAlgorithm();
        String mst = prim.findMST("src/test/java/pl/edu/pw/ee/data/small_data.txt");
        assertEquals("0_12_1|1_13_2|", mst);
    }

    @Test
    public void MST_correct_small_data_Test(){
        PrimAlgorithm prim = new PrimAlgorithm();
        String mst = prim.findMST("src/test/java/pl/edu/pw/ee/data/correct_small_data.txt");
        assertEquals("A_3_B|B_1_C|C_1_D|D_7_E|", mst);
    }

    @Test (expected = IllegalArgumentException.class)
    public void MST_empty_data_Test(){
        PrimAlgorithm prim = new PrimAlgorithm();
        prim.findMST("src/test/java/pl/edu/pw/ee/data/empty.txt");
    }

    @Test (expected = IllegalArgumentException.class)
    public void MST_wrong_directory_Test(){
        PrimAlgorithm prim = new PrimAlgorithm();
        prim.findMST("src/test/java/pl/edu/pw/ee/data/correct_small_data.txt1");
        
    }
 
    @Test (expected = IllegalArgumentException.class)
    public void MST_null_directory_Test(){
        PrimAlgorithm prim = new PrimAlgorithm();
        prim.findMST(null);
    }

    @Test
    public void MST_not_connected_data_Test(){
        PrimAlgorithm prim = new PrimAlgorithm();
        String mst = prim.findMST("src/test/java/pl/edu/pw/ee/data/not_connected_data.txt");
        assertEquals(null, mst);
    }

}
