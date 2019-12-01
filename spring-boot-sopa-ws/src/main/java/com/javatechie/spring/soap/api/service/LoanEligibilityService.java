package com.javatechie.spring.soap.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javatechie.spring.soap.api.loaneligibility.Acknowledgement;
import com.javatechie.spring.soap.api.loaneligibility.CustomerRequest;
import com.javatechie.spring.soap.api.util.ServiceContant;

@Service
public class LoanEligibilityService {

	
	public Acknowledgement checkLoanEligibility(CustomerRequest request) {
		Acknowledgement acknowledgement = new Acknowledgement();
		List<String> mismatchCriteriaList = acknowledgement.getCriteriaMismatch();
		
		if (!(request.getAge() > 30 && request.getAge() <= 60)) {
			mismatchCriteriaList.add(ServiceContant.PERSON_AGE_SHOULD_IN_BETWEEN_30_TO_60);
		}
		if (!(request.getYearlyIncome() > 200000)) {
			mismatchCriteriaList.add(ServiceContant.MINIMUM_INCOME_SHOULD_BE_MORE_THAN_200000);
		}
		if (!(request.getCibilScore() > 500)) {
			mismatchCriteriaList.add(ServiceContant.LOW_CIBIL_SCORE_PLEASE_TRY_AFTER_6_MONTH);
		}

		if (mismatchCriteriaList.size() > 0) {
			acknowledgement.setApprovedAmount(0);
			acknowledgement.setIsEligible(false);
		} else {
			acknowledgement.setApprovedAmount(500000);
			acknowledgement.setIsEligible(true);
			mismatchCriteriaList.clear();
		}
		return acknowledgement;

	}

}
