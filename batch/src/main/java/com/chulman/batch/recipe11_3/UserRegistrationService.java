package com.chulman.batch.recipe11_3;

import com.chulman.batch.UserRegistration;

import java.util.Collection;
import java.util.Date;

public interface UserRegistrationService {

    Collection<UserRegistration> getOutstandingUserRegistrationBatchForDate(int quantity, Date date);
    UserRegistration registerUser(UserRegistration userRegistration);
}
