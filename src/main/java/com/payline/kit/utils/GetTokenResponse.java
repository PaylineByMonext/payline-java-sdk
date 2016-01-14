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
package com.payline.kit.utils;

/**
 * GetTokenResponse class
 * @author payline dev team
 */
public class GetTokenResponse {

    /**
     * cardTokenPan, card number, cryted with Payline tokeniser
     */
    private String cardTokenPan;

    /**
     * cardExp, card expiration date
     */
    private String cardExp;

    /**
     * vCVV, virtual Card Verification Value, returned by Payline getToken servlet
     */
    private String vCVV;

    /**
     * orderRef, the order reference
     */
    private String orderRef;

    /**
     * cardType, the card type (CB, VISA, MASTERCARD, AMEX,...)
     */
    private String cardType;

    /**
     * cardIsCVD, flag that indicates whether card is virtual
     */
    private String cardIsCVD;

    /**
     * cardCountry, coutry of the card
     */
    private String cardCountry;

    /**
     * cardProduct, specific type of the card (Electron, Maestro,...)
     */
    private String cardProduct;

    /**
     * bankCode, 5 digit identifier of the bank
     */
    private String bankCode;

    /**
     * Default constructor.
     * @param rawResponse
     */
    public GetTokenResponse(String rawResponse) {
        String[] rawResponseArray = rawResponse.split(";");
        this.cardTokenPan = rawResponseArray[0];
        this.cardExp = rawResponseArray[1];
        this.vCVV = rawResponseArray[2];
        this.orderRef = rawResponseArray[3];
        this.cardType = rawResponseArray[4];
        this.cardIsCVD = rawResponseArray[5];
        this.cardCountry = rawResponseArray[6];
        this.cardProduct = rawResponseArray[7];
        this.bankCode = rawResponseArray[8];
    }

    /**
     * Returns the card number, cryted with Payline tokeniser
     * @return cardTokenPan
     */
    public String getCardTokenPan() {
        return cardTokenPan;
    }

    /**
     * Returns the card expiration date
     * @return cardExp
     */
    public String getCardExp() {
        return cardExp;
    }

    /**
     * Returns the virtual Card Verification Value, returned by Payline getToken servlet
     * @return vCVV
     */
    public String getvCVV() {
        return vCVV;
    }

    /**
     * Returns the order reference
     * @return orderRef
     */
    public String getOrderRef() {
        return orderRef;
    }

    /**
     * Returns the card type (CB, VISA, MASTERCARD, AMEX,...)
     * @return cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Returns the flag that indicates whether card is virtual
     * @return cardIsCVD
     */
    public String getCardIsCVD() {
        return cardIsCVD;
    }

    /**
     * Returns the coutry of the card
     * @return cardCountry
     */
    public String getCardCountry() {
        return cardCountry;
    }

    /**
     * Returns specific type of the card (Electron, Maestro,...)
     * @return cardProduct
     */
    public String getCardProduct() {
        return cardProduct;
    }

    /**
     * Returns the 5 digit identifier of the bank
     * @return bankCode
     */
    public String getBankCode() {
        return bankCode;
    }
}
