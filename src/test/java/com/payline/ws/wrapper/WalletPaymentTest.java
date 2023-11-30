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

class WalletPaymentTest extends AbstractWireMockTest {

    final ContractNumberWalletList contractNumberWalletList = createContractNumberWalletList("create");
    final WalletIdList walletIdList = createWalletIdList();
    final Wallet wallet = createWallet();
    final String walletId = "5";
    final String cardind = "1";
    final String paymentRecordId = "paymentRecordId 5";
    final String billingRecordId = "billingRecordId 6";
    final BillingRecordForUpdate billingRecordForUpdate = createBillingRecordForUpdate();

    @Test
    void walletPaymentTestConstructor() {
        final WalletPayment actual = new WalletPayment();
        assertNotNull(actual);
    }

    @Nested
    class CreateWalletTest {

        final String responseOKFilePath = "walletpayment/createWallet/createWalletResponse02500.xml";


        @Test
        void createWalletWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/createWallet/createWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final CreateWalletResponse res = walletPayment.createWallet(wallet, contractNumber, privateDataList, authentication3DSecure, DEFAULT_VERSION);

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
        void createWalletWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/createWallet/createWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final CreateWalletResponse res = walletPaymentFromFile.createWallet(wallet, contractNumber, privateDataList, authentication3DSecure, DEFAULT_VERSION);

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
        void createWalletWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/createWallet/createWalletRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final CreateWalletResponse res = walletPayment.createWallet(wallet, contractNumber, privateDataList, authentication3DSecure, DEFAULT_VERSION);

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
        void createWalletShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/createWallet/createWalletResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final CreateWalletResponse res = walletPayment.createWallet(wallet, contractNumber, privateDataList, authentication3DSecure, DEFAULT_VERSION, buyer, owner,
                        media, contractNumberWalletList, transationID);

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
    class GetWalletTest {

        final String responseOKFilePath = "walletpayment/getWallet/getWalletResponse02500.xml";

        @Test
        void getWalletWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getWallet/getWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetWalletResponse res = walletPayment.getWallet(walletId, contractNumber, cardind, DEFAULT_VERSION);

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
        void getWalletWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getWallet/getWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetWalletResponse res = walletPaymentFromFile.getWallet(walletId, contractNumber, cardind, DEFAULT_VERSION);

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
        void getWalletWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getWallet/getWalletRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetWalletResponse res = walletPayment.getWallet(walletId, contractNumber, cardind, DEFAULT_VERSION, media);

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
        void getWalletShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/getWallet/getWalletResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final GetWalletResponse res = walletPayment.getWallet(walletId, contractNumber, cardind, DEFAULT_VERSION, media);

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
    class updateWalletTest {

        final String responseOKFilePath = "walletpayment/updateWallet/updateWalletResponse02500.xml";

        @Test
        void updateWalletWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/updateWallet/updateWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final UpdateWalletResponse res = walletPayment.updateWallet(wallet, contractNumber, privateDataList, authentication3DSecure, cardind, DEFAULT_VERSION);

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
        void updateWalletWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/updateWallet/updateWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final UpdateWalletResponse res = walletPaymentFromFile.updateWallet(wallet, contractNumber, privateDataList, authentication3DSecure, cardind, DEFAULT_VERSION);

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
        void updateWalletWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/updateWallet/updateWalletRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final UpdateWalletResponse res = walletPayment.updateWallet(wallet, contractNumber, privateDataList, authentication3DSecure, cardind, DEFAULT_VERSION, buyer, owner, media,
                        contractNumberWalletList, transationID);

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
        void updateWalletShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/updateWallet/updateWalletResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final UpdateWalletResponse res = walletPayment.updateWallet(wallet, contractNumber, privateDataList, authentication3DSecure, cardind, DEFAULT_VERSION);

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
    class DisableWalletTest {

        final String responseOKFilePath = "walletpayment/disableWallet/disableWalletResponse02500.xml";

        @Test
        void disableWalletWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/disableWallet/disableWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DisableWalletResponse res = walletPayment.disableWallet(walletIdList, contractNumber, cardind);

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
        void disableWalletWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/disableWallet/disableWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DisableWalletResponse res = walletPaymentFromFile.disableWallet(walletIdList, contractNumber, cardind);

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
        void disableWalletShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/disableWallet/disableWalletResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DisableWalletResponse res = walletPayment.disableWallet(walletIdList, contractNumber, cardind);

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
    class EnableWalletTest {

        final String responseOKFilePath = "walletpayment/enableWallet/enableWalletResponse02500.xml";

