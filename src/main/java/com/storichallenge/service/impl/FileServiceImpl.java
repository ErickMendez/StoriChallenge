package com.storichallenge.service.impl;

import com.storichallenge.model.Transaction;
import com.storichallenge.model.TransactionRecord;
import com.storichallenge.service.AWSS3;
import com.storichallenge.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    AWSS3 awss3;

    @Override
    public List<TransactionRecord> readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(awss3.getFile(fileName)))) {
            return br.lines().skip(1)
                    .filter(Predicate.not(String::isEmpty))
                    .map(lines -> lines.split(","))
                    .map(this::generateTransaction)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("error reading csv file" + e);
        }
    }

    private TransactionRecord generateTransaction(String[] data) {
        var id = Integer.parseInt(data[0]);
        var date = this.formatDate(data[1]);
        var transaction = this.transactionParser(data[2]);
        return new TransactionRecord(id, date, transaction);
    }

    private LocalDate formatDate(String strDate) {
        var monthDay = strDate.split("/");
        var month = Integer.parseInt(monthDay[0]);
        var day = Integer.parseInt(monthDay[1]);
        return LocalDate.of(2000, month, day);
    }

    private Transaction transactionParser(String strTransaction) {
        var type = strTransaction.charAt(0);
        var transactionValue = strTransaction.substring(1);
        var value = new BigDecimal(transactionValue);
        return new Transaction(value, type);
    }
}
