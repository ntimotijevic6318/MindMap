package dsw.rudok.app.serializer;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.composite.MapNodeComposite;
import dsw.rudok.app.repository.implementation.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;

public class ProjectAdapter extends TypeAdapter<Project> {

    @Override
    public void write(JsonWriter writer, Project project) throws IOException {

        writer.beginObject();

        writer.name("name");
        writer.value(project.getName());


        writer.name("authorName");
        writer.value(project.getAuthorName());

        writer.name("filePath");
        writer.value(project.getFilePath());

        writer.name("changed");
        writer.value(project.isChanged());

        writer.name("Project children");
        writer.beginArray();


        for (MapNode mapNode : project.getChildren()) {

            writer.beginObject();
            writer.name("Map name").value(mapNode.getName());
            writer.name("Map children");
            writer.beginArray();
            for (MapNode element : ((MapNodeComposite) mapNode).getChildren()) {
                Element e = (Element) element;
                writer.beginObject();

                if (e instanceof Concept) {
                    writer.name("Element type").value("Concept");
                    writer.name("Element name").value(e.getName());
                    writer.name("Stroke size").value(e.getStroke());
                    writer.name("Element color").value(e.getElementColor().getRGB());
                    writer.name("Element position x").value(((Concept) e).getPosition().x);
                    writer.name("Element position y").value(((Concept) e).getPosition().y);
                    writer.name("Element width").value(((Concept) e).getSize().width);
                    writer.name("Element height").value(((Concept) e).getSize().height);
                    writer.name("Stroke color").value(((Concept) e).getStrokeColor().getRGB());
                    writer.name("Text color").value(((Concept)e).getTextColor().getRGB());
                    writer.name("Text size").value(((Concept) e).getFont().getSize());
                    writer.name("Family").value(((Concept) e).getFont().getFamily());
                }

                if (e instanceof Link) {
                    writer.name("Element type").value("Link");
                    writer.name("Element name").value(e.getName());
                    writer.name("From").value(((Link) e).getFrom().hashCode());
                    writer.name("To").value(((Link) e).getTo().hashCode());
                    writer.name("Stroke size").value(e.getStroke());
                    writer.name("Element color").value(e.getElementColor().getRGB());
                    writer.name("Stroke color").value(e.getStrokeColor().getRGB());
                    writer.name("Link startPos x").value(((Link) e).getSourcePosition().x);
                    writer.name("Link startPos y").value(((Link) e).getSourcePosition().y);
                    writer.name("Link endPos x").value(((Link) e).getDestinationPosition().x);
                    writer.name("Link endPos y").value(((Link) e).getDestinationPosition().y);
                }

                writer.endObject();
            }
            writer.endArray();
            writer.endObject();
        }

        writer.endArray();
        writer.endObject();

    }

    @Override
    public Project read(JsonReader in) throws IOException {
        return null;
    }
}

