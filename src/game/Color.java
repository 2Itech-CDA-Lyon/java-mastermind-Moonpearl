package game;

import java.util.Random;

import Utils.ConsoleColor;

/**
 * Représente une couleur du jeu
 */
public enum Color
{
    Red('R', ConsoleColor.RED),
    Green('V', ConsoleColor.GREEN),
    Blue('B', ConsoleColor.BLUE),
    Magenta('M', ConsoleColor.MAGENTA),
    Yellow('J', ConsoleColor.YELLOW),
    Cyan('C', ConsoleColor.CYAN);

    /**
     * Le caractère permettant de symboliser cette couleur
     */
    private char character;
    /**
     * La couleur de la console dans laquelle afficher cette couleur
     */
    private ConsoleColor consoleColor;

    /**
     * Crée une nouvelle couleur
     */
    private Color(char character, ConsoleColor consoleColor)
    {
        this.character = character;
        this.consoleColor = consoleColor;
    }

    /**
     * Récupère le caractère permettant de symboliser cette couleur
     * @return
     */
    public char getCharacter()
    {
        return character;
    }

    /**
     * Renvoie la couleur qui correspond à un caractère donné
     * @param character
     * @return
     */
    public static Color findByChar(char character)
    {
        for (Color color : Color.values()) {
            if (character == color.getCharacter())
            {
                return color;
            }
        }
        throw new IllegalArgumentException(String.format("No existing color matches the character %s", character));
    }

    /**
     * Choisit une couleur au hasard
     * @return
     */
    public static Color pickRandom()
    {
        return Color.values()[new Random().nextInt(Color.values().length)];  
    }

    /**
     * Génère un affichage pour cette couleur
     * @return
     */
    public String generateDisplay()
    {
        return consoleColor + Character.toString(character) + ConsoleColor.RESET;
    }
}
