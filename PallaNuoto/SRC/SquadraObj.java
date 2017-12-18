package PallaNuoto;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public  int  dammiXgiocatore(int gioc)             //  public int dammiYgiocatore(int gioc){
//  public PlayerObj   dammigiocatoreind(int ind);
//  private PortiereObj dammiportiereind(int ind);
//  public  int  dammidirezioneGiocatore(int gioc)
//  private void passapalladafallo( int gioc) {
//  private int  perdepalla(int ng){
//  private void passapalla(PlayerObj Pl, int gioc) {
//  private void passapallaPortiere(PortiereObj Pl, int gioc)
//  public  void provacatturainiziale()
//  public  void fattogolpos(int sq)
//  private void giocatoritogo()
//  public  void next()
//  public  void nascondi()
//  public  void mostra()
//  public  void rimuovitutto()
//  public  void iniziotempo(int qr)
//  public  void spostamiogiocatore(int dx, int dy)
//  public  void firedmiogiocatore()
//  public  void disegnatogo(Graphics g)
//  private void assegnaschema(int indsquadra, int schema)
//  public  void assegnaschemacasuale(int indsquadra, boolean dattacco){
//  private void assegnadoppioschema()
//  public  void controllariposizionamento()
//  private void sbrogliaposizioni()
//  private void sbroglia(PlayerObj G1,PlayerObj G2)
//  public  void annullaeccezioniinfallo();
//  private void annullasbrogliato(){
//  private void annullafallati(){
//  public  void noninattesa()
//  private void avversariopassapalla(int ngio)
//  private void sedueavversaribrogliatipassa()
//  private void allontanaavversarida(int inder)
//  public  void attivafasefallo(int inder)
//  public  void logicadigioco()
//  private int  distanzagiocatori(int p1,int p2)
//  private void TiraGol(PlayerObj G2,int goporta)
//  public  void proprietapalla()
//  public  void annullaaspettapalla();
//  public  void iniziapassaggio()
//  public  void togliaspettapalla()
//  public  void parteazionedaparata(int pl);
//  public  void tempoazionescaduto();
//  public  void risolvi1(){
//  public  boolean dimmisemioattacca()
//  public  boolean dimmisemiodifende()

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Random;



public class SquadraObj {
  private MainCanvas    parent;
  private Interface     parentI;
  private Image         imagepl1;
  private Image         imagepl2;
  private Image         imgportiere;

  public  PortiereObj   portiere1;
  public  PortiereObj   portiere2;
  public  PlayerObj     player1_1;
  public  PlayerObj     player1_2;
  public  PlayerObj     player1_3;
  public  PlayerObj     player2_1;
  public  PlayerObj     player2_2;
  public  PlayerObj     player2_3;
  public  PallaObj      palla;
  public  int           pallaDi=0;

  private int           distcattura;
  public  int           pallainpassaggioa=0;
  public  int           tempoportiere=0;
  private int           MatimePortiere=50;
  public  int           intirogol=0;
  public  int           XGol=0;
  public  int           YGol=0;
  public  int           infallo=0;

  private int           Maxfallato=45;
  public  int           mustpassa=0;
  public  boolean       appenafattofallo=false;
  public  boolean       aspettaappenafattofallo=false;
  public  boolean       infasefallo=false;

  private int[] SequenceKbici   = { 0,0,1,1,2,2,3,3,4,4,3,3,3,2,2,1,2,1,0,0, 1,1,2,2,1,2,3,3,3,4, 4,3,2,2,1,1,1,2,1};
  private int[] SequenceKnuoto  = { 5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12 };
  private int[] SequenceKtiro   = { 13 ,13,13,14,14 ,14,15,15,15,16,16,16 };
  private int[] SequenceKaffoga = { 17,17,17,18,18,18,19,19,19,20,20,20,19,19,19,18,18,18,17,17,17 };

  public int[][] SequenceSbici;
  public int[][] SequenceSnuoto;
  public int[][] SequenceStiro;
  public int[][] SequenceSaffoga;

  public int dimKtiro   = 12;   // della sequenza Ktiro
  public int dimKaffoga = 21;   // della sequenza Kaffoga
  private int dimKbici   = 39;  // della sequenza K1
  private int dimKnuoto  = 16;  // della sequenza Knuoto
  private int dimLS = 21;   // fotogrammi presenti in una riga



