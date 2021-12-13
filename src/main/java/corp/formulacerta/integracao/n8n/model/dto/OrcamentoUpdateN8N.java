package corp.formulacerta.integracao.n8n.model.dto;

public class OrcamentoUpdateN8N {

	private String id;

	private String tray_produto_id;
	
	public OrcamentoUpdateN8N() {
		super();
	}

	public OrcamentoUpdateN8N(String id, String tray_produto_id) {
		this.id = id;
		this.tray_produto_id = tray_produto_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTray_produto_id() {
		return tray_produto_id;
	}

	public void setTray_produto_id(String tray_produto_id) {
		this.tray_produto_id = tray_produto_id;
	}

}
