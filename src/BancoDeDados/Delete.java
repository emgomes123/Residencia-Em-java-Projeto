package BancoDeDados;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

public class Delete {

	private int linhasDeletadas;
	

	public int getLinhasDeletadas() {
		return linhasDeletadas;
	}

	public void setLinhasDeletadas(int linhasDeletadas) {
		this.linhasDeletadas = linhasDeletadas;
	}

	public void delete(int id) throws ParseException {

		conexao conn = new conexao();

		conn.conectar();
		PreparedStatement ps = null;
		String sqlDelete = "DELETE FROM produtos" + " WHERE id = ?";

		try {
			System.out.println("==================");
			ps = conn.criarPreparedStatement(sqlDelete);

			ps.setInt(1, id);
			linhasDeletadas = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.desconectar();
		}

	}

	public void deleteVendas(int id) throws ParseException {

		conexao conn = new conexao();

		conn.conectar();
		PreparedStatement ps = null;
		String sqlDelete = "DELETE FROM vendas" + " WHERE ID_VENDAS = ?";

		try {

			ps = conn.criarPreparedStatement(sqlDelete);
			ps.setInt(1, id);
			linhasDeletadas = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.desconectar();
		}

	}

	public void deleteUsuario(int id) throws ParseException {

		conexao conn = new conexao();

		conn.conectar();
		PreparedStatement ps = null;
		String sqlDelete = "DELETE FROM usuarios" + " WHERE id_usu = ?";

		try {

			ps = conn.criarPreparedStatement(sqlDelete);

			ps.setInt(1, id);
			linhasDeletadas = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.desconectar();
		}

	}

}
