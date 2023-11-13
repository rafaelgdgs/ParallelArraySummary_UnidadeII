import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Tester {
    private final int[] valoresN = { 5, 7, 9};
    private final int[] valoresT = { 1, 4, 16, 64, 256};



    public void executarTestes() throws IOException {
        for (int n: valoresN
             ) {
            for (int t: valoresT
                 ) {
                salvarResultados(n,t,10);
            }
        }
    }

    public void salvarResultados(int n, int t, float tempo) throws IOException {
        File dir = new File("resultados");
        dir.mkdirs();
        File file = new File(dir,"N"+n+"-T"+t+".txt");

        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter printWriter = new PrintWriter(file);

        printWriter.println("Numero de objetos: 10^" + n);
        printWriter.println("Threads: " + t);
        printWriter.println("Executou em: " + tempo);
        printWriter.close();
    }
}
