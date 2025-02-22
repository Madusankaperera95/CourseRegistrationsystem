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
import lk.cmjd.coursework.util.Enums.SemesterTypes;
import lk.cmjd.coursework.util.SemesterUtil;
import lk.cmjd.coursework.util.WordsConverter;

import java.util.ArrayList;
import java.util.List;

public class AdminController {


    private CourseService courseService = (CourseService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.COURSE);

    private DepartmentService departmentService = (DepartmentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.DEPARTMENT);

    private StudentService studentService =(StudentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STUDENT);

    private PreRequisiteService preRequisiteService = (PreRequisiteService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.PREREQUISITE);

    private EnrollmentService enrollmentService = (EnrollmentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ENROLLMENT);

    @FXML
    private ComboBox<SemesterUtil> searchSemesterComboBox;

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
    public AnchorPane enrollmentsPane;


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
    private TableColumn<EnrollmentDto,String> enrollment_id_col;

    @FXML
    private TableColumn<EnrollmentDto,String> enroll_student_id_col;

    @FXML
    public TableColumn<EnrollmentDto,String> enroll_student_name_col;


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
    private TableColumn<EnrollmentDto,Void> add_gpa_col;


    @FXML
    private TableView<StudentDto> studentsTable;

    @FXML
    private TableView<EnrollmentDto> enrollmentTable;

    @FXML
    private  Label enrollId;

    @FXML
    private Label enrollStudentId;

    @FXML
    private Label enrollCourseId;

    @FXML
    private Label semester;

    @FXML
    private Label status;

    @FXML
    private TextField gpa;

    @FXML
    private ComboBox<CourseDto> CourseComboBox;

