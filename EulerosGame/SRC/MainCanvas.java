package Euleros;

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Calendar.*;


public class MainCanvas  extends GameCanvas  implements Runnable {  //
  private EulerosMIDlet parentW;
  private boolean mTrucking;
  private LayerManager mLayerManager;
  public         int   IndSchema=1;
  private final  int   MaxSchema=100;

  public  boolean              infaseMenu       = true;
  public  boolean              infaseInfo       = false;
  public  boolean              infaseRules      = false;
  public  int                  IndiceMenu       = 3; // 1 setLevel;  2 Continue ; 3 New; 4 : Sound;  5=Rules; 6=? 7 = Exit;
  private boolean              premutotastomenu = false;
  private static final int     MenuChoise       = 6;

  public  int                  w = 176;
  public  int                  h = 108;
  public  boolean              Suono=true;

  public  boolean              conPointer = false;
  public  int                  VolumeSettato = 25;


  public  GrafMenu             gMenu;
  public  Interface            gInterface;
  private SfondoObj            sfondo;


  public  long                 TempoiniRace;
  public  long                 tempoOra   ;
  public  long                 tempoIni   ;
  private int                  keyStates  ;
  public  String               QIniziati="";

  private static final String  NomeSfondo = "/sfondo.png";
  public boolean               Helped = true;

  private boolean              DatiCaricati=false;


// *******************   MAIN  **********************************
  public MainCanvas(EulerosMIDlet parent)  throws IOException {
    super(true);
    setFullScreenMode(true);
    parentW = parent;
    w=getWidth();    h=getHeight();
    mLayerManager = new LayerManager();
    mLayerManager.setViewWindow(0, 0, w, h); // 96,0,w,h
    createBackground(NomeSfondo);
    conPointer=(hasPointerEvents() & hasPointerMotionEvents());
    gMenu         = new GrafMenu(this,w,h);
    gInterface    = new Interface(this,w,h);

//    if (!parentW.firstpage.Firstlunc)
     { gInterface.ApriEntrata(); DatiCaricati=true; };

  }




  private void createBackground(String backgroundImageName) throws IOException {
    sfondo = new SfondoObj(this,backgroundImageName);
    mLayerManager.append(sfondo.mSfondo);
  }



  public void start() {
    mTrucking = true;
    Thread t = new Thread(this);
    t.start();
  }


  private void tastoFire(){
           if (infaseMenu) { azioneFireMenu(); } else {azioneFire();};
           premutotastomenu = true;
  }

  public void premuto7(){
      if (infaseMenu)
       {
          if (infaseInfo)  {infaseInfo=false;} else
           if (infaseRules) {infaseRules=false;} else
            { mTrucking=false;}
       } else
        {infaseMenu=true; gInterface.SalvaShemaAttuale();  }
    premutotastomenu = true;
  }

  public void premuto6(){
   if (!infaseMenu) { if (Helped) {Helped=false;} else {Helped=true;};};
   premutotastomenu = true;
  }

