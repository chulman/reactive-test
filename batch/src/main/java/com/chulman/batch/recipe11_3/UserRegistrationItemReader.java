package com.chulman.batch.recipe11_3;

import com.chulman.batch.UserRegistration;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Date;

public class UserRegistrationItemReader implements ItemReader<UserRegistration> {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationItemReader(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public UserRegistration read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        final Date today = new Date();
        return userRegistrationService.getOutstandingUserRegistrationBatchForDate(1, today)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
