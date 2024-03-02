package br.com.eventmanager.scheduler.utils;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public final class CronUtils {

	private CronUtils() {
		super();
	}

	public static String convertOffsetDateTimeToCronExpression(OffsetDateTime dateTime) {

		var zoned = dateTime.atZoneSameInstant(ZoneId.of("America/Sao_Paulo"));

		var localDateTime = zoned.toLocalDateTime();

		int second = localDateTime.getSecond();
		int minute = localDateTime.getMinute();
		int hour = localDateTime.getHour();
		int dayOfMonth = localDateTime.getDayOfMonth();
		int dayOfWeek = localDateTime.getDayOfWeek().getValue();
		int month = localDateTime.getMonthValue();

		return String.format("%d %d %d %d %d %d", second, minute, hour, dayOfMonth, month, dayOfWeek);

	}
}
