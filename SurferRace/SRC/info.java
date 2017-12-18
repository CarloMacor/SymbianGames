package Windserf;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public info(MainCanvas parent) throws IOException
//  private String dammitestoinglese(int ind)
//  public void paintinfo(Graphics g,int w, int h)


import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.util.Random;




public class info {
 private MainCanvas parent;
 private String     hhh;
 private Font       Help_font;
 private int        hfont;
 public  int        rigaattiva=0;
 private int        Locrig=0;
 private String     ss="";
 private String     RR="AA";
 private Image      Upper;
 private Image      Downer;

  public info(MainCanvas parent) throws IOException {
    this.parent  = parent;
    Help_font    = Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_PLAIN,Font.SIZE_SMALL); // Font.SIZE_MEDIUM);
    hfont = Help_font.getHeight();
    Upper  = Image.createImage("/fu.png");
    Downer = Image.createImage("/fd.png");
  }

  private String dammitestoinglese(int ind){
   switch (ind) {
    case 1 : RR="Copyright: 2005"; break;
    case 2 : RR="carlomacor.com"; break;
    case 3  : RR=""; break;
    case 4 : RR="Music"; break;
    case 5 : RR="Paolo Mastrandrea"; break;
    case 6 : RR=""; break;
    case 7 : RR="Help"; break;
    case 8 : RR="'Easy' mode :"; break;
    case 9 : RR="The surfer can go"; break;
    case 10 : RR="in each direction at"; break;
    case 11 : RR="the same velocity."; break;
    case 12 : RR="'Medium' mode :"; break;
    case 13 : RR="As Easy but you must"; break;
    case 14 : RR="turn if you have not"; break;
    case 15 : RR="the back wind."; break;
    case 16 : RR="'Hard' mode :"; break;
    case 17 : RR="As Medium but the surfer"; break;
    case 18 : RR="velocity is influenced"; break;
    case 19 : RR="by wind direction."; break;
    case 20 : RR="3 games are waiting you"; break;
    case 21 : RR="Box :"; break;
    case 22 : RR="get more boxes"; break;
    case 23 : RR="than possible."; break;
    case 24 : RR="Race :"; break;
    case 25 : RR="Win your race and "; break;
    case 26 : RR="put your record."; break;
    case 27 : RR="Shark :"; break;
    case 28 : RR="Be carefull!"; break;
    case 29 : RR="Shark is attacking you!"; break;
    case 30 : RR=""; break;
    case 31 : RR="Good fun!"; break;
    default : RR="";
   }

   if (parent.conPointer) {
    switch (ind) {
     case 32 : RR=""; break;
     case 33 : RR="Pointer :"; break;
     case 34 : RR="You have a Pointer !"; break;
     case 35 : RR="Move your pointer Left"; break;
     case 36 : RR="or Right to change"; break;
     case 37 : RR=" surfer direction"; break;
     case 38 : RR="Point on rectangles"; break;
     case 39 : RR="to obtain specific commands"; break;
    }
   }

   return RR;
  }





  public void paintinfo(Graphics g,int w, int h){
    if (rigaattiva>0)                     {  g.drawImage(Upper,0,20,Graphics.TOP | Graphics.LEFT);}
    if (rigaattiva<parent.maxlinehelp)    {  g.drawImage(Downer,0,h-40,Graphics.TOP | Graphics.LEFT);}
    g.setFont(Help_font);
    parent.gMenu.paintStringa(g, "MENU"       , 0 ,w-10 , h-20, true,1);
    g.setColor(255,255,0);
    hfont = Help_font.getHeight();
    int numrig = (h / hfont)-3;
    ss = "SURFER RACE";
    parent.gMenu.paintStringa(g,ss    , 0 ,w/2 , 10, false,2);
    g.setColor(255,255,0);
    for (int i=1; i<numrig; i++) {
     Locrig=rigaattiva+i;
     // attenzione al maxlinehelp controllato in parent dalla presenza o meno del pointer
     ss=dammitestoinglese(Locrig);
     if (Locrig<6) {g.drawString(ss , w/2, 30+i*hfont,Graphics.BASELINE|Graphics.HCENTER);} else
        { g.drawString(ss , 20, 30+i*hfont,Graphics.BASELINE|Graphics.LEFT);}
    }
  }


}

