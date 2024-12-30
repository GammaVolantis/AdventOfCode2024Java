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
    
    public static void Print(String s){
        System.out.print(s);
    }

    public static ArrayList<Integer> SortData(ArrayList<Integer> data){
        for(int i = 0; i<data.size(); i++){
            for(int j = 0;j<data.size(); j++){
                if(data.get(i)<data.get(j)){
                    // temp = i
                    Integer temp = data.get(i);
                    // i = j
                    data.set(i,data.get(j));
                    // j = temp
                    data.set(j,temp);
                }
            }
        }
        return data;
    }

}
