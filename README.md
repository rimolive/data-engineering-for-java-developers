# Data Engineering for Java developers

This repo contains examples in Java for developers who want to know how to manipulate data. It was part of a talk named `Data engineering for Java Developers (or vice versa)`

## Run the example using Stream API

```
$ mvn clean compile
$ mvn exec:java -Dexec.mainClass="br.nom.martinelli.ricardo.DataProcessingWithStreams"
```

## Run the example using Spark

```
$ mvn clean compile
$ mvn exec:java -Dexec.mainClass="br.nom.martinelli.ricardo.DataProcessingWithSpark"
```

## Run the example using Cassandra

```
$ mvn clean compile
$ mvn exec:java -Dexec.mainClass="br.nom.martinelli.ricardo.DataProcessingWithCassandra"
```

## Run the example using Zeppelin

```
$ podman run -u $(id -u) -p 8080:8080 --rm -v $PWD/logs:/logs -v $PWD/notebooks:/notebook:U -v $PWD:/data:/data:U \
  -e ZEPPELIN_LOG_DIR='/logs' -e ZEPPELIN_NOTEBOOK_DIR='/notebook' --name zeppelin apache/zeppelin:0.10.0
```