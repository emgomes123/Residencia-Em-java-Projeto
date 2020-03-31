package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class conexao {

private Connection conexao;


//Conecta e cria um banco, se não existir.
public boolean conectar() {
try {
//Criando endereço do banco
String url = "jdbc:sqlite:banco/db_vendas.db";

//iniciando a conexão
this.conexao = DriverManager.getConnection(url);
}catch (SQLException e) {
System.out.println("Erro na conexão com o banco! >>"+e.getMessage());
return false;
}

System.out.println("Conectou!");
return true;
}

//Desconecta do banco
public boolean desconectar() {
try {
if(this.conexao.isClosed() == false) {
this.conexao.close();
}
}catch(SQLException e) {
System.out.println("Erro ao desconectar >>"+e.getMessage());
return false;
}

System.out.println("Desconectou!");
return true;

}

//Criando os statements para execução dos códigos SQL
public Statement criarStatement() {
try {
return this.conexao.createStatement();
}catch(SQLException e) {
System.out.println("Erro>>"+e.getMessage());
return null;
}
}

public PreparedStatement criarPreparedStatement(String sql) {
try {
return this.conexao.prepareStatement(sql);
}catch(SQLException e){
System.out.println("Erro: "+e.getMessage());
return null;
}
}

public Connection getConnection() {
return this.conexao;
}

}