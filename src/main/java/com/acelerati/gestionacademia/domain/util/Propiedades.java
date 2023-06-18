package com.acelerati.gestionacademia.domain.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "api.externa")
@Configuration()
public class Propiedades {

    private String gestionUsuario;
}
