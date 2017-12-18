package Line5;

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class Line5 extends MIDlet  {

  public  Display    mDisplay;
  private MainCanvas mainCanvas;


  public void startApp() {
    mDisplay = Display.getDisplay(this);
      try {
          long  tempoIni   = System.currentTimeMillis();
        mainCanvas = new MainCanvas(this);
        mDisplay.setCurrent(mainCanvas);
        mainCanvas.postcreate();
          while  ((System.currentTimeMillis()-tempoIni)<3000) {}; // 7000
        mainCanvas.start();
      }
       catch (IOException ioe) {  }
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
