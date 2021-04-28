/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import sservice.JavaMailUtil;

/**
 *
 * @author asus
 */
public class testapi {
    public static void main(String[] args) throws Exception{
        JavaMailUtil.sendMail("testapimail77@gmail.com");
    }
}
