package com.chulman.batch.recipe11_7;


import com.chulman.batch.UserRegistration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.chulman.batch.recipe11_7")
public class ConcurrencyUserJob {

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

    // 스프링 배치는 다른 프로세스의 짐을 덜어 주는 메커니즘을 제공
    // 스프링 배치는 파티셔닝 기법으로 암시적인 스케일 아웃을 지원한다.
    // 이 기능은 이미 내장되어 있고 아주 유연한 편이다.
    // step 인스턴스를 하위 객체 partitionStep으로 교체하면 분산 실행기를 조정하고 스텝 실행과 연관된 메타데이터를 관리하므로 원격 청킹 기술이 제공했던 지속적인 통신 매체는 더이상 필요없다.
    @Bean
    public Job insertIntoDbFromCsvJob() throws Exception {
        JobBuilder builder = jobs.get("insertIntoDbFromCsvJob");
//      return builder
//                .start()
//                .split(taskExecutors())
//                .add(builder.flow(reprotStatistics()),
//                        ...
//                )
//        .build();
        return null;
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