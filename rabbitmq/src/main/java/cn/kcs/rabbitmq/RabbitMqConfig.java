//package cn.kcs.rabbitmq;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import java.util.Objects;
//
///**
// * RabbitConfig
// *
// * @author kcs
// * @date 2019-05-05 16:18
// **/
//@Configuration
//public class RabbitMqConfig {
//
//    private static final Logger log = LoggerFactory.getLogger(RabbitMqConfig.class);
//    @Autowired
//    private Environment environment;
//    @Autowired
//    private CachingConnectionFactory connectionFactory;
//    @Autowired
//    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;
//
//    @Bean
//    public Queue queue() {
//        return new Queue(Objects.requireNonNull(environment.getProperty("mq_msg_queue_name")), true);
//    }
//
//    @Bean
//    public DirectExchange queueExchange() {
//        return new DirectExchange(environment.getProperty("mq_msg_exchange_name"), true, false);
//    }
//
//    @Bean
//    public Binding queueBinding() {
//        return BindingBuilder.bind(queue()).to(queueExchange()).with(environment.getProperty("mq_msg_routing_key_name"));
//    }
//
//    @Bean
//    public Queue acceptQueue() {
//        return new Queue(Objects.requireNonNull(environment.getProperty("mq_accept_queue_name")), true);
//    }
//
//    @Bean
//    public DirectExchange acceptQueueExchange() {
//        return new DirectExchange(environment.getProperty("mq_accept_exchange_name"), true, false);
//    }
//
//    @Bean
//    public Binding acceptQueueBinding() {
//        return BindingBuilder.bind(acceptQueue()).to(acceptQueueExchange()).with(environment.getProperty("mq_accept_routing_key_name"));
//    }
//
//    /**
//     * 单一消费者
//     *
//     * @return
//     */
//    @Bean(name = "singleListenerContainer")
//    public SimpleRabbitListenerContainerFactory listenerContainer() {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        factory.setConcurrentConsumers(1);
//        factory.setMaxConcurrentConsumers(1);
//        factory.setPrefetchCount(1);
//        factory.setTxSize(1);
//        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        return factory;
//    }
//
//    /**
//     * 多个消费者
//     *
//     * @return
//     */
//    @Bean(name = "multiListenerContainer")
//    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factoryConfigurer.configure(factory, connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
//        factory.setConcurrentConsumers(environment.getProperty("spring.rabbitmq.listener.concurrency", int.class));
//        factory.setMaxConcurrentConsumers(environment.getProperty("spring.rabbitmq.listener.max-concurrency", int.class));
//        factory.setPrefetchCount(environment.getProperty("spring.rabbitmq.listener.prefetch", int.class));
//        return factory;
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        connectionFactory.setPublisherConfirms(true);
//        connectionFactory.setPublisherReturns(true);
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                log.info("消息确认成功---:correlationData({}),ack:({}),cause:({})", correlationData, ack, cause);
//            }
//        });
//        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
//            }
//        });
//        return rabbitTemplate;
//    }
//}
