package com.licenseservice.controller;

import com.licenseservice.model.License;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/licenses")
public class LicenseController {
    @RequestMapping(value = "/{licenseId}")
    public License getLicense(@PathVariable("organizationId")String organizationId,
                              @PathVariable("licenseId") String licenseId)
    {
        License license = new License();
        license.setId(licenseId);
        license.setOrganizationId(organizationId);
        license.setProductName("I-Phone");
        license.setLicenseType("PERM");
        return license;
    }
}
