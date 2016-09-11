//Rohan Menezes
//Proj-Asg3: Project 1 Complete (Battleship)


//import statements
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JApplet;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
   The Battleship_Menezes class implements many of the main methods that make the 
   program run. While the other classes represent individual ships, this class 
   is responsible for instantiating those ships and coordinating their movements 
   into a game. 
*/
public class Battleship_Menezes extends JApplet implements MouseListener{

  //instance variables
  boolean playerTurn = true;
  public JFrame frame;
  int [][] playerBoard = new int[20][20];
  int [][] cpuBoard = new int[20][20];
  boolean firstTime = true;
  boolean cpuFirstTime = true;
  int playerScore = 0;
  int CPUScore = 0;
  boolean cpuHit = false;
  int cpuPreviousX = 0;
  int cpuPreviousY = 0;
  int cpuHitX = 0;
  int cpuHitY = 0;
  int cpuFirstHitX = 0;
  int cpuFirstHitY = 0;
  boolean hasChanged = false;
  boolean rightTried = false;
  boolean downTried = false;
  boolean upTried = false;
  boolean leftTried = false;
  boolean cpuAcMessageSentCopy = false;
  boolean cpuBattleMessageSentCopy = false;
  boolean cpuDestroyMessageSentCopy = false;
  boolean cpuAmHumMessageSentCopy = false;
  double hits;
  double misses;
  double hitAccuracy;
  JTextField field = new JTextField();
  ArrayList<Integer> allX = new ArrayList<Integer> ();
  ArrayList<Integer> allY = new ArrayList<Integer> ();
  boolean acMessageSent = false;
  boolean battleMessageSent = false;
  boolean destroyMessageSent = false;
  boolean amHumMessageSent = false;
  Battleship battleship = new Battleship();
  AircraftCarrier aircraftCarrier = new AircraftCarrier();
  Destroyer destroyer = new Destroyer();
  AmphibiousHummer amphibiousHummer = new AmphibiousHummer();
  ArrayList<Integer> cpuAllX = new ArrayList<Integer> ();
  ArrayList<Integer> cpuAllY = new ArrayList<Integer> ();
  boolean cpuAcMessageSent = false;
  boolean cpuBattleMessageSent = false;
  boolean cpuDestroyMessageSent = false;
  boolean cpuAmHumMessageSent = false;
  Battleship cpuBattleship = new Battleship();
  AircraftCarrier cpuAircraftCarrier = new AircraftCarrier();
  Destroyer cpuDestroyer = new Destroyer();
  AmphibiousHummer cpuAmphibiousHummer = new AmphibiousHummer();
  

