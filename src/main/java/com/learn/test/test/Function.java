package com.learn.test.test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Function {

	public static String getStringFromObject(JsonObject obj, String attribute) {

		String result = "NA";

		try {
			if (obj != null && !obj.isJsonNull() && obj.entrySet().size() > 0 && obj.has(attribute)
					&& !obj.get(attribute).toString().equals("\"\"")) {
				if (obj.get(attribute).isJsonPrimitive()) {
					result = obj.get(attribute).getAsString();
				} else {
					result = obj.get(attribute).toString();
				}
			}
		} catch (Exception e) {
		}

		return result;
	}

	public static Double getNumberFromObject(JsonObject obj, String attribute) {
		double val = 0.0;

		if (obj == null || obj.isJsonNull() || obj.entrySet().size() == 0) {
			val = 0.0;
		} else {
			if (obj.has(attribute) && obj.get(attribute) != null && !obj.get(attribute).getAsString().equals("")) {
				val = obj.get(attribute).getAsDouble();
			} else {
				val = 0.0;
			}
		}
		return val;
	}

	public static Double getDiffDateLong(long dateFrom, long dateTo) {

		try {
			long diff = dateTo - dateFrom;
			return Double.parseDouble(String.valueOf(diff)) / 86400000.0;
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static Long getCampSubStartDate(String campName) {

//		mock data 
		String data = "{\"CELEB\": [\r\n" + "    \"09/12/2020 00:00:00\",\r\n" + "    \"01/08/2021 00:00:00\",\r\n"
				+ "    \"VACCINE\",\r\n" + "    \"\"\r\n" + "  ]}";
		JsonObject DTAC_CAMPAIGN_SUBSCRIPTION_LifeTime = (JsonObject) new JsonParser().parse(data);

		long startDate = 0L;

		if (campName.length() == 0)
			return startDate;

		if (DTAC_CAMPAIGN_SUBSCRIPTION_LifeTime.has(campName)) {
			JsonElement jEle = DTAC_CAMPAIGN_SUBSCRIPTION_LifeTime.get(campName);

			if (jEle.isJsonArray()) {
				startDate = jEle.getAsJsonArray().get(0).getAsLong();
			} else {
				startDate = jEle.getAsLong();
			}
		}
		startDate = 1607706000000L;
		return startDate;
	}

	public static double MathRound(double mathValue, int place) {
		java.math.BigDecimal result = new java.math.BigDecimal(mathValue);
		result = result.setScale(place, java.math.RoundingMode.HALF_UP);
		return result.doubleValue();
	}

	public static String getStringFromSubObject(JsonObject obj, String attr, String subAttr) {

		String result = "NA";
		JsonObject subObj = null;

		try {
			if (obj != null && !obj.isJsonNull() && obj.entrySet().size() > 0 && obj.has(attr)
					&& obj.get(attr).isJsonObject()) {
				subObj = obj.get(attr).getAsJsonObject();
				if (subObj.has(subAttr) && subObj.get(subAttr).isJsonPrimitive()) {
					result = subObj.get(subAttr).getAsString();
				} else {
					result = subObj.get(subAttr).toString();
				}
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static boolean compareList(String leftList, String rightList) {

		if (leftList != null && !leftList.equals("") && rightList != null && !rightList.equals("")) {
			String leftDeli = leftList.indexOf("|") != -1 ? "|" : ",";
			String rightDeli = rightList.indexOf("|") != -1 ? "|" : ",";
			boolean found = false;

			String[] splitList = rightList.split(rightDeli.equals("|") ? "\\|" : rightDeli);

			for (String item : splitList) {
				if ((leftDeli + leftList + leftDeli).contains(leftDeli + item + leftDeli)) {
					found = true;
					break;
				}
			}
			if (!found)
				return false;
		}
		return true;
	}

	public static JsonObject getObjectFromObject(JsonObject obj, String attr) {

		JsonObject result = new JsonObject();
		try {
			if (obj != null && !obj.isJsonNull() && obj.entrySet().size() > 0 && obj.has(attr)
					&& obj.get(attr).isJsonObject()) {
				result = obj.get(attr).getAsJsonObject();
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static double sizeToByte(double value, String unit) {

		double result = 0.0;

		try {
			if (unit.equals("T")) {
				result = value * 1024 * 1024 * 1024 * 1024;
			} else if (unit.equals("G")) {
				result = value * 1024 * 1024 * 1024;
			} else if (unit.equals("M")) {
				result = value * 1024 * 1024;
			} else if (unit.equals("K")) {
				result = value * 1024;
			} else {
				result = value;
			}
		} catch (Exception e) {

		}

		return result;
	}

	public static long addMonth(long dayLong, int months) {
		java.util.Calendar calen = java.util.Calendar.getInstance();
		calen.setTimeInMillis(dayLong);
		calen.add(java.util.Calendar.MONTH, months);

		return calen.getTimeInMillis();
	}

	public static int getDatePart(long dayLong, int part) {
		java.util.Calendar calen = java.util.Calendar.getInstance();
		calen.setTimeInMillis(dayLong);
		calen.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));

		return calen.get(part);
	}

	public static long setDatePart(long dayLong, int part, int val) {

		java.util.Calendar calen = java.util.Calendar.getInstance();
		calen.setTimeInMillis(dayLong);
		calen.set(part, val);

		return calen.getTimeInMillis();
	}

	public static String convertLongToDate(long epoch, String format) {

		String result = "NA";
		epoch -= java.util.TimeZone.getDefault().getOffset(epoch);

		try {
			if (format != null && !format.equals("")) {
				java.util.Date date = new java.util.Date(epoch);
				java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);

				result = df.format(date);
			}
		} catch (Exception e) {
			System.out.println("convertLongToDate:" + e.getMessage());
		}

		return result;
	}
	
}
