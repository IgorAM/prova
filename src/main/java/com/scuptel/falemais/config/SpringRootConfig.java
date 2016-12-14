package com.scuptel.falemais.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.scuptel.falemais.service", "com.scuptel.falemais.facade.impl", "com.scuptel.falemais.assembler"})
public class SpringRootConfig {
}
