package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.*;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
	JComboBox comboBox = new JComboBox<String>();
	private JPanel contentPane;
	private JTextField textField_num1;
	private JTextField textField_num2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.setName("Calculadora Simple");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 199, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_superior = new JPanel();
		contentPane.add(panel_superior, BorderLayout.NORTH);
		
		JLabel lbl_calc = new JLabel("Calculadora");
		panel_superior.add(lbl_calc);
		
		
		panel_superior.add(comboBox);
		
		JPanel panel_inferior = new JPanel();
		contentPane.add(panel_inferior, BorderLayout.CENTER);
		
		textField_num1 = new JTextField();
		textField_num1.setColumns(10);
		
		JLabel lbl_num2 = new JLabel("Numero 2:");
		
		textField_num2 = new JTextField();
		textField_num2.setColumns(10);
		
		JLabel lbl_res = new JLabel("Resultado:");
		
		JLabel lbl_res_num = new JLabel("0");
		panel_inferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbl_num1 = new JLabel("Numero 1:");
		panel_inferior.add(lbl_num1);
		panel_inferior.add(textField_num1);
		panel_inferior.add(lbl_num2);
		panel_inferior.add(textField_num2);
		
		JButton btn_calcular = new JButton("Calcular");
		
		panel_inferior.add(btn_calcular);
		panel_inferior.add(lbl_res);
		panel_inferior.add(lbl_res_num);
		
		JButton btn_actualizar = new JButton("Actualizar operaciones");
		panel_inferior.add(btn_actualizar);
		
		Calculadora c=new Calculadora();
		
		c.getPlugins();
		
		String[] nombres_plugs = c.getNombresPlugins();
		
		for(int i=0; i<nombres_plugs.length; i++) {
			if(nombres_plugs[i].endsWith(".class")) {
				comboBox.addItem(nombres_plugs[i].substring(0, nombres_plugs[i].indexOf(".")));
			}
		}
		
		btn_calcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1,num2,resultado;
				String operacion;
				boolean hubo_error;
				String descrip_error;
				
				try {
					num1= Integer.parseInt(textField_num1.getText());
					num2= Integer.parseInt(textField_num2.getText());
					
					operacion = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
					
					c.runPlugin(num1,num2,operacion);
					
					hubo_error=c.getUltimaOperacionError();
					
					if(hubo_error) {
						descrip_error=c.getDescripcionError();
						JOptionPane.showMessageDialog(null, "ERROR: "+descrip_error);
					}else {
						resultado=c.getUltimoResultado();
						lbl_res_num.setText(""+resultado);
					}
					
					
					
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ingrese datos correctos");
				}
				
			}
		});
		
		//ESTO NO ANDA
		btn_actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox=new JComboBox<String>();
				panel_superior.add(comboBox);
				c.getPlugins();
				String[] nombres_plugs = c.getNombresPlugins();
				
				for(int i=0; i<nombres_plugs.length; i++) {
					comboBox.addItem(nombres_plugs[i].substring(0, nombres_plugs[i].indexOf(".")));
				}
			}
		});
		
	}

}



