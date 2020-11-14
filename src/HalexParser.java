import java.io.*;

public class HalexParser {
    private InputStream data;
    private String Dump;
    private long length;
    private OutputStream parsedData;

    public HalexParser(InputStream data){
        this.data = data;
        this.length = length;
        try {
            Dump = randName(64);
            parsedData = new FileOutputStream(new File(Constants.HTTP_DUMP_DIR + Dump));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parse(){
        try {
            //TEMPORARY BEGIN
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(data));
                data.transferTo(parsedData);
            }
            //TEMPORARY END
            parsedData.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputStream getParsedData() {
        try {
            return new FileInputStream(new File(Constants.HTTP_DUMP_DIR + Dump));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String randName(int length){
        String name= "";
        for(int i=0;i<length;i++)
            name += (char)((int)(Math.random() * 26) + 65);
        return name;
    }
}
