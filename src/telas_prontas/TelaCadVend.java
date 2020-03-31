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

public class TelaCadVend extends JFrame {

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
					TelaCadVend frame = new TelaCadVend();

					frame.setResizable(false);
					frame.setLocationRelativeTo(null);

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
	public TelaCadVend() throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

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
		tabbedPane.addTab("Cadastro de Vendas", null, panel_cadastro, null);
		panel_cadastro.setLayout(null);

		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(28, 56, 98, 29);
		panel_cadastro.add(lblNewLabel);

		JLabel lblQunatidade = new JLabel("Qunatidade:");
		lblQunatidade.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblQunatidade.setBounds(28, 111, 112, 29);
		panel_cadastro.add(lblQunatidade);

		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPreo.setBounds(294, 110, 65, 29);
		panel_cadastro.add(lblPreo);

		JLabel lblValidade = new JLabel("Total:");
		lblValidade.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblValidade.setBounds(527, 111, 58, 29);
		panel_cadastro.add(lblValidade);

		text_desc = new JTextField();
		text_desc.setEditable(false);
		text_desc.setFont(new Font("Times New Roman", Font.BOLD, 20));
		text_desc.setBounds(126, 52, 622, 36);
		panel_cadastro.add(text_desc);
		text_desc.setColumns(10);

		JNumberField text_quant = new JNumberField(6);
		text_quant.setBounds(146, 111, 84, 30);
		panel_cadastro.add(text_quant);

		JDoubleField text_preco = new JDoubleField(6);
		text_preco.setEditable(false);
		text_preco.setBounds(355, 111, 84, 30);
		panel_cadastro.add(text_preco);

		JDoubleField text_data = new JDoubleField(6);
		text_data.setEditable(false);

		text_data.setBounds(588, 111, 160, 30);
		panel_cadastro.add(text_data);

		JFormattedTextField text_id = new JFormattedTextField();
		text_id.setBounds(156, 5, 50, 36);
		panel_cadastro.add(text_id);

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
				model.addColumn("Total");
				model.addColumn("Data");
				model.addColumn("Cod.Produto");

