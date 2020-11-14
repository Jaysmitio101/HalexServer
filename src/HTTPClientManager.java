
import java.io.*;
import java.net.Socket;

public class HTTPClientManager extends Thread{
    private Socket client;
    private HTTPRequest request;
    private long clientId;

    public HTTPClientManager(Socket client, long clientId){
        this.client = client;
        this.clientId = clientId;
        processRequest();
        respond();
    }

    private void respond() {
        if (request!=null){
            try {
                if(request.path.equals("/")) {
                    request.path += "index.hlx";
                    request.name = "text/html";
                }
                parsePath(request);
                if(request.path.indexOf(".hlx") > 0 || request.path.indexOf(".html") > 0 || request.path.indexOf(".htm") > 0 || request.path.indexOf(".hml") > 0  || request.path.indexOf(".hypertext") > 0 || request.path.indexOf(".tml") > 0){
                    request.name = "text/html";
                }
                else{
                    request.name = request.path.substring(request.path.lastIndexOf(".") +1 );
                    if (request.name.equals("jpg"))
                        request.name = "jpeg";
                    else if(request.name.equals("png") ||  request.name.equals("jpeg")||request.name.equals("bmp")||request.name.equals("psd")||request.name.equals("jfif")||request.name.equals("svg")||request.name.equals("gif") || request.name.equals("webp") || request.name.equals("ico"))
                        request.name = "image/" + request.name;
                    else if(request.name.equals("mp4") ||  request.name.equals("mkv")||request.name.equals("mov")||request.name.equals("ts")||request.name.equals("3gp")||request.name.equals("flv")||request.name.equals("m4v") )
                        request.name = "video/" + request.name;
                    else if(request.name.equals("wmv")||request.name.equals("wav")||request.name.equals("mp3") )
                        request.name = "audio/" + request.name;
                    else
                        request.name = "text/" + request.name;
                }
                System.out.println("PATH REQUESTED : " + request.path + " Path Name : " + request.name);
                InputStream fileInputStream = new FileInputStream(new File(Constants.HTTP_ROOT_DIR + (request.path)));
                if(request.name.equals("text/html")) {
                    HalexParser halexParser = new HalexParser(fileInputStream);
                    halexParser.parse();
                    HTTPResponder responder = new HTTPResponder(client.getOutputStream(), halexParser.getParsedData(), request);
                    responder.respond();
                }else{
                    NonHtmlHTTPResponder responder = new NonHtmlHTTPResponder(client.getOutputStream(), new File(Constants.HTTP_ROOT_DIR + request.path), request);
                    responder.respond();
                }
            }catch (FileNotFoundException fnfe){
                if (request.path.indexOf("favicon.ico")>=0){
                    try {
                        NonHtmlHTTPResponder responder = new NonHtmlHTTPResponder(client.getOutputStream(), new File(Constants.HTTP_RES_DIR + "\\favicon.png"), request);
                        responder.respond();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        InputStream fileInputStream = new FileInputStream(new File(Constants.HTTP_ROOT_DIR + Constants.HTTP_404));
                        HalexParser halexParser = new HalexParser(fileInputStream);
                        halexParser.parse();
                        HTTPResponder responder = new HTTPResponder(client.getOutputStream(), halexParser.getParsedData(), request);
                        responder.respond();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parsePath(HTTPRequest request) {
        request.path = request.path.toLowerCase();
        int i = request.path.indexOf("?");
        if(i>0){
            request.data = request.path.substring(i);
            request.path = request.path.substring(0, i);
        }
        request.path = request.path.replace("/", "\\");
        if(request.path.charAt(request.path.length() - 1) == '\\')
            request.path = request.path.substring(0, request.path.length()-1);
    }

    private void processRequest() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String tmp = reader.readLine();
            if (tmp==null || tmp.length()==0)
                return;
            request = new HTTPRequest(tmp.split(" ")[0], tmp.split(" ")[1]);
            if(request.method.toLowerCase().equals("post"))
                return;
            request.addHeader(tmp);
            while (tmp!=null && tmp.length()!=0){
                tmp = reader.readLine();
                request.addHeader(tmp);
            }
            tmp =  request.headers.get(0);
            if(tmp==null || tmp.length()==0)
                return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

