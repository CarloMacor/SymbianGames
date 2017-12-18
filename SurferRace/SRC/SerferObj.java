package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public SerferObj(MainCanvas parent, String fileName,boolean isplayer) throws IOException 
//  private void setState()
//  private void visibilita(int modo)
//  private void setStateCambio()
//  private int dimmiComponenteVento(int correct)
//  private boolean sposta()
//  public void imponiDir(int dir){  Direzione  = dir;  setState();
//  public void scrolla(int dx1, int dy1)
//  public void next()
//  public void Controllo(int tasto)
//  public void start(int modo)
//  public int givemeX()
//  public int givemeY()



import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;


public class SerferObj {
  private MainCanvas parent;
  public  Sprite   mSerfer;
  public  Sprite   mSerfer2;
  public  int      Direzione    = 0;
  public  int      Fase         = 0;
  private boolean  inCambio     = false;
  private int      fotocambio   = 0;
  public  int      Xpos         = 2000;
  public  int      Ypos         = 9600;
  public  int      FattoreVento = 10;
  private final int      dimimg   = 26;
  private final int      dimimg_2 = 13;
  private boolean  player1      = false;
  private static final int C_Cambio=6;
  public  int      ultimaboasuperata = 0;
  private int      prePosX      = 0;
  private boolean  todosupera = false;
  public  int      contafiocchi =0;
  private int      goD = 0;
  private int      locSerfX = 0;
  private int      locSerfY = 0;

  private boolean InizioLampeggioTurn = false;
  private int     contaLampeggioTurn  = 0;
  private int     numlampeggiTurn     = 0;
  public  boolean LampeggioTurn       = false;
  public int     Xtogo =0;
  public int     Ytogo =0;
  public int      Xboatoget =0;
  public int      Yboatoget =0;

  public  boolean arrivatotraguardo = false;
  public  boolean primoarrivato     = false;
  private int     oldcamioframe     = 0;
  private int dx =0;
  private int dy =0;


  private static final int[] Sequence0 = { 0 ,1 ,2 ,1 ,0 , 3, 0, 1, 2, 3 };
  private static final int[] Sequence1 = { 4 ,5 ,6 ,5 ,4 , 7, 4, 5, 6, 7 };
  private static final int[] Sequence2 = { 8 ,9 ,10,9 ,8 ,11, 8, 9,10,11 };
  private static final int[] Sequence3 = { 12,13,14,13,12,15,12,13,14,15 };
  private static final int[] Sequence4 = { 16,17,18,17,16,19,16,17,18,19 };
  private static final int[] Sequence5 = { 20,21,22,21,20,23,20,21,22,23 };
  private static final int[] Sequence6 = { 24,25,26,25,24,27,24,25,26,27 };
  private static final int[] Sequence7 = { 28,29,30,29,28,31,28,29,30,31 };

  private static final int[] Sequence8  = { 32,33,34,33,32,35,32,33,34,35 };
  private static final int[] Sequence9  = { 36,37,38,37,36,39,36,37,38,39 };
  private static final int[] Sequence10 = { 40,41,42,41,40,43,40,41,42,43 };
  private static final int[] Sequence11 = { 44,45,46,45,44,47,44,45,46,47 };
  private static final int[] Sequence12 = { 48,49,50,49,48,51,48,49,50,51 };
  private static final int[] Sequence13 = { 52,53,54,53,52,55,52,53,54,55 };
  private static final int[] Sequence14 = { 56,57,58,57,56,59,56,57,58,59 };
  private static final int[] Sequence15 = { 60,61,62,61,60,63,60,61,62,63 };

// se si cambia la lunghezza dei fotogrammi delle seguenti sequenze .. allora cambiare anche
// la variabile  C_Cambio=6;
  private static final int[] Sequence_00 = { 64,64,65,65,66,66 };
  private static final int[] Sequence_10 = { 67,67,68,68,69,69 };
  private static final int[] Sequence_11 = { 69,69,68,68,67,67 };
  private static final int[] Sequence_20 = { 70,70,71,71,72,72 };
  private static final int[] Sequence_21 = { 72,72,71,71,70,70 };
  private static final int[] Sequence_30 = { 73,73,74,74,75,75 };
  private static final int[] Sequence_31 = { 75,75,74,74,73,73 };
  private static final int[] Sequence_40 = { 76,76,77,77,78,78 };
  private static final int[] Sequence_41 = { 78,78,77,77,76,76 };
  private static final int[] Sequence_50 = { 79,79,80,80,81,81 };
  private static final int[] Sequence_51 = { 81,81,80,80,79,79 };
  private static final int[] Sequence_60 = { 82,82,83,83,84,84 };
  private static final int[] Sequence_61 = { 84,84,83,83,82,82 };
  private static final int[] Sequence_70 = { 85,85,86,86,87,87 };
  private static final int[] Sequence_71 = { 87,87,86,86,85,85 };
//  private static final int[] Sequence_80 = { 88,88,89,89,90,90 };
//  private static final int[] Sequence_81 = { 90,90,89,89,88,88 };
  private static final int[] Sequence_80 = { 88,88,89,89,90,90 };
  private static final int[] Sequence_81 = { 89,88,88 };


