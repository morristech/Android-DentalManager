package za.co.morristech.dentalmanager.app.common.constants;

/**
 * Created by wademorris on 2014/09/10.
 */
public enum PatientGender {

    UNSPECIFIED(0),
    MALE(1),
    FEMALE(2);

    private int id;

    private PatientGender(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PatientGender getGenderById(int id) {
        PatientGender foundGender = PatientGender.UNSPECIFIED;

        for (PatientGender gender : PatientGender.values()) {
            if (gender.getId() == id) {
                foundGender = gender;
                break;
            }
        }

        return foundGender;
    }

}
