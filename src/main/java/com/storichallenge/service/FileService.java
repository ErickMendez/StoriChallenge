package com.storichallenge.service;

import com.storichallenge.model.TransactionRecord;

import java.util.List;

public interface FileService {
    List<TransactionRecord> readLocalFile(String fileName);
    List<TransactionRecord> readAWSS3File(String fileName);

}
