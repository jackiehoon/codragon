package dto;

public class PersonDTO {
	   private String id;
	   private String email;
	   private String password;
	   private String name;
	   
	   public PersonDTO(String id, String email, String password, String name) {
	      super();
	      this.id = id;
	      this.email = email;
	      this.password = password;
	      this.name = name;
	   }

	   public String getId() {
	      return id;
	   }

	   public String getEmail() {
	      return email;
	   }

	   public String getPassword() {
	      return password;
	   }

	   public String getName() {
	      return name;
	   }
	}