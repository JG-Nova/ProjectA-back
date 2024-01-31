package org.jgprojects.a.persistence.entity;

import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String provider;
    @Column(name = "recovery_token")
    private String recoveryToken;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate = new Date();
    @Column(name = "modified_date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate = new Date();
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "is_blocked")
    private boolean isBlocked;
}
