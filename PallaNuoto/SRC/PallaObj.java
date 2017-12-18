package PallaNuoto;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public  void pallaAlta()
//  public  void pallaBassa()
//  public  void pallaCentro()
//  public  void pallaversoCentro()
//  public  void pallasottoContinue()
//  public  void riposizionapalla()
//  public  void riposizionaAlta()
//  public  void sposta(int dx, int dy)
//  private void spostadirezione()
//  public  void next()
//  public  void rimuovi()                    public void introduci()
//  public  int  dammiY()                     public int dammiX()
//  public  int  dammiY100()                  public int dammiX100()
//  public  void vaiverso(int xp,int yp)
//  public  void riposizionapallaGiocatore(int pallaDi)

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Random;


public class PallaObj {

  public  Sprite     mPalla;
  private MainCanvas parent;
  SquadraObj parentsquadra;

  private int xpos100;
  private int ypos100;
  public  int      direzione=0;
  private int      velocita=500;

  public  int      xtogo;
  public  int      ytogo;
  public  boolean  isgoing=false;
  private boolean  oldalta=false;


  public static final int[] Seq_Palla_Alta  = { 0};
  public static final int[] Seq_Palla_Acqua = { 1,1,2,3,3,4,5,5,4,5,6,6,5,5,4,3,2,1,2,3,3,4,4,5,5,6,6,7,7,5,5,4,4,3,3,7,7,2,1,1,5,7,7,4,4,6,5,5,1,1,3,2};



  public PallaObj(MainCanvas parent,SquadraObj parentsquadra, String fileName) throws IOException {
    this.parent = parent;
    this.parentsquadra = parentsquadra;
    Image image = Image.createImage(fileName);
    mPalla = new Sprite(image, 16, 16);
    xpos100 = 100*100;    ypos100 = 120*100;
    mPalla.defineReferencePixel(8, 12);
    mPalla.setRefPixelPosition(xpos100/100, ypos100/100);
    mPalla.setFrameSequence(Seq_Palla_Acqua);
    mPalla.setTransform(Sprite.TRANS_NONE);
  }


  public void pallaAlta() { mPalla.setFrameSequence(Seq_Palla_Alta);  oldalta=true;  }

  public void pallaBassa(){ mPalla.setFrameSequence(Seq_Palla_Acqua); oldalta=false; }


  public void pallaCentro(){
    xpos100 = (parent.w/2)*100;   ypos100 = parent.gInterface.MidF*100;
    mPalla.setRefPixelPosition(xpos100/100, ypos100/100);
  }

  public void pallaversoCentro(){
    xtogo = (parent.w/2)*100;  ytogo = parent.gInterface.MidF*100;
    isgoing = true;
  }


  public void pallasottoContinue(){
    mPalla.setRefPixelPosition(parent.w/2, parent.gMenu.yriga2-34);
    mPalla.setVisible(true);
    if (oldalta) { pallaBassa();};
  }

  public void visualizzapalla(boolean parboo) {
    mPalla.setVisible(parboo);
  }

  public void riposizionapalla(){
    mPalla.setRefPixelPosition(xpos100/100, ypos100/100);
  }

  public void riposizionaAlta(){
   if (oldalta) { pallaAlta();} else {pallaBassa();};
  }



  public void sposta(int dx, int dy) {
   xpos100=xpos100+dx;   ypos100=ypos100+dy;
   if (xpos100>(parent.w-10)*100) {xpos100=(parent.w-10)*100;};
   if (xpos100<10*100) {xpos100=10*100;};
   if (ypos100>(parent.h-5)*100) {ypos100=(parent.h-5)*100;};
   if (ypos100<5*100)  {ypos100=5*100;};
   mPalla.setRefPixelPosition(xpos100/100, ypos100/100);
  }


