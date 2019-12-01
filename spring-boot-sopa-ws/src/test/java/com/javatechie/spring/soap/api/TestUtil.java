package com.javatechie.spring.soap.api;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;

import org.springframework.xml.transform.StringSource;

import com.javatechie.spring.soap.api.loaneligibility.Acknowledgement;
import com.javatechie.spring.soap.api.loaneligibility.CustomerRequest;
import com.javatechie.spring.soap.api.loaneligibility.ObjectFactory;
import com.javatechie.spring.soap.api.util.ServiceContant;

public class TestUtil {
	
	static ObjectFactory factory= new ObjectFactory();
	public static CustomerRequest populatePositiveRequest() {
		CustomerRequest req= factory.createCustomerRequest();
		//CustomerRequest req= new CustomerRequest();
		req.setCustomerName("Roddur");
		req.setAge(46);
		req.setYearlyIncome(200001);
		req.setCibilScore(5600);
		req.setEmploymentMode("Permanent");
		return req;
	}
	
	public static Acknowledgement populatePositiveResponse() {
		Acknowledgement resp= factory.createAcknowledgement();
		//Acknowledgement resp= new Acknowledgement();
		//resp.getCriteriaMismatch().add("minimum income should be moret than 2000");
		resp.setApprovedAmount(500000);
		resp.setIsEligible(true);
		return resp;
	}
	
	public static CustomerRequest populateNegativeRequest() {
		CustomerRequest req= factory.createCustomerRequest();
		//CustomerRequest req= new CustomerRequest();
		req.setCustomerName("Raja");
		req.setAge(46);
		req.setYearlyIncome(500);
		req.setCibilScore(5600);
		req.setEmploymentMode("Permanent");
		return req;
	}
	public static Acknowledgement populateNegativeResponse() {
		Acknowledgement resp= factory.createAcknowledgement();
		//Acknowledgement resp= new Acknowledgement();
		resp.getCriteriaMismatch().add(ServiceContant.MINIMUM_INCOME_SHOULD_BE_MORE_THAN_200000);
		resp.setApprovedAmount(0);
		resp.setIsEligible(false);
		return resp;
	}
	
	public static  Source getSourceFromJAXBObject(Object jaxb) throws Exception {
		return new JAXBSource(JAXBContext.newInstance(jaxb.getClass()), jaxb);
	}
	
	public static Source getSourceFromXMLString(String xmlstring) throws Exception {
		return new StringSource(xmlstring);
	}

}
