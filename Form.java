import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Form extends JFrame implements ActionListener{
JLabel lbid,lbname,lbclass,lbmath,lbphys,lbchem,lberror,errordital;
JTextField tfid, tfname,tfclass,tfmath,tfphy,tfchem;
JButton ok, cancel;
finalexam fn;
String id;
public Form(String s,finalexam st, String i, String name,String cl, String m, String ph, String ch) {
	// TODO Auto-generated constructor stub
	super(s);
	fn = st;
	JPanel f = new JPanel();

	f.setLayout(new GridLayout(7, 2));
//	lbid = new JLabel("Id");
//	tfid = new JTextField(i);
	lbname = new JLabel("Name");
	tfname = new JTextField(name);
	lbclass = new JLabel("Class");
	tfclass = new JTextField(cl);
	lbmath = new JLabel("Math");
	tfmath = new JTextField(m);
	lbphys = new JLabel("Physics");
	tfphy = new JTextField(ph);
	lbchem = new JLabel("Chemistry");
	tfchem = new JTextField(ch);
	ok = new JButton("Ok");
	ok.addActionListener(this);
	cancel = new JButton("Cancel");
	cancel.addActionListener(this);
	lberror = new JLabel("");
	errordital = new JLabel("");
	lberror.setVisible(false);
	errordital.setVisible(false);
//	f.add(lbid);
//	f.add(tfid);
	f.add(lbname);
	f.add(tfname);
	f.add(lbclass);
	f.add(tfclass);
	f.add(lbmath);
	f.add(tfmath);
	f.add(lbphys);
	f.add(tfphy);
	f.add(lbchem);
	f.add(tfchem);
	f.add(lberror);
	f.add(errordital);
	f.add(cancel);
	f.add(ok);
	this.setSize(350, 250);
	this.setLocation(500,150);
	this.add(f);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setVisible(true);
	id = i;
}
	@Override
public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Ok")) {
			insertDB();
		}
		else {
			this.dispose();
		}
	}
public void insertDB() {
	
	if(tfname.getText().equals("")||tfclass.getText().equals("")||tfmath.getText().equals("")||tfchem.getText().equals("")||tfphy.getText().equals("")) {
		lberror.setText("Error");
		errordital.setText("Empty value");
		lberror.setForeground(Color.red);
		errordital.setForeground(Color.RED);
		lberror.setVisible(true);
		errordital.setVisible(true);
	}
	else {
		try {
//			String id = tfid.getText();
			String na = tfname.getText();
			String cl = tfclass.getText();
			float m = Float.parseFloat(tfmath.getText());
			float ph = Float.parseFloat(tfphy.getText());
			float ch = Float.parseFloat(tfchem.getText());
			String sql;
			
			if(this.getTitle().equals("Insert form")) {
				sql = "insert into info(Name,Class,Math,Phys,Chem,Aver)values(\""+na+"\",\""+cl+"\","+m+","+ph+","+ch+","+(m+ph+ch)/3+")";
			}
			else {
				sql="update info set Name = \""+na+"\",Class = \""+cl+"\",Math="+m+","
						+ "Phys = "+ph+",Chem="+ch+",Aver = "+(m+ph+ch)/3+" where ID=\""+id+"\"";
			}
			fn.stm.executeUpdate(sql);
			fn.reload();
			fn.model.fireTableDataChanged();
			this.dispose();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}
}

}
