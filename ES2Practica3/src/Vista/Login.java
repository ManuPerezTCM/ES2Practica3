package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import Aplicacio.ControladorEmpleat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuari;
	private ControladorEmpleat controladorEmpleat;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Login() {
		
		try {
			controladorEmpleat = new ControladorEmpleat();
		} catch (Exception e1) {
			tirarError(e1.getMessage());
		}
		
		setTitle("Iniciar Sessió");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUsuari = new JTextField();
		textFieldUsuari.setText("00000000t");
		textFieldUsuari.setBounds(104, 21, 163, 28);
		contentPane.add(textFieldUsuari);
		textFieldUsuari.setColumns(10);
		
		JLabel lblUsuari = new JLabel("Usuari:");
		lblUsuari.setBounds(18, 27, 61, 16);
		contentPane.add(lblUsuari);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(18, 78, 74, 16);
		contentPane.add(lblPassword);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldUsuari.getText().isEmpty()){
					tirarError("Introdueix un Usuari");
				}
				try {
					String password = controladorEmpleat.aconseguirPassword(textFieldUsuari.getText());
					if(password.equals(passwordField.getText())){
						canviarPantalla(textFieldUsuari.getText());
					}else{
					throw new Exception("Contrasenya incorrecte.");
					}
				} catch (Exception e2) {
					tirarError(e2.getMessage());
				}
			}
		});
		btnEntrar.setBounds(201, 143, 117, 29);
		contentPane.add(btnEntrar);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(104, 72, 163, 28);
		contentPane.add(passwordField);
	}
	
	public void canviarPantalla(String idEmpleat){
		try{ 
			switch(controladorEmpleat.aconseguirEmpleat(textFieldUsuari.getText()).getTipus().toUpperCase()){
				case "ADMINISTRATIU": crearPantallaAdministratiu(idEmpleat);
				case "MOSSO": crearPantallaMosso(idEmpleat);
				case "REPARTIDOR": crearPantallaRepartidor(idEmpleat);
				case "VENEDOR": crearPantallaVenedor(idEmpleat);
			}
			this.dispose();
		}catch(Exception e){
			tirarError(e.getMessage());
		}
	}

	//TOTS AQUESTS MÈTODES ELS PODEM EVITAR FENT UNA FACTORIA, PRIMER FEM QUE FUNCIONI BE I DESPRES JA LA FAREM SI VOLEM
	
	public void crearPantallaAdministratiu(String idEmpleat){
		try{
			pantallaAdministratiu pantalla = new pantallaAdministratiu(idEmpleat);
			pantalla.setVisible(true);
		}catch(Exception e){
			tirarError(e.getMessage());
		}
	}
	
	public void crearPantallaMosso(String idEmpleat){
		try{
			pantallaMosso pantalla = new pantallaMosso(idEmpleat);
			pantalla.setVisible(true);
		}catch(Exception e){
			tirarError(e.getMessage());
		}
	}
	
	public void crearPantallaRepartidor(String idEmpleat){
		try{
			pantallaRepartidor pantalla = new pantallaRepartidor(idEmpleat);
			pantalla.setVisible(true);
		}catch(Exception e){
			tirarError(e.getMessage());
		}
	}
	
	public void crearPantallaVenedor(String idEmpleat){
		try{
			pantallaVenedor pantalla = new pantallaVenedor(idEmpleat);
			pantalla.setVisible(true);
		}catch(Exception e){
			tirarError(e.getMessage());
		}
	}
	
	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
