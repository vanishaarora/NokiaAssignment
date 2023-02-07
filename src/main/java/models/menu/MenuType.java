package models.menu;

import com.google.gson.annotations.SerializedName;

public enum MenuType {
  @SerializedName("PARENT")
  PARENT,
  @SerializedName("CHILD")
  CHILD,
  @SerializedName("BACK")
  BACK
}


