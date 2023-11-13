import java.math.BigDecimal;
import java.util.Random;

import static java.lang.System.currentTimeMillis;


public class ParallelArraySumary {

    public byte[] idGrupo;
    public float[] totais;

    private int limit;



    public void Carregamento(int N) {

        this.limit = (int) Math.pow(10.0, N);
        idGrupo = new byte[limit];
        totais = new float[limit];

        System.out.println(limit);
        long startTime = currentTimeMillis();

        int totalCarreadores = 10;
        Carregador[] carregadores = new Carregador[totalCarreadores];
        int startIndex = 0;
        int load = limit / totalCarreadores;
        for (int i = 0; i < totalCarreadores; i++) {

            //para divisões com resto != 0, adiciona o resto ao load da ultima threrad
            if (i == totalCarreadores - 1) {
                load += limit % totalCarreadores;
            }
            carregadores[i] = new Carregador("Carregador " + (i + 1), idGrupo, totais, startIndex,load);
            startIndex += load;
        }
        for (int i = 0; i < totalCarreadores; i++) {
            carregadores[i].start();
        }

        for (int i = 0; i < totalCarreadores; i++) {
            try {
                carregadores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                System.out.println(carregadores[i].getName() + " finalizou");
            }
        }

//        Random random = new Random();
//        for (int i = 0; i < limit; i++) {
//
//            //lista[i] = (new ParallelArrayObject(i, random.nextFloat(10.0f), (byte) random.nextInt(6)));
//            idGrupo[i] = (byte) random.nextInt(5);
//            totais[i] = random.nextFloat(10.0f);
//
//            if (i % 100000000 == 0) {
//                System.out.println("100 milhoes");
//            }
//        }

        System.out.println(totais.length);
        System.out.println("tempo de carregamento: " + (currentTimeMillis()-startTime));
    }

    public void Processamento(int T) {

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
