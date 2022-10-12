public class VathsaUtil
{

    public static boolean validateName(String name){
        if(name.trim().equals("")) return false; // Empty
        if(!Character.isLetter(name.charAt(0))) return false; // Check if name starts with number
        if(name.split(" ").length>1) return false;  // Check whether name contains more than one word

        for (int i = 1 ; i < name.length() ; i++){

            char a = name.charAt(i);
            if(!Character.isLetterOrDigit(a)) {
                return false;
            }
        }
        return true;
    }
    public static boolean validatePriority(int number){
        if(number <=10 && number>=0) return true;
        return false;
    }

}
