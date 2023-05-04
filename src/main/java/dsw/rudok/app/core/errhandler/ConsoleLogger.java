package dsw.rudok.app.core.errhandler;

public class ConsoleLogger implements ErrorLogger{

    MessageGenerator messageGenerator;

    public ConsoleLogger(MessageGenerator messageGenerator){
        this.messageGenerator = messageGenerator;
        this.messageGenerator.addSubscriber(this);
    }

    @Override
    public void log(Message message) {
        System.out.println("[" + message.getType().name() + "]" + " " + message.getTimestamp() + " " + "["  + message.content + "]");
    }

    @Override
    public void update(Object notification) {
        Message message = (Message) notification;
        log(message);
    }

}
