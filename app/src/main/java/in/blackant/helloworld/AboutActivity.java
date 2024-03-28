package in.blackant.helloworld;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import in.blackant.helloworld.databinding.ActivityAboutBinding;
import io.noties.markwon.Markwon;
import io.noties.markwon.html.HtmlPlugin;
import io.noties.markwon.image.ImagesPlugin;
import io.noties.markwon.image.svg.SvgMediaDecoder;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        final ActivityAboutBinding binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("README.md")));
            final Markwon markwon = Markwon.builder(this)
                    .usePlugin(HtmlPlugin.create())
                    .usePlugin(ImagesPlugin.create(plugin -> {
                        plugin.addMediaDecoder(SvgMediaDecoder.create());
                    }))
                    .build();
            final StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            markwon.setMarkdown(binding.markwon, stringBuilder.toString());
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setContentView(binding.getRoot());
    }
}