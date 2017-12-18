package Line5;

//    public Score()
//    public void reset()
//    public void add()
//    public void add(int x)
//    public int getScore()
//    public int getHighScore()
//    private void introduciattualeRecord(){
//    public void load()
//    public void save()


import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import javax.microedition.rms.*;

import java.io.*;



import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;


public class Score {
    private long        score;
    private long        hi_score;
    public  long[]      recordG;
    private MainCanvas  parent;
    public  int         justintrodotto=-1;

    public Score(MainCanvas parent) {
      this.parent=parent;
      recordG = new long[10];
      score    = 0;
      hi_score = 0;
      load();
    }

    public void reset() {   score = 0;  }

    public void add() {    add(1);  }

    public void add(int x) {
        score += x;
        if (score > hi_score) { hi_score = score;
        }
    }

    public long getScore()     {   return score;      }

    public long getHighScore() {   return hi_score;   }

    public void load() {
     int locvalue=0;        long locvalueL=0;
     try {
      RecordStore rs = null;
      rs = RecordStore.openRecordStore("LL5",false);
      ByteArrayInputStream  bin  = null;
      DataInputStream       din  = null;
      byte[]                data = null;
      data = rs.getRecord(1);
      bin = new ByteArrayInputStream(data);
      din = new DataInputStream(bin);
      locvalue = din.readInt();
      if (locvalue==0) { parent.Suono=false;} else {  parent.Suono=true; };
      locvalue = din.readInt();   // secondo a disposizione
      for (int kj=0; kj<10 ; kj++) { locvalueL = din.readLong();    recordG[kj] = locvalueL;  }

      hi_score=recordG[0];
      din.close();
      bin.close();
      rs.closeRecordStore();
      rs = null;
     }
     catch ( Exception e){
      try{
       RecordStore rs = null;
       rs = RecordStore.openRecordStore("LL5",true);
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       DataOutputStream      dout = new DataOutputStream(bout);
       byte[] data = null;
       if (parent.Suono) {locvalue = 1;} else {locvalue = 0;}
       dout.writeInt(locvalue);
       locvalue = 1;  dout.writeInt(locvalue);
       for (int kj=0; kj<10 ; kj++) {  locvalueL = 0;   dout.writeLong(locvalueL);   }
       data = bout.toByteArray();     rs.addRecord(data,0,data.length);
       bout.reset();     rs.closeRecordStore();    rs = null;   dout.close();   bout.close();
       }   catch ( Exception e2){}
     }
    }


    private void introduciattualeRecord(){
     for ( int i=9; i>=0; i--) {
      if (recordG[i]<score) {
       if (i<9) { recordG[i+1]=recordG[i]; };
       recordG[i]=score; justintrodotto=i;
      }
     }
    }


    public void save() {
      introduciattualeRecord();
      int locvalue=0;        long locvalueL=0;
      try{
       RecordStore rs = null;
       rs = RecordStore.openRecordStore("LL5",true);
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       DataOutputStream      dout = new DataOutputStream(bout);
       byte[] data = null;
       if (parent.Suono) {locvalue = 1;} else {locvalue = 0;}
       dout.writeInt(locvalue);
       locvalue = 0;       dout.writeInt(locvalue);  // a disposizione
       for (int kj=0; kj<10 ; kj++) {  locvalueL = recordG[kj];   dout.writeLong(locvalueL);   }
       data = bout.toByteArray();
       rs.setRecord(1,data,0,data.length);
       bout.reset();     rs.closeRecordStore();    rs = null;   dout.close();   bout.close();
      }   catch ( Exception e2){}
    }




    public long getIndScore(int i){
     return  recordG[i];
    }


}

