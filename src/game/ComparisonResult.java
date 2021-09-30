package game;

/**
 * Représente le résultat de la comparaison entre deux combinaisons de couleur
 */
public class ComparisonResult
{
    /**
     * La quantité de couleurs bien placées
     */
    private int correctCount;
    /**
     * La quantité de couleurs mal placées
     */
    private int misplacedCount;
    /**
     * La quantité de couleurs absentes
     */
    private int absentCount;

    /**
     * Crée un nouveau résultat de comparaison entre deux combinaisons de couleurs
     * @param correctCount La quantité de couleurs bien placées
     * @param misplacedCount La quantité de couleurs mal placées
     * @param absentCount La quantité de couleurs absentes
     */
    public ComparisonResult(int correctCount, int misplacedCount, int absentCount) {
        this.correctCount = correctCount;
        this.misplacedCount = misplacedCount;
        this.absentCount = absentCount;
    }

    /**
     * Génère une chaîne de caractères indiquant les résultats de la comparaison
     * @return
     */
    public String generateDisplay()
    {
        String result = "";
        for (int i = 0; i < correctCount; i++) {
            result += "O ";
        }
        for (int i = 0; i < misplacedCount; i++) {
            result += "X ";
        }
        for (int i = 0; i < absentCount; i++) {
            result += "- ";
        }
        return result;
    }

    /**
     * Détermine s'il s'agir d'un résultat gagnant
     * @return
     */
    public boolean isWinning()
    {
        return correctCount == 4;
    }
}
