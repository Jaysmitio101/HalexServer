

public class Main {
    public static void main(String[] args) {
        System.out.print("Halex Server\n" +
                "version - 1.0.0\n" +
                "Designed & Developed by Jaysmito Mukherjee\n" +
                "----------------------------------------------------\n");
        try {
            if(args[0].equals("deploy")){
                HTTPServer server = new HTTPServer();
                server.deploy();
            }
        }catch (Exception ex){

        }
    }
}
