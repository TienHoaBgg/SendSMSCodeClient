package sendsmscodeclient;

/**
 *
 * @author Nguyen Tien Hoa
 */

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.util.ArrayList;
import java.util.List;
import org.smslib.*;
import org.smslib.modem.SerialModemGateway;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Formatter;
import org.smslib.helper.CommPortIdentifier;
import org.smslib.helper.SerialPort;

public class SendSMSCodeClient {

    private static final String _NO_DEVICE_FOUND = "No device found";
    private final static Formatter _formatter = new Formatter(System.out);
    static CommPortIdentifier portId;
    static Enumeration<CommPortIdentifier> portList;
    static int bauds[] = {9600};//, 14400, 19200, 28800, 33600, 38400, 56000, 57600, 115200 };
    private ItemPort item;

    private Enumeration<CommPortIdentifier> getCleanPortIdentifiers() {
        return CommPortIdentifier.getPortIdentifiers();
    }

    public Socket connectSocket(String url) throws URISyntaxException {
        Socket socket = IO.socket(url);
        Emitter on = socket.on(Socket.EVENT_CONNECT, (Object... args) -> {
            socket.emit("clientSendOTP", "SendOTP connnect...");
        }).on("event", (Object... args) -> {
        }).on(Socket.EVENT_DISCONNECT, (Object... args) -> {
            socket.emit("clientSendOTP", "SendOTP disconnct...");
        });
        socket.connect();
        
       
        return socket;
    }
    
    public List<ItemPort> getCOMPorts() {
        portList = getCleanPortIdentifiers();
        List<ItemPort> listPorts = new ArrayList<>();
        int count = 1;
        while (portList.hasMoreElements()) {
            item = new ItemPort();
            portId = portList.nextElement();
            item.setStt(count);
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                item.setPort(portId.getName());
                for (int i = 0; i < bauds.length; i++) {
                    SerialPort serialPort = null;
                    try {
                        InputStream inStream;
                        OutputStream outStream;
                        int c;
                        String response;
                        serialPort = portId.open("SMSLibCommTester", 1971);
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
                        serialPort.setSerialPortParams(bauds[i], SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                        inStream = serialPort.getInputStream();
                        outStream = serialPort.getOutputStream();
                        serialPort.enableReceiveTimeout(1000);
                        c = inStream.read();
                        while (c != -1) {
                            c = inStream.read();
                        }
                        outStream.write('A');
                        outStream.write('T');
                        outStream.write('\r');
                        Thread.sleep(500);
                        response = "";
                        StringBuilder sb = new StringBuilder();
                        c = inStream.read();
                        while (c != -1) {
                            sb.append((char) c);
                            c = inStream.read();
                        }
                        response = sb.toString();
                        if (response.contains("OK")) {
                            try {
                                outStream.write('A');
                                outStream.write('T');
                                outStream.write('+');
                                outStream.write('C');
                                outStream.write('G');
                                outStream.write('M');
                                outStream.write('I');
                                outStream.write('\r');
                                outStream.write('A');
                                outStream.write('T');
                                outStream.write('+');
                                outStream.write('C');
                                outStream.write('G');
                                outStream.write('M');
                                outStream.write('M');
                                outStream.write('\r');
                                response = "";
                                c = inStream.read();
                                while (c != -1) {
                                    response += (char) c;
                                    c = inStream.read();
                                }
//                                String name = response.replaceAll("\\s+OK\\s+", "").replaceAll("\n", "").replaceAll("\r", "");
//                                item.setModel(name);
                                count++;
                                listPorts.add(item);
                            } catch (Exception e) {
                                System.out.println("Check Name: " + e);
                            }
                        } else {
                            System.out.println(_NO_DEVICE_FOUND);
                        }
                    } catch (Exception e) {
//                        System.out.print(_NO_DEVICE_FOUND);
                        e.printStackTrace();
                    } finally {
                        if (serialPort != null) {
                            serialPort.close();
                        }
                    }
                }
            }
        }
        return listPorts;
    }

    public String getModel(String port) {
        String model = "";
        try {
            SerialModemGateway gateway = new SerialModemGateway("model.com1", port, 9600, null, null);
            gateway.setInbound(true);
            gateway.setOutbound(true);
            Service.getInstance().addGateway(gateway);
            Service.getInstance().startService();
            model = gateway.getModel();
            Service.getInstance().stopService();
            Service.getInstance().removeGateway(gateway);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

//    public Boolean sendSMS(String phoneNumber, String message, String port) {
//        boolean status = false;
//        try {
//            OutboundNotification outboundNotification = new OutboundNotification();
//
//            SerialModemGateway gateway = new SerialModemGateway("", port, 9600, "", "");
//            gateway.setInbound(true);
//            gateway.setOutbound(true);
//            gateway.setSmscNumber("+84980200030");
//            Service.getInstance().setOutboundMessageNotification(outboundNotification);
//
//            String stats = Service.getInstance().getServiceStatus().toString();
//            if ("STOPPED".equals(stats)) {
//                Service.getInstance().addGateway(gateway);
//                Service.getInstance().startService();
//            }
//
//            OutboundMessage msg = new OutboundMessage(phoneNumber, message);
//            status = Service.getInstance().sendMessage(msg);
//            System.out.println("status: " + status);
//
//            Service.getInstance().stopService();
//            Service.getInstance().removeGateway(gateway);
//
//        } catch (Exception e) {
//            System.out.println("Lỗi: " + e);
//        }
//        return status;
//    }

//    public class OutboundNotification implements IOutboundMessageNotification {
//
//        public void process(AGateway gateway, OutboundMessage msg) {
//            System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
//            System.out.println(msg);
//        }
//    }
}
