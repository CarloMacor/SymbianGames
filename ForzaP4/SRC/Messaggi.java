package Forza4;


// public class MessaggiItaliani  {
//   public Messaggi() {};
//   public String dammimsg(int papero,int indice){
//    String LocSt="";
//    switch(papero) {
//     case 1 :
//      switch(indice) {
//       case 0  : LocSt="Ci penso al volo."; break;
//       case 1  : LocSt="Ti hanno detto che e' facile vincermi? Bene fammi vedere quello che sai fare."; break;
//       case 2  : LocSt="Schiappa! se perdi con me non hai nessuna speranza nel continuare nel castello."; break;
//       case 3  : LocSt="Hai vinto! ma con gli altri non sara' cosi' facile."; break;
//       case 10 : LocSt="Ti servira' comunque un po' di impegno."; break;
//       case 11 : LocSt="Gioca gioca, intanto non hai ancora vinto."; break;
//       case 12 : LocSt="Sono comunque un buon allenatore."; break;
//       case 13 : LocSt="Prova a vincermi cosi' puoi andare ai piani alti"; break;
//       case 14 : LocSt="Lo so che sono debole, ma non si sa mai."; break;
//       case 15 : LocSt="Qualche volta faccio anche buone mosse."; break;
//       case 16 : LocSt="Non hai ancora vinto."; break;
//       case 17 : LocSt="Se pareggiamo ne facciamo un'altra."; break;
//       case 18 : LocSt="Dai muovi! Io sono velocissimo."; break;
//       case 19 : LocSt="Sono veloce ma non tanto forte."; break;
//       case 20 : LocSt="Pareggio! per me e' un buon risultato!"; break;
//      }
//      break;
//     case 2 :
//      switch(indice) {
//       case 0  : LocSt="Un solo momento."; break;
//       case 1  : LocSt="Son qui di guardia. Non ti faro passare facilmente."; break;
//       case 2  : LocSt="Hai perso Schiappa! torna pure nelle cantine ad allenarti." ; break;
//       case 3  : LocSt="Ahi ahi, Ho perso , meriti di passare al piano superiore. "; break;
//       case 10 : LocSt="Pensi di vincere facilmente?"; break;
//       case 11 : LocSt="Non mi supererai cosi' facilmente."; break;
//       case 12 : LocSt="Come vedi resisto bene."; break;
//       case 13 : LocSt="Intanto non hai ancora vinto."; break;
//       case 14 : LocSt="Se avessi piu' capacita' ti farei vedere."; break;
//       case 15 : LocSt="Come mai non riesci a vincere?"; break;
//       case 16 : LocSt="Almeno un pareggio ci scappa fuori."; break;
//       case 17 : LocSt="Se perdi sei proprio una schiappa!"; break;
//       case 18 : LocSt="Sono qui per difendere la torre."; break;
//       case 19 : LocSt="Se ti batto verrai ricacciato nelle cantine."; break;
//       case 20 : LocSt="Pareggio! per ora non vai avanti!"; break;
//      }
//      break;
//     case 3 :
//      switch(indice) {
//       case 0  : LocSt="Ci penso un poco."; break;
//       case 1  : LocSt="Non pensare di superarmi sono Fortissima!"; break;
//       case 2  : LocSt="Ha ha , Hai perso come tanti altri che hanno provato ad affrontarmi."; break;
//       case 3  : LocSt="Accipicchia , sono costretta a farti passare , ma lo stregone sapra' cosa fare!"; break;
//       case 10 : LocSt="Non esiste Femmina piu' forte di me!"; break;
//       case 11 : LocSt="Guarda che disposizione di pedine."; break;
//       case 12 : LocSt="Il mio gioco e' ad alto livello."; break;
//       case 13 : LocSt="Alla prossima mossa vado in vantaggio."; break;
//       case 14 : LocSt="Non penserai certo di vincere!"; break;
//       case 15 : LocSt="Le mie mosse sono sempre ben calibrate."; break;
//       case 16 : LocSt="Non passerai!"; break;
//       case 17 : LocSt="Ti vincero' ad ogni costo."; break;
//       case 18 : LocSt="Ne ho battuti tanti debolucci come te."; break;
//       case 19 : LocSt="Non sei cosi' forte."; break;
//       case 20 : LocSt="Pareggio! non sei il piu' forte"; break;
//      }
//      break;
//     case 4 :
//      switch(indice) {
//       case 0  : LocSt="Ora ci penso."; break;
//       case 1  : LocSt="Sei arrivato qui senza Magia? Ti sara' difficile continuare."; break;
//       case 2  : LocSt="Come vedi le mie mosse magiche ti hanno sconfitto! Ora torna negli scantinati."; break;
//       case 3  : LocSt="Ho trovato uno stregone piu' forte di me! Non posso crederci! Prova ora a battere il nostro Capo."; break;
//       case 10 : LocSt="Mi rimane facile prevedere la tua prossima mossa."; break;
//       case 11 : LocSt="So gia' che perderai."; break;
//       case 12 : LocSt="Poche moesse alla tua fine."; break;
//       case 13 : LocSt="Vincero' con una diagonale."; break;
//       case 14 : LocSt="Sono in vantaggio netto."; break;
//       case 15 : LocSt="Come pensi di battermi?"; break;
//       case 16 : LocSt="Hai notato il mio nuovo bastone magico?"; break;
//       case 17 : LocSt="Guarda guarda che mossa."; break;
//       case 18 : LocSt="Vincerti sara' facilissimo."; break;
//       case 19 : LocSt="Sono troppo allenato per te."; break;
//       case 20 : LocSt="Pareggio! Riprovaci se hai coraggio!"; break;
//      }
//      break;
//     case 5 :
//      switch(indice) {
//       case 0  : LocSt="Fammi pensare bene."; break;
//       case 1  : LocSt="Non so come hai fatto ad arrivare fin qui!  Non hai nessuna speranza di vincermi!"; break;
//       case 2  : LocSt="Hai perso ma questo era scontato! Torna nelle cantine del castello ad allenarti."; break;
//       case 3  : LocSt="Impossibile! Ho trovato uno piu' forte di me! Ma come Hai fatto!";   break;
//       case 10 : LocSt="Qualsiasi mossa non mi mette paura."; break;
//       case 11 : LocSt="Hai notato che sono gia' in vantaggio?"; break;
//       case 12 : LocSt="Presto perderai!"; break;
//       case 13 : LocSt="Sono troppo forte per te."; break;
//       case 14 : LocSt="Non hai speranza."; break;
//       case 15 : LocSt="Poche mosse ancora e ti batto!"; break;
//       case 16 : LocSt="Pensa, pensa... tanto vinco io."; break;
//       case 17 : LocSt="E' meglio che ti alleni di piu'."; break;
//       case 18 : LocSt="So gia' che mossa farai."; break;
//       case 19 : LocSt="Ma hai capito con chi stai giocando?"; break;
//       case 20 : LocSt="Pareggio! Rifacciamola subito!"; break;
//      }
//      break;
//    }
//    return LocSt;
//   }


