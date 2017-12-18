package PallaNuoto;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  private void createBackground(String backgroundImageName) throws IOException
//  private void createPorte(String ImageName) throws IOException
//  private void createPalla(String fileName)   throws IOException
//  private void createSquadre()   throws IOException
//  protected void keyPressed(int keyCode)
//  protected void azioneFireMenu()
//  protected void azioneFire()
//  protected void StartTorneo()
//  protected void StartContinue()
//  protected void StartAmichevole()
//  public void mettiinfaseMenu()
//  public void mettiinfaseInterface()                                 
//  public void resettaTimeazione()

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;



public class MainCanvas  extends GameCanvas  implements Runnable {  //
  private NuotoMIDlet parentW;
  private boolean mTrucking;
  public  LayerManager mLayerManager;
  private Image        logo=null;

  public  boolean              infaseMenu       = true;
  public  int                  subfaseMenu      = 0;
  public  int                  IndiceMenu       = 3; // 1 torneo;  2 Continue ; 3 amichevole; 4 : Sound;  5=Rules; 6=? 7 = Exit;
  private boolean              premutotastomenu = false;
  public  static final int     MenuChoise       = 6;

  public  int                  w = 176;
  public  int                  h = 108;
  public  boolean              Suono=true;

  public  int                  VolumeSettato = 30;

  private int                  azioneX=0;
  private int                  azioneY=0;

  public  int                  tempopartita;
  public  int                  tempoazionepalla;
  public  long                 tempoOra   ;
  public  long                 tempoIni   ;
  public  int                  DelayFotogramma=50;  //  1 ;  millisecondi tra una foto e l'altra
  public  final int            valuetimepartita=300000; // 300 secondi = 5 minuti  fotogrammi; 300.000
  public  final int            valuetimepalla  = 18000; // 12 secondi;  /DelayFotogramma


  public  GrafMenu             gMenu;
  public  Interface            gInterface;
  public  Info                 info;
  public  PortaObj             porta1;
  public  PortaObj             porta2;

  private SfondoObj            sfondo;
  private int                  counter=0;
  private int                  FramesC=10;
  private int                  keyStates  ;
  private String               NomeSfondo = "/acqua.png";
  private String               NomePorte  = "/porta.png";
  public  boolean              Helped = true;
  public  int                  pl1=0;
  public  int                  pl2=0;
  public  boolean              justStarted=true;
  public  boolean              inTorneo=false;
  public  int                  faseTorneo=0;
  private Graphics             g;
  public  int                  fasecarica=1;
  private boolean              stopped=false;


// *******************   MAIN  **********************************
  public MainCanvas(NuotoMIDlet parent)  throws IOException {
    super(true);
    setFullScreenMode(true);
    parentW = parent;
    w=getWidth();    h=getHeight();
    logo=null;
    try {  logo  = Image.createImage("/logo.png");  }  catch (IOException e) {};
  }

  public void postcreate0(){
    try {
    mLayerManager = new LayerManager();
    mLayerManager.setViewWindow(0, 0, w, h); // 96,0,w,h
    createBackground(NomeSfondo);
    createPorte(NomePorte);
    }  catch (IOException ioe) {  }
  }


  public void postcreate1(Info locinfo,Interface locinterface, GrafMenu locmenu){
     gMenu         = locmenu;
     gInterface    = locinterface;
     info          = locinfo;
     mettiinfaseInterface();
  }

  public void postcreate2(){
  }

  public void postcreate3(){
     mettiinfaseMenu();
     gInterface.azzeragara();
     gInterface.loadRecord();
     gInterface.SuonaButW();
  }


  private void createBackground(String backgroundImageName) throws IOException {
    sfondo = new SfondoObj(this,backgroundImageName);
    mLayerManager.append(sfondo.mSfondo);
  }

  private void createPorte(String ImageName) throws IOException {
    porta1 = new PortaObj(this,ImageName);
    mLayerManager.insert(porta1.mPorta,0);
    porta2 = new PortaObj(this,ImageName);
    mLayerManager.insert(porta2.mPorta,0);
    porta1.mPorta.setRefPixelPosition(w/2, 18);
    porta2.mPorta.setRefPixelPosition(w/2, h-12);
  }






  public void start() {
    mTrucking = true;
    Thread t = new Thread(this);
    t.start();
  }



  public void tasto7(){
    if (!infaseMenu) {mettiinfaseMenu();} else
      { if (subfaseMenu!=0)
           if (subfaseMenu>=10 & subfaseMenu<=12) {} else {subfaseMenu=0;}
               else
           { mTrucking=false; parentW.destroyApp(true); parentW.notifyDestroyed(); };
      };
    premutotastomenu = true;
  }

  public void tastoFire(){
   if (infaseMenu) { azioneFireMenu(); } else {azioneFire();};
   premutotastomenu = true;
  }


  public void passaG(Graphics locg)  { g=locg;}