  public SquadraObj(MainCanvas parent,Interface parentI) throws IOException {
    this.parent  = parent;
    this.parentI = parentI;
    SequenceSbici   = new int[9][dimKbici];
    SequenceSnuoto  = new int[9][dimKnuoto];
    SequenceStiro   = new int[9][dimKtiro];
    SequenceSaffoga = new int[9][dimKaffoga];
    for (int i=0; i<dimKbici;i++){ for (int j=0; j<9; j++) { SequenceSbici[j][i] = SequenceKbici[i]+dimLS*j; } }
    for (int i=0; i<dimKnuoto;i++){for (int j=0; j<9; j++) { SequenceSnuoto[j][i] = SequenceKnuoto[i]+dimLS*j; } }
    for (int i=0; i<dimKtiro;i++){ for (int j=0; j<9; j++) { SequenceStiro[j][i] = SequenceKtiro[i]+dimLS*j; } }
    for (int i=0; i<dimKaffoga;i++){ for (int j=0; j<9; j++) { SequenceSaffoga[j][i] = SequenceKaffoga[i]+dimLS*j; } }
    imagepl1 = Image.createImage("/player1.png");
    imagepl2 = Image.createImage("/player2.png");
    imgportiere= Image.createImage("/portiere.png");
    portiere1 = new PortiereObj(parent,this,imgportiere,1);
    portiere2 = new PortiereObj(parent,this,imgportiere,2);
    player1_1 = new PlayerObj(parent,this,imagepl1,1);
    player1_2 = new PlayerObj(parent,this,imagepl1,1);
    player1_3 = new PlayerObj(parent,this,imagepl1,1);
    player2_1 = new PlayerObj(parent,this,imagepl2,2);
    player2_2 = new PlayerObj(parent,this,imagepl2,2);
    player2_3 = new PlayerObj(parent,this,imagepl2,2);
    createPalla();
    distcattura=1000*1000;
//    iniziotempo(1);
  }

  private void createPalla()   throws IOException {
    palla         = new PallaObj(parent,this,"/palla.png");
    parent.mLayerManager.insert(palla.mPalla, 0);
  }
  

  public int dammiXgiocatore(int gioc){
   int val=0;
   switch (gioc) {
    case 1 : val=player1_1.dammiX100(); break;
    case 2 : val=player1_2.dammiX100(); break;
    case 3 : val=player1_3.dammiX100(); break;
    case 4 : val=portiere1.dammiX100(); break;
    case 5 : val=player2_1.dammiX100(); break;
    case 6 : val=player2_2.dammiX100(); break;
    case 7 : val=player2_3.dammiX100(); break;
    case 8 : val=portiere2.dammiX100(); break;
   }
   return val;
  }


  public int dammiYgiocatore(int gioc){
   int val=0;
   switch (gioc) {
    case 1 : val=player1_1.dammiY100(); break;
    case 2 : val=player1_2.dammiY100(); break;
    case 3 : val=player1_3.dammiY100(); break;
    case 4 : val=portiere1.dammiY100(); break;
    case 5 : val=player2_1.dammiY100(); break;
    case 6 : val=player2_2.dammiY100(); break;
    case 7 : val=player2_3.dammiY100(); break;
    case 8 : val=portiere2.dammiY100(); break;
   }
   return val;
  }

  public int dammiazionegiocatore(int gioc){
   int val=0;
   switch (gioc) {
    case 1 : val=player1_1.azione; break;
    case 2 : val=player1_2.azione; break;
    case 3 : val=player1_3.azione; break;
//    case 4 : val=portiere1.azione; break;
    case 5 : val=player2_1.azione; break;
    case 6 : val=player2_2.azione; break;
    case 7 : val=player2_3.azione; break;
//    case 8 : val=portiere2.azione; break;
   }
   return val;
  }



  public PlayerObj   dammigiocatoreind(int ind) {
     PlayerObj value=player1_1;
     switch (ind) {
      case 1 : value=player1_1; break;
      case 2 : value=player1_2; break;
      case 3 : value=player1_3; break;
      case 5 : value=player2_1; break;
      case 6 : value=player2_2; break;
      case 7 : value=player2_3; break;
     }
   return value;
  }


  private PortiereObj dammiportiereind(int ind) {
    PortiereObj value=portiere1;
     switch (ind) {
      case 4 : value=portiere1; break;
      case 8 : value=portiere2; break;
     }
   return value;
  }


  public int dammidirezioneGiocatore(int gioc){
   int val=0;
   switch (gioc) {
    case 1 : val=player1_1.direzione; break;
    case 2 : val=player1_2.direzione; break;
    case 3 : val=player1_3.direzione; break;
    case 4 : val=portiere1.direzione; break;
    case 5 : val=player2_1.direzione; break;
    case 6 : val=player2_2.direzione; break;
    case 7 : val=player2_3.direzione; break;
    case 8 : val=portiere2.direzione; break;
   }
   return val;
  }


  private void passapalladafallo( int gioc) {
    if (pallainpassaggioa==0) {
     if (pallaDi==0) { return;};
     PlayerObj     PX=dammigiocatoreind(gioc);
     PX.aspettapalla=true;
     PlayerObj     Pl=dammigiocatoreind(pallaDi);
     Pl.setdirazione(Pl.direzione,2); // tira
     pallainpassaggioa=gioc;
     assegnadoppioschema();
     parent.resettaTimeazione();
     infasefallo=true;
    }
  }

