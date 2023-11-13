
public class Worker extends Thread {
    private int quantidadeMaiores = 0;
    private int quantidadeMenores = 0;
    private int startIndex;
    private int load;

    private byte[] idGrupo;
    private float[] totais;


    public double[] somaInternaGrupos = new double[5];


    public Worker(String name, byte[] idGrupo, float[] totais, int startIndex, int load) {
        super(name);

        for (int i = 0; i < 5; i++) {
            somaInternaGrupos[i] = 0;
        }

        this.idGrupo = idGrupo;
        this.totais = totais;
        this.startIndex = startIndex;
        this.load = load;
    }

    public int getQuantidadeMaiores() {
        return quantidadeMaiores;
    }

    public int getQuantidadeMenores() {
        return quantidadeMenores;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < startIndex + load; i++) {
            somaInternaGrupos[idGrupo[i]] += totais[i];
            if(totais[i] < 5) quantidadeMenores++;
            else quantidadeMaiores++;
        }

    }
}
