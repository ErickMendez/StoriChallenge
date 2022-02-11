package com.storichallenge.service.impl;

import com.storichallenge.model.Transaction;
import com.storichallenge.model.TransactionAmount;
import com.storichallenge.model.TransactionRecord;
import com.storichallenge.model.TransactionType;
import com.storichallenge.service.EmailService;
import com.storichallenge.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private FileService fileService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${spring.mail.properties.mail.subject}")
    private String subjectEmail;

    @Override
    public void sendEmail(String to, String fileName) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subjectEmail);
            helper.setText(this.createBodyEmail(fileName), true);
            helper.addInline("stori-logo", new ClassPathResource("/images/stori-white.png"), "image/png");
            javaMailSender.send(message);
        }
        catch (MessagingException e){
            System.out.println("Error sending email");
            throw new RuntimeException("Error sending email");
        }
    }

    @Override
    public void sendEMail(String[] to, String fileName) {

    }

    @Override
    public String createBodyEmail(String fileName) {
        var transactionRecords = fileService.readFile(fileName);

        var totalBalance = getTotalBalance(transactionRecords);
        var debitAverage = getDebitAverage(transactionRecords);
        var creditAverage = getCreditAverage(transactionRecords);

        var transactionsByMonth = getTransactionAmount(transactionRecords).stream().map(transactionAmount -> {
            var month = transactionAmount.getMonth().name();
            return month + ": " + transactionAmount.getAmount() + " <br/>";
        }).reduce("", (accum, value) -> accum+value);

        Context context = new Context();
        context.setVariable("totalBalance", totalBalance);
        context.setVariable("transactionsByMonth", transactionsByMonth);
        context.setVariable("debitAverage", debitAverage);
        context.setVariable("creditAverage", creditAverage);

        var content = templateEngine.process("EmailTemplate.html", context);
        //return totalBalance.toString() + " " + debitAverage + " " + creditAverage + " " + transactionsByMonth;
        return content;
    }

    private BigDecimal getTotalBalance(List<TransactionRecord> transactionRecords) {
        return transactionRecords.stream()
                .map(TransactionRecord::getTransaction)
                .map(this::negateValueAccordingToTransactionType)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal negateValueAccordingToTransactionType(Transaction transaction) {
        var type = transaction.getType();
        var value = transaction.getValue();

        if (TransactionType.DEBIT.equals(type)) {
            return value.negate();
        }
        return value;
    }

    private BigDecimal getDebitAverage(List<TransactionRecord> transactionRecords) {
        var transactionDebit = transactionRecords.stream()
                .map(TransactionRecord::getTransaction)
                .filter(transaction -> TransactionType.DEBIT.equals(transaction.getType()))
                .collect(Collectors.toList());

        var elements = new BigDecimal(transactionDebit.size());
        var val = transactionDebit.stream()
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(elements, 2, RoundingMode.HALF_EVEN)
                .negate();

        return val;
    }

    private BigDecimal getCreditAverage(List<TransactionRecord> transactionRecords) {
        var transactionCredit = transactionRecords.stream()
                .map(TransactionRecord::getTransaction)
                .filter(transaction -> TransactionType.CREDIT.equals(transaction.getType()))
                .collect(Collectors.toList());

        var elements = new BigDecimal(transactionCredit.size());
        return transactionCredit.stream()
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(elements, 2, RoundingMode.HALF_EVEN);
    }

    private List<TransactionAmount> getTransactionAmount(List<TransactionRecord> transactionRecords) {
        return IntStream.range(1, 13).mapToObj(monthNumber -> {
                    var month = Month.of(monthNumber);
                    var monthDays = transactionRecords.stream()
                            .filter(transactionRecord -> this.isSameMonth(transactionRecord, month))
                            .collect(Collectors.toList());
                    if (monthDays.isEmpty()) {
                        return null;
                    }

                    return new TransactionAmount(month, monthDays.size());

                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Boolean isSameMonth(TransactionRecord transactionRecord, Month month){
        return transactionRecord.getDate().getMonth().equals(month);
    }
}
