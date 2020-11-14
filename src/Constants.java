import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class Constants {
    public static final String HTTP_SERVER_DIR = "C:\\Users\\Jaysmito Mukherjee\\Desktop\\Server\\";
    static BufferedReader reader;

    static {
        try {
            reader = new BufferedReader(new FileReader(HTTP_SERVER_DIR + "env.config"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static String data;
    static {
        try {
            data = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static String splited[]= data.split("~");

    public static final String HTTP_ROOT_DIR = splited[0];
    public static final String HTTP_DUMP_DIR = splited[1];
    public static final String HTTP_LAST_MODIFIED = (LocalDateTime.now()) + "";
    public static final int HTTP_BUFFER = 8192;
    public static final String HTTP_RES_DIR = splited[2];
    public static final String HTTP_404 = "\\404.hlx";
    final public static int HTTP_PORT = Integer.parseInt(splited[3]);



    private Constants() throws IOException {}

}
