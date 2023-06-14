package com.managementemployee.admin.employee.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.managementemployee.admin.common.entity.BaseEntity;
import com.managementemployee.admin.common.exception.InvalidEmailException;
import com.managementemployee.admin.common.exception.InvalidPasswordException;
import com.managementemployee.admin.common.exception.InvalidSalaryException;
import com.managementemployee.admin.furlough.model.Furlough;
import com.managementemployee.admin.timesheet.model.Timesheet;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.net.PasswordAuthentication;
import java.rmi.MarshalException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity( name = "Employee")
@Table(name = "employee")
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int empId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "mid_name", nullable = false)
    private String midName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column (name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;



    @Column (name = "avatar")
    private String avatar;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "start_date")
    private LocalDate startDate;

    private String position;

    private float salary;

    private String status ;

    @Column(name = "type_of_employee", nullable = false)
    private String typeOfEmployee;

    @Transient
    private String fullName;


   @OneToMany(mappedBy = "employee",fetch = FetchType.EAGER)
   private List<Timesheet> timesheetList;

   @OneToMany(mappedBy = "employee",fetch = FetchType.EAGER)
   @JsonIgnore
   private List<Furlough> furloughList;

    public Employee() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmpIdInt() {
        return this.empId;
   }
    public String getEmpId() {
        return "EMP" + this.empId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName(){
        return  this.midName;
    }

    public void setMidName(String midName){
        this.midName = midName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.lastName + " " + this.midName + " " + this.firstName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) throws InvalidEmailException {
        var regex = "^[a-z]+[\\w._]*@gmail.com$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            this.email = email.toLowerCase();
        }
        else{
            this.email = null;
            var msg = "Email is invalid!";
            throw new InvalidEmailException(msg, email);
        }

    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) throws InvalidPasswordException{
        var regex = "^(.){6,}$"; // toi thieu 6 ki tu
        var regex2 = "^.*[a-z]+.*$"; // gom chu cai thuong
        var regex3 = "^.*[A-Z]+.*$"; // gom chu  cai hoa
        var regex4 = "^.*[0-9]+.*$"; // gom so
        var regex5 = "^.*[~!@#$%^&*]+.*$"; // gom ki tu dac biet
        var regex6 = email; // khong trung voi email

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(password);

        Pattern pattern3 = Pattern.compile(regex3);
        Matcher matcher3 = pattern3.matcher(password);

        Pattern pattern4 = Pattern.compile(regex4);
        Matcher matcher4 = pattern4.matcher(password);

        Pattern pattern5 = Pattern.compile(regex5);
        Matcher matcher5 = pattern5.matcher(password);

        Pattern pattern6 = Pattern.compile(regex6, Pattern.CASE_INSENSITIVE);
        Matcher matcher6 = pattern6.matcher(password);

        if(matcher.matches() && matcher3.matches() && matcher2.matches()
        &&  matcher4.matches() && matcher5.matches() && !matcher6.matches()){
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encryptedPassword  = bcrypt.encode(password);
            this.password = encryptedPassword;
        }
        else{
            this.password = null;
            var msg = "Password is invalid!";
            throw  new InvalidPasswordException(msg, password);
        }
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public LocalDate getDateOfBirth() {
         return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getStartDate(){
        return  this.startDate;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public String getPosition(){
        return  this.position;
    }


    public void setPosition(String position){
        this.position = position;
    }

    public float  getSalary() {
        return  this.salary;
    }

    public void setSalary(float salary) throws InvalidSalaryException{
        if(salary > 0 ) {
            this.salary = salary;
        }
        else{
            this.salary = 0;
            var msg = "This salary is invalid!!";
            throw new InvalidSalaryException(msg, salary);
        }
    }

    public String getTypeOfEmployee() { return typeOfEmployee;}

    public void setTypeOfEmployee(String typeOfEmployee) { this.typeOfEmployee = typeOfEmployee;}
}

