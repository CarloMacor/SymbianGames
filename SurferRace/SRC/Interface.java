package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public Interface(MainCanvas parent,int w, int h, WindserfMIDlet parentWind  ) throws IOException
//  public void paintInterface(Graphics g)
//  public void cambiaH(int newH)
//  public void DisegnaBanane(Graphics g)
//  public void disegnafrecciaback(Graphics g)
//  public void loadRecord()
//  public void saveRecord()
//  public void SuonaButW()



import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.String;
import javax.microedition.lcdui.game.Sprite;
import java.io.*;
import javax.microedition.rms.*;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.media.*;


public class Interface {
  private  Image Rosa;
  private  Image Barra;
  private  Image Backer;
  private  Image Banane;
  public   Player      player = null;
  private  int   w;
  private  int   h;
  private  int   rigf=0;
  private  int   colf=0;
  private  int   Lbar=0;
  private  int   dx1=0;
  private  int   dy1=0;
  private  int   dx1_1=0;
  private  int   dy1_1=0;
  private  int   dx1_2=0;
  private  int   dy1_2=0;
  private  int   contalampo =5;
  private  int   locvalue =0;
  private  long  locvalueL =0;

  public   int   recordG1E=0;
  public   int   recordG1M=0;
  public   int   recordG1H=0;
  public   long  recordG2E=100000;
  public   long  recordG2M=100000;
  public   long  recordG2H=100000;
  public   long  recordG3E=0;
  public   long  recordG3M=0;
  public   long  recordG3H=0;


  private  final int dpVento=3;
  private  final int dpSerfer=5;
  private  int   offXArrivo=0;
  private  int   LocofferArrive=0;

  private MainCanvas parent;
  public  WindserfMIDlet parentW;

   public Interface(MainCanvas parent,int w, int h, WindserfMIDlet parentWind  ) throws IOException {
    this.parent = parent;   this.w = w;     this.h = h;
    parentW = parentWind;
    Rosa    = Image.createImage("/rosa.png");
    Barra   = Image.createImage("/barra.png");
    Backer  = Image.createImage("/back.png");
    Banane  = Image.createImage("/dir.png");
    offXArrivo = parent.Xarrivo;
   }



  public void paintInterface(Graphics g){
// qui disegnare Rosa venti le freccie del sefista e del vento
    if ((!parent.infaseMenu) | (h>=180))
    {
     g.drawImage(Rosa,w/2+1,h-8,Graphics.BOTTOM | Graphics.HCENTER);
     DisegnaBanane(g);
    }
// Barra time gioco Box
     if ((parent.GiocoInCorso==1) & (!parent.infaseMenu))  {
      g.drawImage(Barra,parent.posBarraTimer,h-20,Graphics.TOP | Graphics.LEFT);
      g.setColor(240,240,0);
      Lbar = ((52*parent.TimeBox)/(parent.TimeBoxini));
      g.fillRect(parent.posBarraTimer+4,h-16,Lbar,8);
     }
// Fine Barra time gioco Box
//  Linee di arrivo alla Race
     if ((parent.GiocoInCorso==2) & (!parent.infaseMenu))  {
     g.setColor(255,212,18);
       LocofferArrive =offXArrivo-parent.offScreenX;
       g.drawLine( LocofferArrive,0,LocofferArrive,parent.h);
       g.drawLine( LocofferArrive+3,0,LocofferArrive+3,parent.h);
     }
// Fine contatore dei frames
    disegnafrecciaback(g);
  }

  public void cambiaH(int newH){
   h = newH;
  }


