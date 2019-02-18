package com.finleap.notification.util;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import com.finleap.notification.exception.*;
import com.finleap.notification.entity.Schedule;

import scala.concurrent.duration.FiniteDuration;

public class ScheduleHelper {

	public static long nextRunSync(Schedule schedule, Calendar nowCal) throws AppException {

		long durationInSec = 0;
		Calendar scheduleCal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
		String[] hourMin = schedule.getTime().split(":");
		int curMonth = nowCal.get(Calendar.MONTH);
		switch (schedule.getFrequency()) {
		case "daily":
			scheduleCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourMin[0]));
			scheduleCal.set(Calendar.MINUTE, Integer.parseInt(hourMin[1]));
			if (nowCal.getTimeInMillis() > scheduleCal.getTimeInMillis()) {
				scheduleCal.add(Calendar.HOUR, 24);
				durationInSec = (scheduleCal.getTimeInMillis() - nowCal.getTimeInMillis()) / 1000;
			} else if (nowCal.getTimeInMillis() < scheduleCal.getTimeInMillis()) {
				durationInSec = (scheduleCal.getTimeInMillis() - nowCal.getTimeInMillis()) / 1000;
			} else {
				durationInSec = 0;
			}
			break;
		}
		schedule.setNextRun(ShowTimeinGMT(scheduleCal));
		return durationInSec;

	}

	public static String ShowTimeinGMT(Calendar cal) {
		Calendar calendar = cal;
		StringBuilder _ret = new StringBuilder();

		if (cal == null) {
			calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
		}

		_ret.append(calendar.get(Calendar.DATE) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.YEAR) + " T " + calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
		return _ret.toString();
	}

	public static FiniteDuration getFrequency(Schedule schedule) {
		FiniteDuration frequency = null;
		switch (schedule.getFrequency()) {
		case "daily":
			return FiniteDuration.create(24, TimeUnit.HOURS);
		}
		return frequency;
	}

	public enum weekDays {
		SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(7);
		private final int day;

		weekDays(int day) {
			this.day = day;
		}

		public static weekDays getDay(String d) throws Exception {
			String day = d.toUpperCase();
			switch (day) {
			case "MONDAY":
				return weekDays.MONDAY;
			case "TUESDAY":
				return weekDays.TUESDAY;
			case "WEDNESDAY":
				return weekDays.WEDNESDAY;
			case "THURSDAY":
				return weekDays.THURSDAY;
			case "FRIDAY":
				return weekDays.FRIDAY;
			case "SATURDAY":
				return weekDays.SATURDAY;
			case "SUNDAY":
				return weekDays.SUNDAY;
			default:
				throw new Exception("invalid week day " + d);

			}

		}

	}

}
