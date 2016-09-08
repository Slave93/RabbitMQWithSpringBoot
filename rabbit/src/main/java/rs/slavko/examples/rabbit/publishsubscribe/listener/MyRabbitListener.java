package rs.slavko.examples.rabbit.publishsubscribe.listener;

import rs.slavko.examples.rabbit.publishsubscribe.config.MyRabbitConfiguration;
import rs.slavko.examples.rabbit.publishsubscribe.model.MyMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
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
                value = @Queue(exclusive = "true"),
                exchange = @Exchange(type = ExchangeTypes.FANOUT, value = MyRabbitConfiguration.EXCHANGE_NAME)))
@Component
public class MyRabbitListener {
    @RabbitHandler
    public void process(@Payload MyMessage message,@Headers Map<String,String> headers) {
        System.out.println("Message received at "+new Date()+". Message: " +  message+", headers"+headers);
    }
}
