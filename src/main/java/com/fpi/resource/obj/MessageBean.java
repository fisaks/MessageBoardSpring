package com.fpi.resource.obj;



import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;



@XmlType(propOrder = { "id", "title", "content", "sender","url" })
@JsonPropertyOrder({ "id", "title", "content", "sender","url" })
@XmlRootElement(name = "Message")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)

public class MessageBean {
	
	
	@NotBlank
	@Length(min=2,max=64)
	private String title;
	@NotBlank
	@Length(max=64)
	private String sender;
	@NotBlank
	@Length(max=1000)
	private String content;
	@URL
	private String url;

	private Long id;
	
	public MessageBean() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
