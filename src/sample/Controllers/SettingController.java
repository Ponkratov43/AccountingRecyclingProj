package sample.Controllers;



import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.DB.DBManager;
import sample.Entity.Price;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SettingController {


    private static final String SET_SETTING_QUERY =
            "INSERT INTO setting VALUES (null, ?,?,?,?,?,?,?,?);";

    private static final String RESULT_COW =
            "select count(*) from setting";

    private static final String SELECT =
            "select * from setting";

    private static final String UPDATE_SETTING_QUERY =
            "update setting set sbl=?, aluminium=?, copper=?, brass=?, glass=?, paper=?, radiators=?, accumulators=?;";


    public TextField sblFieldSetting;
    public TextField copperFieldSetting;
    public TextField aluminiumFieldSetting;
    public TextField brassFieldSetting;
    public TextField batteryFieldSetting;
    public TextField radiatorFieldSetting;
    public TextField paperFieldSetting;
    public TextField glassFieldSetting;

    private double sblPrice;
    private double copperPrice;
    private double aluminiumPrice;
    private double brassPrice;
    private double batteryPrice;
    private double radiatorPrice;
    private double paperPrice;
    private double glassPrice;
    public Button saveButtonSetting;

    public Connection connection = DBManager.getConnection();

    @FXML
    public void saveButtonSetting() {
        IOService ioService = new IOService();
try {
    setSblPrice();
    setCopperPrice();
    setAluminiumPrice();
    setBrassPrice();
    setGlassPrice();
    setPaperPrice();
    setRadiatorPrice();
    setBatteryPrice();

    selectSetting();
    ioService.printAllSetting();
    ioService.alert();
}catch (Exception e) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image(this.getClass().getResource("/sample/view/resources/error.png").toString()));
    alert.setTitle("Error!");
    alert.setHeaderText(null);
    alert.setContentText("Заполните все поля.");
    alert.showAndWait();
}

        try {
            if (settingExists()) {
                updateSetting();
            } else {
                setSetting();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<Price> selectSetting() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT);
        List<Price> resultList = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            double sbl = resultSet.getDouble(2);
            double aluminium = resultSet.getDouble(3);
            double copper = resultSet.getDouble(4);
            double brass = resultSet.getDouble(5);
            double glass = resultSet.getDouble(6);
            double paper = resultSet.getDouble(7);
            double radiators = resultSet.getDouble(8);
            double accumulators = resultSet.getDouble(9);
            Price price = new Price(id, sbl, aluminium, copper, brass, glass, paper, radiators, accumulators);

            resultList.add(price);
        }
        return resultList;
    }


    public boolean settingExists() throws SQLException {

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(RESULT_COW);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count == 1;
    }


    public void updateSetting() throws SQLException {
        PreparedStatement statement1 = connection.prepareStatement(UPDATE_SETTING_QUERY);
        statement1.setDouble(1, getSblPrice());
        statement1.setDouble(2, getAluminiumPrice());
        statement1.setDouble(3, getCopperPrice());
        statement1.setDouble(4, getBrassPrice());
        statement1.setDouble(5, getGlassPrice());
        statement1.setDouble(6, getPaperPrice());
        statement1.setDouble(7, getRadiatorPrice());
        statement1.setDouble(8, getBatteryPrice());
        statement1.execute();
    }

    public void setSetting() {
        try {
            PreparedStatement statement = connection.prepareStatement(SET_SETTING_QUERY);
            statement.setDouble(1, getSblPrice());
            statement.setDouble(2, getAluminiumPrice());
            statement.setDouble(3, getCopperPrice());
            statement.setDouble(4, getBrassPrice());
            statement.setDouble(5, getGlassPrice());
            statement.setDouble(6, getPaperPrice());
            statement.setDouble(7, getRadiatorPrice());
            statement.setDouble(8, getBatteryPrice());
            statement.execute();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

    }

    public class IOService {

        public void alert() {
            String resMessage = "СБЛ: " + getSblPrice() + "\n" + "Аллюминий: " + getAluminiumPrice() + "\n" +
                    "Медь: : " + getCopperPrice() + "\n" + "Латунь: " + getBrassPrice() + "\n" +
                    "Стекло: " + getGlassPrice() + "\n" + "Бумага: " + getPaperPrice() + "\n" +
                    "Аккамулятор: " + getBatteryPrice() + "\n" + "Радиатор: " + getRadiatorPrice();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/sample/view/resources/ok.png").toString()));
            alert.setTitle("Successful!");
            alert.setHeaderText(null);
            alert.setContentText(resMessage);
            alert.showAndWait();
        }


        public void printAllSetting() throws SQLException {

            selectSetting().forEach(System.out::println);
        }
    }

    public double getSblPrice() {
        return sblPrice;
    }

    public double getCopperPrice() {
        return copperPrice;
    }

    public double getAluminiumPrice() {
        return aluminiumPrice;
    }

    public double getBrassPrice() {
        return brassPrice;
    }

    public double getBatteryPrice() {
        return batteryPrice;
    }

    public double getRadiatorPrice() {
        return radiatorPrice;
    }

    public double getPaperPrice() {
        return paperPrice;
    }

    public double getGlassPrice() {
        return glassPrice;
    }

    public void setSblPrice() {
        this.sblPrice = Double.parseDouble(sblFieldSetting.getText().trim());
    }

    public void setCopperPrice() {
        this.copperPrice = Double.parseDouble(copperFieldSetting.getText().trim());
    }

    public void setAluminiumPrice() {
        this.aluminiumPrice = Double.parseDouble(aluminiumFieldSetting.getText().trim());
    }

    public void setBrassPrice() {
        this.brassPrice = Double.parseDouble(brassFieldSetting.getText().trim());
    }

    public void setBatteryPrice() {
        this.batteryPrice = Double.parseDouble(batteryFieldSetting.getText().trim());
    }

    public void setRadiatorPrice() {
        this.radiatorPrice = Double.parseDouble(radiatorFieldSetting.getText().trim());
    }

    public void setPaperPrice() {
        this.paperPrice = Double.parseDouble(paperFieldSetting.getText().trim());
    }

    public void setGlassPrice() {
        this.glassPrice = Double.parseDouble(glassFieldSetting.getText().trim());
    }
}