				try {
					String query = "SELECT * FROM vendas order by id_vendas desc";

					Statement st = con.criarStatement();
					ResultSet rs = st.executeQuery(query);

					while (rs.next()) {

						model.addRow(new Object[] {

								rs.getInt("id_vendas"),
								rs.getString("desc_vendas"),
								rs.getInt("quant_vendas"),
								rs.getDouble("preco_vendas"),
								rs.getDouble("total_vendas"), 
								rs.getString("Data_vendas"),
								rs.getInt("id_prod")
						});

					}
					rs.close();
					st.close();
					con.desconectar();

					table.setModel(model);
					table.setAutoResizeMode(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(65);
					table.getColumnModel().getColumn(1).setPreferredWidth(283);
					table.getColumnModel().getColumn(2).setPreferredWidth(110);
					table.getColumnModel().getColumn(3).setPreferredWidth(110);
					table.getColumnModel().getColumn(4).setPreferredWidth(110);
					table.getColumnModel().getColumn(0).setPreferredWidth(11);
					table.getColumnModel().getColumn(5).setPreferredWidth(100);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
				}

			}

		});
		
		JFormattedTextField text_datav = new JFormattedTextField();
		text_datav.setEditable(false);
		text_datav.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		text_datav.setFont(new Font("Times New Roman", Font.BOLD, 20));
		text_datav.setBounds(461, 7, 112, 30);
		panel_cadastro.add(text_datav);

		btnNewButton.addActionListener(new ActionListener() {

			private double p;
			private int q;
			private String datav;

			@SuppressWarnings("unused")
			public double getP() {
				return p;
			}

			@SuppressWarnings("unused")
			public void setP(double p) {
				this.p = p;
			}

			@SuppressWarnings("unused")
			public int getQ() {
				return q;
			}

			@SuppressWarnings("unused")
			public void setQ(int q) {
				this.q = q;
			}

			public void actionPerformed(ActionEvent arg0) {

				// select na tabela produtos
				Select select = new Select();

				try {
					int cod = Integer.parseInt(text_id.getText());
					select.select(cod);
				} catch (ParseException e1) {

					e1.printStackTrace();
				}

				int q = select.getText_quantSel();

				p = select.getText_precoSel();

				// fim select na tabela produtos

				// Inserte na tabela vendas

				if ((text_desc.getText().length() > 0) && (text_quant.getText().length() > 0)
						&& (text_preco.getText().length() > 0) && (text_data.getText().length() > 0)) {

					String desc = text_desc.getText().trim();
					int quant = Integer.parseInt(text_quant.getText().trim());
					double preco = Double.parseDouble(text_preco.getText().trim());
					double total = Double.parseDouble(text_data.getText().trim());
					String datav = text_datav.getText();
					int id_prod = Integer.parseInt(text_id.getText());
				    
					
					

					if (q >= quant) {

						Insert insert = new Insert();

						insert.insertVendas(desc, quant, preco, total,datav,id_prod);

						conexao con = new conexao();
						con.conectar();

						DefaultTableModel model = new DefaultTableModel();

						model.addColumn("Código");
						model.addColumn("Descrição");
						model.addColumn("Quantidade");
						model.addColumn("Preço");
						model.addColumn("Total");
						model.addColumn("Data");
						model.addColumn("Cod.Produto");

						try {
							String query = "SELECT * FROM vendas order by id_vendas desc";

							Statement st = con.criarStatement();
							ResultSet rs = st.executeQuery(query);

							while (rs.next()) {

								model.addRow(new Object[] {

										rs.getInt("id_vendas"),
										rs.getString("desc_vendas"),
										rs.getInt("quant_vendas"),
										rs.getDouble("preco_vendas"),
										rs.getDouble("total_vendas"), 
										rs.getString("Data_vendas"),
										rs.getInt("id_prod")

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
							table_1.getColumnModel().getColumn(4).setPreferredWidth(110);
							table_1.getColumnModel().getColumn(0).setPreferredWidth(11);
							table_1.getColumnModel().getColumn(5).setPreferredWidth(100);

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
						}

						// fim Inserte na tabela vendas

						// Select na tabela produtos

						try {
							int cod = Integer.parseInt(text_id.getText());
							select.select(cod);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						int quantProd = select.getText_quantSel();
						int vendProd = quantProd - quant;

						// fim Select na tabela produtos

						// Update na tabela produtos com na coluna quantidade referente ao produto
						// vendido

						Update up = new Update();

						int quantUp = vendProd;

						int codUp = Integer.parseInt(text_id.getText());

						up.updateprodquant(quantUp, codUp);
					} else {
						JOptionPane.showMessageDialog(null, "Qunatida não disponiel em estoque!");
					}

					// fim, Update na tabela produtos com na coluna quantidade referente ao produto
					// vendido
					text_id.setText("");
					text_desc.setText("");
					text_quant.setText("");
					text_preco.setText("");
					text_data.setText("");

					text_desc.setEnabled(false);
					text_quant.setEnabled(false);
					text_preco.setEnabled(false);
					text_data.setEnabled(false);

				} else {

					JOptionPane.showMessageDialog(null, "Campos Obrigaorios ( * )");

				}

			}

			@SuppressWarnings("unused")
			public String getDatav() {
				return datav;
			}

			@SuppressWarnings("unused")
			public void setDatav(String datav) {
				this.datav = datav;
			}

		});

		btnNewButton.setBounds(179, 169, 115, 29);
		panel_cadastro.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Novo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text_id.setText("");
				text_desc.setText("");
				text_quant.setText("");
				text_preco.setText("");
				text_data.setText("");

				text_desc.setEnabled(true);
				text_quant.setEnabled(true);
				text_preco.setEnabled(true);
				text_data.setEnabled(true);
			}
		});
		btnNewButton_1.setBounds(433, 169, 115, 29);
		panel_cadastro.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(15, 101, 16, 16);
		panel_cadastro.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("*");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(15, 49, 16, 16);
		panel_cadastro.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("*");
		lblNewLabel_1_2.setForeground(Color.RED);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(274, 107, 20, 16);
		panel_cadastro.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("*");
		lblNewLabel_1_3.setForeground(Color.RED);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(517, 101, 20, 16);
		panel_cadastro.add(lblNewLabel_1_3);

		JButton btnOk = new JButton("Buscar");
		btnOk.setFont(new Font("Times New Roman", Font.BOLD, 16));

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (text_id.getText().length() > 0) {

					Select select = new Select();

					try {
						int cod = Integer.parseInt(text_id.getText());
						select.select(cod);

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					text_desc.setText(select.getText_descSel());

					text_preco.setText(Double.toString(select.getText_precoSel()));

				} else {
					JOptionPane.showMessageDialog(null, "O campo 'COD' é obrigatório!");

				}

			}
		});
		btnOk.setBounds(221, 7, 84, 29);
		panel_cadastro.add(btnOk);

		JLabel lblNewLabel_4 = new JLabel("Cod. Produto:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_4.setBounds(28, 4, 137, 36);
		panel_cadastro.add(lblNewLabel_4);

		JLabel lblNewLabel_1_1_1 = new JLabel("*");
		lblNewLabel_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(28, 0, 16, 16);
		panel_cadastro.add(lblNewLabel_1_1_1);

		JButton btnNewButton_2 = new JButton("Menu Principal");
		btnNewButton_2.addActionListener(new ActionListener() {

			private TelaCadVend frame;

			@SuppressWarnings("unused")
			public TelaCadVend getFrame() {
				return frame;
			}

			@SuppressWarnings("unused")
			public void setFrame(TelaCadVend frame) {
				this.frame = frame;
			}

			public void actionPerformed(ActionEvent e) {

				try {

					TelaMenuPrincipal frame = new TelaMenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				try {
					frame = new TelaCadVend();
					dispose();
				} catch (Exception e4) {
					e4.printStackTrace();
				}

				if ((text_quant.getText().length() > 0) && (text_preco.getText().length() > 0)) {

					double quant = Double.parseDouble(text_quant.getText());
					double preco = Double.parseDouble(text_preco.getText());

					double resultado = (quant * preco);

					String calculo = Double.toString(resultado);

					text_data.setText(calculo);
				} else {
					JOptionPane.showMessageDialog(null, "Campos quantidade de preço devem ser preenchidos");
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 237, 661, 261);
		panel_cadastro.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnNewButton_3 = new JButton("Enter");
		getRootPane().setDefaultButton(btnNewButton_3);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((text_quant.getText().length() > 0) && (text_preco.getText().length() > 0)) {

					int quant = Integer.parseInt(text_quant.getText());
					double preco = Double.parseDouble(text_preco.getText());

					double mult = quant * preco;

					text_data.setText(Double.toString(mult));

				} else {

					JOptionPane.showMessageDialog(null, "Campos 'quantidade' e 'preço' precisam estar preenchidos!!");
				}

			}
		});
		btnNewButton_3.setBounds(633, 147, 77, 29);
		panel_cadastro.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Menu");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					TelaMenuPrincipal frame = new TelaMenuPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					TelaCadVend frame = new TelaCadVend();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(658, 9, 90, 29);
		panel_cadastro.add(btnNewButton_4);
		
		JLabel lblNewLabel_5 = new JLabel("Data:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_5.setBounds(398, 12, 50, 20);
		panel_cadastro.add(lblNewLabel_5);
		
		

		JPanel panel_consulta = new JPanel();
		tabbedPane.addTab("Consulta", null, panel_consulta, null);
		panel_consulta.setLayout(null);

		text_desc2 = new JTextField();
		text_desc2.setEditable(false);
		text_desc2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		text_desc2.setColumns(10);
		text_desc2.setBounds(113, 56, 622, 36);
		panel_consulta.add(text_desc2);

		JLabel lblQunatidade_1 = new JLabel("Qunatidade:");
		lblQunatidade_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblQunatidade_1.setBounds(15, 104, 112, 29);
		panel_consulta.add(lblQunatidade_1);

		JLabel lblNewLabel_2 = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(15, 60, 98, 29);
		panel_consulta.add(lblNewLabel_2);

		JFormattedTextField text_quant2 = new JFormattedTextField();
		text_quant2.setBounds(133, 104, 84, 30);
		panel_consulta.add(text_quant2);

		JFormattedTextField text_preco2 = new JFormattedTextField();
		text_preco2.setEnabled(false);
		text_preco2.setBounds(342, 104, 84, 30);
		panel_consulta.add(text_preco2);

		JFormattedTextField text_total2 = new JFormattedTextField();
		text_total2.setEditable(false);
		text_total2.setBounds(575, 104, 160, 30);
		panel_consulta.add(text_total2);

		JLabel lblNewLabel_3 = new JLabel("Cod:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(65, 14, 49, 20);
		panel_consulta.add(lblNewLabel_3);

		JFormattedTextField text_id2 = new JFormattedTextField();
		text_id2.setBounds(114, 11, 43, 29);
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
		lblNewLabel_1_3_1.setBounds(501, 104, 20, 16);
		panel_consulta.add(lblNewLabel_1_3_1);

		JLabel lblValidade_1 = new JLabel("Total:");
		lblValidade_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblValidade_1.setBounds(513, 104, 57, 29);
		panel_consulta.add(lblValidade_1);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("*");
		lblNewLabel_1_2_1_1.setForeground(Color.RED);
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1_1.setBounds(15, 94, 20, 16);
		panel_consulta.add(lblNewLabel_1_2_1_1);

		JLabel lblNewLabel_1_2_1_2 = new JLabel("*");
		lblNewLabel_1_2_1_2.setForeground(Color.RED);
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1_2.setBounds(15, 48, 20, 16);
		panel_consulta.add(lblNewLabel_1_2_1_2);

		JLabel lblNewLabel_1_2_1_3 = new JLabel("*");
		lblNewLabel_1_2_1_3.setForeground(Color.RED);
		lblNewLabel_1_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1_3.setBounds(59, 2, 13, 16);
		panel_consulta.add(lblNewLabel_1_2_1_3);

		JButton btnNewButton_2_1 = new JButton("Deletar");

		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				conexao con = new conexao();
				con.conectar();

				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("Código");
				model.addColumn("Descrição");
				model.addColumn("Quantidade");
				model.addColumn("Preço");
				model.addColumn("Data");
				model.addColumn("Cod.Produto");

				try {
					String query = "SELECT * FROM vendas order by id_vendas desc";

					Statement st = con.criarStatement();
					ResultSet rs = st.executeQuery(query);

					while (rs.next()) {

						model.addRow(new Object[] {

								rs.getInt("id_vendas"), rs.getString("desc_vendas"), rs.getInt("quant_vendas"),
								rs.getDouble("preco_vendas"), rs.getDouble("total_vendas"), rs.getInt("id_prod")

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
					table_1.getColumnModel().getColumn(4).setPreferredWidth(110);
					table_1.getColumnModel().getColumn(5).setPreferredWidth(100);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro " + e2.getMessage());
				}

			}

		});
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((text_id2.getText().length() > 0 && text_desc2.getText().length() > 0)
						&& (text_quant2.getText().length() > 0) && (text_preco2.getText().length() > 0)
						&& (text_total2.getText().length() > 0)) {

					Delete del = new Delete();

					int id = Integer.parseInt(text_id2.getText());
					try {
						del.deleteVendas(id);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					text_id2.setText("");
					text_desc2.setText("");
					text_quant2.setText("");
					text_preco2.setText("");
					text_total2.setText("");

					conexao con = new conexao();
					con.conectar();

					DefaultTableModel model = new DefaultTableModel();

					model.addColumn("Código");
					model.addColumn("Descrição");
					model.addColumn("Quantidade");
					model.addColumn("Preço");
					model.addColumn("Data");
					model.addColumn("Cod.Produto");

					try {
						String query = "SELECT * FROM vendas order by id_vendas desc";

						Statement st = con.criarStatement();
						ResultSet rs = st.executeQuery(query);

						while (rs.next()) {

							model.addRow(new Object[] {

									rs.getInt("id_vendas"), rs.getString("desc_vendas"), rs.getInt("quant_vendas"),
									rs.getDouble("preco_vendas"), rs.getDouble("total_vendas"), rs.getInt("id_prod")

							});

						}
						rs.close();
						st.close();
						con.desconectar();

						table.setModel(model);
						table.setAutoResizeMode(0);
						table.getColumnModel().getColumn(0).setPreferredWidth(65);
						table.getColumnModel().getColumn(1).setPreferredWidth(283);
						table.getColumnModel().getColumn(2).setPreferredWidth(110);
						table.getColumnModel().getColumn(3).setPreferredWidth(110);
						table.getColumnModel().getColumn(4).setPreferredWidth(110);
						table.getColumnModel().getColumn(5).setPreferredWidth(100);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
					}

				} else {
					JOptionPane.showMessageDialog(null, "Campos Obrigatórios ( * )");
				}
			}
		});
		btnNewButton_2_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2_1.setBounds(414, 175, 115, 29);
		panel_consulta.add(btnNewButton_2_1);

		JButton btnNewButton_2_1_1 = new JButton("Buscar");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (text_id2.getText().length() > 0) {

					Select select = new Select();

					try {
						int cod = Integer.parseInt(text_id2.getText());
						select.selectVendas(cod);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// text_id2.setText(Integer.toString(select.getText_id2()));
					text_desc2.setText(select.getText_descSel());
					text_quant2.setText(Integer.toString(select.getText_quantSel()));
					text_preco2.setText(Double.toString(select.getText_precoSel()));
					text_total2.setText(Double.toString(select.getText_totalSel()));
				} else {
					JOptionPane.showMessageDialog(null, "O campo 'COD' é obrigatório!");

				}
			}
		});
		btnNewButton_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2_1_1.setBounds(172, 11, 84, 29);
		panel_consulta.add(btnNewButton_2_1_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(49, 237, 661, 261);
		panel_consulta.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		JButton btnNewButton_4_1 = new JButton("Menu");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					TelaMenuPrincipal frame = new TelaMenuPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					TelaCadVend frame = new TelaCadVend();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_4_1.setBounds(658, 11, 90, 29);
		panel_consulta.add(btnNewButton_4_1);

		JButton btnNewButton_5 = new JButton("Atualizar");

		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				conexao con = new conexao();
				con.conectar();

				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("Código");
				model.addColumn("Descrição");
				model.addColumn("Quantidade");
				model.addColumn("Preço");
				model.addColumn("Data");
				model.addColumn("Cod.Produto");

				try {
					String query = "SELECT * FROM vendas order by id_vendas desc";

					Statement st = con.criarStatement();
					ResultSet rs = st.executeQuery(query);

					while (rs.next()) {

						model.addRow(new Object[] {

								rs.getInt("id_vendas"), rs.getString("desc_vendas"), rs.getInt("quant_vendas"),
								rs.getDouble("preco_vendas"), rs.getDouble("total_vendas"), rs.getInt("id_prod")

						});

					}
					rs.close();
					st.close();
					con.desconectar();

					table.setModel(model);
					table.setAutoResizeMode(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(65);
					table.getColumnModel().getColumn(1).setPreferredWidth(283);
					table.getColumnModel().getColumn(2).setPreferredWidth(110);
					table.getColumnModel().getColumn(3).setPreferredWidth(110);
					table.getColumnModel().getColumn(4).setPreferredWidth(110);
					table.getColumnModel().getColumn(5).setPreferredWidth(100);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro " + e2.getMessage());
				}

			}

		});

		btnNewButton_5.addActionListener(new ActionListener() {
			private double preco;
			private int quantProd;
			private int quantv;
			private int diferenca;

			@SuppressWarnings("unused")
			public double getPreco() {
				return preco;
			}

			@SuppressWarnings("unused")
			public void setPreco(double preco) {
				this.preco = preco;
			}

			@SuppressWarnings("unused")
			public int getQuantProd() {
				return quantProd;
			}

			@SuppressWarnings("unused")
			public void setQuantProd(int quantProd) {
				this.quantProd = quantProd;
			}

			@SuppressWarnings("unused")
			public int getQuantv() {
				return quantv;
			}

			@SuppressWarnings("unused")
			public void setQuantv(int quantv) {
				this.quantv = quantv;
			}

			@SuppressWarnings("unused")
			public int getDiferenca() {
				return diferenca;
			}

			@SuppressWarnings("unused")
			public void setDiferenca(int diferenca) {
				this.diferenca = diferenca;
			}

			public void actionPerformed(ActionEvent e) {

				if ((text_id2.getText().length() > 0) && (text_quant2.getText().length() > 0)) {

					// select table vendas

					Select select = new Select();

					try {
						int cod = Integer.parseInt(text_id2.getText());
						select.selectVendas(cod);
					} catch (ParseException e1) {

						e1.printStackTrace();
					}

					quantv = select.getText_quantSel();

					int id_prod = select.getText_id_prod();

					// fim select tabela vendas

					// select na tabela produtos

					try {
						int codi = id_prod;
						select.select(codi);
					} catch (ParseException e1) {

						e1.printStackTrace();
					}

					quantProd = select.getText_quantSel();

					// fim select na tabela produtos

					// Calculo da diferença entre a quantdade vendida antes e a atual

					int quantInt = Integer.parseInt(text_quant2.getText());

					double totalUpdate = Double.parseDouble(text_total2.getText());

					int codup = Integer.parseInt(text_id2.getText());

					// fim do Calculo da diferença entre a quantdade vendida antes e a atual

					Update updateVendas = new Update();
					Update updateProd = new Update();

					updateVendas.updatVendas(quantInt, totalUpdate, codup);

					if (quantv > quantInt) {
						diferenca = quantv - quantInt;

						quantProd = quantProd + diferenca;
						updateProd.updateprodquant(quantProd, id_prod);

					} else if (quantv < quantInt) {
						diferenca = quantInt - quantv;

						if (quantProd >= diferenca) {

							quantProd = quantProd - diferenca;
							updateProd.updateprodquant(quantProd, id_prod);
						} else {
							JOptionPane.showMessageDialog(null, "Quantidade não disponivel em estoque ");
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "Campos ' * ' Obrigatórios");

				}

				conexao con = new conexao();
				con.conectar();

				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("Código");
				model.addColumn("Descrição");
				model.addColumn("Quantidade");
				model.addColumn("Preço");
				model.addColumn("Data");
				model.addColumn("Cod.Produto");

				try {
					String query = "SELECT * FROM vendas order by id_vendas desc";

					Statement st = con.criarStatement();
					ResultSet rs = st.executeQuery(query);

					while (rs.next()) {

						model.addRow(new Object[] {

								rs.getInt("id_vendas"), rs.getString("desc_vendas"), rs.getInt("quant_vendas"),
								rs.getDouble("preco_vendas"), rs.getDouble("total_vendas"), rs.getInt("id_prod")

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
					table_1.getColumnModel().getColumn(4).setPreferredWidth(110);
					table_1.getColumnModel().getColumn(5).setPreferredWidth(100);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro " + e1.getMessage());
				}

			}
		});

		btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_5.setBounds(205, 175, 115, 29);
		panel_consulta.add(btnNewButton_5);

		JButton btnNewButton_3_1 = new JButton("Enter");

		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (text_quant2.getText().length() > 0) {

					int quant = Integer.parseInt(text_quant2.getText());
					double preco = Double.parseDouble(text_preco2.getText());

					double mult = quant * preco;

					text_total2.setText(Double.toString(mult));

				} else {

					JOptionPane.showMessageDialog(null, "Campos 'quantidade' e 'preço' precisam estar preenchidos!!");
				}
			}
		});
		btnNewButton_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_3_1.setBounds(616, 138, 77, 29);
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
		model.addColumn("Total");
		model.addColumn("Data");
		model.addColumn("Cod.Produto");

		try {
			String query = "SELECT * FROM vendas order by id_vendas desc";

			Statement st = con.criarStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				model.addRow(new Object[] {

						rs.getInt("id_vendas"),
						rs.getString("desc_vendas"),
						rs.getInt("quant_vendas"),
						rs.getDouble("preco_vendas"),
						rs.getDouble("total_vendas"), 
						rs.getString("Data_vendas"),
						rs.getInt("id_prod")

				});

			}
			rs.close();
			st.close();
			con.desconectar();

			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(65);
			table.getColumnModel().getColumn(1).setPreferredWidth(283);
			table.getColumnModel().getColumn(2).setPreferredWidth(110);
			table.getColumnModel().getColumn(3).setPreferredWidth(110);
			table.getColumnModel().getColumn(4).setPreferredWidth(110);
			table.getColumnModel().getColumn(0).setPreferredWidth(11);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);

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
		model.addColumn("Total");
		model.addColumn("Data");
		model.addColumn("Cod.Produto");

		try {
			String query = "SELECT * FROM vendas order by id_vendas desc";

			Statement st = con.criarStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				model.addRow(new Object[] {

						rs.getInt("id_vendas"),
						rs.getString("desc_vendas"),
						rs.getInt("quant_vendas"),
						rs.getDouble("preco_vendas"),
						rs.getDouble("total_vendas"), 
						rs.getString("Data_vendas"),
						rs.getInt("id_prod")

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
			table_1.getColumnModel().getColumn(4).setPreferredWidth(110);
			table.getColumnModel().getColumn(0).setPreferredWidth(11);
			table_1.getColumnModel().getColumn(5).setPreferredWidth(100);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
		}
	}
}
