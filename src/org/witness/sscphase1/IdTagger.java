package org.witness.sscphase1;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class IdTagger extends Activity {
	Bundle b;
	String imageRegion;
	
	EditText namespace;
	CheckBox consentCheckbox;
	ImageButton confirmTag;
	String queryBuffer;
	ListView tagSuggestionsHolder;
	ArrayList<String> al;
	
	SSCMetadataHandler mdh;
	boolean shouldLookupNames, isNewSubject;
	
	private static final String SSC = "[Camera Obscura : IdTagger] ****************************";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.idtagger);
		
		namespace = (EditText) findViewById(R.id.namespace);
		consentCheckbox = (CheckBox) findViewById(R.id.consentCheckbox);
		confirmTag = (ImageButton) findViewById(R.id.confirmTag);
		confirmTag.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(v == confirmTag) {
					saveSubject(namespace.getText().toString());
				}
			}
		});
		
		b = getIntent().getExtras();
		imageRegion = b.getString("imageRegion");
		
		tagSuggestionsHolder = (ListView) findViewById(R.id.tagSuggestionsHolder);
		al = new ArrayList<String>();
		
		shouldLookupNames = false;
		isNewSubject = true;
		
		mdh = new SSCMetadataHandler(this);
		/*
		try {
			mdh.createDatabase();
		} catch(IOException e) {}
		*/
		try {
			mdh.openDataBase();
		} catch(SQLException e) {}
		
		if(shouldLookupNames) {
			namespace.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {
					Log.v(SSC,"after text changed: " + s.toString());
				
				}

				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					Log.v(SSC,"before text changed: " + s.toString());
				
				}

				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
					Log.v(SSC,"ON text changed: " + s.toString());
				}
			});
		}
	}
	
	public void saveSubject(String subjectName) {
		if(subjectName.compareTo("") != 0) {
			int finalConsent = 0;
			if(consentCheckbox.isChecked()) {
				finalConsent = 1;
			}
			Intent i = new Intent(this,ImageEditor.class);
			i.putExtra("addedSubject", subjectName);
			i.putExtra("subjectConsent", Integer.toString(finalConsent));
			i.putExtra("imageRegion", imageRegion);
			setResult(Activity.RESULT_OK,i);
			finish();
		} else {
			// TODO: toast to the user that they didn't input anything
		}
		
	}
}