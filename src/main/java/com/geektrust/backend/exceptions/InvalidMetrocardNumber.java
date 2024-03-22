package com.geektrust.backend.exceptions;

public class InvalidMetrocardNumber extends RuntimeException{
    public InvalidMetrocardNumber()
    {
        super();
    }
    public InvalidMetrocardNumber(String msg)
    {
        super(msg);
    }
}