  private void  spostadirezione() {
   int locdx=0;  int locdy=0;
   int deltax=0; int deltay=0;  int dister=0;
    direzione= parent.info.dammidirezionetogo(xtogo,ytogo,xpos100,ypos100);
    locdx = parent.info.componenteX(direzione);
    locdy = parent.info.componenteY(direzione);
    locdx=(locdx*velocita)/50;
    deltax = xtogo-xpos100;    deltay = ytogo-ypos100;
    if (deltax>0) { if (locdx>deltax) { locdx=deltax;}; }
             else { if (locdx<deltax) { locdx=deltax;}; }
    if (deltay>0) { if (locdy>deltay) { locdy=deltay;}; }
             else { if (locdy<deltay) { locdy=deltay;}; }

    //  xtogo e xpos100
    locdy=(locdy*velocita)/50;
    sposta(locdx,locdy);
    dister=deltax+deltay; if (dister<0) { dister=-dister;};

    if (isgoing){
     if (dister<300) {
      isgoing=false; parentsquadra.togliaspettapalla(); pallaBassa();
      // se e' in fase gol la palla rimane dove e' altimenti davanti portiere
      if (!parent.gInterface.inRiposizionamentoGol) {
        if (parentsquadra.pallaDi==4) {
         parentsquadra.portiere1.setdir(0);
         xpos100=parentsquadra.portiere1.dammiX100();
         ypos100=parentsquadra.portiere1.dammiY100()-400;
         riposizionapalla();
         if (parentsquadra.infasefallo) {
          parentsquadra.infasefallo=false;
          if (parentsquadra.pallaDi<=4) { parentsquadra.assegnaschemacasuale(1,true);  parentsquadra.assegnaschemacasuale(2,false);} else
                          { parentsquadra.assegnaschemacasuale(2,true);  parentsquadra.assegnaschemacasuale(1,false);};
         }

        }
        if (parentsquadra.pallaDi==8) {
         parentsquadra.portiere2.setdir(4);
         xpos100=parentsquadra.portiere2.dammiX100();
         ypos100=parentsquadra.portiere2.dammiY100()+1400;
         riposizionapalla();
        }
      };

     };
    };
  }


  public void next(){
   if ((isgoing) & (!parent.infaseMenu)) { spostadirezione(); }
   mPalla.nextFrame();
  }

  public void rimuovi(){  parent.mLayerManager.remove(mPalla); }

  public void introduci(){ parent.mLayerManager.insert(mPalla,0); }

  public int dammiY(){   return ypos100/100; }

  public int dammiX(){   return xpos100/100; }

  public int dammiY100(){   return ypos100;  }

  public int dammiX100(){   return xpos100;  }

  public void vaiverso(int xp,int yp){
   xtogo=xp;
   ytogo=yp;
   isgoing=true;
  }

  public void riposizionapallaGiocatore(int pallaDi) {
   int posxg=0;  int posyg=0; int dirro=0; int locoffx=0; int locoffy=0;
   if (pallaDi==4) {return;};
   if (pallaDi==8) {return;};
   if (pallaDi>0) {
    if (isgoing) {
    } else
    {
     posxg=parentsquadra.dammiXgiocatore(pallaDi);
     posyg=parentsquadra.dammiYgiocatore(pallaDi);
     dirro=parentsquadra.dammidirezioneGiocatore(pallaDi);
     switch (dirro) {
      case 0 : locoffx=  0;   locoffy=-100; break;
      case 1 : locoffx=  38;  locoffy=-92;  break;
      case 2 : locoffx=  70;  locoffy=-70;  break;
      case 3 : locoffx=  92;  locoffy=-38;  break;
      case 4 : locoffx= 100;  locoffy=  0;  break;
      case 5 : locoffx=  92;  locoffy= 38; break;
      case 6 : locoffx=  70;  locoffy= 70; break;
      case 7 : locoffx=  38;  locoffy= 92; break;
      case 8 : locoffx=   0;  locoffy=100; break;
      case 9 : locoffx= -38;  locoffy= 92; break;
      case 10: locoffx= -70;  locoffy= 70; break;
      case 11: locoffx= -92;  locoffy= 38; break;
      case 12: locoffx=-100;  locoffy=  0; break;
      case 13: locoffx= -92;  locoffy=-38; break;
      case 14: locoffx= -70;  locoffy=-70; break;
      case 15: locoffx= -38;  locoffy=-92; break;
     }
     posyg=posyg+locoffy*6;      posxg=posxg+locoffx*6;
     xpos100=posxg;              ypos100=posyg;
     riposizionapalla();
    }
   }
  }

