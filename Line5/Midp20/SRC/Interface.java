package Line5;

// private void faisfondo()
// public  void paintInterface(Graphics g)
// public  void azione(int x,int y,int z)
// public  void cambiaH(int newW,int newH)
// public  void SuonaButW()
// public  void loadRecord()
// public  void saveRecord()
// private int  calcolavuote()
// private void mettipallina(int pos, int col)
// private void mettipallinaxy(int x1,int y1, int col)
// private void metti3()
// public  void nuovapartita()
// private void togliesplosione(){
// private boolean testExplosion(){



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
  public  Player           player = null;
  private InputStream      is0    = null;
  public  boolean          game_over=false;
  private Image            sfondo;
  private Image            pallozze;
  private Image            FonteS;
  private Image            imggameover;
  private String           Alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.-: ";
  private int              dimFS;
  private String           nomeimmaginegameover  ="/gameover.png";
  private String           nomeimmaginelocsfondo="/modback.png";
  private String           nomeimmaginepallozze ="/pallozze.png";
  private String           nomeimmaginebordo1   ="/bordo1.png";
  private String           nomeimmaginebordo2   ="/bordo2.png";
  private String           nomeimmaginebordo3   ="/bordo3.png";
  private String           nomeimmaginebordo4   ="/bordo4.png";
  private String           nomeimmaginebordo5   ="/bordo5.png";
  private String           nomeimmaginebordo6   ="/bordo6.png";
  private String           nomeimmaginebordosotto="/bordosotto.png";
  private String           nomeimmaginebordodx  ="/bordodx.png";
  private String           nomeimmaginecasella  ="/casella.png";
  private int[][]          pospalline;
  private boolean[][]      explpalline;
  private int              xc1;
  private int              yc1;
  private int              xppp;
  private int              yppp;
  private int              dimc;
  private Random           m_r;
  private int              curx;
  private int              cury;
  private int              pedx;
  private boolean          inboard=true;
  public  boolean          messapedina=false;
  private int              contamessapedina;
  private int              maxmessapedina=6;
  private boolean          inesplosione=false;
