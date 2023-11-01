import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        Tester tester = new Tester();
        try{
            tester.executarTestes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
