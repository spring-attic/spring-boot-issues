package milosz.hangup.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class Routes {

    @Bean
    fun routerFunction(
        myHttpHandler: MyHttpHandler
    ) = router {
        GET("/home", myHttpHandler::home)
    }
}
