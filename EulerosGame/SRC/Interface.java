package Euleros;


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.String;
import javax.microedition.lcdui.game.Sprite;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import javax.microedition.rms.*;

import javax.microedition.media.control.VolumeControl;
import javax.microedition.media.Player;
import javax.microedition.media.Manager;
import java.io.*;
import java.util.Random;
//import java.lang.Integer.*;



public class Interface {


  private  int   w;
  private  int   h;
  private  MainCanvas parent;
  private  Image FonteN;
  private  SetQuadro setquadro;

  private  int   lato;
  private  int   segm;
  private  int   OffL=10;
  private  int   OffX;
  private  int   OffY;
  public   int   curX;
  public   int   curY;

  public  int[][]     Ac;
  public  int[][]     Av;
  public  boolean[][] AVis;
  public  boolean[][] AFix;
  public  boolean[][] AHelp;

  private  int      dimFN;
  public   boolean  Solved = false;
  private  boolean  InAnimazione = false;
  private  int      FaseAnimazione = 0;
  private  int      LedX = 0;
  private  int      LedY = 0;
  private  int      contaStep=0;
  private  int      steppo=14;     // velocita' del disegno riga esterne


   public Interface(MainCanvas parent,int w, int h) throws IOException {
    this.parent = parent;   this.w = w;     this.h = h;
    Ac   = new int[9][9];
    Av   = new int[9][9];
    AVis = new boolean[9][9];
    AFix = new boolean[9][9];
    AHelp= new boolean[9][9];
    FonteN  = Image.createImage("/numbers.png");
    dimFN   = FonteN.getHeight()/4;
    cambiaH(h);
    setquadro = new SetQuadro(this);
    TestRecord();
   }

  public void DefinisciQuadro(int indquadro,int nuovo) { // 0 continua , 1 : nuovo
   String Pas="";
    setquadro.DefinisciQuadro(indquadro,nuovo);
    for (int i=0; i<100; i++) {
     if (i!=indquadro) {  Pas = Pas+parent.QIniziati.charAt(i);} else {Pas = Pas+"1";};
    }
   parent.QIniziati=Pas;
  }


