package michelle.healthylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick (View v)
    {
        if (v.getId() == R.id.btnLogin)
        {
            EditText a = findViewById(R.id.TFusername);
            String str = a.getText().toString();

            Intent i = new Intent(Login.this, HealthyLifeMain.class);
            i.putExtra("Username", str);
            startActivity(i);

        }
    }

}
