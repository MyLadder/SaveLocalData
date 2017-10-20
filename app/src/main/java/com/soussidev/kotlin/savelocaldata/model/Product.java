package com.soussidev.kotlin.savelocaldata.model;

/**
 * Created by Soussi on 15/10/2017.
 */

public class Product {

    private int Id_prod;
    private String Nom_prod;
    private Marque Marque_prod;
    private String model_product;
    private Double Price_prod;
    private String Year_prod;
    private int img_prod;


    public Product() {
    }

    public Product(int id_prod, String nom_prod, Marque marque_prod, String model_product, Double price_prod, String year_prod, int img_prod) {
        Id_prod = id_prod;
        Nom_prod = nom_prod;
        Marque_prod = marque_prod;
        this.model_product = model_product;
        Price_prod = price_prod;
        Year_prod = year_prod;
        this.img_prod = img_prod;
    }

    public int getId_prod() {
        return Id_prod;
    }

    public void setId_prod(int id_prod) {
        Id_prod = id_prod;
    }

    public String getNom_prod() {
        return Nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        Nom_prod = nom_prod;
    }

    public Marque getMarque_prod() {
        return Marque_prod;
    }

    public void setMarque_prod(Marque marque_prod) {
        Marque_prod = marque_prod;
    }

    public String getModel_product() {
        return model_product;
    }

    public void setModel_product(String model_product) {
        this.model_product = model_product;
    }

    public Double getPrice_prod() {
        return Price_prod;
    }

    public void setPrice_prod(Double price_prod) {
        Price_prod = price_prod;
    }

    public String getYear_prod() {
        return Year_prod;
    }

    public void setYear_prod(String year_prod) {
        Year_prod = year_prod;
    }

    public int getImg_prod() {
        return img_prod;
    }

    public void setImg_prod(int img_prod) {
        this.img_prod = img_prod;
    }
}
