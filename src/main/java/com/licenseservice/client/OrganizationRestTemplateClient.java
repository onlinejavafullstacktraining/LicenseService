package com.licenseservice.client;

import com.licenseservice.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {
    private RestTemplate restTemplate;
    @Autowired
    public OrganizationRestTemplateClient(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public Organization getOrganization(String organizationId){
        ResponseEntity<Organization> organizationResponseEntity = restTemplate.exchange("http://organization-service/v1/organizations/{organizationId}", HttpMethod.GET
                , null, Organization.class, organizationId);
        return organizationResponseEntity.getBody();
    }
}
