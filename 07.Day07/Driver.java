import java.io.IOException;
import java.util.ArrayList;

class Driver{
    public static void main(String[] args) throws IOException {
        //Test AnswerPt1 == 3749
        String test1 = "Test1.txt";
        String input = "Input.txt";
        int test1Pt1 = HiddenMain(test1);
        if(test1Pt1==3749){
            System.out.println("Success!");
        }
        else{
            System.out.println("Failure " + test1Pt1);
        }


    }
    
    public static int HiddenMain(String file) throws IOException{
        int finalScore = 0;
        ArrayList<String> data = Helper.FileReader(file);

        return finalScore;
    }

}