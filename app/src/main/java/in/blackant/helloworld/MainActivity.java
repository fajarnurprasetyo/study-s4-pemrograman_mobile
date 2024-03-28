package in.blackant.helloworld;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import in.blackant.helloworld.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private MessageAdapter mAdapter;
    private RequestQueue mQueue;
    private static boolean firstOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mAdapter = new MessageAdapter(this);
        mQueue = Volley.newRequestQueue(this);

        setSupportActionBar(mBinding.toolbar);

        mBinding.messageContainer.setLayoutManager(new LinearLayoutManager(this));
        mBinding.messageContainer.setAdapter(mAdapter);
        mBinding.sendButton.setOnClickListener((v) -> {
            final Editable message = mBinding.messageInput.getText();
            if (message.length() > 0) {
                final String text = message.toString();
                if (mBinding.botCheckBox.isChecked()) {
                    mBinding.sendButton.setEnabled(false);
                    final SimiRequest req = new SimiRequest(this, text, res -> {
                        try {
                            if (res.getInt("status") == 200) {
                                mAdapter.addMessage(Uri.decode(res.getString("atext")), false);
                            }
                        } catch (JSONException err) {
                            Log.e("SIMI", err.toString());
                        }
                        mBinding.sendButton.setEnabled(true);
                    }, err -> {
                        Log.e("SIMI", err.toString());
                        mBinding.sendButton.setEnabled(true);
                    });
                    mQueue.add(req);
                }
                mAdapter.addMessage(text, true);
                message.clear();
            }
        });

        setContentView(mBinding.getRoot());
        if (firstOpen) {
            firstOpen = false;
            openAboutActivity();
        }
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