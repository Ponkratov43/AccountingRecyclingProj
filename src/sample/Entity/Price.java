package sample.Entity;

import sample.DB.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Price {

    private static final String SELECT_SETTING =
            "select * from setting";

    Connection connection = DBManager.getConnection();

    private double id;
    private double sbl_price;
    private double aluminium_price;
    private double copper_price;
    private double brass_price;
    private double glass_price;
    private double paper_price;
    private double radiators_price;
    private double accumulators_price;

    public Price(double id, double sbl_price, double aluminium_price, double copper_price, double brass_price, double glass_price, double paper_price, double radiators_price, double accumulators_price) {
        this.id = id;
        this.sbl_price = sbl_price;
        this.aluminium_price = aluminium_price;
        this.copper_price = copper_price;
        this.brass_price = brass_price;
        this.glass_price = glass_price;
        this.paper_price = paper_price;
        this.radiators_price = radiators_price;
        this.accumulators_price = accumulators_price;
    }

    public Price() {
    }

    public  List<Price> selectPrice () throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_SETTING);
        List<Price> resultList = new ArrayList<>();

        while (resultSet.next()) {
             id = resultSet.getInt(1);
             sbl_price = resultSet.getDouble(2);
            aluminium_price = resultSet.getDouble(3);
            copper_price = resultSet.getDouble(4);
            brass_price = resultSet.getDouble(5);
            glass_price = resultSet.getDouble(6);
            paper_price = resultSet.getDouble(7);
            radiators_price = resultSet.getDouble(8);
            accumulators_price = resultSet.getDouble(9);
            Price price = new Price(id, sbl_price, aluminium_price, copper_price, brass_price,
                    glass_price, paper_price, radiators_price, accumulators_price);
            resultList.add(price);
        }
        return resultList;
    }
    public double getId() {
        return id;
    }
    public double getSbl_price() {
        return sbl_price;
    }
    public double getAluminium_price() {
        return aluminium_price;
    }
    public double getCopper_price() {
        return copper_price;
    }
    public double getBrass_price() {
        return brass_price;
    }
    public double getGlass_price() {
        return glass_price;
    }
    public double getPaper_price() {
        return paper_price;
    }

    public double getRadiators_price() {
        return radiators_price;
    }
    public double getAccumulators_price() {
        return accumulators_price;
    }


    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", sbl_price=" + sbl_price +
                ", aluminium_price=" + aluminium_price +
                ", copper_price=" + copper_price +
                ", brass_price=" + brass_price +
                ", glass_price=" + glass_price +
                ", paper_price=" + paper_price +
                ", radiators_price=" + radiators_price +
                ", accumulators_price=" + accumulators_price +
                '}';
    }
}

