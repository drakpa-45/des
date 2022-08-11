package bt.gov.ditt.does.Dto;

import java.io.Serializable;

/**
 * Created by USER on 11/19/2019.
 */
public class MenuDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String menuId;
    private String menuName;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
