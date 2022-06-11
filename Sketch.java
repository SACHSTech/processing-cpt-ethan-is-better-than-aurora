import processing.core.PApplet;
import processing.core.PImage;
/**
* @author: Ethan Rodrigues and Aurora Chen
*/
public class Sketch extends PApplet {

  // import images
  PImage imgKey;
  PImage imgFlashlightCircle;
  PImage imgRobot;
  PImage imgRobotStill;
  PImage imgRobotWalkingSheet;
  PImage [] robotFrames;
  int intWalkingRobotFrames = 8;
  int intWalkingFrameWidth = 192 / 3;
  PImage imgRobotFall;

  PImage imgZombie;
  PImage imgZombieStill;
  PImage imgZombieAttack;
  PImage imgZombieWalkingSheet;
  PImage [] zombieFrames;
  int intWalkingZombieFrames = 3;
  
  // zombie location variables
  float Zombie1X = 150;
  float Zombie1Y = 420;

  float Zombie2X = 520;
  float Zombie2Y = 320;

  float Zombie3X = 620;
  float Zombie3Y = 720;

 float Zombie4X = 920;
  float Zombie4Y = 20;

  float Zombie5X = 1220;
  float Zombie5Y = 420;

  float Zombie1Speed = 2;
 float Zombie2Speed = 3;
 float Zombie3Speed = 3;
 float Zombie4Speed = 3;
  float Zombie5Speed = 3;

  // create arrays for the falling rocks
  float[] circleY = new float[5];
  float[] circleX = new float[5];
  int circleSpeed = 3;

  // player location variables
  float playerX = 50;
  float playerY = 350;
  float playerSpeed = 4;

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

