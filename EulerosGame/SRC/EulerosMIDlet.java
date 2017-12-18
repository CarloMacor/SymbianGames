package Euleros;

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class EulerosMIDlet extends MIDlet { // implements CommandListener

  public  Display    mDisplay;
  private MainCanvas mainCanvas;
  public  FirstPage  firstpage;

  public void startApp() {

    long  tempoIni   = System.currentTimeMillis();
    mDisplay = Display.getDisplay(this);
    FirstPage firstpage = new FirstPage(this);
    mDisplay.setCurrent(firstpage);
    if (mainCanvas == null) {
      try {  mainCanvas = new MainCanvas(this);  mainCanvas.start();  }
      catch (IOException ioe) { System.out.println(ioe); }
    }
    while  ((System.currentTimeMillis()-tempoIni)<5000) {};
    if (mainCanvas != null) { mDisplay.setCurrent(mainCanvas);  firstpage = null;
    }
  }


  public void pauseApp() {}


  public void destroyApp(boolean unconditional) {
    mainCanvas.stop();
  }


//  public void commandAction(Command cmd, Displayable disp)
//  {
//    if (cmd == menuCommand)    { mainCanvas.premuto7(); }
//  }




}
