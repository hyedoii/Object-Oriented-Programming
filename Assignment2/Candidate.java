package assignment2;

public class Candidate extends Student {
	// This class is used to store information about candidate. 
	// Candidates must be compared based on the number of votes.

	private Integer voteNum = 0; // Number of votes

	Candidate(String name, Integer num, Department dept) {
		super(name, num, dept);
	}
	
	public Integer getVoteNum() {
		return voteNum;
	}
	
	public void setVoteNum(Integer a) {
		voteNum = a;
	}
	
	@Override
	public int compareTo(Object o) {
		Candidate candi;
		candi = (Candidate) o;
		return -this.voteNum.compareTo(candi.voteNum);
	}
	
	
}
