package rs.slavko.examples.rabbit.workqueue.listener;

import rs.slavko.examples.rabbit.workqueue.config.MyRabbitConfiguration;
import rs.slavko.examples.rabbit.workqueue.model.MyMessage;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/*
 * Queue,Exchange, and binding will be created if they do not exist with
 * same names. If they exist, they must match properties defined here.If they do not
 * exception will be thrown at application startup.
 */
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = MyRabbitConfiguration.QUEUE_NAME, autoDelete = "false", durable = "true"),
        exchange = @Exchange(type = MyRabbitConfiguration.EXCHANGE_TYPE, value = MyRabbitConfiguration.EXCHANGE_NAME),
        key = MyRabbitConfiguration.QUEUE_NAME + ".#"))
@Component
public class MyRabbitListener {
    @RabbitHandler
    public void process(@Payload MyMessage message, @Header("amqp_receivedRoutingKey") String routingKey, @Headers Map<String, String> headers) {
        System.out.println("Message received at " + new Date() + ". Message: " + message + " , routing key: " + routingKey + ", allHeaders" + headers);
    }
}
