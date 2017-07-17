package dto;

public class ImageDTO {
	private String id;
   private String example_id;
   private String image;
   private String description;
	
	
	public ImageDTO(String id, String example_id, String image, String description) {
		super();
		this.id = id;
		this.example_id = example_id;
		this.image = image;
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public String getExample_id() {
		return example_id;
	}
	public String getImage() {
		return image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
   
}