public class Messaggi  {
   public Messaggi() {};
   public String dammimsg(int papero,int indice){
    String LocSt="";
    switch(papero) {
     case 1 :
      switch(indice) {
       case 0  : LocSt="Only a moment."; break;
       case 1  : LocSt="Who say that it's easy win me?"; break;
       case 2  : LocSt="Schiappa! If you lose you have no choise to continue in the castle."; break;
       case 3  : LocSt="You win! But it will not easy in the future."; break;
       case 10 : LocSt="You need an effort."; break;
       case 11 : LocSt="Play play.. for the moment you don't win."; break;
       case 12 : LocSt="I'm a good trainer."; break;
       case 13 : LocSt="If you win can go on the tower"; break;
       case 14 : LocSt="I'm not so strong, but it all it's possible."; break;
       case 15 : LocSt="Sometime I make good moves."; break;
       case 16 : LocSt="You don't win for the moment."; break;
       case 17 : LocSt="If we draw we could make another match."; break;
       case 18 : LocSt="Move! I'm so fast."; break;
       case 19 : LocSt="I'm fast but not so strong."; break;
       case 20 : LocSt="Draw! for me it's ok!"; break;
      }
      break;
     case 2 :
      switch(indice) {
       case 0  : LocSt="Only a moment."; break;
       case 1  : LocSt="I'm here as a guard. It's not easy pass."; break;
       case 2  : LocSt="You lost Schiappa! Go back." ; break;
       case 3  : LocSt="Ahi ahi, I lost , I can pass to the next level. "; break;
       case 10 : LocSt="Do you think to win me?"; break;
       case 11 : LocSt="You can't pass so easy."; break;
       case 12 : LocSt="How can you see it's not easy win me."; break;
       case 13 : LocSt="For the moment you can't win."; break;
       case 14 : LocSt="If I was better...."; break;
       case 15 : LocSt="Why you don't win?"; break;
       case 16 : LocSt="At least a draw."; break;
       case 17 : LocSt="If you lose you are a schiappa!"; break;
       case 18 : LocSt="I'm here to defent the tower."; break;
       case 19 : LocSt="If I win you'll go back."; break;
       case 20 : LocSt="Draw! for the moment you can't pass!"; break;
      }
      break;
     case 3 :
      switch(indice) {
       case 0  : LocSt="I think a little."; break;
       case 1  : LocSt="Don't hope to win me , I'm strong!"; break;
       case 2  : LocSt="Ha ha , You lost as a lot of other that try to play with me."; break;
       case 3  : LocSt="Hoooo, I must give you the pass , the magician know how win you!"; break;
       case 10 : LocSt="There is no female strong like me!"; break;
       case 11 : LocSt="Look at my position."; break;
       case 12 : LocSt="I have a high quality of strategy."; break;
       case 13 : LocSt="Next move and i'll have a good position."; break;
       case 14 : LocSt="Don't hope to win me!"; break;
       case 15 : LocSt="My moves are well done."; break;
       case 16 : LocSt="You can't pass!"; break;
       case 17 : LocSt="I'll win at any cost."; break;
       case 18 : LocSt="I won a lot players like you."; break;
       case 19 : LocSt="You are not so strong."; break;
       case 20 : LocSt="Draw! You are not able to win me"; break;
      }
      break;
     case 4 :
      switch(indice) {
       case 0  : LocSt="Now I think."; break;
       case 1  : LocSt="You have no magic stick? It's impossible continue."; break;
       case 2  : LocSt="How can you see my magic moves win! Come back down."; break;
       case 3  : LocSt="I met a magician better that me! I can't beleave it! Try to win our King."; break;
       case 10 : LocSt="It's easy know that you'll lose."; break;
       case 11 : LocSt="I know that you'll lose."; break;
       case 12 : LocSt="Few moves and I'll win."; break;
       case 13 : LocSt="I'll win on diagonal."; break;
       case 14 : LocSt="I'm in advantage."; break;
       case 15 : LocSt="How can you think to win me?"; break;
       case 16 : LocSt="Did' you notice my new magic stick?"; break;
       case 17 : LocSt="Hoo! look at this move."; break;
       case 18 : LocSt="Winning you it's so easy!."; break;
       case 19 : LocSt="I'm too strong for you."; break;
       case 20 : LocSt="Draw! do it again!"; break;
      }
      break;
     case 5 :
      switch(indice) {
       case 0  : LocSt="Let me think."; break;
       case 1  : LocSt="How can you arrive here! You have no choise to win!"; break;
       case 2  : LocSt="You lost of course! Go back down and make training."; break;
       case 3  : LocSt="Impossible! You are better that me! How is possible!";   break;
       case 10 : LocSt="no moves can make me problem."; break;
       case 11 : LocSt="Did you notice my advantage"; break;
       case 12 : LocSt="You'll lose soon!"; break;
       case 13 : LocSt="I'm too strong for you!"; break;
       case 14 : LocSt="You have no choise."; break;
       case 15 : LocSt="few moves and I'll win!"; break;
       case 16 : LocSt="Think , think... I win anyway."; break;
       case 17 : LocSt="Make more training, please."; break;
       case 18 : LocSt="I know what you'll do."; break;
       case 19 : LocSt="Do you know Who I'm?"; break;
       case 20 : LocSt="Draw! Play again immediatly!"; break;
      }
      break;
    }
    return LocSt;
   }




}
