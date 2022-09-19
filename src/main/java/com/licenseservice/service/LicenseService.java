package com.licenseservice.service;

import com.licenseservice.client.OrganizationDiscoveryClient;
import com.licenseservice.client.OrganizationFeignClient;
import com.licenseservice.client.OrganizationRestTemplateClient;
import com.licenseservice.model.License;
import com.licenseservice.model.Organization;
import com.licenseservice.model.ServiceConfig;
import com.licenseservice.repository.LicenseRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class LicenseService {
    private final LicenseRepository repository;
    private final ServiceConfig serviceConfig;
    private final OrganizationDiscoveryClient organizationDiscoveryClient;
    private final OrganizationRestTemplateClient organizationRestTemplateClient;

    private final OrganizationFeignClient organizationFeignClient;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository, ServiceConfig serviceConfig,
                          OrganizationDiscoveryClient organizationDiscoveryClient,
                          OrganizationRestTemplateClient organizationRestTemplateClient,
                          OrganizationFeignClient organizationFeignClient) {
        this.repository = licenseRepository;
        this.serviceConfig = serviceConfig;
        this.organizationDiscoveryClient = organizationDiscoveryClient;
        this.organizationRestTemplateClient=organizationRestTemplateClient;
        this.organizationFeignClient=organizationFeignClient;
    }

    @HystrixCommand(fallbackMethod = "buildFallBackLicense",
            threadPoolKey = "licenseByOrgThreadPool",
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "30"),
            @HystrixProperty(name = "maxQueueSize", value = "10")},
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")})
    public License getLicense(String organizationId, String licenseId) {
        System.out.println(serviceConfig.getDefaultProperty());
        randomlyRunning();
        return repository.findByOrganizationIdAndId(organizationId, licenseId);
    }
    private License buildFallBackLicense(String organizationId, String licenseId){
         License license = new License();
        license.setId(licenseId);
        license.setOrganizationId(organizationId);
        license.setProductName("FallBack");
        license.setLicenseType("Hystrix");
        license.setProductName("sorry no license information currently available");
        return license;
    }
    private License buildFallBackLicense(String organizationId, String licenseId, String clientType){
        License license = new License();
        license.setId(licenseId);
        license.setOrganizationId(organizationId);
        license.setProductName("FallBack");
        license.setLicenseType("Hystrix");
        license.setProductName("sorry no license & Organization information currently available");
        return license;
    }

    private void randomlyRunning() {
        Random random=new Random();
        int randomNum=random.nextInt((3-1)+1)+1;
        if(randomNum==3)sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(22000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @HystrixCommand(fallbackMethod = "buildFallBackLicense",
            threadPoolKey = "licenseByOrgThreadPool",
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")},
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "25000")})
    public License getLicense(String organizationId, String licenseId, String clientType) {
        Optional<License> optionalLicense = repository.findById(licenseId);
        if(optionalLicense.isPresent()) {
            Organization organization = retrieveOrgData(organizationId, clientType);
            return optionalLicense.get().withOrganizationId(organization.getId()).withOrganizationEmail(organization.getEmail())
                    .withOrganizationName(organization.getName()).withOrganizationPhone(organization.getPhone());
        }
        return null;
    }

    private Organization retrieveOrgData(String organizationId, String clientType) {
        Organization organization = null;
        if ("discovery".equals(clientType)) {
            System.out.println("I am using the discovery client");
            organization = organizationDiscoveryClient.getOrganization(organizationId);
        }else if("rest".equals(clientType)){
            organization=organizationRestTemplateClient.getOrganization(organizationId);
        }else if("feign".equals(clientType)){
            organization=organizationFeignClient.getOrganization(organizationId);
        }
        return organization;
    }

    public License save(License license) {
        license.setId(UUID.randomUUID().toString());
        return repository.save(license);
    }
}
