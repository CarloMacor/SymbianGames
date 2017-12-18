package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public MainCanvas(WindserfMIDlet parent)  throws IOException 
//  private void createBackground(String backgroundImageName) throws IOException
//  private void createSerfer1(String fileName)   throws IOException
//  private void createSerfer2(String fileName)   throws IOException
//  private void createBandiera(String fileName)   throws IOException
//  private void createSqualo(String fileName)   throws IOException
//  private void createBox(String fileName,String fileName2)   throws IOException
//  private void createBoa(String fileName)   throws IOException
//  public void RiattivaFaseMenu3alti()
//  public void imponisqualo_surfer()
//  private void AttivaInfo()
//  public void AttivaMenudaInfo()
//  private void AttivaContinue()
//  public void start()
//  public void premuto7()
//  private void tastoFire()
//  public void run()
//  public void stop()
//  public void setVisible(int layerIndex, boolean show)
//  public boolean isVisible(int layerIndex)
//  private void AttivaNuovoGioco(int modo)
//  private int distanzaQuadraSerferBox(SerferObj serf, BoxObj boxx)
//  private int distanzaQuadraSerferSqualo()
//  public void scrolling(int dx, int dy)
//  private int minimoAY()
//  private void riordina(int modo)
//  private void ControlloRegoleGioco()
//  protected void pointerPressed(int x,int y)
//  protected void pointerDragged(int x,int y)
//  protected void pointerReleased(int x,int y)
//  public void azioneFire(int indice)
//  protected void keyPressed(int keyCode)
//  protected void keyPressed(int keyCode)
//  public void ripartemusica()




import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Calendar.*;
import java.util.Random;


