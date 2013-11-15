import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClientThread implements Runnable {

    private final static DateFormat RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
    private Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            InputStream clientIn = clientSocket.getInputStream();
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientIn));

            String[] request = clientReader.readLine().split(" ");
            String url = request[1];

            if ("/".equals(url)) {
                url = "/index.html";
            }

            OutputStream clientOut = clientSocket.getOutputStream();

            try {
                File resource = new File("web-root" + url);
                dispatch200(resource, clientOut);
            } catch (FileNotFoundException fex) {
                dispatch404(clientOut);
            }

            clientSocket.close();
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private void dispatch200(File resource, OutputStream clientOut) throws IOException {

        URLConnection connection = resource.toURI().toURL().openConnection();
        InputStream fileIn = connection.getInputStream();

        // response headers
        StringBuilder responseHeaders = new StringBuilder();
        responseHeaders.append("HTTP/1.1 200 OK").append("\r\n")
                .append("Date: ").append(RFC1123.format(new Date(resource.lastModified()))).append("\r\n")
                .append("Content-Type: ").append(connection.getContentType()).append("\r\n")
                .append("Content-Length: ").append(connection.getContentLength()).append("\r\n");

        clientOut.write(responseHeaders.toString().getBytes());

        // new line
        clientOut.write("\r\n".getBytes());

        // response body
        byte[] buff = new byte[2048];
        while (true) {
            int read = fileIn.read(buff, 0, 2048);
            if (read <= 0) {
                break;
            }
            clientOut.write(buff, 0, read);
        }

        clientOut.flush();
    }

    private void dispatch404(OutputStream clientOut) throws IOException {
        PrintWriter clientWrite = new PrintWriter(clientOut);

        StringBuilder html = new StringBuilder();
        html.append("<html>").append("\r\n")
                .append("</head></head>").append("\r\n")
                .append("<body>").append("\r\n")
                .append("File Not Found!").append("\r\n")
                .append("</body>").append("\r\n")
                .append("</html>").append("\r\n");

        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 404 Not Found").append("\r\n")
                .append("Date: ").append(RFC1123.format(Calendar.getInstance().getTime())).append("\r\n")
                .append("Content-Type: text/html").append("\r\n")
                .append("Content-Length: ").append(html.length()).append("\r\n")
                .append("").append("\r\n");

        clientWrite.write(response.toString());
        clientWrite.write("\r\n");
        clientWrite.write(html.toString());

        clientWrite.flush();
    }
}
