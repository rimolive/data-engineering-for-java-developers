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