public class MainCanvas  extends GameCanvas  implements Runnable {
  private WindserfMIDlet parentW;
  private boolean mTrucking;
  private LayerManager mLayerManager;
  public  int                  IndiceMenu       = 2; // 2 box;  3 race ; 4 Shark;
  public  boolean              infaseMenu       = true;
  public  boolean              infaseInfo       = false;
  private boolean              premutotastomenu = false;
  private static final int     MenuChoise       = 8;
  public  boolean              IniziatoGioco    = false;
  public  int                  GiocoInCorso     = 0; // 1= boxes 2= race 3= shark
  public  final  int           offerTable       = 40;
  public  int                  offScreenX       = 0 ;
  public  int                  offScreenY       = 0 ;
  public  int                  Xarrivo          = 0 ;
  public  GrafMenu             gMenu;
  public  Interface            gInterface;
  public  SerferObj            serfer1;
  public  SerferObj            serfer2;
  private BandieraObj          bandiera;
  private SqualoObj            squalo;
  public  BoxObj               box1;
  public  BoaObj               boa;
  private MareObj              mare;
  public  info                 gInfo;
  public  long                 TempoTotShark;
  public  long                 TempoiniRace;
  public  int                  contaframe = 0;
  public  long                 framesec = 0;
  public  int                  w = 176;
  public  int                  h = 108;
  public  int                  BarraTimer    =  0;
  public  int                  posBarraTimer =  0;
  public  int                  PuntiBox      =  0;
  public  int                  TimeBox       =  0;
  public  int                  TimeBoxini    =  0;  // velocita' iniziale dell'acchiappa scatola
  public  int                  TimeBoxiniziale=0;
  public  boolean              FinitoGiocoBox   = false;
  public  boolean              FinitoGiocoRace  = false;
  public  boolean              FinitoGiocoShark = false;
  public  int                  modalita=1;  // easy=1 Medium=2 Hard=3  // da leggere da ultima partita
  public  int                  oldmodalita=modalita;
  public  int                  direzionevento=5;
  public  boolean              Suono=true;
  // ricorda pos ele grafici ;
  private int                  oldxShark=0;
  private int                  oldyShark=0;
  private int                  oldxBox1=0;
  private int                  oldyBox1=0;
  private int                  oldxSerfer1=0;
  private int                  oldySerfer1=0;
  private int                  oldxSerfer2=0;
  private int                  oldySerfer2=0;
  private int                  oldDirSerfer1=0;
  private int                  oldDirSerfer2=0;
  private int                  oldDirShark=0;
  private int                  oldFase1=0;
  private boolean              oldVedoShark     = false;
  private boolean              oldVedoBox1      = false;
  private boolean              oldVedoSplash    = false;
  private boolean              oldVedoBoa1      = false;
  private boolean              oldVedoBoa2      = false;
  private boolean              oldVedoBoa3      = false;
  private boolean              oldVedoBoa4      = false;
  private boolean              oldVedoBoa5      = false;
  private boolean              oldVedoBoa6      = false;
  private boolean              oldVedoSerfer2   = false;
  private boolean              oldVedoSerfer2_2 = false;
  public  int                  fototimeinvisibileBox1=0;
  public  int                  timeinvisibileFix=30;
  public  long                 tempoIni   ;
  public  long                 tempoOra   ;
  public  long                 tempoVento ;
  public  int                  deltaTimeVentonuovo=10000;
  public  long                 deltaTimeRace;
  private long                 TimeBlockContinue;
  private int[]                ordY;
  private int[]                ordA;
  public  boolean              isRecord=false;
  public  boolean              conPointer = false;
  public  int                  VolumeSettato = 25;
  public  int                  maxlinehelp=3;
  private Graphics             g;
  private boolean              fermato=false;

// *******************   MAIN  **********************************
  public MainCanvas(WindserfMIDlet parent)  throws IOException {
    super(true);
    ordY = new int[9];
    ordA = new int[9];
    setFullScreenMode(true);
    // Create a LayerManager.
    w=getWidth();
    h=getHeight();
    mLayerManager = new LayerManager();
    BarraTimer = (w/2 - 13)-8;
    posBarraTimer=w*3/4-20;           // w/2+20;
    Xarrivo = (w*4*7)/10;
    mLayerManager.setViewWindow(0, 0, w, h); // 96,0,w,h
    createBackground("/mare.png");
    createBox       ("/box.png","/Splash.png");
    createSerfer1   ("/Serfer1.png");
    createSerfer2   ("/Serfer2.png");
    createSqualo    ("/squalo.png");
    createBoa       ("/boa.png");
    createBandiera  ("/bandiera.png");
    gMenu         = new GrafMenu(this,w,h,parent);
    gInterface    = new Interface(this,w,h,parent);
    conPointer=(hasPointerEvents() & hasPointerMotionEvents());
    if (conPointer) {maxlinehelp=30;} else {maxlinehelp=24;};
    gInfo         = new info(this);
    gInterface.loadRecord();
    gInterface.SuonaButW();
    RiattivaFaseMenu();
    IndiceMenu =2;
    g = getGraphics();
    g.setClip(0,0,w,h);
  }




  private void createBackground(String backgroundImageName) throws IOException {
    mare = new MareObj(this,backgroundImageName);
    mLayerManager.append(mare.mMare);
  }


  private void createSerfer1(String fileName)   throws IOException {
    serfer1 = new SerferObj(this,fileName,true);
    mLayerManager.insert(serfer1.mSerfer, 0);
    mLayerManager.insert(serfer1.mSerfer2, 0);
  }

  private void createSerfer2(String fileName)   throws IOException {
    serfer2 = new SerferObj(this,fileName,false);
    mLayerManager.insert(serfer2.mSerfer, 0);
    mLayerManager.insert(serfer2.mSerfer2, 0);
    serfer2.mSerfer.setVisible(false);
    serfer2.mSerfer2.setVisible(false);
  }


  private void createBandiera(String fileName)   throws IOException {
    bandiera = new BandieraObj(this, fileName);
    mLayerManager.insert(bandiera.mBandiera, 0);
  }



  private void createSqualo(String fileName)   throws IOException {
    squalo = new SqualoObj(this,fileName);
    mLayerManager.insert(squalo.mSqualo, 0);
  }

  private void createBox(String fileName,String fileName2)   throws IOException {
    box1 = new BoxObj(this,fileName,fileName2);
    mLayerManager.insert(box1.mSplash, 0);
    box1.mSplash.setVisible(false);
    mLayerManager.insert(box1.mBox, 0);
  }

