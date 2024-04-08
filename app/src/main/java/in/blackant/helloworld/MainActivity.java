package in.blackant.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

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

        final View.OnClickListener listener = (v) -> {
            final CheckBox cb = (CheckBox) v;
            if (cb.getId() == R.id.cb1) {
                binding.tv1.setText(cb.isChecked() ? "12.000" : "0");
            } else if (cb.getId() == R.id.cb2) {
                binding.tv2.setText(cb.isChecked() ? "8.000" : "0");
            }
        };

        binding.cb1.setOnClickListener(listener);
        binding.cb2.setOnClickListener(listener);

        setContentView(binding.getRoot());
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