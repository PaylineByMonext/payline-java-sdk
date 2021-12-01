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

import com.payline.ws.model.CreateWalletRequest;
import com.payline.ws.model.CreateWalletResponse;
import com.payline.ws.model.CreateWebWalletRequest;
import com.payline.ws.model.CreateWebWalletResponse;
import com.payline.ws.model.DirectPaymentAPI;
import com.payline.ws.model.DisablePaymentRecordRequest;
import com.payline.ws.model.DisablePaymentRecordResponse;
import com.payline.ws.model.DisableWalletRequest;
import com.payline.ws.model.DisableWalletResponse;
import com.payline.ws.model.DoImmediateWalletPaymentRequest;
import com.payline.ws.model.DoImmediateWalletPaymentResponse;
import com.payline.ws.model.DoRecurrentWalletPaymentRequest;
import com.payline.ws.model.DoRecurrentWalletPaymentResponse;
import com.payline.ws.model.DoScheduledWalletPaymentRequest;
import com.payline.ws.model.DoScheduledWalletPaymentResponse;
import com.payline.ws.model.EnableWalletRequest;
import com.payline.ws.model.EnableWalletResponse;
import com.payline.ws.model.GetBillingRecordRequest;
import com.payline.ws.model.GetBillingRecordResponse;
import com.payline.ws.model.GetCardsRequest;
import com.payline.ws.model.GetCardsResponse;
import com.payline.ws.model.GetPaymentRecordRequest;
import com.payline.ws.model.GetPaymentRecordResponse;
import com.payline.ws.model.GetWalletRequest;
import com.payline.ws.model.GetWalletResponse;
import com.payline.ws.model.GetWebWalletRequest;
import com.payline.ws.model.GetWebWalletResponse;
import com.payline.ws.model.ManageWebWalletRequest;
import com.payline.ws.model.ManageWebWalletResponse;
import com.payline.ws.model.UpdateBillingRecordRequest;
import com.payline.ws.model.UpdateBillingRecordResponse;
import com.payline.ws.model.UpdatePaymentRecordRequest;
import com.payline.ws.model.UpdatePaymentRecordResponse;
import com.payline.ws.model.UpdateWalletRequest;
import com.payline.ws.model.UpdateWalletResponse;
import com.payline.ws.model.UpdateWebWalletRequest;
import com.payline.ws.model.UpdateWebWalletResponse;
import com.payline.ws.model.WebPaymentAPI;
import com.payline.ws.model.Authentication3DSecure;
import com.payline.ws.model.BillingRecordForUpdate;
import com.payline.ws.model.Buyer;
import com.payline.ws.model.ContractNumberWalletList;
import com.payline.ws.model.Order;
import com.payline.ws.model.Owner;
import com.payline.ws.model.Payment;
import com.payline.ws.model.ThreeDSInfo;
import com.payline.ws.model.PrivateDataList;
import com.payline.ws.model.Recurring;
import com.payline.ws.model.RecurringForUpdate;
import com.payline.ws.model.Result;
import com.payline.ws.model.SelectedContractList;
import com.payline.ws.model.SubMerchant;
import com.payline.ws.model.Wallet;
import com.payline.ws.model.WalletIdList;
import com.payline.kit.utils.ConnectParams;
import com.payline.kit.utils.PaylineProperties;
import com.payline.kit.utils.Utils;

/**
 * WalletPayment class
 * @author payline dev team
 */
public class WalletPayment extends WebServiceWrapper {

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
     * The default Constructor class
     */
    public WalletPayment() {
        super();
    }

    /**
     * The Constructor class
     * @param connectParams the connections parameters
     */
    public WalletPayment(ConnectParams connectParams) {
        super();
        this.initFromFile = false;
        this.connectParams = connectParams;
    }


    /**
     * Create a customer wallet. The 'createWallet' function is used to create a virtual wallet for your customer. In order to validate the use of a wallet,
     * Payline performs an e-payment check of the payment method via an authorization transaction of 1 euro which will not then be validated (no actual
     * payment).
     * @param wallet the wallet object, containing the walletId and data about its owner (firstName, lastName, email,...)
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param authentication3DSecure the authentication3DSecure object, filled with MD and PARES retrieved from the ACS after the customer entered his password
     * @param version the API version of Payline
     * @return CreateWalletResponse
     */
    public final CreateWalletResponse createWallet(final Wallet wallet, final String contractNumber, final PrivateDataList privateDataList,
        final Authentication3DSecure authentication3DSecure, final String version) {

        return this.createWallet(wallet, contractNumber, privateDataList,
                authentication3DSecure, version, null, null, null, null, null);
    }


