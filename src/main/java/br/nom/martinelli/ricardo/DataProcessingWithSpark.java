package br.nom.martinelli.ricardo;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class DataProcessingWithSpark {

    private static String DATASET_LOCAL_PATH = System.getProperty("user.dir") + "/data/us-elections.csv";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                            .set("spark.driver.memory", "4g")
                            .set("spark.driver.cores", "2")
                            .set("spark.eventLog.enabled", "true")
                            .set("spark.eventLog.dir", System.getProperty("user.dir") + "/logs/");

        SparkSession spark = SparkSession
                                .builder()
                                .master("local[*]")
                                .appName("Data Processing with Spark")
                                .config(conf)
                                .getOrCreate();
        Dataset<String> usElections = spark.read().textFile(DATASET_LOCAL_PATH).cache();

        usElections.printSchema();

        spark.close();
    }
    
}
