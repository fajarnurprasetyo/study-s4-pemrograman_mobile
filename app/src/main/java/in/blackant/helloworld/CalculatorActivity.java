package in.blackant.helloworld;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import in.blackant.helloworld.databinding.ActivityCalculatorBinding;

public class CalculatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        final ActivityCalculatorBinding binding = ActivityCalculatorBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        final int shape_id = getIntent().getIntExtra("shape_id", -1);
        if (shape_id == R.id.rectangle) {
            setTitle(getString(R.string.rectangle));
            binding.rectangle.setVisibility(View.VISIBLE);

            final TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @SuppressLint({"SetTextI18n", "DefaultLocale"})
                @Override
                public void afterTextChanged(Editable s) {
                    final double length = binding.rectLength.getText() == null || binding.rectLength.getText().length() == 0 ? 0 : Double.parseDouble(binding.rectLength.getText().toString());
                    final double width = binding.rectWidth.getText() == null || binding.rectWidth.getText().length() == 0 ? 0 : Double.parseDouble(binding.rectWidth.getText().toString());
                    final double area = length * width;
                    if ((int) area != area) binding.rectResult.setText(String.format("%.2f", area));
                    else binding.rectResult.setText(Integer.toString((int) area));
                }
            };
            binding.rectLength.addTextChangedListener(watcher);
            binding.rectWidth.addTextChangedListener(watcher);
        } else if (shape_id == R.id.triangle) {
            setTitle(getString(R.string.triangle));
            binding.triangle.setVisibility(View.VISIBLE);

            final TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @SuppressLint({"SetTextI18n", "DefaultLocale"})
                @Override
                public void afterTextChanged(Editable s) {
                    final double base = binding.triBase.getText() == null || binding.triBase.getText().length() == 0 ? 0 : Double.parseDouble(binding.triBase.getText().toString());
                    final double height = binding.triHeight.getText() == null || binding.triHeight.getText().length() == 0 ? 0 : Double.parseDouble(binding.triHeight.getText().toString());
                    final double area = base * height / 2;
                    if ((int) area != area) binding.triResult.setText(String.format("%.2f", area));
                    else binding.triResult.setText(Integer.toString((int) area));
                }
            };
            binding.triBase.addTextChangedListener(watcher);
            binding.triHeight.addTextChangedListener(watcher);
        } else if (shape_id == R.id.circle) {
            setTitle(getString(R.string.circle));
            binding.circle.setVisibility(View.VISIBLE);

            final TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @SuppressLint({"SetTextI18n", "DefaultLocale"})
                @Override
                public void afterTextChanged(Editable s) {
                    final double radius = binding.circRadius.getText() == null || binding.circRadius.getText().length() == 0 ? 0 : Double.parseDouble(binding.circRadius.getText().toString());
                    final double area = Math.PI * Math.pow(radius, 2);
                    if ((int) area != area) binding.circResult.setText(String.format("%.2f", area));
                    else binding.circResult.setText(Integer.toString((int) area));
                }
            };
            binding.circRadius.addTextChangedListener(watcher);
        } else if (shape_id == R.id.ellipse) {
            setTitle(getString(R.string.ellipse));
            binding.ellipse.setVisibility(View.VISIBLE);

            final TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @SuppressLint({"SetTextI18n", "DefaultLocale"})
                @Override
                public void afterTextChanged(Editable s) {
                    final double radius1 = binding.ellRadius1.getText() == null || binding.ellRadius1.getText().length() == 0 ? 0 : Double.parseDouble(binding.ellRadius1.getText().toString());
                    final double radius2 = binding.ellRadius2.getText() == null || binding.ellRadius2.getText().length() == 0 ? 0 : Double.parseDouble(binding.ellRadius2.getText().toString());
                    final double area = Math.PI * radius1 * radius2;
                    if ((int) area != area) binding.ellResult.setText(String.format("%.2f", area));
                    else binding.ellResult.setText(Integer.toString((int) area));
                }
            };
            binding.ellRadius1.addTextChangedListener(watcher);
            binding.ellRadius2.addTextChangedListener(watcher);
        } else {
            finish();
        }

        setContentView(binding.getRoot());
    }
}