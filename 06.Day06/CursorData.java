public class CursorData {
    private char direction;
    private int hLoc;
    private int vLoc;
    

    CursorData(char d, int h, int v){
        direction = d;
        hLoc = h;
        vLoc = v;
    }

    public char getDirection(){
        return direction;
    }

    public int[] getLocation(){
        //horizontal then vertical
        int[] pair = new int[2];
        pair[0] = hLoc;
        pair[1]= vLoc;
        return pair;
    }

    public void moveCursor(){
        if(direction == '^'){
            vLoc--;
        }
        if(direction == 'v'){
            vLoc++;
        }
        if(direction == '>'){
            hLoc++;
        }
        if(direction == '<'){
            hLoc--;
        }
    }

    public int[] futureMoveCursor(){
        int[] temp = getLocation();
        if(direction == '^'){
            temp[1]--;
        }
        if(direction == 'v'){
            temp[1]++;
        }
        if(direction == '>'){
            temp[0]++;
        }
        if(direction == '<'){
            temp[0]--;
        }
        return temp;
    }

    public void turnRight(){
        if(direction == '^'){
            direction = '>';
        }
        else if(direction == '>'){
            direction = 'v';
        }
        else if(direction == 'v'){
            direction = '<';
        }
        else if(direction == '<'){
            direction = '^';
        }
    }

    public boolean equals(CursorData other){
        if(other == null || this == null){
            return false;
        }
        if(other.getLocation()[0] == this.getLocation()[0]){
            if(other.getLocation()[1] == this.getLocation()[1]){
                if(other.getDirection() == this.getDirection()){
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(){
        return "[Cursor:"+direction+" Horizontal:"+hLoc+" Vertical:"+vLoc+" ]";
    }
}
