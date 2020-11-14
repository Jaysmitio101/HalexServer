import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HalexServer {
    private static String Constants, HTTPResponder, HTTPRequest, HalexParser, HTTPClientManager, HTTPServer, Main, NonHtmlHTTPResponder, HTTP404, HTTPIndex, envconf, DOCS;


    public static void main(String args[]){
        initStrings();
        initConstantsString();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Halex Server\n" +
                "version - 1.0.0\n" +
                "Designed & Developed by Jaysmito Mukherjee\n" +
                "----------------------------------------------------\n");
        System.out.print("Do you want to install Halex Server in current directory?[Y/N]");
        String tmp = "";

        try {
            tmp = "" + (char)reader.read();
            if(tmp.toLowerCase().equals("y")){
                System.out.println("Installing...");
                File theDir = new File("www");
                if (!theDir.exists()){
                    theDir.mkdirs();
                }
                theDir = new File("Dump");
                if (!theDir.exists()){
                    theDir.mkdirs();
                }
                theDir = new File("Res");
                if (!theDir.exists()){
                    theDir.mkdirs();
                }
                System.out.println("Writing Classes");
                BufferedWriter bw = new BufferedWriter(new FileWriter("Constants.java"));
                bw.write(Constants);
                bw.close();
                bw = new BufferedWriter(new FileWriter("env.config"));
                bw.write(envconf);
                bw.close();
                bw = new BufferedWriter(new FileWriter("HalexParser.java"));
                bw.write(HalexParser);
                bw.close();
                bw = new BufferedWriter(new FileWriter("HTTPClientManager.java"));
                bw.write(HTTPClientManager);
                bw.close();
                bw = new BufferedWriter(new FileWriter("HTTPRequest.java"));
                bw.write(HTTPRequest);
                bw.close();
                bw = new BufferedWriter(new FileWriter("NonHtmlHTTPResponder.java"));
                bw.write(NonHtmlHTTPResponder);
                bw.close();
                bw = new BufferedWriter(new FileWriter("Main.java"));
                bw.write(Main);
                bw.close();
                bw = new BufferedWriter(new FileWriter("HTTPResponder.java"));
                bw.write(HTTPResponder);
                bw.close();
                bw = new BufferedWriter(new FileWriter("HTTPServer.java"));
                bw.write(HTTPServer);
                bw.close();
                System.out.println("Compiling Classes");
                String ttt= (Runtime.getRuntime().exec("javac Main.java")).toString();
                Thread.sleep(2000);
                System.out.println("Writing Files");
                bw = new BufferedWriter(new FileWriter("www\\404.hlx"));
                bw.write(HTTP404);
                bw.close();
                bw = new BufferedWriter(new FileWriter("www\\index.hlx"));
                bw.write(HTTPIndex);
                bw.close();
                bw = new BufferedWriter(new FileWriter("Res\\favicon.png"));
                bw.write("");
                bw.close();
                for(File file:finder(System.getProperty("user.dir"))){
                    file.delete();
                }
                bw = new BufferedWriter(new FileWriter("DOCS.txt"));
                bw.write(DOCS);
                bw.close();
                System.out.println("Installation Done!!\n" +
                        "Learn how to use the server from DOCS.txt\n" +
                        "We highly recommend to read the DOCS.txt before experimenting with the server!");
            }
        }catch (Exception ex){
            System.out.println("Error : "  + ex);
            System.exit(1);
        }

    }

    private static List<File> finder( String dirName){
            List<File> textFiles = new ArrayList<>();
            File dir = new File(dirName);
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith((".java"))) {
                    textFiles.add(file);
                }
            }
            return textFiles;
        }



    private static void initConstantsString() {
        Constants = "import java.io.BufferedReader;\n" +
                "import java.io.FileNotFoundException;\n" +
                "import java.io.FileReader;\n" +
                "import java.io.IOException;\n" +
                "import java.time.LocalDateTime;\n" +
                "\n" +
                "public class Constants {\n" +
                "    public static final String HTTP_SERVER_DIR = \"\";\n" +
                "    static BufferedReader reader;\n" +
                "\n" +
                "    static {\n" +
                "        try {\n" +
                "            reader = new BufferedReader(new FileReader(HTTP_SERVER_DIR + \"env.config\"));\n" +
                "        } catch (FileNotFoundException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    static String data;\n" +
                "    static {\n" +
                "        try {\n" +
                "            data = reader.readLine();\n" +
                "        } catch (Exception e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "    static String splited[]= data.split(\"~\");\n" +
                "\n" +
                "    public static final String HTTP_ROOT_DIR = splited[0];\n" +
                "    public static final String HTTP_DUMP_DIR = splited[1];\n" +
                "    public static final String HTTP_LAST_MODIFIED = (LocalDateTime.now()) + \"\";\n" +
                "    public static final int HTTP_BUFFER = 8192;\n" +
                "    public static final String HTTP_RES_DIR = splited[2];\n" +
                "    public static final String HTTP_404 = \"\\\\404.hlx\";\n" +
                "    final public static int HTTP_PORT = Integer.parseInt(splited[3]);\n" +
                "\n" +
                "\n" +
                "\n" +
                "    private Constants() throws IOException {}\n" +
                "\n" +
                "}\n";
    }

    private static void initStrings() {
        DOCS = "Welcome to Halex Server DOCS!\n" +
                "This server is completely designed and developed by Jaysmito Mukherjee.\n\n" +
                "To Deploy the server run the command: \n" +
                "java Main deploy\n" +
                "This will deploy a server according to the specifications in the env.config file.\n" +
                "The env.config files specifies the Server characteristics.\n" +
                "There are 4 parameters there.\n" +
                "1- The Web Root Directory[www]= This is a path to any directory on the computer.\n" +
                "2- The Dump Directory[Dump]= This is a internal directory and we recommend not to change this at any case!\n" +
                "3- The Resources Directory[Res]=This has some internal system Resources and should never be deleted or modified!\n" +
                "4- The HTTP Port[80]= This is the port to which the server listens.\n\n" +
                "This is a very simple Server in Java. Any one can donate to this!";
        envconf="www~Dump\\~Res~80\n" +
                "HTTP_ROOT_DIR~HTTP_DUMP_DIR~HTTP_RES_DIR~HTTP_PORT\n" +
                "MODIFY ONLY THE FIRST LINE AND DO NOT CHANGE THE ORDER AND DO NOT REMOVE THE \"~\" SEPARATORS.";
        HTTPIndex = "    \n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>404</title>\n" +
                "  <style type=\"text/css\">\n" +
                "    \n" +
                "    @import url('https://fonts.googleapis.com/css?family=Audiowide&display=swap');\n" +
                "\n" +
                "html,\n" +
                "body{\n" +
                "  margin: 0px;\n" +
                "  overflow: hidden;\n" +
                "}\n" +
                "\n" +
                "div{\n" +
                "  position: absolute;\n" +
                "  top: 0%;\n" +
                "  left: 0%;\n" +
                "  height: 100%;\n" +
                "  width: 100%;\n" +
                "  margin: 0px;\n" +
                "  background: radial-gradient(circle, #240015 0%, #12000b 100%);\n" +
                "  overflow: hidden;\n" +
                "}\n" +
                "\n" +
                ".wrap{\n" +
                "  position: absolute;\n" +
                "  left: 50%;\n" +
                "  top: 50%;\n" +
                "  transform: translate(-50%, -50%);\n" +
                "}\n" +
                "\n" +
                "h2{\n" +
                "  position: absolute;\n" +
                "  top: 50%;\n" +
                "  left: 50%;\n" +
                "  margin-top: 150px;\n" +
                "  font-size: 32px;\n" +
                "  text-transform: uppercase;\n" +
                "  transform: translate(-50%, -50%);\n" +
                "  display: block;\n" +
                "  color: #12000a;\n" +
                "  font-weight: 300;\n" +
                "  font-family: Audiowide;\n" +
                "  text-shadow: 0px 0px 4px #12000a;\n" +
                "  animation: fadeInText 3s ease-in 3.5s forwards, flicker4 5s linear 7.5s infinite, hueRotate 6s ease-in-out 3s infinite;\n" +
                "}\n" +
                "\n" +
                "#svgWrap_1,\n" +
                "#svgWrap_2{\n" +
                "  position: absolute;\n" +
                "  height: auto;\n" +
                "  width: 600px;\n" +
                "  max-width: 100%;\n" +
                "  top: 50%;\n" +
                "  left: 50%;\n" +
                "  transform: translate(-50%, -50%);\n" +
                "}\n" +
                "\n" +
                "#svgWrap_1,\n" +
                "#svgWrap_2,\n" +
                "div{\n" +
                "  animation: hueRotate 6s ease-in-out 3s infinite;\n" +
                "}\n" +
                "\n" +
                "#id1_1,\n" +
                "#id2_1,\n" +
                "#id3_1{\n" +
                "  stroke: #ff005d;\n" +
                "  stroke-width: 3px;\n" +
                "  fill: transparent;\n" +
                "  filter: url(#glow);\n" +
                "}\n" +
                "\n" +
                "#id1_2,\n" +
                "#id2_2,\n" +
                "#id3_2{\n" +
                "  stroke: #12000a;\n" +
                "  stroke-width: 3px;\n" +
                "  fill: transparent;\n" +
                "  filter: url(#glow);\n" +
                "}\n" +
                "\n" +
                "#id3_1{\n" +
                "  stroke-dasharray: 940px;\n" +
                "  stroke-dashoffset: -940px;\n" +
                "  animation: drawLine3 2.5s ease-in-out 0s forwards, flicker3 4s linear 4s infinite;\n" +
                "}\n" +
                "\n" +
                "#id2_1{\n" +
                "  stroke-dasharray: 735px;\n" +
                "  stroke-dashoffset: -735px;\n" +
                "  animation: drawLine2 2.5s ease-in-out 0.5s forwards, flicker2 4s linear 4.5s infinite;\n" +
                "}\n" +
                "\n" +
                "#id1_1{\n" +
                "  stroke-dasharray: 940px;\n" +
                "  stroke-dashoffset: -940px;\n" +
                "  animation: drawLine1 2.5s ease-in-out 1s forwards, flicker1 4s linear 5s infinite;\n" +
                "}\n" +
                "\n" +
                "@keyframes drawLine1 {\n" +
                "  0%  {stroke-dashoffset: -940px;}\n" +
                "  100%{stroke-dashoffset: 0px;}\n" +
                "}\n" +
                "\n" +
                "@keyframes drawLine2 {\n" +
                "  0%  {stroke-dashoffset: -735px;}\n" +
                "  100%{stroke-dashoffset: 0px;}\n" +
                "}\n" +
                "\n" +
                "@keyframes drawLine3 {\n" +
                "  0%  {stroke-dashoffset: -940px;}\n" +
                "  100%{stroke-dashoffset: 0px;}\n" +
                "}\n" +
                "\n" +
                "@keyframes flicker1{\n" +
                "  0%  {stroke: #ff005d;}\n" +
                "  1%  {stroke: transparent;}\n" +
                "  3%  {stroke: transparent;}\n" +
                "  4%  {stroke: #ff005d;}\n" +
                "  6%  {stroke: #ff005d;}\n" +
                "  7%  {stroke: transparent;}\n" +
                "  13% {stroke: transparent;}\n" +
                "  14% {stroke: #ff005d;}\n" +
                "  100%{stroke: #ff005d;}\n" +
                "}\n" +
                "\n" +
                "@keyframes flicker2{\n" +
                "  0%  {stroke: #ff005d;}\n" +
                "  50% {stroke: #ff005d;}\n" +
                "  51% {stroke: transparent;}\n" +
                "  61% {stroke: transparent;}\n" +
                "  62% {stroke: #ff005d;}\n" +
                "  100%{stroke: #ff005d;}\n" +
                "}\n" +
                "\n" +
                "@keyframes flicker3{\n" +
                "  0%  {stroke: #ff005d;}\n" +
                "  1%  {stroke: transparent;}\n" +
                "  10% {stroke: transparent;}\n" +
                "  11% {stroke: #ff005d;}\n" +
                "  40% {stroke: #ff005d;}\n" +
                "  41% {stroke: transparent;}\n" +
                "  45% {stroke: transparent;}\n" +
                "  46% {stroke: #ff005d;}\n" +
                "  100%{stroke: #ff005d;}\n" +
                "}\n" +
                "\n" +
                "@keyframes flicker4{\n" +
                "  0%  {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  30% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  31% {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  32% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  36% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  37% {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  41% {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  42% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  85% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  86% {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  95% {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  96% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  100%{color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "}\n" +
                "\n" +
                "@keyframes fadeInText{\n" +
                "  1%  {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  70% {color: #ff005d;text-shadow:0px 0px 14px #ff005d;}\n" +
                "  100%{color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "}\n" +
                "\n" +
                "@keyframes hueRotate{\n" +
                "  0%  {\n" +
                "    filter: hue-rotate(0deg);\n" +
                "  }\n" +
                "  50%  {\n" +
                "    filter: hue-rotate(-120deg);\n" +
                "  }\n" +
                "  100%  {\n" +
                "    filter: hue-rotate(0deg);\n" +
                "  }\n" +
                "}\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<center>\n" +
                "  <div></div>\n" +
                "<h2 style=\"margin-top:-200px;\">You have sucessfully installed<br><b>Halex Server</b></h2>\n" +
                "</center>\n" +
                "</body>\n" +
                "</html>";
        HTTP404 = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>404</title>\n" +
                "  <style type=\"text/css\">\n" +
                "    \n" +
                "    @import url('https://fonts.googleapis.com/css?family=Audiowide&display=swap');\n" +
                "\n" +
                "html,\n" +
                "body{\n" +
                "  margin: 0px;\n" +
                "  overflow: hidden;\n" +
                "}\n" +
                "\n" +
                "div{\n" +
                "  position: absolute;\n" +
                "  top: 0%;\n" +
                "  left: 0%;\n" +
                "  height: 100%;\n" +
                "  width: 100%;\n" +
                "  margin: 0px;\n" +
                "  background: radial-gradient(circle, #240015 0%, #12000b 100%);\n" +
                "  overflow: hidden;\n" +
                "}\n" +
                "\n" +
                ".wrap{\n" +
                "  position: absolute;\n" +
                "  left: 50%;\n" +
                "  top: 50%;\n" +
                "  transform: translate(-50%, -50%);\n" +
                "}\n" +
                "\n" +
                "h2{\n" +
                "  position: absolute;\n" +
                "  top: 50%;\n" +
                "  left: 50%;\n" +
                "  margin-top: 150px;\n" +
                "  font-size: 32px;\n" +
                "  text-transform: uppercase;\n" +
                "  transform: translate(-50%, -50%);\n" +
                "  display: block;\n" +
                "  color: #12000a;\n" +
                "  font-weight: 300;\n" +
                "  font-family: Audiowide;\n" +
                "  text-shadow: 0px 0px 4px #12000a;\n" +
                "  animation: fadeInText 3s ease-in 3.5s forwards, flicker4 5s linear 7.5s infinite, hueRotate 6s ease-in-out 3s infinite;\n" +
                "}\n" +
                "\n" +
                "#svgWrap_1,\n" +
                "#svgWrap_2{\n" +
                "  position: absolute;\n" +
                "  height: auto;\n" +
                "  width: 600px;\n" +
                "  max-width: 100%;\n" +
                "  top: 50%;\n" +
                "  left: 50%;\n" +
                "  transform: translate(-50%, -50%);\n" +
                "}\n" +
                "\n" +
                "#svgWrap_1,\n" +
                "#svgWrap_2,\n" +
                "div{\n" +
                "  animation: hueRotate 6s ease-in-out 3s infinite;\n" +
                "}\n" +
                "\n" +
                "#id1_1,\n" +
                "#id2_1,\n" +
                "#id3_1{\n" +
                "  stroke: #ff005d;\n" +
                "  stroke-width: 3px;\n" +
                "  fill: transparent;\n" +
                "  filter: url(#glow);\n" +
                "}\n" +
                "\n" +
                "#id1_2,\n" +
                "#id2_2,\n" +
                "#id3_2{\n" +
                "  stroke: #12000a;\n" +
                "  stroke-width: 3px;\n" +
                "  fill: transparent;\n" +
                "  filter: url(#glow);\n" +
                "}\n" +
                "\n" +
                "#id3_1{\n" +
                "  stroke-dasharray: 940px;\n" +
                "  stroke-dashoffset: -940px;\n" +
                "  animation: drawLine3 2.5s ease-in-out 0s forwards, flicker3 4s linear 4s infinite;\n" +
                "}\n" +
                "\n" +
                "#id2_1{\n" +
                "  stroke-dasharray: 735px;\n" +
                "  stroke-dashoffset: -735px;\n" +
                "  animation: drawLine2 2.5s ease-in-out 0.5s forwards, flicker2 4s linear 4.5s infinite;\n" +
                "}\n" +
                "\n" +
                "#id1_1{\n" +
                "  stroke-dasharray: 940px;\n" +
                "  stroke-dashoffset: -940px;\n" +
                "  animation: drawLine1 2.5s ease-in-out 1s forwards, flicker1 4s linear 5s infinite;\n" +
                "}\n" +
                "\n" +
                "@keyframes drawLine1 {\n" +
                "  0%  {stroke-dashoffset: -940px;}\n" +
                "  100%{stroke-dashoffset: 0px;}\n" +
                "}\n" +
                "\n" +
                "@keyframes drawLine2 {\n" +
                "  0%  {stroke-dashoffset: -735px;}\n" +
                "  100%{stroke-dashoffset: 0px;}\n" +
                "}\n" +
                "\n" +
                "@keyframes drawLine3 {\n" +
                "  0%  {stroke-dashoffset: -940px;}\n" +
                "  100%{stroke-dashoffset: 0px;}\n" +
                "}\n" +
                "\n" +
                "@keyframes flicker1{\n" +
                "  0%  {stroke: #ff005d;}\n" +
                "  1%  {stroke: transparent;}\n" +
                "  3%  {stroke: transparent;}\n" +
                "  4%  {stroke: #ff005d;}\n" +
                "  6%  {stroke: #ff005d;}\n" +
                "  7%  {stroke: transparent;}\n" +
                "  13% {stroke: transparent;}\n" +
                "  14% {stroke: #ff005d;}\n" +
                "  100%{stroke: #ff005d;}\n" +
                "}\n" +
                "\n" +
                "@keyframes flicker2{\n" +
                "  0%  {stroke: #ff005d;}\n" +
                "  50% {stroke: #ff005d;}\n" +
                "  51% {stroke: transparent;}\n" +
                "  61% {stroke: transparent;}\n" +
                "  62% {stroke: #ff005d;}\n" +
                "  100%{stroke: #ff005d;}\n" +
                "}\n" +
                "\n" +
                "@keyframes flicker3{\n" +
                "  0%  {stroke: #ff005d;}\n" +
                "  1%  {stroke: transparent;}\n" +
                "  10% {stroke: transparent;}\n" +
                "  11% {stroke: #ff005d;}\n" +
                "  40% {stroke: #ff005d;}\n" +
                "  41% {stroke: transparent;}\n" +
                "  45% {stroke: transparent;}\n" +
                "  46% {stroke: #ff005d;}\n" +
                "  100%{stroke: #ff005d;}\n" +
                "}\n" +
                "\n" +
                "@keyframes flicker4{\n" +
                "  0%  {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  30% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  31% {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  32% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  36% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  37% {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  41% {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  42% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  85% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  86% {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  95% {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  96% {color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "  100%{color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "}\n" +
                "\n" +
                "@keyframes fadeInText{\n" +
                "  1%  {color: #12000a;text-shadow:0px 0px 4px #12000a;}\n" +
                "  70% {color: #ff005d;text-shadow:0px 0px 14px #ff005d;}\n" +
                "  100%{color: #ff005d;text-shadow:0px 0px 4px #ff005d;}\n" +
                "}\n" +
                "\n" +
                "@keyframes hueRotate{\n" +
                "  0%  {\n" +
                "    filter: hue-rotate(0deg);\n" +
                "  }\n" +
                "  50%  {\n" +
                "    filter: hue-rotate(-120deg);\n" +
                "  }\n" +
                "  100%  {\n" +
                "    filter: hue-rotate(0deg);\n" +
                "  }\n" +
                "}\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div></div>\n" +
                "<svg id=\"svgWrap_2\" xmlns=\"http://www.w3.org/2000/svg\" x=\"0px\" y=\"0px\" viewBox=\"0 0 700 250\">\n" +
                "  <g>\n" +
                "    <path id=\"id3_2\" d=\"M195.7 232.67h-37.1V149.7H27.76c-2.64 0-5.1-.5-7.36-1.49-2.27-.99-4.23-2.31-5.88-3.96-1.65-1.65-2.95-3.61-3.89-5.88s-1.42-4.67-1.42-7.22V29.62h36.82v82.98H158.6V29.62h37.1v203.05z\"/>\n" +
                "    <path id=\"id2_2\" d=\"M470.69 147.71c0 8.31-1.06 16.17-3.19 23.58-2.12 7.41-5.12 14.28-8.99 20.6-3.87 6.33-8.45 11.99-13.74 16.99-5.29 5-11.07 9.28-17.35 12.81a85.146 85.146 0 0 1-20.04 8.14 83.637 83.637 0 0 1-21.67 2.83H319.3c-7.46 0-14.73-.94-21.81-2.83-7.08-1.89-13.76-4.6-20.04-8.14a88.292 88.292 0 0 1-17.35-12.81c-5.29-5-9.84-10.67-13.66-16.99-3.82-6.32-6.8-13.19-8.92-20.6-2.12-7.41-3.19-15.27-3.19-23.58v-33.13c0-12.46 2.34-23.88 7.01-34.27 4.67-10.38 10.92-19.33 18.76-26.83 7.83-7.5 16.87-13.36 27.12-17.56 10.24-4.2 20.93-6.3 32.07-6.3h66.41c7.36 0 14.58.94 21.67 2.83 7.08 1.89 13.76 4.6 20.04 8.14a88.292 88.292 0 0 1 17.35 12.81c5.29 5 9.86 10.67 13.74 16.99 3.87 6.33 6.87 13.19 8.99 20.6 2.13 7.41 3.19 15.27 3.19 23.58v33.14zm-37.1-33.13c0-7.27-1.32-13.88-3.96-19.82-2.64-5.95-6.16-11.04-10.55-15.29-4.39-4.25-9.46-7.5-15.22-9.77-5.76-2.27-11.8-3.35-18.13-3.26h-66.41c-6.14-.09-12.11.97-17.91 3.19-5.81 2.22-10.95 5.43-15.44 9.63-4.48 4.2-8.07 9.3-10.76 15.29-2.69 6-4.04 12.67-4.04 20.04v33.13c0 7.36 1.32 14.02 3.96 19.97 2.64 5.95 6.18 11.02 10.62 15.22 4.44 4.2 9.56 7.43 15.36 9.7 5.8 2.27 11.87 3.35 18.2 3.26h66.41c7.27 0 13.85-1.2 19.75-3.61s10.93-5.73 15.08-9.98 7.36-9.32 9.63-15.22c2.27-5.9 3.4-12.34 3.4-19.33v-33.15zm-16-26.91a17.89 17.89 0 0 1 2.83 6.73c.47 2.41.47 4.77 0 7.08-.47 2.31-1.39 4.48-2.76 6.51-1.37 2.03-3.14 3.75-5.31 5.17l-99.4 66.41c-1.61 1.23-3.26 2.08-4.96 2.55-1.7.47-3.45.71-5.24.71-3.02 0-5.9-.71-8.64-2.12-2.74-1.42-4.96-3.44-6.66-6.09a17.89 17.89 0 0 1-2.83-6.73c-.47-2.41-.5-4.77-.07-7.08.43-2.31 1.3-4.48 2.62-6.51 1.32-2.03 3.07-3.75 5.24-5.17l99.69-66.41a17.89 17.89 0 0 1 6.73-2.83c2.41-.47 4.77-.47 7.08 0 2.31.47 4.48 1.37 6.51 2.69 2.03 1.32 3.75 3.02 5.17 5.09z\"/>\n" +
                "    <path id=\"id1_2\" d=\"M688.33 232.67h-37.1V149.7H520.39c-2.64 0-5.1-.5-7.36-1.49-2.27-.99-4.23-2.31-5.88-3.96-1.65-1.65-2.95-3.61-3.89-5.88s-1.42-4.67-1.42-7.22V29.62h36.82v82.98h112.57V29.62h37.1v203.05z\"/>\n" +
                "  </g>\n" +
                "</svg>\n" +
                "<svg id=\"svgWrap_1\" xmlns=\"http://www.w3.org/2000/svg\" x=\"0px\" y=\"0px\" viewBox=\"0 0 700 250\">\n" +
                "  <g>\n" +
                "    <path id=\"id3_1\" d=\"M195.7 232.67h-37.1V149.7H27.76c-2.64 0-5.1-.5-7.36-1.49-2.27-.99-4.23-2.31-5.88-3.96-1.65-1.65-2.95-3.61-3.89-5.88s-1.42-4.67-1.42-7.22V29.62h36.82v82.98H158.6V29.62h37.1v203.05z\"/>\n" +
                "    <path id=\"id2_1\" d=\"M470.69 147.71c0 8.31-1.06 16.17-3.19 23.58-2.12 7.41-5.12 14.28-8.99 20.6-3.87 6.33-8.45 11.99-13.74 16.99-5.29 5-11.07 9.28-17.35 12.81a85.146 85.146 0 0 1-20.04 8.14 83.637 83.637 0 0 1-21.67 2.83H319.3c-7.46 0-14.73-.94-21.81-2.83-7.08-1.89-13.76-4.6-20.04-8.14a88.292 88.292 0 0 1-17.35-12.81c-5.29-5-9.84-10.67-13.66-16.99-3.82-6.32-6.8-13.19-8.92-20.6-2.12-7.41-3.19-15.27-3.19-23.58v-33.13c0-12.46 2.34-23.88 7.01-34.27 4.67-10.38 10.92-19.33 18.76-26.83 7.83-7.5 16.87-13.36 27.12-17.56 10.24-4.2 20.93-6.3 32.07-6.3h66.41c7.36 0 14.58.94 21.67 2.83 7.08 1.89 13.76 4.6 20.04 8.14a88.292 88.292 0 0 1 17.35 12.81c5.29 5 9.86 10.67 13.74 16.99 3.87 6.33 6.87 13.19 8.99 20.6 2.13 7.41 3.19 15.27 3.19 23.58v33.14zm-37.1-33.13c0-7.27-1.32-13.88-3.96-19.82-2.64-5.95-6.16-11.04-10.55-15.29-4.39-4.25-9.46-7.5-15.22-9.77-5.76-2.27-11.8-3.35-18.13-3.26h-66.41c-6.14-.09-12.11.97-17.91 3.19-5.81 2.22-10.95 5.43-15.44 9.63-4.48 4.2-8.07 9.3-10.76 15.29-2.69 6-4.04 12.67-4.04 20.04v33.13c0 7.36 1.32 14.02 3.96 19.97 2.64 5.95 6.18 11.02 10.62 15.22 4.44 4.2 9.56 7.43 15.36 9.7 5.8 2.27 11.87 3.35 18.2 3.26h66.41c7.27 0 13.85-1.2 19.75-3.61s10.93-5.73 15.08-9.98 7.36-9.32 9.63-15.22c2.27-5.9 3.4-12.34 3.4-19.33v-33.15zm-16-26.91a17.89 17.89 0 0 1 2.83 6.73c.47 2.41.47 4.77 0 7.08-.47 2.31-1.39 4.48-2.76 6.51-1.37 2.03-3.14 3.75-5.31 5.17l-99.4 66.41c-1.61 1.23-3.26 2.08-4.96 2.55-1.7.47-3.45.71-5.24.71-3.02 0-5.9-.71-8.64-2.12-2.74-1.42-4.96-3.44-6.66-6.09a17.89 17.89 0 0 1-2.83-6.73c-.47-2.41-.5-4.77-.07-7.08.43-2.31 1.3-4.48 2.62-6.51 1.32-2.03 3.07-3.75 5.24-5.17l99.69-66.41a17.89 17.89 0 0 1 6.73-2.83c2.41-.47 4.77-.47 7.08 0 2.31.47 4.48 1.37 6.51 2.69 2.03 1.32 3.75 3.02 5.17 5.09z\"/>\n" +
                "    <path id=\"id1_1\" d=\"M688.33 232.67h-37.1V149.7H520.39c-2.64 0-5.1-.5-7.36-1.49-2.27-.99-4.23-2.31-5.88-3.96-1.65-1.65-2.95-3.61-3.89-5.88s-1.42-4.67-1.42-7.22V29.62h36.82v82.98h112.57V29.62h37.1v203.05z\"/>\n" +
                "  </g>\n" +
                "</svg>\n" +
                "\n" +
                "<svg>\n" +
                "  <defs>\n" +
                "    <filter id=\"glow\">\n" +
                "      <fegaussianblur class=\"blur\" result=\"coloredBlur\" stddeviation=\"4\"></fegaussianblur>\n" +
                "      <femerge>\n" +
                "        <femergenode in=\"coloredBlur\"></femergenode>\n" +
                "        <femergenode in=\"SourceGraphic\"></femergenode>\n" +
                "      </femerge>\n" +
                "    </filter>\n" +
                "  </defs>\n" +
                "</svg>\n" +
                "\n" +
                "<h2>Page Not Found</h2>\n" +
                "</body>\n" +
                "</html>";
        HTTPRequest = "import java.util.ArrayList;\n" +
                "public class HTTPRequest {\n" +
                "    public String method, data, path, id, name, origin;\n" +
                "    public ArrayList<String> headers = new ArrayList<String>();\n" +
                "\n" +
                "    public HTTPRequest(String method,String path){\n" +
                "        this.method = method;\n" +
                "        this.path = path;\n" +
                "    }\n" +
                "\n" +
                "    public void addHeader(String header){\n" +
                "        headers.add(header);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return (method + \" \" + path);\n" +
                "    }\n" +
                "}\n";
        HTTPServer = "import java.io.IOException;\n" +
                "import java.net.ServerSocket;\n" +
                "import java.net.Socket;\n" +
                "import java.util.ArrayList;\n" +
                "\n" +
                "public class HTTPServer extends Thread {\n" +
                "    private boolean isActive;\n" +
                "\n" +
                "    public HTTPServer(){\n" +
                "        isActive=false;\n" +
                "    }\n" +
                "\n" +
                "    public void deploy(){\n" +
                "        isActive = true;\n" +
                "        try {\n" +
                "            ServerSocket server = new ServerSocket(Constants.HTTP_PORT);\n" +
                "            System.out.println(\"Server deployed at\\n\" +\n" +
                "                    \"http://localhost:\"+Constants.HTTP_PORT+\"\\n\" +\n" +
                "                    \"http://127.0.0.1:\"+Constants.HTTP_PORT+\"\\n\" +\n" +
                "                    \"http://\"+server.getInetAddress().getHostAddress()+\":\"+Constants.HTTP_PORT+\"\\n\" +\n" +
                "                    \"Press Ctrl+C to stop the server.\");\n" +
                "            ArrayList<HTTPClientManager> managers = new ArrayList<>();\n" +
                "            int index = 0;\n" +
                "            while (true){\n" +
                "                Socket client = server.accept();\n" +
                "                System.out.println(\"Connection Request From : \" + client.getRemoteSocketAddress().toString());\n" +
                "                managers.add(new HTTPClientManager(client, index));\n" +
                "                managers.get(index).start();\n" +
                "                index++;\n" +
                "                if(!isActive)\n" +
                "                    break;\n" +
                "            }\n" +
                "        } catch (IOException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void setActive(boolean active) {\n" +
                "        this.isActive = active;\n" +
                "    }\n" +
                "\n" +
                "    public boolean isActive() {\n" +
                "        return this.isActive;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void run() {\n" +
                "    }\n" +
                "}\n";
        HalexParser = "import java.io.*;\n" +
                "\n" +
                "public class HalexParser {\n" +
                "    private InputStream data;\n" +
                "    private String Dump;\n" +
                "    private long length;\n" +
                "    private OutputStream parsedData;\n" +
                "\n" +
                "    public HalexParser(InputStream data){\n" +
                "        this.data = data;\n" +
                "        this.length = length;\n" +
                "        try {\n" +
                "            Dump = randName(64);\n" +
                "            parsedData = new FileOutputStream(new File(Constants.HTTP_DUMP_DIR + Dump));\n" +
                "        } catch (FileNotFoundException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void parse(){\n" +
                "        try {\n" +
                "            //TEMPORARY BEGIN\n" +
                "            {\n" +
                "                BufferedReader reader = new BufferedReader(new InputStreamReader(data));\n" +
                "                data.transferTo(parsedData);\n" +
                "            }\n" +
                "            //TEMPORARY END\n" +
                "            parsedData.close();\n" +
                "        } catch (IOException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public InputStream getParsedData() {\n" +
                "        try {\n" +
                "            return new FileInputStream(new File(Constants.HTTP_DUMP_DIR + Dump));\n" +
                "        } catch (FileNotFoundException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    private String randName(int length){\n" +
                "        String name= \"\";\n" +
                "        for(int i=0;i<length;i++)\n" +
                "            name += (char)((int)(Math.random() * 26) + 65);\n" +
                "        return name;\n" +
                "    }\n" +
                "}\n";
        HTTPResponder = "import java.io.*;\n" +
                "\n" +
                "public class HTTPResponder {\n" +
                "    private OutputStream out;\n" +
                "    private InputStream in;\n" +
                "    private HTTPRequest request;\n" +
                "\n" +
                "    public HTTPResponder(OutputStream out, InputStream in, HTTPRequest request){\n" +
                "        this.out = out;\n" +
                "        this.in = in;\n" +
                "        this.request = request;\n" +
                "    }\n" +
                "\n" +
                "    public boolean respond(){\n" +
                "        try {\n" +
                "            String tmp=\"\";\n" +
                "            BufferedReader reader = new BufferedReader(new InputStreamReader(in));\n" +
                "            String  dataOut = \"\";\n" +
                "            try {\n" +
                "                while (true){\n" +
                "                    tmp = reader.readLine();\n" +
                "                    if (tmp==null)\n" +
                "                        break;\n" +
                "                    dataOut +=(tmp);\n" +
                "                }\n" +
                "            }catch (NullPointerException e){}\n" +
                "            PrintWriter pw = new PrintWriter(out);\n" +
                "            pw.println(\"HTTP/1.1 200 OK\\n\" +\n" +
                "                    \"Date: Mon, 27 Jul 2009 12:28:53 GMT\\n\" +\n" +
                "                    \"Server: Halex/1.0.0 (Win64)\\n\" +\n" +
                "                    \"Last-Modified: \" + Constants.HTTP_LAST_MODIFIED + \"\\n\" +\n" +
                "                    \"Content-Length: \" + dataOut.length() + \"\\n\" +\n" +
                "                    \"Content-Type: text/html\\n\" +\n" +
                "                    \"Connection: Closed\\n\");\n" +
                "            pw.println(dataOut);\n" +
                "            pw.flush();\n" +
                "            out.flush();\n" +
                "            in.close();\n" +
                "            out.close();\n" +
                "            return true;\n" +
                "        }catch (Exception ex){\n" +
                "            ex.printStackTrace();\n" +
                "            return false;\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        Main="\n" +
                "\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.print(\"Halex Server\\n\" +\n" +
                "                \"version - 1.0.0\\n\" +\n" +
                "                \"Designed & Developed by Jaysmito Mukherjee\\n\" +\n" +
                "                \"----------------------------------------------------\\n\");\n" +
                "        try {\n" +
                "            if(args[0].equals(\"deploy\")){\n" +
                "                HTTPServer server = new HTTPServer();\n" +
                "                server.deploy();\n" +
                "            }\n" +
                "        }catch (Exception ex){\n" +
                "\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        NonHtmlHTTPResponder="import javax.sql.DataSource;\n" +
                "import java.io.*;\n" +
                "import java.net.Socket;\n" +
                "\n" +
                "public class NonHtmlHTTPResponder {\n" +
                "    private OutputStream out;\n" +
                "    private InputStream in;\n" +
                "    private long length;\n" +
                "    private HTTPRequest request;\n" +
                "\n" +
                "    public NonHtmlHTTPResponder(OutputStream out, File file, HTTPRequest request){\n" +
                "        this.out = out;\n" +
                "        try {\n" +
                "            in = new FileInputStream(file);\n" +
                "            length = file.length();\n" +
                "        } catch (FileNotFoundException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "        this.in = in;\n" +
                "        this.request = request;\n" +
                "    }\n" +
                "\n" +
                "    public boolean respond(){\n" +
                "        try {\n" +
                "            String tmp;\n" +
                "            BufferedReader reader = new BufferedReader(new InputStreamReader(in));\n" +
                "            PrintWriter pw = new PrintWriter(out);\n" +
                "            pw.println(\"HTTP/1.1 200 OK\\n\" +\n" +
                "                    \"Date: Mon, 27 Jul 2009 12:28:53 GMT\\n\" +\n" +
                "                    \"Server: Halex/1.0.0 (Win64)\\n\" +\n" +
                "                    \"Last-Modified: \" + Constants.HTTP_LAST_MODIFIED + \"\\n\" +\n" +
                "                    \"Content-Length: \" + length + \"\\n\" +\n" +
                "                    \"Content-Type: \" + request.name + \"\\n\" +\n" +
                "                    \"Connection: Closed\\n\");\n" +
                "            pw.flush();\n" +
                "            byte[] buffer = new byte[Constants.HTTP_BUFFER];\n" +
                "            int len;\n" +
                "            while ((len = in.read(buffer)) != -1) {\n" +
                "                out.write(buffer, 0, len);\n" +
                "            }\n" +
                "            out.flush();\n" +
                "            in.close();\n" +
                "            out.close();\n" +
                "            return true;\n" +
                "        }catch (Exception ex){\n" +
                "            ex.printStackTrace();\n" +
                "            return false;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}\n";
        HTTPClientManager="\n" +
                "import java.io.*;\n" +
                "import java.net.Socket;\n" +
                "\n" +
                "public class HTTPClientManager extends Thread{\n" +
                "    private Socket client;\n" +
                "    private HTTPRequest request;\n" +
                "    private long clientId;\n" +
                "\n" +
                "    public HTTPClientManager(Socket client, long clientId){\n" +
                "        this.client = client;\n" +
                "        this.clientId = clientId;\n" +
                "        processRequest();\n" +
                "        respond();\n" +
                "    }\n" +
                "\n" +
                "    private void respond() {\n" +
                "        if (request!=null){\n" +
                "            try {\n" +
                "                if(request.path.equals(\"/\")) {\n" +
                "                    request.path += \"index.hlx\";\n" +
                "                    request.name = \"text/html\";\n" +
                "                }\n" +
                "                parsePath(request);\n" +
                "                if(request.path.indexOf(\".hlx\") > 0 || request.path.indexOf(\".html\") > 0 || request.path.indexOf(\".htm\") > 0 || request.path.indexOf(\".hml\") > 0  || request.path.indexOf(\".hypertext\") > 0 || request.path.indexOf(\".tml\") > 0){\n" +
                "                    request.name = \"text/html\";\n" +
                "                }\n" +
                "                else{\n" +
                "                    request.name = request.path.substring(request.path.lastIndexOf(\".\") +1 );\n" +
                "                    if (request.name.equals(\"jpg\"))\n" +
                "                        request.name = \"jpeg\";\n" +
                "                    else if(request.name.equals(\"png\") ||  request.name.equals(\"jpeg\")||request.name.equals(\"bmp\")||request.name.equals(\"psd\")||request.name.equals(\"jfif\")||request.name.equals(\"svg\")||request.name.equals(\"gif\") || request.name.equals(\"webp\") || request.name.equals(\"ico\"))\n" +
                "                        request.name = \"image/\" + request.name;\n" +
                "                    else if(request.name.equals(\"mp4\") ||  request.name.equals(\"mkv\")||request.name.equals(\"mov\")||request.name.equals(\"ts\")||request.name.equals(\"3gp\")||request.name.equals(\"flv\")||request.name.equals(\"m4v\") )\n" +
                "                        request.name = \"video/\" + request.name;\n" +
                "                    else if(request.name.equals(\"wmv\")||request.name.equals(\"wav\")||request.name.equals(\"mp3\") )\n" +
                "                        request.name = \"audio/\" + request.name;\n" +
                "                    else\n" +
                "                        request.name = \"text/\" + request.name;\n" +
                "                }\n" +
                "                System.out.println(\"PATH REQUESTED : \" + request.path + \" Path Name : \" + request.name);\n" +
                "                InputStream fileInputStream = new FileInputStream(new File(Constants.HTTP_ROOT_DIR + (request.path)));\n" +
                "                if(request.name.equals(\"text/html\")) {\n" +
                "                    HalexParser halexParser = new HalexParser(fileInputStream);\n" +
                "                    halexParser.parse();\n" +
                "                    HTTPResponder responder = new HTTPResponder(client.getOutputStream(), halexParser.getParsedData(), request);\n" +
                "                    responder.respond();\n" +
                "                }else{\n" +
                "                    NonHtmlHTTPResponder responder = new NonHtmlHTTPResponder(client.getOutputStream(), new File(Constants.HTTP_ROOT_DIR + request.path), request);\n" +
                "                    responder.respond();\n" +
                "                }\n" +
                "            }catch (FileNotFoundException fnfe){\n" +
                "                if (request.path.indexOf(\"favicon.ico\")>=0){\n" +
                "                    try {\n" +
                "                        NonHtmlHTTPResponder responder = new NonHtmlHTTPResponder(client.getOutputStream(), new File(Constants.HTTP_RES_DIR + \"\\\\favicon.png\"), request);\n" +
                "                        responder.respond();\n" +
                "                    } catch (IOException e) {\n" +
                "                        e.printStackTrace();\n" +
                "                    }\n" +
                "                }\n" +
                "                else{\n" +
                "                    try {\n" +
                "                        InputStream fileInputStream = new FileInputStream(new File(Constants.HTTP_ROOT_DIR + Constants.HTTP_404));\n" +
                "                        HalexParser halexParser = new HalexParser(fileInputStream);\n" +
                "                        halexParser.parse();\n" +
                "                        HTTPResponder responder = new HTTPResponder(client.getOutputStream(), halexParser.getParsedData(), request);\n" +
                "                        responder.respond();\n" +
                "                    } catch (FileNotFoundException e) {\n" +
                "                        e.printStackTrace();\n" +
                "                    } catch (IOException e) {\n" +
                "                        e.printStackTrace();\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "            } catch (IOException e) {\n" +
                "                e.printStackTrace();\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    private void parsePath(HTTPRequest request) {\n" +
                "        request.path = request.path.toLowerCase();\n" +
                "        int i = request.path.indexOf(\"?\");\n" +
                "        if(i>0){\n" +
                "            request.data = request.path.substring(i);\n" +
                "            request.path = request.path.substring(0, i);\n" +
                "        }\n" +
                "        request.path = request.path.replace(\"/\", \"\\\\\");\n" +
                "        if(request.path.charAt(request.path.length() - 1) == '\\\\')\n" +
                "            request.path = request.path.substring(0, request.path.length()-1);\n" +
                "    }\n" +
                "\n" +
                "    private void processRequest() {\n" +
                "        try {\n" +
                "            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));\n" +
                "            String tmp = reader.readLine();\n" +
                "            if (tmp==null || tmp.length()==0)\n" +
                "                return;\n" +
                "            request = new HTTPRequest(tmp.split(\" \")[0], tmp.split(\" \")[1]);\n" +
                "            if(request.method.toLowerCase().equals(\"post\"))\n" +
                "                return;\n" +
                "            request.addHeader(tmp);\n" +
                "            while (tmp!=null && tmp.length()!=0){\n" +
                "                tmp = reader.readLine();\n" +
                "                request.addHeader(tmp);\n" +
                "            }\n" +
                "            tmp =  request.headers.get(0);\n" +
                "            if(tmp==null || tmp.length()==0)\n" +
                "                return;\n" +
                "        } catch (IOException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}\n" +
                "\n";

    }
}
