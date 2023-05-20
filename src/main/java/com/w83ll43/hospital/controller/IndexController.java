package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.BaseContext;
import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.Doctor;
import com.w83ll43.hospital.model.domain.Drug;
import com.w83ll43.hospital.model.domain.Patient;
import com.w83ll43.hospital.model.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private PatientController patientController;

    @Resource
    private DoctorController doctorController;

    @Resource
    private AdminController adminController;

    @Resource
    private DrugController drugController;

    @GetMapping("/index")
    public String getIndex(Model model) {
        System.out.println(BaseContext.getCurrentId());
        System.out.println("model = " + model);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/text")
    public String text() {
        return "add_user";
    }

    @GetMapping("/index1")
    public String index1() {
        return "index1";
    }

    @GetMapping("/doctors")
    public String doctors(Model model) {
        Result<List<Doctor>> doctorsResult = adminController.getDoctors();
        List<Doctor> doctors = doctorsResult.getData();
        model.addAttribute("doctors", doctors);
        return "doctor/doctors";
    }

    @GetMapping("/edit_doctor/{id}")
    public String editDoctor(@PathVariable("id") Long id, Model model) {
        Result<Doctor> doctorResult = adminController.getDoctorById(id);
        Doctor doctor = doctorResult.getData();
        model.addAttribute("doctor", doctor);
        return "doctor/edit_doctor";
    }

    @GetMapping("/add_doctor")
    public String addDoctor() {
        return "doctor/add_doctor";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Result<List<User>> usersResult = adminController.getUsers();
        List<User> users = usersResult.getData();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/edit_user/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {

        return "edit_user";
    }

    @GetMapping("/add_user")
    public String addUser() {
        return "add_user";
    }

    @GetMapping("/patients")
    public String patients(Model model) {
        Result<List<Patient>> patientsResult = patientController.getPatients();
        List<Patient> patients = patientsResult.getData();
        model.addAttribute("patients", patients);
        return "patient/patients";
    }

    @GetMapping("/edit_patient/{id}")
    public String editPatient(@PathVariable("id") Long id, Model model) {
        Result<Patient> patientResult = patientController.getPatientById(id);
        Patient patient = patientResult.getData();
        model.addAttribute("patient", patient);
        return "patient/edit_patient";
    }

    @GetMapping("/add_patient")
    public String addPatient() {
        return "patient/add_patient";
    }

    @GetMapping("/drugs")
    public String drugs(Model model) {
        Result<List<Drug>> drugsResult = drugController.getDrugs();
        List<Drug> drugs = drugsResult.getData();
        model.addAttribute("drugs", drugs);
        return "drug/drugs";
    }

    @GetMapping("/edit_drug/{id}")
    public String editDrug(@PathVariable("id") Long id, Model model) {
        Result<Drug> drugResult = drugController.getDrugById(id);
        Drug drug = drugResult.getData();
        model.addAttribute("drug", drug);
        return "drug/edit_drug";
    }

    @GetMapping("/add_drug")
    public String addDrug() {
        return "drug/add_drug";
    }
}
