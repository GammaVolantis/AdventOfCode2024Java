import java.util.ArrayList;

public class Tree {
    private char identifier;
    private ArrayList<int[]> locations;
    private ArrayList<int[]> antinodes;

    Tree(char i, int[] loc){
        identifier = i;
        locations = new ArrayList<>();
        locations.add(loc);
    }

    public void addNode(int[] loc){
        locations.add(loc);
    }

    public char getID(){
        return identifier;
    }

    public ArrayList<int[]> getAntinodes(){
        return antinodes;
    }

    public void createMirroredTrees(){
        antinodes = new ArrayList<>();
        for(int[] source : locations){
            for(int[] node : locations){
                if(source!= node){
                    int x = source[0]-node[0];
                    int y = source[1]-node[1];
                    int[] antinode = {source[0]+x, source[1]+y};
                    antinodes.add(antinode);
                }
            }
        }
    }

    public void createResonantMirroredTrees(int[] max){
        int width = max[0];
        int height = max[1];
        antinodes = new ArrayList<>();
        for(int[] source : locations){
            for(int[] node : locations){
                int multi = 1;
                int xDist = source[0]-node[0];
                int yDist = source[1]-node[1];
                int x = source[0]+xDist*multi;
                int y = source[1]+yDist*multi;
                int[] antinode = {x, y};
                antinodes.add(antinode);
                while(x>0 && x<width && y>0 && y<height && xDist!=0 && yDist!=0){
                    multi+=1; 
                    x = source[0]+xDist*multi;
                    y = source[1]+yDist*multi;
                    int[] temp = {x, y};
                    antinodes.add(temp);
                }
            }
        }
    }

    public String toString(){
        String info = "";
         info+="Ident: "+identifier;
         info+="\nLocs: { ";
         for(int[] l : locations){
            info+="["+l[0]+","+l[1]+"] ";
         }
         info+="}\nAntinodes: { ";
         for(int[] a : antinodes){
            info+="["+a[0]+","+a[1]+"] ";
         }
         info+="}\n\n";
         int size = 12;
         for(int y=0; y<size; y++){
            String temp="";
            for(int x=0; x<size; x++){
                boolean addedChar = false;
                for(int[] l : locations){
                    if(l[0]==x && l[1]==y){
                        temp+=identifier;
                        addedChar = true;
                    }
                }
                if(!addedChar){
                    for(int[] a : antinodes){
                        if(a[0]==x && a[1]==y){
                            temp+='#';
                            addedChar = true;
                            break;
                        }
                    }
                }
                if(!addedChar){
                    temp+='.';
                }
            }
            temp+="\n";
            info+=temp;
         }

        return info;
    }
}