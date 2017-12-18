package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //


//  Procedure
//  public class BandieraObj
//  public BandieraObj(MainCanvas parent, String fileName) throws IOException 
//  private void setState() {
//  public void next(){
//  public void cambiaH(int newH)


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;


public class BandieraObj {

  private MainCanvas parent;
  public  Sprite   mBandiera;
  private int      mDirezione  =0;
  public static final int[] Seq_Band_0 = { 0  , 1 , 2};
  public static final int[] Seq_Band_1 = { 3  , 4 , 5};
  public static final int[] Seq_Band_2 = { 6  , 7 , 8};
  public static final int[] Seq_Band_3 = { 9  ,10 ,11};
  public static final int[] Seq_Band_4 = { 12 ,13 ,14};
  public static final int[] Seq_Band_5 = { 15 ,16 ,17};
  public static final int[] Seq_Band_6 = { 18 ,19 ,20};
  public static final int[] Seq_Band_7 = { 21 ,22 ,23};
  public static final int[] Seq_Band_8 = { 24 ,25 ,26};

  public BandieraObj(MainCanvas parent, String fileName) throws IOException {
    this.parent = parent;
    Image image = Image.createImage(fileName);
    mBandiera = new Sprite(image, 20, 20);
    mBandiera.setPosition( 5, parent.h-25);
    mBandiera.defineReferencePixel(9, 9);
    mBandiera.setFrameSequence(Seq_Band_0); mBandiera.setTransform(Sprite.TRANS_NONE);
    setState();
  }

  private void setState() {
    switch (parent.direzionevento) {
     case 8 :  mBandiera.setFrameSequence(Seq_Band_0);  mBandiera.setTransform(Sprite.TRANS_NONE);   break;
     case 7 :  mBandiera.setFrameSequence(Seq_Band_1);  mBandiera.setTransform(Sprite.TRANS_NONE);   break;
     case 6 :  mBandiera.setFrameSequence(Seq_Band_2);  mBandiera.setTransform(Sprite.TRANS_NONE);   break;
     case 5 :  mBandiera.setFrameSequence(Seq_Band_3);  mBandiera.setTransform(Sprite.TRANS_NONE);   break;
     case 4 :  mBandiera.setFrameSequence(Seq_Band_4);  mBandiera.setTransform(Sprite.TRANS_NONE);   break;
     case 3 :  mBandiera.setFrameSequence(Seq_Band_5);  mBandiera.setTransform(Sprite.TRANS_NONE);   break;
     case 2 :  mBandiera.setFrameSequence(Seq_Band_6);  mBandiera.setTransform(Sprite.TRANS_NONE);   break;
     case 1 :  mBandiera.setFrameSequence(Seq_Band_7);  mBandiera.setTransform(Sprite.TRANS_NONE);   break;

     case 0 :  mBandiera.setFrameSequence(Seq_Band_8);  mBandiera.setTransform(Sprite.TRANS_NONE);   break;
     case 15 :  mBandiera.setFrameSequence(Seq_Band_7);  mBandiera.setTransform(Sprite.TRANS_MIRROR); break;
     case 14:  mBandiera.setFrameSequence(Seq_Band_6);  mBandiera.setTransform(Sprite.TRANS_MIRROR); break;
     case 13:  mBandiera.setFrameSequence(Seq_Band_5);  mBandiera.setTransform(Sprite.TRANS_MIRROR); break;
     case 12:  mBandiera.setFrameSequence(Seq_Band_4);  mBandiera.setTransform(Sprite.TRANS_MIRROR); break;
     case 11:  mBandiera.setFrameSequence(Seq_Band_3);  mBandiera.setTransform(Sprite.TRANS_MIRROR); break;
     case 10:  mBandiera.setFrameSequence(Seq_Band_2);  mBandiera.setTransform(Sprite.TRANS_MIRROR); break;
     case 9:  mBandiera.setFrameSequence(Seq_Band_1);  mBandiera.setTransform(Sprite.TRANS_MIRROR); break;
    }
    mDirezione = parent.direzionevento;
  }


  public void next(){
   mBandiera.nextFrame();
   if (mDirezione != parent.direzionevento) {setState();};
  }

  public void cambiaH(int newH){
   mBandiera.setPosition( 5,newH-25);
  }

}

