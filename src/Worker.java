import java.util.ArrayList;
import java.util.List;

public class Worker extends Thread{
    private int quantidadeMaiores = 0;
    private int quantidadeMenores = 0;

    private List<Integer>[] grupos;

    ArrayList<Integer>[] gruposInternos = new ArrayList[5];



    public Worker(String name, List<Integer>[] grupos){
        super(name);
        for (int i = 0; i < 5; i++) {
            gruposInternos[i] = new ArrayList<Integer>();
        }
        this.grupos = grupos;
    }

    @Override
    public void run(){

    }
}