  public void DisegnaBanane(Graphics g) {

   switch (parent.direzionevento) {
    case 0  :  g.drawRegion(Banane,0*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2+3 -10,10+(parent.h-41)-11, Graphics.TOP | Graphics.LEFT);  break;
    case 1  :  g.drawRegion(Banane,1*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2+7 -10,10+(parent.h-41)-10, Graphics.TOP | Graphics.LEFT);  break;
    case 2  :  g.drawRegion(Banane,2*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2+10-10,10+(parent.h-41)-8 , Graphics.TOP | Graphics.LEFT);  break;
    case 3  :  g.drawRegion(Banane,3*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2+13-10,10+(parent.h-41)-4 , Graphics.TOP | Graphics.LEFT);  break;
    case 4  :  g.drawRegion(Banane,4*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2+13-10,10+(parent.h-41)   , Graphics.TOP | Graphics.LEFT);  break;
    case 5  :  g.drawRegion(Banane,5*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2+12-10,10+(parent.h-41)+5 , Graphics.TOP | Graphics.LEFT);  break;
    case 6  :  g.drawRegion(Banane,6*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2+10-10,10+(parent.h-41)+8 , Graphics.TOP | Graphics.LEFT);  break;
    case 7  :  g.drawRegion(Banane,7*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2+6 -10,10+(parent.h-41)+9 , Graphics.TOP | Graphics.LEFT);  break;
    case  8 :  g.drawRegion(Banane,0*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2+2 -10,10+(parent.h-41)+9 , Graphics.TOP | Graphics.LEFT);  break;
    case  9 :  g.drawRegion(Banane,1*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2-3 -10,10+(parent.h-41)+9 , Graphics.TOP | Graphics.LEFT);  break;
    case 10 :  g.drawRegion(Banane,2*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2-6 -10,10+(parent.h-41)+7 , Graphics.TOP | Graphics.LEFT);  break;
    case 11 :  g.drawRegion(Banane,3*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2-8 -10,10+(parent.h-41)+3 , Graphics.TOP | Graphics.LEFT);  break;
    case 12 :  g.drawRegion(Banane,4*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2-8 -10,10+(parent.h-41)-1 , Graphics.TOP | Graphics.LEFT);  break;
    case 13 :  g.drawRegion(Banane,5*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2-7 -10,10+(parent.h-41)-6 , Graphics.TOP | Graphics.LEFT);  break;
    case 14 :  g.drawRegion(Banane,6*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2-5 -10,10+(parent.h-41)-9 , Graphics.TOP | Graphics.LEFT);  break;
    case 15 :  g.drawRegion(Banane,7*20,0,20,20, Sprite.TRANS_NONE  , parent.w/2-1 -10,10+(parent.h-41)-11, Graphics.TOP | Graphics.LEFT);  break;
   }

    if (parent.serfer1.Fase==0) {
      switch (parent.serfer1.Direzione) {
       case 0  :  g.drawRegion(Banane,0*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2+0 -10,10+(parent.h-41)-12, Graphics.TOP | Graphics.LEFT);  break;
       case 1  :  g.drawRegion(Banane,1*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2+4 -10,10+(parent.h-41)-11, Graphics.TOP | Graphics.LEFT);  break;
       case 2  :  g.drawRegion(Banane,2*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2+9 -10,10+(parent.h-41)-10, Graphics.TOP | Graphics.LEFT);  break;
       case 3  :  g.drawRegion(Banane,3*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2+12-10,10+(parent.h-41)-7,  Graphics.TOP | Graphics.LEFT);  break;
       case 4  :  g.drawRegion(Banane,4*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2+14-10,10+(parent.h-41)-2,  Graphics.TOP | Graphics.LEFT); break;
       case 5  :  g.drawRegion(Banane,5*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2+14-10,10+(parent.h-41)+1,  Graphics.TOP | Graphics.LEFT);  break;
       case 6  :  g.drawRegion(Banane,6*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2+12-10,10+(parent.h-41)+6,  Graphics.TOP | Graphics.LEFT);  break;
       case 7  :  g.drawRegion(Banane,7*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2+10-10,10+(parent.h-41)+9,  Graphics.TOP | Graphics.LEFT);  break;
       case 8  :  g.drawRegion(Banane,0*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2+6 -10,10+(parent.h-41)+10, Graphics.TOP | Graphics.LEFT);  break;
       case 9  :  g.drawRegion(Banane,1*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2+3 -10,10+(parent.h-41)+11, Graphics.TOP | Graphics.LEFT);  break;
       case 10 :  g.drawRegion(Banane,2*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2-2 -10,10+(parent.h-41)+9,  Graphics.TOP | Graphics.LEFT);  break;
       case 11 :  g.drawRegion(Banane,3*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2-5 -10,10+(parent.h-41)+7,  Graphics.TOP | Graphics.LEFT);  break;
       case 12 :  g.drawRegion(Banane,4*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2-9 -10,10+(parent.h-41)+2,  Graphics.TOP | Graphics.LEFT); break;
       case 13 :  g.drawRegion(Banane,5*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2-9 -10,10+(parent.h-41)-3 , Graphics.TOP | Graphics.LEFT);  break;
       case 14 :  g.drawRegion(Banane,6*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2-7 -10,10+(parent.h-41)-7,  Graphics.TOP | Graphics.LEFT);  break;
       case 15 :  g.drawRegion(Banane,7*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2-5 -10,10+(parent.h-41)-9,  Graphics.TOP | Graphics.LEFT);  break;
      }
    }
    else {
      switch (parent.serfer1.Direzione) {
       case 0  :  g.drawRegion(Banane,0*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2+5 -10,10+(parent.h-41)-11, Graphics.TOP | Graphics.LEFT);  break;
       case 1  :  g.drawRegion(Banane,1*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2+9 -10,10+(parent.h-41)-10, Graphics.TOP | Graphics.LEFT);  break;
       case 2  :  g.drawRegion(Banane,2*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2+11-10,10+(parent.h-41)-7,  Graphics.TOP | Graphics.LEFT);  break;
       case 3  :  g.drawRegion(Banane,3*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2+13-10,10+(parent.h-41)-3,  Graphics.TOP | Graphics.LEFT);  break;
       case 4  :  g.drawRegion(Banane,4*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2+13-10,10+(parent.h-41)+2,  Graphics.TOP | Graphics.LEFT); break;
       case 5  :  g.drawRegion(Banane,5*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2+12-10,10+(parent.h-41)+6,  Graphics.TOP | Graphics.LEFT);  break;
       case 6  :  g.drawRegion(Banane,6*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2+9 -10,10+(parent.h-41)+8,  Graphics.TOP | Graphics.LEFT);  break;
       case 7  :  g.drawRegion(Banane,7*20,40,20,20, Sprite.TRANS_NONE  , parent.w/2+4 -10,10+(parent.h-41)+11, Graphics.TOP | Graphics.LEFT);  break;
       case 8  :  g.drawRegion(Banane,0*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2-1 -10,10+(parent.h-41)+10, Graphics.TOP | Graphics.LEFT);  break;
       case 9  :  g.drawRegion(Banane,1*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2-5 -10,10+(parent.h-41)+8,  Graphics.TOP | Graphics.LEFT);  break;
       case 10 :  g.drawRegion(Banane,2*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2-8 -10,10+(parent.h-41)+5,  Graphics.TOP | Graphics.LEFT);  break;
       case 11 :  g.drawRegion(Banane,3*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2-8 -10,10+(parent.h-41),    Graphics.TOP | Graphics.LEFT);  break;
       case 12 :  g.drawRegion(Banane,4*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2-8 -10,10+(parent.h-41)-4,  Graphics.TOP | Graphics.LEFT); break;
       case 13 :  g.drawRegion(Banane,5*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2-6 -10,10+(parent.h-41)-8,  Graphics.TOP | Graphics.LEFT);  break;
       case 14 :  g.drawRegion(Banane,6*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2-3 -10,10+(parent.h-41)-10, Graphics.TOP | Graphics.LEFT);  break;
       case 15 :  g.drawRegion(Banane,7*20,20,20,20, Sprite.TRANS_NONE  , parent.w/2   -10,10+(parent.h-41)-11, Graphics.TOP | Graphics.LEFT);  break;
      }
    }
  }



