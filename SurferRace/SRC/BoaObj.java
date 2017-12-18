package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  procedure
//  public BoaObj(MainCanvas parent, String fileName) throws IOException
//  public void visualizza(boolean modo)
//  public void start()
//  public void next()
//  public void scrolla(int dx, int dy)
//  public void insuperamento(int indice)
//  public void disegnalampeggio(Graphics g)


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Calendar.*;
import java.util.Random;
import java.lang.Math.*;


public class BoaObj {
  private MainCanvas parent;
  public  Sprite   mBoa1;
  public  Sprite   mBoa2;
  public  Sprite   mBoa3;
  public  Sprite   mBoa4;
  public  Sprite   mBoa5;
  public  Sprite   mBoa6;
  private int      tipo=0;
  private int      posRandy=0;
  private int      hp;
  private int      wp;
  private int      modulow;
  private int      offy;
  private Random   m_r;
  private int      LocPosery;
  public  boolean  superataboa=false;
  public  int      indiceboasuperata=0;
  private int      posizioYBoa=0;
  private int      offerdisponibile=0;
  private boolean  inlampeggio = false;
  private int      contalampeggio = 0;
  private boolean  attualeonoff = false;


  public static final int[] Seq_Boa_1 = { 0,1,2,3,4,2,1,2,2,1,0,3,4,2,3,2,1,0};
  public static final int[] Seq_Boa_2 = { 5,6,7,6,5,5,7,8,9,7,6,7,8,5,6,6,7,8};

  public BoaObj(MainCanvas parent, String fileName) throws IOException {
    this.parent = parent;
    hp   = parent.h;                         wp   = parent.w;
    modulow = wp*4/10;
    Image image = Image.createImage(fileName);
    mBoa1 = new Sprite(image, 20, 20);       mBoa1.setPosition( 100, 140);
    mBoa1.defineReferencePixel(9, 9);        mBoa1.setFrameSequence(Seq_Boa_1);
    mBoa1.setTransform(Sprite.TRANS_NONE);

    mBoa2 = new Sprite(image, 20, 20);       mBoa2.setPosition( 100, 140);
    mBoa2.defineReferencePixel(9, 9);        mBoa2.setFrameSequence(Seq_Boa_2);
    mBoa2.setTransform(Sprite.TRANS_NONE);

    mBoa3 = new Sprite(image, 20, 20);       mBoa3.setPosition( 100, 140);
    mBoa3.defineReferencePixel(9, 9);        mBoa3.setFrameSequence(Seq_Boa_1);
    mBoa3.setTransform(Sprite.TRANS_NONE);

    mBoa4 = new Sprite(image, 20, 20);       mBoa4.setPosition( 100, 140);
    mBoa4.defineReferencePixel(9, 9);        mBoa4.setFrameSequence(Seq_Boa_2);
    mBoa4.setTransform(Sprite.TRANS_NONE);

    mBoa5 = new Sprite(image, 20, 20);       mBoa5.setPosition( 100, 140);
    mBoa5.defineReferencePixel(9, 9);        mBoa5.setFrameSequence(Seq_Boa_1);
    mBoa5.setTransform(Sprite.TRANS_NONE);

    mBoa6 = new Sprite(image, 20, 20);       mBoa6.setPosition( 100, 140);
    mBoa6.defineReferencePixel(9, 9);        mBoa6.setFrameSequence(Seq_Boa_2);
    mBoa6.setTransform(Sprite.TRANS_NONE);

    visualizza(false);
  }


  public void visualizza(boolean modo){
    mBoa1.setVisible(modo);    mBoa2.setVisible(modo);    mBoa3.setVisible(modo);
    mBoa4.setVisible(modo);    mBoa5.setVisible(modo);    mBoa6.setVisible(modo);
  }





