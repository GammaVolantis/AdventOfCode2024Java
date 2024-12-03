import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Helper {
    //returns a string array
    public static ArrayList<String> FileReader(String file) throws IOException{
        ArrayList<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(file))){
            String line;
            while((line = reader.readLine())!=null){
                lines.add(line);
            }
        }
        return lines;
    }
    
    public static String replaceStringData(String string){
        String[] wordNumber = {"one","two","three","four","five","six","seven","eight","nine"};
        String[] replacement = {"o1e","t2o","t3e","f4r","f5e","s6x","s7n","e8t","n9e"};
        int[] indexOfFirst = new int[9];
        boolean finished = true;
        do{        
            finished = true;
            for(int i = 0; i<wordNumber.length; i++){
                indexOfFirst[i] = string.lastIndexOf(wordNumber[i]);
            }
            int lowest=-1;
            int lowIndex=-1;
            for(int i=0; i<indexOfFirst.length; i++){
                if(indexOfFirst[i]!=-1){
                    finished = false;
                }
                if(lowest == -1 && !finished){
                    lowest = indexOfFirst[i];
                    lowIndex=i;
                }else if(indexOfFirst[i]>-1&&lowest>-1 && indexOfFirst[i]<lowest){
                    lowest = indexOfFirst[i];
                    lowIndex=i;
                }
            }
            if(lowest!=-1) 
                string = string.replace(wordNumber[lowIndex], replacement[lowIndex]);
        }while(!finished);
        return string;
    }
}
