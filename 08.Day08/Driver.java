import java.io.IOException;
import java.util.ArrayList;

class Driver{
    public static void main(String[] args) throws IOException {
        String file = "Input.txt";
        String test1 = "Test1.txt";
        int test1Pt1 = HiddenMain(test1);
        if(test1Pt1 == 14){
            System.out.println("Success!");
        }else{
            System.out.println("Failure " + test1Pt1);
        }

    }

    public static int HiddenMain(String file) throws IOException{
        int total = 0;
        ArrayList<String> data = Helper.FileReader(file);
        int width = data.get(0).length();
        int height = data.size();
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                //need to get the next char then iterate through the array and make sure the point would exist within the array
            }
        }
        return total;
    }
  
    public static char getNextChar(ArrayList<String> data, int height, int width){
        return data.get(height).charAt(width);
    }

    public static ArrayList<int[]> findNextOfType(ArrayList<String> data, char core){
        ArrayList<int[]> locations = new ArrayList<>();
        int width = data.get(0).length();
        int height = data.size();
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(data.get(height).charAt(i) == core){
                    int[] temp = {j,i};
                    locations.add(temp);
                }
            }
        }
        return locations;
    }
}