        @Test
        void enableWalletWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/enableWallet/enableWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final EnableWalletResponse res = walletPayment.enableWallet(walletId, contractNumber, cardind);

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
        void enableWalletWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/enableWallet/enableWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final EnableWalletResponse res = walletPaymentFromFile.enableWallet(walletId, contractNumber, cardind);

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
        void enableWalletShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/enableWallet/enableWalletResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final EnableWalletResponse res = walletPayment.enableWallet(walletId, contractNumber, cardind);

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
    class doImmediateWalletPaymentTest {

        final String responseOKFilePath = "walletpayment/doImmediateWalletPayment/doImmediateWalletPaymentResponse00000.xml";
        final String cvx = "123";
        @Test
        void doImmediateWalletPaymentWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/doImmediateWalletPayment/doImmediateWalletPaymentRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoImmediateWalletPaymentResponse res = walletPayment.doImmediateWalletPayment(payment, order, buyer, privateDataList, walletId, cardind, cvx, DEFAULT_VERSION);

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
        void doImmediateWalletPaymentWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/doImmediateWalletPayment/doImmediateWalletPaymentRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoImmediateWalletPaymentResponse res = walletPaymentFromFile.doImmediateWalletPayment(payment, order, buyer, privateDataList, walletId, cardind, cvx, DEFAULT_VERSION);

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
        void doImmediateWalletPaymentWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/doImmediateWalletPayment/doImmediateWalletPaymentRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoImmediateWalletPaymentResponse res = walletPayment.doImmediateWalletPayment(payment, order, buyer, privateDataList, walletId, cardind, cvx, authentication3DSecure,
                        DEFAULT_VERSION, subMerchant, recurring, linkedTransactionId, media, threeDSInfo, travelFileNumber);

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
        void doImmediateWalletPaymentShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/doImmediateWalletPayment/doImmediateWalletPaymentResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoImmediateWalletPaymentResponse res = walletPayment.doImmediateWalletPayment(payment, order, buyer, privateDataList, walletId, cardind, cvx, DEFAULT_VERSION);

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
    class doScheduledWalletPaymentTest {

        final String responseOKFilePath = "walletpayment/doScheduledWalletPayment/doScheduledWalletPaymentResponse02500.xml";
        final String scheduledDate = "01/01/1990";

        @Test
        void doScheduledWalletPaymentWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/doScheduledWalletPayment/doScheduledWalletPaymentRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoScheduledWalletPaymentResponse res = walletPayment.doScheduledWalletPayment(payment, transactionDate, orderRef, scheduledDate, walletId, order, privateDataList, cardind, DEFAULT_VERSION, subMerchant,
                        recurring, authentication3DSecure, linkedTransactionId);

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
        void doScheduledWalletPaymentWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/doScheduledWalletPayment/doScheduledWalletPaymentRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoScheduledWalletPaymentResponse res = walletPaymentFromFile.doScheduledWalletPayment(payment, transactionDate, orderRef, scheduledDate, walletId, order, privateDataList, cardind, DEFAULT_VERSION, subMerchant,
                        recurring, authentication3DSecure, linkedTransactionId);

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
        void doScheduledWalletPaymentShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/doScheduledWalletPayment/doScheduledWalletPaymentResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoScheduledWalletPaymentResponse res = walletPayment.doScheduledWalletPayment(payment, transactionDate, orderRef, scheduledDate, walletId, order, privateDataList, cardind, DEFAULT_VERSION, subMerchant,
                        recurring, authentication3DSecure, linkedTransactionId);

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
    class doRecurrentWalletPaymentTest {

        final String responseOKFilePath = "walletpayment/doRecurrentWalletPayment/doRecurrentWalletPaymentResponse02500.xml";
        final String cvx = "123";
        @Test
        void doRecurrentWalletPaymentWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/doRecurrentWalletPayment/doRecurrentWalletPaymentRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoRecurrentWalletPaymentResponse res = walletPayment.doRecurrentWalletPayment(payment, recurring, transactionDate, orderRef, walletId, order, privateDataList, cardind, DEFAULT_VERSION,
                        cvx, linkedTransactionId, authentication3DSecure);

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
        void doRecurrentWalletPaymentWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/doRecurrentWalletPayment/doRecurrentWalletPaymentRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoRecurrentWalletPaymentResponse res = walletPaymentFromFile.doRecurrentWalletPayment(payment, recurring, transactionDate, orderRef, walletId, order, privateDataList, cardind, DEFAULT_VERSION,
                        cvx, linkedTransactionId, authentication3DSecure);

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
        void doRecurrentWalletPaymentWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/doRecurrentWalletPayment/doRecurrentWalletPaymentRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DoRecurrentWalletPaymentResponse res = walletPayment.doRecurrentWalletPayment(payment, recurring, transactionDate, orderRef, walletId, order, privateDataList, cardind, DEFAULT_VERSION,
                        cvx, linkedTransactionId, authentication3DSecure, media);

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
        void doRecurrentWalletPaymentShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/doRecurrentWalletPayment/doRecurrentWalletPaymentResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DoRecurrentWalletPaymentResponse res = walletPayment.doRecurrentWalletPayment(payment, recurring, transactionDate, orderRef, walletId, order, privateDataList, cardind, DEFAULT_VERSION,
                        cvx, linkedTransactionId, authentication3DSecure, media);

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
    class GetPaymentRecordTest {

