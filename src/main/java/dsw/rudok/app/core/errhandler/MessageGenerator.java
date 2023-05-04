package dsw.rudok.app.core.errhandler;

import dsw.rudok.app.observer.IPublisher;

public interface MessageGenerator extends IPublisher {
    void generate(Enum eventType);
}
