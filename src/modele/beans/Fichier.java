package modele.beans;

public class Fichier {
	 	private String description;
	    private String nom;
	 	private String titre;
	 	private Integer type;

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription( String description ) {
	        this.description = description;
	    }

	    public String getNom() {
	        return nom;
	    }

	    public void setNom( String nom ) {
	        this.nom = nom;
	    }
	    
	    public String getTitre() {
	    	return titre;
	    }
	    public void setTitre(String titre) {
	    	this.titre = titre;
	    }
	    
	    public Integer getType() {
	    	return type;
	    }
	    public void setType(Integer type) {
	    	this.type = type;
	    }
}
