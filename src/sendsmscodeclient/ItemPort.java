/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendsmscodeclient;

import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Nguyen Tien Hoa
 */
public class ItemPort {
    
    private int stt;
    private String port;
    private String model;
    private boolean chooser;
    private SerialModemGateway gateway;

    public SerialModemGateway getGateway() {
        return gateway;
    }

    public void setGateway(SerialModemGateway gateway) {
        this.gateway = gateway;
    }
    
    

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isChooser() {
        return chooser;
    }

    public void setChooser(boolean chooser) {
        this.chooser = chooser;
    }

    public ItemPort(){}
    
    public ItemPort(int stt, String port, String model, boolean chooser,SerialModemGateway gateway) {
        this.stt = stt;
        this.port = port;
        this.model = model;
        this.chooser = chooser;
        this.gateway = gateway;
    }
    
    
    
}
