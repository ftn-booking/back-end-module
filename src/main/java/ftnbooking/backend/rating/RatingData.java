package ftnbooking.backend.rating;

public class RatingData {

	private double oldGrade;
    private int numberOfGrades;
    private int newGrade;
	public double getOldGrade() {
		return oldGrade;
	}
	public void setOldGrade(double oldGrade) {
		this.oldGrade = oldGrade;
	}
	public int getNumberOfGrades() {
		return numberOfGrades;
	}
	public void setNumberOfGrades(int numberOfGrades) {
		this.numberOfGrades = numberOfGrades;
	}
	public int getNewGrade() {
		return newGrade;
	}
	public void setNewGrade(int newGrade) {
		this.newGrade = newGrade;
	}
	public RatingData(double oldGrade, int numberOfGrades, int newGrade) {
		super();
		this.oldGrade = oldGrade;
		this.numberOfGrades = numberOfGrades;
		this.newGrade = newGrade;
	}
	public RatingData() {
		super();
	}
    public String toJSON()
    {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("{");
    	
    	sb.append("\"oldGrade\" : \""+oldGrade+"\" ,");
    	sb.append("\"numberOfGrades\" : \""+numberOfGrades+"\" ,");
    	sb.append("\"newGrade\" : \""+newGrade+"\" ");
    	
    	
    	sb.append("}");
    	return sb.toString();
    }
    
}
