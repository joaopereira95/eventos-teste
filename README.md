# Event Manager

Para executar, certifique-se de que as portas **3000**, **8080**, **5672**, **15672** e **3306** não estejam sendo utilizadas por nenhum outro processo.

## Comandos para execução:
git clone https://github.com/joaopereira95/eventos-teste.git
cd eventos-teste/
git checkout docker
docker-compose up

### URL de acesso a página web: 
http://localhost:3000/

## Descrição dos componentes:
**event-manager-ui:** interface para inclusão e listagem dos eventos cadastrados.
**event-manager-api:** serviço para inclusão e listagem dos eventos cadastrados.
**event-manager-scheduling:** componente para recebimento e processamento das mensagens recebidas pelo RabbitMQ e rotina automática para atualização dos status dos eventos cadastrados.
