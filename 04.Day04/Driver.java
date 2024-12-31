import java.io.IOException;
import java.util.ArrayList;

class Driver{
    public static void main(String[] args) throws IOException {
        String file = "Input.txt";
        String test1 = "Test1.txt";
        int test1Out = HiddenMain(test1);
        int test1Out2 = HiddenMain2(test1);
        System.out.println("Part 1");
        if(test1Out==18){
            System.out.println("\nSuccess");
            System.out.println(HiddenMain(file));
        }
        else{
            System.out.println("\nFailure  Output = " + test1Out);
        }
        System.out.println("__________________________________________________");
        System.out.println("Part 2");
        if(test1Out2==9){
            System.out.println("\nSuccess");
            //not 1872
            System.out.println(HiddenMain2(file));
        }
        else{
            System.out.println("\nFailure  Output = " + test1Out2);
        }

    }
    public static int HiddenMain(String file) throws IOException{
        int total = 0;
        ArrayList<String> data = Helper.FileReader(file);
        data = DataStripper(data);
        total = CounterXMAS(data);
        return total;
    }
    public static int HiddenMain2(String file) throws IOException{
        int total = 0;
        ArrayList<String> data = Helper.FileReader(file);
        int minHeight = 1;
        int minWidth = 1;
        int maxHeight = data.size()-1;
        int maxWidth = data.get(0).length()-1;
        for(int i = minHeight; i<maxHeight; i++){
            for(int j = minWidth; j<maxWidth;j++){
                if(data.get(i).charAt(j)=='A'){
                    char tL = data.get(i-1).charAt(j-1);
                    char bR = data.get(i+1).charAt(j+1);
                    char tR = data.get(i-1).charAt(j+1);
                    char bL = data.get(i+1).charAt(j-1);
                    if((tL=='M' && bR=='S')||(tL=='S' && bR=='M')){
                        if((tR=='M' && bL=='S')||(tR=='S' && bL=='M')){
                            total++;
                        }
                    }
                }
            }
        }
        return total;
    }
    public static ArrayList<String> DataStripper(ArrayList<String> data){
        ArrayList<String> temp = new ArrayList<>();
        int height = data.size()-1;
        int width = data.get(0).length()-1;
        //normal read and backward read
        //GOOD
        for(String d : data){
            String tempS = "";
            for(int i = d.length()-1; i>-1;i--){
                tempS+=d.charAt(i);
            }
            // System.out.println(d);
            // System.out.println(tempS);
            // System.out.println("------------------");
            temp.add(d);
            temp.add(tempS);
        }
        //top left to bottom right
        // && top right to bottom left
        //GOOD
        for(int i = 0; i<height; i++){
            String tempR = "";
            String tempL = "";
            int iter = i;
            for(int j = 0; j<width; j++){
                tempR+=data.get(iter).charAt(j);
                tempL+=data.get(iter).charAt(width-j);
                if(iter<height)
                    iter++;
                else
                    break;
            }
            if(tempL.length()>3){
                temp.add(tempL);
                temp.add(tempR);
            }
        }
        //bottom left to top right
        // && bottom right to top left
        for(int i = height; i>-1; i--){
            String tempR = "";
            String tempL = "";
            int iter = i;
            for(int j = 0; j<width; j++){
                tempR+=data.get(iter).charAt(j);
                tempL+=data.get(iter).charAt(width-j);
                if(iter>0)
                    iter--;
                else
                    break;
            }
            if(tempL.length()>3){
                // System.out.println(tempL);
                // System.out.println(tempR);
                // System.out.println("------------------");
                temp.add(tempL);
                temp.add(tempR);
            }
        }
        //top to bottom and vise versa
        // GOOD
        for(int i = 0; i<=width; i++){
            String tempT = "";
            String tempB = "";
            for(int j = 0;j<=height;j++){
                tempT+=data.get(j).charAt(i);
                tempB+=data.get(height-j).charAt(i);
            }
            //System.out.println(tempT);
            //System.out.println(tempB);
            //System.out.println("------------------");
            temp.add(tempT);
            temp.add(tempB);
        }
        // two edge cases we need to handle first
        //top row diagonals down
        for(int i = 1; i<width;i++){
            String tempL="";
            String tempR="";
            int iter = i;
            for(int j = 0; j<height;j++){
                tempL+=data.get(j).charAt(width-iter);
                tempR+=data.get(j).charAt(iter);
                iter++;
                if(iter>width)
                    break;

            }
            if(tempL.length()>3){
                temp.add(tempL);
                temp.add(tempR);
            }
        }
        //bottom rown diagonals up
        for(int i = 1; i<width;i++){
            String tempL="";
            String tempR="";
            int iter = i;
            for(int j = height; j>0;j--){
                tempL+=data.get(j).charAt(width-iter);
                tempR+=data.get(j).charAt(iter);
                iter++;
                if(iter>width)
                    break;

            }
            if(tempL.length()>3){
                temp.add(tempL);
                temp.add(tempR);
            }
        }
        return temp;
    }
    public static int CounterXMAS(ArrayList<String> data){
        int total = 0;
        String xmas = "XMAS";
        for(String d : data){
            for(int i = 0; i<d.length();i++){
                if(d.length()-i>3){
                    String tempCheck = "";
                    for(int j=0;j<4;j++){
                        tempCheck+=d.charAt(i+j);
                    }
                    if(xmas.equals(tempCheck)){
                        total++;
                    }
                }
            }
        }
        return total;
    }
}