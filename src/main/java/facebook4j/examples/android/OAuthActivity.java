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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import facebook4j.Facebook;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class OAuthActivity extends Activity implements OAuthWebView.Callback {
    
    public static final String DATA_KEY_FACEBOOK = "facebook";

    private OAuthWebView mOAuthWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOAuthWebView = new OAuthWebView(this);
        setContentView(mOAuthWebView);
        mOAuthWebView.start(this);
    }

    @Override
    public void onSuccess(Facebook facebook) {
        Intent data = new Intent();
        data.putExtra(DATA_KEY_FACEBOOK, facebook);
        setResult(RESULT_OK, data);
        finish();
    }

}
