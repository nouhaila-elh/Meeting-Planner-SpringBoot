package com.example.demo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"room.reservation.controller", "room.reservation.serviceImpl","room.reservation.serviceInterface"})
@EnableJpaRepositories("room.reservation.repository")
@EntityScan("room.reservation.entities")
public class AppConfiguration {

}
