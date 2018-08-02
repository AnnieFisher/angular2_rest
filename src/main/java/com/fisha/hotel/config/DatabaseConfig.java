package com.fisha.hotel.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.fisha.hotel.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}