  public void setup() {
   // background colour
   background(0, 161, 8);

   // set images 
   imgKey = loadImage("keyImage.png");
   imgFlashlightCircle = loadImage("FlashlightCircle.png");

   //robot images
   imgRobot = loadImage("RobotSpriteSheet.png");

   imgRobotStill = imgRobot.get(0,0,192,256);
   imgRobotStill.resize(imgRobotStill.width/3, imgRobotStill.height/3);

   imgRobotWalkingSheet = imgRobot.get(0,1024,1536,256);
   imgRobotWalkingSheet.resize(imgRobotWalkingSheet.width/3, imgRobotWalkingSheet.height/3);

   imgRobotFall = imgRobot.get(768,768,192,256);
   imgRobotFall.resize(imgRobotFall.width/3,imgRobotFall.height/3);

   // zombie images
   imgZombie = loadImage("ZombieSpriteSheet.png");

   imgZombieStill = imgZombie.get(0,0,192,256);
   imgZombieStill.resize(imgZombieStill.width/3, imgZombieStill.height/3);

   imgZombieAttack = imgZombie.get(384,0,192,256);
   imgZombieAttack.resize(imgZombieAttack.width/3, imgZombieAttack.height/3);

   imgZombieWalkingSheet = imgZombie.get(0,768,576,256);
   imgZombieWalkingSheet.resize(imgZombieWalkingSheet.width/3, imgZombieWalkingSheet.height/3);

   // robot walking sheet setup
   robotFrames = new PImage[intWalkingRobotFrames];
    for(int frameNum = 0; frameNum < robotFrames.length; frameNum++ ){
      robotFrames[frameNum] = imgRobotWalkingSheet.get(intWalkingFrameWidth*frameNum, 0, intWalkingFrameWidth, imgRobotWalkingSheet.height );
    }

    // zombie walking sheet setup
   zombieFrames = new PImage[intWalkingZombieFrames];
   for(int frameNum = 0; frameNum < zombieFrames.length; frameNum++ ){
     zombieFrames[frameNum] = imgZombieWalkingSheet.get(intWalkingFrameWidth*frameNum, 0, intWalkingFrameWidth, imgZombieWalkingSheet.height );
   }

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

    if(keyPressed && (key == 'w' || key == 'a' || key == 's' || key == 'd')){
      image(robotFrames[(frameCount/3)%intWalkingRobotFrames], playerX, playerY);
    }
    else{
      image(imgRobotStill, playerX, playerY);
    }

    if(keyPressed){
      if(key == 'w' && canMoveUP(playerX, playerY) == true){
        playerY -= playerSpeed;
      }
      else if(key == 'a' && canMoveLEFT(playerX, playerY) == true){
        playerX -= playerSpeed;
      }
      else if(key == 's' && canMoveDOWN(playerX, playerY) == true){
        playerY += playerSpeed;
      }
      else if(key == 'd' && canMoveRIGHT(playerX, playerY) == true){
        playerX += playerSpeed;
      }
    }

    // zombie 1 movement controls
    
    image(zombieFrames[(frameCount/10)%intWalkingZombieFrames], Zombie1X, Zombie1Y);
    Zombie1Y += Zombie1Speed;
   // if statements to prevent zombie from exiting the screen area
   if (Zombie1X < 120 || Zombie1X > 220){
     Zombie1Speed *= -1;
   }
   if (Zombie1Y < 30 || Zombie1Y > 720){
     Zombie1Speed *= -1;
   }

   // zombie 2 movement controls
    
   image(zombieFrames[(frameCount/10)%intWalkingZombieFrames], Zombie2X, Zombie2Y);
   Zombie2X += Zombie2Speed;
  // if statements to prevent zombie from exiting the screen area
  if (Zombie2X > 860 || Zombie2X < 240){
   Zombie2Speed *= -1;
  }
  if (Zombie2Y < 320 || Zombie2Y > 450){
   Zombie2Speed *= -1;
  }

    // zombie 3 movement controls
      
    image(zombieFrames[(frameCount/10)%intWalkingZombieFrames], Zombie3X, Zombie3Y);
    Zombie3X += Zombie3Speed;
    // if statements to prevent zombie from exiting the screen area
    if (Zombie3X > 1040 || Zombie3X < 240){
     Zombie3Speed *= -1;
    }
    if (Zombie3Y < 700 || Zombie3Y > 840){
    Zombie3Speed *= -1;
    }

    // zombie 4 movement controls
      
    image(zombieFrames[(frameCount/10)%intWalkingZombieFrames], Zombie4X, Zombie4Y);
    Zombie4X += Zombie4Speed;
    // if statements to prevent zombie from exiting the screen area
    if (Zombie4X > 1556 || Zombie4X < 240){
      Zombie4Speed *= -1;
    }
    if (Zombie4Y < 10 || Zombie4Y > 140){
      Zombie4Speed *= -1;
    }

    // zombie 5 movement controls
    
    image(zombieFrames[(frameCount/10)%intWalkingZombieFrames], Zombie5X, Zombie5Y);
    Zombie5X += Zombie5Speed;
    // if statements to prevent zombie from exiting the screen area
    if (Zombie5X > 1556 || Zombie5X < 940){
      Zombie5Speed *= -1;
    }
    if (Zombie5Y < 400 || Zombie5Y > 600){
      Zombie5Speed *= -1;
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

    // FOR TESTING, REMOVE LATER
    if (mousePressed){
      playerX = mouseX;
      playerY = mouseY;
    }
    textSize(50);
    fill(0, 408, 612);
    text((playerX + "," + playerY),0,50);
  }

  public boolean canMoveUP(float playerX, float playerY){
    if (playerY <= 20){
      return false;
    }
    else if(playerX > 183 && playerX < 790 && playerY > 405 && playerY < 410){
      return false;
    }
    else{
      return true;
    }
  }

  public boolean canMoveDOWN(float playerX, float playerY){
    if (playerY >= 724){
      return false;
    }
    else if (playerX > 183 && playerX < 866 && playerY > 430 && playerY < 435){
      return false;
    }
    else if (playerX > 225 && playerX < 790 && playerY > 330 && playerY < 335){
      return false;
    }
    else{
      return true;
    }
  }

  public boolean canMoveRIGHT(float playerX, float playerY){
    if (playerX >= 1546){
      return false;
    }
    else if(playerX > 46 && playerX < 220 && playerY < 690 && playerY > 435){
      return false;
    }
    else if (playerX > 46 && playerX < 220 && playerY > 50 && playerY < 400 ){
      return false;
    }
    else if(playerY < 110 && playerX > 156 && playerX < 210){
      return false;
    }
    else{
      return true;
    }
  }

  public boolean canMoveLEFT(float playerX, float playerY){
    if (playerX <= 30){
      return false;
    }
    else if (playerX < 156 && playerX > 30 && playerY < 690 && playerY > height / 2){
      return false;
    }
    else if (playerX < 156 && playerX > 30 && playerY > 50 && playerY < height / 2){
      return false;
    }
    else if (playerX < 230 && playerX > 225 && playerY > 10 && playerY < 335){
      return false;
    }
    else{
      return true;
    }
  }
}