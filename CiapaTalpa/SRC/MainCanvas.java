package CiapaTalpa;


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.Sprite;


public class MainCanvas  extends Canvas implements Runnable  {  //
  private boolean          versioneItaliana=true;


  private CiapaTalpa       parent;
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
  private final int        FASEINFO     = 4;
  private final int        FASEREGOLE   = 5;
  private final int        FASERECORDS  = 6;

  private boolean          stopped = false;
  public  Score            score;

  public  Font             Title_font,Item_font,Item_fontB;
  private int              Itemselect=1;
  private int              MaxItemsMenu=8;
  public  boolean          Suono=false;
  public  int              difficolta=2;



// *******************   MAIN  **********************************
  public MainCanvas(CiapaTalpa parent)  throws IOException {
    setFullScreenMode(true);
    this.parent = parent;
    score= new Score(this);
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
    while ( tempoOra-tempoIni < 200) {
     tempoOra = System.currentTimeMillis();
    }
    tempoIni = tempoOra;
  }




  public void run() {
    gInterface.nuovapartita();
    while (mTrucking) {
     if ((h!=getHeight()) | (w!=getWidth())) { h=getHeight();        w=getWidth(); gInterface.cambiaH(w,h);  }
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
   g.setColor(255,255,255);
   g.drawString("(c) Copyright 2005"   , 6, getHeight()-26,Graphics.BASELINE|Graphics.LEFT);
   g.drawString("carlomacor.com"       , 6, getHeight()-10,Graphics.BASELINE|Graphics.LEFT);
  }

  private void showInfo(Graphics g) {
   int starty=50; int offy=15;
   if (h<170){starty=40; offy=12;}
   g.setColor(0,0,0);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.drawImage(imagesfondo2,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
//   g.setColor(255,255,255);
   g.setFont(Title_font);
    g.drawString("INFO"                 , getWidth()/2 , 22 ,Graphics.BASELINE|Graphics.HCENTER);
   g.setFont(Item_fontB);
    g.drawString("(c) Copyright 2005"   , getWidth()/2,  starty        ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("carlomacor.com"       , getWidth()/2,  starty+offy   ,Graphics.BASELINE|Graphics.HCENTER);
   if (versioneItaliana)
    { g.drawString("Grafica"              , getWidth()/2,  starty+offy*3 ,Graphics.BASELINE|Graphics.HCENTER);}
    else { g.drawString("Graphics"        , getWidth()/2,  starty+offy*3 ,Graphics.BASELINE|Graphics.HCENTER);}
    g.drawString("Daniele Pintene"      , getWidth()/2,  starty+offy*4 ,Graphics.BASELINE|Graphics.HCENTER);
    if (versioneItaliana)
    { g.drawString("Musica"               , getWidth()/2,  starty+offy*6 ,Graphics.BASELINE|Graphics.HCENTER);}
    else
    { g.drawString("Music"                , getWidth()/2,  starty+offy*6 ,Graphics.BASELINE|Graphics.HCENTER);}
    g.drawString("Paolo Mastrandrea"    , getWidth()/2,  starty+offy*7 ,Graphics.BASELINE|Graphics.HCENTER);
  }

  private void showRegole(Graphics g) {
   int offy=15;   int starty=35;
   if (h<170){starty=25; offy=13;}
   g.setColor(0,0,0);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.drawImage(imagesfondo2,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setFont(Title_font);
   if (versioneItaliana)
    {
    g.drawString("REGOLE"                    , getWidth()/2 , 18 ,Graphics.BASELINE|Graphics.HCENTER);
    g.setFont(Item_fontB);
    g.drawString("Acchiappa la Talpa."          , getWidth()/2, starty+offy   ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Con il martello colpisci"     , getWidth()/2, starty+offy*2 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("la talpa che sbuca dalle"     , getWidth()/2, starty+offy*3 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("buche. Con i cursori"         , getWidth()/2, starty+offy*4 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("punta il martello."           , getWidth()/2, starty+offy*5 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Seleziona per colpire."       , getWidth()/2, starty+offy*6 ,Graphics.BASELINE|Graphics.HCENTER);
    }
    else
    {
    g.drawString("RULES"                    , getWidth()/2 , 18 ,Graphics.BASELINE|Graphics.HCENTER);
    g.setFont(Item_fontB);
    g.drawString("Acchiappa la Talpa."          , getWidth()/2, starty+offy   ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Use the Hammer"     , getWidth()/2, starty+offy*2 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("to get the Talpa"     , getWidth()/2, starty+offy*3 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Use cursors to "         , getWidth()/2, starty+offy*4 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("point the hammer."           , getWidth()/2, starty+offy*5 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Select to shot."       , getWidth()/2, starty+offy*6 ,Graphics.BASELINE|Graphics.HCENTER);
    }
  }


  private void showRecords(Graphics g) {
   int offy=12;   int starty=35;
   String tt;        long aa;
   g.setColor(0,0,0);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.drawImage(imagesfondo2,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setFont(Title_font);
    g.drawString("RECORDS"             , getWidth()/2 , 18 ,Graphics.BASELINE|Graphics.HCENTER);
   g.setFont(Item_font);
   for (int i=0; i<10; i++) {
    g.setColor(0,0,0);
    if (i==score.justintrodotto) { g.setColor(255,0,0);};
    tt=""+(i+1);                g.drawString(tt ,   40, starty+offy*i   ,Graphics.BASELINE|Graphics.LEFT);
    tt=""+score.recordG[i];     g.drawString(tt , w-30, starty+offy*i   ,Graphics.BASELINE|Graphics.RIGHT);
   }
  }





  private void showtitolimenu(Graphics g) {
   int offxtit=52; int baseoffxtit=12;  int offytit=15;   int startytit=52;
   int r1,g1,b1;
   int r2,g2,b2;

   if (h<170) {startytit=42; offytit=15;}
   baseoffxtit=(w-imagesfondo2.getWidth())/2;
   if (baseoffxtit<0) {baseoffxtit=0;};
   g.setFont(Item_fontB);

   offxtit=32+baseoffxtit;

   r1=0;     g1=0;     b1=0;
   r2=70;    g2=0;     b2=255;
   g.setColor(r1,g1,b1);

   g.setFont(Title_font);   g.drawString("MENU"            , getWidth()/2 , 22,Graphics.BASELINE|Graphics.HCENTER);
   g.setFont(Item_fontB);
   if (Itemselect==1) {offxtit=42+baseoffxtit; g.setColor(r2,g2,b2); };
   if (versioneItaliana)
         { g.drawString("Nuova Partita"   , offxtit           , startytit,Graphics.BASELINE|Graphics.LEFT);}
    else { g.drawString("New Game"   , offxtit           , startytit,Graphics.BASELINE|Graphics.LEFT);}
   if (Itemselect==1) {offxtit=32+baseoffxtit; g.setColor(r1,g1,b1); };
   if (Itemselect==2) {offxtit=42+baseoffxtit; g.setColor(r2,g2,b2);};
   if (versioneItaliana)
         { g.drawString("Continua"        , offxtit           , startytit+offytit,Graphics.BASELINE|Graphics.LEFT);}
    else { g.drawString("Continue"        , offxtit           , startytit+offytit,Graphics.BASELINE|Graphics.LEFT);}
   if (Itemselect==2) {offxtit=32+baseoffxtit; g.setColor(r1,g1,b1); };
   if (Itemselect==3) {offxtit=42+baseoffxtit; g.setColor(r2,g2,b2);};
      if (Suono) { g.drawString("Sound On"    , offxtit, startytit+offytit*2,Graphics.BASELINE|Graphics.LEFT);} else
                 { g.drawString("Sound Off"   , offxtit, startytit+offytit*2,Graphics.BASELINE|Graphics.LEFT);};
   if (Itemselect==3) {offxtit=32+baseoffxtit; g.setColor(r1,g1,b1); };
   if (Itemselect==4) {offxtit=42+baseoffxtit; g.setColor(r2,g2,b2);};
   if (versioneItaliana){
     switch (difficolta) {
      case 1 : g.drawString("Facile"          , offxtit          , startytit+offytit*3,Graphics.BASELINE|Graphics.LEFT); break;
      case 2 : g.drawString("Medio"           , offxtit          , startytit+offytit*3,Graphics.BASELINE|Graphics.LEFT); break;
      case 3 : g.drawString("Difficile"       , offxtit          , startytit+offytit*3,Graphics.BASELINE|Graphics.LEFT); break;
     }}
     else
     {
      switch (difficolta) {
      case 1 : g.drawString("Easy"          , offxtit          , startytit+offytit*3,Graphics.BASELINE|Graphics.LEFT); break;
      case 2 : g.drawString("Medium"        , offxtit          , startytit+offytit*3,Graphics.BASELINE|Graphics.LEFT); break;
      case 3 : g.drawString("Hard"          , offxtit          , startytit+offytit*3,Graphics.BASELINE|Graphics.LEFT); break;
     }}

   if (Itemselect==4) {offxtit=32+baseoffxtit; g.setColor(r1,g1,b1);};
   if (Itemselect==5) {offxtit=42+baseoffxtit; g.setColor(r2,g2,b2); };
      g.drawString("Records"          , offxtit          , startytit+offytit*4,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==5) {offxtit=32+baseoffxtit; g.setColor(r1,g1,b1); };
   if (Itemselect==6) {offxtit=42+baseoffxtit; g.setColor(r2,g2,b2);};
   if (versioneItaliana){
      g.drawString("Regole"          , offxtit          , startytit+offytit*5,Graphics.BASELINE|Graphics.LEFT);}
      else
      {g.drawString("Rules"          , offxtit          , startytit+offytit*5,Graphics.BASELINE|Graphics.LEFT);}
   if (Itemselect==6) {offxtit=32+baseoffxtit; g.setColor(r1,g1,b1); };
   if (Itemselect==7) {offxtit=42+baseoffxtit; g.setColor(r2,g2,b2);};
      g.drawString("Info"            , offxtit          , startytit+offytit*6,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==7) {offxtit=32+baseoffxtit; g.setColor(r1,g1,b1); };
   if (Itemselect==8) {offxtit=42+baseoffxtit; g.setColor(r2,g2,b2);};
      g.drawString("Exit"            , offxtit          , startytit+offytit*7,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==8) {offxtit=32+baseoffxtit; g.setColor(r1,g1,b1); };
  }

  private void showpaginamenu(Graphics g) {
   g.setColor(0,0,0);
   g.setFont(Item_font);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.drawImage(imagesfondo2,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setFont(Item_fontB);
  }




  public void paint(Graphics g)  {
   switch(fasemenu) {
    case FASE1PAGINA  : showprimapagina(g);  fasemenu=FASEMENU;   break;
    case FASEMENU     : showpaginamenu(g); showtitolimenu(g);     break;
    case FASEINFO     : showInfo(g);                              break;
    case FASEREGOLE   : showRegole(g);                            break;
    case FASERECORDS  : showRecords(g);                           break;
    case FASEGIOCO    :
      gInterface.paintInterface(g);             break;
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

   if (keycode ==  -10) { tasto7(); };  // motorola A1000 sx
   if (keycode ==  -11) { tasto7(); };  // motorola A1000 dx
   if (keycode ==  - 7) { tasto7(); };
   if (keycode ==  -22) { tasto7(); };
  }



  public void tasto7(){
   switch(fasemenu) {
    case FASEMENU     : escigioco();         break;
    case FASEGIOCO    : if (score.justintrodotto>=0) {fasemenu=FASERECORDS; } else fasemenu=FASEMENU;  break;
    case FASERECORDS  : fasemenu=FASEMENU;   score.justintrodotto=-1; break;
    case FASEINFO     : fasemenu=FASEMENU;   break;
    case FASEREGOLE   : fasemenu=FASEMENU;   break;
   }
  }



  private void azione(int x,int y,int z) {
   switch(fasemenu) {
    case FASEMENU :
     if (z>0){
      switch(Itemselect) {
       case 1 : gInterface.nuovapartita(); fasemenu=FASEGIOCO; Itemselect=2;  break;
       case 2 : gInterface.pareggiatime(); fasemenu=FASEGIOCO;    break;
       case 3 : Suono=!Suono; score.save();
              if (!Suono){ try {gInterface.player.stop();}  catch ( Exception e){};};
              if (Suono) { try {gInterface.player.setMediaTime(0); gInterface.player.start();}  catch ( Exception e){};};
       break;
       case 4 : difficolta++; if (difficolta>3){difficolta=1;}; gInterface.nuovapartita();  break;
       case 5 : fasemenu=FASERECORDS;   break;
       case 6 : fasemenu=FASEREGOLE;    break;
       case 7 : fasemenu=FASEINFO;      break;
       case 8 : escigioco();            break;
      }
     };
     if (y<0){ Itemselect++; if (Itemselect>MaxItemsMenu){Itemselect=1;}   };
     if (y>0){ Itemselect--; if (Itemselect<1){Itemselect=MaxItemsMenu;}   };
    break;
    case FASEINFO   :      if (z>0){ fasemenu=FASEMENU;}    break;
    case FASEREGOLE :      if (z>0){ fasemenu=FASEMENU;}    break;
    case FASEGIOCO  :      gInterface.azione(x,y,z); break;
    case FASERECORDS:      if (z>0){ fasemenu=FASEMENU;}    break;
   }
  }



}

