package dsw.rudok.app.repository;

import dsw.rudok.app.core.MapRepository;
import dsw.rudok.app.repository.composite.MapNode;
import dsw.rudok.app.repository.composite.MapNodeComposite;
import dsw.rudok.app.repository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;


public class MapRepositoryImpl implements MapRepository {

    private ProjectExplorer projectExplorer;

    public MapRepositoryImpl() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(MapNodeComposite parent, MapNode child) {

    }
}
