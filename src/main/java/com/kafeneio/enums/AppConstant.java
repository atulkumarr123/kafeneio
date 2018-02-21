package com.kafeneio.enums;

public enum AppConstant {
	
	BASE_ORDER_NO(100),
//	STP_TEN(60),
	STP_TWENTY(20),
	STP_FIFTY(50),
	STP_HUNDREAD(100),
	STP_TEN_STRING("STP-10"),
	STP_TWENTY_STRING("STP-20"),
	STP_FIFTY_STRING("STP-50"),
	STP_HUNDREAD_STRING("STP-100"),
	INCOME_TYPE_DI("DI"),
	INCOME_TYPE_BINARY("BI"),
	INCOME_TYPE_BINARY_AMMOUNT(500),//Binary AMount;
	
	INCOME_TYPE_DI_AMMOUNT(2000),//Direct referral Amount
	INCOME_TYPE_WORK_AMMOUNT(10),//per day task ammount
	NODE_TYPE_LEFT("Left"),
	NODE_TYPE_RIGHT("Right"),
	ROLE_TYPE_ADMIN("ADMIN"),
	ROLE_TYPE_USER("USER"),
	ADMIN_TAX(10),
	TDS_TAX(2),
	INFO("info"),
	SUCCESS("success"),
	WARNING("warning"),
	ERROR("error");
	
	private  int intValue=0;
	private  String stringValue=null;

    
	AppConstant(final int intValue) {
        this.intValue = intValue;
    }
	AppConstant(final String stringValue) {
		this.stringValue = stringValue;
    }
    
	public int getValue() { return intValue; }
    public String getStringValue() { return stringValue; }
}
