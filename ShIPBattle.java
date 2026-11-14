import java.util.Scanner;

/**
 * A classe {@code ShIPBattle} implementa a lógica para ler, validar e jogar
 * o jogo ShIPBattle (uma adaptação de Bimaru / Batalha Naval Solitário) através
 * de uma interface textual na consola, no âmbito da primeira parte do projeto
 * de Introdução à Programação (IP) 2026/2027.
 * 
 * <p>O jogo consiste numa grelha retangular (n x m) onde o jogador deve
 * posicionar uma armada composta por quatro navios (um couraçado de tamanho 4,
 * um cruzador de tamanho 3, um contratorpedeiro de tamanho 2 e um submarino de
 * tamanho 1), respeitando as pistas numéricas de cada linha e coluna, as peças
 * fixas iniciais e a regra de que dois navios não se podem tocar, nem mesmo
 * na diagonal.</p>
 * 
 * <p>Para compilar e executar o programa:</p>
 * <pre>
 *     javac ShIPBattle.java
 *     java ShIPBattle
 * </pre>
 * 
 * <p>Opcionalmente, podem ser passados os 5 argumentos do tabuleiro inicial
 * diretamente na linha de comandos (linhas, colunas, tabuleiro, pistas de linha
 * e pistas de coluna).</p>
 *
 * @author Gustavo Siqueira (fc67377)
 * @author Kaoutar Sebbagh (fc67410)
 * @version 1.0
 */

public class ShIPBattle {

	/**
	 * Verifica se um dado carácter é um carácter que corresponde a uma peça de navio
	 * @param c
	 * @return True se corresponder a uma peça de navio False se não
	 */
	public static  boolean isFillingChar(char c)
	{
		return isShipPiece(c) || c == 'X';
	}

	/**
	 * Verifica se um dado carácter é  se é ‘C’, ‘L’, ‘R’, ‘B’, ‘T’, ou ‘M’.
	 * @param c Caractere a vericar
	 * @return True se corresponder a uma peça de navio False se não
	 */
	
	public static boolean isShipPiece(char c)
	{
		return c == 'C' || c == 'L' || c == 'R' || c == 'B' || c == 'T' || c == 'M';
	}

	/**
	 * Calcula o número de linhas de um tabuleiro.
	 * @param size número total de posições do tabuleiro
     * @param numColumns número de colunas do tabuleiro
     * @requires {@code size > 0 && numColumns > 0 && size % numColumns == 0}
     * @ensures {@code resultado * numColumns == size}
     * @return o número de linhas do tabuleiro
	 */

	public static  int numberRows(int size, int numColumns)
	{
		return (size / numColumns);
	}

	/**
	 * Calcula a posição num tabuleiro com numColumns colunas, dada a linha row e a coluna column
	 * @param row numero de linha
	 * @param column Coluna atualizada 
	 * @param numColumns Numero total de colunas
	 * @return a posição (numerada a partir de 0) na linha row e coluna column
     * @ensures  {@code rowOfPosition(resultado, numColumns) == row && columnOfPosition(resultado, numColumns) == column}
     */

	public static  int positionNumber(int row, int column, int numColumns)
	{
		return ((row - 1) * numColumns + column - 1);
	}

	/**
	 * * Conta o número de peças de navio já preenchidas numa linha.
     *
     * @param row linha a considerar (numerada a partir de 1)
     * @param stc solução-tabuleiro-em-construção
     * @param numColumns número de colunas do tabuleiro
     * @requires isValidBoardInConstruction(stc) && numColumns > 0
     *           && stc.length() % numColumns == 0
     *           && 1 <= row <= numberRows(stc.length(), numColumns)
     * @return o número de peças de navio na linha row de stc
	 */

	public static int countShipPiecesInRow(int row, String stc, int numColumns)
	{
		int start = (row - 1) * numColumns;
		int numPieces = 0;
	
		do
		{
			numPieces += isShipPiece(stc.charAt(start++)) ? 1 : 0;
		} while (start % numColumns != 0);
		return numPieces;
	}