        final String responseOKFilePath = "walletpayment/getPaymentRecord/getPaymentRecordResponse02500.xml";

        @Test
        void getPaymentRecordWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getPaymentRecord/getPaymentRecordRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetPaymentRecordResponse res = walletPayment.getPaymentRecord(contractNumber, paymentRecordId, DEFAULT_VERSION);

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
        void getPaymentRecordWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getPaymentRecord/getPaymentRecordRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetPaymentRecordResponse res = walletPaymentFromFile.getPaymentRecord(contractNumber, paymentRecordId, DEFAULT_VERSION);

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
        void getPaymentRecordShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/getPaymentRecord/getPaymentRecordResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final GetPaymentRecordResponse res = walletPayment.getPaymentRecord(contractNumber, paymentRecordId, DEFAULT_VERSION);

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
    class updatePaymentRecordTest {

        final String responseOKFilePath = "walletpayment/updatePaymentRecord/updatePaymentRecordResponse02500.xml";
        final RecurringForUpdate recurringForUpdate = createRecurringForUpdate();
        @Test
        void updatePaymentRecordWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/updatePaymentRecord/updatePaymentRecordRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final UpdatePaymentRecordResponse res = walletPayment.updatePaymentRecord(DEFAULT_VERSION, contractNumber, paymentRecordId, recurringForUpdate);

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
        void updatePaymentRecordWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/updatePaymentRecord/updatePaymentRecordRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final UpdatePaymentRecordResponse res = walletPaymentFromFile.updatePaymentRecord(DEFAULT_VERSION, contractNumber, paymentRecordId, recurringForUpdate);

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
        void updatePaymentRecordShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/updatePaymentRecord/updatePaymentRecordResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final UpdatePaymentRecordResponse res = walletPayment.updatePaymentRecord(DEFAULT_VERSION, contractNumber, paymentRecordId, recurringForUpdate);

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
    class disablePaymentRecordTest {

        final String responseOKFilePath = "walletpayment/disablePaymentRecord/disablePaymentRecordResponse02500.xml";
        final String recordId = "recordId 123";
        @Test
        void disablePaymentRecordWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/disablePaymentRecord/disablePaymentRecordRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DisablePaymentRecordResponse res = walletPayment.disablePaymentRecord(contractNumber, recordId);

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
        void disablePaymentRecordWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/disablePaymentRecord/disablePaymentRecordRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final DisablePaymentRecordResponse res = walletPaymentFromFile.disablePaymentRecord(contractNumber, recordId);

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
        void disablePaymentRecordShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/disablePaymentRecord/disablePaymentRecordResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final DisablePaymentRecordResponse res = walletPayment.disablePaymentRecord(contractNumber, recordId);

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
    class getBillingRecordTest {

        final String responseOKFilePath = "walletpayment/getBillingRecord/getBillingRecordResponse02500.xml";

        @Test
        void getBillingRecordWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getBillingRecord/getBillingRecordRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetBillingRecordResponse res = walletPayment.getBillingRecord(contractNumber, paymentRecordId, billingRecordId);

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
        void getBillingRecordWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getBillingRecord/getBillingRecordRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetBillingRecordResponse res = walletPaymentFromFile.getBillingRecord(contractNumber, paymentRecordId, billingRecordId);

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
        void getBillingRecordShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/getBillingRecord/getBillingRecordResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final GetBillingRecordResponse res = walletPayment.getBillingRecord(contractNumber, paymentRecordId, billingRecordId);

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
    class updateBillingRecordTest {

        final String responseOKFilePath = "walletpayment/updateBillingRecord/updateBillingRecordResponse02500.xml";

