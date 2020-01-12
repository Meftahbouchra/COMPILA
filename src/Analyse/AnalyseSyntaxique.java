package Analyse;

import java.util.ArrayList;

public final class AnalyseSyntaxique {

    private AnalyseSyntaxique() {
    }
//si le code est bien forme debut , fin une inst par ligne fiare analyse 
    public static String[] analyse(String input[]) {

        if (compila(input)) {
            ArrayList<String> outputList = new ArrayList<String>();

            int i[] = {0} ;
            while (i[0] < input.length - 2) {
                i[0]++;
                String tmp[] = Instruction(input, i);
                outputList.add(analyseInstruction(tmp));
            }

            String output[] = new String[outputList.size()];
            for (int j = 0; j < output.length; j++) {
                output[j] = outputList.get(j);
            }

            return output;
        } else {
            String error[] = {"ce n'est pas un fichier de coompila"};
            return error;
        }
    }

    private static String[] Instruction(String input[], int i[]) {

        ArrayList<String> instructions = new ArrayList<String>();

        while (i[0] < input.length && !input[i[0]].equals("FININ")) {
            instructions.add(input[i[0]]);
            i[0]++;
            if (input[i[0]].equals("START")) {
                while (!input[i[0]].equals("FINISH") && i[0] < input.length - 1) {
                    instructions.add(input[i[0]]);
                    i[0]++;
                }
                break;
            }
        }
        instructions.add(input[i[0]]);

        String output[] = new String[instructions.size()];
        for (int j = 0; j < output.length; j++) {
            output[j] = instructions.get(j);
        }
        return output;
    }

    private static boolean compila(String input[]) {
        return input[0].equals("STARTPROGRAM") && input[input.length - 1].equals("ENDPROGRAM");
    }

    private static boolean DeclarationINT(String input[]) {
        if (input[0].equals("INTNUMBER")  ) {

            int i = 0;
            while (i < input.length) {
                if (!input[++i].equals("IDENT")) {
                    return false;
                }
                if (input[++i].equals("FININ")) {
                    return true;
                }
                if (!input[i].equals("VIR")) {
                    return false;
                }
            }
            return true;

        } else {
            return false;
        }
    }

    private static boolean DeclarationFLOAT(String input[]) {
        if (input[0].equals("REALNUMBER") ) {

            int i = 0;
            while (i < input.length ) {
                if (!input[++i].equals("IDENT")) {
                    return false;
                }
                if (input[++i].equals("FININ")) {
                    return true;
                }
                if (!input[i].equals("VIR")) {
                    return false;
                }
            }
            return true;

        } else {
            return false;
        }
    }

    private static boolean DeclarationSTRING(String input[]) {
        return (input[0].equals("STRING") && input[1].equals("DEUX") && input[2].equals("CHAR") && input[3].equals("FININ"));
    }

    private static boolean AffectNBR(String input[]) {
        return (input[0].equals("GIVE") && input[1].equals("IDENT") && input[2].equals("DEUX") && (input[3].equals("INT") || input[3].equals("FLOAT")) && input[4].equals("FININ"));
    }

    private static boolean AffectVAR(String input[]) {
        return (input[0].equals("AFFECT") && input[1].equals("IDENT") && input[2].equals("TO") && input[3].equals("IDENT") && input[4].equals("FININ"));
    }


    private static boolean AfficheMES(String input[]) {
        return (input[0].equals("SHOWMES") && input[1].equals("DEUX") && input[2].equals("CHAR") && input[3].equals("FININ"));
    }

    private static boolean AfficheVAL(String input[]) {
        return (input[0].equals("SHOWVAL") && input[1].equals("DEUX") && input[2].equals("IDENT") && input[3].equals("FININ"));
    }
 
    
    private static String analyseInstruction(String input[]) {
        
        if (DeclarationINT(input)) {
            return "Declaration d'un ou plusieur entier(s)";
        } else if (DeclarationFLOAT(input)) {
            return "Declaration d'un ou plusieur reel";
        } else if (DeclarationSTRING(input)) {
            return "Declaration d'une chaine de caractere";
        } else if (AffectNBR(input)) {
            return "Une affectation d'un nombre ";
        } else if (AffectVAR(input)) {
            return " Une affectation d'une variable";
        } else if (AfficheMES(input)) {
            return "affichage d'un message ";
        } else if (AfficheVAL(input)) {
            return "affichage d'une valeur ";
       
        }else {
            return "Erreur Syntaxique";
        }
    }

}
