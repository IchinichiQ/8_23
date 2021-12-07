package ru.vsu.cs.p_p_v.GUI;

import ru.vsu.cs.p_p_v.*;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class FormMain extends JFrame {
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonLoad;
    private JButton buttonSave;
    private JButton buttonProcess;
    private JPanel panelMain;
    private JButton buttonIncreaseSize;
    private JButton buttonDecreaseSize;

    public FormMain() {
        this.setTitle("Friendly array creator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        this.setSize(430, 300);

        buttonIncreaseSize.setMargin(new Insets(0, 0, 0, 0));
        buttonDecreaseSize.setMargin(new Insets(0, 0, 0, 0));

        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(2);
        model.setColumnCount(2);
        tableInput.setModel(model);

        buttonLoad.addActionListener(e -> buttonLoadPressed());
        buttonProcess.addActionListener(e -> buttonProcessPressed());
        buttonSave.addActionListener(e -> buttonSavePressed());
        buttonIncreaseSize.addActionListener(e -> buttonIncreaseSizePressed());
        buttonDecreaseSize.addActionListener(e -> buttonDecreaseSizePressed());
    }

    private void buttonLoadPressed() {
        try {
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                Integer[][] data = ArrayFile.twoDimensionalArrayRead(file.getPath());
                tableInput.setModel(new DefaultTableModel(data, data[0]));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void buttonProcessPressed() {
        try {
            Integer[][] frArray = FriendlyArray.getFriendlyArray(getTableData(tableInput));
            tableOutput.setModel(new DefaultTableModel(frArray, frArray[0]));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void buttonSavePressed() {
        try {
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            int returnVal = fc.showSaveDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                Integer[][] data = getTableData(tableOutput);
                ArrayFile.twoDimensionalArrayWrite(file.getPath(), data);
                JOptionPane.showMessageDialog(null, "Saved!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void buttonIncreaseSizePressed() {
        try {
            DefaultTableModel model = (DefaultTableModel) tableInput.getModel();
            model.addRow((Object[]) null);
            model.addColumn((Object[]) null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void buttonDecreaseSizePressed() {
        try {
            DefaultTableModel model = (DefaultTableModel) tableInput.getModel();

            if (model.getRowCount() > 0) {
                model.removeRow(model.getRowCount() - 1);
                model.setColumnCount(model.getColumnCount() - 1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public Integer[][] getTableData(JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int rowNum = dtm.getRowCount();
        int colNum = dtm.getColumnCount();
        Integer[][] tableData = new Integer[rowNum][colNum];
        for (int i = 0; i < rowNum; i++)
            for (int j = 0; j < colNum; j++)
                tableData[i][j] = Integer.parseInt(dtm.getValueAt(i, j).toString());
        return tableData;
    }
}