  public void start(){
   visualizza(true);
   // la prima boa sta tra la meta' schermo al massimo ( 1.5 schermi in altezza ) meno 40 per far passare il serfista.
   m_r = new Random();                                offy  = 30;

   posRandy = (m_r.nextInt() % (hp/2));  if (posRandy<0) {posRandy = -posRandy;};           posizioYBoa=offy+posRandy;
// posizioYBoa=offy;
   mBoa1.setRefPixelPosition( modulow*1, posizioYBoa);


   offerdisponibile = ((hp*5/4)-posizioYBoa)-2*offy;
   posRandy = (m_r.nextInt() % offerdisponibile);  if (posRandy<0) {posRandy = -posRandy;};
   posizioYBoa=(posizioYBoa+posRandy)+offy;
// posizioYBoa= (hp*5/4)-offy;
   mBoa2.setRefPixelPosition( modulow*2, posizioYBoa);


   offerdisponibile = posizioYBoa-2*offy;
   posRandy = (m_r.nextInt() % offerdisponibile);  if (posRandy<0) {posRandy = -posRandy;}; posizioYBoa= offy+posRandy;
// posizioYBoa=offy;
   mBoa3.setRefPixelPosition( modulow*3, posizioYBoa);

   offerdisponibile = ((hp*5/4)-posizioYBoa)-2*offy;
   posRandy = (m_r.nextInt() % offerdisponibile);  if (posRandy<0) {posRandy = -posRandy;};
   posizioYBoa=(posizioYBoa+posRandy)+offy;
// posizioYBoa= (hp*5/4)-offy;
   mBoa4.setRefPixelPosition( modulow*4, posizioYBoa);

   offerdisponibile = posizioYBoa-2*offy;
   posRandy = (m_r.nextInt() % offerdisponibile);  if (posRandy<0) {posRandy = -posRandy;}; posizioYBoa= offy+posRandy;
// posizioYBoa=offy;
   mBoa5.setRefPixelPosition( modulow*5, posizioYBoa);

   offerdisponibile = ((hp*5/4)-posizioYBoa)-2*offy;
   posRandy = (m_r.nextInt() % offerdisponibile);  if (posRandy<0) {posRandy = -posRandy;};
   posizioYBoa=(posizioYBoa+posRandy)+offy;
   mBoa6.setRefPixelPosition( modulow*6, posizioYBoa);

  }



  public void next(){
   mBoa1.nextFrame();   mBoa2.nextFrame();   mBoa3.nextFrame();
   mBoa4.nextFrame();   mBoa5.nextFrame();   mBoa6.nextFrame();

   if ((inlampeggio) & (!parent.infaseMenu)) {
    contalampeggio--;
    if ((contalampeggio % 5)==0) { attualeonoff = !attualeonoff;};
    if (contalampeggio<0) { inlampeggio = false; attualeonoff=false; }
    switch (indiceboasuperata) {
     case 1 :  mBoa1.setVisible(attualeonoff); break;
     case 2 :  mBoa2.setVisible(attualeonoff); break;
     case 3 :  mBoa3.setVisible(attualeonoff); break;
     case 4 :  mBoa4.setVisible(attualeonoff); break;
     case 5 :  mBoa5.setVisible(attualeonoff); break;
     case 6 :  mBoa6.setVisible(attualeonoff); break;
     default : break;
    }
   }

  }

  public void scrolla(int dx, int dy){
   mBoa1.move(dx,dy);   mBoa2.move(dx,dy);   mBoa3.move(dx,dy);
   mBoa4.move(dx,dy);   mBoa5.move(dx,dy);   mBoa6.move(dx,dy);
  }

  public void insuperamento(int indice) {
   indiceboasuperata=indice;
   inlampeggio = true;
   contalampeggio = 30;
  }

  public void disegnalampeggio(Graphics g) {
   if (inlampeggio) {
    if (!attualeonoff  ) {
     g.setColor(255,212,18);
     int x1=0; int x2=0; int y1=0; int y2=0;
     switch (indiceboasuperata) {
      case 1 :  x1= mBoa1.getX(); x2= mBoa2.getX(); y1= mBoa1.getY(); y2= mBoa2.getY(); break;
      case 2 :  x1= mBoa2.getX(); x2= mBoa3.getX(); y1= mBoa2.getY(); y2= mBoa3.getY(); break;
      case 3 :  x1= mBoa3.getX(); x2= mBoa4.getX(); y1= mBoa3.getY(); y2= mBoa4.getY(); break;
      case 4 :  x1= mBoa4.getX(); x2= mBoa5.getX(); y1= mBoa4.getY(); y2= mBoa5.getY(); break;
      case 5 :  x1= mBoa5.getX(); x2= mBoa6.getX(); y1= mBoa5.getY(); y2= mBoa6.getY(); break;
      default : break;
     }
     if (contalampeggio>18) {
       if (x1>0) {  g.drawLine(x1+19,y1+14,x2-1,y2+14);  };   }
    }
   }
  };




}

