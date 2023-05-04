package dsw.rudok.app.core;


import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.composite.MapNodeComposite;
import dsw.rudok.app.repository.implementation.ProjectExplorer;

public interface MapRepository {
    ProjectExplorer getProjectExplorer();
    void addChild(MapNodeComposite parent, MapNode child);
}
