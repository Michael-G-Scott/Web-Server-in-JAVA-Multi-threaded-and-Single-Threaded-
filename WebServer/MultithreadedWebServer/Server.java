package MultithreadedWebServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server{
    public Consumer<Socket> getConsumer(){
        return (serversocket)->{
            try{
                PrintWriter toClient = new PrintWriter(serversocket.getOutputStream(), true);
                toClient.println("herro from server");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        };

    }
    public static void main(String[] args) throws IOException{
        int port=8090;
        Server server = new Server();
        try{
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(60000);
        System.out.println("Server is listening on port: " + port);
        while(true){
            Socket serversocket = socket.accept();
            Thread thread = new Thread(()->server.getConsumer().accept(serversocket));
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}