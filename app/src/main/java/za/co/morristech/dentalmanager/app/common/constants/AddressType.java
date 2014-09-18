package za.co.morristech.dentalmanager.app.common.constants;

/**
 * Created by wademorris on 2014/09/10.
 */
public enum AddressType {

    BILLING(1),
    POSTAL(2);

    private int id;

    private AddressType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static AddressType getTypeById(int id) {
        AddressType foundType = null;

        for (AddressType type : AddressType.values()) {
            if (type.getId() == id) {
                foundType = type;
                break;
            }
        }

        return foundType;
    }
}
