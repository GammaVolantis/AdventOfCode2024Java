import java.io.IOException;
import java.util.ArrayList;

class Driver{
    public static void main(String[] args) throws IOException {
        String test1 = "Test1.txt";
        String input = "Input.txt";
        int testPt1 = HiddenMain(test1);
        int testPt2 = 0;//HiddenMain2(test1);
        //Part1
        if(testPt1 == 41){
            System.out.println("\nSuccess!");
            System.out.println(HiddenMain(input));
        }
        else{
            System.out.println("\nFailure " + testPt1);
        }
        System.out.println();
        //Part2
        if(testPt2==6){
            System.out.println("\nSuccess!");
            //System.out.println(HiddenMain2(input));
        }
        else{
            System.out.println("\nFailure " + testPt1);
        }
        System.out.println();

    }

    public static int HiddenMain(String file) throws IOException{
        int finalScore = 0;
        ArrayList<String> data = WriteMapData(file);
        for(String d : data){
            for(int i=0; i<d.length(); i++){
                if(d.charAt(i)=='x')
                    finalScore++;
            }
        }
        return finalScore;
    }
    
    public static int HiddenMain2(String file) throws IOException{
        int finalScore = 0;


        return finalScore;
    }


    public static ArrayList<String> WriteMapData(String file) throws IOException{
        ArrayList<String> data = Helper.FileReader(file);
        CursorData cursor = new CursorData('x', -1, -1);
        //Find the cursor
        for(int j=0; j<data.size(); j++){
            for(int i=0; i<data.get(j).length(); i++){
                switch (data.get(j).charAt(i)) {
                    case '^':
                    case '>':
                    case '<':
                    case 'v':
                        cursor = new CursorData(data.get(j).charAt(i), i, j);
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println(cursor.toString());
        //Decide what to do at cursor position
        SetXAtLocation(data, cursor.getLocation());        
        while(cursor.futureMoveCursor()[0]<data.get(0).length() && cursor.futureMoveCursor()[0]>-1 && cursor.futureMoveCursor()[1]<data.size() && cursor.futureMoveCursor()[1]>-1){
            char nextLoc = CheckMapLocation(data, cursor.futureMoveCursor());
            if(nextLoc=='.' || nextLoc=='x'){
                cursor.moveCursor();
                SetXAtLocation(data, cursor.getLocation());
            }
            else if(nextLoc=='#'){
                cursor.turnRight();
            }
            //System.out.println(cursor.toString());
        }
        return data;
    }

    public static char CheckMapLocation(ArrayList<String> data, int[] location){
        return data.get(location[1]).charAt(location[0]);
    }

    public static void SetXAtLocation(ArrayList<String> data, int[] location){
        String temp = data.get(location[1]);
        String newString = "";
        for(int i=0; i<temp.length(); i++){
            if(i==location[0]){ 
                newString+='x';
            }
            else{
                newString+=temp.charAt(i);
            }
        }
        data.set(location[1], newString);
    }
}