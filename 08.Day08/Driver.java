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
        ArrayList<Tree> trees = SetUpTrees(data);
        PrintTrees(trees);
        return total;
    }

    public static ArrayList<Tree>  SetUpTrees(ArrayList<String> data){
        ArrayList<Tree> tempTrees = new ArrayList<>();
        for(int y=0; y<data.size(); y++){
            for(int x=0; x<data.get(y).length(); x++){
                char key = data.get(y).charAt(x);
                if(key!='.'){
                    boolean keyAdded = false;
                    int[] tempLoc = {x,y};
                    for(Tree tT : tempTrees){
                        if(tT.getID()==key){
                            tT.addNode(tempLoc);
                            keyAdded = true;
                        }
                    }
                    if(!keyAdded){
                        Tree temp = new Tree(key, tempLoc);
                        tempTrees.add(temp);
                    }
                }
            }
        }
        for(Tree tT : tempTrees){
            tT.createMirroredTrees();
        }
        return tempTrees;
    }

    public static void PrintTrees(ArrayList<Tree> trees){
        for(Tree t : trees){
            System.out.println(t.toString());
        }
    }

}