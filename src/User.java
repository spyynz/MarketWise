import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

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

    public String checkUser() {

        String code = RequestHandler.makeHttpRequest("https://james.colasac.co/api/csa/login", "{\"username\":\""+username+"\",\"password\":\""+password+"\"}");
        if (code.substring(0, 3).equals("200")) return "Success!";
        else if (code.substring(0, 3).equals("403")) return "Invalid Password.";
        else if (code.substring(0, 3).equals("404")) return "User not found, signing up";
        else return "Error";
    }

    public String getStocks() {
        String str = "";

        // Request current stocks
        String apple = RequestHandler.makeHttpRequest("https://finnhub.io/api/v1/quote?token=cp32fl1r01qvi2qqb110cp32fl1r01qvi2qqb11g&symbol=AAPL");
        String tesla = RequestHandler.makeHttpRequest("https://finnhub.io/api/v1/quote?token=cp32fl1r01qvi2qqb110cp32fl1r01qvi2qqb11g&symbol=TSLA");
        String walmart = RequestHandler.makeHttpRequest("https://finnhub.io/api/v1/quote?token=cp32fl1r01qvi2qqb110cp32fl1r01qvi2qqb11g&symbol=WMT");
        String countsResponse = RequestHandler.makeHttpRequest("https://james.colasac.co/api/csa/getstocks", "{\"username\":\""+username+"\",\"password\":\""+password+"\"}");

        // Extract share counts using regular expression
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(countsResponse);
        List<Integer> counts = new ArrayList<>();
        while (matcher.find()) {
            counts.add(Integer.parseInt(matcher.group()));
        }
        counts.removeFirst();
        if (counts.size() == 3) {
            int appleShares = counts.get(0);
            int teslaShares = counts.get(1);
            int walmartShares = counts.get(2);

            // Calculate the total value of each stock
            double appleValue = Double.parseDouble(FileHandler.extractFirst(apple));
            double teslaValue = Double.parseDouble(FileHandler.extractFirst(tesla));
            double walmartValue = Double.parseDouble(FileHandler.extractFirst(walmart));

            // Create the string representation of the stocks
            str += (appleShares + " share/s of AAPL : $" + appleValue + "/ea = $" + (appleValue * appleShares) + "\n" +
                    teslaShares + " share/s of TSLA : $" + teslaValue + "/ea = $" + (teslaValue * teslaShares) + "\n" +
                    walmartShares + " share/s of WMT : $" + walmartValue + "/ea = $" + (walmartValue * walmartShares));
        } else {
            str = "Error retrieving stock information.";
        }

        return "Current Stocks:\n" + str;
    }



    public String getCash() {
        String res = RequestHandler.makeHttpRequest("https://james.colasac.co/api/csa/getcash", "{\"username\":\""+username+"\",\"password\":\""+password+"\"}");
        return "Current Cash:\n$" + res.substring(15,res.length()-1);
    }
}