  private int perdepalla(int ng){
   int val=ng;
   int x1=dammiXgiocatore(ng);   int y1=dammiYgiocatore(ng);
   int ddep=0;  int ddmin=4000*4000;

   if (ng<=4) {
     ddep=distanzagiocatori(ng,5);  if (ddep<ddmin){ val=5;} else {
      ddep=distanzagiocatori(ng,6);  if (ddep<ddmin){ val=6;} else {
       ddep=distanzagiocatori(ng,7);  if (ddep<ddmin){ val=7;};
      }
     }
   } else   {
     ddep=distanzagiocatori(ng,1);  if (ddep<ddmin){ val=1;} else {
      ddep=distanzagiocatori(ng,2);  if (ddep<ddmin){ val=2;} else {
       ddep=distanzagiocatori(ng,3);  if (ddep<ddmin){ val=3;};
      }
     }
   }

   return val;
  };


  private void passapalla(PlayerObj Pl, int gioc) {
    if (pallainpassaggioa==0) {
     Random m_r = new Random();
     int Rv =0;
      Rv=((m_r.nextInt() % 100));           if (Rv<0){Rv=-Rv;};
// il passaggio puo' non andare a buon fine e quindi va ad altro giocatore avversario.
      if (gioc<5){
       if (Rv>parent.info.parametri[parent.pl1][parent.info.Pattacco]) {
        Rv=((m_r.nextInt() % 100));           if (Rv<0){Rv=-Rv;};
        if (Rv<parent.info.parametri[parent.pl2][parent.info.Pdifesa]) {
         gioc = perdepalla(gioc);
        }
       }
      } else {
       if (Rv>parent.info.parametri[parent.pl2][parent.info.Pattacco]) {
        Rv=((m_r.nextInt() % 100));           if (Rv<0){Rv=-Rv;};
        if (Rv<parent.info.parametri[parent.pl1][parent.info.Pdifesa]) {
         gioc = perdepalla(gioc);
        }
       }
      }

     int locdirezione = parent.info.dammidirezionetogo(dammiXgiocatore(gioc),dammiYgiocatore(gioc),Pl.dammiX100(),Pl.dammiY100());
     Pl.setdirazione(locdirezione,2); // tira
     PlayerObj     PX=dammigiocatoreind(gioc);
     PX.aspettapalla=true;
     pallainpassaggioa=gioc;
    }
  }

  private void passapallaPortiere( int por) {
    int d1; int d2; int d3;   int gioc=1;
    if (pallainpassaggioa==0) {
     if (por==1) {
      d1=dammiYgiocatore(1);      d2=dammiYgiocatore(2);      d3=dammiYgiocatore(3);
      if (d1<d2) { if (d1<d3) {gioc=1; } else {gioc=3;}; }
       else      { if (d2<d3) {gioc=2; } else {gioc=3;}; }
     } else
     {
      d1=dammiYgiocatore(5);      d2=dammiYgiocatore(6);      d3=dammiYgiocatore(7);
      if (d1>d2) { if (d1>d3) {gioc=5; } else {gioc=7;}; }
       else      { if (d2>d3) {gioc=6; } else {gioc=7;}; }
     };
     PlayerObj     PX=dammigiocatoreind(gioc);
     PX.aspettapalla=true;
     pallainpassaggioa=gioc;
     iniziapassaggio();
    }
  }



  public void togliaspettapalla() {
    if (pallaDi==8)
      {portiere2.annullaparata(); portiere2.tengopalla=true; tempoportiere=MatimePortiere; }
           else     {portiere2.tengopalla=false;};
    if (pallaDi==4)
      {
      portiere1.annullaparata(); portiere1.tengopalla=true; tempoportiere=MatimePortiere; }
           else     {portiere1.tengopalla=false;};
    PlayerObj     PX=dammigiocatoreind(pallaDi);    PX.aspettapalla=false;
    aspettaappenafattofallo=false;

    pallainpassaggioa=0;
    switch (pallaDi) {
     case 1: case 2: case 3:   assegnaschemacasuale(1,true);  assegnaschemacasuale(2,false);   break;
     case 5: case 6: case 7:   assegnaschemacasuale(2,true);  assegnaschemacasuale(1,false);   break;
    };
  }


  public  void parteazionedaparata(int pl){
    if (intirogol!=0) {
     intirogol=0;
     parent.resettaTimeazione();
     annullaaspettapalla();
     pallainpassaggioa=0;
     if (pl==1) { assegnaschemacasuale(1,true);  assegnaschemacasuale(2,false); }     // da portire 4
        else    { assegnaschemacasuale(2,true);  assegnaschemacasuale(1,false); };    // da portire 8 };//
    }    
  }



  public void provacatturainiziale() {
   if (pallaDi>0) { return; };

   int dd1, dd2;  Random m_r = new Random();
   int friend = (m_r.nextInt() % 2);
    dd1=player1_2.distdaPalla();    dd2=player2_2.distdaPalla();
    if (dd1<distcattura)
    {
     pallaDi=2;
     if (friend==0) {  passapalla(player1_2,1); } else  { passapalla(player1_2,3); };
       assegnaschema(1,1);
     parent.resettaTimeazione();
    }
     else
    { if (dd2<distcattura) {
       pallaDi=6;
       if (friend==0) { passapalla(player2_2,5); } else  { passapalla(player2_2,7); };
       assegnaschema(2,1);
       parent.resettaTimeazione();
      }
    };
  };


