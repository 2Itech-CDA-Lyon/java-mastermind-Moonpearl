package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Représente une partie du jeu
 */
public class Game {
    /**
     * Le jeu est-il toujours en cours d'exécution?
     */
    private boolean isRunning = true;
    /**
     * La solution à deviner
     */
    private char[] solution;

    private Scanner scanner;

    /**
     * Créer une nouvelle partie
     */
    public Game()
    {
        scanner = new Scanner(System.in);
        solution = new char[] { 'M', 'M', 'C', 'R' };
    }

    /**
     * Permet de savoir si le jeu est toujours en cours d'exécution
     * @return
     */
    public boolean isRunning()
    {
        return isRunning;
    }

    /**
     * Arrête le jeu
     */
    public void terminate()
    {
        isRunning = false;
        scanner.close();
    }

    /**
     * Décrit un cycle d'exécution du jeu
     */
    public void update()
    {
        // Attend une saisie utilisateur
        String userInput = scanner.nextLine();
        userInput = userInput.toUpperCase();
        
        // Valide la saisie utilisateur
        Pattern pattern = Pattern.compile("^[RVBJCM]{4}$");
        Matcher matcher = pattern.matcher(userInput);
        // Si la saisie utilisateur est valide
        if (matcher.matches()) {
            char[] proposition = userInput.toCharArray();
            // Cherche les couleurs bien placées dans la proposition de l'utilisateur
            int correctCount = 0;
            for (int i = 0; i < 4; i++) {
                if (proposition[i] == solution[i]) {
                    correctCount += 1;
                }
            }
            // Cherche les couleurs absentes de la solution dans la proposition de l'utilisateur
            int absentCount = 0;

            // Compte le nombre d'occurrences de chaque couleur dans la solution
            Map<Character, Integer> solutionFrequencies = new HashMap<>() {{
                put('R', 0);
                put('V', 0);
                put('B', 0);
                put('M', 0);
                put('J', 0);
                put('C', 0);
            }};
            for (char color : solution) {
                solutionFrequencies.put(color, solutionFrequencies.get(color) + 1);
            }

            // Compte le nombre d'occurrences de chaque couleur dans la proposition de l'utilisateur
            Map<Character, Integer> propositionFrequencies = new HashMap<>() {{
                put('R', 0);
                put('V', 0);
                put('B', 0);
                put('M', 0);
                put('J', 0);
                put('C', 0);
            }};
            for (char color : proposition) {
                propositionFrequencies.put(color, propositionFrequencies.get(color) + 1);
            }

            // Cherche dans la proposition, les couleurs qui sont en trop par rapport à la solution
            for (char color : new char[] { 'R', 'V', 'B', 'M', 'J', 'C' }) {
                int excess = propositionFrequencies.get(color) - solutionFrequencies.get(color);
                if (excess > 0) {
                    absentCount += excess;
                }
            }

            int misplacedCount = 4 - absentCount - correctCount;

            // Affiche le résultat de la comparaison pour l'utilisateur
            for (int i = 0; i < correctCount; i++) {
                System.out.print("O ");
            }
            for (int i = 0; i < misplacedCount; i++) {
                System.out.print("X ");
            }
            for (int i = 0; i < absentCount; i++) {
                System.out.print("- ");
            }

            System.out.println("");

        // Si la saisie utilisateur n'est pas valide
        } else {
            System.out.println("Combinaison invalide!");
        }

        System.out.println("");
    }
}
