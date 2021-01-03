package jwt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jwt.Entity.Permission;


@JsonInclude(JsonInclude.Include.NON_NULL)

public class PermissionDto {

    private int id;
    private String screen;
    private boolean readData;
    private boolean writeData;
    private boolean deleteData;
    private UserDto user;

    public PermissionDto(Permission result) {
        this.id = result.getId();
        this.screen = result.getScreen();
        this.readData = result.isReadData();
        this.writeData = result.isWriteData();
        this.deleteData = result.isDeleteData();
        this.user = new UserDto(result.getUser());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public boolean isReadData() {
        return readData;
    }

    public void setReadData(boolean readData) {
        this.readData = readData;
    }

    public boolean isWriteData() {
        return writeData;
    }

    public void setWriteData(boolean writeData) {
        this.writeData = writeData;
    }

    public boolean isDeleteData() {
        return deleteData;
    }

    public void setDeleteData(boolean deleteData) {
        this.deleteData = deleteData;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
