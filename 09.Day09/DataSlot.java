public class DataSlot {
    private long data;
    private int dataSize;
    private int emptySize;

    public DataSlot(long data, int dataSize, int emptySize){
        this.data = data;
        this.dataSize = dataSize;
        this.emptySize = emptySize;
    }

    public void setEmptySize(int size){
        emptySize = size;
    }

    public int getEmptySize(){
        return emptySize;
    }

    public long getData(){
        return data;
    }

    public int getDataSize(){
        return dataSize;
    }

    public String toString(){
        String temp = "[";
        for(int i=0; i<emptySize; i++){
            if(i<dataSize){
                temp+= data;
            }else{
                temp+= "-1";
            }
            if(i<emptySize-1){
                temp+= " ";
            }else{
                temp+="]";
            }
        }
        return temp;
    }
}