  public void disegnafrecciaback(Graphics g) {
   if ((parent.GiocoInCorso==2) & (!parent.infaseMenu)) {
    if (parent.serfer1.contafiocchi>0) {
     if (contalampo>=0) {
      g.drawRegion(Backer,0,0,20,20, Sprite.TRANS_NONE, parent.serfer1.mSerfer.getX()-18,
                                                        parent.serfer1.mSerfer.getY()+14, Graphics.VCENTER | Graphics.HCENTER);  }
     contalampo--;
     if (contalampo<-12) {contalampo=5;};
    }
   }
  }



    public void loadRecord() {
     try {
      RecordStore rs = null;
      rs = RecordStore.openRecordStore("Punto",false);
      ByteArrayInputStream  bin  = null;
      DataInputStream       din  = null;
      byte[]                data = null;
      data = rs.getRecord(1);
      bin = new ByteArrayInputStream(data);
      din = new DataInputStream(bin);
      locvalue = din.readInt();
      if (locvalue==0) { parent.Suono=false;} else {  parent.Suono=true; };
      locvalue = din.readInt();      parent.modalita = locvalue;

      locvalue = din.readInt();      recordG1E = locvalue;
      locvalue = din.readInt();      recordG1M = locvalue;
      locvalue = din.readInt();      recordG1H = locvalue;

      locvalueL = din.readLong();      recordG2E = locvalueL;
      locvalueL = din.readLong();      recordG2M = locvalueL;
      locvalueL = din.readLong();      recordG2H = locvalueL;
      locvalueL = din.readLong();      recordG3E = locvalueL;
      locvalueL = din.readLong();      recordG3M = locvalueL;
      locvalueL = din.readLong();      recordG3H = locvalueL;
      din.close();
      bin.close();
      rs.closeRecordStore();
      rs = null;
     }
     catch ( Exception e){
      try{
       RecordStore rs = null;
       rs = RecordStore.openRecordStore("Punto",true);
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       DataOutputStream      dout = new DataOutputStream(bout);
       byte[] data = null;
       locvalue = 0;  dout.writeInt(locvalue);
       locvalue = 1;  dout.writeInt(locvalue);
       locvalue = recordG1E;          dout.writeInt(locvalue);
       locvalue = recordG1M;          dout.writeInt(locvalue);
       locvalue = recordG1H;          dout.writeInt(locvalue);
       locvalueL = recordG2E;          dout.writeLong(locvalueL);
       locvalueL = recordG2M;          dout.writeLong(locvalueL);
       locvalueL = recordG2H;          dout.writeLong(locvalueL);
       locvalueL = recordG3E;          dout.writeLong(locvalueL);
       locvalueL = recordG3M;          dout.writeLong(locvalueL);
       locvalueL = recordG3H;          dout.writeLong(locvalueL);
       data = bout.toByteArray();     rs.addRecord(data,0,data.length);
       bout.reset();     rs.closeRecordStore();    rs = null;   dout.close();   bout.close();
       }   catch ( Exception e2){}
     }
    }