  private void disegnaAnimazione(Graphics g) {
   g.setColor(0,0,124);
   switch (FaseAnimazione) {
    case 0:  g.drawLine(OffX,OffY,LedX,OffY);       g.drawLine(OffX-1,OffY-1,LedX,OffY-1);
             LedX=LedX+steppo;    if (LedX>(OffX+lato)) {FaseAnimazione++;};  break;
    case 1:  g.drawLine(OffX,OffY,OffX+lato,OffY);        g.drawLine(OffX-1,OffY-1,OffX+lato+2,OffY-1);
             g.drawLine(OffX+lato,OffY,OffX+lato,LedY);   g.drawLine(OffX+lato+1,OffY,OffX+lato+1,LedY);
             LedY=LedY+steppo;    if (LedY>(OffY+lato)) {FaseAnimazione++;  LedX=(OffX+lato);};  break;
    case 2:  g.drawLine(OffX,OffY,OffX+lato,OffY);            g.drawLine(OffX-1,OffY-1,OffX+lato+2,OffY-1);
             g.drawLine(OffX+lato,OffY,OffX+lato,OffY+lato);  g.drawLine(OffX+lato+1,OffY,OffX+lato+1,OffY+lato);
             g.drawLine(OffX+lato,OffY+lato,LedX,OffY+lato);  g.drawLine(OffX+lato,OffY+lato+1,LedX,OffY+lato+1);
             LedX=LedX-steppo;    if (LedX<OffX) {FaseAnimazione++;  LedY=(OffY+lato);  };  break;
    case 3:  g.drawLine(OffX,OffY,OffX+lato,OffY);            g.drawLine(OffX-1,OffY-1,OffX+lato+2,OffY-1);
             g.drawLine(OffX+lato,OffY,OffX+lato,OffY+lato);  g.drawLine(OffX+lato+1,OffY,OffX+lato+1,OffY+lato);
             g.drawLine(OffX,OffY+lato,OffX+lato,OffY+lato);  g.drawLine(OffX,OffY+lato+1,OffX+lato,OffY+lato+1);
             g.drawLine(OffX,OffY+lato,OffX,LedY);            g.drawLine(OffX-1,OffY+lato,OffX-1,LedY);
             LedY=LedY-steppo;    if (LedY<OffY) {FaseAnimazione++; contaStep=0;   };  break;
    case 4:  g.setColor(255,255,255);   g.fillRect(OffX,OffY,lato,lato);
             g.setColor(0,0,124);
             g.drawRect(OffX,OffY,lato,lato);
             g.drawRect(OffX-1,OffY-1,lato+2,lato+2);
             contaStep++;             if (contaStep>30) { FaseAnimazione++; LedY=OffY;  LedX=OffX; contaStep=0; } break;
    case 5:  g.setColor(255,255,255);   g.fillRect(OffX,OffY,lato,lato);
             g.setColor(0,0,124);
             g.drawRect(OffX,OffY,lato,lato);  g.drawRect(OffX-1,OffY-1,lato+2,lato+2);
             g.setColor(0,0,255);
             g.drawLine(OffX+3*segm,OffY,OffX+3*segm,LedY);  g.drawLine(OffX+3*segm-1,OffY,OffX+3*segm-1,LedY);
             g.drawLine(OffX+6*segm,OffY,OffX+6*segm,LedY);  g.drawLine(OffX+6*segm-1,OffY,OffX+6*segm-1,LedY);
             g.drawLine(OffX,OffY+3*segm,LedX,OffY+3*segm);  g.drawLine(OffX,OffY+3*segm-1,LedX,OffY+3*segm-1);
             g.drawLine(OffX,OffY+6*segm,LedX,OffY+6*segm);  g.drawLine(OffX,OffY+6*segm-1,LedX,OffY+6*segm-1);
             LedY=LedY+steppo; LedX=LedX+steppo;   if (LedY>(OffY+lato)) {FaseAnimazione++;  LedY=OffY;  LedX=OffX;   };  break;
    case 6:  g.setColor(255,255,255);   g.fillRect(OffX,OffY,lato,lato);
             g.setColor(0,0,124);
             g.drawRect(OffX,OffY,lato,lato);  g.drawRect(OffX-1,OffY-1,lato+2,lato+2);
             g.setColor(0,0,255);
             g.drawLine(OffX+3*segm,OffY,OffX+3*segm,OffY+lato);  g.drawLine(OffX+3*segm-1,OffY,OffX+3*segm-1,OffY+lato);
             g.drawLine(OffX+6*segm,OffY,OffX+6*segm,OffY+lato);  g.drawLine(OffX+6*segm-1,OffY,OffX+6*segm-1,OffY+lato);
             g.drawLine(OffX,OffY+3*segm,OffX+lato,OffY+3*segm);  g.drawLine(OffX,OffY+3*segm-1,OffX+lato,OffY+3*segm-1);
             g.drawLine(OffX,OffY+6*segm,OffX+lato,OffY+6*segm);  g.drawLine(OffX,OffY+6*segm-1,OffX+lato,OffY+6*segm-1);
             contaStep++;             if (contaStep>30) { FaseAnimazione++; LedY=OffY;  LedX=OffX;  } break;
    case 7:  g.setColor(255,255,255);   g.fillRect(OffX,OffY,lato,lato);
             g.setColor(0,0,124);
             g.drawRect(OffX,OffY,lato,lato);  g.drawRect(OffX-1,OffY-1,lato+2,lato+2);
             g.setColor(0,0,255);
             g.drawLine(OffX+3*segm,OffY,OffX+3*segm,OffY+lato);  g.drawLine(OffX+3*segm-1,OffY,OffX+3*segm-1,OffY+lato);
             g.drawLine(OffX+6*segm,OffY,OffX+6*segm,OffY+lato);  g.drawLine(OffX+6*segm-1,OffY,OffX+6*segm-1,OffY+lato);
             g.drawLine(OffX,OffY+3*segm,OffX+lato,OffY+3*segm);  g.drawLine(OffX,OffY+3*segm-1,OffX+lato,OffY+3*segm-1);
             g.drawLine(OffX,OffY+6*segm,OffX+lato,OffY+6*segm);  g.drawLine(OffX,OffY+6*segm-1,OffX+lato,OffY+6*segm-1);
             g.setColor(0,0,0);
             for (int i=1; i<9; i++) {
              g.drawLine(OffX+i*segm,OffY,OffX+i*segm,LedY);
              g.drawLine(OffX,OffY+i*segm,LedX,OffY+i*segm);
             }
             LedY=LedY+steppo; LedX=LedX+steppo;   if (LedY>(OffY+lato)) {FaseAnimazione++;  InAnimazione=false;  };  break;


   }
  }


