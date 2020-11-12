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
import javax.swing.DefaultComboBoxModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
	private JComboBox comboBox = new JComboBox<String>();
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
		//Preparacion de la GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 266, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_superior = new JPanel();
		contentPane.add(panel_superior, BorderLayout.NORTH);
		
		JLabel lbl_calc = new JLabel("Calculadora");
		panel_superior.add(lbl_calc);
		
		panel_superior.add(comboBox);
		
		JPanel panel_central = new JPanel();
		contentPane.add(panel_central, BorderLayout.CENTER);
		
		textField_num1 = new JTextField();
		textField_num1.setColumns(10);
		
		JLabel lbl_num2 = new JLabel("Numero 2:");
		
		textField_num2 = new JTextField();
		textField_num2.setColumns(10);
		
		JLabel lbl_res = new JLabel("Resultado:");
		
		JLabel lbl_res_num = new JLabel("0");
		panel_central.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbl_num1 = new JLabel("Numero 1:");
		panel_central.add(lbl_num1);
		panel_central.add(textField_num1);
		panel_central.add(lbl_num2);
		panel_central.add(textField_num2);
		
		JButton btn_calcular = new JButton("Calcular");
		
		panel_central.add(btn_calcular);
		panel_central.add(lbl_res);
		panel_central.add(lbl_res_num);
		
		JPanel panel_inferior = new JPanel();
		contentPane.add(panel_inferior, BorderLayout.SOUTH);
		
		JButton btn_actualizar = new JButton("Actualizar plugins");
		panel_inferior.add(btn_actualizar);
		
		Calculadora c=new Calculadora();
		
		//Busco los plugins disponibles
		c.getPlugins();
		
		//Cargo las opciones en el combobox
		String[] nombres_plugs = c.getPluginsNames();
		for(int i=0; i<nombres_plugs.length; i++) {
			comboBox.addItem(nombres_plugs[i]);
		}
		
		//accion del boton de calculo
		btn_calcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1,num2;
				float resultado;
				String operacion;
				
				try {
					//obtengo los numeros y el tipo de operacion
					num1= Integer.parseInt(textField_num1.getText());
					num2= Integer.parseInt(textField_num2.getText());
					operacion = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
					
					//realizo el calculo y actualizo el label
					resultado=c.runPlugin(num1,num2,operacion);
					lbl_res_num.setText(String.format("%.2f", resultado));
					
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ingrese datos correctos.");
				}catch(ArithmeticException ex2) {
					JOptionPane.showMessageDialog(null, "Error aritmetico.");
				}
				
			}
		});
		
		//accion del boton de actualizacion
		btn_actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//actualizo los plugins disponibles
				c.getPlugins();
				
				//actualizo las opciones del combobox
				comboBox.removeAllItems();
				String[] nombres_plugs = c.getPluginsNames();
				for(int i=0; i<nombres_plugs.length; i++) {
					comboBox.addItem(nombres_plugs[i]);
				}
				
			}
		});
		
	}

}



