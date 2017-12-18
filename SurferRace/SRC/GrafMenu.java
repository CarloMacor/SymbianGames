package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //


//   public GrafMenu(MainCanvas parent,int w, int h, WindserfMIDlet parentWind  ) throws IOException 
//  public void paintMenu(Graphics g)
//  public void paintStringa(Graphics g , String st, int Big , int x1, int y1, boolean scorri,int alli)
//  public void numeroPtschermo(Graphics g ,int value,int offy, int x1,int y1 )
//  public void cambiaH(int newH)
//  public void premutoPuntatore(int x, int y)
//  public void DraggedPuntatore(int x, int y)
//  public void lasciatoPuntatore(int x, int y)
//  private int dammiIndmenuPressed(int x, int y)



import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.String;
import javax.microedition.lcdui.game.Sprite;


public class GrafMenu {
  private  Image Titolo;
  private  Image FonteS;
  private  Image Note;
  private  Image TxtMenu;
  private  int   w;
  private  int   h;
  private  int   dimFS, dimoffFS;
  public   int   parziall=0;

  private String Alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
  public   int   last1riga = 2;
  public   int   last2riga = 5;
  private  int   rigf=0;
  private  int   colf=0;
  private  int    Lbar=0;
  private  String tt = "";
  private  int xs;
  private  int ys ;
  private  int ll;
  private  int offImg =0;
  private  int riga    =0;
  private  int colonna = 0;
  private  char locC;
  private  int cra = 18;

  private String TxtNumer = "";
  private  String hhh;
  public   long deltaTimeRace1000;
  private  long centisecondi;

  public   long contaftp   ;
  private  int xenterd =0;
  private  int yenterd =0;
  private  boolean premutoPP= false;

  private MainCanvas parent;
  public  WindserfMIDlet parentW;
  public  int        hriga2=0;



   public GrafMenu(MainCanvas parent,int w, int h, WindserfMIDlet parentWind  ) throws IOException {
    this.parent = parent;   this.w = w;     this.h = h;
    parentW = parentWind;
    Titolo  = Image.createImage("/titolo.png");
    FonteS  = Image.createImage("/Caratteri.png");
    Note    = Image.createImage("/note.png");
    TxtMenu = Image.createImage("/txtmenu.png");
    dimFS   = FonteS.getHeight()/2;
    dimoffFS = dimFS-3;
    contaftp=System.currentTimeMillis();

    if (h>=160) {hriga2=140;} else {hriga2=h-10;}
   }