    @FXML
    private Button filterButton;

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
        displayOrRefreshEnrollmentsTable();
        loadCourses();
        loadSemesters();
    }

    private void loadCourses() throws Exception {
        ArrayList<CourseDto> allCourses = courseService.getAll();

        CourseDto selectOption = new CourseDto();
        selectOption.setCourseName("Select"); // Ensure your CourseDto has a setter method

        allCourses.add(0, selectOption);

        ObservableList<CourseDto> courses = FXCollections.observableArrayList(allCourses);
        CourseComboBox.setItems(courses);

        CourseComboBox.setCellFactory(param -> new ListCell<CourseDto>() {
            @Override
            protected void updateItem(CourseDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCourseName());
            }
        });

        CourseComboBox.setButtonCell(new ListCell<CourseDto>() {
            @Override
            protected void updateItem(CourseDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCourseName());
            }
        });

        CourseComboBox.getSelectionModel().selectFirst();
    }


    private void loadSemesters() {
        // Create a dummy "Select" option
        SemesterUtil selectOption = new SemesterUtil();
        selectOption.setValue("Select");

        // Create the list with the "Select" option and actual semesters
        ObservableList<SemesterUtil> semesters = FXCollections.observableArrayList(
                selectOption, // Add "Select" option first
                new SemesterUtil(SemesterTypes.FIRSTSEMESTER, "First Semester"),
                new SemesterUtil(SemesterTypes.SECONDSEMESTER, "Second Semester")
        );

        searchSemesterComboBox.setItems(semesters);

        searchSemesterComboBox.setCellFactory(param -> new ListCell<SemesterUtil>() {
            @Override
            protected void updateItem(SemesterUtil item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getValue());
            }
        });

        searchSemesterComboBox.setButtonCell(new ListCell<SemesterUtil>() {
            @Override
            protected void updateItem(SemesterUtil item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getValue());
            }
        });

        // Set "Select" as the default selected item
        searchSemesterComboBox.getSelectionModel().selectFirst();
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

    private void displayOrRefreshEnrollmentsTable() {
        enrollment_id_col.setCellValueFactory(new PropertyValueFactory<>("enrollId"));
        enroll_course_name_col.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        semester_col.setCellValueFactory(cellData -> {
            String semester = String.valueOf(cellData.getValue().getSemester());
            return new SimpleStringProperty(WordsConverter.getRealValue(semester));
        });
        status_type_col.setCellValueFactory(cellData -> {
            String semester = String.valueOf(cellData.getValue().getStatus());
            return new SimpleStringProperty(WordsConverter.getRealValue(semester));
        });
        enroll_student_id_col.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        enroll_student_name_col.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        gpa_col.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        grade_col.setCellValueFactory(new PropertyValueFactory<>("grade"));
        add_gpa_col.setCellFactory(param -> new TableCell<EnrollmentDto, Void>() {
            private final Button addGpaButton = new Button("Add GPA");

            {
                addGpaButton.setStyle("-fx-background-color: orange; " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 5px 10px; ");

                addGpaButton.setOnAction(event -> {
                    EnrollmentDto enrollment = getTableView().getItems().get(getIndex());
                    handleAddGpa(enrollment);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(addGpaButton);
                }
            }
        });


        try {
            enrollmentTable.setItems(getEnrollmentList());
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }



    private void handleAddGpa(EnrollmentDto enrollment) {
        System.out.println("calling");
        enrollStudentId.setText(enrollment.getStudentId());
        enrollCourseId.setText(enrollment.getCourseId());
        semester.setText(enrollment.getSemester().name());
        status.setText(enrollment.getStatus().name());
        enrollId.setText(enrollment.getEnrollId());
        enrollmentsPane.setVisible(false);
        gpaUpatePane.setVisible(true);
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

    ObservableList<EnrollmentDto> getEnrollmentList() throws Exception{
         ObservableList<EnrollmentDto> enrollments = FXCollections.observableArrayList();
         ArrayList<EnrollmentDto> enrollmentDtos = enrollmentService.getAll();

         for(EnrollmentDto enrollmentDto : enrollmentDtos){
             enrollments.add(enrollmentDto);

         }
        return enrollments;
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
        enrollmentsPane.setVisible(userData.equals("enrollments"));
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
    public void updateGpa(ActionEvent actionEvent) throws Exception {
        String output = enrollmentService.update(new EnrollmentDto(enrollId.getText(),Double.parseDouble(gpa.getText())));
        displayOrRefreshEnrollmentsTable();
        enrollmentsPane.setVisible(true);
        gpaUpatePane.setVisible(false);
    }

    @FXML
    public void SearchStudent(KeyEvent inputMethodEvent) {
        displayOrRefreshStudentsTable();
    }

    public void filterEnrollments(ActionEvent actionEvent) {
        CourseDto courseDto = CourseComboBox.getValue();
        SemesterUtil semesterUtil = searchSemesterComboBox.getValue();
        System.out.println(courseDto.getCourseId());
        System.out.println(semesterUtil.getKey());
        if(courseDto.getCourseName().equals("Select") || semesterUtil.getValue().equals("Select") ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Box");
            alert.setHeaderText(null);
            alert.setContentText("Please select the Options");
            alert.showAndWait();
        }

        else {
            filterEnrollmentsTable(courseDto.getCourseId(), semesterUtil.getKey());
        }
    }

    private void filterEnrollmentsTable(String courseId,SemesterTypes semesterType) {

        enrollment_id_col.setCellValueFactory(new PropertyValueFactory<>("enrollId"));
        enroll_course_name_col.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        semester_col.setCellValueFactory(cellData -> {
            String semester = String.valueOf(cellData.getValue().getSemester());
            return new SimpleStringProperty(WordsConverter.getRealValue(semester));
        });
        status_type_col.setCellValueFactory(cellData -> {
            String semester = String.valueOf(cellData.getValue().getStatus());
            return new SimpleStringProperty(WordsConverter.getRealValue(semester));
        });
        enroll_student_id_col.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        enroll_student_name_col.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        gpa_col.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        grade_col.setCellValueFactory(new PropertyValueFactory<>("grade"));
        add_gpa_col.setCellFactory(param -> new TableCell<EnrollmentDto, Void>() {
            private final Button addGpaButton = new Button("Add GPA");

            {
                addGpaButton.setStyle("-fx-background-color: orange; " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 5px 10px; ");

                addGpaButton.setOnAction(event -> {
                    EnrollmentDto enrollment = getTableView().getItems().get(getIndex());
                    handleAddGpa(enrollment);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(addGpaButton);
                }
            }
        });


        try {
            enrollmentTable.setItems(getEnrollmentForFilter(courseId,semesterType));
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }

    ObservableList<EnrollmentDto> getEnrollmentForFilter(String courseId,SemesterTypes semester) throws Exception{
        ObservableList<EnrollmentDto> enrollments = FXCollections.observableArrayList();
        ArrayList<EnrollmentDto> enrollmentDtos = enrollmentService.getEnrollmentsForFilter(courseId,semester);

        for(EnrollmentDto enrollmentDto : enrollmentDtos){
            enrollments.add(enrollmentDto);

        }
        return enrollments;
    }
}




