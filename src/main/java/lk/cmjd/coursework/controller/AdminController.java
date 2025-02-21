package lk.cmjd.coursework.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lk.cmjd.coursework.dto.*;
import lk.cmjd.coursework.service.ServiceFactory;
import lk.cmjd.coursework.service.custom.*;
import lk.cmjd.coursework.util.WordsConverter;

import java.util.ArrayList;
import java.util.List;

public class AdminController {


    private CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.COURSE);

    private DepartmentService departmentService = (DepartmentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.DEPARTMENT);

    private StudentService studentService =(StudentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STUDENT);

    private PreRequisiteService preRequisiteService = (PreRequisiteService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.PREREQUISITE);

    @FXML
    private ComboBox<PreRequisiteDto> PreRequists;

    @FXML
    private StackPane contentArea;

    @FXML
    private Label courseCount;

    @FXML
    private TextField courseId;

    @FXML
    private TextField courseTitle;

    @FXML
    public TextArea courseDescription;

    @FXML
    public AnchorPane gpaUpatePane;

    @FXML
    private AnchorPane coursesPane;

    @FXML
    private AnchorPane searchAnchorPane;


    @FXML
    private TableView<CourseDto> coursesTable;

    @FXML
    private TextField creditHours;

    @FXML
    private TextField studentId;

    @FXML
    public TextField studentEmail;

    @FXML
    private TextField studentName;

    @FXML
    private TextField searchField;

    @FXML
    private TextField studentProgram;

    @FXML
    private DatePicker studentdob;
    @FXML
    public TextField studentyear;

    @FXML
    public TextField contactNumber;

    @FXML
    private ComboBox<DepartmentDto> department;

    @FXML
    private AnchorPane homePane;

    @FXML
    private TextField max_capacity;

    @FXML
    private Label studentCount;


    @FXML
    private AnchorPane studentsPane;


    @FXML
    private Label totalCount;

    @FXML
    private TableColumn<CourseDto, Integer> course_credit_hours_col;

    @FXML
    private TableColumn<CourseDto, String> course_department_col;

    @FXML
    private TableColumn<CourseDto, String> course_id_col;

    @FXML
    private TableColumn<CourseDto, Integer> course_max_capacity_col;

    @FXML
    private TableColumn<CourseDto, String> course_prerequistes_col;

    @FXML
    public TableColumn<CourseDto, String> course_description_col;


    @FXML
    private TableColumn<CourseDto, String> course_title_col;
    @FXML
    public TableColumn<StudentDto,String> student_id_col;

    @FXML
    private TableColumn<StudentDto,String> student_dob_col;

    @FXML
    private TableColumn<StudentDto,String> student_email_col;

    @FXML
    private TableColumn<StudentDto,String> student_name_col;

    @FXML
    private TableColumn<StudentDto,String> student_program_col;

    @FXML
    private TableColumn<StudentDto,String> student_year_col;

    @FXML
    private TableColumn<StudentDto,String> student_contact_col;


    @FXML
    private TableView<StudentDto> studentsTable;

    @FXML
    private TableView<EnrollmentDto> enrollmentTable;

    @FXML
    private  Label enrollId;



    public void initialize() throws Exception {
        // Load departments (example data)
        List<DepartmentDto> departments = departmentService.getAll();
        List<PreRequisiteDto> prerequisites = preRequisiteService.getAll();


        // Bind the list to the ComboBox
        department.setItems(FXCollections.observableArrayList(departments));

        department.setCellFactory(param -> new ListCell<DepartmentDto>() {
            @Override
            protected void updateItem(DepartmentDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        });

        department.setButtonCell(new ListCell<DepartmentDto>() {
            @Override
            protected void updateItem(DepartmentDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        });

        PreRequists.setItems(FXCollections.observableArrayList(prerequisites));
        PreRequists.setCellFactory(param -> new ListCell<PreRequisiteDto>() {
            @Override
            protected void updateItem(PreRequisiteDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getRequisiteName());
            }
        });

        PreRequists.setButtonCell(new ListCell<PreRequisiteDto>() {
            @Override
            protected void updateItem(PreRequisiteDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getRequisiteName());
            }
        });



        courseCount.setText("15");
        studentCount.setText("250");
        totalCount.setText("265");

        coursesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });

        studentsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateStudentFields(newSelection);
            }
        });

        displayOrRefreshCourseTable();
        displayOrRefreshStudentsTable();
    }

    private void populateStudentFields(StudentDto selectedStudentDto){
        studentId.setText(selectedStudentDto.getStudentId());
        studentName.setText(selectedStudentDto.getStudentName());
        studentEmail.setText(selectedStudentDto.getEmail());
        studentProgram.setText(selectedStudentDto.getProgram());
        studentyear.setText(String.valueOf(selectedStudentDto.getYear()));
        contactNumber.setText(String.valueOf(selectedStudentDto.getContactNumber()));
        studentdob.setValue(selectedStudentDto.getDob());
    }

    private void populateFields(CourseDto selectedCourse) {
        courseId.setText(selectedCourse.getCourseId());
        courseTitle.setText(selectedCourse.getCourseName());
        creditHours.setText(String.valueOf(selectedCourse.getCreditHours()));  // Convert int to String
        max_capacity.setText(String.valueOf(selectedCourse.getMaximumCapacity()));
        courseDescription.setText(selectedCourse.getDescription());
//        PreRequists.setText(selectedCourse.getPrerequisites());

        int course_index = 0;
        for(DepartmentDto departmentDto : department.getItems()){
            System.out.println(departmentDto.getName());
            if(departmentDto.getName().equals(selectedCourse.getDepartMentName())){
                department.getSelectionModel().select(course_index);
            }
            course_index++;
        }
        int pre_index = 0;
        for(PreRequisiteDto preRequisiteDto : PreRequists.getItems()){
            if(preRequisiteDto.getRequisiteName().equals(selectedCourse.getPreRequistName())){
                 PreRequists.getSelectionModel().select(pre_index);
            }
            pre_index++;
        }


    }

    private void displayOrRefreshCourseTable() {
        course_id_col.setCellValueFactory(new PropertyValueFactory<>("CourseId"));
        course_title_col.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        course_prerequistes_col.setCellValueFactory(new PropertyValueFactory<>("PreRequistName"));
        course_max_capacity_col.setCellValueFactory(new PropertyValueFactory<>("MaximumCapacity"));
        course_credit_hours_col.setCellValueFactory(new PropertyValueFactory<>("CreditHours"));
        course_department_col.setCellValueFactory(new PropertyValueFactory<>("departMentName"));
        course_description_col.setCellValueFactory(new PropertyValueFactory<>("description"));


        try {
             coursesTable.setItems(getCoursesList());
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }



    private void displayOrRefreshStudentsTable() {
        student_id_col.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        student_name_col.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        student_program_col.setCellValueFactory(new PropertyValueFactory<>("program"));
        student_email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        student_contact_col.setCellValueFactory(new PropertyValueFactory<>("ContactNumber"));
        student_dob_col.setCellValueFactory(new PropertyValueFactory<>("dob"));
        student_year_col.setCellValueFactory(new PropertyValueFactory<>("year"));


        try {
            if(searchField.getText().trim().isEmpty()){
                studentsTable.setItems(getStudentList());
            }
            else{
                studentsTable.setItems(getSearchedStudentList());
            }

        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }

    ObservableList<CourseDto> getCoursesList() throws Exception{
        ObservableList<CourseDto> courses = FXCollections.observableArrayList();
        ArrayList<CourseDto> courseDtos = courseService.getAll();
        for(CourseDto courseDto : courseDtos){
            courses.add(courseDto);
        }

        return courses;
    }


    ObservableList<StudentDto> getStudentList() throws Exception{
        ObservableList<StudentDto> students = FXCollections.observableArrayList();
        ArrayList<StudentDto> studentDtos = studentService.getAll();
        for(StudentDto studentDto : studentDtos){
            students.add(studentDto);
        }

        return students;
    }

    ObservableList<StudentDto> getSearchedStudentList() throws Exception{
        ObservableList<StudentDto> students = FXCollections.observableArrayList();
        System.out.println(searchField.getText());
        ArrayList<StudentDto> studentDtos = studentService.searchStudentByIdOrEmail(searchField.getText());
        for(StudentDto studentDto : studentDtos){
            students.add(studentDto);
        }

        return students;
    }

    @FXML
    void addCourse(ActionEvent event) throws NumberFormatException, Exception {
        System.out.println(department.getValue().getId());
        String output = courseService.save(new CourseDto(courseId.getText(), courseTitle.getText(), creditHours.getText(), department.getValue().getId(), PreRequists.getValue().getPreRequisiteId(), max_capacity.getText(),courseDescription.getText()));
        displayOrRefreshCourseTable();
       // String output = courseService.save(new CourseDto(courseId.getText(), courseTitle.getText(), creditHours.getText(), Integer.parseInt(department.getText()), PreRequists.getText(), max_capacity.getText()));

    }

    @FXML
    void updateCourse(ActionEvent event) throws Exception {
        String output = courseService.update(new CourseDto(courseId.getText(), courseTitle.getText(), creditHours.getText(), department.getValue().getId(), PreRequists.getValue().getPreRequisiteId(), max_capacity.getText(),courseDescription.getText()));
        displayOrRefreshCourseTable();

    }

    @FXML
    void addStudent(ActionEvent event) throws Exception {
        studentService.save(
                new StudentDto(
                        studentId.getText(),
                        studentName.getText(),
                        studentProgram.getText(),
                        studentEmail.getText(),
                        studentdob.getValue(),
                        "",
                        Integer.parseInt(studentyear.getText()),
                        Integer.parseInt(contactNumber.getText())));
        displayOrRefreshStudentsTable();
    }

    @FXML
    void switchForm(ActionEvent event) {
        String userData = ((Button) event.getSource()).getUserData().toString();

        homePane.setVisible(userData.equals("home"));
        coursesPane.setVisible(userData.equals("courses"));
        studentsPane.setVisible(userData.equals("students"));
    }


    public void deleteCourse(ActionEvent actionEvent) throws Exception {
         String Output = courseService.delete(courseId.getText());
        displayOrRefreshCourseTable();

    }

    @FXML
    void updateStudent(ActionEvent event) throws Exception {
         String Output = studentService.update(new StudentDto(studentId.getText(),studentName.getText(),studentProgram.getText(),studentEmail.getText(),studentdob.getValue(),Integer.parseInt(studentyear.getText()),Integer.parseInt(contactNumber.getText())));
        displayOrRefreshStudentsTable();
    }



    @FXML
    public void SearchStudent(KeyEvent inputMethodEvent) {
        displayOrRefreshStudentsTable();
    }
}
