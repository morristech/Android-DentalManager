package za.co.morristech.dentalmanager.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.Date;

import za.co.morristech.dentalmanager.app.common.constants.PatientGender;

/**
 * Created by wademorris on 2014/09/10.
 */
public class Patient implements Serializable, Parcelable {

    private static final String TAG = Patient.class.getName();
    private static final long serialVersionUID = -6465142026191320681L;

    private int patientId;
    private int pictureId;
    private String firstName;
    private String lastName;
    private String fullName;
    private PatientGender gender;
    private Date dateOfBirth;
    private String address;
    private String allergies;
    private String diseases;
    private String occupation;
    private String parentOrTutor;
    private String phoneNumber;
    private String prescriptionDrugs;
    private String treatment;

    private boolean pregnant;
    private boolean underMedicalTreatment;


    public Patient(int n, String string, int n2, String string2, Date date, String string3, String string4, String string5, boolean bl, String string6, String string7, String string8, boolean bl2, String string9) {
        this.patientId = n;
        this.fullName = string;
        this.pictureId = n2;
        this.address = string2;
        this.dateOfBirth = date;
        this.occupation = string3;
        this.phoneNumber = string4;
        this.parentOrTutor = string5;
        this.underMedicalTreatment = bl;
        this.treatment = string6;
        this.prescriptionDrugs = string7;
        this.allergies = string8;
        this.pregnant = bl2;
        this.diseases = string9;
    }

    public Patient(String string, int n) {
        this.fullName = string;
        this.pictureId = n;
    }

    private Patient(Parcel in) {
        this.patientId = in.readInt();
        this.pictureId = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        int tmpGender = in.readInt();
        this.gender = tmpGender == -1 ? null : PatientGender.values()[tmpGender];
        long tmpDateOfBirth = in.readLong();
        this.dateOfBirth = tmpDateOfBirth == -1 ? null : new Date(tmpDateOfBirth);
        this.address = in.readString();
        this.allergies = in.readString();
        this.diseases = in.readString();
        this.occupation = in.readString();
        this.parentOrTutor = in.readString();
        this.phoneNumber = in.readString();
        this.prescriptionDrugs = in.readString();
        this.treatment = in.readString();
        this.pregnant = in.readByte() != 0;
        this.underMedicalTreatment = in.readByte() != 0;
    }

    public String getAddress() {
        return this.address;
    }

    public String getAlergies() {
        return this.allergies;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getDiseases() {
        return this.diseases;
    }

    public int getId() {
        return this.patientId;
    }

    public String getName() {
        if(TextUtils.isEmpty(this.fullName)) {
            if (!TextUtils.isEmpty(this.firstName) && !TextUtils.isEmpty(this.lastName)) {
                this.fullName = this.firstName + this.lastName;
            }
        }
        return this.fullName;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public String getParentOrTutor() {
        return this.parentOrTutor;
    }

    public String getPhone() {
        return this.phoneNumber;
    }

    public int getPicture() {
        return this.pictureId;
    }

    public String getPrescriptionDrugs() {
        return this.prescriptionDrugs;
    }

    public String getTreatment() {
        return this.treatment;
    }

    public boolean isPregnant() {
        return this.pregnant;
    }

    public boolean isUnderMedicalTreadment() {
        return this.underMedicalTreatment;
    }

    public void setAddress(String string) {
        this.address = string;
    }

    public void setAlergies(String string) {
        this.allergies = string;
    }

    public void setDateOfBirth(Date date) {
        this.dateOfBirth = date;
    }

    public void setDiseases(String string) {
        this.diseases = string;
    }

    public void setId(int n) {
        this.patientId = n;
    }

    public void setName(String string) {
        this.fullName = string;
    }

    public void setOccupation(String string) {
        this.occupation = string;
    }

    public void setParentOrTutor(String string) {
        this.parentOrTutor = string;
    }

    public void setPhone(String string) {
        this.phoneNumber = string;
    }

    public void setPicture(int n) {
        this.pictureId = n;
    }

    public void setPregnant(boolean bl) {
        this.pregnant = bl;
    }

    public void setPrescriptionDrugs(String string) {
        this.prescriptionDrugs = string;
    }

    public void setTreatment(String string) {
        this.treatment = string;
    }

    public void setUnderMedicalTreadment(boolean bl) {
        this.underMedicalTreatment = bl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.patientId);
        dest.writeInt(this.pictureId);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeInt(this.gender == null ? -1 : this.gender.ordinal());
        dest.writeLong(dateOfBirth != null ? dateOfBirth.getTime() : -1);
        dest.writeString(this.address);
        dest.writeString(this.allergies);
        dest.writeString(this.diseases);
        dest.writeString(this.occupation);
        dest.writeString(this.parentOrTutor);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.prescriptionDrugs);
        dest.writeString(this.treatment);
        dest.writeByte(pregnant ? (byte) 1 : (byte) 0);
        dest.writeByte(underMedicalTreatment ? (byte) 1 : (byte) 0);
    }

    public static final Parcelable.Creator<Patient> CREATOR = new Parcelable.Creator<Patient>() {
        public Patient createFromParcel(Parcel source) {
            return new Patient(source);
        }

        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };
}
