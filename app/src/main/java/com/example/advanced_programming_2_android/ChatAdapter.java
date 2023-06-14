package com.example.advanced_programming_2_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.advanced_programming_2_android.classes.Chat;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    List<Chat> chats;

    private class ViewHolder {
        ImageView ivProfilePic;
        TextView tvDisplayName;
        TextView tvLastMessage;
        TextView tvTimestamp;
    }

    public ChatAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public int getCount() {
        return chats.size();
    }

    @Override
    public Object getItem(int position) {
        return chats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.single_chat_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.ivProfilePic = convertView.findViewById(R.id.ivChatProfilePic);
            viewHolder.tvDisplayName = convertView.findViewById(R.id.tvChatDisplayName);
            viewHolder.tvLastMessage = convertView.findViewById(R.id.tvChatLastMessage);
            viewHolder.tvTimestamp = convertView.findViewById(R.id.tvChatTimestamp);

            convertView.setTag(viewHolder);
        }

        Chat c = chats.get(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        Glide.with(parent.getContext())
                .load(c.getProfilePic())
                .into(viewHolder.ivProfilePic);
        viewHolder.tvDisplayName.setText(c.getDisplayName());
        viewHolder.tvLastMessage.setText(c.getLastMessage());
        viewHolder.tvTimestamp.setText(c.getTimestamp());



        return convertView;
    }

    public void updateChats(List<Chat> filteredChats) {
        chats.clear();
        chats.addAll(filteredChats);
        notifyDataSetChanged();
    }
}
