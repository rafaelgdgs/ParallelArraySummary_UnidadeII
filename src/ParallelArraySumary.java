import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class ParallelArraySumary {

    ParallelArrayObject[] lista;


//    List<String> list = new ArrayList<String>();
//    List<String> synlist = Collections.synchronizedList(list);

    public void Carregamento(int N) {

        Random random = new Random();
        int limit = (int) Math.pow(10.0, N);
        lista = new ParallelArrayObject[limit];

        System.out.println(limit);

        for (int i = 0; i < limit; i++) {

            lista[i] = (new ParallelArrayObject(i, random.nextFloat(10.0f), (byte) random.nextInt(6)));


            if (i % 100000000 == 0) {
                System.out.println("100 milhoes");
            }
        }
        System.out.println(lista.length);
    }

    public void Processamento(int T) {
        List<Integer>[] grupos = new ArrayList[5];
        List<Integer>[] gruposSincronizados = new ArrayList[5];

        // initializing
        for (int i = 0; i < 5; i++) {
            grupos[i] = new ArrayList<Integer>();
            gruposSincronizados[i] = Collections.synchronizedList(grupos[i]);
        }

        int quantidadeMaiores = 0;
        int quantidadeMenores = 0;

        Worker[] workers = new Worker[T];
        for (int i = 0; i < T; i++) {
            workers[i] = new Worker("Thread " + (i+1),gruposSincronizados);
        }

        for (int i = 0; i < T; i++) {
            workers[i].start();
        }

        for (int i = 0; i < T; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All printing jobs have been finished.");
    }

}
