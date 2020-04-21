package sample.Entity;

import javafx.beans.property.*;

import java.sql.Date;

public class Table {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty date = new SimpleStringProperty();
    private DoubleProperty sbl = new SimpleDoubleProperty();
    private DoubleProperty aluminium = new SimpleDoubleProperty();
    private DoubleProperty brass = new SimpleDoubleProperty();
    private DoubleProperty copper = new SimpleDoubleProperty();
    private DoubleProperty paper = new SimpleDoubleProperty();
    private DoubleProperty glass = new SimpleDoubleProperty();
    private DoubleProperty radiators = new SimpleDoubleProperty();
    private DoubleProperty accumulators = new SimpleDoubleProperty();
    private DoubleProperty sum = new SimpleDoubleProperty();

    public Table(IntegerProperty id, StringProperty date, DoubleProperty sbl, DoubleProperty aluminium, DoubleProperty brass, DoubleProperty copper, 
                 DoubleProperty paper, DoubleProperty glass, DoubleProperty radiators, DoubleProperty accumulators, DoubleProperty sum) {
        this.id = id;
        this.date = date;
        this.sbl = sbl;
        this.aluminium = aluminium;
        this.brass = brass;
        this.copper = copper;
        this.paper = paper;
        this.glass = glass;
        this.radiators = radiators;
        this.accumulators = accumulators;
        this.sum = sum;
    }

    public Table() {
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }


    public void setDate(Date date) {
        this.date.set(String.valueOf(date));
    }

    public double getSbl() {
        return sbl.get();
    }

    public DoubleProperty sblProperty() {
        return sbl;
    }

    public void setSbl(double sbl) {
        this.sbl.set(sbl);
    }

    public double getAluminium() {
        return aluminium.get();
    }

    public DoubleProperty aluminiumProperty() {
        return aluminium;
    }

    public void setAluminium(double aluminium) {
        this.aluminium.set(aluminium);
    }

    public double getBrass() {
        return brass.get();
    }

    public DoubleProperty brassProperty() {
        return brass;
    }

    public void setBrass(double brass) {
        this.brass.set(brass);
    }

    public double getCopper() {
        return copper.get();
    }

    public DoubleProperty copperProperty() {
        return copper;
    }

    public void setCopper(double copper) {
        this.copper.set(copper);
    }

    public double getPaper() {
        return paper.get();
    }

    public DoubleProperty paperProperty() {
        return paper;
    }

    public void setPaper(double paper) {
        this.paper.set(paper);
    }

    public double getGlass() {
        return glass.get();
    }

    public DoubleProperty glassProperty() {
        return glass;
    }

    public void setGlass(double glass) {
        this.glass.set(glass);
    }

    public double getRadiators() {
        return radiators.get();
    }

    public DoubleProperty radiatorsProperty() {
        return radiators;
    }

    public void setRadiators(double radiators) {
        this.radiators.set(radiators);
    }

    public double getAccumulators() {
        return accumulators.get();
    }

    public DoubleProperty accumulatorsProperty() {
        return accumulators;
    }

    public void setAccumulators(double accumulators) {
        this.accumulators.set(accumulators);
    }

    public double getSum() {
        return sum.get();
    }

    public DoubleProperty sumProperty() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", date=" + date +
                ", sbl=" + sbl +
                ", aluminium=" + aluminium +
                ", brass=" + brass +
                ", copper=" + copper +
                ", paper=" + paper +
                ", glass=" + glass +
                ", radiators=" + radiators +
                ", accumulators=" + accumulators +
                '}';
    }
}



