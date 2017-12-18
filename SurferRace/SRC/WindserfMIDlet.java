package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //


//  public void startApp() 
//  public void pauseApp()
//  public void destroyApp(boolean unconditional)


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class WindserfMIDlet extends MIDlet  { // implements CommandListener
  public  Display    mDisplay;
  private MainCanvas mainCanvas;
  private FirstPage  firstpage;
  private boolean    iniziato=false;
  public void startApp() {
   if (!iniziato) {
    long  tempoIni   = System.currentTimeMillis();
    mDisplay = Display.getDisplay(this);
    FirstPage firstpage = new FirstPage(this);   mDisplay.setCurrent(firstpage);
    if (mainCanvas == null) {
      try {  mainCanvas = new MainCanvas(this);    mainCanvas.start();  }
      catch (IOException ioe) { System.out.println(ioe); }
    }
    while  ((System.currentTimeMillis()-tempoIni)<5000) {};
    if (mainCanvas != null) {
     mDisplay.setCurrent(mainCanvas);
    }
    firstpage = null;
   }  else
   {
    if (mainCanvas != null) { mDisplay.setCurrent(mainCanvas); }
    mainCanvas.ripartemusica();
   }
   iniziato=true;
  }



  public void pauseApp() {
  }


  public void destroyApp(boolean unconditional) {
     try {
       if (mainCanvas.gInterface.player!=null) {
       mainCanvas.gInterface.player.stop(); } }
       catch ( Exception e){};
    mainCanvas.stop();
  }




}
