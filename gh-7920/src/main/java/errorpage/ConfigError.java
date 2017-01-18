package errorpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigError {

    @Autowired(required = false)
    private List<ErrorViewResolver> errorViewResolvers;

    @Bean
    public ErrorController errorController(final ErrorAttributes errorAttributes, final ServerProperties serverProperties) {
        return new ErrorController(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }
}