  public void paintInterface(Graphics g){
   if (InAnimazione) { disegnaAnimazione(g); } else
   {
     g.setColor(255,255,255);
     g.fillRect(OffX,OffY,lato,lato);
     g.setColor(0,0,124);
     g.drawRect(OffX,OffY,lato,lato);
     g.drawRect(OffX-1,OffY-1,lato+2,lato+2);
     g.setColor(0,0,0);
     for (int i=1; i<4; i++) {
       g.drawLine(OffX+segm*i,OffY,OffX+segm*i,OffY+lato);
       g.drawLine(OffX,OffY+segm*i,OffX+lato,OffY+segm*i);
     }
     for (int i=4; i<7; i++) {
       g.drawLine(OffX+segm*i+1,OffY,OffX+segm*i+1,OffY+lato);
       g.drawLine(OffX,OffY+segm*i+1,OffX+lato,OffY+segm*i+1);
     }
     for (int i=7; i<9; i++) {
       g.drawLine(OffX+segm*i+2,OffY,OffX+segm*i+2,OffY+lato);
       g.drawLine(OffX,OffY+segm*i+2,OffX+lato,OffY+segm*i+2);
     }
     g.setColor(0,0,255);
       g.drawLine(OffX+segm*3+1,OffY,OffX+segm*3+1,OffY+lato);
       g.drawLine(OffX+segm*6+2,OffY,OffX+segm*6+2,OffY+lato);
       g.drawLine(OffX,OffY+segm*3+1,OffX+lato,OffY+segm*3+1);
       g.drawLine(OffX,OffY+segm*6+2,OffX+lato,OffY+segm*6+2);
     disegnaCursore(g);
     for ( int i=0; i<9; i++) {  for ( int j=0; j<9; j++) { disegnapedina(g,i,j); } }

     if (w<160) {  if (parent.Helped) { parent.gMenu.paintStringa(g ,"HELP", 0 , 6, h-16, false,0);}} else
                {  if (parent.Helped) { parent.gMenu.paintStringa(g ,"HELP ON", 0 , 6, h-16, false,0);} else
                                      { parent.gMenu.paintStringa(g ,"HELP OFF", 0 ,6, h-16, false,0);};
                }


     parent.gMenu.paintStringa(g ,"MENU", 0 , w-6  , h-16, false,1);
   }
  }

  public void cambiaH(int newH){
   h = newH;
    lato=w; if (lato>h) { lato=h;};
    lato = lato-OffL*2;
    segm = lato / 9;
    lato = segm*9+2;
    OffX = (w-lato) / 2 ;
    OffY = (h-lato) / 2 ;
    curX = 5;   curY = 6;
  }


  public void passaValue(int r, int c){
   r--;  c--;
   Av[r][c]=Ac[r][c];
   AVis[r][c]=true;
   AFix[r][c]=true;
  }


  public void AttivazioneQuadro(int senuovo){
   parent.infaseMenu=false;
    if (senuovo==1) { InAnimazione = true;   FaseAnimazione = 0;  LedX = OffX;  LedY = OffY;};
  }

  private void disegnapedina(Graphics g, int i, int j) {
   String hh="";  int offy=0;  int offx=0;
   switch (i) {
    case 3 : case 4: case 5 : offy =segm/2+3; break;
    case 6 : case 7: case 8 : offy =segm/2+4; break;
    default : offy =segm/2+2; break;
   }
   switch (j) {
    case 3 : case 4: case 5 : offx = segm/2+1; break;
    case 6 : case 7: case 8 : offx = segm/2+2; break;
    default : offx =segm/2; break;
   }
   hh = ""+Av[i][j];
   if (AVis[i][j]) {
    if (AFix[i][j]) {paintNumber(g , Av[i][j], 1 ,OffX+segm*j+offx, OffY+segm*i+offy); } else
     if (AHelp[i][j]) {paintNumber(g , Av[i][j], 0 ,OffX+segm*j+offx, OffY+segm*i+offy); } else
       {paintNumber(g , Av[i][j], 2 ,OffX+segm*j+offx, OffY+segm*i+offy); };
   }

   if (parent.Helped) {
    if (!AFix[i][j])  {
     if (!AHelp[i][j])  {
      if (AVis[i][j]) {
       if (StessaColonna(j,Av[i][j])     ) { disegnapallozzoColonna(g,i,j);    };
       if (StessaRiga(i,Av[i][j])        ) { disegnapallozzoRiga(g,i,j); };
       if (StessoSubQuadro(i,j,Av[i][j]) ) { disegnapallozzoSubQuadro(g,i,j); };
      }
     }
    }
   }
  }