  public void run() {
    g = getGraphics();
    g.setClip(0,0,w,h);
    while (mTrucking) {
     if (!isShown()) { if (Suono){ try {gInterface.player.stop();}  catch ( Exception e){};}; stopped=true; } else
                     { if (stopped) { if (Suono){ gInterface.SuonaButW(); } stopped=false;  };};

     if (stopped) {continue;};

     if ((h!=getHeight()) | (w!=getWidth())) {
       h=getHeight();        w=getWidth();
       gInterface.cambiaH(w,h);
       info.cambiaH(w,h);
       gMenu.cambiaH(w,h);
       porta2.mPorta.setRefPixelPosition(w/2, h-12);
       gInterface.squadra.portiere1.posiziona100(w/2, h-12);
       mLayerManager.setViewWindow(0, 0, w, h);      g.setClip(0,0,w,h);
       mLayerManager.remove(sfondo.mSfondo);         sfondo = null;
       try { sfondo = new SfondoObj(this,NomeSfondo); mLayerManager.append(sfondo.mSfondo); }  catch ( Exception e){};
     }





      sfondo.next();
      azioneX=0;     azioneY=0;
        keyStates = getKeyStates();
        if (!premutotastomenu)
        {
         if ((keyStates & FIRE_PRESSED)   != 0) { tastoFire(); }          else
         if ((keyStates & GAME_A_PRESSED) != 0) { tasto7(); }          else
         if ((keyStates & GAME_B_PRESSED) != 0) { tasto7(); }          else
//         if ((keyStates & GAME_C_PRESSED) != 0) { tasto7(); }          else
//         if ((keyStates & GAME_D_PRESSED) != 0) { tasto7(); }          else

         if ((keyStates & UP_PRESSED)   != 0) {
           if (infaseMenu) { gMenu.premutoUp();  premutotastomenu = true; } else
           { azioneY=-1; }
          }
          else
          if ((keyStates & DOWN_PRESSED) != 0) {
           if (infaseMenu) { gMenu.premutoDown(); premutotastomenu = true; } else  { azioneY=1; }
          }
          else
         if ((keyStates & LEFT_PRESSED) != 0) {
           if (infaseMenu) { gMenu.premutoLeft(); premutotastomenu = true; } else  { azioneX=-1;   }
         }
         else
          if ((keyStates & RIGHT_PRESSED) != 0) {
             if (infaseMenu) { gMenu.premutoRight(); premutotastomenu = true; } else  { azioneX=1;  }
          }
        }   // premutotastomenu
        SvuotaTastoPremuto();
        if (infaseMenu) { gMenu.paintMenu(g);} else  { gInterface.azione(azioneX,azioneY); gInterface.paintInterface(g);  };
        flushGraphics();
     }
  }


