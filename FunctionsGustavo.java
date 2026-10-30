/**
 * Functions
 */
public class FunctionsGustavo {

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
	
		while (col < stc.length())
		{
			numPieces += isShipPiece(stc.charAt(col)) ? 1 : 0;
			col += numColumns;
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
			numPieces += stc.charAt(col) == 'X' ? 1 : 0;
			col += numColumns;
		}
		return numPieces;
	}

	public static boolean isBoard(int rows, int cols, String stc, String rowClues, String colClues)
	{
		if (rows * cols != stc.length())
			return false;
		if (rowClues.length() != rows || colClues.length() != cols)
			return false;
		for (int i = 0; i < rowClues.length(); i++)
		{
			if (!Character.isDigit(i) && rowClues.charAt(i) - '0' < cols)
				return false;
		}
		for (int i = 0; i < colClues.length(); i++)
		{
			if (!Character.isDigit(i) && colClues.charAt(i) - '0' < rows)
				return false;
		}
		return true;
	}

}