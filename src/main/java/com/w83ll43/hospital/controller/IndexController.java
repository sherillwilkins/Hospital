package com.w83ll43.hospital.controller;

import com.w83ll43.hospital.common.Result;
import com.w83ll43.hospital.model.domain.*;
import com.w83ll43.hospital.model.vo.FeeDetail;
import com.w83ll43.hospital.model.vo.PrescriptionVo;
import com.w83ll43.hospital.model.vo.Registration;
import com.w83ll43.hospital.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.expression.Dates;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @Resource
    private WardService wardService;

    @Resource
    private BedService bedService;

    @Resource
    private SectionService sectionService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private HospitalizationFileService hospitalizationFileService;

    @Resource
    private HospitalizationRecordService hospitalizationRecordService;

    @Resource
    private RegisteredOrderService registeredOrderService;

    @Resource
    private PrescriptionService prescriptionService;

    @GetMapping("/index")
    public String getIndex(Model model) {
        Dates dates = new Dates(Locale.CHINA);
        Date now = dates.createNow();
        model.addAttribute("now", now);
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
        return "user/add_user";
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
        return "user/users";
    }

    @GetMapping("/edit_user/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {

        return "user/edit_user";
    }

    @GetMapping("/add_user")
    public String addUser() {
        return "user/add_user";
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

    @GetMapping("/registrations")
    public String registrations(Model model) {
        List<Registration> registrations = registeredOrderService.getRegistrations();
        model.addAttribute("registrations", registrations);
        return "feeDetails/registrations";
    }

    @GetMapping("/prescriptions")
    public String prescriptions(Model model) {
        List<PrescriptionVo> prescriptionVos = prescriptionService.getPrescriptionVos();
        model.addAttribute("prescriptionVos", prescriptionVos);
        return "feeDetails/prescriptions";
    }

    @GetMapping("/prescriptionItemsFeeDetail/{billId}")
    public String prescriptionItemsFeeDetail(@PathVariable("billId") Long billId, Model model) {
        List<FeeDetail> feeDetails = prescriptionService.getPrescriptionFeeDetails(billId);
        model.addAttribute("feeDetails", feeDetails);
        return "feeDetails/prescriptionItemsFeeDetail";
    }

    @GetMapping("/registration")
    public String registration() {
        return "patient/registration";
    }

    @GetMapping("/wards")
    public String wards(Model model) {
        List<Ward> wards = wardService.list();
        model.addAttribute("wards", wards);
        return "ward/wards";
    }

    @GetMapping("/edit_ward/{id}")
    public String editWard(@PathVariable("id") Long id, Model model) {
        Ward ward = wardService.getById(id);
        model.addAttribute("ward", ward);
        return "ward/edit_ward";
    }

    @GetMapping("/add_ward")
    public String addWard() {
        return "ward/add_ward";
    }

    @GetMapping("/sections")
    public String sections(Model model) {
        List<Section> sections = sectionService.list();
        model.addAttribute("sections", sections);
        return "section/sections";
    }

    @GetMapping("/edit_section/{id}")
    public String editSection(@PathVariable("id") Long id, Model model) {
        Section section = sectionService.getById(id);
        model.addAttribute("section", section);
        return "section/edit_section";
    }

    @GetMapping("/add_section")
    public String addSection() {
        return "section/add_section";
    }

    @GetMapping("/beds")
    public String beds(Model model) {
        List<Bed> beds = bedService.list();
        model.addAttribute("beds", beds);
        return "bed/beds";
    }

    @GetMapping("/edit_bed/{id}")
    public String editBed(@PathVariable("id") Long id, Model model) {
        Bed bed = bedService.getById(id);
        model.addAttribute("bed", bed);
        return "bed/edit_bed";
    }

    @GetMapping("/add_bed")
    public String addBed() {
        return "bed/add_bed";
    }


    @GetMapping("/departments")
    public String departments(Model model) {
        List<Department> departments = departmentService.list();
        model.addAttribute("departments", departments);
        return "department/departments";
    }

    @GetMapping("/edit_department/{id}")
    public String editDepartment(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.getById(id);
        model.addAttribute("department", department);
        return "department/edit_department";
    }

    @GetMapping("/add_department")
    public String addDepartment() {
        return "department/add_department";
    }

    @GetMapping("/files")
    public String files(Model model) {
        List<HospitalizationFile> files = hospitalizationFileService.list();
        model.addAttribute("files", files);
        return "file/files";
    }

    @GetMapping("/edit_file/{id}")
    public String editFile(@PathVariable("id") Long id, Model model) {
        HospitalizationFile file = hospitalizationFileService.getById(id);
        model.addAttribute("file", file);
        return "file/edit_file";
    }

    @GetMapping("/add_file")
    public String addFile() {
        return "file/add_file";
    }

    @GetMapping("/records")
    public String records(Model model) {
        List<HospitalizationRecord> records = hospitalizationRecordService.list();
        model.addAttribute("records", records);
        return "fileRecord/records";
    }

    @GetMapping("/edit_record/{id}")
    public String editRecord(@PathVariable("id") Long id, Model model) {
        HospitalizationRecord record = hospitalizationRecordService.getById(id);
        model.addAttribute("record", record);
        return "fileRecord/edit_record";
    }

    @GetMapping("/add_record")
    public String addRecord() {
        return "fileRecord/add_record";
    }
}
