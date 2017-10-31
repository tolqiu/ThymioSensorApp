package com.tolga.thymiosensorapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Tolqiu on 10/22/2017.
 */

public class SensorFile extends AsyncTask{

    private Exception exception ;

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            URL url = new URL("http://humanstxt.org/humans.txt") ;
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = in.readLine();
            return str;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
