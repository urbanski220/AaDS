package pl.edu.pw.ee;

class LongestCommonSubsequence {

    private String first;
    private String second;
    private int[][] tab;
    private String[][] toDisplay;

    public LongestCommonSubsequence(String firstStr, String secondStr){
        if(firstStr == null || secondStr == null){
            throw new IllegalArgumentException("Given strings cannot be null!");
        }
        first = secondStr;
        second = firstStr;
        tab = new int[first.length()+1][second.length()+1];
        toDisplay = new String[first.length()+2][second.length()+2];
        filldisplay();
    }

    

    public String findLCS(){
        int x = first.length();
        int y = second.length();
        String revLCS = "";
        String LCS = "";
        for(int i = 1; i < x+1; i++ ){
            for(int j = 1; j < y+1; j++){
                if(first.charAt(i-1) == second.charAt(j-1)){
                    tab[i][j] = tab[i-1][j-1] + 1;
                    toDisplay[i+1][j+1] = "\\" + Integer.toString(tab[i][j]);
                }
                else{
                    if(tab[i-1][j] >= tab[i][j-1]){
                        tab[i][j] = tab[i-1][j];
                        toDisplay[i+1][j+1] = "^" + Integer.toString(tab[i][j]);
                    }
                    else{
                        tab[i][j] = tab[i][j-1];
                        toDisplay[i+1][j+1] = "<" + Integer.toString(tab[i][j]);
                    }
                }
            }
        }
        int i = x;
        int j = y;
        while(i != 0 && j != 0){
            if(tab[i][j] > tab[i-1][j-1]){
                if(tab[i][j] > tab[i-1][j] && tab[i][j] > tab[i][j-1]){
                    revLCS = revLCS + first.charAt(i-1);
                    i--;
                    j--;
                }
                else{
                    if(tab[i][j] == tab[i-1][j])
                        i--;
                    if(tab[i][j] == tab[i][j-1])
                        j--;
                }
            }
            else{
                i--;
            }
                
        }
        
        for(int k = 0; k < revLCS.length(); k++){
            LCS = revLCS.charAt(k) + LCS;
        }

	    return LCS;
    }

    public void display(){
	    int x = first.length();
        int y = second.length();
        for(int i = 0; i < x+2; i++ ){
            for(int j = 0; j < y+2; j++){
                switch (toDisplay[i][j]) {
                    case "\t": System.out.print("\\t"); break;
                    case "\b": System.out.print("\\b"); break;
                    case "\n": System.out.print("\\n"); break;
                    case "\r": System.out.print("\\r"); break;
                    case "\f": System.out.print("\\f"); break;
                    default: System.out.print(toDisplay[i][j] + " ");
                }
                for(int k = 0; k < toDisplay[x+1][y+1].length() - toDisplay[i][j].length(); k++){
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }

    }

    private void filldisplay(){
        toDisplay[0][0] = " ";
        toDisplay[0][1] = " ";
        toDisplay[1][0] = " ";
        for(int i = 2; i < first.length() + 2; i++)
            toDisplay[i][0] = String.valueOf(first.charAt(i-2));

        for(int i = 2; i < second.length() + 2; i++)
            toDisplay[0][i] = String.valueOf(second.charAt(i-2));

        for(int i = 1; i < first.length() + 2; i++)
            toDisplay[i][1] = "0";

        for(int i = 1; i < second.length() + 2; i++)
            toDisplay[1][i] = "0";
    }

}