  public void fattogolpos(int sq) {
   if (sq==1) {
    player1_1.assegnaTogo((parent.w*3/4)*100,(parent.gInterface.MidF+40)*100);
    player1_2.assegnaTogo((parent.w/2)*100  ,(parent.gInterface.MidF+50)*100);
//    player1_2.posiziona100(parent.w/2       , parent.gInterface.MidF+50);                    player1_2.setdirazione(8,0); // bici
    player1_3.assegnaTogo((parent.w/4)*100  ,(parent.gInterface.MidF+40)*100);
    player2_1.assegnaTogo((parent.w*3/4)*100,(parent.gInterface.MidF-30)*100);
    player2_2.assegnaTogo((parent.w/2)*100  ,(parent.gInterface.MidF-12)*100);
    player2_3.assegnaTogo((parent.w/4)*100  ,(parent.gInterface.MidF-30)*100);
   }
   else
   {
    player1_1.assegnaTogo((parent.w*3/4)*100,(parent.gInterface.MidF+40)*100);
    player1_2.assegnaTogo((parent.w/2)*100  ,(parent.gInterface.MidF+12)*100);
//    player1_2.posiziona100(parent.w/2       , parent.gInterface.MidF+12);                    player1_2.setdirazione(0,0); // bici
    player1_3.assegnaTogo((parent.w/4)*100  ,(parent.gInterface.MidF+40)*100);
    player2_1.assegnaTogo((parent.w*3/4)*100,(parent.gInterface.MidF-30)*100);
    player2_2.assegnaTogo((parent.w/2)*100  ,(parent.gInterface.MidF-50)*100);
    player2_3.assegnaTogo((parent.w/4)*100  ,(parent.gInterface.MidF-30)*100);
   }

  }



  private void giocatoritogo() {
   player1_1.versoTogo();   player1_3.versoTogo();
   if (parent.gInterface.inRiposizionamentoGol) {player1_2.versoTogo();};
   player2_1.versoTogo();   player2_2.versoTogo();   player2_3.versoTogo();
   // se sono arrivato a destinazione con la palla allora la passo e aspetto nuovo schema
   if ((pallaDi==5) & (!player2_1.isgoing)) {  avversariopassapalla(5); };
   if ((pallaDi==6) & (!player2_2.isgoing)) {  avversariopassapalla(6); };
   if ((pallaDi==7) & (!player2_3.isgoing)) {  avversariopassapalla(7); };
  }


  public void next(){
   if ((pallaDi==4) | (pallaDi==8)) {annullafallati();      };
   portiere1.next();   portiere2.next();
   player1_1.next();   player1_2.next();   player1_3.next();
   player2_1.next();   player2_2.next();   player2_3.next();
  }


  public void nascondi(){
   portiere1.nascondi();   portiere2.nascondi();
   player1_1.nascondi();   player1_2.nascondi();   player1_3.nascondi();
   player2_1.nascondi();   player2_2.nascondi();   player2_3.nascondi();
  }

  public void mostra(){
   portiere1.mostra();   portiere2.mostra();
   player1_1.mostra();   player1_2.mostra();   player1_3.mostra();
   player2_1.mostra();   player2_2.mostra();   player2_3.mostra();
  }

  public void rimuovitutto(){
   portiere1.rimuovi();   portiere2.rimuovi();
   player1_1.rimuovi();   player1_2.rimuovi();   player1_3.rimuovi();
   player2_1.rimuovi();   player2_2.rimuovi();   player2_3.rimuovi();
  }




  public void iniziotempo(int qr){
    parent.gInterface.finepartita=false;
    portiere1.posiziona100(parent.w/2-16, (parent.h-12));
    portiere2.posiziona100(parent.w/2, 25);
    palla.pallaCentro();
    switch (qr){
     case 1 :
       player2_1.posiziona100(parent.w*3/4 , 25);           player2_1.setdir(8);
       player2_1.assegnaTogo(player2_1.dammiX100(),(parent.gInterface.MidF-30)*100); player2_1.settaVelocita(70);
       player2_2.posiziona100(parent.w/2-15, 25);           player2_2.setdir(8);
       player2_2.assegnaTogo(palla.dammiX100(),palla.dammiY100()); player2_2.settaVelocita(90);
       player2_3.posiziona100(parent.w/4   , 25);           player2_3.setdir(8);
       player2_3.assegnaTogo(player2_3.dammiX100(),(parent.gInterface.MidF-30)*100); player2_3.settaVelocita(70);
       player1_1.posiziona100(parent.w*3/4 , parent.h-12);  player1_1.setdir(0);
       player1_1.assegnaTogo(player1_1.dammiX100(),(parent.gInterface.MidF+30)*100); player1_1.settaVelocita(70);
       player1_2.posiziona100(parent.w/2+3, parent.h-12);  player1_2.setdir(0);
       player1_3.posiziona100(parent.w/4   , parent.h-12);  player1_3.setdir(0);
       player1_3.assegnaTogo(player1_3.dammiX100(),(parent.gInterface.MidF+30)*100); player1_3.settaVelocita(70);
      break;
     case 2 :
       player1_1.posiziona100(parent.w*3/4 , 25);           player1_1.setdir(8);
       player1_2.posiziona100(parent.w/2-3, 25);            player1_2.setdir(8);
       player1_3.posiziona100(parent.w/4   , 25);           player1_3.setdir(8);
       player2_1.posiziona100(parent.w*3/4 , parent.h-12);  player2_1.setdir(0);
       player2_2.posiziona100(parent.w/2+15, parent.h-12);  player2_2.setdir(0);
       player2_3.posiziona100(parent.w/4   , parent.h-12);  player2_3.setdir(0);
      break;
    }
  }

