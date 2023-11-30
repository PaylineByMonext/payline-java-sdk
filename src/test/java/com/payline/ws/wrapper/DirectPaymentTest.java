package com.payline.ws.wrapper;

import com.payline.kit.utils.Utils;
import com.payline.utils.AbstractWireMockTest;
import com.payline.utils.TestUtils;
import com.payline.ws.model.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.xml.bind.JAXBException;

import static com.payline.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

class DirectPaymentTest extends AbstractWireMockTest {

    @Test
    void directPaymentTestConstructor() {
        final DirectPayment actual = new DirectPayment();
        assertNotNull(actual);
    }

    @Nested
    class DoAuthorizationTest {

        final String responseOKFilePath = "directpayment/doAuthor/doAuthorizationResponse00000.xml";

        @Test
        void doAuthorizationWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doAuthor/doAuthorizationRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoAuthorizationResponse res = directPayment.doAuthorization(payment, order, buyer, card, privateDataList, authentication3DSecure, bankAccountData, DEFAULT_VERSION, subMerchant);

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
        void doAuthorizationWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doAuthor/doAuthorizationRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final DirectPayment directPaymentFromFile = new DirectPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoAuthorizationResponse res = directPaymentFromFile.doAuthorization(payment, order, buyer, card, privateDataList, authentication3DSecure, bankAccountData, DEFAULT_VERSION, subMerchant);

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
        void doAuthorizationWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doAuthor/doAuthorizationRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoAuthorizationResponse res = directPayment.doAuthorization(payment, order, buyer, card, privateDataList, authentication3DSecure, bankAccountData,
                        DEFAULT_VERSION, subMerchant, transientParam, owner, media, asynchronousRetryTimeout, linkedTransactionId, recurring, threeDSInfo, travelFileNumber);

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
        void doAuthorizationWithFullParams03022() throws JAXBException {

            final String response03022FilePath = "directpayment/doAuthor/doAuthorizationResponse03022.xml";
            final String expectedRequest = fileToString("directpayment/doAuthor/doAuthorizationRequestFull.xml");
            final String expectedResponse = fileToString(response03022FilePath);

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, response03022FilePath);

                // Test
                final DoAuthorizationResponse res = directPayment.doAuthorization(payment, order, buyer, card, privateDataList, authentication3DSecure, bankAccountData,
                        DEFAULT_VERSION, subMerchant, transientParam, owner, media, asynchronousRetryTimeout, linkedTransactionId, recurring, threeDSInfo, travelFileNumber);

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
        void doAuthorizationShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/doAuthor/doAuthorizationResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoAuthorizationResponse res = directPayment.doAuthorization(payment, order, buyer, card, privateDataList, authentication3DSecure, bankAccountData,
                        DEFAULT_VERSION, subMerchant, transientParam, owner, media, asynchronousRetryTimeout, linkedTransactionId, recurring, threeDSInfo, travelFileNumber);

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
    class DoDebitTest {

        final String responseOKFilePath = "directpayment/doDebit/doDebitResponse00000.xml";

        @Test
        void doDebitWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doDebit/doDebitRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoDebitResponse res = directPayment.doDebit(payment, order, buyer, card, privateDataList, authentication3DSecure, authorization, DEFAULT_VERSION, subMerchant);

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
        void doDebitWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doDebit/doDebitRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoDebitResponse res = directPayment.doDebit(payment, order, buyer, card, privateDataList, authentication3DSecure, authorization, DEFAULT_VERSION, subMerchant, owner, media, miscData);

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
        void doDebitShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/doDebit/doDebitResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoDebitResponse res = directPayment.doDebit(payment, order, buyer, card, privateDataList, authentication3DSecure, authorization, DEFAULT_VERSION, subMerchant, owner, media, miscData);

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
    class DoBankTransferTest {
        @Test
        void doBankTransferWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doBankTransfer/doBankTransferRequest.xml");
            final String expectedResponse = fileToString("directpayment/doBankTransfer/doBankTransferResponse00000.xml"); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, "directpayment/doBankTransfer/doBankTransferResponse00000.xml");

