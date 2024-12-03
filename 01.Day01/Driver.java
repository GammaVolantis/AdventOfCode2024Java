import java.io.IOException;
import java.util.ArrayList;

class Driver{
    public static void main(String[] args) throws IOException {
        //Pull in data from the file
        String file = "Input.txt";
        Helper.Print("\n");
        ArrayList<String> data = Helper.FileReader(file);
        //Organize into 2 groups of data
        String[] spliter;
        ArrayList<Integer> lLeft = new ArrayList<>();
        ArrayList<Integer> lRight = new ArrayList<>();
        for(String d : data){
            spliter = d.split("   ");
            lLeft.add(Integer.parseInt(spliter[0]));
            lRight.add(Integer.parseInt(spliter[1]));
        }
        //Part 1 START
        lLeft = Helper.SortData(lLeft);
        lRight = Helper.SortData(lRight);
        int totalDist = 0;
        for(int i =0;i<lLeft.size();i++){
            int temp = lLeft.get(i)-lRight.get(i);
            if(temp<0) temp*=-1;
            totalDist+=temp;
        }
        System.out.println(totalDist);
        //Part 2 START
        int similarityScore = 0;
        for(int i : lLeft){
            int counter = 0;
            for(int j : lRight){
                if(i == j){
                    counter++;
                }
            }
            similarityScore+= i*counter;
        }
        System.out.print(similarityScore);
    }
}