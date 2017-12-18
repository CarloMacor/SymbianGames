package Euleros;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import javax.microedition.rms.*;
import java.lang.String.*;


public class SetQuadro {

    private Interface    Qparent;
    private String       DD;

    public SetQuadro(Interface Qparent)  {
    this.Qparent = Qparent;
   }


  private String DammiDDdaFile(int indquadro,int senuovo) {
   String DF = "";   int indiceF =0;
   indiceF = indquadro+1;
   if (senuovo==1) {indiceF = indiceF+100;};
   try {
    RecordStore rs = null;  rs = RecordStore.openRecordStore("Pt2",false);
    DF = new String (rs.getRecord(indiceF));
    rs.closeRecordStore();  rs = null;
   }  catch ( Exception e){  }
   return DF;
  }


  public void DefinisciQuadro(int indquadro,int senuovo) {

   int valo  = 0;
   int rr ;
   int cc ;
   for (int i=0; i<9; i++) { for (int j=0; j<9; j++) {Qparent.Ac[i][j] =0; }};


    DD = DammiDDdaFile(indquadro,1); // 0 continue, 1 nuovo
    for (int i=0; i<81; i++) {
     switch (DD.charAt(i)) {
      case '0' : {valo=0; }; break;    case '1' : {valo=1; }; break;    case '2' : {valo=2; }; break;
      case '3' : {valo=3; }; break;    case '4' : {valo=4; }; break;    case '5' : {valo=5; }; break;
      case '6' : {valo=6; }; break;    case '7' : {valo=7; }; break;    case '8' : {valo=8; }; break;
      case '9' : {valo=9; }; break;
     }
     rr = (i / 9);    cc = (i % 9);    Qparent.Ac[rr][cc]=valo;
    }



   for (int i=0; i<9; i++) { for (int j=0; j<9; j++) {
      Qparent.Av[i][j]=0;            Qparent.AVis[i][j]=false;
      Qparent.AFix[i][j]=false;      Qparent.AHelp[i][j]=false;
      if ((Qparent.Ac[i][j])!=0)    {Qparent.passaValue(i+1,j+1); };
     }
   }

   if (senuovo==0) {
    DD = DammiDDdaFile(indquadro,0); // 0 continue, 1 nuovo
    for (int i=0; i<81; i++) {
     switch (DD.charAt(i)) {
      case '0' : {valo=0; }; break;    case '1' : {valo=1; }; break;    case '2' : {valo=2; }; break;
      case '3' : {valo=3; }; break;    case '4' : {valo=4; }; break;    case '5' : {valo=5; }; break;
      case '6' : {valo=6; }; break;    case '7' : {valo=7; }; break;    case '8' : {valo=8; }; break;
      case '9' : {valo=9; }; break;
     }
     rr = (i / 9);    cc = (i % 9);
     if (Qparent.Ac[rr][cc]==0) {
      if (valo!=0) { Qparent.Av[rr][cc]=valo; Qparent.AVis[rr][cc]=true;  }
     }
    }
   }



   Qparent.Solved=false;
   Qparent.AttivazioneQuadro(senuovo);
  }




}




