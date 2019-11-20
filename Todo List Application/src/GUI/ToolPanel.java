package GUI;

import GUI.Tools.Priorities.HighPriority;
import GUI.Tools.Priorities.LowPriority;
import GUI.Tools.Priorities.MedPriority;
import GUI.Tools.PriorityTool;
import GUI.Tools.Tool;
import GUI.Tools.Type.NormalType;
import GUI.Tools.Type.SecretType;
import GUI.Tools.Type.UrgentType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ToolPanel extends JPanel {
    UserForm form;
    private Tool activePriorityTool;
    private Tool activeTypeTool;

    private java.util.List<Tool> typeTools;
    private List<Tool> priorityTools;

    private JLabel typeLabel;
    private JLabel priorityLabel;

    private PriorityTool btnhigh;
    private PriorityTool btnmed;
    private PriorityTool btnlow;

    private Font font = new Font("Ariel", Font.CENTER_BASELINE, 20);

    public ToolPanel(UserForm form){
        this.form = form;
        typeTools = new ArrayList<>();
        priorityTools = new ArrayList<>();
        activePriorityTool = null;
        activeTypeTool = null;
        typeLabel = new JLabel();
        priorityLabel = new JLabel();

    }

    protected void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,2));

        JPanel typeButtons = new JPanel();
        typeButtons.setLayout(new GridLayout(0, 1));

        JPanel priorityButtons = new JPanel();
        priorityButtons.setLayout(new GridLayout(0, 1));

        JPanel priorityToolArea = new JPanel();
        priorityToolArea.setLayout(new GridLayout(0, 1));

        JPanel typeToolArea = new JPanel();
        typeToolArea.setLayout(new GridLayout(0, 1));

        priorityToolArea.add(priorityLabel, BorderLayout.NORTH);
        priorityToolArea.add(priorityButtons,BorderLayout.SOUTH);

        typeToolArea.add(typeLabel, BorderLayout.NORTH);
        typeToolArea.add(typeButtons,BorderLayout.SOUTH);

        toolArea.add(priorityToolArea,BorderLayout.WEST);
        toolArea.add(typeToolArea,BorderLayout.EAST);
        form.add(toolArea, BorderLayout.PAGE_END);

        Tool normal = new NormalType(this, typeToolArea);
        typeTools.add(normal);

        Tool secret = new SecretType(this, typeToolArea);
        typeTools.add(secret);

        Tool urgent = new UrgentType(this, typeToolArea);
        typeTools.add(urgent);

        PriorityTool high = new HighPriority(this, priorityToolArea);
        priorityTools.add(high);
        btnhigh = high;

        PriorityTool med = new MedPriority(this, priorityToolArea);
        priorityTools.add(med);
        btnmed = med;

        PriorityTool low = new LowPriority(this, priorityToolArea);
        priorityTools.add(low);
        btnlow = low;

        setActivePriorityTool(high);
        setActiveTypeTool(normal);
    }

    // MODIFIES: this
    // EFFECTS:  sets the given tool as the activeTypeTool
    public void setActiveTypeTool(Tool aTool) {
        if (activeTypeTool != null)
            activeTypeTool.deactivate();
        aTool.activate();
        typeLabel.setFont(font);
        typeLabel.setText("Your selected type is " + aTool.getLabel());
        activeTypeTool = aTool;
        checkUrgentType();
    }

    public void setActivePriorityTool(Tool aTool) {
        if (activePriorityTool != null)
            activePriorityTool.deactivate();
        aTool.activate();
        priorityLabel.setFont(font);
        priorityLabel.setText("Your selected priority is " + aTool.getLabel());
        activePriorityTool = aTool;
    }

    public Tool getActiveTypeTool(){
        return activeTypeTool;
    }

    public Tool getActivePriorityTool(){
        return activePriorityTool;
    }

    public void checkUrgentType(){
        if (activeTypeTool.getLabel().equals("Urgent")){
            btnlow.shutDown();
            btnmed.shutDown();
            btnhigh.shutDown();
            priorityLabel.setFont(font);
            priorityLabel.setText("Your selected priority is ASAP!!!");
        }
        else{
            btnlow.powerUp();
            btnmed.powerUp();
            btnhigh.powerUp();
            setActivePriorityTool(btnhigh);
        }
    }
}
