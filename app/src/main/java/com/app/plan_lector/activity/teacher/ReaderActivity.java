package com.app.plan_lector.activity.teacher;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.plan_lector.R;
import com.app.plan_lector.activity.student.MainActivityStudent;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.epub.EpubReader;

public class ReaderActivity extends ListActivity {
    private LayoutInflater inflater;
    private List<RowData> contentDetails;
    private TextView logo;
    private Activity context;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_reader_teacher);
        final String BOOK_NAME = getIntent().getStringExtra("name");
        Button b2 = (Button)findViewById(R.id.resumen);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReaderActivity.this,MainActivityTeacher.class).putExtra("var",6).putExtra("descrip",BOOK_NAME));
            }
        });
        inflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        contentDetails = new ArrayList<RowData>();
        AssetManager assetManager = getAssets();
        try {
            InputStream epubInputStream = assetManager.open(BOOK_NAME);
            Book book = (new EpubReader()).readEpub(epubInputStream);
            logContentsTable(book.getTableOfContents().getTocReferences(), 0);
        } catch (IOException e) {
            Log.e("epublib", e.getMessage());
        }

        CustomAdapter adapter = new CustomAdapter(this, R.layout.list,
                R.id.title, contentDetails);
        setListAdapter(adapter);
        getListView().setTextFilterEnabled(true);
    }


    private class CustomAdapter extends ArrayAdapter<RowData> {

        public CustomAdapter(Context context, int resource,
                             int textViewResourceId, List<RowData> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        private class ViewHolder {
            private View row;
            private TextView titleHolder = null;

            public ViewHolder(View row) {
                super();
                this.row = row;
            }

            public TextView getTitle() {
                if (null == titleHolder)
                    titleHolder = (TextView) row.findViewById(R.id.title);
                return titleHolder;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            TextView title = null;
            RowData rowData = getItem(position);
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            title = holder.getTitle();
            title.setText(rowData.getTitle());
            return convertView;
        }

    }

    private void logContentsTable(List<TOCReference> tocReferences, int depth) {
        if (tocReferences == null) {
            return;
        }
        for (TOCReference tocReference : tocReferences) {
            StringBuilder tocString = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                tocString.append("\t");
            }
            tocString.append(tocReference.getTitle());
            RowData row = new RowData();
            row.setTitle(tocString.toString());
            row.setResource(tocReference.getResource());
            contentDetails.add(row);
            logContentsTable(tocReference.getChildren(), depth + 1);
        }
    }

    private class RowData {
        private String title;
        private Resource resource;

        public RowData() {
            super();
        }

        public String getTitle() {
            return title;
        }

        public Resource getResource() {
            return resource;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setResource(Resource resource) {
            this.resource = resource;
        }

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        RowData rowData = contentDetails.get(position);
        Intent intent = new Intent(ReaderActivity.this, ContentViewActivity.class);
        try {
            intent.putExtra("display", new String(rowData.getResource().getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivity(intent);

    }

}