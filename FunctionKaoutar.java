
public class Funcoes {

	public static void main(String[] args) {
    }
    
    /**
     * Calcula a linha de uma posicao do tabuleiro
     * @param pos a posicao
     * @param numColumns o numero de colunas
     * @return o numero de linha da posicao
     * @requires {@code pos >= 0 && numColumns > 0}
     * @ensures {@code \result < pos}
     */
    

   
    static int rowOfPositions(int pos, int numColumns){
        int linha= (pos/numColumns)+1;
        return linha;
    }
     /**
     * Calcula a coluna de uma posicao do tabuleiro
     * @param pos a posicao
     * @param numColumns o numero de colunas
     * @return o numero de coluna da posicao
     * @requires {@code pos >= 0 && numColumns > 0}
     * @ensures {@code \result < numColumns}
     */
    
    static int columnsOfPositions(int pos, int numColumns){
        int coluna= (pos%numColumns)+1;
        return coluna;
    }

    /**
     * Verifica se stc representa uma solucao-tabuleiro-em-construcao
     * @param stc o strinfg a verificar
     * @return true se e  uma solucao-tabuleiro-em-construcao e false se nao e  uma solucao-tabuleiro-em-construcao
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
     * @param stc a verificar
     * @return true se todas as posicoes estao preenchidas e false se ha posicoes que ainda nao estao prrenchidas
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
    //TC.XXT
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