package week12;
import java.util.Scanner;

class TTT{
	static Scanner userinput = new Scanner(System.in);
	char[][] board = {{'·','·','·'},{'·','·','·'},{'·','·','·'}};
	int turn = 1;
	char player = 'X';

	public void printBoard (){
		int i,j;
		System.out.println("");
		for (i=0;i<=2;i++)
		{
			for (j=0;j<=2;j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");	
		}
	}
	public void move(int i, int j){
		board[i][j] = player;
		turn++;
	}
	public void unDoMove(int i, int j){
		board[i][j] = '·';
		turn--;
	}
	public void switchPlayers (){
		if (player == 'X') {player = 'O';}
		else player = 'X';
	}
	
	public boolean isLegal(int i, int j){
		if (board[i][j] == '·') return true;
		else return false;
	}
	
	public boolean winner(){
		int i;
		boolean test = false;
		for (i = 0; i<=2;i++)
		{
			if ((board[i][0]==board[i][1]) && (board[i][1]==board[i][2]) &&
					(board[i][0]!='·'))
			{test = true;}
			if ((board[0][i]==board[1][i]) && (board[1][i]==board[2][i]) &&
				(board[0][i]!='·'))
			{test = true;}
		}
		if ((board[0][0]==board[1][1]) && (board[1][1]==board[2][2]) &&
				(board[0][0]!='·'))
		    {test = true;}
		
		if ((board[2][0]==board[1][1]) && (board[1][1]==board[0][2]) &&
				(board[2][0]!='·'))
		    {test = true;}
		return test;
	}
	
	public void human() {
		int i,j;

		boolean test = false;  //have I found a place to go
		while (test == false)
		{
			System.out.println("\nEnter coordinates for where you want to go separated by a space...");
			i = userinput.nextInt();
			j = userinput.nextInt();
			userinput.nextLine();
			if (isLegal(i-1,j-1) == true) {test = true; move(i-1,j-1);} 
			else {System.out.println("That space is already occupied, you must go somewhere else.");}
		}
	}
	public void ai() throws InterruptedException {
		int row = -1;
		int column = -1;
		
		// If the AI can win in one move:
		// In a horizontal or vertical line:
		for (int i = 0; i<=2;i++)
		{
			// Horizontally:
			if ((board[i][0]==board[i][1]) && (board[i][0]==player) && (board[i][2]=='·')) {
				row = i;
				column = 2;
			}	
			else if ((board[i][1]==board[i][2]) && (board[i][1]==player) && (board[i][0]=='·')) {
				row = i;
				column = 0;
			}
			else if ((board[i][0]==board[i][2]) && (board[i][0]==player) && (board[i][1]=='·')) {
				row = i;
				column = 1;
			}

			// Vertically:
			else if ((board[0][i]==board[1][i]) && (board[0][i]==player) && (board[2][i]=='·')) {
				row = 2;
				column = i;
			}
			else if ((board[1][i]==board[2][i]) && (board[1][i]==player) && (board[0][i]=='·')) {
				row = 0;
				column = i;
			}
			else if ((board[0][i]==board[2][i]) && (board[0][i]==player) && (board[1][i]=='·')) {
				row = 1;
				column = i;
			}
		}
		
		// Diagonally:
		if (row == -1) 
		{	
			// Left to right diagonals:
			if ((board[0][0]==board[1][1]) && (board[0][0]==player) && (board[2][2]=='·')) {
				row = 2;
				column = 2;
			}
			else if ((board[1][1]==board[2][2]) && (board[1][1]==player) && (board[0][0]=='·')) {
				row = 0;
				column = 0;
			}
			else if ((board[0][0]==board[2][2]) && (board[0][0]==player) && (board[1][1]=='·')) {
				row = 1;
				column = 1;
			}
			
			// Right to left diagonals:
			else if ((board[0][2]==board[1][1]) && (board[0][2]==player) && (board[2][0]=='·')) {
				row = 2;
				column = 0;
			}
			else if ((board[1][1]==board[2][0]) && (board[1][1]==player) && (board[0][2]=='·')) {
				row = 0;
				column = 2;
			}
			else if ((board[0][2]==board[2][0]) && (board[0][0]==player) && (board[1][1]=='·')) {
				row = 1;
				column = 1;
			}
		}
			
		// If the user can win in one move:
		if (row == -1)
		{
			for (int i = 0; i<=2;i++)
			{	
				// Horizontally:
				if ((board[i][0]==board[i][1]) && (board[i][0]=='X') && (board[i][2]=='·')) {
					row = i;
					column = 2;
				}	
				else if ((board[i][1]==board[i][2]) && (board[i][1]=='X') && (board[i][0]=='·')) {
					row = i;
					column = 0;
				}
				else if ((board[i][0]==board[i][2]) && (board[i][0]=='X') && (board[i][1]=='·')) {
					row = i;
					column = 1;
				}
		
				// Vertically:
				else if ((board[0][i]==board[1][i]) && (board[0][i]=='X') && (board[2][i]=='·')) {
					row = 2;
					column = i;
				}
				else if ((board[1][i]==board[2][i]) && (board[1][i]=='X') && (board[0][i]=='·')) {
					row = 0;
					column = i;
				}
				else if ((board[0][i]==board[2][i]) && (board[0][i]=='X') && (board[1][i]=='·')) {
					row = 1;
					column = i;
				}
			}
			
			// Left to right diagonals:
			if ((board[0][0]==board[1][1]) && (board[0][0]=='X') && (board[2][2]=='·')) {
				row = 2;
				column = 2;
			}
			else if ((board[1][1]==board[2][2]) && (board[1][1]=='X') && (board[0][0]=='·')) {
				row = 0;
				column = 0;
			}
			else if ((board[0][0]==board[2][2]) && (board[0][0]=='X') && (board[1][1]=='·')) {
				row = 1;
				column = 1;
			}
			
			// Right to left diagonals:
			else if ((board[0][2]==board[1][1]) && (board[0][2]=='X') && (board[2][0]=='·')) {
				row = 2;
				column = 0;
			}
			else if ((board[1][1]==board[2][0]) && (board[1][1]=='X') && (board[0][2]=='·')) {
				row = 0;
				column = 2;
			}
			else if ((board[0][2]==board[2][0]) && (board[0][0]=='X') && (board[1][1]=='·')) {
				row = 1;
				column = 1;	
			}
		}
		
		// If any other favorable spots are open:
		if (row == -1) {
			// Middle:
			if (board[1][1]=='·') {
				row = 1;
				column = 1;
			}
			// Top left corner:
			else if (board[0][0]=='·') {
				row = 0;
				column = 0;
			}
			// Top right corner:
			else if (board[0][2]=='·') {
				row = 0;
				column = 2;
			}
			// Bottom left corner:
			else if (board[2][0]=='·') {
				row = 2;
				column = 0;
			}
			// Bottom right corner:
			else if (board[2][2]=='·') {
				row = 2;
				column = 2;
			}
		}
		
		// If none of these spots are open, the AI will generate a random spot from the remaining four:
		if (row == -1) {	
			boolean test = false;
			while (test == false) {
				row = (int) (Math.random()*3.0);
				column = (int) (Math.random()*3.0);
				if (isLegal(row,column) == true) {test = true; move(row,column);} 
			}
		}
		
		// Otherwise if a spot was chosen during the other tests then it will move to it:
		else {		
			Thread.sleep(500);
			System.out.println("\nAI is moving ... ");
			move(row,column); 
			Thread.sleep(1000);
		}
	
	}
}


public class TicTacToeAPP {
	static Scanner userinput = new Scanner(System.in);
	public static void main (String[] args) throws InterruptedException 
	{
		System.out.println("Welcome to the Tic-Tac-Toe App! "
				+ "\nThis program allows you to play head to head against AI.");
		TTT game = new TTT();
		game.printBoard();
		for (int i = 1;i<=5; i++)
		{
            game.human();
			game.printBoard();
			if ((game.winner() == true) || (i == 5)) {break;}
			game.switchPlayers();
			game.ai();
			game.printBoard();
			if ((game.winner() == true) || (i == 5)) {break;}
			game.switchPlayers();
		}
		
		if (game.winner() == true) {System.out.println("\nThe winner is " + game.player);}
		else {System.out.println("\nCat Game.");}
	} //end main line
} //end class




