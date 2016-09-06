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

import org.apache.commons.codec.binary.Base64;

import com.payline.ws.model.DirectPaymentAPI;
import com.payline.ws.model.DirectPaymentAPI_Service;
import com.payline.ws.model.ExtendedAPI;
import com.payline.ws.model.ExtendedAPI_Service;
import com.payline.ws.model.WebPaymentAPI;
import com.payline.ws.model.WebPaymentAPI_Service;

/**
 * Utils class.
 * @author payline dev team
 */
public final class Utils {

    /**
     * EndPoints
     */
    public static final String HOMO_ENDPOINT = "https://homologation.payline.com/V4/services/";
    public static final String HOMO_ENDPOINT_CC = "https://homologation-cc.payline.com/V4/services/";
    public static final String PROD_ENDPOINT = "https://services.payline.com/V4/services/";
    public static final String PROD_ENDPOINT_CC = "https://services-cc.payline.com/V4/services/";

    /**
     * Ajax servlet
     */
    public static final String HOMO_GETTOKEN_SERVLET_URL = "https://homologation-webpayment.payline.com/webpayment/getToken";
    public static final String PROD_GETTOKEN_SERVLET_URL = "https://webpayment.payline.com/webpayment/getToken";

    /**
     * API
     */
    public static final String WEB_PAYMENT_API = "WebPaymentAPI";
    public static final String DIRECT_PAYMENT_API = "DirectPaymentAPI";
    public static final String EXTENDED_API = "ExtendedAPI";

    /**
     * WSDL
     */
    public static final String WSDL = "wsdls/v4.47.wsdl";

    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(Utils.class.getName());
    private static final Level LOG_LEVEL = Level.ALL;

    /**
     * Communication error
     */
    public static final String EXCEPTION_CODE = "XXXXX";

    /**
     * kit version
     */
    public static final String kitVersion = "kit JAVA v4.47";

    /**
     * Default constructor.
     */
    private Utils() {
    }

