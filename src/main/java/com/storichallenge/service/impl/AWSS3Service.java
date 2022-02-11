package com.storichallenge.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.storichallenge.service.AWSS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AWSS3Service implements AWSS3 {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Override
    public void uploadFile(MultipartFile file) {
        System.out.println("ENTADO");
        File mainFile = new File(file.getOriginalFilename());
        try (FileOutputStream stream = new FileOutputStream(mainFile)) {
            stream.write(file.getBytes());
            String newFileName = mainFile.getName();
            PutObjectRequest request = new PutObjectRequest(bucket, newFileName, mainFile);
            amazonS3.putObject(request);
        } catch (IOException e) {
           throw  new RuntimeException("error uploading file to amazon S3");
        }
    }

    @Override
    public List<String> getNameFiles() {
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucket);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        List<String> list = objects.stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
        return list;
    }

    @Override
    public InputStream getFile(String fileName) {
        S3Object object = amazonS3.getObject(bucket, fileName);
        return object.getObjectContent();
    }
}
