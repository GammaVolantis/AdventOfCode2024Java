import java.io.IOException;
import java.util.ArrayList;

class Driver{
    public static void main(String[] args) throws IOException {
        String file = "Test1.txt";
        ArrayList<String> data = Helper.FileReader(file);
        int counter = 0;
        ArrayList<int[]> integerCheck = new ArrayList<>();
        for(String d : data){
            String[] line = d.split(" ");
            int[] intList = new int[line.length];
            for(int i = 0; i<line.length;i++){
                intList[i] = Integer.parseInt(line[i]);
            }
            int safe = 0;
            boolean decreasing = intList[0]>intList[1];
            boolean same = decreasing == intList[1]>intList[2];
            int[] tempList = new int[intList.length];
            int current = tempList[0];
            if(same && workable(intList, decreasing,-1)){
                counter++;
            } else if(!same && (workable(intList, decreasing,-1) || workable(intList, !decreasing,1))){
                counter++;
            }
            //System.out.println(d +":"+ counter);
        }
        System.out.println(counter);
        //357, 363, 368, 369, 407, 430 are wrong answers
        //try ???
    }
    public static boolean workable(int[] data, boolean decreasing, int ignore){
        int safe=0;
        int[] temp = new int[data.length];
        if(ignore>-1) {
            temp = new int[data.length-1];
        }
        int tempIter = 0;
        for(int j = 0;j<data.length;j++){
            if(j!=ignore){
                temp[tempIter] = data[j];
                tempIter++;
            }
        }
        int current=temp[0];
        for(int i = 1; i<temp.length;i++){
        int dist = current-temp[i];
            if(decreasing&& dist<=3 && dist>0){ 
                current=temp[i];
            }
            else if(!decreasing&& dist>=-3 && dist<0){
                current=temp[i];
            }
            else{
                safe++;
            }
        }
        return safe<2;
    }
}