import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  PImage Aliens;
  PImage Gameover;
  PImage Shoota;
  boolean createInvader = true; 
  int invaderCoord = 0;
  boolean moveDown = true;
  int invaderPos = 20;
  int invaderPos2 = 70;
  int [] invArmy = new int[15];
  int [] invArmy2 = new int[15];
  int invSpeedX = 1;
  float invadePathX = -100;
  float invadePathY = 500;

  
  boolean keyup = false;
  boolean keydown = false;
  boolean keyright = false;
  boolean keyleft = false;

  public void settings() {
    size(800, 800);
    
  }
  
  public void setup() {
    frameRate(20);
    background(0);
    Aliens = loadImage("invader.png");
    Gameover = loadImage("Gameover.png");
    Shoota = loadImage("spaceship.png");
    for (int i = 0; i < invArmy.length; i++) {
      invaderCoord = invaderCoord + 50;
      invArmy[i] = invaderCoord;

    }
  }

  
  public void draw() {
    background(0);

    for (int i = 0; i < invArmy.length; i++) {

      int leftrightforward = 0;
      leftrightforward = leftrightforward + 1;
      invadePathX = invadePathX + invSpeedX;
      double coordshift;
      coordshift = (0.2  * (invadePathX) - 50);
      invadePathY = (float) coordshift;

      if (leftrightforward == 0) {
        image(Aliens, invArmy[i], invadePathY - 50);
        image(Aliens, invArmy[i], invadePathY);
        image(Aliens, invArmy[i], invadePathY - 100);
        print(invadePathY);
        delay(8);
        }
      if (invadePathY > 910) {
        background(220,20,60);
        image(Gameover, 140, 250);
          
        }
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

