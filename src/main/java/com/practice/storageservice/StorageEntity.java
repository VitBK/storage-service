package com.practice.storageservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "storages")
@NoArgsConstructor
@Getter
@Setter
public class StorageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "storage_type")
    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    @Column(name = "bucket_name")
    private String bucketName;

    public StorageEntity(StorageType storageType, String bucketName) {
        this.storageType = storageType;
        this.bucketName = bucketName;
    }
}
