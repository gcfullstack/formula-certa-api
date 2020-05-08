package corp.gruposfa.novo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "servicoIntegracaoFeignClient", url = "${application.url-apis.url-servico-parametros-integracao}")
public interface ServicoIntegracaoFeignClient {
	
	 @RequestMapping(method = RequestMethod.PUT, value="/inicio/{id}")
	 void salvarInicioDeExecucaoDeServico(@RequestParam("id") Integer idServico);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/fim/{id}")
	 void salvarFimDeExecucaoDeServico(@RequestParam("id") Integer idServico);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/alterar-status-exec/servico/{id}/status/{status}")
	 Integer alterarStatusExecucaoJob(@RequestParam("id") Integer idServico,@RequestParam("status") Boolean status);

	 @RequestMapping(method = RequestMethod.GET, value="/status-exec-job/servico/{id}")
	 String verificarStatusExecucaoJob(@RequestParam("id") Integer idServico);

}
