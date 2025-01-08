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

    public void createMirroredTrees(){
        antinodes = new ArrayList<>();
        for(int[] source : locations){
            for(int[] node : locations){
                int x = source[0]-node[0];
                int y = source[1]-node[1];
                int[] antinode = {source[0]+x, source[1]+y};
                antinodes.add(antinode);
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
         info+="}\n";
        return info;
    }
}