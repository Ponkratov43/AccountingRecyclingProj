package sample.Entity;

import java.util.Date;

public class Total {

    private double id;
    private Date date;
    private double sbl;
    private double aluminium;
    private double copper;
    private double brass;
    private double glass;
    private double paper;
    private double radiators;
    private double accumulators;

    public Total(double id, Date date, double sbl, double aluminium, double copper, double brass, double glass, double paper, double radiators, double accumulators) {
        this.id = id;
        this.date = date;
        this.sbl = sbl;
        this.aluminium = aluminium;
        this.copper = copper;
        this.brass = brass;
        this.glass = glass;
        this.paper = paper;
        this.radiators = radiators;
        this.accumulators = accumulators;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void Date (Date date) {
        this.date = date;
    }

    public double getSbl() {
        return sbl;
    }

    public void setSbl(double sbl) {
        this.sbl = sbl;
    }

    public double getAluminium() {
        return aluminium;
    }

    public void setAluminium(double aluminium) {
        this.aluminium = aluminium;
    }

    public double getCopper() {
        return copper;
    }

    public void setCopper(double copper) {
        this.copper = copper;
    }

    public double getBrass() {
        return brass;
    }

    public void setBrass(double brass) {
        this.brass = brass;
    }

    public double getGlass() {
        return glass;
    }

    public void setGlass(double glass) {
        this.glass = glass;
    }

    public double getPaper() {
        return paper;
    }

    public void setPaper(double paper) {
        this.paper = paper;
    }

    public double getRadiators() {
        return radiators;
    }

    public void setRadiators(double radiators) {
        this.radiators = radiators;
    }

    public double getAccumulators() {
        return accumulators;
    }

    public void setAccumulators(double accumulators) {
        this.accumulators = accumulators;
    }

    @Override
    public String toString() {
        return "Total{" +
                "id=" + id +
                ", date=" + date +
                ", sbl=" + sbl +
                ", aluminium=" + aluminium +
                ", copper=" + copper +
                ", brass=" + brass +
                ", glass=" + glass +
                ", paper=" + paper +
                ", radiators=" + radiators +
                ", accumulators=" + accumulators +
                '}';
    }
}