  public void spostamiogiocatore(int dx, int dy) {
    player1_2.movimento(dx, dy);
    if ((dx==0) & (dy==0)) {} else {player1_2.spostadirezione();};
  }


  private void TiraGol(PlayerObj GG,int goporta) {
    if (intirogol!=0) { if (GG.azione==GG.tira) { return;}; }
//       se io mio giocatore non e' in fase tiro allora continua
    Random m_r = new Random();
    int xer = ((m_r.nextInt() % 22));
    XGol=(parent.w/2+xer)*100;
    if (goporta==2) { YGol=(parent.gInterface.offF1- 2)*100;  intirogol=2; }
             else   { YGol=(parent.gInterface.offF2+2)*100;   intirogol=1; }
    int locdir=parent.info.dammidirezionetogo(XGol,YGol,GG.dammiX100(),GG.dammiY100());
    GG.setdirazione(locdir,2); // tira;
  };






  public void firedmiogiocatore() {
  int dd1; int dd2;
   switch (pallaDi) {
    case 1 :
      dd1= distanzagiocatori(1,2);
      dd2= distanzagiocatori(1,3);
      if (portiere2.inparata) { TiraGol(player1_1,2); } else
      {if (dd1<dd2) { passapalla(player1_1, 2); } else {passapalla(player1_1, 3);};};
     break;
    case 2 :
      dd1= distanzagiocatori(2,1);
      dd2= distanzagiocatori(2,3);
      if (portiere2.inparata) { TiraGol(player1_2,2); } else
      { if (dd1<dd2) { passapalla(player1_2, 1); } else {passapalla(player1_2, 3);};};
     break;
    case 3 :
      dd1= distanzagiocatori(3,1);
      dd2= distanzagiocatori(3,2);
      if (portiere2.inparata) { TiraGol(player1_3,2); } else
      {if (dd1<dd2) { passapalla(player1_3, 1); } else {passapalla(player1_3, 2);};};
     break;

    case 4 :
      tempoportiere=1;
     break;

   }

  }



  public void disegnatogo(Graphics g) {
   g.setStrokeStyle(Graphics.DOTTED);
   player1_1.disegnatogo(g);
   player1_3.disegnatogo(g);
      g.setColor(0,240,255);
   player2_1.disegnatogo(g);
   player2_2.disegnatogo(g);
   player2_3.disegnatogo(g);
  }


  public void assegnaschemacasuale(int indsquadra, boolean dattacco){
   if (indsquadra==1) {
    parent.info.sorteggiapos(dattacco,1,1); // attacco giocatore squadra
    player1_1.assegnaTogo(parent.info.sortx,parent.info.sorty);
    parent.info.sorteggiapos(dattacco,3,1); // attacco giocatore squadra
    player1_3.assegnaTogo(parent.info.sortx,parent.info.sorty);
   }
   if (indsquadra==2) {
    parent.info.sorteggiapos(dattacco,5,2); // attacco giocatore squadra
    player2_1.assegnaTogo(parent.info.sortx,parent.info.sorty);
    parent.info.sorteggiapos(dattacco,6,2); // attacco giocatore squadra
    player2_2.assegnaTogo(parent.info.sortx,parent.info.sorty);
    parent.info.sorteggiapos(dattacco,7,2); // attacco giocatore squadra
    player2_3.assegnaTogo(parent.info.sortx,parent.info.sorty);
   }
  }


  private void assegnadoppioschema(){
   if (pallaDi<=4) { assegnaschemacasuale(1,true);  assegnaschemacasuale(2,false);} else
                   { assegnaschemacasuale(2,true);  assegnaschemacasuale(1,false);};
  }