        @Test
        void updateBillingRecordWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/updateBillingRecord/updateBillingRecordRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final UpdateBillingRecordResponse res = walletPayment.updateBillingRecord(contractNumber, paymentRecordId, billingRecordId, billingRecordForUpdate);

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
        void updateBillingRecordWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/updateBillingRecord/updateBillingRecordRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final UpdateBillingRecordResponse res = walletPaymentFromFile.updateBillingRecord(contractNumber, paymentRecordId, billingRecordId, billingRecordForUpdate);

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
        void updateBillingRecordShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/updateBillingRecord/updateBillingRecordResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final UpdateBillingRecordResponse res = walletPayment.updateBillingRecord(contractNumber, paymentRecordId, billingRecordId, billingRecordForUpdate);

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
    class CreateWebWalletTest {

        final String responseOKFilePath = "walletpayment/createWebWallet/createWebWalletResponse00000.xml";
        final String updatePersonalDetails = "updatePersonalDetails 789";
        final SelectedContractList selectedContractList = createSelectedContractList("wallet");


        @Test
        void createWebWalletWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/createWebWallet/createWebWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final CreateWebWalletResponse res = walletPayment.createWebWallet(buyer, privateDataList, notificationURL, returnURL, cancelURL, languageCode, securityMode, customPaymentPageCode,
                        customPaymentTemplateURL, selectedContractList, updatePersonalDetails, DEFAULT_VERSION);

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
        void createWebWalletWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/createWebWallet/createWebWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final CreateWebWalletResponse res = walletPaymentFromFile.createWebWallet(buyer, privateDataList, notificationURL, returnURL, cancelURL, languageCode, securityMode, customPaymentPageCode,
                        customPaymentTemplateURL, selectedContractList, updatePersonalDetails, DEFAULT_VERSION);

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
        void createWebWalletWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/createWebWallet/createWebWalletRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final CreateWebWalletResponse res = walletPayment.createWebWallet(buyer, privateDataList, notificationURL, returnURL, cancelURL, languageCode, securityMode, customPaymentPageCode,
                        customPaymentTemplateURL, selectedContractList, updatePersonalDetails, DEFAULT_VERSION, contractNumber, owner, contractNumberWalletList);

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
        void createWebWalletShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/createWebWallet/createWebWalletResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, true);

                // Test
                final CreateWebWalletResponse res = walletPayment.createWebWallet(buyer, privateDataList, notificationURL, returnURL, cancelURL, languageCode, securityMode, customPaymentPageCode,
                        customPaymentTemplateURL, selectedContractList, updatePersonalDetails, DEFAULT_VERSION);

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
    class UpdateWebWalletTest {

        final String responseOKFilePath = "walletpayment/updateWebWallet/updateWebWalletResponse00000.xml";
        final String updatePersonalDetails = "updatePersonalDetails 789";
        final String updatePaymentDetails = "updatePaymentDetails 789";
        final String updateOwnerDetails = "updateOwnerDetails 789";
        final SelectedContractList selectedContractList = createSelectedContractList("wallet");


        @Test
        void updateWebWalletWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/updateWebWallet/updateWebWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final UpdateWebWalletResponse res = walletPayment.updateWebWallet(walletId, privateDataList, notificationURL, returnURL, cancelURL, languageCode, securityMode, customPaymentPageCode,
                        customPaymentTemplateURL, selectedContractList, updatePersonalDetails, updatePaymentDetails, buyer, cardind, DEFAULT_VERSION);

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
        void updateWebWalletWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/updateWebWallet/updateWebWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final UpdateWebWalletResponse res = walletPaymentFromFile.updateWebWallet(walletId, privateDataList, notificationURL, returnURL, cancelURL, languageCode, securityMode, customPaymentPageCode,
                        customPaymentTemplateURL, selectedContractList, updatePersonalDetails, updatePaymentDetails, buyer, cardind, DEFAULT_VERSION);

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
        void updateWebWalletWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/updateWebWallet/updateWebWalletRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final UpdateWebWalletResponse res = walletPayment.updateWebWallet(walletId, privateDataList, notificationURL, returnURL, cancelURL, languageCode, securityMode, customPaymentPageCode,
                        customPaymentTemplateURL, selectedContractList, updatePersonalDetails, updatePaymentDetails, buyer, cardind, DEFAULT_VERSION, updateOwnerDetails, contractNumberWalletList, contractNumber);

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
        void updateWebWalletShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/updateWebWallet/updateWebWalletResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, true);

