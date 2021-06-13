package logistics.mate.dto;

public class SignInDto {
	private String Username;
	private String Password;
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public SignInDto(String Username, String Password) {
		this.Username = Username;
		this.Password = Password;
	}
}
