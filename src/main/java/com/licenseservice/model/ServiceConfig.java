package com.licenseservice.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@Setter
@Getter
public class ServiceConfig {
    @Value("${env.property}")
    private String defaultProperty;
}