 public void correggipallatiro(int pallaDi){
//    se il giocatore e' in tiro palla pos diversa
    int azio = parentsquadra.dammiazionegiocatore(pallaDi);
    int posxg=0;  int posyg=0;
    posxg=parentsquadra.dammiXgiocatore(pallaDi);
    posyg=parentsquadra.dammiYgiocatore(pallaDi);
    if (azio==2) {
      PlayerObj     PX=parentsquadra.dammigiocatoreind(pallaDi);
      int dty=0;     int dtx=0;
      switch (PX.direzione) {
       case 0 :
        switch (PX.fototira) {
         case 0: case 1: case 2:                    dtx= 4; dty=-4; break;
         case 3: case 4: case 5:                    dtx= 4; dty=-7; break;
         case 6: case 7: case 8:                    dtx= 6; dty=-7; break;
         case 9: case 10: case 11:                  dtx= 0; dty=-8; break;
        }              break;
       case 1 :  case 15 :
        switch (PX.fototira) {
         case 0: case 1: case 2:                    dtx= 5; dty=-5; break;
         case 3: case 4: case 5:                    dtx= 4; dty=-8; break;
         case 6: case 7: case 8:                    dtx= 5; dty=-6; break;
         case 9: case 10: case 11:                  dtx= 4; dty=-7; break;
        }              break;
       case 2 :  case 14 :
        switch (PX.fototira) {
         case 0: case 1: case 2:                    dtx= 10; dty=-1; break;
         case 3: case 4: case 5:                    dtx= 10; dty=-6; break;
         case 6: case 7: case 8:                    dtx=  7; dty=-6; break;
         case 9: case 10: case 11:                  dtx= 12; dty=-6; break;
        }              break;
       case 3 :  case 13 :
        switch (PX.fototira) {
         case 0: case 1: case 2:                    dtx= 12; dty= 0; break;
         case 3: case 4: case 5:                    dtx= 10; dty=-4; break;
         case 6: case 7: case 8:                    dtx=  6; dty=-1; break;
         case 9: case 10: case 11:                  dtx= 15; dty=-3; break;
        }              break;
       case 4 :  case 12 :
        switch (PX.fototira) {
         case 0: case 1: case 2:                    dtx= 11; dty= 3; break;
         case 3: case 4: case 5:                    dtx=  9; dty=-3; break;
         case 6: case 7: case 8:                    dtx=  4; dty=-5; break;
         case 9: case 10: case 11:                  dtx= 18; dty=-5; break;
        }              break;
       case 5 :  case 11 :
        switch (PX.fototira) {
         case 0: case 1: case 2:                    dtx=  8; dty= 4; break;
         case 3: case 4: case 5:                    dtx=  6; dty= 0; break;
         case 6: case 7: case 8:                    dtx=  2; dty=-4; break;
         case 9: case 10: case 11:                  dtx= 13; dty= 0; break;
        }              break;
       case 6 :  case 10 :
        switch (PX.fototira) {
         case 0: case 1: case 2:                    dtx=  4; dty= 1; break;
         case 3: case 4: case 5:                    dtx=  3; dty=-1; break;
         case 6: case 7: case 8:                    dtx=  0; dty=-5; break;
         case 9: case 10: case 11:                  dtx=  8; dty= 1; break;
        }              break;
       case 7 :  case 9 :
        switch (PX.fototira) {
         case 0: case 1: case 2:                    dtx=  3; dty= 1; break;
         case 3: case 4: case 5:                    dtx=  2; dty=-2; break;
         case 6: case 7: case 8:                    dtx= -2; dty=-5; break;
         case 9: case 10: case 11:                  dtx=  4; dty= 2; break;
        }              break;
       case 8 :
        switch (PX.fototira) {
         case 0: case 1: case 2:                    dtx= -4; dty= 6; break;
         case 3: case 4: case 5:                    dtx= -6; dty=-4; break;
         case 6: case 7: case 8:                    dtx= -7; dty=-6; break;
         case 9: case 10: case 11:                  dtx= -1; dty= 0; break;
        }              break;

      }

      if (PX.direzione>=9) { dtx=-dtx;};

      xpos100=posxg+dtx*100;
      ypos100=posyg+dty*100;
      riposizionapalla();
    }
 }


}

