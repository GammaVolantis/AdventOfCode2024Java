import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Driver{
    public static void main(String[] args) throws IOException {
        String test1 = "Test1.txt";
        String test2 = "Test2.txt";
        String file = "Input.txt";
        if(HiddenMain(test1)==161){
            System.out.print(HiddenMain(file));
        }
        else{
            System.out.print("Test1 Failed");
        }
    }
    //Not 79748299, 73243482, 25292714, 161877942
    
    public static int HiddenMain(String file) throws IOException{
        ArrayList<String> data = Helper.FileReader(file);
        data = fileReconstruction(data);
        System.out.print(data.toString());
        int total=0;
        ArrayList<String> stringStore = new ArrayList<>();
        for(String d : data){
            ArrayList<String> tempStore = new ArrayList<>();
            tempStore = stringToArrayConvertV2(d);
            for(String t : tempStore){
                stringStore.add(t);
            }
        }
        System.out.print(stringStore.toString());
        total = arrayToTotal(stringStore);
        return total;
    }


    public static ArrayList<String>fileReconstruction(ArrayList<String> data){
        ArrayList<String> temp = new ArrayList<>();
        for(String d : data){
            String tempS = "";
            for(int i=0; i<d.length();i++){
                char c = d.charAt(i);
                if(c == 109){
                    tempS+="\n";
                }
                if(charChecker(c)){
                    tempS+=c;
                }
                if(c==41){
                    tempS+="\n";
                }
            }
            temp.add(tempS);
        }

        return temp;
    }
    public static boolean charChecker(char c){
        //numbers
        if(c>47 && c<58) return true;
        //(,)
        if(c==40 || c==41 || c==44) return true;
        //mul
        if(c==109 || c==117 || c==108) return true;
        return false;
    }
    //cooking
    public static ArrayList<String> stringToArrayConvertV2(String string){
        ArrayList<String> hope = new ArrayList<>();
        string.replace("mul(","\nmul(");
        String[] tempS = string.split("\n");
        for (String s : tempS){
            hope.add(s);
        }
        String temp = "";
        for(int i=0;i<hope.size();i++){
            Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
            Matcher matcher = pattern.matcher(hope.get(i));
            if(matcher.find()){
                String data = matcher.group();
                hope.set(i, data);
            }else{
                hope.set(i, "-1");
            }
        }
        ArrayList<String> newHope = new ArrayList<>();
        for(String h : hope){
            if(h!="-1"){
                newHope.add(h);
            }
        }
        return newHope;
    }
    public static int arrayToTotal(ArrayList<String> data){
        int total = 0;
        for(String d:data){
            if(d.contains("mul(")){
            d=d.replace("mul(", "");
            d=d.replace(")", "");
            String[] datS = d.split(",");
            int a = Integer.parseInt(datS[0]);
            int b = Integer.parseInt(datS[1]);
            total+= a*b;
            }
        }
        return total;
    }

    //Does not work well
    public static void dataSubstringChanneler(String string){
        String temp ="";
        for(int i = 0; i<string.length()-2;i++){
            System.out.print(string.substring(i,i+2));
            if(string.substring(i, i+2)=="mul"){
                if(i+10>string.length())
                    System.out.print(string.substring(i,string.length()));
                else
                    System.out.print(string.substring(i,i+10));
            }
        }
    }
    public static ArrayList<int[]> stringToArrayConvert(String string){
        ArrayList<String> output = new ArrayList<>();
        ArrayList<int[]> outputInt = new ArrayList<>();
        String[] sTemp = string.split("\n");
        for(String s : sTemp){
            int end = s.length()-1;
            try{if(s.charAt(0)=='m' && s.charAt(end)==')')
                output.add(s);
            }catch(Exception e){

            }
        }
        for(int i = 0; i<output.size();i++){
            if(output.get(i).length()>12 || output.get(i).length()<8){
                output.remove(i);
            }
        }
        for(int i = 0; i<output.size();i++){
            output.set(i, output.get(i).replace("mul(", ""));
        }
        for(int i = 0; i<output.size();i++){
            String t = output.get(i);
            output.set(i, t.substring(0, t.length()-1));
        }
        for(int i = 0; i<output.size();i++){
            String temp = output.get(i);
            int end = temp.length()-1;
            try{
                if(temp.charAt(0)<58 &&temp.charAt(0)>47){
                    if(temp.charAt(end)<58 &&temp.charAt(end)>47){
                        String[] tempWordNums = temp.split(",");
                        int[] tempNums = new int[tempWordNums.length];
                        for(int j=0; j<tempNums.length;j++){
                            int tempInt = Integer.parseInt(tempWordNums[j]);
                            tempNums[j] = tempInt;
                        }
                        outputInt.add(tempNums);
                    }
                }
            }catch(Exception e){

            }
        }
        return outputInt;
    }
    public static int checkingDataForSubString(String string){
        String temp ="";
        int start = 0;
        int end = 0;
        int a = 0;
        int b = 0;
        int total = 0;
        boolean[] checker = new boolean[8];
        for(int i=0; i<string.length();i++){
            // mul(#,#) mul(##,##) mul(###,###) mul(#,###)
            char c = string.charAt(i);
            if(c=='m' && !dataChecker(checker, 1)) checker[0]=true;
            else if(c=='u' && dataChecker(checker, 1)) checker[1] = true;
            else if(c=='l'&& dataChecker(checker, 2)) checker[2] = true;
            else if (c=='('&& dataChecker(checker, 3) && !checker[4]) checker[3] = true;
            else if (c>47 && c<57 && dataChecker(checker, 4) && !checker[5]){
                 checker[4] = true;
                    temp+=c;
                }
            else if (c==','&& dataChecker(checker, 5)){
                checker[5] = true;
                if(temp=="") a=0;
                else a = Integer.parseInt(temp);
                temp="";
            }
            else if (c>47 && c<57 && dataChecker(checker, 6)){ 
                checker[6] = true;
                temp+=c;
            }
            else if (c==')'&& dataChecker(checker, 7)){ 
                checker[7] = true;
                if(temp=="") b=0;
                else b = Integer.parseInt(temp);
                temp="";
                total+=a*b;
                end=i;
                // System.out.println(a+" * "+b);
                for(int j=start;j<=end;j++)
                    System.out.print(string.charAt(j));
                System.out.println();
                start=i+1;
                a=0;
                b=0;
            }
            else {
                checker = new boolean[8];
                a=0;
                b=0;
                temp="";
                start = i+1;
                if(c=='m' && !dataChecker(checker, 1)) checker[0]=true;
            }
            
        }
        return total;
    }
    public static boolean dataChecker(boolean[] check, int len){
        for(int i = 0; i<len; i++)
            if(!check[i]) return false;
        return true;
    }
}