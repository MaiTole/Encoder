import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Cipher {
	
	public HashMap<String, Integer> codeHash;
	
	public void setMap() {
		codeHash = new HashMap<String, Integer>();
	}
	
	public HashMap<String, Integer> getMap() {
		return codeHash;
	}

	/**
	 * SetHash function sets the hash function 
	 */
	public  void setHash(String fileName) {
		File file = new File(fileName);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				//line = br.readLine();
				String[] arr = line.split(" ");
				String mapKey = arr[1].split("")[0];				
				codeHash.put(mapKey, Integer.parseInt(arr[0]));
			}
			br.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
