package com.chulm.access;

import com.chulman.access.transaction.config.TransactionConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TransactionConfig.class)
public class PlatformTransactionDaoTest {

    PlatformTransactionManager platformTransactionManager;


}
