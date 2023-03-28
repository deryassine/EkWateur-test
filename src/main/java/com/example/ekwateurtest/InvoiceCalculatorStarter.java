package com.example.ekwateurtest;

import com.example.ekwateurtest.services.InvoiceService;
import com.example.ekwateurtest.utils.InvoiceServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InvoiceCalculatorStarter implements CommandLineRunner {
    @Autowired
    private InvoiceService invoiceService;

    @Override
    public void run(String... args) throws Exception {
        log.info("");
        log.info("------Welcome to invoice service calculator------");
        log.info("Processing invoice of an individual");
        String invoice = invoiceService.calculate(InvoiceServiceUtil.buildIndividualCustomer());
        log.info(invoice);

        log.info("");
        log.info("------------------------------------------------");
        log.info("");

        log.info("Processing invoice for a company with a turnover over than 1 million");
        invoice = invoiceService.calculate(InvoiceServiceUtil.buildCompanyCustomer(3000000));
        log.info(invoice);

        log.info("");
        log.info("------------------------------------------------");
        log.info("");

        log.info("Processing invoice for a company with a turnover less than 1 million");
        invoice = invoiceService.calculate(InvoiceServiceUtil.buildCompanyCustomer(900000));
        log.info(invoice);

        log.info("");
        log.info("------thank you for using our service------");

    }
}
