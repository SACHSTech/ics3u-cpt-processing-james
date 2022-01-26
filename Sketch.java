import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  PImage Aliens;
  PImage Gameover;
  PImage Shoota;
  PImage bullet;


  boolean createInvader = true; 
  int [] anylivers = new int [15];
  int invaderCoord = 0;
  int [] invArmy = new int[15];
  int invSpeedX = 1;
  float invadePathX = -100;
  float invadePathY = 500;
  boolean firststrike = false;
  int difficulty = 2;


  int rightval = 0;
  int upval = 0;
  int downval = 0;
  int leftval = 0;
  boolean keyup = false;
  boolean keydown = false;
  boolean keyright = false;
  boolean keyleft = false;
  int leftrightforward = 0;
  

  int bulletspeed = 1;

  class Bullet {
    float x, y, w, h;
    float speed, rotation, maxSpeed, minSpeed;
    boolean firing;
  
    Bullet() {
      x = 100;
      y = -100;
      w = 20;
      h = 10;
  
      speed = 0;
      maxSpeed = 15;
      minSpeed = 5;
      firing = false;
    }
    void setStartLocation(float startX, float startY, float startRotation) {
      if (firing == false) {
        x = startX;
        y = startY;
        firing = true;
        speed = 20;
      }
    }
    void update() {
      if (firing == true) {
        if (speed > minSpeed) {
          speed -= 0.3;
        }
      }
    }
  }

  

    


  public void settings() {
    size(800, 800);
    
  }
  
  public void setup() {
    frameRate(20);
    background(0);
    Aliens = loadImage("invader.png");
    Gameover = loadImage("Gameover.png");
    Shoota = loadImage("spaceship.png");
    bullet = loadImage ("bullet.png");
    for (int i = 0; i < invArmy.length; i++) {
      invaderCoord = invaderCoord + 50;
      invArmy[i] = invaderCoord;
    for (int one = 0; one < anylivers.length; one++) {
      anylivers[one] = 1;
    }
    

    }
  }

  
  public void draw() {
    leftrightforward = leftrightforward + 1;

    background(0);
    if (difficulty == 1) {
      for (int i = 0; i < invArmy.length; i++) {
        if (anylivers[i] == 1) {
          invadePathX = invadePathX + invSpeedX;
          double coordshift;
          coordshift = (0.075  * (invadePathX) - 50);
          invadePathY = (float) coordshift;


          image(Aliens, invArmy[i], invadePathY - 50);
          image(Aliens, invArmy[i], invadePathY);
          image(Aliens, invArmy[i], invadePathY - 100);
          

        }
      }
    }
    if (difficulty == 2) {
      for (int i = 0; i < invArmy.length; i++) {
        leftrightforward = leftrightforward + 1;
        if (anylivers[i] == 1) {
          invadePathX = invadePathX + invSpeedX;
          double coordshift;
          coordshift = (0.125  * (invadePathX) - 50);
          invadePathY = (float) coordshift;

          if (leftrightforward < 800) {
            invArmy[i] = invArmy[i] - 1;
          }
          if ((leftrightforward > 1000) && (leftrightforward < 1850)) {
            invArmy[i] = invArmy[i] + 1;
          }
          if (leftrightforward == 2000) {
            leftrightforward = 0;
          }



          image(Aliens, invArmy[i], invadePathY - 50);
          image(Aliens, invArmy[i], invadePathY);
          image(Aliens, invArmy[i], invadePathY - 100);
      }
    }
  }
    
    image(Shoota, 350 + rightval - leftval, 625 - upval + downval);
    
      if (keyup) {
        upval = upval + 8;
        if (upval > 750) {
          upval = upval + 0;
        }
      }
      if (keydown) {
        if (downval > 125) {
          downval = downval + 0;
        }
        downval = downval + 8;
      }
      if (keyright) {
        rightval = rightval + 8;
        if (rightval > 450) {
          rightval = rightval + 0;
        }
      }
      if (keyleft) {
        leftval = leftval + 8;
        if (leftval > 375) {
          leftval = leftval + 0;
        }
      }
      if (keyCode == CONTROL) {
        for (int l = 0; l < 200; l++) {
          bulletspeed = bulletspeed + (1/2);
        }
    }
      






      if (invadePathY > 910) {
        background(220,20,60);
        image(Gameover, 140, 250);
          
        }
      }
      

  

  
    
  public void keyPressed() {
    if (keyCode == UP) {
      keyup = true;
    }
    if (keyCode == DOWN) {
      keydown = true;
    }
    if (keyCode == RIGHT) {
      keyright = true;
    }
    if (keyCode == LEFT) {
      keyleft = true;
    }
}
  public void keyReleased() {
    if (keyCode == UP) {
      keyup = false;
    }
    if (keyCode == DOWN) {
      keydown = false;
    }
    if (keyCode == RIGHT) {
      keyright = false;
    }
    if (keyCode == LEFT) {
      keyleft = false;
    }
  }
}



