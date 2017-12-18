package PallaNuoto;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //


// procedure
//  public  void posiziona100(int locxpos, int locypos)
//  public  void posiziona(int locxpos, int locypos)
//  public  void nascondi()       // public  void mostra()
//  public  int  dammiY()         // public  int  dammiX()
//  public  int  dammiX100()      // public  int  dammiY100()
//  public  void sposta(int dx100,int dy100)
//  public  void movimento(int dx,int dy)
//  private void spostadirezione()
//  private int  dammidistanzatogo()
//  public  void versoTogo()
//  public  void disegnatogo(Graphics g)
//  private boolean TestFaccioGol(int sq)
//  public  void next()
//  public  void rimuovi()
//  public  void introduciAt(int ind)
//  public  void ruota()
//  public  void setdir(int locdir)
//  public  void setdirazione(int locdir,int locazione)
//  private void visibilita(int modo)
//  public  int  distdaPalla()
//  public  void settaVelocita(int locvel)
//  public  void stoptogo();
//  public  void assegnaTogo(int locx, int locy)
//  private void xyincampo()
//  public  void allontanada(int ax1, int ay1)


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Random;


public class PlayerObj {

  private int testTira=0;

  private MainCanvas parent;
  private SquadraObj parentsquadra;
  public  Sprite    mPlayer1;
  public  Sprite    mPlayer2;
  public  int       direzione=0;
  public  int       azione=0;
  public  boolean   isgoing=false;
  private int       cambiotrafoto=0;
  private int       finecorsa=0;
  public  boolean   aspettapalla=false;
  public  int       fototira=0;
  public  int       fotoaffoga=0;
  private int       dxp=0;
  public  boolean   sbrogliato=false;
  public  int       nfallato=0;


  public  int test=0;


  private int      xtogo;
  private int      ytogo;
  private int      velocita=100;
  public  int      oldframe=0;
  private int      cambiatoda=10;


  private final int ValcambiotraFoto=5;


  public final int  bici=0;
  public final int  nuota=1;
  public final int  tira=2;
  public final int  affoga=3;

  private int   dimimg=20;
  private int   dimimg_2=10;
  private int   oldvisibile=0;
  private int   xpos100;
  private int   ypos100;


  public PlayerObj(MainCanvas parent,SquadraObj parentsquadra,Image image,int sq) throws IOException {
    this.parent = parent;
    this.parentsquadra = parentsquadra;
    mPlayer1 = new Sprite(image, dimimg, dimimg);
    mPlayer1.defineReferencePixel(dimimg_2, 15);
    mPlayer1.setFrameSequence(parentsquadra.SequenceSbici[0]);
    mPlayer1.setTransform(Sprite.TRANS_NONE);
    mPlayer2 = new Sprite(image, dimimg, dimimg);
    mPlayer2.defineReferencePixel(dimimg_2, 15);
    mPlayer2.setFrameSequence(parentsquadra.SequenceSbici[0]);
    mPlayer2.setTransform(Sprite.TRANS_MIRROR);
    mPlayer2.setVisible(false);
    parent.mLayerManager.insert(mPlayer1, 0);
    parent.mLayerManager.insert(mPlayer2, 0);
  }


  public void posiziona100(int locxpos, int locypos){
   xpos100=locxpos*100;  ypos100=locypos*100;
   xyincampo();
    mPlayer1.setRefPixelPosition(xpos100/100, ypos100/100);
    mPlayer2.setRefPixelPosition(xpos100/100, ypos100/100);
  }

  public void posiziona(int locxpos, int locypos){
   xpos100=locxpos;  ypos100=locypos;
   xyincampo();
    mPlayer1.setRefPixelPosition(xpos100/100, ypos100/100);
    mPlayer2.setRefPixelPosition(xpos100/100, ypos100/100);
  }


  public void nascondi(){
    if (mPlayer1.isVisible()) {oldvisibile=0;} else {oldvisibile=1;};
    mPlayer1.setVisible(false);
    mPlayer2.setVisible(false);
  }

  public void mostra(){
   if (oldvisibile==0) {mPlayer1.setVisible(true);} else {mPlayer2.setVisible(true);};
  }

  public int dammiY(){
   return ypos100/100;
  }

  public int dammiX(){
   return xpos100/100;
  }

