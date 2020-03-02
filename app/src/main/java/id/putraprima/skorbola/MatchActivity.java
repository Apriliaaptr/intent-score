package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private TextView text_home;
    private TextView text_away;
    private ImageView logo_home;
    private ImageView logo_away;
    private Integer home_skor;
    private Integer away_skor;
    private TextView skor_home;
    private TextView skor_away;

    private static final String RESULT_KEY = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        text_home = findViewById(R.id.txt_home);
        text_away = findViewById(R.id.txt_away);
        logo_home = findViewById(R.id.home_logo);
        logo_away = findViewById(R.id.away_logo);
        skor_home = findViewById(R.id.score_home);
        skor_away = findViewById(R.id.score_away);

        home_skor = 0;
        away_skor = 0;
        skor_home.setText("0");
        skor_away.setText("0");

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            //1.Menampilkan detail match sesuai data dari main activity
            Bundle bundle = getIntent().getExtras();
            Bitmap home = bundle.getParcelable("home_logo");
            Bitmap away = bundle.getParcelable("away_logo");

            logo_home.setImageBitmap(home);
            logo_away.setImageBitmap(away);

            text_home.setText(extra.getString("home_team"));
            text_away.setText(extra.getString("away_team"));
        }
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }


    public void handleAddHome(View view) {
        home_skor++;
        skor_home.setText(String.valueOf(home_skor));
    }

    public void handleAddAway(View view) {
        away_skor++;
        skor_away.setText(String.valueOf(away_skor));
    }

    public void handleResult(View view) {
        String result;

        if (home_skor > away_skor){
            result = text_home.getText().toString();
        } else if (home_skor < away_skor){
            result = text_away.getText().toString();
        } else {
            result = "DRAW!";
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT_KEY, result);
        startActivity(intent);
    }
}