	/**
	  * Conta o número de peças de navio já preenchidas numa coluna.
     *
     * @param col coluna a considerar (numerada a partir de 1)
     * @param stc solução-tabuleiro-em-construção
     * @param numColumns número de colunas do tabuleiro
     * @requires isValidBoardInConstruction(stc) && numColumns > 0
     *           && stc.length() % numColumns == 0 && 1 <= col <= numColumns
     * @return o número de peças de navio na coluna col de stc
	 */

	public static int countShipPiecesInCol(int col, String stc, int numColumns)
	{
		int numPieces = 0;
		int posicao = col - 1;
		while (posicao < stc.length())
		{
			numPieces += isShipPiece(stc.charAt(posicao)) ? 1 : 0;
			posicao += numColumns;
		}
		return numPieces;
	}

	/**
	 * Conta o número de peças de água ('X') já preenchidas numa linha.
     *
     * @param row linha a considerar (numerada a partir de 1)
     * @param stc solução-tabuleiro-em-construção
     * @param numColumns número de colunas do tabuleiro
     * @requires isValidBoardInConstruction(stc) && numColumns > 0
     *           && stc.length() % numColumns == 0
     *           && 1 <= row <= numberRows(stc.length(), numColumns)
     * @return o número de peças de água na linha row de stc
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
	* Conta o número de peças de água ('X') já preenchidas numa coluna.
     *
     * @param col coluna a considerar (numerada a partir de 1)
     * @param stc solução-tabuleiro-em-construção
     * @param numColumns número de colunas do tabuleiro
     * @requires isValidBoardInConstruction(stc) && numColumns > 0
     *           && stc.length() % numColumns == 0 && 1 <= col <= numColumns

	 */

	public static int countWaterPiecesInCol(int col, String stc, int numColumns)
	{
		int numPieces = 0;
	
		while (col < stc.length())
		{
			numPieces += stc.charAt(col - 1) == 'X' ? 1 : 0;
			col += numColumns;
		}
		return numPieces;
	}
    /**
     * Verifica várias condições necessárias para stc ser um tabuleiro inicial
     * válido: tamanho de stc igual a rows * cols; tamanhos de rowClues e
     * colClues iguais a rows e cols; pistas formadas só por dígitos; e, para
     * cada linha/coluna, existirem posições suficientes para as peças de navio
     * da pista sem que as peças já colocadas a ultrapassem.
     *
     * @param rows número de linhas do tabuleiro
     * @param cols número de colunas do tabuleiro
     * @param stc solução-tabuleiro-em-construção com o tabuleiro inicial
     * @param rowClues pistas de linha
     * @param colClues pistas de coluna
     * @requires rows > 0 && cols > 0 && isValidBoardInConstruction(stc)
     * @return true se todas as condições se verificam; false caso contrário
     */
	public static boolean isBoard(int rows, int cols, String stc, String rowClues, String colClues)
	{
		if (rows * cols != stc.length() || rows * cols < 10)
			return false;
		if (rowClues.length() != rows || colClues.length() != cols)
			return false;

		for (int i = 0; i < rowClues.length(); i++)
		{
			if (!Character.isDigit(rowClues.charAt(i)) || Character.getNumericValue(rowClues.charAt(i)) > cols || countShipPiecesInRow(i + 1, stc, cols) > Character.getNumericValue(rowClues.charAt(i)))
				return false;
		}

		for (int i = 0; i < colClues.length(); i++)
		{
			if (!Character.isDigit(colClues.charAt(i)) || Character.getNumericValue(colClues.charAt(i)) > rows  || countShipPiecesInCol(i + 1, stc, cols) > Character.getNumericValue(colClues.charAt(i)))
				return false;
		}

		return true;
	}
    
    /**
     * Calcula a linha de uma posicao do tabuleiro
     * @param pos a posicao no tabuleiro
     * @param numColumns o número de colunas
     * @return a linha da posição
     * @requires {@code pos >= 0 && numColumns > 0}
     * @ensures {@code \result < pos}
     */
    
