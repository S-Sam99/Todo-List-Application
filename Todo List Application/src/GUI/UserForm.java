package GUI;

import exceptions.NoFilesFound;
import exceptions.TooManyTasks;
import model.ReadWebPage;
import model.TodoList;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserForm extends JFrame implements ActionListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private JTextField field1;
    private JTextField field2;
    private JTextPane panel;
    private JTextPane response;
    private JPanel jpanel1;
    private JPanel jpanel2;
    private JPanel jpanel3;
    private JLabel label;
    private TodoList todo;
    private ToolPanel toolPanel;
    private Font font = new Font("Ariel", Font.CENTER_BASELINE, 20);

    public UserForm() {
        super("My TodoList");
        initializeFields();
        setLayout(new GridLayout(5,1));
        try {
            todo.load("savedTasks");
        } catch (IOException e){
            System.out.println("We could not find any previously saved tasks");
        }
        try {
            initializeStartingScreen();
        } catch (IOException e) {
            label.setText("There was no starting comment");
        }
        initializeGraphics();
    }

    private void initializeGraphics() {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jpanel1.add(label);
        jpanel2.add(response, BorderLayout.NORTH);
        initializeButtons();
        add(jpanel1, BorderLayout.NORTH);
        initializeJPanel1();
        add(jpanel2, BorderLayout.NORTH);
        add(jpanel3, BorderLayout.NORTH);
        toolPanel.createTools();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initializeFields() {
        toolPanel = new ToolPanel(this);
        todo = new TodoList();
        panel = new JTextPane();
        response = new JTextPane();
        response.setEditable(false);
        label = new JLabel();
        field1 = new JTextField();
        field2 = new JTextField();
        jpanel1 = new JPanel();
        jpanel1.setLayout(new GridLayout(3,0));
        jpanel2 = new JPanel();
        jpanel2.setLayout(new GridLayout(2,0));
        jpanel3 = new JPanel();
        jpanel3.setLayout(new FlowLayout());
    }

    private void initializeButtons() {
        JPanel buttonArea = new JPanel();
        JPanel addArea = new JPanel();
        addArea.setLayout(new GridLayout(2,0));
        JPanel removeArea = new JPanel();
        removeArea.setLayout(new GridLayout(2,0));

        JLabel add = new JLabel();
        JLabel remove = new JLabel();
        add.setFont(new Font("Ariel", Font.CENTER_BASELINE, 13));
        remove.setFont(new Font("Ariel", Font.CENTER_BASELINE, 13));
        add.setText("Enter a task you would like to add");
        remove.setText("Enter the array number or task name to remove");

        JButton btn1 = new JButton("Add");
        JButton btn2 = new JButton("Remove");
        JButton btn3 = new JButton("Save");
        btn1.addActionListener(this);
        btn1.setActionCommand("addButton");
        btn2.addActionListener(this);
        btn2.setActionCommand("removeButton");
        btn3.setActionCommand("saveButton");
        btn3.addActionListener(this);
        btn3.setPreferredSize(new Dimension(100,25));
        field1.setPreferredSize(new Dimension(100,25));
        field2.setPreferredSize(new Dimension(100,25));
        addArea.add(add, BorderLayout.NORTH);
        addArea.add(field1, BorderLayout.SOUTH);
        addArea.add(btn1, BorderLayout.SOUTH);
        addArea.add(remove, BorderLayout.NORTH);
        addArea.add(field2, BorderLayout.SOUTH);
        addArea.add(btn2, BorderLayout.SOUTH);
        buttonArea.add(addArea, BorderLayout.WEST);
        buttonArea.add(removeArea, BorderLayout.EAST);
        jpanel3.add(btn3, BorderLayout.PAGE_END);
        jpanel1.add(buttonArea, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addButton")) {
            try {
                todo.addTask(toolPanel.getActiveTypeTool().getLabel(), field1.getText(), toolPanel.getActivePriorityTool().getLabel());
                updateResponse();
                update();
            } catch (TooManyTasks exception) {
                todo.setResponse("You have too many uncompleted tasks in one category!!!" + "\n" + "Remove some tasks in that category before adding more!!!");
                updateResponse();
            }
        } else if (e.getActionCommand().equals("removeButton")) {
            todo.removeTaskinList(field2.getText(), toolPanel.getActiveTypeTool().getLabel());
            updateResponse();
            update();
        } else if (e.getActionCommand().equals("saveButton")){
            try {
                todo.saveTasks("savedTasks", todo);
                todo.setResponse("You have successfully saved your tasks!");
                updateResponse();
            } catch (IOException exception) {
                System.out.println("There was not a file to save to");
            }
        }
    }

    public void initializeJPanel1() {
        StyledDocument doc = panel.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panel.setEditable(false);
        panel.setFont(font);
        update();
        JScrollPane scrollPanel = new JScrollPane(panel);
        add(scrollPanel, BorderLayout.CENTER);
    }

    public void initializeStartingScreen() throws IOException {
        ReadWebPage read = new ReadWebPage();
        label.setFont(font);
        label.setText(read.ReadURL());
    }

    public void update() {
        panel.setText(todo.lookatTasks());
        todo.clearList();
    }

    public void updateResponse(){
        response.setFont(font);
        response.setText(todo.updateResponse());
    }
}
