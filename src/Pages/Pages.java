package Pages;

import java.util.ArrayList;

public class Pages {
    private ArrayList<Page> pages;

    public Pages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    public void printPages(int active) {
        final String BFG = "\u001B[30m";
        final String BBG = "\u001B[40m";
        final String WFG = "\u001B[37m";
        final String WBG = "\u001B[47m";
        for (int i = 0; i < pages.size(); i++) {
            if (i == active) {
                System.out.print(BFG + WBG + pages.get(i).getName());
            }
            else {
                System.out.print(WFG + BBG + pages.get(i).getName());
            }
        }
    }
}