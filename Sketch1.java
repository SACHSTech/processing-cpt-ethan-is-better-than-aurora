import processing.core.PApplet;
/**
* A code that creates a game where there is rocks falling and the player has to avoid hitting the rocks
* @author: Ethan Rodrigues
*/
public class Sketch1 extends PApplet {
  // create arrays for the falling rocks
  float[] circleY = new float[5];
  float[] circleX = new float[5];
  
  // set speed for falling rocks
  float circleSpeed = 4;

  public void settings() {
    // screen size
    size(1600, 800);
  }

  public void setup() {
    // background colour
    background(0, 161, 8);

    // set locations for the rocks within the height and width of the screen
    for (int i = 0; i < circleX.length; i++){
      circleY[i] = random(height);
      circleX[i] = random(width);
    }
  }

  public void draw() {
    // background colour
    background(0, 161, 8);
       
    // draws circle for rocks if ballhidestatus is off
    for (int i = 0; i < circleY.length; i++){
        fill(156, 104, 50);
        ellipse(circleX[i], circleY[i], 40,40);
        circleY[i] = circleY[i] + circleSpeed;
      
      // makes rocks be redrawn at the top of the screen when it reaches the bottom
      if (circleY[i] >= height){
        circleY[i] = 0;
      }
    }
  }
}