    static int rowOfPosition(int pos, int numColumns){
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
    
    static int columnOfPosition(int pos, int numColumns){
        int coluna= (pos%numColumns)+1;
        return coluna;
    }



    /**
     * Verifica se stc representa uma solucao-tabuleiro-em-construção
     * @param stc o string a verificar
     * @return true se é  uma solução-tabuleiro-em-construção e false se não á  uma solução-tabuleiro-em-construção
     */

    
    static boolean isValidBoardInConstruction( String stc){
        if (stc == null || stc.isEmpty())
            return false;
       
        for (int i= 0 ; i < stc.length(); i++){
            if ( stc.charAt(i) != '.' && !isFillingChar(stc.charAt(i)) )
                return false;
        }
       return true;
    }

    /**
     * Verifica se todas as posicoes estao preenchidas
     * @param stc solução-tabuleiro-em-construção
     * @requires  solução-tabuleiro-em-construção válido
     * @return true se todas as posicões estão preenchidas e false se há posicões que ainda não estão preenchidas
     */
    static boolean isFilled( String stc){
        boolean filled;
        for (int i = 0 ; i < stc.length();  i++){
            filled = stc.charAt(i) == '.';
            if (filled)
                return false;
                
        }
        return true;
    }

    /**
     * Verifica se a jogada é válida * não está preenchida no tabuleiro inicial board, e c é uma peça de navio,
     * 
    * @param stc solução-tabuleiro-em-construção atual
     * @param board tabuleiro inicial
     * @param pos posição da jogada
     * @param c ação da jogada
     * @requires {@code isValidBoardInConstruction(stc)
     *           && isValidBoardInConstruction(board)
     *           && stc.length() == board.length()}
     * @return true se a jogada é válida; false caso contrário
     */
    static boolean isValidMove(String stc, String board, int pos, char c ){
  
        if (pos < 0|| pos >= stc.length() )
            return false;
        if (isFillingChar(board.charAt(pos)))
            return false;
        if (!isFillingChar(c))
            return false;
        
        return true;
    }

    /**
     * Retorna uma string que representa a solução-tabuleiro-em-construção que resulta de efetuar a jogada em stc.
     * @param stc  uma soluçãotabuleiro-em-construção,
     * @param c uma jogada
     * @param pos uma posiçao do tabuleiro
     *  @requires  {@code isValidBoardInConstruction(stc)
     *           && 0 <= pos < stc.length()
     *           && (isFillingChar(c) || c == '.')}
     * @return  a solução-tabuleiro-em-construção que resulta de efetuar a jogada
     */
    static String playMove (String stc, int pos, char c){
        StringBuilder st = new StringBuilder(stc);
        st.setCharAt(pos, c);
        return st.toString();
    }
    

    /**
     * Verifica se stc é uma solução do tabuleiro inicial board: stc tem o
     * mesmo tamanho de board e está totalmente preenchida; mantém as peças
     * iniciais de board; respeita as pistas de linha e de coluna; contém os
     * quatro navios (tamanhos 1, 2, 3 e 4); e todas as peças de navio estão
     * corretamente rodeadas (navios não se tocam, nem na diagonal).
     *
     * @param rows número de linhas do tabuleiro
     * @param cols número de colunas do tabuleiro
     * @param board tabuleiro inicial
     * @param rowClues pistas de linha
     * @param colClues pistas de coluna
     * @param stc solução-tabuleiro-em-construção a verificar
     * @requires {@code rows > 0 && cols > 0
     *           && isBoard(rows, cols, board, rowClues, colClues)
     *           && isValidBoardInConstruction(stc)}
     * @return true se stc é solução de board; false caso contrário
     */
    static boolean isSolution (int rows, int cols, String board, String rowClues, String colClues, String stc ){
        return isFilled( stc) 
                && sameSize(stc, board) 
                && samePieces(stc, board) 
                && cluesMatch( cols, rowClues, colClues, stc)
                && allShips(stc, rows, cols);
        
    }
    
    /**
     * Verifica se a solução-tabuleiro-em-construção tem o mesmo tamanho do
     * tabuleiro inicial e se está totalmente preenchida (sem pontos).
     *
     * @param stc solução-tabuleiro-em-construção a verificar
     * @param board tabuleiro inicial
     * @requires isValidBoardInConstruction(stc)
     *           && isValidBoardInConstruction(board)
     * @return true se stc.length() == board.length() e stc não contém '.';
     *         false caso contrário
     */
    static boolean sameSize(String stc, String board){ 
        for (int i = 0; i < stc.length() ; i++ ){
            if(stc.length() != board.length() || stc.charAt(i)== '.')
                return false;
        }
        return true;
    }

    /**
     * Verifica se todas as peças já preenchidas no tabuleiro inicial se
     * mantêm, nas mesmas posições, na solução-tabuleiro-em-construção.
     *
     * @param stc solução-tabuleiro-em-construção a verificar
     * @param board tabuleiro inicial
     * @requires {@code isValidBoardInConstruction(stc) && isValidBoardInConstruction(board)
     *           && stc.length() == board.length()}
     * @return true se, para cada posição preenchida em board, stc tem o mesmo
     *         carácter; false caso contrário
     */
    static boolean samePieces(String stc, String board){ 
        for (int i = 0; i < stc.length() ; i++ ){
            if( board.charAt(i)!= '.' &&  stc.charAt(i)!= board.charAt(i))
                return false;
        }
        return true;
    }


    /**
     * Verifica se o número de peças de navio em cada linha e em cada coluna
     * de stc coincide com a pista correspondente.
     *
     * @param cols número de colunas do tabuleiro
     * @param rowClues pistas de linha (um dígito por linha)
     * @param colClues pistas de coluna (um dígito por coluna)
     * @param stc solução-tabuleiro-em-construção a verificar
     * @requires {@code cols > 0 && isValidBoardInConstruction(stc) && rowClues e colClues só contêm dígitos
     *           && colClues.length() == col && stc.length() == rowClues.length() * cols}
     * @return true se todas as pistas de linha e de coluna são respeitadas;
     *         false caso contrário
     */
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

     /**
     * Devolve o carácter de uma posição do tabuleiro, dada por linha e coluna numeradas a partir de 0. Posições fora do tabuleiro são tratadas como
     * água, o que simplifica a verificação dos vizinhos de peças junto ao limite do tabuleiro.
     *
     * @param stc solução-tabuleiro-em-construção
     * @param rows número de linhas do tabuleiro
     * @param cols número de colunas do tabuleiro
     * @param r linha (a partir de 0), possivelmente fora do tabuleiro
     * @param c coluna (a partir de 0), possivelmente fora do tabuleiro
     * @requires {@code rows > 0 && cols > 0 && stc.length() == rows * cols}
     * @return o carácter de stc na posição (r, c) se estiver dentro do tabuleiro; 'X' caso contrário
     */
    static char character(String stc, int rows, int cols, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) return 'X';
        return stc.charAt(r * cols + c);
    }
    /**
     * Verifica se as quatro posições diagonais à volta de uma posição são água (posições fora do tabuleiro contam como água).
     *
     * @param c coluna da posição (a partir de 0)
     * @param r linha da posição (a partir de 0)
     * @param rows número de linhas do tabuleiro
     * @param cols número de colunas do tabuleiro
     * @param stc solução-tabuleiro-em-construção
     * @requires {@code rows > 0 && cols > 0 && stc.length() == rows * cols && 0 <= r < rows && 0 <= c < cols}
     * @return true se as quatro diagonais de (r, c) são água; false caso contrário
     */