  public void run() {
    Graphics g = getGraphics();
    g.setClip(0,0,w,h);
    while (mTrucking) {
     if ((h!=getHeight()) | (w!=getWidth())) {
       h=getHeight();        w=getWidth();
       gInterface.cambiaH(h);
       gMenu.cambiaH(h);
       mLayerManager.setViewWindow(0, 0, w, h);      g.setClip(0,0,w,h);
       mLayerManager.remove(sfondo.mSfondo);         sfondo = null;
       try { sfondo = new SfondoObj(this,NomeSfondo); mLayerManager.append(sfondo.mSfondo); }  catch ( Exception e){};
     }
       sfondo.next();
        keyStates = getKeyStates();


//  lastTasto=keyStates;


        if (!premutotastomenu)
        {
         if ((keyStates & FIRE_PRESSED)   != 0) { tastoFire(); }          else
         if ((keyStates &  512) != 0) { premuto6(); }          else
         if ((keyStates & 1024) != 0) { premuto7(); }          else
         if ((keyStates & UP_PRESSED)   != 0) {
           if (infaseMenu) {
            if (infaseInfo)  {} else
             if (infaseRules) {
               gMenu.gInfo.rigaattiva--; if (gMenu.gInfo.rigaattiva<0) {gMenu.gInfo.rigaattiva=0;}; } else
             {
              switch (IndiceMenu) {
               case 1 : IndSchema ++ ; if (IndSchema>=MaxSchema) {IndSchema=1;}; break;
               case 5 : case 6 : IndiceMenu = 3;     break;
              }
             }

           } else { gInterface.curY--; if (gInterface.curY<0) {gInterface.curY=0;}; }
           premutotastomenu = true;
          }
          else
          if ((keyStates & DOWN_PRESSED) != 0) {
           if (infaseMenu) {
            if (infaseInfo)  {} else
             if (infaseRules) {
               gMenu.gInfo.rigaattiva++; if (gMenu.gInfo.rigaattiva>10)
                   {gMenu.gInfo.rigaattiva=gMenu.gInfo.maxlinehelp;}; } else
             {
              switch (IndiceMenu) {
               case 1 : IndSchema -- ; if (IndSchema<1) {IndSchema=99;};   break;
               case 2 : case 3 : IndiceMenu = IndiceMenu+3; break;
              }
             }
           } else { gInterface.curY++; if (gInterface.curY>8) {gInterface.curY=8;}; }
           premutotastomenu = true;
          }
          else
         if ((keyStates & LEFT_PRESSED) != 0) {
             if (infaseMenu) {
              if (infaseInfo)  {} else
               if (infaseRules) {} else
               {
                IndiceMenu = IndiceMenu-1;   gMenu.parziall=0;
                if ((IndiceMenu>0) & (IndiceMenu<4)) {gMenu.last1riga=IndiceMenu;};
                if ((IndiceMenu>3) & (IndiceMenu<7)) {gMenu.last2riga=IndiceMenu;};
                if (IndiceMenu==2) { if (QIniziati.charAt(IndSchema)=='0') {IndiceMenu=1;}; };
                if (IndiceMenu==4) { {IndiceMenu=3;}; };
                if (IndiceMenu<1) {IndiceMenu=MenuChoise;};
               }
             } else { gInterface.curX--; if (gInterface.curX<0) {gInterface.curX=0;}; }
            premutotastomenu = true;
         }
         else
          if ((keyStates & RIGHT_PRESSED) != 0) {
             if (infaseMenu) {
              if (infaseInfo)  {} else
               if (infaseRules) {} else
               {
                IndiceMenu = IndiceMenu+1;   gMenu.parziall=0;
                 if ((IndiceMenu>0) & (IndiceMenu<4)) {gMenu.last1riga=IndiceMenu;};
                 if ((IndiceMenu>3) & (IndiceMenu<7)) {gMenu.last2riga=IndiceMenu;};
                 if (IndiceMenu>MenuChoise) {IndiceMenu=1;};
                 if (IndiceMenu==2) { if (QIniziati.charAt(IndSchema)=='0') {IndiceMenu=3;}; };
                 if (IndiceMenu==4) { {IndiceMenu=5;}; };
               }
             } else { gInterface.curX++; if (gInterface.curX>8) {gInterface.curX=8;}; }
           premutotastomenu = true;
          }
        }   // premutotastomenu



        SvuotaTastoPremuto();
        mLayerManager.paint(g, 0, 0);
        if (infaseMenu) { gMenu.paintMenu(g);} else  { gInterface.paintInterface(g);};


        flushGraphics();
     }

     gInterface.SalvaUscita();
     parentW.notifyDestroyed();

  }





  public void stop() {
    mTrucking = false;
  }



 protected void SvuotaTastoPremuto() {
   if (keyStates  == 0) {premutotastomenu = false;};
 }




 protected void azioneFireMenu() {
  if (infaseInfo) { infaseInfo=false;} else
  if (infaseRules) { infaseRules=false;} else
  {
   switch (IndiceMenu) {
    case 1 : IndSchema ++ ; if (IndSchema>=MaxSchema) {IndSchema=1;}; break;
    case 2 : gInterface.DefinisciQuadro(IndSchema,0);  break;
    case 3 : gInterface.DefinisciQuadro(IndSchema,1);  IndiceMenu=2; break;
    case 4 : if (Suono) { Suono=false;} else { Suono=true;};
    case 5 : infaseRules=true; break;
    case 6 : infaseInfo=true;  break;
    case 7 : mTrucking=false;  break;
    default : break;
   }
  }
 }


 protected void azioneFire() {
  gInterface.Fired();
 }

 protected void keyPressed(int keyCode){
  if (keyCode==-7)  { premuto7(); };
  if (keyCode==-22) { premuto7(); };
  if (keyCode==-11) { premuto7(); };
  if (keyCode==-6)  { premuto6(); };
  if (keyCode==-21) { premuto6(); };
  if (keyCode==-10) { premuto6(); };

 }


}

