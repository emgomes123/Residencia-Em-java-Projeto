package BancoDeDados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Select {
	public int text_id2;
	private String text_descSel;
	private int text_quantSel;
	private double text_precoSel;
	private String text_dataSel;
	private double text_totalSel;
	private int Text_id_prod;
	

	// Variaveis para usuario

	private String nome;
	private String login;
	private String senha;
	private String login2;

	// fim Variaveis para usuario

	public int getText_id2() {
		return text_id2;
	}

	public void setText_id2(int text_id2) {
		this.text_id2 = text_id2;
	}

	public String getText_descSel() {
		return text_descSel;
	}

	public void setText_descSel(String text_descSel) {
		this.text_descSel = text_descSel;
	}

	public int getText_quantSel() {
		return text_quantSel;
	}

	public void setText_quantSel(int text_quantSel) {
		this.text_quantSel = text_quantSel;
	}

	public double getText_precoSel() {
		return text_precoSel;
	}

	public void setText_precoSel(double text_precoSel) {
		this.text_precoSel = text_precoSel;
	}

	public String getText_dataSel() {
		return text_dataSel;
	}

	public void setText_dataSel(String text_dataSel) {
		this.text_dataSel = text_dataSel;
	}

	public double getText_totalSel() {
		return text_totalSel;
	}

	public void setText_totalSel(double text_totalSel) {
		this.text_totalSel = text_totalSel;
	}
	
	

	public int getText_id_prod() {
		return Text_id_prod;
	}

	public void setText_id_prod(int text_id_prod) {
		Text_id_prod = text_id_prod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin2() {
		return login2;
	}

	public void setLogin2(String login2) {
		this.login2 = login2;
	}

	public void select(int cod) throws ParseException {

		

		conexao conn = new conexao();

		conn.conectar();

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM produtos WHERE id = ?;";

		try {
			int id = cod;
			ps = conn.criarPreparedStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				// text_id2 = rs.getInt("id");
				text_descSel = rs.getString("desc");
				text_quantSel = rs.getInt("quant");
				text_precoSel = rs.getDouble("preco");
				text_dataSel = rs.getString("data");

			}

		} catch (SQLException e) {

			System.out.println("Erro >>" + e.getMessage());
		} finally {
			// Importante fechar todos os recursos para liberar memória!
			// Caso não feche, consumirá cada vez mais recurso e o aplicativo ficará cada
			// vez mais lento.
			try {
				rs.close();
				conn.desconectar();

			} catch (SQLException e) {
				System.out.println("Erro no fechamento!");

			}

		}

	}

	public void selectVendas(int cod) throws ParseException {

		

		conexao conn = new conexao();

		conn.conectar();

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM vendas WHERE ID_VENDAS= ?;";

		try {
			int id = cod;
			ps = conn.criarPreparedStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				// text_id2 = rs.getInt("id");
				text_descSel = rs.getString("DESC_VENDAS");
				text_quantSel = rs.getInt("QUANT_VENDAS");
				text_precoSel = rs.getDouble("PRECO_VENDAS");
				text_totalSel = rs.getDouble("TOTAL_VENDAS");
				Text_id_prod =  rs.getInt("ID_prod");

			}

		} catch (SQLException e) {

			System.out.println("Erro >>" + e.getMessage());
		} finally {
			// Importante fechar todos os recursos para liberar memória!
			// Caso não feche, consumirá cada vez mais recurso e o aplicativo ficará cada
			// vez mais lento.
			try {
				rs.close();
				conn.desconectar();

			} catch (SQLException e) {
				System.out.println("Erro no fechamento!");

			}

		}

	}

	public void selectUsuario(int cod) throws ParseException {

		conexao conn = new conexao();

		conn.conectar();

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM usuarios WHERE id_usu= ?;";

		try {
			int id = cod;
			ps = conn.criarPreparedStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				// text_id2 = rs.getInt("id");
				nome = rs.getString("nome_usu");
				login = rs.getString("login_usu");
				senha = rs.getString("senha_usu");

			}

		} catch (SQLException e) {

			System.out.println("Erro >>" + e.getMessage());
		} finally {
			// Importante fechar todos os recursos para liberar memória!
			// Caso não feche, consumirá cada vez mais recurso e o aplicativo ficará cada
			// vez mais lento.
			try {
				rs.close();
				conn.desconectar();

			} catch (SQLException e) {
				System.out.println("Erro no fechamento!");

			}

		}

	}

	public void selectLogin(String login) throws ParseException {

		conexao conn = new conexao();

		conn.conectar();

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM usuarios WHERE login_usu= ? ;";

		try {

			ps = conn.criarPreparedStatement(sql);
			ps.setString(1, login);
			
			
			rs = ps.executeQuery();

			while (rs.next()) {

				// text_id2 = rs.getInt("id");
				nome = rs.getString("nome_usu");
				login2 = rs.getString("login_usu");
				senha = rs.getString("senha_usu");

			}

		} catch (SQLException e) {

			System.out.println("Erro >>" + e.getMessage());
		} finally {
			// Importante fechar todos os recursos para liberar memória!
			// Caso não feche, consumirá cada vez mais recurso e o aplicativo ficará cada
			// vez mais lento.
			try {
				rs.close();
				conn.desconectar();

			} catch (SQLException e) {
				System.out.println("Erro no fechamento!");

			}

		}

	}
	
	
	
}
