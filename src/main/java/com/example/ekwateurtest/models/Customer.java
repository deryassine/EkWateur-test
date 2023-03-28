package com.example.ekwateurtest.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Customer extends CustomerReference{

    CustomerType type;
    Long siret;
    String socialReason;
    Integer turnover;
    String firstName;
    String lastName;
    EnergyResource energyResource;
}
