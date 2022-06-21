package com.learn.test.test;

import java.util.Calendar;

public class Calculator {
	public int multiply(int a, int b) {
		return a * b;
	}

	public static void main(String[] args) {

		System.out.println(Test());
	}

	private static boolean Test() {

		long curDay = Calendar.getInstance().getTimeInMillis();
		Calendar curDate = Calendar.getInstance();
		int showDays = 3;
		String aouSegment = "NA";
		
		curDate.setTimeInMillis(curDay);

		java.util.Calendar fSwonDate = java.util.Calendar.getInstance();
//		fSwonDate.setTimeInMillis(convertTimeToDate(getLongFromObject(DTAC_PROFILE_OBJECT_LifeTime, "FIRST_SWTCHON_DATE")));
		fSwonDate.setTimeInMillis(1607501700000L);
		int diffMonth = curDate.get(java.util.Calendar.MONTH) - fSwonDate.get(java.util.Calendar.MONTH);
		int diffYear = curDate.get(java.util.Calendar.YEAR) - fSwonDate.get(java.util.Calendar.YEAR);

		if (curDate.get(java.util.Calendar.DAY_OF_MONTH) < fSwonDate.get(java.util.Calendar.DAY_OF_MONTH))
			diffMonth--;
		if (fSwonDate.get(java.util.Calendar.DAY_OF_MONTH) > curDate.getActualMaximum(java.util.Calendar.DAY_OF_MONTH)
				&& curDate.get(java.util.Calendar.DAY_OF_MONTH) == curDate
						.getActualMaximum(java.util.Calendar.DAY_OF_MONTH))
			diffMonth++;

		diffMonth = (diffYear * 12) + diffMonth;
		diffYear = diffMonth / 12;

		java.util.Calendar curCycleDate = java.util.Calendar.getInstance();
		curCycleDate.setTimeInMillis(fSwonDate.getTimeInMillis());
		curCycleDate.add(java.util.Calendar.YEAR, diffYear);
		java.util.Calendar maxShowDate = java.util.Calendar.getInstance();
		maxShowDate.setTimeInMillis(curCycleDate.getTimeInMillis());
		maxShowDate.add(java.util.Calendar.DATE, showDays);
		curCycleDate.set(java.util.Calendar.DAY_OF_YEAR, 1);

//		long takeLong =   Function.getCampSubStartDate("CELEB_AOU_PO"); //getCampSubStartDate("CELEB_AOU_PO");
		long takeLong = 0L;
		java.util.Calendar takeDate = null;
		if (takeLong != 0) {
			takeDate = java.util.Calendar.getInstance();
			takeDate.setTimeInMillis(takeLong);
		}

		if (curDate.compareTo(curCycleDate) >= 0 && curDate.before(maxShowDate)
				&& (takeDate == null || (takeDate != null && takeDate.before(curCycleDate)))) {
			if (diffYear >= 1 && diffYear <= 5)
				aouSegment = "AOUDATAULM1D";
			else if (diffYear >= 6 && diffYear <= 10)
				aouSegment = "AOUDATAULM2D";
			else if (diffYear >= 11)
				aouSegment = "AOUDATAULM3D";
		}
		System.out.println("diffYear " + diffYear);
		return true;
	}
}
