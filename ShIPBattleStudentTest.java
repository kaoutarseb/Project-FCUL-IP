import java.util.Arrays;

/**
 * O {@code ShIPBattleStudentTest} faz alguns (poucos) teste a funcoes e procedimentos
 * da classe {@code ShIPBattle}, de acordo com enunciado da primeira parte do projeto
 * de IP 2026/27.
 * 
 * Para usar estes testes:
 *  - Compile tanto o ficheiro do projeto como este ficheiro de teste:
 * 
 *          javac ShIPBattle.java ShIPBattleStudentTest.java
 * 
 *  - Em seguida execute o programa de teste:
 * 
 *          java ShIPBattleTest
 *
 * @author filipegouveia
 * @version 1
 */
public class ShIPBattleStudentTest {

    /** Exemplo do tabuleiro inicial 4x5. */
    private static final String BOARD_4X5 = "..L..........X.....B";
    /** Solucao do {@link #BOARD_4X5}. */
    private static final String SOL_4X5 = "TXLMRMXXXXMXCXTBXXXB";
    private static final String ROW_CLUES_4X5 = "4132";
    private static final String COL_CLUES_4X5 = "40213";

    public static void main(String[] args) {
        System.out.println("A testar ShIPBattle.java \n");

        testRowOfPosition();
        testColumnOfPosition();
        testPositionNumber();
        testNumberRows();
        System.out.println();

        testIsShipPiece();
        testIsFillingChar();
        System.out.println();

        testIsValidBoardInConstruction();
        testIsFilled();
        System.out.println();

        testCountShipPiecesInRow();
        testCountShipPiecesInCol();
        testCountWaterPiecesInRow();
        testCountWaterPiecesInCol();
        System.out.println();

        testIsBoard();
        testIsSolution();
        System.out.println();

        testIsValidMove();
        testPlayMove();
    }

