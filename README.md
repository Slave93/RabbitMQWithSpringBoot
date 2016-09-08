# RabbitMQWithSpringBoot
Simple application that uses spring boot to integrate with RabbitMQ message broker.
Implemented both message models: 
 - <b>point to point(work queue)</b> where a message is received by only one consumer. This is implemented in package rs.slavko.examples.rabbit.workqueue
 - <b>publish subscribe</b> where a message is received by all subscribed consumers. This is implemented in package rs.slavko.examples.rabbit.publishsubscribe
 
RabbitMQ integration is implemented using spring versioin 1.4.0 and new way of defining queues,exchanges and bindings via annotations.<br> Queues are defined inside MyRabbitListener.java.<br> Old (java beans) way of defining queues is commented inside MyRabbitConfiguration.java.
 