  private void createBoa(String fileName)   throws IOException {
    // boa blu tipo 1 , fuxia tipo 2;
    boa = new BoaObj(this,fileName);
    mLayerManager.insert(boa.mBoa1, 0);
    mLayerManager.insert(boa.mBoa2, 0);
    mLayerManager.insert(boa.mBoa3, 0);
    mLayerManager.insert(boa.mBoa4, 0);
    mLayerManager.insert(boa.mBoa5, 0);
    mLayerManager.insert(boa.mBoa6, 0);
  }


  public void RiattivaFaseMenu3alti() {
    box1.mBox.setPosition     ( w/4-10, gMenu.hriga2-40);
    serfer1.mSerfer.setPosition( w/2-13, gMenu.hriga2-40);
    squalo.mSqualo.setPosition( (w*3/4)-10, gMenu.hriga2-40);
  }


  public void RiattivaFaseMenu() {
    TimeBlockContinue =System.currentTimeMillis();
    oldVedoShark    = squalo.mSqualo.isVisible();
    oldVedoBox1     = box1.mBox.isVisible();
    oldVedoSplash   = box1.mSplash.isVisible();
    oldVedoBoa1      = boa.mBoa1.isVisible();
    oldVedoBoa2      = boa.mBoa2.isVisible();
    oldVedoBoa3      = boa.mBoa3.isVisible();
    oldVedoBoa4      = boa.mBoa4.isVisible();
    oldVedoBoa5      = boa.mBoa5.isVisible();
    oldVedoBoa6      = boa.mBoa6.isVisible();
    oldVedoSerfer2  = serfer2.mSerfer.isVisible();
    oldVedoSerfer2_2= serfer2.mSerfer2.isVisible();
    oldxShark       = squalo.mSqualo.getX();
    oldyShark       = squalo.mSqualo.getY();
    oldxBox1        = box1.mBox.getX();
    oldyBox1        = box1.mBox.getY();
    oldxSerfer1   = serfer1.mSerfer.getX();
    oldySerfer1   = serfer1.mSerfer.getY();
    oldxSerfer2   = serfer2.mSerfer.getX();
    oldySerfer2   = serfer2.mSerfer.getY();
    oldFase1      = serfer1.Fase;
    oldDirSerfer1 = serfer1.Direzione;
    oldDirShark   = squalo.Direzione;
    squalo.mSqualo.setPosition( (w*3/4)-10, gMenu.hriga2-40);
    box1.mBox.setPosition     ( w/4-10, gMenu.hriga2-40);
    box1.mSplash.setPosition  ( w/4-10, gMenu.hriga2-40);
    box1.mSplash.setVisible(false);
    serfer2.mSerfer.setVisible(false);
    serfer1.mSerfer.setPosition( w/2-13, 100);
    serfer1.Fase =0;
    serfer1.imponiDir(2);
    squalo.imponiDir(4);
    box1.mBox.setVisible(true);
    squalo.mSqualo.setVisible(true);
    boa.visualizza(false);
    IndiceMenu =1;
    infaseMenu=true;
  }


  public void imponisqualo_surfer(){
    squalo.mSqualo.setPosition( (w*3/4)-10, gMenu.hriga2-40);
    box1.mBox.setPosition     ( w/4-10, gMenu.hriga2-40);
    box1.mSplash.setPosition  ( w/4-10, gMenu.hriga2-40);
    box1.mSplash.setVisible(false);
    serfer2.mSerfer.setVisible(false);
    serfer1.mSerfer.setPosition( w/2-13, gMenu.hriga2-40);
    box1.mBox.setVisible(true);
    squalo.mSqualo.setVisible(true);
    boa.visualizza(false);
  }

  private void AttivaInfo(){
    infaseInfo=true;
    gInfo.rigaattiva=0;
//    parent.gInterface.Rosa.
    bandiera.mBandiera.setVisible(false);
    box1.mBox.setVisible(false);
    serfer1.mSerfer.setVisible(false);
    squalo.mSqualo.setVisible(false);
  }

 public void AttivaMenudaInfo(){
    infaseInfo=false;
    bandiera.mBandiera.setVisible(true);
    box1.mBox.setVisible(true);
    serfer1.mSerfer.setVisible(true);
    squalo.mSqualo.setVisible(true);
  }


