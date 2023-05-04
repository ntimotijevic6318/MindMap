package dsw.rudok.app.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dsw.rudok.app.core.Serializer;
import dsw.rudok.app.gui.swing.tree.model.MapTreeItem;
import dsw.rudok.app.gui.swing.view.*;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.composite.MapNodeComposite;
import dsw.rudok.app.repository.implementation.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class GsonSerializer extends Serializer {

     private final Gson g_son = new Gson();
     private final Gson m_gson = new Gson();

     GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Project.class , new ProjectAdapter()).setPrettyPrinting();
     Gson  gson = gsonBuilder.create();


    GsonBuilder mgsonBuilder = new GsonBuilder().registerTypeAdapter(Map.class , new MapAdapter()).setPrettyPrinting();
    Gson  mgson = mgsonBuilder.create();


     private String jsonString;
     private Project project;
     private Map map;


    public void saveProject(Project project) {
        try (FileWriter writer = new FileWriter(project.getFilePath())) {
            gson.toJson(project , writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Project loadProject(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            project = g_son.fromJson(fileReader , Project.class);
            project = setUpProject(project);
            return project;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveMap(Map map){
        try (FileWriter writer = new FileWriter(map.getFilePath())) {
            mgson.toJson(map , writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map loadMap(File file){
        try (FileReader fileReader = new FileReader(file)) {
            map = m_gson.fromJson(fileReader , Map.class);
            map.setFilePath(file.getPath());
            map = setUpMap(map);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map setUpMap(Map map) {

        String filePath = map.getFilePath();

        map = new Map(map.getName() , null) ;


        String t = null;
        String elementName = null;
        int stroke = 0;
        int elementColor = 0;
        int elementPositionX = 0;
        int elementPositionY = 0;
        int elementWidth = 0;
        int elementHeight = 0;
        int strokeColor = 0;
        int textColor = 0;
        int textSize = 0;
        String fontFamily = null;


        //Link
        int startPosX = 0;
        int startPosY = 0;
        int endPosX = 0;
        int endPosY = 0;


        String content = null;
        try {
            content = readFile(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonString =  content;


        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray arrElement = object.getJSONArray("Elements");

            for(int j=0 ;  j< arrElement.length() ; j++){
               String type = arrElement.getJSONObject(j).getString("Element type");
               if(type.equals("Concept")){

                   elementName = arrElement.getJSONObject(j).getString("Element name");
                   stroke = arrElement.getJSONObject(j).getInt("Stroke size");
                   elementColor = arrElement.getJSONObject(j).getInt("Element color");

                   elementPositionX = arrElement.getJSONObject(j).getInt("Element position x");
                   elementPositionY = arrElement.getJSONObject(j).getInt("Element position y");
                   elementWidth = arrElement.getJSONObject(j).getInt("Element width");
                   elementHeight = arrElement.getJSONObject(j).getInt("Element height");
                   strokeColor = arrElement.getJSONObject(j).getInt("Stroke color");
                   textColor = arrElement.getJSONObject(j).getInt("Text color");
                   textSize = arrElement.getJSONObject(j).getInt("Text size");
                   fontFamily = arrElement.getJSONObject(j).getString("Family");

               }else if(type.equals("Link"))
               {
                   elementName = arrElement.getJSONObject(j).getString("Element name");
                   stroke = arrElement.getJSONObject(j).getInt("Stroke size");
                   elementColor = arrElement.getJSONObject(j).getInt("Element color");
                   strokeColor = arrElement.getJSONObject(j).getInt("Stroke color");

                   startPosX = arrElement.getJSONObject(j).getInt("Link startPos x");
                   startPosY = arrElement.getJSONObject(j).getInt("Link startPos y");

                   endPosX = arrElement.getJSONObject(j).getInt("Link endPos x");
                   endPosY = arrElement.getJSONObject(j).getInt("Link endPos y");
               }

                if (type.equals("Concept")) {
                    Concept concept = new Concept(elementName, map , new Color(elementColor), new Color(strokeColor), stroke, new Point(elementPositionX, elementPositionY), new Dimension(elementWidth, elementHeight), new Color(textColor));
                    concept.setText(elementName);
                    concept.setFont(new Font(fontFamily , Font.PLAIN , textSize));

                    MainFrame.getInstance().getMapTree().addChild(concept , MainFrame.getInstance().getMapTree().getParent());


                } else if (type.equals("Link")) {
                    Link link = new Link(elementName, map, new Color(elementColor), new Color(strokeColor), stroke);

                    link.setSourcePosition(new Point(startPosX, startPosY));
                    link.setDestinationPosition(new Point(endPosX, endPosY));

                    MainFrame.getInstance().getMapTree().addChild(link , MainFrame.getInstance().getMapTree().getParent());

                }
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return map;

    }

    private Project setUpProject(Project project) {

        String filePath = project.getFilePath();

        project = new Project(project.getName() , null);

        String content = null;
        try {
            content = readFile(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

         jsonString =  content;

        try {

            JSONObject obj = new JSONObject(jsonString);
            project.setName(obj.getString("name"));
            project.setAuthorName(obj.getString("authorName"));
            project.setChanged(obj.getBoolean("changed"));


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return  project;
    }


    public void addMaps(Project project) {
        try {
            JSONObject obj = new JSONObject(jsonString);
            JSONArray arr = null;

            arr = obj.getJSONArray("Project children");
            for (int i = 0; i < arr.length(); i++)
            {
                Map map = new Map(arr.getJSONObject(i).getString("Map name") , project);
                project.addChild(map);

                //loogika za tree
                MapTreeItem Jproject =  MainFrame.getInstance().getMapTree().findProjectMapTree(project);
                Jproject.add(new MapTreeItem(map));

                MainFrame.getInstance().getMapTree().getTreeView().expandPath(MainFrame.getInstance().getMapTree().getTreeView().getSelectionPath());
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTree().getTreeView());
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


   public void addElements(ProjectView projectView) {

       //Concept
       String t = null;
       String elementName = null;
       int stroke = 0;
       int elementColor = 0;
       int elementPositionX = 0;
       int elementPositionY = 0;
       int elementWidth = 0;
       int elementHeight = 0;
       int strokeColor = 0;
       int textColor = 0;
       int textSize = 0;
       String fontFamily = null;


       //Link
       int startPosX = 0;
       int startPosY = 0;
       int endPosX = 0;
       int endPosY = 0;


       MapView mapV = null;

       try {
           JSONObject obj = new JSONObject(jsonString);
           JSONArray arr = null;
           JSONArray arrElement = null;

           arr = obj.getJSONArray("Project children");

           for (int i = 0; i < arr.length(); i++) {


               String mapName = arr.getJSONObject(i).getString("Map name");
               MapNode parent = null;

               for (MapView mapView : projectView.getMapViewList()) {
                   if (mapView.getMap().getName().equals(mapName)) {
                       parent = mapView.getMap();
                       mapV = mapView;
                   }
               }

               arrElement = arr.getJSONObject(i).getJSONArray("Map children");

               for (int j = 0; j < arrElement.length(); j++) {

                   t = arrElement.getJSONObject(j).getString("Element type");

                   if (t.equals("Concept")) {
                       elementName = arrElement.getJSONObject(j).getString("Element name");
                       stroke = arrElement.getJSONObject(j).getInt("Stroke size");
                       elementColor = arrElement.getJSONObject(j).getInt("Element color");

                       elementPositionX = arrElement.getJSONObject(j).getInt("Element position x");
                       elementPositionY = arrElement.getJSONObject(j).getInt("Element position y");
                       elementWidth = arrElement.getJSONObject(j).getInt("Element width");
                       elementHeight = arrElement.getJSONObject(j).getInt("Element height");
                       strokeColor = arrElement.getJSONObject(j).getInt("Stroke color");
                       textColor = arrElement.getJSONObject(j).getInt("Text color");
                       textSize = arrElement.getJSONObject(j).getInt("Text size");
                       fontFamily = arrElement.getJSONObject(j).getString("Family");

                   } else if (t.equals("Link")) {


                       elementName = arrElement.getJSONObject(j).getString("Element name");
                       stroke = arrElement.getJSONObject(j).getInt("Stroke size");
                       elementColor = arrElement.getJSONObject(j).getInt("Element color");
                       strokeColor = arrElement.getJSONObject(j).getInt("Stroke color");

                       startPosX = arrElement.getJSONObject(j).getInt("Link startPos x");
                       startPosY = arrElement.getJSONObject(j).getInt("Link startPos y");

                       endPosX = arrElement.getJSONObject(j).getInt("Link endPos x");
                       endPosY = arrElement.getJSONObject(j).getInt("Link endPos y");
                   }

                   if (t.equals("Concept")) {
                       Concept concept = new Concept(elementName, null, new Color(elementColor), new Color(strokeColor), stroke, new Point(elementPositionX, elementPositionY), new Dimension(elementWidth, elementHeight), new Color(textColor));
                       concept.setText(elementName);
                       concept.setFont(new Font(fontFamily , Font.PLAIN , textSize));

                       ((MapNodeComposite) parent).addChild(concept);
                       concept.setParent(parent);

                       MapTreeItem Jmap =  MainFrame.getInstance().getMapTree().findMapTree(mapV , project);
                       Jmap.add(new MapTreeItem(concept));

                       MainFrame.getInstance().getMapTree().getTreeView().expandPath(MainFrame.getInstance().getMapTree().getTreeView().getSelectionPath());
                       SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTree().getTreeView());

                   } else if (t.equals("Link")) {
                       Link link = new Link(elementName, null, new Color(elementColor), new Color(strokeColor), stroke);

                       link.setSourcePosition(new Point(startPosX, startPosY));
                       link.setDestinationPosition(new Point(endPosX, endPosY));

                       ((MapNodeComposite) parent).addChild(link);
                       link.setParent(parent);

                       MapTreeItem Jmap =  MainFrame.getInstance().getMapTree().findMapTree(mapV , project);
                       Jmap.add(new MapTreeItem(link));

                       MainFrame.getInstance().getMapTree().getTreeView().expandPath(MainFrame.getInstance().getMapTree().getTreeView().getSelectionPath());
                       SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTree().getTreeView());
                   }
               }
           }
       } catch (JSONException e) {
           throw new RuntimeException(e);
       }
   }


    public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


}
