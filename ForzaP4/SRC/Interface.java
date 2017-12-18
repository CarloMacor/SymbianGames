package Forza4;

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.String;
import javax.microedition.lcdui.game.Sprite;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import javax.microedition.rms.*;

import javax.microedition.media.control.VolumeControl;
import javax.microedition.media.Player;
import javax.microedition.media.Manager;
import java.io.*;
import javax.microedition.lcdui.Graphics;
import java.util.Random;




public class Interface {
  private MainCanvas       parent;
  private int              w;
  private int              h;
  private Connect4State    S4;
  private Messaggi         miomsg;
  public  Player           player = null;
  private InputStream      is0    = null;
  private int[][]          PosPedine;
  private final int        VUOTO=2;
  private final int        BLU=1;
  private final int        ROSSO=0;
  public  int              pospedinadamettere=0;
  public  boolean          toccame=true;
  public  boolean          game_over=true;
  private boolean          Thinking = false;
  public  int              livelloH=1;

  private Image            imageP1;
  private Image            imageP2;
  private Image            imageP3;
  private Image            imageP4;
  private Image            imageP5;

  private Image            imageGate;
  private Image            imageGatep;
  private Image            imagepblu;
  private Image            imagepred;
  private int              dimmodgate;
  public  int              offygate=0;
  public  boolean          cancellopronto=false;
  private boolean          chiudicancello=false;
  private boolean          infaseblink=false;
  private int              fotoBlink=0;

  private long             TempoAdesso;
  public  long             TempoFattoMossa;
  private String           ilmessaggio="";
  public  int              indicemessaggio=0;
  public  int              poscharmsg=0;
  private Random           m_r;
  public  boolean          messaggioincorso=false;


   public Interface(MainCanvas parent,int w, int h   ) throws IOException {
    this.parent = parent;   this.w = w;     this.h = h;
    PosPedine   = new int[6][7];
    S4          = new Connect4State();
    miomsg      = new Messaggi();
    imageGate  = Image.createImage("/modgate.png");
    imageGatep = Image.createImage("/modgatep.png");

    imagepblu  = Image.createImage("/pedblu.png");
    imagepred  = Image.createImage("/pedred.png");


    dimmodgate = imageGate.getWidth();

    imageP1 = null;  imageP2 = null;
    imageP3 = null;  imageP4 = null;  imageP5 = null;


    imageP1 = Image.createImage("/p1.png");
    imageP2 = Image.createImage("/p2.png");
    imageP3 = Image.createImage("/p3.png");
    imageP4 = Image.createImage("/p4.png");
    imageP5 = Image.createImage("/p5.png");

    m_r = new Random();

   }




  public boolean lasciapedina() {
   boolean risulta=false;
   boolean pieno=true;
   if (!game_over) {
    if (toccame) {
      for ( int i = 5; i>=0; i--) {
       if (PosPedine[i][pospedinadamettere]==0) {
         PosPedine[i][pospedinadamettere]=1;   toccame = false;
         S4.dropPiece(0, pospedinadamettere); risulta=true; break;
       }
      }
    }
     else
    {
      for ( int i = 5; i>=0; i--) {
       if (PosPedine[i][pospedinadamettere]==0) {
          PosPedine[i][pospedinadamettere]=2;  toccame = true;
          S4.dropPiece(1, pospedinadamettere); risulta=true; break;
       }
      }
    }

      if (S4.isWinner(0)) {game_over=true; S4.winneris = 0;}
      if (S4.isWinner(1)) {game_over=true; S4.winneris = 1;}

    // se pieno game_over=true;
    for ( int i = 5; i>=0; i--) {
     for ( int j = 6; j>=0; j--) {
      if (PosPedine[i][j]==0) {pieno=false; break;}
     }
     if (!pieno) {break;}
    }
    if (pieno) {game_over=true;};
   }

   int rr=0;
   rr=m_r.nextInt() % 10;
   if (rr<0) { rr=-rr;};
   if (!toccame) {indicemessaggio=0;} else {indicemessaggio=10+rr;};

   if (game_over) {
    switch (S4.winneris){
     case 0 :   indicemessaggio=3;   break;
     case 1 :   indicemessaggio=2;   break;
     case 2 :   indicemessaggio=20;  break;
    }
   }

//   if (risulta)  {toccame=!toccame;}
//   if (game_over) {toccame=true; risulta=false;}
   messaggioincorso=true;
   if (!toccame) { poscharmsg=20;} else {poscharmsg=w;};
   return  risulta;
  }



