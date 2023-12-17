package com.codegenius.user.infra.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Application configuration class for setting up environment-specific behavior.
 *
 * @author hidek
 * @since 2023-08-09
 */
@Configuration
public class AppConfiguration {
    /**
     * Bean configuration for development environment.
     *
     * @return A CommandLineRunner that prints a message for development environment.
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Bean
    @Profile("development")
    public CommandLineRunner executarDevelopment(){
        return args -> {
            System.out.println("RODANDO O AMBIENTE DE DESENVOLVIMENTO");
        };
    }

    /**
     * Bean configuration for production environment.
     *
     * @return A CommandLineRunner that prints a message for production environment.
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Bean
    @Profile("production")
    public CommandLineRunner executarProduction(){
        return args -> {
            System.out.println("RODANDO O AMBIENTE DE PRODUÇÃO");
        };
    }

    /**
     * Bean configuration for test environment.
     *
     * @return A CommandLineRunner that prints a message for test environment.
     *
     * @author hidek
     * @since 2023-09-19
     */
    @Bean
    @Profile("test")
    public CommandLineRunner executarTest(){
        return args -> {
            System.out.println("RODANDO O AMBIENTE DE TESTE");
        };
    }
}
