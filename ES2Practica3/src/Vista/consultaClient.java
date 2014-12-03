package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import Aplicacio.ControladorClient;
import Aplicacio.ControladorEmpleat;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class consultaClient extends JFrame {

	private JPanel contentPane;
	private ControladorClient controladorClient;
	private String idVenedor;
	private JList list;
	private DefaultListModel modelLlista;
	private ControladorEmpleat controladorEmpleat;
	
	/**
	 * Create the frame.
	 */
	public consultaClient(String idVenedor) {
		try {
			controladorClient = new ControladorClient();
			controladorEmpleat = new ControladorEmpleat();

			setTitle("Clients "+controladorEmpleat.aconseguirEmpleat(idVenedor).getNom()
					+" "+controladorEmpleat.aconseguirEmpleat(idVenedor).getCognom());
		} catch (Exception e) {
			tirarError(e.getMessage());
		}

		this.idVenedor = idVenedor;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 369, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		modelLlista = new DefaultListModel();
		omplirTaula(idVenedor);

		list = new JList(modelLlista);
		list.setBounds(22, 17, 323, 252);
		contentPane.add(list);
		
		JButton btnTancar = new JButton("Tancar");
		btnTancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tancar();
		}});
		btnTancar.setBounds(22, 281, 323, 29);
		contentPane.add(btnTancar);
	}
	
	public void omplirTaula(String idVenedor){
		try {
			
			LinkedList<String> clients = controladorClient.recuperarClientsPerVenedor(idVenedor);
			for(String client : clients){
				modelLlista.addElement(client);
			}
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
	}
	
	public void tancar(){
		this.dispose();
	}
	
	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
