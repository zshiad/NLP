package dao;

/**
 * AbstractInfoEntropy entity provides the base persistence definition of the
 * InfoEntropy entity. @author MyEclipse Persistence Tools
 */

public class Item implements java.io.Serializable {

	// Fields

	private Integer problem_id;
	private Double score;
	private Integer number;
	
	private String tags;
	private String content;
	private String title;
	private String prerequisite;
	private String solution;
	
	private String keyword1;
	private Integer tags_num1;
	private Integer content_num1;
	private Integer title_num1;
	private Integer prerequisite_num1;
	private Integer solution_num1;
	private double info_entropy1;
	
	private String keyword2;
	private Integer tags_num2;
	private Integer content_num2;
	private Integer title_num2;
	private Integer prerequisite_num2;
	private Integer solution_num2;
	private double info_entropy2;
	
	private String keyword3;
	private Integer tags_num3;
	private Integer content_num3;
	private Integer title_num3;
	private Integer prerequisite_num3;
	private Integer solution_num3;
	private double info_entropy3;
	
	
	// Constructors
	 public int compare(Item p1, Item p2)
	    {
	       // if last names are the same compare first names
	       if(p1.getNumber().equals(p2.getNumber()))
	       {
	           return p1.getScore().compareTo(p2.getScore());
	       }
	       return p1.getNumber().compareTo(p2.getNumber());
	    }
	
	
	
	public Integer getProblem_id() {
		return problem_id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public void setProblem_id(Integer problem_id) {
		this.problem_id = problem_id;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public Integer getTags_num1() {
		return tags_num1;
	}
	public void setTags_num1(Integer tags_num1) {
		this.tags_num1 = tags_num1;
	}
	public Integer getContent_num1() {
		return content_num1;
	}
	public void setContent_num1(Integer content_num1) {
		this.content_num1 = content_num1;
	}
	public Integer getTitle_num1() {
		return title_num1;
	}
	public void setTitle_num1(Integer title_num1) {
		this.title_num1 = title_num1;
	}
	public Integer getPrerequisite_num1() {
		return prerequisite_num1;
	}
	public void setPrerequisite_num1(Integer prerequisite_num1) {
		this.prerequisite_num1 = prerequisite_num1;
	}
	public Integer getSolution_num1() {
		return solution_num1;
	}
	public void setSolution_num1(Integer solution_num1) {
		this.solution_num1 = solution_num1;
	}
 
	public double getInfo_entropy1() {
		return info_entropy1;
	}
	public void setInfo_entropy1(double info_entropy1) {
		this.info_entropy1 = info_entropy1;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public Integer getTags_num2() {
		return tags_num2;
	}
	public void setTags_num2(Integer tags_num2) {
		this.tags_num2 = tags_num2;
	}
	public Integer getContent_num2() {
		return content_num2;
	}
	public void setContent_num2(Integer content_num2) {
		this.content_num2 = content_num2;
	}
	public Integer getTitle_num2() {
		return title_num2;
	}
	public void setTitle_num2(Integer title_num2) {
		this.title_num2 = title_num2;
	}
	public Integer getPrerequisite_num2() {
		return prerequisite_num2;
	}
	public void setPrerequisite_num2(Integer prerequisite_num2) {
		this.prerequisite_num2 = prerequisite_num2;
	}
	public Integer getSolution_num2() {
		return solution_num2;
	}
	public void setSolution_num2(Integer solution_num2) {
		this.solution_num2 = solution_num2;
	}
	public double getInfo_entropy2() {
		return info_entropy2;
	}
	public void setInfo_entropy2(double info_entropy2) {
		this.info_entropy2 = info_entropy2;
	}
	public String getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	public Integer getTags_num3() {
		return tags_num3;
	}
	public void setTags_num3(Integer tags_num3) {
		this.tags_num3 = tags_num3;
	}
	public Integer getContent_num3() {
		return content_num3;
	}
	public void setContent_num3(Integer content_num3) {
		this.content_num3 = content_num3;
	}
	public Integer getTitle_num3() {
		return title_num3;
	}
	public void setTitle_num3(Integer title_num3) {
		this.title_num3 = title_num3;
	}
	public Integer getPrerequisite_num3() {
		return prerequisite_num3;
	}
	public void setPrerequisite_num3(Integer prerequisite_num3) {
		this.prerequisite_num3 = prerequisite_num3;
	}
	public Integer getSolution_num3() {
		return solution_num3;
	}
	public void setSolution_num3(Integer solution_num3) {
		this.solution_num3 = solution_num3;
	}
	public double getInfo_entropy3() {
		return info_entropy3;
	}
	public void setInfo_entropy3(double info_entropy3) {
		this.info_entropy3 = info_entropy3;
	}
	

	
	
	
}