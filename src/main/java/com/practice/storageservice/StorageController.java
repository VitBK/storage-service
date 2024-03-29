package com.practice.storageservice;

import jakarta.ws.rs.QueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storages")
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping
    public Integer createStorage(@RequestBody StorageEntity storage) {
        return storageService.createStorage(storage.getStorageType(), storage.getBucketName()).getId();
    }

    @GetMapping
    public List<StorageEntity> getStorageList() {
        log.info("Getting storage list");
        return storageService.findAllStorages();
    }

    @DeleteMapping
    public void deleteStorage(@QueryParam("id") List<Integer> ids) {
        storageService.deleteStorage(ids);
    }

}
