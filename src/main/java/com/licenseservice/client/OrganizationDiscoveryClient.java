package com.licenseservice.client;

import com.licenseservice.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OrganizationDiscoveryClient {

    private DiscoveryClient discoveryClient;
    private RestTemplate restTemplate;
    @Autowired
    public OrganizationDiscoveryClient(DiscoveryClient discoveryClient,RestTemplate restTemplate){
        this.discoveryClient=discoveryClient;
        this.restTemplate=restTemplate;
    }

    public Organization getOrganization(String organizationId){
        List<ServiceInstance> serviceInstancesList = discoveryClient.getInstances("organization-service");
        if(serviceInstancesList.isEmpty()) return null;
        String url =String.format("%s/v1/organizations/%s",serviceInstancesList.get(0).getUri().toString(),organizationId);
        ResponseEntity<Organization> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Organization.class);
        return responseEntity.getBody();
    }
}
