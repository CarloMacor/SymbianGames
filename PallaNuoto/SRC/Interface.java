package PallaNuoto;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //


//  public  void azzeragara()
//  private void disegnabandierinafallo(Graphics g){
//  private void disegnabandierinaazione(Graphics g){
//  private void disegnatimesuporta(Graphics g)
//  public  void paintInterface(Graphics g)
//  private void disegnisopra(Graphics g){
//  private void discroce(Graphics g){
//  public  void cambiaH(int newW,int newH)
//  public  void SuonaButW()
//  private int  confrontoZ(int indJ,int ybuf)
//  public  void riordina()
//  public  void azione(int x,int y)
//  public  void fired()
//  public  void FattoGol(int sq)




import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.io.*;
import javax.microedition.rms.*;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.media.*;







public class Interface {
  private  MainCanvas parent;
  public   Player      player = null;
  public   SquadraObj  squadra;
  private  int     w;
  private  int     h;
  private  Image   Vasca;
  private  Image   msggoal;
  private  Image   Bandierer;
  private  Image   Bandierine;

  public   int     offF1;
  public   int     offF2;
  public   int     MidF;
  public   int     PuntiBianchi =0;
  public   int     PuntiBlu     =0;
  public   long    TempotoStart =0;
  public   int     nfotobantime =0;


  public   int     xmio;
  public   int     ymio;
  public   int     xmio2;
  public   int     ymio2;
  public   int     lastgol=2;
  private  String  tt;
  public   int     xcgol=0;
  private  String  hhh="";
  private  int     yfallo=0;
  private  int     locvalue =0;

  public   boolean inAttesaPartenza      = false;
  public   boolean inRiposizionamentoGol = false;
  public   int     WaitdaGol             = 5500;      // 5500
  public   boolean finepartita           = false;



   public Interface(MainCanvas parent,int w, int h   ) throws IOException {
    this.parent = parent;   this.w = w;     this.h = h;
    Vasca      = Image.createImage("/vasca.png");
    msggoal    = Image.createImage("/msg.png");
    Bandierer  = Image.createImage("/bandierer.png");
    Bandierine = Image.createImage("/bandierine.png");
    offF1=24;
    offF2=h-6;
    MidF=((h-(6+offF1))/2)+offF1;
    createSquadre();
   }


   public void azzeragara() {
    PuntiBianchi=0;  PuntiBlu=0;
    TempotoStart=WaitdaGol;   // 5000
    inAttesaPartenza=true;
    squadra.pallaDi=0;

    parent.tempoIni         = System.currentTimeMillis();
    parent.tempopartita     = parent.valuetimepartita; // millisecondi
    parent.tempoazionepalla = parent.valuetimepalla;
    squadra.portiere1.tengopalla=false;   squadra.portiere2.tengopalla=false;
   }


  private void disegnatimesuporta(Graphics g){
 // parent.tempopartita     valuetimepartita
    int  rapT1 = (parent.tempopartita*56)/parent.valuetimepartita;    // 56 larghezza porta
    int  rapT2 = (parent.tempoazionepalla*56)/parent.valuetimepalla;  // 56 larghezza porta
    g.setColor(255,255,255);
    g.setStrokeStyle(Graphics.SOLID);
    for (int j=1; j<3; j++) { g.drawLine(w/2-rapT1/2,j,w/2+rapT1/2,j); };
    for (int j=4; j<6; j++) { g.drawLine(w/2-rapT2/2,j,w/2+rapT2/2,j); };

    g.setColor(255,0,0);
    g.drawLine(w/2-rapT2/2-1,3,w/2-rapT2/2+1,6);
    g.drawLine(w/2+rapT2/2-1,3,w/2+rapT2/2+1,6);
    g.drawLine(w/2-rapT2/2-1,6,w/2+rapT2/2+1,6);
    g.setColor(0,0,255);
    g.drawLine(w/2-rapT1/2-1,0,w/2-rapT1/2+1,2);
    g.drawLine(w/2+rapT1/2-1,0,w/2+rapT1/2+1,2);
    g.drawLine(w/2-rapT1/2-1,0,w/2+rapT1/2+1,0);

  };


  private void discroce(Graphics g,int col){
        if (col==0) {g.setColor(255,0,0);};
        if (col==1) {g.setColor(255,255,0);};
        if (col==2) {g.setColor(255,0,255);};

        g.setStrokeStyle(Graphics.SOLID);
        g.drawLine(xmio-2,ymio,xmio+2,ymio);
        g.drawLine(xmio,ymio-8,xmio,ymio+8);
  }

