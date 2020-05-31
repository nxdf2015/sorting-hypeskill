package sorting;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static sorting.DataType.*;

public class Data {
    private final DataType type;
    private final ArrayList<String> values;
    private final DataType order;

    public Data(DataType[] options) {
        this.type = options[0];
        this.order = options[1];

        this.values = new ArrayList<>();
    }



    public void addLine(String  line) {

        List<String> temp;
        switch (type){
            case WORD:
                case LONG:
                    temp=filterNumber(line);
                    break;
            case NATURAL:
                temp = Arrays.asList(line.split("\\s+"));
                break;
            default:
                temp = List.of(line);
        }

        values.addAll(temp );
    }

    private List<String> filterNumber(String line) {
        List<String> ns = Arrays.asList(line.split("\\s+"));
        List<String> temp = new ArrayList<>();
        for(String n : ns ){
            try{
               Long.parseLong(n);
               temp.add(n);
            }
            catch (NumberFormatException e){
                LogInfo logInfo = LogInfo.getLogger();
                logInfo.log("\""+ n +"\" isn't a long. It's skipped.\n");

            }
        }
        return temp;
    }


    public int size(){
        return values.size();
    }

    public DataType getType(){
        return type;
    }

    public long repetition(){
        long  max = getMax();
        return values.stream().filter(n -> toNumber(n)  == max).count();
    }
    public Function<String,Long> valueKey(){
        if (type == LONG || type == NATURAL){
            return Long::parseLong;
        }
        else {
            return s -> (long)s.length();
        }
    }
    public String findMax() {

       return   values.stream().max(Comparator.comparing(valueKey())).get();
    }

    private long toNumber(String s) throws NumberFormatException{

            return (long) ((type == LONG) ? Long.parseLong(s) : s.length());



    }


    private List<Long> listToNumber(List<String> ls){
        List<Long> numbers = new ArrayList<>();
        for (String l : ls){
            try {
                numbers.add(toNumber(l));
            }
            catch (NumberFormatException e){
                LogInfo logInfo = LogInfo.getLogger();
                String msg = "\""+ l +"\" isn't a long. It's skipped.\n";
                logInfo.log(msg);
                    continue;
            }
        }
        return numbers;

    }

    public long getMax(){
        return toNumber(findMax());
    }




    public void result(){
        long max =  getMax();
        long repetition =  repetition();
        String s =  findMax();
        String suffix = getType() == LONG ? "greatest " : "longest ";
        System.out.println("Total "+ getType()+"s: "+  size()+".");
        System.out.print("The "+suffix + " " + getType()+": ");
        if ( getType() == LINE ){
            System.out.println();
        }
        System.out.print(s);
        if (getType() != LINE){
            System.out.print(" ");
        }
        else {
            System.out.println();
        }
        System.out.println("(" +repetition+" time(s), "+(100* repetition/ size())+"%).");
    }


     public void getData(){
        if (order == BYCOUNT){
            if(type == LONG){
                Counter<Long> counter = new Counter(listToNumber(values));
                counter.show();
            }
            else {
                Counter<String> counter = new Counter(values);
                counter.show();
            }


   }
         else if (order == DataType.NATURAL) {

                List<String> result = sortData();

                System.out.println("Total "+type+"s: " + result.size() + ".");
                System.out.print("Sorted data: ");
                for (String x : result) {
                    System.out.printf(" %s", x);
                }


        }
         else {
             result();
         }
     }

    private List<String> sortData() {

        if ( type == LONG ){
            Collections.sort(values, Comparator.comparing(Long::parseLong));
        }
        else{
            Collections.sort(values);
        }
        return values;
    }

    public void load(String nameinput) {
        try(BufferedReader in = new BufferedReader(new FileReader((new File(nameinput))))) {
            in.lines().forEach(line -> addLine(line));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String nameoutput) {
        try(FileWriter out = new FileWriter(new File(nameoutput))){
            //out.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




