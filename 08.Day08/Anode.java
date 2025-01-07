public class Anode {
    private int[] location;
    //Might need the 2 below
    //int[] source1;
    //int[] source2;

    Anode(int x, int y){
        location = new int[2];
        location[0] = x;
        location[1] = y;
    }

    public boolean equals(Anode other){
        if(this == null || other == null){
            return false;
        }
        if(this.location[0] == other.location[0]){
            if(this.location[1] == other.location[1]){
                return true;
            }
        }
        return false;
    }

    
}
