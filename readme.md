# Inter-service-communication

1.RestTemplate
2.webclient

Order Service ---->Http Req---->Inventory service
# Synchronous Communication
When service A sends a req to service B , And waiting for the response from service B
is called Synchronous communication

opposite A service sends a req to B and does not care about response is called Asynchronous Communication

# Eureka Server Configuration
application.properties

eureka.instance.hostname=localhost

eureka.client.register-with-eureka=false //its a discovery server we dont want it to register itself

eureka.client.fetch-registry=false 

# Client Side

@EnableEurekaClient

application.properties

eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka



