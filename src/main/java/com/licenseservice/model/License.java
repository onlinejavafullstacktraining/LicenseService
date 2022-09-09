package com.licenseservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class License {
    private String id;
    private String organizationId;
    private String productName;
    private String licenseType;
}
