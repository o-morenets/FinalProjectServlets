package ua.training.admission.model.entity;

import java.util.Objects;

/**
 * User
 */
public class User {

	private int id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Role role;

	public static User userGuest() {
		return User.builder()
				.username("Guest")
				.role(Role.GUEST)
				.build();
	}

	public enum Role {
		ADMIN,
		USER,
        GUEST
	}

	public static class Builder {
		private int id;
		private String username;
		private String password;
		private String email;
		private String firstName;
		private String lastName;
		private Role role;

		public Builder setId(int id) {
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

		public Builder firstName(String firstNname) {
			this.firstName = firstNname;
			return this;
		}

		public Builder lastName(String lastNname) {
			this.lastName = lastNname;
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
			user.setRole(role);

			return user;
		}
	}

	public static User.Builder builder() {
		return new Builder();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id &&
				Objects.equals(username, user.username) &&
				Objects.equals(password, user.password) &&
				Objects.equals(email, user.email) &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(lastName, user.lastName) &&
				role == user.role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, email, firstName, lastName, role);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", role=" + role +
				'}';
	}
}
