package Vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Aplicacio.ControladorEmpleat;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pantallaAdministratiu extends JFrame {

	private JPanel contentPane;
	private ControladorEmpleat controladorEmpleat;

	/**
	 * Create the frame.
	 */
	public pantallaAdministratiu(String idEmpleat) {
		
		try {
			controladorEmpleat = new ControladorEmpleat();
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
		try {
			setTitle(controladorEmpleat.aconseguirEmpleat(idEmpleat).getNom()
					+" "+controladorEmpleat.aconseguirEmpleat(idEmpleat).getCognom());
		} catch (Exception e) {
			tirarError(e.getMessage());		
		}
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTancarSessi = new JButton("Tancar Sessió");
		btnTancarSessi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornarALogin();
			}
		});
		btnTancarSessi.setBounds(293, 226, 117, 29);
		contentPane.add(btnTancarSessi);
	}

	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public void tornarALogin(){
		Login login = new Login();
		login.setVisible(true);
		this.dispose();
	}
}
