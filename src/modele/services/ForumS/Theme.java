package modele.services.ForumS;

public class Theme {
	private int m_idTheme;
	private String m_nomTheme;
	private boolean isSubscribed ; 
	private String m_link ; 
	
	public Theme(int id , String nom , boolean sub , String link) {
		this.m_idTheme = id ; 
		this.m_nomTheme = nom ; 
		this.isSubscribed = sub ; 
		this.m_link = link ; 
	}

	public int getId() {
		return this.m_idTheme;
	}

	public String getNom() {
		return this.m_nomTheme;

	}
	public boolean getSub() {
		return this.isSubscribed ; 
	}
	public String getLink() {
		return this.m_link ; 
	}

	

}
