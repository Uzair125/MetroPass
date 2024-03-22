package com.geektrust.backend.entities;

public enum PassengerCharge {
    ADULT(200),
    SENIOR_CITIZEN(100),
    KID(50);

    private int charge;

    PassengerCharge(int charge){
        this.charge = charge;
    }

    public int getCharge(){
        return charge;
    }
}