  public void paintInterface(Graphics g){

   disegnacancello(g);
   int diseg=0;
   switch(S4.winneris) {
    case 2 : diseg=0; break;  // ancora in gioco
    case 0 : diseg=2; break;  // vince
    case 1 : diseg=3; break;  // vince ..
   }
   // il papero in gioco
   switch (livelloH) {
    case 1 : g.drawRegion(imageP1,imageP1.getWidth()*diseg/4,0,imageP1.getWidth()/4,imageP1.getHeight(),
                                                   Sprite.TRANS_NONE,w,h,Graphics.BOTTOM|Graphics.RIGHT); break;
    case 2 : g.drawRegion(imageP2,imageP2.getWidth()*diseg/4,0,imageP2.getWidth()/4,imageP2.getHeight(),
                                                   Sprite.TRANS_NONE,0,h,Graphics.BOTTOM|Graphics.LEFT); break;
    case 3 : g.drawRegion(imageP3,imageP3.getWidth()*diseg/4,0,imageP3.getWidth()/4,imageP3.getHeight(),
                                                   Sprite.TRANS_NONE,w,h,Graphics.BOTTOM|Graphics.RIGHT); break;
    case 4 : g.drawRegion(imageP4,imageP4.getWidth()*diseg/4,0,imageP4.getWidth()/4,imageP4.getHeight(),
                                                   Sprite.TRANS_NONE,0,h,Graphics.BOTTOM|Graphics.LEFT); break;
    case 5 : g.drawRegion(imageP5,imageP5.getWidth()*diseg/4,0,imageP5.getWidth()/4,imageP5.getHeight(),
                                                   Sprite.TRANS_NONE,w,h,Graphics.BOTTOM|Graphics.RIGHT); break;
   }

   if (infaseblink & (diseg==0)) {
    diseg=1;
    switch (livelloH) {
     case 1 : g.drawRegion(imageP1,imageP1.getWidth()*diseg/4,0,imageP1.getWidth()/4,imageP1.getHeight(),
                                                    Sprite.TRANS_NONE,w,h,Graphics.BOTTOM|Graphics.RIGHT); break;
     case 2 : g.drawRegion(imageP2,imageP2.getWidth()*diseg/4,0,imageP2.getWidth()/4,imageP2.getHeight(),
                                                    Sprite.TRANS_NONE,0,h,Graphics.BOTTOM|Graphics.LEFT); break;
     case 3 : g.drawRegion(imageP3,imageP3.getWidth()*diseg/4,0,imageP3.getWidth()/4,imageP3.getHeight(),
                                                    Sprite.TRANS_NONE,w,h,Graphics.BOTTOM|Graphics.RIGHT); break;
     case 4 : g.drawRegion(imageP4,imageP4.getWidth()*diseg/4,0,imageP4.getWidth()/4,imageP4.getHeight(),
                                                    Sprite.TRANS_NONE,0,h,Graphics.BOTTOM|Graphics.LEFT); break;
     case 5 : g.drawRegion(imageP5,imageP5.getWidth()*diseg/4,0,imageP5.getWidth()/4,imageP5.getHeight(),
                                                    Sprite.TRANS_NONE,w,h,Graphics.BOTTOM|Graphics.RIGHT); break;
    }
   }

    if (messaggioincorso) {
      ilmessaggio=miomsg.dammimsg(livelloH,indicemessaggio);
      g.setColor(0,0,0);
      g.fillRect(0,h-(8+parent.Item_fontB.getHeight()),w,parent.Item_fontB.getHeight());
      g.setColor(255,255,0);
      g.drawString( ilmessaggio  , poscharmsg, h-12,Graphics.BASELINE|Graphics.LEFT);
      if (toccame | (indicemessaggio>0)) {poscharmsg=poscharmsg-6;};
      if (poscharmsg< (-parent.Item_fontB.stringWidth(ilmessaggio))) {messaggioincorso=false;};
     }


   fotoBlink--;
   if (fotoBlink<=0) { infaseblink=!infaseblink;
    if (infaseblink) {fotoBlink= (m_r.nextInt() % 20);} else {fotoBlink=(m_r.nextInt() % 120);}
   }

   if (!toccame & cancellopronto ) {
      if (!messaggioincorso) {
       TempoAdesso=System.currentTimeMillis();
       if ((TempoAdesso-TempoFattoMossa)>1000)  {repensamossa(); };
      } else
      {
       if (indicemessaggio!=1 ) {
        TempoAdesso=System.currentTimeMillis();
        if ((TempoAdesso-TempoFattoMossa)>1000)  {repensamossa(); };
       }
      }
   };

  }