  public void assegnaschema(int indsquadra, int schema){
   int locx1=0; int locy1=0; int locx2=0; int locy2=0; int locx3=0; int locy3=0;
    if (indsquadra==1) {
     switch (schema) {
      case 1 :
        locx1  = (parent.w*3/4)*100;     locy1  = (parent.gInterface.offF1+20)*100;
        locx3  = (parent.w/4)*100;       locy3  = (parent.gInterface.offF1+25)*100;
        break;
      default :  break;
     }
     player1_1.assegnaTogo(locx1,locy1);   player1_3.assegnaTogo(locx3,locy3);
    }
     else
    {
     switch (schema) {
      case 1 :
        locx1  = (parent.w*3/4)*100;     locy1  = (parent.gInterface.offF2-20)*100;
        locx2  = (parent.w/2)*100;       locy2  = (parent.gInterface.offF2-20)*100;
        locx3  = (parent.w/4)*100;       locy3  = (parent.gInterface.offF2-25)*100;
        player2_1.assegnaTogo(locx1,locy1);  player2_2.assegnaTogo(locx2,locy2);  player2_3.assegnaTogo(locx3,locy3);
        break;
      case 2 :
        parent.info.sorteggiapos(true,5,2); // attacco giocatore squadra
        player2_1.assegnaTogo(parent.info.sortx,parent.info.sorty);
        parent.info.sorteggiapos(true,6,2); // attacco giocatore squadra
        player2_2.assegnaTogo(parent.info.sortx,parent.info.sorty);
        parent.info.sorteggiapos(true,7,2); // attacco giocatore squadra
        player2_3.assegnaTogo(parent.info.sortx,parent.info.sorty);
        break;
      default :  break;
     }
    };
  }


 public void controllariposizionamento(){
  boolean arrivati=true;
  if (player1_1.isgoing) {arrivati=false;};
  if (player1_2.isgoing) {arrivati=false;};
  if (player1_3.isgoing) {arrivati=false;};
  if (player2_1.isgoing) {arrivati=false;};
  if (player2_2.isgoing) {arrivati=false;};
  if (player2_3.isgoing) {arrivati=false;};
  if (arrivati) {
   parent.gInterface.inRiposizionamentoGol=false;
   parent.gInterface.xcgol=0;
   parent.gInterface.inAttesaPartenza=true;
   parent.gInterface.TempotoStart=parent.gInterface.WaitdaGol/2;   // 5000
   player1_1.setdir(15);   player1_2.setdir(0);   player1_3.setdir(1);
   player2_1.setdir(9);    player2_2.setdir(8);   player2_3.setdir(7);
   if (parent.gInterface.lastgol==2) {pallaDi=2;} else {pallaDi=6;};
   palla.pallaversoCentro();
   portiere1.tengopalla=false;     portiere2.tengopalla=false;
  }
 }



 private int distanzagiocatori(int p1,int p2){
  int value=0;
  int x1 = dammiXgiocatore(p1);     int x2 = dammiXgiocatore(p2);
  int y1 = dammiYgiocatore(p1);     int y2 = dammiYgiocatore(p2);
  value= (x2-x1)*(x2-x1)+ (y2-y1)*(y2-y1);
  return value;
 }


 private void sbroglia(int p1,int p2){
// dimensione img 20x20     // il centro :(10,15)
   PlayerObj   P1;  PlayerObj   P2;   PortiereObj Po;
   int movedx; int movedy; int dx; int dy;
   Random m_r = new Random();
   int Rv=0;
   boolean sb1=false;  boolean sb2=false;
  int x1=dammiXgiocatore(p1);  int y1=dammiYgiocatore(p1);
  int x2=dammiXgiocatore(p2);  int y2=dammiYgiocatore(p2);
  dx = (x2-x1);

  P1=dammigiocatoreind(p1);  P2=dammigiocatoreind(p2);

   if ((dx>=0) & (dx<20*100))  { movedx= (20*100-dx)/2;  P1.posiziona(x1-movedx,y1); sb1=true; };
   if ((dx<0)  & (dx>-20*100)) { movedx= (20*100+dx)/2;  P1.posiziona(x1+movedx,y1); sb1=true; };
   if (sb1) {
    P1.sbrogliato=true;
    if ((p1<4) & (p2>4)) { P1.nfallato++; if (P1.azione==0) { P1.nfallato=P1.nfallato+5; } };
    if (p1!=2) {
     Rv=((m_r.nextInt() % 100));           if (Rv<0){Rv=-Rv;};
     if (Rv< parent.info.parametri[parent.pl2][parent.info.Pfallo]) { P1.nfallato++;  };
    }
   };

  if ((p2!=4) & (p2!=8)) {
   if ((dx>=0) & (dx<20*100))  { movedx= (20*100-dx)/2;  P2.posiziona(x2+movedx,y2); sb2=true; };
   if ((dx<0)  & (dx>-20*100)) { movedx= (20*100+dx)/2;  P2.posiziona(x2-movedx,y2); sb2=true; };
   if (sb2) {
    P2.sbrogliato=true;
    if ((p1<4) & (p2>4)) { P2.nfallato++; if (P2.azione==0) { P2.nfallato=P2.nfallato+5; } };
    Rv=((m_r.nextInt() % 100));           if (Rv<0){Rv=-Rv;};
    if (Rv< parent.info.parametri[parent.pl1][parent.info.Pfallo]) { P2.nfallato++;  };
   };
  }

 }