    private static String maskAccessKey(String accessKey) {
        String maskedAccessKey = new String("");
        if (accessKey != null && accessKey != "" && accessKey.length() > 0) {
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
    public static void setHTTPBasicCredentialAndEndPointFromBundle(final Object proxy, final String api) {
        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "setting HTTPBasic Credentials And EndPoint from payline.properties file");

        // Set HTTP Proxy from bundle
        if (PaylineProperties.getString("USE_PROXY") != null && PaylineProperties.getString("USE_PROXY").equals("1")) {
            // Proxy HTTP
            System.setProperty("http.proxySet", "true");
            System.setProperty("http.proxyHost", PaylineProperties.getString("PROXY_HOST"));
            System.setProperty("http.proxyPort", PaylineProperties.getString("PROXY_PORT"));
            // PROXY HTTPS
            System.setProperty("https.proxySet", "true");
            System.setProperty("https.proxyHost", PaylineProperties.getString("PROXY_HOST"));
            System.setProperty("https.proxyPort", PaylineProperties.getString("PROXY_PORT"));

            // Login Proxy
            if (PaylineProperties.getString("PROXY_LOGIN") != null && PaylineProperties.getString("PROXY_LOGIN").length() != 0) {
                System.setProperty("https.proxyLogin", PaylineProperties.getString("PROXY_LOGIN"));
                System.setProperty("http.proxyLogin", PaylineProperties.getString("PROXY_LOGIN"));
            }
            // Password Proxy
            if (PaylineProperties.getString("PROXY_PWD") != null && PaylineProperties.getString("PROXY_PWD").length() != 0) {
                System.setProperty("https.proxyPassword", PaylineProperties.getString("PROXY_PWD"));
                System.setProperty("http.proxyPassword", PaylineProperties.getString("PROXY_PWD"));
            }
        } else {
            // remove proxy parameters to prevent cache side effects
            System.setProperty("http.proxySet", "false");
            System.clearProperty("http.proxyHost");
            System.clearProperty("http.proxyPort");
            System.clearProperty("http.proxyLogin");
            System.clearProperty("http.proxyPassword");
            System.setProperty("https.proxySet", "false");
            System.clearProperty("https.proxyHost");
            System.clearProperty("https.proxyPort");
            System.clearProperty("https.proxyLogin");
            System.clearProperty("https.proxyPassword");
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
        String endpoint = null;
        String prod = PaylineProperties.getString("PRODUCTION");
        if (prod.equalsIgnoreCase("true")) {
            endpoint = Utils.PROD_ENDPOINT;
        } else {
            endpoint = Utils.HOMO_ENDPOINT;
        }
        endpoint = endpoint + api;

        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "endpoint is : " + endpoint);
        if (endpoint != null) {
            ((BindingProvider) proxy).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        }

        // version
        Map<String, List<String>> httpHeaders = new HashMap<String, List<String>>();
        httpHeaders.put("version", Collections.singletonList(Utils.kitVersion));
        Map<String, Object> reqContext = ((BindingProvider) proxy).getRequestContext();
        reqContext.put(MessageContext.HTTP_REQUEST_HEADERS, httpHeaders);
    }

    public static void setHTTPBasicCredentialAndEndPointFromParams(final Object proxy, final String api, ConnectParams params) {
        if (LOG_LEVEL != Level.OFF)
            logger.log(LOG_LEVEL, "setting HTTPBasic Credentials And EndPoint from function parameters");

        // Set proxy
        if (params.getProxyHost() != null) {
            // Proxy HTTP
            System.setProperty("http.proxySet", "true");
            System.setProperty("http.proxyHost", params.getProxyHost());
            System.setProperty("http.proxyPort", params.getProxyPort());

            // PROXY HTTPS
            System.setProperty("https.proxySet", "true");
            System.setProperty("https.proxyHost", params.getProxyHost());
            System.setProperty("https.proxyPort", params.getProxyPort());

            // Login Proxy
            if (params.getProxyLogin() != null) {
                System.setProperty("https.proxyLogin", params.getProxyLogin());
                System.setProperty("http.proxyLogin", params.getProxyLogin());
            }
            // Password Proxy
            if (params.getProxyPassword() != null) {
                System.setProperty("https.proxyPassword", params.getProxyPassword());
                System.setProperty("http.proxyPassword", params.getProxyPassword());
            }
        } else {
        	// remove proxy parameters to prevent cache side effects
            System.setProperty("http.proxySet", "false");
            System.clearProperty("http.proxyHost");
            System.clearProperty("http.proxyPort");
            System.clearProperty("http.proxyLogin");
            System.clearProperty("http.proxyPassword");
            System.setProperty("https.proxySet", "false");
            System.clearProperty("https.proxyHost");
            System.clearProperty("https.proxyPort");
            System.clearProperty("https.proxyLogin");
            System.clearProperty("https.proxyPassword");
        }

        String endpoint = null;
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
        String headerVersion = null;
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

        URL url = Utils.class.getClassLoader().getResource(Utils.WSDL);
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

        URL url = Utils.class.getClassLoader().getResource(Utils.WSDL);
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

        URL url = Utils.class.getClassLoader().getResource(Utils.WSDL);
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

        URL url = Utils.class.getClassLoader().getResource(Utils.WSDL);
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

        URL url = Utils.class.getClassLoader().getResource(Utils.WSDL);
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

        URL url = Utils.class.getClassLoader().getResource(Utils.WSDL);
        ExtendedAPI_Service service = new ExtendedAPI_Service(url, new QName("http://impl.ws.payline.experian.com", Utils.EXTENDED_API));
        ExtendedAPI port = service.getExtendedAPI();

        setHTTPBasicCredentialAndEndPointFromParams(port, Utils.EXTENDED_API, params);

        return port;
    }

    /**
     * @param merchantId
     * @param orderRef the order reference
     * @param contractNumber Payline identifier of your e-commerce contract number
     * @param accessKey
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

            crypted = new String(Base64.encodeBase64URLSafeString(ciphered));
        } catch (Exception e) {
            crypted = e.toString();
            e.printStackTrace();
        }
        return crypted;
    }

    /**
     * @param encrypt : data returned by getToken
     * @param accessKey
     * @return decrypted data
     */
    public static final byte[] decrypt(final String accessKey, final String encrypt) {
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
            e.printStackTrace();
        }
        return finalDecrypt;
    }

    /**
     * @param decrypt
     * @return decompressed String result
     */
    public static String gzipDecompress(final byte[] decrypt) {
        String outStr = "";
        try {
            GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(decrypt));
            BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
            String line;
            while ((line = bf.readLine()) != null) {
                outStr += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(LOG_LEVEL, "unexpected error during GZip inflation");
        }
        return outStr;
    }
}
