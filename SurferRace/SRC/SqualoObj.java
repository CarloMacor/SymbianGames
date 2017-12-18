package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public SqualoObj(MainCanvas parent,String fileName) throws IOException 
//  private void setState()
//  public boolean lookingyou()
//  private boolean sposta()
//  public void startgame()
//  public void VersoSerfer1()
//  private int distanzaQuadraOldposSerferSqualo()
//  public void imponiDir(int dir)
//  public void next()



import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Random;


public class SqualoObj {
  public  Sprite     mSqualo;
  private MainCanvas parent;
  public  int      Direzione  =0;
  private int      Xpos          = 12000;
  private int      Ypos          = 9000;
  private int      FattoreSqualo = 12;
  public int       XposTogo      = 0;
  public int       YposTogo      = 0;
  private int      numsvolte     = 0;
  private int      contaframe    = 0;
  public  int      contapassi    = 0;
  public  int      LimitFrames   = 200;
  private long     TempoIniShark = System.currentTimeMillis();
  private long     TempoNowShark = System.currentTimeMillis();
  private final int       dimimg = 20;
  private final int       dimimg_2 = 10;
  private int      delta =0;
  private Random   m_r;
  private int      posRand;
  private final int       fattoreVelocita = 2;
  public static final int[] Seq_Squa_0 = { 0 , 1 , 2 , 3 , 2 , 1};
  public static final int[] Seq_Squa_1 = { 4 , 5 , 6 , 7 , 6 , 5};
  public static final int[] Seq_Squa_2 = { 8 , 9 ,10 ,11 ,10 , 9};
  public static final int[] Seq_Squa_3 = { 12,13 ,14 ,15 ,14 ,13};
  public static final int[] Seq_Squa_4 = { 16,17 ,18 ,19 ,18 ,17};
  public static final int[] Seq_Squa_5 = { 20,21 ,22 ,23 ,22 ,21};
  public static final int[] Seq_Squa_6 = { 24,25 ,26 ,27 ,26 ,25};
  public static final int[] Seq_Squa_7 = { 28,29 ,30 ,31 ,30 ,29};
  public static final int[] Seq_Squa_8 = { 32,33 ,34 ,35 ,34 ,33};

  public SqualoObj(MainCanvas parent,String fileName) throws IOException {
    this.parent = parent;
    Image image = Image.createImage(fileName);
    mSqualo = new Sprite(image, 20, 20);
    mSqualo.setPosition( 70, 40);
    mSqualo.defineReferencePixel(10, 10);
    mSqualo.setFrameSequence(Seq_Squa_0);
    mSqualo.setTransform(Sprite.TRANS_NONE);
  }

  private void setState() {
   int nf=  mSqualo.getFrame();
    switch (Direzione) {
     case 0 :  mSqualo.setFrameSequence(Seq_Squa_0);  mSqualo.setTransform(Sprite.TRANS_NONE);   break;
     case 1 :  mSqualo.setFrameSequence(Seq_Squa_1);  mSqualo.setTransform(Sprite.TRANS_NONE);   break;
     case 2 :  mSqualo.setFrameSequence(Seq_Squa_2);  mSqualo.setTransform(Sprite.TRANS_NONE);   break;
     case 3 :  mSqualo.setFrameSequence(Seq_Squa_3);  mSqualo.setTransform(Sprite.TRANS_NONE);   break;
     case 4 :  mSqualo.setFrameSequence(Seq_Squa_4);  mSqualo.setTransform(Sprite.TRANS_NONE);   break;
     case 5 :  mSqualo.setFrameSequence(Seq_Squa_5);  mSqualo.setTransform(Sprite.TRANS_NONE);   break;
     case 6 :  mSqualo.setFrameSequence(Seq_Squa_6);  mSqualo.setTransform(Sprite.TRANS_NONE);   break;
     case 7 :  mSqualo.setFrameSequence(Seq_Squa_7);  mSqualo.setTransform(Sprite.TRANS_NONE);   break;
     case 8 :  mSqualo.setFrameSequence(Seq_Squa_8);  mSqualo.setTransform(Sprite.TRANS_NONE);   break;
     case 9 :  mSqualo.setFrameSequence(Seq_Squa_7);  mSqualo.setTransform(Sprite.TRANS_MIRROR); break;
     case 10:  mSqualo.setFrameSequence(Seq_Squa_6);  mSqualo.setTransform(Sprite.TRANS_MIRROR); break;
     case 11:  mSqualo.setFrameSequence(Seq_Squa_5);  mSqualo.setTransform(Sprite.TRANS_MIRROR); break;
     case 12:  mSqualo.setFrameSequence(Seq_Squa_4);  mSqualo.setTransform(Sprite.TRANS_MIRROR); break;
     case 13:  mSqualo.setFrameSequence(Seq_Squa_3);  mSqualo.setTransform(Sprite.TRANS_MIRROR); break;
     case 14:  mSqualo.setFrameSequence(Seq_Squa_2);  mSqualo.setTransform(Sprite.TRANS_MIRROR); break;
     case 15:  mSqualo.setFrameSequence(Seq_Squa_1);  mSqualo.setTransform(Sprite.TRANS_MIRROR); break;
    }
    mSqualo.setFrame(nf);
  }




