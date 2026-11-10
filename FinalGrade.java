import java.util.Scanner;
public class FinalGrade {
    public static void main (String []args ) {
        Scanner sc = new Scanner (System.in);
        System.out.println("Quantos alunos são: ");
        int numeroDeAlunos = sc.nextInt();
        int [][] notas = new int [numeroDeAluno][2];
        for ( int i = 0; i < numeroDeAlunos; i++){
            for (int j = 0 ; j < 2 ; j++){
                System.out.println("a nota do aluno "+i+ ": ");
                notas[i][j]= sc.nextInt();
            }
        }
        int reprovados = howManyGreaterThan(notas, 10);
        System.out.println ("")
    }
    static int howManyGreaterThan(int[][] v, int x){
        int count = 0;
        for ( int i = 0; i< v.length; i++){
            
            if (v[i][0]  < x ||  v[i][1]  < x)
            count++;
        }
        return count;
    }
}
