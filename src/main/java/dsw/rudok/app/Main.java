package dsw.rudok.app;

import dsw.rudok.app.core.ApplicationFramework;
import dsw.rudok.app.core.Gui;
import dsw.rudok.app.core.MapRepository;
import dsw.rudok.app.core.Serializer;
import dsw.rudok.app.gui.swing.SwingGui;
import dsw.rudok.app.repository.MapRepositoryImpl;
import dsw.rudok.app.serializer.GsonSerializer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {


        ApplicationFramework appCore = ApplicationFramework.getInstance();
        Gui gui = new SwingGui();
        GsonSerializer serializer = new GsonSerializer();
        MapRepository mapRepository = new MapRepositoryImpl();
        appCore.initialise(gui, mapRepository , serializer);
        appCore.run();
    }




}
