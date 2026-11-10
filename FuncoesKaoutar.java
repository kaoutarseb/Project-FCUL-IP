
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
    
    /**
     * retorna o número de peças de água (‘X’) já preenchidas na coluna col.
     * @param col o número da coluna
     * @param stc uma solução-tabuleiro-em-construção,
     * @param numColumns o número total das colunas
     * @return retorna o número de peças de água na coluna
     */
    static int countWaterPiecesInCol (int col, String stc, int numColumns  ){
        int contador = 0;
        int posicao = col -1 ;
        while(posicao < stc.length()){
            if (stc.charAt(posicao) == 'X')
                contador ++;
            posicao +=numColumns;
        }
        return contador;
    }

    /**
     * Verifica se a jogada é válida
     * @param board o tabuleiro inicial 
     * @param stc uma solução-tabuleiro-em-construção
     * @param c a ação jogada
     * @param pos uma posição da jogada
     * @return retorna true se a jogada é válida e false se é inválida
     */
    static boolean isValidMove (String board, String stc, char c, int pos ){
  
        if ((pos < 0 && pos != -1) || pos >= stc.length() )
                return false;

        if (isFillingChar(board.charAt(pos)) );
            return false;

        if (!isFillingChar( c ))
            return false;
        
        return true;
    }

    /**
     * Retorna uma string que representa a solução-tabuleiro-em-construção que resulta de efetuar a jogada em stc.
     * @param stc  uma soluçãotabuleiro-em-construção,
     * @param c uma jogada
     * @param pos uma posiçao do tabuleiro
     * @return  a solução-tabuleiro-em-construção que resulta de efetuar a jogada
     */
    static String playMove (String stc, char c, int pos){
        StringBuilder st = new StringBuilder(stc);
        st.setCharAt(pos, c);
        return st.toString();
    }
    


    static boolean isSolution (int cols, int rows, String board, String rowClues, String colClues, String stc ){
        return isFilled( stc) 
                && sameSize(stc, board) 
                && samePieces(stc, board) 
                && cluesMatch( cols, rowClues, colClues, stc);
        
    }
    
    
    static boolean sameSize(String stc, String board){ 
        for (int i = 0; i < stc.length() ; i++ ){
            if(stc.length() != board.length() || stc.charAt(i)== '.')
                return false;
        }
        return true;
    }

    static boolean samePieces(String stc, String board){ 
        for (int i = 0; i < stc.length() ; i++ ){
            if( board.charAt(i)!= '.' &&  stc.charAt(i)== board.charAt(i))
                return false;
        }
        return true;
    }

    static boolean cluesMatch( int cols, String rowClues, String colClues, String stc){ 
        for (int i = 0; i < rowClues.length() ; i ++){
            if (countShipPiecesInRow( i+1, stc, cols) != Character.getNumericValue(rowClues.charAt(i)))
                return false;
        }

        for (int i = 0; i < colClues.length() ; i ++){
            if (countShipPiecesInCol( i+1, stc, cols) != Character.getNumericValue(colClues.charAt(i)))
                return false;
        }
        return true;
    }

    // lets go 

    //fake main
    static boolean isValidShips( int rows, int cols, String stc){ 
    for (int r = 0 ; r < rows; r++){
        for (int c = 0 ;c < cols ; c++){
            if (!wellSurrounded( stc, rows, cols, r, c))
                return false;
            

        }
    }
    }
    static char character(String stc, int rows, int cols, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) return 'X';
        return stc.charAt(r * cols + c);
    }
    /**
     * 
     * @param c
     * @param r
     * @param rows
     * @param cols
     * @param stc
     * @return
     */
    static boolean diagonalIsWater( int c, int r, int rows, int cols, String stc){
        return character(stc, rows, cols, r-1, c-1) == 'X' && character(stc, rows, cols, r-1, c+1) == 'X' 
        && character(stc, rows, cols, r+1, c-1) == 'X' && character(stc, rows, cols, r+1, c+1) == 'X';
    }

    /**
     * 
     * @param stc
     * @param rows
     * @param cols
     * @param r
     * @param c
     * @return
     */
    static boolean wellSurrounded( String stc, int rows, int cols, int r, int c){
        char pos = character(stc, rows, cols, r, c);
        char cima = character(stc, rows, cols, r-1, c);
        char baixo = character(stc, rows, cols, r+1, c);
        char esquerda = character(stc, rows, cols, r, c-1);
        char direita = character(stc, rows, cols, r, c+1);
        if( !diagonalIsWater( c, r, rows, cols, stc))
            return false;
        if(pos == 'C'){
            return esquerda == 'X' && direita == 'X' && cima == 'X' && baixo == 'X';
        }
        if(pos == 'L'){
            return esquerda == 'X' && (direita== 'R' || direita== 'M' ) && cima == 'X' && baixo == 'X';
        }
        if(pos == 'R'){
            return (esquerda == 'L'|| esquerda == 'M' )  && direita == 'X' && cima == 'X' && baixo == 'X';
        }
        if(pos == 'B'){
            return esquerda== 'X'  && direita == 'X'  && (cima == 'T' || cima == 'M' ) && baixo == 'X';
        }
        if (pos == 'T'){
            return esquerda == 'X'  && direita == 'X' && cima == 'X'  && (baixo== 'B' || baixo == 'M' );
        }
        if (pos == 'M'){
            boolean vertical = esquerda == 'X'  && direita == 'X' && (cima == 'T' || cima == 'M' )  && (baixo == 'B' || baixo == 'M' );
            boolean horizontal = (esquerda== 'L' || esquerda == 'M' )  && (direita == 'R' || direita== 'M' ) && cima == 'X'  && baixo == 'X' ;
            return vertical || horizontal;
        }
    }

    /**
     * 
     * @param stc
     * @param c
     * @param r
     * @param rows
     * @param cols
     * @return
     */
    static boolean allShips(String stc, int c, int r, int rows, int cols){
        char pos = character(stc, rows, cols, r, c);
        boolean tamanho1 = false;
        boolean tamanho2 = false;
        boolean tamanho3 = false;
        boolean tamanho4 = false;
    
        if (pos == 'C' && tamanho1 == false)
            tamanho1 = true;
        else if (pos == 'C' && tamanho1 == true )
            return false;
        
            
        if (pos == 'T' && character(stc, rows, cols, r + 1, c) == 'B')
            tamanho2 = true;
        else if (pos == 'C' && tamanho2 == true )
            return false;
        
        if (pos == 'L' && character(stc, rows, cols, r + 1, c) == 'R')
            tamanho2 = true;
        if (pos == 'M')
        {
            if (character(stc, rows, cols, r + 1, c) == 'M' || character(stc, rows, cols, r - 1, c) == 'M')
                tamanho4 = true;
            else if (character(stc, rows, cols, r, c + 1) == 'M' || character(stc, rows, cols, r, c - 1) == 'M')
                tamanho4 = true;
            else
                tamanho3 = true;
        }                
        return tamanho1 && tamanho2 && tamanho3 && tamanho4;
    }
}