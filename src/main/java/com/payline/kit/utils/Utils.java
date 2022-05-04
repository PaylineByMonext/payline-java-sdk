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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import com.payline.ws.model.DirectPaymentAPI;
import com.payline.ws.model.DirectPaymentAPI_Service;
import com.payline.ws.model.ExtendedAPI;
import com.payline.ws.model.ExtendedAPI_Service;
import com.payline.ws.model.WebPaymentAPI;
import com.payline.ws.model.WebPaymentAPI_Service;
import org.apache.commons.codec.binary.Base64;

/**
 * Utils class.
 * @author payline dev team
 */
public final class Utils {
    /**
     * EndPoints
     */
    private static final String HOMO_ENDPOINT = "https://homologation.payline.com/V4/services/";
    private static final String HOMO_ENDPOINT_CC = "https://homologation-cc.payline.com/V4/services/";
    private static final String PROD_ENDPOINT = "https://services.payline.com/V4/services/";
    private static final String PROD_ENDPOINT_CC = "https://services-cc.payline.com/V4/services/";

    /**
     * Ajax servlet
     */
    public static final String HOMO_GETTOKEN_SERVLET_URL = "https://homologation-webpayment.payline.com/webpayment/getToken";
    public static final String PROD_GETTOKEN_SERVLET_URL = "https://webpayment.payline.com/webpayment/getToken";

    /**
     * API
     */
    private static final String WEB_PAYMENT_API = "WebPaymentAPI";
    private static final String DIRECT_PAYMENT_API = "DirectPaymentAPI";
    private static final String EXTENDED_API = "ExtendedAPI";

    /**
     * WSDL
     */
    private static final String DIRECT_PAYMENT_API_WSDL = "wsdls/DirectPaymentAPI.wsdl";
    private static final String EXTENDED_API_WSDL = "wsdls/ExtendedAPI.wsdl";
    private static final String WEB_PAYMENT_API_WSDL = "wsdls/WebPaymentAPI.wsdl";

    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(Utils.class.getName());
    private static final Level LOG_LEVEL = Level.ALL;

    /**
     * Communication error
     */
    public static final String EXCEPTION_CODE = "XXXXX";
    public static final String EXCEPTION_SHORTMESSAGE = "EXCEPTION";

    /**
     * kit version
     */
    private static final String kitVersion = "kit JAVA v4.70";

    private static final String HTTP_PROXY_SET = "http.proxySet";
    private static final String HTTP_PROXY_HOST = "http.proxyHost";
    private static final String HTTP_PROXY_PORT = "http.proxyPort";
    private static final String HTTPS_PROXY_SET = "https.proxySet";
    private static final String HTTPS_PROXY_HOST = "https.proxyHost";
    private static final String HTTPS_PROXY_PORT = "https.proxyPort";
    private static final String PROXY_LOGIN = "PROXY_LOGIN";
    private static final String HTTPS_PROXY_LOGIN = "https.proxyLogin";
    private static final String HTTP_PROXY_LOGIN = "http.proxyLogin";
    private static final String HTTPS_PROXY_PASSWORD = "https.proxyPassword";
    private static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
    private static final String TRUE = "true";
    private static final String PROXY_PWD = "PROXY_PWD";

    /**
     * Default constructor.
     */
    private Utils() {
    }

    private static String maskAccessKey(String accessKey) {
        String maskedAccessKey = "";
        if (accessKey != null && accessKey.length() > 0) {
            int keyLengh = accessKey.length();
            maskedAccessKey = accessKey.substring(0, 2);
            maskedAccessKey += "********************".substring(0, keyLengh - 4);
            maskedAccessKey += accessKey.substring(keyLengh - 2, keyLengh);
        }
        return maskedAccessKey;
    }

    /**
     * log tools
     */
    final static String RETURN_LINE = "\n";
    final static String SEP_LINE = " - ";
    final static String SEP_PARAGRAPH = "----------";

    /**
     * @param proxy the ws binding provider
     * @param api the type of api to use Set HTTP credentials (username/password) and ws endpoint
     */
    private static void setHTTPBasicCredentialAndEndPointFromBundle(final Object proxy, final String api) {
        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "setting HTTPBasic Credentials And EndPoint from payline.properties file");

