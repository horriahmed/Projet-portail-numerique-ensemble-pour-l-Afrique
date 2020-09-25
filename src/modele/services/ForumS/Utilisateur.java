package modele.services.ForumS;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	private String m_email ; 
	private int m_id ;
	private String nom;
	private String prenom;
	private List<Integer> roles = new ArrayList<>();
	private String avatar ; 
	
	public Utilisateur(String email , int id) {
		this.m_email = email ; 
		this.m_id = id ; 
	}
	public String getMail() {
		return this.m_email ; 
	}
	public int getId() {
		return this.m_id ; 
	}
	public void addRole(Integer role) {
		roles.add(role);
	}
	public List<Integer> getRoles(){
		return roles;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public void setPrneom(String prenom) {
		this.prenom = prenom;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String roleHomePage() {
		
		List<Integer> roles = this.getRoles();
		
		if(roles.contains(7))
			return "Secrétaire";
		
		if(roles.contains(8))
			return "Administrateur technique";
		
		if(roles.contains(5))
			return "Président";
		
		if(roles.contains(6))
			return "Trésorier";
		
		if(roles.contains(4))
			return "Membre du Conseil d'administration";
		
		if(roles.contains(3))
			return "Membre du Bureau de l'association";
		
		if(roles.contains(0))
			return "Adhérant";
		
		if(roles.contains(1))
			return "Abonné";
		
		return null;
	}
	

}
