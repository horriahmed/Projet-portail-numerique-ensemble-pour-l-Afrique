package modele.classe.activite;

   public class Lien {

			//les Attributs
			
			 String Titre;
			 String url;
			
			//methodes 
			
			public Lien(String titre , String url) {
				this.Titre=titre;
				this.url=url;
				
			}
			
			
			public String getTitre() {
				return Titre;
			}
			
			public void setTitre(String titre) {
				Titre = titre;
			}
			
			public String getUrl() {
				return url;
			}
			
			public void setUrl(String url) {
				this.url = url;
			}
			
		}