//  public  int              punti;
  private String           tt;

   public Interface(MainCanvas parent,int w, int h   ) throws IOException {
    this.parent = parent;   this.w = w;     this.h = h;
    sfondo    = Image.createImage(w,h);
    pallozze  = Image.createImage(nomeimmaginepallozze);
    FonteS    = Image.createImage("/caratteri.png");
    imggameover = Image.createImage(nomeimmaginegameover);
    dimFS     = FonteS.getHeight()/2;
    faisfondo();
    m_r = new Random();
    pospalline = new int[9][9];
    explpalline= new boolean[9][9];
    nuovapartita();
   }

  private void faisfondo() {
   Image locsfondo;  Image locbordo1;  Image locbordo2;  Image locbordo3;
   Image locbordo4;  Image locbordo5;  Image locbordo6;
   Image locbordosotto;   Image locbordodx;  Image loccasella;
     int xp=0;    int yp=0;
     int iniyp=32; int iniy1=8;

   try {
    sfondo    = null;
    sfondo    = Image.createImage(w,h);
    locsfondo = Image.createImage(nomeimmaginelocsfondo);
    Graphics gh = sfondo.getGraphics();
    while (yp<h) {
     while (xp<w) {
       gh.drawImage(locsfondo,xp,yp,Graphics.LEFT | Graphics.TOP);
       xp=xp+locsfondo.getWidth();
     }
     yp=yp+locsfondo.getHeight(); xp=0;
    }
    locsfondo = null;

    locbordo1 = Image.createImage(nomeimmaginebordo1);
    locbordo2 = Image.createImage(nomeimmaginebordo2);
    locbordo3 = Image.createImage(nomeimmaginebordo3);
    locbordo4 = Image.createImage(nomeimmaginebordo4);
    locbordo5 = Image.createImage(nomeimmaginebordo5);
    locbordo6 = Image.createImage(nomeimmaginebordo6);
    locbordosotto = Image.createImage(nomeimmaginebordosotto);
    locbordodx = Image.createImage(nomeimmaginebordodx);
    loccasella = Image.createImage(nomeimmaginecasella);

    int dimrx=w-(9*locbordo2.getWidth()+2*locbordo3.getWidth());



    xp=dimrx /2;
    yp=iniyp;
    gh.drawImage(locbordo3,xp,yp,Graphics.LEFT | Graphics.TOP);
    gh.drawImage(locbordo6,xp,yp+9*locbordo1.getHeight()+locbordo3.getHeight(),Graphics.LEFT | Graphics.TOP);
    xp=xp+locbordo3.getWidth();
    for (int i=0; i<9; i++) {
     gh.drawImage(locbordo2,xp+i*locbordo2.getWidth(),yp,Graphics.LEFT | Graphics.TOP);
     gh.drawImage(locbordosotto,xp+i*locbordo2.getWidth(),yp+9*locbordo1.getHeight()+locbordo3.getHeight(),Graphics.LEFT | Graphics.TOP);
    }
    gh.drawImage(locbordo4,xp+9*locbordo2.getWidth(),yp,Graphics.LEFT | Graphics.TOP);
    gh.drawImage(locbordo5,xp+9*locbordo2.getWidth(),yp+9*locbordo1.getHeight()+locbordo3.getHeight(),
                Graphics.LEFT | Graphics.TOP);
    xp=dimrx /2;
    yp=yp+locbordo3.getHeight();
    for (int i=0; i<9; i++) {
     gh.drawImage(locbordo1,xp                       ,yp+i*locbordo1.getHeight(),Graphics.LEFT | Graphics.TOP);
     gh.drawImage(locbordodx,xp+9*locbordo2.getWidth()+locbordo3.getWidth(),yp+i*locbordo1.getHeight(),Graphics.LEFT | Graphics.TOP);
    }

    xp=dimrx /2+locbordo3.getWidth();    xc1=xp+1;
    yp=iniyp+locbordo3.getHeight();      yc1=yp+1;
                                         dimc=loccasella.getWidth();
    for (int i=0; i<9; i++) {
     for (int j=0; j<9; j++) {
      gh.drawImage(loccasella,xp+i*loccasella.getWidth(),yp+j*loccasella.getHeight(),Graphics.LEFT | Graphics.TOP);
     }
    }

    yppp=iniy1;
    xppp=(w-8*loccasella.getWidth())/2;
    for (int i=0; i<8; i++) {gh.drawImage(loccasella,xppp+i*loccasella.getWidth(),yppp,Graphics.LEFT | Graphics.TOP);};
    for (int i=0; i<7; i++) {
      gh.drawRegion(pallozze,i*12,0,12,12, Sprite.TRANS_NONE,
                  xppp+i*loccasella.getWidth()+1,yppp+1, Graphics.LEFT | Graphics.TOP);
    }


    locbordo1 = null;    locbordo2 = null;    locbordo3 = null;
    locbordo4 = null;    locbordo5 = null;    locbordo6 = null;
    locbordosotto= null; locbordodx= null;    loccasella= null;
   } catch (Exception ex) {};
  }


  private void togliesplosione(){
    for (int i=0; i<9; i++) { for (int j=0; j<9; j++) {
     if (explpalline[i][j]) {
      pospalline[i][j]=-1;
      explpalline[i][j]=false;
      parent.score.add(1);
     }
    }}
   inesplosione=false;
  }

  private boolean testExplosion(){
   boolean res=false;
   int inicol;
    for (int i=0; i<5; i++) { for (int j=0; j<9; j++) {  // orizzontali
       inicol=pospalline[i][j];
       if (inicol<0) { continue;};
       if (pospalline[i+1][j]==inicol) {
        if (pospalline[i+2][j]==inicol) {
         if (pospalline[i+3][j]==inicol) {
          if (pospalline[i+4][j]==inicol) {
           for (int ik=0; ik<5; ik++) { explpalline[i+ik][j]=true; };
           inesplosione=true;
          }
         }
        }
       }
     }
    }

    for (int i=0; i<9; i++) { for (int j=0; j<5; j++) {  // verticali
       inicol=pospalline[i][j];
       if (inicol<0) { continue;};
       if (pospalline[i][j+1]==inicol) {
        if (pospalline[i][j+2]==inicol) {
         if (pospalline[i][j+3]==inicol) {
          if (pospalline[i][j+4]==inicol) {
           for (int ik=0; ik<5; ik++) { explpalline[i][j+ik]=true;};
           inesplosione=true;
          }
         }
        }
       }
     }
    }

    for (int i=0; i<5; i++) { for (int j=0; j<5; j++) {  // diagonale dx
       inicol=pospalline[i][j];
       if (inicol<0) { continue;};
       if (pospalline[i+1][j+1]==inicol) {
        if (pospalline[i+2][j+2]==inicol) {
         if (pospalline[i+3][j+3]==inicol) {
          if (pospalline[i+4][j+4]==inicol) {
           for (int ik=0; ik<5; ik++) { explpalline[i+ik][j+ik]=true;};
           inesplosione=true;
          }
         }
        }
       }
     }
    }

    for (int i=4; i<9; i++) { for (int j=0; j<5; j++) {  // diagonale sx
       inicol=pospalline[i][j];
       if (inicol<0) { continue;};
       if (pospalline[i-1][j+1]==inicol) {
        if (pospalline[i-2][j+2]==inicol) {
         if (pospalline[i-3][j+3]==inicol) {
          if (pospalline[i-4][j+4]==inicol) {
           for (int ik=0; ik<5; ik++) { explpalline[i-ik][j+ik]=true;};
           inesplosione=true;
          }
         }
        }
       }
     }
    }


   return res;
  }




  public void paintInterface(Graphics g){
   if (messapedina) {
     contamessapedina--;
     if (contamessapedina<=0) {messapedina=false; metti3(); togliesplosione();
        int vuote=calcolavuote();  if (vuote==0){ game_over=true;  parent.score.save();  }
     };
   };

   g.drawImage(sfondo,w/2,h/2,Graphics.VCENTER | Graphics.HCENTER);
   for (int i=0; i<9; i++) {
    for (int j=0; j<9; j++) {
     if (pospalline[i][j]>=0) {
      g.drawRegion(pallozze,pospalline[i][j]*12,0,12,12, Sprite.TRANS_NONE,
                   xc1+i*dimc,yc1+j*dimc, Graphics.LEFT | Graphics.TOP);
     }
    };
   };

   if (game_over) {
    g.drawImage(imggameover,w/2,h/2,Graphics.VCENTER | Graphics.HCENTER);
   }

   if (messapedina) {
     for (int i=0; i<7; i++) {
      g.drawRegion(pallozze,i*12,12,12,12, Sprite.TRANS_NONE, xppp+i*dimc+1,yppp+1,Graphics.LEFT | Graphics.TOP);
    }
   }


   if (inboard) {
    if (pospalline[curx][cury]>=0) {
     g.drawRegion(pallozze,pospalline[curx][cury]*12,12,12,12, Sprite.TRANS_NONE,
                  xc1+curx*dimc,yc1+cury*dimc, Graphics.LEFT | Graphics.TOP);
    } else{ g.setColor(255,255,255); g.drawRect(xc1+curx*dimc+3,yc1+cury*dimc+3,5,5); };
   } else
   {
    // croce in board
     g.setColor(255,255,255);
      g.drawLine(xc1+curx*dimc+1,yc1+cury*dimc+1,xc1+curx*dimc+10,yc1+cury*dimc+10);
      g.drawLine(xc1+curx*dimc+10,yc1+cury*dimc+1,xc1+curx*dimc+1,yc1+cury*dimc+10);
     if (pedx<7) {
     g.drawRegion(pallozze,pedx*12,12,12,12, Sprite.TRANS_NONE,
                  xppp+pedx*dimc+1,yppp+1, Graphics.LEFT | Graphics.TOP);
    } else{
     g.setColor(255,255,255);
     g.drawRect(xppp+pedx*dimc+4,yppp+4,5,5);
    };
   }
   if (inesplosione) {
    for (int i=0; i<9; i++) { for (int j=0; j<9; j++) {
      if (explpalline[i][j]){
       g.setColor(255,255,255);
       g.drawRegion(pallozze,pospalline[i][j]*12,12,12,12, Sprite.TRANS_NONE,
                    xc1+i*dimc+1,yc1+j*dimc+1, Graphics.LEFT | Graphics.TOP);
      }
     }};
   }

   tt=""+parent.score.getScore();
   paintStringa(g , tt, 10, h-20, 0);
   paintStringa(g , "MENU", w-10, h-20, 2);
  }
  
