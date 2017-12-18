package Line5;


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.Sprite;


public class MainCanvas  extends Canvas implements Runnable  {  //
  private Line5            parent;
  private boolean          mTrucking=false;
  public  Interface        gInterface;
  private Graphics         g;
  private int              w;
  private int              h;
  private String           nomeimmagine1="/sfondo1.png";
  private String           nomeimmagine2="/sfondo2.png";

  private Image            imagesfondo1;
  public  Image            imagesfondo2;

  private long             tempoOra;
  private long             tempoIni;
  public  int              fasemenu=0;
  private final int        FASE1PAGINA  = 0;
  private final int        FASEMENU     = 1;
  private final int        FASEGIOCO    = 3;
  private final int        FASERECORDS  = 4;
  private final int        FASEINFO     = 5;
  private final int        FASEREGOLE   = 6;
  private boolean          stopped = false;


  public  Font             Title_font,Item_font,Item_fontB;
  private int              Itemselect=1;
  private int              MaxItemsMenu=7;
  public  boolean          Suono=false;
  public  Score            score;


// *******************   MAIN  **********************************
  public MainCanvas(Line5 parent)  throws IOException {
    setFullScreenMode(true);
    this.parent = parent;
    Title_font     = Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_BOLD,Font.SIZE_LARGE);
    w=getWidth();    h=getHeight();
    int hh = h-(Title_font.getHeight()+10);
    Item_font     = Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
    Item_fontB    = Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_BOLD,Font.SIZE_MEDIUM);
    if (hh<(Item_font.getHeight()*10)){
     Item_font     = Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_PLAIN,Font.SIZE_SMALL);
     Item_fontB    = Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_BOLD,Font.SIZE_SMALL);
    }
    imagesfondo1 = Image.createImage(nomeimmagine1);
    score= new Score(this);
   
  }


  public void postcreate(){
    try {
     imagesfondo2 = Image.createImage(nomeimmagine2);
     gInterface= new Interface(this,w,h);
    }  catch (IOException ioe) {  }
    score.load();
    gInterface.SuonaButW();
  }


  public void start() {
    mTrucking = true;
    Thread t = new Thread(this);
    t.start();
  }


  private void aspetta(){
    tempoOra = System.currentTimeMillis();
    while ( tempoOra-tempoIni < 100) {
     tempoOra = System.currentTimeMillis();
    }
    tempoIni = tempoOra;
  }




  public void run() {
    while (mTrucking) {
     if ((h!=getHeight()) | (w!=getWidth())) { h=getHeight();   w=getWidth(); gInterface.cambiaH(w,h);  }
     if (!isShown()) { if (Suono){ try {gInterface.player.stop();}  catch ( Exception e){};}; stopped=true; } else
                     { if (stopped) { if (Suono){ gInterface.SuonaButW(); } stopped=false;  };};
     if (stopped) {continue;};
     aspetta();
     repaint();
    } // mTrucking
  }


  private void showprimapagina(Graphics g) {
   g.setColor(90,132,214);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.setColor(0,0,0);
   g.drawImage(imagesfondo1,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setColor(0,0,0);
   g.drawString("(c) Copyright 2005"   , 6, getHeight()-32,Graphics.BASELINE|Graphics.LEFT);
   g.drawString("carlomacor.com"       , 6, getHeight()-12,Graphics.BASELINE|Graphics.LEFT);
  }

  private void showInfo(Graphics g) {
   int starty=50; int offy=15;
   if (h<170){starty=40; offy=12;}
   g.setColor(0,0,0);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.drawImage(imagesfondo2,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setColor(255,255,255);
   g.setFont(Title_font);
    g.drawString("INFO"                 , getWidth()/2 , 22 ,Graphics.BASELINE|Graphics.HCENTER);
   g.setFont(Item_fontB);
    g.drawString("(c) Copyright 2006"   , getWidth()/2,  starty        ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("carlomacor.com"       , getWidth()/2,  starty+offy   ,Graphics.BASELINE|Graphics.HCENTER);
  }

  private void showRegole(Graphics g) {
   int offy=15;   int starty=40;
   if (h<170){starty=30; offy=13;}
   g.setColor(0,0,0);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.drawImage(imagesfondo2,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setColor(255,255,255);
   g.setFont(Title_font);
    g.drawString("REGOLE"                    , getWidth()/2 , 22 ,Graphics.BASELINE|Graphics.HCENTER);
   g.setFont(Item_font);
    g.drawString("Inserisci o cambia una"    , getWidth()/2, starty+offy   ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("pedina. Quando sono 5 "     , getWidth()/2, starty+offy*2 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("allineate fai punti"         , getWidth()/2, starty+offy*3 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("e crei nuovo spazio."         , getWidth()/2, starty+offy*4 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Ad ogni mossa arrivano"   , getWidth()/2, starty+offy*5 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("tre nuove pedine, non"      , getWidth()/2, starty+offy*6 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("devi riempire la tavola!"        , getWidth()/2, starty+offy*7 ,Graphics.BASELINE|Graphics.HCENTER);
  }



  private void showtitolimenu(Graphics g) {
   int offxtit=12; int baseoffxtit=12;  int offytit=18;   int startytit=52;
   if (h<170) {startytit=42; offytit=15;}

   baseoffxtit=(w-imagesfondo2.getWidth())/2;
   if (baseoffxtit<0) {baseoffxtit=0;};

   offxtit=12+baseoffxtit;

   g.setColor(255,255,255);
   g.setFont(Title_font);   g.drawString("MENU"            , getWidth()/2 , 22,Graphics.BASELINE|Graphics.HCENTER);
   g.setFont(Item_font);
   if (Itemselect==1) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
      g.drawString("Nuova Partita"   , offxtit           , startytit,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==1) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==2) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
      g.drawString("Continua"        , offxtit           , startytit+offytit,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==2) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==3) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
      if (Suono) { g.drawString("Sound On"    , offxtit, startytit+offytit*2,Graphics.BASELINE|Graphics.LEFT);} else
                 { g.drawString("Sound Off"   , offxtit, startytit+offytit*2,Graphics.BASELINE|Graphics.LEFT);};
   if (Itemselect==3) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==4) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
      g.drawString("Records"          , offxtit          , startytit+offytit*3,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==4) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==5) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
      g.drawString("Regole"          , offxtit          , startytit+offytit*4,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==5) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==6) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
      g.drawString("Info"            , offxtit          , startytit+offytit*5,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==6) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==7) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
      g.drawString("Exit"            , offxtit          , startytit+offytit*6,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==7) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
  }

  private void showpaginamenu(Graphics g) {
   g.setColor(0,0,0);
   g.setFont(Item_font);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.drawImage(imagesfondo2,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setFont(Item_fontB);
  }

  private void showRecords(Graphics g) {
   int offy=13;   int starty=35;
   String tt;        long aa;
   g.setColor(255,255,255);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.drawImage(imagesfondo2,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setFont(Title_font);
    g.drawString("RECORDS"             , getWidth()/2 , 18 ,Graphics.BASELINE|Graphics.HCENTER);
   g.setFont(Item_font);
   for (int i=0; i<10; i++) {
    g.setColor(255,255,255);
    if (i==score.justintrodotto) { g.setColor(255,0,0);};
    tt=""+(i+1);                g.drawString(tt ,   40, starty+offy*i   ,Graphics.BASELINE|Graphics.LEFT);
    tt=""+score.recordG[i];     g.drawString(tt , w-30, starty+offy*i   ,Graphics.BASELINE|Graphics.RIGHT);
   }
  }



  public void paint(Graphics g)  {
   switch(fasemenu) {
    case FASE1PAGINA  : showprimapagina(g);  fasemenu=FASEMENU;   break;
    case FASEMENU     : showpaginamenu(g); showtitolimenu(g);     break;
    case FASEINFO     : showInfo(g);                              break;
    case FASEREGOLE   : showRegole(g);                            break;
    case FASERECORDS  : showRecords(g);                           break;
    case FASEGIOCO    : gInterface.paintInterface(g);             break;
   }
  }

  public void stop() {
  }





  private void escigioco(){
    parent.destroyApp(true); parent.notifyDestroyed();
  }


  protected void keyPressed(int keycode){
   int gameAction = getGameAction(keycode);
   switch ( gameAction) {
    case UP    : azione( 0, 1, 0);  break;
    case DOWN  : azione( 0,-1, 0);  break;
    case LEFT  : azione(-1, 0, 0);  break;
    case RIGHT : azione( 1, 0, 0);  break;
    case FIRE  : azione( 0, 0, 1);  break;
   }

   if (keycode ==  - 7) { tasto7(); };
   if (keycode ==  -22) { tasto7(); };
  }



  private void tasto7(){
   switch(fasemenu) {
    case FASEMENU     : escigioco();         break;
    case FASEGIOCO    : if (score.justintrodotto>=0) {fasemenu=FASERECORDS; } else fasemenu=FASEMENU;  break;
    case FASEINFO     : fasemenu=FASEMENU;   break;
    case FASEREGOLE   : fasemenu=FASEMENU;   break;
    case FASERECORDS  : fasemenu=FASEMENU;   score.justintrodotto=-1; break;
   }
  }



  private void azione(int x,int y,int z) {
   switch(fasemenu) {
    case FASEMENU :
     if (z>0){
      switch(Itemselect) {
       case 1 :  gInterface.nuovapartita(); fasemenu=FASEGIOCO; break;
       case 2 :  fasemenu=FASEGIOCO; break;
       case 3 :  Suono=!Suono;  score.save();
              if (!Suono){ try {gInterface.player.stop();}  catch ( Exception e){};};
              if (Suono) { try {gInterface.player.setMediaTime(0); gInterface.player.start();}  catch ( Exception e){};};
       break;
       case 4 : fasemenu=FASERECORDS;    break;
       case 5 : fasemenu=FASEREGOLE;    break;
       case 6 : fasemenu=FASEINFO;      break;
       case 7 : escigioco();            break;
      }
     };
     if (y<0){ Itemselect++; if (Itemselect>MaxItemsMenu){Itemselect=1;}   };
     if (y>0){ Itemselect--; if (Itemselect<1){Itemselect=MaxItemsMenu;}   };
    break;
    case FASEINFO   :      if (z>0){ fasemenu=FASEMENU;}    break;
    case FASEREGOLE :      if (z>0){ fasemenu=FASEMENU;}    break;
    case FASEGIOCO  :      gInterface.azione(x,y,z);        break;
    case FASERECORDS:      if (z>0){ fasemenu=FASEMENU;}    break;
   }
  }



}

