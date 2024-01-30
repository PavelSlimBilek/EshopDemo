package com.example.myfirsteshop.model;

public class Product implements Comparable<Product> {

    private static int ID = 0;

    private int id;
    private ProdType type;
    private Brand brand;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private String imgPath;

    // CONSTRUCTORS
    public Product(ProdType type, Brand brand, String name, String description, int price, int quantity, String imgPath) {
        initID();
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imgPath = imgPath;
    }

    public Product() {
        initID();
    }

    // ID init
    private void initID() {
        this.id = ID++;
    }

    public int getId() { return this.id; }
    public ProdType getType() { return this.type; }
    public Brand getBrand() { return this.brand; }
    public String getName() { return this.name; }
    public double getPrice() { return this.price; }
    public String getDescription() { return this.description; }
    public int getQuantity() { return this.quantity; }
    public String getImgPath() { return this.imgPath; }
    public String getBalInString() { return String.format("%.2f Kč", this.price); }
    public String getStockStatus() { return this.quantity > 5 ? "more than 5 on stock" : String.format("%d on stock", this.quantity); }


    @Override
    public String toString() {
        return String.format("%d: %s %s - $ %.2f - Q: %d",
                this.id,
                this.type.toString(),
                this.name,
                this.price,
                this.quantity);
    }

    @Override
    public int compareTo(Product p) { return Double.compare(this.price, p.price); }
}
