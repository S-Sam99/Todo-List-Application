package GUI.Tools;

import GUI.ToolPanel;

import javax.swing.*;

public abstract class PriorityTool extends Tool {
    public PriorityTool (ToolPanel toolpanel, JComponent parent){
        super(toolpanel, parent);
    }

    public abstract String getLabel();

    public void shutDown(){
        button.setEnabled(false);
    }

    public void powerUp(){
        button.setEnabled(true);
    }
}
