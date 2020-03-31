package Classes;

public class Produtos {
	private int id;
	protected String descricao;
	protected int quantidade;
	protected double preco;
	protected String dataValidade;
	
	
	
	public Produtos() {
		super();
	}
	public Produtos(int id, String descricao, int quantidade, double preco, String dataValidade) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.dataValidade = dataValidade;
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
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	
	

}
