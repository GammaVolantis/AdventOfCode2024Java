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

    public int getSize(){
        return size;
    }

    public boolean equalsSpace(FileStorage other){
        return this.size==other.size;
    }

}