                // Test
                final DoBankTransferResponse res = directPayment.doBankTransfer(payment, creditor, transationID, orderid, comment, DEFAULT_VERSION, privateDataList);

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
        void doBankTransferShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/doBankTransfer/doBankTransferResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoBankTransferResponse res = directPayment.doBankTransfer(payment, creditor, transationID, orderid, comment, DEFAULT_VERSION, privateDataList);

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
    class DoCaptureTest {

        final String responseOKFilePath = "directpayment/doCapture/doCaptureResponse00000.xml";

        @Test
        void doCaptureWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doCapture/doCaptureRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final DoCaptureResponse res = directPayment.doCapture(payment, transationID, privateDataList, sequenceNumber, DEFAULT_VERSION);

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
        void doCaptureWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doCapture/doCaptureRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final DoCaptureResponse res = directPayment.doCapture(payment, transationID, privateDataList, sequenceNumber, DEFAULT_VERSION, media, miscData);

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
        void doCaptureShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/doCapture/doCaptureResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoCaptureResponse res = directPayment.doCapture(payment, transationID, privateDataList, sequenceNumber, DEFAULT_VERSION, media, miscData);

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
    class DoRefundTest {

        final String responseOKFilePath = "directpayment/doRefund/doRefundResponse00000.xml";

        @Test
        void doRefundWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doRefund/doRefundRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final DoRefundResponse res = directPayment.doRefund(payment, transationID, comment, privateDataList, sequenceNumber, DEFAULT_VERSION, order);

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
        void doRefundWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doRefund/doRefundRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final DoRefundResponse res = directPayment.doRefund(payment, transationID, comment, privateDataList, sequenceNumber, DEFAULT_VERSION, order, media, miscData);

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
        void doRefundShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/doRefund/doRefundResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoRefundResponse res = directPayment.doRefund(payment, transationID, comment, privateDataList, sequenceNumber, DEFAULT_VERSION, order, media, miscData);

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
    class DoCreditTest {

        final String responseOKFilePath = "directpayment/doCredit/doCreditResponse00000.xml";

        @Test
        void doCreditWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doCredit/doCreditRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final DoCreditResponse res = directPayment.doCredit(payment, card, comment, buyer, order, privateDataList, DEFAULT_VERSION, subMerchant);

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
        void doCreditWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doCredit/doCreditRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final DoCreditResponse res = directPayment.doCredit(payment, card, comment, buyer, order, privateDataList, DEFAULT_VERSION, subMerchant, owner, media, miscData);

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
        void doCreditShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/doCredit/doCreditResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoCreditResponse res = directPayment.doCredit(payment, card, comment, buyer, order, privateDataList, DEFAULT_VERSION, subMerchant, owner, media, miscData);

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
    class VerifyEnrollmentTest {

        final String responseOKFilePath = "directpayment/verifyEnrollment/verifyEnrollmentResponse00000.xml";

        @Test
        void verifyEnrollmentWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/verifyEnrollment/verifyEnrollmentRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final VerifyEnrollmentResponse res = directPayment.verifyEnrollment(card, payment, orderRef, userAgent, DEFAULT_VERSION);

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
        void verifyEnrollmentWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/verifyEnrollment/verifyEnrollmentRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final VerifyEnrollmentResponse res = directPayment.verifyEnrollment(card, payment, orderRef, "walletId888", "5", userAgent, DEFAULT_VERSION, "merchantName",
                        "merchantURL", "merchantCountryCode", "mdFieldValue", "generateVirtualCvx", "returnURL",
                        order, buyer, subMerchant, recurring, threeDSInfo, "merchantScore", "transientParam", privateDataList);

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
        void verifyEnrollmentShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/verifyEnrollment/verifyEnrollmentResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final VerifyEnrollmentResponse res = directPayment.verifyEnrollment(card, payment, orderRef, "walletId888", "5", userAgent, DEFAULT_VERSION, "merchantName",
                        "merchantURL", "merchantCountryCode", "mdFieldValue", "generateVirtualCvx", "returnURL",
                        order, buyer, subMerchant, recurring, threeDSInfo, "merchantScore", "transientParam", privateDataList);

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
    class DoResetTest {

