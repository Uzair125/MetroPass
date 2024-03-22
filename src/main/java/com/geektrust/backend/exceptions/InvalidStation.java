package com.geektrust.backend.exceptions;

public class InvalidStation extends RuntimeException{
    public InvalidStation()
    {
        super();
    }
    public InvalidStation(String msg)
    {
        super(msg);
    }
}