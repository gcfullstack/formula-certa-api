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

