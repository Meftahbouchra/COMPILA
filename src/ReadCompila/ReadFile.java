
import Analyse.AnalyseSyntaxique;
import Analyse.AnalyseLexical;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

class ReadFile {

    private String[] line;
    private String[] word; 
    private String[] tokens;
    private String sourceCode;

    public ReadFile(String path) {

        this.sourceCode = readFile(path);
        line = Lines(sourceCode);
        word = Words(line);

    }

    private String readFile(String path) {
        String tmp = "";
        try {
            tmp = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }
// ignore les commentire ,sa focntion de lire les lignes
    private String[] Lines(String input) {

        String line = "";
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);

            if (c.equals('/')) {//commenteire
                Character c1 = input.charAt(i + 2);
                if (c1.equals('.')) {
                    int k = i + 3;
                    do {

                        c1 = input.charAt(k);
                        k++;
                    } while (!c1.equals('\n') && !c.equals('\r'));
                    i = k; 
                    c = input.charAt(i);
                }
            }

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

    private String[] Words(String input[]) {

        ArrayList<String> result = new ArrayList<String>();
        String temp = "";
        String ari = "<>,";
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                char c = input[i].charAt(j);
                if (!Character.isWhitespace(c) && c != ',' && !ari.contains(String.valueOf(c)) && c != '"') {
                    temp += c;
                } else if (c == '"' || ari.contains(String.valueOf(c))) {
                    if (!temp.equals("")) {
                        result.add(temp);
                        temp = "";
                    }
                    if (ari.contains(String.valueOf(c))) {
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

    public String lexicalFunction() {
        tokens = AnalyseLexical.analyse(word);
        String output = "";
        for (int i = 0; i < word.length; i++) {
            if (tokens[i] != null) {
                String resultat = null;
                switch (tokens[i]) {
                    case "STARTPROGRAM":
                        resultat = "Mot reserve pour le debut du programme";
                        break;
                    case "REALNUMBER":
                        resultat = "Declaration d'un reel";
                        break;
                    case "INTNUMBER":
                        resultat = "Delaration d'un entier";
                        break;
                    
                    case "START":
                        resultat = "Debut d un bloc";
                        break;
                    case "FINISH":
                        resultat = "Fin d un bloc";
                        break;
                    case "GIVE":
                        resultat = "Mot reserve pour affectation d'une valeur ";
                        break;
                    case "FININ":
                        resultat = "Fin d'une instruction";
                        break;

                    case "SHOWMES":
                        resultat = "Mot reserve pour l'affichage d'une message";
                        break;
                    case "ENDPROGRAM":
                        resultat = "Mot reserve pour la fin du programme";
                        break;
                    case "AFFECT":
                        resultat = "Mot reserve pour la affectation d un nombre";
                        break;
                    case "TO":
                        resultat = "Mot reserve pour la affectation d un nombre";
                        break;
                    case "INT":
                        resultat = "Un entier";
                        break;
                    case "FLOAT":
                        resultat = "Un Reel";
                        break;
                    case "STR":
                        resultat = "Un caractere";
                        break;
                    case "IF":
                        resultat = "Mot reserve pour la condition";
                        break;
                    case "ELSE":
                        resultat = "Mot reserve pour la condition alternative";
                        break;
                    case "STRING":
                        resultat = "Mot reserve pour la declaration d'un caractere";
                        break;
                    case "TIR":
                        resultat = "Caractere reserve pour une condition";
                        break;

                    case "SHOWVAL":
                        resultat = " Affichage d un valeur";
                        break;
                    case "INF":
                        resultat = "Symbol inf";
                        break;
                    case "SUP":
                        resultat = "Symbol sub";
                        break;
                    case "DEUX":
                        resultat = "  Caratrere reserve";
                        break;
                    case "CHAR":
                        resultat = "  Une chaine de caractere";
                        break;
                        case "VIR":
                        resultat = "   Caractere reserve";
                        break;
                        case "IDENT":
                        resultat = "Identificateur";
                        break;

                    default:
                        resultat = "";
                }
                output += word[i] + "   : " + resultat + "\n";
            }
        }
        return output;
    }

    public String syntaxiqueFunction() {
        String resultat[] = AnalyseSyntaxique.analyse(tokens);
        String output = "";
        for (int i = 0; i < resultat.length; i++) {
            output += String.valueOf(i + 1) + ". " + resultat[i] + "\n";
        }

        return output;

    }

}
