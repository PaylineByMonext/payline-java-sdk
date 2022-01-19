/*
 * Copyright (C) 2016 Monext
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/lgpl-3.0.fr.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.payline.ws.wrapper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.ws.WebServiceException;

import com.payline.ws.model.DoWebPaymentRequest;
import com.payline.ws.model.DoWebPaymentResponse;
import com.payline.ws.model.GetWebPaymentDetailsRequest;
import com.payline.ws.model.GetWebPaymentDetailsResponse;
import com.payline.ws.model.WebPaymentAPI;
import com.payline.ws.model.Buyer;
import com.payline.ws.model.ContractNumberWalletList;
import com.payline.ws.model.Order;
import com.payline.ws.model.Owner;
import com.payline.ws.model.Payment;
import com.payline.ws.model.PrivateDataList;
import com.payline.ws.model.Recurring;
import com.payline.ws.model.Result;
import com.payline.ws.model.SelectedContractList;
import com.payline.ws.model.SubMerchant;
import com.payline.ws.model.ThreeDSInfo;
import com.payline.kit.utils.ConnectParams;
import com.payline.kit.utils.PaylineProperties;
import com.payline.kit.utils.Utils;

/**
 * . WebPayment class
 * 
 * @author payline dev team
 */
public class WebPayment extends WebServiceWrapper {

	/**
	 * A Logger object is used to log messages
	 */
	private static final Logger logger = Logger.getLogger(WebPayment.class.getName());

	/**
	 * initFromFile
	 */
	private boolean initFromFile = true;

	/**
	 * connectParams Class constructor specifying the connection properties to
	 * create
	 */
	private ConnectParams connectParams;

	/**
	 * The default Constructor class
	 */
	public WebPayment() {
		super();
	}

	/**
	 * The Constructor class
	 * 
	 * @param connectParams
	 */
	public WebPayment(ConnectParams connectParams) {
		super();
		this.initFromFile = false;
		this.connectParams = connectParams;
	}

	/**
	 * Get web payment results
	 * 
	 * @param token    identifier of a web payment, create/update wallet session
	 * @param version, the API version of Payline
	 * @return GetWebPaymentDetailsResponse the response from Payline to a request
	 *         for the results of a web payment.
	 */
	public final GetWebPaymentDetailsResponse getWebPaymentDetails(final String token, final String version) {
		setException(null);
		WebPaymentAPI port = null;
		GetWebPaymentDetailsResponse result = new GetWebPaymentDetailsResponse();
		GetWebPaymentDetailsRequest parameters = new GetWebPaymentDetailsRequest();
		parameters.setToken(token);
		parameters.setVersion(version);
		try {
			if (this.initFromFile) {
				port = Utils.initServiceWeb();
			} else {
				port = Utils.initServiceWeb(this.connectParams);
			}
			result = port.getWebPaymentDetails(parameters);

		} catch (WebServiceException ex) {
			setException(ex);
			logger.log(Level.SEVERE, "Error during getWebPaymentDetails call : ", ex);
			Result err = new Result();
			err.setCode(Utils.EXCEPTION_CODE);
			err.setLongMessage(ex.getMessage());
			err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
			result.setResult(err);
		}
		return result;
	}

