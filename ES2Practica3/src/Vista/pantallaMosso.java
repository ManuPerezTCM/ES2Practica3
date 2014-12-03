package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Aplicacio.ControladorEmpleat;

public class pantallaMosso extends JFrame {

	private JPanel contentPane;
	private ControladorEmpleat controladorEmpleat;

	/**
	 * Create the frame.
	 */
	public pantallaMosso(String idEmpleat) {
		
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
		setBounds(100, 100, 572, 480);
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
		btnTancarSessi.setBounds(401, 376, 119, 43);
		contentPane.add(btnTancarSessi);
		
		JList listPreparar = new JList();
		listPreparar.setBounds(40, 66, 218, 238);
		contentPane.add(listPreparar);
		
		JLabel lblComandesPreparar = new JLabel("Comandes per Preparar ");
		lblComandesPreparar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblComandesPreparar.setBounds(57, 29, 173, 29);
		contentPane.add(lblComandesPreparar);
		
		JButton btnMarcarComPreparada = new JButton("Marcar Com Preparada");
		btnMarcarComPreparada.setBounds(67, 316, 173, 43);
		contentPane.add(btnMarcarComPreparada);
		
		JButton btnMarcarComReposada = new JButton("Marcar Com Reposada");
		btnMarcarComReposada.setBounds(327, 316, 164, 43);
		contentPane.add(btnMarcarComReposada);
		
		JList list = new JList();
		list.setBounds(302, 66, 218, 238);
		contentPane.add(list);
		
		JLabel lblComandesReposar = new JLabel("Comandes per Reposar");
		lblComandesReposar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblComandesReposar.setBounds(327, 29, 173, 29);
		contentPane.add(lblComandesReposar);
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
