package com.javatechie.spring.soap.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.test.server.MockWebServiceClient;

import com.javatechie.spring.soap.api.config.SoapWSConfig;
import com.javatechie.spring.soap.api.loaneligibility.Acknowledgement;
import com.javatechie.spring.soap.api.loaneligibility.CustomerRequest;
import com.javatechie.spring.soap.api.service.LoanEligibilityService;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SoapWSConfig.class)
@TestPropertySource("classpath:application-test.properties")
public class SpringBootSopaWsApplicationTests {
	@Autowired
	public LoanEligibilityService service;
	
	@Autowired
	private ApplicationContext appContext;
	
	//NOT AUTOWIRED!!!!!!!!!!!!!!!!!!!
	private MockWebServiceClient mockClient;
	
	@Value("${mvp.switch.rabi}")
	private String bypass;
	
	@Before
	public void init() {
		mockClient= MockWebServiceClient.createClient(appContext);
	}
	@Test
	public void testPositive() throws Exception{
		
		CustomerRequest req= TestUtil.populatePositiveRequest();
		Acknowledgement resp= TestUtil.populatePositiveResponse();
		
		mockClient.sendRequest(withPayload(TestUtil.getSourceFromJAXBObject(req)))
		.andExpect(payload(TestUtil.getSourceFromJAXBObject(resp)));
	}
	
	@Test
	public void testNegative() throws Exception{
		
		CustomerRequest req= TestUtil.populateNegativeRequest();
		Acknowledgement resp= TestUtil.populateNegativeResponse();
		
		mockClient.sendRequest(withPayload(TestUtil.getSourceFromJAXBObject(req)))
		.andExpect(payload(TestUtil.getSourceFromJAXBObject(resp)));
	}
	
	
	
	
	

}
