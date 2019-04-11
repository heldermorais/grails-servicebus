package my.routes

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.apache.camel.Exchange
import org.springframework.jms.JmsException

import javax.jms.JMSException

@Transactional
class TransactedConsumer001Service {

    void execute(String body, Exchange exchange) {
        log.info "Begin"

        log.info "Processando Msg ${body} "


        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(body)

        log.info( "Object.count = ${object.count}");


        throw new JMSException("Erro Horrivel.")

        log.info "End"
    }

}
