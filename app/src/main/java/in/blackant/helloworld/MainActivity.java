package in.blackant.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import in.blackant.helloworld.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private boolean firstOpen = true;

    private String getInput() {
        return mBinding.input.getText().toString();
    }

    private void setResult(String text) {
        mBinding.result.setText(text);
    }

    private void setResult(Spanned spanned) {
        mBinding.result.setText(spanned);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setSupportActionBar(mBinding.toolbar);

        setContentView(mBinding.getRoot());
        if (firstOpen) {
            firstOpen = false;
            openAboutActivity();
        }

        mBinding.send1.setText(String.format(getString(R.string.send), 1));
        mBinding.send2.setText(String.format(getString(R.string.send), 2));

        mBinding.send1.setOnClickListener((v) -> setResult(getInput()));
        mBinding.send2.setOnClickListener((v) -> {
            final String text = getInput();
            setResult(Html.fromHtml(
                    "<div>" + text + "</div>" +
                            "<div><i>" + text + "</i></div>" +
                            "<div><b>" + text + "</b></div>" +
                            "<div><b><i>" + text + "</i></b></div>"
                    , HtmlCompat.FROM_HTML_MODE_LEGACY));
        });
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