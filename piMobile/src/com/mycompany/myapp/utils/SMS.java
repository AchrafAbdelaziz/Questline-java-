/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URISyntaxException;




/**
 *
 * @author nidha
 */
public class SMS {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC7189d1488bf94cfd77848a8b1e161ab6";
    public static final String AUTH_TOKEN = "2ff98f70d635ada0c11e09279df81a44";

    public static void send() throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+21653521119"), // to
                        new PhoneNumber("+13126673106"), // from
                        "Un nouveau voyage a été ajouté ")
                .create();
        System.out.println(message.getSid());
        System.out.println("notif");
    }
}
