import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HTTPServer extends Thread {
    private boolean isActive;

    public HTTPServer(){
        isActive=false;
    }

    public void deploy(){
        isActive = true;
        try {
            ServerSocket server = new ServerSocket(Constants.HTTP_PORT);
            System.out.println("Server deployed at\n" +
                    "http://localhost:"+Constants.HTTP_PORT+"\n" +
                    "http://127.0.0.1:"+Constants.HTTP_PORT+"\n" +
                    "http://"+server.getInetAddress().getHostAddress()+":"+Constants.HTTP_PORT+"\n" +
                    "Press Ctrl+C to stop the server.");
            ArrayList<HTTPClientManager> managers = new ArrayList<>();
            int index = 0;
            while (true){
                Socket client = server.accept();
                System.out.println("Connection Request From : " + client.getRemoteSocketAddress().toString());
                managers.add(new HTTPClientManager(client, index));
                managers.get(index).start();
                index++;
                if(!isActive)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public void run() {
    }
}
