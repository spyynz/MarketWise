import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        User currentUser = null;

        while (!loggedIn) {
            System.out.println("Enter your username:");
            String username = scanner.nextLine();

            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            // Authenticate the user
            currentUser = new User(username, password);
            if (currentUser.checkUser().equals("Success!")) {
                loggedIn = true;
                System.out.println("Login successful!");
            } else if (currentUser.checkUser().equals("Invalid Password.")) {
                System.out.println("Invalid password. Please try again.");
            } else if (currentUser.checkUser().equals("User not found, signing up")) {
                loggedIn = true;
                System.out.println("User not found, signing up.");
            } else {

                System.out.println("Error");
            }
        }

        System.out.println("Welcome, " + currentUser.getUsername() + "!");

        boolean running = true;
        while (running) {
            System.out.println("Enter a command (info, invest, sell, quit):");
            String commandLine = scanner.nextLine();
            String[] input = commandLine.split(" ");

            switch (input[0].toLowerCase()) {
                case "help":
                    clear();
                    System.out.println("Available commands:");
                    System.out.println(" - info: Current stats");
                    System.out.println(" - invest <SYMBOL> <SHARES>: Invest in a stock");
                    System.out.println(" - sell <SYMBOL> <SHARES>: Sell a stock");
                    System.out.println(" - quit: Quit the program");
                    break;
                case "info":
                    clear();
                    space(1);
                    System.out.println(currentUser.getCash());
                    space(1);
                    System.out.println(currentUser.getStocks());
                    break;
                case "invest":
                    if (input.length == 3) {
                        String symbol = input[1];
                        int shares = Integer.parseInt(input[2]);
                        System.out.println("Investing " + shares + " shares of " + symbol);
                        String price = RequestHandler.makeHttpRequest("https://finnhub.io/api/v1/quote?token=cp32fl1r01qvi2qqb110cp32fl1r01qvi2qqb11g&symbol=" + symbol);
//                        System.out.println("{\"username\":\""+currentUser.getUsername()+"\",\"password\":\""+currentUser.getPassword()+",\"stock:\"" + symbol + "\",\"shares\":\""+shares+"\",\"price\":\""+ FileHandler.extractFirst(price) +"\"}");
                        String res = RequestHandler.makeHttpRequest("https://james.colasac.co/api/csa/buy", "{\"username\":\""+currentUser.getUsername()+"\",\"password\":\""+currentUser.getPassword()+"\",\"stock\":\"" + symbol + "\",\"shares\":"+shares+",\"price\":"+ FileHandler.extractFirst(price) +"}");
                        System.out.println(res.substring(16, res.length() - 2));
                    } else {
                        System.out.println("Invalid invest command. Usage: invest <SYMBOL> <SHARES>");
                    }
                    break;
                case "sell":
                    if (input.length == 3) {
                        String symbol = input[1];
                        int shares = Integer.parseInt(input[2]);
                        System.out.println("Selling " + shares + " shares of " + symbol);
                        String price = RequestHandler.makeHttpRequest("https://finnhub.io/api/v1/quote?token=cp32fl1r01qvi2qqb110cp32fl1r01qvi2qqb11g&symbol=" + symbol);
//                        System.out.println("{\"username\":\""+currentUser.getUsername()+"\",\"password\":\""+currentUser.getPassword()+",\"stock:\"" + symbol + "\",\"shares\":\""+shares+"\",\"price\":\""+ FileHandler.extractFirst(price) +"\"}");
                        String res = RequestHandler.makeHttpRequest("https://james.colasac.co/api/csa/sell", "{\"username\":\""+currentUser.getUsername()+"\",\"password\":\""+currentUser.getPassword()+"\",\"stock\":\"" + symbol + "\",\"shares\":"+shares+",\"price\":"+ FileHandler.extractFirst(price) +"}");
                        System.out.println(res.substring(16, res.length() - 2));
                    } else {
                        System.out.println("Invalid sell command. Usage: sell <SYMBOL> <SHARES>");
                    }
                    break;
                case "quit":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' for available commands.");
            }
        }

        System.out.println("Goodbye!");

    }



    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void space() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }

    public static void space(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println();
        }
    }
}