  public SerferObj(MainCanvas parent, String fileName,boolean isplayer) throws IOException {
    this.parent = parent;
    Image image = Image.createImage(fileName);
    player1=isplayer;
    mSerfer = new Sprite(image, dimimg, dimimg);
    mSerfer.defineReferencePixel(dimimg_2, dimimg_2);
    mSerfer.setRefPixelPosition( Xpos/100, Ypos/100);
    mSerfer.setFrameSequence(Sequence0);
    mSerfer.setTransform(Sprite.TRANS_NONE);

    mSerfer2 = new Sprite(image, dimimg, dimimg);
    mSerfer2.defineReferencePixel(dimimg_2, dimimg_2);
    mSerfer2.setRefPixelPosition( Xpos/100, Ypos/100);
    mSerfer2.setFrameSequence(Sequence0);
    mSerfer2.setTransform(Sprite.TRANS_MIRROR);
    setState();
  }


  private void setState() {
    if (Fase==0) {
     switch (Direzione) {
      case 0 :  mSerfer.setFrameSequence(Sequence0);   visibilita(1);  break;
      case 1 :  mSerfer.setFrameSequence(Sequence1);   visibilita(1);  break;
      case 2 :  mSerfer.setFrameSequence(Sequence2);   visibilita(1);  break;
      case 3 :  mSerfer.setFrameSequence(Sequence3);   visibilita(1);  break;
      case 4 :  mSerfer.setFrameSequence(Sequence4);   visibilita(1);  break;
      case 5 :  mSerfer.setFrameSequence(Sequence5);   visibilita(1);  break;
      case 6 :  mSerfer.setFrameSequence(Sequence6);   visibilita(1);  break;
      case 7 :  mSerfer.setFrameSequence(Sequence7);   visibilita(1);  break;
      case 8 :  mSerfer2.setFrameSequence(Sequence8);  visibilita(2);  break;
      case 9 :  mSerfer2.setFrameSequence(Sequence15); visibilita(2);  break;
      case 10:  mSerfer2.setFrameSequence(Sequence14); visibilita(2);  break;
      case 11:  mSerfer2.setFrameSequence(Sequence13); visibilita(2);  break;
      case 12:  mSerfer2.setFrameSequence(Sequence12); visibilita(2);  break;
      case 13:  mSerfer2.setFrameSequence(Sequence11); visibilita(2);  break;
      case 14:  mSerfer2.setFrameSequence(Sequence10); visibilita(2);  break;
      case 15:  mSerfer2.setFrameSequence(Sequence9);  visibilita(2);  break;
      default: break;
     }
    }
    if (Fase==1) {
     switch (Direzione) {
      case 0 :  mSerfer2.setFrameSequence(Sequence0);  visibilita(2);  break;
      case 1 :  mSerfer.setFrameSequence(Sequence9);   visibilita(1);  break;
      case 2 :  mSerfer.setFrameSequence(Sequence10);  visibilita(1);  break;
      case 3 :  mSerfer.setFrameSequence(Sequence11);  visibilita(1);  break;
      case 4 :  mSerfer.setFrameSequence(Sequence12);  visibilita(1);  break;
      case 5 :  mSerfer.setFrameSequence(Sequence13);  visibilita(1);  break;
      case 6 :  mSerfer.setFrameSequence(Sequence14);  visibilita(1);  break;
      case 7 :  mSerfer.setFrameSequence(Sequence15);  visibilita(1);  break;
      case 8 :  mSerfer.setFrameSequence(Sequence0);   visibilita(1);  break;
      case 9 :  mSerfer2.setFrameSequence(Sequence7);  visibilita(2);  break;
      case 10:  mSerfer2.setFrameSequence(Sequence6);  visibilita(2);  break;
      case 11:  mSerfer2.setFrameSequence(Sequence5);  visibilita(2);  break;
      case 12:  mSerfer2.setFrameSequence(Sequence4);  visibilita(2);  break;
      case 13:  mSerfer2.setFrameSequence(Sequence3);  visibilita(2);  break;
      case 14:  mSerfer2.setFrameSequence(Sequence2);  visibilita(2);  break;
      case 15:  mSerfer2.setFrameSequence(Sequence1);  visibilita(2);  break;
      default: break;
     }
    }
    mSerfer.setFrame(0);
  }


