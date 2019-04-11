package servicebus.common


import org.apache.activemq.ActiveMQConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.config.JmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate

import javax.jms.ConnectionFactory


/**
 * Esta classe cria as configurações mínimas para implementação de service bus baseado no Apache ActiveMQ.
 */
@EnableJms
@Configuration
class ServicebusConfiguration {

    /**
     * Endereço do broker ActiveMQ. Deve ser configurado através da property *spring.activemq.broker-url* no arquivo application.yml da aplicação
     */
    @Value('${spring.activemq.broker-url:tcp://localhost:61616}')
    private String brokerUrl

    /**
     * Nome do usuário de conexão com o ActiveMQ. Deve ser configurado através da property *spring.activemq.user* no arquivo application.yml da aplicação
     */
    @Value('${spring.activemq.user:admin}')
    private String user

    /**
     * Senha do usuário de conexão com o ActiveMQ. Deve ser configurado através da property *spring.activemq.password* no arquivo application.yml da aplicação
     */
    @Value('${spring.activemq.password:admin}')
    private String password


    @Bean
    ActiveMQConnectionFactory connectionFactory() {
        if ("".equals(user)) {
            return new ActiveMQConnectionFactory(brokerUrl)
        }
        return new ActiveMQConnectionFactory(user, password, brokerUrl)
    }


    @Bean
    JmsListenerContainerFactory jmsFactoryTopic(ConnectionFactory connectionFactory,
                                                DefaultJmsListenerContainerFactoryConfigurer configurer) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory()
        configurer.configure(factory, connectionFactory)
        factory.setPubSubDomain(true)
        return factory

    }


    @Bean
    JmsTemplate jmsTemplate() {

        return new JmsTemplate(connectionFactory())

    }


    @Bean
    JmsTemplate jmsTemplateTopic() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory())
        jmsTemplate.setPubSubDomain(true)
        return jmsTemplate
    }

}
