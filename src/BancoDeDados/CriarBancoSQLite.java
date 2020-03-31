package BancoDeDados;

import java.sql.SQLException;
import java.sql.Statement;


public class CriarBancoSQLite {

//Atributos final devem ser inicializados no contrutor.
private final conexao con;

public CriarBancoSQLite(conexao con) {
this.con = con;
}

//Método responsável por criar uma tabela

public void criarTabelas() {

String USUARIOS = "CREATE TABLE IF NOT EXISTS usuarios"
+ "("
+ "id_usu integer  PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE  ,"
+ "nome_usu VARCHAR (255) NOT NULL,"
+ " login_usu VARCHAR (255) NOT NULL,"
+ "senha_usu VARCHAR (255) NOT NULL"
+ ")";

String PRODUTOS = "CREATE TABLE IF NOT EXISTS produtos"
+ "("
+ "id integer  PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE  ,"
+ "desc VARCHAR (255) NOT NULL,"
+ "quant integer NOT NULL,"
+ "preco double  NOT NULL,"
+ "data VARCHAR (255) NOT NULL"
+ ")";

String VENDAS = "CREATE TABLE IF NOT EXISTS vendas"
+ "("
+ "ID_VENDAS integer  PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE  ,"
+ "DESC_VENDAS VARCHAR (255) NOT NULL,"
+ "QUANT_VENDAS integer NOT NULL,"
+ "PRECO_VENDAS double  NOT NULL,"
+ "TOTAL_VENDAS double NOT NULL,			"
+ "DATA_VENDAS VARCHAR (255) NOT NULL,"
+ "ID_PROD INTEGER NOT NULL,"
+ "FOREIGN KEY(ID_PROD) REFERENCES produtos(id)"
+ ")";

boolean conectou = false;

try {
conectou = this.con.conectar();

Statement stmt = this.con.criarStatement();

stmt.execute(USUARIOS);
stmt.execute(PRODUTOS);
stmt.execute(VENDAS);

System.out.println("Tabela pessoa criada!");
System.out.println("Tabela pessoa produtos!");
System.out.println("Tabela pessoa vendas!");

}catch(SQLException e) {
System.out.println("Erro>>"+e.getMessage());
}finally {
if(conectou) {
this.con.desconectar();
}
}
}


}
