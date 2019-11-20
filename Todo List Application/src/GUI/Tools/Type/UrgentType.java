package GUI.Tools.Type;

import GUI.ToolPanel;
import GUI.Tools.Tool;
import GUI.UserForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UrgentType extends Tool {

    public UrgentType(ToolPanel toolpanel, JComponent parent){
        super(toolpanel, parent);
    }

    @Override
    public String getLabel(){return "Urgent";}

    @Override
    protected void addListener() {
        button.addActionListener(new UrgentTypeClickHandler());
    }

    private class UrgentTypeClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {toolPanel.setActiveTypeTool(UrgentType.this); }
    }
}
