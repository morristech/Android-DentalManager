package za.co.morristech.dentalmanager.app.common.constants;

/**
 * Created by wademorris on 2014/09/10.
 */
public enum PatientRace {

    UNSPECIFIED(0),
    EUROPEAN(1),
    AFRICAN(2),
    MIXED(3),
    ASIAN(4),
    INDIAN(5);

    private int id;

    private PatientRace(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PatientRace getRaceById(int id) {
        PatientRace foundRace = PatientRace.UNSPECIFIED;

        for (PatientRace race : PatientRace.values()) {
            if (race.getId() == id) {
                foundRace = race;
                break;
            }
        }

        return foundRace;
    }
}
