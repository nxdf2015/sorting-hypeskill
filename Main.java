package sorting;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataType[] options = null;
        String nameinput = null;
        String nameoutput = null ;

        try {
            options = Parse.getType(args);

             nameinput = Parse.getValue(args,"inputFile");

             nameoutput = Parse.getValue(args, "outputFile");

             LogInfo.create(nameoutput);

        }
        catch (IllegalArgumentException | IOException e){
            LogInfo logInfo = LogInfo.getLogger();
            logInfo.log(e.getMessage()+System.lineSeparator());


            System.exit(1);
        }

        Data data = new Data(options);
        if(nameinput !=null){
            data.load(nameinput);
        }
        else {
            while (scanner.hasNext()) {
                data.addLine(scanner.nextLine());

            }
        }

        data.getData();






    }


}
