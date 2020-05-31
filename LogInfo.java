package sorting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogInfo {
    private static LogInfo logInfo ;
    private String name;

    public static void  create(String name) throws IOException {
        if (logInfo == null)
            logInfo = new LogInfo(name);
    }
    public static LogInfo getLogger(){
        if (logInfo == null){
            logInfo = new LogInfo();
        }
        return logInfo;
    }
    public LogInfo(String name) throws IOException {
       this.name = name;
    }

    public LogInfo(){

    }
    public void log(String msg)  {
        System.out.print(msg);
        if (name != null){
            try(FileWriter writer = new FileWriter(new File(name),true)){
                writer.write(msg);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
