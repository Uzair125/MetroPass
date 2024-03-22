package com.geektrust.backend.helpers;

import com.geektrust.backend.entities.Stations;

public class CheckValidStation {

    public boolean validStation(String checkStation) {
        for (Stations s : Stations.values()) {
            if (s.name().equals(checkStation)) {
                return true;
            }
        }
        return false;
    }
}