        final String responseOKFilePath = "directpayment/doReset/doResetResponse00000.xml";

        @Test
        void doResetWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doReset/doResetRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final DoResetResponse res = directPayment.doReset(transationID, comment, DEFAULT_VERSION, payment.getAmount(), payment.getCurrency(), privateDataList);

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
        void doResetWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doReset/doResetRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final DoResetResponse res = directPayment.doReset(transationID, comment, DEFAULT_VERSION, payment.getAmount(), payment.getCurrency(), privateDataList, sequenceNumber, media);

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
        void doResetShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/doReset/doResetResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoResetResponse res = directPayment.doReset(transationID, comment, DEFAULT_VERSION, payment.getAmount(), payment.getCurrency(), privateDataList, sequenceNumber, media);

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
    class GetBalanceTest {

        final String responseOKFilePath = "directpayment/getBalance/getBalanceResponse00000.xml";

        @Test
        void getBalance() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/getBalance/getBalanceRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final GetBalanceResponse res = directPayment.getBalance(cardId, contractNumber, DEFAULT_VERSION);

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
        void getBalanceShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/getBalance/getBalanceResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final GetBalanceResponse res = directPayment.getBalance(cardId, contractNumber, DEFAULT_VERSION);

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
    class GetEncryptionTest {

        final String responseOKFilePath = "directpayment/getEncryptionKey/getEncryptionKeyResponse00000.xml";

        @Test
        void getEncryptionKey() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/getEncryptionKey/getEncryptionKeyRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final GetEncryptionKeyResponse res = directPayment.getEncryptionKey(DEFAULT_VERSION);

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
        void getEncryptionKeyShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/getEncryptionKey/getEncryptionKeyResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final GetEncryptionKeyResponse res = directPayment.getEncryptionKey();

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
    class GetMerchantSettingsTest {

        final String responseOKFilePath = "directpayment/getMerchantSettings/getMerchantSettingsResponse00000.xml";

        @Test
        void getMerchantSettings() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/getMerchantSettings/getMerchantSettingsRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final GetMerchantSettingsResponse res = directPayment.getMerchantSettings(DEFAULT_VERSION);

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
        void getMerchantSettingsShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/getMerchantSettings/getMerchantSettingsResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final GetMerchantSettingsResponse res = directPayment.getMerchantSettings(DEFAULT_VERSION);

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
    class GetTokenTest {

        final String responseOKFilePath = "directpayment/getToken/getTokenResponse02500.xml";

        @Test
        void getToken() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/getToken/getTokenRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final GetTokenResponse res = directPayment.getToken(card.getNumber(), card.getExpirationDate(), contractNumber);

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
        void getTokenShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/getToken/getTokenResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final GetTokenResponse res = directPayment.getToken(card.getNumber(), card.getExpirationDate(), contractNumber);

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
    class UnBlockTest {

        final String responseOKFilePath = "directpayment/unBlock/unBlockResponse00000.xml";

        @Test
        void unBlock() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/unBlock/unBlockRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final UnBlockResponse res = directPayment.unBlock(transationID, transactionDate, DEFAULT_VERSION);

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
        void unBlockShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/unBlock/unBlockResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final UnBlockResponse res = directPayment.unBlock(transationID, transactionDate, DEFAULT_VERSION);

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
    class DoScoringChequeTest {

        final String responseOKFilePath = "directpayment/doScoringCheque/doScoringChequeResponse00000.xml";

        @Test
        void doScoringChequeWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doScoringCheque/doScoringChequeRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final DoScoringChequeResponse res = directPayment.doScoringCheque(cheque, order, payment, privateDataList, DEFAULT_VERSION, media);

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
        void doScoringChequeWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doScoringCheque/doScoringChequeRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final DoScoringChequeResponse res = directPayment.doScoringCheque(cheque, order, payment, privateDataList, DEFAULT_VERSION, media);

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
        void doScoringChequeShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/doScoringCheque/doScoringChequeResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoScoringChequeResponse res = directPayment.doScoringCheque(cheque, order, payment, privateDataList, DEFAULT_VERSION, media);

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
    class DoReAuthorizationTest {

