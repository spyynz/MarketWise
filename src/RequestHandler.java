import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestHandler {
    public static String makeHttpRequest(String urlString) {
        return makeHttpRequest(urlString, null);
    }

    public static String makeHttpRequest(String urlString, String requestBody) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder responseContent = new StringBuilder();
        int responseCode = 0;

        try {
            // Create a URL object from the URL string
            URL url = new URL(urlString);

            // Open a connection to the URL
            connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET or POST depending on the presence of a request body
            if (requestBody != null && !requestBody.isEmpty()) {
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Write the request body to the output stream
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = requestBody.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            } else {
                connection.setRequestMethod("GET");
            }

            // Get the response code
            responseCode = connection.getResponseCode();

            // Create a BufferedReader to read the response from the input stream
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            return String.format("%03d %s", responseCode, responseContent.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return String.format("%03d %s", responseCode, "Error occurred while making the request");
        } finally {
            // Close the BufferedReader
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Disconnect the HttpURLConnection
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


}
