package com.payline.ws.wrapper;

import com.payline.kit.utils.Utils;
import com.payline.utils.AbstractWireMockTest;
import com.payline.utils.TestUtils;
import com.payline.ws.model.ContractNumberWalletList;
import com.payline.ws.model.DoWebPaymentResponse;
import com.payline.ws.model.GetWebPaymentDetailsResponse;
import com.payline.ws.model.SelectedContractList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.xml.bind.JAXBException;

import static com.payline.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

class WebPaymentTest extends AbstractWireMockTest {

    @Test
    void webPaymentTestConstructor() {
        final WebPayment actual = new WebPayment();
        assertNotNull(actual);
    }


    @Nested
    class GetWebPaymentDetailsTest {

        final String responseOKFilePath = "webpayment/getWebPaymentDetails/getWebPaymentDetailsResponse00000.xml";
        final String token = "xxxxxtokenxxxxxx";

        @Test
        void getWebPaymentDetailsWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("webpayment/getWebPaymentDetails/getWebPaymentDetailsRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test

                final GetWebPaymentDetailsResponse res = webPayment.getWebPaymentDetails(token, DEFAULT_VERSION);

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
        void getWebPaymentDetailsWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("webpayment/getWebPaymentDetails/getWebPaymentDetailsRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WebPayment WebpaymentFromFile = new WebPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetWebPaymentDetailsResponse res = WebpaymentFromFile.getWebPaymentDetails(token, DEFAULT_VERSION);

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
        void getWebPaymentDetailsShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("webpayment/getWebPaymentDetails/getWebPaymentDetailsResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, true);

                // Test
                final GetWebPaymentDetailsResponse res = webPayment.getWebPaymentDetails(token, DEFAULT_VERSION);

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
    class DoWebPaymentTest {

        final String responseOKFilePath = "webpayment/doWebPayment/doWebPaymentResponse00000.xml";
        final SelectedContractList selectedContractList = createSelectedContractList("firt");
        final SelectedContractList secondSelectedContractList = createSelectedContractList("second");
        final ContractNumberWalletList contractNumberWalletList = createContractNumberWalletList("second");

        @Test
        void doWebPaymentWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("webpayment/doWebPayment/doWebPaymentRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoWebPaymentResponse res = webPayment.doWebPayment(DEFAULT_VERSION, payment, order, buyer, privateDataList, recurring, notificationURL, returnURL, cancelURL, languageCode,
                        securityMode, customPaymentPageCode, selectedContractList, secondSelectedContractList, customPaymentTemplateURL, merchantName, subMerchant, miscData);

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
        void doWebPaymentWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("webpayment/doWebPayment/doWebPaymentRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WebPayment webPaymentFromFile = new WebPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoWebPaymentResponse res = webPaymentFromFile.doWebPayment(DEFAULT_VERSION, payment, order, buyer, privateDataList, recurring, notificationURL, returnURL, cancelURL, languageCode,
                        securityMode, customPaymentPageCode, selectedContractList, secondSelectedContractList, customPaymentTemplateURL, merchantName, subMerchant, miscData);

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
        void doWebPaymentWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("webpayment/doWebPayment/doWebPaymentRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoWebPaymentResponse res = webPayment.doWebPayment(DEFAULT_VERSION, payment, order, buyer, privateDataList, recurring, notificationURL, returnURL, cancelURL, languageCode,
                        securityMode, customPaymentPageCode, selectedContractList, secondSelectedContractList, customPaymentTemplateURL, merchantName, subMerchant, miscData, true,
                        owner, contractNumberWalletList, "async 100", threeDSInfo, "merchantScore 0");

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
        void doWebPaymentShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("webpayment/doWebPayment/doWebPaymentResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, true);

                // Test
                final DoWebPaymentResponse res = webPayment.doWebPayment(DEFAULT_VERSION, payment, order, buyer, privateDataList, recurring, notificationURL, returnURL, cancelURL, languageCode,
                        securityMode, customPaymentPageCode, selectedContractList, secondSelectedContractList, customPaymentTemplateURL, merchantName, subMerchant, miscData);

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