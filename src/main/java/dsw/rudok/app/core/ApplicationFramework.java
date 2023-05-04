package dsw.rudok.app.core;

import dsw.rudok.app.core.errhandler.*;
import dsw.rudok.app.gui.swing.view.MainFrame;
import dsw.rudok.app.serializer.GsonSerializer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationFramework {

    protected Gui gui;
    protected MapRepository mapRepository;

    MessageGenerator messageGenerator;
    ErrorLogger fileLogger;
    ErrorLogger consoleLogger;

    GsonSerializer serializer;

    public void run(){
        this.gui.start();
    }

    public void initialise(Gui gui, MapRepository mapRepository, GsonSerializer serializer)
    {
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.messageGenerator = new MessageGeneratorImp();
        this.messageGenerator.addSubscriber(MainFrame.getInstance());

        fileLogger =  new FileLogger(messageGenerator);
        consoleLogger = new ConsoleLogger(messageGenerator);

        this.serializer = serializer;
    }

    // Singleton
    private static ApplicationFramework instance;

    private ApplicationFramework(){

    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

}
