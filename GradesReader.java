import java.util.Scanner;


public class GradesReader {
    public static void main (String []args ) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qantas notas quer fornevcer, o número tem qye ser entre 0 e 10 (inclusive)  : ");
        int numberOfGrades = sc.nextInt();
        int [] grades = new int [numberOfGrades];
        int soma = 0;
        for ( int i = 0 ; i < numberOfGrades; i++){
            System.out.println("Digite o a nota "+i+1 + ": ");
            grades [i] = sc.nextInt(); 
            soma += grades [i];
        }
        double media = UtilsIP.mean( grades []);
    }
}
