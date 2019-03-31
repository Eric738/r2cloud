package ru.r2cloud.it.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.Properties;

import org.junit.Before;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

public abstract class BaseTest {

	protected RestClient client;

	protected String username = "info@r2cloud.ru";
	protected String password = "1";
	protected String keyword = "ittests";

	protected Properties config;

	@Before
	public void start() throws Exception {
		client = new RestClient(System.getProperty("r2cloud.baseurl"));
		config = new Properties();
		try (InputStream is = BaseTest.class.getClassLoader().getResourceAsStream("config-dev.properties")) {
			config.load(is);
		}
	}

	public static void assertErrorInField(String field, HttpResponse<String> response) {
		JsonObject result = (JsonObject) Json.parse(response.body());
		JsonObject errors = (JsonObject) result.get("errors");
		assertNotNull(errors);
		assertNotNull(errors.get(field));
	}
	
	public static void assertTempEmpty() {
		File temp = new File(WebTest.TEMP_DIRECTORY);
		if( !temp.exists() ) {
			return;
		}
		assertTrue(temp.isDirectory());
		File[] contents = temp.listFiles();
		assertEquals("expected empty, but got: " + contents.length, 0, contents.length);
	}

}
