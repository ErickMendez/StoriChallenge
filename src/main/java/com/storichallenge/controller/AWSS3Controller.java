package com.storichallenge.controller;

import com.storichallenge.service.AWSS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/s3")
public class AWSS3Controller {

    @Autowired
    AWSS3 awsS3;

    @PostMapping(value = "/upload-file")
    public ResponseEntity<String> uploadFile(@RequestPart(value="file") MultipartFile file){
        var response = "";
        try {
            awsS3.uploadFile(file);
           response = file.getOriginalFilename() + " file successfully uploaded to Amazon S3";
           return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping (value="/name-files")
    public ResponseEntity<List<String>> getNameFiles(){
        try {
            var nameFiles = awsS3.getNameFiles();
            return ResponseEntity.status(HttpStatus.OK).body(nameFiles);
        } catch (Exception e) {
            var response = new ArrayList<String>();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
