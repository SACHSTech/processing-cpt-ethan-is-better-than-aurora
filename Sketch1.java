import processing.core.PApplet;
/**
* A code that creates a game where there is snow falling and the player has to avoid hitting the snow
* @author: Ethan Rodrigues
*/
public class Sketch1 extends PApplet {
  // create arrays for the falling snow
  float[] circleY = new float[10];
  float[] circleX = new float[10];
  
  // set speed for falling snow
  float circleSpeed = 4;

  public void settings() {
    // screen size
    size(1600, 800);
  }

  public void setup() {
    // background colour
    background(212, 244, 255);

    // set locations for the snow within the height and width of the screen
    for (int i = 0; i < circleX.length; i++){
      circleY[i] = random(height);
      circleX[i] = random(width);
    }
  }

  public void draw() {
    // background colour
    background(212, 244, 255);
       
    // draws circle for snow if ballhidestatus is off
    for (int i = 0; i < circleY.length; i++){
        fill(255);
        ellipse(circleX[i], circleY[i], 30, 30);
        circleY[i] = circleY[i] + circleSpeed;
      
      // makes snow be redrawn at the top of the screen when it reaches the bottom
      if (circleY[i] >= height){
        circleY[i] = 0;
      }
      
      // if the player clicks on the snow, the it will be hidden
      if (mousePressed){
        if ((mouseX <= circleX[i] + 20) && (mouseX >= circleX[i] - 20)){
          if((mouseY <= circleY[i] + 20) && (mouseY >= circleY[i] - 20)){
          }
        }
      }
    }
  }
}