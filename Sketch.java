import processing.core.PApplet;
import processing.core.PImage;

/**
 * User input
 * @author: A. Chan
 */
public class Sketch extends PApplet {

  PImage background;
  PImage Fabroa;

  float fltDvdX = random(0, 553);
  float fltDvdY = random(0, 600);

  //circle variables (position and speed)
  float fltCircleX = random(0, 1000);
  float fltCircleY = random(0, 800);
  float circleSpeedX = 10;
  float circleSpeedY = 10;
  
  // dvd colours empty array
  PImage [] dvdColours = new PImage[5];
  int numImages = 5; 

  int randNum;
  int previousColour;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(1000, 800);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {

    // load images and add them as elements to an array
    dvdColours[0] = loadImage("Blue.png"); // img dimensions = 447 x 200
    dvdColours[1] = loadImage("Orange.png");
    dvdColours[2] = loadImage("Yellow.png");
    dvdColours[3] = loadImage("Purple.png");
    dvdColours[4] = loadImage("Cyan.png");
    background = loadImage("Black background.png");
    Fabroa = loadImage("Fabroa.png");

    // randomize the starting dvd logo colour
    randColour();
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // draw black background
    image(background, 0, 0);

    // draw moving circle
    ellipse(fltCircleX, fltCircleY, 50, 50);

    fltCircleX += circleSpeedX;
    fltCircleY += circleSpeedY;


    // randomly select from a list of dvd colours
    image(dvdColours[randNum], fltDvdX, fltDvdY);

    // circle right X edge detection
    
    if (fltCircleX >= 975) {
      circleSpeedX = -circleSpeedX;
      fltCircleX = 975;
    }

    // circle left X edge detection
    else if (fltCircleX <= 25) {
      circleSpeedX = -circleSpeedX;
      fltCircleX = 25;
    }

    // circle bottom Y edge detection
    if (fltCircleY >= 775) {
      circleSpeedY = -circleSpeedY;
      fltCircleY = 775;
    } 

    // circle top Y edge detection
    else if (fltCircleY <= 0) {
      circleSpeedY = -circleSpeedY;
      fltCircleY = 0;
    }

    // Dvd right X edge detection, reverse X speed if image reaches boundary
    if (keyPressed) {
      if (keyCode == LEFT || key == 'a') {
          fltDvdX += -5;
          if (fltDvdX <= 0) {
            fltDvdX = 0;
          }
      }   
      if (keyCode == RIGHT || key == 'd') {
          fltDvdX += 5;
          if (fltDvdX >= 553) {
            fltDvdX = 553;
          }
      }

      if (keyCode == UP || key == 'w') {
          fltDvdY += -5;
          if (fltDvdY <= 0) {
            fltDvdY = 0;
          }
      }

      if (keyCode == DOWN || key == 's') {
          fltDvdY += 5;
          if (fltDvdY >= 600) {
            fltDvdY = 600;
          }
      }
    }

  
    
  }
  
  // define other methods down here.

  /**
   * Generates a random number depending on the number of images using numImages variable
   * 
   * @param: N/A
   * @return: N/A
   * @author: A. Chan
   */
  public void randColour() {
    randNum = (int) random(numImages);
  }

  /**
   * Checks to see if a number is repeated twice, if a number is repeated twice, generate a new number until condition is false. Prevents repeated colours when logo collides with edge of screen.
   * 
   * @param: N/A
   * @return: returns the new generated number and stores it in the previousColour variable 
   * @author: A. Chan
   */
  public void noDuplicates() {
    while (randNum == previousColour) {
      randColour();
    }
    previousColour = randNum;
  }

  public void mousePressed() {

    if (mousePressed) {

      randColour();
      noDuplicates();
      
    }
    
  }

  public void mouseWheel() {
    
    image(Fabroa, 402, 264);

  }

  public void mouseDragged() {

    ellipse(fltCircleX, fltCircleY, 50, 50);

    fltCircleX += circleSpeedX;
    fltCircleY += circleSpeedY;

  }

  public void mouseMoved() {

    background (255, 255, 255);
  }
}