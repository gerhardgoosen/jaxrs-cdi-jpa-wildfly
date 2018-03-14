"# jaxrs-cdi-jpa-wildfly" 

Endpoints:
============

POST http://localhost:8080/eoh-cic-service/rest/cic/

POST DATA:
============
{
"cicType":"email2",
"subject":"test",
"body":"test",
"sourceSystem":"test",
"entity": {
	"emailAddress":"gerhardgoosen@gmail.com",
	"entityName":"test"
	}
}


Response:
===
{
    "id": 107,
    "cicType": "email2",
    "subject": "test",
    "body": "test",
    "sourceSystem": "test",
    "cicTimeStamp": 1521052794317,
    "entity": {
        "id": 6,
        "entityName": "test",
        "emailAddress": "gerhardgoosen@gmail.com"         
    }
}


GET http://localhost:8080/eoh-cic-service/rest/cic/{id}

response:
==
{
    "id": 102,
    "cicType": "email2",
    "subject": "test",
    "body": "test",
    "sourceSystem": "test",
    "cicTimeStamp": 1521052647519,
    "entity": {
        "id": 1,
        "entityName": "test",
        "emailAddress": "gerhardgoosen@gmail.com",
        "cicList": [
            1
        ]
    }
}



GET http://localhost:8080/eoh-cic-service/rest/entity/{id}

response:
==
{
    "id": 1,
    "entityName": "test",
    "emailAddress": "gerhardgoosen@gmail.com",
    "cicList": [
        {
            "id": 102,
            "cicType": "email2",
            "subject": "test",
            "body": "test",
            "sourceSystem": "test",
            "cicTimeStamp": 1521052647519,
            "entity": 1
        }
    ]
}



POST http://localhost:8080/eoh-cic-service/rest/entity/

POST DATA:
============
 {
"emailAddress":"gerhardgoosen@gmail.com",
"entityName":"test",
 "cicList": [
        { 
            "cicType": "email",
            "subject": "test",
            "body": "test",
            "sourceSystem": "dev"
        },
         { 
            "cicType": "email",
            "subject": "test",
            "body": "test",
            "sourceSystem": "dev"
        },
         { 
            "cicType": "email",
            "subject": "test",
            "body": "test",
            "sourceSystem": "dev"
        }
    ]
}


response:
==
{
    "id": 1,
    "entityName": "test",
    "emailAddress": "gerhardgoosen@gmail.com",
    "cicList": [
        {
            "id": 102,
            "cicType": "email",
            "subject": "test",
            "body": "test",
            "sourceSystem": "dev",
            "cicTimeStamp": 1521054271867,
            "entity": 1
        },
        {
            "id": 103,
            "cicType": "email",
            "subject": "test",
            "body": "test",
            "sourceSystem": "dev",
            "cicTimeStamp": 1521054271867,
            "entity": 1
        },
        {
            "id": 104,
            "cicType": "email",
            "subject": "test",
            "body": "test",
            "sourceSystem": "dev",
            "cicTimeStamp": 1521054271867,
            "entity": 1
        }
    ]
}




NOTES:
====


You might note that the Pom contains some extra dependencies.
These are libs I like to user for code coverage (cobertura) ,arquillian /graphene for testing.

I really only had a couple of hours to put this together, so there's not test cases yet..

I could add some test if you want to see how i go about that..



