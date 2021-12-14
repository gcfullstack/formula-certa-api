package corp.formulacerta.integracao.tray.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import corp.formulacerta.integracao.mirror.model.OrcTrail;
import corp.formulacerta.integracao.n8n.model.dto.OrcamentoN8N;
import corp.formulacerta.integracao.utils.ConstantsUtils;
import corp.formulacerta.integracao.utils.MethodsUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoDTO {

	private String ean;

	private String name;

	private String ncm;

	private String description;

	@JsonProperty("description_small")
	private String descriptionSmall;

	private BigDecimal price;

	@JsonProperty("cost_price")
	private BigDecimal costPrice;

	@JsonProperty("promotional_price")
	private BigDecimal promotionalPrice;

	@JsonProperty("start_promotion")
	private Date startPromotion;

	@JsonProperty("end_promotion")
	private Date endPromotion;

	@JsonProperty("ipi_value")
	private BigDecimal ipiValue;

	private String brand;

	private String model;

	private Integer weight;

	private Integer length;

	private Integer width;

	private Integer height;

	private Integer stock;

	@JsonProperty("category_id")
	private Integer categoryId;

	private Integer available;

	private String availability;

	@JsonProperty("availability_days")
	private Integer availabilityDays;

	private String reference;

	private Integer hot;

	private Integer release;

	@JsonProperty("additional_button")
	private String additionalButton;

	@JsonProperty("related_categories")
	private String[] relatedCategories;

	@JsonProperty("release_date")
	private Date releaseDate;

	@JsonProperty("picture_source_1")
	private String pictureSource1;

	@JsonProperty("picture_source_2")
	private String pictureSource2;

	@JsonProperty("picture_source_3")
	private String pictureSource3;

	@JsonProperty("picture_source_4")
	private String pictureSource4;

	@JsonProperty("picture_source_5")
	private String pictureSource5;

	@JsonProperty("picture_source_6")
	private String pictureSource6;

	private Metatag[] metatag;

	@JsonProperty("virtual_product")
	private String virtualProduct;
	
	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(OrcTrail orc) {
		super();
		this.name = orc.getDescricaoSimples();
		this.description = orc.getDescricaoCompleta();
		this.descriptionSmall = "Forma Farmaceutica:" + orc.getFormaFarmaceutica() + "- Tratamento: " + orc.getTratamento() +/* " - Quantidade: "  + orc.getQuantidade() + " - Unidade: " + orc.getUnidade() + */" - Cód.Filial: " + orc.getCodFilial() + " - Data Entrada: " + MethodsUtils.formatarDataString(orc.getDataEntrada(), ConstantsUtils.DATE_FORMAT_DD_MM_YYYY_BAR) + " - Cód.Cliente: " + (orc.getCodCliente() != null ? orc.getCodCliente() : "") + " - Cód. Forma Farmacêutica: " + orc.getCodFormaFarmaceutica() + " - Cód. Funcionário: " + orc.getCodFuncionario();
		this.price = orc.getPreco();
		this.costPrice = orc.getPreco();
		this.promotionalPrice = orc.getPrecoOferta();
		this.brand = orc.getMarca();
		this.model = orc.getModelo();
		this.weight = 1000;
		this.length = orc.getComprimento().intValue();
		this.width = orc.getLargura().intValue();
		this.height = orc.getAltura().intValue();
		// TODO: quantidade nao esta definido
		this.stock = 1;//orc.getQuantidade().intValue();
		this.categoryId = ConstantsUtils.ID_CATEGORIA_TRAY;
		this.available = orc.getDisponivel().equals("SIM") ? 1 : 0;
		this.availability = "3";
		this.availabilityDays = 3;
		this.reference = orc.getNumOrcamento().toString();
		this.startPromotion = new Date();
		this.endPromotion = MethodsUtils.addDaysInDate(new Date(), 30);
		this.pictureSource1 = ConstantsUtils.URL_REPOSITORIO_IMAGENS; // Concatenar o tipo de forma farmaceutica. + orc.getCodFormaFarmaceutica() + ".jpg"
	}
	
	public ProdutoDTO(OrcamentoN8N orc) {
		super();
		this.name = orc.getDescricaoSimples();
		this.description = orc.getDescricaoCompleta();
		this.descriptionSmall = "Forma Farmaceutica:" + orc.getFormaFarmaceutica() + "- Tratamento: " + orc.getTratamento() +/* " - Quantidade: "  + orc.getQuantidade() + " - Unidade: " + orc.getUnidade() + */" - Cód.Filial: " + orc.getCodFilial() + " - Data Entrada: " + MethodsUtils.formatarDataString(orc.getDataEntrada(), ConstantsUtils.DATE_FORMAT_DD_MM_YYYY_BAR) + " - Cód.Cliente: " + (orc.getCodCliente() != null ? orc.getCodCliente() : "") + " - Cód. Forma Farmacêutica: " + orc.getCodFormaFarmaceutica() + " - Cód. Funcionário: " + orc.getCodFuncionario();
		this.price = orc.getPreco();
		this.costPrice = orc.getPreco();
		this.promotionalPrice = orc.getPrecoOferta();
		this.brand = orc.getMarca();
		this.model = orc.getModelo();
		this.weight = 1000;
		this.length = orc.getComprimento().intValue();
		this.width = orc.getLargura().intValue();
		this.height = orc.getAltura().intValue();
		// TODO: quantidade nao esta definido
		this.stock = 1;//orc.getQuantidade().intValue();
		this.categoryId = ConstantsUtils.ID_CATEGORIA_TRAY;
		this.available = orc.getDisponivel().equals("SIM") ? 1 : 0;
		this.availability = "3";
		this.availabilityDays = 3;
		this.reference = orc.getNumOrcamento().toString();
		this.startPromotion = new Date();
		this.endPromotion = MethodsUtils.addDaysInDate(new Date(), 30);
		this.pictureSource1 = ConstantsUtils.URL_REPOSITORIO_IMAGENS; // Concatenar o tipo de forma farmaceutica. + orc.getCodFormaFarmaceutica() + ".jpg"
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionSmall() {
		return descriptionSmall;
	}

	public void setDescriptionSmall(String descriptionSmall) {
		this.descriptionSmall = descriptionSmall;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getPromotionalPrice() {
		return promotionalPrice;
	}

	public void setPromotionalPrice(BigDecimal promotionalPrice) {
		this.promotionalPrice = promotionalPrice;
	}

	public Date getStartPromotion() {
		return startPromotion;
	}

	public void setStartPromotion(Date startPromotion) {
		this.startPromotion = startPromotion;
	}

	public Date getEndPromotion() {
		return endPromotion;
	}

	public void setEndPromotion(Date endPromotion) {
		this.endPromotion = endPromotion;
	}

	public BigDecimal getIpiValue() {
		return ipiValue;
	}

	public void setIpiValue(BigDecimal ipiValue) {
		this.ipiValue = ipiValue;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Integer getAvailabilityDays() {
		return availabilityDays;
	}

	public void setAvailabilityDays(Integer availabilityDays) {
		this.availabilityDays = availabilityDays;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getRelease() {
		return release;
	}

	public void setRelease(Integer release) {
		this.release = release;
	}

	public String getAdditionalButton() {
		return additionalButton;
	}

	public void setAdditionalButton(String additionalButton) {
		this.additionalButton = additionalButton;
	}

	
	public String[] getRelatedCategories() {
		return relatedCategories;
	}

	public void setRelatedCategories(String[] relatedCategories) {
		this.relatedCategories = relatedCategories;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPictureSource1() {
		return pictureSource1;
	}

	public void setPictureSource1(String pictureSource1) {
		this.pictureSource1 = pictureSource1;
	}

	public String getPictureSource2() {
		return pictureSource2;
	}

	public void setPictureSource2(String pictureSource2) {
		this.pictureSource2 = pictureSource2;
	}

	public String getPictureSource3() {
		return pictureSource3;
	}

	public void setPictureSource3(String pictureSource3) {
		this.pictureSource3 = pictureSource3;
	}

	public String getPictureSource4() {
		return pictureSource4;
	}

	public void setPictureSource4(String pictureSource4) {
		this.pictureSource4 = pictureSource4;
	}

	public String getPictureSource5() {
		return pictureSource5;
	}

	public void setPictureSource5(String pictureSource5) {
		this.pictureSource5 = pictureSource5;
	}

	public String getPictureSource6() {
		return pictureSource6;
	}

	public void setPictureSource6(String pictureSource6) {
		this.pictureSource6 = pictureSource6;
	}

	public Metatag[] getMetatag() {
		return metatag;
	}

	public void setMetatag(Metatag[] metatag) {
		this.metatag = metatag;
	}

	public String getVirtualProduct() {
		return virtualProduct;
	}

	public void setVirtualProduct(String virtualProduct) {
		this.virtualProduct = virtualProduct;
	}

}
