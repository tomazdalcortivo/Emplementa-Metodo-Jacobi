package emplementacao;

import java.util.Scanner;

class MetodoJacobi {

    Scanner leia = new Scanner(System.in);

    static final int MAX_ITERACOES = 1000;
    static final double ALPHA = 0000000000000001; // escala de precisão 

    public static void main(String[] args) {
    // tive que instanciar a classe por causa que os numero de interacoes e a taxa de precissao serem estaticos, nao conseguam ser lidos pelo Scanner
        MetodoJacobi metodoJacobi = new MetodoJacobi(); 
        metodoJacobi.start();
    }

    public void start() {
        double A[][] = new double[3][3];
        double b[] = new double[3];
        int n = b.length;
        double[] estimativaInicial = new double[n];
        double[] estimativaAtualizada = new double[n];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.println("Escreva o sistema em forma matricial [" + (i + 1) + "][" + (j + 1) + "]:");
                A[i][j] = leia.nextDouble();
            }
        }

        for (int i = 0; i < b.length; i++) {
            System.out.println("Escreva o valor do vetor b na posição [" + (i + 1) + "]:");
            b[i] = leia.nextDouble();
        }
        // Inicialização das estimativas iniciais como zero
        for (int i = 0; i < n; i++) {
            estimativaInicial[i] = 0;
        }

        // Iterações do método de Jacobi
        for (int iteration = 1; iteration <= MAX_ITERACOES; iteration++) {
            // Cálculo das estimativas atualizadas
            for (int i = 0; i < n; i++) {
                double sum = b[i];
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= A[i][j] * estimativaInicial[j];
                    }
                }
                estimativaAtualizada[i] = sum / A[i][i];
            }

            // Cálculo da diferença máxima entre as estimativas atualizadas e as estimativas anteriores
            double maxDifference = Math.abs(estimativaAtualizada[0] - estimativaInicial[0]);
            for (int i = 1; i < n; i++) {
                double difference = Math.abs(estimativaAtualizada[i] - estimativaInicial[i]);
                if (difference > maxDifference) {
                    maxDifference = difference;
                }
            }

            // Verificação do critério de convergência
            if (maxDifference < ALPHA) {
                System.out.println("Converge após " + iteration + " iterações");
                break;
            }

            // Atualização das estimativas iniciais para a próxima 
            // for (int i = 0; i < n; i++) {
            //    estimativaInicial[i] = estimativaAtualizada[i];
            //} 
            // similar a este processo porem mais eficiente, sugestao da IDE 
            System.arraycopy(estimativaAtualizada, 0, estimativaInicial, 0, n);
        }

        // Impressão da solução final
        System.out.println("Solução:");
        for (int i = 0; i < n; i++) {
            System.out.println(estimativaAtualizada[i]);
        }
    }
}
