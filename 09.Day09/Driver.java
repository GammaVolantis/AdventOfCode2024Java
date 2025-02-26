import java.io.IOException;
import java.util.ArrayList;
//import java.math.BigInteger;

class Driver{
    public static void main(String[] args) throws IOException {
        String test1 = "Test1.txt";
        String test2 = "Test2.txt";
        String test3 = "Test3.txt";
        String test4 = "Test4.txt";
        String test5 = "Test5.txt";
        String file = "Input.txt";
        long test1Pt1 = HiddenMain(test1);
        long test1Pt2 = HiddenMain3(test1);
        long test2Pt2HM2 = HiddenMain2(test2);
        long test2Pt2 = HiddenMain3(test2);
        long test3Pt2 = HiddenMain3(test3);
        long test4Pt2 = HiddenMain3(test4);
        if(test1Pt1 == 1928){
            System.out.println("Success!");
            System.out.println(HiddenMain(file));
            //NOT 1302097953 (too low), 5597065249 (too low)
        }
        else{
            System.out.println("Failure " + test1Pt1);
        }
        if(test1Pt2 == 2858 && test2Pt2 == 58 && test3Pt2 == 3){
            System.out.println("Success!");
            long output = HiddenMain3(file);
            if(output< 7264802235567L)
                System.out.println("Success! "+ output);
            else
                System.out.println("Failure(Too High) " + output);
            //Not 7264802235567 (too high)
        }else{
            System.out.println("Failure " + test1Pt2 + " " + test2Pt2 + " " + test3Pt2 + " " + test4Pt2 );
        }
    }

