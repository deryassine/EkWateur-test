package com.example.ekwateurtest.utils;

import com.example.ekwateurtest.models.Customer;
import com.example.ekwateurtest.models.CustomerType;
import com.example.ekwateurtest.models.EnergyResource;

public class InvoiceServiceUtil {

    public static Customer buildIndividualCustomer() {
        EnergyResource energy = EnergyResource.builder()
                .gas(2)
                .electricity(3)
                .build();

        return Customer.builder()
                .energyResource(energy)
                .firstName("Yassine")
                .type(CustomerType.INDIVIDUAL)
                .lastName("Dergueche")
                .build();

    }

    public static Customer buildCompanyCustomer(Integer turnover) {
        EnergyResource energy = EnergyResource.builder()
                .gas(2)
                .electricity(3)
                .build();

        return Customer.builder()
                .energyResource(energy)
                .type(CustomerType.COMPANY)
                .turnover(turnover)
                .siret(123456L)
                .socialReason("EkWateur")
                .build();
    }

}
