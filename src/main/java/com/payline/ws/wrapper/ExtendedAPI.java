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

import com.payline.kit.utils.ConnectParams;
import com.payline.kit.utils.Utils;
import com.payline.ws.model.GetAlertDetailsRequest;
import com.payline.ws.model.GetAlertDetailsResponse;
import com.payline.ws.model.GetTransactionDetailsRequest;
import com.payline.ws.model.GetTransactionDetailsResponse;
import com.payline.ws.model.Result;
import com.payline.ws.model.TransactionsSearchRequest;
import com.payline.ws.model.TransactionsSearchResponse;

/**
 * . WebPayment class
 * @author payline dev team
 */
public class ExtendedAPI extends WebServiceWrapper {

    /**
     * A Logger object is used to log messages
     */
    private static final Logger logger = Logger.getLogger(ExtendedAPI.class.getName());

    /**
     * initFromFile
     */
    private boolean initFromFile = true;

    /**
     * connectParams
     * @see ConnectParams
     */
    private ConnectParams connectParams;

    /**
     * Constructor class
     */
    public ExtendedAPI() {
        super();
    }

    /**
     * Constructor class
     * @param connectParams
     */
    public ExtendedAPI(ConnectParams connectParams) {
        super();
        this.initFromFile = false;
        this.connectParams = connectParams;
    }

    /**
     * This web service collects all information relative to a FAF alert, the alert that is sent following the detection of fraud when checking the rule in
     * question
     * @param alertId the alert identification
     * @param transactionId the unique Payline transaction ID
     * @return GetAlertDetailsResponse
     */
    public final GetAlertDetailsResponse getAlertDetails(final String alertId, final String transactionId, final String transactionDate, final String version) {
        GetAlertDetailsResponse result = new GetAlertDetailsResponse();
        GetAlertDetailsRequest parameters = new GetAlertDetailsRequest();
        parameters.setAlertId(alertId);
        parameters.setTransactionDate(transactionDate);
        parameters.setTransactionId(transactionId);
        parameters.setVersion(version);
        com.payline.ws.model.ExtendedAPI port = null;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceExtended();
            } else {
                port = Utils.initServiceExtended(this.connectParams);
            }
            result = port.getAlertDetails(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getAlertDetails call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(ex.getMessage());
            result.setResult(err);
        }
        return result;
    }

    /**
     * Obtain payment details of any nature. The <b>getTransactionDetails</b> function is used to obtain the details of a payment transaction whatever its
     * status.
     * @param orderRef the order reference
     * @param transactionID the unique Payline transaction ID
     * @param startDate the Start date of the transaction search period
     * @param endDate the End date of the transaction search period
     * @param transactionHistory the status history for a given transaction
     * @param version the API version of Payline : 13 corresponds to 4.45 release
     * @return GetTransactionDetailsResponse
     */
    public final GetTransactionDetailsResponse getTransactionDetails(final String orderRef, final String transactionID, final String startDate,
        final String endDate, final String transactionHistory, final String version) {
        setException(null);
        GetTransactionDetailsResponse result = new GetTransactionDetailsResponse();
        GetTransactionDetailsRequest request = new GetTransactionDetailsRequest();
        request.setOrderRef(orderRef);
        request.setTransactionId(transactionID);
        request.setVersion(version);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setTransactionHistory(transactionHistory);
        com.payline.ws.model.ExtendedAPI port = null;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceExtended();
            } else {
                port = Utils.initServiceExtended(this.connectParams);
            }
            result = port.getTransactionDetails(request);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getTransactionDetails call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(ex.getMessage());
            result.setResult(err);
        }
        return result;
    }

    /**
     * Find the transaction
     * @param orderRef the unique order reference
     * @param transactionID the transaction ID sent by Payline
     * @param startDate the Start date of the transaction search
     * @param endDate the End date of the transaction search
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param authorizationNumber the transaction authorization number
     * @param returnCode the return code sent to the user
     * @param cardType the card type
     * @param transactionType the type of transaction
     * @param buyerName the name of buyer
     * @param email the email
     * @param buyerFName the firstName of buyer
     * @param cardNumber the card number used for the transaction
     * @param currency the currency used for the transaction
     * @param minAmount the minimum transaction amount
     * @param maxAmount the maximum transaction amount
     * @param walletID the wallet identifier
     * @param seqNumber the transaction sequence number
     * @return TransactionsSearchResponse
     */
    public final TransactionsSearchResponse transactionsSearch(final String orderRef, final String transactionID, final String startDate, final String endDate,
        final String contractNumber, final String authorizationNumber, final String returnCode, final String cardType, final String transactionType,
        final String buyerName, final String email, final String buyerFName, final String cardNumber, final String currency, final String minAmount,
        final String maxAmount, final String walletID, final String seqNumber, final String version) {
        setException(null);
        TransactionsSearchResponse result = new TransactionsSearchResponse();
        TransactionsSearchRequest request = new TransactionsSearchRequest();
        request.setOrderRef(orderRef);
        request.setTransactionId(transactionID);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setContractNumber(contractNumber);
        request.setAuthorizationNumber(authorizationNumber);
        request.setReturnCode(returnCode);
        request.setPaymentMean(cardType);
        request.setTransactionType(transactionType);
        request.setName(buyerName);
        request.setFirstName(buyerFName);
        request.setEmail(email);
        request.setCardNumber(cardNumber);
        request.setCurrency(currency);
        request.setMinAmount(minAmount);
        request.setMaxAmount(maxAmount);
        request.setWalletId(walletID);
        request.setSequenceNumber(seqNumber);
        request.setVersion(version);
        com.payline.ws.model.ExtendedAPI port = null;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceExtended();
            } else {
                port = Utils.initServiceExtended(this.connectParams);
            }
            result = port.transactionsSearch(request);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during transactionsSearch call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(ex.getMessage());
            result.setResult(err);
        }
        return result;
    }
}