    public static long HiddenMain(String file) throws IOException{
        long total = 0;
        int storedData = 0;
        ArrayList<String> data = Helper.FileReader(file);
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0; i<data.getFirst().length(); i+=2){
            int fileSpace = Character.getNumericValue(data.getFirst().charAt(i));
            int emptySpace =0;
            if(i+1<data.getFirst().length()){
                emptySpace = Character.getNumericValue(data.getFirst().charAt(i+1));
            }
            for(int j=0; j<fileSpace; j++){
                String storedString = Integer.toString(storedData);
                temp.addLast(storedString);
            }
            storedData++;
            for(int j=0; j<emptySpace; j++){
                temp.addLast(".");
            }
        }
        //System.out.println(temp);
        for(int i=0; i<temp.size(); i++){
            if(temp.get(i)=="."){
                String tempString = ".";
                do{
                    tempString = temp.getLast();
                    temp.removeLast();
                }while(tempString ==".");
                if(i<temp.size()){
                    temp.set(i,tempString);
                }else{
                    temp.add(tempString);
                }
            }
        }
        //System.out.println(temp);
        int iter = 0;
        for(String t : temp){
            total+= iter*Integer.parseInt(t);
            iter++;
        }
        return total;
    }

    public static long HiddenMain2(String file) throws IOException{
        long total = 0;
        int storedData = 0;
        ArrayList<String> data = Helper.FileReader(file);
        ArrayList<FileStorage> temp = new ArrayList<>();
        for(int i=0; i<data.getFirst().length(); i+=2){
            FileStorage tempFile;
            FileStorage emptySlot;
            int fileSpace = Character.getNumericValue(data.getFirst().charAt(i));
            int emptySpace =0;
            if(i+1<data.getFirst().length()){
                emptySpace = Character.getNumericValue(data.getFirst().charAt(i+1));
            }
            if(fileSpace>0){
                String storedString = Integer.toString(storedData);
                tempFile = new FileStorage(storedString, fileSpace);
                temp.add(tempFile);
            }
            storedData++;
            if(emptySpace>0){
                emptySlot = new FileStorage(".", emptySpace);
                temp.add(emptySlot);
            }
            
        }
        temp = CompressFilesV2(temp);
        //System.out.println("\nFinal");
        //System.out.println(temp);
        long iter = 0;
        for(FileStorage t : temp){
            if(t.getID()!="."){
                for(int i=0; i<t.getSize(); i++){
                    total+= iter*Long.parseLong(t.getID());
                    iter++;
                }
            }else{
                for(int i=0; i<t.getSize(); i++){
                    iter++;
                }
            }

        }
        return total;
    }

    public static ArrayList<FileStorage> CompressFiles(ArrayList<FileStorage> files){
        ArrayList<FileStorage> temp = files;
        int k=0;
        int j=temp.size()-1;
        while(k<j){
            FileStorage tempFile = temp.get(j);
            while(tempFile.getID()=="."){
                j--;
                if(j<k){
                    break;
                }
                tempFile = temp.get(j);
            }
            FileStorage tempSpace = temp.get(k);
            while(tempSpace.getID()!="."){
                k++;
                tempSpace = temp.get(k);
            }
            if(k<j && tempFile.getSize()<=tempSpace.getSize()){
                int eS = tempSpace.getSize()-tempFile.getSize();
                FileStorage t = tempFile;
                temp.set(j,tempSpace);
                temp.set(k, t);
                if(eS!=0){
                    FileStorage extSpace = new FileStorage(".",eS);
                    ArrayList<FileStorage> tmm = new ArrayList<>();
                    for(int i=0; i<temp.size(); i++){
                        FileStorage tfm = temp.get(i);
                        tmm.add(tfm);
                        if(i==k && eS>0){
                            tmm.add(extSpace);
                            j++;
                        }
                    }
                    temp = tmm;
                }
                temp = CondenseSpaces(temp);
                System.out.println(temp);
            }
            if(!(k<j)){
                break;
            }
            j--;
        }
        return temp;
    }

    public static ArrayList<FileStorage> CompressFilesV2(ArrayList<FileStorage> files){
        ArrayList<FileStorage> temp = files;
        for(int j=temp.size()-1; j>-1; j--){
            if(temp.get(j).getID()!="."){
                String last = temp.get(j).getID();
                for(int k=0 ; k<=j; k++){
                    if(temp.get(k).getID()=="." && temp.get(k).getSize()>=temp.get(j).getSize()){
                        FileStorage tempSpace = temp.get(k);
                        FileStorage tempFile = temp.get(j);
                        int eS = tempSpace.getSize()-tempFile.getSize();
                        FileStorage t = tempFile;
                        temp.set(j,tempSpace);
                        temp.set(k, t);
                        if(eS>0){
                            FileStorage extSpace = new FileStorage(".",eS);
                            tempSpace.setSize(tempSpace.getSize()-eS);
                            ArrayList<FileStorage> tmm = new ArrayList<>();
                            for(int i=0; i<temp.size(); i++){
                                FileStorage tfm = temp.get(i);
                                tmm.add(tfm);
                                if(i==k && eS>0){
                                    tmm.add(extSpace);
                                    j++;
                                }
                            }
                            if(j<tmm.size()-1 && tmm.get(j) == tmm.get(j+1)){
                                tmm.get(j).setSize(tmm.get(j).getSize()+tmm.get(j+1).getSize());
                                tmm.remove(j+1);
                            }
                            if(tmm.get(j)==tmm.get(j-1) && j>0){
                                tmm.get(j).setSize(tmm.get(j).getSize()+tmm.get(j-1).getSize());
                                tmm.remove(j-1);
                                j--;
                            }
                            temp = tmm;
                        }
                        //int beforeCondense = temp.size();
                        //temp = CondenseSpaces(temp);
                        //j = j+(temp.size() - beforeCondense);
                        System.out.println(temp);
                    }
                }
            }
        }
        return temp;
    }

    public static ArrayList<FileStorage> CondenseSpaces(ArrayList<FileStorage> temp){
        ArrayList<FileStorage> tmm = temp;
        for(int i=1;i<tmm.size()-1; i++){
            if(tmm.get(i-1).getID() == tmm.get(i).getID()){
                int newSize = tmm.get(i-1).getSize()+tmm.get(i).getSize();
                i--;
                tmm.remove(i+1);
                tmm.get(i).setSize(newSize);
            }
            if(tmm.get(i+1).getID() == tmm.get(i).getID()){
                int newSize = tmm.get(i+1).getSize()+tmm.get(i).getSize();
                tmm.remove(i+1);
                tmm.get(i).setSize(newSize);
            }

        }
        return tmm;
    }

    public static long HiddenMain3(String file) throws IOException{
        long total = 0;
        ArrayList<String> data = Helper.FileReader(file);
        ArrayList<DataSlot> driveStorage = StoreDataOnDrive(data);
        System.out.println(driveStorage);
        driveStorage = ShiftFilesToEmpty(driveStorage);
        System.out.println(driveStorage);
        long iter=0;
        for(DataSlot d : driveStorage){
            for(int i=0; i<d.getEmptySize(); i++){
                if(i<d.getDataSize()){
                    total+=iter*d.getData();
                }
                iter++;
            }
        }
        return total;
    }

    public static ArrayList<DataSlot> StoreDataOnDrive(ArrayList<String> data){
        ArrayList<DataSlot> drive = new ArrayList<>();
        long iterator = 0;
        String dataString = data.get(0);
        for(int i=0; i<dataString.length(); i+=2){
            int first = Character.getNumericValue(dataString.charAt(i));
            int second = 0;
            if(i+1<dataString.length()){
                second = Character.getNumericValue(dataString.charAt(i+1));
            }
            DataSlot temp;
            if(first>0){
                temp = new DataSlot(iterator, first, first+second);
            }else{
                temp = new DataSlot(-1, first, first+second);
            }
            drive.add(temp);
            iterator++;
        }
        return drive;
    }

    public static ArrayList<DataSlot> ShiftFilesToEmpty(ArrayList<DataSlot> data){
        ArrayList<DataSlot> driveStorage = data;
        for(int i=driveStorage.size()-1; i>0; i--){
            DataSlot mover = driveStorage.get(i);
            DataSlot beforeMover = driveStorage.get(i-1);
            for(int j=0; j<=i; j++){
                long val = driveStorage.get(j).getData();
                int size = driveStorage.get(j).getDataSize();
                int empty = driveStorage.get(j).getEmptySize();
                DataSlot checked = new DataSlot(val, size, empty);
                if(mover.getDataSize()<=checked.getEmptySize()-checked.getDataSize() && i!=j){
                    beforeMover.setEmptySize(beforeMover.getEmptySize()+mover.getEmptySize());
                    driveStorage.remove(i);
                    int extraSpace = checked.getEmptySize()-checked.getDataSize();
                    mover.setEmptySize(extraSpace);
                    checked.setEmptySize(checked.getEmptySize()-extraSpace);
                    driveStorage.add(j+1, mover);
                    driveStorage.set(j, checked);
                    break;
                }
                else if(mover.getDataSize()<=checked.getEmptySize()-checked.getDataSize()){
                    //need to write a way to handle this edge case where i==j
                }
            }
        }
        return driveStorage;
    }



}