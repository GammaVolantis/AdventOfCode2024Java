import java.io.IOException;
import java.util.ArrayList;

class Driver{
    public static void main(String[] args) throws IOException {
        String test1 = "Test1.txt";
        String test2 = "Test2.txt";
        String test3 = "Test3.txt";
        String input = "Input.txt";
        int testPt1 = HiddenMain(test1);
        int testPt2 = TraverseMapData(test1);
        int test2Pt2 = TraverseMapData(test2);
        int test3Pt2 = TraverseMapData(test3);
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
        if(testPt2==6 && test2Pt2==3 && test3Pt2 == 1){
            System.out.println("\nSuccess!");
            System.out.println(TraverseMapData(input));
            //NOT 541 (Too Low), 2018 (Too High), 1933 (Too High)
        }
        else{
            System.out.println("\nFailure---First:" + testPt2 + " Second:" + test2Pt2 + " Third:" + test3Pt2);
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
        SetAtLocation(data, cursor.getLocation(),'x');        
        while(cursor.futureMoveCursor()[0]<data.get(0).length() && cursor.futureMoveCursor()[0]>-1 && cursor.futureMoveCursor()[1]<data.size() && cursor.futureMoveCursor()[1]>-1){
            char nextLoc = CheckMapLocation(data, cursor.futureMoveCursor());
            if(nextLoc=='.' || nextLoc=='x'){
                cursor.moveCursor();
                SetAtLocation(data, cursor.getLocation(),'x');
            }
            else if(nextLoc=='#'){
                cursor.turnRight();
            }
            //System.out.println(cursor.toString());
        }
        return data;
    }

    public static int TraverseMapData(String file) throws IOException{
        int finalScore = 0;
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
        SetAtLocation(data, cursor.getLocation(),'x');
        ArrayList<CursorData> postTurns = new ArrayList<>();
        //obstacle locations
        ArrayList<int[]> obsLoc= new ArrayList<>();         
        while(NotOffGrid(cursor, data)){
            char nextLoc = CheckMapLocation(data, cursor.futureMoveCursor());
            if(nextLoc=='.' || nextLoc == 'x'){
                //CHECK TO SEE IF THERE ARE ANY CURSOR LOCATIONS ON THE OTHER DIRECTIONS
                if(nextLoc!='x' && CheckPossibleInfinatesV2(data, postTurns, cursor)){
                    int[] temp = cursor.futureMoveCursor();
                    boolean contained = false;
                    for(int[] o : obsLoc){
                        if(temp[0] == o[0] && temp[1] == o[1]){
                            contained = true;
                        }
                    }
                    if(!contained){
                        finalScore++;
                        obsLoc.add(temp);
                    }
                }
                SetAtLocation(data,cursor.getLocation(),'x');
                cursor.moveCursor();
            }
            else if(nextLoc=='#'){
                //PUT A CURSOR IN THE ARRAY OF CURSOR LOCATIONS.
                postTurns.add(new CursorData(cursor.getDirection(), cursor.getLocation()[0], cursor.getLocation()[1]));
                cursor.turnRight();
            }
            //System.out.println(cursor.toString());
        }
        return finalScore;
    }

    public static char CheckMapLocation(ArrayList<String> data, int[] location){
        return data.get(location[1]).charAt(location[0]);
    }

    public static void SetAtLocation(ArrayList<String> data, int[] location, char var){
        String temp = data.get(location[1]);
        String newString = "";
        for(int i=0; i<temp.length(); i++){
            if(i==location[0]){ 
                newString+=var;
            }
            else{
                newString+=temp.charAt(i);
            }
        }
        data.set(location[1], newString);
    }

    public static boolean NotOffGrid(CursorData cursor, ArrayList<String> data){
        return (cursor.futureMoveCursor()[0]<data.get(0).length() && cursor.futureMoveCursor()[0]>-1 && cursor.futureMoveCursor()[1]<data.size() && cursor.futureMoveCursor()[1]>-1);
    }
    
    public static boolean CheckPossibleInfinates(ArrayList<String> map, ArrayList<CursorData> data, CursorData current){
        char checkDire;
        switch(current.getDirection()){
            case'^':
                checkDire = '>';
                for(int i=0; i<data.size();i++){
                    if(data.get(i).getDirection()==checkDire && data.get(i).getLocation()[0]>current.getLocation()[0] && current.getLocation()[1]==data.get(i).getLocation()[1]){
                        boolean blocked = false;
                        for(int j=current.getLocation()[0]; j<data.get(i).getLocation()[0]; j++){
                            if(map.get(current.getLocation()[1]).charAt(j)=='#'){
                                blocked = true;
                            }
                        }
                        if(!blocked){
                            return true;
                        }
                    }
                }
                break;
            case'>':
                checkDire = 'v';
                for(int i=0; i<data.size();i++){
                    if(data.get(i).getDirection()==checkDire && data.get(i).getLocation()[1]>current.getLocation()[1] && current.getLocation()[0]==data.get(i).getLocation()[0]){
                        boolean blocked = false;
                        for(int j=current.getLocation()[1]; j<data.get(i).getLocation()[1]; j++){
                            if(map.get(j).charAt(current.getLocation()[0])=='#'){
                                blocked = true;
                            }
                        }
                        if(!blocked){
                            return true;
                        }
                    }
                }
                break;
            case'v':
                checkDire = '<';
                for(int i=0; i<data.size();i++){
                    if(data.get(i).getDirection()==checkDire && data.get(i).getLocation()[0]<current.getLocation()[0] && current.getLocation()[1]==data.get(i).getLocation()[1]){
                        boolean blocked = false;
                        for(int j=current.getLocation()[0]; j>data.get(i).getLocation()[0]; j--){
                            if(map.get(current.getLocation()[1]).charAt(j)=='#'){
                                blocked = true;
                            }
                        }
                        if(!blocked){
                            return true;
                        }
                    }
                }
                break;
            case'<':
                checkDire = '^';
                for(int i=0; i<data.size();i++){
                    if(data.get(i).getDirection()==checkDire && data.get(i).getLocation()[1]<current.getLocation()[1] && current.getLocation()[0]==data.get(i).getLocation()[0]){
                        boolean blocked = false;
                        for(int j=current.getLocation()[1]; j>data.get(i).getLocation()[1]; j--){
                            if(map.get(j).charAt(current.getLocation()[0])=='#'){
                                blocked = true;
                            }
                        }
                        if(!blocked){
                            return true;
                        }
                    }
                }
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean CheckPossibleInfinatesV2(ArrayList<String> map, ArrayList<CursorData> data, CursorData current){
        CursorData childCursor = new CursorData(current.getDirection(), current.getLocation()[0], current.getLocation()[1]);
        ArrayList<String> tempMap = new ArrayList<>();
        for(int i=0; i<map.size(); i++){
            String temp = "";
            for(int j=0; j<map.get(i).length(); j++){
                if(childCursor.futureMoveCursor()[0]==j && childCursor.futureMoveCursor()[1]==i){
                    temp+='#';
                }
                else{
                    temp+=map.get(i).charAt(j);
                }
            }
            tempMap.add(temp);
        }
        childCursor.turnRight();
        ArrayList<CursorData> checkData = new ArrayList<>();
        for(CursorData d : data){
            checkData.add(new CursorData(d.getDirection(), d.getLocation()[0], d.getLocation()[1]));
        }
        while(NotOffGrid(childCursor, tempMap)){
            char nextLoc = CheckMapLocation(tempMap, childCursor.futureMoveCursor());
            if(nextLoc=='.' || nextLoc == 'x'){
                childCursor.moveCursor();
            }
            else if(nextLoc == '#'){
                for(int i=0; i<checkData.size(); i++){
                    if(checkData.get(i).equals(childCursor)){
                        return true;
                    }
                }
                checkData.add(new CursorData(childCursor.getDirection(), childCursor.getLocation()[0], childCursor.getLocation()[1]));
                childCursor.turnRight();
            }
        }
        return false;
    }

}