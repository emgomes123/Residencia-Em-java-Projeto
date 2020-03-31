package BancoDeDados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update {

	private ResultSet rs;
	private ResultSet rs2;
	private ResultSet rs3;
	private ResultSet rs4;
	private ResultSet rs5;
	
	

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public ResultSet getRs2() {
		return rs2;
	}

	public void setRs2(ResultSet rs2) {
		this.rs2 = rs2;
	}

	public ResultSet getRs3() {
		return rs3;
	}

	public void setRs3(ResultSet rs3) {
		this.rs3 = rs3;
	}

	public ResultSet getRs4() {
		return rs4;
	}

	public void setRs4(ResultSet rs4) {
		this.rs4 = rs4;
	}

	public ResultSet getRs5() {
		return rs5;
	}

	public void setRs5(ResultSet rs5) {
		this.rs5 = rs5;
	}

	public void update(String descUp, int quantUp, double precoUp, String dataUp, int codUp) {

		conexao conn = new conexao();

		conn.conectar();

		PreparedStatement ps = null;
		rs = null;

		String sqlUpdate = "UPDATE produtos " + "SET " + "desc=? , " + "quant=?, " + "preco = ?," + "data =? "
				+ "WHERE id =?";

		try {
			System.out.println("=================================");
			ps = conn.criarPreparedStatement(sqlUpdate);
			ps.setString(1, descUp);
			ps.setInt(2, quantUp);
			ps.setDouble(3, precoUp);
			ps.setString(4, dataUp);
			ps.setInt(5, codUp);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro");
			e.printStackTrace();
		} finally {
			// try {
			// rs.close();
			conn.desconectar();
			// } catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}

	}

	public void updateVendas(String descUp, int quantUp, double precoUp, double totalUp, int codUp) {

		conexao conn = new conexao();

		conn.conectar();

		PreparedStatement ps = null;
		rs2 = null;

		String sqlUpdate = "UPDATE vendas " + "SET " + "DESC_VENDAS=? , " + "QUANT_VENDAS=?, " + "PRECO_VENDAS = ?,"
				+ "TOTAL_VENDAS =? " + "WHERE ID_VENDAS =?";

		try {
			System.out.println("=================================");
			ps = conn.criarPreparedStatement(sqlUpdate);
			ps.setString(1, descUp);
			ps.setInt(2, quantUp);
			ps.setDouble(3, precoUp);
			ps.setDouble(4, totalUp);
			ps.setInt(5, codUp);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro");
			e.printStackTrace();
		} finally {
			// try {
			// rs.close();
			conn.desconectar();
			// } catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}

	}

	public void updateprodquant(int quantUp, int codUp) {

		conexao conn = new conexao();

		conn.conectar();

		PreparedStatement ps = null;
		rs3 = null;

		String sqlUpdate = "UPDATE produtos " + "SET " + "quant=? " + "WHERE id =?";

		try {
			System.out.println("=================================");
			ps = conn.criarPreparedStatement(sqlUpdate);
			ps.setInt(1, quantUp);
			ps.setInt(2, codUp);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro");
			e.printStackTrace();
		} finally {
			// try {
			// rs.close();
			conn.desconectar();
			// } catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}

	}

	public void updateUsuario(String nome, String login, String senha, int codUp) {

		conexao conn = new conexao();

		conn.conectar();

		PreparedStatement ps = null;
		rs4 = null;

		String sqlUpdate = "UPDATE usuarios " + "SET " + "nome_usu=? , " + "login_usu=?, " + "senha_usu = ?"
				+ "WHERE id_usu =?";

		try {

			ps = conn.criarPreparedStatement(sqlUpdate);
			ps.setString(1, nome);
			ps.setString(2, login);
			ps.setString(3, senha);

			ps.setInt(4, codUp);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro");
			e.printStackTrace();
		} finally {
			// try {
			// rs.close();
			conn.desconectar();
			// } catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}

	}

	public void updatVendas(int quant, double total, int codUp) {

		conexao conn = new conexao();

		conn.conectar();

		PreparedStatement ps = null;
		rs5 = null;

		String sqlUpdate = "UPDATE vendas " + "SET " + "quant_vendas=?," + "total_vendas=? " + "WHERE id_vendas =?";

		try {

			ps = conn.criarPreparedStatement(sqlUpdate);
			ps.setInt(1, quant);
			ps.setDouble(2, total);
			ps.setInt(3, codUp);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro");
			e.printStackTrace();
		} finally {
			// try {
			// rs.close();
			conn.desconectar();
			// } catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		}

	}

}
