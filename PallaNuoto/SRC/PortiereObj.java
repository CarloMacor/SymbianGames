package PallaNuoto;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public  int  dammiX()
//  public  int  dammiY()
//  public  int  dammiX100()
//  public  int  dammiY100()
//  public  void posiziona100(int locxpos, int locypos)
//  private void posiziona(int locxpos, int locypos)
//  public  void nascondi()
//  public  void mostra()
//  private void sposta(int dx)
//  private void spostarispettopalla()
//  private void direzionapalla()
//  public  void annullaparata()
//  public  void setdir(int locdir)
//  private void visibilita(int modo)
//  public  void next()
//  public  void rimuovi()
//  public  void introduciAt(int ind)



import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;


public class PortiereObj {

  private MainCanvas parent;
  private SquadraObj parentsquadra;
  public  Sprite   mPortiere1;
  public  Sprite   mPortiere2;

  public  int      direzione;
  private int      oldvisibile=0;
  private int      xpos100;
  private int      ypos100;
  private int      distpalla=0;

  private int      dimimg=20;
  private int      dimimg_2=10;
  private int      dpmin=45*45;
  public  boolean  inparata=false;
  public  boolean  tengopalla=false;
  private int      indiceinterno;



  private static final int[] Sequence0  = { 0,0,1,1,2,2,3,3,4,4,3,3,3,2,2,1,2,1,0,0,1,1,2,2,1,2,3,3,3,4,4,3,2,2,1,1,1,2,1};
  private static final int[] SequenceA0 = { 5,5,6,6,6,7,7,8,8,8,9,9,9,8,8,7,7,7,6,6,5,5,5,8,8,8,7,7,8,8,7,7,9,9,9,5,5,5,6,6,7,7,7,8,8,8,7,7,6,6,5,5,9,9,9};

  private int[] Sequence1;
  private int[] Sequence2;
  private int[] Sequence3;
  private int[] Sequence4;

  private int[] SequenceA1;
  private int[] SequenceA2;
  private int[] SequenceA3;
  private int[] SequenceA4;


  public PortiereObj(MainCanvas parent,SquadraObj parentSquadra,Image image,int indo) throws IOException {
    this.parent = parent;
    this.parentsquadra = parentSquadra;
    direzione   = 0;
    int dimK1 =39;
    int dimK2 =55;
    indiceinterno=indo;

    Sequence1  = new int[dimK1];    Sequence2  = new int[dimK1];    Sequence3  = new int[dimK1];    Sequence4  = new int[dimK1];
    SequenceA1 = new int[dimK2];    SequenceA2 = new int[dimK2];    SequenceA3 = new int[dimK2];    SequenceA4 = new int[dimK2];

    for (int i=0; i<dimK1;i++){
     Sequence1[i] = Sequence0[i]+10;
     Sequence2[i] = Sequence0[i]+20;
     Sequence3[i] = Sequence0[i]+30;
     Sequence4[i] = Sequence0[i]+40;
    }

    for (int i=0; i<dimK2;i++){
     SequenceA1[i] = SequenceA0[i]+10;
     SequenceA2[i] = SequenceA0[i]+20;
     SequenceA3[i] = SequenceA0[i]+30;
     SequenceA4[i] = SequenceA0[i]+40;
    }

//    Image image = Image.createImage("/portiere.png");
    mPortiere1 = new Sprite(image, dimimg, dimimg);
    mPortiere1.defineReferencePixel(dimimg_2, dimimg_2);
    mPortiere1.setFrameSequence(Sequence0);
    mPortiere1.setTransform(Sprite.TRANS_NONE);

    mPortiere2 = new Sprite(image, dimimg, dimimg);
    mPortiere2.defineReferencePixel(dimimg_2, dimimg_2);
    mPortiere2.setFrameSequence(Sequence0);
    mPortiere2.setTransform(Sprite.TRANS_MIRROR);
    mPortiere2.setVisible(false);

    parent.mLayerManager.insert(mPortiere1, 0);
    parent.mLayerManager.insert(mPortiere2, 0);
  }


  public int dammiY()    { return ypos100/100;  }

  public int dammiX()    { return xpos100/100;  }

  public int dammiY100() { return ypos100;      }

  public int dammiX100() { return xpos100;      }

  public void posiziona100(int locxpos, int locypos){
   xpos100=locxpos*100;  ypos100=locypos*100;
    mPortiere1.setRefPixelPosition(xpos100/100, ypos100/100);
    mPortiere2.setRefPixelPosition(xpos100/100, ypos100/100);
  }

  private void posiziona(int locxpos, int locypos){
   xpos100=locxpos;  ypos100=locypos;
    mPortiere1.setRefPixelPosition(xpos100/100, ypos100/100);
    mPortiere2.setRefPixelPosition(xpos100/100, ypos100/100);
  }

  public void nascondi(){
    if (mPortiere1.isVisible()) {oldvisibile=0;} else {oldvisibile=1;};
    mPortiere1.setVisible(false);
    mPortiere2.setVisible(false);
  }

  public void mostra(){
   if (oldvisibile==0) {mPortiere1.setVisible(true);} else {mPortiere2.setVisible(true);};
  }

  private void sposta(int dx) {
   int newx=xpos100+dx;
     if (newx>(parent.w/2+10)*100 ) {newx=(parent.w/2+10)*100;};
     if (newx<(parent.w/2-10)*100 ) {newx=(parent.w/2-10)*100;};
   posiziona(newx,ypos100);
  };

