package in.blackant.helloworld;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.noties.markwon.Markwon;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private final Context mContext;
    private final Markwon markwon;
    private final List<Message> messages = new ArrayList<>();

    public MessageAdapter(Context context) {
        mContext = context;
        markwon = Markwon.create(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false);
        return new ViewHolder(view, markwon);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setMessage(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(String text, boolean isUser) {
        messages.add(new Message(text, isUser));
        notifyItemInserted(getItemCount() - 1);
        final MediaPlayer mp = MediaPlayer.create(mContext, isUser ? R.raw.outgoing : R.raw.incoming);
        mp.setOnCompletionListener(MediaPlayer::release);
        mp.start();
    }

    static public class Message {
        public final String text;
        public final boolean isUser;

        public Message(String text, boolean isUser) {
            this.text = text;
            this.isUser = isUser;
        }
    }

    static public class ViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final Markwon markwon;
        private final TextView name, text;

        public ViewHolder(@NonNull View itemView, Markwon markwon) {
            super(itemView);
            context = itemView.getContext();
            this.markwon = markwon;
            name = itemView.findViewById(R.id.name);
            text = itemView.findViewById(R.id.text);
        }

        public void setMessage(Message message) {
            name.setText(message.isUser ? "User" : "Simi");
            name.setTextColor(context.getColor(message.isUser ? R.color.black : R.color.yellow));
            markwon.setMarkdown(text, message.text);
        }
    }
}
