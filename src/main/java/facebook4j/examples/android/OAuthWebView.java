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

import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import facebook4j.Facebook;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class OAuthWebView extends WebView {
    private Callback mCallback;
    private Facebook mFacebook;

    public OAuthWebView(Context context) {
        super(context);
        init();
    }

    public OAuthWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OAuthWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        WebSettings settings = getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
    }
    
    public Facebook getFacebook() {
        return mFacebook;
    }
    
    public void setFacebook(Facebook facebook) {
        mFacebook = facebook;
    }
    
    public void start(Callback callback) {
        mCallback = callback;
        try {
            new OAuthTask().execute(this, new URL("http://facebook4j.org/"));
        } catch (MalformedURLException ignore) {}
    }

    public void end() {
        mCallback.onSuccess(mFacebook);
    }
    
    interface Callback {
        void onSuccess(Facebook facebook);
    }

}
