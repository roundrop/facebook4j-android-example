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
import android.widget.TextView;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class PostDetailActivity extends Activity {
    private TextView mFrom;
    private TextView mMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_detail);
        
        Intent intent = getIntent();

        String from = intent.getStringExtra("FROM");
        mFrom = (TextView) findViewById(R.id.post_detail_from);
        mFrom.setText(from);
        String message = intent.getStringExtra("MESSAGE");
        mMessage = (TextView) findViewById(R.id.post_detail_message);
        mMessage.setText(message);
    }

}
