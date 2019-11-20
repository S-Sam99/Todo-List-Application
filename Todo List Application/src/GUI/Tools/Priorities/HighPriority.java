package GUI.Tools.Priorities;

import GUI.ToolPanel;
import GUI.Tools.PriorityTool;
import GUI.Tools.Tool;
import GUI.UserForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighPriority extends PriorityTool {
    public HighPriority (ToolPanel toolpanel, JComponent parent){
        super(toolpanel, parent);
    }

    @Override
    public String getLabel(){return "High";}

    @Override
    protected void addListener() {
        button.addActionListener(new HighPriority.HighPriorityClickHandler());
    }


    private class HighPriorityClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {toolPanel.setActivePriorityTool(HighPriority.this); }
    }
}
