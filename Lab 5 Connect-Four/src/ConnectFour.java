import java.util.Scanner;

public class ConnectFour {

    public static void printBoard(char[][] array) { // prints the board for the game when method is called
        for(int j=(array.length-1); j>=0; j--)
        {
            for(int f=0; f<array[0].length; f++)
            {
                System.out.print(array[j][f] + " ");
            }
            System.out.println(" ");
        }
    }

    public static void initializeBoard(char[][] array) { // allows the board when printed to be lined with dashes ("-")

        for(int j=0; j<array.length; j++)
        {
            for(int f=0; f<array[0].length; f++)
            {
                array[j][f] = '-';
            }
        }

    }

    public static int insertChip(char[][] array, int col, char chipType) { // allows the dash ("-") to be replaced with chip ("x" or "o") on the board

        for(int j=0; j<array.length; j++)
        {

            if(array[j][col] == '-'){

                array[j][col] = chipType;

                return j;
            }
        }
        return -1;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) { // checks winner of the game in terms of chip placement on the board

        int num = 0;

        for(int j=0; j<array.length; j++)
        {
            if(array[j][col] == chipType)
            {
                num++;
                if(num == 4)
                {
                    return true;
                }
            }
            else
            {
                num = 0;
            }
        }
        num = 0;

        for(int i=0;i<array[0].length;i++)
        {
            if (array[row][i] == chipType)
            {
                num++;

                if (num == 4)
                {
                    return true;
                }
            }
            else
            {
                num = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        int col; // column of board
        int row; // row of board
        int colChoice = 0; // choice of column by player for where their chip will go
        int end = 0; // end of round, result of where chips end up
        int roundsPlayed = 0; // number of rounds played in one game
        char player; // player char, used in checkIfWinner method
        boolean gameFinished = false; // if game is finished or not, if game is finished program will exit
        boolean player1Win = true; // tells if player 1 wins or not

        while(!false)
        {

            System.out.print("What would you like the height of the board to be?");

            row = scnr.nextInt();

            if(row >= 4)
            {
                break;
            }
            else
            {
                System.out.println("Height should be at least 4. Please try again!");
            }
        }
        while(!false)
        {

            System.out.print("What would you like the length of the board to be?");

            col = scnr.nextInt();

            if(col >= 4)
            {
                break;
            }
            else
            {
                System.out.println("Length should be at least 4. Please try again!");
            }

        }

        char menu[][] = new char[row][col];
        initializeBoard(menu);
        printBoard(menu);

        System.out.println("Player 1 : x");
        System.out.println("Player 2 : o");

        while(!false){

            if(player1Win)
            {

                System.out.print("Player 1:");
                player = 'x';

            }
            else
            {

                System.out.print("Player 2:");
                player = 'o';

            }

            System.out.print("Which column would you like to choose?");

            colChoice = scnr.nextInt();

            if(colChoice < 0 || colChoice >= col){

                System.out.println("Please enter choice between 0 and" + " " + (col-1));

            }
            else
            {

                end = insertChip(menu,colChoice,player);

                if(end == -1)
                {

                    System.out.println("There is no room to insert. Please try again!!!");

                }

                else
                {
                    printBoard(menu);

                    gameFinished = checkIfWinner(menu,colChoice,end,player);

                    if(gameFinished)
                    {

                        if(player1Win)
                        {
                            System.out.print("Player 1 won the game!");
                            System.out.println(" ");
                        }
                        else
                        {
                            System.out.print("Player 2 won the game!");
                            System.out.println(" ");
                        }
                        break;
                    }
                    player1Win = !player1Win;
                    roundsPlayed++;
                }
            }
            if(roundsPlayed == row*col){
                System.out.println("Draw. Nobody wins.");
                break;
            }
        }
    }
}