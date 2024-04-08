package in.blackant.helloworld;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

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

        @SuppressLint({"DefaultLocale", "SetTextI18n"}) final View.OnClickListener listener = (v) -> {
            final Button btn = (Button) v;
            if (btn.getId() != R.id.btn_eq) {
                binding.op.setText(btn.getText());
                return;
            }

            if (binding.n1.getText() == null || binding.n1.getText().length() == 0 || binding.n2.getText() == null || binding.n2.getText().length() == 0) {
                Snackbar.make(binding.getRoot(), "Kolom tidak boleh kosong!", Snackbar.LENGTH_SHORT).show();
                return;
            }

            final double n1 = Double.parseDouble(binding.n1.getText().toString());
            final double n2 = Double.parseDouble(binding.n2.getText().toString());
            double result;
            switch (binding.op.getText().toString()) {
                case "-":
                    result = n1 - n2;
                    break;
                case "+":
                    result = n1 + n2;
                    break;
                case "÷":
                    if (n2 == 0) {
                        Snackbar.make(binding.getRoot(), "Tidak bisa membagi dengan 0!", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    result = n1 / n2;
                    break;
                case "×":
                    result = n1 * n2;
                    break;
                default:
                    Snackbar.make(binding.getRoot(), "Harap pilih operan terlebih dahulu!", Snackbar.LENGTH_SHORT).show();
                    return;
            }
            if ((int) result != result) binding.result.setText(String.format("%.2f", result));
            else binding.result.setText(Integer.toString((int) result));
        };

        binding.btnMin.setOnClickListener(listener);
        binding.btnAdd.setOnClickListener(listener);
        binding.btnDiv.setOnClickListener(listener);
        binding.btnTim.setOnClickListener(listener);
        binding.btnEq.setOnClickListener(listener);

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