        final String responseOKFilePath = "directpayment/doReAuthor/doReAuthorizationResponse00000.xml";

        @Test
        void doReAuthorizationWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doReAuthor/doReAuthorizationRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoReAuthorizationResponse res = directPayment.doReAuthorization(transationID, order, payment, privateDataList, DEFAULT_VERSION);

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
        void doReAuthorizationWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doReAuthor/doReAuthorizationRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final DirectPayment directPaymentFromFile = new DirectPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoReAuthorizationResponse res = directPaymentFromFile.doReAuthorization(transationID, order, payment, privateDataList, DEFAULT_VERSION);

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
        void doReAuthorizationWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/doReAuthor/doReAuthorizationRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoReAuthorizationResponse res = directPayment.doReAuthorization(transationID, order, payment, privateDataList, DEFAULT_VERSION, media);

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
        void doReAuthorizationShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/doReAuthor/doReAuthorizationResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoReAuthorizationResponse res = directPayment.doReAuthorization(transationID, order, payment, privateDataList, DEFAULT_VERSION, media);

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
    class VerifyAuthenticationTest {

        final String responseOKFilePath = "directpayment/verifyAuthentication/verifyAuthenticationResponse03000.xml";

        @Test
        void verifyAuthenticationWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/verifyAuthentication/verifyAuthenticationRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath);

                // Test
                final VerifyAuthenticationResponse res = directPayment.verifyAuthentication(contractNumber, "pares xxx", "md yyy", card, DEFAULT_VERSION, "transientParam ooo", privateDataList);

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
        void verifyAuthenticationShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/verifyAuthentication/verifyAuthenticationResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final VerifyAuthenticationResponse res = directPayment.verifyAuthentication(contractNumber, "pares xxx", "md yyy", card, DEFAULT_VERSION, "transientParam ooo", privateDataList);

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
    class IsRegisteredResponseTest {

        final String responseOKFilePath = "directpayment/isRegistered/isRegisteredResponse00000.xml";

        @Test
        void isRegisteredWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/isRegistered/isRegisteredRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final IsRegisteredResponse res = directPayment.isRegistered(buyer, order, payment, privateDataList, DEFAULT_VERSION);

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
        void isRegisteredWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/isRegistered/isRegisteredRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final DirectPayment directPaymentFromFile = new DirectPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final IsRegisteredResponse res = directPaymentFromFile.isRegistered(buyer, order, payment, privateDataList, DEFAULT_VERSION);

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
        void isRegisteredWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/isRegistered/isRegisteredRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final IsRegisteredResponse res = directPayment.isRegistered(buyer, order, payment, privateDataList, DEFAULT_VERSION, miscData);

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
        void isRegisteredShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/isRegistered/isRegisteredResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final IsRegisteredResponse res = directPayment.isRegistered(buyer, order, payment, privateDataList, DEFAULT_VERSION);

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
    class PrepareSessionResponseTest {

        final String responseOKFilePath = "directpayment/prepareSession/prepareSessionResponse00000.xml";

        @Test
        void prepareSessionWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/prepareSession/prepareSessionRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final PrepareSessionResponse res = directPayment.prepareSession(DEFAULT_VERSION, contractNumber, orderRef, miscData);

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
        void prepareSessionWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("directpayment/prepareSession/prepareSessionRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final DirectPayment directPaymentFromFile = new DirectPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final PrepareSessionResponse res = directPayment.prepareSession(DEFAULT_VERSION, contractNumber, orderRef, miscData);

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
        void prepareSessionShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("directpayment/prepareSession/prepareSessionResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final PrepareSessionResponse res = directPayment.prepareSession(DEFAULT_VERSION, contractNumber, orderRef, miscData);

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