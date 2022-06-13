import processing.core.PApplet;
import processing.core.PImage;
/**
* @author: Ethan Rodrigues and Aurora Chen
*/
public class Sketch extends PApplet {

  // import images
  PImage imgKey;
  PImage imgSmallKey;
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
  int zombieHeight = 86;
  int zombieWidth = 64;
  PImage imgBackground;
  PImage imgHeart;
  PImage imgEmptyHeart;
  
  // main screen images
  PImage imgInstructionScreen;
  PImage imgLooseScreen;
  PImage imgMenuScreen;
  PImage imgWinScreen;
  boolean showingMenu = false;

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

  // Menu Strings

  // Win screen Strings
  String strWin = "You win!";
  String strRedo = "Return to menu";

  // player  variables
  float playerX = -22;
  float playerY = 316;
  float playerSpeed = 4;
  int robotHeight = 86;
  int robotWidth = 64;

  // key locations
  int firstKeyX = 120;
  int firstKeyY = 720;

  int secondKeyX = 420;
  int secondKeyY = 720;

  int thirdKeyX = 600;
  int thirdKeyY = 120;

  int fourthKeyX = 1200;
  int fourthKeyY = 120;

  // key variable
  int keysCollected = 0;
  int keyHeight = 100;
  int keyWidth = 150;
  boolean showKey1 = true;
  boolean showKey2 = true;
  boolean showKey3 = true;
  boolean showKey4 = true;


  // life variable
  int playerLives = 5;

  public void settings() {
	// screen size
    size(1640,840);
  }

