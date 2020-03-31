package Classes;

public class Vendas {

	private int id;
	private String descricao;
	private  int quantidade;
	private double preco;
	private double total;
	private String datav;
	private int id_prod;
	
	

	public Vendas() {
		super();
	}

	public Vendas(int id, String descricao, int quantidade, double preco, double total, String datav) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.total = total;
		this.datav = datav;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getId_prod() {
		return id_prod;
	}

	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}

	public String getDatav() {
		return datav;
	}

	public void setDatav(String datav) {
		this.datav = datav;
	}
	

}
