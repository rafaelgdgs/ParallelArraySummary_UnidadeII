import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParallelArraySumary {
    public List<ParallelArrayObject> lista;

    public void Carregamento(int N) {
        List<ParallelArrayObject> listaTemp = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < Math.pow(10.0, N); i++) {
            ParallelArrayObject obj = new ParallelArrayObject(i, random.nextDouble(10.0), random.nextInt(6));
            listaTemp.add(obj);
        }
        lista = listaTemp;
    }

    public void Processamento(int T) {

    }

}
