package com.wsadevv.angryweather;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by willi on 06/02/2017.
 */



    public class FetchWeatherTask extends AsyncTask<Void, Void, String> {
        ImageButton sync;
        @Override
        protected String doInBackground(Void... params) {
            String forecastJSON;
            final  String LOG_TAG = "MY_TAG";
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?lat=-47.9287&lon=-15.7784&mode=json&APPID=81f1130147c42be3457940e3a143f925");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();


                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();
                if(inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = reader.readLine())!= null){
                    stringBuffer.append(line+"\n");

                }
                if(stringBuffer.length() == 0){
                    forecastJSON = null;
                }
                forecastJSON = stringBuffer.toString();
                Log.v(LOG_TAG,forecastJSON);


            }catch (MalformedURLException m){
                Log.e(LOG_TAG,m.getMessage());
            } catch (IOException e) {
                Log.e(LOG_TAG,e.getMessage());
            }finally {
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG,e.getMessage());
                    }
                }
            }

                return null;

            }
        }



