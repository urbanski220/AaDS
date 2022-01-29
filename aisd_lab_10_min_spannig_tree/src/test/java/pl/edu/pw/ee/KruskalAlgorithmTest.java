package pl.edu.pw.ee;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KruskalAlgorithmTest {
    @Test
    public void MST_class_data_Test(){
        KruskalAlgorithm prim = new KruskalAlgorithm();
        String mst = prim.findMST("src/test/java/pl/edu/pw/ee/data/class_data.txt");
        assertEquals("c_4_h|f_7_g|g_7_b|f_8_a|f_9_e|h_10_d|e_12_c|", mst);
    }

    @Test
    public void MST_small_data_Test(){
        KruskalAlgorithm prim = new KruskalAlgorithm();
        String mst = prim.findMST("src/test/java/pl/edu/pw/ee/data/small_data.txt");
        assertEquals("0_12_1|1_13_2|", mst);
    }

    @Test
    public void MST_correct_small_data_Test(){
        KruskalAlgorithm prim = new KruskalAlgorithm();
        String mst = prim.findMST("src/test/java/pl/edu/pw/ee/data/correct_small_data.txt");
        assertEquals("B_1_C|C_1_D|A_3_B|D_7_E|", mst);
    }

    @Test (expected = IllegalArgumentException.class)
    public void MST_empty_data_Test(){
        KruskalAlgorithm prim = new KruskalAlgorithm();
        prim.findMST("src/test/java/pl/edu/pw/ee/data/empty.txt");
    }

    @Test 
    public void MST_wrong_directory_Test(){
        KruskalAlgorithm prim = new KruskalAlgorithm();
        String mst = prim.findMST("src/test/java/pl/edu/pw/ee/data/correct_small_data.txt1");
        assertEquals(null, mst);
        
    }
 
    @Test (expected = IllegalArgumentException.class)
    public void MST_null_directory_Test(){
        KruskalAlgorithm prim = new KruskalAlgorithm();
        prim.findMST(null);
    }

    @Test
    public void MST_not_connected_data_Test(){
        KruskalAlgorithm prim = new KruskalAlgorithm();
        String mst = prim.findMST("src/test/java/pl/edu/pw/ee/data/not_connected_data.txt");
        assertEquals(null, mst);
    }

}
