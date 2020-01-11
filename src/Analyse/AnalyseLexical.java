package Analyse;

public final class AnalyseLexical {
	
	private AnalyseLexical() {
		// nothing for now, this is just here to prevent any instantiation
	}
	
	public static String[] analyse(String input[]) {
		String output[] = new String[input.length];
		
		for(int i=0;i<input.length;i++) {
			String tmp = isKeyword(input[i]);
			if(tmp != "") output[i] = tmp;
			else if(isIdent(input[i])) output[i] = "IDENT";
			else if(isInteger(input[i])) output[i] = "INT";
			else if(isFloat(input[i])) output[i] = "FLOAT";
			else if(isString(input[i])) output[i] = "STR";
			
		}
		return output;
	}
	
	// --------------------chek if that String is a integer	
	public static boolean isInteger(String input) {
		for(byte i=0;i<input.length();i++) {
			char c = input.charAt(i);
			if(!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
	
	// ----------------------------chek if that String is a float	
	public static boolean isFloat(String input) {
		byte numberOfPoints = 0;
		for(byte i=0;i<input.length();i++) {
			char c = input.charAt(i);
			if(!Character.isDigit(c) && c != '.') {
				return false;
			}
			else if(c == '.') {
				numberOfPoints++;
			}
		}
		if(numberOfPoints == 1) return true;
		else return false;
	}
	
	// ---------------- check if the word given is a KEYWORD and returns the appropriate token		-
	private static String isKeyword(String input) {		
		switch(input) {
			/*case "Start_Program":
				return "MRSTARTPRGRM";
			case "End_Program":
				return "MRENDPRGRM";
			case "Int_Number":
				return "MRINT";
			case "Real_Number":
				return "MRREEL";
			case "SnlSt":
				return "DSTR";
			case "Start":
				return "OPBR";
			case "Finish":
				return "CLBR";
			case "Set":
				return "SET";
			case ";;":
				return "FININ";
			case "Snl_Put":
				return "PUT";
			case "Get":
				return "GET";
			case "from":
				return "FROM";
			case ",":
				return "VIR";
			case "If":
				return "IF";
			case "Else":
				return "ELSE";
			case "--":
				return "COPAR";
			default:
				return "";
		}
	}*/
                   case "String" :                                 return"MRSTRING";
                   case "Give" :                                     return "MRGIVE";
                   case "Affect"   :                                return "MRAFFECT";
                   case "to"  :                                     return "MRTO";
                   case  "If" :                                     return "MRIF";
                   case "Else" :                                    return "MRELSE";
                   case "Start" :                                       return "MRSTART";
                   case "Finish"  :                                  return "MRFINISH";
                   case"Start_Program"   :                       return "MRSTARTPRGRM";
                   case "End_Program"  :                            return "MRENDPRGRM";
                   case "Int_Number" :                               return "MRINT";
                   case "Real_Number" :                             return "MRREEL";
                   case "ShowMes"  :                                 return "MRSCHOWMS";
                   case "ShowVal"  :                                 return "MRSCHOWVAL";
                   case ":":                                   return "DEUXPOINT";
                   case "," :                                   return" VIR";
                    case "ù" :                                   return"INF";
                    case ">" :                                   return" SUP";
                    case "==" :                                   return" EQ";
                    case "=!" :                                   return" NE";
                   case "//."  :                                  return "TIR";
                   case ";;"  :                                  return "POINTVIR";
                   default:
				return "";
                }
        }

	//--------------------------------  check if the word given is a string and return true or false	
	public static boolean isString(String input) {
		return (input.charAt(0) == '"' && input.charAt(input.length()-1) == '"');
	}
	
	// ------------------------- checks if the word given is an identifier and returns true of false	
	private static boolean isIdent(String input) {
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
	
	// -----------------------checks if the word given is an arithmetic operator and returns true of false	
	//private static boolean isArthOper(String input) {
		////String tmp = "+-*/" ;
		//return tmp.contains(input);
	//}
	
	// ---------------------------checks if the word given is a logical operator and returns true or false
//private static boolean isLogOper(String input) {
		//String tmp = "<>!=";
	//	return tmp.contains(input);
	//}
     
	
	
	
	
}
