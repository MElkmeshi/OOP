package Project;

import java.io.*;
import java.text.*;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.*;
import javafx.geometry.*;

/**
 *
 * @author melkmeshi
 */
public class RMS extends Application {

    static String loggedInAs = new String();
    static Employees emp = new Employees();
    static Interviews interview = new Interviews();
    static Agencies agency = new Agencies();
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    static Jobs job = new Jobs();
    static ArrayList<String> jobCategories = new ArrayList<>();
    static Date applydate = new Date();

    @Override
    public void start(Stage primaryStage) {
        Button agencySignUpButton = new Button("Agency Sign Up");
        agencySignUpButton.setOnAction(e -> AgencySignup());
        Button employeeSignUpButton = new Button("Employee Sign Up");
        employeeSignUpButton.setOnAction(e -> EmployeeSignUp());
        Button loginbutton = new Button("Log in");
        loginbutton.setOnAction(e -> login());
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(5);
        root.getChildren().addAll(agencySignUpButton, employeeSignUpButton, loginbutton);

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("RMS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void EmployeeSignUp() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("You entrend a dublicated username");
        alert.setContentText("Please choose another username");
        Alert alertnum = new Alert(AlertType.ERROR);
        alertnum.setTitle("Error Dialog");
        alertnum.setHeaderText("You entrend a word instend of number");
        alertnum.setContentText("Please recheck your input");
        GridPane register = new GridPane();
        CheckMenuItem[] jobcatsarray = {new CheckMenuItem("Accounting/Finance"), new CheckMenuItem("Administration"), new CheckMenuItem("Analyst/Research"), new CheckMenuItem("C-Level Executive/GM/Director"), new CheckMenuItem("Banking"), new CheckMenuItem("Business Development"), new CheckMenuItem("Creative/Design/Art"), new CheckMenuItem("Education/Teaching"), new CheckMenuItem("Engineering - Construction/Civil/Architecture"), new CheckMenuItem("Customer Service/Support"), new CheckMenuItem("Engineering - Telecom/Technology"), new CheckMenuItem("Engineering - Oil & Gas/Energy"), new CheckMenuItem("Engineering - Mechanical/Electrical"), new CheckMenuItem("Engineering - Other"), new CheckMenuItem("Fashion"), new CheckMenuItem("Human Resources"), new CheckMenuItem("Hospitality/Hotels/Food Services"), new CheckMenuItem("Installation/Maintenance/Repair"), new CheckMenuItem("Legal"), new CheckMenuItem("Logistics/Supply Chain"), new CheckMenuItem("Manufacturing/Production"), new CheckMenuItem("Marketing/PR/Advertising"), new CheckMenuItem("IT/Software Development"), new CheckMenuItem("Media/Journalism/Publishing"), new CheckMenuItem("Medical/Healthcare"), new CheckMenuItem("Operations/Management"), new CheckMenuItem("Project/Program Management"), new CheckMenuItem("Purchasing/Procurement"), new CheckMenuItem("Quality"), new CheckMenuItem("R&D/Science"), new CheckMenuItem("Sales/Retail"), new CheckMenuItem("Sports and Leisure"), new CheckMenuItem("Tourism/Travel"), new CheckMenuItem("Strategy/Consulting"), new CheckMenuItem("Pharmaceutical"), new CheckMenuItem("Training/Instructor"), new CheckMenuItem("Writing/Editorial")};
        MenuButton jobcatsmenu = new MenuButton("Job Categories");
        jobcatsmenu.getItems().addAll(jobcatsarray);
        TextField[] signupfields = new TextField[6];
        for (int i = 0; i < signupfields.length; i++) {
            if (i == 2) {
                signupfields[i] = new PasswordField();
            } else {
                signupfields[i] = new TextField();
            }
        }

        register.add(new Label("Name"), 0, 0);
        register.add(signupfields[0], 1, 0);
        register.add(new Label("UserName"), 0, 1);
        register.add(signupfields[1], 1, 1);
        register.add(new Label("Password"), 0, 2);
        register.add(signupfields[2], 1, 2);
        register.add(new Label("Degree"), 0, 3);
        register.add(signupfields[3], 1, 3);
        register.add(new Label("Years of Experience"), 0, 4);
        register.add(signupfields[4], 1, 4);
        register.add(new Label("Age"), 0, 5);
        register.add(signupfields[5], 1, 5);
        register.add(new Label("Job Categories"), 0, 6);
        register.add(jobcatsmenu, 1, 6);

        Button addStudentButton = new Button("Add Employee");
        register.add(addStudentButton, 0, 9);
        Button eraseAllButton = new Button("Erase All");
        register.add(eraseAllButton, 1, 9);
        register.setPadding(new Insets(10));
        register.setVgap(5);
        register.setHgap(5);
        Scene scene = new Scene(register, 400, 350);
        Stage s = new Stage();
        s.setScene(scene);
        s.setTitle("Registration Form");
        s.show();
        eraseAllButton.setOnAction(eerase -> {
            for (int i = 0; i < signupfields.length; i++) {
                signupfields[i].setText(null);
            }
            for (CheckMenuItem jobcat : jobcatsarray) {
                jobcat.setSelected(false);
            }
        });
        addStudentButton.setOnAction(esignupagency -> {
            String[] data = new String[signupfields.length];
            for (int i = 0; i < signupfields.length; i++) {
                data[i] = (String) signupfields[i].getText();
            }
            ArrayList<String> jobcats = new ArrayList<>();
            for (CheckMenuItem jobcat : jobcatsarray) {
                if (jobcat.isSelected()) {
                    jobcats.add(jobcat.getText());
                }
            }
            if (!agency.isUnique(data[1]) || !emp.isUnique(data[1]) || data[1].equals("admin")) {
                alert.showAndWait();
                signupfields[1].setText(null);
                return;
            }
            try {
                emp.Add(new Employee(data[0], data[1], data[2], Integer.parseInt(data[5]), data[3], Integer.parseInt(data[4]), jobcats));
                s.close();
            } catch (NumberFormatException cwd) {
                alertnum.showAndWait();
                signupfields[5].setText(null);
                signupfields[4].setText(null);

            } catch (IOException ex) {
                System.out.println(ex.toString());
                s.close();
            }
        });

    }

    public void AgencySignup() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("You entrend a dublicated username");
        alert.setContentText("Please choose another username");
        GridPane register = new GridPane();
        TextField[] signupfields = new TextField[5];
        for (int i = 0; i < signupfields.length; i++) {
            if (i == 2) {
                signupfields[i] = new PasswordField();
            } else {
                signupfields[i] = new TextField();
            }
        }
        register.add(new Label("Name"), 0, 0);
        register.add(signupfields[0], 1, 0);
        register.add(new Label("UserName"), 0, 1);
        register.add(signupfields[1], 1, 1);
        register.add(new Label("Password"), 0, 2);
        register.add(signupfields[2], 1, 2);
        register.add(new Label("Address"), 0, 3);
        register.add(signupfields[3], 1, 3);
        register.add(new Label("Industry"), 0, 4);
        register.add(signupfields[4], 1, 4);

        Button addStudentButton = new Button("Add Agency");
        register.add(addStudentButton, 0, 9);
        Button eraseAllButton = new Button("Erase All");
        register.add(eraseAllButton, 1, 9);
        register.setPadding(new Insets(10));
        register.setVgap(5);
        register.setHgap(5);
        Scene scene = new Scene(register, 400, 350);
        Stage s = new Stage();
        s.setScene(scene);
        s.setTitle("Registration Form");
        s.show();
        eraseAllButton.setOnAction(eerase -> {
            for (int i = 0; i < signupfields.length; i++) {
                signupfields[i].setText(null);
            }
        });
        addStudentButton.setOnAction(esignupagency -> {
            String[] data = new String[signupfields.length];
            for (int i = 0; i < signupfields.length; i++) {
                data[i] = (String) signupfields[i].getText();
            }
            if (!agency.isUnique(data[1]) || !emp.isUnique(data[1]) || data[1].equals("admin")) {
                alert.showAndWait();
                signupfields[1].setText(null);
                return;
            }
            try {
                agency.Add(new Agency(data[0], data[1], data[3], data[4], data[2]));
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            s.close();
        });
    }

    public void AgencyUpdate(Stage s) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> AgencyHome(s));
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("You entrend a dublicated username");
        alert.setContentText("Please choose another username");
        GridPane register = new GridPane();
        TextField[] signupfields = new TextField[5];
        for (int i = 0; i < signupfields.length; i++) {
            if (i == 2) {
                signupfields[i] = new PasswordField();
            } else {
                signupfields[i] = new TextField();
            }
        }
        register.add(new Label("Name"), 0, 0);
        register.add(signupfields[0], 1, 0);
        register.add(new Label("UserName"), 0, 1);
        register.add(signupfields[1], 1, 1);
        register.add(new Label("Password"), 0, 2);
        register.add(signupfields[2], 1, 2);
        register.add(new Label("Address"), 0, 3);
        register.add(signupfields[3], 1, 3);
        register.add(new Label("Industry"), 0, 4);
        register.add(signupfields[4], 1, 4);

