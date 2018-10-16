package michelle.healthylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

       Thread myThread = new Thread() {
           @Override
           public void run() {
               try {
                   sleep(5000);
                   Intent intent = new Intent(getApplicationContext(),Login.class);
                   startActivity(intent);
                   finish();
                    } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               }
           };
       myThread.start();

       }
    }

