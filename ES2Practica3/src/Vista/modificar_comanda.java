package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import Aplicacio.ControladorComanda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class modificar_comanda extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtCodiArticle;
	private JTextField txtPreu;
	private JTextField txtQuantitat;
	private DefaultTableModel dft;
	private JTextField txtNomArticle;
	private JButton btnAfegirLinia;
	private JTextField txtTotal;
	private JTextField txtEstoc;
	private JLabel lblDataComanda;
	private JTextField txtDataComanda;
	private ControladorComanda controlador;
	private JLabel lblCodiClient;
	private JTextField txtCodiClient;
	private String idEmpleat;
	private int idComanda;

	public modificar_comanda(String idEmpleat, int codiComanda) {
		try {
			controlador = new ControladorComanda();
			this.idComanda=codiComanda;
		} catch (Exception e1) {
			tirarError(e1.getMessage());
		}
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("Modificar comanda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 647, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		table = new JTable(baseTaula());
		table.setBounds(0, 0, 1, 1);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(340, 82, 281, 169);
		contentPane.add(scrollPane);

		btnAfegirLinia = new JButton("Afegir");
		btnAfegirLinia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float preuTotal=0;
				try {
					String dadesLlista[] = new String[5];
					dadesLlista[0] = txtCodiArticle.getText();
					dadesLlista[1] = txtNomArticle.getText();
					dadesLlista[2] = txtQuantitat.getText();
					dadesLlista[3] = txtPreu.getText();
					afegir(dadesLlista);
					for(int x=0;x<dft.getRowCount();x++){
						preuTotal+=(Float.parseFloat((String) dft.getValueAt(x, 3))) * (Float.parseFloat((String) dft.getValueAt(x, 2)));	
					}
					txtTotal.setText(String.valueOf(preuTotal));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error al afegir a la llista", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		btnAfegirLinia.setBounds(119, 228, 89, 23);
		contentPane.add(btnAfegirLinia);

		txtCodiArticle = new JTextField();
		txtCodiArticle.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {


				try {
					
					String[] dades = controlador.dadesArticle(txtCodiArticle.getText());
					txtPreu.setText(dades[1]);
					txtNomArticle.setText(dades[0]);
					txtEstoc.setText(dades[4]);
					btnAfegirLinia.setEnabled(true);
					txtQuantitat.setText("1");
				} catch (Exception e) {
					txtNomArticle.setText("");
					txtPreu.setText("");
					txtQuantitat.setText("1");
					txtEstoc.setText("");
					JOptionPane.showMessageDialog(null, e.getMessage(), "Article no trobat", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		txtCodiArticle.setBounds(10, 116, 86, 20);
		contentPane.add(txtCodiArticle);
		txtCodiArticle.setColumns(10);

		JLabel lblCodiArticle = new JLabel("Codi Article");
		lblCodiArticle.setBounds(10, 91, 86, 14);
		contentPane.add(lblCodiArticle);

		JLabel lblPreu = new JLabel("Preu ");
		lblPreu.setBounds(10, 155, 46, 14);
		contentPane.add(lblPreu);

		JLabel lblQuantitat = new JLabel("Quantitat");
		lblQuantitat.setBounds(131, 155, 77, 14);
		contentPane.add(lblQuantitat);

		txtPreu = new JTextField();
		txtPreu.setBounds(10, 177, 86, 20);
		contentPane.add(txtPreu);
		txtPreu.setColumns(10);

		txtQuantitat = new JTextField();
		txtQuantitat.setBounds(119, 177, 86, 20);
		contentPane.add(txtQuantitat);
		txtQuantitat.setColumns(10);

		txtNomArticle = new JTextField();
		txtNomArticle.setBounds(119, 116, 86, 20);
		contentPane.add(txtNomArticle);
		txtNomArticle.setColumns(10);

		JButton btnTreureLinia = new JButton("Treure");
		btnTreureLinia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float preuTotal=0;		
				int fila = table.getSelectedRow();
				if (fila != -1) {
					dft.removeRow(fila);
				}
				for(int x=0;x<dft.getRowCount();x++){
					preuTotal+=(Float.parseFloat((String) dft.getValueAt(x, 3))) * (Float.parseFloat((String) dft.getValueAt(x, 2)));	
				}
				txtTotal.setText(String.valueOf(preuTotal));
			}
		});
		btnTreureLinia.setBounds(228, 228, 89, 23);
		contentPane.add(btnTreureLinia);

		JLabel lblNomArticle = new JLabel("Nom Article");
		lblNomArticle.setBounds(119, 91, 89, 14);
		contentPane.add(lblNomArticle);

		JLabel lblAltaDunaComanda = new JLabel("Alta d'una Comanda");
		lblAltaDunaComanda.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAltaDunaComanda.setBounds(200, 11, 268, 40);
		contentPane.add(lblAltaDunaComanda);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(535, 263, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JButton btnGuardarComanda = new JButton("Desar");
		btnGuardarComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Aqui programem el clic en el boto guardar comanda
				try {
					controlador.esborrarLiniesComanda(idComanda);
				} catch (Exception e1) {
					tirarError(e1.getMessage());
				}
				try {
					float preuTotal=(Float.parseFloat((String) txtTotal.getText()));
					controlador.modificarComanda(idComanda,txtCodiClient.getText(), txtDataComanda.getText(), "PENDENT", preuTotal );
				} catch (Exception e) {
					tirarError(e.getMessage());
				}
				for(int x=0;x<dft.getRowCount();x++){
					int codiArticle=Integer.parseInt((String) dft.getValueAt(x, 0));
					int quantitat=Integer.parseInt((String) dft.getValueAt(x,2));
					float preu=Float.parseFloat((String) dft.getValueAt(x, 3));
					try {
							controlador.afegirLiniaComanda(idComanda, codiArticle, quantitat, preu);
							
					} catch (Exception e) {
						tirarError("Error al crear linea comanda");
					}
					
				}
				JOptionPane.showMessageDialog(null,"Comanda "+idComanda+" guardada correctament" , "Comanda "+idComanda+" guardada correctament", JOptionPane.PLAIN_MESSAGE);
				Tancar();
			}
		});
		btnGuardarComanda.setBounds(200, 273, 94, 40);
		contentPane.add(btnGuardarComanda);
		
		JButton btnCancelarComanda = new JButton("Cancelar");
		btnCancelarComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornarEnrere();
			}
		});
		btnCancelarComanda.setBounds(326, 273, 94, 40);
		contentPane.add(btnCancelarComanda);
		
		JLabel lblEstocActual = new JLabel("Estoc Actual");
		lblEstocActual.setBounds(10, 209, 86, 14);
		contentPane.add(lblEstocActual);
		
		txtEstoc = new JTextField();
		txtEstoc.setEditable(false);
		txtEstoc.setColumns(10);
		txtEstoc.setBounds(10, 231, 86, 20);
		contentPane.add(txtEstoc);
		
		lblDataComanda = new JLabel("Data Comanda");
		lblDataComanda.setBounds(228, 91, 100, 14);
		contentPane.add(lblDataComanda);
		
		txtDataComanda = new JTextField();
		txtDataComanda.setEnabled(false);
		txtDataComanda.setColumns(10);
		txtDataComanda.setBounds(228, 116, 100, 20);
		contentPane.add(txtDataComanda);
		contentPane.setVisible(true);
		
		lblCodiClient = new JLabel("Codi Client");
		lblCodiClient.setBounds(10, 35, 86, 14);
		contentPane.add(lblCodiClient);
		
		txtCodiClient = new JTextField();
		txtCodiClient.setEnabled(false);
		txtCodiClient.setColumns(10);
		txtCodiClient.setBounds(10, 60, 86, 20);
		contentPane.add(txtCodiClient);
		this.idEmpleat = idEmpleat;
		OmplirDadesComanda(codiComanda);

	}
	
	private void OmplirDadesComanda(int codiComanda) {
		String[] dadesComanda;
		ArrayList<String[]> liniesComanda;
		try {
			dadesComanda=controlador.obtenirComanda(codiComanda);
			this.txtCodiClient.setText(dadesComanda[0].toString());
			this.txtDataComanda.setText(dadesComanda[1].toString());
			liniesComanda=controlador.obtenirLiniesComanda(codiComanda);
			for(int x=0;x<liniesComanda.size();x++){
				afegir(liniesComanda.get(x));
			}
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
		
	}
	
	private void Tancar(){
		this.dispose();
	}

	private DefaultTableModel baseTaula() {

		dft = new DefaultTableModel();
		dft.addColumn("codi");
		dft.addColumn("nom");
		dft.addColumn("quantitat");
		dft.addColumn("preu");
		return dft;

	}

	private void afegir(String dades[]) {

		int x = 0;
		if (dft.getRowCount() != 0) {
			for (x = 0; x < dft.getRowCount(); x++) {
				if (dft.getValueAt(x, 0).equals(dades[0])) {
					dft.removeRow(x);
				}
			}
		}
		dft.addRow(dades);
	}
	
	public void tornarEnrere(){
		pantallaVenedor pantalla = new pantallaVenedor(this.idEmpleat);
		pantalla.setVisible(true);
		this.dispose();
	}
	
	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
