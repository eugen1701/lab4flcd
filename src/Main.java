import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FiniteAutomata fa = new FiniteAutomata();
        while(true) {
            displayMenu();
            Scanner keyboard = new Scanner(System.in);
            int cmd = keyboard.nextInt();
            if(cmd == 1) {
                System.out.println(fa);
            }
            if(cmd == 2) {
                System.out.println(fa.displayStates());
            }
            if(cmd == 3) {
                System.out.println(fa.displayAlphabet());
            }
            if(cmd == 4) {
                System.out.println(fa.displayTransition());
            }
            if(cmd == 5) {
                System.out.println(fa.displayInitialState());
            }
            if(cmd == 6) {
                System.out.println(fa.displayFinalState());
            }
            if(cmd == 7) {
                if(fa.isValid())
                    System.out.println("TRUE, FA is valid");
                else System.out.println("FALSE, Fa is not valid");
            }
            if(cmd == 8) {
                if(fa.isDfa())
                    System.out.println("TRUE, FA is deterministic");
                else System.out.println("FALSE, FA is not deterministic");
            }
            if(cmd == 9) {
                String seq = keyboard.nextLine();
                seq = seq.strip();
                if(fa.isAccepted(seq))
                    System.out.println("YES, the seq " + seq + " is accepted by FA");
                else System.out.println("NO, the seq " + seq + " is not accepted by FA");
            }
            if(cmd == 10) {
                //try {
                    fa.read("FA.in");
               // }
               // catch (Exception e) {
               //     System.out.println("Finite automata couldn't be read" + e);
              //  }
            }
            if(cmd == 0)
                return;

        }
    }

    private static void displayMenu() {
        System.out.println("1. Display FA ");
        System.out.println("2. Display states ");
        System.out.println("3. Display alphabet ");
        System.out.println("4. Display transitions ");
        System.out.println("5. Display initial states ");
        System.out.println("6. Display final states ");
        System.out.println("7. Check if FA is valid ");
        System.out.println("8. Check if FA is deterministic ");
        System.out.println("9. Check if a sequence is accepted by the FA ");
        System.out.println("10. Read FA from file");
        System.out.println("0. Exit ");
    }
}
