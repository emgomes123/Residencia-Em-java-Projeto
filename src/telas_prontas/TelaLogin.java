package telas_prontas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import BancoDeDados.CriarBancoSQLite;
import BancoDeDados.Insert;
import BancoDeDados.Select;
import BancoDeDados.conexao;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField text_senha;
	@SuppressWarnings("unused")
	private JFormattedTextField text_login;

	/**
	 * Launch the application.
	 * 
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {

		String nome1 = "Usuario Padrão";
		String login1 = "Admin";
		String senha1 = "Admin";

		conexao con = new conexao();

		CriarBancoSQLite criardb = new CriarBancoSQLite(con);

		criardb.criarTabelas();

		Select select = new Select();

		Insert insert = new Insert();

		select.selectLogin(login1);

		String recebeNome = select.getNome();
		String recebeLogin = select.getLogin2();
		String recebeSenha = select.getSenha();

		if ((recebeNome == null) && (recebeLogin == null) && (recebeSenha == null)) {

			insert.insertUsuario(nome1, login1, senha1);

		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {

		setTitle("Eidos Systech");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\edsom\\eclipse-workspace\\ProjetoFinal_v1.,0\\img\\box_full_products_14639.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(31, 83, 61, 25);
		contentPane.add(lblNewLabel);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSenha.setBounds(31, 131, 71, 25);
		contentPane.add(lblSenha);

		JLabel lblNewLabel_1 = new JLabel("Acesso do Usu\u00E1rio");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(149, 16, 156, 52);
		contentPane.add(lblNewLabel_1);

		JFormattedTextField text_login = new JFormattedTextField();
		text_login.setBounds(101, 84, 303, 25);
		contentPane.add(text_login);

		JButton btnNewButton = new JButton("Confirmar");
		getRootPane().setDefaultButton(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			private String loginCadastrado;
			private String loginDigitado;
			private String senhaDigitada;
			private String senhaCadastrada;
			private String log;
			private TelaLogin frame;

			@SuppressWarnings("unused")
			public String getLog() {
				return log;
			}

			@SuppressWarnings("unused")
			public void setLog(String log) {
				this.log = log;
			}

			@SuppressWarnings("unused")
			public TelaLogin getFrame() {
				return frame;
			}

			@SuppressWarnings("unused")
			public void setFrame(TelaLogin frame) {
				this.frame = frame;
			}

			@SuppressWarnings("unused")
			public String getLoginCadastrado() {
				return loginCadastrado;
			}

			@SuppressWarnings("unused")
			public void setLoginCadastrado(String loginCadastrado) {
				this.loginCadastrado = loginCadastrado;
			}

			@SuppressWarnings("unused")
			public String getLoginDigitado() {
				return loginDigitado;
			}

			@SuppressWarnings("unused")
			public void setLoginDigitado(String loginDigitado) {
				this.loginDigitado = loginDigitado;
			}

			@SuppressWarnings("unused")
			public String getSenhaDigitada() {
				return senhaDigitada;
			}

			@SuppressWarnings("unused")
			public void setSenhaDigitada(String senhaDigitada) {
				this.senhaDigitada = senhaDigitada;
			}

			@SuppressWarnings("unused")
			public String getSenhaCadastrada() {
				return senhaCadastrada;
			}

			@SuppressWarnings("unused")
			public void setSenhaCadastrada(String senhaCadastrada) {
				this.senhaCadastrada = senhaCadastrada;
			}

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				if ((text_login.getText().length() > 0) && (text_senha.getText().length() > 0)) {

					Select select = new Select();

					try {
						String login = text_login.getText();
						select.selectLogin(login);
					} catch (ParseException e) {

						e.printStackTrace();
					}

					log = select.getLogin2();

					loginCadastrado = select.getLogin2();
					loginDigitado = text_login.getText();

					senhaCadastrada = select.getSenha();

					senhaDigitada = text_senha.getText();

					if (text_login.getText().equalsIgnoreCase(select.getLogin2())) {

						if (text_senha.getText().equalsIgnoreCase(select.getSenha())) {

							try {
								TelaMenuPrincipal frame = new TelaMenuPrincipal();
								frame.setLocationRelativeTo(null);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								frame = new TelaLogin();

								dispose();

							} catch (Exception e) {
								e.printStackTrace();
							}

						} else {
							JOptionPane.showMessageDialog(null, "Login ou Senha Incorreto!!!");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Login ou Senha Incorreto!!!");
					}

				} else {

					JOptionPane.showMessageDialog(null, "Campos ' * ' Obrigatórios!!!");
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(101, 185, 115, 29);
		contentPane.add(btnNewButton);

		JButton btnCancelar = new JButton("Cancelar");

		btnCancelar.addActionListener(new ActionListener() {
			private TelaLogin frame;

			@SuppressWarnings("unused")
			public TelaLogin getFrame() {
				return frame;
			}

			@SuppressWarnings("unused")
			public void setFrame(TelaLogin frame) {
				this.frame = frame;
			}

			public void actionPerformed(ActionEvent arg0) {

				try {
					frame = new TelaLogin();
					dispose();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnCancelar.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnCancelar.setBounds(258, 185, 115, 29);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel_2 = new JLabel("*");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(31, 74, 16, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("*");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(31, 124, 16, 14);
		contentPane.add(lblNewLabel_2_1);

		text_senha = new JPasswordField();
		text_senha.setBounds(101, 132, 303, 25);
		contentPane.add(text_senha);

	}
}
