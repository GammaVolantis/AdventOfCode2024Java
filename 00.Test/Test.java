import java.io.IOException;
import java.util.ArrayList;

class Test{

    public static void main(String[] args) throws IOException {
        String file = "InputDay012023.txt";
        ArrayList<String> data = Helper.FileReader(file);
        int val1=0;
        int val2=0;
        long output = 0;
        for (int i = 0; i<data.size();i++){
            data.set(i, Helper.replaceStringData(data.get(i)));
        }
        for (String string : data) {
            for(int i = 0; i<string.length(); i++){
                char c = string.charAt(i);
                if(Character.isDigit(c)){
                    if (val1==0){
                        val1 = c-48;
                        break;
                    }
                }
            }
            for(int i = string.length()-1; i>-1; i--){
                char c = string.charAt(i);
                if(Character.isDigit(c)){
                    if (val2==0){
                        val2 = c-48;
                        break;
                    }
                }
            }
            output+= (val1*10)+val2;
            val1=0;
            val2=0;
        }
        System.out.println(output);
        //wrong answers: output < 54617
        //try 54649:first     54679:second
    }

}