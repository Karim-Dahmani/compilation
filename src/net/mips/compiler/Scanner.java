package net.mips.compiler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scanner {
    public static final int EOF='\0';
	
	ArrayList<Symbole> motsCles;
	Symbole symbCour;
	char carCour;
	FileReader fluxSour;
	
	
	public List<Symbole> getMotsCles() {
		return motsCles;
	}


	public void setMotsCles(ArrayList<Symbole> motsCles) {
		this.motsCles = motsCles;
	}


	public Symbole getSymbCour() {
		return symbCour;
	}


	public void setSymbCour(Symbole symbCour) {
		this.symbCour = symbCour;
	}


	public char getCarCour() {
		return carCour;
	}


	public void setCarCour(char carCour) {
		this.carCour = carCour;
	}


	public FileReader getFluxSour() {
		return fluxSour;
	}


	public void setFluxSour(FileReader fluxSour) {
		this.fluxSour = fluxSour;
	}


	public Scanner(String nomfile)throws 
	IOException, ErreurCompilation {
	File file = new File(nomfile);
	if (!file.exists()) 
		throw new ErreurLexicale(CODES_ERR.FIC_VID_ERR);
		fluxSour=new FileReader(file);
		motsCles=new ArrayList<Symbole>();	
	
	}
	public void initMotsCles() {
		this.motsCles.add(new Symbole(Tokens.PROGRAM_TOKEN,"program"));
		motsCles.add(new Symbole(Tokens.CONST_TOKEN, "const"));
		motsCles.add(new Symbole(Tokens.VAR_TOKEN, "var"));
		motsCles.add(new Symbole(Tokens.BEGIN_TOKEN, "begin"));
		motsCles.add(new Symbole(Tokens.END_TOKEN, "end"));
		motsCles.add(new Symbole(Tokens.IF_TOKEN, "if"));
		motsCles.add(new Symbole(Tokens.THEN_TOKEN, "then"));
		motsCles.add(new Symbole(Tokens.WHILE_TOKEN, "while"));
		motsCles.add(new Symbole(Tokens.DO_TOKEN, "do"));
		motsCles.add(new Symbole(Tokens.WRITE_TOKEN, "write"));
		motsCles.add(new Symbole(Tokens.READ_TOKEN, "read"));
	}
	
	public void Codage_Lex() {
		String nom1 = this.symbCour.getNom();
		for(Symbole sy:motsCles) {
		String mot2= sy.getNom();
		if(nom1.equalsIgnoreCase(mot2)) {
			symbCour.setToken(sy.getToken());
			return;
		} }
		symbCour.setToken(Tokens.ID_TOKEN);
	}
	
	public void lireCar()throws Exception {
		if(fluxSour.ready()) {
			
			carCour=(char) fluxSour.read();
		}else 
			carCour=EOF;
	}
	public void lireNombre() throws Exception 	{
		symbCour.setNom(symbCour.getNom()+carCour);
		lireCar();
		while(Character.isDigit(carCour)) {
			symbCour.setNom(symbCour.getNom()+carCour);
			lireCar();
		}
		symbCour.setToken(Tokens.NUM_TOKEN);
	}
	public void lireMot() throws Exception {
		symbCour.setNom(symbCour.getNom()+carCour);
		lireCar();
		while(Character.isLetterOrDigit(carCour)) {
			symbCour.setNom(symbCour.getNom()+carCour);
			lireCar();
		}
		Codage_Lex();
	}
	public void symbSuiv() throws Exception {
		symbCour=new Symbole();
		while(Character.isWhitespace(carCour))
			lireCar();
		if (Character.isLetter(carCour)) {
			lireMot();
			return;
		}
		if(Character.isDigit(carCour)) {
			lireNombre();
			return;
		}
		switch(carCour) {
		case '+':
			symbCour.setToken(Tokens.PLUS_TOKEN);
			lireCar();
			break;
		case '-':
			symbCour.setToken(Tokens.MOINS_TOKEN);
			lireCar();
			break;
		case '*':
			symbCour.setToken(Tokens.MUL_TOKEN);
			lireCar();
			break;
		case '/':
			symbCour.setToken(Tokens.DIV_TOKEN);
			lireCar();
			break;
		case '(':
			symbCour.setToken(Tokens.PARG_TOKEN);
			lireCar();
			break;
		case ')':
			symbCour.setToken(Tokens.PARD_TOKEN);
			lireCar();
			break;
		case '.':
			symbCour.setToken(Tokens.PNT_TOKEN);
			lireCar();
			break;
		case ',':
			symbCour.setToken(Tokens.VIR_TOKEN);
			lireCar();
			break;
		case ';':
			symbCour.setToken(Tokens.PVIR_TOKEN);
			lireCar();
			break;
		case '=':
			symbCour.setToken(Tokens.EG_TOKEN);
			lireCar();
			break;
		case EOF:
			symbCour.setToken(Tokens.EOF_TOKEN);
			break;
		case ':':
			lireCar();
			switch(carCour) {
			case '=':
				symbCour.setToken(Tokens.AFFEC_TOKEN);
				lireCar();
				break;
			default:
				symbCour.setToken(Tokens.ERR_TOKEN);
				throw new ErreurLexicale(CODES_ERR.CAR_INC_ERR);
			}
			break;
		case '>':
			lireCar();
			switch(carCour) {
			case '=':
				symbCour.setToken(Tokens.SUPEG_TOKEN);
				lireCar();
				break;
			default:
				symbCour.setToken(Tokens.SUP_TOKEN);
				break;
			}
			break;
		case '<':
			lireCar();
			switch(carCour) {
			case '=':
				symbCour.setToken(Tokens.INFEG_TOKEN);
				lireCar();
				break;
			case '>':
				symbCour.setToken(Tokens.DIFF_TOKEN);
				lireCar();
				break;
			default:
				symbCour.setToken(Tokens.INF_TOKEN);
				break;
			}
			break;
		default:
			throw new ErreurLexicale(CODES_ERR.CAR_INC_ERR);}
		}
		
	
	
	public static void main(String args[]) 
			throws Exception {
			Scanner scanner=new Scanner("C:\\Users\\moham\\eclipse-workspace\\mips1Proj\\src\\test.txt");
			scanner.initMotsCles();
			scanner.lireCar();
			while(scanner.getCarCour()!=EOF) {
				scanner.symbSuiv();
				System.out.println(scanner.getSymbCour().getToken());
			}
		}
	
}