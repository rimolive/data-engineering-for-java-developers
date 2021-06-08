package br.nom.martinelli.ricardo;

public class UsElectionsItem {

    public Integer lineNumber;
    public String fipsCode;
    public String fipsClass;
    public String stateCode;
    public String stateName;
    public String capital;
    public String countyName;
    public String party;
    public Integer totalVotes2008;
    public Integer totalVotes2012;
    public Integer totalVotes2016;

    public UsElectionsItem(String... args) {
        this.lineNumber = Integer.valueOf(args[0].replaceAll("\"", ""));
        this.fipsCode = args[1].replaceAll("\"", "");
        this.fipsClass = args[2].replaceAll("\"", "");
        this.stateCode = args[3].replaceAll("\"", "");
        this.stateName = args[4].replaceAll("\"", "");
        this.capital = args[5].replaceAll("\"", "");
        this.countyName = args[6].replaceAll("\"", "");
        this.party = args[7].replaceAll("\"", "");
        this.totalVotes2008 = Integer.valueOf(args[8].replaceAll("\"", ""));
        this.totalVotes2012 = Integer.valueOf(args[9].replaceAll("\"", ""));
        this.totalVotes2016 = Integer.valueOf(args[0].replaceAll("\"", ""));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
            .append(this.lineNumber)
            .append(",")
            .append(this.fipsCode)
            .append(",")
            .append(this.fipsClass)
            .append(",")
            .append(this.stateCode)
            .append(",")
            .append(this.stateName)
            .append(",")
            .append(this.capital)
            .append(",")
            .append(this.countyName)
            .append(",")
            .append(this.party)
            .append(",")
            .append(this.totalVotes2008)
            .append(",")
            .append(this.totalVotes2012)
            .append(",")
            .append(this.totalVotes2016)
            .append("\n");

        return sb.toString();
    }
    
}