        // Set HTTP Proxy from bundle
        if (PaylineProperties.getString("USE_PROXY") != null && "1".equals(PaylineProperties.getString("USE_PROXY"))) {
            // Proxy HTTP
            System.setProperty(HTTP_PROXY_SET, TRUE);
            System.setProperty(HTTP_PROXY_HOST, PaylineProperties.getString("PROXY_HOST"));
            System.setProperty(HTTP_PROXY_PORT, PaylineProperties.getString("PROXY_PORT"));
            // PROXY HTTPS
            System.setProperty(HTTPS_PROXY_SET, TRUE);
            System.setProperty(HTTPS_PROXY_HOST, PaylineProperties.getString("PROXY_HOST"));
            System.setProperty(HTTPS_PROXY_PORT, PaylineProperties.getString("PROXY_PORT"));

            // Login Proxy
            if (PaylineProperties.getString(PROXY_LOGIN) != null && PaylineProperties.getString(PROXY_LOGIN).length() != 0) {
                System.setProperty(HTTPS_PROXY_LOGIN, PaylineProperties.getString(PROXY_LOGIN));
                System.setProperty(HTTP_PROXY_LOGIN, PaylineProperties.getString(PROXY_LOGIN));
            }
            // Password Proxy
            if (PaylineProperties.getString(PROXY_PWD) != null && PaylineProperties.getString(PROXY_PWD).length() != 0) {
                System.setProperty(HTTPS_PROXY_PASSWORD, PaylineProperties.getString(PROXY_PWD));
                System.setProperty(HTTP_PROXY_PASSWORD, PaylineProperties.getString(PROXY_PWD));
            }
        } else {
            // remove proxy parameters to prevent cache side effects
            System.setProperty(HTTP_PROXY_SET, "false");
            System.clearProperty(HTTP_PROXY_HOST);
            System.clearProperty(HTTP_PROXY_PORT);
            System.clearProperty(HTTP_PROXY_LOGIN);
            System.clearProperty(HTTP_PROXY_PASSWORD);
            System.setProperty(HTTPS_PROXY_SET, "false");
            System.clearProperty(HTTPS_PROXY_HOST);
            System.clearProperty(HTTPS_PROXY_PORT);
            System.clearProperty(HTTPS_PROXY_LOGIN);
            System.clearProperty(HTTPS_PROXY_PASSWORD);
        }

