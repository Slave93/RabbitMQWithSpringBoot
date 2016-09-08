# RabbitMQWithSpringBoot
Simple application that uses spring boot to integrate with RabbitMQ message broker.
Implemented both message models: 
 - <b>point to point(work queue)</b> where a message is received by only one consumer. This is implemented in package rs.slavko.examples.rabbit.workqueue
 - <b>publish subscribe</b> where a message is received by all subscribed consumers. This is implemented in package rs.slavko.examples.rabbit.publishsubscribe
 