package com.payline.utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.payline.kit.utils.ConnectParams;
import com.payline.kit.utils.Utils;
import com.payline.ws.model.*;
import com.payline.ws.wrapper.DirectPayment;
import com.payline.ws.wrapper.WalletPayment;
import com.payline.ws.wrapper.WebPayment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.ProtocolException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.payline.utils.TestUtils.*;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractWireMockTest {
    public static final String PATH_URL_SOAP_SERVICES = "/V4/services/";

    public final ConnectParams params = createDefaultConnectParams();
    public final DirectPayment directPayment = new DirectPayment(params);
    public final WalletPayment walletPayment = new WalletPayment(params);
    public final WebPayment webPayment = new WebPayment(params);
    public final com.payline.ws.wrapper.ExtendedAPI extendedAPI = new com.payline.ws.wrapper.ExtendedAPI(params);

    public final String asynchronousRetryTimeout = "10";
    public final Authentication3DSecure authentication3DSecure = createAuthentication3DSecure();
    public final Authorization authorization = createAuthorization();
    public final BankAccountData bankAccountData = createBankAccountData();
    public final Buyer buyer = createBuyer();
    public final Card card = createCard();
    public final Cheque cheque = createCheque();
    public final String cardId = "cardId";
    public final String comment = "Commentbbbb";
    public final String contractNumber = "contractNumber";
    public final Creditor creditor = createCreditor();
    public final String linkedTransactionId = "LinkedTrsIdyyyy";
    public final String media = "media pc";
    public final String miscData = "miscData value";
    public final String orderid = "OrderIdaaaa";
    public final String orderRef = "OrderRefbbbb";
    public final Order order = createOrder();
    public final Owner owner = createOwner();
    public final Payment payment = createPayment();
    public final Recurring recurring = createRecurring();
    public final PrivateDataList privateDataList = createPrivateDataList();
    public final String sequenceNumber = "sequence number 111";
    public final SubMerchant subMerchant = createSubMerchant();
    public final String transientParam = "transientParam 222";
    public final String transationID = "TrsIdyyyy";
    public final String transactionDate = "15/01/2020 10:00";
    public final String travelFileNumber = "TravelNumberyyyy";
    public final ThreeDSInfo threeDSInfo = createThreeDSInfo();
    public final String userAgent = USER_AGENT;
    public final String merchantName = "Balthazar Picsou";

    public final String notificationURL = "http://notif.url";
    public final String returnURL = "http://return.url";
    public final String cancelURL = "http://cancel.url";
    public final String languageCode = "FR_fr";
    public final String securityMode = "XX";
    public final String customPaymentPageCode = "PloufXX";
    public final String customPaymentTemplateURL = "PloufXXUrl";

    protected static WireMockServer wireMockServer;

    @BeforeAll
    static void beforeAll() {
        if (wireMockServer == null) {

            final String keystorePath = trustStoreSetter();

            wireMockServer = new WireMockServer(options()
                    .bindAddress("localhost")
                    .dynamicPort()
                    .dynamicHttpsPort()
                    .keystorePath(keystorePath)
                    .keystorePassword(KEYSTORE_PASSWORD)
            );
        }
        wireMockServer.start();
        configureFor("http", "localhost", wireMockServer.port());
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }

    @AfterEach
    void after() {
        wireMockServer.resetAll();
    }

    /**
     * Fonction qui retourne le host pour wiremock
     * https://localhost:" + wireMockServer.httpsPort()
     */
    protected String getDefaultHostUrl() {
        return "https://localhost:" + wireMockServer.httpsPort() + PATH_URL_SOAP_SERVICES;
    }


    /**
     * Fonction qui permet de mocker le service {@link DirectPaymentAPI} en modifiant l'url du enpoint
     */
    protected void mockDirectPaymentAPIServiceUrl(final MockedStatic<Utils> utilities, final boolean shouldReturnException) {
        utilities.when(Utils::initServiceDirect)
                .thenAnswer(invocationOnMock -> mockEndpointUrlForDirectPayment(invocationOnMock, shouldReturnException));

        utilities.when(() -> Utils.initServiceDirect(params))
                .thenAnswer(invocationOnMock -> mockEndpointUrlForDirectPayment(invocationOnMock, shouldReturnException));
    }
    private DirectPaymentAPI mockEndpointUrlForDirectPayment(final InvocationOnMock invocationOnMock, final boolean shouldReturnException) throws Throwable {

        if (shouldReturnException) {
            throw new ProtocolException("Erreur lors de l'appel au service DirectPaymentAPI");
        }

        // Appel de la méthode
        final DirectPaymentAPI object = (DirectPaymentAPI) invocationOnMock.callRealMethod();

        // On change le endpoint à la volet et on renvoit un SPY
        ((BindingProvider) object).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getDefaultHostUrl());
        return spy(object);
    }

    /**
     * ---
     * Fonction qui permet de mocker le service {@link ExtendedAPI} en modifiant l'url du enpoint
     */
    protected void mockExtendedApiServiceUrl(final MockedStatic<Utils> utilities, final boolean shouldReturnException) {
        utilities.when(Utils::initServiceExtended)
                .thenAnswer(invocationOnMock -> mockEndpointUrlExtendedApi(invocationOnMock, shouldReturnException));

        utilities.when(() -> Utils.initServiceExtended(params))
                .thenAnswer(invocationOnMock -> mockEndpointUrlExtendedApi(invocationOnMock, shouldReturnException));
    }
    private ExtendedAPI mockEndpointUrlExtendedApi(final InvocationOnMock invocationOnMock, final boolean shouldReturnException) throws Throwable {

        if (shouldReturnException) {
            throw new ProtocolException("Erreur lors de l'appel au service ExtendedAPI");
        }

        // Appel de la méthode
        final ExtendedAPI object = (ExtendedAPI) invocationOnMock.callRealMethod();

        // On change le endpoint à la volet et on renvoit un SPY
        ((BindingProvider) object).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getDefaultHostUrl());
        return spy(object);
    }

    /**
     * ---
     * Fonction qui permet de mocker le service {@link ExtendedAPI} en modifiant l'url du enpoint
     */
    protected void mockWebPaymentServiceUrl(final MockedStatic<Utils> utilities, final boolean shouldReturnException) {
        utilities.when(Utils::initServiceWeb)
                .thenAnswer(invocationOnMock -> mockEndpointUrlWebPayment(invocationOnMock, shouldReturnException));

        utilities.when(() -> Utils.initServiceWeb(params))
                .thenAnswer(invocationOnMock -> mockEndpointUrlWebPayment(invocationOnMock, shouldReturnException));
    }
    private WebPaymentAPI mockEndpointUrlWebPayment(final InvocationOnMock invocationOnMock, final boolean shouldReturnException) throws Throwable {

        if (shouldReturnException) {
            throw new ProtocolException("Erreur lors de l'appel au service WebPayment");
        }

        // Appel de la méthode
        final WebPaymentAPI object = (WebPaymentAPI) invocationOnMock.callRealMethod();

        // On change le endpoint à la volet et on renvoit un SPY
        ((BindingProvider) object).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getDefaultHostUrl());
        return spy(object);
    }


    /**
     * Fonction qui permet de mocker les appels SOAP
     * @param status => Le status à renvoyer
     * @param responseFilePath Le path vers le fichier XML de réponse
     */
    protected void stubWSPostCall(final int status, final String responseFilePath) {
        stubFor(post(urlMatching(PATH_URL_SOAP_SERVICES))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withBody(fileToString(responseFilePath))
                )
        );
    }

    /**
     * Fonction qui permet de vérifier que l'appel HTTP SOAP a bien eu lieu pour les autres services REST
     */
    protected void verifyWSPostCall(final String body) {
        verify(postRequestedFor(urlEqualTo(PATH_URL_SOAP_SERVICES))
                .withRequestBody(equalToXml(body, false))
        );
    }

    /**
     * Fonction qui permet de vérifier qu'aucun appel HTTP n'a été effectué.
     */
    protected void verifyNoCall() {
        verify(0, postRequestedFor(urlEqualTo(PATH_URL_SOAP_SERVICES)));
    }

}
