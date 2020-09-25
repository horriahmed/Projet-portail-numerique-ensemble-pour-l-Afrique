package modele.services.ForumS;

import java.sql.Timestamp;
import java.util.Date;

public class Sujet {
	private int m_idSujet ; 
	private String m_nomSujet ; 
	private int m_idTheme ; 
	private String link ; 
	private String linkTheme ; 
	private String contenu ; 
	private int nbLike ; 
	private String nomTheme ; 
	private Timestamp maDate ;
	private int nbCommentaire ; 

	
	

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getNbLike() {
		return nbLike;
	}

	public void setNbLike(int nbLike) {
		this.nbLike = nbLike;
	}

	public int getM_idTheme() {
		return m_idTheme;
	}

	public void setM_idTheme(int m_idTheme) {
		this.m_idTheme = m_idTheme;
	}

	public String getM_nomSujet() {
		return m_nomSujet;
	}

	public void setM_nomSujet(String m_nomSujet) {
		this.m_nomSujet = m_nomSujet;
	}

	public int getM_idSujet() {
		return m_idSujet;
	}

	public void setM_idSujet(int m_idSujet) {
		this.m_idSujet = m_idSujet;
	}

	public String getNomTheme() {
		return nomTheme;
	}

	public void setNomTheme(String nomTheme) {
		this.nomTheme = nomTheme;
	}

	public Timestamp getMaDate() {
		return maDate;
	}

	public void setMaDate(Timestamp maDate) {
		this.maDate = maDate;
	}

	public int getNbCommentaire() {
		return nbCommentaire;
	}

	public void setNbCommentaire(int nbCommentaire) {
		this.nbCommentaire = nbCommentaire;
	}

	public String getLinkTheme() {
		return linkTheme;
	}

	public void setLinkTheme(String linkTheme) {
		this.linkTheme = linkTheme;
	}
	
	

}