 private void avversariopassapalla(int ngio) {
  int dd1=0;  int dd2=0;
  switch(ngio) {
   case 5 :
    dd1=player2_2.distdaPalla();    dd2=player2_3.distdaPalla();
    player2_1.stoptogo();
    if (dd1<dd2) { passapalla(player2_1, 6); } else {passapalla(player2_1, 7); };
    break;
   case 6 :
    dd1=player2_1.distdaPalla();    dd2=player2_3.distdaPalla();
    player2_2.stoptogo();
    if (dd1<dd2) { passapalla(player2_2, 5); } else {passapalla(player2_2, 7); };
    break;
   case 7 :
    player2_3.stoptogo();
    dd1=player2_1.distdaPalla();    dd2=player2_2.distdaPalla();
    if (dd1<dd2) { passapalla(player2_3, 5); } else {passapalla(player2_3, 6); };
    break;

  }
 }


 private void sedueavversaribrogliatipassa(){
  int conta=0;
  if ((pallaDi>4) & (pallaDi<8)) {
   if (player2_1.sbrogliato) { conta++;};
   if (player2_2.sbrogliato) { conta++;};
   if (player2_3.sbrogliato) { conta++;};
   if (conta>1) {  avversariopassapalla(pallaDi);};
  }
 }


 private void allontanaavversarida(int inder){
  int ax1=0; int ay1=0;
   ax1=dammiXgiocatore(inder);   ay1=dammiYgiocatore(inder);
  if (inder<=4) {
   player2_1.allontanada(ax1,ay1);
   player2_2.allontanada(ax1,ay1);
   player2_3.allontanada(ax1,ay1);
  } else
  {
   player1_1.allontanada(ax1,ay1);
   player1_2.allontanada(ax1,ay1);
   player1_3.allontanada(ax1,ay1);
  }
 }


 public void attivafasefallo(int inder)  {
  infallo=inder;
  parent.resettaTimeazione();
  switch (inder) {
   case 1: player1_1.setdirazione(player1_1.direzione,player1_1.affoga);    break;
   case 2: player1_2.setdirazione(player1_2.direzione,player1_2.affoga);    break;
   case 3: player1_3.setdirazione(player1_3.direzione,player1_3.affoga);    break;
   case 5: player2_1.setdirazione(player2_1.direzione,player2_1.affoga);    break;
   case 6: player2_2.setdirazione(player2_2.direzione,player2_2.affoga);    break;
   case 7: player2_3.setdirazione(player2_3.direzione,player2_3.affoga);    break;
  }
  annullafallati();
   player1_1.stoptogo();   player1_2.stoptogo();   player1_3.stoptogo();
   player2_1.stoptogo();   player2_2.stoptogo();   player2_3.stoptogo();
   player1_1.setdirazione(player1_1.direzione,player1_1.bici);
   player1_2.setdirazione(player1_2.direzione,player1_2.bici);
   player1_3.setdirazione(player1_3.direzione,player1_3.bici);
   player2_1.setdirazione(player2_1.direzione,player2_1.bici);
   player2_2.setdirazione(player2_2.direzione,player2_2.bici);
   player2_3.setdirazione(player2_3.direzione,player2_3.bici);
   allontanaavversarida(inder);
 }


 private void guardasefallo(){
// prima se e' fallo sul possessore palla
  if (player1_1.nfallato>Maxfallato) {  if (pallaDi==1) { attivafasefallo(1);  }; }
  if (player1_2.nfallato>Maxfallato) {  if (pallaDi==2) { attivafasefallo(2);  }; }
  if (player1_3.nfallato>Maxfallato) {  if (pallaDi==3) { attivafasefallo(3);  }; }
  if (player2_1.nfallato>Maxfallato) {  if (pallaDi==5) { attivafasefallo(5);  }; }
  if (player2_2.nfallato>Maxfallato) {  if (pallaDi==6) { attivafasefallo(6);  }; }
  if (player2_3.nfallato>Maxfallato) {  if (pallaDi==7) { attivafasefallo(7);  }; }

  if (infallo==0){
   if (player1_1.nfallato>Maxfallato) {  player1_1.nfallato=0; if (pallaDi==1) { attivafasefallo(1);  }; }
   if (player2_1.nfallato>Maxfallato) {  player2_1.nfallato=0; if (pallaDi==5) { attivafasefallo(5);  }; }
   if (player2_2.nfallato>Maxfallato) {  player2_2.nfallato=0; if (pallaDi==6) { attivafasefallo(6);  }; }
   if (player1_2.nfallato>Maxfallato) {  player1_2.nfallato=0; if (pallaDi==2) { attivafasefallo(2);  }; }
   if (player1_3.nfallato>Maxfallato) {  player1_3.nfallato=0; if (pallaDi==3) { attivafasefallo(3);  }; }
   if (player2_3.nfallato>Maxfallato) {  player2_3.nfallato=0; if (pallaDi==7) { attivafasefallo(7);  }; }
  }
  if (infallo>0)  {annullafallati();
  };
 }