  public void setup() {
   // background colour
   background(139, 163, 155);

   // set images 
   imgKey = loadImage("keyImage.png");
   imgKey.resize(150,100);
   imgFlashlightCircle = loadImage("flashlightCircle2.png");
   imgBackground = loadImage("Background.png");
  // imgBackground.resize(width, height);
   imgHeart = loadImage("heart.png");
   imgHeart.resize(30,30);
   imgEmptyHeart = loadImage("EmptyHeart.png");
   imgEmptyHeart.resize(30,30);
   imgSmallKey =loadImage("keyImage.png");
   imgSmallKey.resize(75,50);



   // main screen images
   imgInstructionScreen = loadImage("InstructionScreen.png");
   imgLooseScreen = loadImage("LooseScreen.png");
   imgMenuScreen = loadImage("MenuScreen.png");
   imgWinScreen = loadImage("WinScreen.png");


   // flashlight circle image 
   imgFlashlightCircle.resize(imgFlashlightCircle.width * 2, imgFlashlightCircle.height * 2);

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
	  // draw background
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

    // draw gates
    fill(122, 81, 13);
    if(!(keysCollected == 4)){
      rect(1610,620,10,100);
    }
    if(playerX > 20){ 
    rect(20,320,10,100);
    }
    
    // player movement controls 

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
    if (showKey1 == true){
      image(imgKey,firstKeyX,firstKeyY);
    }
    if (showKey2 == true){
      image(imgKey,secondKeyX,secondKeyY); 
    }
    if (showKey3 == true){
      image(imgKey,thirdKeyX,thirdKeyY); 
    }
    if (showKey4 == true){
      image(imgKey,fourthKeyX,fourthKeyY);
    }

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

    // Zombie 1 colission detection
    if ((playerX > Zombie1X && playerX < Zombie1X + zombieWidth && playerY > Zombie1Y && playerY < Zombie1Y + zombieHeight) || 
      (playerX + robotWidth > Zombie1X && playerX  + robotWidth < Zombie1X + zombieWidth && playerY  + robotHeight> Zombie1Y && playerY + robotHeight < Zombie1Y + zombieHeight) || 
      (playerX > Zombie1X + zombieWidth && playerX < Zombie1X + zombieWidth && playerY > Zombie1Y + zombieHeight && playerY < Zombie1Y + zombieHeight) || 
      (playerX + robotWidth > Zombie1X + zombieWidth && playerX  + robotWidth  < Zombie1X + zombieWidth && playerY  + robotHeight> Zombie1Y + zombieHeight && playerY + robotHeight < Zombie1Y + zombieHeight)){

          playerLifeLost();
      // method for player died
    }

    // Zombie 2 colission detection
    if ((playerX > Zombie2X && playerX < Zombie2X + zombieWidth && playerY > Zombie2Y && playerY < Zombie2Y + zombieHeight) || 
      (playerX + robotWidth > Zombie2X && playerX  + robotWidth < Zombie2X + zombieWidth && playerY  + robotHeight> Zombie2Y && playerY + robotHeight < Zombie2Y + zombieHeight) || 
      (playerX > Zombie2X + zombieWidth && playerX < Zombie2X + zombieWidth && playerY > Zombie2Y + zombieHeight && playerY < Zombie2Y + zombieHeight) || 
      (playerX + robotWidth > Zombie2X + zombieWidth && playerX  + robotWidth  < Zombie2X + zombieWidth && playerY  + robotHeight> Zombie2Y + zombieHeight && playerY + robotHeight < Zombie2Y + zombieHeight)){

          playerLifeLost();
      // method for player died
    }
    // Zombie 3 colission detection
    if ((playerX > Zombie3X && playerX < Zombie3X + zombieWidth && playerY > Zombie3Y && playerY < Zombie3Y + zombieHeight) || 
      (playerX + robotWidth > Zombie3X && playerX  + robotWidth < Zombie3X + zombieWidth && playerY  + robotHeight> Zombie3Y && playerY + robotHeight < Zombie3Y + zombieHeight) || 
      (playerX > Zombie3X + zombieWidth && playerX < Zombie3X + zombieWidth && playerY > Zombie3Y + zombieHeight && playerY < Zombie3Y + zombieHeight) || 
      (playerX + robotWidth > Zombie3X + zombieWidth && playerX  + robotWidth  < Zombie3X + zombieWidth && playerY  + robotHeight> Zombie3Y + zombieHeight && playerY + robotHeight < Zombie3Y + zombieHeight)){

          playerLifeLost();
      // method for player died
    }

    // Zombie 4 colission detection
    if ((playerX > Zombie4X && playerX < Zombie4X + zombieWidth && playerY > Zombie4Y && playerY < Zombie4Y + zombieHeight) || 
      (playerX + robotWidth > Zombie4X && playerX  + robotWidth < Zombie4X + zombieWidth && playerY  + robotHeight> Zombie4Y && playerY + robotHeight < Zombie4Y + zombieHeight) || 
      (playerX > Zombie4X + zombieWidth && playerX < Zombie4X + zombieWidth && playerY > Zombie4Y + zombieHeight && playerY < Zombie4Y + zombieHeight) || 
      (playerX + robotWidth > Zombie4X + zombieWidth && playerX  + robotWidth  < Zombie4X + zombieWidth && playerY  + robotHeight> Zombie4Y + zombieHeight && playerY + robotHeight < Zombie4Y + zombieHeight)){

          playerLifeLost();
      // method for player died
    }

    // Zombie 5 colission detection
    if ((playerX > Zombie5X && playerX < Zombie5X + zombieWidth && playerY > Zombie5Y && playerY < Zombie5Y + zombieHeight) || 
      (playerX + robotWidth > Zombie5X && playerX  + robotWidth < Zombie5X + zombieWidth && playerY  + robotHeight> Zombie5Y && playerY + robotHeight < Zombie5Y + zombieHeight) || 
      (playerX > Zombie5X + zombieWidth && playerX < Zombie5X + zombieWidth && playerY > Zombie5Y + zombieHeight && playerY < Zombie5Y + zombieHeight) || 
      (playerX + robotWidth > Zombie5X + zombieWidth && playerX  + robotWidth  < Zombie5X + zombieWidth && playerY  + robotHeight> Zombie5Y + zombieHeight && playerY + robotHeight < Zombie4Y + zombieHeight)){

          playerLifeLost();
      // method for player died
    }

    // key 1 colission detection
    if(showKey1 == true){
      if ((playerX > firstKeyX && playerX < firstKeyX + keyWidth && playerY > firstKeyY && playerY < firstKeyY + keyHeight) || 
        (playerX + robotWidth > firstKeyX && playerX  + robotWidth < firstKeyX + keyWidth && playerY  + robotHeight> firstKeyY && playerY + robotHeight < firstKeyY + keyHeight) || 
        (playerX > firstKeyX + keyWidth && playerX < firstKeyX + keyWidth && playerY > firstKeyY + keyHeight && playerY < firstKeyY + keyHeight) || 
        (playerX + robotWidth > firstKeyX + keyWidth && playerX  + robotWidth  < firstKeyX + keyWidth && playerY  + robotHeight> firstKeyY + keyHeight && playerY + robotHeight < firstKeyY + keyHeight)){
        keysCollected = keysCollected + 1;
        showKey1 = false;
      } 
    }

     // key 2 colission detection
    if(showKey2 == true){
      if ((playerX > secondKeyX && playerX < secondKeyX + keyWidth && playerY > secondKeyY && playerY < secondKeyY + keyHeight) || 
        (playerX + robotWidth > secondKeyX && playerX  + robotWidth < secondKeyX + keyWidth && playerY  + robotHeight> secondKeyY && playerY + robotHeight < secondKeyY + keyHeight)  ){
        keysCollected = keysCollected + 1;
        showKey2 = false;
      } 
    }
    
    // key 3 colission detection
    if(showKey3 == true){
      if ((playerX > thirdKeyX && playerX < thirdKeyX + keyWidth && playerY > thirdKeyY && playerY < thirdKeyY + keyHeight) || 
        (playerX + robotWidth > thirdKeyX && playerX  + robotWidth < thirdKeyX + keyWidth && playerY  + robotHeight> thirdKeyY && playerY + robotHeight < thirdKeyY + keyHeight)  ){
        keysCollected = keysCollected + 1;
        showKey3 = false;
      } 
    }

    // key 4 colission detection
    if(showKey4 == true){
      if ((playerX > fourthKeyX && playerX < fourthKeyX + keyWidth && playerY > fourthKeyY && playerY < fourthKeyY + keyHeight) || 
        (playerX + robotWidth > fourthKeyX && playerX  + robotWidth < fourthKeyX + keyWidth && playerY  + robotHeight> fourthKeyY && playerY + robotHeight < fourthKeyY + keyHeight)  ){
        keysCollected = keysCollected + 1;
        showKey4 = false;
      } 
    }

    // flashlight movement
    image(imgFlashlightCircle, ((playerX - imgFlashlightCircle.width/2 ) + 50), ((playerY - imgFlashlightCircle.height/2) + 50 ));

    // draw lives and keys collected
    if(keysCollected == 1){
      image(imgSmallKey,1555,780);
    }
    else if(keysCollected == 2){
      image(imgSmallKey,1555,780);
      image(imgSmallKey,1480,780);
    }
    else if(keysCollected == 3){
      image(imgSmallKey,1555,780);
      image(imgSmallKey,1480,780);
      image(imgSmallKey,1405,780);
    }
    else if(keysCollected == 4){
      image(imgSmallKey,1555,780);
      image(imgSmallKey,1480,780);
      image(imgSmallKey,1405,780);
      image(imgSmallKey,1330,780);
    }

    if(playerLives == 5){
      image(imgHeart,10,10);
      image(imgHeart,40,10);
      image(imgHeart,70,10);
      image(imgHeart,100,10);
      image(imgHeart,130,10);
    }
    else if(playerLives == 4){
      image(imgHeart,10,10);
      image(imgHeart,40,10);
      image(imgHeart,70,10);
      image(imgHeart,100,10);
      image(imgEmptyHeart,130,10);
    }
    else if(playerLives == 3){
      image(imgHeart,10,10);
      image(imgHeart,40,10);
      image(imgHeart,70,10);
      image(imgEmptyHeart,100,10);
      image(imgEmptyHeart,130,10);
    }
    else if(playerLives == 2){
      image(imgHeart,10,10);
      image(imgHeart,40,10);
      image(imgEmptyHeart,70,10);
      image(imgEmptyHeart,100,10);
      image(imgEmptyHeart,130,10);
    }
    else if(playerLives == 1){
      image(imgHeart,10,10);
      image(imgEmptyHeart,40,10);
      image(imgEmptyHeart,70,10);
      image(imgEmptyHeart,100,10);
      image(imgEmptyHeart,130,10);
    }
    else if(playerLives == 0){
      fill(139, 163, 155);
      rect(0,0,1640,840);
      image(imgLooseScreen,0,0);
      if(mousePressed && playerLives == 0){
       keysCollected = 0;
       playerLives = 5;
       playerX = -22;
       playerY = 316;
       showKey1 = true;
       showKey2 = true;
       showKey3 = true;
       showKey4 = true;

      }
    }



    // win screen 
    if(playerX >= 1560){
      displayWinScreen();
    }

    // menu screen 
    if(playerX == -22 && playerY == 316){
      image(imgMenuScreen,0,0);
      showingMenu = true;
      image(zombieFrames[(frameCount/10)%intWalkingZombieFrames], 720, 360);
      image(robotFrames[(frameCount/3)%intWalkingRobotFrames], 850, 360);

      if(mousePressed){
        if(mouseX > 470 && mouseX < 1170 && mouseY > 600 && mouseY < 710){
          image(imgInstructionScreen,0,0);
        }
      }

    }

  // FOR TESTING, REMOVE LATER
   text((playerX + "," + playerY),0,50);
  
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
    else if(playerX > 270 && playerX < 810 && playerY > 310 && playerY < 315){
      return false;
    }
    else if(playerX > 310 && playerX < 700 && playerY > 80 && playerY < 115){
      return false;
    }
    else if(playerX > 365 && playerX < 715 && playerY > 190 && playerY < 205 ){
      return false;
    }
    else if(playerX > 1020 && playerX < 1500 && playerY > 105 && playerY < 115){
      return false;
    }
    else if(playerX > 1070 && playerX < 1500 && playerY > 195 && playerY < 210){
      return false;
    }
    else if(playerX > 975 && playerX < 1405 && playerY > 290 && playerY < 310){
      return false;
    }
    else if(playerX > 970 && playerX < 1505 && playerY > 390 && playerY < 410){
      return false;
    }
    else if(playerX > 180 && playerX < 915 && playerY > 495 && playerY < 510){
      return false;
    }
    else if(playerX > 270 && playerX < 1000 && playerY > 590 && playerY < 610){
      return false;
    }
    else if(playerX > 205 && playerX < 900 && playerY > 685 && playerY < 700){
      return false;
    }
    else if(playerX > 1005 && playerX < 1560 && playerY > 500 && playerY < 510){
      return false;
    }
    else if(playerX > 1100 && playerX < 1400 && playerY > 560 && playerY < 610 ){
      return false;
    }
    else if(playerX > 1173 && playerX < 1515 && playerY > 690 && playerY < 710){
      return false;
    }
    else if(playerX > -30 && playerX < 20 && playerY > 280 && playerY < 340){
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
    else if(playerX > 265 && playerX < 720 && playerY > 30 && playerY < 40){
      return false;
    }
    else if(playerX > 270 && playerX < 795 && playerY > 230 && playerY < 260){
      return false;
    }
    else if(playerX > 375 && playerX < 670 && playerY > 125 && playerY < 155){
      return false;
    }
    else if(playerX > 970 && playerX < 1500 && playerY > 30 && playerY < 45){
      return false;
    }
    else if( playerX > 1060 && playerX < 1520 && playerY > 130 && playerY < 145){
      return false;
    }
    else if(playerX > 1020 && playerX < 1420 && playerY > 220 && playerY < 240){
      return false;
    }
    else if(playerX > 970 && playerX < 1504 && playerY > 330 && playerY < 350){
      return false;
    }
    else if(playerX > 270 && playerX < 980 && playerY > 525 && playerY < 535){
      return false;
    }
    else if(playerX > 205 && playerX < 900 && playerY > 625 && playerY < 645){
      return false;
    }
    else if(playerX > 970 && playerX < 1560 && playerY > 430 && playerY < 440){
      return false;
    }
    else if(playerX > 1070 && playerX < 1400 && playerY > 530 && playerY < 545){
      return false;
    }
    else if(playerX > 1170 && playerX < 1480 && playerY > 630 && playerY < 645){
      return false; 
    }
    else if(playerX > -30 && playerX < 20 && playerY > 280 && playerY < 340){
      return false;
    }
    else{
      return true;
    }
  }

  public boolean canMoveRIGHT(float playerX, float playerY){
    if (playerX > 46 && playerX < 220 && playerY < 690 && playerY > 435){
      return false;
    }
    //else if(playerX > 1560 &&(playerY))
    else if (playerX > 70 && playerX < 220 && playerY > 50 && playerY < 400 ){
      return false;
    }
    else if(playerY < 110 && playerX > 156 && playerX < 210){
      return false;
    }
    else if(playerX > 265 && playerX < 270 && playerY > 40 && playerY < 200){
      return false;
    }
    else if(playerX > 260 && playerX < 270 && playerY > 240 && playerY < 300){
      return false;
    }
    else if(playerX > 160 && playerX < 170 && playerY > 690 && playerY < 730){
      return false;
    }
    else if(playerX > 765 && playerX < 780 && playerY > 50 && playerY < 240){
      return false;
    }
    else if(playerX > 660 && playerX < 670 && playerY > 105 && playerY < 160){
      return false;
    }
    else if(playerX > 850 && playerX < 890 && playerY > 45 && playerY < 440){
      return false;
    }
    else if(playerX > 960 && playerX < 970 && playerY > 45 && playerY < 300){
      return false;
    }
    else if(playerX > 1465 && playerX < 1480 && playerY > 160 && playerY < 390){
      return false;
    }
    else if(playerX > 964 && playerX < 980 && playerY > 430 && playerY < 690){
      return false;
    }
    else if(playerX > 1060 && playerX < 1085 && playerY > 550 && playerY < 760){
      return false;
    }
    else if(playerX > 1460 && playerX < 1470 && playerY > 470 && playerY < 670){
      return false;
    }
    else if(playerX > 1550 && playerX < 1560 && playerY > 0 && playerY < 575){
      return false;
    }
    else if(playerX > 1550 && playerX < 1560 && playerY > 640 && playerY < 790){
      return false;
    }
    else if(playerX > 1550 && playerX < 1560 && playerY > 590 && playerY < 640 && keysCollected < 4){
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
    else if (playerX < 156 && playerX > 20 && playerY < 690 && playerY > height / 2){
      return false;
    }
    else if (playerX < 156 && playerX > 20 && playerY > 50 && playerY < height / 2){
      return false;
    }
    else if (playerX < 230 && playerX > 225 && playerY > 10 && playerY < 335){
      return false;
    }
    else if (playerX < 830 && playerX > 825 && playerY > 40 && playerY < 300){
      return false;
    }
    else if (playerX < 815 && playerX > 810 && playerY > 325 && playerY < 405){
      return false;
    }
    else if(playerX < 320 && playerX > 315 && playerY > 85 && playerY < 175){
      return false;
    }
    else if(playerX < 725 && playerX > 715 && playerY > 40 && playerY < 195){
      return false;
    }
    else if(playerX < 930 && playerX > 920 && playerY > 40 && playerY < 505){
      return false;
    }
    else if(playerX < 1030 && playerX > 1015 && playerY > 65 && playerY < 270){
      return false;
    }
    else if(playerX < 1515 && playerX > 1500 && playerY > 35 && playerY < 100){
      return false;
    }
    else if(playerX < 1530 && playerX > 1505 && playerY > 130 && playerY < 400){
      return false;
    }
    else if(playerX < 225 && playerX > 215 && playerY > 470 && playerY < 656){
      return false;
    }
    else if(playerX < 1020 && playerX > 1005 && playerY > 460 && playerY < 700 ){
      return false;
    }
    else if(playerX < 1130 && playerX > 1110 && playerY > 580 && playerY < 730){
      return false;
    }
    else if(playerX < 1520 && playerX > 1510 && playerY > 490 && playerY < 700){
      return false;
    }
    else if(playerX < 230 && playerX > 220 && playerY >660 && playerY < 760){
      return false;
    }
    else if(playerX < 1640 && playerX > 1560){
      return false;
    }
    else{
      return true;
    }
  }

  public void displayWinScreen(){
    image(imgWinScreen,0,0);

  }
  
  public void playerLifeLost(){
    playerLives = playerLives - 1;
    playerX = 22;
    playerY = 315;
    if(mousePressed && keysCollected == 4){
      keysCollected = 0;
      playerLives = 5;
      playerX = -22;
      playerY = 316;
      showKey1 = true;
      showKey2 = true;
      showKey3 = true;
      showKey4 = true;
     }
  }
}