package com.practice.storageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Transactional(readOnly = true)
    public List<StorageEntity> findAllStorages() {
        return storageRepository.findAll();
    }

    @Transactional
    public StorageEntity createStorage(StorageType storageType, String bucketName) {
        return storageRepository.save(new StorageEntity(storageType, bucketName));
    }

    @Transactional
    public void cleanUpAllStorages() {
        storageRepository.deleteAll();
    }

    @Transactional
    public void deleteStorage(List<Integer> ids) {
        storageRepository.deleteAllById(ids);
    }
}
