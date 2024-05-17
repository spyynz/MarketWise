import java.util.ArrayList;

public class StockMarket {
    private ArrayList<Stock> stocks;

    public StockMarket() {
        stocks = new ArrayList<Stock>();
        //get all US stocks from the finnlab.io web api
        //for each of the stocks, create a new Stock object w/ info
        //and add it to the stocks ArrayList
    }

    public void update() {
        //for each item in the stocks ArrayList, search for the
        //item in the finnlab.io web api and find a match, then
        //update all variable info
    }
}