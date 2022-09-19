package com.licenseservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@Setter
@Getter
@NoArgsConstructor
public class Organization {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phone;

}
