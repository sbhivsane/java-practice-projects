package arrays;

class Solution {

    static void main() {
        String s = longestCommonPrefix(new String[]{"a"});
        System.out.println(s);
    }

    public static String longestCommonPrefix(String[] strs) {
        String result = "";
        String minLengthStr = strs[0];
        for(int i=0;i<strs.length;i++){
            if(strs[i].length()<minLengthStr.length()){
                minLengthStr=strs[i];
            }
        }
        for(int i=0;i<minLengthStr.length();i++){
            boolean isSame = true;
            for(int j=0;j<strs.length;j++){
                if(strs[j].charAt(i)!=minLengthStr.charAt(i)){
                    isSame=false;
                    break;
                }
            }
            if(!isSame){
                break;
            }else{
                result+=minLengthStr.charAt(i);
            }
        }
        return result;
        
    }
}