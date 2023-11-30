package com.payline.kit.utils;

import com.payline.ws.model.WebPaymentAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UtilsTest {

    @BeforeEach
    void setUp() {
    }

    @Nested
    class InitServiceWebTest {
        @Test
        void shouldInitWebWebWithConnectParamsWithProxy() {
            // Data
            final ConnectParams params = new ConnectParams("6", false, true, "111222333", "accesskey", "proxyHost",
                    "proxyPort", "proxyLogin", "proxyPassword");

            // Test
            final WebPaymentAPI webPaymentAPI = Utils.initServiceWeb(params);

            // Verif
            assertNotNull(webPaymentAPI);
            final Map<String, Object> requestContext = ((BindingProvider) webPaymentAPI).getRequestContext();
            assertNull(requestContext.get(BindingProvider.USERNAME_PROPERTY));
            assertNull(requestContext.get(BindingProvider.PASSWORD_PROPERTY));
            assertEquals("https://homologation-cc.payline.com/V4/services/WebPaymentAPI", requestContext.get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
            assertEquals("6", ((Map<String, List<String>>) requestContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("version").get(0));
            assertEquals("true", System.getProperty("http.proxySet"));
            assertEquals("proxyHost", System.getProperty("http.proxyHost"));
            assertEquals("proxyPort", System.getProperty("http.proxyPort"));
            assertEquals("proxyLogin", System.getProperty("http.proxyLogin"));
            assertEquals("proxyPassword", System.getProperty("https.proxyPassword"));
            assertEquals("true", System.getProperty("https.proxySet"));
            assertEquals("proxyHost", System.getProperty("https.proxyHost"));
            assertEquals("proxyPort", System.getProperty("https.proxyPort"));
            assertEquals("proxyLogin", System.getProperty("https.proxyLogin"));
            assertEquals("proxyPassword", System.getProperty("https.proxyPassword"));
        }

        @Test
        void shouldInitWebWebWithConnectParamsWithoutProxy() {
            // Data
            final ConnectParams params = new ConnectParams("6", false, true, "111222333", "accesskey");

            // Test
            final WebPaymentAPI webPaymentAPI = Utils.initServiceWeb(params);

            // Verif
            assertNotNull(webPaymentAPI);
            final Map<String, Object> requestContext = ((BindingProvider) webPaymentAPI).getRequestContext();
            assertNull(requestContext.get(BindingProvider.USERNAME_PROPERTY));
            assertNull(requestContext.get(BindingProvider.PASSWORD_PROPERTY));
            assertEquals("https://homologation-cc.payline.com/V4/services/WebPaymentAPI", requestContext.get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
            assertEquals("6", ((Map<String, List<String>>) requestContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("version").get(0));
            assertEquals("false", System.getProperty("http.proxySet"));
            assertNull(System.getProperty("http.proxyHost"));
            assertNull(System.getProperty("http.proxyPort"));
            assertNull(System.getProperty("http.proxyLogin"));
            assertNull(System.getProperty("https.proxyPassword"));
            assertEquals("false", System.getProperty("https.proxySet"));
            assertNull(System.getProperty("https.proxyHost"));
            assertNull(System.getProperty("https.proxyPort"));
            assertNull(System.getProperty("https.proxyLogin"));
            assertNull(System.getProperty("https.proxyPassword"));
        }

        @Test
        void shouldInitWebWebWithBundle() {
            // Test
            final WebPaymentAPI webPaymentAPI = Utils.initServiceWeb();

            // Verif
            assertNotNull(webPaymentAPI);
            final Map<String, Object> requestContext = ((BindingProvider) webPaymentAPI).getRequestContext();
            assertEquals("111222333", requestContext.get(BindingProvider.USERNAME_PROPERTY));
            assertEquals("xxx000xxx", requestContext.get(BindingProvider.PASSWORD_PROPERTY)); // Données dans payline.properties
            assertEquals("https://homologation.payline.com/V4/services/WebPaymentAPI", requestContext.get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
            assertEquals("kit JAVA v4.76", ((Map<String, List<String>>) requestContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("version").get(0));
            assertEquals("false", System.getProperty("http.proxySet"));
            assertNull(System.getProperty("http.proxyHost"));
            assertNull(System.getProperty("http.proxyPort"));
            assertNull(System.getProperty("http.proxyLogin"));
            assertNull(System.getProperty("https.proxyPassword"));
            assertEquals("false", System.getProperty("https.proxySet"));
            assertNull(System.getProperty("https.proxyHost"));
            assertNull(System.getProperty("https.proxyPort"));
            assertNull(System.getProperty("https.proxyLogin"));
            assertNull(System.getProperty("https.proxyPassword"));
        }

    }
}