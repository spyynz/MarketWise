import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int checkUser() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serviceUrl))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        //check if the user exists in the backend database
        //  if so, check if the password matches the one in the db
        //      if so, return 200, allows user into their account
        //      if not, return 403, prompt incorrect password
        //  if not, return 404, causes backend to add the user to db
        return 200;
    }

    public String getStocks() {
        String str = "";
        //request current stocks
        //  if none, str = "No stocks available"
        //  if any, str = "Stock Symbol : Stock Price"
        //  user can do -info (or -i) {Symbol} to get extra info on it
        str = str.substring(0, str.length()-1);
        return "Current Stocks:\n" + str;
    }
}