import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  PImage Aliens;
  PImage Gameover;
  PImage Shoota;
  PImage bullet;
  PImage Aliens2;


  boolean createInvader = true; 
  int invaderCoord = 0;
  float [] specArmy = new float [5];
  int [] invArmy = new int[7];
  int invSpeedX = 1;
  float invadePathX = -100;
  float invadePathY = 500;
  boolean firststrike = false;
  int difficulty = 2;
  float randenemy = random(1, 3);
  float randspawnx[] = new float [5];
  float randspawny[] = new float [5];
  float enemypos = random(300, 700);
  float enemypos2 = random(1, 2);
  int posconvert;

  


  int rightval = 0;
  int upval = 0;
  int downval = 0;
  int leftval = 0;
  boolean keyup = false;
  boolean keydown = false;
  boolean keyright = false;
  boolean keyleft = false;
  int leftrightforward = 0;
  int highscore = 0;
  

  


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
    Aliens2 = loadImage("invader2.png");
    for (int i = 0; i < invArmy.length; i++) {
      invaderCoord = invaderCoord + 100;
      invArmy[i] = invaderCoord;
    }
    
    for ( int j = 0; j < specArmy.length; j++) {
      specArmy[j] = randenemy;
      randenemy = random(1, 4);
    }
    for ( int k = 0; k < specArmy.length; k++) {
      randspawnx[k] = enemypos;
      enemypos = random(300, 700);
    }
    for ( int l = 0; l < specArmy.length; l++) {
      if (enemypos2 > 1.5) {
        posconvert = 0;
      }
      if (enemypos2 <= 1.5) {
        posconvert = 500;
      }
      randspawny[l] = posconvert;
      enemypos = random(1, 2);
    }
  
  }


  
  public void draw() {
    leftrightforward = leftrightforward + 1;
    highscore = highscore + 1;

    background(0);
    if (difficulty == 1) {
      for (int i = 0; i < invArmy.length; i++) {
        invadePathX = invadePathX + invSpeedX;
        double coordshift;
        coordshift = (0.075  * (invadePathX) - 50);
        invadePathY = (float) coordshift;


        image(Aliens, invArmy[i], invadePathY - 50);
        image(Aliens, invArmy[i], invadePathY);
      
      
        }
      }
    
    
    if (difficulty == 2) {
      for (int i = 0; i < invArmy.length; i++) {
        leftrightforward = leftrightforward + 1;
        invadePathX = invadePathX + invSpeedX;
        double coordshift;
        coordshift = (0.5  * (invadePathX) - 50);
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
        image(Aliens, invArmy[i], invadePathY - 100);

        for ( int j = 0; j < specArmy.length; j++) {
          if (specArmy[j] == 1) {
            image(Aliens2, randspawnx[j], randspawny[j]);
            

          }

        
        
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



