package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RbtMapTest {
    RbtMap<String, Integer> tree = new RbtMap<>();
    

    @Test
    public void setValueTest() {
        tree.setValue("c", 1);
        tree.setValue("z", 2);
        tree.setValue("l", 3);
        tree.setValue("o", 4);
        tree.setValue("w", 5);
        tree.setValue("i", 6);
        tree.setValue("e", 7);
        tree.setValue("k", 8);
    }

    @Test
    public void getValueTest() {
        tree.setValue("c", 1);
        tree.setValue("z", 2);
        tree.setValue("l", 3);
        tree.setValue("o", 4);
        tree.setValue("w", 5);
        tree.setValue("i", 6);
        tree.setValue("e", 7);
        tree.setValue("k", 8);
        assertEquals(2, (int) tree.getValue("z"));
        assertEquals(1, (int) tree.getValue("c"));
        assertEquals(7, (int) tree.getValue("e"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void NullNodeKeyTest() {
        tree.setValue(null, 234);
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void NullNodeValueTest() {
        tree.setValue("a", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void NullKeyInGetValueTest() {
        tree.getValue(null);
    }

    @Test
    public void NoSuchValueInATreeTest() {
        assertEquals(null, tree.getValue("o"));
    }

    @Test
    public void ValueOverwritingTest() {
        tree.setValue("a", 1);
        tree.setValue("b", 2);
        tree.setValue("c", 3);
        

        tree.setValue("b", 7);
        assertEquals(7, (int) tree.getValue("b"));
    }
}