  /** sets up the graphics window, draws the battleship grid and basic graphics, and displays 
      the score. */
  public void paint(Graphics g) {
    super.paint(g);
    //System.out.println("Repaint");
    Image image = Toolkit.getDefaultToolkit().getImage("main.png");
     for(int i = 0; i<20; i++) {
        for(int j = 0; j<20; j++) {
           if(playerBoard[j][i] == 0) {
              image = Toolkit.getDefaultToolkit().getImage("main.png");
              g.drawImage(image,(j*25),(i*25),25,25, this);
           }
           if(cpuBoard[j][i] == 0) {
              image = Toolkit.getDefaultToolkit().getImage("main.png");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
           }
           if(playerBoard[j][i] == 1) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25),(i*25),25,25, this);
           }
           if(cpuBoard[j][i] == 5) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
           }
           if(playerBoard[j][i] == 2) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25),(i*25),25,25, this);
           }
           if(cpuBoard[j][i] == 6) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
           }
           if(playerBoard[j][i] == 3) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25),(i*25),25,25, this);
           }
           if(cpuBoard[j][i] == 7) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
           }
           if(playerBoard[j][i] == 4) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25),(i*25),25,25, this);
           }
           if(cpuBoard[j][i] == 8) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
           }
           if(playerBoard[j][i] == -1) {
              image = Toolkit.getDefaultToolkit().getImage("splashreal.jpg");
              g.drawImage(image,(j*25),(i*25),25,25, this);
           }
           if(cpuBoard[j][i] == -2) {
              image = Toolkit.getDefaultToolkit().getImage("splashreal.jpg");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
           }
        }
     }
    frame = new JFrame("Battleship");
    frame.getContentPane().setBackground(Color.BLUE);
    if(firstTime == true)
    {
       aircraftCarrier.playAgain(g);
       allX = aircraftCarrier.addXPoints(allX);
       allY = aircraftCarrier.addYPoints(allY);
       battleship.playAgain(g);
       for(int y = 0; y < allX.size(); y++)
       {
          for(int z = 0; z < (battleship.getXCoords()).size(); z++)
          {
             if((allX.get(y) == (battleship.getXCoords()).get(z)) && (allY.get(y) == (battleship.getYCoords()).get(z)))
             {
                //System.out.println("battleship reset");
                //System.out.println("(" + allX.get(y) + "," + allY.get(y) + ")");
                //System.out.println("(" + (battleship.getXCoords()).get(z) + "," + (battleship.getYCoords()).get(z) + ")");
                battleship.playAgain(g);
                y = 0;
                z = 0;
             }
          }
       }
       allX = battleship.addXPoints(allX);
       allY = battleship.addYPoints(allY);
       destroyer.playAgain(g);
       for(int y = 0; y < allX.size(); y++)
       {
          for(int z = 0; z < (destroyer.getXCoords()).size(); z++)
          {
             if((allX.get(y) == (destroyer.getXCoords()).get(z)) && (allY.get(y) == (destroyer.getYCoords()).get(z)))
             {
                //System.out.println("destroyer reset");
                //System.out.println("(" + allX.get(y) + "," + allY.get(y) + ")");
                //System.out.println("(" + (destroyer.getXCoords()).get(z) + "," + (destroyer.getYCoords()).get(z) + ")");
                destroyer.playAgain(g);
                y = 0;
                z = 0;
             }
          }
       }
       allX = destroyer.addXPoints(allX);
       allY = destroyer.addYPoints(allY);
       amphibiousHummer.playAgain(g);
       for(int y = 0; y < allX.size(); y++)
       {
          for(int z = 0; z < (amphibiousHummer.getXCoords()).size(); z++)
          {
             if((allX.get(y) == (amphibiousHummer.getXCoords()).get(z)) && (allY.get(y) == (amphibiousHummer.getYCoords()).get(z)))
             {
                //System.out.println("am hum reset");
                //System.out.println("(" + allX.get(y) + "," + allY.get(y) + ")");
                //System.out.println("(" + (amphibiousHummer.getXCoords()).get(z) + "," + (amphibiousHummer.getYCoords()).get(z) + ")");
                amphibiousHummer.playAgain(g);
                y = 0;
                z = 0;
             }
          }
       }
       allX = amphibiousHummer.addXPoints(allX);
       allY = amphibiousHummer.addYPoints(allY);
       
       cpuAircraftCarrier.playAgain(g);
       cpuAllX = cpuAircraftCarrier.addXPoints(cpuAllX);
       cpuAllY = cpuAircraftCarrier.addYPoints(cpuAllY);
       cpuBattleship.playAgain(g);
       for(int y = 0; y < cpuAllX.size(); y++)
       {
          for(int z = 0; z < (cpuBattleship.getXCoords()).size(); z++)
          {
             if((cpuAllX.get(y) == (cpuBattleship.getXCoords()).get(z)) && (cpuAllY.get(y) == (cpuBattleship.getYCoords()).get(z)))
             {
                //System.out.println("cpu battleship reset");
                //System.out.println("(" + cpuAllX.get(y) + "," + cpuAllY.get(y) + ")");
                //System.out.println("(" + (cpuBattleship.getXCoords()).get(z) + "," + (cpuBattleship.getYCoords()).get(z) + ")");
                cpuBattleship.playAgain(g);
                y = 0;
                z = 0;
             }
          }
       }
       cpuAllX = cpuBattleship.addXPoints(cpuAllX);
       cpuAllY = cpuBattleship.addYPoints(cpuAllY);
       cpuDestroyer.playAgain(g);
       for(int y = 0; y < cpuAllX.size(); y++)
       {
          for(int z = 0; z < (cpuDestroyer.getXCoords()).size(); z++)
          {
             if((cpuAllX.get(y) == (cpuDestroyer.getXCoords()).get(z)) && (cpuAllY.get(y) == (cpuDestroyer.getYCoords()).get(z)))
             {
                //System.out.println("cpu destroyer reset");
                //System.out.println("(" + cpuAllX.get(y) + "," + cpuAllY.get(y) + ")");
                //System.out.println("(" + (cpuDestroyer.getXCoords()).get(z) + "," + (cpuDestroyer.getYCoords()).get(z) + ")");
                cpuDestroyer.playAgain(g);
                y = 0;
                z = 0;
             }
          }
       }
       cpuAllX = cpuDestroyer.addXPoints(cpuAllX);
       cpuAllY = cpuDestroyer.addYPoints(cpuAllY);
       cpuAmphibiousHummer.playAgain(g);
       for(int y = 0; y < cpuAllX.size(); y++)
       {
          for(int z = 0; z < (cpuAmphibiousHummer.getXCoords()).size(); z++)
          {
             if((cpuAllX.get(y) == (cpuAmphibiousHummer.getXCoords()).get(z)) && (cpuAllY.get(y) == (cpuAmphibiousHummer.getYCoords()).get(z)))
             {
                //System.out.println("cpu am hum reset");
                //System.out.println("(" + cpuAllX.get(y) + "," + cpuAllY.get(y) + ")");
                //System.out.println("(" + (cpuAmphibiousHummer.getXCoords()).get(z) + "," + (cpuAmphibiousHummer.getYCoords()).get(z) + ")");
                cpuAmphibiousHummer.playAgain(g);
                y = 0;
                z = 0;
             }
          }
       }
       cpuAllX = cpuAmphibiousHummer.addXPoints(cpuAllX);
       cpuAllY = cpuAmphibiousHummer.addYPoints(cpuAllY);
    
       firstTime = false;
    }
    image = Toolkit.getDefaultToolkit().getImage("player.png");
    g.drawImage(image,75,507,200,65, this);
    image = Toolkit.getDefaultToolkit().getImage("cpu.png");
    g.drawImage(image,845,507,100,65, this);
    image = Toolkit.getDefaultToolkit().getImage("battleship.png");
    g.drawImage(image,390,507,350,90, this);
    image = Toolkit.getDefaultToolkit().getImage("author.png");
    g.drawImage(image,410,607,300,45, this);
    g.setColor(Color.black);
    String score1 = "Player Wins = " + playerScore;
    g.drawString(score1,115,620);
    String score2 = "CPU Wins = " + CPUScore;
    g.drawString(score2,850,620);
    image = Toolkit.getDefaultToolkit().getImage("main.png");
    g.drawImage(image,505,110,25,25, this);
    String score = "= not tried";
    g.drawString(score,530,127);
    image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
    g.drawImage(image,505,140,25,25, this);
    score = "= hit";
    g.drawString(score,530,157);
    image = Toolkit.getDefaultToolkit().getImage("splashreal.jpg");
    g.drawImage(image,505,170,25,25, this);
    score = "= miss";
    g.drawString(score,530,187);
    g.setColor(Color.red);
    for(int i = 25; i < 501; i = i + 25)
    {
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
    }
    
    for(int i = 600; i < 1100; i = i + 25)
    {
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
       g.drawLine(i,0,i,500);
    }
    
    for(int j = 25; j < 501; j = j + 25)
    {
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
       g.drawLine(0,j,500,j);
    }
    
    for(int j = 25; j < 501; j = j + 25)
    {
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
       g.drawLine(600,j,1100,j);
    }
  }
  /** updates the virtual grid (that is stored in a 2-D array) for either the 
      player or the cpu */
  public void changeArray(int row, int column)
  {
     if(playerTurn)
     {
        if(playerBoard [row][column] == 0)
        {
           int hit = 0;
           if(battleship.checkHit(row,column))
           {
              hit = 1;
              hits++;
           }
           if(aircraftCarrier.checkHit(row,column))
           {
              hit = 2;
              hits++;
           }
           if(destroyer.checkHit(row,column))
           {
              hit = 3;
              hits++;
           }
           if(amphibiousHummer.checkHit(row,column))
           {
              hit = 4;
              hits++;
           }
           if(hit != 0)
           {
              playerBoard [row][column] = hit;
           }
           else
           {
              playerBoard [row][column] = -1;
              misses++;
           }
        }
        else
        {
           JOptionPane.showMessageDialog(frame,"This spot has already been " + 
                                      "selected. Please try again.");
        }
     }
     else
     {
        if(cpuBoard [row][column] == 0)
        {
           int hit = 0;
           if(cpuBattleship.checkHit(row,column))
           {
              hit = 5;
           }
           if(cpuAircraftCarrier.checkHit(row,column))
           {
              hit = 6;
           }
           if(cpuDestroyer.checkHit(row,column))
           {
              hit = 7;
           }
           if(cpuAmphibiousHummer.checkHit(row,column))
           {
              hit = 8;
           }
           if(hit != 0)
           {
              cpuBoard [row][column] = hit;
           }
           else
           {
              cpuBoard [row][column] = -2;
           }
        }
        else
        {
           if(playerTurn)
           {
              JOptionPane.showMessageDialog(frame,"This spot has already been " + 
                                      "selected. Please try again.");
           }
           else
           {
              //System.out.println("cpu already guessed here");
              int xpos = (int)(Math.random() * 20);
              int ypos = (int)(Math.random() * 20);
              fixCPU(xpos, ypos);
           }
        }
     }
     playerTurn = !playerTurn;
  }
  
  public void fixCPU(int row, int column)
  {
     if(playerTurn)
     {
        if(playerBoard [row][column] == 0)
        {
           int hit = 0;
           if(battleship.checkHit(row,column))
           {
              hit = 1;
              hits++;
           }
           if(aircraftCarrier.checkHit(row,column))
           {
              hit = 2;
              hits++;
           }
           if(destroyer.checkHit(row,column))
           {
              hit = 3;
              hits++;
           }
           if(amphibiousHummer.checkHit(row,column))
           {
              hit = 4;
              hits++;
           }
           if(hit != 0)
           {
              playerBoard [row][column] = hit;
           }
           else
           {
              playerBoard [row][column] = -1;
              misses++;
           }
        }
        else
        {
           JOptionPane.showMessageDialog(frame,"This spot has already been " + 
                                      "selected. Please try again.");
        }
     }
     else
     {
        if(cpuBoard [row][column] == 0)
        {
           int hit = 0;
           if(cpuBattleship.checkHit(row,column))
           {
              hit = 5;
           }
           if(cpuAircraftCarrier.checkHit(row,column))
           {
              hit = 6;
           }
           if(cpuDestroyer.checkHit(row,column))
           {
              hit = 7;
           }
           if(cpuAmphibiousHummer.checkHit(row,column))
           {
              hit = 8;
           }
           if(hit != 0)
           {
              cpuBoard [row][column] = hit;
           }
           else
           {
              cpuBoard [row][column] = -2;
           }
        }
        else
        {
           if(playerTurn)
           {
              JOptionPane.showMessageDialog(frame,"This spot has already been " + 
                                      "selected. Please try again.");
           }
           else
           {
              //System.out.println("cpu already guessed here");
              int xpos = (int)(Math.random() * 20);
              int ypos = (int)(Math.random() * 20);
              fixCPU(xpos, ypos);
           }
        }
     }
  }
  /** updates the user interface based on our virtual grid */
  public void displayBoard(Graphics g)
  {
     g.setColor(Color.blue);
     Image image = Toolkit.getDefaultToolkit().getImage("main.jpg");
     for(int i = 0; i<20; i++) {
        for(int j = 0; j<20; j++) {
           if(playerBoard[j][i] == 1) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25),(i*25),25,25, this);
           }
           if(playerBoard[j][i] == 2) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25),(i*25),25,25, this);
           }
           if(playerBoard[j][i] == 3) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25),(i*25),25,25, this);
           }
           if(playerBoard[j][i] == 4) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25),(i*25),25,25, this);
              //g.drawLine((j*25),(i*25),(j*25)+25,(i*25)+25);
              //g.drawLine((j*25),(i*25)+25,(j*25)+25,(i*25));
           }
           else if(playerBoard[j][i] == -1)
           {
              image = Toolkit.getDefaultToolkit().getImage("splashreal.jpg");
              g.drawImage(image,(j*25),(i*25),25,25, this);
              //g.drawOval((j*25),(i*25),25,25);
           }
        }
     }
     for(int i = 0; i<20; i++) {
        for(int j = 0; j<20; j++) {
           if(cpuBoard[j][i] == 5) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
           }
           if(cpuBoard[j][i] == 6) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
           }
           if(cpuBoard[j][i] == 7) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
           }
           if(cpuBoard[j][i] == 8) {
              image = Toolkit.getDefaultToolkit().getImage("explosion.jpg");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
              //g.drawLine((j*25),(i*25),(j*25)+25,(i*25)+25);
              //g.drawLine((j*25),(i*25)+25,(j*25)+25,(i*25));
           }
           else if(cpuBoard[j][i] == -2)
           {
              image = Toolkit.getDefaultToolkit().getImage("splashreal.jpg");
              g.drawImage(image,(j*25)+600,(i*25),25,25, this);
              //g.drawOval((j*25),(i*25),25,25);
           }
        }
     }
     g.setColor(Color.black);
     hitAccuracy = (hits)/(hits + misses);
     hitAccuracy = (hitAccuracy * 100) + 0.5;
     int hitAccuracyReal = (int)(hitAccuracy);
     String score = "Hits: " + (int)(hits) + ", Misses: " + (int)(misses) + ", Hit Accuracy: " + hitAccuracyReal + "%";
     //field.setText(score);
     //frame.add(field);
     //g.drawString(score,30,577);
  }
  
  /** checks to see if there is a winner and/or if a ship has been sunk 
      and displays the appropriate message for the situation */
  public void getResult(){
     String result = aircraftCarrier.getResult(playerBoard);
     String result2 = battleship.getResult(playerBoard);
     String result3 = destroyer.getResult(playerBoard);
     String result4 = amphibiousHummer.getResult(playerBoard);
     String finalResult = result + result2 + result3 + result4;
     if(result.equals("AircraftCarrier5") && !(acMessageSent))
     {
        JOptionPane.showMessageDialog(null,"You have sunk the CPU's aircraft carrier!"); 
        acMessageSent = true;
     } 
     if(result2.equals("Battleship4") && !(battleMessageSent))
     {
        JOptionPane.showMessageDialog(null,"You have sunk the CPU's battleship!"); 
        battleMessageSent = true;
     }   
     if(result3.equals("Destroyer3") && !(destroyMessageSent))
     {
        JOptionPane.showMessageDialog(null,"You have sunk the CPU's destroyer!"); 
        destroyMessageSent = true;
     }  
     if(result4.equals("AmphibiousHummer2") && !(amHumMessageSent))
     {
        JOptionPane.showMessageDialog(null,"You have sunk the CPU's amphibious hummer!"); 
        amHumMessageSent = true;
     }  
     String result5 = cpuAircraftCarrier.getResult(cpuBoard);
     String result6 = cpuBattleship.getResult(cpuBoard);
     String result7 = cpuDestroyer.getResult(cpuBoard);
     String result8 = cpuAmphibiousHummer.getResult(cpuBoard);
     String finalResult2 = result5 + result6 + result7 + result8;
     ////System.out.println("HFHAHARTHSRHSRHSRHRH\nDGAGADGADGAGAEGGRGERDFHSH\nSHFHRTHWRHRHRHRHRHHRWRHRH\nRHEHHETHEHBAEHEHAETHAEH");
     //System.out.println(finalResult2);
     if(result5.equals("AircraftCarrier5") && !(cpuAcMessageSent))
     {
        JOptionPane.showMessageDialog(null,"Your aircraft carrier has been sunk!"); 
        cpuAcMessageSent = true;
        hasChanged = true;
     } 
     if(result6.equals("Battleship4") && !(cpuBattleMessageSent))
     {
        JOptionPane.showMessageDialog(null,"Your battleship has been sunk!"); 
        cpuBattleMessageSent = true;
        hasChanged = true;
     }   
     if(result7.equals("Destroyer3") && !(cpuDestroyMessageSent))
     {
        JOptionPane.showMessageDialog(null,"Your destroyer has been sunk!"); 
        cpuDestroyMessageSent = true;
        hasChanged = true;
     }  
     if(result8.equals("AmphibiousHummer2") && !(cpuAmHumMessageSent))
     {
        JOptionPane.showMessageDialog(null,"Your amphibious hummer has been sunk!"); 
        cpuAmHumMessageSent = true;
        hasChanged = true;
     } 
     if(finalResult.equals("AircraftCarrier5Battleship4Destroyer3AmphibiousHummer2") || finalResult2.equals("AircraftCarrier5Battleship4Destroyer3AmphibiousHummer2")){
        hitAccuracy = (hits)/(hits + misses);
        //System.out.println(hitAccuracy);
        hitAccuracy = (hitAccuracy * 100) + 0.5;
        //System.out.println(hitAccuracy);
        int hitAccuracyReal = (int)(hitAccuracy);
        if(finalResult.equals("AircraftCarrier5Battleship4Destroyer3AmphibiousHummer2")){
           JOptionPane.showMessageDialog(null,"You have won the game!");
           JOptionPane.showMessageDialog(null,"Your stats:\nHits: " + (int)(hits) + ", Misses: " + (int)(misses) + ", Hit Accuracy: " + hitAccuracyReal + "%");
           playerScore++;
        }
        else{
           JOptionPane.showMessageDialog(null,"The CPU has won the game!");
           JOptionPane.showMessageDialog(null,"Your stats:\nHits: " + (int)(hits) + ", Misses: " + (int)(misses) + ", Hit Accuracy: " + hitAccuracyReal + "%");
           CPUScore++;
        }
        playAgain(this.getGraphics());
     }
  }
  
  // checks if a certain space on the cpu's grid is a hit or miss
  public boolean checkCPUHit(int row, int column)
  {
     boolean hit = false;
     System.out.println(cpuBoard[row][column]);
     if(cpuBoard[row][column] == 5 || cpuBoard[row][column] == 6 || cpuBoard[row][column] == 7 || cpuBoard[row][column] == 8)
     {
        hit = true;
     }
     return hit;
  } 
  
  /** checks if the user wants to play another game and redirects 
      user to proper screen based on answer */
  public void playAgain(Graphics g){
     //System.out.println("Play Again");
     int playAgain = JOptionPane.showConfirmDialog(frame,"Would you like to play again?",
                         "Would you like to play again?",JOptionPane.YES_NO_OPTION);
     if(playAgain == JOptionPane.YES_OPTION){
        firstTime = true;
        cpuFirstTime = true;
        hits = 0.0;
        misses = 0.0;
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++)
                  playerBoard[i][j] = 0;
        }
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++)
                  cpuBoard[i][j] = 0;
        }
        update(this.getGraphics());
         cpuHit = false;
         cpuPreviousX = 0;
         cpuPreviousY = 0;
         cpuHitX = 0;
         cpuHitY = 0;
         cpuFirstHitX = 0;
         cpuFirstHitY = 0;
         hasChanged = false;
         rightTried = false;
         downTried = false;
         upTried = false;
         leftTried = false;
         cpuAcMessageSentCopy = false;
         cpuBattleMessageSentCopy = false;
         cpuDestroyMessageSentCopy = false;
         cpuAmHumMessageSentCopy = false;
         field = new JTextField();
         allX = new ArrayList<Integer> ();
         allY = new ArrayList<Integer> ();
         acMessageSent = false;
         battleMessageSent = false;
         destroyMessageSent = false;
         amHumMessageSent = false;
         cpuAllX = new ArrayList<Integer> ();
         cpuAllY = new ArrayList<Integer> ();
         cpuAcMessageSent = false;
         cpuBattleMessageSent = false;
         cpuDestroyMessageSent = false;
         cpuAmHumMessageSent = false;
     }
     else{
        if(playerScore > CPUScore){
           String score = "PLAYER WINS!!! \n\nFinal Score:\n Player Wins = " + playerScore+ ", CPU Wins = " + CPUScore;
           JOptionPane.showMessageDialog(frame, score);
           System.exit(0);
        }
        else if(CPUScore > playerScore){
           String score = "CPU WINS!!! \n\nFinal Score:\n Player Wins = " + playerScore+ ", CPU Wins = " + CPUScore;
           JOptionPane.showMessageDialog(frame, score);
           System.exit(0);
        }
        else
        {
           String score = "IT'S A TIE!!! \n\nFinal Score:\n X = " + playerScore+ ", O = " + CPUScore;
           JOptionPane.showMessageDialog(frame, score);
           System.exit(0);
        }
     }  
  }
  
  /** insignificant method that we must define to make MouseListener work */
  public void mousePressed(MouseEvent e) {
  }
  
  /** reads in the specific box that the user clicks, picks a box for the cpu,
      and updates the virtual grid and user interface to reflect that */
  public void mouseReleased(MouseEvent e) {
     int xpos = 0;
     int ypos = 0;
     if(playerTurn)
     {
        xpos = (int)(e.getX()/25);
        ypos = (int)(e.getY()/25);
     }
     /*else
     {
        xpos = (int)((e.getX()-600)/25);
        ypos = (int)(e.getY()/25);
        //System.out.println(xpos);
        //System.out.println(ypos);
     }*/
     if((xpos < 20 && ypos < 20) && (xpos >=0 && ypos >= 0)){
        changeArray(xpos, ypos);
        displayBoard(this.getGraphics());
        getResult();
        boolean isHit = checkCPUHit(cpuPreviousX, cpuPreviousY);
        System.out.println("isHit: " + isHit);
        System.out.println("cpuHit: " + cpuHit);
        if(!cpuHit) {
           if(isHit)
           {
              cpuHit = true;
              cpuHitX = cpuPreviousX;
              cpuHitY = cpuPreviousY;
              cpuFirstHitX = cpuPreviousX;
              cpuFirstHitY = cpuPreviousY;
              hasChanged = false;
              cpuAcMessageSentCopy = cpuAcMessageSent;
              cpuBattleMessageSentCopy = cpuBattleMessageSent;
              cpuDestroyMessageSentCopy = cpuDestroyMessageSent;
              cpuAmHumMessageSentCopy = cpuAmHumMessageSent;
              rightTried = false;
              upTried = false;
              downTried = false;
              leftTried = false;
              if((cpuPreviousX < 19 && cpuPreviousY < 20) && (cpuPreviousX >=0 && cpuPreviousY >= 0)){
              xpos = cpuPreviousX + 1;
              ypos = cpuPreviousY;
              }
              else{
              xpos = cpuPreviousX - 1;
              ypos = cpuPreviousY;
              }
              rightTried = true; 
           }
           else
           {
              xpos = (int)(Math.random() * 20);
              ypos = (int)(Math.random() * 20);
              cpuPreviousX = xpos;
              cpuPreviousY = ypos;
              cpuHit = false;
              isHit = false;
              cpuHitX = 0;
              cpuHitY = 0;
               cpuFirstHitX = 0;
               cpuFirstHitY = 0;
               rightTried = false;
               downTried = false;
               upTried = false;
               leftTried = false;
           }
        }
        else
        {
           if(isHit)
           {
              cpuHit = true;
              cpuHitX = cpuPreviousX;
              cpuHitY = cpuPreviousY;
              rightTried = false;
              upTried = false;
              downTried = false;
              leftTried = false;
           }
           if(!hasChanged){
           //if(cpuAcMessageSentCopy == cpuAcMessageSent && cpuBattleMessageSentCopy == cpuBattleMessageSent && cpuDestroyMessageSentCopy == cpuDestroyMessageSent && cpuAmHumMessageSentCopy == cpuAmHumMessageSent){
             if((cpuHitX - cpuFirstHitX) == 0 && (cpuHitY - cpuFirstHitY) == 0)
             {
              if (!rightTried && !upTried && !downTried && !leftTried)
              {
                 if((cpuHitX < 19 && cpuHitY < 20) && (cpuHitX >=0 && cpuHitY >= 0)){
                 xpos = cpuHitX + 1;
                 ypos = cpuHitY;
                 }
                 else{
                 xpos = cpuHitX - 1;
                 ypos = cpuHitY;
                 }
                 rightTried = true;
                 cpuPreviousX = xpos;
                 cpuPreviousY = ypos;
              }
              else if (rightTried && !upTried && !downTried && !leftTried)
              {
                 if((cpuHitX < 20 && cpuHitY < 19) && (cpuHitX >=0 && cpuHitY >= 0)){
                 xpos = cpuHitX;
                 ypos = cpuHitY + 1;
                 }
                 else{
                 xpos = cpuHitX;
                 ypos = cpuHitY - 1;
                 }
                 downTried = true;
                 cpuPreviousX = xpos;
                 cpuPreviousY = ypos;
              }
              else if (rightTried && !upTried && downTried && !leftTried)
              {
                 if((cpuHitX < 20 && cpuHitY < 20) && (cpuHitX >=0 && cpuHitY >= 1)){
                 xpos = cpuHitX;
                 ypos = cpuHitY - 1;
                 }
                 else{
                 xpos = cpuHitX;
                 ypos = cpuHitY + 1;
                 }
                 upTried = true;
                 cpuPreviousX = xpos;
                 cpuPreviousY = ypos;
              }
              else if (rightTried && upTried && downTried && !leftTried)
              {
                 if((cpuHitX < 20 && cpuHitY < 20) && (cpuHitX >=1 && cpuHitY >= 0)){
                 xpos = cpuHitX - 1;
                 ypos = cpuHitY;
                 }
                 else{
                 xpos = cpuHitX + 1;
                 ypos = cpuHitY;
                 }
                 cpuPreviousX = xpos;
                 cpuPreviousY = ypos;
                 leftTried = true;
              }
             }
             /*if((cpuPreviousX < 20 && cpuPreviousY < 20) && (cpuPreviousX >=1 && cpuPreviousY >= 0)){
                 xpos = cpuPreviousX - 1;
                 ypos = cpuPreviousY;
                 }
                 else if((cpuPreviousX < 19 && cpuPreviousY < 20) && (cpuPreviousX >=0 && cpuPreviousY >= 0)){
                 xpos = cpuPreviousX + 1;
                 ypos = cpuPreviousY;
                 }
                 else{
                 xpos = (int)(Math.random() * 20);
                 ypos = (int)(Math.random() * 20); 
                 }*/
              else
              {
                 if((cpuHitX - cpuFirstHitX) <= -1)
                 {
                    if(cpuHitX == cpuPreviousX)
                    {
                       xpos = cpuHitX - 1;
                       ypos = cpuHitY;
                    }
                    else
                    {
                       xpos = cpuFirstHitX + 1;
                       ypos = cpuFirstHitY;
                    }
                    cpuPreviousX = xpos;
                    cpuPreviousY = ypos;
                 }
                 if((cpuHitX - cpuFirstHitX) >= 1)
                 {
                    if(cpuHitX == cpuPreviousX)
                    {
                       xpos = cpuHitX + 1;
                       ypos = cpuHitY;
                    }
                    else
                    {
                       xpos = cpuFirstHitX - 1;
                       ypos = cpuFirstHitY;
                    }
                    cpuPreviousX = xpos;
                    cpuPreviousY = ypos;
                 }
                 if((cpuHitY - cpuFirstHitY) <= -1)
                 {
                    if(cpuHitY == cpuPreviousY)
                    {
                       xpos = cpuHitX;
                       ypos = cpuHitY - 1;
                    }
                    else
                    {
                       xpos = cpuFirstHitX;
                       ypos = cpuFirstHitY + 1;
                    }
                    cpuPreviousX = xpos;
                    cpuPreviousY = ypos;
                 }
                 if((cpuHitY - cpuFirstHitY) >= 1)
                 {
                    if(cpuHitY == cpuPreviousY)
                    {
                       xpos = cpuHitX;
                       ypos = cpuHitY + 1;
                    }
                    else
                    {
                       xpos = cpuFirstHitX;
                       ypos = cpuFirstHitY - 1;
                    }
                    cpuPreviousX = xpos;
                    cpuPreviousY = ypos;
                 }
              }
           }
           else
           {
              xpos = (int)(Math.random() * 20);
              ypos = (int)(Math.random() * 20);
               cpuHit = false;
               cpuPreviousX = 0;
               cpuPreviousY = 0;
               cpuHitX = 0;
               cpuHitY = 0;
               cpuFirstHitX = 0;
               cpuFirstHitY = 0;
               hasChanged = false;
               rightTried = false;
               downTried = false;
               upTried = false;
               leftTried = false;
               cpuAcMessageSentCopy = false;
               cpuBattleMessageSentCopy = false;
               cpuDestroyMessageSentCopy = false;
               cpuAmHumMessageSentCopy = false;
               if(checkCPUHit(xpos, ypos) == true)
               {
                  isHit = true;
                  cpuHit = true;
                  cpuHitX = xpos;
                  cpuHitY = ypos;
                  cpuFirstHitX = xpos;
                  cpuFirstHitY = ypos;
               }
           }
     }
  }
     else
        JOptionPane.showMessageDialog(frame, "Invalid Box. Please try again.");
  changeArray(xpos, ypos);
  displayBoard(this.getGraphics());
  getResult();
  cpuPreviousX = xpos;
  cpuPreviousY = ypos;
  }
  
  /** insignificant method that we must define to make MouseListener work */
  public void mouseEntered(MouseEvent e) {
  }
  
  /** insignificant method that we must define to make MouseListener work */
  public void mouseExited(MouseEvent e) {
  }
  
  /** insignificant method that we must define to make MouseListener work */
  public void mouseClicked(MouseEvent e) {
  }
  
  /** initializes the MouseListener object */
  public void init() {
     addMouseListener(this);
     setSize(1100, 650);
     JOptionPane.showMessageDialog(frame,"Welcome to Battleship by Rohan Menezes!\nThe objective" +
                                         " of this game is to find your opponent's four ships by " +
                                         "guessing before the computer finds your vessels.\n\nTypes of " +
                                         "ships:\nAircraft Carrier: 5 units\nBattleship: 4 units" +
                                         "\nDestroyer: 3 units\nAmphibious Hummer: 2 units"); 
  }
}