  public int dammiX100(){
   return xpos100;
  }

  public int dammiY100(){
   return ypos100;
  }

  private void xyincampo(){
   if (xpos100<1500) { xpos100=1500;};
   if (xpos100>(parent.w-15)*100) { xpos100=(parent.w-15)*100;};
   if (ypos100<(parent.gInterface.offF1*100+4)) { ypos100=parent.gInterface.offF1*100+400;};
   if (ypos100>parent.gInterface.offF2*100) { ypos100=parent.gInterface.offF2*100;};
  }


  public void sposta(int dx100,int dy100) {
   xpos100=xpos100+dx100;   ypos100=ypos100+dy100;
   xyincampo();
   posiziona(xpos100 , ypos100);
  };


  public void movimento(int dx,int dy) {
   boolean mover = false;  int locdir;
   locdir=direzione;
   if (cambiotrafoto>2) {
   }
    else
   {
    if (dx>0) {
     if (locdir==4) { } else {if ((locdir>4) & (locdir<12)) { locdir--; } else { locdir++; } }
     mover=true;
    }
    if (dx<0) {
     if (locdir==12) { } else {if ((locdir>4) & (locdir<12)) { locdir++; } else { locdir--; } }
     mover=true;
    }
    if (dy>0) {
     if (locdir==8) { } else {if ((locdir>0) & (locdir<8)) { locdir++; } else { locdir--; } }
     mover=true;
    }
    if (dy<0) {
     if (locdir==0) { } else {if ((locdir>0) & (locdir<8)) { locdir--; } else { locdir++; } }
     mover=true;
    }
    if (locdir<0)  { locdir=16+locdir;};
    if (locdir>15) { locdir=locdir-16;};

    if (mover) { setdirazione(locdir,nuota); }
     else { if (finecorsa>0) { finecorsa--; if (finecorsa==0) {  setdirazione(direzione,bici); }; }
     };
   }
  }


  public void  spostadirezione() {
   int locdx=0;  int locdy=0;
   if (aspettapalla) {
     setdirazione(direzione,bici);
   } else
   {
    if (azione==nuota) {
     locdx = parent.info.componenteX(direzione);
     locdy = parent.info.componenteY(direzione);
     locdx=(locdx*velocita)/35;     locdy=(locdy*velocita)/35;
     sposta(locdx,locdy);
    }
   }
  }




  private int dammidistanzatogo(){
   int dx,dy,value;
    dx=xtogo-xpos100;   dy=ytogo-ypos100;
    value=dx*dx+dy*dy;
   return  value;
  }


  public void versoTogo() {
   if ((azione!=tira) & (azione!=affoga)) {
     int dd = dammidistanzatogo();
     if (dd<(300*300)) {
      xtogo=xpos100; ytogo=ypos100;
      isgoing=false;
     }
     if ((xtogo==xpos100) & (ytogo==ypos100)) {
      if (azione!=bici) {setdirazione(direzione,bici);
      }
     } else
     {
      int locdirezione=parent.info.dammidirezionetogo(xtogo,ytogo,xpos100,ypos100);
      setdirazione(locdirezione,nuota);
      spostadirezione();
     }
   }
  }


  public void disegnatogo(Graphics g) {
   g.drawLine(xpos100/100,ypos100/100,xtogo/100,ytogo/100 ); //
  }

  private boolean TestFaccioGol(int sq){
   boolean res=false;
   Random m_r = new Random();
   int Rv =0;
   switch(sq) {
    case 2 :
         dxp=parentsquadra.XGol-parentsquadra.portiere1.dammiX100();
         if ((dxp>1000) | (dxp<-1000)) {
          Rv=((m_r.nextInt() % 100));           if (Rv<0){Rv=-Rv;};
           if (Rv<parent.info.parametri[parent.pl2][parent.info.Ptiro]) {  // capacita tiro  di 1
             if (parentsquadra.dimmisemiodifende()) { Rv=((m_r.nextInt() % 80));} else  { Rv=((m_r.nextInt() % 105));};
             if (Rv<0){Rv=-Rv;};
             if (Rv>parent.info.parametri[parent.pl1][parent.info.Pportiere]) { res=true; }
           }
         };
     break;
    case 1 :
         dxp=parentsquadra.XGol-parentsquadra.portiere2.dammiX100();
         if ((dxp>1000) | (dxp<-1000)) {
          Rv=((m_r.nextInt() % 100));           if (Rv<0){Rv=-Rv;};
           if (Rv<parent.info.parametri[parent.pl1][parent.info.Ptiro]) {  // capacita tiro  di 2
             if (parentsquadra.dimmisemioattacca()) { Rv=((m_r.nextInt() % 125));} else { Rv=((m_r.nextInt() % 95));};
             if (Rv<0){Rv=-Rv;};
             if (Rv>parent.info.parametri[parent.pl2][parent.info.Pportiere]) { res=true; }
           }
         };
     break;
   }

//   return true;
   return res;
  }


