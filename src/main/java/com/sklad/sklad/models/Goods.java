package com.sklad.sklad.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double priceBuy, priceSell;
    private int percent, count;

    public void setId(Long id) {
        this.id = id;
    }

    public Goods(String name, String priceBuy, String priceSell, String count) {
        this.name = name;
        this.priceBuy = Double.parseDouble(priceBuy);
        this.priceSell = Double.parseDouble(priceSell);
        this.count = Integer.parseInt(count);
    }

    public Goods() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public void setPriceSell(double priceSell) {
        this.priceSell = priceSell;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public double getPriceSell() {
        return priceSell;
    }

    public int getPercent() {
        return percent;
    }

    public int getCount() {
        return count;
    }
}
