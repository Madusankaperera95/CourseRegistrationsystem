package lk.cmjd.coursework.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.cmjd.coursework.dto.CourseDto;
import lk.cmjd.coursework.dto.EnrollmentDto;
import lk.cmjd.coursework.dto.StudentDto;
import lk.cmjd.coursework.service.ServiceFactory;
import lk.cmjd.coursework.service.custom.CourseService;
import lk.cmjd.coursework.service.custom.EnrollmentService;
import lk.cmjd.coursework.util.Enums.SemesterTypes;
import lk.cmjd.coursework.util.Enums.Status;
import lk.cmjd.coursework.util.WordsConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    public AnchorPane coursePane;

    private CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.COURSE);

    private EnrollmentService enrollmentService = (EnrollmentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ENROLLMENT);

    private StudentDto studentObj;

    @FXML
    private AnchorPane EnrollmentsPane;

    @FXML
    private AnchorPane academicRecordsPane;

    @FXML
    private StackPane contentArea;

    @FXML
    private Text courseId;

    @FXML
    private AnchorPane homePane;

    @FXML private TextField searchField;
    @FXML private TilePane cardContainer;

    @FXML
    public Label courseNameLbl;

    @FXML
    public Label courseDurationLbl;

    @FXML
    public TextArea courseDescriptionLbl;

    @FXML
    public ComboBox<String> semester;

    @FXML
    private TableColumn<EnrollmentDto,String> enrollment_id_col;

    @FXML
    private TableColumn<EnrollmentDto,String> enroll_course_name_col;

    @FXML
    private TableColumn<EnrollmentDto,String> semester_col;

    @FXML
    private TableColumn<EnrollmentDto,String> status_type_col;

    @FXML
    private TableColumn<EnrollmentDto,String> gpa_col;

    @FXML
    private TableColumn<EnrollmentDto,String> grade_col;

    @FXML
    private TableColumn<EnrollmentDto,Void> action_col;

    @FXML
    public TableView<EnrollmentDto> enrollTable;


    private final SemesterTypes[] semesterTypes = SemesterTypes.values();




    private List<CourseDto> courses = courseService.getAll();

    public StudentController() throws Exception {
    }

    public StudentDto getStudentObj() {
        return studentObj;
    }

    public void setStudentObj(StudentDto studentObj) {
        this.studentObj = studentObj;
    }

    @FXML
    void switchForm(ActionEvent event) {
        String userData = ((Button) event.getSource()).getUserData().toString();

        coursePane.setVisible(false);
        homePane.setVisible(userData.equals("allcourses"));
        EnrollmentsPane.setVisible(userData.equals("Enrollments"));
        academicRecordsPane.setVisible(userData.equals("AcademmicRecords"));
        if(userData.equals("Enrollments")){
            displayOrRefreshEnrollmentTable();
        }
    }

    @FXML
    void registerForCourse(ActionEvent event) throws Exception {
        System.out.println(courseId.getText());
        System.out.println(semester.getValue());

        SemesterTypes semesterType = null;
        if(semester.getValue().equals("First Semester")){
             semesterType = SemesterTypes.FIRSTSEMESTER;
        }

        if(semester.getValue().equals("Second Semester")){
            semesterType = SemesterTypes.SECONDSEMESTER;
        }

        String Output= enrollmentService.save(new EnrollmentDto(courseId.getText(),semesterType, Status.ONGOING, getStudentObj().getStudentId(),0,""));
        if(!Output.equals("ok")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Course Registration Error");
            alert.setHeaderText(null);
            alert.setContentText(Output);
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createCards();
        String semesters[] = new String[semesterTypes.length];
        for(int i = 0; i< semesters.length;i++){
            semesters[i] = getRealValue(semesterTypes[i]);
        }

        semester.setItems(FXCollections.observableArrayList(semesters));
        // Add search listener
        searchField.textProperty().addListener((obs, oldVal, newVal) -> filterCards(newVal));

        Platform.runLater(() -> {
            if (EnrollmentsPane.isVisible()) {

            }
        });

    }

    private String getRealValue(SemesterTypes val) {
        if (val == null) {
            return "Unknown"; // Handle null case safely
        }

        switch (val) {
            case FIRSTSEMESTER:
                return "First Semester";
            case SECONDSEMESTER:
                return "Second Semester";
            default:
                return val.name(); // Return the enum name if no match
        }
    }

    private void createCards() {
        cardContainer.getChildren().clear();
        for (CourseDto course : courses) {
            VBox card = createCard(course);
            cardContainer.getChildren().add(card);
        }
    }

    private VBox createCard(CourseDto course) {
        VBox card = new VBox(10);
        card.getStyleClass().add("card");
        card.setUserData(course);

        // Ensure a fixed size to align with prefTileWidth
        card.setPrefSize(250, 100);

        Label title = new Label(course.getCourseName());
        title.getStyleClass().add("title");

//        Label desc = new Label(item.getDescription());
//        desc.getStyleClass().add("desc");
//        desc.setWrapText(true);
        card.setOnMouseClicked(event -> handleCardClick(course));
        card.getChildren().addAll(title);
        return card;
    }

    private void handleCardClick(CourseDto course) {
        System.out.println("Course Selected: " + course.toString());
        System.out.println("Student:"+getStudentObj().toString());
       courseNameLbl.setText(course.getCourseName());
       courseDurationLbl.setText(course.getCreditHours());
       courseDescriptionLbl.setText(course.getDescription());
        homePane.setVisible(false);
        coursePane.setVisible(true);
        courseId.setText(course.getCourseId());
        // You can add more functionality like opening details, navigating to another page, etc.
    }

    private void filterCards(String query) {
        String searchText = query.toLowerCase();
        for (Node card : cardContainer.getChildren()) {
            CourseDto item = (CourseDto) card.getUserData();
            boolean match = item.getCourseName().toLowerCase().contains(searchText);
            card.setVisible(match);
            card.setManaged(match);
        }
    }

    private void displayOrRefreshEnrollmentTable() {
        enrollment_id_col.setCellValueFactory(new PropertyValueFactory<>("enrollId"));
        enroll_course_name_col.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        semester_col.setCellValueFactory(cellData -> {
            String semester = String.valueOf(cellData.getValue().getSemester());
            return new SimpleStringProperty(WordsConverter.getRealValue(semester));
        });
        gpa_col.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        grade_col.setCellValueFactory(new PropertyValueFactory<>("grade"));
        status_type_col.setCellValueFactory(cellData -> {
            String semester = String.valueOf(cellData.getValue().getStatus());
            return new SimpleStringProperty(WordsConverter.getRealValue(semester));
        });

        action_col.setCellFactory(param -> new TableCell<EnrollmentDto, Void>() {
            private final Button dropCourseBtn = new Button("Drop Course");

            {
                dropCourseBtn.setStyle("-fx-background-color: #9f0f0f; " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 5px 10px; ");

                dropCourseBtn.setOnAction(event -> {
                    EnrollmentDto enrollment = getTableView().getItems().get(getIndex());
                    dropCourse(enrollment);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(dropCourseBtn);
                }
            }
        });


        try {
            enrollTable.setItems(getStudentEnrollmentList());
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }




    private ObservableList<EnrollmentDto> getStudentEnrollmentList() throws Exception {
        ObservableList<EnrollmentDto> enrollments = FXCollections.observableArrayList();
        System.out.println(getStudentObj().toString());
        ArrayList<EnrollmentDto> enrollmentDtos = enrollmentService.getEnrollmentsByStudentId(getStudentObj().getStudentId());
        for(EnrollmentDto enrollmentDto : enrollmentDtos){
            enrollments.add(enrollmentDto);
        }

        return enrollments;
    }

    private void dropCourse(EnrollmentDto enrollment) {
        String resp = enrollmentService.deleteEnrollment(Integer.parseInt(enrollment.getEnrollId()));
        displayOrRefreshEnrollmentTable();

    }


}


