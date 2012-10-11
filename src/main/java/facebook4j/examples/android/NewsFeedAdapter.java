/*
 * Copyright 2012 Ryuji Yamashita
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package facebook4j.examples.android;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import facebook4j.Post;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class NewsFeedAdapter extends ArrayAdapter<Post> {
    private LayoutInflater mInflater;
    private TextView mFrom;
    private TextView mMessage;

    public NewsFeedAdapter(Context context, List<Post> objects) {
        super(context, 0, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.feed_row, null);
        }

        Post post = this.getItem(position);
        if (post != null) {
            mFrom = (TextView) view.findViewById(R.id.post_from);
            mFrom.setText(post.getFrom().getName());
            mMessage = (TextView) view.findViewById(R.id.post_message);
            mMessage.setText(post.getMessage() == null ? "" : post.getMessage());
        }
        return view;
    }
}
