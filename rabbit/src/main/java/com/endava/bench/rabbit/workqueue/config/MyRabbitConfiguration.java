package com.endava.bench.rabbit.workqueue.config;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRabbitConfiguration {

    public static final String QUEUE_NAME = "work.queue";
    public static final String DL_QUEUE_NAME = "work.dl.queue";
    public static final String EXCHANGE_NAME = "work.exchange";
    public static final String EXCHANGE_TYPE = ExchangeTypes.TOPIC;


    /*
    Explicit (non annotation) way of defining queues,exchanges, and bindings
    If we define them as beans, Spring will try to create them,
    if they do not exist with
    same names. If they exist, they must match properties defined here.If they do not
    exception will be thrown at application startup.
    */
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME);
//    }
//
//    @Bean
//    public TopicExchange exchange() {
//        return new TopicExchange(EXCHANGE_NAME);
//    }
//
//    @Bean
//    public Binding binding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME+".#");
//    }

    /*
    This will define the way that the rabbit message(type that has body as a byte array and additional information about message)
    will be prepared(converted) for listener method.
    Here we set JsonMessageConverter. This tells spring that body of received message that is in form of a
    byte array is json and that should be converted into object that is annotated as @Payload
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new JsonMessageConverter());
        //will dispatch messages to consumers more fairly
        //That is, it will not dispatch messages using only simple round-robin algorithm,
        //and will skip consumer if it has not yet acknowledged previous message
        factory.setPrefetchCount(1);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new JsonMessageConverter());
        rabbitTemplate.setExchange(MyRabbitConfiguration.EXCHANGE_NAME);
        rabbitTemplate.setRoutingKey(QUEUE_NAME+".#means.any.number.of.words");
        return rabbitTemplate;
    }
}
