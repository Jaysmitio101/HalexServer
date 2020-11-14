import javax.sql.DataSource;
import java.io.*;
import java.net.Socket;

public class NonHtmlHTTPResponder {
    private OutputStream out;
    private InputStream in;
    private long length;
    private HTTPRequest request;

    public NonHtmlHTTPResponder(OutputStream out, File file, HTTPRequest request){
        this.out = out;
        try {
            in = new FileInputStream(file);
            length = file.length();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.in = in;
        this.request = request;
    }

    public boolean respond(){
        try {
            String tmp;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter pw = new PrintWriter(out);
            pw.println("HTTP/1.1 200 OK\n" +
                    "Date: Mon, 27 Jul 2009 12:28:53 GMT\n" +
                    "Server: Halex/1.0.0 (Win64)\n" +
                    "Last-Modified: " + Constants.HTTP_LAST_MODIFIED + "\n" +
                    "Content-Length: " + length + "\n" +
                    "Content-Type: " + request.name + "\n" +
                    "Connection: Closed\n");
            pw.flush();
            byte[] buffer = new byte[Constants.HTTP_BUFFER];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            in.close();
            out.close();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


}