    static boolean diagonalIsWater( int c, int r, int rows, int cols, String stc){
        return character(stc, rows, cols, r-1, c-1) == 'X' && character(stc, rows, cols, r-1, c+1) == 'X' 
        && character(stc, rows, cols, r+1, c-1) == 'X' && character(stc, rows, cols, r+1, c+1) == 'X';
    }

     /**
     * Verifica se a peça numa posição está corretamente rodeada, de acordo com o seu tipo: as diagonais têm de ser água e os vizinhos ortogonais
     * têm de ser água ou a continuação do navio (por exemplo, um 'L' tem água à esquerda, em cima e em baixo, e 'R' ou 'M' à direita).
     * Para a água ('X') devolve true.
     *
     * @param stc solução-tabuleiro-em-construção
     * @param rows número de linhas do tabuleiro
     * @param cols número de colunas do tabuleiro
     * @param r linha da peça (a partir de 0)
     * @param c coluna da peça (a partir de 0)
     * @requires {@code rows > 0 && cols > 0 && stc.length() == rows * cols && 0 <= r < rows && 0 <= c < cols }
     * @return true se a peça em (r, c) está bem rodeada; false caso contrário
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
        return true;
    }

    /**
     * Conta o número total de peças de navio numa solução-tabuleiro-em-construção.
     *
     * @param stc solução-tabuleiro-em-construção
     * @requires isValidBoardInConstruction(stc)
     * @return o número de peças de navio em stc
     */
    static int totalShipPieces(String stc) {
        int count = 0;
        for (int i = 0; i < stc.length(); i++) {
            if (isShipPiece(stc.charAt(i)))
                count++;
        }
        return count;
}

