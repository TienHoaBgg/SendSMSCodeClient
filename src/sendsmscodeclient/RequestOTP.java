/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendsmscodeclient;

/**
 *
 * @author Nguyen Tien Hoa
 */
public class RequestOTP {
    
    private String number;
    private String code;

    public RequestOTP() {
    }

    public RequestOTP(String number, String code) {
        this.number = number;
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
    
}
