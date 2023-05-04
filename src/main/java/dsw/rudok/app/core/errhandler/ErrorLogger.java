package dsw.rudok.app.core.errhandler;

import dsw.rudok.app.observer.ISubscriber;

public interface ErrorLogger extends ISubscriber {
    void log(Message message);
}