  private void AttivaContinue(){
    squalo.mSqualo.setVisible(oldVedoShark);
    box1.mBox.setVisible(oldVedoBox1);
    box1.mSplash.setVisible(oldVedoSplash);
    boa.mBoa1.setVisible(oldVedoBoa1);
    boa.mBoa2.setVisible(oldVedoBoa2);
    boa.mBoa3.setVisible(oldVedoBoa3);
    boa.mBoa4.setVisible(oldVedoBoa4);
    boa.mBoa5.setVisible(oldVedoBoa5);
    boa.mBoa6.setVisible(oldVedoBoa6);
    box1.mBox.setPosition( oldxBox1, oldyBox1);
    box1.mSplash.setPosition( oldxBox1, oldyBox1);
    squalo.mSqualo.setPosition( oldxShark, oldyShark);
    squalo.mSqualo.setPosition( oldxShark, oldyShark);
    serfer1.mSerfer.setPosition( oldxSerfer1, oldySerfer1);
    serfer1.imponiDir(oldDirSerfer1);
    serfer1.Fase = oldFase1;

    serfer2.mSerfer.setVisible(oldVedoSerfer2);
    serfer2.mSerfer2.setVisible(oldVedoSerfer2_2);

    serfer2.mSerfer.setPosition( oldxSerfer2, oldySerfer2);
    squalo.imponiDir(oldDirShark);

    if (GiocoInCorso==2){
     TempoiniRace= System.currentTimeMillis()-deltaTimeRace;
    };
    tempoVento = tempoVento+(System.currentTimeMillis()-TimeBlockContinue);

   infaseMenu=false;
  }



  public void start() {
    mTrucking = true;
    Thread t = new Thread(this);
    t.start();
  }




  public void premuto7(){
      if (infaseMenu)
       { if (infaseInfo) {AttivaMenudaInfo();} else
         { if (IndiceMenu==8 ) {azioneFire(IndiceMenu);} else {  IndiceMenu=8;};};
       } else { RiattivaFaseMenu(); }
   premutotastomenu = true;
  }



  private void tastoFire() {
           if (infaseMenu) { if (infaseInfo) { AttivaMenudaInfo();}  else{ azioneFire(IndiceMenu);}; }
           else { serfer1.Controllo(2); };
           premutotastomenu = true;
  }


