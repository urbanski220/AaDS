package pl.edu.pw.ee;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class LongestCommonSubsequenceTest 
{
    @Test
    public void LCSTestForWordsGivenInClass(){
        String firstStr = "SPAŁEM";
        String secondStr = "PIŁEM";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);
        String actual = lcs.findLCS();
        assertEquals("PŁEM", actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullString1Test(){
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("string1", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullString2Test(){
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(null, "string2");
    }

    @Test (expected = IllegalArgumentException.class)
    public void NullStringsTest(){
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(null, null);
    }

    @Test
    public void LCSSameStringsTest(){
        String s = "longestcommonsubsequence";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(s, s);
        String actual = lcs.findLCS();
        assertEquals(s, actual);
    }

    @Test
    public void NoLCSTest(){
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("aaaaaa", "bbbbbb");
        String actual = lcs.findLCS();
        assertEquals("", actual);

    }


    @Test
    public void LCSTestForStringsGivenInIsod(){
        String firstStr = "P\r\nYTANIA\t_CIĄGLE_TE_S\n\rAME";
        String secondStr = "W\r\nRÓCĘ\t_CZY_Z\n\rOSTANĘ";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstStr, secondStr);
        String actual = lcs.findLCS();
        System.out.println(actual);
        //assertEquals("cz__raie", actual);
        lcs.display();
    }

    @Test
    public void EscapeCharactersDisplayTest(){
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("nie\twchodz\flagodnie", "do\rtej\bdobrej_nocy");
        String actual = lcs.findLCS();
        lcs.display();
        assertEquals("dode", actual);
    }
}
