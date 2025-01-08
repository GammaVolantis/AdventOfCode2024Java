import java.io.IOException;
import java.util.ArrayList;
//import java.math.BigInteger;

class Driver{
    public static void main(String[] args) throws IOException {
        String test1 = "Test1.txt";
        String file = "Input.txt";
        long test1Pt1 = HiddenMain(test1);
        long test1Pt2 = HiddenMain2(test1);
        if(test1Pt1 == 1928){
            System.out.println("Success!");
            System.out.println(HiddenMain(file));
            //NOT 1302097953 (too low), 5597065249 (too low)
        }
        else{
            System.out.println("Failure " + test1Pt1);
        }
        if(test1Pt2 == 2858){
            System.out.println("Success!");
            System.out.println(HiddenMain2(file));
        }else{
            System.out.println("Failure " + test1Pt2);
        }
    }

    public static long HiddenMain(String file) throws IOException{
        long total = 0;
        int storedData = 0;
        ArrayList<String> data = Helper.FileReader(file);
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0; i<data.getFirst().length(); i+=2){
            int fileSpace = Character.getNumericValue(data.getFirst().charAt(i));
            int emptySpace =0;
            if(i+1<data.getFirst().length()){
                emptySpace = Character.getNumericValue(data.getFirst().charAt(i+1));
            }
            for(int j=0; j<fileSpace; j++){
                String storedString = Integer.toString(storedData);
                temp.addLast(storedString);
            }
            storedData++;
            for(int j=0; j<emptySpace; j++){
                temp.addLast(".");
            }
        }
        //System.out.println(temp);
        for(int i=0; i<temp.size(); i++){
            if(temp.get(i)=="."){
                String tempString = ".";
                do{
                    tempString = temp.getLast();
                    temp.removeLast();
                }while(tempString ==".");
                if(i<temp.size()){
                    temp.set(i,tempString);
                }else{
                    temp.add(tempString);
                }
            }
        }
        //System.out.println(temp);
        int iter = 0;
        for(String t : temp){
            total+= iter*Integer.parseInt(t);
            iter++;
        }
        return total;
    }

    public static long HiddenMain2(String file) throws IOException{
        long total = 0;
        int storedData = 0;
        ArrayList<String> data = Helper.FileReader(file);
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0; i<data.getFirst().length(); i+=2){
            int fileSpace = Character.getNumericValue(data.getFirst().charAt(i));
            int emptySpace =0;
            if(i+1<data.getFirst().length()){
                emptySpace = Character.getNumericValue(data.getFirst().charAt(i+1));
            }
            for(int j=0; j<fileSpace; j++){
                String storedString = Integer.toString(storedData);
                temp.addLast(storedString);
            }
            storedData++;
            for(int j=0; j<emptySpace; j++){
                temp.addLast(".");
            }
        }
        //System.out.println(temp);
        for(int i=0; i<temp.size(); i++){
            if(temp.get(i)=="."){
                String tempString = ".";
                do{
                    tempString = temp.getLast();
                    temp.removeLast();
                }while(tempString ==".");
                if(i<temp.size()){
                    temp.set(i,tempString);
                }else{
                    temp.add(tempString);
                }
            }
        }
        //System.out.println(temp);
        int iter = 0;
        for(String t : temp){
            total+= iter*Integer.parseInt(t);
            iter++;
        }
        return total;
    }

}