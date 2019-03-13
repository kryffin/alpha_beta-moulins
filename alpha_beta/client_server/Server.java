package alpha_beta.client_server;

import java.io.*;
import java.net.*;

public class Server {
    static final int port = 18080;
    ServerSocket s;
    Socket soc;
    BufferedReader plec;
    PrintWriter pred;

    public Server() throws Exception { // Vous trouverez votre ip avec la commande "ifconfig" dans le paragraphe "eth0" à la ligne "inet adr:w.x.y.z"
        s = new ServerSocket(port);
        Socket soc = s.accept();

        System.out.println("SOCKET = " + soc);
        plec = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())),true);
    }

    public void send(String move) throws Exception {
        if(move.length() == 3){
            pred.println(move);
        } else {
            throw new Exception("Moves must be 3 characters long !\n You sent "+ move);
        }
    }

    public String receive() throws Exception {
        String str = plec.readLine();
        if(str.length() == 3){
            return str;
        } else {
            throw new Exception("Moves must be 3 characters long !\n You received "+ str);
        }

    }

    public static void main(String[] args) throws Exception {
        Server serveur = new Server();
        while (true) {
            String str = serveur.receive();          // lecture du message
            if (str.equals("ZZZ")) {
                System.out.println(str);
                break;
            }
            System.out.println("Position à Ajouter = " + str.charAt(0));   // trace locale
            System.out.println("Position à Enlever = " + str.charAt(1));   // trace locale
            System.out.println("Position à Capturer = " + str.charAt(2));   // trace locale
            serveur.send("AZZ");                     // renvoi d'un écho
        }
    }
}