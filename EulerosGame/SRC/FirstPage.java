package Euleros;

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import javax.microedition.rms.*;



public class FirstPage extends Canvas {

  private EulerosMIDlet  parent;
  private Image          logo;
  public  boolean        Firstlunc=false;

  // Il costruttore
 public FirstPage(EulerosMIDlet parent)  {
   setFullScreenMode(true);
   this.parent = parent;
   try {  logo  = Image.createImage("/logo.png");  }  catch (IOException e) {};
     try {
      RecordStore rs = null;  rs = RecordStore.openRecordStore("Pt2",false);
      rs.closeRecordStore();  rs = null;
     }
     catch ( Exception e){ try{ Firstlunc=true; }  catch ( Exception e2){} }
  }


  // procedura fondamentale: PAINT
  public void paint(Graphics g) {  // synchronized
   g.setColor(90,168,210);
   g.setClip(0,0,getWidth(),getHeight());
   g.fillRect(0,0,getWidth(),getHeight());
   g.setColor(0,0,0);
   g.drawImage(logo,getWidth()/2,getHeight()/2,Graphics.VCENTER | Graphics.HCENTER);
   if (Firstlunc) {
    g.drawString("Prima esecuzione!"   , getWidth()/2, 15, Graphics.BASELINE|Graphics.HCENTER);
    g.drawString("Aspettare 20 secondi..."   , getWidth()/2, 30, Graphics.BASELINE|Graphics.HCENTER);
//    g.drawString("First execution!"   , getWidth()/2, 15, Graphics.BASELINE|Graphics.HCENTER);
//    g.drawString("Wait 20 sec. please.."   , getWidth()/2, 30, Graphics.BASELINE|Graphics.HCENTER);
   };

   g.drawString("(c) Copyright 2005"   , getWidth()/2, getHeight()-30,Graphics.BASELINE|Graphics.HCENTER);
   g.drawString("carlomacor.com"   , getWidth()/2, getHeight()-10,Graphics.BASELINE|Graphics.HCENTER);
  }







}
