import processing.core.PApplet;
import processing.core.PImage;
/**
* @author: Ethan Rodrigues
*/
public class Sketch1 extends PApplet {

  PImage imgBackground;


  public void settings() {
	// screen size
    size(1640,840);
  }

  public void setup() {
    imgBackground = loadImage("Background.png");

  }

  public void draw() {
    int intX = 0;
    int intY = 0;
    // loop for location of squares in section 1
    for(int intRow = 0; intRow < width; intRow+=(imgBackground.width)){
      for(int intColumn = 0; intColumn < height; intColumn+=(imgBackground.height)){
        intX = intRow; 
        intY = intColumn; 

        // drawing for squares in section 1
        image(imgBackground, intX, intY);
      }
    }
  }
}