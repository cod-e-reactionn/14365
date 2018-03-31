package app.coderecationn.com.kaksha;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{




    public FirebaseAuth mAuth = FirebaseAuth.getInstance();
    View view;
    private Context context;
    private List<Messages> mMessageList;
    public TextView messageTime;
    Messages message_time;

    public MessageAdapter(List<Messages> mMessageList) {

        this.mMessageList = mMessageList;

    }


    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_single_layout ,parent, false);


        return new MessageViewHolder(v);

    }


    @Override
    public void onBindViewHolder(final MessageViewHolder viewHolder, int i) {

        String current_user_id = mAuth.getUid().toString();

        Messages c = mMessageList.get(i);

        String from_user = c.getFrom();
        String message_type = c.getType();
        message_time = new Messages();

        if (from_user.equals(current_user_id)) {

            viewHolder.messageText.setBackgroundResource(R.drawable.message_text_background);
            viewHolder.messageText.setTextColor(Color.BLACK);
            viewHolder.messageText.setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams params = (new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)); viewHolder.messageText.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            viewHolder.messageText.setLayoutParams(params);
//            messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", m));
        }
        else {

            viewHolder.messageText.setBackgroundResource(R.drawable.message_text_background_1);
            viewHolder.messageText.setTextColor(Color.BLACK);
            viewHolder.messageText.setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams params = (new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            viewHolder.messageText.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            viewHolder.messageText.setLayoutParams(params);
//            messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",m));
        }

        viewHolder.messageText.setText(c.getMessage());

//        String from_user = c.getFrom();
//        String message_type = c.getType();
//
//
//        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(from_user);
//
//        mUserDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                String name = dataSnapshot.child("name").getValue().toString();
//                String image = dataSnapshot.child("thumb_image").getValue().toString();
//
//                viewHolder.displayName.setText(name);
//
//                Picasso.with(viewHolder.profileImage.getContext()).load(image)
//                        .placeholder(R.drawable.default_avatar).into(viewHolder.profileImage);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        if (message_type.equals("text")) {

            viewHolder.messageText.setText(c.getMessage());
            viewHolder.messageImage.setVisibility(View.GONE);

        } else {

            if (from_user.equals(current_user_id)) {

                viewHolder.messageText.setVisibility(View.GONE);
                viewHolder.messageImage.setVisibility(View.VISIBLE);
                Picasso.with(viewHolder.messageText.getContext()).load(c.getMessage())
                        .placeholder(R.drawable.placeholder).into(viewHolder.messageImage);
                RelativeLayout.LayoutParams params = (new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                viewHolder.messageImage.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                viewHolder.messageImage.setLayoutParams(params);
            }
            else {

                viewHolder.messageText.setVisibility(View.GONE);
                viewHolder.messageImage.setVisibility(View.VISIBLE);
                Picasso.with(viewHolder.messageText.getContext()).load(c.getMessage())
                        .placeholder(R.drawable.placeholder).into(viewHolder.messageImage);
                RelativeLayout.LayoutParams params = (new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                viewHolder.messageImage.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                viewHolder.messageImage.setLayoutParams(params);

            }



        }
    }

    @Override
    public int getItemCount() {

        return mMessageList.size();

    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public TextView displayName;
        public ImageView messageImage;


        public MessageViewHolder(View view) {
            super(view);

            messageText = view.findViewById(R.id.message_text_layout);
//            displayName = (TextView) view.findViewById(R.id.name_text_layout);
            messageImage = view.findViewById(R.id.message_image_layout);
            messageTime = view.findViewById(R.id.message_time1);

        }
    }






}