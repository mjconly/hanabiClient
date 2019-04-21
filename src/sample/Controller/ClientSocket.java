package sample.Controller;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.rmi.server.ExportException;
import java.time.Instant;
import java.util.ArrayList;
import java.net.Socket;

/**
 * This class will be instantiated and run as a background thread. The responsiblity
 * of this class is to establish a socket connection with the server and initiate
 * a ServerListener thread to read messages off the stream from the server. The
 * ClientSocket thread will be responsible for sending client requests to the server
 * and sending server responses to the client
 */
public class ClientSocket implements Runnable {
    /** controller to receive client requests from and send server responses to **/
    ClientController controller;
    /** controller will update this attribute with a JSON request **/
    JSONObject clientRequest;
    /** list of JSON objects representing server responses **/
    ArrayList<JSONObject> serverResponse;
    /** Flag indicating that there is a client request **/
    boolean toSend;
    /** flag indicating that the clientSocket thread should continue in while loop **/
    boolean spinCS = true;
    /** Instance of helper thread **/
    ServerListener helper;
    /** Socket connection to server **/
    Socket s;

    boolean testSent;

    public ClientSocket(){
        this.serverResponse = new ArrayList<>();
        testSent = false;
    }

    /**
     * Subclass: The responsibility of this class is to run as a background thread
     * and continuously make reads off of the socket input stream, when a server
     * response is read off, it will update the clientSocket thread
     */
    public class ServerListener extends Thread {
        /** socket input from clientSocket **/
        BufferedReader socketIn;
        /** flag indicating the serverListener thread to keep spinning in loop **/
        boolean spinSL;


        /** Sets the input attribute to the socketIn parameter passed in and create a new instance of ServerListener
         *
         * @param socketIn - ObjectInputStream established on socket from ClientSocket Thread
         */
        public ServerListener(BufferedReader socketIn){
            this.socketIn = socketIn;
            this.spinSL = true;
        }

        public void stopServerListener(){
            this.spinSL = false;
        }

        /**
         * run method for serverListener thread, will spin in a loop making reads from the
         * input stream, when successful will pull message from stream and send to clientSocket
         */
        @Override
        public void run(){
            try {
                while(spinCS){
                    JSONParser parse = new JSONParser();
                    String response = "";
                    String aByte = "";
                    boolean newMsg = false;
                    while (socketIn.ready() && !newMsg) {
                        aByte = Character.toString((Character.toChars(socketIn.read()))[0]);
//                        response += Character.toString(Character.toChars(socketIn.read())[0]);
                        response += aByte;
                        if (aByte.equals("}")){
                            newMsg = true;
                        }
                    }
                    if (!response.equals("")){
                        System.out.println(response);
                        JSONObject jsonReply = (JSONObject) parse.parse(response);
                        serverResponse.add(jsonReply);
                    }
                    sleep(500);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    /**
     * Stops the threads and allows them to terminate
     */
    public void stopClientSocket(){
        this.helper.stopServerListener();
        this.spinCS = false;
        try {
            this.s.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * runs the connection protocol to the server. Initiates socket, input and output streams,
     * spins up serverListener thread, and then enters a while true loop to send to or receive
     * from client
     * @param local server url
     */
    private void connect(String local) {

        BufferedReader input;
        PrintStream output;


        try {

            s = new Socket(local, 10219);

            System.out.println("Client Socketed Connected");

            output = new PrintStream(s.getOutputStream());
            output.flush();
            input = new BufferedReader(new InputStreamReader(s.getInputStream()));

            //spin up helper thread to always listen for input from server
            helper = new ServerListener(input);
            helper.start();

            while(this.spinCS){
                if(toSend){
                    System.out.println("Sending create msg from ClientSocket to server");
                    output.print(clientRequest);
                    System.out.println("Message Sent!" + clientRequest.toString());
                    output.flush(); // Indicates end of output stream
                    toSend = false;
                    testSent = true;
                }else if(!serverResponse.isEmpty()){
                    JSONObject response = serverResponse.get(0); // grab from queue
                    serverResponse.remove(0);   // remove it from queue

                    System.out.println("Testing response from server in ClientSocket");
                    System.out.println(response.toString());

                    //Commented out the line below to run from Main (no handleResponse yet) -- Mike
                    this.controller.handleResponse(response);   // Tells controller to handle response

                }else{
                    Thread.sleep(200);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * ClientController will call this method to send the ClientSocket a client
     * request that needs to be sent to the server
     * @param fromController
     */
    public void sendToServer(JSONObject fromController){
        clientRequest = fromController;
        toSend = true;
    }

    /**
     * sets the controller attribute
     * @param control an instance of clientController
     */
    public void setController(ClientController control){
        this.controller = control;
    }

    /**
     * Runs the clientSocket connect method
     */
    @Override
    public void run(){
        //runs the connect method
        this.connect("gpu2.usask.ca");
    }


}

