package Euleros;




import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.String;
import javax.microedition.lcdui.game.Sprite;




public class Info {

  private GrafMenu   parent;
  private Font       Help_font;
  public  int        rigad=1;
  private Image      Upper;
  private Image      Downer;
  private int        hfont;
  public  int        rigaattiva=0;
  private int        Locrig=0;
  private String     ss="";
  private String     RR="";
  public  int        maxlinehelp=10;

  public Info(GrafMenu parent) throws IOException {
    this.parent = parent;
    Help_font    = Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_PLAIN,Font.SIZE_MEDIUM);
    hfont = Help_font.getHeight();
    Upper  = Image.createImage("/fu.png");
    Downer = Image.createImage("/fd.png");
  }



 public void DisegnaInfo(Graphics g,int w,int h){
     parent.paintStringa(g,"EULEROS GAME"    , 0 ,w/2 , 10, false,2);
     parent.paintStringa(g, "MENU"                  , 0 ,w-6   , h-20, false,1);
     g.setFont(Help_font);
     g.setColor(255,255,0);
//     g.drawString("Versione Tester" , w/2, 30+2*hfont,Graphics.BASELINE|Graphics.HCENTER);
//     g.drawString("Non Commerciale" , w/2, 30+3*hfont,Graphics.BASELINE|Graphics.HCENTER);

     g.drawString("COPYRIGHT 2005"  , w/2, 30+4*hfont,Graphics.BASELINE|Graphics.HCENTER);
     g.drawString("carlomacor.com"  , w/2, 30+5*hfont,Graphics.BASELINE|Graphics.HCENTER);
 }


  private String dammitestoitaliano(int ind){
   RR="";
   switch (ind) {
     case 1 : RR="Muovi il cursore e"  ; break;
     case 2 : RR="scegli una casella." ; break;
     case 3 : RR="Seleziona per"       ; break;
     case 4 : RR="cambiare il numero." ; break;
     case 5 : RR=""                    ; break;
     case 6 : RR="Per completare"      ; break;
     case 7 : RR="il diagramma devi :" ; break;
     case 8 : RR="Mettere un numero"   ; break;
     case 9 : RR="da 1 a 9 in "        ;  break;
     case 10: RR="ciascuna casella."   ; break;
     case 11: RR=""                    ; break;
     case 12: RR="In ogni riga,"       ; break;
     case 13: RR="in ogni colonna,"    ; break;
     case 14: RR="e sottoquadro 3x3,"  ; break;
     case 15: RR="devono esserci tutti"; break;
     case 16: RR="i numeri da 1 a 9."  ; break;
     default : RR="";                    break;
   }
   return RR;
  }



  private String dammitestoinglese(int ind){
   RR="";
   switch (ind) {
     case 1 : RR="MOVE THE CURSOR" ; break;
     case 2 : RR="TO CHOOSE A CELL"; break;
     case 3 : RR="AND SELECT "     ; break;
     case 4 : RR="TO CHANGE NUMBER"; break;
     case 5 : RR=""                ; break;
     case 6 : RR="TO WIN THE"      ; break;
     case 7 : RR="LEVEL "          ; break;
     case 8 : RR="PLACE A NUMBER"  ; break;
     case 9 : RR="1 TO 9 IN EACH"  ; break;
     case 10: RR="BLANK CELL"      ; break;
     case 11: RR=""                ; break;
     case 12: RR="EACH ROW"        ; break;
     case 13: RR="EACH COLUMN"     ; break;
     case 14: RR="AND 3X3 BLOCK"   ; break;
     case 15: RR="MUST CONTAIN"    ; break;
     case 16: RR="NUMBER 1 TO 9"   ; break;
   }
   return RR;
  }


 public void DisegnaRules(Graphics g,int w,int h){
    if (rigaattiva>0)                     {  g.drawImage(Upper,0,20,Graphics.TOP | Graphics.LEFT);}
    if (rigaattiva<maxlinehelp)    {  g.drawImage(Downer,0,h-40,Graphics.TOP | Graphics.LEFT);}
    g.setFont(Help_font);
    parent.paintStringa(g,"EULEROS GAME"    , 0 ,w/2 , 10, false,2);
    parent.paintStringa(g, "MENU"       , 0 ,w-10 , h-20, true,1);
    int numrig = (h / hfont)-3;
    g.setColor(255,255,0);
//             attenzione al maxlinehelp controllato in parent dalla presenza o meno del pointer
    for (int i=1; i<numrig; i++) {
     Locrig=rigaattiva+i;
//     ss=dammitestoinglese(Locrig);
     ss=dammitestoitaliano(Locrig);
     g.drawString(ss , 20, 30+i*hfont,Graphics.BASELINE|Graphics.LEFT);

    }





  }



}