  private void discroce2(Graphics g,int col){
        if (col==0) {g.setColor(255,0,0);};
        if (col==1) {g.setColor(255,255,0);};
        if (col==2) {g.setColor(255,0,255);};

        g.setStrokeStyle(Graphics.SOLID);
        g.drawLine(xmio2+10,ymio2,xmio2+6,ymio2);
//        g.drawLine(xmio2,ymio2-2,xmio2,ymio2+2);
  }


  private void disegnisopra(Graphics g){
        g.setColor(255,0,0);
        g.setStrokeStyle(Graphics.SOLID);
        g.drawLine(xmio-4,ymio-12,xmio+4,ymio-12);
        g.setColor(240,240,0);
        g.setStrokeStyle(Graphics.DOTTED);
// linee campo
        if (inAttesaPartenza) { g.drawLine(0,MidF,w,MidF);};
        g.drawLine(0,offF1,w/2-28,offF1);    g.drawLine(w/2+28,offF1,w,offF1);
        g.drawLine(0,offF2,w/2-28,offF2);    g.drawLine(w/2+28,offF2,w,offF2);
// bordo vasca
        g.drawImage(Vasca, 0 ,MidF, Graphics.BOTTOM | Graphics.LEFT);
        g.drawImage(Vasca, 0 ,MidF, Graphics.TOP    | Graphics.LEFT);
        g.drawImage(Vasca, w ,MidF, Graphics.BOTTOM | Graphics.RIGHT);
        g.drawImage(Vasca, w ,MidF, Graphics.TOP    | Graphics.RIGHT);
        if (h>240) {
         g.drawImage(Vasca, 0 ,MidF-120, Graphics.BOTTOM | Graphics.LEFT);
         g.drawImage(Vasca, 0 ,MidF+120, Graphics.TOP    | Graphics.LEFT);
         g.drawImage(Vasca, w ,MidF-120, Graphics.BOTTOM | Graphics.RIGHT);
         g.drawImage(Vasca, w ,MidF+120, Graphics.TOP    | Graphics.RIGHT);
        }

// punteggio

// Bandierer
        g.drawRegion(Bandierer,  parent.pl1*15, 0,15,10,0,        8,2,Graphics.TOP | Graphics.LEFT);
        g.drawRegion(Bandierer,  parent.pl2*15, 0,15,10,0,     w-22,2,Graphics.TOP | Graphics.LEFT);

        tt = ""+PuntiBianchi;    parent.gMenu.paintStringa(g, tt , 0 ,28    ,0 , false,0);
        tt = ""+PuntiBlu;        parent.gMenu.paintStringa(g, tt , 0 ,w-30  ,0 , false,1);
// qui disegnare la barra del tempo rimasto della partita .. a colori distinti per fase
       disegnatimesuporta(g);
      g.setColor(240,240,0);
      g.setStrokeStyle(Graphics.DOTTED);
//        squadra.disegnatogo(g);
        if (inRiposizionamentoGol) {
         if (!squadra.palla.isgoing) {
           g.drawRegion(msggoal,0,0,100,50,0,xcgol ,h/2,Graphics.VCENTER | Graphics.HCENTER);
           xcgol=xcgol+5; if (xcgol>w/2) {xcgol=w/2;};
         }
         squadra.annullafallati();
        };
      if (inAttesaPartenza) {
       if (TempotoStart>0) {
         tt = ""+TempotoStart/1000;
        if (lastgol==2) { parent.gMenu.paintStringa(g, tt ,0, w/2 ,MidF-20    , false,2);}
                   else { parent.gMenu.paintStringa(g, tt ,0, w/2 ,MidF+2    , false,2);};
//        squadra.palla.mPalla.setVisible(false);
       } else
       { squadra.palla.mPalla.setVisible(true); inAttesaPartenza=false;
         if (lastgol==1) { squadra.assegnaschema(2,2); };
       }
      }
   if (nfotobantime>0) { disegnabandierinaazione(g);  nfotobantime--; }

       if (finepartita)  {
       // disegna le bandiere grandi
        parent.gMenu.paintStringa(g,"FINE PARTITA", 0 ,w/2 ,h/2-45 , false,2);
        g.drawRegion(parent.gMenu.Bandiere,  parent.pl1*30, 0,30,20,0,    w/2-30,h/2-20,Graphics.TOP | Graphics.RIGHT);
        g.drawRegion(parent.gMenu.Bandiere,  parent.pl2*30, 0,30,20,0,    w/2+30,h/2-20,Graphics.TOP | Graphics.LEFT);
        tt = ""+PuntiBianchi;    parent.gMenu.paintStringa(g, tt , 0 ,w/2-10 ,h/2-18 , false,1);
        tt = ""+PuntiBlu;        parent.gMenu.paintStringa(g, tt , 0 ,w/2+10 ,h/2-18 , false,2);
        if (PuntiBianchi==PuntiBlu) {
          g.drawRegion(msggoal,0,50,100,50,0,w/2 ,h/2+20,Graphics.VCENTER | Graphics.HCENTER);
        }
        if (PuntiBianchi>PuntiBlu) {
          g.drawRegion(msggoal,0,100,100,50,0,w/2 ,h/2+20,Graphics.VCENTER | Graphics.HCENTER);
          g.drawRegion(msggoal,0,150,100,50,0,w/2 ,h/2+60,Graphics.VCENTER | Graphics.HCENTER);
        }
        if (PuntiBianchi<PuntiBlu) {
          g.drawRegion(msggoal,0,100,100,50,0,w/2 ,h/2+20,Graphics.VCENTER | Graphics.HCENTER);
          g.drawRegion(msggoal,0,200,100,50,0,w/2 ,h/2+60,Graphics.VCENTER | Graphics.HCENTER);
        }

        parent.gMenu.paintStringa(g,"MENU", 0 ,w-10 ,h-16 , false,1);
       }



  }

