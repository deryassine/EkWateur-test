package com.example.ekwateurtest.services;

import com.example.ekwateurtest.exception.CustomerTypeNullException;
import com.example.ekwateurtest.models.Customer;
import com.example.ekwateurtest.utils.InvoiceServiceUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    @Test
    void should_calculate_invoice_for_an_individual() {
        //GIVEN
        Customer customer = InvoiceServiceUtil.buildIndividualCustomer();
        String expectedInvoice = "Total invoice amount for gaz is 0.242 and for electricity is 0.23";

        //WHEN
        String actualInvoice = invoiceService.calculate(customer);

        //THEN
        assertEquals(expectedInvoice, actualInvoice);
    }

    @Test
    void should_calculate_invoice_for_a_company_with_turnover_over_than_1_million() {
        //GIVEN
        Customer customer = InvoiceServiceUtil.buildCompanyCustomer(3000000);
        String expectedInvoice = "Total invoice amount for gaz is 0.242 and for electricity is 0.242";

        //WHEN
        String actualInvoice = invoiceService.calculate(customer);

        //THEN
        assertEquals(expectedInvoice, actualInvoice);
    }

    @Test
    void should_calculate_invoice_for_a_company_with_turnover_less_than_1_million() {
        //GIVEN
        Customer customer = InvoiceServiceUtil.buildCompanyCustomer(900000);
        String expectedInvoice = "Total invoice amount for gaz is 0.23 and for electricity is 0.23";

        //WHEN
        String actualInvoice = invoiceService.calculate(customer);

        //THEN
        assertEquals(expectedInvoice, actualInvoice);
    }


    @Test
    void should_throw_exception_when_no_customer_type_found() {
        //GIVEN
        Customer customer = Customer.builder().build();

        //WHEN & THEN
        assertThrows(CustomerTypeNullException.class
                , () -> invoiceService.calculate(customer)
                , Constants.CUSTOMER_TYPE_COULD_NOT_BE_NUL_EXCEPTION_MESSAGE);
    }
}