package com.learn.test.test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CampSub {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonObject DTAC_CAMPAIGN_SUBSCRIPTION_LifeTime = new JsonObject();
		JsonObject result = new JsonObject();
		JsonObject campSegmentObj = new JsonObject();

		JsonElement jEle = null;
		long startTime = 0L;
		long endTime = 0L;
		long takeTime = 0L;
		String campName = "";
		String campSegment = "";
		String campPack = "";
		String[] campSegArr = null;
		String latestCampaign = "";
		String latestPacklist = "";
		long lastTimeStamp = 0L;
		long curTime = System.currentTimeMillis();
//		double camSubLT = getNumericSupportingData("SIFT_CONSTANT", "CAMP_SUB_LIFETIME");
		boolean takerFlag = false;

		String campaignStr = "{\"BTLBROADCAST\": [\r\n"
				+ "    \"04/11/2020 10:35:51\",\r\n"
				+ "    \"04/11/2022 10:35:51\",\r\n"
				+ "    \"21300022_CMT\",\r\n"
				+ "    \"\"\r\n"
				+ "  ],\r\n"
				+ "  \"DVCDISC\": [\r\n"
				+ "    \"30/06/2021 00:00:00\",\r\n"
				+ "    \"01/08/2021 00:00:00\",\r\n"
				+ "    \"DVCDISC1000\",\r\n"
				+ "    \"\"\r\n"
				+ "  ],\r\n"
				+ "  \"BNPL\": [\r\n"
				+ "    \"30/06/2021 00:00:00\",\r\n"
				+ "    \"01/08/2021 00:00:00\",\r\n"
				+ "    \"BNPLAND\",\r\n"
				+ "    \"\"\r\n"
				+ "  ],\r\n"
				+ "  \"BTLSPECIAL\": [\r\n"
				+ "    \"05/07/2021 00:00:00\",\r\n"
				+ "    \"01/08/2021 00:00:00\",\r\n"
				+ "    \"21501017_BTLRCEND\",\r\n"
				+ "    \"\"\r\n"
				+ "  ]}";
		JsonParser jsonParser = new JsonParser();
		DTAC_CAMPAIGN_SUBSCRIPTION_LifeTime = jsonParser.parse(campaignStr).getAsJsonObject();

		for (java.util.Map.Entry<String, JsonElement> entry : DTAC_CAMPAIGN_SUBSCRIPTION_LifeTime.entrySet()) {
			campName = (String) entry.getKey();
			jEle = (JsonElement) entry.getValue();

			try {
				if (jEle.isJsonArray()) {
//		            startTime = jEle.getAsJsonArray().get(0).getAsLong();
//		            endTime = jEle.getAsJsonArray().get(1).getAsLong();
					campSegment = jEle.getAsJsonArray().get(2).getAsString();
					campPack = "";
					takeTime = jEle.getAsJsonArray().get(3).getAsString().length() == 0 ? 0L
							: jEle.getAsJsonArray().get(3).getAsLong();

					if (campSegment.contains(";")) {
						campSegArr = campSegment.split(";");
						campPack = campSegArr[0];
						campSegment = campSegArr[1];
					}
				} else {
					startTime = jEle.getAsLong();
					endTime = curTime;// addDay(startTime, (int) camSubLT);
					campSegment = "";
					campPack = "";
					takeTime = 0L;
				}

				// CAMPAIGN_SEGMENT
//		        if (curTime >= startTime && curTime <= endTime) {
//		            // Check taker
//		            if (campName.equalsIgnoreCase("HOTDEAL_MN")) { // Non check taker
//		                takerFlag = false;
//		            } else if (campName.contains("LINK_45_90")) { // Exclude list
//		                takerFlag = true;
//		            } else if (takeTime == 0L) {
//		                takerFlag = false;
//		            } else {
//		                takerFlag = true;
//		            }
//		            
//		            if (!takerFlag) {
//		                campSegmentObj.addProperty(campName, campSegment);
//		            }
//		            
//		        }
				campSegmentObj.addProperty(campName, campSegment);
				// latestCampaign, latestPacklist
				if (campName.contains("_MN_") && lastTimeStamp <= startTime) {
					lastTimeStamp = startTime;
					latestCampaign = campName;
				}
			} catch (Exception e) {
			}
		}

//		System.out.println(campSegmentObj);
//		boolean a = (campSegmentObj.has("DEVICEUPSELL") && campSegmentObj.get("DEVICEUPSELL").getAsString().equals("FLASH700SS"));
//		if(!a) {
//			campSegmentObj.remove("DVCDISC");
//		}

		if(campSegmentObj.has("BTLSPECIAL")) {
			
			String get = campSegmentObj.get("BTLSPECIAL").getAsString().length() > 0 ? campSegmentObj.get("BTLSPECIAL").getAsString():"";
			campSegmentObj.addProperty("BTLSPECIAL", get.split("_")[1]);
		}
			
		result.add("CAMPAIGN_SEGMENT", campSegmentObj);
		System.out.println(result);
	}

}