  private void visibilita(int modo) {
   if (modo==1) { mSerfer.setVisible(true); mSerfer2.setVisible(false); } else
                { mSerfer2.setVisible(true); mSerfer.setVisible(false); };
  }

  private void setStateCambio() {
    if (Fase==0) {
     switch (Direzione) {
      case 0 :  mSerfer.setFrameSequence(Sequence_00); visibilita(1);   break;
      case 1 :  mSerfer.setFrameSequence(Sequence_10); visibilita(1);   break;
      case 2 :  mSerfer.setFrameSequence(Sequence_20); visibilita(1);   break;
      case 3 :  mSerfer.setFrameSequence(Sequence_30); visibilita(1);   break;
      case 4 :  mSerfer.setFrameSequence(Sequence_40); visibilita(1);   break;
      case 5 :  mSerfer.setFrameSequence(Sequence_50); visibilita(1);   break;
      case 6 :  mSerfer.setFrameSequence(Sequence_60); visibilita(1);   break;
      case 7 :  mSerfer.setFrameSequence(Sequence_70); visibilita(1);   break;
      case 8 :  mSerfer.setFrameSequence(Sequence_80); visibilita(1);   break;
      case 9 :  mSerfer2.setFrameSequence(Sequence_71); visibilita(2);   break;
      case 10:  mSerfer2.setFrameSequence(Sequence_61); visibilita(2);   break;
      case 11:  mSerfer2.setFrameSequence(Sequence_51); visibilita(2);   break;
      case 12:  mSerfer2.setFrameSequence(Sequence_41); visibilita(2);   break;
      case 13:  mSerfer2.setFrameSequence(Sequence_31); visibilita(2);   break;
      case 14:  mSerfer2.setFrameSequence(Sequence_21); visibilita(2);   break;
      case 15:  mSerfer2.setFrameSequence(Sequence_11); visibilita(2);   break;
      default: break;
     }
    }
    if (Fase==1) {
     switch (Direzione) {
      case 0 :  mSerfer2.setFrameSequence(Sequence_00); visibilita(2);   break;
      case 1 :  mSerfer.setFrameSequence(Sequence_11);  visibilita(1);   break;
      case 2 :  mSerfer.setFrameSequence(Sequence_21);  visibilita(1);   break;
      case 3 :  mSerfer.setFrameSequence(Sequence_31);  visibilita(1);   break;
      case 4 :  mSerfer.setFrameSequence(Sequence_41);  visibilita(1);   break;
      case 5 :  mSerfer.setFrameSequence(Sequence_51);  visibilita(1);   break;
      case 6 :  mSerfer.setFrameSequence(Sequence_61);  visibilita(1);   break;
      case 7 :  mSerfer.setFrameSequence(Sequence_71);  visibilita(1);   break;
      case 8 :  mSerfer.setFrameSequence(Sequence_81);  visibilita(1);   break;
      case 9 :  mSerfer2.setFrameSequence(Sequence_70); visibilita(2);   break;
      case 10:  mSerfer2.setFrameSequence(Sequence_60); visibilita(2);   break;
      case 11:  mSerfer2.setFrameSequence(Sequence_50); visibilita(2);   break;
      case 12:  mSerfer2.setFrameSequence(Sequence_40); visibilita(2);   break;
      case 13:  mSerfer2.setFrameSequence(Sequence_30); visibilita(2);   break;
      case 14:  mSerfer2.setFrameSequence(Sequence_20); visibilita(2);   break;
      case 15:  mSerfer2.setFrameSequence(Sequence_10); visibilita(2);   break;
      default: break;
     }
    }
    mSerfer.setFrame(0);    inCambio   = true;    fotocambio = 0;
  }


