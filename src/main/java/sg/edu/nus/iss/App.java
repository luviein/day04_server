package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/** PUT THIS IN TERMINAL:
 * c:\Users\tanye\OneDrive\Documents\Software Development Fundamentals\Day Four\day04_server\src\main\java\sg\edu\nus\iss>java sg.edu.nus.iss/App data\cookie.txt 5000 
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        //2 arguments
        //1 argument for file, 1 argument for port that server will start on.
        String fileName= args[0]; //presume is full path C:/Data
        String port = args[1];


        File cookieFile = new File(fileName);

        if(!cookieFile.exists()){
            System.out.println("Cookie file not found!");
            System.exit(0); //0  to indicate successful termination
        }

        //testing the Cookie class
        Cookie cookie = new Cookie();
        cookie.readCookieFile(fileName);
        String myCookie = cookie.getRandomCookie();
        System.out.println(myCookie);
        String myCookie2 = cookie.getRandomCookie();
        System.out.println(myCookie2);

        //slide 8 - establishing connection
        ServerSocket ss = new ServerSocket(Integer.parseInt(port)); //convert string port to int
        Socket socket = ss.accept();

        // store the data sent over from client eg: get-cookie
        String msgReceived = " ";
    
        //slide 9 - allow server to read and write over the communication channel. can also not be nested to not keep opening and closing stream
        try(InputStream is = socket.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            

            try(OutputStream os = socket.getOutputStream()){
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);
               
               //write logic to recceive and send
                while(msgReceived.equals("close")){
                    //slide 9 - receive message
                    msgReceived = dis.readUTF();
                    if(msgReceived.equals("get-cookie")){
                        //get a random cookie
                        String randomCookie = cookie.getRandomCookie();
                     

                        //send the random cookie out using DataOutputStream (dos.writeUTF(XXXX))
                        dos.writeUTF(randomCookie);
                    }
                } 

                //closes all output streams in reverse order
                dos.close();
                bos.close();
                os.close();

            } catch (EOFException ex) {
                ex.printStackTrace(); //prints out exception to console
            }
            //closes all input streams in reverse order
            dis.close();
            bis.close();
            is.close();


        } catch (EOFException ex) {
            socket.close();
            ss.close();
        }
    
    
        
    
    
    
    
    
    }

}
