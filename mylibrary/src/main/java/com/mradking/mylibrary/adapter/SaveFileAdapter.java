package com.mradking.mylibrary.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.mradking.mylibrary.database.DatabaseHelper;
import com.mradking.mylibrary.modal.Modal;
import com.mradking.mylibrary.other.Download_file;

import java.io.File;
import java.util.List;

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

        holder.name_file.setText(noteslist.get(position).getName());

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(key.contentEquals("1")){

                    if( noteslist.get(position).getStatus().contentEquals("no")){


                        Intent intent=new Intent(mCtx, Download_file.class);
                        intent.putExtra("key",noteslist.get(position).getPaths());
                        intent.putExtra("id",String.valueOf(noteslist.get(position).get_id()));
                        intent.putExtra("book_number",key);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mCtx.startActivity(intent);



                    }else {

                        Intent intent=new Intent(mCtx, Pdf_view_act.class);
                        intent.putExtra("key",noteslist.get(position).getPaths());
                        mCtx.startActivity(intent);


                    }

                }else if(key.contentEquals("2")) {

                    if( noteslist.get(position).getStatus().contentEquals("no")){


                        Intent intent=new Intent(mCtx, Download_file.class);
                        intent.putExtra("key",noteslist.get(position).getPaths());
                        intent.putExtra("id",String.valueOf(noteslist.get(position).get_id()));
                        intent.putExtra("book_number",key);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mCtx.startActivity(intent);



                    }else {

                        Intent intent=new Intent(mCtx, Pdf_view_act.class);
                        intent.putExtra("key",noteslist.get(position).getPaths());
                        mCtx.startActivity(intent);


                    }
                }else if(key.contentEquals("3")) {

                    if( noteslist.get(position).getStatus().contentEquals("no")){


                        Intent intent=new Intent(mCtx, Download_file.class);
                        intent.putExtra("key",noteslist.get(position).getPaths());
                        intent.putExtra("id",String.valueOf(noteslist.get(position).get_id()));
                        intent.putExtra("book_number",key);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mCtx.startActivity(intent);



                    }else {

                        Intent intent=new Intent(mCtx, Pdf_view_act.class);
                        intent.putExtra("key",noteslist.get(position).getPaths());
                        mCtx.startActivity(intent);


                    }
                }
//


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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;

            name_file=mview.findViewById(R.id.chapter_name);
            databaseHelper=new DatabaseHelper(mCtx);

        }
    }
}
