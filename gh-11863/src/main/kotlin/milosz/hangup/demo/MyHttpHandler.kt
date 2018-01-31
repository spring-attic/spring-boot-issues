package milosz.hangup.demo

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class MyHttpHandler {

    fun home(request: ServerRequest): Mono<ServerResponse> {
        val webClient = WebClient.create("http://example.com")

        val response = webClient.get()
            .retrieve()
            .bodyToMono(String::class.java)
            .log()

        LoggerFactory.getLogger("TEST").info("BEFORE BLOCK")
        val result = response.block()!!
        LoggerFactory.getLogger("TEST").info("AFTER BLOCK")
        return ServerResponse.ok().syncBody(result)
    }
}