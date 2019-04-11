##Service Bus Common
Este plugin agrega as dependências básicas para implementação de um "service bus" baseado em Apache ActiveMQ e Apache Camel.

###Configuração
Se estiver de posse do código fonte, deve-se adicionar a seguinte cláusula ao *build.gradle*:

```java

grails{
    plugins{
        compile project(':tcmpa-servicebus-common')
    }
}

```

###Requisitos operacionais
A utilização deste plugin requer que esteja rodando em _localhost_ uma instância do Apache ActiveMQ ( 5.15.9 ou superior ) com sua configuração padrão.
Deve-se utilizar as seguintes variáveis (_application.yml_ ou runtime) para configuração da conexão :

```java

---
spring:
    activemq:
        broker-url: tcp://localhost:61616
        user: admin ou karaf
        password: admin ou karaf
---

```

###Quick start

O padrão adotado baseia-se na criação de rotas [Route] do Apache Camel. Sua aplicação pode configurar várias rotas pela 
criação se _services_ que estendam a classe _ServicebusRouteProcessor_. Após a criação do _service_ , será necessário 
implementar o método _configure()_, como ilustrado no exemplo abaixo:

```java

@Transactional
class CamelRoute03Service extends ServicebusRouteProcessor {
    
    @Override
    public void configure() throws Exception {

        log.info "Configurando rota"

        from("file://c:/temp/inbox" )
           .setHeader("SISTEMA_ID", constant("SPE"))                
           .to("file://c:/temp/outbox")

        log.info "Configuração da rota realizada."

    }

}

```

O código para configuração da rota usa _DSL_ padrão do Apache Camel.