package com.finleap.notification.util;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import com.finleap.notification.exception.*;
import com.finleap.notification.entity.Schedule;

import scala.concurrent.duration.FiniteDuration;

/**
 * The type Schedule helper.
 */
public class ScheduleHelper {

	/**
	 * Next run sync long.
	 *
	 * @param schedule the schedule
	 * @param nowCal   the now cal
	 * @return the long
	 * @throws AppException the app exception
	 */
	public static long nextRunSync(Schedule schedule, Calendar nowCal) throws AppException {

		long durationInSec = 0;
		Calendar scheduleCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
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

	/**
	 * Show timein gmt string.
	 *
	 * @param cal the cal
	 * @return the string
	 */
	public static String ShowTimeinGMT(Calendar cal) {
		Calendar calendar = cal;
		StringBuilder _ret = new StringBuilder();

		if (cal == null) {
			calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		}

		_ret.append(calendar.get(Calendar.DATE) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.YEAR) + " T " + calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
		return _ret.toString();
	}

	/**
	 * Gets frequency.
	 *
	 * @param schedule the schedule
	 * @return the frequency
	 */
	public static FiniteDuration getFrequency(Schedule schedule) {
		FiniteDuration frequency = null;
		switch (schedule.getFrequency()) {
		case "daily":
			return FiniteDuration.create(24, TimeUnit.HOURS);
		case "minute":
			return FiniteDuration.create(schedule.getFrequencyValues(), TimeUnit.MINUTES);
		}
		return frequency;
	}

	/**
	 * The enum Week days.
	 */
	public enum weekDays {
		/**
		 * Sunday week days.
		 */
		SUNDAY(1),
		/**
		 * Monday week days.
		 */
		MONDAY(2),
		/**
		 * Tuesday week days.
		 */
		TUESDAY(3),
		/**
		 * Wednesday week days.
		 */
		WEDNESDAY(4),
		/**
		 * Thursday week days.
		 */
		THURSDAY(5),
		/**
		 * Friday week days.
		 */
		FRIDAY(6),
		/**
		 * Saturday week days.
		 */
		SATURDAY(7);
		private final int day;

		weekDays(int day) {
			this.day = day;
		}

		/**
		 * Gets day.
		 *
		 * @param d the d
		 * @return the day
		 * @throws Exception the exception
		 */
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
