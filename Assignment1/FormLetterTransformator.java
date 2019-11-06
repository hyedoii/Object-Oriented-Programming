package assignment1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FormLetterTransformator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String something, something2;
		Scanner fileTemp = null;
		Scanner fileProp = null;
		KeyValue a = null;
		ArrayList<KeyValue> propList = new ArrayList<KeyValue>();

		try {
			File file = new File("output_file.txt");

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bfw = new BufferedWriter(fw);

			if (file.isFile() && file.canWrite()) {
				try {
					fileTemp = new Scanner(new FileInputStream("template_file.txt"));
					fileProp = new Scanner(new FileInputStream("properties.txt"));

				} catch (FileNotFoundException e) {
					System.out.println("File not found.");
					System.exit(0);
				}

				while (fileProp.hasNextLine()) {
					something2 = fileProp.nextLine();
					a = new KeyValue(something2);
					propList.add(a);
				}

				while (fileTemp.hasNextLine()) {
					String getString, text;

					something = fileTemp.nextLine();
					if (something.indexOf("{") == -1) {
						System.out.println(something);
						bfw.write(something);
						bfw.newLine();
					}

					else if (something.indexOf("{") != -1) {
						text = something.substring(0, something.indexOf("{"));
						System.out.print(text);
						bfw.write(text);
						while (something.indexOf("{") != -1) {
							something = something.substring(something.indexOf("{") + 1);
							StringTokenizer stn = new StringTokenizer(something, "}");
							getString = stn.nextToken();
							for (int i = 0; i < propList.size(); i++) {
								if (propList.get(i).getKey().equals(getString)) {
									if (stn.hasMoreElements()) {
										System.out.print(propList.get(i).getValue());
										bfw.write(propList.get(i).getValue());
										text = something.substring(something.indexOf("}") + 1);
										if (text.indexOf("{") == -1) {
											System.out.println(text);
											bfw.write(text);
											bfw.newLine();
										} else {
											System.out.print(text.substring(0, text.indexOf("{")));
											bfw.write(text.substring(0, text.indexOf("{")));
										}

									} else if (!stn.hasMoreElements()) {
										System.out.println(propList.get(i).getValue());
										bfw.write(propList.get(i).getValue());
										bfw.newLine();
									}
								}
							}
						}
					}
				}
				bfw.close();
			}
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		fileTemp.close();;
		fileProp.close();;
	}
}
