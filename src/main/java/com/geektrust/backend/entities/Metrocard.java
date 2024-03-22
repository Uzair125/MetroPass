package com.geektrust.backend.entities;

public class Metrocard extends BaseEntity{
    private final String cardNumber;
    private final Integer balance;

    public Metrocard(Metrocard metrocard){
        this(metrocard.id,metrocard.cardNumber,metrocard.balance);
    }

    public  Metrocard(String id,String cardNumber, Integer balance){
        this(cardNumber,balance);
        this.id=id;
    }

    public Metrocard(String cardNumber, Integer balance) {
        this.cardNumber=cardNumber;
        this.balance=balance;
    }

    public String getCardNumber(){
        return cardNumber;
    }


    public Integer getBalance(){
        return balance;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Metrocard other = (Metrocard) obj;
//        if (id == null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.id))
//            return false;
//        return true;
//    }
}
