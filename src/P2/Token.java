package P2;

enum TokenCode { 
	ID, ASSIGN, SEMICOL, INT, 
	PLUS, MINUS, MULT, LPAREN, 
	RPAREN, PRINT, END, ERROR
}

public class Token {
	public String lexeme;
	public TokenCode tCode;
	
	public Token(TokenCode tCode, String lexeme) {
		this.lexeme = lexeme;
		this.tCode = tCode;
	}
	
	
	public String toString() {
		return tCode.toString();
	}
}
