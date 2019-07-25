package ua.training.admission.model.entity;

/**
 * User
 */
public class User {

	private Long id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Speciality speciality;
	private Role role;

	public enum Role {
		ADMIN,
		USER,
        GUEST
	}

	public static class Builder {

		private Long id;
		private String username;
		private String password;
		private String email;
		private String firstName;
		private String lastName;
		private Speciality speciality;
		private Role role;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder speciality(Speciality speciality) {
			this.speciality = speciality;
			return this;
		}

		public Builder role(Role role) {
			this.role = role;
			return this;
		}

		public User build(){
			User user = new User();
			user.setId(id);
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setSpeciality(speciality);
			user.setRole(role);

			return user;
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastNname) {
		this.lastName = lastNname;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
