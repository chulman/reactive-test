package com.chulman.batch.recipe11_3;

import com.chulman.batch.UserRegistration;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class UserRegistrationItemWriter implements ItemWriter<UserRegistration> {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationItemWriter(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public void write(List<? extends UserRegistration> items) throws Exception {
        items.forEach(item -> userRegistrationService.registerUser(item));
    }
}
