import game.Game;

public class App
{
    // Cette méthode est appelée automatiquement au lancement de l'application
    public static void main(String[] args) throws Exception {
        // Efface la console
        System.out.print("\033[H\033[2J");   
        System.out.flush();
        
        // Crée une nouvelle partie
        Game game = new Game();

        // Tant que la partie est en cours, demande au jeu de se mettre à jour
        while (game.isRunning()) {
            game.update();
        }
    }
}
