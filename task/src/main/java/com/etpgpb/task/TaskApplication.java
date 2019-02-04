package com.etpgpb.task;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.sql.SQLException;

@SpringBootApplication
public class TaskApplication extends SpringBootServletInitializer {
    private final static Logger log = LoggerFactory.getLogger(TaskApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TaskApplication.class);
    }

    public static void main(String[] args) {
        runH2Server();
        SpringApplication.run(TaskApplication.class);
    }

    private static void runH2Server() {
        try {
            Server h2Server = Server.createWebServer("-webPort", "9000").start();
            if (h2Server.isRunning(true)) {
                log.info("H2 server was started and is running.");
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }

    }

}

