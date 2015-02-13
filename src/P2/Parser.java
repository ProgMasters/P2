package P2;

import java.util.Stack;


public class Parser {	
	
	private Lexer lexer = new Lexer();
	private Token token;
	
	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}
	
	public void parse() {
		// TODO: Initiate the parse
		Statements();
	}

	// All other methods are private methods
	
	private void Statements() {
		token = lexer.nextToken();
		if (token.tCode != TokenCode.END) {
			Statement();
			token = lexer.nextToken();

			if(token.tCode != TokenCode.SEMICOL) {
				error();
			}
			
			Statements();
		}
	}
	
	private void Statement() {
		if (token.tCode == TokenCode.ID) {
			System.out.println("PUSH " + token.lexeme); // PUSH command for ID token
			
			token = lexer.nextToken();
			
			if (token.tCode == TokenCode.ASSIGN) {
				Expr();
				System.out.println("ASSIGN");
			} else {
				error();
			}
			
		} else if (token.tCode == TokenCode.PRINT) {
			token = lexer.nextToken();
			
			if (token.tCode == TokenCode.ID) {
				System.out.println("PUSH " + token.lexeme);
			} else {
				error();
			}
			
			System.out.println("PRINT");
		} else {
			error();
		}
	}
	
	private void Expr() {
		Term();
		
		Token temp = lexer.peekToken();

		if (temp != null) {
			if (temp.tCode == TokenCode.PLUS) {
				lexer.nextToken(); // remove plus token
				Expr();
				System.out.println("ADD");
			} else if (temp.tCode == TokenCode.MINUS) {
				lexer.nextToken(); // remove minus token
				Expr();
				System.out.println("SUB");
			}
		}
	}
	
	private void Term() {
		Factor();
		
		Token temp = lexer.peekToken();

		if (temp != null) {
			if (temp.tCode == TokenCode.MULT) {
				lexer.nextToken(); // remove mult token
				Term();
				System.out.println("MULT");
			}
		}
	}
	
	private void Factor() {
		token = lexer.nextToken();
		
		if (token.tCode == TokenCode.INT || token.tCode == TokenCode.ID) {
			System.out.println("PUSH " + token.lexeme);
		} else if (token.tCode == TokenCode.LPAREN) {
			Expr();
			
			token = lexer.nextToken();
			if (token.tCode != TokenCode.RPAREN) {
				error();
			}
		} else {
			error();
		}
	}
	
	private void error() {
		System.out.println("Syntax error!");
		System.exit(0);
	}
}
