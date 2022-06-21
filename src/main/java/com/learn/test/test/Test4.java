package com.learn.test.test;

public class Test4 {

	public static void main(String[] args) {

		
		System.out.println(new Test4().test());
		
	}
	
	public boolean test() {
		
		String leftList ="21500851|21500852|21501016|21501051|21501067|21501068|21501069|21501070|21501071|21501072|21501081";
		String rightList = "21500850,21500851,21500852,21501016,21501051,21501067,21501068,21501069,21501070,21501071,21501072,21501081";
		
		boolean resp = false;
		
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
}
