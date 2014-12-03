package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import Aplicacio.ControladorComanda;

public class frmLlistaComandes extends JFrame {

 private JButton btnEliminarComanda ;
 private JButton btnModificarComanda ;
 private JPanel pnlMenuSuperior;
 private JButton btnSortir;
 private JPanel contentPane;
 private DefaultTableModel dft;
 private JTable tblComandes;
 private  JButton btnRefrescar ;
 private JScrollPane scrollPane;
 private String idEmpleat;


 /**
  * Create the frame.
  */
 public frmLlistaComandes(String idEmpleat) {
	 this.idEmpleat = idEmpleat;
  setTitle("Llista de Comandes");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 450, 300);
  contentPane = new JPanel();
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  contentPane.setLayout(new BorderLayout(0, 0));
  setContentPane(contentPane);
  
  JPanel pnlMenuInferior = new JPanel();
  contentPane.add(pnlMenuInferior, BorderLayout.SOUTH);
  pnlMenuInferior.setBorder(new EmptyBorder(5, 5, 5, 5));
  pnlMenuInferior.setLayout(new BorderLayout(0, 0));
   btnSortir = new JButton("Sortir");
   btnSortir.addActionListener(new ActionListener() {
   	public void actionPerformed(ActionEvent arg0) {
   		Tancar();
   	}

	
   });
  pnlMenuInferior.add(btnSortir, BorderLayout.NORTH);
  JButton btnRefrescar = new JButton("Refrescar");
  btnRefrescar.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    try {
     tblComandes.setModel(baseTaula());
     
    } catch (Exception e) {
     JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
   }
  });
  pnlMenuInferior.add(btnRefrescar, BorderLayout.EAST);
  
   pnlMenuSuperior = new JPanel();
  pnlMenuSuperior.setBorder(new EmptyBorder(5, 5, 5, 5));
  contentPane.add(pnlMenuSuperior, BorderLayout.NORTH);
  pnlMenuSuperior.setLayout(new BorderLayout(0, 0));
  
   btnModificarComanda = new JButton("Modificar Comanda");
   btnModificarComanda.addActionListener(new ActionListener() {
   	public void actionPerformed(ActionEvent arg0) {
   		int idComanda=0;
   		int fila = tblComandes.getSelectedRow();
		if (fila != -1) {
			idComanda=Integer.parseInt((String) dft.getValueAt(fila, 0));
		}
		modificarComanda(idComanda);
   	}

	
   });
  pnlMenuSuperior.add(btnModificarComanda, BorderLayout.CENTER);
   btnEliminarComanda = new JButton("Eliminar Comanda");
  pnlMenuSuperior.add(btnEliminarComanda, BorderLayout.EAST);
  JPanel pnlContingut = new JPanel();
  pnlContingut.setBorder(new EmptyBorder(5, 5, 5, 5));
  contentPane.add(pnlContingut, BorderLayout.CENTER);
  pnlContingut.setLayout(new BorderLayout(0, 0));
  
   scrollPane = new JScrollPane();
  pnlContingut.add(scrollPane, BorderLayout.CENTER);
  
  try {
   tblComandes = new JTable(baseTaula());
  
  } catch (Exception e1) {
   JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  }
  scrollPane.setViewportView(tblComandes);
 }
 
 public void tirarError(String missatge) {
  JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
    JOptionPane.ERROR_MESSAGE);
 }
 
 private DefaultTableModel baseTaula() throws Exception {

  dft = new DefaultTableModel();
  dft.addColumn("codi_comanda");
  dft.addColumn("data_comanda");
  dft.addColumn("Import_total");
  dft.addColumn("EStat");
  dft.addColumn("Client");

 ControladorComanda controlador = new ControladorComanda();

 ArrayList<String[]>  dades = controlador.obtenirComandes_Estat_Pendent();
 
 int x = 0;
 for (x = 0; x < dades.size(); x++){
  
 
  String valorsTaula[] = new String[5];
  
  valorsTaula[0] = String.valueOf( dades.get(x)[0]);
  valorsTaula[1] = String.valueOf( dades.get(x)[1]);
  valorsTaula[2] = String.valueOf( dades.get(x)[2]);
  valorsTaula[3] = String.valueOf( dades.get(x)[3]);
  valorsTaula[4] = String.valueOf( dades.get(x)[4]);
  dft.addRow(valorsTaula);
 }
  return dft;

 } 
 
 private void modificarComanda(int idComanda) {
		modificar_comanda modificar=new modificar_comanda(this.idEmpleat,idComanda);
		modificar.setVisible(true);
		
	}
 
 private void Tancar() {
		this.dispose();
		
	}

}