  public void run() {
    int frameCount = 0;
    int factor = 2;
    int animatedDelta = 0;

    tempoIni   = System.currentTimeMillis();
    tempoOra   = System.currentTimeMillis();
    tempoVento = System.currentTimeMillis();

    while (mTrucking) {

     if (!isShown()) { if (Suono){ try {gInterface.player.stop();}  catch ( Exception e){};}; fermato=true; } else
                     { if (fermato) { if (Suono){ gInterface.SuonaButW(); } fermato=false;  };};

     if (fermato) { continue;};

     if ((h!=getHeight()) | (w!=getWidth())) {
       h=getHeight();        w=getWidth();
       mLayerManager.setViewWindow(0, 0, w, h); // 96,0,w,h
       g.setClip(0,0,w,h);
       gInterface.cambiaH(h);
       gMenu.cambiaH(h);
       bandiera.cambiaH(h);
       mLayerManager.remove(mare.mMare);
       mare = null;
       try { mare = new MareObj(this,"/mare.png"); mLayerManager.append(mare.mMare); }
         catch ( Exception e){};
     };
       if (infaseMenu) {} else ControlloRegoleGioco();
// corrrezione pennino
       if (infaseMenu) { if (conPointer) { RiattivaFaseMenu3alti(); } };
       if (GiocoInCorso==2) { // se in race centra player1
        if ((serfer1.Xpos/100)<w/3)     { scrolling(-1, 0); };
        if ((serfer1.Xpos/100)>(w/2)) { scrolling( 1, 0); };
        if ((serfer1.Ypos/100)<h/3)     { scrolling( 0,-1); };
        if ((serfer1.Ypos/100)>(h*2/3)) { scrolling( 0, 1); };
       }
       serfer1.next();
       box1.next();
       bandiera.next();
       squalo.next();
       boa.next();
       mare.next();
       int keyStates = getKeyStates();
        if (premutotastomenu){} else
        {
         if ((keyStates & FIRE_PRESSED)   != 0) { tastoFire(); }          else
         if ((keyStates & GAME_A_PRESSED) != 0) { tastoFire(); }          else
         if ((keyStates & GAME_B_PRESSED) != 0) { tastoFire(); }          else
         if ((keyStates & GAME_C_PRESSED) != 0) { tastoFire(); }          else
         if ((keyStates & GAME_D_PRESSED) != 0) { tastoFire(); }          else
         if ((keyStates & UP_PRESSED)   != 0) {
           if (infaseMenu) {
             if (infaseInfo) {gInfo.rigaattiva--; if (gInfo.rigaattiva<0) {gInfo.rigaattiva=0;}; } else
             {
              switch (IndiceMenu) {
               case 1 :                   IndiceMenu = 8;                break;
               case 2 : case 3 : case 4 :
                if (IniziatoGioco & (oldmodalita==modalita)) { IndiceMenu = 1;}
                                                        else { IndiceMenu = 8;}; break;
               case 5 : case 6 : case 7 : IndiceMenu = IndiceMenu-3;             break;  // gMenu.last1riga;
               case 8 :                   IndiceMenu = gMenu.last2riga;          break;
              }
              gMenu.parziall=0;
             }
            premutotastomenu = true;
           }
          }
          else
         if ((keyStates & DOWN_PRESSED) != 0) {
           if (infaseMenu) {
             if (infaseInfo) {gInfo.rigaattiva++; if (gInfo.rigaattiva>maxlinehelp) {gInfo.rigaattiva=maxlinehelp;}; } else
             {
              switch (IndiceMenu) {
               case 1 :                   IndiceMenu = gMenu.last1riga;  break;
               case 2 : case 3 : case 4 : IndiceMenu = IndiceMenu+3;     break; // gMenu.last2riga
               case 5 : case 6 : case 7 : IndiceMenu = 8;                break;
               case 8 :                   if (IniziatoGioco & (oldmodalita==modalita)) { IndiceMenu = 1;}
                                          else { IndiceMenu = gMenu.last1riga;}   break;
              }
              gMenu.parziall=0;
             }
             premutotastomenu = true;
           }
          }
          else
         if ((keyStates & LEFT_PRESSED) != 0) {
             if (infaseMenu) {
              if (infaseInfo) {} else
              {
               IndiceMenu = IndiceMenu-1;   gMenu.parziall=0;
               if ((IndiceMenu>1) & (IndiceMenu<5)) {gMenu.last1riga=IndiceMenu;};
               if ((IndiceMenu>4) & (IndiceMenu<8)) {gMenu.last2riga=IndiceMenu;};
                if (IniziatoGioco & (oldmodalita==modalita))
                                   { if (IndiceMenu<1) {IndiceMenu=MenuChoise;};}
                             else  { if (IndiceMenu<2) {IndiceMenu=MenuChoise;};};
              }
             } else
             { serfer1.Controllo(3);     };
            premutotastomenu = true;
         }
         else if ((keyStates & RIGHT_PRESSED) != 0) {
             if (infaseMenu) {
              if (infaseInfo) {} else
              {
               IndiceMenu = IndiceMenu+1;   gMenu.parziall=0;
               if ((IndiceMenu>1) & (IndiceMenu<5)) {gMenu.last1riga=IndiceMenu;};
               if ((IndiceMenu>4) & (IndiceMenu<8)) {gMenu.last2riga=IndiceMenu;};
               if (IniziatoGioco & (oldmodalita==modalita))
                                   { if (IndiceMenu>MenuChoise) {IndiceMenu=1;};}
                             else  { if (IndiceMenu>MenuChoise) {IndiceMenu=2;};};
              };
             } else
             { serfer1.Controllo(1);    };
          premutotastomenu = true;
         }
        }   // premutotastomenu
         if ((keyStates & LEFT_PRESSED) == 0) {
          if ((keyStates & RIGHT_PRESSED) == 0) {
           if ((keyStates & UP_PRESSED) == 0) {
            if ((keyStates & DOWN_PRESSED) == 0) {
             if ((keyStates & FIRE_PRESSED) == 0) {
              if ((keyStates & GAME_A_PRESSED) == 0) {
               if ((keyStates & GAME_B_PRESSED) == 0) {
                if ((keyStates & GAME_C_PRESSED) == 0) {
                 if ((keyStates & GAME_D_PRESSED) == 0) {
                  premutotastomenu = false;
                 }
                }
               }
              }
             }
            }
           }
          }
         }



        mLayerManager.paint(g, 0, 0);
        gMenu.paintMenu(g);
        if (!infaseInfo) {  gInterface.paintInterface(g);} else {gInfo.paintinfo(g,w,h);};

        // in gioco box ho preso box
        // se in lampeggio visualizza nuova traiettoria
        if  (!infaseMenu) {boa.disegnalampeggio(g);}
        flushGraphics();
      tempoOra = System.currentTimeMillis();
      while ( tempoOra-tempoIni < 50) { // 200 // 150
       tempoOra = System.currentTimeMillis();
      }
      tempoIni = tempoOra;
      if (!infaseMenu) {
       if ( (tempoOra-tempoVento) > deltaTimeVentonuovo ){          // in millisecondi
        tempoVento = tempoOra;
        Random m_r = new Random();
        direzionevento = direzionevento+(m_r.nextInt() % 3);
        if (direzionevento<0) {direzionevento=-direzionevento;};
        if (direzionevento>15) {direzionevento=0;};
        deltaTimeVentonuovo = (m_r.nextInt() %  3)*1000+ 9000;
       }
      }
    }
  }


