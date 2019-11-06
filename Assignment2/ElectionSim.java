package assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class ElectionSim {
	/*	This class will be used as the election simulator. 
	 *	This class must read input data from a text files,
	 *	run the simulation and then write the output to another text file.
	 */
	private String deptInputPath;
	private String studInputPath;
	private String outputFilePath;
	ArrayList<Department> departmentCol = new ArrayList<Department>(); // The collection of all departments
	ArrayList<Candidate> electedCandidatesCol = new ArrayList<Candidate>(); // The collection of elected candidates
	
	
	ElectionSim(String deptInput, String StuInput, String outputFile){
		this.deptInputPath = deptInput;
		this.studInputPath = StuInput;
		this.outputFilePath = outputFile;
		
		BufferedReader bw1 = null;
		BufferedReader bw2 = null;
		
		try {
			String line, line2;

			bw1 = new BufferedReader(new InputStreamReader(new FileInputStream(deptInputPath), "UTF-8"));
			line = bw1.readLine();
			
			while(true) {
				line = bw1.readLine();
				if (line == null) break;
				StringTokenizer tk = new StringTokenizer(line, ",");
				tk.nextToken();
				String deptName = tk.nextToken();
				
				Department a = new Department(deptName);
				departmentCol.add(a);
			}
			
			bw2 = new BufferedReader(new InputStreamReader(new FileInputStream(studInputPath), "UTF-8"));
			line2 = bw2.readLine();
			
			while(true) {
				line2 = bw2.readLine();
				if(line2 == null) { break; }
				StringTokenizer tk = new StringTokenizer(line2, ",");
				Integer num = Integer.parseInt(tk.nextToken());
				Integer deptnum = Integer.parseInt(tk.nextToken());
				String name = tk.nextToken();
				String candidate = tk.nextToken();
				
				Student student = new Student(name, num, departmentCol.get(deptnum-1));
				departmentCol.get(deptnum-1).getStudList().add(student);
				
				Candidate cand = new Candidate(name, num, departmentCol.get(deptnum-1));
				if(candidate.equals("TRUE")) {
					departmentCol.get(deptnum-1).getCandList().add(cand);
				}
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveData() {
		/*	i.	Sort the collection of elected candidates.
		 * 	ii. Write the information in the collection of candidates to the output file.
		 */
		for (int i = 0 ; i < departmentCol.size() ; i++) {
			electedCandidatesCol.add(departmentCol.get(i).selectedCandidate());
		}
		Collections.sort(electedCandidatesCol);
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(outputFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			for( int i = 0 ; i < electedCandidatesCol.size(); i++) {
				bw.write("======== Elected Candidate ========");
				bw.newLine();
				bw.write("Department Name : " + electedCandidatesCol.get(i).getStudentDepartment().getName());
				bw.newLine();
				bw.write("name : " + electedCandidatesCol.get(i).getStudentName());
				bw.newLine();
				bw.write("Student_id : " + electedCandidatesCol.get(i).getStudentNum());
				bw.newLine();
				bw.write("votes : " + electedCandidatesCol.get(i).getVoteNum());
				bw.newLine();
				bw.write("===================================");
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runSimulator() {
		/*	i.	All students will vote by using iterator.
		 *	ii.	Each department elects representative.
		 *	iii.	Then call saveData method to save the simulation results to the output file.
		 */
		Iterator<Student> iter;
		
		for (int i = 0; i < departmentCol.size(); i++) {
			iter = departmentCol.get(i).getStudList().iterator();
			while(iter.hasNext())
				iter.next().vote();
		}
		saveData();
	}
}