/**
   This class contains all the necessary methods and instance variables for the aircraft 
   carrier and keeps track of its x and y coordinates, if it has been hit, and how many times 
   it has been hit.
*/
class AircraftCarrier
{  
  //instance variables
  ArrayList <Integer> aircraftCarrierX = new ArrayList <Integer> ();
  ArrayList <Integer> aircraftCarrierY = new ArrayList <Integer> ();
      
  public ArrayList getXCoords()
  {
     return aircraftCarrierX;
  }
  
  public ArrayList getYCoords()
  {
     return aircraftCarrierY;
  }
  
  public ArrayList addXPoints(ArrayList<Integer> allX)
  {
     for(int i = 0; i < 5; i++)
     {
        allX.add(aircraftCarrierX.get(i));
     }
     return allX;
  }   
  
  public ArrayList addYPoints(ArrayList<Integer> allY)
  {
     for(int i = 0; i < 5; i++)
     {
        allY.add(aircraftCarrierY.get(i));
     }
     return allY;
  }
  
  /** checks if the ship has been hit*/
  public boolean checkHit(int row, int column)
  {
     boolean hit = false;
     for(int i = 0; i < 5; i++)
     {
        if(row == aircraftCarrierX.get(i) && column == aircraftCarrierY.get(i))
        {
           hit = true;
        }
     }
     return hit;
  }
  
  
  /** checks to see how many times the ship has been hit*/
  public String getResult(int[][] playerBoard){
     String result = "";
     ArrayList <Integer> xHits = new ArrayList <Integer> ();
     ArrayList <Integer> yHits = new ArrayList <Integer> ();
     int turn = 0;
     for(int x = 0; x < 5; x++)
     {
        xHits.add(20);
        yHits.add(20);
     }
     for(int a = 0; a < 20; a++)
     {
        for(int i = 0; i < 20; i++)
        {
           if(playerBoard[a][i] == 2 || playerBoard[a][i] == 6)
           {
              xHits.set(turn,a);
              yHits.set(turn,i);
              turn++;
              result = "AircraftCarrier" + turn;
              //System.out.println(result);
           }
        }
     }        
     return result;
  }
  
