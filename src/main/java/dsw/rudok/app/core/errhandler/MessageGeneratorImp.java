package dsw.rudok.app.core.errhandler;

import dsw.rudok.app.observer.ISubscriber;
import dsw.rudok.app.repository.implementation.NotificationType;

import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImp implements MessageGenerator{

    List<ISubscriber> subscribers;

    @Override
    public void generate(Enum eventType) {

        switch(eventType.name()){
            case "NODE_CANNOT_BE_DELETED" : notifySubscribers(new Message("Project Explorer ne može biti obrisan!", MessageType.ERROR)); break;
            case "CANNOT_ADD_CHILD_TO_LEAF" : notifySubscribers(new Message("Nije moguce dodati cvor jer je selektovani cvor leaf" , MessageType.WARNING)); break;
            case "NAME_CANNOT_BE_EMPTY_STRING" : notifySubscribers(new Message("Nije moguce da cvor nema ime!" , MessageType.ERROR)); break;
            case "CANNOT_NAME_NODE_WITH_EXISTING_NAME" : notifySubscribers(new Message("Ime noda je zauzeto" , MessageType.ERROR)); break;
            case "NEED_TO_SELECT_PROJECT" : notifySubscribers(new Message("Morate selektovati projekat da biste uneli autora" , MessageType.WARNING)); break;
            case "NEED_TO_SELECT_NODE_TO_DELETE" : notifySubscribers(new Message("Morate prvo selektovati node da biste ga obrisali" , MessageType.WARNING)); break;
            case "NEED_TO_SELECT_NODE_TO_ADD" : notifySubscribers(new Message("Morate da selektujete node da bi dodali novi", MessageType.WARNING)); break;
            case "CANNOT_ADD_CONCEPT_AT_THIS_PLACE" : notifySubscribers(new Message("Na mestu na kom je predvidjeno dodavanje novog elemeta se vec nalazi element" , MessageType.ERROR)); break;
            case "CONCEPT_MUST_HAVE_NAME" : notifySubscribers(new Message("Pojam mora da ima ime!" , MessageType.ERROR )); break;
            case "NEED_TO_SELECT_STATE" :  notifySubscribers(new Message("Morate selektovati neko od stanja" , MessageType.ERROR)); break;
            case "NEED_TO_SELECT_ELEMENT" : notifySubscribers(new Message("Morate selektovati bar jedan element da bi stega pomerili!" , MessageType.WARNING)); break;
            case "NEED_TO_SELECT_AT_LEAST_ONE_ELEMENT" : notifySubscribers(new Message("Da biste vrsili izmenu nad elementima morate selektovati bar jedan" , MessageType.WARNING)); break;
            case "LINE_MUST_HAVE_START_POINT" : notifySubscribers(new Message("Niste kliknuli ni na jedan pojam - Linija se ne moze iscrtati!" , MessageType.WARNING)); break;
            case "LINE_MUST_HAVE_END_POINT" : notifySubscribers(new Message("Tacku na kojoj ste otpustili mis ne sadrzi ni jedan pojan - Linija se ne moze iscrtati!", MessageType.WARNING)); break;
            case "YOU_GO_OUTSIDE_WORKSPACE" : notifySubscribers(new Message("Izasli ste iz radne povrsine"  , MessageType.WARNING)); break;
            case "LINK_CANNOT_BE_INSIDE_ONE_ELEMENT" : notifySubscribers(new Message("Linija ne moze da pocinje i da se zavrsava u istom elementu!" , MessageType.WARNING)); break;
            case "NEED_TO_SAVE_ONLY_PROJECT" :  notifySubscribers(new Message("Samo projekat moze da se sacuva!" , MessageType.ERROR)); break;
            case "PROJECT_ALREADY_EXIST" : notifySubscribers(new Message("Vec je ucitan projekat sa tim imenom!" , MessageType.ERROR)); break;
            case "PNG_CREATED" : notifySubscribers(new Message("png je uspesno eksportovan!" , MessageType.NOTIFICATION)); break;
            case "NEED_TO_SAVE_ONLY_MAP" : notifySubscribers(new Message("Samo mapa moze da se sacuva kao template!" , MessageType.ERROR)); break;
            case "CANNOT_ZOOM_BECAUSE_JT_IS_NOT_SHOWING" : notifySubscribers(new Message("Zumiranje nije moguce" , MessageType.WARNING)); break;
            case "NEED_TO_CLICK_ON_ELEMENT" : notifySubscribers(new Message("Niste kliknuli ni na jedan element- Brisanje nije moguce" , MessageType.WARNING)); break;
            case "PROJECT_SUCCESIFULL_LOADED" : notifySubscribers(new Message("Projekat je uspesno učitan u workspace" , MessageType.NOTIFICATION)); break;
            case "WARNING" : notifySubscribers(new Message("Brisanje ne radi bas kako treba, radi polovicno, ali ako mogu neki poen tu da dobijem okej :) , ako ne nemojte uzimati u obzir brisanje uopste" , MessageType.WARNING));
        }
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        // TODO Auto-generated method stub
        if (sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        // TODO Auto-generated method stub
        if (notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for (ISubscriber listener : subscribers) {
            listener.update(notification);
        }
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        // TODO Auto-generated method stub
        if (sub == null)
            return;
        if (this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if (this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);

    }
}
