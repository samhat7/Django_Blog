/*
 * Person.java
 *
 * Created on Nov 15, 2007, 5:15:38 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.accounting.Invoice;
import bean.person.PersonDependent;
import bean.person.PersonInformation;
import bean.reference.Department;
import bean.sales.Payment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import service.util.AbstractIBean;
import springbean.IProcess;
import template.ActionButton;
import template.ActionButtons;
import util.DBClient;
import util.DateUtil;
import template.Display;
import template.Displays;
import template.UITemplate;
import template.screen.TemplateDefault;
import util.BeanUtil;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "Person")
@UITemplate(template = TemplateDefault.class, canBackup=false, 
		columnSearch = {"schoolYear","studentNumber", "lastName", "firstName", "course" , "gradeLevel","section","age","personType"})
@DiscriminatorColumn(name = "personType", discriminatorType = DiscriminatorType.STRING)
@ActionButtons({
    @ActionButton(label="Top1 Button", name="sample1", top=true), 
    @ActionButton(label="Top2 Button", name="sample1", top=true), 
    @ActionButton(label="Top3 Button", name="sample1", top=true), 
    @ActionButton(label="Top4 Button", name="sample1", top=true), 
    @ActionButton(label="Bottom1 Button", name="sample1"),
    @ActionButton(label="Bottom2 Button", name="sample1"),
    @ActionButton(label="Bottom3 Button", name="sample1"),
    @ActionButton(label="Bottom4 Button", name="sample1")
})
@Displays({
        @Display(name="personId"),
        @Display(name="hiredDate"),
        @Display(name="shortName"),
        @Display(name="lastName"),
        @Display(name="firstName"),
        @Display(name="middleInitial"),
        @Display(name="personType"),
        @Display(name="gender", type = "Combo", modelCombo = {"MALE", "FEMALE"},addInfoOnly=true, width = 100),
        @Display(name="maritalStatus", type = "Combo", modelCombo = {"SINGLE", "MARRIED"}, addInfoOnly=true, width = 100),
        @Display(name="birthDate"),
        @Display(name="department", type = "PopSearch", linktoBean=Department.class, width = 200),
        @Display(name="age"),
        @Display(name="placeOfBirth"),
        @Display(name="citizenship"),
        @Display(name="religion"),
        @Display(name="email"),
        @Display(name="contactNumber"),
        @Display(name="contactNumber1"),
        @Display(name="address"),
        @Display(name="address1"),
        @Display(name="tinNumber"),
        @Display(name="sssNumber"),
        @Display(name="pagibigNumber"),
        @Display(name="status"),
        @Display(name="position",type = "Combo", modelCombo = {"PHYSICIAN", "NURSE", "MAINTENANCE", "MEDICAL DIRECTOR"}),
        @Display(name="occupation"),
        @Display(name="mother"),
        @Display(name="father"),
        @Display(name="spouse"),
        @Display(name="im1"),
        @Display(name="im2"),
        @Display(name="mobilePhone"),
        @Display(name="userid"),
        @Display(name="guardianOccupation"),
        @Display(name="guardianName"),
        @Display(name="guardianRelationship"),
        @Display(name="guardianAddress"),
        @Display(name="guardianContactNumber"),
        @Display(name="zipCode"),
        @Display(name="streetNumber"),
        @Display(name="barangay"),
        @Display(name="townDistrict"),
        @Display(name="cityProvince"),
        @Display(name="isActive"),
        @Display(name="place"),
        @Display(name="state"),
        @Display(name="country"),
        @Display(name="fax"),
        @Display(name="companyName"),
        @Display(name="provincialAddress"),
        @Display(name="companyTelephoneNumber"),
        @Display(name="companyAddress")
})
public class Person extends AbstractIBean implements Serializable {

    @Id
    @Column(name = "personId", nullable = false)
    public Integer personId;
    @Column(name = "studentNumber", length = 20)
    public String studentNumber;
    @Column(name = "gradeLevel", length = 30)
    public String gradeLevel;
    @Column(name = "accountNumber")
    public String accountNumber;
    @Column(name = "parentId")
    public int parentId;
    @Column(name = "individual")
    public int individual;
    @Column(name = "hiredDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date hiredDate;
    @Column(name = "shortName")
    public String shortName;
    @Column(name = "mIName")
    public String mIName;
    @Column(name = "parentsMaritalStatus")
    public String parentsMaritalStatus;
    @Column(name = "lastName", nullable = false)
    public String lastName;
    @Column(name = "firstName", nullable = false)
    public String firstName;
    @Column(name = "middleInitial")
    public String middleInitial;
    @Column(name = "maidenName")
    public String maidenName;
    @Column(name = "personType", nullable = false)
    public String personType;
    @Column(name = "gender")
    public String gender;
    @Column(name = "maritalStatus", length = 10)
    public String maritalStatus;
    @Column(name = "birthDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date birthDate;
    @Column(name = "department", length = 50)
    public String department;
    @Column(name = "age")
    public int age;
    @Column(name = "placeOfBirth")
    public String placeOfBirth;
    @Column(name = "citizenship", length = 50)
    public String citizenship;
    @Column(name = "religion", length = 50)
    public String religion;
    @Column(name = "email")
    public String email;
    @Column(name = "contactNumber", length = 50)
    public String contactNumber;
    @Column(name = "contactNumber1", length = 50)
    public String contactNumber1;
    @Column(name = "address", length = 150)
    public String address;
    @Column(name = "address1", length = 150)
    public String address1;
    @Column(name = "tinNumber", length = 150)
    public String tinNumber;
    @Column(name = "sssNumber", length = 20)
    public String sssNumber;
    @Column(name = "status")
    public String status;
    @Column(name = "addStatus")
    public String addStatus;
    @Column(name = "position", length = 50)
    public String position;
    @Column(name = "occupation", length = 50)
    public String occupation;
    @Column(name = "mother", length = 150)
    public String mother;
    @Column(name = "father", length = 150)
    public String father;
    @Column(name = "spouse")
    public String spouse;
    @Column(name = "im1")
    public String im1;
    @Column(name = "im2")
    public String im2;
    @Column(name = "mobilePhone")
    public String mobilePhone;
    @Column(name = "userid")
    public String userid;
    @Column(name = "tempPass")
    public String tempPass;
    @Column(name = "guardianOccupation")
    public String guardianOccupation;
    @Column(name = "guardianName", length = 50)
    public String guardianName;
    @Column(name = "guardianRelationship", length = 50)
    public String guardianRelationship;
    @Column(name = "guardianAddress")
    public String guardianAddress;
    @Column(name = "guardianContactNumber", length = 50)
    public String guardianContactNumber;
    @Column(name = "guardianName2", length = 50)
    public String guardianName2;
    @Column(name = "guardianRelationship2", length = 50)
    public String guardianRelationship2;
    @Column(name = "guardianContactNumber2", length = 50)
    public String guardianContactNumber2;
    @Column(name = "zipCode")
    public String zipCode;
    @Column(name = "streetNumber")
    public String streetNumber;
    @Column(name = "barangay")
    public String barangay;
    @Column(name = "townDistrict")
    public String townDistrict;
    @Column(name = "cityProvince")
    public String cityProvince;
    @Column(name = "isActive")
    public boolean isActive = true;
    @Column(name = "place", length = 100)
    public String place;
    @Column(name = "state", length = 5)
    public String state;
    @Column(name = "country")
    public String country;
    @Column(name = "fax", length = 20)
    public String fax;
    @Column(name = "companyName", length = 255)
    public String companyName;
    @Column(name = "provincialAddress")
    public String provincialAddress;
    @Column(name = "companyTelephoneNumber", length = 50)
    public String companyTelephoneNumber;
    @Column(name = "companyAddress", length = 255)
    public String companyAddress;
    @Column(name = "section", length = 150)
    public String section;
    @Column(name = "childCount")
    public int childCount;
    @Column(name = "prcLicenseNumber")
    public String prcLicenseNumber;
    @Column(name = "isLETPasser")
    public boolean isLETPasser;
    @Column(name = "letRating")
    public int letRating;
    @Column(name = "prcValidUntil")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date prcValidUntil;
    @Column(name = "prcRegistrationDate")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    public Date prcRegistrationDate;
    @Column(name = "prcLevel")
    public String prcLevel;
    @Column(name = "aCROrICRNumber")
    public String aCROrICRNumber;
    @Column(name = "weight")
    public String weight;
    @Column(name = "height")
    public String height;
    @Column(name = "specialSkills")
    public String specialSkills;
    @Column(name = "hobbies")
    public String hobbies;
    @Column(name = "basicPay")
    public double basicPay;
    @Column(name = "sickLeaveBenefit")
    public int sickLeaveBenefit;
    @Column(name = "vacLeaveBenefit")
    public int vacLeaveBenefit;
    @Column(name = "restDay1")
    public int restDay1;
    @Column(name = "restDay2")
    public int restDay2;
    @Column(name = "taxTable")
    public String taxTable;
    @Column(name = "contractType")
    public String contractType;
    @Column(name = "scheduleType")
    public String scheduleType;
    @Column(name = "pagibigNumber")
    public String pagibigNumber;
    @Column(name = "philhealthNumber")
    public String philhealthNumber;
    @Column(name = "perDay")
    public double perDay;
    @Column(name = "perHourPay")
    public double perHourPay;
    @Column(name = "monthlyAllowance")
    public double monthlyAllowance;
    @Column(name = "gradeLevelDesc")
    public String gradeLevelDesc;
    @Column(name = "shift")
    public int shift;
    @Column(name = "officiallyRegistered")
    public boolean officiallyRegistered;
    @Column(name = "paymentStatus")
	public String paymentStatus;

    public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

    public String getPrcLevel() {
        return prcLevel;
    }

    public void setPrcLevel(String prcLevel) {
        this.prcLevel = prcLevel;
    }

	public boolean isOfficiallyRegistered() {
		return officiallyRegistered;
	}

	public void setOfficiallyRegistered(boolean officiallyRegistered) {
		this.officiallyRegistered = officiallyRegistered;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

        public String getMIName() {
        return mIName;
        }

        public void setMIName(String mIName) {
        this.mIName = mIName;
        }

	public String getAddStatus() {
		return addStatus;
	}

	public void setAddStatus(String addStatus) {
		this.addStatus = addStatus;
	}

	public String getGradeLevelDesc() {
		return gradeLevelDesc;
	}

	public void setGradeLevelDesc(String gradeLevelDesc) {
		this.gradeLevelDesc = gradeLevelDesc;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getParentsMaritalStatus() {
		return parentsMaritalStatus;
	}

	public void setParentsMaritalStatus(String parentsMaritalStatus) {
		this.parentsMaritalStatus = parentsMaritalStatus;
	}

	public boolean isLETPasser() {
		return isLETPasser;
	}

	public void setLETPasser(boolean isLETPasser) {
		this.isLETPasser = isLETPasser;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getGuardianContactNumber2() {
        return guardianContactNumber2;
    }

    public void setGuardianContactNumber2(String guardianContactNumber2) {
        this.guardianContactNumber2 = guardianContactNumber2;
    }

    public String getGuardianName2() {
        return guardianName2;
    }

    public void setGuardianName2(String guardianName2) {
        this.guardianName2 = guardianName2;
    }

    public String getGuardianRelationship2() {
        return guardianRelationship2;
    }

    public void setGuardianRelationship2(String guardianRelationship2) {
        this.guardianRelationship2 = guardianRelationship2;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public double getMonthlyAllowance() {
        return monthlyAllowance;
    }

    public void setMonthlyAllowance(double monthlyAllowance) {
        this.monthlyAllowance = monthlyAllowance;
    }

    public double getPerDay() {
        return perDay;
    }

    public void setPerDay(double perDay) {
        this.perDay = perDay;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getTaxTable() {
        return taxTable;
    }

    public void setTaxTable(String taxTable) {
        this.taxTable = taxTable;
    }

    public double getPerHourPay() {
        return perHourPay;
    }

    public void setPerHourPay(double perHourPay) {
        this.perHourPay = perHourPay;
    }

    public int getRestDay1() {
        return restDay1;
    }

    public void setRestDay1(int restDay1) {
        this.restDay1 = restDay1;
    }

    public int getRestDay2() {
        return restDay2;
    }

    public void setRestDay2(int restDay2) {
        this.restDay2 = restDay2;
    }

    public double getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }

    public java.util.Date getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(java.util.Date hiredDate) {
        this.hiredDate = hiredDate;
    }

    public int getSickLeaveBenefit() {
        return sickLeaveBenefit;
    }

    public void setSickLeaveBenefit(int sickLeaveBenefit) {
        this.sickLeaveBenefit = sickLeaveBenefit;
    }

    public int getVacLeaveBenefit() {
        return vacLeaveBenefit;
    }

    public void setVacLeaveBenefit(int vacLeaveBenefit) {
        this.vacLeaveBenefit = vacLeaveBenefit;
    }

    public String getPagibigNumber() {
        return pagibigNumber;
    }

    public void setPagibigNumber(String pagibigNumber) {
        this.pagibigNumber = pagibigNumber;
    }

    public String getPhilhealthNumber() {
        return philhealthNumber;
    }

    public void setPhilhealthNumber(String philhealthNumber) {
        this.philhealthNumber = philhealthNumber;
    }

    public String getACROrICRNumber() {
        return aCROrICRNumber;
    }

    public void setACROrICRNumber(String aCROrICRNumber) {
        this.aCROrICRNumber = aCROrICRNumber;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getSpecialSkills() {
        return specialSkills;
    }

    public void setSpecialSkills(String specialSkills) {
        this.specialSkills = specialSkills;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    public boolean isIsLETPasser() {
        return isLETPasser;
    }

    public void setIsLETPasser(boolean isLETPasser) {
        this.isLETPasser = isLETPasser;
    }

    public int getLetRating() {
        return letRating;
    }

    public void setLetRating(int lETRating) {
        this.letRating = lETRating;
    }

    public Date getPrcRegistrationDate() {
        return prcRegistrationDate;
    }

    public void setPrcRegistrationDate(Date pRCRegistrationDate) {
        this.prcRegistrationDate = pRCRegistrationDate;
    }

    public Date getPrcValidUntil() {
        return prcValidUntil;
    }

    public void setPrcValidUntil(Date pRCValidUntil) {
        this.prcValidUntil = pRCValidUntil;
    }

    public String getPrcLicenseNumber() {
        return prcLicenseNumber;
    }

    public void setPrcLicenseNumber(String prcLicenseNumber) {
        this.prcLicenseNumber = prcLicenseNumber;
    }
  
    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
    
    public java.lang.String getSection() {
        return section;
    }

    public void setSection(java.lang.String section) {
        this.section = section;
    }

    public String getTempPass() {
        return tempPass;
    }

    public void setTempPass(String tempPass) {
        this.tempPass = tempPass;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getIndividual() {
        return individual;
    }

    public void setIndividual(int individual) {
        this.individual = individual;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        if (isEmptyKey()) return "";
        if (notEmpty(middleInitial)) {
        	return BeanUtil.concat(this.lastName,", ",this.firstName," ",this.middleInitial.substring(0, 1),".");
        }
        return BeanUtil.concat(this.lastName,", ",this.firstName);
    }

    public java.lang.String getAddress1() {
        return address1;
    }

    public void setAddress1(java.lang.String address1) {
        this.address1 = address1;
    }

    public java.lang.String getContactNumber1() {
        return contactNumber1;
    }

    public void setContactNumber1(java.lang.String contactNumber1) {
        this.contactNumber1 = contactNumber1;
    }

    public java.util.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.util.Date birthDate) {
        this.birthDate = birthDate;
    }

    public java.lang.String getDepartment() {
        return department;
    }

    public void setDepartment(java.lang.String department) {
        this.department = department;
    }

    public java.lang.String getPosition() {
        return position;
    }

    public void setPosition(java.lang.String position) {
        this.position = position;
    }

    public String getFormattedTitle() {
        if (personId == null || personId == 0) {
            return "";
        } else {
            if (lastName.equals("XXX")) {
                return shortName;
            }
            return BeanUtil.concat(lastName,", ",firstName);
        }
    }

    public java.lang.String getShortName() {
        return shortName;
    }

    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }

    public java.lang.String getOccupation() {
        return occupation;
    }

    public void setOccupation(java.lang.String occupation) {
        this.occupation = occupation;
    }

    public java.lang.String getMother() {
        return mother;
    }

    public void setMother(java.lang.String mother) {
        this.mother = mother;
    }

    public java.lang.String getFather() {
        return father;
    }

    public void setFather(java.lang.String father) {
        this.father = father;
    }

    public java.lang.String getSpouse() {
        return spouse;
    }

    public void setSpouse(java.lang.String spouse) {
        this.spouse = spouse;
    }

    public java.lang.String getUserid() {
        return userid;
    }

    public void setUserid(java.lang.String userid) {
        this.userid = userid;
    }

    public String getDateOfBirth() {
        if (birthDate == null) {
            return "";
        }
        return DateUtil.formatDate(birthDate, "MMM dd, yyyy");
    }

    public java.lang.String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(java.lang.String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public java.lang.String getIm2() {
        return im2;
    }

    public void setIm2(java.lang.String im2) {
        this.im2 = im2;
    }

    public java.lang.String getIm1() {
        return im1;
    }

    public void setIm1(java.lang.String im1) {
        this.im1 = im1;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public void login() {
        IProcess.Process.getInstance().doLogin(this);
    }

    public void logout() {
        IProcess.Process.getInstance().doLogout(this);
    }

    public java.lang.String getGuardianOccupation() {
        return guardianOccupation;
    }

    public void setGuardianOccupation(java.lang.String guardianOccupation) {
        this.guardianOccupation = guardianOccupation;
    }

    public java.lang.String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(java.lang.String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public static Person extractObject(Object personId) {
        return (Person) firstRecord("SELECT a FROM Person a WHERE a.personId=",personId);
    }

    public java.lang.String getZipCode() {
        return zipCode;
    }

    public void setZipCode(java.lang.String zipCode) {
        this.zipCode = zipCode;
    }

    public java.lang.String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(java.lang.String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public java.lang.String getBarangay() {
        return barangay;
    }

    public void setBarangay(java.lang.String barangay) {
        this.barangay = barangay;
    }

    public java.lang.String getTownDistrict() {
        return townDistrict;
    }

    public void setTownDistrict(java.lang.String townDistrict) {
        this.townDistrict = townDistrict;
    }

    public java.lang.String getCityProvince() {
        return cityProvince;
    }

    public void setCityProvince(java.lang.String cityProvince) {
        this.cityProvince = cityProvince;
    }

    public java.lang.String getSssNumber() {
        return sssNumber;
    }

    public void setSssNumber(java.lang.String sssNumber) {
        this.sssNumber = sssNumber;
    }

    public java.lang.String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(java.lang.String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public boolean getIsActive() {
        return isActive;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria, "lastName", "firstName", "studentNumber");
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public java.lang.String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(java.lang.String guardianName) {
        this.guardianName = guardianName;
    }

    public java.lang.String getGuardianAddress() {
        return guardianAddress;
    }

    public void setGuardianAddress(java.lang.String guardianAddress) {
        this.guardianAddress = guardianAddress;
    }

    public java.lang.String getGuardianContactNumber() {
        return guardianContactNumber;
    }

    public void setGuardianContactNumber(java.lang.String guardianContactNumber) {
        this.guardianContactNumber = guardianContactNumber;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTelephoneNumber() {
        return companyTelephoneNumber;
    }

    public void setCompanyTelephoneNumber(String companyTelephoneNumber) {
        this.companyTelephoneNumber = companyTelephoneNumber;
    }

    public String getGuardianRelationship() {
        return guardianRelationship;
    }

    public void setGuardianRelationship(String guardianRelationship) {
        this.guardianRelationship = guardianRelationship;
    }

    public String getProvincialAddress() {
        return provincialAddress;
    }

    public void setProvincialAddress(String provincialAddress) {
        this.provincialAddress = provincialAddress;
    }

    @Override
    public String orderBy() {
        return "a.lastName";
    }  
    
    public String extractEmailSubject() {
        return BeanUtil.concat("TO: ",toString());
    }
    public String extractEmailContent() {
        StringBuffer sb = new StringBuffer();
        sb.append("Dear ").append(toString()).append(", \n");
        sb.append("\n\n");
        sb.append("Sincerely Yours,\n");
        return sb.toString();
    }
    public int extractEmailCustomerId() {
        return personId;
    }
    public String extractEmailRecipient() {
        return email;
    }

    @Override
    public void delete() {
        //delete child records using personid
        super.delete();
    }

    @Override
    public void save() {
		gradeLevelDesc = Invoice.extractGradeLevelDescription(gradeLevel);
//      setup middle initial
        try {
        	if (middleInitial!=null && !middleInitial.isEmpty()) {
                if (middleInitial.contains(" ")) {
                    String[] arr = middleInitial.split(" ");
                    mIName = BeanUtil.concat(arr[0].substring(0, 1),". ");
                    mIName += BeanUtil.concat(arr[1].substring(0, 1),".");
                }
                else {
                	mIName = BeanUtil.concat(middleInitial.substring(0, 1),".");
                }
        	}
        	else {
        		middleInitial = "";
        	}
        }
        catch (Exception e) {
        }
        super.save();
        PersonInformation info = (PersonInformation) AbstractIBean.firstRecord("SELECT a FROM PersonInformation a WHERE a.personId=",personId);
        if (info==null) {
            info = new PersonInformation();
            info.personId = personId;
            info.save();
        }
//        update age
        DBClient.runSQLNative("update person set age=floor(DATEDIFF(sysdate(), birthdate)/365) where birthdate is not null");
    }
    
    public PersonDependent extractOrCreateDependent(String relation) {
        String sql = BeanUtil.concat("SELECT a FROM PersonDependent a WHERE a.relation='",relation,"' AND a.personId=",personId);
        PersonDependent dep = (PersonDependent) AbstractIBean.firstRecord(sql);
        if (dep==null || dep.isEmptyKey()) {
            dep = new PersonDependent();
            dep.personId = personId;
            dep.relation = relation;
            dep.save();
            clearCache(sql);
        }
        return dep;
    }
    
	@Override
	public void setupIndex() {
		DBClient.runSQLNative("UPDATE Person SET shift=1 WHERE shift IS NULL");
		DBClient.runSQLNative("UPDATE Person SET gender='MALE' WHERE gender LIKE 'M%' OR gender LIKE 'B%'");
		DBClient.runSQLNative("UPDATE Person SET gender='FEMALE' WHERE gender IS NULL OR gender LIKE 'F%' OR gender LIKE 'G%'");
		
		runIndex(1, "lastName","firstName","studentNumber","personType");
		runIndex(2, "personType");
		runIndex(3, "userid");
		runIndex(4, "gradeLevel");
		runIndex(5, "gradeLevel", "shift");
		runIndex(6, "birthDate");
		runIndex(7, "status", "section");
	}
	
    public List<Payment> extractPayments(String schoolYear) {
      return list("SELECT a FROM Payment a WHERE a.schoolYear='",schoolYear,"' AND a.paidBy=",personId," ORDER BY a.dueDate");
  }

}