  /** resets the coordinates of the ship */
  public void playAgain(Graphics g){
        aircraftCarrierX = new ArrayList<Integer>();
        aircraftCarrierY = new ArrayList<Integer>();
        if((int)(Math.random()+0.5) == 0)
       {
          int randInt1 = (int)(Math.random() * 15);
          int randInt2 = (int)(Math.random() * 20);
          for(int i = randInt1; i < randInt1 + 5; i++)
          {
             aircraftCarrierX.add(i);
          }
          for(int j = 0; j < 5; j++)
          {
            aircraftCarrierY.add(randInt2);
          }
       }
       else
       {
          int randInt1 = (int)(Math.random() * 15);
          int randInt2 = (int)(Math.random() * 20);
          for(int i = randInt1; i < randInt1 + 5; i++)
          {
             aircraftCarrierY.add(i);
          }
          for(int j = 0; j < 5; j++)
          {
            aircraftCarrierX.add(randInt2);
          }
       }
       //System.out.println(aircraftCarrierX.get(0));
       //System.out.println(aircraftCarrierY.get(0));
     } 
  }

/**
   This class contains all the necessary methods and instance variables for the battleship 
   and keeps track of its x and y coordinates, if it has been hit, and how many times 
   it has been hit.
*/
class Battleship
{  
  //instance variables
  ArrayList <Integer> battleshipX = new ArrayList <Integer> ();
  ArrayList <Integer> battleshipY = new ArrayList <Integer> ();
  
