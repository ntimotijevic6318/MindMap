package dsw.rudok.app.state;

import dsw.rudok.app.gui.swing.view.ProjectView;
import dsw.rudok.app.state.concrete.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter

public class StateManager {

    private State currentState;

    private ConceptState conceptState;
    private LinkState linkState;
    private SelectionState selectionState;
    private RemoveState removeState;
    private MoveState moveState;

    private ZoomState zoomSate;

    public StateManager() {
        initStates();
    }

    private void initStates() {

        conceptState = new ConceptState();
        linkState = new LinkState();
        selectionState = new SelectionState();
        removeState = new RemoveState();
        moveState = new MoveState();

        zoomSate = new ZoomState();

    }

    public State getCurrent() {
        return currentState;
    }

    public void setConceptState() {
        currentState = conceptState;
    }

    public void setLinkState() {
        currentState = linkState;
    }

    public void setSelectionState() {
        currentState = selectionState;
    }

    public void setRemoveState() {currentState = removeState;}

    public void setMoveState() {currentState = moveState;}

    public void startZoomState() {currentState = zoomSate;}
}
