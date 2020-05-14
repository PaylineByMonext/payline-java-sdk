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

import com.payline.kit.utils.ConnectParams;
import com.payline.kit.utils.Utils;
import com.payline.ws.model.*;

import javax.xml.ws.WebServiceException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DirectPayment class.
 * @author payline dev team
 */
public class DirectPayment extends WebServiceWrapper {

    /**
     * A Logger object is used to log messages
     */
    private static final Logger logger = Logger.getLogger(DirectPayment.class.getName());
    
    /**
     * initFromFile, initiating Direct Services from payline properties file
     */
    private boolean initFromFile = true;

    /**
     * connectParams
     * @see ConnectParams
     */
    private ConnectParams connectParams;

    /**
     * The Constructor class
     */
    public DirectPayment() {
        super();
    }

    /**
     * The Constructor class
     * @param connectParams @see ConnectParams
     */
    public DirectPayment(ConnectParams connectParams) {
        super();
        this.initFromFile = false;
        this.connectParams = connectParams;
    }

    /**
     * Carry out payment authorization requests. The <b>doAuthorization</b> function sends a debit authorization request to your bank authorization server.
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param card the card object, containing the card data : number, expirationDate, cvx,...
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param authentication3DSecure the authentication3DSecure object, filled with MD and PARES retrieved from the ACS after the customer entered his password
     * @param bank the bankAccountData object, used for ELV payment only
     * @param version the API version of Payline
     * @param subMerchant sub-merchant info in case you're using Payline as a payment facilitator for other merchants
     * @return DoAuthorizationResponse the response given by Payline to a debit authorization request
     */
    public final DoAuthorizationResponse doAuthorization(final Payment payment, final Order order, final Buyer buyer, final Card card,
        final PrivateDataList privateDataList, final Authentication3DSecure authentication3DSecure, final BankAccountData bank, final String version, final SubMerchant subMerchant) {
        setException(null);
        DoAuthorizationResponse result = new DoAuthorizationResponse();
        DoAuthorizationRequest parameters = new DoAuthorizationRequest();
        parameters.setPayment(payment);
        parameters.setOrder(order);
        parameters.setBuyer(buyer);
        parameters.setPrivateDataList(privateDataList);
        parameters.setCard(card);
        parameters.setAuthentication3DSecure(authentication3DSecure);
        parameters.setVersion(version);
        parameters.setSubMerchant(subMerchant);
        parameters.setBankAccountData(bank);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doAuthorization(parameters);

        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doAuthorization call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * The <b>doDebit</b> function is used following a phone call and is a forced debit. The merchant has contacted his bank and the bank provides an
     * authorization number; this allows him to request a debit on his customer's bank card.
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param card the card object, containing the card data : number, expirationDate, cvx,...
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param authentication3DSecure the authentication3DSecure object, filled with MD and PARES retrieved from the ACS after the customer entered his password
     * @param authorization the authorization
     * @param version the API version of Payline
     * @param subMerchant sub-merchant info in case you're using Payline as a payment facilitator for other merchants
     * @return DoDebitResponse the response given by Payline to a debit request
     */
    public final DoDebitResponse doDebit(final Payment payment, final Order order, final Buyer buyer, final Card card, final PrivateDataList privateDataList,
        final Authentication3DSecure authentication3DSecure, final Authorization authorization, final String version, final SubMerchant subMerchant) {
        setException(null);
        DoDebitResponse result = new DoDebitResponse();
        DoDebitRequest parameters = new DoDebitRequest();
        parameters.setPayment(payment);
        parameters.setAuthorization(authorization);
        parameters.setOrder(order);
        parameters.setBuyer(buyer);
        parameters.setPrivateDataList(privateDataList);
        parameters.setCard(card);
        parameters.setVersion(version);
        parameters.setAuthentication3DSecure(authentication3DSecure);
        parameters.setSubMerchant(subMerchant);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {

                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doDebit(parameters);

        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doDebit call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param creditor the creditor
     * @param transationID the unique Payline transaction ID
     * @param orderID the unique Payline order id
     * @param comment the comment
     * @param version the API version of Payline
     * @return DoBankTransferResponse
     */
    public final DoBankTransferResponse doBankTransfer(final Payment payment, final Creditor creditor, final String transationID, final String orderID,
        final String comment, final String version) {
        setException(null);
        DoBankTransferResponse result = new DoBankTransferResponse();
        DoBankTransferRequest parameters = new DoBankTransferRequest();
        parameters.setPayment(payment);
        parameters.setCreditor(creditor);
        parameters.setTransactionID(transationID);
        parameters.setOrderID(orderID);
        parameters.setComment(comment);
        parameters.setVersion(version);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doBankTransfer(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doBankTransfer call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Validate an accepted payment request. The <b>doCapture</b> function requests validation of an accepted authorization. Once the validation is taken into
     * account, a file with all the validations is sent to the merchant's bank; Payline sends payment requests to your bank.
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param transationID the unique Payline transaction ID
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param sequenceNumber the transaction sequence number
     * @return DoCaptureResponse the response from Payline to a validation request for a debit authorization
     */
    public final DoCaptureResponse doCapture(final Payment payment, final String transationID, final PrivateDataList privateDataList,
        final String sequenceNumber, final String version) {
        setException(null);
        DoCaptureResponse result = new DoCaptureResponse();
        DoCaptureRequest parameters = new DoCaptureRequest();
        parameters.setPayment(payment);
        parameters.setTransactionID(transationID);
        parameters.setSequenceNumber(sequenceNumber);
        parameters.setPrivateDataList(privateDataList);
        parameters.setVersion(version);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doCapture(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doCapture call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Refund a payment using an accepted authorization number. A refund request for a validated payment paid into the bank; therefore the customer has been
     * debited and the merchant credited
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param transationID the unique Payline transaction ID
     * @param comment the comment
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param sequenceNumber the transaction sequence number
     * @param order the order info. Only details (cart content) is used for doRefund
     * @return DoRefundResponse the response given by Payline to a refund request
     */
    public final DoRefundResponse doRefund(final Payment payment, final String transationID, final String comment, final PrivateDataList privateDataList,
        final String sequenceNumber, final String version, final Order order) {
        setException(null);
        DoRefundResponse result = new DoRefundResponse();
        DoRefundRequest parameters = new DoRefundRequest();
        parameters.setPayment(payment);
        parameters.setTransactionID(transationID);
        parameters.setComment(comment);
        parameters.setSequenceNumber(sequenceNumber);
        parameters.setPrivateDataList(privateDataList);
        parameters.setVersion(version);
        parameters.setDetails(order.getDetails());
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doRefund(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doRefund call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Credit a payment card using a the merchant account. The <b>doCredit</b> function is used to request that the bank card used by your customer for the
     * payment be credited. This function is useful for refunding your customer if you have not kept the transaction authorization ID needed for the doRefund
     * function.
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param card the card object, containing the card data : number, expirationDate, cvx,...
     * @param comment the comment
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param version the API version of Payline
     * @param subMerchant sub-merchant info in case you're using Payline as a payment facilitator for other merchants
     * @return DoCreditResponse
     */
    public final DoCreditResponse doCredit(final Payment payment, final Card card, final String comment, final Buyer buyer, final Order order,
        final PrivateDataList privateDataList, final String version, final SubMerchant subMerchant) {
        setException(null);
        DoCreditResponse result = new DoCreditResponse();
        DoCreditRequest parameters = new DoCreditRequest();
        parameters.setPayment(payment);
        parameters.setCard(card);
        parameters.setComment(comment);
        parameters.setOrder(order);
        parameters.setBuyer(buyer);
        parameters.setPrivateDataList(privateDataList);
        parameters.setVersion(version);
        parameters.setSubMerchant(subMerchant);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doCredit(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doCredit call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * created for compatibility purpose after introduction of walletId and walletCardInd in v4.41
     * @param card the card object, containing the card data : number, expirationDate, cvx,...
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param orderRef the order reference
     * @param UsrAgent the user Agent
     * @param version the API version of Payline
     * @return VerifyEnrollmentResponse
     */
    public final VerifyEnrollmentResponse verifyEnrollment(final Card card, final Payment payment, final String orderRef, final String UsrAgent,
        final String version) {
        return this.verifyEnrollment(card, payment, orderRef, null, null, UsrAgent, version, null);
    }

    /**
     * Verify that the buyers card is 3DSecure.
     * @param card the card object, containing the card data : number, expirationDate, cvx,...
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param orderRef the order reference
     * @param walletId the wallet identifier
     * @param walletCardInd the card index to use
     * @param UsrAgent the user Agent
     * @param version the API version of Payline
     * @param merchantName name displayed to buyer on 3D Secure authentication form
     * @return VerifyEnrollmentResponse
     */
    public final VerifyEnrollmentResponse verifyEnrollment(final Card card, final Payment payment, final String orderRef, final String walletId,
        final String walletCardInd, final String UsrAgent, final String version, final String merchantName) {
        setException(null);
        VerifyEnrollmentResponse result = new VerifyEnrollmentResponse();
        VerifyEnrollmentRequest parameters = new VerifyEnrollmentRequest();
        parameters.setPayment(payment);
        parameters.setCard(card);
        parameters.setWalletId(walletId);
        parameters.setWalletCardInd(walletCardInd);
        parameters.setOrderRef(orderRef);
        parameters.setUserAgent(UsrAgent);
        parameters.setVersion(version);
        parameters.setMerchantName(merchantName);
        
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.verifyEnrollment(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during verifyEnrollment call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Cancel a transaction using a transaction that has been authorized and validated but not transferred to the bank. The <b>doReset</b> function lets you
     * cancel sending your bank a debit or credit transaction made using the following functions: doauthorization, doDebit, doCredit, doRefund
     * @param transationID the unique Payline transaction ID
     * @param comment the comment
     * @param version WSDL version
     * @param amount Amount to be reset
     * @param currency Currency of the transaction
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @return DoResetResponse the response given by Payline to a reset request
     */
    public final DoResetResponse doReset(final String transationID, final String comment, final String version, final String amount, final String currency, final PrivateDataList privateDataList) {
        setException(null);
        DoResetResponse result = new DoResetResponse();
        DoResetRequest parameters = new DoResetRequest();
        parameters.setTransactionID(transationID);
        parameters.setComment(comment);
        parameters.setVersion(version);
        parameters.setAmount(amount);
        parameters.setCurrency(currency);
        parameters.setPrivateDataList(privateDataList);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doReset(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doReset call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * @param cardID the unique Payline card ID
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @return GetBalanceResponse
     */
    public final GetBalanceResponse getBalance(final String cardID, final String contractNumber, final String version) {
        setException(null);
        GetBalanceResponse result = new GetBalanceResponse();
        GetBalanceRequest parameters = new GetBalanceRequest();
        parameters.setCardID(cardID);
        parameters.setContractNumber(contractNumber);
        parameters.setVersion(version);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.getBalance(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getBalance call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Supply the public card data encryption key
     * @return GetEncryptionKeyResponse
     */
    public final GetEncryptionKeyResponse getEncryptionKey() {
        setException(null);
        GetEncryptionKeyResponse result = new GetEncryptionKeyResponse();
        GetEncryptionKeyRequest parameters = new GetEncryptionKeyRequest();
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.getEncryptionKey(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getEncryptionKey call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;

    }

    /**
     * Get the information on the request processing
     * @return GetMerchantSettingsResponse
     */
    public final GetMerchantSettingsResponse getMerchantSettings(final String version) {
        setException(null);
        GetMerchantSettingsResponse result = new GetMerchantSettingsResponse();
        GetMerchantSettingsRequest parameters = new GetMerchantSettingsRequest();
        parameters.setVersion(version);

        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.getMerchantSettings(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getMerchantSettings call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * This web service allows the merchant to retrieve a token as well as a number of other pieces of information from a card and an expiry date.
     * @param cardNumber the card number
     * @param expirationDate the card expire date
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @return GetTokenResponse
     */
    public final GetTokenResponse getToken(final String cardNumber, final String expirationDate, final String contractNumber) {
        GetTokenResponse result = new GetTokenResponse();
        GetTokenRequest parameters = new GetTokenRequest();
        parameters.setCardNumber(cardNumber);
        parameters.setExpirationDate(expirationDate);
        parameters.setContractNumber(contractNumber);

        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.getToken(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getToken call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * @param transactionID the unique Payline transaction ID
     * @param transactionDate the transaction date
     * @param version the API version of Payline
     * @return UnBlockResponse
     */
    public final UnBlockResponse unBlock(final String transactionID, final String transactionDate, final String version) {
        UnBlockResponse result = new UnBlockResponse();
        UnBlockRequest parameters = new UnBlockRequest();
        parameters.setTransactionDate(transactionDate);
        parameters.setVersion(version);
        parameters.setTransactionID(transactionID);

        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.unBlock(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during unBlock call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * @param cheque the cheque
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param privatedatalist A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @return DoScoringChequeResponse
     */
    public final DoScoringChequeResponse doScoringCheque(final Cheque cheque, final Order order, final Payment payment, final PrivateDataList privatedatalist,
        final String version) {
        setException(null);
        DoScoringChequeResponse result = new DoScoringChequeResponse();
        DoScoringChequeRequest parameters = new DoScoringChequeRequest();
        parameters.setCheque(cheque);
        parameters.setOrder(order);
        parameters.setPayment(payment);
        parameters.setPrivateDataList(privatedatalist);
        parameters.setVersion(version);

        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doScoringCheque(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doScoringCheque call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Allow transaction replay. This allows a transaction which has been accepted to be replayed.
     * @param transationID the unique Payline transaction ID
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param privatedatalist A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @return DoReAuthorizationResponse
     */
    public final DoReAuthorizationResponse doReAuthorization(final String transationID, final Order order, final Payment payment,
        final PrivateDataList privatedatalist, final String version) {
        setException(null);
        DoReAuthorizationResponse result = new DoReAuthorizationResponse();
        DoReAuthorizationRequest parameters = new DoReAuthorizationRequest();
        parameters.setTransactionID(transationID);
        parameters.setOrder(order);
        parameters.setPayment(payment);
        parameters.setPrivateDataList(privatedatalist);
        parameters.setVersion(version);

        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doReAuthorization(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doReAuthorization call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Verify the authentication.
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param pares
     * @param md the md fields value
     * @param card the card object, containing the card data : number, expirationDate, cvx,...
     * @return VerifyAuthenticationResponse
     */
    public final VerifyAuthenticationResponse verifyAuthentication(final String contractNumber, final String pares, final String md, final Card card,
        final String version) {
        setException(null);
        VerifyAuthenticationResponse result = new VerifyAuthenticationResponse();
        VerifyAuthenticationRequest parameters = new VerifyAuthenticationRequest();

        parameters.setContractNumber(contractNumber);
        parameters.setMd(md);
        parameters.setPares(pares);
        parameters.setCard(card);
        parameters.setVersion(version);

        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.verifyAuthentication(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during verifyAuthentication call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }
    
    /**
     * Ask for customer scoring to an external payment processor
     * @param buyer
     * @param order
     * @param payment
     * @param privatedatalist
     * @param version
     * @return
     */
    public final IsRegisteredResponse isRegistered(final Buyer buyer, final Order order, final Payment payment, final PrivateDataList privatedatalist, final String version){
    	IsRegisteredResponse result = new IsRegisteredResponse();
    	IsRegisteredRequest parameters = new IsRegisteredRequest();
    	
        parameters.setBuyer(buyer);
    	parameters.setOrder(order);
    	parameters.setPayment(payment);
    	parameters.setPrivateDataList(privatedatalist);
    	parameters.setVersion(version);
    	
    	 final DirectPaymentAPI port;
         try {
             if (this.initFromFile) {
                 port = Utils.initServiceDirect();
             } else {
                 port = Utils.initServiceDirect(this.connectParams);
             }
             result = port.isRegistered(parameters);
         } catch (WebServiceException ex) {
             setException(ex);
             logger.log(Level.SEVERE, "Error during isRegistered call : ", ex);
             Result err = new Result();
             err.setCode(Utils.EXCEPTION_CODE);
             err.setLongMessage(ex.getMessage());
             err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
             result.setResult(err);
         }
    	return result;
    }
}
