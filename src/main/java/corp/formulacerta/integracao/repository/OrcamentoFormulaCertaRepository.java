package corp.formulacerta.integracao.repository;

import java.util.Date;
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
	@Query(value = "SELECT PRODUTO.DESCRPRD AS descricaoSimples, produto.DESCR AS descricaoCompleta, ORCAMENTO .CDFIL AS codFilial, orcamento.VRRQU AS preco, (orcamento.VRRQU - orcamento.VRDSC) AS precoOferta, orcamento.nrorc AS numOrcamento, orcamento.DTENTR AS dataEntrada, orcamento.CDCLI AS codCliente, detalhe.QUANT AS quantidade, detalhe.UNIDA AS unidade, FUNCIONARIO.NOMEFUN AS nomeFuncionario, terceira.cdfunre AS codFuncionario, terceira.serieo AS serie,CASE terceira.TPFORMAFARMA WHEN '1' THEN 'CAPSULAS' WHEN '2' THEN 'CREMES' WHEN '3' THEN 'LOCOES' WHEN '4' THEN 'SHAMPOO' WHEN '5' THEN 'DIVERSAS' WHEN '6' THEN 'UNIDADES' WHEN '7' THEN 'HOMEOPATIA' WHEN '8' THEN 'FLORAL' WHEN '9' THEN 'COMPRIMIDOS' WHEN '10' THEN 'GEL' WHEN '11' THEN 'POMADA' WHEN '12' THEN 'XAROPE' WHEN '13' THEN 'FILTRO SOLAR' WHEN '14' THEN 'INJETAVEIS' WHEN '15' THEN 'ENVELOPES' WHEN '16' THEN 'BISCOITOS' WHEN '17' THEN 'PASTILHAS' WHEN '18' THEN 'PATCH GEL' WHEN '19' THEN 'FILMES' WHEN '20' THEN 'PASTA ORAL' WHEN '21' THEN 'SOLUCAO ORAL' END AS formaFarmaceutica, CASE WHEN terceira.PFCRM = 2 THEN 'USO VETERINARIO' ELSE 'USO HUMANO' END AS tratamento, TERCEIRA.TPFORMAFARMA AS codFormaFarmaceutica, 'SIM' AS disponivel, 'Orçamento Manipulado' AS modelo, 'Attive Care' AS marca, 16 AS comprimento, 11 AS LARGURA, 2 AS ALTURA, 1 AS estoqueAtual, 'Orçamento Manipulado' AS categoria FROM fc15000 orcamento INNER JOIN fc15110 detalhe ON (ORCAMENTO.nrorc = DETALHE.nrorc) INNER JOIN fc15100 terceira ON (ORCAMENTO.nrorc = TERCEIRA.nrorc) INNER JOIN fc03000 PRODUTO ON (PRODUTO.CDPRO = detalhe.CDPRIN) LEFT JOIN FC08000 funcionario ON (terceira.CDFUNRE = FUNCIONARIO.cdfun) WHERE orcamento.nrorc > :last ORDER BY ORCAMENTO.nrorc", nativeQuery = true)
	List<OrcamentoInterfaceDTO> findOrcamentoBiggerThanCustomID(@Param("last") Integer lastId);
	
