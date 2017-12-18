package PallaNuoto;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //


//  public void paintMenu(Graphics g)
//  public void paintStringa(Graphics g , String st, int Big , int x1, int y1, boolean scorri,int alli)
//  public void cambiaH(int newW,int newH)


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Random;

public class GrafMenu {
  private  Image Titolo;
  private  Image FonteS;
  private  Image Menu2;
  private  Image Friend;
  private  Image Tour;
  public   Image Bandiere;
  private  int   w;                      private  int   h;
  private  int   dimFS,  dimoffFS;
  public   int   parziall=0;
  private String Alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.-: ";
  private String Numerici = "0123456789 ";
  public   int   last1riga = 2;          public   int   last2riga = 5;
  private  int   rigf=0;                 private  int   colf=0;
  private  int   Lbar=0;
  private  String tt = "";
  private  int xs;                       private  int ys ;
  private  int ll;
  private  int offImg =0;
  private  int riga    =0;               private  int colonna = 0;
  private  char locC;
  private  int cra = 18;
  private  String TxtNumer = "";
  private  String hhh;
  private  int xenterd =0;               private  int yenterd =0;
  private  boolean premutoPP= false;
  private  int faseonda=0;
  private  boolean inondasu=true;
  private  MainCanvas parent;
  public   int   indicebandiera=0;
  public   int   Numsel=0;
  public   int   yriga2=0;

   public GrafMenu(MainCanvas parent,int w, int h) throws IOException {
    this.parent = parent;   this.w = w;     this.h = h;
    Titolo  = Image.createImage("/titolo.png");
    FonteS  = Image.createImage("/caratteri.png");
    Menu2   = Image.createImage("/menu2.png");
    Friend  = Image.createImage("/pallanuoto.png");
    Tour    = Image.createImage("/tour.png");
    Bandiere= Image.createImage("/bandiere.png");
    dimFS   = FonteS.getHeight()/2;
    cra     = FonteS.getWidth()/dimFS;
    dimoffFS = dimFS-3;
//    contaftp=System.currentTimeMillis();
    if (h>160) {yriga2=150;} else {yriga2=h*3/4;}
   }


  public void premutoUp(){
   switch (parent.subfaseMenu) {
    case 0 :          if (parent.IndiceMenu>3) {parent.IndiceMenu = parent.IndiceMenu-3; parziall=0;};
            if (parent.justStarted & (parent.IndiceMenu==2)) {parent.IndiceMenu=3;};                      break;  // menu normale
    case 2 :          if (parent.info.rigaattiva>0) {parent.info.rigaattiva--; }                          break;  // menu - help
    case 3 : case 4 : if (indicebandiera>3) {indicebandiera=indicebandiera-4;};                           break;  // scegli bandiera
   }
  }

  public void premutoDown(){
   switch (parent.subfaseMenu) {
    case 0 :          if (parent.IndiceMenu<4) {parent.IndiceMenu = parent.IndiceMenu+3; parziall=0;};    break;  // menu normale
    case 2 :          if (parent.info.rigaattiva<(parent.info.maxlinehelp)) {parent.info.rigaattiva++; }    break;  // menu - help
    case 3 : case 4 : if (indicebandiera<4) {indicebandiera=indicebandiera+4;};                           break;  // scegli bandiera
   }
  }

  public void premutoRight(){
   switch (parent.subfaseMenu) {
    case 0 :          parent.IndiceMenu++;   parziall=0;  if (parent.IndiceMenu>parent.MenuChoise) {parent.IndiceMenu=1;};
               if (parent.justStarted & (parent.IndiceMenu==2)) {parent.IndiceMenu=3;};
               break;  // menu normale
    case 3 : case 4 : if (indicebandiera<7) {indicebandiera++;} else {indicebandiera=0;};                                  break;  // scegli bandiera
   }
  }

  public void premutoLeft(){
   switch (parent.subfaseMenu) {
    case 0 :          parent.IndiceMenu--;   parziall=0;  if (parent.IndiceMenu<=0) {parent.IndiceMenu=parent.MenuChoise;};
             if (parent.justStarted & (parent.IndiceMenu==2)) {parent.IndiceMenu=1;};
             break;  // menu normale
    case 3 : case 4 : if (indicebandiera>0) {indicebandiera--;} else {indicebandiera=7;};                                  break;  // scegli bandiera
   }
  }




