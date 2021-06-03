package com.example.fisharymanagement;

public class FishDetails {
    private String mtvFishName , mtvDealerName;
    private int mtvQuantity , mtvTotalPur , mtvSoldQuantity;

    public FishDetails(String tvFishName, int tvQuantity, int tvTotalPur, int tvSoldQuantity, String tvDealerName){
        mtvFishName = tvFishName;
        mtvQuantity = tvQuantity;
        mtvTotalPur = tvTotalPur;
        mtvSoldQuantity = tvSoldQuantity;
        mtvDealerName = tvDealerName;

    }

    public String getMtvFishName(){
        return mtvFishName;

    }

    public int getMtvQuantity(){
        return mtvQuantity;
    }

    public int getMtvTotalPur(){
        return mtvTotalPur;
    }

    public int getMtvSoldQuantity(){
        return mtvSoldQuantity;
    }

    public String getMtvDealerName(){
        return mtvDealerName;
    }
}