  private int dimmiComponenteVento(int correct){
   int offdir =0;
   int value =0;
   int locfase = Fase;
   if (correct==1) { if (Fase==1) {locfase=0;} else {locfase=1;}; }

   offdir = parent.direzionevento-Direzione;
    if (offdir<-8) { offdir=offdir+16;};
    if (offdir> 8) { offdir=offdir-16;};

   switch (parent.modalita){
        // se Easy  corre in modo costante nella direzione serf a prescindere dal vento
    case 1 : return 8;
        // se medium influenzato dalla posione serfista
    case 2 :
       if (offdir>=0) { if (locfase==1) { return 7;} };
       if (offdir<=0) { if (locfase==0) { return 7;} };
     break;
        // se Hard influenzato dalla direzione vento e dalla posizione del serfista
    case 3 : break;
   }

   if (locfase==1) {
     switch (offdir) {
      case 0  : value = 4;   break;
      case 1  : value =10;   break;
      case 2  : value = 9;   break;
      case 3  : value = 7;   break;
      case 4  : value = 6;   break;
      case 5  : value = 5;   break;
      case 6  : value = 4;   break;
      case 7  : value = 2;   break;
      case 8  : value = 1;   break;
      default : value = 0;   break;
     }
   }
   if (locfase==0) {
     switch (offdir) {
      case  0 :  value = 4;   break;
      case -1 :  value =10;   break;
      case -2 :  value = 9;   break;
      case -3 :  value = 7;   break;
      case -4 :  value = 6;   break;
      case -5 :  value = 5;   break;
      case -6 :  value = 4;   break;
      case -7 :  value = 2;   break;
      case -8 :  value = 1;   break;
      default :  value = 0; break;
     }
   }
   return value;
  }


