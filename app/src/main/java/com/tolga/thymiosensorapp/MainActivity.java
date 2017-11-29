package com.tolga.thymiosensorapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String TextFileURL = "http://duspenceresi.com/sensorData.txt" ;
    TextView textView ;
    Button button ;
    boolean refreshData = false ;
    URL url ;
    String TextHolder = "" , TextHolder2 = "";
    BufferedReader bufferReader ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //while (true) {
                new GetNotePadFileFromServer().execute();
                System.out.println("WHILE INNN*************************");
                //}
            }
        });

    }

    protected void onStart(){
        super.onStart();
        refreshData = true;
        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }

    @Override
public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu,menu);
        return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item){
    if (item.getItemId() == R.id.settings_menu)
         System.out.println("Hello Menu");
    return true;
}

    public class GetNotePadFileFromServer extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {

            try {
                url = new URL(TextFileURL);

                bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));

                while ((TextHolder2 = bufferReader.readLine()) != null) {

                    TextHolder = TextHolder2;
                }
                bufferReader.close();

            } catch (MalformedURLException malformedURLException) {

                // TODO Auto-generated catch block
                malformedURLException.printStackTrace();
                TextHolder = malformedURLException.toString();

            } catch (IOException iOException) {

                // TODO Auto-generated catch block
                iOException.printStackTrace();

                TextHolder = iOException.toString();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void finalTextHolder) {

            textView.setText(TextHolder);

            super.onPostExecute(finalTextHolder);
        }

    }

    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            while (refreshData == true){
                new GetNotePadFileFromServer().execute();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


}