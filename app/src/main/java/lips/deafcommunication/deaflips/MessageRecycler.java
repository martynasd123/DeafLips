package lips.deafcommunication.deaflips;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MessageRecycler extends  RecyclerView.Adapter<MessageRecycler.ViewHolder> {


    private Context context;
    private ArrayList<MessageData> messages;
    private SharedPreferences sharedPreferences;
    private LayoutInflater layoutInflater;
    private boolean show = false;
    private String is_admin = "0";

    public MessageRecycler(Context context, ArrayList<MessageData> messageDataHolder) {
        this.context = context;

        this.messages = messageDataHolder;
        layoutInflater = LayoutInflater.from(context);

    }


    public void remove(int position) {
        messages.remove(position);
        notifyItemRemoved(position);

    }

    public void add(MessageData info, int position) {
        messages.add(position, info);
        notifyItemInserted(position);
    }


    @Override
    public int getItemViewType(int position) {
        //return messages.get(position).getType();
        return 0;
    }

    @Override
    public MessageRecycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageRecycler.ViewHolder viewHolder = null;

        switch (viewType) {
            case 0:
                View ask_question = layoutInflater.inflate(R.layout.simple_message, parent, false);
                viewHolder = new ViewHolder(ask_question, 0);
                return viewHolder;
            case 1:
                View ask_question_admin = layoutInflater.inflate(R.layout.special_message, parent, false);
                viewHolder = new ViewHolder(ask_question_admin, 1);
                return viewHolder;

        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MessageRecycler.ViewHolder holder, int position) {
        MessageData data = messages.get(position);
        int random = (int) (20 + (Math.random() * (25 - 20)));
        switch (data.getType()){
            case 0:
                holder.simpleMessage.setText(data.getMessage());
                holder.simpleMessage.setTextSize(random);
                    break;
        }

    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView simpleMessage;

        public ViewHolder(View itemView, int type) {
            super(itemView);


            switch (type) {
                case 0:
                    simpleMessage = (TextView) itemView.findViewById(R.id.simple_message_txt);
                    break;
            }

        }
    }
}