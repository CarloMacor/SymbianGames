package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  procedure
//  public BoxObj(MainCanvas parent, String fileName,String fileName2) throws IOException
//  public void start(int modo)
//  public void NuovaPosizione()
//  public void next()


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Random;

public class BoxObj {

  public  Sprite   mBox;
  public  Sprite   mSplash;
  private MainCanvas parent;
  public static final int[] Seq_Box_0    = { 0,1,2,3,2,1,2,2,1,0,3,2,3,2,1,0};
  public static final int[] Seq_Splash_0 = { 0,1,2,3,2,1,2,2,1,0,3,2,3,2,1,0};



  public BoxObj(MainCanvas parent, String fileName,String fileName2) throws IOException {
    this.parent = parent;
    Image image  = Image.createImage(fileName);
    Image imageSplash = Image.createImage(fileName2);
    mBox = new Sprite(image, 20, 20);
    mBox.setPosition( 100, 100);
    mBox.defineReferencePixel(10, 10);
    mBox.setFrameSequence(Seq_Box_0);
    mBox.setTransform(Sprite.TRANS_NONE);
    mSplash = new Sprite(imageSplash, 20, 20);
    mSplash.setPosition( 100, 100);
    mSplash.defineReferencePixel(10, 10);
    mSplash.setTransform(Sprite.TRANS_NONE);
    mSplash.setFrameSequence(Seq_Splash_0);
  }


  public void start(int modo){
   switch (modo) {
    case 1 :
      mBox.setRefPixelPosition(120, 90);
      mSplash.setRefPixelPosition(120, 90);
      parent.TimeBox = parent.TimeBoxini;
      parent.TimeBoxiniziale= parent.TimeBoxini;
      parent.PuntiBox = 0;
     break;
    default : break;
   }
  }


  public void NuovaPosizione(){
    Random m_r = new Random();
    int newx = m_r.nextInt() % (parent.w-20) ;    if (newx<0) {newx = - newx;};
    int newy = (m_r.nextInt() % (parent.h-40));   if (newy<0) {newy = - newy;};
    mBox.setRefPixelPosition(newx+10,newy+10);       mSplash.setRefPixelPosition(newx+10,newy+10);
    mBox.setVisible(true);                        mSplash.setVisible(false);
     parent.TimeBoxiniziale = parent.TimeBoxiniziale*98 / 100;
    if (parent.TimeBoxiniziale>parent.TimeBox) {
      parent.TimeBox = parent.TimeBox+(parent.TimeBoxiniziale-parent.TimeBox)/2; };
  }


  public void next(){
   mBox.nextFrame();
   mSplash.nextFrame();
  }

}

