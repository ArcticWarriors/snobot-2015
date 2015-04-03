package com.snobot.simulator.joysticks;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SelectJoystickView extends JPanel
{
    // http://stackoverflow.com/questions/638807/how-do-i-drag-and-drop-a-row-in-a-jtable
    public interface Reorderable
    {
        public void reorder(int fromIndex, int toIndex);
    }

    /**
     * Handles drag & drop row reordering
     */
    public class TableRowTransferHandler extends TransferHandler
    {
        private final DataFlavor localObjectFlavor = new ActivationDataFlavor(Integer.class, DataFlavor.javaJVMLocalObjectMimeType,
                "Integer Row Index");
        private JTable table = null;

        public TableRowTransferHandler(JTable table)
        {
            this.table = table;
        }

        @Override
        protected Transferable createTransferable(JComponent c)
        {
            assert (c == table);
            return new DataHandler(new Integer(table.getSelectedRow()), localObjectFlavor.getMimeType());
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport info)
        {
            boolean b = info.getComponent() == table && info.isDrop() && info.isDataFlavorSupported(localObjectFlavor);
            table.setCursor(b ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
            return b;
        }

        @Override
        public int getSourceActions(JComponent c)
        {
            return TransferHandler.COPY_OR_MOVE;
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport info)
        {
            JTable target = (JTable) info.getComponent();
            JTable.DropLocation dl = (JTable.DropLocation) info.getDropLocation();
            int index = dl.getRow();
            int max = table.getModel().getRowCount();

            if (index < 0 || index > max)
            {
                index = max;
            }
            target.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            try
            {
                Integer rowFrom = (Integer) info.getTransferable().getTransferData(localObjectFlavor);
                if (rowFrom != -1 && rowFrom != index)
                {
                    TableModel model = table.getModel();
                    if (model instanceof Reorderable)
                    {
                        ((Reorderable) model).reorder(rowFrom, index);

                    }
                    if (index > rowFrom)
                    {
                        index--;
                    }
                    target.getSelectionModel().addSelectionInterval(index, index);
                    return true;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void exportDone(JComponent c, Transferable t, int act)
        {
            if (act == TransferHandler.MOVE)
            {
                table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }

    }

    private class ReorderableTableModel extends DefaultTableModel implements Reorderable
    {

        @SuppressWarnings("unchecked")
        @Override
        public void reorder(int fromIndex, int toIndex)
        {
            Object o = getDataVector().remove(fromIndex);
            if (toIndex < getRowCount())
            {
                getDataVector().add(toIndex, o);
            }
            else
            {
                getDataVector().add(o);
            }
            fireTableDataChanged();
        }

    }

    private JTable mJoystickTable;
    private ReorderableTableModel model;

    public SelectJoystickView()
    {
        model = new ReorderableTableModel();
        model.setColumnIdentifiers(new Object[]
        { "Joystick Name" });
        mJoystickTable = new JTable(model);
        mJoystickTable.setDragEnabled(true);
        mJoystickTable.setDropMode(DropMode.INSERT_ROWS);
        mJoystickTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(mJoystickTable);
        mJoystickTable.setTransferHandler(new TableRowTransferHandler(mJoystickTable));

        mJoystickTable.getModel().addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent e)
            {
                if (e.getType() == TableModelEvent.UPDATE)
                {
                    notifyJoysticksReordered();
                }
            }
        });

        add(pane);

        Map<Integer, IMockJoystick> availableJoysticks = JoystickFactory.get().getJoysticks();
        List<Integer> indexes = new ArrayList<Integer>(availableJoysticks.keySet());

        for (int i : indexes)
        {
            model.addRow(new Object[]
            { availableJoysticks.get(i) });
        }
    }

    private void notifyJoysticksReordered()
    {
        List<IMockJoystick> joysticks = new ArrayList<IMockJoystick>();

        for (int i = 0; i < model.getRowCount(); ++i)
        {
            joysticks.add((IMockJoystick) model.getValueAt(i, 0));
        }

        JoystickFactory.get().setJoysticks(joysticks);
    }

    public void clear()
    {
        while (model.getRowCount() > 0)
        {
            model.removeRow(0);
        }
    }

    public void addJoystick(BaseJoystick aJoystick)
    {
        model.addRow(new Object[]
        { aJoystick });
    }

    public static void main(String[] args)
    {

        JFrame frame = new JFrame();

        SelectJoystickView panel = new SelectJoystickView();

        // panel.addJoystick(new BaseJoystick("joystick1"));
        // panel.addJoystick(new BaseJoystick("joystick2"));
        // panel.addJoystick(new BaseJoystick("joystick3"));

        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);
        frame.setLocation(new Point(2000, 0));
    }
}
