package net.mips.compiler;

import java.io.IOException;

public class Parser {
	Scanner scanner;

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public Parser(String filename) throws IOException, ErreurCompilation {
		scanner = new Scanner(filename);
		
	}
	
	public void testeAccept(Tokens t,CODES_ERR c) throws Exception {
		if(scanner.getSymbCour().getToken()==t) {
			scanner.symbSuiv();
		}
		else {
			throw new ErreurLexicale(c);
		}
			
			
	}
	
	public void PROGRAM() throws Exception {
		testeAccept(Tokens.PROGRAM_TOKEN,CODES_ERR.PROGRAM_ERR);
		testeAccept(Tokens.ID_TOKEN,CODES_ERR.ID_ERR);
		testeAccept(Tokens.PVIR_TOKEN,CODES_ERR.PVIR_ERR);
		BLOCK();
		testeAccept(Tokens.PNT_TOKEN,CODES_ERR.PNT_ERR);
	}
	public void BLOCK() throws Exception {
		CONSTS();
		VARS();
		INSTS();
		
	}
	public void CONSTS() throws Exception {
		testeAccept(Tokens.CONST_TOKEN,CODES_ERR.CONST_ERR);
		testeAccept(Tokens.ID_TOKEN,CODES_ERR.ID_ERR);
		testeAccept(Tokens.EG_TOKEN,CODES_ERR.EG_ERR);
		testeAccept(Tokens.NUM_TOKEN,CODES_ERR.NUM_ERR);
		testeAccept(Tokens.PVIR_TOKEN,CODES_ERR.PVIR_ERR);
		
		while(scanner.symbCour.getToken()==Tokens.ID_TOKEN) {
		     scanner.symbSuiv();
			testeAccept(Tokens.EG_TOKEN,CODES_ERR.EG_ERR);
			testeAccept(Tokens.NUM_TOKEN,CODES_ERR.NUM_ERR);
			testeAccept(Tokens.PVIR_TOKEN,CODES_ERR.PVIR_ERR);
		}
		
	}
	public void VARS() throws Exception {
		testeAccept(Tokens.VAR_TOKEN,CODES_ERR.VAR_ERR);
		testeAccept(Tokens.ID_TOKEN,CODES_ERR.ID_ERR);
		while(scanner.symbCour.getToken()==Tokens.VIR_TOKEN) {
		     scanner.symbSuiv();
		     testeAccept(Tokens.ID_TOKEN,CODES_ERR.ID_ERR);
		}
		testeAccept(Tokens.PVIR_TOKEN,CODES_ERR.PVIR_ERR);
		
		
	}
	public void INSTS() throws Exception {
		testeAccept(Tokens.BEGIN_TOKEN,CODES_ERR.BEGIN_ERR);
		INST();
		while(scanner.symbCour.getToken()==Tokens.PVIR_TOKEN) {
		     scanner.symbSuiv();
		     INST();
		}
		testeAccept(Tokens.END_TOKEN,CODES_ERR.END_ERR);
	}
	public void INST() throws Exception {
		if(scanner.symbCour.getToken()==Tokens.ID_TOKEN) {
			affec();
		}else if (scanner.symbCour.getToken()==Tokens.IF_TOKEN){
			si();
			
		}else if (scanner.symbCour.getToken()==Tokens.WHILE_TOKEN){
			tantque();
			
		}else if (scanner.symbCour.getToken()==Tokens.WRITE_TOKEN){
			ecrire();
			
		}else if (scanner.symbCour.getToken()==Tokens.READ_TOKEN){
			lire();
			
		}else if (scanner.symbCour.getToken()==Tokens.BEGIN_TOKEN){
			INSTS();
			
		}
	}
    public void affec() throws Exception {
    	testeAccept(Tokens.ID_TOKEN,CODES_ERR.ID_ERR);
    	testeAccept(Tokens.AFFEC_TOKEN,CODES_ERR.AFFEC_ERR);
    	expr();
	}
    public void si() throws Exception {
    	testeAccept(Tokens.IF_TOKEN,CODES_ERR.IF_ERR);
    	cond();
    	testeAccept(Tokens.THEN_TOKEN,CODES_ERR.THEN_ERR);
    	INST();
    	
	}
	
