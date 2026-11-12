import java.util.Scanner;

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
	 * 
	 * @param size Numero de posicoes existentes.
	 * @param numColumns Numero total de colunas
	 * @return Numero de rows.
	 */

	public static  int numberRows(int size, int numColumns)
	{
		return (size / numColumns) + 1;
	}

	/**
	 * Calcula a posição num tabuleiro com numColumns colunas, dada a linha row e a coluna column
	 * @param row numero de linha
	 * @param column Coluna atualizada 
	 * @param numColumns Numero total de colunas
	 * @return 
	 */

	public static  int positionNumber(int row, int column, int numColumns)
	{
		return ((row - 1) * numColumns + column - 1);
	}

	/**
	 * 
	 * @param row Linha a ser analizada
	 * @param stc String com conteudo
	 * @param numColumns Numero total de colunas em uma linha
	 * @return True se houver Peças de tipo Ship na row
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
	 * 
	 * @param col coluna a ser analizada
	 * @param stc String com conteudo
	 * @param numColumns Numero total de colunas em uma linha
	 * @return True se houver peças desse tipo Ship na col.
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
	 * 
	 * @param row Linha a ser analizada
	 * @param stc String com conteudo
	 * @param numColumns Numero total de colunas em uma linha
	 * @return True se houver Peças de tipo Water na row
	 */

	public static int countWaterPiecesInRow(int row, String stc, int numColumns)
	{
		int start = (row - 1) * numColumns;
		int numPieces = 0;
	
		do
		{
			numPieces += stc.charAt(start) == 'X' ? 1 : 0;
		} while (start % numColumns != 0);

		return numPieces;
	}

	/**
	 * 
	 * @param col coluna a ser analizada
	 * @param stc String com conteudo
	 * @param numColumns Numero total de colunas em uma linha
	 * @return True se houver peças desse tipo Water na col.
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
     * @param pos a posicao
     * @param numColumns o número de colunas
     * @return o número de linha da posicao
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
     * Verifica se stc representa uma solucao-tabuleiro-em-construcao
     * @param stc o string a verificar
     * @return true se é  uma solução-tabuleiro-em-construção e false se não á  uma solução-tabuleiro-em-construção
     */

    
    static boolean isValidBoardInConstruction( String stc){
        if (stc.isEmpty())
            return false;
       
        for (int i= 0 ; i < stc.length(); i++){
            if ( stc.charAt(i) != '.' && !isFillingChar(stc.charAt(i)) )
                return false;
        }
       return true;
    }

    /**
     * Verifica se todas as posicoes estao preenchidas
     * @param stc o String a verificar
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
     * Verifica se a jogada é válida
     * @param board o tabuleiro inicial 
     * @param stc uma solução-tabuleiro-em-construção
     * @param c a ação jogada
     * @param pos uma posição da jogada
     * @return retorna true se a jogada é válida e false se é inválida
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
     * @return  a solução-tabuleiro-em-construção que resulta de efetuar a jogada
     */
    static String playMove (String stc, int pos, char c){
        StringBuilder st = new StringBuilder(stc);
        st.setCharAt(pos, c);
        return st.toString();
    }
    


    static boolean isSolution (int cols, int rows, String board, String rowClues, String colClues, String stc ){
        return isFilled( stc) 
                && sameSize(stc, board) 
                && samePieces(stc, board) 
                && cluesMatch( cols, rowClues, colClues, stc)
                && allShips(stc, rows, cols);
        
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
            if( board.charAt(i)!= '.' &&  stc.charAt(i)!= board.charAt(i))
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
        return true;
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
        return tamanho1 && tamanho2 && tamanho3 && tamanho4;
    }


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

	public static void play(Scanner sc, int rows, int cols, String board, String rowClues, String colClues) {
		String	stc = board;
		int 	position = 0;
		String	piecePlay;
		boolean	checkInvalid;
	
		do
		{
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