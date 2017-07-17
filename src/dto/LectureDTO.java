package dto;

import java.util.ArrayList;

public class LectureDTO {

	public LectureDTO(String id, String party_id, String title, String lock) {
		super();
		this.id = id;
		this.party_id = party_id;
		this.title = title;
		this.lock = lock;
	}

	public String getId() {
		return id;
	}

	public String getParty_id() {
		return party_id;
	}

	public String getTitle() {
		return title;
	}



	private String id,party_id,title,lock; 
	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}


	private ArrayList<ExampleDTO> exampleList;
	public ArrayList<ExampleDTO> getExampleList() {
		return exampleList;
	}

	public void setExampleList(ArrayList<ExampleDTO> exampleList) {
		this.exampleList = exampleList;
	}


}
