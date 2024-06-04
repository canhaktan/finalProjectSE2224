public class DataValidationChecker {
    // Not a 'necessary' class, however I wanted to put this in case of any usage needed
    public boolean isInteger(String txt){
        boolean integer = true;
        for(int i = 0;i < txt.length();i++){
            char c = txt.charAt(i);
            int charInt = (int)c;
            if(charInt < 48 || charInt > 57){
                integer = false;
                break;
            }
        }
        return integer;
    }
    public boolean isString(String txt){
        boolean string = true;
        for(int i = 0;i < txt.length();i++){
            if(!string){
                break;
            }
            char c = txt.charAt(i);
            int charInt = (int)c;
            if((65 <= charInt && charInt <= 90) || (97 <= charInt && charInt <= 122)){
                string = true;
            }
            else if(charInt != 32){//if not space char ASCII
                string = false;
            }
        }
        return string;
    }
}
