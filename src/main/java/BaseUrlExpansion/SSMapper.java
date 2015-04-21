package BaseUrlExpansion;

import api.MyContext;
import api.MyMapper;
import impl.LongWritable;
import impl.StringWritable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SSMapper implements
        MyMapper<LongWritable, StringWritable> {


    static URLConnection connectURL(String strURL) {
        URLConnection conn = null;
        try {
            URL inputURL = new URL(strURL);
            conn = inputURL.openConnection();
            int test = 0;

        } catch (MalformedURLException e) {
            System.out.println("Please input a valid URL");
        } catch (IOException ioe) {
            System.out.println("Can not connect to the URL");
        }
        return conn;
    }

    @Override
    public void map(LongWritable key, StringWritable value, MyContext myContext) {

        String shortURL = value.getString();
        URLConnection urlConn = connectURL(shortURL);
        if (urlConn != null) {
            urlConn.getHeaderFields();
            shortURL = "" + urlConn.getURL();
        } else {
            shortURL = "www.fakeurl.com";
        }
        try {
            myContext.write(new StringWritable(key.getString()), new StringWritable(new String(shortURL)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}