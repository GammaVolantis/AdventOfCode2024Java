public class MasterVal {
    private int value;
    private int first;
    private int second;

    public MasterVal(){
        value=-1;
        first=-1;
        second=-1;
    }

    public MasterVal(int v, int f, int s){
        value = v;
        first = f;
        second = s;
    }

    public int getValue() {
        return value;
    }

    public boolean Equals(MasterVal obj){
        if(obj.value == this.value && obj.first == this.first && obj.second == this.second){
            return true;
        }
        return false;
    }

    public boolean FirstGreaterThan(MasterVal obj){
        if(obj.first<this.first){
            return true;
        }
        return false;
    }

    public boolean SecondGreaterThan(MasterVal obj){
        if(obj.second<this.second){
            return true;
        }
        return false;
    }
}