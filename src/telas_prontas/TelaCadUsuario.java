package telas_prontas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BancoDeDados.Delete;
import BancoDeDados.Insert;
import BancoDeDados.Select;
import BancoDeDados.Update;
import BancoDeDados.conexao;

public class TelaCadUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_nome;
	private JTextField text_login;
	private JTextField text_id;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadUsuario frame = new TelaCadUsuario();
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
	public TelaCadUsuario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

				ShowData();
			}
		});
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 820, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cadastro de Usu\u00E1rios ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblNewLabel.setBounds(224, 0, 377, 59);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(172, 90, 82, 44);
		contentPane.add(lblNewLabel_1);

		text_nome = new JTextField();
		text_nome.setBounds(256, 101, 326, 26);
		contentPane.add(text_nome);
		text_nome.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Login:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(172, 132, 82, 44);
		contentPane.add(lblNewLabel_1_1);

		text_login = new JTextField();
		text_login.setColumns(10);
		text_login.setBounds(256, 143, 326, 26);
		contentPane.add(text_login);

		JLabel lblNewLabel_1_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_1_1.setBounds(172, 172, 82, 44);
		contentPane.add(lblNewLabel_1_1_1);

		JFormattedTextField text_senha = new JFormattedTextField();
		text_senha.setBounds(256, 184, 326, 26);
		contentPane.add(text_senha);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((text_nome.getText().length() > 0) && (text_login.getText().length() > 0)
						&& (text_senha.getText().length() > 0)) {

					String nome = text_nome.getText().trim();
					String login = text_login.getText().trim();
					String senha = text_senha.getText().trim();

					Insert insert = new Insert();

					insert.insertUsuario(nome, login, senha);

					text_nome.setText("");
					text_login.setText("");
					text_senha.setText("");

					conexao con = new conexao();
					con.conectar();

					DefaultTableModel model = new DefaultTableModel();

					model.addColumn("Código");
					model.addColumn("Nome");
					model.addColumn("Login");
					model.addColumn("Senha");

					try {
						String query = "SELECT id_usu,nome_usu,login_usu,senha_usu FROM usuarios order by id_usu desc";

						Statement st = con.criarStatement();
						ResultSet rs = st.executeQuery(query);

						while (rs.next()) {

							model.addRow(new Object[] { rs.getInt("id_usu"), rs.getString("nome_usu"),
									rs.getString("login_usu"), rs.getString("senha_usu")

							});

						}
						rs.close();
						st.close();
						con.desconectar();

						table.setModel(model);
						table.setAutoResizeMode(0);
						table.getColumnModel().getColumn(0).setPreferredWidth(65);
						table.getColumnModel().getColumn(1).setPreferredWidth(255);
						table.getColumnModel().getColumn(2).setPreferredWidth(110);
						table.getColumnModel().getColumn(3).setPreferredWidth(110);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
					}

				} else {

					JOptionPane.showMessageDialog(null, "Campos Obrigaorios ( * )");
				}

			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(208, 232, 119, 29);
		contentPane.add(btnNewButton);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((text_nome.getText().length() > 0 && text_login.getText().length() > 0)
						&& (text_senha.getText().length() > 0)) {

					Delete del = new Delete();

					int id = Integer.parseInt(text_id.getText());
					try {
						del.deleteUsuario(id);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					text_id.setText("");
					text_nome.setText("");
					text_login.setText("");
					text_senha.setText("");

					conexao con = new conexao();
					con.conectar();

					DefaultTableModel model = new DefaultTableModel();

					model.addColumn("Código");
					model.addColumn("Nome");
					model.addColumn("Login");
					model.addColumn("Senha");

					try {
						String query = "SELECT id_usu,nome_usu,login_usu,senha_usu FROM usuarios order by id_usu desc";

						Statement st = con.criarStatement();
						ResultSet rs = st.executeQuery(query);

						while (rs.next()) {

							model.addRow(new Object[] { rs.getInt("id_usu"), rs.getString("nome_usu"),
									rs.getString("login_usu"), rs.getString("senha_usu")

							});

						}
						rs.close();
						st.close();
						con.desconectar();

						table.setModel(model);
						table.setAutoResizeMode(0);
						table.getColumnModel().getColumn(0).setPreferredWidth(65);
						table.getColumnModel().getColumn(1).setPreferredWidth(255);
						table.getColumnModel().getColumn(2).setPreferredWidth(110);
						table.getColumnModel().getColumn(3).setPreferredWidth(110);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
					}

				} else {
					JOptionPane.showMessageDialog(null, "Campos Obrigatórios ( * )");
				}

			}
		});
		btnDeletar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDeletar.setBounds(366, 233, 96, 29);
		contentPane.add(btnDeletar);

		JButton btnAtualizar = new JButton("Modificar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((text_nome.getText().length() > 0 && text_login.getText().length() > 0)
						&& (text_senha.getText().length() > 0)) {

					Update up = new Update();

					String nome = text_nome.getText();
					String login = text_login.getText();
					String senha = text_senha.getText();

					int codUp = Integer.parseInt(text_id.getText());

					up.updateUsuario(nome, login, senha, codUp);

					conexao con = new conexao();
					con.conectar();

					DefaultTableModel model = new DefaultTableModel();

					model.addColumn("Código");
					model.addColumn("Nome");
					model.addColumn("Login");
					model.addColumn("Senha");

					try {
						String query = "SELECT id_usu,nome_usu,login_usu,senha_usu FROM usuarios order by id_usu desc";

						Statement st = con.criarStatement();
						ResultSet rs = st.executeQuery(query);

						while (rs.next()) {

							model.addRow(new Object[] { rs.getInt("id_usu"), rs.getString("nome_usu"),
									rs.getString("login_usu"), rs.getString("senha_usu")

							});

						}
						rs.close();
						st.close();
						con.desconectar();

						table.setModel(model);
						table.setAutoResizeMode(0);
						table.getColumnModel().getColumn(0).setPreferredWidth(65);
						table.getColumnModel().getColumn(1).setPreferredWidth(255);
						table.getColumnModel().getColumn(2).setPreferredWidth(110);
						table.getColumnModel().getColumn(3).setPreferredWidth(110);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
					}

				} else {
					JOptionPane.showMessageDialog(null, "Campos Obrigatórios ( * )");
				}

			}
		});
		btnAtualizar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAtualizar.setBounds(499, 233, 119, 29);
		contentPane.add(btnAtualizar);

		JLabel lblNewLabel_2 = new JLabel("*");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(158, 106, 17, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("*");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(158, 147, 17, 16);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("*");
		lblNewLabel_2_2.setForeground(Color.RED);
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_2.setBounds(158, 187, 17, 16);
		contentPane.add(lblNewLabel_2_2);

		JLabel lblNewLabel_1_2 = new JLabel("Cod:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(29, 27, 57, 44);
		contentPane.add(lblNewLabel_1_2);

		text_id = new JTextField();
		text_id.setBounds(87, 32, 44, 41);
		contentPane.add(text_id);
		text_id.setColumns(10);

		JButton btnNewButton_1 = new JButton("Enter");
		getRootPane().setDefaultButton(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (text_id.getText().length() > 0) {

					Select select = new Select();

					try {
						int cod = Integer.parseInt(text_id.getText());
						select.selectUsuario(cod);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// text_id2.setText(Integer.toString(select.getText_id2()));
					text_nome.setText(select.getNome());
					text_login.setText(select.getLogin());
					text_senha.setText(select.getSenha());

				} else {
					JOptionPane.showMessageDialog(null, "O campo 'COD' é obrigatório!");

				}

			}
		});
		btnNewButton_1.setBounds(139, 38, 70, 29);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Menu Principal");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					TelaCadUsuario frame = new TelaCadUsuario();
					frame.setLocationRelativeTo(null);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					TelaMenuPrincipal frame = new TelaMenuPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					TelaMenuPrincipal frame = new TelaMenuPrincipal();
					frame.setLocationRelativeTo(null);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnNewButton_2.setBounds(638, 23, 135, 36);
		contentPane.add(btnNewButton_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(137, 277, 542, 263);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

	}

	public void ShowData() {

		conexao con = new conexao();
		con.conectar();

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Código");
		model.addColumn("Nome");
		model.addColumn("Login");
		model.addColumn("Senha");

		try {
			String query = "SELECT id_usu,nome_usu,login_usu,senha_usu FROM usuarios order by id_usu desc";

			Statement st = con.criarStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				model.addRow(new Object[] { rs.getInt("id_usu"), rs.getString("nome_usu"), rs.getString("login_usu"),
						rs.getString("senha_usu")

				});

			}
			rs.close();
			st.close();
			con.desconectar();

			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(65);
			table.getColumnModel().getColumn(1).setPreferredWidth(255);
			table.getColumnModel().getColumn(2).setPreferredWidth(110);
			table.getColumnModel().getColumn(3).setPreferredWidth(110);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
		}

	}
}
