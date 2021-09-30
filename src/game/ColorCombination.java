package game;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Représente une combinaison de couleurs
 */
public class ColorCombination
{
    /**
     * La liste de toutes les couleurs présentes dans la combinaison
     */
    private Color[] colors;

    /**
     * Crée une combinaison aléatoire
     * @return
     */
    public static ColorCombination createRandom()
    {
        // Crée une nouvelle combinaison de couleurs vide
        ColorCombination result = new ColorCombination();
        result.colors = new Color[4];
        // Choisit 4 fois de suite une couleur au hasard et l'insère dans la combinaison
        for (int i = 0; i < 4; i++) {
            result.colors[i] = Color.pickRandom();
        }
        return result;
    }

    /**
     * Crée une nouvelle combinaison
     */
    public ColorCombination()
    {

    }

    /**
     * Crée une nouvelle combinaison
     * @param input La combinaison représentée sous forme de caractères (exemple: "RRVV" pour Rouge-Rouge-Vert-Vert)
     */
    public ColorCombination(String input)
    {
        // Valide la chaîne entrée
        Pattern pattern = Pattern.compile("^[RVBJCM]{4}$");
        Matcher matcher = pattern.matcher(input);
        // Si la chaîne en entrée est invalide
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("%s is not a valid color combination.", input));
        }
        // Sinon, extrait les couleurs de la chaîne
        colors = new Color[4];
        for (int i = 0; i < 4; i++) {
            colors[i] = Color.findByChar(input.charAt(i));
        }
    }

    /**
     * Calcule le nombre de couleurs bien placées, mal placées et absentes par rapport à une autre combinaison
     * @param otherCombination
     * @return
     */
    public ComparisonResult compareTo(ColorCombination otherCombination)
    {
        // Cherche les couleurs bien placées dans la proposition de l'utilisateur
        int correctCount = 0;
        for (int i = 0; i < 4; i++) {
            if (colors[i] == otherCombination.colors[i]) {
                correctCount += 1;
            }
        }

        // Cherche les couleurs absentes de la solution dans la proposition de l'utilisateur
        int absentCount = 0;

        // Cherche dans la proposition, les couleurs qui sont en trop par rapport à la solution
        for (Color color : Color.values()) {
            // Compte le nombre d'occurrence de chaque couleur parmi les couleurs de la combinaison
            // Et y soustrait le nombre d'occurence de cette même couleurs parmi les couleurs de l'autre combinaison
            int excess = Collections.frequency(Arrays.asList(colors), color) - Collections.frequency(Arrays.asList(otherCombination.colors), color);
            // Ajoute le nombre de couleurs qui est en excès (seulement si chaque couleur est en quantité plus importante dans cette combinaison)
            if (excess > 0) {
                absentCount += excess;
            }
        }

        // Calcule le nombre de couleurs mal placées
        int misplacedCount = 4 - absentCount - correctCount;

        return new ComparisonResult(correctCount, misplacedCount, absentCount);
    }

    /**
     * Génère un affichage pour cette combinaison de couleur
     * @return
     */
    public String generateDisplay()
    {
        String result = "";
        for (Color color : colors) {
            result += color.generateDisplay() + " ";
        }
        return result;
    }
}