    public void tantque() throws Exception {
    	testeAccept(Tokens.WHILE_TOKEN,CODES_ERR.WHILE_ERR);
    	cond();
    	testeAccept(Tokens.DO_TOKEN,CODES_ERR.DO_ERR);
    	INST();
		
	}
public void ecrire() throws Exception {
	testeAccept(Tokens.WRITE_TOKEN,CODES_ERR.WRITE_ERR);
	testeAccept(Tokens.PARG_TOKEN,CODES_ERR.PARG_ERR);
	expr();
	while(scanner.symbCour.getToken()==Tokens.VIR_TOKEN) {
	     scanner.symbSuiv();
	     expr();
	}
	testeAccept(Tokens.PARD_TOKEN,CODES_ERR.PARD_ERR);
		
	}
public void lire() throws Exception {
	testeAccept(Tokens.READ_TOKEN,CODES_ERR.READ_ERR);
	testeAccept(Tokens.PARG_TOKEN,CODES_ERR.PARG_ERR);
	testeAccept(Tokens.ID_TOKEN,CODES_ERR.ID_ERR);
	while(scanner.symbCour.getToken()==Tokens.VIR_TOKEN) {
	     scanner.symbSuiv();
	     testeAccept(Tokens.ID_TOKEN,CODES_ERR.ID_ERR);
	}
	testeAccept(Tokens.PARD_TOKEN,CODES_ERR.PARD_ERR);
	
	
}
public void cond() throws Exception {
	 expr();
	 relop();
	 expr();
}
public void relop() throws Exception {
	if(scanner.symbCour.getToken()==Tokens.EG_TOKEN) {
		testeAccept(Tokens.EG_TOKEN,CODES_ERR.EG_ERR);
	}else if (scanner.symbCour.getToken()==Tokens.INF_TOKEN && scanner.symbCour.getToken()==Tokens.SUP_TOKEN ){
		testeAccept(Tokens.INF_TOKEN,CODES_ERR.INF_ERR);
		testeAccept(Tokens.SUP_TOKEN,CODES_ERR.SUP_ERR);
		
	}else if (scanner.symbCour.getToken()==Tokens.INF_TOKEN){
		testeAccept(Tokens.INF_TOKEN,CODES_ERR.INF_ERR);
		
	}else if (scanner.symbCour.getToken()==Tokens.SUP_TOKEN){
		testeAccept(Tokens.SUP_TOKEN,CODES_ERR.SUP_ERR);
		
	}else if (scanner.symbCour.getToken()==Tokens.INFEG_TOKEN){
		testeAccept(Tokens.INFEG_TOKEN,CODES_ERR.INFEG_ERR);
		
	}else if (scanner.symbCour.getToken()==Tokens.SUPEG_TOKEN){
		testeAccept(Tokens.SUPEG_TOKEN,CODES_ERR.SUPEG_ERR);
		
	}
}
public void expr() throws Exception {
	term();
	while(scanner.symbCour.getToken()==Tokens.PLUS_TOKEN || scanner.symbCour.getToken()==Tokens.MOINS_TOKEN) {
		addop(); 
	     term();
	     
	}
	
}
public void term() throws Exception {
	fact();
	while(scanner.symbCour.getToken()==Tokens.MUL_TOKEN || scanner.symbCour.getToken()==Tokens.DIV_TOKEN) {
		mulop(); 
	     fact();
	     
	}
}
public void fact() throws Exception {
	if(scanner.symbCour.getToken()==Tokens.ID_TOKEN) {
		testeAccept(Tokens.ID_TOKEN,CODES_ERR.ID_ERR);
	}else if (scanner.symbCour.getToken()==Tokens.NUM_TOKEN) {
		testeAccept(Tokens.NUM_TOKEN,CODES_ERR.NUM_ERR);
	}else if(scanner.symbCour.getToken()==Tokens.PARG_TOKEN) {
		testeAccept(Tokens.PARG_TOKEN,CODES_ERR.PARG_ERR);
         expr();
         testeAccept(Tokens.PARD_TOKEN,CODES_ERR.PARD_ERR);
	}
		
}
public void mulop() throws Exception {
	if(scanner.symbCour.getToken()==Tokens.MUL_TOKEN) {
		testeAccept(Tokens.MUL_TOKEN,CODES_ERR.MUL_ERR);
	}else if (scanner.symbCour.getToken()==Tokens.DIV_TOKEN) {
		testeAccept(Tokens.DIV_TOKEN,CODES_ERR.DIV_ERR);
	}
}
public void addop() throws Exception {
	if(scanner.symbCour.getToken()==Tokens.PLUS_TOKEN) {
		testeAccept(Tokens.PLUS_TOKEN,CODES_ERR.PLUS_ERR);
	}else if (scanner.symbCour.getToken()==Tokens.MOINS_TOKEN) {
		testeAccept(Tokens.MOINS_TOKEN,CODES_ERR.MOINS_ERR);
	}
}

public static void main(String[] args) throws Exception {
    Parser sc = new Parser("C:\\Users\\moham\\eclipse-workspace\\mips1Proj\\src\\test.txt");
    sc.scanner.initMotsCles();
    sc.scanner.lireCar();
//    while (sc.scanner.getCarCour()!=Scanner.EOF) {
        sc.scanner.symbSuiv();
        sc.PROGRAM();
//    }

    }
}

