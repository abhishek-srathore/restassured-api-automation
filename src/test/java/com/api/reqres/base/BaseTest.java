package com.api.reqres.base;

import io.restassured.RestAssured;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

import com.api.reqres.utilty.ConfigReader;


public class BaseTest {
	
	private static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeClass
    public void setUp() {
    	log.info("Initating Test Suit");
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
    }
}
