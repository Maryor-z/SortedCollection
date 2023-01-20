package com.maryor;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 100, 20);
        stockList.addStock(temp);

        temp = new StockItem("cake", 5000, 3);
        stockList.addStock(temp);

        temp = new StockItem("car", 2000, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 3000, 4);
        stockList.addStock(temp);

        temp = new StockItem("cup", 500, 5);
        stockList.addStock(temp);
        temp = new StockItem("cup", 400, 6);
        stockList.addStock(temp);

        temp = new StockItem("door", 3500, 3);
        stockList.addStock(temp);

        temp = new StockItem("juice", 500, 5);
        stockList.addStock(temp);

        temp = new StockItem("phone", 3200, 2);
        stockList.addStock(temp);

        temp = new StockItem("towel", 400, 5);
        stockList.addStock(temp);

        temp = new StockItem("vase", 250, 2);
        stockList.addStock(temp);

        Basket maryorBasket = new Basket("Maryor");

        sellItem(maryorBasket, "car", 1);
        System.out.println(maryorBasket);

        sellItem(maryorBasket, "car", 1);
        System.out.println(maryorBasket);

        if (sellItem(maryorBasket, "car", 1) != 1) {
            System.out.println("there are no cars in stock");
        }

        sellItem(maryorBasket, "spanner", 5);
//        System.out.println(maryorBasket);

        sellItem(maryorBasket, "juice", 4);
        sellItem(maryorBasket, "cup", 12);
        sellItem(maryorBasket, "bread", 1);
//        System.out.println(maryorBasket);

//        System.out.println(stockList);
        Basket basket = new Basket("customer");
        sellItem(basket, "cup", 100);
        sellItem(basket, "juice", 5);
        removeItem(basket, "cup", 1);
        System.out.println(basket);

        removeItem(maryorBasket, "car", 1);
        removeItem(maryorBasket, "cup", 10);
        removeItem(maryorBasket, "car", 1);

        System.out.println("cars removed: " + removeItem(maryorBasket, "car", 1)); // shoundnt remove any

        System.out.println(maryorBasket);

        removeItem(maryorBasket, "bread", 1);
        removeItem(maryorBasket, "cup", 3);
        removeItem(maryorBasket, "juice", 4);
        removeItem(maryorBasket, "cup", 3);

        System.out.println(maryorBasket);

        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stockList);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);


//        temp = new StockItem("pen", 100);
//        stockList.Items().put(temp.getName(), temp);
        StockItem car = stockList.Items().get("car");
        if (car != null) {
            car.adjustStock(2000);
        }
        if (car != null) {
            stockList.get("car").adjustStock(-1000);
        }
        stockList.Items().get("car").adjustStock(1000);
        stockList.get("car").adjustStock(-500);
        System.out.println(stockList);

//        for (Map.Entry<String, Double> price : stockList.PriceList().entrySet()) {
//            System.out.println(price.getKey() + " cost " + price.getValue());
//        }

        checkOut(maryorBasket);
        System.out.println(maryorBasket);

    }

    public static int sellItem(Basket basket, String item, int quantity) {
//        retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We dont sell " + item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
//        retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We dont sell " + item);
            return 0;
        }
        if (basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkOut(Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}