  public void stop() {
    mTrucking = false;
  }


  public void setVisible(int layerIndex, boolean show) {
    Layer layer = mLayerManager.getLayerAt(layerIndex);
    layer.setVisible(show);
  }

  public boolean isVisible(int layerIndex) {
    Layer layer = mLayerManager.getLayerAt(layerIndex);
    return layer.isVisible();
  }

  private void AttivaNuovoGioco(int modo){
   IniziatoGioco    = true;
   GiocoInCorso     = modo;
   FinitoGiocoBox   = false;
   FinitoGiocoRace  = false;
   FinitoGiocoShark = false;
   isRecord         = false;
   Random m_r1 = new Random();
   tempoVento = System.currentTimeMillis();
   deltaTimeVentonuovo = (m_r1.nextInt() %  3)*1000+ 9000;
   direzionevento      = (m_r1.nextInt() % 15);
   if (direzionevento<0) {direzionevento=-direzionevento;};

   oldmodalita=modalita;
   offScreenX    = 0  ;
   offScreenY    = 0  ;
   switch (modo) {
    case 1 : // boxes
      squalo.mSqualo.setVisible(false);
      serfer1.start(1);
      TimeBoxini    = 1000;  // velocita' iniziale dell'acchiappa scatola
      fototimeinvisibileBox1 = 0;
      box1.start(1);
     break;
    case 2 : // Race
      squalo.mSqualo.setVisible(false);  box1.mBox.setVisible(false);   box1.mSplash.setVisible(false);
      serfer2.mSerfer.setVisible(true);
      serfer1.start(2);
      serfer2.start(2);
      boa.start();
      TempoiniRace = System.currentTimeMillis();
     break;
    case 3 : // Shark
      serfer1.start(3);
      squalo.startgame();
      box1.mBox.setVisible(false);
      box1.mSplash.setVisible(false);
     break;
    default :  infaseMenu=true ; break;
   }
  }



