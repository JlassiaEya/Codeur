import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Travaille {
    private Integer id;
    private String titre;
    private Object date_creation;
    // ManyToOne relation with utilisateur
    private Utilisateur gestionnaire_id;

    // Constructeur sans champs
    public Travaille() {
        // Initialisez les champs si nécessaire
    }

    // Constructeur avec champs
    public Travaille(Integer id, String titre, Object date_creation, Utilisateur gestionnaire_id) {
        this.id = id;
        this.titre = titre;
        this.date_creation = date_creation;
        this.gestionnaire_id = gestionnaire_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Object getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Object date_creation) {
        this.date_creation = date_creation;
    }

    public Utilisateur getGestionnaire_id() {
        return gestionnaire_id;
    }

    public void setGestionnaire_id(Utilisateur gestionnaire_id) {
        this.gestionnaire_id = gestionnaire_id;
    }

    @Override
    public String toString() {
        return "Travaille{" +
id='" + id + '\'' + ", titre='" + titre + '\'' + ", date_creation='" + date_creation + '\'' + ", gestionnaire_id='" + gestionnaire_id + '\'' + '}';
    }
}
