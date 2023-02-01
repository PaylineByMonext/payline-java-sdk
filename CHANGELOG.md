----------------------------------------

* 4.73 (2023-01-25)
  * Update WSDL from 4.72 to 4.73

----------------------------------------

* 4.72 (2022-09-09)
  * Update WSDL from 4.71 to 4.72
  * add miscData in doCapture
  * add miscData in doRefund
  * add legalDocumentType on buyer object

----------------------------------------

* 4.71 (2022-06-16)
  * Update WSDL from 4.70 to 4.71
  * changer parameter type for sdk/maxTimeout

----------------------------------------

* 4.70 (2022-05-04)
  * Update WSDL from 4.69 to 4.70
  * new parameter encData for sdk

----------------------------------------

* 4.69 (2022-01-19)
  * Update WSDL from 4.68 to 4.69
  * new parameters threeDSinfo, travelFileNumber for doAuthorization
  * new parameters threeDSinfo, travelFileNumber for doImmediateWalletPayment

----------------------------------------

* 4.68 (2021-10-07)
  * Update WSDL from 4.66 to 4.68
  * add missing parameters merchantURL, merchantCountryCode for verifyEnrollment
  * add missing parameters transient, owner, media, asynchronousRetryTimeout, linkedTransactionId, recurring for doAuthorization
  * add missing parameters owner, media for doDebit
  * add missing parameters media for doCapture
  * add missing parameters media, details for doRefund
  * add missing parameters media, owner for doCredit
  * add missing parameters version for getEncryptionKey
  * add missing parameters media for doScoringCheque
  * add missing parameters media for doReAuthorization
  * add missing parameters  miscData for isRegistered
  * add missing parameters archiveSearch for getTransactionDetails
  * add missing parameters buyer, owner, media, contractNumberWalletList, transactionID for createWallet
  * add missing parameters media for getWallet
  * add missing parameters  buyer, owner, media, contractNumberWalletList, transactionID for updateWallet
  * add missing parameters media for doImmediateWalletPayment
  * add missing parameters media for doRecurrentWalletPayment
  * add missing parameters contractNumber, owner, contractNumberWalletList for createWebWallet
  * add missing parameters contractNumber, updateOwnerDetails, contractNumberWalletList for updateWebWallet
  * add missing parameters threeDSInfo for manageWebWallet

----------------------------------------

* 4.66.1 (2021-10-01)
  * new parameters transientParam, owner, media, asynchronousRetryTimeout, linkedTransactionId, recurring for DoAuthorization

----------------------------------------

* 4.66 (2020-04-30)
  * Update WSDL from 4.64.1 to 4.66
  * new parameter PrivateDataList for doBankTransfer
  * new parameters mdFieldValue, generateVirtualCvx, recurring, returnURL, order, buyer, subMerchant, recurring, threeDSinfo, transientParam, privateDataList for verifyEnrollment
  * new parameters sequenceNumber, media for doReset
  * new parameters transientParam, PrivateDataList for verifyAuthentication

  * new parameters recurring, linkedTransactionId for doImmediateWalletPayment
  * new parameters recurring, authentication3dSecure, linkedTransactionId for doScheduledWalletPayment
  * new parameters cvx, linkedTransactionId, authentication3DSecure for doRecurrentWalletPayment
  * new parameters skipSmartDisplay, owner, contractNumberWalletList, asynchronousRetryTimeout, threeDSInfo, merchantScore for doWebPayment

----------------------------------------

* 4.64.1 (2020-12-14)
  * Update WSDL from 4.62.1 to 4.64.1
  * Split wsl in tree files
  * Improve pom.xml definition

----------------------------------------

* 4.62.1 (2020-05-01)
 * Update WSDL from 4.59 to 4.62.1
 * new posId, cardNetwork, threeDSecure and customerMediaId parameters for GetTransactionDetails 
 * new authorizedAmount and authorizedCurrency attributes in Authorization object
 
----------------------------------------

* 4.59 (2019-08-01)
  * new 3D-Secure V2 fields
  * minor fixes
    
---------------------------------------- 
* 4.58.1 (2019-07-10)
  * new 3D-Secure V2 fields
    
---------------------------------------- 
* 4.53 (2018-04-24)
  * remove com.experian.payline.ws.obj.ObjectFactory dependencies from all com.payline.ws.wrapper classes
  * new deliveryCharge attribute in order object
  * new registrationToken attribute in payment object
  * new object subMerchant
  * new method isRegistered
  * new subMerchant parameter for doAuthorization, doWebPayment, doCredit, doDebit, doImmediateWalletPayment, doScheduledWalletPayment
  * new miscData parameter for doWebPayment
  
---------------------------------------- 

* 4.52 (2017-10-25)
  * new "merchantName" parameter for services
  	- doWebPayment
  	- manageWebWallet
  	- verifyEnrollment
  * new "details" object parameter for doRefund service
  * new "avs" child node for "transaction" object
  * new "paymentData" child node for "card" object

----------------------------------------

* 4.48 (2016-09-20)
  * new getAlertDetails parameters
  	- version
  	- TransactionDate	

----------------------------------------

* 4.47 (2016-06-16)
  * new buyer properties :
  	- deviceFingerprint
  	- isBot
  	- isIncognito
  	- isBehindProxy
  	- isFromTor
  	- isEmulator
  	- isRooted
  	- hasTimezoneMismatch
  * new property cardBrand for payment and wallet objets
  * new property version for getCardsRequest
  
  * contractNumberWalletList can have 99 contractNumberWallet elements (report from 4.46.1)
  * add of PaResStatus and VeResStatus (Authentication3DSecure class - report from 4.46) 
  * add of softDescriptor payment attribute (Payment class - report from 4.45)
  
----------------------------------------

* 4.44.1 (2016-01-20)
  * First Payline release deployed on GitHub & Maven
