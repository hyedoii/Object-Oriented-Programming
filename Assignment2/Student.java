package assignment2;

import java.util.ArrayList;
import java.util.Random;

public class Student implements Voter{
	// This class will be used to store information about student.
	
	private String studentName; // The name of the student
	private Integer studentNum; // The identification number of the student.
	private Department studentDepartment; // The department of the student.
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}

	public Department getStudentDepartment() {
		return studentDepartment;
	}

	public void setStudentDepartment(Department studentDepartment) {
		this.studentDepartment = studentDepartment;
	}

	Random random = new Random();
	
	Student(){
		
	}
	
	Student(String name, Integer num, Department dept) {
		this.studentName = name;
		this.studentNum = num;
		this.studentDepartment = dept;
	}
	
	@Override
	public void vote() {
		/*	i.	Select a number randomly between 0 and the number of Candidates of the department of the student.
			ii.	Increase the number of votes of the candidate of the department 
				in the ArrayList at the random number location.*/
		
		int studVote = random.nextInt(studentDepartment.getCandList().size());
	
		studentDepartment.getCandList().get(studVote).setVoteNum((studentDepartment.getCandList().get(studVote).getVoteNum())+1);
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
