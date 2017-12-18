package PallaNuoto;

import java.io.IOException;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

public class SfondoObj {

  public  TiledLayer mSfondo;
  private MainCanvas parent;

  private int xpos=0;
  private int ypos=0;

  private int moduloX=11;
  private int moduloY=11;
  private int attualeFoto=1;

  public SfondoObj(MainCanvas parent,String fileName) throws IOException {
    this.parent = parent;
    moduloX = (parent.w / 24)+2;
    moduloY = (parent.h / 24)+2;
    Image image = Image.createImage(fileName);
    mSfondo = new TiledLayer(moduloX, moduloY, image, 24, 24);
    mSfondo.setPosition(0, 0);
    mSfondo.fillCells(0, 0, moduloX, moduloY, 1);
  }


  public void next(){
     xpos = xpos+ 5;  // ypos = ypos+ 2;

    if (xpos> 10) { mSfondo.move( 1, 0); xpos = xpos-10; }
    if (xpos<-10) { mSfondo.move(-1, 0); xpos = xpos+10; }
    if (ypos> 10) { mSfondo.move( 0,-1); ypos = ypos-10; }
    if (ypos<-10) { mSfondo.move( 0, 1); ypos = ypos+10; }
    int offer =mSfondo.getX();
      if (offer<-22) {mSfondo.move(24,0); };   if (offer> 0) {mSfondo.move(-24,0); };
        offer =mSfondo.getY();
      if (offer<-22) {mSfondo.move(0,24); };   if (offer> 0) {mSfondo.move(0,-24); };
  }

  public void scrolla(int dx, int dy){
   mSfondo.move(dx,dy);
  }

}

