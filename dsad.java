/*
 * AppMenu.java
 * 
 * Created on Aug 9, 2008, 2:28:50 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.admin;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import service.util.AbstractIBean;
import springbean.AAAConfig;
import template.*;
import template.screen.TemplateSinglePage;
import util.BeanUtil;
import util.DBClient;

/**
 *
 * @author Entokwaa
 */
@Entity
@Table(name = "AppMenu")
@UITemplate(template = TemplateSinglePage.class, gridCount = 4, columnSearch = {"moduleLabel", "menuLabel", "moduleName", "sortNum"}, orderBy = " a.sortNum")
@Displays({
    @Display(name="moduleName", upCase=false),
    @Display(name="menuLabel", upCase=false),
    @Display(name="moduleLabel", upCase=false),
    @Display(name="sortNum"),
    @Display(name="description", type = "TextArea", gridFieldWidth=3, width=-1)
})
@ActionButtons({
    @ActionButton(name="btnSetupGroups", label="Setup User Groups", parentOnly=false)
})
public class AppMenu extends AbstractIBean implements Serializable {
    @Id
    @Column(name = "moduleName", nullable = false)
    public String moduleName;
    @Column(name = "menuLabel")
    public String menuLabel;
    @Column(name = "moduleLabel")
    public String moduleLabel;
    @Column(name = "description")
    public String description;
    @Column(name = "sortNum")
    public int sortNum;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    public AppMenu(String text, String text0, String text1, int i) {
        this.moduleLabel = text;
        this.moduleName = text0;
        this.menuLabel = text1;
        this.sortNum = i;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getModuleLabel() {
        return moduleLabel;
    }

    public void setModuleLabel(String moduleLabel) {
        this.moduleLabel = moduleLabel;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public AppMenu() {
    }

    @Override
    public String toString() {
        return BeanUtil.concat(moduleName," - ",moduleLabel);
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }
        
    public static AppMenu createAppMenuObj(String moduleName, String moduleLabel, String menuLabel, int sortNumber) {
        AppMenu stud = new AppMenu();
        stud.moduleName = moduleName;
        stud.menuLabel = menuLabel;
        stud.moduleLabel = moduleLabel;
        stud.sortNum = sortNumber;
        return stud;
    }
    
    @Override
    public void runSetup() {
//        select concat('createAppMenuObj("',moduleName,'", "',moduleLabel,'", "',menuLabel,'", ',sortNum,').save();') from appmenu order by sortNum
        List lst = DBClient.getList("SELECT a FROM AppMenu a");
        if (lst!=null && !lst.isEmpty()) {
            return;
        }
        
        String module = AAAConfig.getInstance().getModule();
        if ("SCHOOL".equalsIgnoreCase(module)) {
            createAppMenuObj("Admission", "Main", "Admission", 110).save();
            createAppMenuObj("Enrollment", "Main", "Enrollment", 120).save();
            createAppMenuObj("CollegeEnrollmentExt", "Main", "College Enrollment", 121).save();
            createAppMenuObj("Student", "Main", "Student Info", 130).save();
            createAppMenuObj("LibraryForm", "Main", "Library", 140).save();
            createAppMenuObj("FacultyGradingForm", "Main", "Faculty Tool", 160).save();
            createAppMenuObj("FacultyAdminForm", "Main", "Faculty Admin", 161).save();
            createAppMenuObj("SRUForm", "Main", "SRU Tool", 162).save();
            createAppMenuObj("ScheduleExt", "Main", "Student Tool", 163).save();
            createAppMenuObj("Schedule", "Main", "Schedule", 170).save();
            createAppMenuObj("StudentEncounterClinic", "Main", "Clinic", 180).save();
            createAppMenuObj("StudentEncounterGuidanceForm", "Main", "Guidance", 185).save();
            createAppMenuObj("OSAForm", "Main", "OSA", 190).save();
            createAppMenuObj("CashierTransactionForm", "Main", "Cashier", 191).save();
//            createAppMenuObj("SectioningForm", "Main", "Organization List", 195).save();
//            createAppMenuObj("CashierGenericForm", "Main", "Cashier", 191).save();
            createAppMenuObj("SchoolReferenceForm", "Reference", "School Reference", 200).save();
            createAppMenuObj("StudentExt", "Reference", "Student Reference", 210).save();
            createAppMenuObj("KioskReferenceForm", "Reference", "Kiosk Reference", 220).save();
            createAppMenuObj("AttendanceForm", "Reference", "Attendance", 230).save();
            createAppMenuObj("SendMessageAccount", "Reference", "Messaging Config", 240).save();
            createAppMenuObj("EmployeeSchoolForm", "Accounting", "School Employee", 310).save();
            createAppMenuObj("SchoolEmployeeApplicantExt", "Accounting", "Applicant", 320).save();
            createAppMenuObj("AccountingReferenceForm", "Accounting", "Accounting Reference", 330).save();
            createAppMenuObj("SchoolAccountingProcessForm", "Accounting", "Accounting Process", 340).save();
            createAppMenuObj("PayrollForm", "Accounting", "Payroll", 341).save();
            createAppMenuObj("PurchaseOrderForm", "Accounting", "Purchase Order", 351).save();
            createAppMenuObj("InventoryForm", "Accounting", "Inventory", 352).save();
        }
        else if ("TOOL".equalsIgnoreCase(module)) {
            createAppMenuObj("CustomerContactForm", "Main", "Customer", 100).save();
            createAppMenuObj("CustomerCall", "Main", "Appointments", 110).save();
            createAppMenuObj("SupportTicket", "Main", "Support Ticket", 120).save();
            createAppMenuObj("CashierTransactionForm", "Main", "Cashier", 191).save();
            createAppMenuObj("BookMark", "Main", "Bookmark", 130).save();
            createAppMenuObj("ToolReferenceForm", "Reference", "Reference", 200).save();
            createAppMenuObj("ProductExt", "Reference", "Product", 210).save();
            createAppMenuObj("KnowledgeBase", "Reference", "Knowledge Base", 220).save();
            createAppMenuObj("Employee", "Accounting", "Employee", 300).save();
            createAppMenuObj("AccountingReferenceForm", "Accounting", "Accounting Reference", 310).save();
            createAppMenuObj("AccountingProcessForm", "Accounting", "Accounting Process", 320).save();
            createAppMenuObj("PayrollForm", "Accounting", "Payroll", 341).save();
        }
        else if ("HOSPITAL".equalsIgnoreCase(module)) {
            createAppMenuObj("OutPatient", "Main", "Out Patient", 100).save();
            createAppMenuObj("OutPatientClinicExt", "Main", "Out Patient", 101).save();
            createAppMenuObj("Emergency", "Main", "Emergency", 110).save();
            createAppMenuObj("AdmissionForm", "Main", "Admission", 120).save();
            createAppMenuObj("RoomHospitalExt", "Main", "Releasing", 120).save();
            createAppMenuObj("Patient", "Main", "Patient", 130).save();
            createAppMenuObj("PharmacyForm", "Main", "Pharmacy", 140).save();
            createAppMenuObj("Physician", "Main", "Physician", 150).save();
            createAppMenuObj("CashierTransactionForm", "Main", "Cashier", 191).save();
            createAppMenuObj("HospitalClaimForm", "Main", "Hospital Claim", 192).save();
            createAppMenuObj("ClinicReferenceForm", "Reference", "Reference", 200).save();
            createAppMenuObj("EmployeeHospitalForm", "Accounting", "Hospital Employee", 310).save();
            createAppMenuObj("HospitalEmployeeApplicantExt", "Accounting", "Applicant", 320).save();
            createAppMenuObj("AccountingReferenceForm", "Accounting", "Accounting Reference", 330).save();
            createAppMenuObj("HospitalAccountingProcessForm", "Accounting", "Accounting Process", 340).save();
            createAppMenuObj("PayrollForm", "Accounting", "Payroll", 341).save();
        }
        else if ("CARGO".equalsIgnoreCase(module)) {
            createAppMenuObj("CargoBookingForm", "Main", "Cargo Booking", 100).save();
            createAppMenuObj("CargoHandlingForm", "Main", "Cargo Handling", 110).save();
            createAppMenuObj("CargoForwardingForm", "Main", "Cargo Forwarding", 120).save();
            createAppMenuObj("CargoMessageInbox", "Main", "Message Inbox", 191).save();
            createAppMenuObj("FlightSchedule", "Reference", "Flight Schedule", 200).save();
            createAppMenuObj("Participant", "Reference", "Participant", 210).save();
            createAppMenuObj("UldNumber", "Reference", "Container Reference", 220).save();
            createAppMenuObj("CargoReferenceForm", "Reference", "Reference Data", 240).save();
        }
        else if ("ENERGY".equalsIgnoreCase(module)) {
            createAppMenuObj("Offer", "Main", "Offer", 100).save();
            createAppMenuObj("MarginalPlant", "Main", "Marginal Plants", 110).save();
            createAppMenuObj("PricesAndSchedule", "Main", "Prices And Schedule", 120).save();
            createAppMenuObj("Weather", "Main", "Weather", 120).save();
            createAppMenuObj("Region", "Reference", "Region", 400).save();
            createAppMenuObj("ParticipantType", "Reference", "Type", 410).save();
            createAppMenuObj("Participant", "Reference", "Participant", 420).save();
            createAppMenuObj("ParticipantResource", "Reference", "Resource", 430).save();
            createAppMenuObj("Weather", "Reference", "Weather", 440).save();
            createAppMenuObj("Location", "Reference", "Location", 450).save();
            createAppMenuObj("PayrollForm", "Accounting", "Payroll", 341).save();
        }
    }
}