    public void saveRecord() {
      try{
       RecordStore rs = null;
       rs = RecordStore.openRecordStore("Punto",true);
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       DataOutputStream      dout = new DataOutputStream(bout);
       byte[] data = null;
       if (parent.Suono) {locvalue = 1;} else {locvalue = 0;}   dout.writeInt(locvalue);
       locvalue = parent.modalita;    dout.writeInt(locvalue);
       locvalue = recordG1E;          dout.writeInt(locvalue);
       locvalue = recordG1M;          dout.writeInt(locvalue);
       locvalue = recordG1H;          dout.writeInt(locvalue);
       locvalueL = recordG2E;          dout.writeLong(locvalueL);
       locvalueL = recordG2M;          dout.writeLong(locvalueL);
       locvalueL = recordG2H;          dout.writeLong(locvalueL);
       locvalueL = recordG3E;          dout.writeLong(locvalueL);
       locvalueL = recordG3M;          dout.writeLong(locvalueL);
       locvalueL = recordG3H;          dout.writeLong(locvalueL);
       data = bout.toByteArray();
       rs.setRecord(1,data,0,data.length);
       bout.reset();     rs.closeRecordStore();    rs = null;   dout.close();   bout.close();
      }   catch ( Exception e2){}
    }



  public void SuonaButW(){
   String ctype  = "audio/midi";
    InputStream is0    = null;
    player = null;
     try {
       is0 = getClass().getResourceAsStream("/surf.mid");
       player = Manager.createPlayer(is0, ctype);
       player.setLoopCount(-1);
        player.start();
        VolumeControl vc = (VolumeControl)player.getControl("VolumeControl");
        vc.setLevel(parent.VolumeSettato);  // al nokia bene il 25  // 80 per Motorola V3
       if (!parent.Suono){ player.stop();};
     } catch (Exception ex) {};
  }

}



