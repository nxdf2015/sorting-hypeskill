package sorting;

import java.io.IOException;

public class Parse {
    public static DataType[]  getType(String[] args) throws IllegalArgumentException, IOException {

        DataType[] options = {DataType.WORD,DataType.NATURAL};
        if (args.length == 0){
            throw new IllegalArgumentException("no options!");
        }
        if (args.length >  0){
            int option = 0;
            int i = 0;
//           for(int i = 0 ; i <args.length ; i++ )
           while(i < args.length){
               if (args[i].endsWith("File") && !args[i+1].startsWith("-")){
                   i+=2;
               }
               else if (args[i].equals("-sortingType")){
                   if( !((i == args.length - 1) ||
                           (args[i + 1].startsWith("-")))
                   ){
                       options[1] = DataType.valueOf(args[i+1].toUpperCase());
                       i+=2;
                   }
                   else {
                       throw new IllegalArgumentException("No sorting type defined!");
                   }

               }
               else if (args[i].equals("-dataType")) {
                   if( !((i == args.length - 1) ||
                           (args[i + 1].startsWith("-")))
                   ){
                       options[0] = DataType.valueOf(args[i+1].toUpperCase());
                       i+=2;
                   }
                   else {
                       throw new IllegalArgumentException("No data type defined!");
                   }
               }

               else {
                   String  msg = "\"" + args[i] + "\" isn't a valid parameter. It's skipped.";
                   LogInfo logger = LogInfo.getLogger();
                   logger.log(msg);

                   i++;
               }

           }

        }
        return options;
    }

    public static String getValue(String[] args,String type) throws IllegalArgumentException {
        for(int i = 0 ; i < args.length ; i++ ){
            if (args[i].equals("-"+type)){
                if( !((i == args.length - 1) ||
                        (args[i + 1].startsWith("-")))
                ){

                    return args[i+1];
                }
                else {
                    throw new IllegalArgumentException("No data type defined!");
                }
            }
        }
        return null;
    }
}
