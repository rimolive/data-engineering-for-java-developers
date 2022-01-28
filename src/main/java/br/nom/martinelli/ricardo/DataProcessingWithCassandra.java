package br.nom.martinelli.ricardo;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class DataProcessingWithCassandra {

    private static String DATASET_REMOTE_PATH = "https://rimolive.fedorapeople.org/us-elections.csv";
    private static String DATASET_LOCAL_PATH = System.getProperty("user.dir") + "/data/us-elections.csv";

    private Cluster cluster;

    private Session session;

    public void connect(String node, Integer port) {
        Builder b = Cluster.builder()
                        .addContactPoint(node)
                        .withoutJMXReporting();
        if (port != null) {
            b.withPort(port);
        }

        cluster = b.build();

        session = cluster.connect();
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }

    public static void main(String args[]) {
        DataProcessingWithCassandra connector = new DataProcessingWithCassandra();
        connector.connect("127.0.0.1", null);
        Session session = connector.getSession();

        StringBuilder createKeyspace = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                    .append("uselections WITH replication = {'class':'SimpleStrategy','replication_factor':1};");
        session.execute(createKeyspace.toString());

        StringBuilder createTable = new StringBuilder("CREATE TABLE IF NOT EXISTS uselections.uselections(")
                    .append("id uuid PRIMARY KEY,")
                    .append("fips_code varchar,")
                    .append("fips_class varchar,")
                    .append("state_code varchar,")
                    .append("state_name varchar,")
                    .append("capital varchar,")
                    .append("county_name varchar,")
                    .append("party varchar,")
                    .append("total_votes_2008 bigint,")
                    .append("total_votes_2012 bigint,")
                    .append("total_votes_2016 bigint);");
        session.execute(createTable.toString());

        StringBuilder insertTable = new StringBuilder("INSERT INTO uselections.uselections(")
                    .append("id,")
                    .append("fips_code,")
                    .append("fips_class,")
                    .append("state_code,")
                    .append("state_name,")
                    .append("capital,")
                    .append("county_name,")
                    .append("party,")
                    .append("total_votes_2008,")
                    .append("total_votes_2012,")
                    .append("total_votes_2016)")
                    .append(" VALUES(uuid(),'','','','','','','dem',123456,0,0);");
        session.execute(insertTable.toString());

        StringBuilder insertCsv = new StringBuilder("COPY uselections.uselections")
                    .append(" FROM 'data/us-elections.csv' ")
                    .append(" WITH DELIMITER=',' ")
                    .append(" AND HEADER=TRUE ");
        session.execute(insertCsv.toString());

        // StringBuilder selectTable = new StringBuilder("SELECT * FROM uselections.uselections;");
        // ResultSet rs = session.execute(selectTable.toString());
        // for (Row r : rs) {
        //     System.out.print(r.getUUID("id"));
        //     System.out.print("\t");
        //     System.out.print(r.getString("party"));
        //     System.out.print("\t");
        //     System.out.print(r.getDecimal("total_votes_2008"));
        //     System.out.print("\n");
        // }

        //StringBuilder dropKeyspace = new StringBuilder("DROP KEYSPACE uselections;");
        //session.execute(dropKeyspace.toString());

        connector.close();
    }
}