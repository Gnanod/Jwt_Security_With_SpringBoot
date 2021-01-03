package jwt.Entity;

import javax.persistence.*;

@Entity
public class Permission extends DateTime{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String screen;
	private boolean readData;
	private boolean writeData;
	private boolean deleteData;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
	

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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Permission [id=" + id + ", screen=" + screen + ", readData=" + readData + ", writeData=" + writeData
				+ ", deleteData=" + deleteData + ", user=" + user + "]";
	}
	
}
