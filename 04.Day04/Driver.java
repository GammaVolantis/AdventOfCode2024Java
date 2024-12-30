import java.io.IOException;
import java.util.ArrayList;

class Driver{
    public static void main(String[] args) throws IOException {
        String file = "Test1.txt";
        HiddenMain(file);

    }
    public static int HiddenMain(String file) throws IOException{
        int total = 0;
        ArrayList<String> data = Helper.FileReader(file);
        data = DataStripper(data);
        System.out.print(data.toString());
        return total;
    }
    public static ArrayList<String> DataStripper(ArrayList<String> data){
        ArrayList<String> temp = new ArrayList<>();
        int height = data.size()-1;
        int width = data.get(0).length()-1;
        //normal read and backward read
        for(String d : data){
            temp.add(d);
            String tempS = "";
            for(int i = d.length()-1; i>-1;i--){
                tempS+=d.charAt(i);
            }
            temp.add(tempS);
        }
        //top left to bottom right
        // && top right to bottom left
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
        for(int i = height; i<-1; i--){
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
                temp.add(tempL);
                temp.add(tempR);
            }
        }
        //top to bottom and vise versa
        for(int i = 0; i<width; i++){
            String tempT = "";
            String tempB = "";
            for(int j = 0;j<height;j++){
                tempT+=data.get(i).charAt(j);
                tempB+=data.get(i).charAt(j);
            }
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

        
        return total;
    }
}