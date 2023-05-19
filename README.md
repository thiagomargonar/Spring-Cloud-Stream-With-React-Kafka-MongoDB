## Spring-Cloud-Stream-With-React-Kafka-MongoDB
### Projeto iniciado de forma simples apenas para consumir um servico REST, 
com a requisição, enviara uma mensagem para o kafka
e recebera essa mesma mensagem. as informações serão salvas no banco(MongoDB).

A ideia é apenas praticas e utilizar alguns patterns.

## Execução do projeto.
### Obrigatório 
1) Docker.
2) Java 17.

### Docker.
1) Criar arquivo docker-compose.yml em uma pasta de preferencia.
2) Salvar conteudo abaixo no arquivo docker-sompose criado.
   
<html>
<p>
#inicio</br>
version: '3' </br>
   services:</br>
   mongo:</br>
   image: mongo</br>
   environment:</br>
    - MONGO_INITDB_ROOT_USERNAME=root</br>
    - MONGO_INITDB_ROOT_PASSWORD=rootpassword</br>
    - MONGO_INITDB_DATABASE=project</br>
      ports:</br>
    - "27017:27017"</br>
      zookeeper:</br>
      image: confluentinc/cp-zookeeper:latest</br>
      environment:</br>
      ZOOKEEPER_CLIENT_PORT: 2181</br>
      ZOOKEEPER_TICK_TIME: 2000</br>
      ports:</br>
    - 2181:2181</br>
      networks:</br>
    - kafka-cluster-teste</br>
      kafka:</br>
      image: confluentinc/cp-kafka:latest</br>
      depends_on:</br>
    - zookeeper</br>
      ports:</br>
    - 9092:9092</br>
      environment:</br>
   KAFKA_BROKER_ID: 1</br>
   KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181</br>
   KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:29092,PLAINTEXT_HOST://localhost:9092</br>
   KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT</br>
   KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT</br>
   KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1</br>
   networks:</br>
    - kafka-cluster-teste</br>
      networks:</br>
      kafka-cluster-teste:</br>
      driver: bridge</br>
#fim</br>
</p>
</html>

3) Feito isso basta abrir o pront da sua maquina, navegar até a pasta do arquivo e digitar o comando "docker-compose-up"
