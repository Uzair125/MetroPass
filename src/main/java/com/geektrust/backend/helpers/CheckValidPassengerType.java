package com.geektrust.backend.helpers;

import com.geektrust.backend.entities.PassengerType;

public class CheckValidPassengerType {
    public boolean validPassengerType(String passengerType) {
        for (PassengerType pt : PassengerType.values()) {
            if (pt.name().equals(passengerType)) {
                return true;
            }
        }
        return false;
    }
}
