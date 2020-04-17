// Conway's Game of Life - v0
// Reed Nelson

public class Main {

  public static void main(String[] args) {

    final int ROWS = 60;
    final int COLS = 120;
    final int GENERATIONS = 1000;
    final int LIFETIME = 100; // in milliseconds
    double mod = 0.7; // changes likelihood of living in each cell initially
        
    int[][] past = init(ROWS, COLS, mod);
    int[][] pres = new int[COLS][ROWS];
    pres = past;

    for (int g = 0; g < GENERATIONS; g++) {

      // Print the present generation
      System.out.println("Gen " + g);
      print(pres);
      

      // pause for understanding
      wait(LIFETIME);
      
      // Generate the next generation based off previous
      past = pres;
      pres = generate(past);
      
      if (past.equals(pres)) // doesn't work, be cooler if it did though
        break;

    }

  }

  static int[][] init(int rows, int cols, double mod) {
    
    int[][] gen0 = new int[rows][cols];
    
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        gen0[r][c] = (int)(Math.random()*2*mod);
      }
    }
    
    return gen0;
    
  }
   
  static int[][] generate(int[][] past) {

    int[][] pres = new int[past.length][past[0].length];
    int neighbors;

    for (int r = 0; r < past.length; r++) {
      for (int c = 0; c < past[0].length; c++) {

        neighbors = getNeighbors(past, r, c);

        // In case subject cell is alive:
        if (past[r][c] == 1) {
          if (neighbors < 2) {
            pres[r][c] = 0;
          } else if (neighbors >= 2 && neighbors < 4) {
            pres[r][c] = 1;
          } else if (neighbors >= 4) {
            pres[r][c] = 0;
          }
        }

        // In case subject cell is dead:
        if (past[r][c] == 0) {
          if (neighbors == 3) {
            pres[r][c] = 1;
          }
        }

      }
      neighbors = 0;
    }

    return pres;

  }

  static int getNeighbors(int[][] past, int r, int c) {

    int neighbors = 0;

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {

        // skip all out of bounds cases
        if ((r + i < 0) || (r + i > past.length - 1) || (c + j < 0) || (c + j > past[0].length - 1))
          break;

        neighbors += past[r + i][c + j];

      }
    }

    return neighbors - past[r][c];

  }

  static void print(int[][] pres) {
    
    for (int r = 0; r < pres.length; r++) {
      for (int c = 0; c < pres[0].length; c++) {
        if (pres[r][c] == 0) {
          System.out.print(".");
        } else {
          System.out.print("*");
        }
      }
      System.out.println();
    }
    System.out.println();
    
  }
  
  static void wait(int ms) {
    
    try {
      Thread.sleep(ms);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
}