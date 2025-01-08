import java.util.ArrayList;

public class Tree {
    private char identifier;
    private ArrayList<int[]> locations;
    private ArrayList<int[]> antinodes;

    Tree(char i, int[] loc){
        identifier = i;
        locations.add(loc);
    }

    public void addNode(int[] loc){
        locations.add(loc);
    }

    public char getID(){
        return identifier;
    }

    

}
