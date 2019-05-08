package Usuario.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Usuario.Control.EventoUsuario;
import Usuario.Control.UsuarioDTO;
import Usuario.Control.ControllerUsuario;

public class MainWindowCrearCuenta extends JPanel {
	private static final long serialVersionUID = 1L;

	private ControllerUsuario contUs;
	private UsuarioDTO _user;
	
	private JLabel usernameLabel;
	private JTextArea username;
	private JLabel passwordLabel;
	private JTextArea password;
	private JLabel confirmPasswordLabel;
	private JTextArea confirmPassword;
	private JLabel emailLabel;
	private JTextArea email;
	private JLabel confirmEmailLabel;
	private JTextArea confirmEmail;
	//private JLabel empresa;
	private JCheckBox empresaDesarrolladora;
	private JButton ok;
	
	public MainWindowCrearCuenta () {
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		//configPanel();
		createCrearCuenta();
	}
	
	private void createCrearCuenta() {
		
		JPanel generalPanel = new JPanel();
        BoxLayout generalLayout = new BoxLayout(generalPanel, BoxLayout.Y_AXIS);
        generalPanel.setLayout(generalLayout);
        int sizex = 200;
        
        //USERNAME
        JPanel usernamePanel = new JPanel();
        BoxLayout usernameLayout = new BoxLayout(usernamePanel, BoxLayout.X_AXIS);
        usernamePanel.setLayout(usernameLayout);
        
        usernameLabel = new JLabel();
        usernameLabel.setPreferredSize(new Dimension(200,20));
        usernameLabel.setText("Username: ");
        
        username = new JTextArea();
        username.setPreferredSize(new Dimension(sizex,25));
        username.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        usernamePanel.add(usernameLabel);
        usernamePanel.add(username);
        
        //PASSWORD
        JPanel passwordPanel = new JPanel();
        BoxLayout passwordLayout = new BoxLayout(passwordPanel, BoxLayout.X_AXIS);
        passwordPanel.setLayout(passwordLayout);
        
        passwordLabel = new JLabel();
        passwordLabel.setPreferredSize(new Dimension(200,20));
        passwordLabel.setText("Password:  ");
        
        password = new JTextArea();
        password.setPreferredSize(new Dimension(sizex,25));
        password.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);
        
        //CONFIRMPASSWORD
        JPanel confirmPasswordPanel = new JPanel();
        BoxLayout confirmPasswordLayout = new BoxLayout(confirmPasswordPanel, BoxLayout.X_AXIS);
        confirmPasswordPanel.setLayout(confirmPasswordLayout);
        
        confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setPreferredSize(new Dimension(200,20));
        confirmPasswordLabel.setText("Confirm password:  ");
        
        confirmPassword = new JTextArea();
        confirmPassword.setPreferredSize(new Dimension(sizex,25));
        confirmPassword.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPassword);
        
        //EMAIL
        JPanel emailPanel = new JPanel();
        BoxLayout emailLayout = new BoxLayout(emailPanel, BoxLayout.X_AXIS);
        emailPanel.setLayout(emailLayout);
        
        emailLabel = new JLabel();
        emailLabel.setPreferredSize(new Dimension(200,20));
        emailLabel.setText("Email:  ");
        
        email = new JTextArea();
        email.setPreferredSize(new Dimension(sizex,25));
        email.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        emailPanel.add(emailLabel);
        emailPanel.add(email);
        
        //CONFIRMEMAIL
        JPanel confirmEmailPanel = new JPanel();
        BoxLayout confirmEmailLayout = new BoxLayout(confirmEmailPanel, BoxLayout.X_AXIS);
        confirmEmailPanel.setLayout(confirmEmailLayout);
        
        confirmEmailLabel = new JLabel();
        confirmEmailLabel.setPreferredSize(new Dimension(200,20));
        confirmEmailLabel.setText("Confirm email:  ");
        
        confirmEmail = new JTextArea();
        confirmEmail.setPreferredSize(new Dimension(sizex,25));
        confirmEmail.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        confirmEmailPanel.add(confirmEmailLabel);
        confirmEmailPanel.add(confirmEmail);
        
        empresaDesarrolladora = new JCheckBox("Empresa desarrolladora");
        ok = new JButton ("CONTINUAR");
       
        ok.addActionListener(new continuarButton());
        
        generalPanel.add(usernamePanel);
        generalPanel.add(passwordPanel);
        generalPanel.add(confirmPasswordPanel);
        generalPanel.add(emailPanel);
        generalPanel.add(confirmEmailPanel);
        generalPanel.add(empresaDesarrolladora);
        generalPanel.add(ok);
       
        this.add(generalPanel);
	}
	
	class continuarButton implements ActionListener {
		public void actionPerformed(ActionEvent e){  
			 if (!username.getText().isEmpty() && !password.getText().isEmpty() && !email.getText().isEmpty()) {
				 
				 //COMPROBAR QUE EL NOMBRE DE USUARIO NO EXISTE ACTUALMENTE EN LA LISTA DE USUARIOS
				 
				if (password.getText().equals(confirmPassword.getText()) && email.getText().equals(confirmEmail.getText())) {
				 
					if(empresaDesarrolladora.isSelected()) {
						//Crear usuario tipo empresa desarrolladora
						//Usando como username:username
						//Como password: password
						//Como email: email
					}
					else {
						//Crear usuario
						//Usando como username:username
						//Como password: password
						//Como email: email
					}
					contUs.evento(EventoUsuario.CrearCuenta, null);
					//firePropertyChange("CrearCuenta", null, null);	 
				}
			 }
		 }
	}
}