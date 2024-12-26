import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Utilisateur {
    private Integer Id;
    private String Nom;
    private String Prenom;
    private String Login;
    private String Password;
    private Integer id;
    private String nom_utilisateur;
    private String email;
    private String mot_de_passe;

    // Constructeur sans champs
    public Utilisateur() {
        // Initialisez les champs si nécessaire
    }

    // Constructeur avec champs
    public Utilisateur(Integer Id, String Nom, String Prenom, String Login, String Password, Integer id, String nom_utilisateur, String email, String mot_de_passe) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Login = Login;
        this.Password = Password;
        this.id = id;
        this.nom_utilisateur = nom_utilisateur;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
Id='" + Id + '\'' + ", Nom='" + Nom + '\'' + ", Prenom='" + Prenom + '\'' + ", Login='" + Login + '\'' + ", Password='" + Password + '\'' + ", id='" + id + '\'' + ", nom_utilisateur='" + nom_utilisateur + '\'' + ", email='" + email + '\'' + ", mot_de_passe='" + mot_de_passe + '\'' + '}';
    }
}
