package in.blackant.helloworld;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SimiRequest extends StringRequest {
    final Context mContext;
    final String mText;

    public SimiRequest(Context context, String text, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.POST, context.getString(R.string.endpoint), res -> {
            try {
                listener.onResponse(new JSONObject(res));
            } catch (JSONException e) {
                assert errorListener != null;
                errorListener.onErrorResponse(new VolleyError(e));
            }
        }, errorListener);
        mContext = context;
        mText = Uri.encode(text);
    }

    @Override
    public Map<String, String> getHeaders() {
        final Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("x-api-key", mContext.getString(R.string.api_key));
        return header;
    }

    @Override
    public byte[] getBody() {
        return mContext.getString(R.string.request_data, mText).getBytes();
    }
}
