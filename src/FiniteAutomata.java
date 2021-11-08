import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FiniteAutomata {

    private List<String> Q; // set of states
    private List<String> E; //set of input symbols
    private String q0; //initial state
    private List<String> F; //set of final state
    private Map<Pair<String, String>, ArrayList<String>> S; // transition function (S : Q X E --> Q)


    public List<String> readLineToList(String line){
        String array[] = line.split(" ");
        return Arrays.asList(array.clone());
    }

    public FiniteAutomata() {
        this.S = new HashMap<>();
    }

    /**
     * It reads the FA
     * */
    public void read(String inputFile) {
        File file = new File(inputFile);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Q = readLineToList(scanner.nextLine());
        E = readLineToList(scanner.nextLine());
        q0 = scanner.nextLine();
        F = readLineToList(scanner.nextLine());

        while(scanner.hasNextLine()) {
            List<String> line = readLineToList(scanner.nextLine());
            Pair<String, String> key = new Pair<>(line.get(0), line.get(1));
            if(!S.containsKey(key)){ // we look if the key is already added
                ArrayList<String> value = new ArrayList<>();
                value.add(line.get(2)); // if not we create a list with the element and put it in S with the new key
                S.put(key, value);
            }
            else{ // if the key already exists we just ad the element to the existing element
                if(!S.get(key).contains(line.get(2)))
                    S.get(key).add(line.get(2));
            }

        }

        scanner.close();
    }

    /**
     * In order to check that a FA is valid we check if the initial state is among the set
     * of states and if all the final states are among the set of states. Also, we do the
     * same for the states in transitions and for the symbols in transitions we check if
     * all of them are in the set of input symbols.
     * */
    public boolean isValid() {
        if(!Q.contains(q0))
            return false;
        for(String state: F){
            if(!Q.contains(state))
                return false;
        }

        for(Map.Entry el : S.entrySet()) {
            Pair<String, String> key = (Pair<String, String>) el.getKey();
            ArrayList<String> value = (ArrayList<String>) el.getValue();
            if(!Q.contains(key.getKey()))
                return false;
            if(!E.contains((key.getValue())))
                return false;
            for(String dest : value) {
                if(!Q.contains(dest))
                    return false;
            }
        }

        return true;
    }

    public boolean isAccepted(String seq) {
        if(this.isDfa()){
            String current = q0;
            for(int i = 0; i < seq.length(); i++){
                char c = seq.charAt(i);
                Pair<String, String> cp = new Pair<>(current, Character.toString(c));
                if(S.keySet().contains(cp)) {
                    current = S.get(cp).get(0);
                }
                else return false;
            }
            if(!F.contains(current))
                return false;
            else return true;
        }
        return false;
    }

    public boolean isDfa() {
        for(Map.Entry el : S.entrySet()) {
            ArrayList<String> value = (ArrayList<String>) el.getValue();
            if(value.size()>1) return false;
        }
        return true;
    }

    public String displayStates() {
        return Q.toString();
    }

    public String displayAlphabet() {
        return E.toString();
    }

    public String displayInitialState() {
        return q0;
    }

    public String displayFinalState() {
        return F.toString();
    }

    public String displayTransition() {
        StringBuilder toDislplay = new StringBuilder("S = { \n");

        for(Map.Entry el : S.entrySet()) {
            Pair<String, String> key = (Pair<String, String>) el.getKey();
            toDislplay.append("(").append(key.getKey()).append(", ").append(key.getValue()).append(") -> ").append(el.getValue()).append("\n");
        }
        toDislplay.append("}");
        return toDislplay.toString();
    }

    @Override
    public String toString() {
        StringBuilder toDislplay = new StringBuilder("FiniteAutomata{\n" +
                "Q=" + Q + "\n" +
                "E=" + E + "\n" +
                "q0='" + q0 + '\'' + "\n" +
                "F=" + F + "\n" );
        toDislplay.append(this.displayTransition());
        return toDislplay.toString();
    }
}
