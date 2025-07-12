package com.example.expensetracker.Enum;

public enum MonthEnum {

    JAN(1, "JAN", "January"),
    FEB(2, "FEB", "February"),
    MAR(3, "MAR", "March"),
    APR(4, "APR", "April"),
    MAY(5, "MAY", "May"),
    JUN(6, "JUN", "June"),
    JUL(7, "JUL", "July"),
    AUG(8, "AUG", "August"),
    SEP(9, "SEP", "September"),
    OCT(10, "OCT", "October"),
    NOV(11, "NOV", "November"),
    DEC(12, "DEC", "December");
	
	private final int number;
	
	private final String shortName;
	
	private final String longName;
	
	MonthEnum(int number, String shortName, String longName){
		this.number = number;
		this.shortName = shortName;
		this.longName = longName;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public String getLongName() {
		return longName;
	}
	
	
	public static MonthEnum fromNumber(int number) {
		for(MonthEnum month : MonthEnum.values()) {
			if(month.getNumber() == number) {
				return month;
			}
		}
		
		throw new IllegalArgumentException("invalid month number : "+ number);
	}
	
	public static MonthEnum fromShortName(String shortName) {
		
		for(MonthEnum month : MonthEnum.values()) {
			if(month.shortName.equalsIgnoreCase(shortName)) {
				return month;
			}
		}
		throw new IllegalArgumentException("invalide month name : "+ shortName);
	}
}
