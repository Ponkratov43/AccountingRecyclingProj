package sample.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DB.DBManager;
import sample.Entity.Price;
import sample.Entity.Total;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MainController {

    private static final String ADD =
            "INSERT INTO total VALUES (null, CURDATE(), ?,?,?,?,?,?,?,?,?);";

    private static final String RESULT_COW =
            "select count(*) from total";

    private static final String SELECT_TOTAL =
            "select * from total;";

    private static final String UPDATE =
            "UPDATE total set sbl = sbl + ?, aluminium = aluminium + ?, copper = copper + ?, brass = brass + ?, " +
                    "glass = glass + ?, paper = paper + ?, radiators = radiators + ?, accumulators = accumulators + ?, sum = sum + ?;";
    private static final String SUBTRACT =
            "UPDATE total set sbl = sbl - ?, aluminium = aluminium - ?, copper = copper - ?, brass = brass - ?, " +
                    "glass = glass - ?, paper = paper - ?, radiators = radiators - ?, accumulators = accumulators - ?, sum = sum - ?;";


    private String res;

    public Button addButton;
    public Button enterButton;
    public Button showButton;

    public TextField sblField;
    public TextField copperField;
    public TextField aluminiumField;
    public TextField brassField;
    public TextField batteryField;
    public TextField radiatorField;
    public TextField paperField;
    public TextField glassField;

    private double sbl;
    private double copper;
    private double aluminium;
    private double brass;
    private double battery;
    private double radiator;
    private double paper;
    private double glass;
    private double sum;

    Connection connection = DBManager.getConnection();
    Price price = new Price();

    //open setting window
    @FXML
    public void settingButtonClicked() {
        addButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/SettingFXML.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/view/SettingFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    public void showButtonClicked() throws SQLException {
        showButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/ShowFXML.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/view/ShowFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Table");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();

        ShowController showController = new ShowController();
        showController.populateTableView();
    }


    public void setAll() throws SQLException {
        setSbl();
        setCopper();
        setAluminium();
        setBrass();
        setGlass();
        setPaper();
        setRadiator();
        setBattery();
        price.selectPrice();
        setSum();
        printTotal();


    }

    public boolean checkAvailabilitySettings() throws SQLException {
        setAll();
        if (price.getId() == 0)
            return false;
        else
            return true;
    }

    public void alert (){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful!");
        alert.setHeaderText(null);
        alert.setContentText("Всего: " + "\n" + res);
        alert.showAndWait();
    }
    //  enter and save
    @FXML
    public void addButtonClicked() throws SQLException {

        if (checkAvailabilitySettings()) {

            try {
                if (enterExists()) {
                    updateOrSubtract(UPDATE);
                } else {
                    setRes();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            res = "СБЛ: " + getSbl() + "\n" + "Аллюминий: " + getAluminium() + "\n" +
                    "Медь: " + getCopper() + "\n" + "Латунь: " + getBrass() + "\n" +
                    "Стекло: " + getGlass() + "\n" + "Бумага: " + getPaper() + "\n" +
                    "Радиторы: " + getRadiator() + "\n" + "Аккамуляторы: " + getBattery() + "\n" + "\n" + "К выплате: " + getSum();

            alert();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Настройки не выставлены.");
            alert.showAndWait();
        }
    }

    @FXML
    public void subtractButtonClicked() throws SQLException {

        setAll();

        try {
            if (enterExists()) {
                updateOrSubtract(SUBTRACT);

                res = "СБЛ: " + getSbl() + "\n" + "Аллюминий: " + getAluminium() + "\n" +
                        "Медь: " + getCopper() + "\n" + "Латунь: " + getBrass() + "\n" +
                        "Стекло: " + getGlass() + "\n" + "Бумага: " + getPaper() + "\n" +
                        "Радиторы: " + getRadiator() + "\n" + "Аккамуляторы: " + getBattery() + "\n" + "\n" + "Вычтено: " + getSum();
                alert();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Нет ни одной записи.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateOrSubtract(String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, getSbl());
            statement.setDouble(2, getAluminium());
            statement.setDouble(3, getCopper());
            statement.setDouble(4, getBrass());
            statement.setDouble(5, getGlass());
            statement.setDouble(6, getPaper());
            statement.setDouble(7, getRadiator());
            statement.setDouble(8, getBattery());
            statement.setDouble(9, getSum());
            statement.execute();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }


    public void setRes() {
        try {
            PreparedStatement statement = connection.prepareStatement(ADD);
            statement.setDouble(1, getSbl());
            statement.setDouble(2, getAluminium());
            statement.setDouble(3, getCopper());
            statement.setDouble(4, getBrass());
            statement.setDouble(5, getGlass());
            statement.setDouble(6, getPaper());
            statement.setDouble(7, getRadiator());
            statement.setDouble(8, getBattery());
            statement.setDouble(9, getSum());
            statement.execute();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    public boolean enterExists() throws SQLException {

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(RESULT_COW);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        System.out.println(count);
        return count == 1;
    }

    public List<Total> selectTotal() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_TOTAL);
        List<Total> resultList = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            Date date = resultSet.getDate(2);
            double sbl = resultSet.getDouble(3);
            double aluminium = resultSet.getDouble(4);
            double copper = resultSet.getDouble(5);
            double brass = resultSet.getDouble(6);
            double glass = resultSet.getDouble(7);
            double paper = resultSet.getDouble(8);
            double radiators = resultSet.getDouble(9);
            double accumulators = resultSet.getDouble(10);
            Total total = new Total(id, date, sbl, aluminium, copper, brass, glass, paper, radiators, accumulators);

            resultList.add(total);
        }
        return resultList;
    }


    public void printTotal() throws SQLException {

        selectTotal().forEach(System.out::println);
    }


    public double getSbl() {
        return sbl;
    }

    public void setSbl() {
        this.sbl = sblField.getText().isEmpty() ? 0 : Double.parseDouble(sblField.getText().trim());
    }

    public double getCopper() {
        return copper;
    }

    public void setCopper() {
        this.copper = copperField.getText().isEmpty() ? 0 : Double.parseDouble(copperField.getText().trim());
    }

    public double getAluminium() {
        return aluminium;
    }

    public void setAluminium() {
        this.aluminium = aluminiumField.getText().isEmpty() ? 0 : Double.parseDouble(aluminiumField.getText().trim());
    }

    public double getBrass() {
        return brass;
    }

    public void setBrass() {
        this.brass = brassField.getText().isEmpty() ? 0 : Double.parseDouble(brassField.getText().trim());
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery() {
        this.battery = batteryField.getText().isEmpty() ? 0 : Double.parseDouble(batteryField.getText().trim());
    }

    public double getRadiator() {
        return radiator;
    }

    public void setRadiator() {
        this.radiator = radiatorField.getText().isEmpty() ? 0 : Double.parseDouble(radiatorField.getText().trim());
    }

    public double getPaper() {
        return paper;
    }

    public void setPaper() {
        this.paper = paperField.getText().isEmpty() ? 0 : Double.parseDouble(paperField.getText().trim());
    }

    public double getGlass() {
        return glass;
    }

    public void setGlass() {
        this.glass = glassField.getText().isEmpty() ? 0 : Double.parseDouble(glassField.getText().trim());
    }

    public double getSum() {
        return sum;
    }

    public void setSum() {
        this.sum = (getSbl() * price.getSbl_price()) + (getCopper() * price.getCopper_price()) +
                (getAluminium() * price.getAluminium_price()) + (getBrass() * price.getBrass_price()) +
                (getGlass() * price.getGlass_price()) + (getPaper() * price.getPaper_price()) +
                (getBattery() * price.getAccumulators_price()) + (getRadiator() * price.getRadiators_price());
    }
}
