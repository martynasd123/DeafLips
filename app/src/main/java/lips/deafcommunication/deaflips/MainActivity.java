package lips.deafcommunication.deaflips;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button start_listening_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing views
        start_listening_btn = (Button) findViewById(R.id.start_listening_button);



        //Setting fonts
//        Typeface font = Typeface.createFromAsset(getAssets(), "Verdana.ttf");
//        start_listening_btn.setTypeface(font);
    }

    public void startListening(View view) {
        Intent intent = new Intent(this, ChatInProgressActivity.class);
        startActivity(intent);

    }
}
