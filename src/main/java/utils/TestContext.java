package utils;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class TestContext {
	PrintStream log;
	public Response response;
	public Map<String, Object> session = new HashMap<String, Object>();
	private static final String CONTENT_TYPE = "application/json";
//	private static final String CONTENT_TYPE = PropertiesFile.getProperty("content.type");
	public RequestSpecification requestSetup() throws FileNotFoundException {
		RestAssured.reset();
		Options options = Options.builder().logStacktrace().build();
		RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options); 
//		RestAssured.baseURI = PropertiesFile.getPropertys("baseURL");
		log = new PrintStream (new FileOutputStream ("log.txt"));
		RestAssured.baseURI ="https://restful-booker.herokuapp.com";
		return RestAssured.given()
				.config(config)
				.filter(new RestAssuredRequestFilter())				
				.contentType(CONTENT_TYPE)
				.accept(CONTENT_TYPE)
				.filter (RequestLoggingFilter.logRequestTo(log));

	} 
}