  private void disegnabandierinafallo(Graphics g){
   if (squadra.infallo!=0) {
       yfallo=squadra.dammiYgiocatore(squadra.infallo)/100;
       if (squadra.infallo<=4) {
           g.drawRegion(Bandierine, 0, 0,20,20,0,        0,yfallo-12,Graphics.TOP | Graphics.LEFT);
       } else
       {   g.drawRegion(Bandierine, 20, 0,20,20,0,       w,yfallo-12,Graphics.TOP | Graphics.RIGHT); }
   }
  }

  private void disegnabandierinaazione(Graphics g){
    g.drawRegion(Bandierine, 0, 0,20,20,0,        w/2,6,Graphics.VCENTER | Graphics.HCENTER);
  }




  public void paintInterface(Graphics g){
      parent.tempoOra = System.currentTimeMillis();
      while ( parent.tempoOra-parent.tempoIni < parent.DelayFotogramma) { // 200 // 150
       parent.tempoOra = System.currentTimeMillis();
      }
      if (TempotoStart>0) { TempotoStart=TempotoStart-(parent.tempoOra-parent.tempoIni); };
      if ((!inRiposizionamentoGol) & (!inAttesaPartenza)) {
        parent.tempopartita     = parent.tempopartita-parent.DelayFotogramma;
        if  (squadra.pallaDi!=0) { parent.tempoazionepalla = parent.tempoazionepalla-parent.DelayFotogramma; }
      }

      parent.tempoIni=parent.tempoOra;
       // abbassa il timer azione palla e tempo partita.
       if (parent.tempoazionepalla<0) {parent.tempoazionepalla=0;  squadra.tempoazionescaduto();   };
       if (parent.tempopartita<0) {
        if  (parent.inTorneo) {
         if (PuntiBianchi==PuntiBlu) {parent.tempopartita=parent.valuetimepalla; };
        }
        if (parent.tempopartita<0) { parent.tempopartita=0;      finepartita=true; squadra.attivafasefallo(0);}
       };

       if (!finepartita)
       {
        squadra.provacatturainiziale();
        if (squadra.infallo==0) {
         if (!inAttesaPartenza) { squadra.noninattesa(); };
          squadra.logicadigioco();
        } else { squadra.annullaeccezioniinfallo(); };
       }

        squadra.next();
        squadra.palla.next();
        squadra.palla.riposizionapallaGiocatore(squadra.pallaDi);
        riordina();
        squadra.palla.correggipallatiro(squadra.pallaDi);


        parent.mLayerManager.paint(g, 0, 0);
        if (inRiposizionamentoGol) { squadra.controllariposizionamento();};

        disegnabandierinafallo(g);

        xmio = squadra.player1_2.mPlayer1.getRefPixelX();
        ymio = squadra.player1_2.mPlayer1.getRefPixelY();
       disegnisopra(g);
// pezza per l'errore di palla di xx main attesa e' yy ed e' in passaggio a yy
        squadra.risolvi1();
  }


  public void cambiaH(int newW,int newH){
   w = newW;  h = newH;    offF1=24;    offF2=h-6;    MidF=((h-(6+offF1))/2)+offF1;
  }

