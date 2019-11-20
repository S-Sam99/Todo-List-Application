package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadWebPage {
    BufferedReader br = null;
    public ReadWebPage() {   }

    public String ReadURL() throws MalformedURLException, IOException{
        String carry;
        try {
            String theURL = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html"; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            String sb = "";

            while ((line = br.readLine()) != null) {

                sb+=line;
                sb+=System.lineSeparator();
            }

            carry = sb;

        } finally {

            if (br != null) {
                br.close();
            }
        }
        return carry;
    }
}