        // Set username from bundle
        ((BindingProvider) proxy).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, PaylineProperties.getString("MERCHANT_ID"));
        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "user name is : " + PaylineProperties.getString("MERCHANT_ID"));
        // Set password from bundle
        ((BindingProvider) proxy).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PaylineProperties.getString("ACCES_KEY"));
        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "password is : " + maskAccessKey(PaylineProperties.getString("ACCES_KEY")));

        // Set endpoint
        String endpoint;
        String prod = PaylineProperties.getString("PRODUCTION");
        if (TRUE.equalsIgnoreCase(prod)) {
            endpoint = Utils.PROD_ENDPOINT;
        } else {
            endpoint = Utils.HOMO_ENDPOINT;
        }
        endpoint = endpoint + api;

        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "endpoint is : " + endpoint);
        ((BindingProvider) proxy).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

        // version
        Map<String, List<String>> httpHeaders = new HashMap<String, List<String>>();
        httpHeaders.put("version", Collections.singletonList(Utils.kitVersion));
        Map<String, Object> reqContext = ((BindingProvider) proxy).getRequestContext();
        reqContext.put(MessageContext.HTTP_REQUEST_HEADERS, httpHeaders);
    }

    private static void setHTTPBasicCredentialAndEndPointFromParams(final Object proxy, final String api, ConnectParams params) {
        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "setting HTTPBasic Credentials And EndPoint from function parameters");

        // Set proxy
        if (params.getProxyHost() != null) {
            // Proxy HTTP
            System.setProperty(HTTP_PROXY_SET, TRUE);
            System.setProperty(HTTP_PROXY_HOST, params.getProxyHost());
            System.setProperty(HTTP_PROXY_PORT, params.getProxyPort());

            // PROXY HTTPS
            System.setProperty(HTTPS_PROXY_SET, TRUE);
            System.setProperty(HTTPS_PROXY_HOST, params.getProxyHost());
            System.setProperty(HTTPS_PROXY_PORT, params.getProxyPort());

            // Login Proxy
            if (params.getProxyLogin() != null) {
                System.setProperty(HTTPS_PROXY_LOGIN, params.getProxyLogin());
                System.setProperty(HTTP_PROXY_LOGIN, params.getProxyLogin());
            }
            // Password Proxy
            if (params.getProxyPassword() != null) {
                System.setProperty(HTTPS_PROXY_PASSWORD, params.getProxyPassword());
                System.setProperty(HTTP_PROXY_PASSWORD, params.getProxyPassword());
            }
        } else {
        	// remove proxy parameters to prevent cache side effects
            System.setProperty(HTTP_PROXY_SET, "false");
            System.clearProperty(HTTP_PROXY_HOST);
            System.clearProperty(HTTP_PROXY_PORT);
            System.clearProperty(HTTP_PROXY_LOGIN);
            System.clearProperty(HTTP_PROXY_PASSWORD);
            System.setProperty(HTTPS_PROXY_SET, "false");
            System.clearProperty(HTTPS_PROXY_HOST);
            System.clearProperty(HTTPS_PROXY_PORT);
            System.clearProperty(HTTPS_PROXY_LOGIN);
            System.clearProperty(HTTPS_PROXY_PASSWORD);
        }

        String endpoint;
        if (params.isClientAuthentication()) {
            // Set endpoint
            if (params.isProduction()) {
                endpoint = Utils.PROD_ENDPOINT_CC;
            } else {
                endpoint = Utils.HOMO_ENDPOINT_CC;
            }
        } else {
            // Set username
            ((BindingProvider) proxy).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, params.getMerchantId());
            if (LOG_LEVEL != Level.OFF)
                logger.log(LOG_LEVEL, "user name is : " + params.getMerchantId());
            // Set password
            ((BindingProvider) proxy).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, params.getAccessKey());
            if (LOG_LEVEL != Level.OFF)
                logger.log(LOG_LEVEL, "password is : " + maskAccessKey(params.getAccessKey()));

            // Set endpoint
            if (params.isProduction()) {
                endpoint = Utils.PROD_ENDPOINT;
            } else {
                endpoint = Utils.HOMO_ENDPOINT;
            }
        }
        endpoint += api;
        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "endpoint is : " + endpoint);
        ((BindingProvider) proxy).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

        // version
        String headerVersion;
        if (params.getModule() != null) {
            headerVersion = params.getModule();
        } else {
            headerVersion = Utils.kitVersion;
        }
        Map<String, List<String>> httpHeaders = new HashMap<String, List<String>>();
        httpHeaders.put("version", Collections.singletonList(headerVersion));
        Map<String, Object> reqContext = ((BindingProvider) proxy).getRequestContext();
        reqContext.put(MessageContext.HTTP_REQUEST_HEADERS, httpHeaders);
    }

    /**
     * initServiceWeb method.
     * @return WebPaymentAPI
     */
    public static WebPaymentAPI initServiceWeb() {
        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "Initiating WebPayment Services from payline.properties file");

        URL url = Utils.class.getClassLoader().getResource(Utils.WEB_PAYMENT_API_WSDL);
        WebPaymentAPI_Service service = new WebPaymentAPI_Service(url, new QName("http://impl.ws.payline.experian.com", Utils.WEB_PAYMENT_API));
        WebPaymentAPI port = service.getWebPaymentAPI();

        setHTTPBasicCredentialAndEndPointFromBundle(port, Utils.WEB_PAYMENT_API);

        return port;
    }

    /**
     * initServiceWeb method.
     * @return WebPaymentAPI
     */
    public static WebPaymentAPI initServiceWeb(ConnectParams params) {
        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "Initiating WebPayment Services from function parameters");

        URL url = Utils.class.getClassLoader().getResource(Utils.WEB_PAYMENT_API_WSDL);
        WebPaymentAPI_Service service = new WebPaymentAPI_Service(url, new QName("http://impl.ws.payline.experian.com", Utils.WEB_PAYMENT_API));
        WebPaymentAPI port = service.getWebPaymentAPI();

        setHTTPBasicCredentialAndEndPointFromParams(port, Utils.WEB_PAYMENT_API, params);

        return port;
    }

    /**
     * . initServiceDirect method
     * @return DirectPaymentAPI
     */
    public static DirectPaymentAPI initServiceDirect() {

        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "Initiating Direct Services from payline.properties file");

        URL url = Utils.class.getClassLoader().getResource(Utils.DIRECT_PAYMENT_API_WSDL);
        DirectPaymentAPI_Service service = new DirectPaymentAPI_Service(url, new QName("http://impl.ws.payline.experian.com", Utils.DIRECT_PAYMENT_API));
        DirectPaymentAPI port = service.getDirectPaymentAPI();

        setHTTPBasicCredentialAndEndPointFromBundle(port, Utils.DIRECT_PAYMENT_API);

        return port;
    }

    /**
     * . initServiceDirect method
     * @return DirectPaymentAPI
     */
    public static DirectPaymentAPI initServiceDirect(ConnectParams params) {

        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "Initiating Direct Services from function parameters");

        URL url = Utils.class.getClassLoader().getResource(Utils.DIRECT_PAYMENT_API_WSDL);
        DirectPaymentAPI_Service service = new DirectPaymentAPI_Service(url, new QName("http://impl.ws.payline.experian.com", Utils.DIRECT_PAYMENT_API));
        DirectPaymentAPI port = service.getDirectPaymentAPI();

        setHTTPBasicCredentialAndEndPointFromParams(port, Utils.DIRECT_PAYMENT_API, params);

        return port;
    }

    /**
     * . initServiceExtended method
     * @return ExtendedAPI
     */
    public static ExtendedAPI initServiceExtended() {

        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "Initiating Extended Services from payline.properties file");

        URL url = Utils.class.getClassLoader().getResource(Utils.EXTENDED_API_WSDL);
        ExtendedAPI_Service service = new ExtendedAPI_Service(url, new QName("http://impl.ws.payline.experian.com", Utils.EXTENDED_API));
        ExtendedAPI port = service.getExtendedAPI();

        setHTTPBasicCredentialAndEndPointFromBundle(port, Utils.EXTENDED_API);

        return port;
    }

    /**
     * . initServiceExtended method
     * @return ExtendedAPI
     */
    public static ExtendedAPI initServiceExtended(ConnectParams params) {

        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "Initiating Extended Services from function parameters");

        URL url = Utils.class.getClassLoader().getResource(Utils.EXTENDED_API_WSDL);
        ExtendedAPI_Service service = new ExtendedAPI_Service(url, new QName("http://impl.ws.payline.experian.com", Utils.EXTENDED_API));
        ExtendedAPI port = service.getExtendedAPI();

        setHTTPBasicCredentialAndEndPointFromParams(port, Utils.EXTENDED_API, params);

        return port;
    }

    /**
     * @param merchantId the merchant identifier
     * @param orderRef the order reference
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param accessKey the access key
     * @return String authentication data for getToken servlet
     */
    public static String encrypt(final String merchantId, final String orderRef, final String contractNumber, final String accessKey) {
        String crypted;
        String sep = ";";

        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA-256");
            byte[] aes256Key = sha.digest(accessKey.getBytes("UTF-8"));
            byte[] msgUtf8 = merchantId.concat(sep).concat(orderRef).concat(sep).concat(contractNumber).getBytes("UTF-8");

            SecretKeySpec secretKeySpec = new SecretKeySpec(aes256Key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] ciphered = cipher.doFinal(msgUtf8);

            crypted = Base64.encodeBase64URLSafeString(ciphered);
        } catch (Exception e) {
            crypted = e.toString();
            logger.log(Level.SEVERE, "Unexpected error", e);
        }
        return crypted;
    }

    /**
     * @param encrypt data returned by getToken
     * @param accessKey the access key
     * @return decrypted data
     */
    public static byte[] decrypt(final String accessKey, final String encrypt) {
        byte[] finalDecrypt = new byte[0];
        try {
            byte[] accessKeyBytes = accessKey.getBytes("UTF-8");
            final MessageDigest sha = MessageDigest.getInstance("SHA-256");
            accessKeyBytes = sha.digest(accessKeyBytes);

            final SecretKeySpec secretKeySpec = new SecretKeySpec(accessKeyBytes, "AES");

            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            finalDecrypt = cipher.doFinal(Base64.decodeBase64(encrypt.getBytes("UTF-8")));
        } catch (final Exception e) {
            logger.log(Level.SEVERE, "Unexpected error", e);
        }
        return finalDecrypt;
    }

    /**
     * @param decrypt data
     * @return decompressed String result
     */
    public static String gzipDecompress(final byte[] decrypt) {
        StringBuilder outStr = new StringBuilder();
        try {
            GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(decrypt));
            BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
            String line;
            while ((line = bf.readLine()) != null) {
                outStr.append(line);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error during GZip inflation", e);
        }
        return outStr.toString();
    }
}