                // Test
                final UpdateWebWalletResponse res = walletPayment.updateWebWallet(walletId, privateDataList, notificationURL, returnURL, cancelURL, languageCode, securityMode, customPaymentPageCode,
                        customPaymentTemplateURL, selectedContractList, updatePersonalDetails, updatePaymentDetails, buyer, cardind, DEFAULT_VERSION);

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
    class ManageWebWalletTest {

        final String responseOKFilePath = "walletpayment/manageWebWallet/manageWebWalletResponse00000.xml";
        final String updatePersonalDetails = "updatePersonalDetails 789";
        final SelectedContractList selectedContractList = createSelectedContractList("wallet");

        @Test
        void manageWebWalletWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/manageWebWallet/manageWebWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final ManageWebWalletResponse res = walletPayment.manageWebWallet(DEFAULT_VERSION, contractNumber, selectedContractList, updatePersonalDetails, buyer, owner,
                        languageCode, customPaymentPageCode, securityMode, returnURL, cancelURL, notificationURL, privateDataList, customPaymentTemplateURL, contractNumberWalletList, merchantName);

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
        void manageWebWalletWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/manageWebWallet/manageWebWalletRequestMinimal.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final ManageWebWalletResponse res = walletPayment.manageWebWallet(DEFAULT_VERSION, contractNumber, selectedContractList, updatePersonalDetails, buyer, owner,
                        languageCode, customPaymentPageCode, securityMode, returnURL, cancelURL, notificationURL, privateDataList, customPaymentTemplateURL, contractNumberWalletList, merchantName);

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
        void manageWebWalletWithFullParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/manageWebWallet/manageWebWalletRequestFull.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final ManageWebWalletResponse res = walletPayment.manageWebWallet(DEFAULT_VERSION, contractNumber, selectedContractList, updatePersonalDetails, buyer, owner, languageCode,
                        customPaymentPageCode, securityMode, returnURL, cancelURL, notificationURL, privateDataList, customPaymentTemplateURL, contractNumberWalletList, merchantName, threeDSInfo);

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
        void manageWebWalletShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/manageWebWallet/manageWebWalletResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, true);

                // Test
                final ManageWebWalletResponse res = walletPayment.manageWebWallet(DEFAULT_VERSION, contractNumber, selectedContractList, updatePersonalDetails, buyer, owner,
                        languageCode, customPaymentPageCode, securityMode, returnURL, cancelURL, notificationURL, privateDataList, customPaymentTemplateURL, contractNumberWalletList, merchantName);

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
    class getWebWalletTest {

        final String responseOKFilePath = "walletpayment/getWebWallet/getWebWalletResponse02500.xml";
        final String token = "xxxx-zzzz-yyyy";

        @Test
        void getWebWalletWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getWebWallet/getWebWalletRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetWebWalletResponse res = walletPayment.getWebWallet(token, DEFAULT_VERSION);

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
        void getWebWalletWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getWebWallet/getWebWalletRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetWebWalletResponse res = walletPaymentFromFile.getWebWallet(token, DEFAULT_VERSION);

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
        void getWebWalletShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/getWebWallet/getWebWalletResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockWebPaymentServiceUrl(utilities, true);

                // Test
                final GetWebWalletResponse res = walletPayment.getWebWallet(token, DEFAULT_VERSION);

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
    class getCardsTest {

        final String responseOKFilePath = "walletpayment/getCards/getCardsResponse02500.xml";

        @Test
        void getCardsWithMinimalParams() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getCards/getCardsRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetCardsResponse res = walletPayment.getCards(contractNumber, walletId, cardind, DEFAULT_VERSION);

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
        void getCardsWithMinimalParamsInitFromFile() throws JAXBException {

            final String expectedRequest = fileToString("walletpayment/getCards/getCardsRequest.xml");
            final String expectedResponse = fileToString(responseOKFilePath); // On va checker que la réponse correspond bien à la réponse renvoyé par le WS SOAP

            final WalletPayment walletPaymentFromFile = new WalletPayment();

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, false);
                // Stub de l'appel HTTP
                stubWSPostCall(200, responseOKFilePath );

                // Test
                final GetCardsResponse res = walletPayment.getCards(contractNumber, walletId, cardind, DEFAULT_VERSION);

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
        void getCardsShouldReturnError() throws JAXBException {

            final String expectedResponse = fileToString("walletpayment/getCards/getCardsResponseInternalError.xml");

            // Mock
            try (MockedStatic<Utils> utilities = mockStatic(Utils.class)) {
                mockDirectPaymentAPIServiceUrl(utilities, true);

                // Test
                final GetCardsResponse res = walletPayment.getCards(contractNumber, walletId, cardind, DEFAULT_VERSION);

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