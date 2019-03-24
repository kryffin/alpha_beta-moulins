package alpha_beta.model.game;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class Player {

    /**
     * nom du joueur
     */
    private String name;

    /**
     * Constructeur par nom de joueur
     * @param name nom du joueur
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * @return nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * Setteur sur le nom du joueur
     * @param name nom du joueur
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