    /**
     * Verifica se todas as peças de navio estão bem rodeadas e se o tabuleiro tem todos os naviod
     * está completa: um navio de tamanho 1, um de tamanho 2, um de tamanho 3
     * e um de tamanho 4, cada um deles uma só vez.
     *
     * @param stc solução-tabuleiro-em-construção totalmente preenchida
     * @param rows número de linhas do tabuleiro
     * @param cols número de colunas do tabuleiro
     * @requires {@code rows > 0 && cols > 0 && stc.length() == rows * cols && isFilled(stc)}
     * @return true se todas as peças de navio estão bem rodeadas e existen exatamente a armada pedida; false caso contrário
     */
    static boolean allShips(String stc, int rows, int cols){
        
        
        boolean tamanho1 = false;
        boolean tamanho2 = false;
        boolean tamanho3 = false;
        boolean tamanho4 = false;
    
        for (int r = 0 ; r < rows; r++){
            for (int c = 0 ;c < cols ; c++){
                char pos = character(stc, rows, cols, r, c);
                if (pos != 'X' && !wellSurrounded( stc, rows, cols, r, c))
                    return false;
                if (pos == 'C' && tamanho1 == false)
                    tamanho1 = true;
                else if (pos == 'C' && tamanho1 == true )
                    return false;
                
                    
                if (pos == 'T' && character(stc, rows, cols, r + 1, c) == 'B')
                    tamanho2 = true;
                else if ((pos == 'T' && character(stc, rows, cols, r + 1, c) == 'B' ) && tamanho2 == true )
                    return false;
                
                if (pos == 'L' && character(stc, rows, cols, r, c+1) == 'R')
                    tamanho2 = true;
                else if ((pos == 'L' && character(stc, rows, cols, r, c+1) == 'R' ) && tamanho2 == true )
                    return false;

                if (pos == 'M')
                {
                    if (character(stc, rows, cols, r + 1, c) == 'M' || character(stc, rows, cols, r - 1, c) == 'M')
                        tamanho4 = true;
                    else if ((character(stc, rows, cols, r + 1, c) == 'M' || character(stc, rows, cols, r - 1, c) == 'M') && tamanho4 == true )
                        return false;

                    else if (character(stc, rows, cols, r, c + 1) == 'M' || character(stc, rows, cols, r, c - 1) == 'M')
                        tamanho4 = true;
                    else if ((character(stc, rows, cols, r, c + 1) == 'M' || character(stc, rows, cols, r, c - 1) == 'M') && tamanho4 == true )
                        return false;

                    else {
                        if (tamanho3 == false)
                            tamanho3 = true;
                        else
                            return false;
                    }
                }         
            }
        }
        return tamanho1 && tamanho2 && tamanho3 && tamanho4 && totalShipPieces(stc) == 10;
    }

    /**
     * Imprime uma solução-tabuleiro-em-construção em forma de quadrícula.
     * Cada linha do tabuleiro é impressa numa linha, seguida de um espaço e da
     * respetiva pista de linha; por baixo são impressas as pistas de coluna,
     * alinhadas com as colunas.
     *
     * @param rows número de linhas do tabuleiro
     * @param cols número de colunas do tabuleiro
     * @param stc solução-tabuleiro-em-construção a imprimir
     * @param rowClues pistas de linha
     * @param colClues pistas de coluna
     * @requires {@code rows > 0 && cols > 0 && isValidBoardInConstruction(stc) && stc.length() == rows * cols
     *           && rowClues.length() == rows && colClues.length() == cols}
     */
	public static void	printBoard(int row, int col, String stc, String rowClues, String colClues)
	{
		int	row_index = 0;
	
		for (int i = 0; i < stc.length(); i++)
		{
			System.out.print(stc.charAt(i));
			if ((i + 1) % col == 0)
				System.out.println(" " + rowClues.charAt(row_index++));
		}
		System.out.println(colClues);
	}

