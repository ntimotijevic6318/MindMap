package dsw.rudok.app.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    //Gornji ToolBar
    private InfoAction infoAction;
    private NewProjectAction newProjectAction;
    private DeleteAction deleteAction ;
    private AddAuthorAction addAuthorAction;
    private ZoomAction zoomAction;

    private UndoAction undoAction;
    private RedoAction redoAction;


    //ProjectViewToolBar
    private ConceptAction conceptAction;
    private LinkAction linkAction;
    private SelectionAction selectionAction;
    private RemoveAction removeAction;
    private MoveAction moveAction;


    private SaveAction saveAction;
    private SaveAsAction saveAsAction;
    private OpenAction openAction;

    private ExportAction exportAction;
    private TemplateSaveAsAction templateSaveAsAction;
    private LoadTemplate loadTemplate ;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        infoAction = new InfoAction();
        newProjectAction = new NewProjectAction();
        deleteAction = new DeleteAction();
        addAuthorAction = new AddAuthorAction();

        zoomAction = new ZoomAction();

        conceptAction = new ConceptAction();
        linkAction=  new LinkAction();
        selectionAction = new SelectionAction();
        removeAction = new RemoveAction();
        moveAction = new MoveAction();

        saveAction  = new SaveAction();
        saveAsAction = new SaveAsAction();
        openAction = new OpenAction();

        undoAction = new UndoAction();
        redoAction = new RedoAction();

        exportAction = new ExportAction();
        templateSaveAsAction = new TemplateSaveAsAction();
        loadTemplate = new LoadTemplate();
    }
}
