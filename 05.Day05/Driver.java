import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Driver{
    public static void main(String[] args) throws IOException {
        String test1 = "Test1.txt";
        String file = "Input.txt";
        //ArrayList<String> data = Helper.FileReader(test1);
        int test1Pt1 = HiddenMain(test1);
        int test1Pt2 = HiddenMain2(test1);
        if(test1Pt1==143){
            System.out.println("Pt1 Success\n");
            System.out.println(HiddenMain(file));
        }else{
            System.out.println("Pt1 Failure " + test1Pt1 + "\n");
        }
        System.out.println();
        if(test1Pt2==123){
            System.out.println("Pt2 Success\n");
            System.out.println(HiddenMain2(file));
            //NOT 5815 (too low), 6133 (too low)
        }else{
            System.out.println("Pt2 Failure " + test1Pt2 + "\n");
        }

    }

    public static int HiddenMain(String file) throws IOException{
        int total = 0;
        //Standard Pull from File
        ArrayList<String> data = Helper.FileReader(file);
        //New Arrays to hold the data from the file
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> pages = new ArrayList<>();
        //Data is in two forms "45|23" and "45,23,34,67" so checking for the separators
        for(String d : data){
            if(d.contains("|")){
                keys.add(d);
            }
            else if(d.contains(",")){
                pages.add(d);
            }
        }

        //Keys rule is that the first number must come before the second number somewhere in the array.
        //Page order does not matter as long as the rule is followed and the page is somewhere before the other page in the list

        //modding the keys and pages
        ArrayList<int[]> intKeys = regexIntegerConverter(keys, "\\d+|\\d+");
        ArrayList<int[]> intPages = stringSplitter(pages, ",");
        ArrayList<int[]> finals = new ArrayList<>();
        for(int[] page : intPages){
            boolean good = true;
            for(int[] key : intKeys){
                int first = -1;
                int second = -1;
                for(int i=0; i<page.length; i++){
                    if(key[0]==page[i]){
                        first = i;
                    }
                    if(key[1]==page[i]){
                        second = i;
                    }
                }
                if(first>second && first!=-1 && second!=-1){
                    good=false;
                }
            }
            if(good){
                finals.add(page);
            }
        }
        for(int[] f : finals){
            // for(int i = 0; i<f.length;i++){
            //     System.out.print(f[i]+",");
            // }
            // System.out.println();
            total+= f[f.length/2];
        }
        return total;
    }

    public static int HiddenMain2(String file) throws IOException{
        int total = 0;
        //Standard Pull from File
        ArrayList<String> data = Helper.FileReader(file);
        //New Arrays to hold the data from the file
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> pages = new ArrayList<>();
        //Data is in two forms "45|23" and "45,23,34,67" so checking for the separators
        for(String d : data){
            if(d.contains("|")){
                keys.add(d);
            }
            else if(d.contains(",")){
                pages.add(d);
            }
        }

        //Keys rule is that the first number must come before the second number somewhere in the array.
        //Page order does not matter as long as the rule is followed and the page is somewhere before the other page in the list

        //modding the keys and pages
        ArrayList<int[]> intKeys = regexIntegerConverter(keys, "\\d+|\\d+");
        ArrayList<int[]> intPages = stringSplitter(pages, ",");
        ArrayList<int[]> finals = new ArrayList<>();
        for(int[] page : intPages){
            boolean good = true;
            for(int[] key : intKeys){
                int first = -1;
                int second = -1;
                for(int i=0; i<page.length; i++){
                    if(key[0]==page[i]){
                        first = i;
                    }
                    if(key[1]==page[i]){
                        second = i;
                    }
                }
                if(first>second && first!=-1 && second!=-1){
                    good=false;
                }
            }
            if(!good){
                finals.add(page);
            }
        }
        // //need to build an array of the keys sorted in one array
        // //building a method called MasterKey
        // ArrayList<Integer> masterKey = MasterKeyV2(intKeys);
        // for(int f =0; f<finals.size(); f++){
        //     int[] fin = finals.get(f);
        //     // need to go through and sort the data based off the key
        //     //check for each key and if key swaps a part then restart the check
        //     for(int i=0; i<fin.length; i++){
        //         for(int j=0; j<fin.length; j++){
        //             if(masterKey.indexOf(fin[i])<masterKey.indexOf(fin[j])){
        //                 int temp = fin[j];
        //                 fin[j]=fin[i];
        //                 fin[i]=temp;
        //             }
        //         }
        //     }
        //     finals.set(f, fin);
        // }
        for(int i=0; i<finals.size(); i++){
            finals.set(i,SortPages(finals.get(i),MasterKeyV2(intKeys)));
        }
        for(int[] f : finals){
            for(int i = 0; i<f.length;i++){
                System.out.print(f[i]+",");
            }
            System.out.println();
            total+= f[f.length/2];
        }
        return total;
    }

    public static ArrayList<int[]> regexIntegerConverter(ArrayList<String> data, String pattern){
        Pattern keyPattern = Pattern.compile(pattern);
        ArrayList<int[]> intData = new ArrayList<>();
        for(String k : data){
            Matcher keyMatcher = keyPattern.matcher(k);
            int[] temp = new int[2];
            int iter = 0;
            while(keyMatcher.find()){
                temp[iter] = Integer.parseInt(keyMatcher.group());
                iter++;
            }
            intData.add(temp);
        }
        return intData;
    }

    public static ArrayList<int[]> stringSplitter(ArrayList<String> data, String splitter){
        ArrayList<int[]> intPages = new ArrayList<>();
        for(String p : data){
            String[] tempS = p.split(splitter);
            int[] tempI = new int[tempS.length];
            for(int i = 0; i< tempS.length; i++){
                tempI[i]=Integer.parseInt(tempS[i]);
            }
            intPages.add(tempI);
        }
        return intPages;
    }

    public static ArrayList<Integer> MasterKey(ArrayList<int[]> data){
        ArrayList<Integer> masterVals = new ArrayList<>();
        for(int[] dat : data){
            for(int d : dat){
                if(!masterVals.contains(d)){
                    masterVals.add(d);
                }
            }
        }
        // Sort the incomming data
        ArrayList<int[]> sortedData = data;
        for(int i=0; i<data.size(); i++){
            for(int j=0; j<data.size(); j++){
                if(sortedData.get(i)[0]>sortedData.get(j)[0]){
                    int temp[] = sortedData.get(i);
                    sortedData.set(i,sortedData.get(j));
                    sortedData.set(j,temp);

                }
            }
        }
        // for(int i=0; i<data.size(); i++){
        //     System.out.print(sortedData.get(i)[0]+ ",");
        // }
        // System.out.println();

        //----------------BROKEN START----------------------------------
        //-----INFINATE LOOP PROBLEM
        //sort the ArrayList based on the keys
        //First: pull in the data to a class designed to hold and parse it.
        ArrayList<MasterVal> masterKeys = new ArrayList<>();
        for(int i =0; i<masterVals.size(); i++){
            int first = 0;
            int second = 0;
            int value = masterVals.get(i);
            for(int[] d : data){
                if(d[0] == masterVals.get(i)){
                    first++;
                }
                if(d[1] == masterVals.get(i)){
                    second++;
                }
            }
            MasterVal temp = new MasterVal(value, first, second);
            masterKeys.add(temp);
        }
        //Second: order a new array based on the data in the class
        ArrayList<Integer> masterKey = new ArrayList<>();
        while(masterKeys.size()>0){
            int index = 0;
            MasterVal temp = masterKeys.get(index);
            for(int i=1; i<masterKeys.size(); i++){
            //first: compare the fist column then if needed compare the second to make a choice
            //Greater First = Forward in the array
                if(masterKeys.get(index).FirstGreaterThan(temp)){
                    temp = masterKeys.get(i);
                    index = i;
                }
            //Greater Second = Backward in the array
                else if(temp.SecondGreaterThan(masterKeys.get(i))){
                    temp=masterKeys.get(i);
                    index = i;
                }


            }
            masterKey.addLast(temp.getValue());
            masterKeys.remove(index);
        }
        //System.out.println(masterKey.toString());
        int counter = 0;
        while(counter<sortedData.size()*3){
            for(int i=0; i<sortedData.size(); i++){
                int first = -1;
                int second = -1;
                counter++;
                for(int j = 0; j< masterKey.size(); j++){
                    if(sortedData.get(i)[0]==masterKey.get(j)){
                        first=j;
                    }
                    if(sortedData.get(i)[1]==masterKey.get(j)){
                        second=j;
                    }
                }
                if(first>second && first!=-1 && second!=-1){
                    //switch and restart main loop;
                    int temp = masterKey.get(first);
                    masterKey.set(first, masterKey.get(second));
                    masterKey.set(second, temp);
                    counter = 0;
                }
            }
        }
        //----------------BROKEN END-------------------------------
        System.out.println(masterKey.toString());
        return masterKey;
    }

    public static ArrayList<MasterDict> MasterKeyV2(ArrayList<int[]> data){
        ArrayList<Integer> finalKey = new ArrayList<>();
        ArrayList<MasterDict> dictKey = new ArrayList<>();
        for(int[] d : data){
            int location = -1;
            for(int i=0; i<dictKey.size(); i++){
                if(dictKey.get(i).GetCoreVal() == d[0]){
                    location = i;
                }
            }
            if(location == -1){
                MasterDict temp = new MasterDict(d[0]);
                temp.AddPartVal(d[1]);
                dictKey.add(temp);
            }
            else{
                dictKey.get(location).AddPartVal(d[1]);
            }
        }
        /*need to place vals into their correct locations
        /*based on all values in the dictionary that it needs
        /*to be in front of it*/
        //FIRST: PUT FINAL VALS INTO THE ARRAY
        for(int i=0; i<dictKey.size(); i++){
            finalKey.add(dictKey.get(i).GetCoreVal());
        }
        for(int i=0; i<data.size(); i++){
            if(!finalKey.contains(data.get(i)[1])){
                finalKey.addLast(data.get(i)[1]);
            }
        }
        //NEXT: SORT THE DATA BASED ON THE VALUES IN THE DICT ARRAY
        //boolean swap = false;
        //do{
            // swap = false;
            // for(MasterDict dict : dictKey){
            //     int tempLocal = -1;
            //     for(int p : dict.GetPartVals()){
            //         if(tempLocal == -1){
            //             tempLocal = finalKey.indexOf(p);
            //         }
            //         else if(tempLocal>finalKey.indexOf(p)){
            //             tempLocal = finalKey.indexOf(p);
            //         }
            //     }
            //     int coreIndex = finalKey.indexOf(dict.GetCoreVal());
            //     if(tempLocal<coreIndex){
            //         swap = true;
            //         int tempCore = finalKey.get(coreIndex);
            //         finalKey.remove(coreIndex);
            //         finalKey.add(tempCore);
            //         while(finalKey.get(tempLocal) != tempCore){
            //             int tempSwap = finalKey.get(tempLocal);
            //             finalKey.remove(tempLocal);
            //             finalKey.add(tempSwap);
            //         }
            //     }
            // }
        //}while(swap);
        //Return should be this "[97, 75, 47, 61, 53, 29, 13]"
        // System.out.println(finalKey.toString());
        // return finalKey;
        for(MasterDict d : dictKey){
            System.out.println(d.toString());
        }
        return dictKey;
    }

    public static int[] SortPages(int[] data, ArrayList<MasterDict> keys){
        int[] tempArray = data;
        boolean swap = false;
        do{
            swap = false;
            for(MasterDict k : keys){
                int first = -1;
                int second = tempArray.length;
                for(int i=0; i<k.GetPartVals().length; i++){
                    for(int j=0; j<tempArray.length; j++){
                        if(tempArray[j]==k.GetPartVals()[i] && j<second){
                            second = j;
                        }
                    }
                }
                for(int i=0; i<tempArray.length; i++){
                    if(tempArray[i] == k.GetCoreVal()){
                        first = i;
                    }
                }
                if(first>second){
                    swap = true;
                    int temp = tempArray[first];
                    tempArray[first] = tempArray[second];
                    tempArray[second] = temp;
                }
            }
        }while(swap);
        
        return tempArray;
    }
}