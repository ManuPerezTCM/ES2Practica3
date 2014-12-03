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

public class pantallaVenedor extends JFrame {

	private JPanel contentPane;
	private ControladorEmpleat controladorEmpleat;
	private String idEmpleat;

	/**
	 * Create the frame.
	 */
	public pantallaVenedor(String idEmpleat) {
		this.idEmpleat = idEmpleat;
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
		setBounds(100, 100, 473, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn = new JButton("Tancar Sessió");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornarALogin();
			}
		});
		btn.setBounds(306, 224, 117, 29);
		contentPane.add(btn);
		
		JButton btnNovaComanda = new JButton("Nova Comanda");
		btnNovaComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anarAltaComanda();
			}
		});
		btnNovaComanda.setBounds(21, 32, 148, 47);
		contentPane.add(btnNovaComanda);
		
		JButton btnConsultarClients = new JButton("Consultar Clients");
		btnConsultarClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarClients();
			}
		});
		btnConsultarClients.setBounds(21, 195, 148, 47);
		contentPane.add(btnConsultarClients);
		
		JButton btnModificarComanda = new JButton("Modificar Comanda");
		btnModificarComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anarLlistaComandes();
			}
		});
		btnModificarComanda.setBounds(21, 115, 148, 47);
		contentPane.add(btnModificarComanda);
	}
	
	public void consultarClients(){
		consultaClient pantalla = new consultaClient(this.idEmpleat);
		pantalla.setVisible(true);
	}
	
	public void anarAltaComanda(){
		alta_comanda pantalla = new alta_comanda(this.idEmpleat);
		pantalla.setVisible(true);
		this.dispose();
	}
	
	public void anarLlistaComandes(){
		
		frmLlistaComandes pantalla = new frmLlistaComandes(this.idEmpleat);
		pantalla.setVisible(true);
		this.dispose();
	}
	
	
	public void tornarALogin(){
		Login login = new Login();
		login.setVisible(true);
		this.dispose();
	}
	
	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
