import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class ParallelArraySumary {

    //ParallelArrayObject[] lista;
    public byte[] idGrupo;
    public float[] totais;

    private int limit;

//    List<String> list = new ArrayList<String>();
//    List<String> synlist = Collections.synchronizedList(list);

    public void Carregamento(int N) {

        Random random = new Random();
        this.limit = (int) Math.pow(10.0, N);
        //lista = new ParallelArrayObject[limit];
        idGrupo = new byte[limit];
        totais = new float[limit];

        System.out.println(limit);

        for (int i = 0; i < limit; i++) {

            //lista[i] = (new ParallelArrayObject(i, random.nextFloat(10.0f), (byte) random.nextInt(6)));
            idGrupo[i] = (byte) random.nextInt(5);
            totais[i] = random.nextFloat(10.0f);

            if (i % 100000000 == 0) {
                System.out.println("100 milhoes");
            }
        }
        System.out.println(totais.length);
    }

    public void Processamento(int T) {
//        List<Float>[] grupos = new ArrayList[5];
//        List<Float>[] gruposSincronizados = new List[5];
//
//        // initializing
//        for (int i = 0; i < 5; i++) {
//            grupos[i] = new ArrayList<Float>();
//            gruposSincronizados[i] = Collections.synchronizedList(grupos[i]);
//        }
//        List<Float> grupos = new ArrayList<Float>();
//        List<Float> gruposSincronizados = Collections.synchronizedList(grupos);
        double[] grupos = new double[5];

        int quantidadeMaiores = 0;
        int quantidadeMenores = 0;
        double somaTotais = 0;

        Worker[] workers = new Worker[T];
        int startIndex = 0;
        int load = limit / T;
        for (int i = 0; i < T; i++) {

            //para divisões com resto != 0, adiciona o resto ao load da ultima threrad
            if (i == T - 1) {
                load += limit % T;
            }
            workers[i] = new Worker("Thread " + (i + 1), idGrupo, totais, startIndex,load);
            startIndex += load;
        }

        for (int i = 0; i < T; i++) {
            workers[i].start();
        }

        for (int i = 0; i < T; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                quantidadeMaiores += workers[i].getQuantidadeMaiores();
                quantidadeMenores += workers[i].getQuantidadeMenores();
            }
        }
        System.out.println("quantidadeMaiores: "+ quantidadeMaiores);
        System.out.println("quantidadeMenores: "+ quantidadeMenores);
        for (int i=0;i<5;i++){
            for(int j=0;j<T;j++){
                grupos[i] +=  workers[j].somaInternaGrupos[i];
            }
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(grupos[i]));
            System.out.println("soma do grupo "+(i+1)+": "+ bigDecimal.toPlainString());
            somaTotais += grupos[i];
        }
        for (int i=0;i<5;i++){
            System.out.println("soma do grupo "+(i+1)+" é menor que total: " + (grupos[i]< somaTotais));
        }

        BigDecimal bigDecimal = new BigDecimal(String.valueOf(somaTotais));
        var longValue = bigDecimal.longValue();
        System.out.println("soma totais: "+ bigDecimal.toPlainString());
        System.out.println("parte inteira: " + longValue);
        System.out.println("parte fracional: " + bigDecimal.subtract(
                new BigDecimal(longValue)).toPlainString());
        System.out.println("All printing jobs have been finished.");
    }

}
