import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

public class Server {
    private final static int PORT = 80;

	public static void main(String[] args){
		try{
            ServerSocket serverSocket = new ServerSocket(PORT);
			while(true){
				new ClientThread(serverSocket.accept()).run();
			}
		} catch (IOException ioe){
			System.out.println(Arrays.toString(ioe.getStackTrace()));
		}
	}

}
