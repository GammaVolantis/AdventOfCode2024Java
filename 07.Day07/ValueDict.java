
public class ValueDict{
    private long total;
    private int[] values;

    ValueDict(long total, int[] values){
        this.total = total;
        this.values = values;
    }

    public long ValidateBinaryTrue(){
        for(int i=0; i<Math.pow(2,values.length); i++){
            String binary = BinaryMathConverter(i,values.length);
            long check = values[0];
            for(int j=1; j<values.length; j++){
                if(binary.charAt(j-1) == '+'){
                    check+=values[j];
                }else{
                    check*=values[j];
                }
            }
            if(total == check){
                return total;
            }
        }
        return 0;
    }

    public long ValidateTrinaryTrue(){
        for(int i=0; i<Math.pow(3,values.length); i++){
            String trinary = TrinaryMathConverter(i,values.length);
            long check = values[0];
            for(int j=1; j<values.length; j++){
                if(trinary.charAt(j-1) == '+'){
                    check+=values[j];
                }else if(trinary.charAt(j-1) == '*'){
                    check*=values[j];
                }else{
                    String temp = Long.toString(check) + Integer.toString(values[j]);
                    check = Long.parseLong(temp);
                }
            }
            if(total == check){
                return total;
            }
        }
        return 0;
    }

    private String BinaryMathConverter(int value, int size){
        String output ="";
        int val = value;
        while(val!=0){
            int temp=val%2;
            if (temp==0){
                output+='+';
            }
            else{
                output+="*";
            }
            val = val/2;
        }
        while(output.length()!=size){
            output+='+';
        }
        return output;
    }

    private String TrinaryMathConverter(int value, int size){
        String output ="";
        int val = value;
        while(val!=0){
            int temp=val%3;
            if (temp==0){
                output+='+';
            }
            else if(temp==1){
                output+="*";
            }
            else{
                output+="|";
            }
            val = val/3;
        }
        while(output.length()!=size){
            output+='+';
        }
        return output;
    }


}