package modele.services.ForumS;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Commentaire {
	private String m_email ; 
	private String m_commentaire ; 
	private Date m_date ; 
	private String image ; 
	private ArrayList<String> fichier ; 
	
	public Commentaire(String mail , String comment,Timestamp timestamp) {
		this.m_email = mail ; 
		this.m_commentaire= comment ; 
		this.m_date=timestamp ; 
	}
	public String getMail() {
		return this.m_email ; 
		
	}
	public String getCommentaire() {
		return this.m_commentaire ; 
		
	}
	public Date getData() {
		return this.m_date ; 
		
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public ArrayList<String> getFichier() {
		return fichier;
	}
	public void setFichier(ArrayList<String> fichier) {
		this.fichier = fichier;
	}
	

}
