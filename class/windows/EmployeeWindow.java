package windows;
import Server.myDATA;

import Classes.Ticket;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//main window for employees to see ,create or update tickets
public class EmployeeWindow {
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;
    private Object[] columns = {"Description", "Status", "Ticket number", "Name"};
    private Object[] row = new Object[4];
    private JButton newBtn = new JButton(" New ");
    DefaultTableModel model = new DefaultTableModel();
    //load lists
    private myDATA data;
    private menuBar menuBar;

    public EmployeeWindow(myDATA data){
        this.data=data;
        frame = new JFrame();
        panel = new JPanel();
        setUP();
    }
    //main setup for frame
    public void setUP() {
        menuBar = new menuBar(1, data);
        frame.setBounds(400, 200, 700, 600);
        frame.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(700,300));
        panel.setLayout(new BorderLayout());
        setTable();
        frame.add(scrollPane,BorderLayout.CENTER);
        newBtn.setPreferredSize(new Dimension(50,20));
        newBtn.addActionListener(e -> { newTicket();
        });
        panel.add(newBtn,BorderLayout.SOUTH);
        frame.add(panel,BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(menuBar.getMenu());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    //creates table and makes it un-editable
    public void setTable() {
        table = new JTable() {
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // setting table
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setRowHeight(30);
        table.setPreferredScrollableViewportSize(new Dimension(650, 400));
        scrollPane = new JScrollPane(table);
        //load data to table line 72
        loadTable();
        //function to add event listener for double click line 83
        addActionToTable();
    }
    //load data to table
    public void loadTable() {
        //first active tickets
        for (int i = 0; i < data.ticketList.size; i++) {
            Ticket T = data.ticketList.getInstance(i);
            row[0] = T.getDescription();
            row[1] = T.getStatus();
            row[2] = T.getTicketNum();
            row[3] = T.getName();
            model.addRow(row);
        }
        //then completed ones
        for (int i = 0; i < data.completedtickets.size; i++) {
            Ticket T = data.completedtickets.getInstance(i);
            row[0] = T.getDescription();
            row[1] = T.getStatus();
            row[2] = T.getTicketNum();
            row[3] = T.getName();
            model.addRow(row);
        }
    }

    public void addActionToTable() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //check for double click
                if(e.getClickCount()==2){
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int ticketNum = (int) table.getValueAt(row,2);
                    String status = (String) table.getValueAt(row,1);
                    if (status.equals("Complete")){
                        JOptionPane.showMessageDialog(frame,
                                "This ticket has been complete,\nUnable to edit, please create a new ticket.",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);}
                    else {
                    frame.dispose();
                    //close frame and open ticket window with ticket
                            new TicketWindow(data.ticketList.findByID(ticketNum),data);
                    }
                }
            }
        });
    }
    //create new ticket
    public void newTicket(){
        frame.dispose();
        new TicketWindow(1,data);
    }

}