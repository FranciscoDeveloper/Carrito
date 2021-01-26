package com.salesmanager.shop.transbank;

import java.io.IOException;

import org.codehaus.plexus.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.shop.store.controller.order.ShoppingOrderConfirmationController;

import cl.transbank.common.IntegrationType;
import cl.transbank.webpay.exception.TransactionCommitException;
import cl.transbank.webpay.exception.TransactionCreateException;
import cl.transbank.webpay.webpayplus.WebpayPlus;
import cl.transbank.webpay.webpayplus.model.WebpayPlusTransactionCommitResponse;
import cl.transbank.webpay.webpayplus.model.WebpayPlusTransactionCreateResponse;

public class WebPay {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebPay.class);

	public WebPay() {
		WebpayPlus.Transaction.setCommerceCode("597055555532");
		WebpayPlus.Transaction.setApiKey("579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C");
		WebpayPlus.Transaction.setIntegrationType(IntegrationType.TEST);
	}
	
	private static WebPay webpay;
	
	public static WebPay getInstance() {
		if(webpay == null) {
			webpay = new WebPay(); 
		}
		return webpay;
	}

	public TransbankDTO generateTransaction(String buyOrder, String sessionId, double amount, String returnUrl) {
		TransbankDTO transbank = new TransbankDTO();
		try {
			final WebpayPlusTransactionCreateResponse preResponse = WebpayPlus.Transaction.create(buyOrder, sessionId,
					amount, returnUrl);

			
			transbank.setToken(preResponse.getToken());
			transbank.setUrl(preResponse.getUrl());
			return transbank;
		} catch (TransactionCreateException | IOException  e) {
			e.getStackTrace();
			transbank.setCodeError(1);
		}
		return transbank;
	}
	
	public void  commitTransaction(String token) {
		try {
			
			final WebpayPlusTransactionCommitResponse response = WebpayPlus.Transaction.commit(token);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(response);
			LOGGER.info(jsonInString);
		} catch (TransactionCommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
