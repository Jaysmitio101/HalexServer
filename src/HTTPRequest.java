import java.util.ArrayList;
public class HTTPRequest {
    public String method, data, path, id, name, origin;
    public ArrayList<String> headers = new ArrayList<String>();

    public HTTPRequest(String method,String path){
        this.method = method;
        this.path = path;
    }

    public void addHeader(String header){
        headers.add(header);
    }

    @Override
    public String toString() {
        return (method + " " + path);
    }
}
