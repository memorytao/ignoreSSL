package com.learn.test.test;

public class Test2 {

	public String BTLSx(String valid,double datToInV, double dataFee) {


		String result = "NA";
		String preferValidData = valid;//getStringFromObject(DTAC_PROFILE_OBJECT_LifeTime,"PREFER_VALID_DATA");
		double dayToInvDate = datToInV;  // REMAIN_DAY
		double dataPkgFree = dataFee; //getNumberFromObject(DTAC_PROFILE_OBJECT_LifeTime,"DATA_PKG_FEE");
		
		String ACCOUNT_TYPE_LifeTime = "POSTPAID";

		if (ACCOUNT_TYPE_LifeTime.equalsIgnoreCase("POSTPAID")) {
		    
		    if(preferValidData.equals("Daily")){
		    
		        if(dayToInvDate >= 1 && dayToInvDate <= 10 && dataPkgFree < 69){
		            result = "BTLTP1";
		        }else if(dayToInvDate >= 11 && dayToInvDate <= 30 && dataPkgFree < 69){
		            result = "BTLTP2";
		        }else{
		    
		            if(dataPkgFree >= 299){
		                result = "BTLTP8";
		            }else if(dataPkgFree >= 199 && dataPkgFree < 299){
		                result = "BTLTP7";
		            }else if(dataPkgFree >= 109 && dataPkgFree < 199){
		                result = "BTLTP5";
		            }else if(dataPkgFree >= 99 && dataPkgFree < 109){
		                result = "BTLTP4";
		            }else if(dataPkgFree >= 69 && dataPkgFree < 99){
		                result = "BTLTP3";
		            }else {
		            	  result = "BTLTP8";
		            }
		        }
		        
		    }else if(preferValidData.equals("Weekly")){
		    
		        if(dayToInvDate >= 1 && dayToInvDate <= 10 && dataPkgFree < 99){
		        	result = "BTLTP3";
		    
		        }else if(dayToInvDate >= 1 && dayToInvDate <= 10 && (dataPkgFree >= 99 && dataPkgFree < 109)) {
		        	result = "BTLTP4";
		        	
		        }else if(dayToInvDate >= 11 && dayToInvDate <= 30 && dataPkgFree < 109){
		            result = "BTLTP4";
		            
		        }else{
		    
		            if(dataPkgFree >= 299){
		                result = "BTLTP8";
		            }else if(dataPkgFree >= 199 && dataPkgFree < 299){
		                result = "BTLTP7";
		            }else if(dataPkgFree >= 109 && dataPkgFree < 199){
		                result = "BTLTP5";
		            }else {
		            	  result = "BTLTP8";
		            }
		        }
		        
		    }else if(preferValidData.equals("Biweekly")){
		    
		        if(dayToInvDate >= 1 && dayToInvDate <= 10 && dataPkgFree < 199){
		            result = "BTLTP5";
		            
		         }else if(dayToInvDate >= 11 && dayToInvDate <= 30 && dataPkgFree < 199){
		             result = "BTLTP6";
		    
		         }else{
		     
		             if(dataPkgFree >= 299){
		                 result = "BTLTP8";
		             }else if(dataPkgFree >= 199 && dataPkgFree < 299){
		                 result = "BTLTP7";
		             }else {
		            	  result = "BTLTP8";
		            }
		         }
		        
		    }else if(preferValidData.equals("Monthly")){
		        
		        if(dayToInvDate >= 1 && dayToInvDate <= 10 ){
		            result = (dataPkgFree >= 299)? "BTLTP8":"BTLTP7";
		    
		        }else if(dayToInvDate >= 11 && dayToInvDate <= 30 ){
		            result = "BTLTP8";
		        }else {
	            	  result = "BTLTP8";
	            }
		    }else{
		        result = "BTLTP8";
		    }
		}
//		System.out.println(result);
		return result;
	}
	
	public static void main(String[] args) {
		
//		String resp = new  Test2().BTLSx("",0,0);
//		System.out.println(resp);
		
		
		double dataQuotaVol = 100;//getNumberFromObject(DTAC_DATA_USAGE_OBJECT_LifeTime, "dataQuotaVol");
		double dataUsageVol = 80;//getNumberFromObject(DTAC_DATA_USAGE_OBJECT_LifeTime, "dataUsageVol");
		
		double fupUsage = dataUsageVol / (dataQuotaVol == 0?1:dataQuotaVol) * 100.0;
		
		System.out.println(fupUsage);
	}
}
