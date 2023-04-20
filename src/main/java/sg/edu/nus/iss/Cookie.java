package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        // use Math random function to get random item from cookies collection object
        Random random = new Random(); 


        //check if cookies collection are loaded
        if (cookies == null){
            return "There is no cookie.";
        }else{
            String randomCookie = cookies.get(random.nextInt(cookies.size()));
            return randomCookie;
        }

        //if cookies collection loaded, then generate and return random cookie

      
    }

}
