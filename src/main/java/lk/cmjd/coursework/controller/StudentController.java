package lk.cmjd.coursework.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
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
import lk.cmjd.coursework.service.custom.StudentService;
import lk.cmjd.coursework.util.Enums.SemesterTypes;
import lk.cmjd.coursework.util.Enums.Status;

import java.net.URL;
import java.util.Arrays;
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
    public ComboBox<SemesterTypes> semester;


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
    }

    @FXML
    void registerForCourse(ActionEvent event) throws Exception {
        System.out.println(courseId.getText());
        System.out.println(semester.getValue());
        String Output= enrollmentService.save(new EnrollmentDto(courseId.getText(),semester.getValue(), Status.ONGOING, getStudentObj().getStudentId()));
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
        semester.setItems(FXCollections.observableArrayList(semesterTypes));
        // Add search listener
        searchField.textProperty().addListener((obs, oldVal, newVal) -> filterCards(newVal));
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


}
