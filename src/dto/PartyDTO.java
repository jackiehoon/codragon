package dto;
public class PartyDTO {
   private String id;
   private String title;
   private String person_id;
   private String description;
   private int person_num;
   private String person_name;
   



   public String getPerson_name() {
	return person_name;
}


public void setPerson_name(String person_name) {
	this.person_name = person_name;
}


public PartyDTO(String id, String title, String person_id, String description) {
	super();
	this.id = id;
	this.title = title;
	this.person_id = person_id;
	this.description = description;
   }

   
   public void setPerson_num(int person_num) {
	this.person_num = person_num;
}


public int getPerson_num() {
		return person_num;
	}

public String getDescription() {
	return description;
}


public String getId() {
      return id;
   }


   public String getTitle() {
      return title;
   }


   public String getPerson_id() {
      return person_id;
   }   
}