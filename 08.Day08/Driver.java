import java.io.IOException;
import java.util.ArrayList;

class Driver{
    public static void main(String[] args) throws IOException {
        String file = "Input.txt";
        String test1 = "Test1.txt";
        int test1Pt1 = HiddenMain(test1);
        int test1Pt2 = HiddenMain2(test1);
        if(test1Pt1 == 14){
            System.out.println("Success!");
            System.out.println(HiddenMain(file));
        }else{
            System.out.println("Failure " + test1Pt1);
        }
        if(test1Pt2 == 34){
            System.out.println("Success!");
            System.out.println(HiddenMain2(file));
        }else{
            System.out.println("Failure " + test1Pt2);
        }

    }

    public static int HiddenMain(String file) throws IOException{
        int total = 0;
        ArrayList<String> data = Helper.FileReader(file);
        int width = data.get(0).length();
        int height = data.size();
        int[] max = {width,height};
        ArrayList<Tree> trees = SetUpTrees(data);
        ArrayList<int[]> uniqueLocs = new ArrayList<>();
        for(Tree t : trees){
            ArrayList<int[]> antinodes = t.getAntinodes();
            for(int[] a : antinodes){
                boolean match = false;
                for(int[] u : uniqueLocs){
                    if(a[0] == u[0] && a[1]==u[1]){
                        match = true;
                    }
                }
                if(!match && Contained(a, max)){
                    uniqueLocs.add(a);
                }
            } 
        }
        total = uniqueLocs.size();
        return total;
    }

    public static int HiddenMain2(String file) throws IOException{
        int total = 0;
        ArrayList<String> data = Helper.FileReader(file);
        int width = data.get(0).length();
        int height = data.size();
        int[] max = {width,height};
        ArrayList<Tree> trees = SetUpMaxTrees(data, max);
        ArrayList<int[]> uniqueLocs = new ArrayList<>();
        for(Tree t : trees){
            ArrayList<int[]> antinodes = t.getAntinodes();
            for(int[] a : antinodes){
                boolean match = false;
                for(int[] u : uniqueLocs){
                    if(a[0] == u[0] && a[1]==u[1]){
                        match = true;
                    }
                }
                if(!match && Contained(a, max)){
                    uniqueLocs.add(a);
                }
            } 
        }
        total = uniqueLocs.size();
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
    
    public static ArrayList<Tree>  SetUpMaxTrees(ArrayList<String> data, int[] max){
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
            tT.createResonantMirroredTrees(max);
            //System.out.println(tT.toString());
        }
        return tempTrees;
    }

    public static void PrintTrees(ArrayList<Tree> trees){
        for(Tree t : trees){
            System.out.println(t.toString());
        }
    }

    public static boolean Contained(int[] loc, int[] max){
        if(loc[0]>-1 && loc[0]<max[0]){
            if(loc[1]>-1 && loc[1]<max[1]){
                return true;
            }   
        }
        return false;
    }

}