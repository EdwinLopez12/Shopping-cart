
# Shopping Cart. 🛒

_Small application that simulates a shopping cart, has integration with PayPal, Jwtokens, Swagger documentation, and more_
_You can register, verify the account, log in, reset the password, update JWT, log out, manage privileges and roles. You can manage products, categories, orders, and payments with paypal_

## Starting. 🚀

_These instructions will allow you to get a copy of the project running on your local machine for development and testing purposes._

See **Deployment** to know how to deploy the project.


### Requirements. 📋

_What things do you need to install the application?_

```  
Maven  
Text editor or IDE like Eclipse or IntelliJ (I recommend it)  
Database manager (Mysql, MariaDB, H2, PostgreSQL)
Postman 
```  

### Installation. 🔧

_Clone the repository._

```  
git clone https://github.com/EdwinLopez12/Shopping-cart.git
```  

_Open the project with the IDE or editor to add the necessary DB credentials in the `application-dev.properties`, make sure that in the `application.properties` the propertie `spring.profiles.active=` is equals to `dev`_

```  
spring.datasource.url= [The connection url]
spring.datasource.username= [The DB username]
spring.datasource.password= [The DB password]
```  
_Remember that each database manager requires the use of its Dialect and driver_

```  
spring.datasource.driver-class-name=
spring.jpa.properties.hibernate.dialect=
```  
_Set the mail settings. You can use [Mailtrap](https://mailtrap.io/) or any mail service, like Gmail._
_Get the credentials of the mail service and set it in `application-dev-properties`_
```  
spring.mail.host=
spring.mail.port= 
spring.mail.username=
spring.mail.password=
``` 
_if you decided to use Gmail add these other properties_
```  
spring.mail.protocol=smtp  
spring.mail.properties.mail.smtp.starttls.enable=true  
spring.mail.properties.mail.smtp.starttls.required=true  
spring.mail.properties.mail.smtp.auth=true  
spring.mail.properties.mail.smtp.connectiontimeout=5000  
spring.mail.properties.mail.smtp.timeout=5000  
spring.mail.properties.mail.smtp.writetimeout=5000
```  

### how to use it? 💭

_Go to `src/main/resources/documentation/postman/shopping cart.postman_collection_json` and import it on Postman._

_The logic is simple, a user registers and obtains basic privileges (in this case administrator privileges will be granted), these privileges will be validated through each endpoint of the application. Once the user registers and verifies the account through an email, he/she will be able to log in and carry out the operations (requests) that are in the postman file._


## Example request. 💡

_Any tool that can perform this task can be used to consume the API. It should be noted that in this case the step-by-step will be done using Postman as the main tool._
### Remember.

- In each request, it will be validated that the user is logged in, through Jwtokens that will travel at the head of the request, likewise, it will be verified that the token is valid and that it has not expired.
- Authentication routes (endpoints) (/ auth) do not have this rule defined, that is, it can be used even if the user is not logged in
- To facilitate Postman requests and make them faster and more intuitive, data is added to the database in the application. This data can be inserted in each restart of the application, to avoid this behavior edit the property `spring.jpa.hibernate.ddl-auto=`

### Request.
_Before starting to make requests, it is necessary to configure some environment variables in Postman, to automate and facilitate these requests._

_Go to `src/main/resources/documentation/postman/Shopping cart -API - Env.postman_environment.json` and import it on Postman._

#### Example Signup
_In the body of the postman's **signup** request, add the necessary data_
```  
{
	"username" : "",
	"email": "",
	"password": "",
	"passwordVerify": ""
}
``` 

##### Signup example response
```  
{
	"timestamp": "08-10-2021 19:02:51",
	"status": "Ok",
	"exception": null,
	"response": {
		"code": 201,
		"type": "signup",
		"message": "Account successfully created, please check the email to activate it!",
		"data": null
		}
}
``` 
_After this you can go to the email server and activate the account via the link sent._
#### Example Verify account
_Just click the button and the account will be activated automatically._
![Verify-Account](src/main/resources/documentation/img/email_to_active_account.png)

#### Example Login
_Once the account is activated, you can go to the **login** request and add the data to the required fields_

```  
{
	"username" : "",
	"password": "",
}
``` 

##### Signup example response
```  
{
	"timestamp": "08-10-2021 14:39:19",
	"status": "Ok",
	"exception": null,
	"response": {
		"code": 200,
		"type": "login",
		"message": "Logged in",
		"data": [
			{
				"authenticationToken": "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJBRE1JTiIsImlhdCI6MTYzMzcyMTk1OSwiZXhwIjoxNjMzNzMwOTU5fQ.EScy96APxj42otyh7f0iFmk1rJQgu60T0IPSleNU6v0czxSyEUtBXjDk4AqDjnBnyKkyFVDGDFrcGtwkpv9qaRP4titSMmxEKK3rgFZueR03QMUMhmSDl6HTwDIbATc5CCQ1l6EmfFkxuSXntOFuTfd-39IoXeIyxDUWuLrvhm2NVq75jQhpIh1EPHnQy3QEKbpBBzYoRJ7dluCRdVop4x-SU1FyTPb7mtx_rUC7gVUrYMPEkiHPsbOzWehpGXbSAF_rwcwwg7yrayUfUXWfONKI00h04FdheQBc-ayDw0jo9KNRR2wqwL2Sb1SFLXJ_WeLyY0VXdlsi1rZtd7MEaw",
				"refreshToken": "eb164686-c8cb-4a20-a8eb-9337ebbdb48f",
				"expiresAt": "08-10-2021 17:09:19",
				"username": "ADMIN"
			}
		]
	}
}
``` 

#### Example Payment with PayPal.
_To make the payment with PayPal, first certain conditions are needed_

1. The user has set all the person data.
2. There is an order in the database.
3. Generate the order from the database in Paypal.
4. Approve payment.
5. Capture the payment.

##### Set user data
_The user must be logged in and go to the request **add user data** and add the necessary fields._
```  
{
    "nid" : "",
    "name" : "",
    "lastName" : "",
    "cellphone" : "",
    "address" : "",
    "town" : 1
}
```  

##### Create an order.
_Go to the **add order** request and enter the necessary data_

```  
{
	
	"products" : [
		{
			"id" : "5",
			"amount" : "5"
		},
		{
			"id" : "4",
			"amount" : "4"
		},
		{
			"id" : "3",
			"amount" : "3"
		},
		{
			"id" : "2",
			"amount" : "2"
		},
		{
			"id" : "1",
			"amount" : "1"
		}
	]
}
``` 
#### Example response
_You can see an example of each answer in the collections of the postman._

#### Generate Paypal order
_Once the order is created, go to the **payment** request and add the necessary data._
```  
{
	"paymentMethod" : "PAYPAL",
	"orderId" : ""
}
``` 
_Once the request is made, you should receive a response like the ones from the postman, which will contain a `paypalOrderId` and some Paypal links_
```  
"links": [
	{
		"rel": "self",
		"type": "GET",
		"href": "https://api.sandbox.paypal.com/v2/checkout/orders/6CK12760FG415261C"
	},
	{
		"rel": "approve",
		"type": "GET",
		"href": "https://www.sandbox.paypal.com/checkoutnow?token=6CK12760FG415261C"
	},
	{
		"rel": "update",
		"type": "PATCH",
		"href": "https://api.sandbox.paypal.com/v2/checkout/orders/6CK12760FG415261C"
	},
	{
		"rel": "capture",
		"type": "POST",
		"href": "https://api.sandbox.paypal.com/v2/checkout/orders/6CK12760FG415261C/capture"
	}
]
```  
_Once the links are obtained, you can enter the link of `"rel":"approve"` through the browser and create an account in **PayPal Sandbox** or log in. After one, you will see a view like the following_

![Approve-payment](src/main/resources/documentation/img/paypal_aprove.png)

#### Pay with PayPal
_To make the payment with Paypal, you must first obtain the credentials of your Paypal account in the developer section, follow the documentation and once the credentials are obtained add them in the fields of `Username` and `Password`, as well as verify that the authentication type is `Basic Auth`, which you will find in the request **get access token**_

![Set-access-token](src/main/resources/documentation/img/paypal_credentials.png)

_Once the access token is obtained, you can go to the ** capture with backend PayPal** request and add the necessary fields_
```  
{
	"orderPaypalId" : "45942061540137348",
	"token" : ""
}
```  
_Once this action is completed, the number of available products will be reduced according to the order and the status of the order will be changed to paid._

## Deployment. 📦

_Use a [Heroku](https://www.heroku.com/) to deploy the application most simply._

_What things do you need to deploy the application?_


- Create a new application on Heroku.
- Add SQL and get the credentials to set up in `application-heroku.properties`.
- Create a new file on the root folder named `system.properties` and add the java runtime version `java.runtime.version=` 8 or 11
- Go to [deploy](https://devcenter.heroku.com/categories/deployment) and follow the steps.


## Demo. 🚀

_Look at the Swagger documentation at the below link, then you can try submitting requests with Postman_

[Live Preview](https://edwinlopez12-shopping-cart.herokuapp.com/swagger-ui.html).

## Developed with. 🛠️
* Java 1.8
* [Spring boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)

## Integration . ⛏
* [Paypal](https://developer.paypal.com/home)
* [Jwt](https://jwt.io/)


## Contributing. 🖇️

If you have something in mind, make a Pull Requests.

## Versions. 📌

We use [SemVer](http://semver.org/) for versions. For all available versions, look at the [tags in this repository](https://github.com/EdwinLopez12/Shopping-cart/tags).

## Authors. ✒️

**Edwin López** - *Original author* - [EdwinLopez12](https://github.com/EdwinLopez12).


## License. 📄

This project is under the MIT License - see the [LICENSE.md](https://github.com/EdwinLopez12/Shopping-cart/blob/main/LICENSE) file for details.
  
---  
⌨️ with ❤️ by [Edwin Lopez](https://github.com/EdwinLopez12/) 😊.