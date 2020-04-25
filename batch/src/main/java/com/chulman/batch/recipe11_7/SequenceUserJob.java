package com.chulman.batch.recipe11_7;


import com.chulman.batch.UserRegistration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.chulman.batch.recipe11_7")
public class SequenceUserJob {

    private static final String INSERT_REGISTRATION_QUERY =
            "insert into USER_REGISTRATION (FIRST_NAME, LAST_NAME, COMPANY, ADDRESS,CITY,STATE,ZIP,COUNTY,URL,PHONE_NUMBER,FAX)" +
                    " values " +
                    "(:firstName,:lastName,:company,:address,:city,:state,:zip,:county,:url,:phoneNumber,:fax)";

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private DataSource dataSource;

    @Value("file:${user.home}/batches/registrations.csv")
    private Resource input;

    //순차 실행
    @Bean
    public Job insertIntoDbFromCsvJob() throws Exception {
        return jobs.get("User Registration Import Job")
                .start(step1())
                .next(steps.get(" next step").chunk(3).build()) // next
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return steps.get("User Registration CSV To DB Step")
                .<UserRegistration,UserRegistration>chunk(5)
                .reader(csvFileReader())
                .processor(userRegistrationValidationItemProcessor())
                .writer(jdbcItemWriter())//스프링 배치가 컨텍스트에서 트랜잭션 매니저를 사용할 수 있기 때문에 writer에서 지정하지 않아도 됨.
                .transactionManager(new DataSourceTransactionManager(dataSource))
                .build();
    }


    //processor는 안전장치 역할을 할 수 있다.
    @Bean
    public UserRegistrationValidationItemProcessor userRegistrationValidationItemProcessor() {
        return new UserRegistrationValidationItemProcessor();
    }
    @Bean
    public FlatFileItemReader<UserRegistration> csvFileReader() throws Exception {

        return new FlatFileItemReaderBuilder<UserRegistration>()
                .name(ClassUtils.getShortName(FlatFileItemReader.class))
                .resource(input)
                .targetType(UserRegistration.class)
                .delimited()
                .names(new String[]{"firstName","lastName","company","address","city","state","zip","county","url","phoneNumber","fax"})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<UserRegistration> jdbcItemWriter() {
        return new JdbcBatchItemWriterBuilder<UserRegistration>()
                .dataSource(dataSource)
                .sql(INSERT_REGISTRATION_QUERY)
                .beanMapped()
                .build();
    }
}