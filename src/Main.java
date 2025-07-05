import controller.BasicProgram;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new BasicProgram("localhost", 8080).start();
    }
}