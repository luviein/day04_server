package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cookie {
    List<String> cookies = null;

    public void readCookieFile(String fileName) throws IOException {
        // instantiate the cookies collection object
        cookies = new ArrayList<>();
        // use a File object to pass the fileName
        File fileObj = new File(fileName);
        // use FileReader and BufferReader for reading from cookie file
        BufferedReader br = new BufferedReader(new FileReader(fileObj));
        String readFileObj = "";

        // while loop to loop through the file
        while ((readFileObj = br.readLine()) != null) {
            // read each line and add into the cookies collection object
            cookies.add(readFileObj);
        }
        // close the BufferedReader in reverse order
        br.close();

    }

    public String getRandomCookie() {
        return "";
    }

}
