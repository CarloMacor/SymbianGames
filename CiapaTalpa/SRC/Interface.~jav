package CiapaTalpa;

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
  public  Player           player  = null;
  public  Player           player1 = null;
  public  Player           player2 = null;
  private InputStream      is0    = null;
  private InputStream      is1    = null;
  private InputStream      is2    = null;
  public  boolean          toccame=true;
  private Image            background;
  private Image            imgbuca;
  private Image            imgtalpa;
  private Image            imgmartello;
  private Image            imggameover;
  private Image            imgstelle;
  private String           nomeimmaginebackground="/fondale.png";
  private String           nomeimmaginebuca      ="/buca.png";
  private String           nomeimmaginetalpa     ="/talpa.png";
  private String           nomeimmaginemartello  ="/martello.png";
  private String           nomeimmaginegameover  ="/gameover.png";
  private String           nomeimmaginestelle    ="/stelle.png";
  private int[][]          posizionibuche;
  private int[]            posizionitalpa;
  private long             tempo1;
  private long             tempo2;
  private int              FotogrammaTalpa=0;
  private int              FotogrammaMartello=0;
  private boolean          Talpainsu     = true;
  private boolean          Martelloingiu = true;
  private boolean          vedotalpa = false;
  private boolean          vedomartello = false;
  private int              postalpa  = 0;
  private Random           m_r;
  private int              Pospointer=4;
  private int              numbuche=9;
  private boolean          Talpacolpita=false;
  private int              Talpeperse=0;
  private int              MaxPerse=10;
  private int              DeltaTimeTalpa=5000;
  private boolean          game_over=false;
  private int              riduzionetimeTalpa=0;

   public Interface(MainCanvas parent,int w, int h   ) throws IOException {
    this.parent = parent;   this.w = w;     this.h = h;
    background  = Image.createImage(nomeimmaginebackground);
    imgbuca     = Image.createImage(nomeimmaginebuca);
    imgtalpa    = Image.createImage(nomeimmaginetalpa);
    imgmartello = Image.createImage(nomeimmaginemartello);
    imggameover = Image.createImage(nomeimmaginegameover);
    imgstelle   = Image.createImage(nomeimmaginestelle);
    posizionibuche = new int[9][2];
    posizionitalpa = new int[9];
    faiposizionibuche();
    tempo1 = System.currentTimeMillis();
    tempo2 = System.currentTimeMillis();
    m_r = new Random();
   }


  public void paintInterface(Graphics g){
   g.setFont(parent.Title_font);
   g.setClip((w-background.getWidth()) /2,(h-background.getHeight()) /2,background.getWidth(),background.getHeight());

   g.drawImage(background,w/2,h/2,Graphics.VCENTER | Graphics.HCENTER);
   for ( int i=0; i<numbuche; i++) {
    g.drawImage(imgbuca,posizionibuche[i][0],posizionibuche[i][1],Graphics.VCENTER | Graphics.HCENTER);
   }
   // punteggi
   String tt=""+parent.score.getScore();
   g.setColor(255,255,0);
   g.drawString(tt , w/4  , h-4   ,Graphics.BASELINE|Graphics.RIGHT);
   tt=""+Talpeperse+" / "+MaxPerse;
   g.drawString(tt , w*3/4, h-4   ,Graphics.BASELINE|Graphics.LEFT);

//   tt="R:"+parent.score.getHighScore();
   tt=""+parent.score.getHighScore();
   g.drawString(tt , w/2  , h-4   ,Graphics.BASELINE|Graphics.HCENTER);


   if (game_over) {
    g.drawImage(imggameover,w/2,h/2,Graphics.VCENTER | Graphics.HCENTER);
    return;
   }

   if (vedotalpa) {
       g.drawRegion(imgtalpa,imgtalpa.getWidth()/7*FotogrammaTalpa ,0, imgtalpa.getWidth()/7,imgtalpa.getHeight(),
                    Sprite.TRANS_NONE, posizionibuche[postalpa][0],posizionitalpa[postalpa], Graphics.VCENTER | Graphics.HCENTER);

      if (Talpainsu) {FotogrammaTalpa++; if (FotogrammaTalpa>=6) {Talpainsu=false;} }
         else
         { FotogrammaTalpa--;
           if (FotogrammaTalpa<=0) {Talpainsu=true; vedotalpa=false;
            if (!Talpacolpita) {Talpeperse++;
             if (Talpeperse>=MaxPerse){game_over=true; parent.score.save(); }
            };
           }
         };

   }

   if (vedomartello) {
     if (FotogrammaMartello<0) {FotogrammaMartello=0; }
       g.drawRegion(imgmartello,imgmartello.getWidth()/4*FotogrammaMartello ,0,
                                imgmartello.getWidth()/4,imgmartello.getHeight(),
                                Sprite.TRANS_NONE,
                                posizionibuche[Pospointer][0]+imgmartello.getWidth()/32,
                                posizionitalpa[Pospointer]-6,
                                Graphics.VCENTER | Graphics.HCENTER);

      if (Martelloingiu) {FotogrammaMartello++;
             if (FotogrammaMartello>=3) {
              Martelloingiu=false;
              if (Pospointer==postalpa)
               {
                 if (vedotalpa) {
                  Talpacolpita=true; parent.score.add(1);
                  if (parent.Suono) { try {  player1.start();} catch ( Exception e){};};
                 }
               }
                else
               { if (parent.Suono) { try { player2.start();} catch ( Exception e){}; };
               }
             } }
             else        {FotogrammaMartello--; if (FotogrammaMartello<0) {Martelloingiu=true; vedomartello=false;};};

   }

    tempo2 = System.currentTimeMillis();

    if ((tempo2-tempo1)>DeltaTimeTalpa){
      if (!vedotalpa) {
        tempo1=tempo2; vedotalpa=true;    Talpacolpita=false;
        postalpa=m_r.nextInt() % numbuche;  if (postalpa<0) { postalpa=-postalpa;};
        DeltaTimeTalpa=DeltaTimeTalpa-riduzionetimeTalpa;
        if (DeltaTimeTalpa<0) {DeltaTimeTalpa=0;};
      }
    }

   if (Talpacolpita) {disegnastelle(g);};
   disegnaPointer(g);

  }

  public void disegnaPointer(Graphics g){
   int xp,yp;
   xp =posizionibuche[Pospointer][0];  yp =posizionibuche[Pospointer][1]-imgtalpa.getHeight()/4;
   g.setColor(255,255,255);
   g.drawLine(xp-6,yp,xp+6,yp);
   g.drawLine(xp,yp-6,xp,yp+6);
   g.drawArc(xp-4,yp-4,8,8,0,360);
  }

  public void disegnastelle(Graphics g){
   int xp,yp;
   xp =posizionibuche[postalpa][0];  yp =posizionibuche[postalpa][1]-imgtalpa.getHeight()/4;
   g.drawImage(imgstelle,xp,yp,Graphics.VCENTER | Graphics.HCENTER);
  }


  private void faiposizionibuche() {
   switch(parent.difficolta) {
    case 1 :
     posizionibuche[0][0]=w/2+imgbuca.getWidth()/8;     posizionibuche[0][1]=h/4;
     posizionibuche[1][0]=w/4-imgbuca.getWidth()/12;    posizionibuche[1][1]=h/2-imgbuca.getHeight()/4;
     posizionibuche[2][0]=w/2;                          posizionibuche[2][1]=h/2;
     posizionibuche[3][0]=w*3/4+imgbuca.getWidth()/12;  posizionibuche[3][1]=h/2+imgbuca.getHeight()/4;
     posizionibuche[4][0]=w/2-imgbuca.getWidth()/8;     posizionibuche[4][1]=h*3/4;
     for (int i=0; i<5; i++) { posizionitalpa[i]=posizionibuche[i][1]-imgtalpa.getHeight()*6/10; }
     numbuche=5;
    break;
    case 2 :
     posizionibuche[0][0]=w/3;     posizionibuche[0][1]=h/4-imgbuca.getHeight()/8;
     posizionibuche[1][0]=w*2/3;   posizionibuche[1][1]=h/4+imgbuca.getHeight()/8;
     posizionibuche[2][0]=w/4;     posizionibuche[2][1]=h/2;
     posizionibuche[3][0]=w*3/4;   posizionibuche[3][1]=h/2;
     posizionibuche[4][0]=w/3;     posizionibuche[4][1]=h*3/4+imgbuca.getHeight()/8;
     posizionibuche[5][0]=w*2/3;   posizionibuche[5][1]=h*3/4-imgbuca.getHeight()/8;
     for (int i=0; i<6; i++) { posizionitalpa[i]=posizionibuche[i][1]-imgtalpa.getHeight()*6/10; }
     numbuche=6;
    break;

    case 3 :
     posizionibuche[4][0]=w/2;     posizionibuche[4][1]=h/2-imgbuca.getHeight()/4;
     posizionibuche[3][0]=w/4;     posizionibuche[3][1]=h/2+imgbuca.getHeight()/4;
     posizionibuche[5][0]=w*3/4;   posizionibuche[5][1]=h/2+imgbuca.getHeight()/4;
     posizionibuche[1][0]=w/2;     posizionibuche[1][1]=h/4-imgbuca.getHeight()/4;
     posizionibuche[0][0]=w/4;     posizionibuche[0][1]=h/4+imgbuca.getHeight()/4;
     posizionibuche[2][0]=w*3/4;   posizionibuche[2][1]=h/4+imgbuca.getHeight()/4;
     posizionibuche[7][0]=w/2;     posizionibuche[7][1]=h*3/4-imgbuca.getHeight()/4;
     posizionibuche[6][0]=w/4;     posizionibuche[6][1]=h*3/4+imgbuca.getHeight()/4;
     posizionibuche[8][0]=w*3/4;   posizionibuche[8][1]=h*3/4+imgbuca.getHeight()/4;
     for (int i=0; i<9; i++) { posizionitalpa[i]=posizionibuche[i][1]-imgtalpa.getHeight()*6/10; }
     numbuche=9;
    break;
   }

  }


  public void cambiaH(int newW,int newH){
   w = newW;  h = newH;
   faiposizionibuche();
  }


  public void SuonaButW(){
   String ctype  = "audio/midi";
    player = null;
    player1 = null;
    player2 = null;
     try {
       is0 = getClass().getResourceAsStream("/hammer.mid");
       player  = Manager.createPlayer(is0, ctype);
       player.setLoopCount(1);

       is1 = getClass().getResourceAsStream("/got.mid");
       player1 = Manager.createPlayer(is1, ctype);
       player1.setLoopCount(1);

       is2 = getClass().getResourceAsStream("/notgot.mid");
       player2 = Manager.createPlayer(is2, ctype);
       player2.setLoopCount(1);

        VolumeControl vc = (VolumeControl)player.getControl("VolumeControl");
        vc.setLevel(80);  // al nokia bene il 25  // 80 per Motorola V3
        vc = (VolumeControl)player1.getControl("VolumeControl");
        vc.setLevel(80);  // al nokia bene il 25  // 80 per Motorola V3
        vc = (VolumeControl)player2.getControl("VolumeControl");
        vc.setLevel(80);  // al nokia bene il 25  // 80 per Motorola V3
       if (!parent.Suono){ player.stop();} else {player.start();};

     } catch (Exception ex) {};
  }



 public void azione(int x, int y, int z) {
  switch(parent.difficolta) {
   case 1 :
    if (x<0) { switch (Pospointer){
               case 2: case 3:  Pospointer--; break;
               case 0: case 4:  Pospointer=1; break;
             }}
    if (x>0) { switch (Pospointer){
               case 1: case 2:  Pospointer++; break;
               case 0: case 4:  Pospointer=3; break;
             }}
    if (y>0) { switch (Pospointer){
                case 2: case 4:  Pospointer=Pospointer-2; break;
                case 1: Pospointer--; break;
                case 3: Pospointer=Pospointer-3; break;
             }}
    if (y<0) { switch (Pospointer){
                case 0: case 2:  Pospointer=Pospointer+2; break;
                case 1:          Pospointer=Pospointer+3; break;
                case 3:          Pospointer=Pospointer+1; break;
             }}
   break;
   case 2 :
    if (x<0) { switch (Pospointer){ case 1: case 3: case 5: Pospointer--; break; }}
    if (x>0) { switch (Pospointer){ case 0: case 2: case 4: Pospointer++; break; }}
    if (y>0) { switch (Pospointer){ case 2: case 3: case 4: case 5: Pospointer=Pospointer-2; break;}}
    if (y<0) { switch (Pospointer){ case 0: case 1: case 2: case 3: Pospointer=Pospointer+2; break;}}
   break;
   case 3 :
    if (x<0) {
     switch (Pospointer){ case 1 : case 4 :case 7 : case 2 : case 5 :case 8 :  Pospointer--; break; }}
    if (x>0) {
     switch (Pospointer){ case 1 : case 4 :case 7 : case 0 : case 3 :case 6 :  Pospointer++; break; }}
    if (y>0) {
     switch (Pospointer){ case 3 : case 4 :case 5 : case 6 : case 7 :case 8 :  Pospointer=Pospointer-3; break; }}
    if (y<0) {
     switch (Pospointer){ case 0 : case 1 :case 2 : case 3 : case 4 :case 5 :  Pospointer=Pospointer+3; break; }}
   break;
  }

  if (z>0) {if (game_over) { parent.tasto7();  } else
                           { if (vedomartello) {} else
                             {  vedomartello=true;
                               if (parent.Suono){
                                try {player.start();} catch ( Exception e){};  }};
                             }
                           }
 }


 public void nuovapartita(){
  faiposizionibuche();
  Talpeperse=0;
  parent.score.reset();
  switch(parent.difficolta) {
   case 1 : MaxPerse=5;  DeltaTimeTalpa=5000; riduzionetimeTalpa=20; break;
   case 2 : MaxPerse=7;  DeltaTimeTalpa=4500; riduzionetimeTalpa=20; break;
   case 3 : MaxPerse=10; DeltaTimeTalpa=4000; riduzionetimeTalpa=20; break;
  }
  pareggiatime();
  game_over=false;
  parent.score.justintrodotto=-1;
 }


 public void pareggiatime(){
  tempo2 = System.currentTimeMillis();
  tempo1 = System.currentTimeMillis();
 }


}



