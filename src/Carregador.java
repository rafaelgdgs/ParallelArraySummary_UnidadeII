import java.util.Random;
public class Carregador extends Thread{

    private byte[] idGrupo;
    private float[] totais;
    private int startIndex;
    private int load;
    public Carregador(String name, byte[] idGrupo, float[] totais, int startIndex, int load){
        super(name);
        this.idGrupo = idGrupo;
        this.totais = totais;
        this.startIndex = startIndex;
        this.load = load;

    }
    @Override
    public void run() {
        Random random = new Random(); //mover para fora do for
        for (int i = startIndex; i < startIndex + load; i++) {

            idGrupo[i] = (byte) random.nextInt(5);
            totais[i] = random.nextFloat(10.0f);
        }
    }
}
