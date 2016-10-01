package utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ParserJson {
	public static JsonArray parsearJsonArray(HttpServletRequest request) {
		try {
			JsonElement jEle = new JsonParser().parse(request.getReader().readLine());
			return jEle.getAsJsonArray();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JsonObject parsearJsonObject(HttpServletRequest request) {
		try {
			JsonElement jEle = new JsonParser().parse(request.getReader().readLine());
			return jEle.getAsJsonObject();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JsonObject parsearJsonObject(String jsonBestSellers) {
		try {
			JsonElement jEle = new JsonParser().parse(jsonBestSellers);
			return jEle.getAsJsonObject();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}