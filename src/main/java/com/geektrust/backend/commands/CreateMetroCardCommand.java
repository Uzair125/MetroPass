package com.geektrust.backend.commands;

import com.geektrust.backend.entities.Metrocard;
import com.geektrust.backend.services.IMetroCardService;

import java.util.List;

public class CreateMetroCardCommand implements ExecuteCommand{

    public final IMetroCardService metroCardService;

    public CreateMetroCardCommand(IMetroCardService metroCardService) {
        this.metroCardService = metroCardService;
    }

    @Override
    public void execute(List<String> tokens) {
        String cardNumber = tokens.get(1);
        Integer balance = Integer.valueOf(tokens.get(2));
        try{
            Metrocard metrocard = metroCardService.create(cardNumber,balance);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