  private int distanzaQuadraSerferBox(SerferObj serf, BoxObj boxx) {
   int value=0;
   int x1= serf.mSerfer.getX()+13;   int y1= serf.mSerfer.getY()+20;
   int x2= boxx.mBox.getX()+10;      int y2= boxx.mBox.getY()+15;
   value = (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
   return value;
  }

  private int distanzaQuadraSerferSqualo() {
   int value=0;
   int x1= serfer1.mSerfer.getX()+10; int y1= serfer1.mSerfer.getY()+26;
   int x2= squalo.mSqualo.getX()+10;  int y2= squalo.mSqualo.getY()+15;
   value = (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
   return value;
  }


   public void scrolling(int dx, int dy) {
    offScreenX = offScreenX+dx;
    offScreenY = offScreenY+dy;
    if (offScreenX<0)     { dx=0; offScreenX=0;   };
    if (offScreenX>(w*2)) { dx=0; offScreenX=w*2; };
    if (offScreenY<0)     { dy=0; offScreenY=0;   };
    if (offScreenY>(h/4)) { dy=0; offScreenY=h/4; };

    boa.scrolla    (-dx,-dy);
    serfer1.scrolla(-dx,-dy);
    serfer2.scrolla(-dx,-dy);
    mare.scrolla   (-dx,-dy);
  }


  private int minimoAY() {
   int value =1000;  int code =0;
   for ( int i=1 ; i<9; i++) {
    if  (ordY[i]>0) { if (ordY[i]<value) {value = ordY[i]; code = i;} }
   }
   ordY[code]=0;
   return code;
  }


  private void riordina(int modo) {
   int orY1;
   int orY2;
   switch (modo) {
    case 1 :
     orY1=box1.mBox.getY()+10;                   orY2=serfer1.mSerfer.getY()+26;
     mLayerManager.remove(box1.mBox);
     mLayerManager.remove(serfer1.mSerfer);      mLayerManager.remove(serfer1.mSerfer2);
     mLayerManager.insert(serfer1.mSerfer, 1);   mLayerManager.insert(serfer1.mSerfer2, 1);
     if (orY1<orY2) {  mLayerManager.insert(box1.mBox, 4); mLayerManager.insert(box1.mSplash, 4); }
              else  {  mLayerManager.insert(box1.mBox, 1); mLayerManager.insert(box1.mSplash, 1); };
     break;
    case 2 :
     orY1=serfer1.mSerfer.getY()+10;             orY2=serfer2.mSerfer.getY()+10;
     mLayerManager.remove(serfer1.mSerfer);      mLayerManager.remove(serfer1.mSerfer2);
     mLayerManager.remove(serfer2.mSerfer);      mLayerManager.remove(serfer2.mSerfer2);
     mLayerManager.remove(boa.mBoa1);            mLayerManager.remove(boa.mBoa2);
     mLayerManager.remove(boa.mBoa3);            mLayerManager.remove(boa.mBoa4);
     mLayerManager.remove(boa.mBoa5);            mLayerManager.remove(boa.mBoa6);
     ordY[1]=boa.mBoa1.getY()+15;        ordY[2]=boa.mBoa2.getY()+15;     ordY[3]=boa.mBoa3.getY()+15;
     ordY[4]=boa.mBoa4.getY()+15;        ordY[5]=boa.mBoa5.getY()+15;     ordY[6]=boa.mBoa6.getY()+15;
     ordY[7]=serfer1.mSerfer.getY()+20;  ordY[8]=serfer2.mSerfer.getY()+20;
     for (int j=1 ; j<9 ; j++) { ordA[j]=minimoAY();}
     for (int jh=1 ; jh<9 ; jh++)
      {
       switch (ordA[jh]) {
        case 1 : mLayerManager.insert(boa.mBoa1, 1); break;
        case 2 : mLayerManager.insert(boa.mBoa2, 1); break;
        case 3 : mLayerManager.insert(boa.mBoa3, 1); break;
        case 4 : mLayerManager.insert(boa.mBoa4, 1); break;
        case 5 : mLayerManager.insert(boa.mBoa5, 1); break;
        case 6 : mLayerManager.insert(boa.mBoa6, 1); break;
        case 7 : mLayerManager.insert(serfer1.mSerfer, 1);   mLayerManager.insert(serfer1.mSerfer2, 1); break;
        case 8 : mLayerManager.insert(serfer2.mSerfer, 1);   mLayerManager.insert(serfer2.mSerfer2, 1); break;
       };
      }


     break;
    case 3 :
     orY1=squalo.mSqualo.getY()+10;              orY2=serfer1.mSerfer.getY()+26;
     mLayerManager.remove(squalo.mSqualo);
     mLayerManager.remove(serfer1.mSerfer);      mLayerManager.remove(serfer1.mSerfer2);
     mLayerManager.insert(serfer1.mSerfer, 1);   mLayerManager.insert(serfer1.mSerfer2, 1);
     if (orY1<orY2) {  mLayerManager.insert(squalo.mSqualo, 4);}
              else  {  mLayerManager.insert(squalo.mSqualo, 1);};
     break;
   }
  }


  private void ControlloRegoleGioco(){
   switch (GiocoInCorso) {
    case 1 :
      riordina(1);
      int dist = distanzaQuadraSerferBox(serfer1,box1);
      if (box1.mBox.isVisible()) { TimeBox--;
        if (TimeBox<=0)
        { FinitoGiocoBox=true;
          switch (modalita) {
           case 1 : if (gInterface.recordG1E<PuntiBox) { gInterface.recordG1E=PuntiBox; gInterface.saveRecord(); isRecord=true;};
            break;
           case 2 : if (gInterface.recordG1M<PuntiBox) { gInterface.recordG1M=PuntiBox; gInterface.saveRecord(); isRecord=true;};
            break;
           case 3 : if (gInterface.recordG1H<PuntiBox) { gInterface.recordG1H=PuntiBox; gInterface.saveRecord(); isRecord=true;};
            break;
          }
         break;
        }
        if (dist< 300) {
          box1.mBox.setVisible(false);  box1.mSplash.setVisible(true); // acchiappa e relativa logica.
          fototimeinvisibileBox1=timeinvisibileFix;
          PuntiBox++;
        }
       } else
       { fototimeinvisibileBox1--; if (fototimeinvisibileBox1<=0) { box1.NuovaPosizione(); }; // rimettere in gioco
       };
     break;
    case 2 :
      riordina(2);
      serfer2.next();
    break;
    case 3 :
      riordina(3);
      int distShark = distanzaQuadraSerferSqualo();
      if (distShark< 240) {
        FinitoGiocoShark=true;
        switch (modalita) {
         case 1 : if (gInterface.recordG3E<(TempoTotShark/100))
                 { gInterface.recordG3E=(TempoTotShark/100); gInterface.saveRecord(); isRecord=true;};
          break;
         case 2 : if (gInterface.recordG3M<(TempoTotShark/100))
                 { gInterface.recordG3M=(TempoTotShark/100); gInterface.saveRecord(); isRecord=true;};
          break;
         case 3 : if (gInterface.recordG3H<(TempoTotShark/100))
                 { gInterface.recordG3H=(TempoTotShark/100); gInterface.saveRecord(); isRecord=true;};
          break;
        }
      }
     break;
    default : break;
   }
  }



 protected void pointerPressed(int x,int y) {
    gMenu.premutoPuntatore(x,y);
 }

 protected void pointerDragged(int x,int y) {
    gMenu.DraggedPuntatore(x,y);
 }

 protected void pointerReleased(int x,int y) {
    gMenu.lasciatoPuntatore(x,y);
 }

 public void azioneFire(int indice) {
            switch (indice) {
             case 1 :  AttivaContinue(); infaseMenu=false;    break;                             // Continue
             case 2 :  infaseMenu=false; AttivaNuovoGioco(1); break;                             // Boxes
             case 3 :  infaseMenu=false; AttivaNuovoGioco(2); break;                             // Race
             case 4 :  infaseMenu=false; AttivaNuovoGioco(3); break;                             // Shark
             case 5 :  if (Suono) { Suono=false;} else { Suono=true;}; gInterface.saveRecord();
                       if (!Suono){ try {gInterface.player.stop();}  catch ( Exception e){};};
                       if (Suono) {
                        try { gInterface.player.setMediaTime(0);
                              gInterface.player.start();}  catch ( Exception e){};};
                        break;  // suono On/Off
             case 6 :  modalita++; if (modalita>3) { modalita=1;}; gInterface.saveRecord(); break;      // level ( easy medium hard)
             case 7 :  AttivaInfo();   break;           // help
             case 8 :  gMenu.parentW.destroyApp(true);   gMenu.parentW.notifyDestroyed(); break; // exit
            }

 }


 protected void keyPressed(int keyCode){
  if (keyCode==-7) { premuto7(); };
  if (keyCode==-22) { premuto7(); };
 }

 public void ripartemusica(){
  if (Suono) {
   try { gInterface.player.setMediaTime(0);
   gInterface.player.start();}  catch ( Exception e){};
  }
 }

}

