package com.licenseservice.controller;

import com.licenseservice.model.License;
import com.licenseservice.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/licenses")
public class LicenseController {

    private LicenseService licenseService;
    @Autowired
    public LicenseController(LicenseService licenseService){
        this.licenseService=licenseService;
    }
    @RequestMapping(value = "/{licenseId}")
    public License getLicense(@PathVariable("organizationId")String organizationId,
                              @PathVariable("licenseId") String licenseId)
    {
       /* License license = new License();
        license.setId(licenseId);
        license.setOrganizationId(organizationId);
        license.setProductName("I-Phone");
        license.setLicenseType("PERM");
        return license;*/
        return licenseService.getLicense(organizationId, licenseId);
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public License saveLicense(@RequestBody  License license)
    {
        return licenseService.save(license);
    }

    @RequestMapping(value = "/{licenseId}/{clientType}",method = RequestMethod.GET)
    public License getLicensewithClient(@PathVariable("licenseId") String licenseId,
                                        @PathVariable("clientType") String clientType,
                                        @PathVariable("organizationId")String organizationId)
    {
        return licenseService.getLicense(organizationId, licenseId,clientType);
    }
}
