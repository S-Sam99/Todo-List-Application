package GUI.Tools.Priorities;

import GUI.ToolPanel;
import GUI.Tools.PriorityTool;
import GUI.Tools.Tool;
import GUI.UserForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LowPriority extends PriorityTool {
    public LowPriority (ToolPanel toolpanel, JComponent parent){
        super(toolpanel, parent);
    }

    @Override
    public String getLabel(){return "Low";}

    @Override
    protected void addListener() {
        button.addActionListener(new LowPriority.LowPriorityClickHandler());
    }

    private class LowPriorityClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {toolPanel.setActivePriorityTool(LowPriority.this); }
    }
}
