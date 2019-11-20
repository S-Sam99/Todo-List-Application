package GUI.Tools;

import GUI.ToolPanel;
import GUI.UserForm;

import javax.swing.*;

public abstract class Tool {
    protected Boolean active;
    protected ToolPanel toolPanel;
    protected JButton button;

    public Tool(ToolPanel toolpanel, JComponent parent) {
        this.toolPanel = toolpanel;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    protected void createButton(JComponent parent) {
        button = new JButton(getLabel());
        button = customizeButton(button);
        addToParent(parent);
    }

    //EFFECTS: Returns the string for the label.
    public abstract String getLabel();

    // getters
    public boolean isActive() { return active; }

    // EFFECTS: sets this Tool's active field to true
    public void activate() {active = true; }

    // EFFECTS: sets this Tool's active field to false
    public void deactivate() {active = false; }


    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}
