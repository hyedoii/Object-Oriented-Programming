package assignment1;

import java.util.StringTokenizer;

public class KeyValue {

	private String key, value;
	
	KeyValue(String str) {
	      StringTokenizer st = new StringTokenizer(str, "=");
	      key = st.nextToken();
	      value = st.nextToken();
	   }
	   
	   KeyValue(String key, String value) {
	      this.key = key;
	      this.value = value;      
	   }
	   
	   public String getKey() {
	      return key;
	   }
	   public String getValue() {
	      return value;
	   }

}
