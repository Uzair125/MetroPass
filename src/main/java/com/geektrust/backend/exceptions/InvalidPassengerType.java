package com.geektrust.backend.exceptions;

public class InvalidPassengerType  extends RuntimeException{
    public InvalidPassengerType()
    {
        super();
    }
    public InvalidPassengerType(String msg)
    {
        super(msg);
    }
}