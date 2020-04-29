package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.DB.DBManager;
import sample.Entity.Table;
import java.sql.*;
import java.util.Date;


public class ShowController {

    private final static String SHOW_QUERY = "SELECT * FROM total;";
    private final static String DELETE_TABLE = "delete from total where id > -1";


    public Button deleteTable;

    @FXML
    private TableView<Table> tableView;
    @FXML
    private TableColumn<Table, Integer> colId;
    @FXML
    private TableColumn<Table, Date> colDate;
    @FXML
    private TableColumn<Table, Double> colSbl;
    @FXML
    private TableColumn<Table, Double> colAluminium;
    @FXML
    private TableColumn<Table, Double> colBrass;
    @FXML
    private TableColumn<Table, Double> colCopper;
    @FXML
    private TableColumn<Table, Double> colPaper;
    @FXML
    private TableColumn<Table, Double> colGlass;
    @FXML
    private TableColumn<Table, Double> colRadiators;
    @FXML
    private TableColumn<Table, Double> colAccumulators;
    @FXML
    private TableColumn<Table, Double> colSum;

    public Connection connection;
    private ObservableList<Table> list = FXCollections.observableArrayList();



    public ObservableList<Table> init() throws SQLException {
        connection = DBManager.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SHOW_QUERY);

        while (resultSet.next()) {
            Table table = new Table();
            table.setId(resultSet.getInt("id"));
            table.setDate(resultSet.getDate("date"));
            table.setSbl(resultSet.getDouble("sbl"));
            table.setAluminium(resultSet.getDouble("aluminium"));
            table.setCopper(resultSet.getDouble("copper"));
            table.setBrass(resultSet.getDouble("brass"));
            table.setGlass(resultSet.getDouble("glass"));
            table.setPaper(resultSet.getDouble("paper"));
            table.setRadiators(resultSet.getDouble("radiators"));
            table.setAccumulators(resultSet.getDouble("accumulators"));
            table.setSum(resultSet.getDouble("sum"));
            list.add(table);
        }
        return list;
    }
    public void populateTableView() throws NullPointerException {

        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSbl.setCellValueFactory(new PropertyValueFactory<>("sbl"));
        colAluminium.setCellValueFactory(new PropertyValueFactory<>("aluminium"));
        colCopper.setCellValueFactory(new PropertyValueFactory<>("copper"));
        colBrass.setCellValueFactory(new PropertyValueFactory< >("brass"));
        colGlass.setCellValueFactory(new PropertyValueFactory<>("glass"));
        colPaper.setCellValueFactory(new PropertyValueFactory<>("paper"));
        colRadiators.setCellValueFactory(new PropertyValueFactory<>("radiators"));
        colAccumulators.setCellValueFactory(new PropertyValueFactory<>("accumulators"));
        colSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        tableView.setItems(list);
}

    @FXML
   public void showTable() {
        tableView.getItems().clear();
        populateTableView();
    }
    @FXML
    public void deleteTable() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute(DELETE_TABLE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/sample/view/resources/ok.png").toString()));
        alert.setTitle("Successful!");
        alert.setHeaderText(null);
        alert.setContentText("Таблица успешно удалена.");
        alert.showAndWait();
    }

}

