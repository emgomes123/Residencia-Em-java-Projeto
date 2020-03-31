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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BancoDeDados.Delete;
import BancoDeDados.Insert;
import BancoDeDados.Select;
import BancoDeDados.Update;
import BancoDeDados.conexao;
import Classes.JDoubleField;
import Classes.JNumberField;

public class TelaCadProd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_desc;
	private JTextField text_desc2;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadProd frame = new TelaCadProd();
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
	 * 
	 * @throws ParseException
	 */
	public TelaCadProd() throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ShowData();
				ShowData2();

			}
		});

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 820, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(15, 16, 768, 532);
		contentPane.add(tabbedPane);

		JPanel panel_cadastro = new JPanel();
		tabbedPane.addTab("Cadastro de Produtos", null, panel_cadastro, null);
		panel_cadastro.setLayout(null);

		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(28, 57, 98, 29);
		panel_cadastro.add(lblNewLabel);

		JLabel lblQunatidade = new JLabel("Qunatidade:");
		lblQunatidade.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblQunatidade.setBounds(28, 115, 112, 29);
		panel_cadastro.add(lblQunatidade);

		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPreo.setBounds(309, 115, 65, 29);
		panel_cadastro.add(lblPreo);

		JLabel lblValidade = new JLabel("Data:");
		lblValidade.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblValidade.setBounds(535, 115, 48, 29);
		panel_cadastro.add(lblValidade);

		text_desc = new JTextField();
		text_desc.setFont(new Font("Times New Roman", Font.BOLD, 20));
		text_desc.setBounds(126, 53, 622, 36);
		panel_cadastro.add(text_desc);
		text_desc.setColumns(10);

		JNumberField text_quant = new JNumberField(5);
		text_quant.setBounds(146, 112, 84, 30);
		panel_cadastro.add(text_quant);

		JDoubleField text_preco = new JDoubleField(5);
		text_preco.setBounds(378, 115, 84, 30);
		panel_cadastro.add(text_preco);

		JFormattedTextField text_data = new JFormattedTextField();
		text_data.setEditable(false);
		text_data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		text_data.setBounds(588, 112, 160, 30);
		panel_cadastro.add(text_data);

		JButton btnNewButton = new JButton("Cadastrar");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				conexao con = new conexao();
				con.conectar();

				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("Código");
				model.addColumn("Descrição");
				model.addColumn("Quantidade");
				model.addColumn("Preço");
				model.addColumn("Data");

				try {
					String query = "SELECT * FROM produtos order by id desc";

					Statement st = con.criarStatement();
					ResultSet rs = st.executeQuery(query);

					while (rs.next()) {

						model.addRow(new Object[] {

								rs.getInt("id"), rs.getString("desc"), rs.getInt("quant"), rs.getDouble("preco"),
								rs.getString("data")

						});

					}
					rs.close();
					st.close();
					con.desconectar();

					table_1.setModel(model);
					table_1.setAutoResizeMode(0);
					table_1.getColumnModel().getColumn(0).setPreferredWidth(65);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(298);
					table_1.getColumnModel().getColumn(2).setPreferredWidth(110);
					table_1.getColumnModel().getColumn(3).setPreferredWidth(110);
					table_1.getColumnModel().getColumn(3).setPreferredWidth(110);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro " + e1.getMessage());
				}

			}
		});

		btnNewButton.addActionListener(new ActionListener() {

			private String desc;
			private int quant;
			private double preco;
			private String data;
			
			

			@SuppressWarnings("unused")
			public String getDesc() {
				return desc;
			}



			@SuppressWarnings("unused")
			public void setDesc(String desc) {
				this.desc = desc;
			}



			@SuppressWarnings("unused")
			public int getQuant() {
				return quant;
			}



			@SuppressWarnings("unused")
			public void setQuant(int quant) {
				this.quant = quant;
			}



			@SuppressWarnings("unused")
			public double getPreco() {
				return preco;
			}



			@SuppressWarnings("unused")
			public void setPreco(double preco) {
				this.preco = preco;
			}



			@SuppressWarnings("unused")
			public String getData() {
				return data;
			}



			@SuppressWarnings("unused")
			public void setData(String data) {
				this.data = data;
			}



			public void actionPerformed(ActionEvent arg0) {

				if ((text_desc.getText().length() > 0) && (text_quant.getText().length() > 0)
						&& (text_preco.getText().length() > 0) && (text_data.getText().length() > 0)) {
					
					@SuppressWarnings("unused")
					Insert insertProd = new Insert();
					
				desc = text_desc.getText();
				quant = Integer.parseInt(text_quant.getText());
				preco = Double.parseDouble(text_preco.getText());
				data = text_data.getText();
					
				insertProd.insert(desc, quant, preco, data);
					
					
					
					
					
					
					
					
					
					
					
					

					conexao con = new conexao();
					con.conectar();

					DefaultTableModel model = new DefaultTableModel();

					model.addColumn("Código");
					model.addColumn("Descrição");
					model.addColumn("Quantidade");
					model.addColumn("Preço");
					model.addColumn("Data");

					try {
						String query = "SELECT * FROM produtos order by id desc";

						Statement st = con.criarStatement();
						ResultSet rs = st.executeQuery(query);

						while (rs.next()) {

							model.addRow(new Object[] {

									rs.getInt("id"), rs.getString("desc"), rs.getInt("quant"), rs.getDouble("preco"),
									rs.getString("data")

							});

						}
						rs.close();
						st.close();
						con.desconectar();

						table.setModel(model);
						table.setAutoResizeMode(0);
						table.getColumnModel().getColumn(0).setPreferredWidth(65);
						table.getColumnModel().getColumn(1).setPreferredWidth(298);
						table.getColumnModel().getColumn(2).setPreferredWidth(110);
						table.getColumnModel().getColumn(3).setPreferredWidth(110);
						table.getColumnModel().getColumn(3).setPreferredWidth(110);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Erro " + e1.getMessage());
					}

					text_desc.setText("");
					text_quant.setText("");
					text_preco.setText("");

					text_desc.setEnabled(false);
					text_quant.setEnabled(false);
					text_preco.setEnabled(false);
					text_data.setEnabled(false);

				} else {

					JOptionPane.showMessageDialog(null, "Campos Obrigaorios ( * )");

				}

			}

		});

		btnNewButton.setBounds(179, 176, 115, 29);
		panel_cadastro.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Novo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				text_desc.setText("");
				text_quant.setText("");
				text_preco.setText("");

				text_desc.setEnabled(true);
				text_quant.setEnabled(true);
				text_preco.setEnabled(true);

			}
		});
		btnNewButton_1.setBounds(433, 176, 115, 29);
		panel_cadastro.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(15, 102, 20, 16);
		panel_cadastro.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("*");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(15, 44, 20, 16);
		panel_cadastro.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("*");
		lblNewLabel_1_2.setForeground(Color.RED);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(299, 113, 16, 16);
		panel_cadastro.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("*");
		lblNewLabel_1_3.setForeground(Color.RED);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(520, 112, 20, 16);
		panel_cadastro.add(lblNewLabel_1_3);

		JButton btnNewButton_3 = new JButton("Menu");
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					TelaMenuPrincipal frame = new TelaMenuPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					TelaCadProd frame = new TelaCadProd();
					frame.setLocationRelativeTo(null);
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		btnNewButton_3.setBounds(670, 8, 78, 29);
		panel_cadastro.add(btnNewButton_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 237, 661, 261);
		panel_cadastro.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_consulta = new JPanel();
		tabbedPane.addTab("Consulta", null, panel_consulta, null);
		panel_consulta.setLayout(null);

		text_desc2 = new JTextField();
		text_desc2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		text_desc2.setColumns(10);
		text_desc2.setBounds(113, 45, 622, 36);
		panel_consulta.add(text_desc2);

		JLabel lblQunatidade_1 = new JLabel("Qunatidade:");
		lblQunatidade_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblQunatidade_1.setBounds(15, 104, 112, 29);
		panel_consulta.add(lblQunatidade_1);

		JLabel lblNewLabel_2 = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(15, 49, 98, 29);
		panel_consulta.add(lblNewLabel_2);

		JFormattedTextField text_quant2 = new JFormattedTextField();
		text_quant2.setBounds(133, 104, 84, 30);
		panel_consulta.add(text_quant2);

		JFormattedTextField text_preco2 = new JFormattedTextField();
		text_preco2.setBounds(342, 104, 84, 30);
		panel_consulta.add(text_preco2);

		JFormattedTextField text_data2 = new JFormattedTextField();
		text_data2.setBounds(575, 104, 160, 30);
		panel_consulta.add(text_data2);

		JLabel lblNewLabel_3 = new JLabel("Cod:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(64, 12, 49, 20);
		panel_consulta.add(lblNewLabel_3);

		JFormattedTextField text_id2 = new JFormattedTextField();
		text_id2.setBounds(113, 9, 43, 29);
		panel_consulta.add(text_id2);

		JLabel lblPreo_1 = new JLabel("Pre\u00E7o:");
		lblPreo_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPreo_1.setBounds(274, 104, 65, 29);
		panel_consulta.add(lblPreo_1);

		JLabel lblNewLabel_1_2_1 = new JLabel("*");
		lblNewLabel_1_2_1.setForeground(Color.RED);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1.setBounds(254, 101, 20, 16);
		panel_consulta.add(lblNewLabel_1_2_1);

		JLabel lblNewLabel_1_3_1 = new JLabel("*");
		lblNewLabel_1_3_1.setForeground(Color.RED);
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_3_1.setBounds(464, 104, 20, 16);
		panel_consulta.add(lblNewLabel_1_3_1);

		JLabel lblValidade_1 = new JLabel("Validade:");
		lblValidade_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblValidade_1.setBounds(476, 104, 84, 29);
		panel_consulta.add(lblValidade_1);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("*");
		lblNewLabel_1_2_1_1.setForeground(Color.RED);
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1_1.setBounds(15, 94, 20, 16);
		panel_consulta.add(lblNewLabel_1_2_1_1);

		JLabel lblNewLabel_1_2_1_2 = new JLabel("*");
		lblNewLabel_1_2_1_2.setForeground(Color.RED);
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1_2.setBounds(15, 37, 20, 16);
		panel_consulta.add(lblNewLabel_1_2_1_2);

		JLabel lblNewLabel_1_2_1_3 = new JLabel("*");
		lblNewLabel_1_2_1_3.setForeground(Color.RED);
		lblNewLabel_1_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1_3.setBounds(58, 0, 13, 16);
		panel_consulta.add(lblNewLabel_1_2_1_3);

		JButton btnNewButton_2 = new JButton("Atualizar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((text_id2.getText().length() > 0 && text_desc2.getText().length() > 0)
						&& (text_quant2.getText().length() > 0) && (text_preco2.getText().length() > 0)
						&& (text_data2.getText().length() > 0)) {

					Update up = new Update();

					String descUp = text_desc2.getText();
					int quantUp = Integer.parseInt(text_quant2.getText());
					double precoUp = Double.parseDouble(text_preco2.getText());
					String dataUp = text_data2.getText();

					int codUp = Integer.parseInt(text_id2.getText());

					up.update(descUp, quantUp, precoUp, dataUp, codUp);

					conexao con = new conexao();
					con.conectar();

					DefaultTableModel model = new DefaultTableModel();

					model.addColumn("Código");
					model.addColumn("Descrição");
					model.addColumn("Quantidade");
					model.addColumn("Preço");
					model.addColumn("Data");

					try {
						String query = "SELECT * FROM produtos order by id desc";

						Statement st = con.criarStatement();
						ResultSet rs = st.executeQuery(query);

						while (rs.next()) {

							model.addRow(new Object[] {

									rs.getInt("id"), rs.getString("desc"), rs.getInt("quant"), rs.getDouble("preco"),
									rs.getString("data")

							});

						}
						rs.close();
						st.close();
						con.desconectar();

						table_1.setModel(model);
						table_1.setAutoResizeMode(0);
						table_1.getColumnModel().getColumn(0).setPreferredWidth(65);
						table_1.getColumnModel().getColumn(1).setPreferredWidth(283);
						table_1.getColumnModel().getColumn(2).setPreferredWidth(110);
						table_1.getColumnModel().getColumn(3).setPreferredWidth(110);
						table_1.getColumnModel().getColumn(3).setPreferredWidth(110);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
					}

				} else {
					JOptionPane.showMessageDialog(null, "Campos Obrigatórios ( * )");
				}

			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2.setBounds(181, 150, 115, 29);
		panel_consulta.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("Deletar");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((text_id2.getText().length() > 0 && text_desc2.getText().length() > 0)
						&& (text_quant2.getText().length() > 0) && (text_preco2.getText().length() > 0)
						&& (text_data2.getText().length() > 0)) {

					Delete del = new Delete();

					int id = Integer.parseInt(text_id2.getText());
					try {
						del.delete(id);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					text_id2.setText("");
					text_desc2.setText("");
					text_quant2.setText("");
					text_preco2.setText("");
					text_data2.setText("");

					conexao con = new conexao();
					con.conectar();

					DefaultTableModel model = new DefaultTableModel();

					model.addColumn("Código");
					model.addColumn("Descrição");
					model.addColumn("Quantidade");
					model.addColumn("Preço");
					model.addColumn("Data");

					try {
						String query = "SELECT * FROM produtos order by id desc";

						Statement st = con.criarStatement();
						ResultSet rs = st.executeQuery(query);

						while (rs.next()) {

							model.addRow(new Object[] {

									rs.getInt("id"), rs.getString("desc"), rs.getInt("quant"), rs.getDouble("preco"),
									rs.getString("data")

							});

						}
						rs.close();
						st.close();
						con.desconectar();

						table_1.setModel(model);
						table_1.setAutoResizeMode(0);
						table_1.getColumnModel().getColumn(0).setPreferredWidth(65);
						table_1.getColumnModel().getColumn(1).setPreferredWidth(283);
						table_1.getColumnModel().getColumn(2).setPreferredWidth(110);
						table_1.getColumnModel().getColumn(3).setPreferredWidth(110);
						table_1.getColumnModel().getColumn(3).setPreferredWidth(110);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
					}

				} else {
					JOptionPane.showMessageDialog(null, "Campos Obrigatórios ( * )");
				}
			}
		});
		btnNewButton_2_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2_1.setBounds(434, 150, 115, 29);
		panel_consulta.add(btnNewButton_2_1);

		JButton btnNewButton_2_1_1 = new JButton("Buscar");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (text_id2.getText().length() > 0) {

					Select select = new Select();

					try {
						int cod = Integer.parseInt(text_id2.getText());
						select.select(cod);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// text_id2.setText(Integer.toString(select.getText_id2()));
					text_desc2.setText(select.getText_descSel());
					text_quant2.setText(Integer.toString(select.getText_quantSel()));
					text_preco2.setText(Double.toString(select.getText_precoSel()));
					text_data2.setText(select.getText_dataSel());
				} else {

					JOptionPane.showMessageDialog(null, "Campo 'COD' Obrigatório!");
				}
			}
		});
		btnNewButton_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_2_1_1.setBounds(171, 9, 79, 29);
		panel_consulta.add(btnNewButton_2_1_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(49, 237, 661, 261);
		panel_consulta.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		JButton btnNewButton_3_1 = new JButton("Menu");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					TelaMenuPrincipal frame = new TelaMenuPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					TelaCadProd frame = new TelaCadProd();
					frame.setLocationRelativeTo(null);
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		btnNewButton_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_3_1.setBounds(670, 9, 78, 29);
		panel_consulta.add(btnNewButton_3_1);
	}

	public void ShowData() {

		conexao con = new conexao();
		con.conectar();

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Código");
		model.addColumn("Descrição");
		model.addColumn("Quantidade");
		model.addColumn("Preço");
		model.addColumn("Data");

		try {
			String query = "SELECT * FROM produtos order by id desc";

			Statement st = con.criarStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				model.addRow(new Object[] {

						rs.getInt("id"), rs.getString("desc"), rs.getInt("quant"), rs.getDouble("preco"),
						rs.getString("data")

				});

			}
			rs.close();
			st.close();
			con.desconectar();

			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(65);
			table.getColumnModel().getColumn(1).setPreferredWidth(263);
			table.getColumnModel().getColumn(2).setPreferredWidth(110);
			table.getColumnModel().getColumn(3).setPreferredWidth(110);
			table.getColumnModel().getColumn(4).setPreferredWidth(110);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
		}
	}

	public void ShowData2() {

		conexao con = new conexao();
		con.conectar();

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Código");
		model.addColumn("Descrição");
		model.addColumn("Quantidade");
		model.addColumn("Preço");
		model.addColumn("Data");

		try {
			String query = "SELECT * FROM produtos order by id desc";

			Statement st = con.criarStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				model.addRow(new Object[] {

						rs.getInt("id"), rs.getString("desc"), rs.getInt("quant"), rs.getDouble("preco"),
						rs.getString("data")

				});

			}
			rs.close();
			st.close();
			con.desconectar();

			table_1.setModel(model);
			table_1.setAutoResizeMode(0);
			table_1.getColumnModel().getColumn(0).setPreferredWidth(65);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(283);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(110);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(110);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(110);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
		}
	}
}
