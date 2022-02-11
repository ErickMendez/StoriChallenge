package com.storichallenge.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface AWSS3 {
    void uploadFile(MultipartFile file);
    List<String> getNameFiles();
    InputStream getFile(String fileName);
}