  public ArrayList getXCoords()
  {
     return battleshipX;
  }
  
  public ArrayList getYCoords()
  {
     return battleshipY;
  }
  
  public ArrayList addXPoints(ArrayList<Integer> allX)
  {
     for(int i = 0; i < 4; i++)
     {
        allX.add(battleshipX.get(i));
     }
     return allX;
  }   
  
  public ArrayList addYPoints(ArrayList<Integer> allY)
  {
     for(int i = 0; i < 4; i++)
     {
        allY.add(battleshipY.get(i));
     }
     return allY;
  }
  
  //checks if the ship has been hit
  public boolean checkHit(int row, int column)
  {
     boolean hit = false;
     for(int i = 0; i < 4; i++)
     {
        if(row == battleshipX.get(i) && column == battleshipY.get(i))
        {
           hit = true;
        }
     }
     return hit;
  }
  
  
  /** checks how many times the ship has been hit */
  public String getResult(int[][] playerBoard){
     String result = "";
     ArrayList <Integer> xHits = new ArrayList <Integer> ();
     ArrayList <Integer> yHits = new ArrayList <Integer> ();
     int turn = 0;
     for(int x = 0; x < 4; x++)
     {
        xHits.add(20);
        yHits.add(20);
     }
     for(int a = 0; a < 20; a++)
     {
        for(int i = 0; i < 20; i++)
        {
           if(playerBoard[a][i] == 1 || playerBoard[a][i] == 5)
           {
              xHits.set(turn,a);
              yHits.set(turn,i);
              turn++;
              result = "Battleship" + turn;
              //System.out.println(result);
           }
        }
     }       
     return result;
  }
  
