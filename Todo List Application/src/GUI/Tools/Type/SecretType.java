package GUI.Tools.Type;

import GUI.ToolPanel;
import GUI.Tools.Tool;
import GUI.UserForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecretType extends Tool {
    public SecretType(ToolPanel toolpanel, JComponent parent){
        super(toolpanel, parent);
    }

    @Override
    public String getLabel(){return "Secret";}

    @Override
    protected void addListener() {
        button.addActionListener(new SecretType.SecretTypeClickHandler());
    }

    private class SecretTypeClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {toolPanel.setActiveTypeTool(SecretType.this); }
    }
}
