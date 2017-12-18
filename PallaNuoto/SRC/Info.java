package PallaNuoto;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public void DisegnaInfo(Graphics g,int w,int h)
//  public int  componenteX(int dir)
//  public int  componenteY(int dir)
//  public int  dammidirezionetogo(int locxtogo,int locytogo,int locx100,int locy100)
//  public void cambiah(int w, int h){
//  public void sorteggiapos(boolean dattacco,int indgioc,int indsq); // attacco giocatore squadra
//  public void sorteggiatorneo(){
//  public void paintfaseregole(Graphics g,int w,int h)


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Random;






public class Info {

  private MainCanvas parent;
  private Image      Upper;
  private Image      Downer;
  private Font       Help_font;
  private int        hfont;
  private String     RR="";
  public  int        rigad=1;
  private int        wrid;
  private int        wc;
  private int        hc;
  private int        Off1int;
  private int        Off2int;
  public  int        sortx=0;
  public  int        sorty=0;
  private int        poser;
  private Random     m_r;
  public  int        rigaattiva=0;
  private int        Locrig=0;
  public  int        maxlinehelp=39; // 44


  public  int[][]    parametri;
  public  int[]      SqTorneo;
  public  int        Pfallo   =0;
  public  int        Ptiro    =1;
  public  int        Pportiere=2;
  public  int        Pdifesa  =3;
  public  int        Pattacco =4;
  public  int        NItalia    =0;
  public  int        NFrancia   =1;
  public  int        NGermania  =2;
  public  int        NBrasile   =3;
  public  int        NGrecia    =4;
  public  int        NUsa       =5;
  public  int        NRussia    =6;
  public  int        NAustralia =7;



  public Info(MainCanvas parent,int w,int h ) throws IOException {
    this.parent = parent;
    Upper  = Image.createImage("/fu.png");
    Downer = Image.createImage("/fd.png");
    Help_font    = Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_PLAIN,Font.SIZE_SMALL); // Font.SIZE_MEDIUM);
    hfont = Help_font.getHeight();

    wrid=w-10;  wc = w/2;
    Off1int=24; Off2int=h-6; // vedi gInterface
    hc=((h-30)/2)+24; // mezzocampo
    m_r = new Random();
    parametri = new int[8][5];
    SqTorneo  = new int[8];
// Italia
    parametri[NItalia][Pfallo]      = 20;
    parametri[NItalia][Ptiro]       = 80;
    parametri[NItalia][Pportiere]   = 45;
    parametri[NItalia][Pdifesa]     = 38;
    parametri[NItalia][Pattacco]    = 50;

    parametri[NFrancia][Pfallo]     = 60;
    parametri[NFrancia][Ptiro]      = 50;
    parametri[NFrancia][Pportiere]  = 30;
    parametri[NFrancia][Pdifesa]    = 28;
    parametri[NFrancia][Pattacco]   = 30;

    parametri[NGermania][Pfallo]    = 35;
    parametri[NGermania][Ptiro]     = 70;
    parametri[NGermania][Pportiere] = 40;
    parametri[NGermania][Pdifesa]   = 18;
    parametri[NGermania][Pattacco]  = 50;

    parametri[NBrasile][Pfallo]     = 15;
    parametri[NBrasile][Ptiro]      = 90;
    parametri[NBrasile][Pportiere]  = 40;
    parametri[NBrasile][Pdifesa]    = 58;
    parametri[NBrasile][Pattacco]   = 45;

    parametri[NGrecia][Pfallo]      = 25;
    parametri[NGrecia][Ptiro]       = 75;
    parametri[NGrecia][Pportiere]   = 34;
    parametri[NGrecia][Pdifesa]     = 18;
    parametri[NGrecia][Pattacco]    = 75;

    parametri[NUsa][Pfallo]         = 35;
    parametri[NUsa][Ptiro]          = 35;
    parametri[NUsa][Pportiere]      = 80;
    parametri[NUsa][Pdifesa]        = 48;
    parametri[NUsa][Pattacco]       = 70;

    parametri[NRussia][Pfallo]      = 65;
    parametri[NRussia][Ptiro]       = 60;
    parametri[NRussia][Pportiere]   = 50;
    parametri[NRussia][Pdifesa]     = 55;
    parametri[NRussia][Pattacco]    = 60;

