package com.mradking.mylibrary.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mradking.mylibrary.R;
import com.mradking.mylibrary.activity.Pdf_view_act;
import com.mradking.mylibrary.activity.chapter_list;
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.database.DatabaseHeper_Chapter;
import com.mradking.mylibrary.modal.Modal;
import com.mradking.mylibrary.other.Download_file;
import com.mradking.mylibrary.other.XUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class main_adapter extends RecyclerView.Adapter<main_adapter.ViewHolder>{

    private Context mCtx;

    List<Modal> noteslist;
    String key;

    public main_adapter(Context mCtx, List<Modal> noteslist, String key) {
        this.mCtx = mCtx;
        this.noteslist = noteslist;
        this.key = key;
    }

    @NonNull
    @Override
    public main_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_row, parent, false);
        mCtx = parent.getContext();
        return new main_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull main_adapter.ViewHolder holder, int position) {

        if(key.contentEquals("2")){
            Drawable drawable = mCtx.getResources().getDrawable(R.drawable.note_icon); // Replace "my_image" with the actual name of your drawable resource
            holder.image.setImageDrawable(drawable);

        }else if(key.contentEquals("3")){
            Drawable drawable = mCtx.getResources().getDrawable(R.drawable.books_icon); // Replace "my_image" with the actual name of your drawable resource
            holder.image.setImageDrawable(drawable);

        }
        holder.name_file.setText(noteslist.get(position).getName());


        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(key.contentEquals("1")){
                    DatabaseHeper_Chapter db = new DatabaseHeper_Chapter(mCtx);
                    db.deleteAllData();
                    Intent intent=new Intent(mCtx,chapter_list.class);
                    intent.putExtra("book","solution");
                    intent.putExtra("key",noteslist.get(position).getPaths());
                    intent.putExtra("book_name",noteslist.get(position).getName());
                    mCtx.startActivity(intent);

                }else if(key.contentEquals("2")){
                    DatabaseHeper_Chapter db = new DatabaseHeper_Chapter(mCtx);
                    db.deleteAllData();
                    Intent intent=new Intent(mCtx,chapter_list.class);
                    intent.putExtra("book","note");
                    intent.putExtra("key",noteslist.get(position).getPaths());
                    intent.putExtra("book_name",noteslist.get(position).getName());
                    mCtx.startActivity(intent);
                }else if(key.contentEquals("3")){
                    DatabaseHeper_Chapter db = new DatabaseHeper_Chapter(mCtx);
                    db.deleteAllData();
                    Intent intent=new Intent(mCtx,chapter_list.class);
                    intent.putExtra("book","book");
                    intent.putExtra("key",noteslist.get(position).getPaths());
                    intent.putExtra("book_name",noteslist.get(position).getName());
                    mCtx.startActivity(intent);
                }


            }
        });



    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mview;
        TextView name_file,date;
        CardView bt,view,share;
        DatabaseHelper databaseHelper;
        CircleImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;

            name_file=mview.findViewById(R.id.chapter_name);
            databaseHelper=new DatabaseHelper(mCtx);
            image=mview.findViewById(R.id.image);

        }
    }
}