    public void loadRecord() {
     try {
      RecordStore rs = null;      rs = RecordStore.openRecordStore("Nuoto",false);
      ByteArrayInputStream  bin  = null;      DataInputStream       din  = null;
      byte[]                data = null;      data = rs.getRecord(1);
      bin = new ByteArrayInputStream(data);   din = new DataInputStream(bin);
      locvalue = din.readInt();
      if (locvalue==0) { parent.Suono=false;} else {  parent.Suono=true; };
      din.close();      bin.close();      rs.closeRecordStore();      rs = null;
     }
     catch ( Exception e){
      try{
       RecordStore rs = null;     rs = RecordStore.openRecordStore("Nuoto",true);
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
      try{
       RecordStore rs = null;
       rs = RecordStore.openRecordStore("Nuoto",true);
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       DataOutputStream      dout = new DataOutputStream(bout);
       byte[] data = null;
       if (parent.Suono) {locvalue = 1;} else {locvalue = 0;}   dout.writeInt(locvalue);
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
       is0 = getClass().getResourceAsStream("/nuoto.mid");
       player = Manager.createPlayer(is0, ctype);
       player.setLoopCount(-1);
        player.start();
        VolumeControl vc = (VolumeControl)player.getControl("VolumeControl");
        vc.setLevel(parent.VolumeSettato);  // al nokia bene il 25  // 80 per Motorola V3
       if (!parent.Suono){ player.stop();};
     } catch (Exception ex) {};
  }




 private int confrontoZ(int indJ,int numpl, int ybuf){
  int value=0;
// se invece possiede la palla vedere solo la direzione del giocatore che la possiede
   if (indJ>=0){ if (ybuf<squadra.palla.dammiY()){ value++;}; }
   if (indJ>1){ if (ybuf<squadra.portiere1.dammiY()){ value++; value++;};}
   if (indJ>2){ if (ybuf<squadra.player1_1.dammiY()){ value++; value++;};}
   if (indJ>3){ if (ybuf<squadra.player1_2.dammiY()){ value++; value++;};}
   if (indJ>4){ if (ybuf<squadra.player1_3.dammiY()){ value++; value++;};}
   if (indJ>5){ if (ybuf<squadra.player2_1.dammiY()){ value++; value++;};}
   if (indJ>6){ if (ybuf<squadra.player2_2.dammiY()){ value++; value++;};}
   if (indJ>7){ if (ybuf<squadra.player2_3.dammiY()){ value++; value++;};}
  return value;
 }

 public void riordina(){
  int partZ;
  squadra.palla.rimuovi();
  squadra.rimuovitutto();
  parent.porta1.rimuovi();     parent.porta2.rimuovi();
  parent.porta1.introduci();   squadra.palla.introduci();
   partZ =confrontoZ(0,4,squadra.portiere1.dammiY());   squadra.portiere1.introduciAt(partZ);
   partZ =confrontoZ(1,1,squadra.player1_1.dammiY());   squadra.player1_1.introduciAt(partZ);
   partZ =confrontoZ(2,2,squadra.player1_2.dammiY());   squadra.player1_2.introduciAt(partZ);
   partZ =confrontoZ(3,3,squadra.player1_3.dammiY());   squadra.player1_3.introduciAt(partZ);
   partZ =confrontoZ(4,5,squadra.player2_1.dammiY());   squadra.player2_1.introduciAt(partZ);
   partZ =confrontoZ(5,6,squadra.player2_2.dammiY());   squadra.player2_2.introduciAt(partZ);
   partZ =confrontoZ(6,7,squadra.player2_3.dammiY());   squadra.player2_3.introduciAt(partZ);
   partZ =confrontoZ(7,8,squadra.portiere2.dammiY());   squadra.portiere2.introduciAt(partZ);
  parent.porta2.introduci();
 }



 public void azione(int x,int y){
   if (!inAttesaPartenza & !inRiposizionamentoGol) {
    if (squadra.infallo==0) {
//     if (!finepartita) {
      squadra.spostamiogiocatore(x,y);
//      }
    }
   }
 }

 public void fired(){
   if (!inAttesaPartenza & !inRiposizionamentoGol) {
    squadra.firedmiogiocatore();
   }

   if (  (squadra.appenafattofallo) & (!squadra.aspettaappenafattofallo)) {
    squadra.appenafattofallo=false;
    squadra.assegnaschemacasuale(1,true);
    squadra.assegnaschemacasuale(2,false);
   }


 }


 public void FattoGol(int sq) {
  inRiposizionamentoGol=true;
  squadra.fattogolpos(sq);
  if (sq==1) {PuntiBianchi++; };
  if (sq==2) {PuntiBlu++;};
  lastgol=sq;
  squadra.pallaDi=0;
  squadra.annullaaspettapalla();
 }



  private void createSquadre()   throws IOException {
   squadra = new SquadraObj(parent,this);
  }





}



