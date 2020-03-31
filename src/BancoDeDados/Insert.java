package BancoDeDados;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Classes.Adm;
import Classes.Produtos;
import Classes.Vendas;

public class Insert {

	private int resultado;
	private int resultado2;
	private int resultado3;
	
	

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public int getResultado2() {
		return resultado2;
	}

	public void setResultado2(int resultado2) {
		this.resultado2 = resultado2;
	}

	public int getResultado3() {
		return resultado3;
	}

	public void setResultado3(int resultado3) {
		this.resultado3 = resultado3;
	}

	public void insert(String desc, int quant, double preco, String data) {
		Produtos prod = new Produtos();

		prod.setDescricao(desc.trim());
		prod.setQuantidade(quant);
		prod.setPreco(preco);
		prod.setDataValidade(data.trim());

		conexao con = new conexao();

		con.conectar();

		String sqlInsert = "INSERT INTO produtos (" + "desc," + "quant," + "preco," + "data" + ") VALUES(?,?,?,?)"
				+ ";";

		PreparedStatement preparedStatement = con.criarPreparedStatement(sqlInsert);
		try {

			preparedStatement.setString(1, prod.getDescricao());
			preparedStatement.setInt(2, prod.getQuantidade());
			preparedStatement.setDouble(3, prod.getPreco());
			preparedStatement.setString(4, prod.getDataValidade());

			resultado = preparedStatement.executeUpdate();

		} catch (SQLException e1) {
			System.out.println("Erro: " + e1.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {

					preparedStatement.close();
					con.desconectar();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	public void insertVendas(String desc, int quant, double preco, double total,String data,int id_prod) {
		Vendas vend = new Vendas();

		vend.setDescricao(desc.trim());
		vend.setQuantidade(quant);
		vend.setPreco(preco);
		vend.setTotal(total);
		vend.setDatav(data);
		vend.setId_prod(id_prod);

		conexao con = new conexao();

		con.conectar();

		String sqlInsert = "INSERT INTO vendas (" + "DESC_VENDAS," + "QUANT_VENDAS," + "PRECO_VENDAS," + "TOTAL_VENDAS,"+ "DATA_VENDAS,"+"ID_PROD"
				
				+ ") VALUES(?,?,?,?,?,?)" + ";";

		PreparedStatement preparedStatement = con.criarPreparedStatement(sqlInsert);
		try {

			preparedStatement.setString(1, vend.getDescricao());
			preparedStatement.setInt(2, vend.getQuantidade());
			preparedStatement.setDouble(3, vend.getPreco());
			preparedStatement.setDouble(4, vend.getTotal());
			preparedStatement.setString(5, vend.getDatav());
			preparedStatement.setInt(6, vend.getId_prod());

			resultado2 = preparedStatement.executeUpdate();

		} catch (SQLException e1) {
			System.out.println("Erro: " + e1.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {

					preparedStatement.close();
					con.desconectar();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	public void insertUsuario(String nome, String login, String senha) {
		Adm usu = new Adm();

		usu.setNome(nome.trim());
		usu.setLogin(login.trim());
		usu.setSenha(senha.trim());

		conexao con = new conexao();

		con.conectar();

		String sqlInsert = "INSERT INTO usuarios (" + "nome_usu," + "login_usu," + "senha_usu" + ") VALUES(?,?,?)"
				+ ";";

		PreparedStatement preparedStatement = con.criarPreparedStatement(sqlInsert);
		try {

			preparedStatement.setString(1, usu.getNome());
			preparedStatement.setString(2, usu.getLogin());
			preparedStatement.setString(3, usu.getSenha());

			resultado3 = preparedStatement.executeUpdate();

		} catch (SQLException e1) {
			System.out.println("Erro: " + e1.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {

					preparedStatement.close();
					con.desconectar();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

	}
	
	
}
