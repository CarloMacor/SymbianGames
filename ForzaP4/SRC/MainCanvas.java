package Forza4;


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.Sprite;


public class MainCanvas  extends Canvas implements Runnable  {  //
  private boolean          versioneitaliana=false;
  private Forza4           parent;
  private boolean          mTrucking=false;
  public  Interface        gInterface;
  private Graphics         g;
  private int              w;
  private int              h;
  private String           nomesfondo="/castle.png";
  private String           nomepupi="/pupi.png";
  private String           nomeimmagine1="/sfondo1.png";
  private String           nomeimmagine2="/sfondo2.png";
  private int              stepmovimentofondo=2;

  private Image            imagesfondocastello;
  private Image            imagesfondopupi;
  private Image            imagesfondo1;
  public  Image            imagesfondo2;

  private int              yposSfondo=0;
  private long             tempoOra;
  private long             tempoIni;
  public  int              fasemenu=0;
  private final int        FASE1PAGINA  = 0;
  private final int        FASEMENU     = 1;
  private final int        FASECASTELLO = 2;
  private final int        FASEGIOCO    = 3;
  private final int        FASEINFO     = 4;
  private final int        FASEREGOLE   = 5;

  public  Font             Title_font,Item_font,Item_fontB;
  private int              Itemselect=1;
  private int              MaxItemsMenu=6;
  public  boolean          Suono=false;

  private int[]            posizioni;
  public  int              posizionenelcastello;
  public  int              maxfasicastello=5;
  private int              x1pupi,y1pupi,x2pupi,y2pupi,dxpupi,dypupi;
  private boolean          stopped=false;

  ///////////////////////////////////////++++++++++++++++++++++++ to erase
//  private int              codicetasto1=0;
//  private int              codicetasto2=0;
  ///////////////////////////////////////++++++++++++++++++++++++ to erase


// *******************   MAIN  **********************************
  public MainCanvas(Forza4 parent)  throws IOException {
//    super(true);
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
  }


  public void postcreate(){
    x1pupi=0;   y1pupi=0;
    x2pupi=0;   y2pupi=0;
    dxpupi=w;   dypupi=0;
    posizioni = new int[6];
    posizioni[1]=18;    posizioni[2]=35;    posizioni[3]=60;    posizioni[4]=77;    posizioni[5]=85;
    posizionenelcastello=1;
    // Posizione di visualizzazione castello in 100/ dell'altezza sfondo castello

    try {
     imagesfondocastello  = Image.createImage(nomesfondo);   // il castello
     dypupi=imagesfondocastello.getHeight()*70/800;
     imagesfondopupi      = Image.createImage(nomepupi);   // il castello
     imagesfondo2 = Image.createImage(nomeimmagine2);
     gInterface= new Interface(this,w,h);
    }  catch (IOException ioe) {  }
    gInterface.loadRecord();
    gInterface.SuonaButW();
  }


  public void start() {
    mTrucking = true;
    Thread t = new Thread(this);
    t.start();
  }


  private void aspetta(){
    tempoOra = System.currentTimeMillis();
    while ( tempoOra-tempoIni < 50) {
     tempoOra = System.currentTimeMillis();
    }
    tempoIni = tempoOra;
  }




