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
        //need to build an array of the keys sorted in one array
        //building a method called MasterKey
        ArrayList<Integer> masterKey = MasterKey(intKeys);
        for(int f =0; f<finals.size(); f++){
            int[] fin = finals.get(f);
            // need to go through and sort the data based off the key
            //check for each key and if key swaps a part then restart the check
            for(int i=0; i<fin.length; i++){
                for(int j=0; j<fin.length; j++){
                    if(masterKey.indexOf(fin[i])<masterKey.indexOf(fin[j])){
                        int temp = fin[j];
                        fin[j]=fin[i];
                        fin[i]=temp;
                    }
                }
            }
            finals.set(f, fin);
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
        ArrayList<Integer> masterKey = new ArrayList<>();
        for(int[] dat : data){
            for(int d : dat){
                if(!masterKey.contains(d)){
                    masterKey.add(d);
                }
            }
        }
        //----------------BROKEN START----------------------------------
        //-----INFINATE LOOP PROBLEM
        //sort the ArrayList based on the key
        for(int i=0; i<data.size(); i++){
            int first = -1;
            int second = -1;
            for(int j = 0; j< masterKey.size(); j++){
                if(data.get(i)[0]==masterKey.get(j)){
                    first=j;
                }
                if(data.get(i)[1]==masterKey.get(j)){
                    second=j;
                }
            }
            if(first>second && first!=-1 && second!=-1){
                //switch and restart main loop;
                int temp = masterKey.get(first);
                masterKey.set(first, masterKey.get(second));
                masterKey.set(second, temp);
                i=0;
            }
        }
        //----------------BROKEN END-------------------------------
        System.out.println(masterKey.toString());
        return masterKey;
    }
}