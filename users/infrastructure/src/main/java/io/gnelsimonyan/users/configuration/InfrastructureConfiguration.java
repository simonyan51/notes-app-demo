package io.gnelsimonyan.users.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({
        AuthorizationServerConfig.class,
        JWKSourceConfiguration.class,
        WebSecurityConfig.class
})
public class InfrastructureConfiguration {
}
