package game;

import java.util.Scanner;

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
    private ColorCombination solution;

    private Scanner scanner;

    /**
     * Créer une nouvelle partie
     */
    public Game()
    {
        scanner = new Scanner(System.in);
        reset();
    }

    /**
     * Remet la partie à zéro (lorsque le jeu est lancé ou la partie gagnée)
     */
    public void reset()
    {
        // Génère une solution au hasard
        solution = ColorCombination.createRandom();
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
        String userInput = scanner.nextLine().toUpperCase();

        try {
            // Interpréte la saisie de l'utilisateur sous forme de combinaison de couleurs
            ColorCombination proposition = new ColorCombination(userInput);

            // Demande à la proposition de l'utilisateur de se comparer avec la solution
            ComparisonResult result = proposition.compareTo(solution);

            // Affiche le résultat de la comparaison pour l'utilisateur
            System.out.print(proposition.generateDisplay());
            System.out.print("  =>  ");
            System.out.print(result.generateDisplay());
            System.out.println();

            // Si la proposition de l'utilisateur correspond exactement à la solution
            if (result.isWinning()) {
                win();
            }
        }
        // Si l'utilisateur a rentré une combinaison de couleurs invalide
        catch (IllegalArgumentException exception) {
            System.out.println("Combinaison de couleurs invalide!");
        }
            
        System.out.println("");
    }

    /**
     * Procédure à exécuter lorsque le joueur a gagné la partie
     */
    public void win()
    {
        System.out.println("Bravo! Vous avez deviné la combinaison!");
        System.out.println("Voulez-vous rejouer? [O/N]");

        String userInput = scanner.nextLine().toUpperCase();

        switch (userInput) {
            case "O":
                reset();
                break;

            case "N":
                terminate();
                break;

            default:
                throw new RuntimeException("Invalid user input.");
        }
    }
}
