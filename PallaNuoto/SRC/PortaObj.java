package PallaNuoto;

// ----------------------------------- //
// -- (c) CopyRight CarloMacor.com  -- //
//                2005                 //
// ----------------------------------- //

//  public PortaObj(MainCanvas parent, String fileName) throws IOException 
//  public void rimuovi()
//  public void introduci()



import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

public class PortaObj {

  public  Sprite     mPorta;
  private MainCanvas parent;


  public PortaObj(MainCanvas parent, String fileName) throws IOException {
    this.parent = parent;
    Image image  = Image.createImage(fileName);
    mPorta = new Sprite(image, 56, 20);
    mPorta.defineReferencePixel(28, 10);
    mPorta.setTransform(Sprite.TRANS_NONE);
    mPorta.setVisible(false);
  }

  public void rimuovi(){
   parent.mLayerManager.remove(mPorta);
  }

  public void introduci(){
   parent.mLayerManager.insert(mPorta,0);
  }




}