  private String damminomesquadra(int ind){
   String value="";
      switch (ind) {
       case 0 : value="ITALIA";      break;
       case 1 : value="FRANCIA";     break;
       case 2 : value="GERMANIA";    break;
       case 3 : value="BRASILE";     break;
       case 4 : value="GRECIA";      break;
       case 5 : value="USA";         break;
       case 6 : value="RUSSIA";      break;
       case 7 : value="AUSTRALIA";   break;
      }
   return value;
  }


  private void diserb(Graphics g,int ind ,int nband){
     int ax1=nband*30;      int ax2=0; int ay2=0;
     switch(ind) {
      case 0: ax2=w/7;   ay2=h/4-10;    break;
      case 1: ax2=w/7;   ay2=h/4+20;    break;
      case 2: ax2=w/7;   ay2=h*3/4-20;  break;
      case 3: ax2=w/7;   ay2=h*3/4+10;  break;
      case 4: ax2=w*6/7; ay2=h/4-10;    break;
      case 5: ax2=w*6/7; ay2=h/4+20;    break;
      case 6: ax2=w*6/7; ay2=h*3/4-20;  break;
      case 7: ax2=w*6/7; ay2=h*3/4+10;  break;
     }
     if (ind<4)
      {g.drawRegion(Bandiere, ax1, 0,30,20,0,  ax2 ,ay2,    Graphics.TOP | Graphics.LEFT);} else
      {g.drawRegion(Bandiere, ax1, 0,30,20,0,  ax2 ,ay2,    Graphics.TOP | Graphics.RIGHT);};
  }

  private void diserb4(Graphics g,int ind ,int nband){
     int ax1=nband*30;      int ax2=0; int ay2=0;
     switch(ind) {
      case 0: ax2=w/7;   ay2=h/2-10;    break;
      case 1: ax2=w/7;   ay2=h/2+20;    break;
      case 2: ax2=w*6/7; ay2=h/2-10;    break;
      case 3: ax2=w*6/7; ay2=h/2+20;    break;
     }
     if (ind<2)
      {g.drawRegion(Bandiere, ax1, 0,30,20,0,  ax2 ,ay2,    Graphics.TOP | Graphics.LEFT);} else
      {g.drawRegion(Bandiere, ax1, 0,30,20,0,  ax2 ,ay2,    Graphics.TOP | Graphics.RIGHT);};
  }

  private void diserb2(Graphics g,int ind ,int nband){
     int ax1=nband*30;      int ax2=0; int ay2=0;
     switch(ind) {
      case 0: ax2=w/7;   ay2=h/2-10;    break;
      case 1: ax2=w*6/7; ay2=h/2-10;    break;
     }
     if (ind<1)
      {g.drawRegion(Bandiere, ax1, 0,30,20,0,  ax2 ,ay2,    Graphics.TOP | Graphics.LEFT);} else
      {g.drawRegion(Bandiere, ax1, 0,30,20,0,  ax2 ,ay2,    Graphics.TOP | Graphics.RIGHT);};
  }


  private void diserb1(Graphics g,int ind ,int nband){
     int ax1=nband*30;      int ax2=0; int ay2=0;
     switch(ind) {
      case 0: ax2=w/7;   ay2=h/2-10;    break;
      case 1: ax2=w*6/7; ay2=h/2-10;    break;
     }
     if (ind<2)
      {g.drawRegion(Bandiere, ax1, 0,30,20,0,  ax2 ,ay2,    Graphics.TOP | Graphics.LEFT);} else
      {g.drawRegion(Bandiere, ax1, 0,30,20,0,  ax2 ,ay2,    Graphics.TOP | Graphics.RIGHT);};
  }




  private void paintfasetorneosort(Graphics g){
    paintStringa(g, "TOURNAMENT"          , 0 ,w/2   , 10, false,2);
     for (int i=0; i<8; i++) {
      diserb(g,i,parent.info.SqTorneo[i]);
     }
  }


  private void paintfasetorneosemifinali(Graphics g){
    paintStringa(g, "TOURNAMENT"          , 0 ,w/2   , 10, false,2);
    paintStringa(g, "QUARTER"             , 0 ,w/2   , 40, false,2);
     for (int i=0; i<4; i++) {
      diserb4(g,i,parent.info.SqTorneo[i]);
     }
  }