  private void disegnaCursore(Graphics g) {
    int offy=0;  int offx=0;
   g.setColor( 0,124,255);
   switch (curX) {
    case 3 : case 4: case 5 : offx =2; break;
    case 6 : case 7: case 8 : offx =3; break;
    default : offx =1; break;
   }
   switch (curY) {
    case 3 : case 4: case 5 : offy =2; break;
    case 6 : case 7: case 8 : offy =3; break;
    default : offy =1; break;
   }
   g.drawRect(OffX+segm*curX+offx   ,OffY+segm*curY+offy  ,segm-2,segm-2);
   g.drawRect(OffX+segm*curX+offx+1 ,OffY+segm*curY+offy+1,segm-4,segm-4);
  }

 public void Fired(){
  if (!AFix[curY][curX]) {
   if (!AHelp[curY][curX]) {
    Av[curY][curX]++;
    AVis[curY][curX]=true;
    if (Av[curY][curX]>9) { Av[curY][curX]=0;  AVis[curY][curX]=false;  }
    if (Av[curY][curX]<0) { Av[curY][curX]=0;  AVis[curY][curX]=false;  }
   }
  }
  TestSolved();
  if (Solved) {
   String Pas="";
    for (int i=0; i<100; i++) { if (i!=parent.IndSchema) {  Pas = Pas+parent.QIniziati.charAt(i);} else {Pas = Pas+"2";}; }
   parent.QIniziati=Pas;
  };


 }



  public void paintNumber(Graphics g , int valN, int Color , int x1, int y1) {
    if (Solved) {Color=3*dimFN;} else { Color = Color*dimFN;};
    {g.drawRegion(FonteN,valN*dimFN,Color,dimFN,dimFN,0,x1-dimFN/2,y1-dimFN/2,Graphics.TOP | Graphics.LEFT); };
  }

 private void disegnapallozzoRiga(Graphics g, int i,int j) {
   g.setColor(255,0,0);
   int ofx=1;  int ofy=1;
   if (j>2) {ofx=2;};  if (j>5) {ofx=3;};
   if (i>2) {ofy=2;};  if (i>5) {ofy=3;};
   g.drawLine(OffX+segm*j+ofx,OffY+segm*i+ofy,OffX+segm*j+ofx+4,OffY+segm*i+ofy);
   g.drawLine(OffX+segm*j+ofx,OffY+segm*i+ofy,OffX+segm*j+ofx,OffY+segm*i+ofy+4);
   g.drawLine(OffX+segm*j+ofx+4,OffY+segm*i+ofy,OffX+segm*j+ofx,OffY+segm*i+ofy+4);
 }


 private void disegnapallozzoColonna(Graphics g, int i,int j) {
   g.setColor(255,0,0);
   int ofx=1;  int ofy=1;
   if (j>2) {ofx=2;};  if (j>5) {ofx=3;};
   if (i>2) {ofy=2;};  if (i>5) {ofy=3;};
   g.drawLine(OffX+segm*j+ofx+segm-2,OffY+segm*i+ofy,OffX+segm*j+ofx+segm-5,OffY+segm*i+ofy);
   g.drawLine(OffX+segm*j+ofx+segm-2,OffY+segm*i+ofy,OffX+segm*j+ofx+segm-2,OffY+segm*i+ofy+4);
   g.drawLine(OffX+segm*j+ofx+segm-5,OffY+segm*i+ofy,OffX+segm*j+ofx+segm-2,OffY+segm*i+ofy+4);
 }


 private void disegnapallozzoSubQuadro(Graphics g, int i,int j) {
   g.setColor(255,0,0);
   int ofx=1;  int ofy=1;
   if (j>2) {ofx=2;};  if (j>5) {ofx=3;};
   if (i>2) {ofy=2;};  if (i>5) {ofy=3;};
   ofy= ofy+segm+OffY;                     ofx= ofx+OffX;
   g.drawLine(segm*j+ofx+4,segm*i+ofy-2,segm*j+ofx+segm-5,segm*i+ofy-2);
   g.drawLine(segm*j+ofx+4,segm*i+ofy-3,segm*j+ofx+segm-5,segm*i+ofy-3);
 }


 private boolean StessaColonna(int c, int value) {
  boolean valueresult = false;
  int volte =0;
  for ( int i=0; i<9; i++) { if ( Av[i][c]==value) { volte++;} };
  if (volte>1) { valueresult=true;};
  return valueresult;
 }


 private boolean StessaRiga(int r, int value) {
  boolean valueresult = false;
  int volte =0;
  for ( int i=0; i<9; i++) { if ( Av[r][i]==value) { volte++;} };
  if (volte>1) { valueresult=true;};
  return valueresult;
 }

