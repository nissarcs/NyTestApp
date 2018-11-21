package com.nissar.nytestapp.adapter;

import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.nissar.nytestapp.R;
import com.nissar.nytestapp.activity.MainActivity;
import com.nissar.nytestapp.fragment.ArticleDetailFragment;
import com.nissar.nytestapp.model.Response;
import com.nissar.nytestapp.utils.Constant;
import com.nissar.nytestapp.utils.FragmentHelper;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private Response response;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView date;
        final public ImageView img_article_icon;
        public View layout;
        public TextView source;
        public TextView byLine;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.title);
            img_article_icon = (ImageView)v.findViewById(R.id.img_article_icon);
            date = (TextView)v.findViewById(R.id.date);
            source = (TextView)v.findViewById(R.id.source);
            byLine = (TextView)v.findViewById(R.id.byLine);


        }
    }

    public FragmentManager fragmentManager;
    public MainActivity mainActivity;
    // Provide a suitable constructor (depends on the kind of dataset)
    public ArticlesAdapter(Response response, MainActivity mainActivity, FragmentManager fragmentManager) {
        this.response = response;
        this.fragmentManager = fragmentManager;
        this.mainActivity = mainActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.layout_fragment_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BUNDLE_ARTICLE_URL,response.getResults().get(position).getUrl());
                articleDetailFragment.setArguments(bundle);
                FragmentHelper.addAndInitFragmentWithBackStack(articleDetailFragment,R.id.fragment_content_container,fragmentManager);

            }
        });

        final String name = response.getResults().get(position).getTitle();
        holder.txtHeader.setText(name);
        holder.date.setText(response.getResults().get(position).getPublishedDate());

        holder.source.setText(response.getResults().get(position).getSource());
        holder.byLine.setText(response.getResults().get(position).getByline());


        Glide.with(mainActivity).load(response.getResults().get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.img_article_icon) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mainActivity.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.img_article_icon.setImageDrawable(circularBitmapDrawable);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return response.getResults().size();
    }

}
