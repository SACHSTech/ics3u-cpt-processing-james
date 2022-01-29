import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  //images
  PImage Aliens;
  PImage Gameover;
  PImage Shoota;
  PImage Shoota2;

  //bullet mechanism variables
  int ammo = 1000;
  int truAmmo = 3;
  int aCounter = 2;
  int ammo2 = 1000;
  int truAmmo2 = 3;
  int aCounter2 = 2;
  int hold = 1;
  int hold2 = 1;
  boolean addOne = false;
  boolean addOne2 = false;
  //Rectangle bullet2; 

  float[] meteorX = new float[50];
  float[] meteorY = new float[50];
  float[] alienX = new float[10];
  float[] alienY = new float[10];
  boolean[] aliens = new boolean[10];
  float[] bx = new float[ammo];
  float[] by = new float[ammo];
  float[] bx2 = new float[ammo];
  float[] by2 = new float[ammo];

  //navigation 
  int rightval = 0;
  int upval = 0;
  int downval = 0;
  int leftval = 0;
  int aVal = 0;
  int sVal = 0;
  int dVal = 0;
  int wVal = 0;

  //navigations
  float ypoint = 700 - upval + downval;
  float ypoint2 = 700 - wVal + sVal;
  float xpoint = 600 + rightval - leftval;
  float xpoint2 =  200 + aVal - dVal;

  //navigations again
  boolean keyup = false;
  boolean keydown = false;
  boolean keyright = false;
  boolean keyleft = false;
  boolean wUp = false;
  boolean aLeft = false;
  boolean sDown = false;
  boolean dRight = false;
  boolean xShoot, xShot = false;
  boolean xShoot2, xShot2 = false;
  boolean shiftShoot = false;

  //counter things
  float counter = 0;
  float counter2 = 0;
  int randomMore = 0;

  //gamemode
  boolean gameover = false;

  public void settings() {
    size(800, 800);
    
  }
  
  public void setup() {
    
    frameRate(80);
    background(0);

    Aliens = loadImage("invader.png");
    Gameover = loadImage("Gameover.png");
    Shoota = loadImage("spaceship.png");
    Shoota2 = loadImage("spaceship2.png");

    //creates random values for space collision items
      for (int x = 0; x < meteorX.length; x++) {
        meteorX[x] = random(width);
      }
      for (int x = 0; x < meteorY.length; x++) {
        meteorY[x] = random(0,height-200);
      }
      for (int x = 0; x < alienX.length; x++) {
        alienX[x] = random(100,height-100);
      }
      for (int x = 0; x < alienY.length; x++) {
        alienY[x] = random(200,height-200);
      }
      for (int x = 0; x < aliens.length; x++) {
        aliens[x] = true;
      }
  }
  
  public void draw() {

    background(0);

    //game start
    if (gameover == false){

    //point display
    if(counter >= 0){
      text(counter,100,750);
    }
    if(counter2 >= 0){
      text(counter2,500,750);
    }

    text("ammo = " + truAmmo,300,750);
    text("ammo = " + truAmmo2,700,750);

    //draws space collision items
    for (int i = 0; i < meteorX.length; i++) {
      fill(255);
      ellipse(meteorX[i], meteorY[i], 10, 10);
      meteorX[i] = meteorX[i] + 2;
        if (meteorX[i] > width) {
          meteorX[i] = 0;
          meteorY[i] = random(0,height-200);
        }
    }

    //draws random aliens everywhere for extra point mech
    for (int i = 0; i < alienX.length; i++){

      if(aliens[i] == true){
      imageMode(CENTER);
      image(Aliens,alienX[i],alienY[i]);
      alienX[i] = alienX[i] + 1;
      alienX[i] = alienX[i] - 1;
      }

    }
  
  //necessary naviagtion variables here
  float ypoint = 700 - upval + downval;
  float ypoint2 = 700 - wVal + sVal;
  float xpoint = 600 + rightval - leftval;
  float xpoint2 =  200 + aVal - dVal;
  
    //space ship 2
    imageMode(CENTER);
    image(Shoota, xpoint, ypoint);
    textMode(CENTER);
    text(2,xpoint-5,ypoint-25);
    
      //more navigation stuff
      if (keyup) {
        upval = upval + 4;
        if (upval > 750) {
          upval = upval + 0;
        }
      }
      if (keydown) {
        if (downval > 125) {
          downval = downval + 0;
        }
        downval = downval + 4;
      }
      if (keyright) {
        rightval = rightval + 4;
        if (rightval > 450) {
          rightval = rightval + 0;
        }
      }
      if (keyleft) {
        leftval = leftval + 4;
        if (leftval > 375) {
          leftval = leftval + 0;
        }
      }

    //spaceship 1
    imageMode(CENTER);
    image(Shoota2, xpoint2, ypoint2);
    textMode(CENTER);
    text(1,xpoint2-5,ypoint2-25);  

    //even more navigation stuff
    if (wUp) {
      wVal = wVal + 4;
      if (wVal > 750) {
        wVal = wVal + 0;
      }
    }
    if (sDown) {
      if (sVal > 125) {
        sVal = sVal + 0;
      }
      sVal = sVal + 4;
    }
    if (dRight) {
      dVal = dVal - 4;
      if (dVal > 450) {
        dVal = dVal + 0;
      }
    }
    if (aLeft) {
      aVal = aVal - 4;
      if (aVal > 375) {
        aVal = aVal + 0;
      }
    }

      //800x800 barrier
      if (xpoint > 780){
        rightval = 180;
        leftval = 0;
      }
      if (xpoint < 20){
        rightval = 0;
        leftval = 580;
      }
      if (xpoint2 > 780){
        dVal = 0;
        aVal = 580;
      }
      if (xpoint2 < 20){
        dVal = 180;
        aVal = 0;
      }
      if (ypoint > 700){
        upval = 0;
        downval = 0;
      }
      if (ypoint2 > 700){
        wVal = 0;
        sVal = 0;
      }

    //death at collision - ship 2
    for (int i = 0; i < meteorX.length; i++) {
      if ((xpoint<=meteorX[i]+20) && (xpoint>=meteorX[i]-20)){
        if((ypoint<=meteorY[i]+20) && (ypoint>=meteorY[i]-20)){
        //counter2 = counter2 - 1;
        upval = 0;
        downval = 0;
        }
      }
    }

    //death at collision - ship 1
    for (int i = 0; i < meteorX.length; i++) {
      if ((xpoint2<=meteorX[i]+20) && (xpoint2>=meteorX[i]-20)){
        if((ypoint2<=meteorY[i]+20) && (ypoint2>=meteorY[i]-20)){
        //counter2 = counter2 - 1;
        wVal = 0;
        sVal = 0;
        }
      }
    }

    //draws bullets both ships
    if (xShot){
      fill(255,0,0);
      rectMode(CENTER);
      rect(bx[aCounter],by[aCounter],4,10);
      if (by[aCounter] > -50){
        by[aCounter] = by[aCounter] - 4;
      }
    }
    if (xShot2){
      fill(255,0,0);
      rectMode(CENTER);
      rect(bx2[aCounter2],by2[aCounter2],4,10);
      if (by2[aCounter2] > -50){
        by2[aCounter2] = by2[aCounter2] - 4;
      }
      if ((by2[aCounter2] < 0)){
        xShot2 = false;
      }
    }

  //break alien at collision - ship 2
  for (int i = 0; i < alienX.length; i++){
    if ((bx[aCounter]<=alienX[i]+23) && (bx[aCounter]>=alienX[i]-23)){
      if ((by[aCounter]<=alienY[i]+26) && (by[aCounter]>=alienY[i]-26)){
        addOne = true;
        aliens[i] = false;
        }
      }
    }

      //break alien at collision - ship 2
      for (int i = 0; i < alienX.length; i++){
        if ((bx2[aCounter2]<=alienX[i]+23) && (bx2[aCounter2]>=alienX[i]-23)){
          if ((by2[aCounter2]<=alienY[i]+26) && (by2[aCounter2]>=alienY[i]-26)){
            addOne2 = true;
            aliens[i] = false;
            }
          }
        }

  //points reward for alien destruction
  if (addOne){
    counter = counter + (1/13f);
  }
  if (addOne2){
    counter2 = counter2 + (1/13f);
  }

  addOne = false;
  addOne2 = false;

     //spaceship reset location on point + rewards
     if (ypoint <= -40){
      counter2 = counter2 + 3;
      aCounter2 = aCounter2 + 3;
      upval = 0;
      downval = 0;
      truAmmo2 = truAmmo2 + 3;
    }

    if (ypoint2 <= -40){
      counter = counter + 3;
      aCounter = aCounter + 3;
      wVal = 0;
      sVal = 0;
      truAmmo = truAmmo + 3;
    }

    //aliens wiped out respawn
    if((aliens[0] == false) && (aliens[1] == false) && (aliens[2] == false) && (aliens[3] == false) && (aliens[4] == false) && (aliens[5] == false) && (aliens[6] == false) && (aliens[7] == false) && (aliens[8] == false) && (aliens[9] == false)){
      for (int x = 0; x < aliens.length; x++){
      aliens[x] = true; 
    }
  }
}

  //ends game
  if (counter>=25f || counter2>=25f){
    gameover = true;
  }

  //ends game begins if, the if above is tru
  if (gameover){
    imageMode(CENTER);
    image(Gameover,width/2,height/2);
    if(counter>=25f){
    text("Player 1 Wins", 400-25, 600);
    text("Click 'ENTER,'to play again or 'ESC' to leave", 400-125,620);
    }

    else if(counter2>=25f){
    text("Player 2 Wins", 400-25, 600);
    text("Click 'ENTER,'to play again or 'ESC' to leave", 400-125,620);
    }
  }
}

  //input press thing method yea
  public void keyPressed() {

    float ypoint = 700 - upval + downval;
    float ypoint2 = 700 - wVal + sVal;
    float xpoint = 600 + rightval - leftval;
    float xpoint2 =  200 + aVal - dVal;

    if (keyCode == UP) {
      keyup = true;
    }
    if (key == 'a'){
      aLeft = true;
    }
    if (keyCode == DOWN) {
      keydown = true;
    }
    if (key == 's'){
      sDown = true;
    }
    if (keyCode == RIGHT) {
      keyright = true;
    }
    if (key == 'd'){
      dRight = true;
    }
    if (keyCode == LEFT) {
      keyleft = true;
    }
    if (key == 'w'){
      wUp = true;
    }
    if (key == 'x'){
        if (hold == 1){
          aCounter++;
          hold--;
        }
        if(aCounter>0){
        aCounter--;
        truAmmo--;
        xShoot = true;
        }
      if (xShoot){
        bx[aCounter] = xpoint2;
        by[aCounter] = ypoint2 - 20;
        xShot = true;
      }
    }
    if (keyCode == SHIFT){
      if (hold2 == 1){
        aCounter2++;
        hold2--;
      }
      if(aCounter2>0){
        aCounter2--;
        truAmmo2--;
        xShoot2 = true;
      }
      if (xShoot2){
        bx2[aCounter2] = xpoint;
        by2[aCounter2] = ypoint - 20;
        xShot2 = true;
      }
    }
    if (keyCode == ENTER){
      gameover = false;
      counter = 0;
      counter2 = 0;
    }
}

  //input release method, makes most booleans false thats all
  public void keyReleased() {
      if (keyCode == UP) {
        keyup = false;
      }
      if (key == 'a'){
        aLeft = false;
      }
      if (keyCode == DOWN) {
        keydown = false;
      }
      if (key == 's'){
        sDown = false;
      }
      if (keyCode == RIGHT) {
        keyright = false;
      }
      if (key == 'd'){
        dRight = false;
      }
      if (keyCode == LEFT) {
        keyleft = false;
      }
      if (key == 'w'){
        wUp = false;
      }
      if (key == 'x'){
        xShoot = false;
      }
      if (keyCode == SHIFT){
        xShoot2 = false;
      }
    }
}