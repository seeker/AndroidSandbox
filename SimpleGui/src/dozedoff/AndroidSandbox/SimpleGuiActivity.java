package dozedoff.AndroidSandbox;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class SimpleGuiActivity extends Activity {
	private TextView text;
	private int counter = 0;
	private Button button;
	private Button btnToast;
	private EditText txtInput;
	private TableLayout table;
	private ArrayList<String> savedEntry = new ArrayList<String>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		text = (TextView)this.findViewById(R.id.text);
		button = (Button)this.findViewById(R.id.button);
		btnToast = (Button)this.findViewById(R.id.btnToast);
		txtInput = (EditText)this.findViewById(R.id.txtInput);
		table = (TableLayout)this.findViewById(R.id.table);

		updateText();
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				counter++;
				updateText();
			}
		});

		btnToast.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast toast = Toast.makeText(getApplicationContext(), "A Toast to you!", 1);
				toast.show();
			}
		});

		txtInput.setOnEditorActionListener(new OnEditorActionListener() {

			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
					if(! txtInput.equals("")){
						Toast toast = Toast.makeText(getApplicationContext(), "You entered the text: "+txtInput.getText(), 1);
						toast.show();
						TextView newtext = new TextView(getApplicationContext());
						newtext.setText(txtInput.getText());
						savedEntry.add(newtext.getText().toString());
						table.addView(newtext);
						txtInput.setText("");
						return true;
					}
				}
				return false;
			}
		});
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putStringArrayList("entry", savedEntry);
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if(savedInstanceState.getStringArrayList("entry") == null){
			savedEntry = new ArrayList<String>();
			savedEntry.add("Return was NULL");
		}else{
			savedEntry = savedInstanceState.getStringArrayList("entry");
		}
		
		for(String s : savedEntry){
			TextView tv = new TextView(getApplicationContext());
			tv.setText(s);
			table.addView(tv);
		}
		super.onRestoreInstanceState(savedInstanceState);
	}

	private void updateText(){
		text.setText("Button pressed "+counter+" times.");
	}
}