        Agency myagecy = agency.Get(agency.SearchByUserName(loggedInAs));
        signupfields[0].setText(myagecy.getName());
        signupfields[1].setText(myagecy.getUserName());
        signupfields[1].setEditable(false);
        signupfields[2].setText(myagecy.getPassword());
        signupfields[3].setText(myagecy.getAddress());
        signupfields[4].setText(myagecy.getIndustry());

        Button updateagency = new Button("Update");
        register.add(updateagency, 0, 9);
        register.setPadding(new Insets(10));
        register.setVgap(5);
        register.setHgap(5);
        Scene scene = new Scene(new VBox(backButton, register), 400, 350);
        s.setScene(scene);
        s.setTitle("Registration Form");
        s.show();
        updateagency.setOnAction(esignupagency -> {
            String[] data = new String[signupfields.length];
            for (int i = 0; i < signupfields.length; i++) {
                data[i] = (String) signupfields[i].getText();
            }
            if (!data[1].equals(myagecy.getUserName())) {
                if (!agency.isUnique(data[1]) || !emp.isUnique(data[1])) {
                    alert.showAndWait();
                    signupfields[1].setText(null);
                    return;
                }
            }

            agency.Get(agency.SearchByUserName(loggedInAs)).setName(data[0]);
            agency.Get(agency.SearchByUserName(loggedInAs)).setPassword(data[2]);
            agency.Get(agency.SearchByUserName(loggedInAs)).setAddress(data[3]);
            agency.Get(agency.SearchByUserName(loggedInAs)).setIndustry(data[4]);
            try {
                this.agency.Save();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            AgencyHome(s);
        });
    }

