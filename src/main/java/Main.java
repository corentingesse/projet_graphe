import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/graphe.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int c;

        while ((c = br.read()) != -1) {
            char ch = (char) c;

            System.out.print(ch);
        }
    }
}