//	@Query(value = "SELECT ORCAMENTO.CDFIL AS codFilial, terceira.PRCOBR AS preco, terceira.prreal AS precoOferta, orcamento.nrorc AS numOrcamento, CAST (terceira.DTCAD||' '||terceira.HRCAD AS TIMESTAMP) AS dataCadastro, orcamento.DTENTR AS dataEntrada, orcamento.CDCLI AS codCliente, FUNCIONARIO.NOMEFUN AS nomeFuncionario, terceira.cdfunre AS codFuncionario, terceira.serieo AS serie, CASE terceira.TPFORMAFARMA WHEN '1' THEN 'CAPSULAS' WHEN '2' THEN 'CREMES' WHEN '3' THEN 'LOCOES' WHEN '4' THEN 'SHAMPOO' WHEN '5' THEN 'DIVERSAS' WHEN '6' THEN 'UNIDADES' WHEN '7' THEN 'HOMEOPATIA' WHEN '8' THEN 'FLORAL' WHEN '9' THEN 'COMPRIMIDOS' WHEN '10' THEN 'GEL' WHEN '11' THEN 'POMADA' WHEN '12' THEN 'XAROPE' WHEN '13' THEN 'FILTRO SOLAR' WHEN '14' THEN 'INJETAVEIS' WHEN '15' THEN 'ENVELOPES' WHEN '16' THEN 'BISCOITOS' WHEN '17' THEN 'PASTILHAS' WHEN '18' THEN 'PATCH GEL' WHEN '19' THEN 'FILMES' WHEN '20' THEN 'PASTA ORAL' WHEN '21' THEN 'SOLUCAO ORAL' END AS formaFarmaceutica, CASE WHEN terceira.PFCRM = 2 THEN 'USO VETERINARIO' ELSE 'USO HUMANO' END AS tratamento, TERCEIRA.TPFORMAFARMA AS codFormaFarmaceutica, 'SIM' AS disponivel, 'Orçamento Manipulado' AS modelo, 'Attive Care' AS marca, 16 AS comprimento, 11 AS LARGURA, 2 AS ALTURA, 1 AS estoqueAtual, 'Orçamento Manipulado' AS categoria FROM fc15000 orcamento INNER JOIN fc15100 terceira ON (ORCAMENTO.nrorc = TERCEIRA.nrorc) LEFT JOIN FC08000 funcionario ON (terceira.CDFUNRE = FUNCIONARIO.cdfun) WHERE CAST (terceira.DTCAD||' '||terceira.HRCAD AS TIMESTAMP) > :data ORDER BY (terceira.DTCAD||' '||terceira.HRCAD) DESC", nativeQuery = true)
//	List<OrcamentoInterfaceDTO> findOrcamentoByLastDataCadastro(@Param("data") Date data);

	@Query(value = "SELECT PRODUTO.DESCRPRD AS descricaoSimples, produto.DESCR AS descricaoCompleta, ORCAMENTO .CDFIL AS codFilial, orcamento.VRRQU AS preco, (orcamento.VRRQU - orcamento.VRDSC) AS precoOferta, orcamento.nrorc AS numOrcamento, orcamento.DTENTR AS dataEntrada, orcamento.CDCLI AS codCliente, detalhe.QUANT AS quantidade, detalhe.UNIDA AS unidade, FUNCIONARIO.NOMEFUN AS nomeFuncionario, terceira.cdfunre AS codFuncionario, terceira.serieo AS serie,CASE terceira.TPFORMAFARMA WHEN '1' THEN 'CAPSULAS' WHEN '2' THEN 'CREMES' WHEN '3' THEN 'LOCOES' WHEN '4' THEN 'SHAMPOO' WHEN '5' THEN 'DIVERSAS' WHEN '6' THEN 'UNIDADES' WHEN '7' THEN 'HOMEOPATIA' WHEN '8' THEN 'FLORAL' WHEN '9' THEN 'COMPRIMIDOS' WHEN '10' THEN 'GEL' WHEN '11' THEN 'POMADA' WHEN '12' THEN 'XAROPE' WHEN '13' THEN 'FILTRO SOLAR' WHEN '14' THEN 'INJETAVEIS' WHEN '15' THEN 'ENVELOPES' WHEN '16' THEN 'BISCOITOS' WHEN '17' THEN 'PASTILHAS' WHEN '18' THEN 'PATCH GEL' WHEN '19' THEN 'FILMES' WHEN '20' THEN 'PASTA ORAL' WHEN '21' THEN 'SOLUCAO ORAL' END AS formaFarmaceutica, CASE WHEN terceira.PFCRM = 2 THEN 'USO VETERINARIO' ELSE 'USO HUMANO' END AS tratamento, TERCEIRA.TPFORMAFARMA AS codFormaFarmaceutica, 'SIM' AS disponivel, 'Orçamento Manipulado' AS modelo, 'Attive Care' AS marca, 16 AS comprimento, 11 AS LARGURA, 2 AS ALTURA, 1 AS estoqueAtual, 'Orçamento Manipulado' AS categoria FROM fc15000 orcamento INNER JOIN fc15110 detalhe ON (ORCAMENTO.nrorc = DETALHE.nrorc) INNER JOIN fc15100 terceira ON (ORCAMENTO.nrorc = TERCEIRA.nrorc) INNER JOIN fc03000 PRODUTO ON (PRODUTO.CDPRO = detalhe.CDPRIN) LEFT JOIN FC08000 funcionario ON (terceira.CDFUNRE = FUNCIONARIO.cdfun) WHERE orcamento.dtentr = :data ORDER BY ORCAMENTO.nrorc", nativeQuery = true)
	List<OrcamentoInterfaceDTO> findOrcamentoByDataEntrada(@Param("data") String data);
	
	@Query(value = "SELECT OC.CDFIL AS codFilial, OC.NRORC AS numOrcamento, OC.SERIEO AS serie, CAST (OC.DTENTR||' '||OC.HRCAD AS TIMESTAMP) AS dataEntrada, oc.QTAPROV AS qtAprov, OC.CDFUNRE AS codFuncionario, FN.NOMEFUN AS nomeFuncionario, OC.PRCOBR AS preco, OC.prreal AS precoOferta, CASE OC.TPFORMAFARMA WHEN '1' THEN 'CAPSULAS' WHEN '2' THEN 'CREMES' WHEN '3' THEN 'LOCOES' WHEN '4' THEN 'SHAMPOO' WHEN '5' THEN 'DIVERSAS' WHEN '6' THEN 'UNIDADES' WHEN '7' THEN 'HOMEOPATIA' WHEN '8' THEN 'FLORAL' WHEN '9' THEN 'COMPRIMIDOS' WHEN '10' THEN 'GEL' WHEN '11' THEN 'POMADA' WHEN '12' THEN 'XAROPE' WHEN '13' THEN 'FILTRO SOLAR' WHEN '14' THEN 'INJETAVEIS' WHEN '15' THEN 'ENVELOPES' WHEN '16' THEN 'BISCOITOS' WHEN '17' THEN 'PASTILHAS' WHEN '18' THEN 'PATCH GEL' WHEN '19' THEN 'FILMES' WHEN '20' THEN 'PASTA ORAL' WHEN '21' THEN 'SOLUCAO ORAL' END AS formaFarmaceutica, CASE WHEN OC.PFCRM = 2 THEN 'USO VETERINARIO' ELSE 'USO HUMANO' END as tratamento, OC.TPFORMAFARMA AS codFormaFarmaceutica, 'SIM' AS disponivel, 'Orçamento Manipulado' AS modelo, 'Attive Care' AS marca, 16 AS comprimento, 11 AS LARGURA, 2 AS ALTURA, 1 AS estoqueAtual, 'Orçamento Manipulado' AS categoria FROM FC15100 OC INNER JOIN FC08000 FN ON (FN.CDFUN = OC.CDFUNRE AND FN.CDCON = OC.CDCONRE) WHERE OC.CDFIL = :filial AND (OC.DTENTR ||' '||OC.HRCAD) > :data ORDER BY (OC.DTENTR ||' '||OC.HRCAD)", nativeQuery = true)
	List<OrcamentoInterfaceDTO> findOrcamentoByLastDataCadastro(@Param("data") String data, @Param("filial") Integer filial);
	
	@Query(value = "SELECT PRODUTO.DESCRPRD AS descricaoSimples, produto.DESCR AS descricaoCompleta, ORCAMENTO .CDFIL AS codFilial, orcamento.VRRQU AS preco, (orcamento.VRRQU - orcamento.VRDSC) AS precoOferta, orcamento.nrorc AS numOrcamento, orcamento.DTENTR AS dataEntrada, orcamento.CDCLI AS codCliente, detalhe.QUANT AS quantidade, detalhe.UNIDA AS unidade, FUNCIONARIO.NOMEFUN AS nomeFuncionario, terceira.cdfunre AS codFuncionario, terceira.serieo AS serie, CASE terceira.TPFORMAFARMA WHEN '1' THEN 'CAPSULAS' WHEN '2' THEN 'CREMES' WHEN '3' THEN 'LOCOES' WHEN '4' THEN 'SHAMPOO' WHEN '5' THEN 'DIVERSAS' WHEN '6' THEN 'UNIDADES' WHEN '7' THEN 'HOMEOPATIA' WHEN '8' THEN 'FLORAL' WHEN '9' THEN 'COMPRIMIDOS' WHEN '10' THEN 'GEL' WHEN '11' THEN 'POMADA' WHEN '12' THEN 'XAROPE' WHEN '13' THEN 'FILTRO SOLAR' WHEN '14' THEN 'INJETAVEIS' WHEN '15' THEN 'ENVELOPES' WHEN '16' THEN 'BISCOITOS' WHEN '17' THEN 'PASTILHAS' WHEN '18' THEN 'PATCH GEL' WHEN '19' THEN 'FILMES' WHEN '20' THEN 'PASTA ORAL' WHEN '21' THEN 'SOLUCAO ORAL' END AS formaFarmaceutica, CASE WHEN terceira.PFCRM = 2 THEN 'USO VETERINARIO' ELSE 'USO HUMANO' END AS tratamento, TERCEIRA.TPFORMAFARMA AS codFormaFarmaceutica, 'SIM' AS disponivel, 'Orçamento Manipulado' AS modelo, 'Attive Care' AS marca, 16 AS comprimento, 11 AS LARGURA, 2 AS ALTURA, 1 AS estoqueAtual, 'Orçamento Manipulado' AS categoria FROM fc15000 orcamento INNER JOIN fc15110 detalhe ON (ORCAMENTO.nrorc = DETALHE.nrorc) INNER JOIN fc15100 terceira ON (ORCAMENTO.nrorc = TERCEIRA.nrorc) INNER JOIN fc03000 PRODUTO ON (PRODUTO.CDPRO = detalhe.CDPRIN) LEFT JOIN FC08000 funcionario ON (terceira.CDFUNRE = FUNCIONARIO.cdfun) WHERE orcamento.nrorc in (:nrOrcs) ORDER BY ORCAMENTO.nrorc", nativeQuery = true)
	List<OrcamentoInterfaceDTO> findOrcamentoByNrOrcs(@Param("nrOrcs") List<Integer> nrOrcs);
	
	@Query(value = "SELECT NRORC FROM FC15000 WHERE DTENTR = :data",nativeQuery=true)
	List<Integer> findNrOrcsPorData(@Param("data") String data);	
	
	@Query(value = "SELECT FIRST 1 'a' FROM fc15000",nativeQuery = true)
	String teste();

	@Query(value = "SELECT (DESCR||' '||CAST(QUANT as int)||' '||UNIDA) AS x FROM fc15110 WHERE NRORC = :orc AND cdfil = :codFilial AND SERIEO = :serie", nativeQuery = true)
	List<String> buscarSubstanciasDoOrcamento(@Param("orc") Integer numOrc, @Param("codFilial") Integer codFilial,@Param("serie") String serie);
	
	@Query(value = "SELECT COUNT(*) FROM FC15100 OC WHERE OC.NRORC = :orc AND  OC.CDFIL = :codFilial AND oc.serieo = :serie AND OC.QTAPROV = 1",nativeQuery = true)
	Integer buscarOrcamentoAprovado(@Param("orc") Integer numOrc, @Param("codFilial") Integer codFilial,@Param("serie") String serie);
	
}
