package WaveTune.Wavetune;

public class Musique {

	private int id;
	private String pseudo;
	private String titre;
	private String album;
	private String dateUpload;
	private String url;
	private String artiste;

	public Musique(String pseudo, String titre, String album, String dateUpload, String url, String artiste){
		this.pseudo=pseudo;
		this.titre = titre;
		this.album=album;
		this.dateUpload = dateUpload;
		this.url = url;
		this.artiste = artiste;
	}



	/**
	 * Vérifie si le lien ajouté est compatible avec l'ajout dans une page HTML5
	 * Retourne le lien sous la forme compatibe intégration HTML5
	 */
	public String verficationLien(String lien){
		if(lien.contains("www.youtube.com/embed/") == false){
			String res[] = lien.split("=");
			res[1] = "www.youtube.com/embed/" + res[1];
			return res[1];
		}

		return lien;
	}

	public Musique(){

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getDateUpload() {
		return dateUpload;
	}
	public void setDateUpload(String dateUpload) {
		this.dateUpload = dateUpload;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getArtiste() {
		return artiste;
	}
	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", pseudo=" + pseudo + ", titre=" + titre + ", album=" + album + ", dateUpload=" + dateUpload + ", url=" + url + ", artiste="
				+ artiste + "]";
	}

}