  public void next(){
   cambiatoda++;
   if (cambiotrafoto>0) {cambiotrafoto--;};

     mPlayer1.nextFrame();
     mPlayer2.nextFrame();

  if (parent.gInterface.finepartita) { return;}


   if (azione==affoga) { fotoaffoga++;
     if (fotoaffoga>=parentsquadra.dimKaffoga) {
      parentsquadra.mustpassa=parentsquadra.infallo;
      parentsquadra.infallo=0;
      setdirazione(direzione,bici);
      fotoaffoga=0; nfallato=0;
     }
   };


   if (azione==tira) {
     fototira++;
     if (fototira>=parentsquadra.dimKtiro) {
      switch (parentsquadra.intirogol) {
       case 0 : parentsquadra.iniziapassaggio();  break;
       case 1 :   // e' la squadra 2= avversaria che tira
         if (!TestFaccioGol(2)) {
          parentsquadra.YGol=parentsquadra.YGol-300; parentsquadra.parteazionedaparata(1); }
         else                          { parent.gInterface.FattoGol(2); };
// non sul palo.         if XGol
         if (parentsquadra.XGol<(parent.w/2-19)*100) {parentsquadra.XGol=(parent.w/2-19)*100;};
         if (parentsquadra.XGol>(parent.w/2+19)*100) {parentsquadra.XGol=(parent.w/2+19)*100;};
         parentsquadra.palla.vaiverso(parentsquadra.XGol,parentsquadra.YGol); parentsquadra.pallaDi=4;
        break;
       case 2 :
         if (!TestFaccioGol(1)) {
           parentsquadra.YGol=parentsquadra.YGol+300; parentsquadra.parteazionedaparata(2); }
           else                        { parent.gInterface.FattoGol(1); };
         if (parentsquadra.XGol<(parent.w/2-19)*100) {parentsquadra.XGol=(parent.w/2-19)*100;};
         if (parentsquadra.XGol>(parent.w/2+19)*100) {parentsquadra.XGol=(parent.w/2+19)*100;};
         parentsquadra.palla.vaiverso(parentsquadra.XGol,parentsquadra.YGol); parentsquadra.pallaDi=8;
        break;
      }
      setdirazione(direzione,bici); fototira=0; parentsquadra.intirogol=0;
     };
   };
  }



  public void rimuovi(){
   parent.mLayerManager.remove(mPlayer1);
   parent.mLayerManager.remove(mPlayer2);
  }

  public void introduciAt(int ind){
   parent.mLayerManager.insert(mPlayer1,ind);
   parent.mLayerManager.insert(mPlayer2,ind);
  }

   public void setdir(int locdir){
     setdirazione(locdir,azione);
   }


