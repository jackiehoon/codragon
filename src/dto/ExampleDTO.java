package dto;

public class ExampleDTO {
	
		private String id ,lecture_id,title,content;
		public ExampleDTO(String id, String lecture_id, String title, String content) {
			super();
			this.id = id;
			this.lecture_id = lecture_id;
			this.title = title;
			this.content = content;
		}
		public String getId() {
			return id;
		}
		public String getLecture_id() {
			return lecture_id;
		}
		public String getTitle() {
			return title;
		}
		public String getContent() {
			return content;
		}
	
}
