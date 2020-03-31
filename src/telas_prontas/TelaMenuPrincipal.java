package telas_prontas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TelaMenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuPrincipal frame = new TelaMenuPrincipal();
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
	public TelaMenuPrincipal() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 820, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCadastroDeConsulta = new JButton("Cadastro de Consulta de Usu\u00E1rios");
		btnCadastroDeConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TelaCadUsuario frame = new TelaCadUsuario();
					frame.setLocationRelativeTo(null);
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
		btnCadastroDeConsulta
				.setIcon(new ImageIcon("D:\\Games\\ProjetoFinal_v2.0\\img\\teste2.png"));
		btnCadastroDeConsulta.setHorizontalAlignment(SwingConstants.LEFT);
		btnCadastroDeConsulta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastroDeConsulta.setBounds(167, 33, 459, 109);
		contentPane.add(btnCadastroDeConsulta);

		JButton btnD = new JButton("Cadastro de Consulta de Produtos");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					TelaCadProd frame = new TelaCadProd();
					frame.setLocationRelativeTo(null);
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
		btnD.setHorizontalAlignment(SwingConstants.LEFT);
		btnD.setIcon(
				new ImageIcon("D:\\Games\\ProjetoFinal_v2.0\\img\\teste3.png"));
		btnD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnD.setBounds(167, 229, 459, 109);
		contentPane.add(btnD);

		JButton btnCadastroDeConsulta_2 = new JButton("Cadastro de Consulta de Vendas");
		btnCadastroDeConsulta_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					TelaCadVend frame = new TelaCadVend();
					frame.setLocationRelativeTo(null);
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
		btnCadastroDeConsulta_2
				.setIcon(new ImageIcon("D:\\Games\\ProjetoFinal_v2.0\\img\\ed.png"));
		btnCadastroDeConsulta_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnCadastroDeConsulta_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastroDeConsulta_2.setBounds(167, 414, 459, 109);
		contentPane.add(btnCadastroDeConsulta_2);
	}

}
