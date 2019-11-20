package GUI.Tools.Type;

import GUI.ToolPanel;
import GUI.Tools.Tool;
import GUI.UserForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NormalType extends Tool {
    public NormalType(ToolPanel toolpanel, JComponent parent){
        super(toolpanel, parent);
    }

    @Override
    public String getLabel(){return "Normal";}

    @Override
    protected void addListener() {
        button.addActionListener(new NormalType.NormalTypeClickHandler());
    }

    private class NormalTypeClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {toolPanel.setActiveTypeTool(NormalType.this); }
    }
}
