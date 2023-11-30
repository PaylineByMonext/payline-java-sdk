package com.payline.ws.wrapper;

import com.payline.kit.utils.Utils;
import com.payline.utils.AbstractWireMockTest;
import com.payline.utils.TestUtils;
import com.payline.ws.model.GetAlertDetailsResponse;
import com.payline.ws.model.GetTransactionDetailsResponse;
import com.payline.ws.model.TransactionsSearchResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.xml.bind.JAXBException;

import static com.payline.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

class ExtendedAPITest extends AbstractWireMockTest {

    @Test
    void extendedAPITestConstructor() {
        final ExtendedAPI actual = new ExtendedAPI();
        assertNotNull(actual);
    }

    @Nested
    class GetAlertDetailsTest {

        final String responseOKFilePath = "extended/getAlertDetails/getAlertDetailsResponse00000.xml";
        final String alertId = "alertId5";

        @Test
        void getAlertDetailsWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("extended/getAlertDetails/getAlertDetailsRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockExtendedApiServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test

                final GetAlertDetailsResponse res = extendedAPI.getAlertDetails(alertId, transationID, transactionDate, DEFAULT_VERSION);

                // Verif
                assertNotNull(res);
                // Vérification de l'appel Rest avec le token
                verifyWSPostCall(expectedRequest);

                final String responseWithoutEnvelop = deleteSoapEnv(expectedResponse);

                // On vérifie que la reponse XML est celle attendue
                TestUtils.compareXml(responseWithoutEnvelop, marshall(res));
            }
        }