  private void paintfasetorneofinali(Graphics g){
    paintStringa(g, "TOURNAMENT"          , 0 ,w/2   , 10, false,2);
    paintStringa(g, "FINAL"               , 0 ,w/2   , 40, false,2);
     for (int i=0; i<2; i++) { diserb2(g,i,parent.info.SqTorneo[i]);}
  }

  private void paintfasetorneofinito(Graphics g){
    paintStringa(g, "TOURNAMENT"          , 0 ,w/2   , 10, false,2);
    paintStringa(g, "YOU ARE WINNER"      , 0 ,w/2   , 40, false,2);
    g.drawRegion(Bandiere, parent.info.SqTorneo[0]*30, 0,30,20,0,  w/2,h/2,  Graphics.HCENTER | Graphics.VCENTER);
  }


  private void paintfasetorneo(Graphics g){
      int offb =((w/4)-30)/2;
      int posname=0;
     if (parent.subfaseMenu==4) {
       paintStringa(g, "FRIENDY"             , 0 ,w/2   , 10, false,2);
       paintStringa(g, "VS"                  , 0 ,w/2   , 25, false,2);
       if (Numsel==0) { posname=w/4;} else { posname=w*3/4;};
       if (Numsel==0) { tt=damminomesquadra(indicebandiera); paintStringa(g, tt, 0 ,posname   , 40, false,2); }
       if (Numsel==1) {
        tt=damminomesquadra(parent.pl1);            paintStringa(g, tt, 0 ,w/4       , 35, false,2);
        tt=damminomesquadra(indicebandiera);        paintStringa(g, tt, 0 ,posname   , 48, false,2); }
     }
     if (parent.subfaseMenu==3) {
       paintStringa(g, "TOURNAMENT"          , 0 ,w/2   , 10, false,2);
       posname=w/2;
       tt=damminomesquadra(indicebandiera);        paintStringa(g, tt, 0 ,posname   , 40, false,2);
     };

      g.drawRegion(Bandiere,   0, 0,30,20,0,        offb,h/2-20,Graphics.TOP | Graphics.LEFT);
      g.drawRegion(Bandiere,  30, 0,30,20,0,(w/4)  +offb,h/2-20,Graphics.TOP | Graphics.LEFT);
      g.drawRegion(Bandiere,  60, 0,30,20,0,(w/2)  +offb,h/2-20,Graphics.TOP | Graphics.LEFT);
      g.drawRegion(Bandiere,  90, 0,30,20,0,(w/4)*3+offb,h/2-20,Graphics.TOP | Graphics.LEFT);
      g.drawRegion(Bandiere, 120, 0,30,20,0,        offb,h/2+20,Graphics.TOP | Graphics.LEFT);
      g.drawRegion(Bandiere, 150, 0,30,20,0,(w/4)  +offb,h/2+20,Graphics.TOP | Graphics.LEFT);
      g.drawRegion(Bandiere, 180, 0,30,20,0,(w/2)  +offb,h/2+20,Graphics.TOP | Graphics.LEFT);
      g.drawRegion(Bandiere, 210, 0,30,20,0,(w/4)*3+offb,h/2+20,Graphics.TOP | Graphics.LEFT);

      g.setColor(255,255,0);
      g.setStrokeStyle(Graphics.SOLID);
      if (indicebandiera<4) { g.drawRect((w/4)*indicebandiera+offb-1,h/2-21,32,22); } else
                            { g.drawRect((w/4)*(indicebandiera-4)+offb-1,h/2+19,32,22); };

     paintStringa(g, "MENU"                , 0 ,w-6   , h-20, false,1);
     paintStringa(g, "SELECT"              , 0 ,10   , h-20, false,0);
  }