    public void login() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Username or password is incorrect");
        alert.setContentText("Please check your username and password");
        GridPane loginPane = new GridPane();
        TextField loginTextField = new TextField();
        TextField passwordTextField = new PasswordField();
        loginPane.add(new Label("Username"), 0, 0);
        loginPane.add(loginTextField, 1, 0);
        loginPane.add(new Label("Password"), 0, 1);
        loginPane.add(passwordTextField, 1, 1);
        Button innerloginbutton = new Button("Log In");
        loginPane.add(innerloginbutton, 1, 5);
        loginPane.setPadding(new Insets(10));
        loginPane.setVgap(5);
        loginPane.setHgap(5);
        Scene scene = new Scene(loginPane, 800,400);
        Stage s = new Stage();
        s.setScene(scene);
        innerloginbutton.setOnAction(elogin -> {
            if (agency.Login((String) loginTextField.getText(), (String) passwordTextField.getText())) {
                loggedInAs = (String) loginTextField.getText();
                AgencyHome(s);
            } else if (emp.Login((String) loginTextField.getText(), (String) passwordTextField.getText())) {
                loggedInAs = (String) loginTextField.getText();
                EmployeeHome(s);
            } else if (((String) loginTextField.getText()).equals("admin") && ((String) passwordTextField.getText()).equals("admin")) {
                loggedInAs = (String) loginTextField.getText();
                AdminHome(s);
            } else {
                alert.showAndWait();
            }
        });
        s.show();
    }

    public void AgencyHome(Stage stage) {
        Button cJobButton = new Button("Create Job");
        cJobButton.setOnAction(e -> CreateJob(stage));
        Button sJobButton = new Button("Show and Edit my Jobs");
        sJobButton.setOnAction(e -> ShowJob(stage));
        Button sInterButton = new Button("Show my interview");
        sInterButton.setOnAction(e -> ShowinterviewsAgency(stage));
        Button eProButton = new Button("Edit My Profile");
        eProButton.setOnAction(e -> AgencyUpdate(stage));
        Button dDataButton = new Button("Delete all my data");
        dDataButton.setOnAction(e -> {
            try {
                agency.Delete(agency.SearchByUserName(loggedInAs));
                job.DELETE(loggedInAs);
                interview.DELETE(loggedInAs);
                loggedInAs = "";
                stage.close();
                login();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        });
        Button signOut = new Button("Sign Out");
        signOut.setOnAction(e -> {
            stage.close();
            loggedInAs = "";
            login();
        });
        VBox hbox = new VBox();
        hbox.getChildren().addAll(cJobButton, sJobButton, sInterButton, eProButton, dDataButton, signOut);
        stage.setTitle("Agency : " + loggedInAs);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(5);
        stage.setScene(new Scene(hbox, 400, 300));
        stage.show();
    }

    public void AdminHome(Stage stage) {
        Button jobs = new Button("Show Jobs");
        jobs.setOnAction(e -> AdminJobs(stage));
        Button interviews = new Button("Show Interviews");
        interviews.setOnAction(e -> AdminInterviews(stage));
        Button agencies = new Button("Show Agencies");
        agencies.setOnAction(e -> AdminAgencies(stage));
        Button employees = new Button("Show Employees");
        employees.setOnAction(e -> AdminEmployees(stage));
        Button signOut = new Button("Sign Out");
        signOut.setOnAction(e -> {
            stage.close();
            loggedInAs = "";
            login();
        });
        VBox hbox = new VBox();
        hbox.getChildren().addAll(jobs, interviews, agencies, employees, signOut);
        stage.setTitle("Welcome back my " + loggedInAs);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(5);
        stage.setScene(new Scene(hbox, 400, 300));
        stage.show();
    }

    public void EmployeeHome(Stage stage) {
        Button sJobButton = new Button("Apply for job");
        sJobButton.setOnAction(e -> ShowAllJob(stage));
        Button sInterButton = new Button("Show my interview");
        sInterButton.setOnAction(e -> ShowinterviewsEmployee(stage));
        Button eProButton = new Button("Edit My Profile");
        eProButton.setOnAction(e -> EmployeeUpdate(stage));
        Button dDataButton = new Button("Delete all my data");
        dDataButton.setOnAction(e -> {
            try {
                interview.DELETE(emp.SearchByUserName(loggedInAs));
                emp.Delete(emp.SearchByUserName(loggedInAs));
                loggedInAs = "";
                stage.close();
                login();
            } catch (/*IO*/Exception ex) {
                System.out.println(ex.toString());
            }
        });
        Button signOut = new Button("Sign Out");
        signOut.setOnAction(e -> {
            stage.close();
            loggedInAs = "";
            login();
        });
        VBox hbox = new VBox();
        hbox.getChildren().addAll(sJobButton, sInterButton, eProButton, dDataButton, signOut);
        stage.setTitle("Employee : " + loggedInAs);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(5);
        stage.setScene(new Scene(hbox, 400, 300));
        stage.show();
    }

    public void UpdateJob(Stage s, int jobID) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> ShowJob(s));
        Alert alertnum = new Alert(AlertType.ERROR);
        alertnum.setTitle("Error Dialog");
        alertnum.setHeaderText("You entrend a word instend of number");
        alertnum.setContentText("Please recheck your input");

        CheckMenuItem[] jobcatsarray = {new CheckMenuItem("Accounting/Finance"), new CheckMenuItem("Administration"), new CheckMenuItem("Analyst/Research"), new CheckMenuItem("C-Level Executive/GM/Director"), new CheckMenuItem("Banking"), new CheckMenuItem("Business Development"), new CheckMenuItem("Creative/Design/Art"), new CheckMenuItem("Education/Teaching"), new CheckMenuItem("Engineering - Construction/Civil/Architecture"), new CheckMenuItem("Customer Service/Support"), new CheckMenuItem("Engineering - Telecom/Technology"), new CheckMenuItem("Engineering - Oil & Gas/Energy"), new CheckMenuItem("Engineering - Mechanical/Electrical"), new CheckMenuItem("Engineering - Other"), new CheckMenuItem("Fashion"), new CheckMenuItem("Human Resources"), new CheckMenuItem("Hospitality/Hotels/Food Services"), new CheckMenuItem("Installation/Maintenance/Repair"), new CheckMenuItem("Legal"), new CheckMenuItem("Logistics/Supply Chain"), new CheckMenuItem("Manufacturing/Production"), new CheckMenuItem("Marketing/PR/Advertising"), new CheckMenuItem("IT/Software Development"), new CheckMenuItem("Media/Journalism/Publishing"), new CheckMenuItem("Medical/Healthcare"), new CheckMenuItem("Operations/Management"), new CheckMenuItem("Project/Program Management"), new CheckMenuItem("Purchasing/Procurement"), new CheckMenuItem("Quality"), new CheckMenuItem("R&D/Science"), new CheckMenuItem("Sales/Retail"), new CheckMenuItem("Sports and Leisure"), new CheckMenuItem("Tourism/Travel"), new CheckMenuItem("Strategy/Consulting"), new CheckMenuItem("Pharmaceutical"), new CheckMenuItem("Training/Instructor"), new CheckMenuItem("Writing/Editorial")};
        MenuButton jobcatsmenu = new MenuButton("Job Categories");
        jobcatsmenu.getItems().addAll(jobcatsarray);
        String[] carrerlevelarray = {"Student", "Entry Level", "Experienced (Non-Manager)", "Manager", "Senior Management"};
        ComboBox carrerlevels = new ComboBox(FXCollections.observableList(Arrays.asList(carrerlevelarray)));
        TextField[] signupfields = new TextField[3];
        for (int i = 0; i < signupfields.length; i++) {
            signupfields[i] = new TextField();
        }
        RadioButton FullTimeRadioButton = new RadioButton();
        GridPane register = new GridPane();
        register.add(new Label("Job Title"), 0, 0);
        register.add(signupfields[0], 1, 0);
        register.add(new Label("Job Description"), 0, 1);
        register.add(signupfields[1], 1, 1);
        register.add(new Label("Years of Experience"), 0, 2);
        register.add(signupfields[2], 1, 2);
        register.add(new Label("Career Level"), 0, 3);
        register.add(carrerlevels, 1, 3);
        register.add(new Label("Job Categories"), 0, 4);
        register.add(jobcatsmenu, 1, 4);
        register.add(new Label("is Full Time"), 0, 5);
        register.add(FullTimeRadioButton, 1, 5);

        Button updateStudentButton = new Button("Update Job");
        updateStudentButton.setId(String.valueOf(jobID));
        register.add(updateStudentButton, 0, 9);

        Job myjob = job.Get(job.getIndex(jobID));
        signupfields[0].setText(myjob.getJobTitle());
        signupfields[1].setText(myjob.getJobDescription());
        signupfields[2].setText(String.valueOf(myjob.getYearsofExperience()));
        carrerlevels.setValue(myjob.getCareerLevel());

        for (CheckMenuItem jobcat : jobcatsarray) {
            if (myjob.getJobCategory().contains(jobcat.getText())) {
                jobcat.setSelected(true);
            }
        }
        FullTimeRadioButton.setSelected(myjob.isFulltime());

        register.setPadding(new Insets(10));
        register.setVgap(5);
        register.setHgap(5);

        Scene scene = new Scene(new VBox(backButton, register), 400, 350);
        s.setScene(scene);
        s.setTitle("New Job Form");
        s.show();
        updateStudentButton.setOnAction(eujob -> {
            String[] data = new String[signupfields.length];
            for (int i = 0; i < signupfields.length; i++) {
                data[i] = (String) signupfields[i].getText();
            }
            ArrayList<String> jobcats = new ArrayList<>();
            for (CheckMenuItem jobcat : jobcatsarray) {
                if (jobcat.isSelected()) {
                    jobcats.add(jobcat.getText());
                }
            }
            Job myjobs = job.Get(job.getIndex(Integer.parseInt(((Button) eujob.getSource()).getId())));
            myjobs.setFulltime(FullTimeRadioButton.isSelected());
            myjobs.setYearsofExperience(Integer.parseInt(data[2]));
            myjobs.setJobTitle(data[0]);
            myjobs.setCareerLevel((String) carrerlevels.getValue());
            myjobs.setJobDescription(data[1]);
            myjobs.setJobCategory(jobcats);
            ShowJob(s);
        });
    }

    public void CreateJob(Stage s) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> AgencyHome(s));
        Alert alertnum = new Alert(AlertType.ERROR);
        alertnum.setTitle("Error Dialog");
        alertnum.setHeaderText("You entrend a word instend of number");
        alertnum.setContentText("Please recheck your input");

        CheckMenuItem[] jobcatsarray = {new CheckMenuItem("Accounting/Finance"), new CheckMenuItem("Administration"), new CheckMenuItem("Analyst/Research"), new CheckMenuItem("C-Level Executive/GM/Director"), new CheckMenuItem("Banking"), new CheckMenuItem("Business Development"), new CheckMenuItem("Creative/Design/Art"), new CheckMenuItem("Education/Teaching"), new CheckMenuItem("Engineering - Construction/Civil/Architecture"), new CheckMenuItem("Customer Service/Support"), new CheckMenuItem("Engineering - Telecom/Technology"), new CheckMenuItem("Engineering - Oil & Gas/Energy"), new CheckMenuItem("Engineering - Mechanical/Electrical"), new CheckMenuItem("Engineering - Other"), new CheckMenuItem("Fashion"), new CheckMenuItem("Human Resources"), new CheckMenuItem("Hospitality/Hotels/Food Services"), new CheckMenuItem("Installation/Maintenance/Repair"), new CheckMenuItem("Legal"), new CheckMenuItem("Logistics/Supply Chain"), new CheckMenuItem("Manufacturing/Production"), new CheckMenuItem("Marketing/PR/Advertising"), new CheckMenuItem("IT/Software Development"), new CheckMenuItem("Media/Journalism/Publishing"), new CheckMenuItem("Medical/Healthcare"), new CheckMenuItem("Operations/Management"), new CheckMenuItem("Project/Program Management"), new CheckMenuItem("Purchasing/Procurement"), new CheckMenuItem("Quality"), new CheckMenuItem("R&D/Science"), new CheckMenuItem("Sales/Retail"), new CheckMenuItem("Sports and Leisure"), new CheckMenuItem("Tourism/Travel"), new CheckMenuItem("Strategy/Consulting"), new CheckMenuItem("Pharmaceutical"), new CheckMenuItem("Training/Instructor"), new CheckMenuItem("Writing/Editorial")};
        MenuButton jobcatsmenu = new MenuButton("Job Categories");
        jobcatsmenu.getItems().addAll(jobcatsarray);
        String[] carrerlevelarray = {"Student", "Entry Level", "Experienced (Non-Manager)", "Manager", "Senior Management"};
        ComboBox carrerlevels = new ComboBox(FXCollections.observableList(Arrays.asList(carrerlevelarray)));
        TextField[] signupfields = new TextField[3];
        for (int i = 0; i < signupfields.length; i++) {
            signupfields[i] = new TextField();
        }
        RadioButton FullTimeRadioButton = new RadioButton();

        GridPane register = new GridPane();
        register.add(new Label("Job Title"), 0, 0);
        register.add(signupfields[0], 1, 0);
        register.add(new Label("Job Description"), 0, 1);
        register.add(signupfields[1], 1, 1);
        register.add(new Label("Years of Experience"), 0, 2);
        register.add(signupfields[2], 1, 2);
        register.add(new Label("Career Level"), 0, 3);
        register.add(carrerlevels, 1, 3);
        register.add(new Label("Job Categories"), 0, 4);
        register.add(jobcatsmenu, 1, 4);
        register.add(new Label("is Full Time"), 0, 5);
        register.add(FullTimeRadioButton, 1, 5);

        Button addStudentButton = new Button("Add Job");
        register.add(addStudentButton, 0, 9);
        register.setPadding(new Insets(10));
        register.setVgap(5);
        register.setHgap(5);
        Scene scene = new Scene(new VBox(backButton, register), 400, 350);
        s.setScene(scene);
        s.setTitle("New Job Form");
        s.show();
        addStudentButton.setOnAction(esignupagency -> {
            String[] data = new String[signupfields.length];
            for (int i = 0; i < signupfields.length; i++) {
                data[i] = (String) signupfields[i].getText();
            }
            ArrayList<String> jobcats = new ArrayList<>();
            for (CheckMenuItem jobcat : jobcatsarray) {
                if (jobcat.isSelected()) {
                    jobcats.add(jobcat.getText());
                }
            }
            try {
                job.CREATE(agency.Get(agency.SearchByUserName(loggedInAs)), new Date(), FullTimeRadioButton.isSelected(), Integer.parseInt(data[2]), data[0], (String) carrerlevels.getValue(), data[1], jobcats);
            } catch (NumberFormatException cwd) {
                alertnum.showAndWait();
                signupfields[2].setText(null);
            } catch (IOException ex) {
                System.out.println(ex.toString());
            } finally {
                AgencyHome(s);
            }
        });
    }

    public void EmployeeUpdate(Stage s) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("You entrend a dublicated username");
        alert.setContentText("Please choose another username");
        Alert alertnum = new Alert(AlertType.ERROR);
        alertnum.setTitle("Error Dialog");
        alertnum.setHeaderText("You entrend a word instend of number");
        alertnum.setContentText("Please recheck your input");
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> EmployeeHome(s));

        GridPane register = new GridPane();
        CheckMenuItem[] jobcatsarray = {new CheckMenuItem("Accounting/Finance"), new CheckMenuItem("Administration"), new CheckMenuItem("Analyst/Research"), new CheckMenuItem("C-Level Executive/GM/Director"), new CheckMenuItem("Banking"), new CheckMenuItem("Business Development"), new CheckMenuItem("Creative/Design/Art"), new CheckMenuItem("Education/Teaching"), new CheckMenuItem("Engineering - Construction/Civil/Architecture"), new CheckMenuItem("Customer Service/Support"), new CheckMenuItem("Engineering - Telecom/Technology"), new CheckMenuItem("Engineering - Oil & Gas/Energy"), new CheckMenuItem("Engineering - Mechanical/Electrical"), new CheckMenuItem("Engineering - Other"), new CheckMenuItem("Fashion"), new CheckMenuItem("Human Resources"), new CheckMenuItem("Hospitality/Hotels/Food Services"), new CheckMenuItem("Installation/Maintenance/Repair"), new CheckMenuItem("Legal"), new CheckMenuItem("Logistics/Supply Chain"), new CheckMenuItem("Manufacturing/Production"), new CheckMenuItem("Marketing/PR/Advertising"), new CheckMenuItem("IT/Software Development"), new CheckMenuItem("Media/Journalism/Publishing"), new CheckMenuItem("Medical/Healthcare"), new CheckMenuItem("Operations/Management"), new CheckMenuItem("Project/Program Management"), new CheckMenuItem("Purchasing/Procurement"), new CheckMenuItem("Quality"), new CheckMenuItem("R&D/Science"), new CheckMenuItem("Sales/Retail"), new CheckMenuItem("Sports and Leisure"), new CheckMenuItem("Tourism/Travel"), new CheckMenuItem("Strategy/Consulting"), new CheckMenuItem("Pharmaceutical"), new CheckMenuItem("Training/Instructor"), new CheckMenuItem("Writing/Editorial")};
        MenuButton jobcatsmenu = new MenuButton("Job Categories");
        jobcatsmenu.getItems().addAll(jobcatsarray);
        TextField[] signupfields = new TextField[6];
        for (int i = 0; i < signupfields.length; i++) {
            if (i == 2) {
                signupfields[i] = new PasswordField();
            } else {
                signupfields[i] = new TextField();
            }
        }

        register.add(new Label("Name"), 0, 0);
        register.add(signupfields[0], 1, 0);
        register.add(new Label("UserName"), 0, 1);
        register.add(signupfields[1], 1, 1);
        register.add(new Label("Password"), 0, 2);
        register.add(signupfields[2], 1, 2);
        register.add(new Label("Degree"), 0, 3);
        register.add(signupfields[3], 1, 3);
        register.add(new Label("Years of Experience"), 0, 4);
        register.add(signupfields[4], 1, 4);
        register.add(new Label("Age"), 0, 5);
        register.add(signupfields[5], 1, 5);
        register.add(new Label("Job Categories"), 0, 6);
        register.add(jobcatsmenu, 1, 6);

        Button addStudentButton = new Button("Update");
        register.add(addStudentButton, 0, 9);

        Employee myemp = emp.Get(emp.SearchByUserName(loggedInAs));
        signupfields[0].setText(myemp.getName());
        signupfields[1].setText(myemp.getUserName());
        signupfields[1].setEditable(false);
        signupfields[2].setText(myemp.getPassword());
        signupfields[3].setText(myemp.getDegree());
        signupfields[4].setText(String.valueOf(myemp.getYearsofExperience()));
        signupfields[5].setText(String.valueOf(myemp.getAge()));
        for (CheckMenuItem jobcat : jobcatsarray) {
            if (myemp.getJobCategory().contains(jobcat.getText())) {
                jobcat.setSelected(true);
            }
        }

        register.setPadding(new Insets(10));
        register.setVgap(5);
        register.setHgap(5);

        Scene scene = new Scene(new VBox(backButton, register), 400, 350);
        s.setScene(scene);
        s.setTitle("Update Emoloyee");
        s.show();
        addStudentButton.setOnAction(esignupagency -> {
            String[] data = new String[signupfields.length];
            for (int i = 0; i < signupfields.length; i++) {
                data[i] = (String) signupfields[i].getText();
            }
            ArrayList<String> jobcats = new ArrayList<>();
            for (CheckMenuItem jobcat : jobcatsarray) {
                if (jobcat.isSelected()) {
                    jobcats.add(jobcat.getText());
                }
            }
            if (!data[1].equals(myemp.getUserName())) {
                if (!agency.isUnique(data[1]) || !emp.isUnique(data[1])) {
                    alert.showAndWait();
                    signupfields[1].setText(null);
                    return;
                }
            }
            try {
                emp.Get(emp.SearchByUserName(loggedInAs)).setAge(Integer.parseInt(data[5]));
                emp.Get(emp.SearchByUserName(loggedInAs)).setYearsofExperience(Integer.parseInt(data[4]));
                emp.Get(emp.SearchByUserName(loggedInAs)).setName(data[0]);
                emp.Get(emp.SearchByUserName(loggedInAs)).setPassword(data[2]);
                emp.Get(emp.SearchByUserName(loggedInAs)).setDegree(data[3]);
                emp.Get(emp.SearchByUserName(loggedInAs)).setJobCategory(jobcats);
                emp.Save();
                EmployeeHome(s);
            } catch (NumberFormatException cwd) {
                alertnum.showAndWait();
                signupfields[5].setText(null);
                signupfields[4].setText(null);

            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        });

    }

    public void AdminEmployees(Stage stage) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> AdminHome(stage));

        GridPane register = new GridPane();

        ArrayList<Employee> list = emp.GetAll();
        Button[] deletebtns = new Button[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Employee emps = list.get(i);
            register.add(new Label("Name : " + emps.getName()), 0, (i * 5) + 0);
            register.add(new Label("UserName : " + emps.getUserName()), 0, (i * 5) + 1);
            register.add(new Label("Age : " + String.valueOf(emps.getAge())), 0, (i * 5) + 2);
            register.add(new Label("Degree: " + emps.getDegree()), 0, (i * 5) + 3);
            register.add(new Label(""), 0, (i * 5) + 4);
            deletebtns[i] = new Button("Delete");
            deletebtns[i].setId(String.valueOf(emps.getUserName()));
            register.add(deletebtns[i], 1, (i * 5) + 2);
            deletebtns[i].setOnAction(e -> {
                try {                    
                    interview.DELETE(emp.Get(emp.SearchByUserName(((Button) e.getSource()).getId())));
                    this.emp.Delete(this.emp.SearchByUserName(((Button) e.getSource()).getId()));
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                } finally {
                    AdminEmployees(stage);
                }
            });
        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(register);
        Scene s = new Scene(new VBox(backButton, sp), 400, 300);
        stage.setScene(s);
        stage.setTitle("Show Job Form");
        stage.show();
    }

    public void AdminAgencies(Stage stage) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> AdminHome(stage));

        GridPane register = new GridPane();

        ArrayList<Agency> list = agency.GetAll();
        Button[] deletebtns = new Button[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Agency agencys = list.get(i);
            register.add(new Label("Name : " + agencys.getName()), 0, (i * 5) + 0);
            register.add(new Label("UserName : " + agencys.getUserName()), 0, (i * 5) + 1);
            register.add(new Label("Address : " + agencys.getAddress()), 0, (i * 5) + 2);
            register.add(new Label("Industry: " + agencys.getIndustry()), 0, (i * 5) + 3);
            register.add(new Label(""), 0, (i * 5) + 4);
            deletebtns[i] = new Button("Delete");
            deletebtns[i].setId(String.valueOf(agencys.getUserName()));
            register.add(deletebtns[i], 1, (i * 5) + 2);
            deletebtns[i].setOnAction(e -> {
                try {
                    this.agency.Delete(this.agency.SearchByUserName(((Button) e.getSource()).getId()));
                    job.DELETE(((Button) e.getSource()).getId());
                    interview.DELETE(((Button) e.getSource()).getId());
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                } finally {
                    AdminAgencies(stage);
                }
            });
        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(register);
        Scene s = new Scene(new VBox(backButton, sp), 400, 350);
        stage.setScene(s);
        stage.setTitle("Show Agencies Form");
        stage.show();
    }

    public void ShowAllJob(Stage stage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("You have applied successfully");
        alert.setContentText("wait for response");

        Alert alertyears = new Alert(AlertType.ERROR);
        alertyears.setTitle("Error Dialog");
        alertyears.setHeaderText("you don't have the experience required for this job");
        alertyears.setContentText("Sorrt try for another job");
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> EmployeeHome(stage));

        GridPane register = new GridPane();

        ArrayList<Job> list = job.READ(emp.Get(emp.SearchByUserName(loggedInAs)));
        Button[] applybtns = new Button[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Job job = list.get(i);
            register.add(new Label(job.getJobTitle()), 0, (i * 7) + 0);
            register.add(new Label(job.getAgency().getName() + " " + job.getAgency().getAddress()), 0, (i * 7) + 1);
            register.add(new Label(format.format(job.getStart_Date())), 0, (i * 7) + 2);
            register.add(new Label((job.isFulltime()) ? ("Full time") : ("Part Time")), 0, (i * 7) + 3);
            register.add(new Label(job.getYearsofExperience() + " Years of Experience · " + String.join(" ", job.getJobCategory())), 0, (i * 7) + 4);
            register.add(new Label(job.getJobDescription()), 0, (i * 7) + 5);
            register.add(new Label(""), 0, (i * 7) + 6);
            applybtns[i] = new Button("Apply");
            applybtns[i].setId(String.valueOf(job.getId()));
            register.add(applybtns[i], 1, (i * 7) + 3);
            applybtns[i].setOnAction(e -> {
                try {
                    if (interview.CREATE(this.job.Get(this.job.getIndex(Integer.parseInt(((Button) e.getSource()).getId()))), emp.Get(emp.SearchByUserName(loggedInAs)))) {
                        alert.showAndWait();
                    } else {
                        alertyears.showAndWait();
                    }
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
            });
        }
        TextField text = new TextField();
        text.setOnKeyReleased(e -> {
            register.getChildren().clear();
            ArrayList<Job> lists = job.READ(emp.Get(emp.SearchByUserName(loggedInAs)), text.getText());
            Button[] apply = new Button[lists.size()];
            for (int i = 0; i < lists.size(); i++) {
                Job job = lists.get(i);
                register.add(new Label(job.getJobTitle()), 0, (i * 7) + 0);
                register.add(new Label(job.getAgency().getName() + " " + job.getAgency().getAddress()), 0, (i * 7) + 1);
                register.add(new Label(format.format(job.getStart_Date())), 0, (i * 7) + 2);
                register.add(new Label((job.isFulltime()) ? ("Full time") : ("Part Time")), 0, (i * 7) + 3);
                register.add(new Label(job.getYearsofExperience() + " Years of Experience · " + String.join(" ", job.getJobCategory())), 0, (i * 7) + 4);
                register.add(new Label(job.getJobDescription()), 0, (i * 7) + 5);
                register.add(new Label(""), 0, (i * 7) + 6);
                apply[i] = new Button("Apply");
                apply[i].setId(String.valueOf(job.getId()));
                register.add(apply[i], 1, (i * 5) + 2);
                apply[i].setOnAction(exc -> {
                    try {
                        if (interview.CREATE(this.job.Get(this.job.getIndex(Integer.parseInt(((Button) exc.getSource()).getId()))), emp.Get(emp.SearchByUserName(loggedInAs)))) {
                            alert.showAndWait();
                        } else {
                            alertyears.showAndWait();
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.toString());
                    }
                });
            }
        });
        ScrollPane sp = new ScrollPane();
        sp.setContent(register);
        Scene s = new Scene(new VBox(backButton, text, sp), 800,400);
        stage.setScene(s);
        stage.setTitle("Show Job Form");
        stage.show();
    }

    public void AdminJobs(Stage stage) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> AdminHome(stage));

        GridPane register = new GridPane();

        ArrayList<Job> list = job.READ();
        Button[] deletebtns = new Button[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Job job = list.get(i);
            register.add(new Label(job.getJobTitle()), 0, (i * 7) + 0);
            register.add(new Label(job.getAgency().getName() + " " + job.getAgency().getAddress()), 0, (i * 7) + 1);
            register.add(new Label(format.format(job.getStart_Date())), 0, (i * 7) + 2);
            register.add(new Label((job.isFulltime()) ? ("Full time") : ("Part Time")), 0, (i * 7) + 3);
            register.add(new Label(job.getYearsofExperience() + " Years of Experience · " + String.join(" ", job.getJobCategory())), 0, (i * 7) + 4);
            register.add(new Label(job.getJobDescription()), 0, (i * 7) + 5);
            register.add(new Label(""), 0, (i * 7) + 6);
            deletebtns[i] = new Button("Delete");
            deletebtns[i].setId(String.valueOf(job.getId()));
            register.add(deletebtns[i], 1, (i * 7) + 2);
            deletebtns[i].setOnAction(e -> {
                try {
                    this.job.DELETE(this.job.getIndex(Integer.parseInt(((Button) e.getSource()).getId())));
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                } finally {
                    AdminJobs(stage);
                }
            });
        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(register);
        Scene s = new Scene(new VBox(backButton, sp), 800,400);
        stage.setScene(s);
        stage.setTitle("Show Job Form");
        stage.show();
    }

    public void ShowJob(Stage stage) {

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> AgencyHome(stage));
        GridPane register = new GridPane();

        ArrayList<Job> list = job.READ(loggedInAs);
        Button[] deletebtns = new Button[list.size()];
        Button[] updatebtns = new Button[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Job job = list.get(i);
            register.add(new Label(job.getJobTitle()), 0, (i * 7) + 0);
            register.add(new Label(job.getAgency().getName() + " " + job.getAgency().getAddress()), 0, (i * 7) + 1);
            register.add(new Label(format.format(job.getStart_Date())), 0, (i * 7) + 2);
            register.add(new Label((job.isFulltime()) ? ("Full time") : ("Part Time")), 0, (i * 7) + 3);
            register.add(new Label(job.getYearsofExperience() + " Years of Experience · " + String.join(" ", job.getJobCategory())), 0, (i * 7) + 4);
            register.add(new Label(job.getJobDescription()), 0, (i * 7) + 5);
            register.add(new Label(""), 0, (i * 7) + 6);
            deletebtns[i] = new Button("Delete");
            deletebtns[i].setId(String.valueOf(job.getId()));
            updatebtns[i] = new Button("Update");
            updatebtns[i].setId(String.valueOf(job.getId()));
            register.add(deletebtns[i], 1, (i * 7) + 2);
            register.add(updatebtns[i], 2, (i * 7) + 2);
            updatebtns[i].setOnAction(e -> UpdateJob(stage, Integer.parseInt(((Button) e.getSource()).getId())));
            deletebtns[i].setOnAction(e -> {
                try {
                    this.job.DELETE(this.job.getIndex(Integer.parseInt(((Button) e.getSource()).getId())));
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                } finally {
                    ShowJob(stage);
                }
            });
        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(register);
        Scene s = new Scene(new VBox(backButton, sp), 800,400);
        stage.setScene(s);
        stage.setTitle("Show Job Form");
        stage.show();
    }
    
    public void AdminInterviews(Stage stage) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> AdminHome(stage));

        GridPane register = new GridPane();

        ArrayList<Interview> list = interview.READ();
        Button[] deletebtns = new Button[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Job job = list.get(i).getJob();
            Label expjobcatslabel = new Label(job.getYearsofExperience() + " Yrs of Exp · " + String.join(" ", job.getJobCategory()));
            expjobcatslabel.setWrapText(true);
            register.add(new Label(job.getJobTitle()), 0, (i * 7) + 0);
            register.add(new Label(job.getAgency().getName() + " " + job.getAgency().getAddress()), 0, (i * 7) + 1);
            register.add(new Label(format.format(job.getStart_Date())), 0, (i * 7) + 2);
            register.add(new Label((job.isFulltime()) ? ("Full time") : ("Part Time")), 0, (i * 7) + 3);
            register.add(expjobcatslabel, 0, (i * 7) + 4);
            register.add(new Label("Apply by " + list.get(i).getEmployee().getUserName()), 0, (i * 7) + 5);
            register.add(new Label(""), 0, (i * 7) + 6);
            deletebtns[i] = new Button("Delete");
            deletebtns[i].setId(String.valueOf(list.get(i).getID()));
            register.add(deletebtns[i], 1, (i * 7) + 2);
            deletebtns[i].setOnAction(e -> {
                try {
                    this.interview.DELETE(this.interview.getIndex(Integer.parseInt(((Button) e.getSource()).getId())));
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                } finally {
                    AdminInterviews(stage);
                }
            });
        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(register);
        Scene s = new Scene(new VBox(backButton, sp), 800,400);
        stage.setScene(s);
        stage.setTitle("Show Job Form");
        stage.show();
    }

    public void ShowinterviewsAgency(Stage stage) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> AgencyHome(stage));

        GridPane register = new GridPane();

        ArrayList<Interview> list = interview.READ(loggedInAs);
        Button[] deletebts = new Button[list.size()];
        Button[] approvebts = new Button[list.size()];
        DatePicker[] date = new DatePicker[list.size()];
        for (int i = 0; i < list.size(); i++) {
            register.add(new Label(list.get(i).getJob().getJobTitle()), 0, (i * 7) + 0);
            register.add(new Label(list.get(i).getEmployee().getName() + " " + list.get(i).getEmployee().getDegree()), 0, (i * 7) + 1);
            register.add(new Label("Age: " + String.valueOf(list.get(i).getEmployee().getAge())), 0, (i * 7) + 2);
            register.add(new Label(list.get(i).getEmployee().getYearsofExperience() + " Years of Experience · "), 0, (i * 7) + 3);
            register.add(new Label(String.join(" ", list.get(i).getEmployee().getJobCategory())), 0, (i * 7) + 4);
            register.add(new Label((list.get(i).getIsApproved() != null) ? ("Status : Approved Date is: " + format.format(list.get(i).getInterviewDate())) : ("still no responce")), 0, (i * 7) + 5);
            register.add(new Label(""), 0, (i * 7) + 6);
            deletebts[i] = new Button("Reject");
            deletebts[i].setId(String.valueOf(list.get(i).getID()));
            register.add(deletebts[i], 1, (i * 7) + 2);
            deletebts[i].setOnAction(e -> {
                interview.Get(interview.getIndex(Integer.parseInt(((Button) e.getSource()).getId()))).setIsApproved(false);
                ShowinterviewsAgency(stage);
            });
            approvebts[i] = new Button("Approve");
            approvebts[i].setId(String.valueOf(i));
            register.add(approvebts[i], 2, (i * 7) + 2);
            approvebts[i].setOnAction(e -> {
                // ShowinterviewsAgency(stage);
                //   System.out.println(Integer.parseInt(((Button) e.getSource()).getId()));

                date[Integer.parseInt(((Button) e.getSource()).getId())].setVisible(true);
            });
            date[i] = new DatePicker();
            date[i].setVisible(false);
            date[i].setId(String.valueOf(list.get(i).getID()));
            //   System.out.println(list.get(i).getID());
            register.add(date[i], 3, (i * 7) + 2);
            date[i].setOnAction(e -> {
                System.out.println("hi");
                Date approvedate = new Date();

                try {
                    //    System.out.println("eerror before here");
                    approvedate = Date.from(((DatePicker) e.getSource()).getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    //  System.out.println(approvedate);
                } catch (Exception exh) {
                    System.out.println(exh.toString());
                }
                System.out.println(Integer.parseInt(((DatePicker) e.getSource()).getId()));
                interview.Get(interview.getIndex(Integer.parseInt(((DatePicker) e.getSource()).getId()))).setIsApproved(true);
                //System.out.println(interview.Get(interview.getIndex(Integer.parseInt(((DatePicker) e.getSource()).getId()))).getIsApproved());
                interview.Get(interview.getIndex(Integer.parseInt(((DatePicker) e.getSource()).getId()))).setInterviewDate(approvedate);
                //System.out.println(interview.Get(interview.getIndex(Integer.parseInt(((DatePicker) e.getSource()).getId()))).getInterviewDate());
                ShowinterviewsAgency(stage);
            });

        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(register);
        Scene s = new Scene(new VBox(backButton, sp), 800,400);
        stage.setScene(s);
        stage.setTitle("Show Job Form");
        stage.show();
    }

    public void ShowinterviewsEmployee(Stage stage) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> EmployeeHome(stage));

        GridPane register = new GridPane();

        ArrayList<Interview> list = interview.READ(emp.Get(emp.SearchByUserName(loggedInAs)));
        Button[] deletebts = new Button[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Job job = list.get(i).getJob();
            Label expjobcatslabel = new Label("Experienced · " + job.getYearsofExperience() + " Yrs of Exp · " + String.join(" ", job.getJobCategory()));
            expjobcatslabel.setWrapText(true);
            register.add(new Label(job.getJobTitle()), 0, (i * 7) + 0);
            register.add(new Label(job.getAgency().getName() + " " + job.getAgency().getAddress()), 0, (i * 7) + 1);
            register.add(new Label(format.format(job.getStart_Date())), 0, (i * 7) + 2);
            register.add(new Label((job.isFulltime()) ? ("Full time") : ("Part Time")), 0, (i * 7) + 3);
            register.add(expjobcatslabel, 0, (i * 7) + 4);
            register.add(new Label((list.get(i).getIsApproved() != null) ? ((list.get(i).getIsApproved()) ? ("Status : Approved Date is: " + format.format(list.get(i).getInterviewDate())):("Status : Rejected")) : ("still no responce")), 0, (i * 7) + 5);
            register.add(new Label(""), 0, (i * 7) + 6);
            deletebts[i] = new Button("Delete");
            deletebts[i].setId(String.valueOf(list.get(i).getID()));
            register.add(deletebts[i], 1, (i * 7) + 2);
            deletebts[i].setOnAction(e -> {
                try {
                    interview.DELETE(interview.getIndex(Integer.parseInt(((Button) e.getSource()).getId())));
                    ShowinterviewsEmployee(stage);
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                }
            });
        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(register);
        Scene s = new Scene(new VBox(backButton, sp), 800,400);
        stage.setScene(s);
        stage.setTitle("Show Job Form");
        stage.show();
    }

    public static void main(String[] args) throws IOException, ParseException, FileNotFoundException, ClassNotFoundException {
        agency.Sync();
        emp.Sync();
        job.Sync();
        interview.Sync();
        launch(args);
        agency.Save();
        emp.Save();
        job.SAVE();
        interview.SAVE();
    }

}
