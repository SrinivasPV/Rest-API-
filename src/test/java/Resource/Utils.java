package Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification reqSpecBuilder() throws FileNotFoundException {
		
		PrintStream p = new PrintStream(new File("./target/logging.txt"));
		RequestSpecification reqBody = new RequestSpecBuilder().setBaseUri("http://216.10.245.166").setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(p))
				.addFilter(ResponseLoggingFilter.logResponseTo(p))
				.build();
		return reqBody;
		
	}
	
	public static String getJasonPath(String response, String path)
	{
		JsonPath js = new JsonPath(response);
		String object = js.get(path);
		return object;
	}

}
