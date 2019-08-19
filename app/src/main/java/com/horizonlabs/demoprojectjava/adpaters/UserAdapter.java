package com.horizonlabs.demoprojectjava.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.horizonlabs.demoprojectjava.R;
import com.horizonlabs.demoprojectjava.model.UserEntity;
import com.horizonlabs.demoprojectjava.utility.Util;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Rajeev Ranjan -  ABPB on 19-08-2019.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private ItemClick itemClick;
    List<UserEntity> userEntities = new ArrayList<>();
    Context context;

    public UserAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_all_user, viewGroup, false);
        return new UserHolder(itView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int i) {
        UserEntity userEntity = userEntities.get(i);
        holder.tvName.setText(userEntity.getName());
        holder.tvUserName.setText(userEntity.getUsername());
        holder.tvShortName.setText(Util.getShortName(userEntity.getName()));
        holder.tvAddress.setText(userEntity.getAddress().getStreet() + ", "
                + userEntity.getAddress().getSuite() + ", "
                + userEntity.getAddress().getCity());
        holder.tvMobile.setText(userEntity.getPhone());
        holder.tvEmail.setText(userEntity.getEmail());

        holder.tvShortName.setBackgroundResource(Util.getFixedBackground(i));

    }

    @Override
    public int getItemCount() {
        return userEntities.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvShortName, tvUserName, tvAddress, tvMobile, tvEmail;
        ImageView ivFavourite;

        public UserHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvShortName = itemView.findViewById(R.id.tvShortName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvMobile = itemView.findViewById(R.id.tvMobile);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            ivFavourite = itemView.findViewById(R.id.ivFavourite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemView != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                        itemClick.onItemClick(userEntities.get(getAdapterPosition()));
                }
            });
            ivFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemView != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                        itemClick.onFavouriteClick(userEntities.get(getAdapterPosition()));
                }
            });
        }
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
        notifyDataSetChanged();
    }

    public interface ItemClick {
        void onItemClick(UserEntity userEntity);

        void onFavouriteClick(UserEntity userEntity);
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.itemClick = listener;
    }
}