  public void run() {
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
    g.drawString("(c) Copyright 2005"   , getWidth()/2,  starty        ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("carlomacor.com"       , getWidth()/2,  starty+offy   ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Graphic"              , getWidth()/2,  starty+offy*3 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Daniele Pintene"      , getWidth()/2,  starty+offy*4 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Music"               , getWidth()/2,  starty+offy*6 ,Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Paolo Mastrandrea"    , getWidth()/2,  starty+offy*7 ,Graphics.BASELINE|Graphics.HCENTER);
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
    if (versioneitaliana) {
     g.drawString("REGOLE"                    , getWidth()/2 , 22 ,Graphics.BASELINE|Graphics.HCENTER);
     g.setFont(Item_font);
      g.drawString("Affronta i castellani.  Per"    , getWidth()/2, starty+offy   ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("vincerli devi allineare 4 "     , getWidth()/2, starty+offy*2 ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("pedine in orizzontale,"         , getWidth()/2, starty+offy*3 ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("verticale o diagonale."         , getWidth()/2, starty+offy*4 ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("con i cursori vai a sinistra"   , getWidth()/2, starty+offy*5 ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("e a destra. Seleziona per"      , getWidth()/2, starty+offy*6 ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("far scendere la pedina."        , getWidth()/2, starty+offy*7 ,Graphics.BASELINE|Graphics.HCENTER);
    }
     else
    {g.drawString("RULES"                    , getWidth()/2 , 22 ,Graphics.BASELINE|Graphics.HCENTER);
      g.setFont(Item_font);
      g.drawString("Win All the ducks. To"    , getWidth()/2, starty+offy   ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("win them put in line 4 "     , getWidth()/2, starty+offy*2 ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("pices orizzontally,"         , getWidth()/2, starty+offy*3 ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("verticlly or in diagonal."         , getWidth()/2, starty+offy*4 ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("use the cursors for left"   , getWidth()/2, starty+offy*5 ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("and right. Select to"      , getWidth()/2, starty+offy*6 ,Graphics.BASELINE|Graphics.HCENTER);
      g.drawString("drop down the piece."        , getWidth()/2, starty+offy*7 ,Graphics.BASELINE|Graphics.HCENTER);
    }
  }



  private void showtitolimenu(Graphics g) {
   int offxtit=12; int baseoffxtit=12;  int offytit=20;   int startytit=52;
   if (h<170) {startytit=42; offytit=15;}

   baseoffxtit=(w-imagesfondo2.getWidth())/2;
   if (baseoffxtit<0) {baseoffxtit=0;};

   offxtit=12+baseoffxtit;

   g.setColor(255,255,255);
   g.setFont(Title_font);   g.drawString("MENU"            , getWidth()/2 , 22,Graphics.BASELINE|Graphics.HCENTER);
   g.setFont(Item_font);
   if (Itemselect==1) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
    if (versioneitaliana) { g.drawString("Nuova Partita"   , offxtit           , startytit,Graphics.BASELINE|Graphics.LEFT);}
                     else { g.drawString("New Game"   , offxtit           , startytit,Graphics.BASELINE|Graphics.LEFT);}
   if (Itemselect==1) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==2) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
    if (versioneitaliana) { g.drawString("Continua"        , offxtit           , startytit+offytit,Graphics.BASELINE|Graphics.LEFT);}
                     else { g.drawString("Continue"        , offxtit           , startytit+offytit,Graphics.BASELINE|Graphics.LEFT);}
   if (Itemselect==2) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==3) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
      if (Suono) { g.drawString("Sound On"    , offxtit, startytit+offytit*2,Graphics.BASELINE|Graphics.LEFT);} else
                 { g.drawString("Sound Off"   , offxtit, startytit+offytit*2,Graphics.BASELINE|Graphics.LEFT);};
   if (Itemselect==3) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==4) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
    if (versioneitaliana) { g.drawString("Regole"          , offxtit          , startytit+offytit*3,Graphics.BASELINE|Graphics.LEFT);}
                     else { g.drawString("Rules"           , offxtit          , startytit+offytit*3,Graphics.BASELINE|Graphics.LEFT);}
   if (Itemselect==4) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==5) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
      g.drawString("Info"            , offxtit          , startytit+offytit*4,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==5) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
   if (Itemselect==6) {g.setFont(Item_fontB); offxtit=22+baseoffxtit; };
      g.drawString("Exit"            , offxtit          , startytit+offytit*5,Graphics.BASELINE|Graphics.LEFT);
   if (Itemselect==6) {g.setFont(Item_font);  offxtit=12+baseoffxtit; };
  }

  private void showpaginamenu(Graphics g) {
   g.setColor(0,0,0);
   g.setFont(Item_font);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.drawImage(imagesfondo2,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setFont(Item_fontB);
  }


  private void showcastello(Graphics g) {
   int baseoffxtit=(w-imagesfondo2.getWidth())/2;
   if (baseoffxtit<0) {baseoffxtit=0;};

    controllaposizionesfondo();
    g.drawImage(imagesfondocastello,w/2,yposSfondo,Graphics.TOP|Graphics.HCENTER);

    g.drawRegion(imagesfondopupi,x1pupi,y1pupi,dxpupi,dypupi,Sprite.TRANS_NONE,x2pupi+baseoffxtit,y2pupi, Graphics.TOP|Graphics.LEFT      );

  }



  public void paint(Graphics g)  {
   switch(fasemenu) {
    case FASE1PAGINA  : showprimapagina(g);  fasemenu=FASEMENU;   break;
    case FASEMENU     : showpaginamenu(g); showtitolimenu(g);     break;
    case FASEINFO     : showInfo(g);                              break;
    case FASEREGOLE   : showRegole(g);                            break;
    case FASECASTELLO : showcastello(g);                          break;
    case FASEGIOCO    : showpaginamenu(g);
                        gInterface.paintInterface(g);
                                                                  break;
   }


  }


  public void stop() {
  }





  private void escigioco(){
    parent.destroyApp(true); parent.notifyDestroyed();
  }


  protected void keyPressed(int keycode){
   boolean eseguito=false;
//   codicetasto1=keycode;
   int gameAction = getGameAction(keycode);
   switch ( gameAction) {
    case UP    : azione( 0, 1, 0); eseguito=true; break;
    case DOWN  : azione( 0,-1, 0); eseguito=true; break;
    case LEFT  : azione(-1, 0, 0); eseguito=true; break;
    case RIGHT : azione( 1, 0, 0); eseguito=true; break;
    case FIRE  : azione( 0, 0, 1); eseguito=true; break;
   }

   if (!eseguito) {
     if (keycode ==  - 10) { tasto7(); };      // motorola A1000 tasto alto sx
     if (keycode ==  - 11) { tasto7(); };      // motorola A1000 tasto alto dx
     if (keycode ==  -  7) { tasto7(); };
     if (keycode ==  - 22) { tasto7(); };
   }  
  }


  
  private void tasto7(){
   switch(fasemenu) {
    case FASEMENU     : escigioco();         break;
    case FASECASTELLO : fasemenu=FASEMENU;   break;
    case FASEGIOCO    : if (gInterface.game_over) { gInterface.alzacancello(); }
         else           fasemenu=FASEMENU;   break;
    case FASEINFO     : fasemenu=FASEMENU;   break;
    case FASEREGOLE   : fasemenu=FASEMENU;   break;

   }
  }



  private void azione(int x,int y,int z) {
   switch(fasemenu) {
    case FASEMENU :
     if (z>0){
      switch(Itemselect) {
       case 1 : yposSfondo=0; fasemenu=FASECASTELLO;  break;
       case 2 : if (gInterface.game_over)
               {yposSfondo=0; fasemenu=FASECASTELLO;} else
               {fasemenu=FASEGIOCO;}
                                        break;
       case 3 : Suono=!Suono; gInterface.saveRecord();
              if (!Suono){ try {gInterface.player.stop();}  catch ( Exception e){};};
              if (Suono) { try {gInterface.player.setMediaTime(0); gInterface.player.start();}  catch ( Exception e){};};
       break;
       case 4 : fasemenu=FASEREGOLE;    break;
       case 5 : fasemenu=FASEINFO;      break;
       case 6 : escigioco();            break;
      }
     };
     if (y<0){ Itemselect++; if (Itemselect>MaxItemsMenu){Itemselect=1;}   };
     if (y>0){ Itemselect--; if (Itemselect<1){Itemselect=MaxItemsMenu;}   };
    break;
    case FASEINFO   :      if (z>0){ fasemenu=FASEMENU;}    break;
    case FASEREGOLE :      if (z>0){ fasemenu=FASEMENU;}    break;
    case FASECASTELLO :
     if (y>0){ if (posizionenelcastello<maxfasicastello)  posizionenelcastello++;   }
     if (y<0){ if (posizionenelcastello>1)                posizionenelcastello--;   }
     if (z>0){ fasemenu=FASEGIOCO;
                                   gInterface.resettapedine();
                                   gInterface.livelloH=posizionenelcastello;
                                   gInterface.toccame = false;
                                   gInterface.abbassacancello();  };
    break;
    case FASEGIOCO :
     if (x>0){ if (gInterface.pospedinadamettere<6 & gInterface.toccame)  gInterface.pospedinadamettere++;   }
     if (x<0){ if (gInterface.pospedinadamettere>0 & gInterface.toccame)  gInterface.pospedinadamettere--;   }
     if (y<0) {
                if (gInterface.toccame & gInterface.cancellopronto ){ // & !gInterface.messaggioincorso
                     if(gInterface.lasciapedina()) { gInterface.TempoFattoMossa=System.currentTimeMillis(); };
                }
     }
     if (z>0) {
                if (gInterface.game_over) {
                 if (!gInterface.messaggioincorso) { gInterface.alzacancello();};
                }
                 else
                if (gInterface.toccame & gInterface.cancellopronto ){ // & !gInterface.messaggioincorso
                     if(gInterface.lasciapedina()) { gInterface.TempoFattoMossa=System.currentTimeMillis(); };
                }
                if (!gInterface.toccame & (gInterface.indicemessaggio==1) )
                  { gInterface.indicemessaggio=0; gInterface.poscharmsg=20;  repaint(); }
             }
    break;
   }
  }


  private void controllaposizionesfondo(){
    int hpos=0;
    int dif =h-imagesfondocastello.getHeight();
    switch (posizionenelcastello) {
     case 5 : hpos=0;         break;
     case 4 : hpos=dif/4;     break;
     case 3 : hpos=dif*2/4;   break;
     case 2 : hpos=dif*3/4;   break;
     case 1 : hpos=dif;       break;
    }
   int delta=yposSfondo+hpos; if (delta<0) delta=-delta;
   if  (delta<stepmovimentofondo) { yposSfondo=hpos; };
   if (yposSfondo>hpos) {yposSfondo=yposSfondo-stepmovimentofondo;};
   if (yposSfondo<hpos) {yposSfondo=yposSfondo+stepmovimentofondo;};

    int hh =imagesfondocastello.getHeight();
    int ww =imagesfondocastello.getWidth();
    switch (posizionenelcastello) {
     case 5 :    y1pupi=hh*36/800;    x1pupi=ww*256/480; dxpupi=ww*123/480; dypupi=hh*120/800;  break;
     case 4 :    y1pupi=hh*100/800;   x1pupi=ww*384/480; dxpupi=ww*83/480;  dypupi=hh*120/800;  break;
     case 3 :    y1pupi=hh*208/800;   x1pupi=ww*167/480; dxpupi=ww*148/480; dypupi=hh*152/800;  break;
     case 2 :    y1pupi=hh*396/800;   x1pupi=ww*120/480; dxpupi=ww*170/480; dypupi=hh*172/800;  break;
     case 1 :    y1pupi=hh*570/800;   x1pupi=ww*130/480; dxpupi=ww*150/480; dypupi=hh*149/800;  break;
    }

    y2pupi=y1pupi+yposSfondo;
    x2pupi=x1pupi;


  }


}

