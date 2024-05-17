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

    public static String extractFirst(String jsonString) {
        int colonIndex = jsonString.indexOf(':');
        if (colonIndex == -1) {
            return "Colon not found";
        }

        int commaIndex = jsonString.indexOf(',', colonIndex);
        if (commaIndex == -1) {
            // If there is no comma, it might be the last element in the JSON object
            commaIndex = jsonString.indexOf('}', colonIndex);
        }

        if (commaIndex == -1) {
            return "Comma or closing brace not found";
        }

        // Extract the part between the colon and the comma
        return jsonString.substring(colonIndex + 1, commaIndex).trim();
    }

}