  public void cambiaH(int newW,int newH){
   w = newW;  h = newH;
  }


    public void repensamossa() {
     if (!game_over) {
      Thinking = true;
       parent.repaint();
       if (toccame)  { ProvaSu(0,livelloH);} { ProvaSu(1,livelloH);}
       lasciapedina();
      Thinking = false;
     } 
    }



    private void ProvaSu(int player,int level ) {
      int bestXPos = -1, goodness = 0, bestWorst = -30000;  int numOfEqual = 0;
    // Simulate a drop in each of the columns
    for (int i = 0; i < 7; i++) {
      Connect4State tempState = new Connect4State(S4);
      // If column is full, move on
      if (tempState.dropPiece(player, i) < 0) continue;
      // If this drop wins the game, then cool
      if (tempState.isWinner(player)) { bestWorst = 25000;  bestXPos = i; }
      // Otherwise, look ahead to see how good it is
      else  goodness = tempState.evaluate(player, level, 1, -30000, -bestWorst);
      // If this move looks better than previous moves, remember it
      if (goodness > bestWorst) { bestWorst = goodness; bestXPos = i; numOfEqual = 1; }

      // If two moves are equally good, make a random choice
      if (goodness == bestWorst) {
        numOfEqual++;
        if (Math.abs(m_r.nextInt()) % 10000 <  (10000 / numOfEqual))
        bestXPos = i;
      }


    }
    // Drop the piece in the best column
    if (bestXPos >= 0) {  pospedinadamettere = bestXPos; }
    }


  public void abbassacancello(){
   offygate=-100;
   cancellopronto=false;
   chiudicancello=true;
  }

  public void alzacancello(){
   cancellopronto=false;
   chiudicancello=false;
  }