    /**
     * Permite ao utilizador jogar o ShIPBattle. Inicializa a solução com o tabuleiro inicial e, repetidamente, imprime o tabuleiro, lê uma jogada
     * e se for válida, efetua-a; caso contrário indica que é inválida. Termina quando stc é solução de board ou quando o utilizador introduz -1 como 
     * posição, imprimindo uma mensagem apropriada.
     *
     * @param sc canal de leitura do standard input
     * @param rows número de linhas do tabuleiro
     * @param cols número de colunas do tabuleiro
     * @param board tabuleiro inicial
     * @param rowClues pistas de linha
     * @param colClues pistas de coluna
     * @requires {@code sc aberto && rows > 0 && cols > 0 && isBoard(rows, cols, board, rowClues, colClues)}
     */

	public static void play(Scanner sc, int rows, int cols, String board, String rowClues, String colClues) {
		String	stc = board;
		int 	position = 0;
		String	piecePlay;
		boolean	checkInvalid;
	
		do
		{
            if (isSolution(rows, cols, board, rowClues, colClues, stc))
            {   printBoard(rows, cols, stc, rowClues, colClues);
                System.out.println("Parabéns!!! Encontrou todos os navios em ShIPBattle!");
                return ;
            }
			checkInvalid = false;
			printBoard(rows, cols, stc, rowClues, colClues);
			System.out.println("Introduza uma jogada (ou -1 para sair).");
			System.out.print("Posicao: ");

			if (sc.hasNextInt())
				position = sc.nextInt();
			else
				checkInvalid = true;
			sc.nextLine();
			System.out.print("Jogada: ");
			piecePlay = sc.nextLine();
			checkInvalid = checkInvalid || piecePlay.length() != 1 || !isValidMove(stc, board, position, piecePlay.charAt(0));
			if (position != -1 && !checkInvalid )
				stc = playMove(stc, position , piecePlay.charAt(0));
			if (checkInvalid && position != -1)
                System.out.println("Jogada inválida! Por favor tente novamente, ou indique -1 como posição de jogada para terminar.");
		} while (position != -1);
        System.out.println("Saiste do jogo. Obrigado Por jogar!!");
	}

	public static void main(String[] args)
	{
		int rows = -1;
		Scanner sc = new Scanner(System.in);
		int cols = -1;
		String stc;
		String rowClues;
		String colClues;

		if (args.length == 5)
		{
			if (args[0].length() == 1 && Character.isDigit(args[0].charAt(0)))
				rows =  Character.getNumericValue(args[0].charAt(0));
			if (args[1].length() == 1 && Character.isDigit(args[1].charAt(0)))
				cols =  Character.getNumericValue(args[1].charAt(0));
			stc = args[2];
			rowClues = args[3];
			colClues = args[4];
			if (rows >= 0 && cols >= 0 && isBoard(rows, cols, stc, rowClues, colClues))
				play(sc, rows, cols, stc, rowClues, colClues);
			else
				System.out.println("Tabuleiro inicial inválido! Verifique o tamanho, as pistas fornecidas, ou as peças no tabuleiro inicial.\r\n");				
			return ;
		}
		System.out.print("Número de linhas: ");
		if (sc.hasNextInt())
			rows = sc.nextInt();
		sc.nextLine();
		System.out.print("Número de colunas: ");
		if (sc.hasNextInt())
			cols = sc.nextInt();
		sc.nextLine();
		System.out.print("Tabuleiro inicial (string de tamanho " + (rows * cols) + "): ");
		stc = sc.nextLine();
		System.out.print("Pistas de linha: ");
		rowClues = sc.nextLine();
		System.out.print("Pistas de coluna: ");
		colClues = sc.nextLine();
		if (rows >= 0 && rows <= 9 && cols >= 0  && cols <= 9 && isBoard(rows, cols, stc, rowClues, colClues))
			play(sc, rows, cols, stc, rowClues, colClues);
		else
			System.out.println("Tabuleiro inicial inválido! Verifique o tamanho, as pistas fornecidas, ou as peças no tabuleiro inicial.\r\n");		
	}
}