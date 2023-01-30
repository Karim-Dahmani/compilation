package net.mips.compiler;

public class Symbole {
	Tokens token;
	private String nom;
	ClasseIdf classe;
	
	public ClasseIdf getClasse() {
		return classe;
	}
	public void setClasse(ClasseIdf classe) {
		this.classe = classe;
	}
	public Tokens getToken() {
		return token;
	}
	public void setToken(Tokens token) {
		this.token = token;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Symbole(Tokens t, String n){
		this.token=t;
		this.nom=n;
	}
	public Symbole() {
		this.nom="";
	}
	

}
