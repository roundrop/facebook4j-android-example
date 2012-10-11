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

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import facebook4j.Facebook;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
@TargetApi(3)
public class NewsFeedReaderTask extends AsyncTask<Void, Void, NewsFeedAdapter> {
    private static final int FEED_LIMIT = 10;

    private Facebook mFacebook;
    private NewsFeedActivity mActivity;
    private NewsFeedAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private Throwable t = null;

    public NewsFeedReaderTask(Facebook facebook, NewsFeedActivity activity, NewsFeedAdapter adapter) {
        mFacebook = facebook;
        mActivity = activity;
        mAdapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setMessage("Now Loading...");
        mProgressDialog.show();
    }

    @Override
    protected NewsFeedAdapter doInBackground(Void... params) {
        try {
            ResponseList<Post> feed = mFacebook.getHome(new Reading()
                                                        .fields("from", "message")
                                                        .limit(FEED_LIMIT));
            for (Post post : feed) {
                mAdapter.add(post);
            }
        } catch (Throwable t) {
            this.t = t;
        }
        return mAdapter;
    }

    @Override
    protected void onPostExecute(NewsFeedAdapter result) {
        mProgressDialog.dismiss();
        if (t != null) {
            mActivity.onError(t);
            return;
        }
        mActivity.setListAdapter(result);
    }

}
