# formula-certa-api
Compilar o projeto: mvn clean install

Após acessar a pasta target:
nohup java -jar api-formulacerta.jar &

Acessar console:
tail -f nohup.out

Visualizar processos em execução
ps -aux

---

<b>GET</b><br />
http://localhost:8080/api/integrar
<br /><br />
Alterar:<br />
<i>localhost:8080</i> pelo endereço do servidor

<b>Headers</b><br />
<b>Key:</b> Authorization<br />
<b>Value:</b> Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJKV1QgQXR0aXZlIiwiaWF0IjoxNjQxNTE3NjAyLCJleHAiOjE4OTM5Nzk2MzAsImF1ZCI6ImFwaS5mb3JtdWxhY2VydGEiLCJzdWIiOiJmb3JtdWxhY2VydGEiLCJHaXZlbk5hbWUiOiJGb3JtdWxhIENlcnRhIn0.6IkschU0vldjNpg4GES-0WmRVeizzVSvA7uJxF9sPupMNEY03sgFfG0gM5iSwJJjWZoW-qyVwhFFZt3yOI2JCw

	
	  Etapas do processo de importação e integração de orçamentos
	 
	  1 - É realizada uma consulta no banco do mongo, para buscar todos os orçamentos que ainda não foram integrados na Tray.
	  As condições da consulta é que o registro tenha o campo "idtray" igual a nulo e que a data de cadastro seja maior que a data atual subtraída de 3 dias.
	  Esta regra foi criada para que exista um limite de data para que o sistema tente integrar os orçamentos retardatários	 
	  Se algum registro for encontrado, ele será validado no banco do formula certa (firebird), afim de saber se esse orçamento ja foi aprovado.
	  Caso positivo, o orçamento sera integrado na Tray e o campo  "idtray" sera preenchido e atualizado no banco.
	  Caso negativo, não possui ação.
	  
	  2 - É realizada uma busca no mongo para buscar o ultimo log de execução salvo, pois ele contém a data de cadastro do ultimo orçamento importado.
	  Essa, servirá de referência para buscar os proximos orçamentos.
	  
	  3 - É realizada uma validação para verificar se a data de cadastro do orçamento mais recente cadastrado no banco é menor do que 
	  um determinado numero de dias, o qual é utilizado para definir um limite maximo de dias que o sistema ira permitir importar novos orçamentos.
	  Essa regra foi criada para o caso do sistema ficar muitos dias sem executar e quando for executar, acontecer de existir milhares de registros para serem
	  importados de uma vez.
	  Caso a diferença de dias seja maior do que a data limite, o sistema irá criar uma data padrao que é equivalente ao dia atual às 00:00:00hrs.
	  
	  4 - É realizada uma busca no banco do formula certa para verificar se existem registros(orçamentos) que possuam o campo data de cadastro
	  maior do que a data de cadastro (lastDataCadastro campo do banco) do registro encontrado na etapa 3
	  Os registros que forem encontrados serão integrados na tray, caso algum dos produtos que constituem o orçamento, esteja aprovado.
	  Essa regra foi criada desta forma, pois o banco do formula certa nao informa se um determinado produto do orçamento esta aprovado ou não,
	  ele possui apenas a quantidade de produtos aprovados.
	  
	  5 - No final um log é criado e salvo no banco, com a data de cadastro do orçamento mais recente encontrado. Para que ela sirva de referencia
	   para buscar os proximos orçamentos
     
     
     


Realizar o deploy: <br/>
cd //<br/>
rm -r api-formula-certa<br/>
mkdir api-formula-certa<br/>
cd api-formula-certa	<br/>
git clone --branch hml  https://github.com/gcfullstack/formula-certa-api<br/>
cd formula-certa-api<br/>
mvn clean install -Pprod<br/>
cd target<br/>
ps -aux<br/>
kill ${PID ID}<br/>
nohup java -jar api-formulacerta.jar &<br/>
tail -f nohup.out<br/>


	 
