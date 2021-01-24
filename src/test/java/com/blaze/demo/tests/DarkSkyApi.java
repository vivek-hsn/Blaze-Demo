package com.blaze.demo.tests;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.blaze.demo.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DarkSkyApi extends BaseTest{
	
	private static final String api_key = "6508dfe23c140cf146a5ad4c2b233282";
	private static final double lat = 40.73;
	private static final double lon = -73.93;
	
	private static RequestSpecification httpRequest;
	private static Response response;
	private static JsonPath js;
		
	@BeforeClass
	public void setup() throws InterruptedException {
		
		logger = Logger.getLogger("RestAssuredFrameWork");
		
		PropertyConfigurator.configure("log4j.properties");
		
		logger.setLevel(Level.ALL);

		RestAssured.baseURI = "https://api.darksky.net/forecast";

		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/" +api_key +"/" +lat +"," +lon);

		Thread.sleep(3000);
	}
	
	@Test()
	public void checkStatusCode(){
		
		logger.info("Checking the status code");
		
		logger.info("Status code is " + response.getStatusCode());
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test()
	public void checkTopLevelStructure(){
		
		js = response.jsonPath();
		Assert.assertNotNull(js.get("latitude"), "Latitude is null");
		Assert.assertNotNull(js.get("longitude"), "Longitude is null");
		Assert.assertNotNull(js.get("timezone"), "Timezone is null");
		Assert.assertNotNull(js.get("currently"), "Currently is null");
		Assert.assertNotNull(js.get("minutely"), "Minutely is null");
		Assert.assertNotNull(js.get("hourly"), "Hourly is null");
		Assert.assertNotNull(js.get("daily"), "Daily is null");
		Assert.assertNotNull(js.get("flags"), "Flags is null");
		Assert.assertNotNull(js.get("offset"), "Offset is null");
		
		logger.info("Latitude value is " +js.get("latitude"));
		logger.info("Longitude value is " +js.get("longitude"));
		logger.info("Timezone value is " +js.get("timezone"));
		logger.info("Currently value is " +js.get("currently"));
		logger.info("Minutely value is " +js.get("minutely"));
		logger.info("Hourly value is " +js.get("hourly"));
		logger.info("Daily value is " +js.get("daily"));
		logger.info("Flags value is " +js.get("flags"));
		logger.info("Offset value is " +js.get("offset"));
		
	}
	
	@Test()
	public void verifyMinutelyArray(){
		
		js = response.jsonPath();
		ArrayList ja = js.get("minutely.data");
		Assert.assertEquals(ja.size(), 61, "Minutely array is not equal to 61");
		logger.info(js.get("minutely.data"));
		
	}
	
	@Test()
	public void verifyHourlyArray(){
		
		js = response.jsonPath();
		ArrayList ja = js.get("hourly.data");
		Assert.assertEquals(ja.size(), 49, "Hourly array is not equal to 49");
		logger.info(js.get("minutely.data"));
		
	}
	
	@Test()
	public void verifyDailyArray(){
		
		js = response.jsonPath();
		ArrayList ja = js.get("daily.data");
		Assert.assertEquals(ja.size(), 8, "Daily array is not equal to 8");
		logger.info(js.get("minutely.data"));
		
	}

}