  private void spostarispettopalla() {
   int offx;
    offx=(parentsquadra.palla.dammiX100()-xpos100)/100;
    if (offx>10) {sposta(100);}
    if (offx<-10) {sposta(-100);}
  }

  private void direzionapalla() {
   int locdir=0;          int offx; int offy;
    offx=parentsquadra.palla.dammiX100()-xpos100;    if (offx<0) {offx = -offx;};
    offy=parentsquadra.palla.dammiY100()-ypos100;    if (offy<0) {offy = -offy;};
    offx=offx/100;    offy=offy/100;
    if (ypos100<parentsquadra.palla.dammiY100())
     {
      if (xpos100<parentsquadra.palla.dammiX100())
       {
        if ((offy*3)<offx) {locdir = 2;} else
         if (offy<offx*3/2) {locdir = 3;} else locdir = 4;
       } else
       {
        if ((offy*3)<offx) {locdir = 6;} else
         if (offy<offx*3/2) {locdir = 5;} else locdir = 4;
       };
     } else
     {
      if (xpos100<parentsquadra.palla.dammiX100())
       {
        if ((offy*3)<offx) {locdir = 2;} else
         if (offy<offx*3/2) {locdir = 1;} else locdir = 0;
       } else
       {
        if ((offy*3)<offx) {locdir = 6;} else
         if (offy<offx*3/2) {locdir = 7;} else locdir = 0;
       };
     };

    int olddistpalla=distpalla;
    distpalla=offx*offx+offy*offy;
    if ( olddistpalla>=dpmin & distpalla<dpmin) { setdir(locdir); } else
     if ( olddistpalla<=dpmin & distpalla>dpmin) { setdir(locdir); } else
      if (locdir!=direzione) { setdir(locdir); }
  }



   public void annullaparata(){
    inparata=false;
     switch (direzione) {
      case 0 : mPortiere1.setFrameSequence(Sequence0); visibilita(1);  break; //
      case 1 : mPortiere1.setFrameSequence(Sequence1); visibilita(1);  break; //
      case 2 : mPortiere1.setFrameSequence(Sequence2); visibilita(1);  break; //
      case 3 : mPortiere1.setFrameSequence(Sequence3); visibilita(1);  break; //
      case 4 : mPortiere1.setFrameSequence(Sequence4); visibilita(1);  break; //
      case 5 : mPortiere2.setFrameSequence(Sequence3); visibilita(2);  break; //
      case 6 : mPortiere2.setFrameSequence(Sequence2); visibilita(2);  break; //
      case 7 : mPortiere2.setFrameSequence(Sequence1); visibilita(2);  break; //
     }
   }




   public void setdir(int locdir){
    if (!tengopalla) {
     direzione=locdir;
     boolean lontano=false;
     if (indiceinterno==1) {
      if (parentsquadra.pallaDi<=4) {lontano=true;  inparata=false; } else
      { if (distpalla<=dpmin){ inparata=true; lontano=false;  } else {inparata=false; lontano=true; };}
     } else
     {
      if (parentsquadra.pallaDi>4) {lontano=true;  inparata=false; } else
      { if (distpalla<=dpmin){ inparata=true; lontano=false;  } else {inparata=false; lontano=true; };}
     }
      switch (direzione) {
       case 0 : if (lontano) {mPortiere1.setFrameSequence(Sequence0); } else {mPortiere1.setFrameSequence(SequenceA0); }
                  visibilita(1);  break; //
       case 1 : if (lontano) {mPortiere1.setFrameSequence(Sequence1); } else {mPortiere1.setFrameSequence(SequenceA1); }
                  visibilita(1);  break; //
       case 2 : if (lontano) {mPortiere1.setFrameSequence(Sequence2); } else {mPortiere1.setFrameSequence(SequenceA2); }
                  visibilita(1);  break; //
       case 3 : if (lontano) {mPortiere1.setFrameSequence(Sequence3); } else {mPortiere1.setFrameSequence(SequenceA3); }
                  visibilita(1);  break; //
       case 4 : if (lontano) {mPortiere1.setFrameSequence(Sequence4); } else {mPortiere1.setFrameSequence(SequenceA4); }
                  visibilita(1);  break; //
       case 5 : if (lontano) {mPortiere2.setFrameSequence(Sequence3); } else {mPortiere2.setFrameSequence(SequenceA3); }
                  visibilita(2);  break; //
       case 6 : if (lontano) {mPortiere2.setFrameSequence(Sequence2); } else {mPortiere2.setFrameSequence(SequenceA2); }
                  visibilita(2);  break; //
       case 7 : if (lontano) {mPortiere2.setFrameSequence(Sequence1); } else {mPortiere2.setFrameSequence(SequenceA1); }
                  visibilita(2);  break; //
      }
    }
   }




  private void visibilita(int modo) {
   if (modo==1) { mPortiere1.setVisible(true); mPortiere2.setVisible(false); } else
                { mPortiere2.setVisible(true); mPortiere1.setVisible(false); };
  }



  public void next(){

   if ((parentsquadra.pallaDi==4) & (indiceinterno==1)) {} else
    if ((parentsquadra.pallaDi==8) & (indiceinterno==2)) {} else
      direzionapalla();

   spostarispettopalla();
   mPortiere1.nextFrame();
   mPortiere2.nextFrame();
  }


  public void rimuovi(){
   parent.mLayerManager.remove(mPortiere1);
   parent.mLayerManager.remove(mPortiere2);
  }

  public void introduciAt(int ind){
   parent.mLayerManager.insert(mPortiere1,ind);
   parent.mLayerManager.insert(mPortiere2,ind);
  }



}