        @Test
        void getAlertDetailsWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("extended/getAlertDetails/getAlertDetailsRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final ExtendedAPI extendedApiFromFile = new ExtendedAPI();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockExtendedApiServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetAlertDetailsResponse res = extendedApiFromFile.getAlertDetails(alertId, transationID, transactionDate, DEFAULT_VERSION);

                // Verif
                assertNotNull(res);
                // Vérification de l'appel Rest avec le token
                verifyWSPostCall(expectedRequest);

                final String responseWithoutEnvelop = deleteSoapEnv(expectedResponse);

                // On vérifie que la reponse XML est celle attendue
                TestUtils.compareXml(responseWithoutEnvelop, marshall(res));
            }
        }

        @Test
        void getAlertDetailsShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("extended/getAlertDetails/getAlertDetailsResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockExtendedApiServiceUrl(utilities, true);

                // Test
                final GetAlertDetailsResponse res = extendedAPI.getAlertDetails(alertId, transationID, transactionDate, DEFAULT_VERSION);

                // Verif
                assertNotNull(res);
                // Verif aucun appel HTTP
                verifyNoCall();

                final String responseWithoutEnvelop = deleteSoapEnv(expectedResponse);

                // On vérifie que la reponse XML est celle attendue
                TestUtils.compareXml(responseWithoutEnvelop, marshall(res));
            }
        }
    }

    @Nested
    class GetTransactionDetailsTest {

        final String responseOKFilePath = "extended/getTransactionDetails/getTransactionDetailsResponse00000.xml";
        final String endDate = "endDate";
        final String transactionHistory = "Y";

        @Test
        void getTransactionDetailsWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("extended/getTransactionDetails/getTransactionDetailsRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockExtendedApiServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test

                final GetTransactionDetailsResponse res = extendedAPI.getTransactionDetails(orderRef, transationID, transactionDate, endDate, transactionHistory, DEFAULT_VERSION);

                // Verif
                assertNotNull(res);
                // Vérification de l'appel Rest avec le token
                verifyWSPostCall(expectedRequest);

                final String responseWithoutEnvelop = deleteSoapEnv(expectedResponse);

                // On vérifie que la reponse XML est celle attendue
                TestUtils.compareXml(responseWithoutEnvelop, marshall(res));
            }
        }

        @Test
        void getTransactionDetailsWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("extended/getTransactionDetails/getTransactionDetailsRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final ExtendedAPI extendedApiFromFile = new ExtendedAPI();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockExtendedApiServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetTransactionDetailsResponse res = extendedApiFromFile.getTransactionDetails(orderRef, transationID, transactionDate, endDate, transactionHistory, DEFAULT_VERSION);

                // Verif
                assertNotNull(res);
                // Vérification de l'appel Rest avec le token
                verifyWSPostCall(expectedRequest);

                final String responseWithoutEnvelop = deleteSoapEnv(expectedResponse);

                // On vérifie que la reponse XML est celle attendue
                TestUtils.compareXml(responseWithoutEnvelop, marshall(res));
            }
        }

        @Test
        void getTransactionDetailsWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("extended/getTransactionDetails/getTransactionDetailsRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockExtendedApiServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test

                final GetTransactionDetailsResponse res = extendedAPI.getTransactionDetails(orderRef, transationID, transactionDate, endDate, transactionHistory, DEFAULT_VERSION, "archiveSearch");

                // Verif
                assertNotNull(res);
                // Vérification de l'appel Rest avec le token
                verifyWSPostCall(expectedRequest);

                final String responseWithoutEnvelop = deleteSoapEnv(expectedResponse);

                // On vérifie que la reponse XML est celle attendue
                TestUtils.compareXml(responseWithoutEnvelop, marshall(res));
            }
        }

        @Test
        void getTransactionDetailsShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("extended/getTransactionDetails/getTransactionDetailsResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockExtendedApiServiceUrl(utilities, true);

                // Test
                final GetTransactionDetailsResponse res = extendedAPI.getTransactionDetails(orderRef, transationID, transactionDate, endDate, transactionHistory, DEFAULT_VERSION);

                // Verif
                assertNotNull(res);
                // Verif aucun appel HTTP
                verifyNoCall();

                final String responseWithoutEnvelop = deleteSoapEnv(expectedResponse);

                // On vérifie que la reponse XML est celle attendue
                TestUtils.compareXml(responseWithoutEnvelop, marshall(res));
            }
        }
    }

    @Nested
    class TransactionsSearchTest {

        final String responseOKFilePath = "extended/transactionsSearch/transactionsSearchResponse00000.xml";
        final String endDate = "endDate";
        final String authorizationNumber = "authorizationNumber";
        final String returnCode = "0000";
        final String cardType = "XX";
        final String transactionType = "06";
        final String buyerName = "name 6";
        final String email = "email 8";
        final String buyerFName = "fname 7";
        final String cardNumber = "4970100000000";
        final String currency = "978";
        final String minAmount = "200";
        final String maxAmount = "4000";
        final String walletID = "walIdp";
        final String seqNumber = "seqNum";
        final String posId = "pos44";
        final String cardNetwork = "CB";
        final String threeDSecure = "3DS";
        final String customerMediaId = "media8";

        @Test
        void transactionsSearchWithParams() throws JAXBException {

            final String expectedRequest = fileToString("extended/transactionsSearch/transactionsSearchRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockExtendedApiServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test

                final TransactionsSearchResponse res = extendedAPI.transactionsSearch(orderRef, transationID, transactionDate, endDate, contractNumber, authorizationNumber, returnCode, cardType,
                transactionType, buyerName, email, buyerFName, cardNumber, currency, minAmount, maxAmount, walletID, seqNumber, DEFAULT_VERSION, posId, cardNetwork, threeDSecure, customerMediaId);

                // Verif
                assertNotNull(res);
                // Vérification de l'appel Rest avec le token
                verifyWSPostCall(expectedRequest);

                final String responseWithoutEnvelop = deleteSoapEnv(expectedResponse);

                // On vérifie que la reponse XML est celle attendue
                TestUtils.compareXml(responseWithoutEnvelop, marshall(res));
            }
        }

        @Test
        void transactionsSearchWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("extended/transactionsSearch/transactionsSearchRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final ExtendedAPI extendedApiFromFile = new ExtendedAPI();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockExtendedApiServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final TransactionsSearchResponse res = extendedApiFromFile.transactionsSearch(orderRef, transationID, transactionDate, endDate, contractNumber, authorizationNumber, returnCode, cardType,
                        transactionType, buyerName, email, buyerFName, cardNumber, currency, minAmount, maxAmount, walletID, seqNumber, DEFAULT_VERSION, posId, cardNetwork, threeDSecure, customerMediaId);

                // Verif
                assertNotNull(res);
                // Vérification de l'appel Rest avec le token
                verifyWSPostCall(expectedRequest);

                final String responseWithoutEnvelop = deleteSoapEnv(expectedResponse);

                // On vérifie que la reponse XML est celle attendue
                TestUtils.compareXml(responseWithoutEnvelop, marshall(res));
            }
        }

        @Test
        void transactionsSearchShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("extended/transactionsSearch/transactionsSearchResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockExtendedApiServiceUrl(utilities, true);

                // Test
                final TransactionsSearchResponse res = extendedAPI.transactionsSearch(orderRef, transationID, transactionDate, endDate, contractNumber, authorizationNumber, returnCode, cardType,
                        transactionType, buyerName, email, buyerFName, cardNumber, currency, minAmount, maxAmount, walletID, seqNumber, DEFAULT_VERSION, posId, cardNetwork, threeDSecure, customerMediaId);

                // Verif
                assertNotNull(res);
                // Verif aucun appel HTTP
                verifyNoCall();

                final String responseWithoutEnvelop = deleteSoapEnv(expectedResponse);

                // On vérifie que la reponse XML est celle attendue
                TestUtils.compareXml(responseWithoutEnvelop, marshall(res));
            }
        }
    }

}