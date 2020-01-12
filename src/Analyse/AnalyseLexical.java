package Analyse;

public final class AnalyseLexical {

    private AnalyseLexical() {
    }

    public static String[] analyse(String input[]) {
        String output[] = new String[input.length];

        for (int i = 0; i < input.length; i++) {
            String tmp = Keyword(input[i]);
            if (tmp != "") {
                output[i] = tmp;
            } else if (Ident(input[i])) {
                output[i] = "IDENT";
            } else if (Integer(input[i])) {
                output[i] = "INT";
            } else if (Float(input[i])) {
                output[i] = "FLOAT";
            } else if (String(input[i])) {
                output[i] = "CHAR";
            }

        }
        return output;
    }

    public static boolean Integer(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i); 
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean Float(String input) {
        byte numberPoints = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isDigit(c) && c != '.') { 
                return false;
            } else if (c == '.') {
                numberPoints++;
            }
        }
        if (numberPoints == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static String Keyword(String input) {
        switch (input) {
                case "ShowMes":
                return "SHOWMES";
            case "ShowVal":
                return "SHOWVAL";
            case "Affect":
                return "AFFECT";
            case "to":
                return "TO";
            case "Start_Program":
                return "STARTPROGRAM";
            case "End_Program":
                return "ENDPROGRAM";
            case "Real_Number":
                return "REALNUMBER";
            case "Int_Number":
                return "INTNUMBER";
            case "String":
                return "STRING";
            case "Start":
                return "START";
            case "Finish":
                return "FINISH";
            case "Give":
                return "GIVE";
            case ";;":
                return "FININ";
        
            case ",":
                return "VIR";
            case "If":
                return "IF";
            case "Else":
                return "ELSE";
            case "--":
                return "TIR";
            case ":":
                return "DEUX";
            case "<":
                return "INF";
            case ">":
                return "SUP";

            default:
                return "";
        }
    }

    public static boolean String(String input) {
        return (input.charAt(0) == '"' && input.charAt(input.length() - 1) == '"');
    }

    private static boolean Ident(String input) {
   if(!Character.isLetter(input.charAt(0))) return false;
		else if(input.charAt(input.length()-1) == '_') return false;
		else {
			for(int i=1;i<input.length()-1;i++) {
				if(input.charAt(i)=='_') {
					i++;
					if(input.charAt(i)=='_') return false;
				}
				if(!Character.isLetterOrDigit(input.charAt(i))) return false;
			}
		}
		return true;
    }
}
