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