package WaveTune.Wavetune;

public class User {

	private int id;
	private String pseudo;
	private String password;
	private String email;
	private String type;
	private String dateInscription;
	private String des;
	private String fb;
	private String twitter;
	private String gplus;


	public User() {}

	public User(int id, String pseudo, String password, String email) {
		this.id = id;
		this.pseudo = pseudo;
		this.password = password;
		this.email = email;
		this.type = "standard user";
		this.dateInscription = "00/00/0000";
		this.des = "";
		this.fb = "*Facebook*";
		this.twitter = "*Twitter*";
		this.gplus = "*Google+*";
	}

	public User(int id, String pseudo, String password, String email, String type, String dateInscription){
		this(id,pseudo,password,email);
		this.type = type;
		this.dateInscription = dateInscription;
		this.des = "";
		this.fb = "*Facebook*";
		this.twitter = "*Twitter*";
		this.gplus = "*Google+*";
	}

	public String getDes() {
		return des;
	}
	public void setDes(String des){
		this.des = des;
	}
	public String getFb() {
		return fb;
	}
	public void setFb(String fb){
		this.fb = fb;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter){
		this.twitter = twitter;
	}
	public String getGplus() {
		return gplus;
	}
	public void setGplus(String gplus){
		this.gplus = gplus;
	}

	public String getMdp() {
		return password;
	}
	public void setMdp(String mdp){
		this.password = mdp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
