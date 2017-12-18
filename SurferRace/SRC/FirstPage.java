package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

// public FirstPage(WindserfMIDlet parent)  
// public void paint(Graphics g)



import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class FirstPage extends Canvas {
  private WindserfMIDlet parent;
  private Image          logo;

  // Il costruttore
 public FirstPage(WindserfMIDlet parent)  {
   setFullScreenMode(true);
   this.parent = parent;
   try {  logo  = Image.createImage("/logo.png");  }  catch (IOException e) {};
  }


  // procedura fondamentale: PAINT
  public void paint(Graphics g) {  // synchronized
   g.setColor(24,140,240);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.setColor(0,0,0);
   g.drawImage(logo,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   g.setColor(0,0,0);
   g.drawString("(c) Copyright 2005"   , getWidth()/2, getHeight()-30,Graphics.BASELINE|Graphics.HCENTER);
   g.drawString("carlomacor.com"   , getWidth()/2, getHeight()-15,Graphics.BASELINE|Graphics.HCENTER);
  }







}