  public void paint(Graphics g) {  // synchronized
   g.setColor(90,132,214);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.setColor(0,0,0);
   g.drawImage(logo,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setColor(0,0,0);
   g.drawString("(c) Copyright 2005"   , getWidth()/2, getHeight()-30,Graphics.BASELINE|Graphics.HCENTER);
   g.drawString("carlomacor.com"   , getWidth()/2, getHeight()-10,Graphics.BASELINE|Graphics.HCENTER);

   g.setColor(0,0,255);
   int hf1=8;   int hf2=9;
   switch (fasecarica) {
    case 1 :
     g.drawLine(getWidth()/2-60,getHeight()-hf1,getWidth()/2-50,getHeight()-hf1);
     g.drawLine(getWidth()/2-60,getHeight()-hf2,getWidth()/2-50,getHeight()-hf2);
     break;
    case 2 :
     g.drawLine(getWidth()/2-60,getHeight()-hf1,getWidth()/2-30,getHeight()-hf1);
     g.drawLine(getWidth()/2-60,getHeight()-hf2,getWidth()/2-30,getHeight()-hf2);
     break;
    case 3 :
     g.drawLine(getWidth()/2-60,getHeight()-hf1,getWidth()/2-10,getHeight()-hf1);
     g.drawLine(getWidth()/2-60,getHeight()-hf2,getWidth()/2-10,getHeight()-hf2);
     break;
    case 4 :
     g.drawLine(getWidth()/2-60,getHeight()-hf1,getWidth()/2+20,getHeight()-hf1);
     g.drawLine(getWidth()/2-60,getHeight()-hf2,getWidth()/2+20,getHeight()-hf2);
     break;
    case 5 :
     g.drawLine(getWidth()/2-60,getHeight()-hf1,getWidth()/2+40,getHeight()-hf1);
     g.drawLine(getWidth()/2-60,getHeight()-hf2,getWidth()/2+40,getHeight()-hf2);
     break;
    case 6 :
     g.drawLine(getWidth()/2-60,getHeight()-hf1,getWidth()/2+60,getHeight()-hf1);
     g.drawLine(getWidth()/2-60,getHeight()-hf2,getWidth()/2+60,getHeight()-hf2);
     break;
    case 7 :
     g.drawLine(getWidth()/2-60,getHeight()-hf1,getWidth()/2+70,getHeight()-hf1);
     g.drawLine(getWidth()/2-60,getHeight()-hf2,getWidth()/2+70,getHeight()-hf2);
     break;
     }
  }


  public void stop() {
  }


 protected void SvuotaTastoPremuto() {
   if ((keyStates & LEFT_PRESSED) == 0) {
   if ((keyStates & RIGHT_PRESSED) == 0) {
   if ((keyStates & UP_PRESSED) == 0) {
   if ((keyStates & DOWN_PRESSED) == 0) {
   if ((keyStates & FIRE_PRESSED) == 0) {
   if ((keyStates & GAME_A_PRESSED) == 0) {
   if ((keyStates & GAME_B_PRESSED) == 0) {
   if ((keyStates & GAME_C_PRESSED) == 0) {
   if ((keyStates & GAME_D_PRESSED) == 0) {  premutotastomenu = false;
   } } } } } } } } }
 }




 protected void azioneFireMenu() {
  switch ( subfaseMenu ) {
   case 0 :
    switch (IndiceMenu) {
     case 1 : subfaseMenu=3;  gMenu.Numsel=0;    break;        // vado in fase torneo
     case 2 : StartContinue();                   break;
     case 3 : subfaseMenu=4;  gMenu.Numsel=0;    break;        // vado in fase amichevole
     case 4 :  if (Suono) { Suono=false;} else { Suono=true;};   gInterface.saveRecord();
               if (!Suono){ try {gInterface.player.stop();}  catch ( Exception e){};};
               if (Suono) { try { gInterface.player.setMediaTime(0); gInterface.player.start();}
                                                             catch ( Exception e){};};
                                                 break;  // suono On/Off
     case 5 : subfaseMenu=2;  gMenu.Numsel=0;    break;  // vado in fase Help ?
     case 6 : subfaseMenu=1;                     break;  // vado in fase info
     case 7 : // parentW.destroyApp(true);
              // parentW.notifyDestroyed();
               break;  // exit
     default:                                    break;
    }
    break;
   case 1 : subfaseMenu=0;                       break;   // in fase info
   case 2 : subfaseMenu=0;                       break;   // in fase info
   case 3 : gMenu.azionemenuTorneo();            break;   // in fase torneo
   case 4 : gMenu.azionemenuFriend();            break;   // in fase amichevole
   case 5 : gMenu.azionemenuTorneo();            break;   // in fase torneo

   case 10: gMenu.azionemenuTorneo4();           break;   // in fase torneo
   case 11: gMenu.azionemenuTorneo2();           break;   // in fase torneo
   case 12: gMenu.azionemenuTorneo1();           break;   // in fase torneo



  }
 }


 protected void azioneFire() {
  gInterface.fired();
 }


 public void StartPartitaTorneo(){
  IndiceMenu=2;
  subfaseMenu=0;
  gInterface.squadra.iniziotempo(1);
  gInterface.azzeragara();
  mettiinfaseInterface();
  justStarted=false;
  gInterface.squadra.annullaaspettapalla();
 }


 protected void StartTorneo(){
  inTorneo=true;
  faseTorneo=0;
  StartPartitaTorneo();
}

 protected void StartContinue(){
  if (!justStarted) { mettiinfaseInterface();};
 }



 protected void StartAmichevole() {
  inTorneo=false;
  faseTorneo=0;
  IndiceMenu=2;
  subfaseMenu=0;
  gInterface.squadra.iniziotempo(1);
  gInterface.azzeragara();
  mettiinfaseInterface();
  justStarted=false;
  gInterface.squadra.annullaaspettapalla();
 }


 public void mettiinfaseMenu(){
  porta1.mPorta.setVisible(false);
  porta2.mPorta.setVisible(false);
  gInterface.squadra.nascondi();
  gInterface.squadra.palla.pallasottoContinue();
  infaseMenu=true;
 }

 public void mettiinfaseInterface(){
  infaseMenu=false;
  porta1.mPorta.setVisible(true);
  porta2.mPorta.setVisible(true);
  gInterface.squadra.mostra();
  gInterface.squadra.palla.riposizionapalla();
  gInterface.squadra.palla.riposizionaAlta();
 }

  public void resettaTimeazione(){
     tempoazionepalla= valuetimepalla;
  };


 protected void keyPressed(int keyCode){
  if (keyCode == - 7) { tasto7(); };
  if (keyCode == -22) { tasto7(); };
  if (keyCode == -10) { tasto7(); };
  if (keyCode == -11) { tasto7(); };
 }

 public void annullalogo() {
  logo=null;
 }

 public void ripartemusica(){
  if (Suono) {
   try { gInterface.player.setMediaTime(0);
   gInterface.player.start();}  catch ( Exception e){};
  }
 }



}