    /**
     * Create a customer wallet. The 'createWallet' function is used to create a virtual wallet for your customer. In order to validate the use of a wallet,
     * Payline performs an e-payment check of the payment method via an authorization transaction of 1 euro which will not then be validated (no actual
     * payment).
     * @param wallet the wallet object, containing the walletId and data about its owner (firstName, lastName, email,...)
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param authentication3DSecure the authentication3DSecure object, filled with MD and PARES retrieved from the ACS after the customer entered his password
     * @param version the API version of Payline
     * @param buyer
     * @param owner
     * @param media
     * @param contractNumberWalletList a list of selectedContract objects, containing Payline identifier of your e-commerce contract number
     * @param transactionID
     * @return CreateWalletResponse
     */
    public final CreateWalletResponse createWallet(final Wallet wallet, final String contractNumber, final PrivateDataList privateDataList,
        final Authentication3DSecure authentication3DSecure, final String version, final Buyer buyer, final Owner owner, final String media, final ContractNumberWalletList contractNumberWalletList, final String transationID) {
        setException(null);
        CreateWalletResponse result = new CreateWalletResponse();
        CreateWalletRequest parameters = new CreateWalletRequest();
        parameters.setContractNumber(contractNumber);
        parameters.setWallet(wallet);
        parameters.setPrivateDataList(privateDataList);
        parameters.setAuthentication3DSecure(authentication3DSecure);
        parameters.setVersion(version);
		parameters.setBuyer(buyer);
		parameters.setOwner(owner);
        parameters.setMedia(media);
        parameters.setContractNumberWalletList(contractNumberWalletList);
        parameters.setTransactionID(transationID);

        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.createWallet(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during createWallet call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

        /**
         * Retrieve information making up the customer wallet The 'getWallet' function retrieves virtual wallet data.
         * @param walletId the wallet identifier
         * @param contractNumber Payline identifier of your e-commerce contract number
         * @param Cardind within a wallet, index of the card to be used for payment
         * @param version the API version of Payline
         * @return GetWalletResponse
         */
        public final GetWalletResponse getWallet(final String walletId, final String contractNumber, final String Cardind, final String version) {
            return this.getWallet(walletId, contractNumber, Cardind, version, null);
        }

    /**
     * Retrieve information making up the customer wallet The 'getWallet' function retrieves virtual wallet data.
     * @param walletId the wallet identifier
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param Cardind within a wallet, index of the card to be used for payment
     * @param version the API version of Payline
     * @param media                 Detection of the media used during the payment.
     * @return GetWalletResponse
     */
    public final GetWalletResponse getWallet(final String walletId, final String contractNumber, final String Cardind, final String version, final String media) {
        setException(null);
        GetWalletResponse result = new GetWalletResponse();
        GetWalletRequest parameters = new GetWalletRequest();
        parameters.setWalletId(walletId);
        parameters.setContractNumber(contractNumber);
        parameters.setVersion(version);
        parameters.setCardInd(Cardind);
        parameters.setMedia(media);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.getWallet(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getWallet call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }


    /**
         * Update customer wallet. The 'updateWallet' function is used to update the virtual wallet.
         * @param wallet the wallet object, containing the walletId and data about its owner (firstName, lastName, email,...)
         * @param contractNumber Payline identifier of your e-commerce contract number
         * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
         * @param authentication3DSecure the authentication3DSecure object, filled with MD and PARES retrieved from the ACS after the customer entered his password
         * @param Cardind within a wallet, index of the card to be used for payment
         * @param version the API version of Payline
         * @return UpdateWalletResponse
         */
        public final UpdateWalletResponse updateWallet(final Wallet wallet, final String contractNumber, final PrivateDataList privateDataList,
            final Authentication3DSecure authentication3DSecure, final String Cardind, final String version) {
            return this.updateWallet(wallet, contractNumber, privateDataList,
                        authentication3DSecure, Cardind, version, null, null, null, null, null);

        }

    /**
     * Update customer wallet. The 'updateWallet' function is used to update the virtual wallet.
     * @param wallet the wallet object, containing the walletId and data about its owner (firstName, lastName, email,...)
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param authentication3DSecure the authentication3DSecure object, filled with MD and PARES retrieved from the ACS after the customer entered his password
     * @param Cardind within a wallet, index of the card to be used for payment
     * @param version the API version of Payline
     * @param buyer
     * @param owner
     * @param media
     * @param contractNumberWalletList a list of selectedContract objects, containing Payline identifier of your e-commerce contract number
     * @param transactionID
     * @return UpdateWalletResponse
     */
    public final UpdateWalletResponse updateWallet(final Wallet wallet, final String contractNumber, final PrivateDataList privateDataList,
        final Authentication3DSecure authentication3DSecure, final String Cardind, final String version, final Buyer buyer, final Owner owner, final String media, ContractNumberWalletList contractNumberWalletList, final String transationID ) {
        setException(null);
        UpdateWalletResponse result = new UpdateWalletResponse();
        UpdateWalletRequest parameters = new UpdateWalletRequest();
        parameters.setWallet(wallet);
        parameters.setContractNumber(contractNumber);
        parameters.setPrivateDataList(privateDataList);
        parameters.setAuthentication3DSecure(authentication3DSecure);
        parameters.setCardInd(Cardind);
        parameters.setVersion(version);
		parameters.setBuyer(buyer);
		parameters.setOwner(owner);
        parameters.setMedia(media);
        parameters.setContractNumberWalletList(contractNumberWalletList);
        parameters.setTransactionID(transationID);

        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.updateWallet(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during updateWallet call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Deactivate a customer wallet. The 'disableWalletResponse' message is the response from Payline to a virtual wallet deactivation request.
     * @param walletIdList the list of virtual wallet ID
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param Cardind within a wallet, index of the card to be used for payment
     * @return DisableWalletResponse
     */
    public final DisableWalletResponse disableWallet(final WalletIdList walletIdList, final String contractNumber, final String Cardind) {
        setException(null);
        DisableWalletResponse result = new DisableWalletResponse();
        DisableWalletRequest parameters = new DisableWalletRequest();
        parameters.setWalletIdList(walletIdList);
        parameters.setContractNumber(contractNumber);
        parameters.setCardInd(Cardind);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.disableWallet(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during disableWallet call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Reactivate a customer wallet. The <b>enableWallet</b> function is used to activate the virtual wallet.
     * @param walletId the wallet identifier
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param Cardind within a wallet, index of the card to be used for payment
     * @return EnableWalletResponse
     */
    public final EnableWalletResponse enableWallet(final String walletId, final String contractNumber, final String Cardind) {
        setException(null);
        EnableWalletResponse result = new EnableWalletResponse();
        EnableWalletRequest parameters = new EnableWalletRequest();
        parameters.setContractNumber(contractNumber);
        parameters.setWalletId(walletId);
        parameters.setCardInd(Cardind);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.enableWallet(parameters);

        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during enableWallet call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Carry out a payment request from a customer wallet. The <b>doImmediateWalletPayment</b> function makes a virtual wallet payment.
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param walletId the wallet identifier
     * @param Cardind within a wallet, index of the card to be used for payment
     * @param cvx Card Verification Value associated to the Card Number
     * @param version the API version of Payline
     * @return DoImmediateWalletPaymentResponse
     */
    public final DoImmediateWalletPaymentResponse doImmediateWalletPayment(final Payment payment, final Order order, final Buyer buyer,
        final PrivateDataList privateDataList, final String walletId, final String Cardind, final String cvx, final String version) {
        return this.doImmediateWalletPayment(payment, order, buyer, privateDataList, walletId, Cardind, cvx, null, version, null, null, null, null);
    }


    /**
     * Carry out a payment request from a customer wallet. The <b>doImmediateWalletPaymen</b> function makes a virtual wallet payment. With this function, you
     * may use the payment in full (FUL) and deferred (DEF) payment methods. Payline will send return code 02308: payment method not accepted for other methods.
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param walletId the wallet identifier
     * @param Cardind within a wallet, index of the card to be used for payment
     * @param cvx Card Verification Value associated to the Card Number
     * @param auth3ds 3D Secure authentication data
     * @param version the API version of Payline
     * @param subMerchant sub-merchant info in case you're using Payline as a payment facilitator for other merchants
     * @param recurring Recurring information
     * @param linkedTransactionId Use to identify the first authorization request which initializes the payment (for merchants managing their own wallets).
     * @return DoImmediateWalletPaymentResponse
     */
    public final DoImmediateWalletPaymentResponse doImmediateWalletPayment(final Payment payment, final Order order, final Buyer buyer,
        final PrivateDataList privateDataList, final String walletId, final String Cardind, final String cvx, final Authentication3DSecure auth3ds,
        final String version, final SubMerchant subMerchant, final Recurring recurring, final String linkedTransactionId) {

            return this.doImmediateWalletPayment(payment, order, buyer,
                    privateDataList, walletId, Cardind, cvx, auth3ds,
                    version, subMerchant, recurring, linkedTransactionId, null);
        }

    /**
     * Carry out a payment request from a customer wallet. The <b>doImmediateWalletPaymen</b> function makes a virtual wallet payment. With this function, you
     * may use the payment in full (FUL) and deferred (DEF) payment methods. Payline will send return code 02308: payment method not accepted for other methods.
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param walletId the wallet identifier
     * @param Cardind within a wallet, index of the card to be used for payment
     * @param cvx Card Verification Value associated to the Card Number
     * @param auth3ds 3D Secure authentication data
     * @param version the API version of Payline
     * @param subMerchant sub-merchant info in case you're using Payline as a payment facilitator for other merchants
     * @param recurring Recurring information
     * @param linkedTransactionId Use to identify the first authorization request which initializes the payment (for merchants managing their own wallets).
	 * @param owner
	 * @param media                 Detection of the media used during the payment.
     * @return DoImmediateWalletPaymentResponse
     */
    public final DoImmediateWalletPaymentResponse doImmediateWalletPayment(final Payment payment, final Order order, final Buyer buyer,
        final PrivateDataList privateDataList, final String walletId, final String Cardind, final String cvx, final Authentication3DSecure auth3ds,
        final String version, final SubMerchant subMerchant, final Recurring recurring, final String linkedTransactionId, final String media) {
        setException(null);
        DoImmediateWalletPaymentResponse result = new DoImmediateWalletPaymentResponse();
        DoImmediateWalletPaymentRequest parameters = new DoImmediateWalletPaymentRequest();
        parameters.setOrder(order);
        parameters.setPayment(payment);
        parameters.setBuyer(buyer);
        parameters.setPrivateDataList(privateDataList);
        parameters.setWalletId(walletId);
        parameters.setCardInd(Cardind);
        parameters.setCvx(cvx);
        parameters.setAuthentication3DSecure(auth3ds);
        parameters.setVersion(version);
        parameters.setSubMerchant(subMerchant);
        parameters.setRecurring(recurring);
        parameters.setLinkedTransactionId(linkedTransactionId);
        parameters.setMedia(media);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doImmediateWalletPayment(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doImmediateWalletPayment call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Schedule a payment request for a set day. The <b>doScheduledWalletPayment</b> function registers a scheduled payment request and, on the desired date,
     * performs a debit authorization request using the wallet data.
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param date the date to use
     * @param ref the reference
     * @param scheduledDate the sechduled Date
     * @param walletId the wallet identifier
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param Cardind within a wallet, index of the card to be used for payment
     * @param version the API version of Payline
     * @param subMerchant sub-merchant info in case you're using Payline as a payment facilitator for other merchants
     * @param recurring Recurring information
     * @param authentication3dSecure authentication3DSecure information
     * @param linkedTransactionId Use to identify the first authorization request which initializes the payment (for merchants managing their own wallets).
     * @return DoScheduledWalletPaymentResponse
     */
    public final DoScheduledWalletPaymentResponse doScheduledWalletPayment(final Payment payment, final String date, final String ref,
        final String scheduledDate, final String walletId, final Order order, final PrivateDataList privateDataList, final String Cardind,
        final String version, final SubMerchant subMerchant, final Recurring recurring, final Authentication3DSecure authentication3dSecure,
        final String linkedTransactionId) {
        setException(null);
        DoScheduledWalletPaymentResponse result = new DoScheduledWalletPaymentResponse();
        DoScheduledWalletPaymentRequest parameters = new DoScheduledWalletPaymentRequest();
        parameters.setOrderDate(date);
        parameters.setOrderRef(ref);
        parameters.setPayment(payment);
        parameters.setScheduledDate(scheduledDate);
        parameters.setWalletId(walletId);
        parameters.setOrder(order);
        parameters.setPrivateDataList(privateDataList);
        parameters.setCardInd(Cardind);
        parameters.setVersion(version);
        parameters.setSubMerchant(subMerchant);
        parameters.setRecurring(recurring);
        parameters.setAuthentication3DSecure(authentication3dSecure);
        parameters.setLinkedTransactionId(linkedTransactionId);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doScheduledWalletPayment(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doScheduledWalletPayment call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Schedule a fixed payment amount for payment requests (subscription). The <b>doRecurringWalletPayment</b> function registers a payment record for
     * automatic and recurring billing of your customer. Payline processes the instalments that come up on a daily basis and informs you of the result through
     * the notification function.
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param recurring the recurring object, containing data about how Payline will deal with the recurring payment : first amount, other amount, number of
     *            instalment, periodicity
     * @param date the date to use
     * @param ref the reference
     * @param walletId the wallet identifier
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param Cardind within a wallet, index of the card to be used for payment
     * @param cvx Visual cryptogram on the back of the credit card
     * @param linkedTransactionId In case of installment, recurring or split shipment payment refers to the first authorization (bank id)
     * @param authentication3DSecure 3DSecure operations information
     * @return DoRecurrentWalletPaymentResponse
     */
    public final DoRecurrentWalletPaymentResponse doRecurrentWalletPayment(final Payment payment, final Recurring recurring, final String date,
        final String ref, final String walletId, final Order order, final PrivateDataList privateDataList, final String Cardind, final String version,
        final String cvx, final String linkedTransactionId, final Authentication3DSecure authentication3DSecure) {
            return this.doRecurrentWalletPayment(payment, recurring, date,
                    ref, walletId, order, privateDataList, Cardind, version,
                    cvx, linkedTransactionId, authentication3DSecure, null);
        }

    /**
     * Schedule a fixed payment amount for payment requests (subscription). The <b>doRecurringWalletPayment</b> function registers a payment record for
     * automatic and recurring billing of your customer. Payline processes the instalments that come up on a daily basis and informs you of the result through
     * the notification function.
     * @param payment the payment object containing the amount, the currency, action and mode codes
     * @param recurring the recurring object, containing data about how Payline will deal with the recurring payment : first amount, other amount, number of
     *            instalment, periodicity
     * @param date the date to use
     * @param ref the reference
     * @param walletId the wallet identifier
     * @param order the order object containing the ref, the amount, the currency, the date and cart content in details child
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param Cardind within a wallet, index of the card to be used for payment
     * @param cvx Visual cryptogram on the back of the credit card
     * @param linkedTransactionId In case of installment, recurring or split shipment payment refers to the first authorization (bank id)
     * @param authentication3DSecure 3DSecure operations information
     * @param media                 Detection of the media used during the payment.
     * @return DoRecurrentWalletPaymentResponse
     */
    public final DoRecurrentWalletPaymentResponse doRecurrentWalletPayment(final Payment payment, final Recurring recurring, final String date,
        final String ref, final String walletId, final Order order, final PrivateDataList privateDataList, final String Cardind, final String version,
        final String cvx, final String linkedTransactionId, final Authentication3DSecure authentication3DSecure, final String media) {
        setException(null);
        DoRecurrentWalletPaymentResponse result = new DoRecurrentWalletPaymentResponse();
        DoRecurrentWalletPaymentRequest parameters = new DoRecurrentWalletPaymentRequest();
        parameters.setOrderDate(date);
        parameters.setOrderRef(ref);
        parameters.setPayment(payment);
        parameters.setRecurring(recurring);
        parameters.setOrder(order);
        parameters.setPrivateDataList(privateDataList);
        parameters.setCardInd(Cardind);
        parameters.setWalletId(walletId);
        parameters.setVersion(version);
        parameters.setCvx(cvx);
        parameters.setLinkedTransactionId(linkedTransactionId);
        parameters.setAuthentication3DSecure(authentication3DSecure);
        parameters.setMedia(media);

        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.doRecurrentWalletPayment(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during doRecurrentWalletPayment call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Retrieve a payment record. The <b>getPaymentRecord</b> function is used to get the information from a recurring payment record.
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param paymentRecordId the record identifier
     * @return GetPaymentRecordResponse
     */
    public final GetPaymentRecordResponse getPaymentRecord(final String contractNumber, final String paymentRecordId, final String version) {
        setException(null);
        GetPaymentRecordResponse result = new GetPaymentRecordResponse();
        GetPaymentRecordRequest parameters = new GetPaymentRecordRequest();
        parameters.setContractNumber(contractNumber);
        parameters.setPaymentRecordId(paymentRecordId);
        parameters.setVersion(version);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.getPaymentRecord(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getPaymentRecord call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * This web service modifies one or several settings for a payment record.
     * @param version the API version of Payline
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param paymentRecordId identifier of a payment record (a list of billing record)
     * @param recurring the recurring object, containing data about how Payline will deal with the recurring payment : first amount, other amount, number of
     *            instalment, periodicity
     * @return UpdatePaymentRecordResponse
     */
    public final UpdatePaymentRecordResponse updatePaymentRecord(final String version, final String contractNumber, final String paymentRecordId,
        final RecurringForUpdate recurring) {
        UpdatePaymentRecordResponse result = new UpdatePaymentRecordResponse();
        UpdatePaymentRecordRequest parameters = new UpdatePaymentRecordRequest();
        parameters.setVersion(version);
        parameters.setContractNumber(contractNumber);
        parameters.setPaymentRecordId(paymentRecordId);
        parameters.setRecurring(recurring);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.updatePaymentRecord(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during updatePaymentRecord call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Deactivate a payment record. The <b>disablePaymentRecord</b> function is used to deactivate a payment record. Once a payment record is disabled, its
     * associated instalments will no longer be processed.
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param recordId the record identifier
     * @return DisablePaymentRecordResponse
     */
    public final DisablePaymentRecordResponse disablePaymentRecord(final String contractNumber, final String recordId) {
        setException(null);
        DisablePaymentRecordResponse result = new DisablePaymentRecordResponse();
        DisablePaymentRecordRequest parameters = new DisablePaymentRecordRequest();
        parameters.setContractNumber(contractNumber);
        parameters.setPaymentRecordId(recordId);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.disablePaymentRecord(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during disablePaymentRecord call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * This web service is used to retrieve the specifications of a payment instalment.
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param paymentRecordId identifier of a payment record (a list of billing record)
     * @param billingRecordId identifier of a billing record, within a payment record
     * @return GetBillingRecordResponse
     */
    public final GetBillingRecordResponse getBillingRecord(final String contractNumber, final String paymentRecordId, final String billingRecordId) {
        GetBillingRecordResponse result = new GetBillingRecordResponse();
        GetBillingRecordRequest parameters = new GetBillingRecordRequest();
        parameters.setContractNumber(contractNumber);
        parameters.setPaymentRecordId(paymentRecordId);
        parameters.setBillingRecordId(billingRecordId);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.getBillingRecord(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getBillingRecord call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * This web service modifies the instalment payment date or the amount of an NX instalment. It also allows a series of payment attempts to be resubmitted
     * when the instalment has 'Failed status' (NX and REC)
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param paymentRecordId identifier of a payment record (a list of billing record)
     * @param billingRecordId identifier of a billing record, within a payment record
     * @param billingRecord the billingRecord object, containing data about a billing record : amount, date, result, transaction,...
     * @return UpdateBillingRecordResponse
     */
    public final UpdateBillingRecordResponse updateBillingRecord(final String contractNumber, final String paymentRecordId, final String billingRecordId,
        final BillingRecordForUpdate billingRecord) {
        UpdateBillingRecordResponse result = new UpdateBillingRecordResponse();
        UpdateBillingRecordRequest parameters = new UpdateBillingRecordRequest();
        parameters.setContractNumber(contractNumber);
        parameters.setPaymentRecordId(paymentRecordId);
        parameters.setBillingRecordId(billingRecordId);
        parameters.setBillingRecordForUpdate(billingRecord);
        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.updateBillingRecord(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during updateBillingRecord call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;

    }


/**
     * Create a customer wallet using web pages The <b>createWebWallet</b> function initialises the creation of a virtual wallet via the web interface. Once
     * your customer is redirected, they are asked to enter their card details to create their virtual wallet. Payline checks this information by a debit
     * authorization request for a sum of EUR 1 (validation does not take place so no card is credited on creation) and registers the customer wallet with the ID
     * (walletId) you have provided.
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param notificationURL store URL which Payline will call if customer do not come back to store after his payment
     * @param returnURL store URL to which customer is redirected when "back to store" button is used on Payline payment page after the payment
     * @param cancelURL store URL to which customer is redirected when "cancel" button is used on Payline payment page before the payment
     * @param languageCode ISO 639-1 code of language in which payment page is displayed
     * @param securityMode type of security when wallet is used on payment page : 3DS, CVV or CVV+3DS
     * @param customPaymentPageCode code of payment page customization, created in administration center
     * @param customPaymentTemplateURL URL of the custom template
     * @param selectedContractList a list of selectedContract objects, containing Payline identifier of your e-commerce contract number
     * @param updatePersonalDetails flag (0/1) to indicate wether customer can change his personal details (firstname, lastname,...) on update wallet page
     * @return CreateWebWalletResponse
     */
    public final CreateWebWalletResponse createWebWallet(final Buyer buyer, final PrivateDataList privateDataList, String notificationURL, String returnURL,
        String cancelURL, final String languageCode, final String securityMode, final String customPaymentPageCode, final String customPaymentTemplateURL,
        final SelectedContractList selectedContractList, final String updatePersonalDetails, final String version) {

            return this.createWebWallet(buyer, privateDataList, notificationURL, returnURL,
                    cancelURL, languageCode, securityMode, customPaymentPageCode, customPaymentTemplateURL, selectedContractList, updatePersonalDetails, version, null, null, null);

        }

    /**
     * Create a customer wallet using web pages The <b>createWebWallet</b> function initialises the creation of a virtual wallet via the web interface. Once
     * your customer is redirected, they are asked to enter their card details to create their virtual wallet. Payline checks this information by a debit
     * authorization request for a sum of EUR 1 (validation does not take place so no card is credited on creation) and registers the customer wallet with the ID
     * (walletId) you have provided.
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param notificationURL store URL which Payline will call if customer do not come back to store after his payment
     * @param returnURL store URL to which customer is redirected when "back to store" button is used on Payline payment page after the payment
     * @param cancelURL store URL to which customer is redirected when "cancel" button is used on Payline payment page before the payment
     * @param languageCode ISO 639-1 code of language in which payment page is displayed
     * @param securityMode type of security when wallet is used on payment page : 3DS, CVV or CVV+3DS
     * @param customPaymentPageCode code of payment page customization, created in administration center
     * @param customPaymentTemplateURL URL of the custom template
     * @param selectedContractList a list of selectedContract objects, containing Payline identifier of your e-commerce contract number
     * @param updatePersonalDetails flag (0/1) to indicate wether customer can change his personal details (firstname, lastname,...) on update wallet page
	 * @param version                the API version of Payline
	 * @param contractNumber Payline identifier of your e-commerce contract number
	 * @param owner                      Cardholder Information
	 * @param contractNumberWalletList   A chart of the contract numbers of the
	 *                                   wallet. The Widget mode uses the multi-PDV
	 *                                   Wallet and multi-contract.
     * @return CreateWebWalletResponse
     */
    public final CreateWebWalletResponse createWebWallet(final Buyer buyer, final PrivateDataList privateDataList, String notificationURL, String returnURL,
        String cancelURL, final String languageCode, final String securityMode, final String customPaymentPageCode, final String customPaymentTemplateURL,
        final SelectedContractList selectedContractList, final String updatePersonalDetails, final String version, final String contractNumber, final Owner owner, final ContractNumberWalletList contractNumberWalletList) {
        setException(null);
        CreateWebWalletResponse result = new CreateWebWalletResponse();
        if (returnURL.length() <= 0) {
            returnURL = PaylineProperties.getString("RETURN_URL");
        }
        if (cancelURL.length() <= 0) {
            cancelURL = PaylineProperties.getString("CANCEL_URL");
        }
        if (notificationURL.length() <= 0) {
            notificationURL = PaylineProperties.getString("NOTIFICATION_URL");
        }

        CreateWebWalletRequest parameters = new CreateWebWalletRequest();
        parameters.setBuyer(buyer);
        parameters.setCancelURL(cancelURL);

        if(contractNumber != null ) {
            parameters.setContractNumber(contractNumber);
        } else if (parameters.getContractNumber() == null || parameters.getContractNumber().length() == 0) {
            parameters.setContractNumber(PaylineProperties.getString("CONTRACT_NUMBER"));
        }

        parameters.setCustomPaymentPageCode(customPaymentPageCode);
        parameters.setCustomPaymentTemplateURL(customPaymentTemplateURL);
        parameters.setLanguageCode(languageCode);
        parameters.setNotificationURL(notificationURL);
        parameters.setPrivateDataList(privateDataList);
        parameters.setReturnURL(returnURL);
        parameters.setSecurityMode(securityMode);
        parameters.setSelectedContractList(selectedContractList);
        parameters.setUpdatePersonalDetails(updatePersonalDetails);
        parameters.setVersion(version);
        parameters.setContractNumber(contractNumber);
        parameters.setOwner(owner);
		parameters.setContractNumberWalletList(contractNumberWalletList);

        final WebPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceWeb();
            } else {
                port = Utils.initServiceWeb(this.connectParams);
            }
            result = port.createWebWallet(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during createWebWallet call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }



    /**
     * Modify a customer wallet using web pages The <b>updateWebWallet</b> function initialises the modification of a virtual wallet via the web interface.
     * @param walletId the wallet identifier
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param notificationURL store URL which Payline will call if customer do not come back to store after his payment
     * @param returnURL store URL to which customer is redirected when "back to store" button is used on Payline payment page after the payment
     * @param cancelURL store URL to which customer is redirected when "cancel" button is used on Payline payment page before the payment
     * @param languageCode ISO 639-1 code of language in which payment page is displayed
     * @param securityMode type of security when wallet is used on payment page : 3DS, CVV or CVV+3DS
     * @param customPaymentPageCode code of payment page customization, created in administration center
     * @param customPaymentTemplateURL URL of the custom template
     * @param selectedContractList a list of selectedContract objects, containing Payline identifier of your e-commerce contract number
     * @param updatePersonalDetails flag (0/1) to indicate whether customer can change his personal details (firstname, lastname,...) on update wallet page
     * @param updatePaymentDetails flag (0/1) to indicate whether customer can change his card data on update wallet page
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param Cardind within a wallet, index of the card to be used for payment
     * @return UpdateWebWalletResponse
     */
    public final UpdateWebWalletResponse updateWebWallet(final String walletId, final PrivateDataList privateDataList, String notificationURL, String returnURL,
        String cancelURL, final String languageCode, final String securityMode, final String customPaymentPageCode, final String customPaymentTemplateURL,
        final SelectedContractList selectedContractList, final String updatePersonalDetails, final String updatePaymentDetails, final Buyer buyer,
        final String Cardind, final String version) {

            return this.updateWebWallet(walletId, privateDataList, notificationURL, returnURL,
                     cancelURL, languageCode, securityMode, customPaymentPageCode, customPaymentTemplateURL,
                    selectedContractList, updatePersonalDetails, updatePaymentDetails, buyer,
                    Cardind, version, null, null, null);

    }
    /**
     * Modify a customer wallet using web pages The <b>updateWebWallet</b> function initialises the modification of a virtual wallet via the web interface.
     * @param walletId the wallet identifier
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param notificationURL store URL which Payline will call if customer do not come back to store after his payment
     * @param returnURL store URL to which customer is redirected when "back to store" button is used on Payline payment page after the payment
     * @param cancelURL store URL to which customer is redirected when "cancel" button is used on Payline payment page before the payment
     * @param languageCode ISO 639-1 code of language in which payment page is displayed
     * @param securityMode type of security when wallet is used on payment page : 3DS, CVV or CVV+3DS
     * @param customPaymentPageCode code of payment page customization, created in administration center
     * @param customPaymentTemplateURL URL of the custom template
     * @param selectedContractList a list of selectedContract objects, containing Payline identifier of your e-commerce contract number
     * @param updatePersonalDetails flag (0/1) to indicate whether customer can change his personal details (firstname, lastname,...) on update wallet page
     * @param updatePaymentDetails flag (0/1) to indicate whether customer can change his card data on update wallet page
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param Cardind within a wallet, index of the card to be used for payment
     * @param updateOwnerDetails
     * @param contractNumberWalletList   A chart of the contract numbers of the
	 *                                   wallet. The Widget mode uses the multi-PDV
     * @return UpdateWebWalletResponse
     */
    public final UpdateWebWalletResponse updateWebWallet(final String walletId, final PrivateDataList privateDataList, String notificationURL, String returnURL,
        String cancelURL, final String languageCode, final String securityMode, final String customPaymentPageCode, final String customPaymentTemplateURL,
        final SelectedContractList selectedContractList, final String updatePersonalDetails, final String updatePaymentDetails, final Buyer buyer,
        final String Cardind, final String version, final String  updateOwnerDetails, ContractNumberWalletList contractNumberWalletList, final String contractNumber) {
        setException(null);
        UpdateWebWalletResponse result = new UpdateWebWalletResponse();
        UpdateWebWalletRequest parameters = new UpdateWebWalletRequest();
        parameters.setWalletId(walletId);
        parameters.setCancelURL(cancelURL);
        if(contractNumber != null ) {
            parameters.setContractNumber(contractNumber);
        } else if (parameters.getContractNumber() == null || parameters.getContractNumber().length() == 0) {
            parameters.setContractNumber(PaylineProperties.getString("CONTRACT_NUMBER"));
        }
        parameters.setCustomPaymentPageCode(customPaymentPageCode);
        parameters.setCustomPaymentTemplateURL(customPaymentTemplateURL);
        parameters.setLanguageCode(languageCode);
        parameters.setNotificationURL(notificationURL);
        parameters.setPrivateDataList(privateDataList);
        parameters.setReturnURL(returnURL);
        parameters.setSecurityMode(securityMode);
        parameters.setUpdatePersonalDetails(updatePersonalDetails);
        parameters.setUpdatePaymentDetails(updatePaymentDetails);
        parameters.setCardInd(Cardind);
        parameters.setBuyer(buyer);
        parameters.setVersion(version);
		parameters.setUpdateOwnerDetails(updateOwnerDetails);
		parameters.setContractNumberWalletList(contractNumberWalletList);

        final WebPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceWeb();
            } else {
                port = Utils.initServiceWeb(this.connectParams);
            }
            result = port.updateWebWallet(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during updateWebWallet call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Manage a wallet from the web interface.
     * @param version the API version of Payline
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param selectedContractList a list of selectedContract objects, containing Payline identifier of your e-commerce contract number
     * @param updatePersonalDetails flag (0/1) to indicate whether customer can change his personal details (firstname, lastname,...) on update wallet page
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param owner the owner object, containing info about the owner of an American Express card
     * @param languageCode ISO 639-1 code of language in which payment page is displayed
     * @param customPaymentPageCode code of payment page customization, created in administration center
     * @param securityMode type of security when wallet is used on payment page : 3DS, CVV or CVV+3DS
     * @param returnURL store URL to which customer is redirected when "back to store" button is used on Payline payment page after the payment
     * @param cancelURL store URL to which customer is redirected when "cancel" button is used on Payline payment page before the payment
     * @param notificationURL store URL which Payline will call if customer do not come back to store after his payment
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param customPaymentTemplateURL URL of the custom template
     * @param contractNumberWalletList a list of selectedContract objects, containing Payline identifier of your e-commerce contract number
     * @param merchantName name displayed to buyer on 3D Secure authentication form
     * @return ManageWebWalletResponse
     */
    public final ManageWebWalletResponse manageWebWallet(final String version, final String contractNumber, final SelectedContractList selectedContractList,
        final String updatePersonalDetails, final Buyer buyer, final Owner owner, final String languageCode, final String customPaymentPageCode,
        final String securityMode, final String returnURL, final String cancelURL, final String notificationURL, final PrivateDataList privateDataList,
        final String customPaymentTemplateURL, final ContractNumberWalletList contractNumberWalletList, final String merchantName) {
            return this.manageWebWallet(version, contractNumber, selectedContractList,
                    updatePersonalDetails, buyer, owner, languageCode, customPaymentPageCode,
                    securityMode, returnURL, cancelURL, notificationURL, privateDataList,
                    customPaymentTemplateURL, contractNumberWalletList, merchantName, null);
        }

    /**
     * Manage a wallet from the web interface.
     * @param version the API version of Payline
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param selectedContractList a list of selectedContract objects, containing Payline identifier of your e-commerce contract number
     * @param updatePersonalDetails flag (0/1) to indicate whether customer can change his personal details (firstname, lastname,...) on update wallet page
     * @param buyer the buyer object, containing many information about the buyer: firstname, lastname, email, addresses,...
     * @param owner the owner object, containing info about the owner of an American Express card
     * @param languageCode ISO 639-1 code of language in which payment page is displayed
     * @param customPaymentPageCode code of payment page customization, created in administration center
     * @param securityMode type of security when wallet is used on payment page : 3DS, CVV or CVV+3DS
     * @param returnURL store URL to which customer is redirected when "back to store" button is used on Payline payment page after the payment
     * @param cancelURL store URL to which customer is redirected when "cancel" button is used on Payline payment page before the payment
     * @param notificationURL store URL which Payline will call if customer do not come back to store after his payment
     * @param privateDataList A list of privateData, allowing to send any kind of extra information organized with keys and values
     * @param customPaymentTemplateURL URL of the custom template
     * @param contractNumberWalletList a list of selectedContract objects, containing Payline identifier of your e-commerce contract number
     * @param merchantName name displayed to buyer on 3D Secure authentication form
	 * @param threeDSInfo                Information specific to 3DS authentication
     * @return ManageWebWalletResponse
     */
    public final ManageWebWalletResponse manageWebWallet(final String version, final String contractNumber, final SelectedContractList selectedContractList,
        final String updatePersonalDetails, final Buyer buyer, final Owner owner, final String languageCode, final String customPaymentPageCode,
        final String securityMode, final String returnURL, final String cancelURL, final String notificationURL, final PrivateDataList privateDataList,
        final String customPaymentTemplateURL, final ContractNumberWalletList contractNumberWalletList, final String merchantName, final ThreeDSInfo threeDSInfo) {
        setException(null);
        final WebPaymentAPI port;
        ManageWebWalletResponse result = new ManageWebWalletResponse();
        ManageWebWalletRequest parameters = new ManageWebWalletRequest();
        parameters.setVersion(version);
        parameters.setContractNumber(contractNumber);
        parameters.setSelectedContractList(selectedContractList);
        parameters.setUpdatePersonalDetails(updatePersonalDetails);
        parameters.setBuyer(buyer);
        parameters.setOwner(owner);
        parameters.setLanguageCode(languageCode);
        parameters.setCustomPaymentPageCode(customPaymentPageCode);
        parameters.setSecurityMode(securityMode);
        parameters.setReturnURL(returnURL);
        parameters.setCancelURL(cancelURL);
        parameters.setNotificationURL(notificationURL);
        parameters.setPrivateDataList(privateDataList);
        parameters.setCustomPaymentTemplateURL(customPaymentTemplateURL);
        parameters.setContractNumberWalletList(contractNumberWalletList);
        parameters.setMerchantName(merchantName);
		parameters.setThreeDSInfo(threeDSInfo);

        try {
            if (this.initFromFile) {
                port = Utils.initServiceWeb();
            } else {
                port = Utils.initServiceWeb(this.connectParams);
            }
            result = port.manageWebWallet(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during manageWebWallet call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * This method is used to retrieve information from a virtual wallet created via the web interface
     * @param token identifier of a web payment, create/update wallet session
     * @param version the API version of Payline
     * @return GetWebWalletResponse
     */
    public final GetWebWalletResponse getWebWallet(final String token, final String version) {
        setException(null);
        final WebPaymentAPI port;
        GetWebWalletResponse result = new GetWebWalletResponse();
        GetWebWalletRequest parameters = new GetWebWalletRequest();
        parameters.setToken(token);
        parameters.setVersion(version);
        try {
            if (this.initFromFile) {
                port = Utils.initServiceWeb();
            } else {
                port = Utils.initServiceWeb(this.connectParams);
            }
            result = port.getWebWallet(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getWebWallet call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }

    /**
     * Retrieve information on cards in a multicard wallet. The <b>getCards</b> function allows information to be retrieved from cards in a multicard wallet.
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param walletId the wallet identifier
     * @param cardInd within a wallet, index of the card to be used for payment
     * @return GetCardsResponse
     */
    public final GetCardsResponse getCards(final String contractNumber, final String walletId, final String cardInd, final String version) {
        setException(null);
        GetCardsResponse result = new GetCardsResponse();
        GetCardsRequest parameters = new GetCardsRequest();
        parameters.setContractNumber(contractNumber);
        parameters.setWalletId(walletId);
        parameters.setCardInd(cardInd);
        parameters.setVersion(version);

        final DirectPaymentAPI port;
        try {
            if (this.initFromFile) {
                port = Utils.initServiceDirect();
            } else {
                port = Utils.initServiceDirect(this.connectParams);
            }
            result = port.getCards(parameters);
        } catch (WebServiceException ex) {
            setException(ex);
            logger.log(Level.SEVERE, "Error during getCards call : ", ex);
            Result err = new Result();
            err.setCode(Utils.EXCEPTION_CODE);
            err.setLongMessage(ex.getMessage());
            err.setShortMessage(Utils.EXCEPTION_SHORTMESSAGE);
            result.setResult(err);
        }
        return result;
    }
}
