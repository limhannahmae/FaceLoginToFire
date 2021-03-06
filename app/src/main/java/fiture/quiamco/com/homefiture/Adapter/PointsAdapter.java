package fiture.quiamco.com.homefiture.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fiture.quiamco.com.homefiture.R;

/**
 * Created by User on 14/10/2017.
 */

public class PointsAdapter extends RecyclerView.Adapter <PointsAdapter.MyViewHolder> {

    private List<Points> pointsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, points,category;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            points = (TextView) view.findViewById(R.id.points);
            category = (TextView) view.findViewById(R.id.category);

        }
    }

        public PointsAdapter(List<Points> pointsList) {
            this.pointsList = pointsList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.leaderboard_row, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Points points = pointsList.get(position);
            holder.name.setText(points.getName());
            holder.points.setText(points.getPoints());
            holder.category.setText(points.getCategory());

        }

        @Override
        public int getItemCount() {
            return pointsList.size();
        }
    }

