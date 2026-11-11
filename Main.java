import java.util.Scanner;

public class Main {
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
				play();
			else
				System.out.println("Tabuleiro inicial inválido! Verifique o tamanho, as pistas fornecidas, ou as peças no tabuleiro inicial.\r\n");				
			return ;
		}
		if (sc.hasNextInt())
			rows = sc.nextInt();
		else
			sc.nextLine();
		if (sc.hasNextInt())
			cols = sc.nextInt();
		else
			sc.nextLine();
		stc = sc.nextLine();
		rowClues = sc.nextLine();
		colClues = sc.nextLine();
		if (rows >= 0 && rows <= 9 && cols >= 0  && cols <= 9 && isBoard(rows, cols, stc, rowClues, colClues))
			play();
		else
			System.out.println("Tabuleiro inicial inválido! Verifique o tamanho, as pistas fornecidas, ou as peças no tabuleiro inicial.\r\n");		
	}
}