package GUI.panel;

import GUI.MainFrame;
import SQL.Impl.CategoryDAO;
import SQL.data.Category;
import util.GUIUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class CategoryPanel extends WorkPanel {
    protected final CategoryDAO dao = new CategoryDAO();
    private final CategoryTableModel ctm = CategoryTableModel.instance;
    private final JTable t = new JTable(ctm);
    private final JScrollPane sp = new JScrollPane(t);
    private final JToolBar tb = new JToolBar();
    private final JButton bAdd = new JButton("添加");
    private final JButton bUpdate = new JButton("修改");
    private final JButton bDelete = new JButton("删除");
    private void init() {
        GUIUtil.addWithSeparator(tb,10,bAdd,bUpdate,bDelete);
        tb.setFloatable(false);
        add(sp,BorderLayout.CENTER);
        add(tb,BorderLayout.SOUTH);
    }
    private void listenerInit()
    {
        bAdd.addActionListener(e->{
            String newCategoryName = JOptionPane.showInputDialog(this,"输入");
            if (newCategoryName != null) {
                ctm.categoryDAO.add(new Category(newCategoryName));
                refresh();
            }
        });
        bUpdate.addActionListener(e->{
            try
            {
                Category c = ctm.categoryList.get(t.getSelectedRow());
                RenameCounterUI.instance.refresh(c);
            }catch (Exception q)
            {
                JOptionPane.showMessageDialog(this,"当前未选中");
            }
        });

        bDelete.addActionListener(e->
        {
            try
            {
                Category c = ctm.categoryList.get(t.getSelectedRow());
                dao.delete(c);
                refresh();
            }catch (Exception q)
            {
                JOptionPane.showMessageDialog(this,"当前未选中");
            }
        });


    }
    private CategoryPanel()
    {
        super();
        setLayout(new BorderLayout());
        init();
        listenerInit();
    }
    public static CategoryPanel instance = new CategoryPanel();
    public void refresh()
    {
        ctm.update();
        t.updateUI();
    }
}

class CategoryTableModel extends AbstractTableModel
{


    public static CategoryTableModel instance = new CategoryTableModel();
    String[] columnNames = new String[] {"消费名称","消费次数"};
    CategoryDAO categoryDAO = new CategoryDAO();
    List<Category> categoryList = categoryDAO.list();
    void update ()
    {
        categoryList = categoryDAO.list();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return categoryList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category c = categoryList.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return c.getName();
            case 1:
                return c.getTimes();
            default:
                return null;
        }
    }
}

class RenameCounterUI extends JDialog implements ActionListener {
    // GUI Components
    private JLabel nameLabel, timesLabel;
    private JTextField nameTextField, timesTextField;
    private JButton confirmButton;
    private int cId;

    public static RenameCounterUI instance  = new RenameCounterUI();


    private RenameCounterUI() {
        super(MainFrame.instance);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));

        // Initialize the components
        nameLabel = new JLabel("name:");
        nameTextField = new JTextField();
        timesLabel = new JLabel("次数：");
        timesTextField = new JTextField();
        confirmButton = new JButton("确认修改");
        confirmButton.addActionListener(this);

        // Add the components to the frame
        this.add(nameLabel);
        this.add(nameTextField);
        this.add(timesLabel);
        this.add(timesTextField);
        this.add(confirmButton);

        // Set the size of the frame and make it visible
        this.setModal(true);
        this.setSize(300, 150);
    }
    public void refresh(Category c)
    {
        cId = c.getId();
        nameTextField.setText(c.getName());
        timesTextField.setText(String.valueOf(c.getTimes()));
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        // Handle the button click event
        if (e.getSource() == confirmButton) {
            String name = nameTextField.getText();
            int times = Integer.parseInt(timesTextField.getText());
            Category c = new Category(cId, name, times);
            CategoryPanel.instance.dao.update(c);
            CategoryPanel.instance.refresh();
//            TODO return the Category and refresh the database
            JOptionPane.showMessageDialog(this, "操作成功！");
            this.setVisible(false);
        }
    }


}