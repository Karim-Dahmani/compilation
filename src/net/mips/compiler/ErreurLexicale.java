package net.mips.compiler;

public class ErreurLexicale extends ErreurCompilation {

	
	public ErreurLexicale( CODES_ERR cd) {
		super(cd.getMessage());
	}
	
	
}
