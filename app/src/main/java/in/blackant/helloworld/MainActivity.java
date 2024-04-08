package in.blackant.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import in.blackant.helloworld.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private boolean firstOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        if (firstOpen) {
            firstOpen = false;
            openAboutActivity();
        }

        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);

        binding.rectangle.setOnClickListener((v) -> openCalculatorActivity(v.getId()));
        binding.triangle.setOnClickListener((v) -> openCalculatorActivity(v.getId()));
        binding.circle.setOnClickListener((v) -> openCalculatorActivity(v.getId()));
        binding.ellipse.setOnClickListener((v) -> openCalculatorActivity(v.getId()));

        setContentView(binding.getRoot());
    }

    private void openCalculatorActivity(int shape_id) {
        final Intent intent = new Intent(this, CalculatorActivity.class);
        intent.putExtra("shape_id", shape_id);
        startActivity(intent);
    }

    private void openAboutActivity() {
        final Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            openAboutActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}