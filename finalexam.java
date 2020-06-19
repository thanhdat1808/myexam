import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class finalexam extends JFrame implements ActionListener, MouseListener
{
	Connection conn;
	Statement stm;
	ResultSet rst;
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	JScrollPane tableResult;
	DefaultTableModel model;
	JTable tb = new JTable();
	int colum = 0;
	JButton edit, delete, insert, exit, search,reload,sort;
	JTextField tfsearch;
	JComboBox cb;
	JPopupMenu menu;
	JMenuItem itedit, itdelete, itinsert;
	int change=1;
public finalexam(String s) {
	super(s);
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalexam_java", "root", "");
		stm = conn.createStatement();
		
		edit = new JButton("Edit");
		edit.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		insert = new JButton("Insert");
		insert.addActionListener(this);
		exit = new JButton("Exit");
		exit.addActionListener(this);
		sort = new JButton("Sort");
		sort.addActionListener(this);
		
		tfsearch = new JTextField(20);
		search = new JButton("Search");
		search.addActionListener(this);
		reload = new JButton("Reload");
		reload.addActionListener(this);
		String t[] = {"ID","Name","Class"};
		cb = new JComboBox(t);
		JPanel p1 = new JPanel();
		
		menu = new JPopupMenu();
		itedit = new JMenuItem("Edit");
		itedit.addActionListener(this);
		itdelete = new JMenuItem("Delete");
		itdelete.addActionListener(this);
		itinsert = new JMenuItem("Insert");
		itinsert.addActionListener(this);
		menu.add(itinsert);
		menu.add(itedit);
		menu.add(itdelete);
		
		p1.add(cb);
		p1.add(tfsearch);
		p1.add(search);
		p1.add(reload);
		this.add(p1, "North");
		
		JPanel p2 = new JPanel();
		p2.add(edit);
		p2.add(delete);
		p2.add(insert);
		p2.add(sort);
		p2.add(exit);
		this.add(p2,"South");
		
		reload();
		model = new DefaultTableModel(vData, vTitle);
		tb = new JTable(model);
		tb.addMouseListener(this);
		tableResult = new JScrollPane(tb);
		this.getContentPane().add(tableResult,"Center");
		this.setSize(600, 400);
		this.setLocation(400, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e.getMessage());
	}
}
public void reload() {
	try {
		vTitle.clear();
		vData.clear();
		ResultSet rst = stm.executeQuery("Select * From info");
		ResultSetMetaData rstm = rst.getMetaData();
		int num = rstm.getColumnCount();
		for (int i = 1; i <= num; i++) {
			vTitle.add(rstm.getColumnLabel(i));
		}
		while(rst.next()) {
			Vector row = new Vector();
			for (int i = 1; i <= num; i++) {
				row.add(rst.getString(i));
			}
			vData.add(row);
		}
		rst.close();	
		
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e.getMessage());
	}
}
public void search() {
	try {
		vData.clear();
		vTitle.clear();
		ResultSet rst = stm.executeQuery("Select * from info where "+cb.getItemAt(cb.getSelectedIndex())+" like '%"+tfsearch.getText()+"%'");
//		stm.executeQuery("Select * from info where Name like '%\""+tfsearch.getText()+"\"'");
		ResultSetMetaData rstm = rst.getMetaData();
		int num = rstm.getColumnCount();
		for (int i = 1; i <= num; i++) {
			vTitle.add(rstm.getColumnLabel(i));
		}
		while(rst.next()) {
			Vector row = new Vector();
			for (int i = 1; i <= num; i++) {
				row.add(rst.getString(i));
			}
			vData.add(row);
		}
		rst.close();	
		model.fireTableDataChanged();
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e.getMessage());
	}
}
public void delete() {
	Vector st = (Vector)vData.elementAt(colum);
	int i = JOptionPane.showConfirmDialog(this, "Are you sure delete student "+st.elementAt(1)+"?");
	if(i==0) {
		try {
			
			String sql = "Delete from info where ID = \""+st.elementAt(0)+"\"";
			stm.executeUpdate(sql);
			vData.remove(colum);
			model.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
public void sort() {
	try {
		vTitle.clear();
		vData.clear();
		ResultSet rst = null;
		if(change == 0) {
			change=1;
			rst = stm.executeQuery("Select * From info order by aver");
		}
		else {
			change=0;
			rst = stm.executeQuery("Select * From info order by aver desc");
		}
		
		ResultSetMetaData rstm = rst.getMetaData();
		int num = rstm.getColumnCount();
		for (int i = 1; i <= num; i++) {
			vTitle.add(rstm.getColumnLabel(i));
		}
		while(rst.next()) {
			Vector row = new Vector();
			for (int i = 1; i <= num; i++) {
				row.add(rst.getString(i));
			}
			vData.add(row);
		}
		rst.close();	
		
	} catch (Exception e1) {
		// TODO: handle exception
		System.out.println(e1.getMessage());
	}
	model.fireTableDataChanged();
}


public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("Delete")) 
	{
		delete();
	}
	if(e.getActionCommand().equals("Insert"))
	{
		
		new Form("Insert form",this,"","","","","","");
	}
	if(e.getActionCommand().equals("Edit")) {
		Vector st = (Vector) vData.elementAt(colum);
		new Form("Edit form", this, (String)st.elementAt(0),(String)st.elementAt(1),
				(String)st.elementAt(2),(String)st.elementAt(3),(String)st.elementAt(4),(String)st.elementAt(5));
	}
	if(e.getActionCommand().equals("Exit")) {
		int i = JOptionPane.showConfirmDialog(this, "Are you sure exit?");
		if(i==0) this.dispose();
	}
	
	if(e.getActionCommand().equals("Search")) search();
	
	if(e.getActionCommand().equals("Reload")) {
		reload();
		model.fireTableDataChanged();
	}
	if(e.getActionCommand().equals("Sort")) sort();
}
@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	colum = tb.getSelectedRow();
}
@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	showPopup(e);
}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	showPopup(e);
}
public void showPopup(MouseEvent e) {
	if(e.isPopupTrigger()) {
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}
public static void main(String[]args) {
	new finalexam("Student Management");
}
}