// fine disegna



  public void azione(int x,int y,int z) {
   if (game_over) { return; };
   if (messapedina) {return; };
   if (inboard){
    curx=curx+x;  cury=cury-y;
    if (cury<0) { cury=0;}  if (cury>8) { cury=8;}
    if (curx<0) { curx=0;}  if (curx>8) { curx=8;}
    if (z>0) {inboard=false;  }
   } else {
    pedx=pedx+x;  pedx=pedx-y;
    if (pedx<0) { pedx=0;}  if (pedx>7) { pedx=7;}
    if (z>0) {
     if (pedx<7){mettipallinaxy(curx,cury, pedx);
                 messapedina=true; contamessapedina=maxmessapedina;
                 testExplosion();
                };  
     inboard=true;
    }
   }

  }


  public void cambiaH(int newW,int newH){
   w = newW;  h = newH;
   faisfondo();
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





  private int calcolavuote(){
   int res=0;
    for (int i=0; i<9; i++) { for (int j=0; j<9; j++)  { if (pospalline[i][j]==-1) res++; };}
   return res;
  }


  private void mettipallina(int pos, int col){
   int conta=0;
   for (int i=0; i<9; i++) {
    for (int j=0; j<9; j++) {
     if (pospalline[i][j]==-1) {
      if (conta==pos) {pospalline[i][j]=col;}; conta++;
     }
    }
   }
  }

  private void mettipallinaxy(int x1,int y1, int col){
   pospalline[x1][y1]=col;
  }


  private void metti3(){
   int vuote=calcolavuote();   int pos1;   int col1;
    if (vuote>0){
      pos1=m_r.nextInt() % vuote;  if (pos1<0) {pos1=-pos1;};     col1=m_r.nextInt() % 7;      if (col1<0) {col1=-col1;};
      mettipallina(pos1,col1);
    }
    if (vuote>1){
      pos1=m_r.nextInt() % (vuote-1);  if (pos1<0) {pos1=-pos1;}; col1=m_r.nextInt() % 7;      if (col1<0) {col1=-col1;};
      mettipallina(pos1,col1);
    }
    if (vuote>2){
      pos1=m_r.nextInt() % (vuote-2);  if (pos1<0) {pos1=-pos1;}; col1=m_r.nextInt() % 7;      if (col1<0) {col1=-col1;};
      mettipallina(pos1,col1);
    }
//   vuote=calcolavuote();  if (vuote==0){ game_over=true;  parent.score.save();  }
  }


  public void nuovapartita() {
    for (int i=0; i<9; i++) { for (int j=0; j<9; j++)
      {pospalline[i][j]=-1; explpalline[i][j]=false; }; };
    metti3(); parent.score.reset();
    curx=0;  cury=0; pedx=0; messapedina=false; contamessapedina=0;
    inesplosione=false; game_over=false;
    parent.score.justintrodotto=-1;
  }


  public void paintStringa(Graphics g , String st, int x1, int y1, int alli) {
  // alli 0 = Left    1 = Center  2 = Right
    int xs = x1; int ys = y1; int ll;  int offImg=0;  int cra = 20;  // caratteri per riga
    int riga=0;  int colonna =0;
    ll = st.length();
    switch (alli) {
     case 0 : xs = x1; break;
     case 1 : xs = x1-dimFS*ll/2; break;
     case 2 : xs = x1-dimFS*ll; break;
     default : break;
    }
    for (int i=0; i<ll; i++){
      offImg =Alfabeto.indexOf(st.charAt(i));
      if (offImg>=cra) { riga=dimFS;  offImg=offImg-cra;  colonna=offImg*dimFS; }
               else  {   riga =0;     colonna = offImg*dimFS;  };
      if (offImg<cra) if (offImg>=0) {g.drawRegion(FonteS,colonna,riga,dimFS,dimFS,0,xs,ys,Graphics.TOP | Graphics.LEFT); }
      xs = xs + dimFS;
    }
  }



}



