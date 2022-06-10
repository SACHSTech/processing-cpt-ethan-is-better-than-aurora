import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // import images
  PImage imgKey;

  // create arrays for the falling rocks
  float[] circleY = new float[5];
  float[] circleX = new float[5];
  int circleSpeed = 3;

  // player location variables
  float playerX = 50;
  float playerY = 350;

  // key locations
  int firstKeyX = 270;
  int firstKeyY = 770;

  int secondKeyX = 470;
  int secondKeyY = 770;

  int thirdKeyX = 570;
  int thirdKeyY = 270;

  int fourthKeyX = 1350;
  int fourthKeyY = 170;

  // monster locations
  
  // life variable
  int playerlives = 3;

  public void settings() {
	// screen size
    size(1640,840);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
   // background colour
   background(0, 161, 8);

   // set images 
   imgKey = loadImage("keyImage.png");

   // set locations for the rocks within the height and width of the screen
   for (int i = 0; i < circleX.length; i++){
     circleY[i] = random(height);
     circleX[i] = random(width);
    }  
  }

  public void draw() {
	  // background colour
    background(0, 161, 8);

    // draw maze outlines
    noStroke();
    fill(40, 66, 36);
    rect(20,20,1600,10); //top line
    rect(20,20,10,300); // left side
    rect(20,420,10,400);
    rect(20,810,1600,10); // bottom line
    rect(1610,20,10,600); // right side
    rect(1610,720,10,100);

    // draw maze lines
    rect(120, 120,10,600);
    rect(220, 20, 10, 400);
    rect(220, 520, 10, 300);
    rect(220,420,600,10);
    rect(220,520,700,10);
    rect(320,120,400,10);
    rect(420,220,300,10);
    rect(320,320,500,10);
    rect(420,220,300,10);
    rect(220,520,700,10);
    rect(320,620,700,10);
    rect(220,720,700,10);
    rect(320,120,10,100);
    rect(820,120,10,210);
    rect(920,120,10,410);
    rect(1020,120,10,200);
    rect(1020, 520,10,200);
    rect(1120, 620,10,200);
    rect(1020, 120, 500, 10);
    rect(720, 120, 10, 110);
    rect(1120, 220, 400, 10);
    rect(1020, 320, 400, 10);
    rect(1520, 220, 10, 200);
    rect(1020, 420, 510, 10);
    rect(1020, 520, 600, 10);
    rect(1120, 620,300,10);
    rect(1220, 720,300,10);
    rect(1520, 520,10,210);

    // player movement controls - ADD METHOD TO DETERMINE IF IT CAN MOVE OR NOT
    ellipse(playerX, playerY, 80,80);
    if (canMove(playerX, playerY) == true){
      if (keyPressed) {
        if (key == 'w') {
          playerY -= 2;
        } 
        else if (key == 'a') {
          playerX -= 2;
        } 
        else if(key == 's'){
          playerY += 2;
        }
        else if(key == 'd'){
          playerX += 2;
        }
      }
    }

    // draw keys
    imgKey.resize(100,100); 
    image(imgKey,120,720);
    image(imgKey,420,720); 
    image(imgKey,600,120); 
    image(imgKey,1200,120);

    // draws circle for rocks
    stroke(0);
    for (int i = 0; i < circleY.length; i++){
        fill(156, 104, 50);
        ellipse(circleX[i], circleY[i], 40,40);
        circleY[i] = circleY[i] + circleSpeed;
      
      // makes rocks be redrawn at the top of the screen 
      if (circleY[i] >= height + 50 ){
        circleY[i] = 0 - random(0,1000);
        circleX[i] = random(0, width);
      }
    }
  }

  public boolean canMove(float playerX, float playerY){
    if (playerX == 220 && playerY == 200){
      return false;
    }
    else{
      return true;
    }
  }
}