  private boolean sposta() {
   boolean result = false;
    // se in fase menu il serfista rimane fermo
   if (parent.infaseMenu) { return true;};
   dx=0;
   dy=0;
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
    FattoreVento = dimmiComponenteVento(0);
    if (FattoreVento==0)
     {
     if (!InizioLampeggioTurn) {
      InizioLampeggioTurn=true;
      contaLampeggioTurn=5;
      numlampeggiTurn = 3;
     }
      if (numlampeggiTurn>0 ) {
       { FattoreVento = dimmiComponenteVento(1);
         FattoreVento = FattoreVento *numlampeggiTurn/3;
       };
      }

      if (numlampeggiTurn==0 ) {   if (!player1) { Controllo(2); }   }

      if (contaLampeggioTurn>=0) {LampeggioTurn=true;} else  {LampeggioTurn=false;};
      if (numlampeggiTurn>0)   { contaLampeggioTurn--;} else {
        if (contaLampeggioTurn>0){ contaLampeggioTurn--;}; };
      if (contaLampeggioTurn<-5) {contaLampeggioTurn=9;  numlampeggiTurn--;};
    } else
    {
     InizioLampeggioTurn=false;
     LampeggioTurn=false;
     }



    if (player1) {  FattoreVento = FattoreVento*2;} else {  FattoreVento = FattoreVento*7/4;}
    dx = FattoreVento*dx;    dy = FattoreVento*dy;

    prePosX = mSerfer.getX()+dimimg_2;

    Xpos = Xpos+dx;
    Ypos = Ypos-dy;


    if (player1) {
      if (Xpos <  dimimg_2*100) { Xpos = dimimg_2*100;  };  if (Xpos > (parent.w-dimimg_2)*100) { Xpos =(parent.w-dimimg_2)*100;  };
      if (Ypos <  dimimg_2*100) { Ypos = dimimg_2*100;  };  if (Ypos > (parent.h-dimimg_2)*100) { Ypos =(parent.h-dimimg_2)*100;  };
    }  else
    {
      if (Xpos < (dimimg_2*100-parent.offScreenX*100 )) { Xpos=dimimg_2*100-parent.offScreenX*100;  };
      if (Xpos > ((parent.w*3-dimimg_2)*100-parent.offScreenX*100 )) { Xpos=(parent.w*3-dimimg_2)*100-parent.offScreenX*100;  };
      if (Ypos < (dimimg_2*100-parent.offScreenY*100 )) { Ypos=dimimg_2*100-parent.offScreenY*100;  };
      if (Ypos > ((parent.h*5/4-dimimg_2)*100-parent.offScreenY*100 )) { Ypos=(parent.h*5/4-dimimg_2)*100-parent.offScreenY*100;  };
    }


     mSerfer.setRefPixelPosition( (Xpos /100), (Ypos /100));
     mSerfer2.setRefPixelPosition( (Xpos /100), (Ypos /100));


    if (parent.GiocoInCorso==2) {
// ho superato una boa ?
      locSerfX = mSerfer.getX()+dimimg_2;
      locSerfY = mSerfer.getY()+dimimg_2;
      switch (ultimaboasuperata) {
       case 0  : Xboatoget =parent.boa.mBoa1.getX(); Yboatoget = parent.boa.mBoa1.getY(); break;
       case 1  : Xboatoget =parent.boa.mBoa2.getX(); Yboatoget = parent.boa.mBoa2.getY(); break;
       case 2  : Xboatoget =parent.boa.mBoa3.getX(); Yboatoget = parent.boa.mBoa3.getY(); break;
       case 3  : Xboatoget =parent.boa.mBoa4.getX(); Yboatoget = parent.boa.mBoa4.getY(); break;
       case 4  : Xboatoget =parent.boa.mBoa5.getX(); Yboatoget = parent.boa.mBoa5.getY(); break;
       case 5  : Xboatoget =parent.boa.mBoa6.getX(); Yboatoget = parent.boa.mBoa6.getY(); break;
       case 6  :              // la punta del serfer e' a 20 o 26 ?
        if  (mSerfer.getX()+20> (parent.Xarrivo-parent.offScreenX) )
           { arrivatotraguardo=true;
           }
        break;
       default : break;
      }

     Xboatoget = Xboatoget+9;
     Yboatoget = Yboatoget+12;


     if (player1) {
      if (arrivatotraguardo) {
       if (!parent.serfer2.arrivatotraguardo)  { primoarrivato=true;};
          parent.FinitoGiocoRace=true;
          switch (parent.modalita) {
           case 1 :
            if (parent.gInterface.recordG2E>(parent.deltaTimeRace/100))
             { parent.gInterface.recordG2E=parent.deltaTimeRace/100; parent.gInterface.saveRecord();
               parent.isRecord=true;};
            break;
           case 2 :
            if (parent.gInterface.recordG2M>(parent.deltaTimeRace/100))
             { parent.gInterface.recordG2M=parent.deltaTimeRace/100; parent.gInterface.saveRecord();
               parent.isRecord=true;};
            break;
           case 3 :
            if (parent.gInterface.recordG2H>(parent.deltaTimeRace/100))
             { parent.gInterface.recordG2H=parent.deltaTimeRace/100; parent.gInterface.saveRecord();
               parent.isRecord=true;};
            break;
          }
      }
     };
// IA serfer2
     if (!player1) {
      Xtogo =Xboatoget;
      switch (ultimaboasuperata) {
       case 0  : case 2 : case 4 :  Ytogo = Yboatoget-14; break;
       case 1  : case 3 : case 5 :  Ytogo = Yboatoget+7; break;
       case 6  : Xtogo = 1000; Ytogo = mSerfer.getY(); break;
      }
     int dirxloc = Xtogo-(mSerfer.getX()+dimimg_2);
     int diryloc = Ytogo-(mSerfer.getY()+dimimg_2);
// moltiplico per 16 in modo da vedere le fascie di direzione
     if (dirxloc!=0) {
       int direzionLocaleR = (diryloc * 16) / dirxloc;
      oldcamioframe++;
      if (oldcamioframe>3)
      {
       if  (direzionLocaleR<-52) {
         if (Direzione>0){  Controllo(3); }  else
         if (Direzione<=0){  Controllo(1); };
         }   else
       { if  (direzionLocaleR<-24) {
         if (Direzione>1){  Controllo(3); }  else
         if (Direzione<1){  Controllo(1); };
         }   else
         { if  (direzionLocaleR<-12) {
           if (Direzione>2){  Controllo(3); }  else
           if (Direzione<2){  Controllo(1); };
         }   else
           { if  (direzionLocaleR<-4) {
             if (Direzione>3){  Controllo(3); }  else
             if (Direzione<3){  Controllo(1); };
           }   else
             { if  (direzionLocaleR<4) {
               if (Direzione>4){  Controllo(3); }  else
               if (Direzione<4){  Controllo(1); };
             }   else
               { if  (direzionLocaleR<12) {
                 if (Direzione>5){  Controllo(3); }  else
                 if (Direzione<5){  Controllo(1); };
               }   else
                 { if  (direzionLocaleR<24) {
                   if (Direzione>6){  Controllo(3); }  else
                   if (Direzione<6){  Controllo(1); };
                 }   else
                   { if  (direzionLocaleR<52) {
                     if (Direzione>7){  Controllo(3); }  else
                     if (Direzione<7){  Controllo(1); };
                   }
                      else  {
                        if (Direzione<8){  Controllo(1); };
                      }
                   }
                 }
               }
             }
           }
         }
       }
      }
     } // dirxloc!=0
     }
    }  //      GiocoInCorso==2

    if (parent.GiocoInCorso==2) { // se in race centra player1
      switch (Direzione) {
       case 0 :  case 8 :  goD =0; break;
       case 1 : case 2 : case 3 : case 4 : case 5 : case 6 : case 7 : goD =  1; break;
       case 9 : case 10: case 11: case 12: case 13: case 14: case 15: goD = -1; break;
      }
      todosupera=false;
      if ((prePosX<=Xboatoget) & ( locSerfX >= Xboatoget))
       { switch (ultimaboasuperata) {
          case 0 : case 2 : case 4 :
            if ( (locSerfY < Yboatoget) & ( goD== 1) )  { if (contafiocchi==0) {todosupera=true; } };
            if ( (locSerfY > Yboatoget) & ( goD== 1) )  { contafiocchi=1;};
           break;
          case 1 : case 3 : case 5 :
            if ( (locSerfY > Yboatoget) & ( goD== 1) )  { if (contafiocchi==0) {todosupera=true; } };
            if ( (locSerfY < Yboatoget) & ( goD== 1) )  { contafiocchi=1;};
            break;
          default : break;
        }
       };

      if (!player1) {
       if (contafiocchi>0) {
         locSerfX=prePosX;
         Xpos = Xpos-dx;
         mSerfer.setRefPixelPosition ( (Xpos /100), (Ypos /100));
         mSerfer2.setRefPixelPosition( (Xpos /100), (Ypos /100));
         contafiocchi=0;
       }
      }


      if ((prePosX>=Xboatoget) & ( locSerfX <= Xboatoget))
       { switch (ultimaboasuperata) {
          case 0 : case 2 : case 4 : if ( (locSerfY > Yboatoget) & ( goD==-1) )  { contafiocchi=0;};   break;
          case 1 : case 3 : case 5 : if ( (locSerfY < Yboatoget) & ( goD==-1) )  { contafiocchi=0;};   break;
          default : break;
        }
       };


      if (todosupera) {
       if (player1) { parent.boa.insuperamento(ultimaboasuperata+1);}
       ultimaboasuperata++; contafiocchi=0;
      };
    }
// ho superato una boa ?
    result = true;
   return result;
  };


