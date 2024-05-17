import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    private static final String FILE_PATH = "savedUser.txt";

    public static void delLogin() {
        try {
            FileWriter myWriter = new FileWriter(FILE_PATH);
            myWriter.write("");
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void saveLogin(String username, String password) {
        try {
            FileWriter myWriter = new FileWriter(FILE_PATH);
            myWriter.write(username + "," + password);
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}