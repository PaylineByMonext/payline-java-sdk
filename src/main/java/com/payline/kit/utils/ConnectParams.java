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
 * ConnectParams class Object containing credentials, proxy parameters and target environement in order to create a
 * DirectPayment/ExtendedAPI/WalletPayment/WebPayment instance
 * @author payline dev team
 */
public class ConnectParams {

    /**
     * production, flag to indicate whether web services calls should be done in production environment
     */
    private static boolean production;

    /**
     * clientAuthentication, This attribute specifies if client authentication should be required. Returns whether client authentication is required. true, if
     * clients are required to authenticate, false otherwise.
     */
    private static boolean clientAuthentication;

    /**
     * module, the module to connect
     */
    private static String module = null;

    /**
     * merchantId the merchant identifier being used
     */
    private static String merchantId;

    /**
     * accessKey, the access key for this credentials object The unique identifier access key of the connection in security domain
     */
    private static String accessKey;

    /**
     * proxyHost, The hostname, or address, of the proxy server
     */
    private static String proxyHost = null;

    /**
     * proxyPort, The port number of the proxy server.
     */
    private static String proxyPort = null;

    /**
     * proxyLogin, the proxy login should use for retrieving Connections
     */
    private static String proxyLogin = null;

    /**
     * proxyPassword, the proxy password that should use for retrieving Connections
     */
    private static String proxyPassword = null;

    /**
     * Class constructor specifying the connection properties to create
     * @param module
     * @param production
     * @param clientAuthentication
     * @param merchantId
     * @param accessKey
     * @param proxyHost
     * @param proxyPort
     * @param proxyLogin
     * @param proxyPassword
     */
    public ConnectParams(String module, boolean production, boolean clientAuthentication, String merchantId, String accessKey, String proxyHost,
        String proxyPort, String proxyLogin, String proxyPassword) {
        if (module != null && module.length() != 0) {
            ConnectParams.module = module;
        }
        ConnectParams.production = production;
        ConnectParams.clientAuthentication = clientAuthentication;
        ConnectParams.merchantId = merchantId;
        ConnectParams.accessKey = accessKey;
        if (proxyHost != null && proxyHost.length() != 0) {
            ConnectParams.proxyHost = proxyHost;
        }
        if (proxyPort != null && proxyPort.length() != 0) {
            ConnectParams.proxyPort = proxyPort;
        }
        if (proxyLogin != null && proxyLogin.length() != 0) {
            ConnectParams.proxyLogin = proxyLogin;
        }
        if (proxyPassword != null && proxyPassword.length() != 0) {
            ConnectParams.proxyPassword = proxyPassword;
        }
    }

    /**
     * The Constructor specifying the connection properties to create
     * @param module
     * @param production
     * @param clientAuthentication
     * @param merchantId
     * @param accessKey
     */
    public ConnectParams(String module, boolean production, boolean clientAuthentication, String merchantId, String accessKey) {
        if (module != null && module.length() != 0) {
            ConnectParams.module = module;
        }
        ConnectParams.production = production;
        ConnectParams.clientAuthentication = clientAuthentication;
        ConnectParams.merchantId = merchantId;
        ConnectParams.accessKey = accessKey;
    }

    /**
     * Returns the module to connect
     * @return module
     */
    public String getModule() {
        return module;
    }

    /**
     * Returns the flag to indicate whether web services calls should be done in production environment
     * @return production
     */
    public boolean isProduction() {
        return production;
    }

    /**
     * Returns whether client authentication is required. true, if clients are required to authenticate, false otherwise.
     * @return clientAuthentication
     */
    public boolean isClientAuthentication() {
        return clientAuthentication;
    }

    /**
     * Return the access key for this credentials object
     * @return merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * Set the access key for this credentials object
     * @return accessKey,
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     * Return the proxy host for HTTP connection
     * @return proxyHost
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * Return the port number on the proxy host to use
     * @return proxyPort
     */
    public String getProxyPort() {
        return proxyPort;
    }

    /**
     * Return the proxy login should use for retrieving Connections
     * @return proxyLogin
     */
    public String getProxyLogin() {
        return proxyLogin;
    }

    /**
     * Return the proxy password that should use for retrieving Connections
     * @return proxyPassword
     */
    public String getProxyPassword() {
        return proxyPassword;
    }
}
