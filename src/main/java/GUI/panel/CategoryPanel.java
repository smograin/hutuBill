package GUI.panel;

import SQL.Impl.CategoryDAO;
import SQL.data.Category;
import util.GUIUtil;

import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class CategoryPanel extends WorkPanel {

    private final CategoryTableModel ctm = CategoryTableModel.instance;
    private final JTable t = new JTable(ctm);
    private final JScrollPane sp = new JScrollPane(t);
    private final JToolBar tb = new JToolBar();
    private final JButton bAdd = new JButton("添加");
    private final JButton bUpdate = new JButton("修改");
    private final JButton bDelete = new JButton("删除");
    private void init() {

        t.getSelectionModel().addListSelectionListener(e->{
            int row = t.getSelectedRow();
            Category c = ctm.categoryList.get(row);
        });
        bAdd.addActionListener(e->{
            String newCategoryName = JOptionPane.showInputDialog(this,"输入");
            if (newCategoryName != null) {
                ctm.categoryDAO.add(new Category(newCategoryName));
                refresh();
            }
        });
        GUIUtil.addWithSeparator(tb,10,bAdd,bUpdate,bDelete);
        tb.setFloatable(false);
        add(sp,BorderLayout.CENTER);
        add(tb,BorderLayout.SOUTH);
    }
    private CategoryPanel()
    {
        super();
        setLayout(new BorderLayout());
        init();
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
                return 0;
            default:
                return null;
        }
    }
}