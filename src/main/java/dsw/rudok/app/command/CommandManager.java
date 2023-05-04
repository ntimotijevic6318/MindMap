package dsw.rudok.app.command;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

public class CommandManager {

    private List<Command> commands = new ArrayList<Command>();
    //pokazivač steka, sadrži redni broj komande za undo / redo operaciju
    private int currentCommand = 0;
    /*
     * Dodaje novu komandu na stek i poziva izvršavanje komande
     */
    public void addCommand(Command command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }

    public void removeCommand(Command command){
        this.commands.remove(command);
    }

    /*
     * Metoda koja poziva izvršavanje konkretne komande
     */
    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            ApplicationFramework.getInstance().getGui().enableUndoAction();
        }
        if(currentCommand==commands.size()){
            ApplicationFramework.getInstance().getGui().disableRedoAction();
        }
    }

    /*
     * Metoda koja poziva redo konkretne komande
     */
    public void undoCommand(){

         if(currentCommand > 0){
            ApplicationFramework.getInstance().getGui().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
        }

        if(currentCommand==0){
            ApplicationFramework.getInstance().getGui().disableUndoAction();
        }
    }


    }