   public void setdirazione(int locdir,int locazione){
    boolean cambio=false;
    if (direzione!=locdir) {cambio=true;}
    if (azione!=locazione) {cambio=true;}
    oldframe = 0;
    if ( (direzione!=locdir) & (azione==locazione))  {
     oldframe = mPlayer1.getFrame();
     if (azione==nuota) {
       if (cambiatoda<3) { return;};  ///////////////////////
      if ((direzione==0)  & (locdir==15)) { oldframe=oldframe-8; };
      if ((direzione==15) & (locdir==0))  { oldframe=oldframe-8; };
      if ((direzione==8)  & (locdir==9))  { oldframe=oldframe-8; };
      if ((direzione==9)  & (locdir==8))  { oldframe=oldframe-8; };
      if (oldframe<0) { oldframe=16+oldframe; };
      if (oldframe>15) { oldframe=0; };
     }
    }


     direzione=locdir;

    switch(azione) {
     case tira   :  if (fototira  >=parentsquadra.dimKtiro)   { azione=locazione;  };   break;
     case affoga :  if (fotoaffoga>=parentsquadra.dimKaffoga) { azione=locazione;  };   break;
     default :         azione=locazione; break;
    }


     if (azione!=nuota) {isgoing=false;} else  {isgoing=true;};
     if (azione==tira)  {if (oldframe>=parentsquadra.dimKtiro) {oldframe=0;};  };
     if (azione==affoga)  {oldframe=0;};  


    if (cambio) {
     cambiatoda=0;
     switch (direzione) {
      case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8:
       switch (azione) {
        case bici  : mPlayer1.setFrameSequence(parentsquadra.SequenceSbici[direzione]);
                     break;
        case nuota : mPlayer1.setFrameSequence(parentsquadra.SequenceSnuoto[direzione]);
                     cambiotrafoto=ValcambiotraFoto; finecorsa=ValcambiotraFoto;  break;
        case tira  : mPlayer1.setFrameSequence(parentsquadra.SequenceStiro[direzione]); parentsquadra.palla.pallaAlta();   break;
        case affoga: mPlayer1.setFrameSequence(parentsquadra.SequenceSaffoga[direzione]);
                     break;
        default :  mPlayer1.setFrameSequence(parentsquadra.SequenceSbici[direzione]);   break;
       }
        try {  mPlayer1.setFrame(oldframe);   }
           catch ( IndexOutOfBoundsException e )            { }
         visibilita(1);
        break; //
      case 9: case 10: case 11: case 12: case 13: case 14: case 15:
       switch (azione) {
        case bici  : mPlayer2.setFrameSequence(parentsquadra.SequenceSbici[16-direzione]);  break;
        case nuota : mPlayer2.setFrameSequence(parentsquadra.SequenceSnuoto[16-direzione]);
                     cambiotrafoto=ValcambiotraFoto; finecorsa=ValcambiotraFoto;  break;
        case tira  : mPlayer2.setFrameSequence(parentsquadra.SequenceStiro[16-direzione]); parentsquadra.palla.pallaAlta(); break;
        case affoga: mPlayer2.setFrameSequence(parentsquadra.SequenceSaffoga[16-direzione]);
                     break;
        default :  mPlayer2.setFrameSequence(parentsquadra.SequenceSbici[16-direzione]); break;
       }
        try {  mPlayer2.setFrame(oldframe);   }
           catch ( IndexOutOfBoundsException e )
                 {         }
         visibilita(2);
        break; //
     }
    }

    if (azione!=tira)   { fototira=0;};
    if (azione!=affoga) { fotoaffoga=0;};


   }



  private void visibilita(int modo) {
   if (modo==1) { mPlayer1.setVisible(true); mPlayer2.setVisible(false); } else
                { mPlayer2.setVisible(true); mPlayer1.setVisible(false); };
  }


  public int distdaPalla(){
   int valuex;   int valuey;   int value;
    valuex=(xpos100-parentsquadra.palla.dammiX100());
    valuey=(ypos100-parentsquadra.palla.dammiY100());
    value = valuex*valuex+valuey*valuey;
   return value;
  }


  public void settaVelocita(int locvel){
   velocita=locvel;
  }

  public void assegnaTogo(int locx, int locy) {
   xtogo=locx;   ytogo=locy;   isgoing=true;
  }


  public  void stoptogo() {
   xtogo=xpos100;   ytogo=ypos100;   isgoing=false;
  }


  public void allontanada(int ax1, int ay1){
   boolean spostato=false;
   int dx =xpos100-ax1;
   int dy =ypos100-ay1;
   int delta=1200;
   if ((dx>=0) & (dx<delta)) { xpos100=ax1+delta; spostato=true; };
   if ((dy>=0) & (dy<delta)) { ypos100=ay1+delta; spostato=true; };
   if ((dx<0) & (dx>-delta)) { xpos100=ax1-delta; spostato=true; };
   if ((dy<0) & (dy>-delta)) { ypos100=ay1-delta; spostato=true; };
   if (spostato) {posiziona(xpos100,ypos100);};
  }



}



