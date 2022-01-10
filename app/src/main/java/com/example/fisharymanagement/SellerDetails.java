package com.example.fisharymanagement;

public class SellerDetails {
    private String mtvSellerNameHere;
    private String mtvSellerPhone;

    public SellerDetails(String tvSellerNameHere, String tvSellerPhone) {
        mtvSellerNameHere = tvSellerNameHere;
        mtvSellerPhone = tvSellerPhone;
    }
    public String getMtvSellerNameHere() {
        return mtvSellerNameHere;
    }

    public String getMtvSellerPhone() {
        return mtvSellerPhone;
    }
}