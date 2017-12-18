package PallaNuoto;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

import java.io.IOException;                                          
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class NuotoMIDlet extends MIDlet   { // implements CommandListener
  public  Display    mDisplay;
  private MainCanvas mainCanvas;
  private Info       MainInfo;
  private Interface  MainInterface;
  private GrafMenu   MainMenu;
  private SquadraObj MainSquadra;
  private boolean    iniziato=false;

  public void startApp() {                                  
   if (!iniziato){
    mDisplay = Display.getDisplay(this);
      try {
       long  tempoIni   = System.currentTimeMillis();
       mainCanvas=null;   MainInfo=null; MainInterface=null; MainMenu=null;
       MainSquadra=null;
       while (mainCanvas==null) {
        mainCanvas = new MainCanvas(this);
        mDisplay.setCurrent(mainCanvas);
       }
       mainCanvas.postcreate0();
       while (MainInfo==null) {
        MainInfo       = new Info(mainCanvas,mainCanvas.w,mainCanvas.h);
        mainCanvas.fasecarica=2;       mainCanvas.repaint();
       }
       while (MainInterface==null) {
        MainInterface  = new Interface(mainCanvas,mainCanvas.w,mainCanvas.h);
        mainCanvas.fasecarica=3;       mainCanvas.repaint();
       }
       while (MainMenu==null) {
        MainMenu       = new GrafMenu(mainCanvas,mainCanvas.w,mainCanvas.h);
        mainCanvas.fasecarica=4;       mainCanvas.repaint();
       }
       mainCanvas.postcreate1(MainInfo,MainInterface,MainMenu);
        mainCanvas.fasecarica=5;       mainCanvas.repaint();
       mainCanvas.postcreate2();
        mainCanvas.fasecarica=6;       mainCanvas.repaint();
       mainCanvas.postcreate3();
        mainCanvas.fasecarica=7;       mainCanvas.repaint();
       while  ((System.currentTimeMillis()-tempoIni)<7000) {};
       mainCanvas.start();
      }
       catch (IOException ioe) {  }
   }  else
   {
    if (mainCanvas != null) { mDisplay.setCurrent(mainCanvas);  mainCanvas.ripartemusica();}
   }
   iniziato=true;
  }


  public void pauseApp() {}


  public void destroyApp(boolean unconditional) {
     try {
       if (mainCanvas.gInterface.player!=null) {
       mainCanvas.gInterface.player.stop(); } }
       catch ( Exception e){};
    mainCanvas.stop();
  }



}
