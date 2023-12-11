package com.barisd.rabbitmqproducerdirect.controller;

import com.barisd.rabbitmqproducerdirect.model.Message;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange exchange;

    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }
    @PostMapping("/send")
    public String convertAndSend(@RequestBody Message message){
        rabbitTemplate.convertAndSend(exchange.getName(),"Routing.A",message);
        return  exchange.getName() + "exchangine Routing Key: Routing.A kullanılarak "+ message+" mesajı gönderildi.";
    }
}