	/**
	 * Initialisation of a web payment The <b>doWebPayment</b> function initialises
	 * a web payment before redirecting your customer to Payline payment pages. This
	 * allows immediate or deferred cash payments, X-time payments, and subscription
	 * payments to be made
	 * Kept for compatibility
	 * 
	 * @param version                    the API version of Payline
	 * @param payment                    the payment object containing the amount,
	 *                                   the currency, action and mode codes
	 * @param order                      the order object containing the ref, the
	 *                                   amount, the currency, the date and cart
	 *                                   content in details child. order.amount is
	 *                                   required from 4.65.1
	 * @param buyer                      the buyer object, containing many
	 *                                   information about the buyer: firstname,
	 *                                   lastname, email, addresses,...
	 * @param privateDataList            A list of privateData, allowing to send any
	 *                                   kind of extra information organized with
	 *                                   keys and values
	 * @param recurring                  the recurring object, containing data about
	 *                                   how Payline will deal with the recurring
	 *                                   payment : first amount, other amount,
	 *                                   number of instalment, periodicity
	 * @param notificationURL            store URL which Payline will call if
	 *                                   customer do not come back to store after
	 *                                   his payment
	 * @param returnURL                  store URL to which customer is redirected
	 *                                   when "back to store" button is used on
	 *                                   Payline payment page after the payment
	 * @param cancelURL                  store URL to which customer is redirected
	 *                                   when "cancel" button is used on Payline
	 *                                   payment page before the payment
	 * @param languageCode               ISO 639-1 code of language in which payment
	 *                                   page is displayed
	 * @param securityMode               type of security when wallet is used on
	 *                                   payment page : 3DS, CVV or CVV+3DS
	 * @param customPaymentPageCode      code of payment page customization, created
	 *                                   in administration center
	 * @param selectedContractList       a list of selectedContract objects,
	 *                                   containing Payline identifier of your
	 *                                   e-commerce contract number
	 * @param secondSelectedContractList the list of contract numbers you wish to
	 *                                   present again after a first payment attempt
	 *                                   has failed.
	 * @param customPaymentTemplateURL   URL of the custom template
	 * @param merchantName               name displayed to buyer on 3D Secure
	 *                                   authentication form
	 * @param subMerchant                sub-merchant info in case you're using
	 *                                   Payline as a payment facilitator for other
	 *                                   merchants
	 * @param miscData                   Additional data , without character limit
	 *                                   and transmitted to the partners. Consult
	 *                                   the payment method for each use
	 * @return DoWebPaymentResponse the response given by Payline to a web payment
	 *         initialisation request
	 */
	public final DoWebPaymentResponse doWebPayment(final String version, final Payment payment, final Order order,
			final Buyer buyer, final PrivateDataList privateDataList, final Recurring recurring, String notificationURL,
			String returnURL, String cancelURL, String languageCode, String securityMode,
			final String customPaymentPageCode, SelectedContractList selectedContractList,
			SelectedContractList secondSelectedContractList, final String customPaymentTemplateURL,
			final String merchantName, final SubMerchant subMerchant, final String miscData) {
		return doWebPayment(version, payment, order,
				buyer, privateDataList, recurring, notificationURL,
				returnURL, cancelURL, languageCode, securityMode,
				customPaymentPageCode, selectedContractList,
				secondSelectedContractList, customPaymentTemplateURL,
				merchantName, subMerchant, miscData, null, null, null, null, null, null);
	}
			
