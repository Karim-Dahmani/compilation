package net.mips.compiler;
import java.io.IOException;
import java.util.ArrayList;

public class ScannerWS extends Scanner{
	ArrayList<Symbole> tableSymb;
	
	public ArrayList<Symbole> getTableSymb() {
		return tableSymb;
	}
	public void setTableSymb(ArrayList<Symbole> tableSymb) {
		this.tableSymb = tableSymb;
	}
	private int placeSymb;
	public ScannerWS(String nomfile) throws IOException, ErreurCompilation {
		super(nomfile);
		tableSymb=new ArrayList<Symbole>();	
	}
	public void inittableSymb() {
		this.tableSymb.add(new Symbole(Tokens.PROGRAM_TOKEN,"program"));
		tableSymb.add(new Symbole(Tokens.CONST_TOKEN, "const"));
		tableSymb.add(new Symbole(Tokens.VAR_TOKEN, "var"));
		tableSymb.add(new Symbole(Tokens.BEGIN_TOKEN, "begin"));
		tableSymb.add(new Symbole(Tokens.END_TOKEN, "end"));
		tableSymb.add(new Symbole(Tokens.IF_TOKEN, "if"));
		tableSymb.add(new Symbole(Tokens.THEN_TOKEN, "then"));
		tableSymb.add(new Symbole(Tokens.WHILE_TOKEN, "while"));
		tableSymb.add(new Symbole(Tokens.DO_TOKEN, "do"));
		tableSymb.add(new Symbole(Tokens.WRITE_TOKEN, "write"));
		tableSymb.add(new Symbole(Tokens.READ_TOKEN, "read"));
	}
	public void Codage_Lex() {
		String nom1 = this.symbCour.getNom();
		for(Symbole sy:tableSymb) {
		String mot2= sy.getNom();
		if(nom1.equalsIgnoreCase(mot2)) {
			symbCour.setToken(sy.getToken());
			return;
		} }
		symbCour.setToken(Tokens.ID_TOKEN);
	}
	
}