  private void paintfase0(Graphics g){
        parent.gInterface.squadra.palla.pallasottoContinue();
        g.drawImage(Titolo,w/2,45,Graphics.VCENTER | Graphics.HCENTER);
        g.drawImage(Friend ,w*3/4,(yriga2-39)+faseonda,Graphics.VCENTER | Graphics.HCENTER);
        g.drawImage(Tour   ,w/4  ,(yriga2-31)-faseonda,Graphics.VCENTER | Graphics.HCENTER);
       // ultimo parametro   alli 0 = Left    1 = Right   2 = Center
       // seconda riga menu note level help
        if (parent.Suono){ g.drawRegion(Menu2,60,0,20,20,0,(w/4)-6-faseonda ,(yriga2+2)-faseonda/2,Graphics.TOP | Graphics.LEFT);}
                    else { g.drawRegion(Menu2,40,0,20,20,0,(w/4)-6-faseonda ,(yriga2+2)-faseonda/2,Graphics.TOP | Graphics.LEFT);};
       // help
         g.drawRegion(Menu2,20,0,20,20,0,((w*3/4)-10)+faseonda ,(yriga2+2)-faseonda*3/4,Graphics.TOP | Graphics.LEFT);
         g.drawRegion(Menu2,0 ,0,20,20,0,(w/2)-10              ,(yriga2-2)+faseonda*3/4,Graphics.TOP | Graphics.LEFT);
        switch (parent.IndiceMenu) {
         case 1 : paintStringa(g, "TOURNA-"          , 0 ,w/4   , yriga2-52 , true,2);
                  paintStringa(g, "MENT"             , 0 ,w/4   , yriga2-36 , true,2); break;
         case 2 : paintStringa(g, "CONTINUE"         , 0 ,w/2   , yriga2-44 , true,2);break;
         case 3 : paintStringa(g, "FRIENDY"          , 0 ,w*3/4 , yriga2-44 , true,2);break;
         case 4 : paintStringa(g, "SOUND"          , 0 ,w/4     , yriga2-10 , true,2);
                  if (parent.Suono){ paintStringa(g, "ON" , 0 ,w/4 ,yriga2+5, true,2);}
                              else { paintStringa(g, "OFF", 0 ,w/4 ,yriga2+5, true,2);};  break;
         case 5 : paintStringa(g, "HELP"           , 0 ,w/2     , yriga2    , true,2);break;
         case 6 : paintStringa(g, "INFO"           , 0 ,w*3/4   , yriga2    , true,2);break;
         default : break;
        }
        paintStringa(g, "EXIT"   , 0 ,w-6 ,h-20 , false,1);
  }


  public void paintMenu(Graphics g){
    int locL; Layer L;


     parent.gInterface.squadra.palla.next();
     parent.gInterface.squadra.palla.mPalla.setVisible(true);

     if (inondasu)   {faseonda++;} else { faseonda--;};
     if (faseonda>8) {faseonda = 8; inondasu=false; };
     if (faseonda<0) {faseonda = 0; inondasu=true; };

     if (parent.infaseMenu) {
        locL=parent.mLayerManager.getSize();
        if (locL>4) {
         for ( int i=locL; i > 4 ; i--) {
          L = parent.mLayerManager.getLayerAt(0);
          parent.mLayerManager.remove(L);
         }
        }
        if  (parent.subfaseMenu!=0) { parent.gInterface.squadra.palla.visualizzapalla(false); };
        parent.mLayerManager.paint(g, 0, 0);

      if (parent.inTorneo & parent.gInterface.finepartita) {
       if (parent.gInterface.PuntiBianchi>=parent.gInterface.PuntiBlu) {
        parent.faseTorneo++;        parent.gInterface.finepartita=false;
        switch(parent.faseTorneo) {
         case 1: parent.subfaseMenu=10;      break; // semifinali
         case 2: parent.subfaseMenu=11;      break; // finale
         case 3: parent.subfaseMenu=12;      break; // finito perche' vinto
        }
       };
      }

      switch (parent.subfaseMenu) {
       case 0: paintfase0(g);                   break;  // menu standard
       case 1: parent.info.DisegnaInfo(g,w,h);  break;  // sono in fase info
       case 2: parent.info.paintfaseregole(g,w,h);  break;  // sono in fase regole
       case 3: paintfasetorneo(g);              break;  // sono in fase scegli per torneo
       case 4: paintfasetorneo(g);              break;  // sono in fase scegli per partita
       case 5: paintfasetorneosort(g);          break;  // sono in fase scegli per partita

       case 10: paintfasetorneosemifinali(g);   break;  // visualizza semifinali torneo
       case 11: paintfasetorneofinali(g);       break;  // visualizza finali     torneo
       case 12: paintfasetorneofinito(g);       break;  // visualizza finito     torneo
      }
     }
  }