	/**
	 * Initialisation of a web payment The <b>doWebPayment</b> function initialises
	 * a web payment before redirecting your customer to Payline payment pages. This
	 * allows immediate or deferred cash payments, X-time payments, and subscription
	 * payments to be made
	 * 
	 * @param version                    the API version of Payline
	 * @param payment                    the payment object containing the amount,
	 *                                   the currency, action and mode codes
	 * @param order                      the order object containing the ref, the
	 *                                   amount, the currency, the date and cart
	 *                                   content in details child. order.amount is
	 *                                   required from 4.65.1
	 * @param buyer                      the buyer object, containing many
	 *                                   information about the buyer: firstname,
	 *                                   lastname, email, addresses,...
	 * @param privateDataList            A list of privateData, allowing to send any
	 *                                   kind of extra information organized with
	 *                                   keys and values
	 * @param recurring                  the recurring object, containing data about
	 *                                   how Payline will deal with the recurring
	 *                                   payment : first amount, other amount,
	 *                                   number of instalment, periodicity
	 * @param notificationURL            store URL which Payline will call if
	 *                                   customer do not come back to store after
	 *                                   his payment
	 * @param returnURL                  store URL to which customer is redirected
	 *                                   when "back to store" button is used on
	 *                                   Payline payment page after the payment
	 * @param cancelURL                  store URL to which customer is redirected
	 *                                   when "cancel" button is used on Payline
	 *                                   payment page before the payment
	 * @param languageCode               ISO 639-1 code of language in which payment
	 *                                   page is displayed
	 * @param securityMode               type of security when wallet is used on
	 *                                   payment page : 3DS, CVV or CVV+3DS
	 * @param customPaymentPageCode      code of payment page customization, created
	 *                                   in administration center
	 * @param selectedContractList       a list of selectedContract objects,
	 *                                   containing Payline identifier of your
	 *                                   e-commerce contract number
	 * @param secondSelectedContractList the list of contract numbers you wish to
	 *                                   present again after a first payment attempt
	 *                                   has failed.
	 * @param customPaymentTemplateURL   URL of the custom template
	 * @param merchantName               name displayed to buyer on 3D Secure
	 *                                   authentication form
	 * @param subMerchant                sub-merchant info in case you're using
	 *                                   Payline as a payment facilitator for other
	 *                                   merchants
	 * @param miscData                   Additional data , without character limit
	 *                                   and transmitted to the partners. Consult
	 *                                   the payment method for each use
	 * @param skipSmartDisplay           Use to skip the smartdisplay rules : see
	 *                                   Rgles d'affichage de vos moyens de
	 *                                   paiements
	 * @param owner                      Cardholder Information
	 * @param contractNumberWalletList   A chart of the contract numbers of the
	 *                                   wallet. The Widget mode uses the multi-PDV
	 *                                   Wallet and multi-contract.
	 * @param asynchronousRetryTimeout   For the Asynchronous Retry function , this
	 *                                   is a numeric that specifies the period in
	 *                                   minutes. It must be between 5 and 10080 (7
	 *                                   days).
	 * @param threeDSInfo                Information specific to 3DS authentication
	 * @param merchantScore              Score calculated by the merchant (Used
	 *                                   exclusively for CB Scoring)
	 * @return DoWebPaymentResponse the response given by Payline to a web payment
	 *         initialisation request
	 */
	public final DoWebPaymentResponse doWebPayment(final String version, final Payment payment, final Order order,
			final Buyer buyer, final PrivateDataList privateDataList, final Recurring recurring, String notificationURL,
			String returnURL, String cancelURL, String languageCode, String securityMode,
			final String customPaymentPageCode, SelectedContractList selectedContractList,
			SelectedContractList secondSelectedContractList, final String customPaymentTemplateURL,
			final String merchantName, final SubMerchant subMerchant, final String miscData, Boolean skipSmartDisplay,
			Owner owner, ContractNumberWalletList contractNumberWalletList, String asynchronousRetryTimeout,
			ThreeDSInfo threeDSInfo, String merchantScore) {
		setException(null);
		DoWebPaymentResponse result = new DoWebPaymentResponse();
		DoWebPaymentRequest parameters = new DoWebPaymentRequest();

		if (this.initFromFile) {
			if (returnURL == null || returnURL.length() == 0) {
				returnURL = PaylineProperties.getString("RETURN_URL");
			}
			if (cancelURL == null || cancelURL.length() == 0) {
				cancelURL = PaylineProperties.getString("CANCEL_URL");
			}
			if (notificationURL == null || notificationURL.length() == 0) {
				notificationURL = PaylineProperties.getString("NOTIFICATION_URL");
			}
			if (payment.getContractNumber() == null || payment.getContractNumber().length() == 0) {
				payment.setContractNumber(PaylineProperties.getString("CONTRACT_NUMBER"));
			}
			if (payment.getAction() == null || payment.getAction().length() == 0) {
				payment.setAction(PaylineProperties.getString("PAYMENT_ACTION"));
			}
			if (payment.getMode() == null || payment.getMode().length() == 0) {
				payment.setMode(PaylineProperties.getString("PAYMENT_MODE"));
			}

			if (selectedContractList == null) {
				String list = PaylineProperties.getString("SELECTED_CONTRACT_LIST");
				selectedContractList = new SelectedContractList();
				String[] contracts = list.split(";");
				for (String con : contracts) {
					selectedContractList.getSelectedContract().add(con);
				}
			}

			if (languageCode == null || languageCode.length() == 0) {
				languageCode = PaylineProperties.getString("LANGUAGE_CODE");
			}

			if (securityMode == null || securityMode.length() == 0) {
				securityMode = PaylineProperties.getString("SECURITY_MODE");
			}
		}

		parameters.setVersion(version);
		parameters.setPayment(payment);
		parameters.setOrder(order);
		parameters.setBuyer(buyer);
		parameters.setSkipSmartDisplay(skipSmartDisplay);
		parameters.setOwner(owner);
		parameters.setContractNumberWalletList(contractNumberWalletList);
		if (asynchronousRetryTimeout != null)
			parameters.setAsynchronousRetryTimeout(asynchronousRetryTimeout);
		parameters.setThreeDSInfo(threeDSInfo);
		parameters.setMerchantScore(merchantScore);

		parameters.setPrivateDataList(privateDataList);
		if (recurring != null && recurring.getAmount().length() > 0) {
			parameters.setRecurring(recurring);
		}
		parameters.setNotificationURL(notificationURL);
		parameters.setReturnURL(returnURL);
		parameters.setCancelURL(cancelURL);
		parameters.setLanguageCode(languageCode);
		parameters.setSecurityMode(securityMode);
		parameters.setCustomPaymentPageCode(customPaymentPageCode);
		parameters.setCustomPaymentTemplateURL(customPaymentTemplateURL);
		parameters.setSelectedContractList(selectedContractList);
		parameters.setSecondSelectedContractList(secondSelectedContractList);
		parameters.setMerchantName(merchantName);
		parameters.setSubMerchant(subMerchant);
		parameters.setMiscData(miscData);

		WebPaymentAPI port = null;
		try {
			if (this.initFromFile) {
				port = Utils.initServiceWeb();
			} else {
				port = Utils.initServiceWeb(this.connectParams);
			}
			result = port.doWebPayment(parameters);
		} catch (WebServiceException ex) {
			setException(ex);
			logger.log(Level.SEVERE, "Error during doWebPayment call : ", ex);
			Result err = new Result();
			err.setCode(Utils.EXCEPTION_CODE);
			err.setLongMessage(ex.getMessage());
			err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
			result.setResult(err);
		}
		return result;
	}

}
