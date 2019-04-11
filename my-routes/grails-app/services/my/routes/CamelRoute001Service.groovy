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
        from("timer:foo?period=3000").routeId("my.routes.route001")
        .log(LoggingLevel.INFO,"Hi there !")
        log.info "Configuration end."
    }

}
