package com.storichallenge.service.impl;

import com.storichallenge.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceImplTest {
    @Autowired
    private EmailService emailService;

    @Test
    void createBodyEmail() {
        var expectedMessage2 = "-0.26 -23.59 35.25 JULY: 2 <br/>AUGUST: 2 <br/>SEPTEMBER: 1 <br/>";
        var expectedMessage = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "    <link href='https://fonts.googleapis.com/css?family=Rubik' rel='stylesheet' type='text/css'>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Rubik', Rubik, sans-serif;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "        .centered-text{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .no-margin{\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "        .no-margin-top{\n" +
                "            margin-top: 0;\n" +
                "        }\n" +
                "        .no-margin-bottom{\n" +
                "            margin-bottom: 0;\n" +
                "        }\n" +
                "        .right-box{\n" +
                "            text-align: right;\n" +
                "            padding-top: 1%;\n" +
                "            padding-right: 1%;\n" +
                "        }\n" +
                "        .left-box{\n" +
                "            text-align: left;\n" +
                "            padding-left: 1%;\n" +
                "        }\n" +
                "        .centered-box {\n" +
                "            margin: auto;\n" +
                "            width: 80%;\n" +
                "        }\n" +
                "        .banner-box{\n" +
                "            background-image: linear-gradient(182deg,#00677f,#2cd5c4);\n" +
                "            padding: 3% 0 3% 0%;\n" +
                "            color: white;\n" +
                "            text-align: center;\n" +
                "            font-size: 2em;\n" +
                "        }\n" +
                "        .body-box{\n" +
                "            border-left: 1px solid #2cd5c4;\n" +
                "            border-right: 1px solid #2cd5c4;\n" +
                "            border-bottom: 1px solid #2cd5c4;\n" +
                "            font-size: 1.17em;\n" +
                "            color: #384967;\n" +
                "        }\n" +
                "\n" +
                "        /*Small devices (landscape phones, 576px and up) */\n" +
                "        @media (min-width: 480px) {\n" +
                "            .banner-box{\n" +
                "                background-image: linear-gradient(182deg,#00677f,#2cd5c4);\n" +
                "                padding: 3% 0 3% 0%;\n" +
                "                color: white;\n" +
                "                text-align: center;\n" +
                "                font-size: 1.17em;\n" +
                "            }\n" +
                "            .body-box{\n" +
                "                border-left: 1px solid #2cd5c4;\n" +
                "                border-right: 1px solid #2cd5c4;\n" +
                "                border-bottom: 1px solid #2cd5c4;\n" +
                "                font-size: .83em;\n" +
                "                color: #384967;\n" +
                "            }\n" +
                "        }\n" +
                "        /*Medium devices (tablets, 768px and up)*/\n" +
                "        @media (min-width: 768px) {\n" +
                "            .banner-box{\n" +
                "                background-image: linear-gradient(182deg,#00677f,#2cd5c4);\n" +
                "                padding: 3% 0 3% 0%;\n" +
                "                color: white;\n" +
                "                text-align: center;\n" +
                "                font-size: 1.17em;\n" +
                "            }\n" +
                "            .body-box{\n" +
                "                border-left: 1px solid #2cd5c4;\n" +
                "                border-right: 1px solid #2cd5c4;\n" +
                "                border-bottom: 1px solid #2cd5c4;\n" +
                "                font-size: .83em;\n" +
                "                color: #384967;\n" +
                "            }\n" +
                "        }\n" +
                "        /*Large devices (desktops, 992px and up)*/\n" +
                "        @media (min-width: 992px) {\n" +
                "            .banner-box{\n" +
                "                background-image: linear-gradient(182deg,#00677f,#2cd5c4);\n" +
                "                padding: 3% 0 3% 0%;\n" +
                "                color: white;\n" +
                "                text-align: center;\n" +
                "                font-size: 3em;\n" +
                "            }\n" +
                "            .body-box{\n" +
                "                border-left: 1px solid #2cd5c4;\n" +
                "                border-right: 1px solid #2cd5c4;\n" +
                "                border-bottom: 1px solid #2cd5c4;\n" +
                "                font-size: 2em;\n" +
                "                color: #384967;\n" +
                "            }\n" +
                "        }\n" +
                "        /*Extra large devices (large desktops, 1200px and up)*/\n" +
                "        @media (min-width: 1200px) {\n" +
                "            .banner-box{\n" +
                "                background-image: linear-gradient(182deg,#00677f,#2cd5c4);\n" +
                "                padding: 3% 0 3% 0%;\n" +
                "                color: white;\n" +
                "                text-align: center;\n" +
                "                font-size: 3em;\n" +
                "            }\n" +
                "            .body-box{\n" +
                "                border-left: 1px solid #2cd5c4;\n" +
                "                border-right: 1px solid #2cd5c4;\n" +
                "                border-bottom: 1px solid #2cd5c4;\n" +
                "                font-size: 2em;\n" +
                "                color: #384967;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"centered-box\">\n" +
                "        <div class=\"banner-box\">\n" +
                "            <img class=\"\" src=\"cid:stori-logo\"/>\n" +
                "            <p class=\"centered-text no-margin\" >Statement of account</p>\n" +
                "        </div>\n" +
                "        <div class=\"body-box\">\n" +
                "            <p class=\"right-box no-margin\">Total balance is: -0.26</p>\n" +
                "\n" +
                "            <div class=\"centered-box\">\n" +
                "                <p class=\"centered-text\" >Number of transactions</p>\n" +
                "                <p class=\"centered-text\">JULY: 2 <br/>AUGUST: 2 <br/>SEPTEMBER: 1 <br/></p>\n" +
                "            </div>\n" +
                "\n" +
                "            <p class=\"left-box no-margin-bottom\">Average debit amount: -23.59</p>\n" +
                "            <p class=\"left-box no-margin-top\">Average credit amount: 35.25</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        var message = emailService.createBodyEmail("transactions.csv");
        Assertions.assertEquals(expectedMessage, message);
    }
}