# pin-generator
To generate and validate 4 digit pin assessment in Spring boot
prerequisite-

1. Jave8
2. Mysql


1. Kindly downlaod the project and setup in any of IDE(Preferable intellij IDEA). Once the project is setup please execute mvn clean install and reopen the project again.

2. Start the services.

3. Below Rest endpoints are secured with Basic authentication -

	1. Generate Pin - Provide a MSISDN(Format : +<Country Code><10 Digit Number>) in input. 
	
	
	Endpoint: http://localhost:9081/pin/generate
	Method: Post
	Sample Request : 
	{
	"msisdn":"+34999112233",
	}
	Headers:
		Content-Type : application/json
		
	Authorization:
		username: amit
		password: amit
	Sample Response :
	{
        "pin": "1234",
	}
	
		
	2. Validate Pin: Provide a MSISDN and PIN in the input, it will validate the pin. 
	
		Endpoint: http://localhost:9081/pin/validate
		Method: POST
		Sample Request :
		Sample Request : 
		{
			"msisdn":"+34999112233",
		}
		Headers:
			Content-Type : application/json
			
		Authorization:
		username: amit
		password: amit
		Sample Response :
		{
			"message": "Pin validated",
		}	
			
		
Please write a any query related to project setup or the running the servies to akjain1107@gmail.com