    private static void testRowOfPosition() {
        System.out.println("A testar rowOfPosition:");
        boolean error = false;

        int pos = 0;
        int cols = 5;
        int expected = 1;
        int obtained = ShIPBattle.rowOfPosition(pos, cols);
        error = checkEqual(expected, obtained, Integer.toString(pos), Integer.toString(cols)) || error;

        pos = 19;
        expected = 4;
        obtained = ShIPBattle.rowOfPosition(pos, cols);
        error = checkEqual(expected, obtained, Integer.toString(pos), Integer.toString(cols)) || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testColumnOfPosition() {
        System.out.println("A testar columnOfPosition:");
        boolean error = false;

        int pos = 0;
        int cols = 5;
        int expected = 1;
        int obtained = ShIPBattle.columnOfPosition(pos, cols);
        error = checkEqual(expected, obtained, Integer.toString(pos), Integer.toString(cols)) || error;

        pos = 7;
        cols = 4;
        expected = 4;
        obtained = ShIPBattle.columnOfPosition(pos, cols);
        error = checkEqual(expected, obtained, Integer.toString(pos), Integer.toString(cols)) || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testPositionNumber() {
        System.out.println("A testar positionNumber:");
        boolean error = false;

        int row = 1;
        int col = 1;
        int cols = 5;
        int expected = 0;
        int obtained = ShIPBattle.positionNumber(row, col, cols);
        error = checkEqual(expected, obtained, Integer.toString(row), Integer.toString(col),
                Integer.toString(cols)) || error;

        row = 1;
        col = 3;
        expected = 2;
        obtained = ShIPBattle.positionNumber(row, col, cols);
        error = checkEqual(expected, obtained, Integer.toString(row), Integer.toString(col),
                Integer.toString(cols)) || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testNumberRows() {
        System.out.println("A testar numberRows:");
        boolean error = false;

        int size = 20;
        int cols = 5;
        int expected = 4;
        int obtained = ShIPBattle.numberRows(size, cols);
        error = checkEqual(expected, obtained, Integer.toString(size), Integer.toString(cols)) || error;

        size = 36;
        cols = 6;
        expected = 6;
        obtained = ShIPBattle.numberRows(size, cols);
        error = checkEqual(expected, obtained, Integer.toString(size), Integer.toString(cols)) || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testIsShipPiece() {
        System.out.println("A testar isShipPiece:");
        boolean error = false;

        error = checkEqual(true, ShIPBattle.isShipPiece('C'), "C") || error;
        error = checkEqual(false, ShIPBattle.isShipPiece('m'), "m") || error;
        error = checkEqual(false, ShIPBattle.isShipPiece('O'), "O") || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testIsFillingChar() {
        System.out.println("A testar isFillingChar:");
        boolean error = false;

        error = checkEqual(true, ShIPBattle.isFillingChar('C'), "C") || error;
        error = checkEqual(false, ShIPBattle.isFillingChar('.'), ".") || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testIsValidBoardInConstruction() {
        System.out.println("A testar isValidBoardInConstruction:");
        boolean error = false;

        error = checkEqual(true, ShIPBattle.isValidBoardInConstruction(BOARD_4X5), BOARD_4X5) || error;
        error = checkEqual(true, ShIPBattle.isValidBoardInConstruction(SOL_4X5), SOL_4X5) || error;

        error = checkEqual(false, ShIPBattle.isValidBoardInConstruction(null), "null") || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testIsFilled() {
        System.out.println("A testar isFilled:");
        boolean error = false;

        error = checkEqual(true, ShIPBattle.isFilled(SOL_4X5), SOL_4X5) || error;
        error = checkEqual(false, ShIPBattle.isFilled(BOARD_4X5), BOARD_4X5) || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testCountShipPiecesInRow() {
        System.out.println("A testar countShipPiecesInRow:");
        boolean error = false;

        int cols = 5;
        error = checkEqual(1, ShIPBattle.countShipPiecesInRow(1, BOARD_4X5, cols), "board", "1") || error;
        error = checkEqual(0, ShIPBattle.countShipPiecesInRow(2, BOARD_4X5, cols), "board", "2") || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testCountShipPiecesInCol() {
        System.out.println("A testar countShipPiecesInCol:");
        boolean error = false;

        int cols = 5;
        error = checkEqual(0, ShIPBattle.countShipPiecesInCol(1, BOARD_4X5, cols), "board", "1") || error;

        error = checkEqual(4, ShIPBattle.countShipPiecesInCol(1, SOL_4X5, cols), "sol", "1") || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testCountWaterPiecesInRow() {
        System.out.println("A testar countWaterPiecesInRow:");
        boolean error = false;

        int cols = 5;
        error = checkEqual(1, ShIPBattle.countWaterPiecesInRow(3, BOARD_4X5, cols), "board", "3") || error;

        error = checkEqual(1, ShIPBattle.countWaterPiecesInRow(1, SOL_4X5, cols), "sol", "1") || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testCountWaterPiecesInCol() {
        System.out.println("A testar countWaterPiecesInCol:");
        boolean error = false;

        int cols = 5;
        error = checkEqual(0, ShIPBattle.countWaterPiecesInCol(1, BOARD_4X5, cols), "board", "1") || error;

        error = checkEqual(0, ShIPBattle.countWaterPiecesInCol(1, SOL_4X5, cols), "sol", "1") || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testIsBoard() {
        System.out.println("A testar isBoard:");
        boolean error = false;

        error = checkEqual(true,
                ShIPBattle.isBoard(4, 5, BOARD_4X5, ROW_CLUES_4X5, COL_CLUES_4X5), "4x5 ok") || error;
                
        // tamanho do tabuleiro errado
        error = checkEqual(false,
                ShIPBattle.isBoard(4, 5, "..L..........X...C", ROW_CLUES_4X5, COL_CLUES_4X5),
                "len") || error;

        // pecas ja colocadas excedem a pista
        // row 1 tem L (1 peca de navio) mas a pista e 0
        error = checkEqual(false,
                ShIPBattle.isBoard(4, 5, BOARD_4X5, "0132", COL_CLUES_4X5), "ships > clue") || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testIsSolution() {
        System.out.println("A testar isSolution:");
        boolean error = false;

        error = checkEqual(true,
                ShIPBattle.isSolution(4, 5, BOARD_4X5, ROW_CLUES_4X5, COL_CLUES_4X5, SOL_4X5),
                "4x5 sol") || error;
        // nao preenchido
        error = checkEqual(false,
                ShIPBattle.isSolution(4, 5, BOARD_4X5, ROW_CLUES_4X5, COL_CLUES_4X5, BOARD_4X5),
                "unfilled") || error;

        // nao mantem pistas iniciais (L na posicao 2)
        String brokenGiven = "TXXMRMXXXXMXCXTBXXXB";
        error = checkEqual(false,
                ShIPBattle.isSolution(4, 5, BOARD_4X5, ROW_CLUES_4X5, COL_CLUES_4X5, brokenGiven),
                "given L") || error;
        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testIsValidMove() {
        System.out.println("A testar isValidMove:");
        boolean error = false;

        String stc = BOARD_4X5;
        String board = BOARD_4X5;

        error = checkEqual(true, ShIPBattle.isValidMove(stc, board, 0, 'T'), "0", "T") || error;
        error = checkEqual(true, ShIPBattle.isValidMove(stc, board, 1, 'X'), "1", "X") || error;

        // posicao ja preenchida no tabuleiro inicial
        error = checkEqual(false, ShIPBattle.isValidMove(stc, board, 2, 'L'), "2 given L") || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static void testPlayMove() {
        System.out.println("A testar playMove:");
        boolean error = false;

        String stc = BOARD_4X5;
        String expected = "T.L..........X.....B";
        String obtained = ShIPBattle.playMove(stc, 0, 'T');
        error = checkEqual(expected, obtained, "pos0 T") || error;

        expected = "TXL..........X.....B";
        obtained = ShIPBattle.playMove(obtained, 1, 'X');
        error = checkEqual(expected, obtained, "pos1 X") || error;

        // limpar uma posicao jogada anteriormente
        expected = "T.L..........X.....B";
        obtained = ShIPBattle.playMove(obtained, 1, '.');
        error = checkEqual(expected, obtained, "pos1 clear") || error;

        System.out.println(error ? "FAIL" : "PASS");
    }

    private static boolean checkEqual(int expected, int obtained, String... args) {
        if (obtained != expected) {
            System.out.println(Arrays.toString(args));
            System.out.printf(">>> esperado: %d obtido: %d %n", expected, obtained);
            return true;
        }
        return false;
    }

    private static boolean checkEqual(boolean expected, boolean obtained, String... args) {
        if (expected != obtained) {
            System.out.println(Arrays.toString(args));
            System.out.printf(">>> esperado: %b obtido: %b %n", expected, obtained);
            return true;
        }
        return false;
    }

    private static boolean checkEqual(String expected, String obtained, String... args) {
        if (!expected.equals(obtained)) {
            System.out.println(Arrays.toString(args));
            System.out.printf(">>> esperado: \"%s\" obtido: \"%s\" %n", expected, obtained);
            return true;
        }
        return false;
    }
}