  /** resets the coordinates of the ship */
  public void playAgain(Graphics g){
        battleshipX = new ArrayList<Integer>();
        battleshipY = new ArrayList<Integer>();
        if((int)(Math.random()+0.5) == 0)
       {
          int randInt1 = (int)(Math.random() * 15);
          int randInt2 = (int)(Math.random() * 20);
          for(int i = randInt1; i < randInt1 + 4; i++)
          {
             battleshipX.add(i);
          }
          for(int j = 0; j < 4; j++)
          {
            battleshipY.add(randInt2);
          }
       }
       else
       {
          int randInt1 = (int)(Math.random() * 15);
          int randInt2 = (int)(Math.random() * 20);
          for(int i = randInt1; i < randInt1 + 4; i++)
          {
             battleshipY.add(i);
          }
          for(int j = 0; j < 4; j++)
          {
            battleshipX.add(randInt2);
          }
       }
       //System.out.println(battleshipX.get(0));
       //System.out.println(battleshipY.get(0));
     } 
  }
  
  /**
   This class contains all the necessary methods and instance variables for the destroyer 
   and keeps track of its x and y coordinates, if it has been hit, and how many times 
   it has been hit.
  */
  class Destroyer
  {  
     ArrayList <Integer> destroyerX = new ArrayList <Integer> ();
     ArrayList <Integer> destroyerY = new ArrayList <Integer> ();
     
     public ArrayList getXCoords()
     {
        return destroyerX;
     }
     
     public ArrayList getYCoords()
     {
        return destroyerY;
     }
     
     public ArrayList addXPoints(ArrayList<Integer> allX)
     {
        for(int i = 0; i < 3; i++)
        {
           allX.add(destroyerX.get(i));
        }
        return allX;
     }   
     
     public ArrayList addYPoints(ArrayList<Integer> allY)
     {
        for(int i = 0; i < 3; i++)
        {
           allY.add(destroyerY.get(i));
        }
        return allY;
     }
     
     //checks if the ship has been hit
     public boolean checkHit(int row, int column)
     {
        boolean hit = false;
        for(int i = 0; i < 3; i++)
        {
           if(row == destroyerX.get(i) && column == destroyerY.get(i))
           {
              hit = true;
           }
        }
        return hit;
     }
     
     
     /** checks how many times the ship has been hit */
     public String getResult(int[][] playerBoard){
        String result = "";
        ArrayList <Integer> xHits = new ArrayList <Integer> ();
        ArrayList <Integer> yHits = new ArrayList <Integer> ();
        int turn = 0;
        for(int x = 0; x < 3; x++)
        {
           xHits.add(20);
           yHits.add(20);
        }
        for(int a = 0; a < 20; a++)
        {
           for(int i = 0; i < 20; i++)
           {
              if(playerBoard[a][i] == 3 || playerBoard[a][i] == 7)
              {
                 xHits.set(turn,a);
                 yHits.set(turn,i);
                 turn++;
                 result = "Destroyer" + turn;
                 //System.out.println(result);
              }
           }
        }       
        return result;
     }
     
     /** resets the coordinates and position of the ship */
     public void playAgain(Graphics g){
           destroyerX = new ArrayList<Integer>();
           destroyerY = new ArrayList<Integer>();
           if((int)(Math.random()+0.5) == 0)
          {
             int randInt1 = (int)(Math.random() * 15);
             int randInt2 = (int)(Math.random() * 20);
             for(int i = randInt1; i < randInt1 + 3; i++)
             {
                destroyerX.add(i);
             }
             for(int j = 0; j < 3; j++)
             {
               destroyerY.add(randInt2);
             }
          }
          else
          {
             int randInt1 = (int)(Math.random() * 15);
             int randInt2 = (int)(Math.random() * 20);
             for(int i = randInt1; i < randInt1 + 3; i++)
             {
                destroyerY.add(i);
             }
             for(int j = 0; j < 3; j++)
             {
               destroyerX.add(randInt2);
             }
          }
          //System.out.println(destroyerX.get(0));
          //System.out.println(destroyerY.get(0));
        } 
  }
  
  /**
   This class contains all the necessary methods and instance variables for the amphibious 
   hummer and keeps track of its x and y coordinates, if it has been hit, and how many times 
   it has been hit.
  */
  class AmphibiousHummer
  {  
     //instance variables
     ArrayList <Integer> amphibiousHummerX = new ArrayList <Integer> ();
     ArrayList <Integer> amphibiousHummerY = new ArrayList <Integer> ();
     
     public ArrayList getXCoords()
     {
        return amphibiousHummerX;
     }
     
     public ArrayList getYCoords()
     {
        return amphibiousHummerY;
     }
     
     public ArrayList addXPoints(ArrayList<Integer> allX)
     {
        for(int i = 0; i < 2; i++)
        {
           allX.add(amphibiousHummerX.get(i));
        }
        return allX;
     }   
     
     public ArrayList addYPoints(ArrayList<Integer> allY)
     {
        for(int i = 0; i < 2; i++)
        {
           allY.add(amphibiousHummerY.get(i));
        }
        return allY;
     }
     
     //check if ship has been hit
     public boolean checkHit(int row, int column)
     {
        boolean hit = false;
        for(int i = 0; i < 2; i++)
        {
           if(row == amphibiousHummerX.get(i) && column == amphibiousHummerY.get(i))
           {
              hit = true;
           }
        }
        return hit;
     }
     
     
     /** checks how many times the ship has been hit */
     public String getResult(int[][] playerBoard){
        String result = "";
        ArrayList <Integer> xHits = new ArrayList <Integer> ();
        ArrayList <Integer> yHits = new ArrayList <Integer> ();
        int turn = 0;
        for(int x = 0; x < 2; x++)
        {
           xHits.add(20);
           yHits.add(20);
        }
        for(int a = 0; a < 20; a++)
        {
           for(int i = 0; i < 20; i++)
           {
              if(playerBoard[a][i] == 4 || playerBoard[a][i] == 8)
              {
                 xHits.set(turn,a);
                 yHits.set(turn,i);
                 turn++;
                 result = "AmphibiousHummer" + turn;
                 //System.out.println(result);
              }
           }
        }        
        return result;
     }
     
     /** resets the coordinates and the position of the ship */
     public void playAgain(Graphics g){
           amphibiousHummerX = new ArrayList<Integer>();
           amphibiousHummerY = new ArrayList<Integer>();
           if((int)(Math.random()+0.5) == 0)
          {
             int randInt1 = (int)(Math.random() * 15);
             int randInt2 = (int)(Math.random() * 20);
             for(int i = randInt1; i < randInt1 + 2; i++)
             {
                amphibiousHummerX.add(i);
             }
             for(int j = 0; j < 2; j++)
             {
               amphibiousHummerY.add(randInt2);
             }
          }
          else
          {
             int randInt1 = (int)(Math.random() * 15);
             int randInt2 = (int)(Math.random() * 20);
             for(int i = randInt1; i < randInt1 + 2; i++)
             {
                amphibiousHummerY.add(i);
             }
             for(int j = 0; j < 2; j++)
             {
               amphibiousHummerX.add(randInt2);
             }
          }
          //System.out.println(amphibiousHummerX.get(0));
          //System.out.println(amphibiousHummerY.get(0));
        } 
  }