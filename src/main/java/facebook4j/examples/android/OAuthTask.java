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

import java.net.URL;
import java.util.concurrent.CountDownLatch;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.AsyncTask;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
@TargetApi(3)
public class OAuthTask extends AsyncTask<Object, Void, OAuthWebView> {

    private OAuthWebView mOAuthWebView;
    private URL mCallbackURL;
    private String mCode;
    private CountDownLatch mLatch = new CountDownLatch(1);

    @Override
    protected OAuthWebView doInBackground(Object... params) {
        mOAuthWebView = (OAuthWebView) params[0];
        mCallbackURL = (URL) params[1];

        mOAuthWebView.setWebViewClient(new InternalWebViewClient());
        mOAuthWebView.setFacebook(new FacebookFactory().getInstance());
        publishProgress();
        waitForAuthorization();
        if (mCode == null) {
            System.out.println("oauth code is null!!!!!!!!");
            return mOAuthWebView;
        }
        AccessToken accessToken = getAccessToken();
        if (accessToken == null) {
            System.out.println("Access Token is null!!!!!!!!");
            return mOAuthWebView;
        }
        return mOAuthWebView;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Facebook facebook = mOAuthWebView.getFacebook();
        String url = facebook.getOAuthAuthorizationURL(mCallbackURL.toString());
        mOAuthWebView.loadUrl(url);
    }


    @Override
    protected void onPostExecute(OAuthWebView result) {
        mOAuthWebView.end();
    }

    private void waitForAuthorization() {
        try {
            mLatch.await();
        } catch (InterruptedException e) {}
    }

    private AccessToken getAccessToken() {
        try {
            Facebook facebook = mOAuthWebView.getFacebook();
            return facebook.getOAuthAccessToken(mCode);
        } catch (FacebookException e) {
            e.printStackTrace();
            return null;
        }
    }



    private class InternalWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!url.startsWith(mCallbackURL.toString())) {
                return false;
            }
            Uri uri = Uri.parse(url);
            mCode = uri.getQueryParameter("code");
            mLatch.countDown();
            return true;
        }
        
    }

}
