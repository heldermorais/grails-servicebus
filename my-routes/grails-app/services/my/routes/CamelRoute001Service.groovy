package my.routes

import grails.gorm.transactions.Transactional
import org.apache.camel.LoggingLevel
import servicebus.common.ServicebusRouteBuilder


@Transactional
class CamelRoute001Service extends ServicebusRouteBuilder {

    def serviceMethod() {

    }

    @Override
    void configure() throws Exception {
        log.info "Configuration Begin"
//        from("timer:foo?period=3000").routeId("my.routes.route001")
//        .transform().constant("Hello World!")
//        .log(LoggingLevel.INFO,"Hi there !")
//        .to("activemq:queue:queue.redeliver")

        from("activemq:queue:queue.redeliver" )
        .to("bean:transactedConsumer001Service")
        log.info "Configuration end."
    }

}
