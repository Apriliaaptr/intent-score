package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String HOME_KEY = "home_team";
    public static final String AWAY_KEY = "away_team";
    public static final String HLOGO_KEY = "home_logo";
    public static final String ALOGO_KEY = "away_logo";
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1&2;

    private EditText home_team1;
    private EditText away_team1;
    private ImageView home_logo1;
    private ImageView away_logo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home_team1 = findViewById(R.id.home_team);
        away_team1 = findViewById(R.id.away_team);
        home_logo1 = findViewById(R.id.home_logo);
        away_logo1 = findViewById(R.id.away_logo);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }

        if (requestCode == 1) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    home_logo1.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }

        if (requestCode == 2) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    away_logo1.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleAva1(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleAva2(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    public void handleSubmit(View view) {
        String home_team = home_team1.getText().toString();
        String away_team = away_team1.getText().toString();
        //1. Validasi Input Home Team
        if (!(home_team).equals("")) {
            //2. Validasi Input Away Team
            if (!(away_team).equals("")){
                //5. Next Button Pindah Ke MatchActivity
                Intent intent = new Intent(this, MatchActivity.class);
                home_logo1.buildDrawingCache();
                away_logo1.buildDrawingCache();
                Bitmap home_logo = home_logo1.getDrawingCache();
                Bitmap away_logo = away_logo1.getDrawingCache();
                Bundle extra = new Bundle();
                extra.putParcelable(HLOGO_KEY, home_logo);
                extra.putParcelable(ALOGO_KEY, away_logo);
                intent.putExtras(extra);
                intent.putExtra(HOME_KEY, home_team);
                intent.putExtra(AWAY_KEY, away_team);

                startActivity(intent);
            } else {
                Toast.makeText(this, "Away Team tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Home Team tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
    }
}
