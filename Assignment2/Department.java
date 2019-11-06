package assignment2;

import java.util.ArrayList;

public class Department {
	// This class is used to store information about each department.
	// This class have a method that return candidate with the most votes in the department.
	
	private String name;			// The name of the department.
	private ArrayList<Candidate> candidateCol = new ArrayList<Candidate>(); 
								// The collection of candidates that the department contains.
	private ArrayList<Student> studentCol = new ArrayList<Student>(); 
								// The collection of students that the department contains.
	
	public Department(String name) {
		this.name = name;
	}
	
	public ArrayList<Student> getStudList() {
		return studentCol;
	}
	
	public ArrayList<Candidate> getCandList() {
		return candidateCol;
	}
	
	public String getName() {
		return name;
	}
	
	public Candidate selectedCandidate() {
		// This class have a method that return candidate with the most votes in the department
		Candidate selected = candidateCol.get(0);
		for( int i = 0 ; i < candidateCol.size(); i++) {
			for( int j = i+1 ; j < candidateCol.size(); j++ ) {
				if(candidateCol.get(i).getVoteNum() < candidateCol.get(j).getVoteNum()) {
					selected = candidateCol.get(j);
				}
				else if(candidateCol.get(i).getVoteNum() == candidateCol.get(j).getVoteNum()) {
					if(candidateCol.get(i).getStudentNum() < candidateCol.get(j).getStudentNum()) {
						selected = candidateCol.get(j);
					}
				}
			}
		}
		return selected;
	}
}
