package com.learn.test.test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CampSeg {

	public static String POSTPAID = "POSTPAID";
	public static String PREPAID = "PREPAID";

	public static void main(String[] args) {

		String jsonStr = "{\r\n" + "		  \"id\": \"CAMP_BROADCAST\",\r\n"
				+ "		  \"docType\": \"SupportingData\",\r\n"
//				+ "\"CAMPAIGN2_0921_1\": \"POSTPAID;CAMPAIGN2;CAMPAIGN2;PO_5DBILLEND_2;20210917000000;20211001000000\","
//				+ "\"CAMPAIGN1_0921_1\": \"POSTPAID;CAMPAIGN1;CAMPAIGN1;PO_5DBILLEND_1;20210917000000;20211001000000\","
//				+ "\"BTLTP1_0621_1\": \"POSTPAID;GOPLUS2;GOPLUS2;;20210601000000;20210701000000\","
//				+ "\"PP2PO_1021_1\": \"PREPAID;PP2PODEVICE;PP2PO_DEVICE;;20211011000000;20211018000000\","
//				+ "\"PP2PO_1021_2\": \"PREPAID;PP2PONORMAL;PP2PO_FREEYT;;20211011000000;20211018000000\","
//				+ "\"BTLTP2_0721_1\": \"POSTPAID;BTLTP2;BTLTP2;;20210701000000;20210801000000\",\r\n"
//				+ "\"BTLTP6_0721_1\": \"POSTPAID;BTLTP6;BTLTP6;;20210701000000;20210801000000\",\r\n"
//				+ "\"FLASHDEAL_0921_1\": \"POSTPAID;HERO700SS;FLASHDEAL;DEVICEUPSELL;20210904000000;20210906000000\",\r\n"
//				+ "\"BTLCMPGN_1021_1\": \"PREPAID;BTLCMPGN;HotDeal_MN;HotDeal_MN;20211022000000;20211101000000\","
//				+ "\"BTLSPCL_0621_1\": \"PREPAID;BTLSPCL;Hotdeal_Taker;HotDeal_Taker_MN;20211203000000;20211231000000\","
//				+ "\"BTLCMPGN_1221_1\": \"PREPAID;FESTIVE;Festive;;20210618000000;20301231000000\","
				+ "\"NONDOB_1121_1\": \"POSTPAID;NONDOB;;NONDOB;20220107000000;20220116000000\","
				+ "\"BNPL_1121_1\": \"POSTPAID;BNPL;;BNPL;20220107000000;20220116000000\","
				+ "\"BTLSPCL_0122_1\": \"POSTPAID;GOPLUS;;;20220101000000;20220130000000\"\r\n"
//				+ "\"DEVICECARE_1121_1\": \"POSTPAID;DEVICECARE;;DEVICECARE;20211201000000;20220701000000\","
//				+ "\"FLASHDEAL_0921_2\": \"POSTPAID;PSON700SS;PSON700SS;DEVICEUPSELL;20210906000000;20211001000000\"\r\n"
				+ "}";

		JsonElement bc = new JsonParser().parse(jsonStr);

		JsonObject DTAC_CAMPAIGN_SUBSCRIPTION_LifeTime = new JsonObject();

		String ACCOUNT_TYPE_LifeTime = POSTPAID;
		JsonObject campBroad = bc.getAsJsonObject();
		JsonObject result = new JsonObject();
		JsonObject campSub = new JsonObject();

		String[] campBCArr = null;
		JsonElement bcEle = null;
		String bcId = "";
		String aliasName = "";
		long startTime = 0;
		long endTime = 0;
		long curTime = System.currentTimeMillis();
		campSub.addProperty("BNPL", "BNPLAND3");
//		campSub.addProperty("BTLFDVC", "BTLFDVCNOTP");
//		campSub.addProperty("BTLSPECIAL", "BTLRCEND");
		campSub.addProperty("NONDOB", "");
//		campSub.addProperty("NONDOB", "GAMENONDOB");

		System.out.println("CAMP_SUB : " + campSub);

		for (java.util.Map.Entry<String, JsonElement> bcEntry : campBroad.entrySet()) {
			bcId = bcEntry.getKey().toString();
			bcEle = (JsonElement) bcEntry.getValue();

			if (",id,docType,".contains(bcId))
				continue;

			campBCArr = bcEle.getAsString().split(";");

			if (campBCArr.length < 5)
				continue;

			try {
				if (!campBCArr[0].equalsIgnoreCase(POSTPAID))
					continue;

				aliasName = campBCArr[3];
//				startTime = campBCArr[4];
//				endTime = campBCArr[5];

//				if (curTime < startTime || curTime > endTime)
//					continue;

				String campSubSegment = "";
				String campSegment = "";
				String campPack = "";
				JsonElement campEle = null;

				for (String name : aliasName.split(",")) {
					if (!name.equals("") && !campSub.has(name))
						continue;

					campSegment = !name.equals("") ? campSub.get(name).getAsString() : "";

					if (name.equals("HotDeal_Taker_MN")) {
						campEle = DTAC_CAMPAIGN_SUBSCRIPTION_LifeTime.get(name);
						campSubSegment = campEle.getAsJsonArray().get(2).getAsString();
						String[] campSubArr = campSubSegment.split(";");
						campPack = campSubArr.length > 1 ? campSubArr[0] : "";

						result.addProperty(campBCArr[1], "HotDeal_" + campPack);
					} else if (campBCArr[2].equals(""))
						result.addProperty(campBCArr[1], campSegment);
					else
						result.addProperty(campBCArr[1], campBCArr[2]);
				}
			} catch (Exception e) {
			}
		}


		System.out.println("segment before: " + result);
		
		System.out.println("segment after: " + result);

	}
}
