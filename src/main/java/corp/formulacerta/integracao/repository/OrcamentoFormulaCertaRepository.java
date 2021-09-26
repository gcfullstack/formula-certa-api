package corp.formulacerta.integracao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import corp.formulacerta.integracao.model.OrcamentoFormulaCerta;
import corp.formulacerta.integracao.model.dto.OrcamentoDTO.OrcamentoInterfaceDTO;

@Repository
public interface OrcamentoFormulaCertaRepository extends JpaRepository<OrcamentoFormulaCerta, Integer> {
	/**
	 * descricaoSimples
	 * descricaoCompleta
	 * codFilial
	 * preco
	 * precoOferta
	 * numOrcamento
	 * dataEntrada
	 * codCliente
	 * quantidade
	 * unidade
	 * codFuncionario
	 * formaFarmaceutica
	 * tratamento
	 * codFormaFarmaceutica
	 * disponivel
	 * modelo
	 * marca
	 * comprimento
	 * largura
	 * altura
	 * estoqueAtual
	 * categoria
	 * @return
	 */
	@Query(value = "SELECT first 1 PRODUTO.DESCRPRD AS descricaoSimples, produto.DESCR AS descricaoCompleta, ORCAMENTO .CDFIL AS codFilial, orcamento.VRRQU AS preco, (orcamento.VRRQU - orcamento.VRDSC) AS precoOferta, orcamento.nrorc AS numOrcamento, orcamento.DTENTR AS dataEntrada, orcamento.CDCLI AS codCliente, detalhe.QUANT AS quantidade, detalhe.UNIDA AS unidade, terceira.cdfunre AS codFuncionario, case terceira.TPFORMAFARMA WHEN '1' THEN 'CAPSULAS' WHEN '2' THEN 'CREMES' WHEN '3' THEN 'LOCOES' WHEN '4' THEN 'SHAMPOO' WHEN '5' THEN 'DIVERSAS' WHEN '6' THEN 'UNIDADES' WHEN '7' THEN 'HOMEOPATIA' WHEN '8' THEN 'FLORAL' WHEN '9' THEN 'COMPRIMIDOS' WHEN '10' THEN 'GEL' WHEN '11' THEN 'POMADA' WHEN '12' THEN 'XAROPE' WHEN '13' THEN 'FILTRO SOLAR' WHEN '14' THEN 'INJETAVEIS' WHEN '15' THEN 'ENVELOPES' WHEN '16' THEN 'BISCOITOS' WHEN '17' THEN 'PASTILHAS' WHEN '18' THEN 'PATCH GEL' WHEN '19' THEN 'FILMES' WHEN '20' THEN 'PASTA ORAL' WHEN '21' THEN 'SOLUCAO ORAL' end AS formaFarmaceutica, case when terceira.PFCRM = 2 then 'USO VETERINARIO' ELSE 'USO HUMANO' END AS tratamento, TERCEIRA.TPFORMAFARMA AS codFormaFarmaceutica, 'SIM' AS disponivel, 'Orçamento Manipulado' AS modelo, 'Attive Pharma' AS marca, 16 AS comprimento, 11 AS LARGURA, 2 AS ALTURA, 1 AS estoqueAtual, 'Orçamento Manipulado' AS categoria FROM fc15000 orcamento INNER JOIN fc15110 detalhe ON (ORCAMENTO.nrorc = DETALHE.nrorc) INNER JOIN fc15100 terceira ON (ORCAMENTO.nrorc = TERCEIRA.nrorc) inner join fc03000 PRODUTO ON (PRODUTO.CDPRO = detalhe.CDPRIN) where orcamento.nrorc > :last ORDER BY ORCAMENTO.nrorc", nativeQuery = true)
	List<OrcamentoInterfaceDTO> findOrcamentoBiggerThanCustomID(@Param("last") Integer lastId);
	
	@Query(value = "SELECT FIRST 1 'a' FROM fc15000",nativeQuery = true)
	String teste();

}
