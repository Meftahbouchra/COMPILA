
import Analyse.AnalyseLexical;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

class ReadFile {

    private String sourceCode;
    private String[] linesCode;
    private String[] wordsCode;
    private String[] tokens;
    private int errorCounter;

    public ReadFile(String path) {
/*
        this.sourceCode = readFile(path);
        linesCode = _splitLines(sourceCode);
        wordsCode = _splitWords(linesCode);*/

    }

    //this functions reads the snail file given	--WORKS PERFECTLY--
    private String readFile(String path) {
        String tmp = "";
        try {
            tmp = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    //this function splits the text into a String array of lines & deletes comments	--WORKS PERFECTLY
    private String[] _splitLines(String input) {

        String line = "";
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);

            //----------------------------------------------------------
            if (c.equals('%')) {
                Character c1 = input.charAt(i + 2);
                if (c1.equals('.')) {
                    int k = i + 3;
                    do {
                        /* This condition & loop ignores comments */
                        c1 = input.charAt(k);
                        k++;
                    } while (!c1.equals('\n') && !c.equals('\r'));
                    i = k; // this is the actual ignoring because it's here where the index is moved forward
                    c = input.charAt(i);
                }
            }
            //----------------------------------------------------------
            if (!c.equals('\n') && !c.equals('\r')) {
                line += c;
            } else if (!line.equals("\n") && !line.equals("\r") && line.length() != 0) {
                result.add(line);
                line = "";
            }
        }
        result.add(line);
        line = "";

        String output[] = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }

        return output;
    }

    // This function takes a String array of source code lines and return a String array of words
    // cases handled : logical/arithmetic operators, commas, strings							
    // -- WORKS PERFECTLY --	-- BUT IT LOOKS HIDEOUS, YOU DON'T WANNA LOOK AT IT! --	-- TO GET BACK TO --
    private String[] _splitWords(String input[]) {

        ArrayList<String> result = new ArrayList<String>();
        String temp = "";
        String arithLogOps = "+-*/<>!=,"; //for a more pleasant condition

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                char c = input[i].charAt(j);
                if (!Character.isWhitespace(c) && c != ',' && !arithLogOps.contains(String.valueOf(c)) && c != '"') {
                    temp += c;
                } else if (c == '"' || arithLogOps.contains(String.valueOf(c))) {
                    if (!temp.equals("")) {
                        result.add(temp);
                        temp = "";
                    }
                    if (arithLogOps.contains(String.valueOf(c))) {
                        result.add(String.valueOf(c));
                    } else if (c == '"') {
                        do {
                            temp += c;
                            j++;
                            c = input[i].charAt(j);
                        } while (c != '"' && j < input[i].length());
                        temp += c;
                        result.add(temp);
                        temp = "";
                    }
                } else if (temp.length() != 0) {
                    result.add(temp);
                    temp = "";
                }
            }
            result.add(temp);
            temp = "";
        }

        String output[] = new String[result.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = result.get(i);
        }
        return output;
    }

    // This function format the result of the lexical analysis and returns it as a simple String
    public String lexicalAnalysisFunction() {
        tokens = AnalyseLexical.analyse(wordsCode);
        String output = "";
        for (int i = 0; i < wordsCode.length; i++) {
            if (tokens[i] != null &&tokens[i] != "VIR") {
                String comment = null;
                switch (tokens[i]) {
                    /*case "MRSTARTPRGRM":
							comment = "Mot r�serv� pour le debut du programme";
							break;
						case "MRREEL":
							comment = "D�claration d'un r�el";
							break;
						case "MRINT":
							comment = "D�claration d'un entier";
							break;
						case "IDENT":
							comment = "Identificateur";
							break;
                                                case "FININ":
							comment = "Fin d'une instruction";
							break;
                                                        
                                                        
                                                        
						case "OPBR":
							comment = "croch� ouvrant";
							break;
						case "CLBR":
							comment = "croch� fermant";
							break;
						case "SET":
							comment = "Mot r�serv� pour affectation d'une valeur";
							break;
						
						case "AROP":
							comment = "Op�rateur arithimetique";
							break;
						case "LOOP":
							comment = "Op�rateur logique";
							break;
						case "PUT":
							comment = "Mot r�serve pour l'affichage d'un string";
							break;
						case "MRENDPRGRM":
							comment = "Mot r�serv� pour la fin du programme";
							break;
						case "GET":
							comment = "Mot r�serv� pour la transmission de valeur";
							break;
						case "FROM":
							comment = "Mot r�serv� pour la sp�cification de source";
							break;
						case "INT":
							comment = "Un entier";
							break;
						case "FLOAT":
							comment = "Un R�el";
							break;
						case "STR":
							comment = "Un String";
							break;
						case "IF":
							comment = "Mot r�serv� pour tester";
							break;
						case "ELSE":
							comment = "Mot r�serv� pour la condition alternative";
							break;
						/*case "DSTR":
							comment = "Mot r�serv� pour la d�claration d'un String";
							break;*/
 /*case "COPAR":
							comment = "Mot r�serv� pour une condition";
							break;*/
                    case "MRSTRING":
                        comment = "mot réserve de la declaration d une chaine de caractere";
                        break;
                    case "IDENT":
                        comment = " est un identificateur";
                        break;
                    case "INT":
                        comment = "nombre entier";
                        break;
                    case "FLOAT":
                        comment = "nombre reel";
                        break;
                    case "STR":
                        comment = "est une chaine de caractre";
                        break;
                    case "TIR":
                        comment = "mot reserve pour une condition";
                        break; // --
                    case "MRGIVE":
                        comment = "mot reserve";
                        break;
                    case "MRAFFECT":
                        comment = "mot reserve pour l' affectation de la valeur d 'une variable a une autre variable";
                        break;
                    case "MRTO":
                        comment = "mot reserve pour l' affectation de la valeur d 'une variable a une autre variable";
                        break;
                    case "MRIF":
                        comment = "mot reserve pour une condition";
                        break;
                    case "MRELSE":
                        comment = "mot reserve pour la condition alternative";
                        break;
                    case "MRSTART":
                        comment = "debut d un bloc";
                        break;
                    case "MRFINISH":
                        comment = "fin d un bloc";
                        break;

                    case "MRSTARTPRGRM":
                        comment = "mot reserve debut d un program";
                        break;
                    case "MRENDPRGRM":
                        comment = "mot reserve fin d un program";
                        break;
                    case "MRINT":
                        comment = "mot reserve declaration d un enier";
                        break;
                    case "MRREEL":
                        comment = "mot reserve declaration d un reel";
                        break;
                    case "MRSCHOWMS":
                        comment = "mot reserve por l affichage d un message";
                        break;
                    case "MRSCHOWVAL":
                        comment = "mot reserve pour l a ffichage dune valeur d une variable";
                        break;
                    case "DEUXPOINT":
                        comment = "caractere reserve";
                        break;
                    case " VIR":
                        comment = "caractere reserve";
                        break;
                    case "POINTVIR":
                        comment = "caractere reserve";
                        break;
                    case "INF":
                        comment = "symbol inferieur";
                        break;
                    case " SUP":
                        comment = "symbol superieur";
                        break;
                    case " EQ":
                        comment = "symbol egale";
                        break;
                    case " NE":
                        comment = "symbol diffierent";
                        break;

                    default:
                        comment = "";
                }
                output += wordsCode[i] + " : " + comment + "\n";
            }
        }
        return output;
    }

    // this function is for determining whether the semantic button should be enabled or not
    public int getErrorCount() {
        return errorCounter;
    }

}
