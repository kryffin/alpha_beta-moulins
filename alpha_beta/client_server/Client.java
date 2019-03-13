package alpha_beta.client_server;

import alpha_beta.model.game.Board;
import alpha_beta.model.game.Player;
import alpha_beta.model.game.State;
import alpha_beta.view.BoardView;

import java.io.*;
import java.net.*;
/** Le processus client se connecte au site fourni dans la commande
 *   d'appel en premier argument et utilise le port distant 8080.
 */
public class Client {
    static final int port = 18080;
    Socket socket;
    BufferedReader plec;
    PrintWriter pred;

    private static BoardView view;

    public Client(String ip) throws Exception { // "ip" est sous la forme "w.x.y.z" avec w,x,y,z entre 0 et 255
        socket = new Socket(ip, port);
        System.out.println("SOCKET = " + socket);
        plec = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
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
        Client client;
        if(args.length == 1){
            client = new Client(args[0]);
        } else {
            throw new Exception("Entrez l'adresse ip du serveur en argument du programme");
        }

        view = new BoardView(client);

        State s = new Board(new Player("1"), new Player("2"), view);
    }
}