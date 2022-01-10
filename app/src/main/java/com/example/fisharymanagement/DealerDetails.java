package com.example.fisharymanagement;

public class DealerDetails {
    private String mtvDealerNameHere ;
    private String mtvDealerPhone;

    public DealerDetails(String tvDealerNameHere,String tvDealerPhone){
        mtvDealerNameHere = tvDealerNameHere;
        mtvDealerPhone = tvDealerPhone;
    }

    public String getMtvDealerNameHere() {
        return mtvDealerNameHere;
    }

    public String getMtvDealerPhone() {
        return mtvDealerPhone;
    }
}
