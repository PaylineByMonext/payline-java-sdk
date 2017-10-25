# Payline Java SDK
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.payline/payline-java-sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.payline/payline-java-sdk)

The Payline API provides access to the various functions of the Payline payment solution. It is
based on standard web service components, which include the SOAP protocol, the WSDL and
XSD definition languages. These standards are supported by a large range of development tools
on multiple platforms.

This SDK covers all the functions of the Payline payment solution.
## Features
The set of web services Payline is covered by this library. Web services are divided into four classes: 
* DirectPayment
* ExtendedAPI
* WalletPayment
* WebPayment

## Requirements
Java 1.6 or higher

## Installation
### Maven users
Add this dependency in your project's POM:

```xml
<dependency>
  <groupId>com.payline</groupId>
  <artifactId>payline-java-sdk</artifactId>
  <version>4.52</version>
</dependency>
```

## Usage
SampleCalls.java

```java

import java.util.logging.Level;
import java.util.logging.Logger;

import com.payline.kit.utils.ConnectParams;
import com.payline.ws.model.GetMerchantSettingsResponse;
import com.payline.ws.wrapper.DirectPayment;

public class SampleCalls {

    private static final Logger logger = Logger.getLogger(SampleCalls.class.getName());

    public static void main(String[] args) {
        ConnectParams params = new ConnectParams(
                null, /* module */
                false, /* production */
                false, /* clientAuthentication */
                "", /* Your merchant account login: merchantId */
                "", /* Your access key to the Payline service: accessKey */
                null, /* proxyHost (optional) */
                null, /* proxyPort (optional) */
                null, /* proxyLogin (optional) */
                null /* proxyPassword (optional) */
                );
        DirectPayment directPayment = new DirectPayment(params);
        GetMerchantSettingsResponse res = directPayment.getMerchantSettings(null);

        logger.log(Level.INFO, "\ngetMerchantSettings result :  ");
        logger.log(Level.INFO, "  - code :  " + res.getResult().getCode());
        logger.log(Level.INFO, "  - short message :  " + res.getResult().getShortMessage());
        logger.log(Level.INFO, "  - long message :  " + res.getResult().getLongMessage());
    }
}
```

## Version
4.52

## Learn about Payline
* [www.payline.com](http://www.payline.com/index.php/en/)
* [support payline](https://support.payline.com/hc/en-us)
* [Getting Started Guide](https://support.payline.com/hc/en-us/articles/201221883-Users-guide-to-installing-the-Payline-payment-solution)

*For assistance, advice or an answer to your question, contact Payline Support by email at*
`support@payline.com`

## License

[LGPL v3](./LICENSE)


