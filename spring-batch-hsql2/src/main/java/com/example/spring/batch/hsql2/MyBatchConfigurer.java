package com.example.spring.batch.hsql2;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;
import java.util.HashMap;

public class MyBatchConfigurer extends DefaultBatchConfigurer {

    @Autowired
    @Qualifier("memory")
    private DataSource dataSource;
}
