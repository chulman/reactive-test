package com.chulman.batch.recipe11_9;

import com.chulman.batch.UserRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;


public class UserRegistrationValidationItemProcessor implements ItemProcessor<UserRegistration, UserRegistration> {

    private StepExecution stepExecution;
    private Collection<String> states;

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationValidationItemProcessor.class);

    public UserRegistrationValidationItemProcessor() {
        this.states = Arrays.asList((
                "AL AK AS AZ AR CA CO CT DE DC FM " +
                        "FL GA GU HI ID IL IN IA KS KY LA ME MH MD " +
                        "MA MI MN MS MO MT NE NV NH NJ NM NY NC ND " +
                        "MP OH OK OR PW PA PR RI SC SD TN TX UT " +
                        "VT VI VA WA WV WI WY").split(" "));
    }

    private String stripNonNumbers(String input) {
        String output = input == null ? "" : input;
        StringBuilder numbersOnly = new StringBuilder();
        for (char potentialDigit : output.toCharArray()) {
            if (Character.isDigit(potentialDigit)) {
                numbersOnly.append(potentialDigit);
            }
        }
        return numbersOnly.toString();
    }

    private boolean isTelephoneValid(String telephone) {
        return StringUtils.hasText(telephone) && telephone.length() == 10;
    }

    private boolean isZipCodeValid(String zip) {
        return StringUtils.hasText(zip) && ((zip.length() == 5) || (zip.length() == 9));
    }

    private boolean isValidState(String state) {
        return StringUtils.hasText(state) && states.contains(state.trim());
    }


    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution){
        this.stepExecution = stepExecution;
    }

    public UserRegistration process(UserRegistration input)
            throws Exception {

        /**
         * parameter 가쟈올 수 있음 하지만 매우 제한 적임. batChConfiguration의 @StepScope를 활용할 수 있음.
         */
        Map<String, JobParameter> map = stepExecution.getJobParameters().getParameters();

        String zipCode = stripNonNumbers(input.getZip());
        String telephone = stripNonNumbers(input.getPhoneNumber());
        String state = input.getState();

        if (isTelephoneValid(telephone) && isZipCodeValid(zipCode) && isValidState(state)) {
            input.setZip(zipCode);
            input.setPhoneNumber(telephone);
            logger.debug("input is valid, returning");
            return input;
        }

        logger.debug("Returning null");
        return null;
    }


}