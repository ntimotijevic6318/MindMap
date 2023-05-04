package dsw.rudok.app.core.errhandler;

import java.io.*;

public class FileLogger implements ErrorLogger {

    MessageGenerator messageGenerator;
    boolean alredyCreated = false;

    public FileLogger(MessageGenerator messageGenerator){
     this.messageGenerator = messageGenerator;
     this.messageGenerator.addSubscriber(this);
    }

    @Override
    public void log(Message message) {

        try{

        if(alredyCreated == false){
            File file = new File("D:\\InteliJWork\\rm\\src\\main\\resources\\log.txt");
            file.createNewFile();
            alredyCreated = true;
        }
        Writer output;
        output = new BufferedWriter(new FileWriter("D:\\InteliJWork\\rm\\src\\main\\resources\\log.txt" , true));  //clears file every time
            output.append("[" + message.getType().name() + "]" + " " + message.getTimestamp() + " "  + "[" + message.getContent() + "]");
            output.append("\n") ;
            output.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Object notification) {
        Message message = (Message) notification;
        log(message);
    }
}
