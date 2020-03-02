package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView text_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        text_winner = findViewById(R.id.winner);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String result = getIntent().getExtras().getString("result");

            text_winner.setText(result);
        }
    }
}
