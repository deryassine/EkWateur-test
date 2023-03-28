package com.example.ekwateurtest.services;

import com.example.ekwateurtest.exception.CustomerTypeNullException;
import com.example.ekwateurtest.models.Customer;
import com.example.ekwateurtest.models.CustomerType;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class InvoiceService {

    public String calculate(Customer customer) {
        checkCustomerType(customer);
        String invoice = null;
        if (CustomerType.INDIVIDUAL.equals(customer.getType())) {
            invoice = calculateInvoice(customer
                    , Constants.GAZ_PRICE_PER_KW_FOR_INDIVIVDUAL
                    , Constants.ELEC_PRICE_PER_KW_FOR_INDIVIVDUAL);
        }
        if (CustomerType.COMPANY.equals(customer.getType())) {
            if (customer.getTurnover() > Constants.ONE_MILLION) {
                invoice = calculateInvoice(customer
                        , Constants.GAZ_PRICE_PER_KW_FOR_COMPANY_OVER_1_M
                        , Constants.ELEC_PRICE_PER_KW_FOR_COMPANY_OVER_1_M);
            } else {
                invoice = calculateInvoice(customer
                        , Constants.GAZ_PRICE_PER_KW_FOR_COMPANY_UNDER_1_M
                        , Constants.ELEC_PRICE_PER_KW_FOR_COMPANY_UNDER_1_M);
            }
        }
        return invoice;
    }

    private static void checkCustomerType(Customer customer) {
        if (Objects.isNull(customer.getType()))
            throw new CustomerTypeNullException(Constants.CUSTOMER_TYPE_COULD_NOT_BE_NUL_EXCEPTION_MESSAGE);
    }

    private static String calculateInvoice(Customer customer, Double gazIndicePrice, Double electricyIndicePrice) {
        double gazTotal = customer.getEnergyResource().getGas() * gazIndicePrice;
        double electricityTotal = customer.getEnergyResource().getGas() * electricyIndicePrice;
        return "Total invoice amount for gaz is " + gazTotal + " and for electricity is " + electricityTotal;
    }
}
