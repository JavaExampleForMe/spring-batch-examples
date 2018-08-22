package com.example.spring.batch.sqlserver;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class SavingItemWriter implements ItemWriter<Transaction> {
    private StepExecution stepExecution;

    @Override
    public void write(List<? extends Transaction> list) throws Exception {
        ExecutionContext stepContext = this.stepExecution.getExecutionContext();
        stepContext.put("someKey", "Ida");
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
