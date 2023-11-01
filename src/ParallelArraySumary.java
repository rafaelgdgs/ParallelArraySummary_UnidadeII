import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.instrument.Instrumentation;

public class ParallelArraySumary {
    public List<ParallelArrayObject> lista;
    ParallelArrayObject[] lista2;

    public void Carregamento(int N) {
        //lista = new ArrayList<>();
        Random random = new Random();
        int limit = (int) Math.pow(10.0, N);
        lista2 = new ParallelArrayObject[limit];
        System.out.println(limit);
        for (int i = 0; i < limit; i++) {
            lista2[i] = (new ParallelArrayObject(i, random.nextFloat(10.0f), (byte) random.nextInt(6)));
        }
        System.out.println(lista.size());
    }

    public void Processamento(int T) {

    }

}
