import processing.core.PApplet;
import processing.core.PImage;

/**
 * User input using mouse and keyboard
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

  int r = 0;
  int g = 0;
  int b = 0;

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
    Fabroa = loadImage("Fabroa.png");

    // randomize the starting dvd logo colour
    randColour();
    noDuplicates();
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // draw background
    background(r, g, b);

    // reset position of circle if mouse is pressed to the coordinates of the mouse
    if (mousePressed) {
      fltCircleX = mouseX;
      fltCircleY = mouseY;
    }

    // draw moving circle
    ellipse(fltCircleX, fltCircleY, 50, 50);

    fltCircleX += circleSpeedX;
    fltCircleY += circleSpeedY;

    // randomly select from an array of dvd colours
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

    // Checks if key is pressed
    if (keyPressed) {

      // if 'a' or left arrow is pressed, move DVD logo to the left
      if (keyCode == LEFT || key == 'a') {
          fltDvdX += -5;
          if (fltDvdX <= 0) {
            fltDvdX = 0;
          }
      }   

      // if 'd' or right arrow is pressed, move DVD logo to the right
      if (keyCode == RIGHT || key == 'd') {
          fltDvdX += 5;
          if (fltDvdX >= 553) {
            fltDvdX = 553;
          }
      }

      // if 'w' or up arrow is pressed, move DVD logo upwards
      if (keyCode == UP || key == 'w') {
          fltDvdY += -5;
          if (fltDvdY <= 0) {
            fltDvdY = 0;
          }
      }

      // if 's' or down arrow is pressed, move DVD logo downwards
      if (keyCode == DOWN || key == 's') {
          fltDvdY += 5;
          if (fltDvdY >= 600) {
            fltDvdY = 600;
          }
      }

      // if 'n' is pressed, change DVD logo colour
      if (key == 'n') {
        randColour();
        noDuplicates();
      }

      // if 'r' is pressed, change background to red
      if (key == 'r') {
        r = 254;
        g = 57;
        b = 57;
      }

      // if 'b' is pressed, change background to blue
      if (key == 'b') {
        r = 133;
        g = 201;
        b = 232;
      }
      
      // if 'g' is pressed, change background to green
      if (key == 'g') {
        r = 0;
        g = 129;
        b = 64;
      }

      // if 'SHIFT' is pressed, reset background to black
      if (keyCode == SHIFT) {
        r = 0;
        g = 0;
        b = 0;
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

  /**
   * Uses mouseWheel user input and draws an image to the screen
   */
  public void mouseWheel() {
    image(Fabroa, mouseX, mouseY);
  }

  /**
   * If mouse is dragged, reset position of circle to the current mouse coordinates 
   */
  public void mouseDragged() {
    fltCircleX = mouseX;
    fltCircleY = mouseY;
  }

}