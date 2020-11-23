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
public class ItemStatus {
    private int stt;
    private String model;
    private String reciverID;
    private String code;
    private String status;

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getReciverID() {
        return reciverID;
    }

    public void setReciverID(String reciverID) {
        this.reciverID = reciverID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ItemStatus(){}
    public ItemStatus(int stt, String model, String reciverID, String code, String status) {
        this.stt = stt;
        this.model = model;
        this.reciverID = reciverID;
        this.code = code;
        this.status = status;
    }
    
    
    
    
    
}
