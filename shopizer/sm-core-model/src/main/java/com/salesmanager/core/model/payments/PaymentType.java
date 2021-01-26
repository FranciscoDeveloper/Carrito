package com.salesmanager.core.model.payments;

public enum PaymentType {
	
	
	
	CREDITCARD("creditcard"), FREE("free"), COD("cod"), MONEYORDER("moneyorder"), PAYPAL("paypal");

	
	private String paymentType;
	
	PaymentType(String type) {
		this.paymentType = type;
	}
	
    public static PaymentType fromString(String text) {
		    if (text != null) {
		      for (PaymentType b : PaymentType.values()) {
		    	String payemntType = text.toUpperCase(); 
		        if (payemntType.equalsIgnoreCase(b.name())) {
		          return b;
		        }
		      }
		    }
		    return null;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
}
