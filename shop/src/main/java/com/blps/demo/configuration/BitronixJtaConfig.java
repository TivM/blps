package com.blps.demo.configuration;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


@Configuration
public class BitronixJtaConfig {

    @Bean(name = "bitronixTransactionManager")
    @DependsOn
    public BitronixTransactionManager bitronixTransactionManager() throws Throwable {
        BitronixTransactionManager bitronixTransactionManager = TransactionManagerServices.getTransactionManager();
        bitronixTransactionManager.setTransactionTimeout(10000);
        return bitronixTransactionManager;
    }
}
