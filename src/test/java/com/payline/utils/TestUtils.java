package com.payline.utils;

import com.payline.kit.utils.ConnectParams;
import com.payline.ws.model.*;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestUtils {

    public static final String KEYSTORE_PASSWORD = "password";
    public static final String DEFAULT_MERCHANT_ID = "111222333";
    public static final String DEFAULT_ACCESS_KEY = "xxx888xxx999xxx";
    public static final String DEFAULT_VERSION = "99";
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36";


    /**
     * Fonction qui pemret de setter les variables système pour le truststore et keystore
     * @return le path du keystore
     */
    public static String trustStoreSetter() {
        final String keystorePath = Objects.requireNonNull(TestUtils.class.getClassLoader().getResource("keystore_localhost.jks")).getPath();
        final String keystorePassword = KEYSTORE_PASSWORD;

        System.setProperty("javax.net.ssl.trustStore", keystorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", keystorePassword);
        System.setProperty("javax.net.ssl.keyStore", keystorePath);
        System.setProperty("javax.net.ssl.keyStorePassword", keystorePassword);

        return keystorePath;
    }


    public static ConnectParams createDefaultConnectParams() {
        return new ConnectParams(
                null, /* module */
                false, /* production */
                false, /* clientAuthentication */
                DEFAULT_MERCHANT_ID, /* Your merchant account login: merchantId */
                DEFAULT_ACCESS_KEY, /* Your access key to the Payline service: accessKey */
                null, /* proxyHost (optional) */
                null, /* proxyPort (optional) */
                null, /* proxyLogin (optional) */
                null /* proxyPassword (optional) */
        );
    }

    public static Address createAddress(final String suffix) {
        final Address address = new Address();
        address.setCityName("Aix");
        address.setCountry("FR");
        address.setPhone("0600000000 + suffix");
        address.setStreet1("123 rue de nowhere + suffix");
        address.setStreet2("Street 2 + suffix");
        address.setZipCode("11000 + suffix");
        address.setAddressCreateDate("00/00/0000");
        address.setEmail("toto@fait.dubateau + suffix");
        address.setFirstName("First yeah" + suffix);
        address.setLastName("Last oups" + suffix);
        address.setPhone("000000000" + suffix);
        address.setPhoneType("Nokia3310" + suffix);
        address.setTitle("Maitre de lunivers");
        return address;
    }

    public static AddressOwner createAddressOwner() {
        final AddressOwner address = new AddressOwner();
        address.setCityName("Aix");
        address.setCountry("FR");
        address.setPhone("0600000000");
        address.setStreet("123 rue de nowhere");
        address.setZipCode("11000");
        return address;
    }

    public static Authentication3DSecure createAuthentication3DSecure() {
        final Authentication3DSecure secure = new Authentication3DSecure();
        secure.setCavv("aaaaaaaaaaaaaaa");
        secure.setEci("05");
        secure.setMd("Md2123454657897");
        secure.setXid("xidzzzzz");
        secure.setPares("Pares");
        secure.setPaResStatus("ParesStatus9");
        return secure;
    }

    public static Authorization createAuthorization() {
        final Authorization authorization = new Authorization();
        authorization.setAuthorizedAmount("100");
        authorization.setAuthorizedCurrency("978");
        authorization.setNumber("999888777");
        authorization.setDate("15/12/2014 12:20");
        return authorization;
    }

    public static BankAccountData createBankAccountData() {
        final BankAccountData bank = new BankAccountData();
        bank.setKey("bankKey");
        bank.setBankCode("BankCode");
        bank.setAccountNumber("123456789");
        bank.setCountryCode("FR");
        return bank;
    }

    public static BillingRecordForUpdate createBillingRecordForUpdate() {
        final BillingRecordForUpdate billingRecordForUpdate = new BillingRecordForUpdate();
        billingRecordForUpdate.setAmount("100");
        billingRecordForUpdate.setStatus("OK");
        billingRecordForUpdate.setExecutionDate("20/20/2020");
        billingRecordForUpdate.setDate("10/10/1010");
        return billingRecordForUpdate;
    }

    public static Browser createBrowser() {
        final Browser browser = new Browser();
        browser.setAcceptHeader("headers");
        browser.setLanguage("FR");
        browser.setUserAgent(USER_AGENT);
        browser.setColorDepth("ColorDepth");
        browser.setJavaEnabled("javaTrue");
        browser.setJavascriptEnabled("jsTrus");
        browser.setScreenHeight("500");
        browser.setScreenWidth("400");
        browser.setTimeZoneOffset("GTM+5");
        return browser;
    }

    public static Buyer createBuyer() {
        final Buyer buyer = new Buyer();
        buyer.setEmail("toto@nowhere.fr");
        buyer.setFirstName("firstnameyyy");
        buyer.setLastName("lastnamexxx");
        buyer.setBuyerExtended("BuyerExt");
        buyer.setBillingAddress(createAddress(" billing"));
        buyer.setShippingAdress(createAddress(" shipping"));
        buyer.setAccountAverageAmount("1");
        buyer.setAccountCreateDate("22/22/2222");
        buyer.setAccountOrderCount("9");
        buyer.setDeviceFingerprint("print");
        buyer.setFingerprintID("aaa-aaa-aaa");
        buyer.setBirthDate("01/01/1900");
        buyer.setIsBehindProxy("proxy false");
        buyer.setIsBot("bot false");
        buyer.setIsEmulator("emul false");
        buyer.setIsFromTor("thor false");
        buyer.setIsRooted("root false");
        buyer.setIsIncognito("incognito false");
        buyer.setHasTimezoneMismatch("jesaispas");
        buyer.setCustomerId("yoo");
        buyer.setLegalDocument("le papier");
        buyer.setLegalStatus("Pacs");
        buyer.setLegalDocumentType("Papier");
        return buyer;
    }

    public static Card createCard() {
        final Card card = new Card();
        card.setCardPresent("Y");
        card.setCardholder("Jean Claude");
        card.setCvx("123");
        card.setNumber("4970101122334455");
        card.setExpirationDate("1255");
        card.setType("CB");
        return card;
    }

    public static Cheque createCheque() {
        final Cheque cheque = new Cheque();
        cheque.setNumber("cheque4970101122334455");
        return cheque;
    }

    public static Creditor createCreditor() {
        final Creditor creditor = new Creditor();
        creditor.setBic("BIC123");
        creditor.setIban("IBAN123456789");
        creditor.setName("Creditor Name");
        return creditor;
    }

    public static Order createOrder() {
        final Order order = new Order();
        order.setAmount("100");
        order.setCurrency("978");
        order.setDate("15/12/2014 12:20");
        order.setRef("Refxxx999");
        order.setOrderDetail("OrderDetails 9999");
        order.setBookingReference("BookingRef ppp");
        order.setDeliveryCharge("DeliveryChareg 8");
        order.setDeliveryMode("Par camion");
        order.setDeliveryTime("entre 6h et 22h");
        order.setDeliveryExpectedDelay("200");
        order.setDeliveryExpectedDate("Demain");
        order.setOrigin("BIGBANG");
        order.setTaxes("50%");
        order.setOrderOTA("OrderOTA supp");
        order.setOtaDestinationCountry("GB");
        order.setOtaPackageType("Valise");
        return order;
    }

    public static Owner createOwner() {
        final Owner owner = new Owner();
        owner.setFirstName("Owner firstname");
        owner.setLastName("Owner LastName");
        owner.setBillingAddress(createAddressOwner());
        owner.setIssueCardDate("15/12/2020 10:00");
        return owner;
    }

    public static Payment createPayment() {
        final Payment payment = new Payment();
        payment.setAmount("100");
        payment.setCurrency("978");
        payment.setContractNumber("CB");
        payment.setMode("CPT");
        payment.setAction("100");
        payment.setCardBrand("VISA-CB-MC");
        payment.setCumulatedAmount("0");
        payment.setMethod("Method");
        payment.setDifferedActionDate("DiffActDate 11/11/2011");
        payment.setSoftDescriptor("SoftDescriptor");
        return payment;
    }

    public static PrivateDataList createPrivateDataList() {
        final PrivateData privateData = new PrivateData();
        privateData.setKey("pvDataKey");
        privateData.setValue("pvDataValue");
        final PrivateDataList privateDataList = new PrivateDataList();
        privateDataList.getPrivateData().add(privateData);
        return privateDataList;
    }

    public static Recurring createRecurring() {
        final Recurring recurring = new Recurring();
        recurring.setAmount("100");
        recurring.setBillingCycle("12");
        recurring.setBillingDay("5");
        recurring.setBillingLeft("20");
        recurring.setFirstAmount("30");
        recurring.setStartDate("15/01/2020 10:00");
        return recurring;
    }

    public static RecurringForUpdate createRecurringForUpdate() {
        final RecurringForUpdate recurring = new RecurringForUpdate();
        recurring.setBillingDay("5");
        recurring.setBillingLeft("20");
        recurring.setEndDate("99/99/9999");
        recurring.setAmountModificationDate("today 00/00/0000");
        recurring.setNewAmount("10000");
        return recurring;
    }


    public static Sdk createSdk() {
        final Sdk sdk = new Sdk();
        sdk.setAppID("AppId9");
        sdk.setMaxTimeout("10");
        sdk.setTransID("trsIdSDK");
        return sdk;
    }

    public static SelectedContractList createSelectedContractList(final String type) {
        final SelectedContractList list = new SelectedContractList();
        list.getSelectedContract().add("CB " + type);
        list.getSelectedContract().add("APPLE_PAY " + type);
        return list;
    }

    public static ContractNumberWalletList createContractNumberWalletList(final String type) {
        final ContractNumberWalletList list = new ContractNumberWalletList();
        list.getContractNumberWallet().add("CB " + type);
        return list;
    }

    public static SubMerchant createSubMerchant() {
        final SubMerchant subMerchant = new SubMerchant();
        subMerchant.setSubMerchantCity("Aix");
        subMerchant.setSubMerchantCountry("Fr");
        subMerchant.setSubMerchantName("Monext");
        subMerchant.setSubMerchantStreet("123 rue des petits poids");
        return subMerchant;
    }

    public static ThreeDSInfo createThreeDSInfo() {
        final ThreeDSInfo threeDSInfo = new ThreeDSInfo();
        final Browser browser = createBrowser();
        final Sdk sdk = createSdk();
        threeDSInfo.setBrowser(browser);
        threeDSInfo.setSdk(sdk);
        threeDSInfo.setChallengeInd("1");
        threeDSInfo.setThreeDSMethodResult("3dsMethodResult");
        threeDSInfo.setChallengeWindowSize("500px");
        threeDSInfo.setThreeDSReqPriorAuthData("ThreeDSReqPriorAuthData");
        threeDSInfo.setThreeDSMethodNotificationURL("ThreeDSMethodNotificationURL");
        threeDSInfo.setThreeDSReqPriorAuthMethod("ThreeDSReqPriorAuthMethod");
        threeDSInfo.setThreeDSReqPriorAuthTimestamp("ThreeDSReqPriorAuthTimestamp");
        return threeDSInfo;
    }

    public static Wallet createWallet() {
        final Wallet wallet = new Wallet();
        wallet.setWalletId("1");
        wallet.setCard(createCard());
        wallet.setCardBrand("VISA");
        wallet.setEmail("aaaaa@nowhere.fr");
        wallet.setFirstName("first wallet");
        wallet.setLastName("last wallet");
        wallet.setCardStatus("OK");
        wallet.setComment("comment wallet");
        wallet.setDefault("Y");
        wallet.setShippingAddress(createAddress("wallet"));
        return wallet;
    }

    public static WalletIdList createWalletIdList() {
        final WalletIdList list = new WalletIdList();
        list.getWalletId().add("WalletId1");
        return list;
    }

    /**
     * Fonction qui permet de lire un fichier dans les resources de test et de renvoyer le contenu sous forme de string.
     * @param filePath Le pathdu fichier
     * @return le contenu du fichier
     */
    public static String fileToString(String filePath) {
        try {
            return Files.readString(Path.of(Objects.requireNonNull(TestUtils.class.getClassLoader().getResource(filePath)).toURI()));
        } catch (final URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Fonction qui permet de comparer 2 xml et d'afficher les différences.
     */
    public static  void compareXml(final String xmlControl, final String xmlResult) {
        // Comparaison des XML
        final Diff xmlDiff = DiffBuilder
                .compare(xmlControl)
                .withTest(xmlResult)
                .ignoreWhitespace()
                .ignoreElementContentWhitespace()
                .checkForSimilar()
                // On ne compare pas les namespace de la réponse. Ca ne sert à rien (c'est les namespace de la request qui sont importants)
                .withDifferenceEvaluator(DifferenceEvaluators.chain(DifferenceEvaluators.ignorePrologDifferences(),
                        DifferenceEvaluators.downgradeDifferencesToSimilar(ComparisonType.NAMESPACE_PREFIX),
                        DifferenceEvaluators.downgradeDifferencesToSimilar(ComparisonType.NAMESPACE_URI),
                        DifferenceEvaluators.downgradeDifferencesToSimilar(ComparisonType.NO_NAMESPACE_SCHEMA_LOCATION),
                        DifferenceEvaluators.downgradeDifferencesToSimilar(ComparisonType.XML_VERSION),
                        DifferenceEvaluators.downgradeDifferencesToSimilar(ComparisonType.XML_VERSION),
                        DifferenceEvaluators.downgradeDifferencesToSimilar(ComparisonType.XML_ENCODING),
                        new XmlCustomDifferenceEvaluator()))
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText)) // Options qui compare les balises indépendamment de l'ordre
                .build();

        assertFalse(xmlDiff.hasDifferences(), xmlDiff.fullDescription());
    }

    public static String marshall(Object obj) throws JAXBException {
        final StringWriter sw = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(obj, sw);
        return sw.toString();
    }


    /**
     * Fonction qui permet de supprimer l'envelop du docuement XML
     */
    public static String deleteSoapEnv(final String responseXML) {
        final int indexOfBodyStart = responseXML.indexOf("<soapenv:Body>") ;
        final int indexOfBodyEnd = responseXML.indexOf("</soapenv:Body>") ;
        return responseXML.substring(indexOfBodyStart + 14, indexOfBodyEnd);
    }

}