 private boolean StessoSubQuadro(int r, int c, int value) {
  boolean valueresult = false;
  int volte =0;   int r1 =0;   int c1 =0;
  switch (r) {
   case 0 :   case 1 :   case 2 : r1=0; break;
   case 3 :   case 4 :   case 5 : r1=3; break;
   case 6 :   case 7 :   case 8 : r1=6; break;
  }
  switch (c) {
   case 0 :   case 1 :   case 2 : c1=0; break;
   case 3 :   case 4 :   case 5 : c1=3; break;
   case 6 :   case 7 :   case 8 : c1=6; break;
  }
  for ( int i=r1; i<(r1+3); i++){ for ( int j=c1; j<(c1+3); j++){ if ( Av[i][j]==value) { volte++;}; }};
  if (volte>1) { valueresult=true;};
  return valueresult;
 }




 private void TestSolved() {
  int numvuoti=0; boolean notSolved=false;
   for (int i=0; i<9; i++) { for (int j=0; j<9; j++) { if (AVis[i][j]) {numvuoti++;}; }; };
  if (numvuoti>=81) {
   for (int i=0; i<9; i++) {
    for (int j=0; j<9; j++) {
     if (StessaColonna(j,Av[i][j])     ) { notSolved=true;};
     if (StessaRiga(i,Av[i][j])        ) { notSolved=true;};
     if (StessoSubQuadro(i,j,Av[i][j]) ) { notSolved=true;};
    }
   }
   Solved=!notSolved;
  };
 }


 public void AddHelp() {
  int numvuoti=0;  int scelta=0;  int numcount=0;
  Random m_r = new Random();

   for (int i=0; i<9; i++) { for (int j=0; j<9; j++) { if (!AVis[i][j]) {numvuoti++;}; }; };
  if (numvuoti>0) {
   scelta=(m_r.nextInt() % numvuoti);
   if (scelta<0) {scelta=-scelta;}
   scelta++;
   for (int i=0; i<9; i++) {
    for (int j=0; j<9; j++) {
     if (!AVis[i][j]) {   numcount++;
       if (scelta==numcount) {AVis[i][j]=true; Av[i][j]=Ac[i][j]; AHelp[i][j]=true; TestSolved(); return;};
     };
    };
   };
  };
  TestSolved();
 };





    public void TestRecord() {
     char loccar;
     try {
      RecordStore rs = null;  rs = RecordStore.openRecordStore("Pt2",false);
      rs.closeRecordStore();  rs = null;
     }
     catch ( Exception e){
      try{
       Defer  DEF = null;   DEF = new Defer();  DEF = null;
       }  catch ( Exception e2){}
     }
    }




 public void SalvaUscita() {
  String R1="";  String RQ="";
   try {
    RecordStore rs = null;  rs = RecordStore.openRecordStore("Pt2",false);
    if (parent.Suono) {R1=R1+"S";} else {R1=R1+"N";};
    RQ = ""+parent.IndSchema;
    if (parent.IndSchema<10) {RQ="0"+RQ;};
    if (parent.IndSchema==100) { RQ="00";};
    R1= R1+RQ+parent.QIniziati;
    rs.setRecord(1,R1.getBytes(),0,R1.length());
    rs.closeRecordStore();  rs = null;
   }
     catch ( Exception e){}
 }


 public void ApriEntrata() {
  char CC;
  String IndQ;
   try {
    RecordStore rs = null;  rs = RecordStore.openRecordStore("Pt2",false);
    String  DF = new String (rs.getRecord(1));
     CC = DF.charAt(0);
     if (CC=='S') {parent.Suono=true;} else {parent.Suono=false;};
     IndQ = ""+DF.charAt(1)+DF.charAt(2);
     parent.IndSchema = Integer.parseInt(IndQ,10);
     if (parent.IndSchema==0) {parent.IndSchema=100;};
     for (int i=3; i<103; i++) {parent.QIniziati=parent.QIniziati+DF.charAt(i);};
    rs.closeRecordStore();  rs = null;
   }
     catch ( Exception e){
     for (int i=0; i<100; i++) {parent.QIniziati=parent.QIniziati+"0";};
     }
 }


 public void SalvaShemaAttuale() {
  String R1="";
   SalvaUscita();
   try {
    RecordStore rs = null;  rs = RecordStore.openRecordStore("Pt2",false);
    for (int i=0; i<9; i++) { for (int j=0; j<9; j++) { R1 = R1+Av[i][j];   }}
    rs.setRecord(parent.IndSchema+1,R1.getBytes(),0,R1.length());
    rs.closeRecordStore();  rs = null;
   }
     catch ( Exception e){}
 }



}




