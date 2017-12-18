package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public MareObj(MainCanvas parent,String fileName) throws IOException
//  public void next()
//  public void scrolla(int dx, int dy)



import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

public class MareObj {
  public  TiledLayer   mMare;
  private MainCanvas parent;
  private int xpos=0;
  private int ypos=0;
  private int moduloX=11;
  private int moduloY=11;


  public MareObj(MainCanvas parent,String fileName) throws IOException {
    this.parent = parent;
    moduloX = (parent.w / 22)+2;
    moduloY = (parent.h / 22)+2;
    Image image = Image.createImage(fileName);
    mMare = new TiledLayer(moduloX, moduloY, image, 22, 22);
    mMare.setPosition(0, 0);
    mMare.fillCells(0, 0, moduloX, moduloY, 1);
  }


  public void next(){
    switch (parent.direzionevento) {
     case  0 : xpos = xpos+ 0;  ypos = ypos+10; break;
     case  1 : xpos = xpos+ 5;  ypos = ypos+ 8; break;
     case  2 : xpos = xpos+ 7;  ypos = ypos+ 7; break;
     case  3 : xpos = xpos+ 8;  ypos = ypos+ 5; break;
     case  4 : xpos = xpos+10;  ypos = ypos+ 0; break;
     case  5 : xpos = xpos+ 8;  ypos = ypos- 5; break;
     case  6 : xpos = xpos+ 7;  ypos = ypos- 7; break;
     case  7 : xpos = xpos+ 5;  ypos = ypos- 8; break;
     case  8 : xpos = xpos+ 0;  ypos = ypos-10; break;
     case  9 : xpos = xpos- 5;  ypos = ypos- 8; break;
     case 10 : xpos = xpos- 7;  ypos = ypos- 7; break;
     case 11 : xpos = xpos- 8;  ypos = ypos- 5; break;
     case 12 : xpos = xpos-10;  ypos = ypos+ 0; break;
     case 13 : xpos = xpos- 8;  ypos = ypos+ 5; break;
     case 14 : xpos = xpos- 7;  ypos = ypos+ 7; break;
     case 15 : xpos = xpos- 5;  ypos = ypos+ 8; break;
    }
    if (xpos> 10) { mMare.move( 1, 0); xpos = xpos-10; }
    if (xpos<-10) { mMare.move(-1, 0); xpos = xpos+10; }
    if (ypos> 10) { mMare.move( 0,-1); ypos = ypos-10; }
    if (ypos<-10) { mMare.move( 0, 1); ypos = ypos+10; }
    int offer =mMare.getX();
      if (offer<-22) {mMare.move(22,0); };   if (offer> 0) {mMare.move(-22,0); };
        offer =mMare.getY();
      if (offer<-22) {mMare.move(0,22); };   if (offer> 0) {mMare.move(0,-22); };
  }

  public void scrolla(int dx, int dy){
   mMare.move(dx,dy);
  }

}

