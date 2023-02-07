package models.menu;

import com.google.gson.annotations.SerializedName;

public enum MenuCategory {
    @SerializedName("INITIAL")
    INITIAL,
    @SerializedName("DATA")
    DATA,
    @SerializedName("MANUFACTURERS")
    MANUFACTURERS,
    @SerializedName("COMPANY")
    COMPANY;

    public String getHandlerKey() {
        switch (this) {
            case INITIAL -> {
                return "InitialHandler";
            }
            case DATA -> {
                return "DataHandler";
            }
            case MANUFACTURERS -> {
                return "ManufacturersHandler";
            }
            case COMPANY -> {
                return "CompanyHandler";
            }
            default -> {
                return "";
            }
        }
    }
}