  public void disegnacancello(Graphics g){
   int offx=5;
   int offy=20;

   int baseoffxtit=(w-parent.imagesfondo2.getWidth())/2;   if (baseoffxtit<0) {baseoffxtit=0;};
   int baseoffytit=(h-parent.imagesfondo2.getHeight())/2;  if (baseoffytit<0) {baseoffytit=0;};

   switch (livelloH) {
    case 1: case 3: case 5 : offx=5+baseoffxtit; break;
    case 2: case 4:          offx=w-(5+7*dimmodgate)-baseoffxtit; break;
   }
   offy=offygate;
   if (chiudicancello) {
     if (offygate<(20+baseoffytit)) {offygate=offygate+5; };
     if (offygate>=(20+baseoffytit)) {offygate=20+baseoffytit; cancellopronto=true;};
    }
    else
    {
     if (offygate>-100) {offygate=offygate-5;};
     if (offygate<=-100) {
       if (S4.winneris==0) {
        if (parent.posizionenelcastello<parent.maxfasicastello) { parent.posizionenelcastello++; };
        parent.fasemenu=2; // parent.FASECASTELLO;
       };
       if ((S4.winneris==1) | (S4.winneris==2)) {
        resettapedine();  toccame = true; abbassacancello();
       }; // rifare partita
     };
    }
   for ( int i=0; i<7; i++) {
    for ( int j=0; j<6; j++) {
     g.drawImage(imageGate,offx+i*dimmodgate,offy+j*dimmodgate,Graphics.TOP|Graphics.LEFT);
     if (S4.board[i][5-j]==ROSSO)  {
      g.drawImage(imagepred,offx+i*dimmodgate,offy+j*dimmodgate,Graphics.TOP|Graphics.LEFT);
     }
     if (S4.board[i][5-j]==BLU)    {
      g.drawImage(imagepblu,offx+i*dimmodgate,offy+j*dimmodgate,Graphics.TOP|Graphics.LEFT);
     }
    }
   }
   for ( int i=0; i<7; i++) {
     g.drawImage(imageGatep,offx+i*dimmodgate,offy+6*dimmodgate,Graphics.TOP|Graphics.LEFT);
   }

   if (!game_over) {
     if (toccame) { g.drawImage(imagepred,offx+pospedinadamettere*dimmodgate,offy-dimmodgate,Graphics.TOP|Graphics.LEFT);}
             else { g.drawImage(imagepblu,offx+pospedinadamettere*dimmodgate,offy-dimmodgate,Graphics.TOP|Graphics.LEFT);}
   }
  }


    public void resettapedine() {
     game_over = false;
     S4.winneris = 2;
     for (int i = 0; i < 6; i++) { for (int j =0; j< 7; j++)
      {
       PosPedine[i][j]=0;
       S4.board[j][i]=2;
      }};
    // Initialize the scores
     for (int i = 0; i < 2; i++) { for (int j = 0; j < S4.winPlaces; j++) S4.score[i][j] = 1; }
     S4.numPieces = 0;
     toccame = false;
     indicemessaggio=1;
     messaggioincorso=true;
     poscharmsg=w/2;
    }


  public void SuonaButW(){
   String ctype  = "audio/midi";
    player = null;
     try {
       is0 = getClass().getResourceAsStream("/sound.mid");
       player = Manager.createPlayer(is0, ctype);
       player.setLoopCount(-1);
        player.start();
        VolumeControl vc = (VolumeControl)player.getControl("VolumeControl");
        vc.setLevel(25);  // al nokia bene il 25  // 80 per Motorola V3
       if (!parent.Suono){ player.stop();};
     } catch (Exception ex) {};
  }



    public void loadRecord() {
     int locvalue=0;
     try {
      RecordStore rs = null;      rs = RecordStore.openRecordStore("FP4",false);
      ByteArrayInputStream  bin  = null;      DataInputStream       din  = null;
      byte[]                data = null;      data = rs.getRecord(1);
      bin = new ByteArrayInputStream(data);   din = new DataInputStream(bin);
      locvalue = din.readInt();
      if (locvalue==0) { parent.Suono=false;} else {  parent.Suono=true; };
      din.close();      bin.close();      rs.closeRecordStore();      rs = null;
     }
     catch ( Exception e){
      try{
       RecordStore rs = null;     rs = RecordStore.openRecordStore("FP4",true);
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       DataOutputStream      dout = new DataOutputStream(bout);
       byte[] data = null;
       locvalue = 0;  dout.writeInt(locvalue);
       data = bout.toByteArray();     rs.addRecord(data,0,data.length);
       bout.reset();     rs.closeRecordStore();    rs = null;   dout.close();   bout.close();
       }   catch ( Exception e2){}
     }
    }

    public void saveRecord() {
     int locvalue=0;
      try{
       RecordStore rs = null;
       rs = RecordStore.openRecordStore("FP4",true);
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       DataOutputStream      dout = new DataOutputStream(bout);
       byte[] data = null;
       if (parent.Suono) {locvalue = 1;} else {locvalue = 0;}   dout.writeInt(locvalue);
       data = bout.toByteArray();
       rs.setRecord(1,data,0,data.length);
       bout.reset();     rs.closeRecordStore();    rs = null;   dout.close();   bout.close();
      }   catch ( Exception e2){}
    }



}



