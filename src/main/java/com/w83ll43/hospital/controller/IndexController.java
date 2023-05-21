package com.w83ll43.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.w83ll43.hospital.common.BaseContext;
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
    private PatientService patientService;

    @Resource
    private DoctorController doctorController;

    @Resource
    private AdminController adminController;

    @Resource
    private DrugService drugService;

    @Resource
    private WardService wardService;

    @Resource
    private BedService bedService;

    @Resource
    private SectionService sectionService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private BillService billService;

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
        Result<List<Department>> departmentsResult = adminController.getDepartments();
        List<Department> departments = departmentsResult.getData();
        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departments);
        return "doctor/edit_doctor";
    }

    @GetMapping("/add_doctor")
    public String addDoctor(Model model) {
        Result<List<Department>> departmentsResult = adminController.getDepartments();
        List<Department> departments = departmentsResult.getData();
        model.addAttribute("departments", departments);
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
        List<Patient> patients = patientService.list();
        model.addAttribute("patients", patients);
        return "patient/patients";
    }

    @GetMapping("/edit_patient/{id}")
    public String editPatient(@PathVariable("id") Long id, Model model) {
        Patient patient = patientService.getById(id);
        model.addAttribute("patient", patient);
        return "patient/edit_patient";
    }

    @GetMapping("/add_patient")
    public String addPatient() {
        return "patient/add_patient";
    }

    @GetMapping("/drugs")
    public String drugs(Model model) {
        List<Drug> drugs = drugService.list();
        model.addAttribute("drugs", drugs);
        return "drug/drugs";
    }

    @GetMapping("/edit_drug/{id}")
    public String editDrug(@PathVariable("id") Long id, Model model) {
        Drug drug = drugService.getById(id);
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
    public String registration(Model model) {
        Result<List<Doctor>> doctorsResult = adminController.getDoctors();
        List<Doctor> doctors = doctorsResult.getData();
        List<Patient> patients = patientService.list();
        List<Department> departments = departmentService.list();

        model.addAttribute("doctors", doctors);
        model.addAttribute("patients", patients);
        model.addAttribute("departments", departments);
        return "patient/registration";
    }

    @GetMapping("/wards")
    public String wards(Model model) {
        List<Ward> wards = wardService.list();
        List<Department> departments = departmentService.list();
        model.addAttribute("departments", departments);
        model.addAttribute("wards", wards);
        return "ward/wards";
    }

    @GetMapping("/edit_ward/{id}")
    public String editWard(@PathVariable("id") Long id, Model model) {
        Ward ward = wardService.getById(id);
        List<Department> departments = departmentService.list();
        model.addAttribute("departments", departments);
        model.addAttribute("ward", ward);
        return "ward/edit_ward";
    }

    @GetMapping("/add_ward")
    public String addWard(Model model) {
        List<Department> departments = departmentService.list();
        model.addAttribute("departments", departments);
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
    public String addBed(Model model) {
        List<Ward> wards = wardService.list();
        model.addAttribute("wards", wards);
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
        Result<List<Section>> sectionsResult = adminController.getSections();
        List<Section> sections = sectionsResult.getData();
        model.addAttribute("sections", sections);
        model.addAttribute("department", department);
        return "department/edit_department";
    }

    @GetMapping("/add_department")
    public String addDepartment(Model model) {
        Result<List<Section>> sectionsResult = adminController.getSections();
        List<Section> sections = sectionsResult.getData();
        model.addAttribute("sections", sections);
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
        List<Bed> beds = bedService.list();
        model.addAttribute("beds", beds);
        model.addAttribute("file", file);
        return "file/edit_file";
    }

    @GetMapping("/add_file")
    public String addFile(Model model) {
        List<Patient> patients = patientService.list();
        List<Bed> beds = bedService.list();

        // 住院需要预缴费
        LambdaQueryWrapper<Bill> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 类型 3 为预缴费类型
        queryWrapper.eq(Bill::getType, 3);
        // TODO 状态 1 为已缴费
        queryWrapper.eq(Bill::getStatus, 1);
        List<Bill> bills = billService.list(queryWrapper);

        model.addAttribute("patients", patients);
        model.addAttribute("beds", beds);
        model.addAttribute("bills", bills);
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
        List<HospitalizationFile> files = hospitalizationFileService.list();
        model.addAttribute("files", files);
        model.addAttribute("record", record);
        return "fileRecord/edit_record";
    }

    @GetMapping("/add_record")
    public String addRecord(Model model) {
        List<HospitalizationFile> files = hospitalizationFileService.list();
        model.addAttribute("files", files);
        return "fileRecord/add_record";
    }

    @GetMapping("/hospitalizations")
    public String hospitalizations(Model model) {
        LambdaQueryWrapper<Bill> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Bill::getType, 3);

        List<Bill> bills = billService.list(queryWrapper);
        model.addAttribute("bills", bills);
        return "feeDetails/hospitalizations";
    }

    @GetMapping("/add_hospitalization")
    public String addHospitalization(Model model) {
        List<Patient> patients = patientService.list();
        model.addAttribute("patientId", BaseContext.getCurrentId());
        model.addAttribute("patients", patients);
        return "feeDetails/add_hospitalization_bill";
    }

    @GetMapping("/add_prescription")
    public String addPrescription(Model model) {
        LambdaQueryWrapper<Drug> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(Drug::getStack, 0);
        List<Drug> drugs = drugService.list(lambdaQueryWrapper);
        List<Patient> patients = patientService.list();
        model.addAttribute("drugs", drugs);
        model.addAttribute("patients", patients);
        return "doctor/add_prescription";
    }
}
