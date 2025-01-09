public class FileStorage {
    private String id;
    private int size;

    FileStorage(){
        id=".";
        size=0;
    }
    
    FileStorage(String id, int size){
        this.id = id;
        this.size = size;
    }

    public String getID(){
        return id;
    }

    public void setSize(int val){
        this.size = val;
    }

    public int getSize(){
        return size;
    }

    public boolean equalsSpace(FileStorage other){
        return this.size==other.size;
    }

    public String toString(){
        String temp = "";
        for(int i=0; i<size; i++){
            temp+=id;
        }
        return temp;
    }

}
