package model;

public class Coworker {
	private Integer id;
	private String name;
	private String conversation;

	public Coworker(Integer id, String name, String conversation) {
		this.id = id;
		this.name = name;
		this.conversation = conversation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConversation() {
		return conversation;
	}

	public void setConversation(String conversation) {
		this.conversation = conversation;
	}
}
