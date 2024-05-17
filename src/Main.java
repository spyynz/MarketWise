import Pages.Page;
import Pages.MainPage;
import Pages.StocksPage;
import Pages.InvestPage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);

        Page mainPage = new MainPage();
        Page stocksPage = new StocksPage();
        Page investPage = new InvestPage();


        System.out.println("Welcome to MarketWise, login below.");
        System.out.print("username: ");
        String username = input.nextLine();
        System.out.print("password: ");
        String password = input.nextLine();

        User currentUser = new User(username, password);
        if (currentUser.checkUser() == 200) {
            clear();
            System.out.println("Welcome, " + currentUser.getUsername());;
            space();
            System.out.println(currentUser.getStocks());
        }
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