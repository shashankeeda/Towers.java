import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Towers {
    /*
    Data members meant to store the inputs and the random matrix generated
     */
    int[][] matrix;
    int rows;
    int columns;
    int tubesfilled;
    /*
    Constructor meant to initialize the data members with inputs
     */
    public Towers(int ballspertube, int numoftubes,int tubesfill){
        matrix=new int[ballspertube][numoftubes];
        rows=ballspertube;
        columns=numoftubes;
        tubesfilled=tubesfill;
    }
    /*
    The solveMatrix method will keep shifting the tubes that are not already filled
    with the appropriate values in the last two columns and will keep putting them back in order to fill the
    columns appropriately.
     */
    public void solveMatrix(){
        int one=0;
        int zero=0;
        int check=0;
        int count=0;
        /*
        The while loop makes sure that it will keep running until all the columns are sorted
        with either a one or zero
         */
        while(count<tubesfilled-1) {
            int x = count + 2;
            for (int i = count; i < x; i++) {
                for (int j = 0; j < rows; j++) {
                    int t = matrix[rows - j - 1][columns - check - 1];
                    if (matrix[j][i] == 0)
                        zero++;
                    else if (matrix[j][i] == 1)
                        one++;
                    matrix[rows - j - 1][columns - check - 1] = matrix[j][i];
                    System.out.println("t"+i+" - t"+(columns-check-1));
                    matrix[j][i] = t;
                }
                check++;
            }
            check = 0;
            int index = count;
            /*
            If there are more zeros than ones this if statement will execute, or if
            the amount of zeros and ones are the same it will first add all the zeros
            then add everything else to the next column
             */
            if (zero >= one) {
                int r = 0;
                int u = 0;
                for (int w = columns - 2; w < columns; w++) {
                    for (int y = 0; y < rows; y++) {
                        if (!checkColumn(index)) {
                            if (matrix[y][w] == 0) {
                                matrix[rows - r - 1][index] = matrix[y][w];
                                System.out.println("t"+w+" - t"+(index));
                                r++;
                                matrix[y][w] = -1;
                            } else {
                                matrix[rows - u - 1][index + 1] = matrix[y][w];
                                System.out.println("t"+w+" - t"+(index+1));
                                u++;
                                matrix[y][w] = -1;
                            }
                        } else {
                            matrix[rows - u - 1][index + 1] = matrix[y][w];
                            System.out.println("t"+w+" - t"+(index+1));
                            u++;
                            matrix[y][w] = -1;
                        }
                    }
                }
            } else if (zero < one) {
                /*
                This else if statement will execute if there are more ones than zeros,
                it will add all the ones first and then add the remainder ones and zeros
                to the next columns
                 */
                int r = 0;
                int u = 0;
                for (int w = columns - 2; w < columns; w++) {
                    for (int y = 0; y < rows; y++) {
                        if (!checkColumn(index)) {
                            if (matrix[y][w] == 1) {
                                matrix[rows - r - 1][index] = matrix[y][w];
                                System.out.println("t"+w+" - t"+(index));
                                r++;
                                matrix[y][w] = -1;
                            } else {
                                matrix[rows - u - 1][index + 1] = matrix[y][w];
                                System.out.println("t"+w+" - t"+(index+1));
                                u++;
                                matrix[y][w] = -1;
                            }
                        } else {
                            matrix[rows - u - 1][index + 1] = matrix[y][w];
                            System.out.println("t"+w+" - t"+(index+1));
                            u++;
                            matrix[y][w] = -1;
                        }
                    }
                }
            }
            count++;
            zero=0;
            one=0;
        }
        System.out.println();
    }
    /*
    This method is meant to check whether the entire column is matching
    or not and will return false if the column doesn't consist of all the
    same values, it also checks to make sure that the column is not -1
     */
    public boolean checkColumn(int col){
        int temp=matrix[0][col];
        boolean truth=true;
        for(int i=0;i<rows;i++){
            if(matrix[i][col]==temp&&temp!=-1){
                continue;
            }
            else{
                truth=false;
                break;
            }
        }
        return truth;
    }
    /*
    The fillMatrix method will generate a random order of ones and zeros in the columns
    of the matrix array. It will first add the appropriate numbers of ones and zeros in
    the temp array based on the number of tubes filled and the number of rows, then it will
    remove from the ArrayList in a random order until the ArrayList becomes empty
     */
    public void fillMatrix(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                matrix[i][j]=-1;
            }
        }
        ArrayList<Integer> temp=new ArrayList<>(tubesfilled*rows);
        int k=0;
        for(int i=0;i<tubesfilled;i++){
            for(int j=0;j<rows;j++){
                temp.add(k);
            }
            if(k==0)
                k++;
            else
                k--;
        }
        int i=0;
        while(!(temp.isEmpty())){
            for(int j=0;j<tubesfilled;j++){
                int rnd=new Random().nextInt(temp.size());
                matrix[i][j]=temp.get(rnd);
                temp.remove(rnd);
            }
            i++;
        }
    }
    /*
    The printMatrix method will print out the matrix and its contents
    row by columns
     */
    public void printMatrix(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        /*
        Everything in the main method is meant to take input, check for error handling,
        and test out the methods on the bottom
         */
        Scanner input=new Scanner(System.in);
        int numoftubes=0;
        int ballspertube=0;
        int tubesfilled=0;
        System.out.println("Enter the number of tubes, number of balls per tube, and number of tubes filled separated by a space:");
        String in=input.nextLine();
        String arr[]=in.split(" ");
        if(arr.length<3){
            System.out.println("Need 3 inputs, program will exit.");
            System.exit(1);
        }
        try {
            numoftubes = Integer.parseInt(arr[0]);
            ballspertube = Integer.parseInt(arr[1]);
            tubesfilled = Integer.parseInt(arr[2]);
            while(numoftubes<4||numoftubes>12){
                System.out.println(numoftubes+" number of tubes is not a valid input, please enter something between 4 and 12:");
                numoftubes=input.nextInt();
            }
            while(ballspertube<4||ballspertube>8){
                System.out.println(ballspertube+" balls per tube is not a valid input, please enter something between 4 and 8:");
                ballspertube=input.nextInt();
            }
            while(tubesfilled<2||tubesfilled>numoftubes-2){
                System.out.println(tubesfilled+" tubes filled is not a valid input, please enter something between 2 and number of tubes minus 2:");
                tubesfilled=input.nextInt();
            }
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("Program will exit");
            System.exit(2);
        }
        Towers t=new Towers(ballspertube,numoftubes,tubesfilled);
        t.fillMatrix();
        System.out.println("Random Matrix:");
        t.printMatrix();
        System.out.println();
        t.solveMatrix();
        System.out.println("Fixed Matrix: ");
        t.printMatrix();
    }
}
