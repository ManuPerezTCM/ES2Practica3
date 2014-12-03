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
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;

public class pantallaRepartidor extends JFrame {

	private JPanel contentPane;
	private ControladorEmpleat controladorEmpleat;


	/**
	 * Create the frame.
	 */
	public pantallaRepartidor(String idEmpleat) {
		
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
		setBounds(100, 100, 564, 421);
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
		btnTancarSessi.setBounds(401, 316, 119, 43);
		contentPane.add(btnTancarSessi);
		
		JList list = new JList();
		list.setBounds(40, 66, 480, 238);
		contentPane.add(list);
		
		JLabel lblComandesPendentsDe = new JLabel("Comandes Pendents de Repartir");
		lblComandesPendentsDe.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblComandesPendentsDe.setBounds(142, 25, 345, 29);
		contentPane.add(lblComandesPendentsDe);
		
		JButton btnMarcarComEntregada = new JButton("Marcar Com Entregada");
		btnMarcarComEntregada.setBounds(40, 316, 173, 43);
		contentPane.add(btnMarcarComEntregada);
		
		JButton btnMarcarComRetornada = new JButton("Marcar Com Retornada");
		btnMarcarComRetornada.setBounds(225, 316, 164, 43);
		contentPane.add(btnMarcarComRetornada);
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
