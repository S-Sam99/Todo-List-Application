package GUI.Tools.Priorities;

import GUI.ToolPanel;
import GUI.Tools.PriorityTool;
import GUI.Tools.Tool;
import GUI.UserForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedPriority extends PriorityTool {
    public MedPriority(ToolPanel toolpanel, JComponent parent){
        super(toolpanel, parent);
    }

    @Override
    public String getLabel(){return "Med";}

    @Override
    protected void addListener() {
        button.addActionListener(new MedPriority.MedPriorityClickHandler());
    }

    private class MedPriorityClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {toolPanel.setActivePriorityTool(MedPriority.this); }
    }
}