 public void logicadigioco(){
// se due avversari brogliati allor anache il terzo cosi passa palla e si fa nuovo schema
// nel frattempo i due brogliati si fermano.
  guardasefallo();
  sedueavversaribrogliatipassa();
  if (!palla.isgoing & (infallo==0)){
   switch (pallaDi) {
    case 1 :
     break;
    case 5 :
     if (portiere1.inparata) { TiraGol(player2_1,1); } else
      { if (player2_1.sbrogliato) { avversariopassapalla(5);};};
     break;
    case 6 :
     if (portiere1.inparata) { TiraGol(player2_2,1); } else
      { if (player2_2.sbrogliato) { avversariopassapalla(6);};};
     break;
    case 7 :
     if (portiere1.inparata) { TiraGol(player2_3,1); } else
      { if (player2_3.sbrogliato) { avversariopassapalla(7);};};
     break;
    case 4 :
      if (parent.gInterface.inRiposizionamentoGol) {tempoportiere=0;};
      if (tempoportiere>0) {tempoportiere--; if (tempoportiere<=0) { passapallaPortiere(1); };};
     break;
    case 8 :
      if (parent.gInterface.inRiposizionamentoGol) {tempoportiere=0;};
      if (tempoportiere>0) {tempoportiere--; if (tempoportiere<=0) { passapallaPortiere(2); };};
     break;
   }
  }
 }


 public  void annullaeccezioniinfallo() {
  if ((pallaDi==4) | (pallaDi==8)) {
    infallo=0;
  } else
  {
   if (mustpassa==0)
   {
    switch(infallo) {
     case 1: if (player1_1.azione!=player1_1.affoga) {infallo=0;};   break;
     case 2: if (player1_2.azione!=player1_2.affoga) {infallo=0;};   break;
     case 3: if (player1_3.azione!=player1_3.affoga) {infallo=0;};   break;
     case 5: if (player2_1.azione!=player2_1.affoga) {infallo=0;};   break;
     case 6: if (player2_2.azione!=player2_2.affoga) {infallo=0;};   break;
     case 7: if (player2_3.azione!=player2_3.affoga) {infallo=0;};   break;
    }

   }
  }

 }


 private void annullasbrogliato(){
   player1_1.sbrogliato=false;   player1_2.sbrogliato=false;   player1_3.sbrogliato=false;
   player2_1.sbrogliato=false;   player2_2.sbrogliato=false;   player2_3.sbrogliato=false;
 }

 public void annullafallati(){
   player1_1.nfallato=0;   player1_2.nfallato=0;   player1_3.nfallato=0;
   player2_1.nfallato=0;   player2_2.nfallato=0;   player2_3.nfallato=0;
 }


 public void noninattesa() {
   player1_1.test=0;   player1_2.test=0;   player1_3.test=0;
   player2_1.test=0;   player2_2.test=0;   player2_3.test=0;

   if (mustpassa!=0) {
     if (pallaDi!=mustpassa){ passapalladafallo(mustpassa); parent.resettaTimeazione();};
     mustpassa=0; annullafallati();  appenafattofallo=true; aspettaappenafattofallo=true;
   };

   giocatoritogo();                          
   annullasbrogliato();
   if (!parent.gInterface.inRiposizionamentoGol) { sbrogliaposizioni();};
 }

 private void sbrogliaposizioni(){
  int dd;
  for (int i=1; i<8; i++)
  { for (int j=i+1; j<9; j++)              // 2000*2000 = 20 pixel dist
    {
      if (i!=4) { dd = distanzagiocatori(i,j); if (dd<(4000000)) { sbroglia(i,j); };};
    };
  }
 }




  public  void annullaaspettapalla(){
   player1_1.aspettapalla=false;   player1_2.aspettapalla=false;   player1_3.aspettapalla=false;
   player2_1.aspettapalla=false;   player2_2.aspettapalla=false;   player2_3.aspettapalla=false;
  }


  public void iniziapassaggio(){
   if (pallainpassaggioa>0) {
     pallaDi=pallainpassaggioa;
     palla.vaiverso(dammiXgiocatore(pallaDi),dammiYgiocatore(pallaDi));
   };
  }


  
  public  void  risolvi1(){
   PlayerObj Pl;
   if (!palla.isgoing){
    if (pallaDi!=pallainpassaggioa) {
     Pl=dammigiocatoreind(pallaDi);
     if (Pl.azione==0) {
      if (pallainpassaggioa>0) {
       Pl=dammigiocatoreind(pallainpassaggioa);
       if (Pl.aspettapalla) {
        Pl.aspettapalla=false;
        pallainpassaggioa=0;
       }
      } 
     }
    }
   }
  }



  public  void tempoazionescaduto() {
    parent.tempoazionepalla = parent.valuetimepalla;
    // visualizzare bandierina sul tempo
     parent.gInterface.nfotobantime=20;
    // passare la palla avversario
    PlayerObj Pl;
    if (pallaDi<=4) {
     if (pallaDi>0) { Pl=dammigiocatoreind(pallaDi); passapalla(Pl, 6); }
    } else          { Pl=dammigiocatoreind(pallaDi); passapalla(Pl, 2); };
  }

  public  boolean dimmisemioattacca(){
   boolean res=false;
   int yer=dammiYgiocatore(2)/100;
   if (yer<(parent.h/3)) {res=true; };
   return res;
  }

  public  boolean dimmisemiodifende(){
   boolean res=false;
   int yer=dammiYgiocatore(2)/100;
   if (yer>(parent.h*2/3)) {res=true; };
   return res;
  }


}



