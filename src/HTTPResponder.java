import java.io.*;

public class HTTPResponder {
    private OutputStream out;
    private InputStream in;
    private HTTPRequest request;

    public HTTPResponder(OutputStream out, InputStream in, HTTPRequest request){
        this.out = out;
        this.in = in;
        this.request = request;
    }

    public boolean respond(){
        try {
            String tmp="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String  dataOut = "";
            try {
                while (true){
                    tmp = reader.readLine();
                    if (tmp==null)
                        break;
                    dataOut +=(tmp);
                }
            }catch (NullPointerException e){}
            PrintWriter pw = new PrintWriter(out);
            pw.println("HTTP/1.1 200 OK\n" +
                    "Date: Mon, 27 Jul 2009 12:28:53 GMT\n" +
                    "Server: Halex/1.0.0 (Win64)\n" +
                    "Last-Modified: " + Constants.HTTP_LAST_MODIFIED + "\n" +
                    "Content-Length: " + dataOut.length() + "\n" +
                    "Content-Type: text/html\n" +
                    "Connection: Closed\n");
            pw.println(dataOut);
            pw.flush();
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