    parametri[NAustralia][Pfallo]   = 45;
    parametri[NAustralia][Ptiro]    = 75;
    parametri[NAustralia][Pportiere]= 40;
    parametri[NAustralia][Pdifesa]  = 28;
    parametri[NAustralia][Pattacco] = 70;
  }




 public void cambiaH(int w, int h){
    wrid=w-10;  wc = w/2;
    Off1int=24; Off2int=h-6; // vedi gInterface
    hc=((h-30)/2)+24; // mezzocampo
 }







 public void DisegnaInfo(Graphics g,int w,int h){
        parent.gMenu.paintStringa(g, "COPYRIGHT 2005"        , 0 ,w/2   , 20, false,2);
        parent.gMenu.paintStringa(g, "CARLOMACOR.COM"        , 0 ,w/2   , 50, false,2);
        parent.gMenu.paintStringa(g, "MUSIC"                 , 0 ,w/2   , 93, false,2);
        parent.gMenu.paintStringa(g, "PAOLO"                 , 0 ,w/2   , 115, false,2);
        parent.gMenu.paintStringa(g, "MASTRANDREA"           , 0 ,w/2   , 135, false,2);
        parent.gMenu.paintStringa(g, "MENU"                  , 0 ,w-6   , h-20, false,1);
 }





  public int  componenteX(int dir) {
   int locdx=0;
   switch ( dir) {
    case 0 : locdx=  0; break;
    case 1 : locdx= 19; break;
    case 2 : locdx= 35; break;
    case 3 : locdx= 46; break;
    case 4 : locdx= 50; break;
    case 5 : locdx= 46; break;
    case 6 : locdx= 35; break;
    case 7 : locdx= 19; break;
    case 8 : locdx=  0; break;
    case 9 : locdx=-19; break;
    case 10: locdx=-35; break;
    case 11: locdx=-46; break;
    case 12: locdx=-50; break;
    case 13: locdx=-46; break;
    case 14: locdx=-35; break;
    case 15: locdx=-19; break;
   }
   return locdx;
  }

  public int  componenteY(int dir) {
   int locdy=0;
   switch ( dir) {
    case 0 : locdy=-50; break;
    case 1 : locdy=-46; break;
    case 2 : locdy=-35; break;
    case 3 : locdy=-19; break;
    case 4 : locdy=  0; break;
    case 5 : locdy= 19; break;
    case 6 : locdy= 35; break;
    case 7 : locdy= 46; break;
    case 8 : locdy= 50; break;
    case 9 : locdy= 46; break;
    case 10: locdy= 35; break;
    case 11: locdy= 19; break;
    case 12: locdy=  0; break;
    case 13: locdy=-19; break;
    case 14: locdy=-35; break;
    case 15: locdy=-46; break;
   }
   return locdy;
  }


  public int dammidirezionetogo(int locxtogo,int locytogo,int locx100,int locy100){
   int dx,dy,rap,rapabs; int value=0;
    dx=locxtogo-locx100;   dy=-(locytogo-locy100);
    if (dx!=0) {
     rap=(dy*100)/dx;
     rapabs=rap; if (rapabs<0) { rapabs=-rapabs; };
     // tg 11=19 ; tg 22.5=41; tg 33=65; tg 45=100;
     // tg 56=148; tg56=148; tg 67=235; tg 78=470;
      if (rapabs<21) {value=4;} else          // 11 gradi
       if (rapabs<67) {value=3;} else         // 33 gradi
        if (rapabs<153) {value=2;} else       // 56 gradi
         if (rapabs<514) {value=1;} else  {value=0;} // 78 gradi

     if ((dx>0) & (dy==0)) { value=4; };
     if ((dx<0) & (dy==0)) { value=12; };
     if ((dx>0) & (dy<0))
      {
       switch (value) {
        case 0 : value=8;  break;
        case 1 : value=7;  break;
        case 2 : value=6;  break;
        case 3 : value=5;  break;
        case 4 : value=4;  break;
       }
      };
     if ((dx<0) & (dy>0))
      {
       switch (value) {
        case 0 : value=0;   break;
        case 1 : value=15;   break;
        case 2 : value=14;  break;
        case 3 : value=13;  break;
        case 4 : value=12;  break;
       }
      };
     if ((dx<0) & (dy<0))
      {
       switch (value) {
        case 0 : value=8;   break;
        case 1 : value=9;   break;
        case 2 : value=10;  break;
        case 3 : value=11;  break;
        case 4 : value=12;  break;
       }
      };
    }     else  { if (dy>0) {  value= 0;  } else { value= 8; }; };
   return  value;
  }


  public void sorteggiapos(boolean dattacco,int indgioc,int indsq){  // attacco giocatore squadra
   poser = (m_r.nextInt() % 7); if (poser<0) {poser=-poser;};
   if (dattacco) {
    switch ( indgioc ) {
     case 5 :     case 1 :
       switch (poser) {
        case 0 :  sortx=wc*7/4-5;   sorty = Off1int+(hc*1)/7;   break;
        case 1 :  sortx=wc*7/4;     sorty = Off1int+(hc*2)/7;   break;
        case 2 :  sortx=wc*3/2;     sorty = Off1int+(hc*1)/7;   break;
        case 3 :  sortx=wc*3/2;     sorty = Off1int+(hc*2)/7;   break;
        case 4 :  sortx=wc+18;      sorty = Off1int+(hc*3)/14;  break;
        case 5 :  sortx=wc+12;      sorty = Off1int+(hc*2)/7;   break;
        case 6 :  sortx=wc+6;       sorty = Off1int+(hc*5)/14;  break;
        default :  sortx=wc;        sorty = hc;  break;
       }
      break;
     case 6 :
       switch (poser) {
        case 0 :  sortx=wc/2;   sorty = Off1int+(hc*2)/7;   break;
        case 1 :  sortx=wc-23;  sorty = Off1int+(hc*1)/7;   break;
        case 2 :  sortx=wc-12;  sorty = Off1int+(hc*3)/7;   break;
        case 3 :  sortx=wc;     sorty = Off1int+(hc*2)/7;   break;
        case 4 :  sortx=wc+23;  sorty = Off1int+(hc*1)/7;   break;
        case 5 :  sortx=wc+12;  sorty = Off1int+(hc*3)/7;   break;
        case 6 :  sortx=wc*3/2; sorty = Off1int+(hc*2)/7;   break;
        default :  sortx=wc;        sorty = hc;  break;
       }
      break;
     case 7 :     case 3 :
       switch (poser) {
        case 0 :  sortx=wc/4+5;   sorty = Off1int+(hc*1)/7;   break;
        case 1 :  sortx=wc/4;     sorty = Off1int+(hc*2)/7;   break;
        case 2 :  sortx=wc/2;     sorty = Off1int+(hc*1)/7;   break;
        case 3 :  sortx=wc/2;     sorty = Off1int+(hc*2)/7;   break;
        case 4 :  sortx=wc-18;    sorty = Off1int+(hc*3)/14;  break;
        case 5 :  sortx=wc-12;    sorty = Off1int+(hc*2)/7;   break;
        case 6 :  sortx=wc-6;     sorty = Off1int+(hc*5)/14;  break;
        default :  sortx=wc;        sorty = hc;  break;
       }
      break;
    }
    if (indsq==2) { sorty= Off2int+Off1int-sorty; };
   }
   else  // non e' d'attacco ma schemi difensivi
   {
    switch ( indgioc ) {
     case 5 :     case 1 :
       switch (poser) {
        case 0 :  sortx=wc*7/4-5;   sorty = Off1int+(hc*1)/7;   break;
        case 1 :  sortx=wc*7/4;     sorty = Off1int+(hc*2)/7;   break;
        case 2 :  sortx=wc*3/2;     sorty = Off1int+(hc*1)/7;   break;
        case 3 :  sortx=wc*3/2;     sorty = Off1int+(hc*2)/7;   break;
        case 4 :  sortx=wc+18;      sorty = Off1int+(hc*3)/14;  break;
        case 5 :  sortx=wc+12;      sorty = Off1int+(hc*2)/7;   break;
        case 6 :  sortx=wc+6;       sorty = Off1int+(hc*5)/14;  break;
        default :  sortx=wc;        sorty = hc;  break;
       }
      break;
     case 6 :
       switch (poser) {
        case 0 :  sortx=wc/2;   sorty = Off1int+(hc*2)/7;   break;
        case 1 :  sortx=wc-23;  sorty = Off1int+(hc*1)/7;   break;
        case 2 :  sortx=wc-12;  sorty = Off1int+(hc*3)/7;   break;
        case 3 :  sortx=wc;     sorty = Off1int+(hc*2)/7;   break;
        case 4 :  sortx=wc+23;  sorty = Off1int+(hc*1)/7;   break;
        case 5 :  sortx=wc+12;  sorty = Off1int+(hc*3)/7;   break;
        case 6 :  sortx=wc*3/2; sorty = Off1int+(hc*2)/7;   break;
        default :  sortx=wc;        sorty = hc;  break;
       }
      break;
     case 7 :     case 3 :
       switch (poser) {
        case 0 :  sortx=wc/4+5;   sorty = Off1int+(hc*1)/7;   break;
        case 1 :  sortx=wc/4;     sorty = Off1int+(hc*2)/7;   break;
        case 2 :  sortx=wc/2;     sorty = Off1int+(hc*1)/7;   break;
        case 3 :  sortx=wc/2;     sorty = Off1int+(hc*2)/7;   break;
        case 4 :  sortx=wc-18;    sorty = Off1int+(hc*3)/14;  break;
        case 5 :  sortx=wc-12;    sorty = Off1int+(hc*2)/7;   break;
        case 6 :  sortx=wc-6;     sorty = Off1int+(hc*5)/14;  break;
        default :  sortx=wc;        sorty = hc;  break;
       }
      break;
    }
    if (indsq==1) { sorty= Off2int+Off1int-sorty; };
   }   // FINE non e' d'attacco ma schemi difensivi
   sortx=sortx*100;   sorty=sorty*100;
  };


  private int sorteggiasqtorneoint(int isq){
   int value =0;     int Rv=0;    int Rvsq=0;
   Random m_r = new Random();
   Rv=((m_r.nextInt() % 8)); if (Rv<0) {Rv=-Rv;};
   for (int j=0; j<isq; j++){
    if (Rv==SqTorneo[j]) { Rv++; if (Rv>=8) {Rv=0;}; j=-1; };
   }
   value=Rv;
   return value;
  }


  public void sorteggiatorneo(){
    for (int i=1; i<8; i++) { SqTorneo[i]=sorteggiasqtorneoint(i); };
  }


  private String dammitestoitaliano(int ind){
   switch (ind) {                    //X
    case 1 : RR="Partecipa alla partita";     break;
    case 2 : RR="amichevole .Friendly.";      break;
    case 3 : RR="scegliendo  2 squadre";      break;
    case 4 : RR="oppure completa un";         break;
    case 5 : RR="torneo ad eliminazione";     break;
    case 6 : RR="diretta scegliendo con";     break;
    case 7 : RR="quale squadra partecipare."; break;
    case 8 : RR="potrai muovere con i ";      break;
    case 9 : RR="tasti Up Left Down Right";   break;
    case 10: RR="il giocatore centrale";      break;
    case 11: RR=" della tua squadra.";        break;
    case 12: RR="con il Fire puoi passare";   break;
    case 13: RR="la palla tra i giocatori";   break;
    case 14: RR="e tirare in porta";          break;
    case 15: RR="quando sei in zona tiro";    break;
    case 16: RR="e te ne accorgerai dal";     break;
    case 17: RR="portiere avversario.";       break;
    case 18: RR="Hai pochi secondi per";      break;
    case 19: RR="completare l'azione";        break;
    case 20: RR="per cui veloce nel";         break;
    case 21: RR="completare l'avanzata";      break;
    case 22: RR="ed il tiro.";                break;
    case 23: RR="Circa 5 minuti sono";        break;
    case 24: RR="necessari per completare";   break;
    case 25: RR="la partita.";                break;
    case 26: RR="Se vi sono troppi";          break;
    case 27: RR="scontri verra' segnalato";   break;
    case 28: RR="il fallo.";                  break;
    case 29: RR="Specialmente il tuo";        break;
    case 30: RR="giocatore non deve essere";  break;
    case 31: RR="troppo violento nello";      break;
    case 32: RR="scontrarsi con gli";         break;
    case 33: RR="avversari fermi.";           break;
    case 34: RR="Se vuoi vincere cerca";      break;
    case 35: RR="sempre di essere presente";  break;
    case 36: RR="sia nella azione di";        break;
    case 37: RR="attacco che in";             break;
    case 38: RR="quella difensiva.";          break;
    case 39: RR="";                           break;
    case 40: RR="Ricorda che ogni";           break;
    case 41: RR="squadra ha diverse";         break;
    case 42: RR="caratteristiche di gioco";   break;
    case 43: RR="";                           break;
    case 44: RR="Buon Divertimento";          break;
    default : RR="";
   }
   return RR;
  }

  private String dammitestoinglese(int ind){
   switch (ind) {                    //X
    case 1 : RR="Make your match";     break;
    case 2 : RR="Friendly.";      break;
    case 3 : RR="choose you teams";      break;
    case 4 : RR="or make a full";         break;
    case 5 : RR="tournament";     break;
    case 6 : RR="choose";     break;
    case 7 : RR="your team."; break;
    case 8 : RR="move your player using";      break;
    case 9 : RR="Up Left Down Right";   break;
    case 10: RR="you'll move the central ";      break;
    case 11: RR="player of your team.";        break;
    case 12: RR="use Fire to pass";   break;
    case 13: RR="the ball to players";   break;
    case 14: RR="and shot in goal";          break;
    case 15: RR="when you are at distance";    break;
    case 16: RR="you'll notice the ";     break;
    case 17: RR="movement of difencer.";       break;
    case 18: RR="You have few seconds";      break;
    case 19: RR="to make the goal";        break;
    case 20: RR="so be fast!";         break;
    case 21: RR="complete the advancement";      break;
    case 22: RR="and make the shot.";                break;
    case 23: RR="There are quite 5";        break;
    case 24: RR=" minuts to complete";   break;
    case 25: RR="the match.";                break;
    case 26: RR="if the are to mach";          break;
    case 27: RR="interference there is";   break;
    case 28: RR="a penalty.";                  break;
    case 29: RR="Specially your cetral";        break;
    case 30: RR="palyer must not";  break;
    case 31: RR="violent with";      break;
    case 32: RR="other player that";         break;
    case 33: RR="don't move.";           break;
    case 34: RR="If you want win";      break;
    case 35: RR="try to be present";  break;
    case 36: RR="in the action in";        break;
    case 37: RR="attack but in the ";             break;
    case 38: RR="defence too.";          break;
    case 39: RR="";                           break;
    case 40: RR="Remeamber that each";           break;
    case 41: RR="team has different";         break;
    case 42: RR="caratteristic to play";   break;
    case 43: RR="";                           break;
    case 44: RR="Good fun!";          break;
    default : RR="";
   }
   return RR;
  }



  public void paintfaseregole(Graphics g,int w, int h){
   String ss="";
     parent.gMenu.paintStringa(g,"PALLANUOTO"    , 0 ,w/2 , 10, false,2);
     parent.gMenu.paintStringa(g, "MENU"                   , 0 ,w-6   , h-20, false,1);

    if (rigaattiva>0)                {  g.drawImage(Upper,0,20,Graphics.TOP | Graphics.LEFT);}
    if (rigaattiva<maxlinehelp)      {  g.drawImage(Downer,0,h-40,Graphics.TOP | Graphics.LEFT);}


    int numrig = (h / hfont)-3;
    g.setFont(Help_font);
    g.setColor(255,255,0);

    for (int i=1; i<numrig; i++) {
     Locrig=rigaattiva+i;
     // attenzione al maxlinehelp controllato in parent dalla presenza o meno del pointer
     ss=dammitestoinglese(Locrig);
//     ss=dammitestoitaliano(Locrig);
     g.drawString(ss , 20, 30+i*hfont,Graphics.BASELINE|Graphics.LEFT);
    }
  }



}


