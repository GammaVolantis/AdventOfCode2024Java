import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Driver{
    public static void main(String[] args) throws IOException {
        //Test AnswerPt1 == 3749
        String test1 = "Test1.txt";
        String input = "Input.txt";
        long test1Pt1 = HiddenMain(test1);
        long test1Pt2 = HiddenMain2(test1);
        //Pt 2 == 11387
        if(test1Pt1==3749){
            System.out.println("Success!");
            System.out.println(HiddenMain(input));
        }
        else{
            System.out.println("Failure " + test1Pt1);
        }

        //Test Trinary
        int val = 3;
        for(int i=0; i<Math.pow(3,val);i++){
            System.out.println(TrinaryMathConverter(i,val));
        }
        
        if(test1Pt2==11387){
            System.out.println("Success!");
            System.out.println(HiddenMain2(input));
        }
        else{
            System.out.println("Failure " + test1Pt2);
        }


    }
    
    public static long HiddenMain(String file) throws IOException{
        long finalScore = 0;
        ArrayList<String> data = Helper.FileReader(file);
        ArrayList<ValueDict> mathData = MakeArray(data);
        for(ValueDict m : mathData){
            finalScore+=m.ValidateBinaryTrue();
        }
        return finalScore;
    }

    public static long HiddenMain2(String file) throws IOException{
        long finalScore = 0;
        ArrayList<String> data = Helper.FileReader(file);
        ArrayList<ValueDict> mathData = MakeArray(data);
        for(ValueDict m : mathData){
            finalScore += m.ValidateTrinaryTrue();
        }
        return finalScore;
    }

    public static ArrayList<ValueDict> MakeArray(ArrayList<String> data){
        ArrayList<ValueDict> mathData = new ArrayList<>();
        for(String d : data){
            String tempInt = "";
            Pattern firstPat = Pattern.compile("\\d+");
            Matcher firstMatch = firstPat.matcher(d);
            if(firstMatch.find()){
                tempInt = firstMatch.group(0);
            }
            String[] splitted = d.split(" ");
            String[] tempValues = new String[splitted.length-1];
            for(int i=1; i<splitted.length; i++){
                tempValues[i-1] = splitted[i];
            }
            long finalInteger = Long.parseLong(tempInt);
            int[] finalValues = new int[tempValues.length];
            for(int i=0; i<tempValues.length; i++){
                finalValues[i] = Integer.parseInt(tempValues[i]);
            }
            ValueDict tempDict = new ValueDict(finalInteger, finalValues);
            mathData.add(tempDict);
        }
        return mathData;
    }

    public static String TrinaryMathConverter(int value, int size){
        String output ="";
        int val = value;
        while(val!=0){
            int temp=val%3;
            if (temp==0){
                output+='+';
            }
            else if(temp==1){
                output+="*";
            }
            else{
                output+="|";
            }
            val = val/3;
        }
        while(output.length()!=size){
            output+='+';
        }
        return output;
    }

}