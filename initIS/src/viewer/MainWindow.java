package viewer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Biblioteca.View.MainViewBiblioteca;
import Biblioteca.Control.*;
import Formulario.View.ViewFormulario;
import IncidenciasMejoras.View.MainViewIncidenciasJugador;
import Juego.Control.JuegoDTO;
import Juego.View.MainViewJuego;
import Tienda.View.ComprarJuego;
import Tienda.View.MainViewTienda;
import Usuario.Control.UsuarioDTO;
import Usuario.Control.tipoCuenta;
import Usuario.View.MainWindowIniciarSesion;

//*
import Usuario.View.MainWindowAcuerdoSuscriptor;
import Usuario.View.MainWindowCrearCuenta;
import Usuario.View.MainWindowEliminarCuenta;
import Usuario.View.MainWindowModificarCuenta;
import Usuario.View.MainWindowPerfilUsuario; //*/

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel menuPanel;
	private JPanel headerPanel;
	private JPanel principalPanel;
	
	private Boolean state_unregistered = true;
	private Boolean state_user = false;
	private Boolean state_developer = false;
	private Boolean state_admin = false;
	
	public MainWindow() {
		super("Gecko");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Hace que el jframe aparezca en la mitad de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		
		initComponent();
		ponCosas();
	}
	
	private void ponCosas() {
		
		this.getContentPane().removeAll();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(headerPanel, BorderLayout.NORTH);
		this.getContentPane().add(menuPanel, BorderLayout.WEST);
		this.getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		principalPanel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
            	try {
            		if (e.getPropertyName().equals("JuegoTienda")) {
            			principalPanel = new MainViewJuego((JuegoDTO)e.getNewValue());
            			reinicia();
            		}
            		else if (e.getPropertyName().equals("Soporte")) {
            			principalPanel = new MainViewTienda("");
            			reinicia();
            		}
            		else if (e.getPropertyName().equals("ComprarJuego")){
            			if (state_unregistered) {
            				String tipoError = "Tienes que estar logeado para comprar juegos";
            				JOptionPane.showMessageDialog(MainWindow.this, tipoError, "Error", JOptionPane.ERROR_MESSAGE);
            			}
            			else {
            				principalPanel = new ComprarJuego((JuegoDTO)e.getNewValue());
            				reinicia();
            			}
            		}
            		else if (e.getPropertyName().equals("CrearCuenta")){
            			principalPanel = new MainWindowCrearCuenta();
            			reinicia();
            		}
            		else if (e.getPropertyName().equals("IniciarSesion")){
            			changeBoxes((UsuarioDTO) e.getNewValue());
            			principalPanel = new MainWindowPerfilUsuario((UsuarioDTO) e.getNewValue());
            			reinicia();
            		}
            		else if (e.getPropertyName().equals("Biblioteca")){
            			principalPanel = new MainViewBiblioteca((BibliotecaDTO)e.getNewValue());
            			reinicia();
            		}
            		
            	}
            	catch(Exception e1) {
            		//Nada
            	}
            }

			private void changeBoxes(UsuarioDTO user) {
				List<tipoCuenta> types = user.get_types();
				
				state_unregistered = types.contains(tipoCuenta.unregistered) ?  true : false;
				state_user = types.contains(tipoCuenta.user) ?  true : false;
				state_developer = types.contains(tipoCuenta.developer) ?  true : false;
				state_admin = types.contains(tipoCuenta.admin) ?  true : false;
				
			}
        });
		
	}
	
	public void reinicia() {
		menuPanel = initMenuPanel();
		headerPanel = initHeaderPanel();
		
		ponCosas();
		
		this.validate();
	}

	private void initComponent() {
		menuPanel = initMenuPanel();
		headerPanel = initHeaderPanel();
		principalPanel = new MainViewTienda("");
	}

	private JPanel initMenuPanel() {
		JPanel panel = new JPanel();

		GridLayout la = new GridLayout(18, 0);
		panel.setLayout(la);
		
		JButton buttonTienda = new JButton("Tienda");
		buttonTienda.addActionListener(new TiendaButton());
		panel.add(buttonTienda);
		buttonTienda.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton buttonBiblio = new JButton("Biblioteca");
		buttonBiblio.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonBiblio.addActionListener(new BibliotecaButton());
		panel.add(buttonBiblio);

		JButton buttonCom = new JButton("Comunidad");
		buttonCom.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(buttonCom);
		
		JButton buttonSoporte = new JButton("Soporte");
		buttonSoporte.addActionListener(new SoporteButton());
		buttonSoporte.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(buttonSoporte);
		
		
		JButton buttonFormulario = new JButton("Formulario");
		buttonFormulario.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonFormulario.addActionListener(new FormularioButton());
		buttonFormulario.setEnabled(state_developer);
		panel.add(buttonFormulario);
	
		return panel;
	}

	@SuppressWarnings("serial")
	private JPanel initHeaderPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel west = new JPanel();
		
		JButton buttonIcon = new JButton(new ImageIcon("./src/resources/usuario.png"));
		buttonIcon.addActionListener(new UserButton());
		west.add(buttonIcon);
		 
		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		JLabel tipo = new JLabel("  ");
		JCheckBox unregistered = new JCheckBox("Unregistered  ");
		unregistered.setModel(new DefaultButtonModel() { public boolean isSelected() { return state_unregistered; }});
		JCheckBox user = new JCheckBox("User");
		user.setModel(new DefaultButtonModel() { public boolean isSelected() { return state_user; }});
		JCheckBox developer = new JCheckBox("Developer");
		developer.setModel(new DefaultButtonModel() { public boolean isSelected() { return state_developer; }});
		JCheckBox admin = new JCheckBox("Admin");
		admin.setModel(new DefaultButtonModel() { public boolean isSelected() { return state_admin; }});
		
		east.add(tipo);
		east.add(unregistered);
		east.add(user);
		east.add(developer);
		east.add(admin);
		
		panel.add(west, BorderLayout.WEST);
		panel.add(east, BorderLayout.EAST);
		//panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		return panel;
	}
	
	class TiendaButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			principalPanel = new MainViewTienda("");
			reinicia();
		}
	}
	
	class FormularioButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			principalPanel = new ViewFormulario();
			reinicia();
		}
	}
	
	class SoporteButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			principalPanel = new MainViewIncidenciasJugador("");
			reinicia();
		}
	}
	
	class UserButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			principalPanel = new MainWindowIniciarSesion();
			//principalPanel = new MainWindowAcuerdoSuscriptor();
			//principalPanel = new MainWindowCrearCuenta();
			//principalPanel = new MainWindowEliminarCuenta();
			//principalPanel = new MainWindowModificarCuenta();
			//principalPanel = new MainWindowPerfilUsuario(null);
			reinicia();
		}
	}
	
	class BibliotecaButton implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			principalPanel = new MainViewBiblioteca("");
			reinicia();
		}
	}
	
}