  public void paintMenu(Graphics g){
    if (parent.conPointer & !parent.infaseMenu) {
      paintStringa(g, "MENU"       , 0 ,w-4 ,  2, true,1);
      g.setColor(255,212,18);
      g.setStrokeStyle(Graphics.DOTTED);
       g.drawLine(w-50  ,2 ,w-2   ,2);
       g.drawLine(w-2   ,2 ,w-2   ,17);
       g.drawLine(w-2   ,17,w-50  ,17);
       g.drawLine(w-50  ,17,w-50  ,2);
       g.drawLine(w/4-17  ,h-22 ,w/4+32  ,h-22);
       g.drawLine(w/4+32  ,h-22 ,w/4+32  ,h-7);
       g.drawLine(w/4+32  ,h-7,w/4-17    ,h-7);
       g.drawLine(w/4-17  ,h-7,w/4-17    ,h-22);
      g.setStrokeStyle(Graphics.SOLID);
    }
    if ((parent.FinitoGiocoBox) & (!parent.infaseMenu)) {
     paintStringa(g, "GAME  OVER"       , 0 ,w/2 ,  35, true,2);
     paintStringa(g, "YOU GOT"       , 0 ,w/2 ,  70, true,2);
     TxtNumer = parent.PuntiBox +"  BOXES";
     if (parent.PuntiBox == 0)  {TxtNumer = "NONE  BOX";}  ;
     paintStringa(g, TxtNumer       , 0 ,w/2 ,  90, true,2);
     if (parent.isRecord) { TxtNumer = "NEW RECORD";  paintStringa(g, TxtNumer       , 0 ,w/2 ,  120, true,2); }
       else      {
        int valrec =0;
        switch (parent.modalita) {
         case 1 : valrec=parent.gInterface.recordG1E; break;
         case 2 : valrec=parent.gInterface.recordG1M; break;
         case 3 : valrec=parent.gInterface.recordG1H; break;
        }
        TxtNumer = "RECORD IS "+valrec ;  paintStringa(g, TxtNumer       , 0 ,w/2 ,  120, true,2);
     }


    }
// Fine Barra time gioco Box
    if ((parent.FinitoGiocoRace) & (!parent.infaseMenu)) {
     paintStringa(g, "GAME  OVER"       , 0 ,w/2 ,  25, true,2);
     if (parent.serfer1.primoarrivato) { paintStringa(g, "YOU WIN"  , 0 ,w/2 ,  50, true,2);};
     paintStringa(g, "YOU FINISHED"       , 0 ,w/2 ,  75, true,2);
     TxtNumer = "IN " + (parent.deltaTimeRace/1000) + " SEC";
     paintStringa(g, TxtNumer       , 0 ,w/2 ,  95, true,2);
     if (parent.isRecord) { TxtNumer = "NEW RECORD";  paintStringa(g, TxtNumer       , 0 ,w/2 ,  130, true,2); }
       else      {
        long valrecS =0;
        switch (parent.modalita) {
         case 1 : valrecS=parent.gInterface.recordG2E/10; break;
         case 2 : valrecS=parent.gInterface.recordG2M/10; break;
         case 3 : valrecS=parent.gInterface.recordG2H/10; break;
        }
        TxtNumer = "RECORD IS "+valrecS ;  paintStringa(g, TxtNumer       , 0 ,w/2 ,  130, true,2);
     }
    }

    if ((parent.FinitoGiocoShark) & (!parent.infaseMenu)) {
     paintStringa(g, "GAME  OVER"       , 0 ,w/2 ,  35, true,2);
     paintStringa(g, "THE SHARK"            , 0 ,w/2 ,  70, true,2);
     paintStringa(g, "GOT  YOU"        , 0 ,w/2 ,  90, true,2);
     TxtNumer = "IN " + parent.TempoTotShark/1000 + " SEC" ;
     paintStringa(g, TxtNumer       , 0 ,w/2 ,  110, true,2);
     if (parent.isRecord) { TxtNumer = "NEW RECORD";  paintStringa(g, TxtNumer       , 0 ,w/2 ,  140, true,2); }
       else      {
        long valrecS =0;
        switch (parent.modalita) {
         case 1 : valrecS=parent.gInterface.recordG3E/10; break;
         case 2 : valrecS=parent.gInterface.recordG3M/10; break;
         case 3 : valrecS=parent.gInterface.recordG3H/10; break;
        }
        TxtNumer = "RECORD IS "+valrecS ;  paintStringa(g, TxtNumer       , 0 ,w/2 ,  140, true,2);
     }
    }


// Help Turn !
     if ((parent.serfer1.LampeggioTurn) & (!parent.infaseMenu)) { paintStringa(g, "TURN"       , 0 ,w/4+5 , h-22, true,2); };
//        framesec=contaframe;
          parent.contaframe++;
          if  (parent.contaframe++>=100) {
           parent.contaframe=0;
           contaftp=(System.currentTimeMillis()-contaftp);
           if (contaftp>0) {
            parent.framesec= (100000/contaftp);
           }
           contaftp=System.currentTimeMillis();
          }


// se in Race indica i secondo passati
    if (( parent.GiocoInCorso==2) & (!parent.infaseMenu)) {
      if (!parent.FinitoGiocoRace) {
       parent.deltaTimeRace = parent.tempoOra-parent.TempoiniRace;
       centisecondi  = (parent.deltaTimeRace%1000)/100;
       deltaTimeRace1000 = parent.deltaTimeRace/1000;
       hhh = ""+deltaTimeRace1000+"'"+ centisecondi;
       paintStringa(g, hhh              , 0 ,    6 , 12 , true,0);
      }

    }

// se in Shark indica i secondo passati
    if (( parent.GiocoInCorso==3) & (!parent.infaseMenu)  & (!parent.FinitoGiocoShark) ) {
      centisecondi  = (parent.TempoTotShark%1000)/100;
      deltaTimeRace1000 = parent.TempoTotShark/1000;
      hhh = ""+deltaTimeRace1000+"'"+ centisecondi;
        paintStringa(g, hhh              , 0 ,    6 , 12 , true,0);
    }


   if ((parent.fototimeinvisibileBox1>0) & (!parent.infaseMenu))  {
     numeroPtschermo(g,parent.PuntiBox,parent.timeinvisibileFix-parent.fototimeinvisibileBox1, parent.box1.mBox.getX(), parent.box1.mBox.getY());
    };


// in fase menu inizio
    if (parent.infaseMenu) {

     if (!parent.infaseInfo){
     // Titolo
      parent.imponisqualo_surfer();
      g.drawImage(Titolo,w/2,40,Graphics.VCENTER | Graphics.HCENTER);
     // ultimo parametro   alli 0 = Left    1 = Right   2 = Center
     // seconda riga menu note level help
      if (parent.Suono){ g.drawRegion(Note,20,0,20,20,0,(w/4)-10 ,hriga2,Graphics.TOP | Graphics.LEFT);}
                  else { g.drawRegion(Note, 0,0,20,20,0,(w/4)-10 ,hriga2,Graphics.TOP | Graphics.LEFT);};


      if (parent.conPointer) {
       //DD
       g.setColor(255,212,18);
       g.setStrokeStyle(Graphics.DOTTED);
       //  Box
       g.drawLine(w/4-15,hriga2-45 ,w/4+15,hriga2-45);
       g.drawLine(w/4+15,hriga2-45 ,w/4+15,hriga2-15);
       g.drawLine(w/4+15,hriga2-15 ,w/4-15,hriga2-15);
       g.drawLine(w/4-15,hriga2-15 ,w/4-15,hriga2-45);
       //  Serfer
       g.drawLine(w/2-15,100-5 ,w/2+15,100-5);
       g.drawLine(w/2+15,100-5 ,w/2+15,100+25);
       g.drawLine(w/2+15,100+25,w/2-15,100+25);
       g.drawLine(w/2-15,100+25,w/2-15,100-5);
       //  Shark
       g.drawLine(w*3/4-15,100-5 ,w*3/4+15,100-5);
       g.drawLine(w*3/4+15,100-5 ,w*3/4+15,100+25);
       g.drawLine(w*3/4+15,100+25,w*3/4-15,100+25);
       g.drawLine(w*3/4-15,100+25,w*3/4-15,100-5);
       // LE NOTE
       g.drawLine(w/4-15,150-15,w/4+15,150-15);
       g.drawLine(w/4+15,150-15,w/4+15,150+15);
       g.drawLine(w/4+15,150+15,w/4-15,150+15);
       g.drawLine(w/4-15,150+15,w/4-15,150-15);
       // E M H
       g.drawLine(w/2-15,150-15,w/2+15,150-15);
       g.drawLine(w/2+15,150-15,w/2+15,150+15);
       g.drawLine(w/2+15,150+15,w/2-15,150+15);
       g.drawLine(w/2-15,150+15,w/2-15,150-15);
       // ?
       g.drawLine(w*3/4-15,150-15,w*3/4+15,150-15);
       g.drawLine(w*3/4+15,150-15,w*3/4+15,150+15);
       g.drawLine(w*3/4+15,150+15,w*3/4-15,150+15);
       g.drawLine(w*3/4-15,150+15,w*3/4-15,150-15);

       g.drawLine(w-56 ,h-20 ,w-6  ,h-20);
       g.drawLine(w-6  ,h-20 ,w-6  ,h-5);
       g.drawLine(w-6  ,h-5  ,w-56 ,h-5);
       g.drawLine(w-56 ,h-5  ,w-56 ,h-20);

       if (parent.IniziatoGioco & (parent.oldmodalita==parent.modalita)) {
        g.drawLine(w*3/4-45  ,70 ,w*3/4+45 ,70);
        g.drawLine(w*3/4+45  ,70 ,w*3/4+45 ,85);
        g.drawLine(w*3/4+45  ,85 ,w*3/4-45 ,85);
        g.drawLine(w*3/4-45  ,85 ,w*3/4-45 ,70);
       }
        g.setStrokeStyle(Graphics.SOLID);
        paintStringa(g, "EXIT"   , 0 ,w-6 ,h-20 , false,1);
      }


      switch (parent.modalita) {
       case 1 : g.drawRegion(TxtMenu,0 ,0,20,20,0,(w/2)-10 ,hriga2,Graphics.TOP | Graphics.LEFT); break;
       case 2 : g.drawRegion(TxtMenu,60,0,20,20,0,(w/2)-10 ,hriga2,Graphics.TOP | Graphics.LEFT); break;
       case 3 : g.drawRegion(TxtMenu,20,0,20,20,0,(w/2)-10 ,hriga2,Graphics.TOP | Graphics.LEFT); break;
      }
     // help
       g.drawRegion(TxtMenu,40,0,20,20,0,(w*3/4)-10 ,hriga2,Graphics.TOP | Graphics.LEFT);



      switch (parent.IndiceMenu) {
       case 1 : paintStringa(g, "CONTINUE"          , 0 ,w*3/4 ,  70 , true,2); break;
       case 2 : paintStringa(g, "BOXES"             , 0 ,w/4   , hriga2-50 , true,2); break;
       case 3 : paintStringa(g, "RACE"              , 0 ,w/2   , hriga2-50 , true,2);break;
       case 4 : paintStringa(g, "SHARK"             , 0 ,w*3/4 , hriga2-50 , true,2);
                paintStringa(g, "ATTACK"            , 0 ,w*3/4 , hriga2-30 , true,2);break;
       case 5 : paintStringa(g, "SOUND"             , 0 ,w/4   , hriga2-10 , true,2);
                if (parent.Suono){ paintStringa(g, "ON" , 0 ,w/4 ,hriga2+10, true,2);}
                            else { paintStringa(g, "OFF", 0 ,w/4 ,hriga2+10, true,2);};  break;
       case 6 : switch (parent.modalita) {
                  case 1 : paintStringa(g, "EASY"   , 0 ,w/2   ,hriga2-10  , true,2);break;
                  case 2 : paintStringa(g, "MEDIUM" , 0 ,w/2   ,hriga2-10  , true,2);break;
                  case 3 : paintStringa(g, "HARD"   , 0 ,w/2   ,hriga2-10  , true,2);break;
                }
               break;
       case 7 : paintStringa(g, "HELP"              , 0 ,w*3/4 ,hriga2-10  , true,2);break;
       case 8 : paintStringa(g, "EXIT"              , 0 ,w-6   ,h-20 , true,1);break;
       default : break;
      }

     }
    }
// in fase menu fine
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

    // se Piccoli
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


  public void numeroPtschermo(Graphics g ,int value,int offy, int x1,int y1 ){
    if (( parent.GiocoInCorso==1) & (!parent.infaseMenu)) {
     tt = ""+value;
     paintStringa(g ,tt, 0 , x1, y1-offy*2, false,0);
    }
  };

  public void cambiaH(int newH){
   h = newH;
   if (h>=160) {hriga2=140;} else {hriga2=h-10;}
  }




  public void premutoPuntatore(int x, int y) {
   int oldindiceM = parent.IndiceMenu;
   premutoPP=true;
   if (parent.infaseMenu) {
    parent.IndiceMenu = dammiIndmenuPressed(x,y);
    if (oldindiceM!=parent.IndiceMenu) { parziall=0; };
   }
   else
   {
     if ((x>=w-50) & (x<=w)) { if ((y>= 2) & (y<=17))  {  parent.RiattivaFaseMenu(); premutoPP=false;  } }
     if ((x>=w/4-17) & (x<=w/4+32)) { if ((y>=h-22) & (y<=h-7))  {  parent.serfer1.Controllo(2);} }
   }
   xenterd = x;  yenterd = y;
  }

  public void DraggedPuntatore(int x, int y) {
   int oldindiceM = parent.IndiceMenu;
   if (parent.infaseMenu) {
    if (premutoPP) {
     parent.IndiceMenu = dammiIndmenuPressed(x,y);
     if (oldindiceM!=parent.IndiceMenu) { parziall=0; };
    }
   }
   else { if ((x>=w-50) & (x<=w)) { if ((y>= 2) & (y<=17)) {  if (premutoPP) { parent.RiattivaFaseMenu(); premutoPP=false; } } } }
  }



  public void lasciatoPuntatore(int x, int y) {
   int newIndiceM =  dammiIndmenuPressed(x,y);
   if (parent.infaseMenu) { if (newIndiceM>0) {parent.azioneFire(newIndiceM);};
   } else
   {
   if ( (x>=w-50) & (x<=w) & (y>= 2) & (y<=17) )
     {
     if (premutoPP) { parent.RiattivaFaseMenu();} }
     else
     {
      if (x>xenterd) { parent.serfer1.Controllo(1); };
      if (x<xenterd) { parent.serfer1.Controllo(3); };
     }
   };
   premutoPP=false;

   if (newIndiceM==10)  { parent.AttivaMenudaInfo();};
   if (newIndiceM==11)  { parent.gInfo.rigaattiva++; if (parent.gInfo.rigaattiva>parent.maxlinehelp)
                                                        {parent.gInfo.rigaattiva=parent.maxlinehelp;}; }
   if (newIndiceM==12)  { parent.gInfo.rigaattiva--; if (parent.gInfo.rigaattiva<0) {parent.gInfo.rigaattiva=0;}; };

  }

 private int dammiIndmenuPressed(int x, int y) {
  int value = 0;
   if (parent.infaseInfo) {
     if ((x>=w/2) & (y>h-40)) {value = 10;}

     if ((x<30) & (y>h/2)) {value = 11;}
     if ((x<30) & (y<h/2)) {value = 12;}

   }
    else
   {

    if ((x>=w/4-15) & (x<=w/4+15)) {
     if ((y>= 95) & (y<=125)) { value = 2;  }    // box
     if ((y>=135) & (y<=165)) { value = 5;  }    // note
    }
    if ((x>=w/2-15) & (x<=w/2+15)) {
     if ((y>= 95) & (y<=125)) { value = 3;  }    // race
     if ((y>=135) & (y<=165)) { value = 6;  }    // EMH
    }
    if ((x>=w*3/4-15) & (x<=w*3/4+15)) {
     if ((y>= 95) & (y<=125)) { value = 4;  }    // shark
     if ((y>=135) & (y<=165)) { value = 7;  }    // ?
    }
    if ((x>=w-56) & (x<=w)) {
     if ((y>= (h-30)) & (y<=(h-5))) { value = 8;  }    // Exit
    }
    if (parent.IniziatoGioco & (parent.oldmodalita==parent.modalita)) {
     if ((x>=w*3/4-45) & (x<=w*3/4+45)) {
      if ((y>= 70) & (y<=85)) { value = 1;  }    // continue
     }
    }
   }
  return value;
 }





}



