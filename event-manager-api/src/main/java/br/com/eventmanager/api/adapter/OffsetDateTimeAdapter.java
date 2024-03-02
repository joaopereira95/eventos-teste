package br.com.eventmanager.api.adapter;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class OffsetDateTimeAdapter implements JsonDeserializer<OffsetDateTime>, JsonSerializer<OffsetDateTime> {
	
	private static final String DATETIME_WITH_TIMEZONE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

	@Override
	public JsonElement serialize(OffsetDateTime src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(getOffsetDateTimeToString(src, DATETIME_WITH_TIMEZONE_PATTERN));
	}

	@Override
	public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		var date = json.getAsString();
		return OffsetDateTime.parse(date);
	}
	
	private static String getOffsetDateTimeToString(OffsetDateTime offsetDateTime, String pattern) {
		var formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(offsetDateTime);
	}

}
