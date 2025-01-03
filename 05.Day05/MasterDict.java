public class MasterDict {
    int coreVal;
    int[] partVals;

    MasterDict(int coreVal){
        this.coreVal = coreVal;
    }

    void AddPartVal(int partVal){
        if (partVals == null){
            partVals = new int[1];
            partVals[0] = partVal;
        }
        else{
            int[] temp = new int[partVals.length+1];
            for(int i=0; i<partVals.length; i++){
                temp[i] = partVals[i];
            }
            temp[partVals.length] = partVal;
            partVals = temp;
        }
    }

    int GetCoreVal(){
        return this.coreVal;
    }

    int[] GetPartVals(){
        return this.partVals;
    }
}