 public boolean lookingyou() {
   boolean result = false;
   int dx=0;    int dy=0;
   switch (Direzione) {
    case  0 : dx =  0; dy = 10;  break;
    case  1 : dx =  5; dy =  8;  break;
    case  2 : dx =  7; dy =  7;  break;
    case  3 : dx =  8; dy =  5;  break;
    case  4 : dx = 10; dy =  0;  break;
    case  5 : dx =  8; dy = -5;  break;
    case  6 : dx =  7; dy = -7;  break;
    case  7 : dx =  5; dy = -8;  break;
    case  8 : dx =  0; dy =-10;  break;
    case  9 : dx = -5; dy = -8;  break;
    case 10 : dx = -7; dy = -7;  break;
    case 11 : dx = -8; dy = -5;  break;
    case 12 : dx =-10; dy =  0;  break;
    case 13 : dx = -8; dy =  5;  break;
    case 14 : dx = -7; dy =  7;  break;
    case 15 : dx = -5; dy =  8;  break;
   }
    dx = FattoreSqualo*dx;
    dy = FattoreSqualo*dy;
//    dx = dx*fattoreVelocita;
//    dx = dy*fattoreVelocita;

    Xpos = Xpos+dx;
    Ypos = Ypos-dy;

    if (Xpos < dimimg_2*100) { Xpos = dimimg_2*100;  };
    if (Xpos > (parent.w-dimimg_2)*100) { Xpos =(parent.w-dimimg_2)*100;  };
    if (Ypos < dimimg_2*100) { Ypos = dimimg_2*100;  };
    if (Ypos > (parent.h-dimimg_2)*100) { Ypos =(parent.h-dimimg_2)*100;  };
    mSqualo.setRefPixelPosition( Xpos /100, Ypos /100);
    if (dx!=0) { result = true;};
    if (dy!=0) { result = true;};
    return result;
 }

 private boolean sposta() {
  boolean value = false;
  if (parent.GiocoInCorso != 3) {return false; };
  if (parent.infaseMenu)        {return false; };
  value = lookingyou();
  contaframe++;
// se i passi dovuti sono stati fatti allora riaggiorna ricerca
  if  (contapassi<0) {VersoSerfer1();} // circa arrivato al goto
   else
    if  (contaframe>LimitFrames) {VersoSerfer1();}; // antiblocking
  contapassi--;
  if (!parent.FinitoGiocoShark) {
   TempoNowShark = System.currentTimeMillis();
   parent.TempoTotShark = TempoNowShark - TempoIniShark;
  }
  return value;
 }


 public void startgame(){
   mSqualo.setVisible(true);
   Xpos= 12000;   Ypos= 9000;   mSqualo.setRefPixelPosition( Xpos/100, Ypos/100);
   numsvolte   = 0;
   contaframe  = 0;
   contapassi  = 0;
   LimitFrames = 200;
   VersoSerfer1();
   TempoIniShark = System.currentTimeMillis();
 }




 public void VersoSerfer1() {
  contaframe=0;
  LimitFrames = LimitFrames-20;  // di partenza 400 15
  if (LimitFrames<0) {LimitFrames=0;};
  delta =   LimitFrames / 16;
  if (delta<1) {delta=1;};
  m_r = new Random();  posRand = (m_r.nextInt() % delta);
  XposTogo=parent.serfer1.givemeX()+posRand*100;
  m_r = new Random();  posRand = (m_r.nextInt() % delta);
  YposTogo=parent.serfer1.givemeY()+posRand*100;

// randomizzare un poco l'obbiettivo
  int fascia = 20 -numsvolte;
  if (fascia<0) { fascia=0;};
  int dx    = XposTogo-Xpos;
  int dy    = YposTogo-Ypos;
  int absdx ; int absdy ;
  int dirro=0 ; int locdirro=0;
  if (dx<0) {absdx=-dx;}  else {absdx=dx;};
  if (dy<0) {absdy=-dy;}  else {absdy=dy;};
  int rel;
  if (absdx<1){ if (dy<0) {dirro=0;} else { dirro=8;}; }
   else
  {
   rel=(4*absdy) / absdx; // to understend the angle nearest for new direction
   if (rel<1){ locdirro =4;} else
    if (rel<3){ locdirro =3;} else
     if (rel<6){ locdirro =2;} else
      if (rel<16){ locdirro =1;} else {locdirro =0;};
//  now consider the signed direction
   if (dx<0) {locdirro=(16-locdirro);};

   if (dy>0) {
    locdirro=locdirro+8;     if (locdirro>=16) { locdirro=locdirro-16;};
    locdirro=(16-locdirro);  if (locdirro>=16) { locdirro=locdirro-16;};
   };

   if (locdirro==16) {locdirro=0;};
   dirro= locdirro;
  }

  contapassi = ((absdx*absdx+absdy*absdy) / 1000000)  ;

//    dx = FattoreSqualo*dx*fattoreVelocita;
//    dy = FattoreSqualo*dy*fattoreVelocita;

  Direzione=dirro;
  setState();
 }



  private int distanzaQuadraOldposSerferSqualo() {
   int value=0;
   int x1= XposTogo;   int y1= YposTogo;
   int x2= Xpos;       int y2= Ypos;
   value = (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
   return value;
  }



 public void imponiDir(int dir){  Direzione  = dir;  setState(); }

  public void next(){
   mSqualo.nextFrame();
   if (!parent.infaseMenu) {sposta();}
  }

}

