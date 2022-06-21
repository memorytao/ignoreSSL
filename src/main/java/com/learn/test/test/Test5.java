package com.learn.test.test;

public class Test5 {

	public static void main(String[] args) {

		System.out.println(Test());
	}

	public static double Test() {
		
		String subGroupName = "AD_1M_0_PR_N_1_S_LIN_0";
        if (subGroupName.equals("-9999999"))
            return 0;
        
			String[] subGrpArray = subGroupName.split("_");
			String quotaUnit = "";
			String usageUnit = ""; //bidirVolumeUnit;
			String sQuotaValue = "";
			double quotaValue = 0;
			double usageValue = -9999999; //bidirVolume;
			String returnValue = "-99";
			String maxSpeed = "42M"; //getSupportingData("SIFT_CONSTANT", "MAX_SPEED");
			
			try {
				if (subGrpArray.length > 1) {
					String prefix = subGrpArray[0];
					String packSpeed = (subGrpArray[1].startsWith("MAX") || subGrpArray[1].startsWith("NA"))?maxSpeed:subGrpArray[1];
					
					// New format from PCRF
					if (",AD,MA,".contains(","+prefix+",")) {
					    //if (QUOTA_PACKAGE == null || QUOTA_PACKAGE.equals("") || QUOTA_PACKAGE.equals("-9999999"))
					    //   return 0;
					    
						try {
							Integer.parseInt(packSpeed.substring(packSpeed.length()-1));
							packSpeed = packSpeed.substring(0, packSpeed.length()-1);
						} catch (Exception e) {}
						
						returnValue = packSpeed;
					} 
					// Old format
					else if (subGrpArray[2].length() > 1 && usageValue != -9999999.0) {
						quotaUnit = subGrpArray[2].substring(subGrpArray[2].length() - 1, subGrpArray[2].length());
						sQuotaValue = subGrpArray[2].substring(0, subGrpArray[2].length() - 1);
						quotaValue = sizeToByte(Double.parseDouble(sQuotaValue), "M");
						
						if (quotaUnit.equalsIgnoreCase("G")) {
							if (sQuotaValue.equals("0"))
								returnValue = packSpeed;
							else if (sQuotaValue.equals("00"))
								quotaValue = sizeToByte(1000, "M");
							quotaValue = sizeToByte(quotaValue, "K");
						}
						
						usageValue = sizeToByte(usageValue, usageUnit);
						
						if (quotaValue <= usageValue && subGrpArray[3].length() > 1) {
							if (subGrpArray[3].equals("NA")) {
								returnValue = maxSpeed;
							} else {
								String fourthToken = "";
								
								for (int i = 0; i < subGrpArray[3].length(); i++) {
									fourthToken += subGrpArray[3].charAt(i);
									if (!Character.isDigit(subGrpArray[3].charAt(i)))
										break;
								}
							
								returnValue = fourthToken;
							}
						} else {
							returnValue = packSpeed;
						}
					}
				} else if (subGroupName.equals("NA")) {
					return 0;
				} else {
					returnValue = maxSpeed;
				}
			} catch (Exception e) {
				returnValue = maxSpeed;
			}
			
			try {
				Double.parseDouble(returnValue.substring(0, returnValue.length() - 1));
			} catch (Exception e) {
				returnValue = maxSpeed;
			}
			
			// converting return value to kb
			if (!returnValue.equals("-99") && returnValue.length() > 1) {
				String returnQuotaUnit = returnValue.substring(returnValue.length() - 1, returnValue.length());
				double returnQuota = Double.parseDouble(returnValue.substring(0, returnValue.length() - 1));
				
				return sizeToByte(returnQuota, returnQuotaUnit) / 1024.0;
			} else {
				return -99;
			}
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

}
