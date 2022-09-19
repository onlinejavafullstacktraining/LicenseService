package com.licenseservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class License {
    @Id
    private String id;
    @Column
    private String organizationId;
    @Column
    private String productName;
    @Column
    private String licenseType;

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phone;

    public License withOrganizationId(String organizationId){
        this.setOrganizationId(organizationId);
        return this;
    }
    public License withOrganizationName(String organizationName){
        this.setName(organizationName);
        return this;
    }
    public License withOrganizationEmail(String email){
        this.setEmail(email);
        return this;
    }
    public License withOrganizationPhone(String phone){
        this.setPhone(phone);
        return this;
    }
}