  public void imponiDir(int dir){  Direzione  = dir;  setState(); }


  public void scrolla(int dx1, int dy1){
   Xpos=Xpos+dx1*100;
   Ypos=Ypos+dy1*100;
  }


  public void next(){
  // in fase di cambio non ho velocita'
   if (inCambio) { fotocambio++; if (fotocambio>=C_Cambio){inCambio=false; setState();};  };
   if (sposta()) {mSerfer.nextFrame(); mSerfer2.nextFrame(); }  else { if (inCambio) {mSerfer.nextFrame(); mSerfer2.nextFrame();} };
  }

  public void Controllo(int tasto) {
  if (inCambio) {} else
   { switch (tasto) {
      case 2: case 4:  if (Fase==0) { Fase=1;} else { Fase=0;}; inCambio=true;    setStateCambio();            break;
      case 1:          Direzione ++; if (Direzione>15) {Direzione= 0;};           setState(); oldcamioframe=0; break;
      case 3:          Direzione --; if (Direzione<0)  {Direzione=15;};           setState(); oldcamioframe=0; break;
     }
   }
  }

  public void start(int modo){
   switch (modo) {
    case 1 :
      Xpos = 2000; Ypos = 9600;  mSerfer.setRefPixelPosition(Xpos/100, Ypos/100);    imponiDir(3);
     break;
    case 2 :
      if (player1) { Xpos = 2000; Ypos = 9600; } else {  Xpos = 2000; Ypos = 7600; };
       contafiocchi=0;
       mSerfer.setRefPixelPosition(Xpos/100, Ypos/100);  ultimaboasuperata=0;   imponiDir(3);
       arrivatotraguardo = false;  primoarrivato = false;
     break;
    case 3 :
      Xpos = 2000; Ypos = 9600;  mSerfer.setRefPixelPosition(Xpos/100, Ypos/100);   imponiDir(3);
     break;

    default : break;
   }
  }


  public int givemeX(){
   return Xpos;
  }

  public int givemeY(){
   return Ypos;
  }


}



