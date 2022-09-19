package com.licenseservice.client;

import com.licenseservice.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient("organization-service")
public interface OrganizationFeignClient {
    @RequestMapping(value = "/v1/organizations/{organizationId}", method = RequestMethod.GET,
            consumes = "application/json")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);
}
