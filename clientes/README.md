
```shell
docker-compose up -d
```

```shell
./gradlew registerSchemasTask
```

```shell
curl --silent -X GET http://localhost:8081/subjects/cliente/versions/1 | jq .
```

```shell
./gradlew generateAvroJava
```

```shell
./gradlew bootRun
```

```shell
http :8080/clientes nome=Humberto documento=010
```

```shell
docker-compose exec schema-registry kafka-avro-console-consumer --from-beginning --topic clientes --bootstrap-server broker:29092
```