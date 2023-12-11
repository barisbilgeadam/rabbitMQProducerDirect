package com.barisd.rabbitmqproducerdirect.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
//1. Kuyrukları Ekle
    @Bean
    Queue queueA(){
        return new Queue("q.A");
    }
    @Bean
    Queue queueB(){
        return new Queue("q.B");
    }
    @Bean
    Queue queueC(){
        return new Queue("q.C");
    }
    //2. Exchange Ekle
    @Bean
    DirectExchange exchange(){
        return new DirectExchange("java12.exchange.direct");
    }
    //3. Bindingleri Ekle
    @Bean
    Binding bindingA(Queue queueA,DirectExchange exchange){
        return BindingBuilder
                .bind(queueA)
                .to(exchange)
                .with("Routing.A");
    }

    @Bean
    Binding bindingB(Queue queueB,DirectExchange exchange){
        return BindingBuilder
                .bind(queueB)
                .to(exchange)
                .with("Routing.B");
    }

    @Bean
    Binding bindingC(Queue queueC,DirectExchange exchange){
        return BindingBuilder
                .bind(queueC)
                .to(exchange)
                .with("Routing.C");
    }
    //4. Nesne-JSON dönüşümü için bir converter ekle
    @Bean
    MessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }
    //5. Converter aktifleştirmek için RabbitTemplate içinde tanımla
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
