package com.eazybytes.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {
    @Column(updatable = false)
    private LocalDate createdAt;
    @Column(updatable = false)
    private String createdBy;
    @Column(updatable = false)
    private LocalDate updatedAt;
    @Column(updatable = false)
    private String updatedBy;
}
