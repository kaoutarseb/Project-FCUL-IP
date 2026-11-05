
public class FuncoesKaoutar {

	public static void main(String[] args) {
    }
    
    /**
     * Calcula a linha de uma posicao do tabuleiro
     * @param pos a posicao
     * @param numColumns o número de colunas
     * @return o número de linha da posicao
     * @requires {@code pos >= 0 && numColumns > 0}
     * @ensures {@code \result < pos}
     */
    
    static int rowOfPositions(int pos, int numColumns){
        int linha= (pos/numColumns)+1;
        return linha;
    }




     /**
     * Calcula a coluna de uma posicao do tabuleiro
     * @param pos a posicão
     * @param numColumns o número de colunas
     * @return o número de coluna da posicao
     * @requires {@code pos >= 0 && numColumns > 0}
     * @ensures {@code \result < numColumns}
     */
    
    static int columnsOfPositions(int pos, int numColumns){
        int coluna= (pos%numColumns)+1;
        return coluna;
    }



    /**
     * Verifica se stc representa uma solucao-tabuleiro-em-construcao
     * @param stc o string a verificar
     * @return true se é  uma solução-tabuleiro-em-construção e false se não á  uma solução-tabuleiro-em-construção
     */

    
    static boolean isValidBoardConstrucion( String stc){
        if (stc.isEmpty())
            return false;
       
        for (int i= 0 ; i < stc.length(); i++){
            if ( stc.charAt(i) != '.' && !isFillingChar(stc.charAt(i)) )
                return false;
        }
       
    }




    /**
     * Verifica se todas as posicoes estao preenchidas
     * @param stc o String a verificar
     * @return true se todas as posicões estão preenchidas e false se há posicões que ainda não estão preenchidas
     */
    static boolean isFilled( String stc){
        boolean filled;
        for (int i = 0 ; i < stc.lentgh();  i++){
            filled = stc.charAt(i) == '.';
            if (filled)
                return false;
                
        }
        return true;
    }




    /**
     * 
     * @param row o número da linha
     * @param stc os caráters da linha row
     * @param numColumns o número das colunas
     * @return retorna o número de peças de água (‘X’) já preenchidas na linha row da solução-tabuleiro-em-construção stc.
     */
    static int countWaterPiecesInRow (int row, String stc, int numColumns  ){
        int contador = 0;
        int start = ((row-1)  * numColumns);
        int end = (row * numColumns )-1; 
        for (int i = start; i <= end ; i++){
            if (stc.charAt(i) == 'X')
                contador ++;
        }
        return contador;
    }
    
    
    static int countWaterPiecesInCol (int col, String stc, int numColumns  ){
        int contador = 0;
        for (int i = 0; i <= col ; i++){
            if (stc.charAt((col-1)+(5*i)) == 'X')
                contador ++;
        }
        return contador;
    }
    static int isValidMove (String board, String stc, char c, int pos ){
        int contador = 0;
        for (int i = 0; i <= col ; i++){
            if (stc.charAt((col-1)+(5*i)) == 'X')
                contador ++;
        }
        return contador;
    }

    
}