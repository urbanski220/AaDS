package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DeterministicFiniteAutomatonTextSearchTest {

    @Test
    public void FindFirstTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch("ABC");
        int actual = fsa.findFirst("CBABCAB");
        assertEquals(2, actual);
    }

    @Test
    public void FindAllTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch("ABC");
        int [] actual = fsa.findAll("ABCCBBABCBCABCC");
        int [] expected = {0,6,11};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void SpecialCharactersTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch("ąbć\n");
        int actual = fsa.findFirst("ąbć\nbbć");
        assertEquals(0, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullPatternTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullTextFindAllTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch("pattern");
        fsa.findAll(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullTextFindFirstTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch("pattern");
        fsa.findFirst(null);
    }

    @Test
    public void NoKeyInMapTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch("DCDB");
        int actualFirst = fsa.findFirst("XYZDCDBABCDCDBXYZ");
        int [] actualAll = fsa.findAll("XYZDCDBABCDCDBXYZ");
        int [] expectedAll = {3,10};
        assertEquals(3, actualFirst);
        assertArrayEquals(expectedAll, actualAll);
    }

    @Test
    public void NoPatternInTextTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch("pattern");
        int actualFirst = fsa.findFirst("abcdefghijklmnoprst");
        int [] actualAll = fsa.findAll("abcdefghijklmnoprst");
        int [] expectedAll = new int[0];
        assertEquals(-1, actualFirst);
        assertArrayEquals(expectedAll, actualAll);
    }

    @Test
    public void JoinedPatternsInTextTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch("CCC");
        int actualFirst = fsa.findFirst("CCCCCC");
        int [] actualAll = fsa.findAll("CCCCCC");
        int [] expectedAll = {0,1,2,3};
        assertEquals(0, actualFirst);
        assertArrayEquals(expectedAll, actualAll);
    }

    @Test (expected = IllegalArgumentException.class)
    public void EmptyPatternTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch("");
    }

    @Test
    public void EmptyTextTest(){
        DeterministicFiniteAutomatonTextSearch fsa = new DeterministicFiniteAutomatonTextSearch("pattern");
        int actualFirst = fsa.findFirst("");
        int [] actualAll = fsa.findAll("");
        int [] expectedAll = new int[0];
        assertEquals(-1, actualFirst);
        assertArrayEquals(expectedAll, actualAll);
    }
}
