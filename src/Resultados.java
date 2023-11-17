import java.math.BigDecimal;

public class Resultados {

    private int quantidadeMaiores;
    private int quantidadeMenores;
    private double[] somaGrupos = new double[5];
    private double somaTotais;
    public Resultados(int quantidadeMaiores, int quantidadeMenores, double[] somaGrupos, double somaTotais) {
        this.quantidadeMaiores = quantidadeMaiores;
        this.quantidadeMenores = quantidadeMenores;
        this.somaGrupos = somaGrupos;
        this.somaTotais = somaTotais;
    }

    public String getQuantidadeMaiores() {
        return "valores maiores ou iguais a 5: " + quantidadeMaiores;
    }

    public String getQuantidadeMenores() {
        return "valores menores a 5: " + quantidadeMenores;
    }

    public String getSomaGrupos() {
        String texto = "";
        for (int i=0; i< somaGrupos.length;i++) {
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(somaGrupos[i]));
            texto = texto.concat("soma do grupo "+ (i+1)+": "+ bigDecimal.toPlainString()+"\n");
        }
        return texto;
    }

    public String getSomaTotais() {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(somaTotais));
        return "soma totais: "+bigDecimal.toPlainString();
    }

}