  public void paintStringa(Graphics g , String st, int Big , int x1, int y1, boolean scorri,int alli) {
  // alli 0 = Left    1 = Right   2 = Center
  // Big =0 small  Big=1  BIG
    xs = x1; ys = y1;
    // con il sottoindcarattereDisplay si incrementa e al 3 o .. si passa a piu' caratteri da display
    // se scorri nfatti si propone solo parte della stringa
    ll = st.length();
    // parziall serve per la vista ritardata della scritta
    if (scorri) {if (parziall<ll) {ll=parziall; parziall++; }};

   switch (alli) {
    case 0 : xs = x1; break;
    case 1 : xs = x1-dimoffFS*ll; break;
    case 2 : xs = x1-dimoffFS*ll/2; break;
    default : break;
   }
    // se testo normale
    if (Big==0) {
      for (int i=0; i<ll; i++){
        offImg =Alfabeto.indexOf(st.charAt(i));
        if (offImg>=cra) { riga=dimFS;  offImg=offImg-cra;  colonna=offImg*dimFS; }
                 else  {   riga =0;     colonna = offImg*dimFS;  };
        if (offImg<cra) if (offImg>=0) {g.drawRegion(FonteS,colonna,riga,dimFS,dimFS,0,xs,ys,Graphics.TOP | Graphics.LEFT); }
        xs = xs + dimoffFS;
      }
    }
  }


  public void  azionemenuFriend(){
   if (Numsel==0){
     Numsel++; parent.pl1=indicebandiera;
               indicebandiera++; if (indicebandiera>7) {indicebandiera=0;};
   } else
   { parent.pl2=indicebandiera; parent.StartAmichevole();}
  }



  public void  azionemenuTorneo(){
   if (parent.subfaseMenu==3) {
    if (Numsel==0){ Numsel++; parent.pl1=indicebandiera;
    parent.pl2=7;
    parent.info.SqTorneo[0]=indicebandiera;
 // sorteggia torneo e visualizzalo;
     parent.info.sorteggiatorneo();
     parent.pl2=parent.info.SqTorneo[1];
     parent.subfaseMenu=5;
    };                                                        
   }
    else
   {
    parent.StartTorneo();
    Random m_r = new Random();
    int sort = (m_r.nextInt() % 2);  if (sort<0) {sort=-sort;};
    if (sort==0){ parent.info.SqTorneo[1]=parent.info.SqTorneo[2];}
      else      { parent.info.SqTorneo[1]=parent.info.SqTorneo[3];};
        sort = (m_r.nextInt() % 2);  if (sort<0) {sort=-sort;};
    if (sort==0){ parent.info.SqTorneo[2]=parent.info.SqTorneo[4];}
      else      { parent.info.SqTorneo[2]=parent.info.SqTorneo[5];};
        sort = (m_r.nextInt() % 2);  if (sort<0) {sort=-sort;};
    if (sort==0){ parent.info.SqTorneo[3]=parent.info.SqTorneo[6];}
      else      { parent.info.SqTorneo[3]=parent.info.SqTorneo[7];};
   }
  }


   public void  azionemenuTorneo4(){
    parent.pl1=parent.info.SqTorneo[0];
    parent.pl2=parent.info.SqTorneo[1];
    parent.StartPartitaTorneo();
    Random m_r = new Random();
    int sort = (m_r.nextInt() % 2);  if (sort<0) {sort=-sort;};
    if (sort==0){ parent.info.SqTorneo[1]=parent.info.SqTorneo[2];}
      else      { parent.info.SqTorneo[1]=parent.info.SqTorneo[3];};
   }

   public void  azionemenuTorneo2(){
    parent.pl1=parent.info.SqTorneo[0];
    parent.pl2=parent.info.SqTorneo[1];
    parent.StartPartitaTorneo();
   }

   public void  azionemenuTorneo1(){
    parent.subfaseMenu=0;
   }




  public void cambiaH(int newW,int newH){   h = newH;   w = newW;
    if (h>160) {yriga2=150;} else {yriga2=h*3/4;}
  }



}



