package cn.ac.iie.Entity;

public class WEBContentEntity {
	
	private String title;
	private String content;
	
	public WEBContentEntity(){
		this.title = null;
		this.content = null;
	}
	public WEBContentEntity(String title, String content){
		this.title = title;
		this.content = content;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return this.content;
	}
	@Override
	public String toString() {
		return "WEBContentEntity [title=" + title + ", content=" + content + "]";
	}

}
