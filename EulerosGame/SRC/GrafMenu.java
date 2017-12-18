package Euleros;


import java.io.IOException;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.String;
import javax.microedition.lcdui.game.Sprite;


public class GrafMenu {

  private  Image Titolo;
  private  Image FonteS;
  private  Image Menu2;

  private  Image Newgame;
  private  Image Level;


  private  int   w;
  private  int   h;
  private  int   dimFS, dimFL, dimoffFS,dimoffFL;
  public   int   parziall=0;

  private String Alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
  private String Numerici = "0123456789 ";
  public   int   last1riga = 2;
  public   int   last2riga = 5;
  private  int   rigf=0;
  private  int   colf=0;
  private  int   Lbar=0;
  private  String tt = "";
  private  int xs;
  private  int ys ;
  private  int ll;
  private  int offImg =0;
  private  int riga    =0;
  private  int colonna = 0;
  private  char locC;
  private  int cra = 18;

  private String TxtNumer = "";
  private  String hhh;

  private  int xenterd =0;
  private  int yenterd =0;
  private  boolean premutoPP= false;

  public MainCanvas parent;
  public  Info gInfo;

  public  int   yriga2=0;


   public GrafMenu(MainCanvas parent,int w, int h) throws IOException {
    this.parent = parent;   this.w = w;     this.h = h;
    Titolo  = Image.createImage("/titolo.png");
    FonteS  = Image.createImage("/Caratteri.png");
    Menu2   = Image.createImage("/menu2.png");
    Newgame = Image.createImage("/ngame.png");
    Level   = Image.createImage("/level.png");

    gInfo   = new Info(this);
    dimFS   = FonteS.getHeight()/2;
    dimFL   = Level.getHeight();
    dimoffFS = dimFS-3;
    dimoffFL = (dimFL/2)+3;
//    contaftp=System.currentTimeMillis();
    if (h<=160) { yriga2=120; } else  { yriga2=140; };  
   }


  public void paintMenu(Graphics g){
     if (parent.infaseMenu) {
      if (parent.infaseInfo) {
       gInfo.DisegnaInfo(g,w,h);
      }
      else
       if (parent.infaseRules) { gInfo.DisegnaRules(g,w,h);}
       else
      {

       tt = ""+parent.IndSchema;
       paintStringa(g, tt                  , 1, (w/4)-10 ,yriga2-40  , false,2);
   // a seconda se iniziato o meno ....
       char Igg='z';
       Igg = parent.QIniziati.charAt(parent.IndSchema);
       switch (Igg) {
        case '0' :  g.drawRegion(Newgame ,24,0,24,24,0,(w/2)-12  ,yriga2-37,Graphics.TOP | Graphics.LEFT);   break;
        case '1' :  g.drawRegion(Newgame ,48,0,24,24,0,(w/2)-12  ,yriga2-37,Graphics.TOP | Graphics.LEFT);   break;
        case '2' :  g.drawRegion(Newgame ,72,0,24,24,0,(w/2)-12  ,yriga2-37,Graphics.TOP | Graphics.LEFT);   break;
       }

       g.drawRegion(Newgame ,0,0,24,24,0,(w*3/4)-12,yriga2-37,Graphics.TOP | Graphics.LEFT);

       paintStringa(g, "EXIT"   , 0 ,w-4 ,h-20 , false,1);


      // help
        g.drawRegion(Menu2,20,0,20,20,0,(w*2/3)-10 ,yriga2+10,Graphics.TOP | Graphics.LEFT);
        g.drawRegion(Menu2,0 ,0,20,20,0,(w/3)-10   ,yriga2+10,Graphics.TOP | Graphics.LEFT);
        g.drawImage(Titolo,w/2,45,Graphics.VCENTER | Graphics.HCENTER);

       switch (parent.IndiceMenu) {
        case 1 : paintStringa(g, "LEVEL"          , 0 ,w/4   , yriga2-50 , true,2); break;
        case 2 : paintStringa(g, "CONTINUE"       , 0 ,w/2   , yriga2-50 , true,2);break;
        case 3 : paintStringa(g, "NEW"            , 0 ,w*3/4 , yriga2-50 , true,2);
                 paintStringa(g, "GAME"           , 0 ,w*3/4 , yriga2-35 , true,2);break;
        case 4 : paintStringa(g, "SOUND"          , 0 ,w/4   , yriga2 , true,2);
                 if (parent.Suono){ paintStringa(g, "ON" , 0 ,w/4 ,yriga2+15 , true,2);}
                             else { paintStringa(g, "OFF", 0 ,w/4 ,yriga2+15 , true,2);};  break;
        case 5 : paintStringa(g, "HELP"           , 0 ,w/3   ,yriga2 , true,2);break;
        case 6 : paintStringa(g, "INFO"           , 0 ,w*2/3 ,yriga2 , true,2);break;
        default : break;
       }
      }
     }
  }



  public void paintStringa(Graphics g , String st, int Big , int x1, int y1, boolean scorri,int alli) {
  // alli 0 = Left    1 = Right   2 = Center
  // Big =0 small  Big=1  BIG
    xs = x1; ys = y1;

    // con il sottoindcarattereDisplay si incrementa e al 3 o .. si passa a piu' caratteri da display
    // se scorri nfatti si propone solo parte della stringa
    ll = st.length();

    // parziall serve per la vista ritardata della scritta
    if (scorri) {if (parziall<ll) {ll=parziall; parziall++; }};

   switch (alli) {
    case 0 : xs = x1; break;
    case 1 : xs = x1-dimoffFS*ll; break;
    case 2 : xs = x1-dimoffFS*ll/2; break;
    default : break;
   }

    // se testo normale
    if (Big==0) {
      for (int i=0; i<ll; i++){
        offImg =Alfabeto.indexOf(st.charAt(i));
        if (offImg>=cra) { riga=dimFS;  offImg=offImg-cra;  colonna=offImg*dimFS; }
                 else  {   riga =0;     colonna = offImg*dimFS;  };
        if (offImg<cra) if (offImg>=0) {g.drawRegion(FonteS,colonna,riga,dimFS,dimFS,0,xs,ys,Graphics.TOP | Graphics.LEFT); }
        xs = xs + dimoffFS;
      }
    }

    // se Livello Numerico
    if (Big==1) {
      for (int i=0; i<ll; i++){
        offImg =Numerici.indexOf(st.charAt(i));
        colonna = offImg*dimFL;
        if (offImg>=0) {g.drawRegion(Level,colonna,0,dimFL,dimFL,0,xs,ys,Graphics.TOP | Graphics.LEFT); }
        xs = xs + dimoffFL;
      }
    }

  }



  public void cambiaH(int newH){
   h = newH;
  }







}






