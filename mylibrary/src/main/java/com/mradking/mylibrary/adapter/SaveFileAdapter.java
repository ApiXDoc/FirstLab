package com.mradking.mylibrary.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;


import com.mradking.mylibrary.R;
import com.mradking.mylibrary.activity.Pdf_view_act;
import com.mradking.mylibrary.activity.download_read_act;
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.modal.Modal;
import com.mradking.mylibrary.other.Download_file;
import com.mradking.mylibrary.other.sharePrefX;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SaveFileAdapter extends RecyclerView.Adapter<SaveFileAdapter.ViewHolder>{

    private Context mCtx;

    List<Modal> noteslist;
    String key;

    public SaveFileAdapter(Context mCtx, List<Modal> noteslist, String key) {
        this.mCtx = mCtx;
        this.noteslist = noteslist;
        this.key = key;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_row, parent, false);
        mCtx = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {

        if( sharePrefX.containsKey(mCtx,noteslist.get(position).getPaths())){

            holder.offline.setVisibility(View.VISIBLE);
        }

        if(key.contentEquals("2")){

            holder.name_file.setText(noteslist.get(position).getName());
        } else if(key.contentEquals("2")){
            Drawable drawable = mCtx.getResources().getDrawable(R.drawable.note_icon); // Replace "my_image" with the actual name of your drawable resource
            holder.image.setImageDrawable(drawable);
            holder.name_file.setText(noteslist.get(position).getName());

        }else if(key.contentEquals("3")){
            Drawable drawable = mCtx.getResources().getDrawable(R.drawable.books_icon); // Replace "my_image" with the actual name of your drawable resource
            holder.image.setImageDrawable(drawable);


            holder.name_file.setText(noteslist.get(position).getName().substring(2));

        }

        holder.name_file.setText(noteslist.get(position).getName());


        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( sharePrefX.containsKey(mCtx,noteslist.get(position).getPaths())){
                    String path =sharePrefX.getString(mCtx,noteslist.get(position).getPaths(),"no");
                    Intent intent=new Intent(mCtx, download_read_act.class);
                    intent.putExtra("key",path);
                    mCtx.startActivity(intent);

                }else {

                    Intent intent=new Intent(mCtx, Download_file.class);
                    intent.putExtra("key",noteslist.get(position).getPaths());
                    intent.putExtra("id",String.valueOf(noteslist.get(position).get_id()));
                    intent.putExtra("book_number",key);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        TextView name_file,offline;
        CardView bt,view,share;
        DatabaseHelper databaseHelper;
        CircleImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;

            offline=mview.findViewById(R.id.offline);
            name_file=mview.findViewById(R.id.chapter_name);
            databaseHelper=new DatabaseHelper(mCtx);
            image=mview.findViewById(R.id.image);
        }
    }
}
