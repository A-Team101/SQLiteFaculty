package com.example.sqlitefaculty;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ProductViewer>{


    List<stringName> facultyDetails;
    Context context;
    OpenHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public RecycleAdapter(List<stringName> facultyDetails) {
        this.facultyDetails = facultyDetails;
    }

    @NonNull
    @Override
    public ProductViewer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {





        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View iteView = inflater.inflate(R.layout.faculty_info, parent, false);
        ProductViewer viewHolder = new ProductViewer(iteView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewer holder, final int position) {
        final stringName stringname=facultyDetails.get(position);


        holder.fmId.setText("ID: " + stringname.getP_id());
        holder.fId.setText("ID Number :"+ stringname.getP_idnum());
        holder.fAdd.setText("Name: "+ stringname.getP_address());
        holder.fName.setText("Address: "+ stringname.getP_name());
        holder.fDegree.setText("Highest Degree: "+ stringname.getP_degree());
        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int pId = stringname.getP_id();
                dbHelper = new OpenHelper(context);
                sqLiteDatabase = dbHelper.getWritableDatabase();
                sqLiteDatabase.delete(DatabaseInfo.TABLE_NAME,DatabaseInfo._ID+ " = " + pId,null);
                notifyItemRangeChanged(position,facultyDetails.size());
                facultyDetails.remove(position);
                notifyItemRemoved(position);
                sqLiteDatabase.close();
            }
        });

        holder.txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdatingFaculty.class);
                intent.putExtra("prodId", stringname.getP_id());
                context.startActivity(intent);

            }
        });
    }//onBindViewHolder

    @Override
    public int getItemCount() {
        return facultyDetails.size();
    }

    public class ProductViewer extends RecyclerView.ViewHolder {


        TextView fmId;
        TextView fId;
        TextView fName;
        TextView fAdd;
        TextView fDegree;
        TextView txtDelete;
        TextView txtUpdate;


        public ProductViewer(View itemView) {
            super(itemView);

            fmId = itemView.findViewById(R.id.txtId);
            fId =  itemView.findViewById(R.id.txtCode);
            fName = itemView.findViewById(R.id.txtName);
            fAdd = itemView.findViewById(R.id.txtDes);
            fDegree = itemView.findViewById(R.id.txtQuantity);
            txtDelete=itemView.findViewById(R.id.txtDelete);
            txtUpdate=itemView.findViewById(R.id.txtUpdate);

        }


    }

    public void deleteProduct(){

    }
}