package com.salesmanager.shop.transbank;

import java.io.IOException;

import cl.transbank.common.IntegrationType;
import cl.transbank.webpay.exception.TransactionCreateException;
import cl.transbank.webpay.webpayplus.WebpayPlus;
import cl.transbank.webpay.webpayplus.model.WebpayPlusTransactionCreateResponse;

public class WebPay {
	public WebPay() {
		WebpayPlus.Transaction.setCommerceCode("597055555532");
		WebpayPlus.Transaction.setApiKey("579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C");
		WebpayPlus.Transaction.setIntegrationType(IntegrationType.TEST);
	}
	
	public void generateTransaction(String buyOrder, String sessionId, double amount, String returnUrl){
		try {
			final WebpayPlusTransactionCreateResponse response = WebpayPlus.Transaction.create(
					  buyOrder, sessionId, amount, returnUrl
					);
		} catch (TransactionCreateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}