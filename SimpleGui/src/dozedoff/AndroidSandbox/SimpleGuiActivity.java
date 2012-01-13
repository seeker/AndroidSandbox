package dozedoff.AndroidSandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleGuiActivity extends Activity {
	private TextView text;
	private int counter = 0;
	private Button button;
	private Button btnToast;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        text = (TextView)this.findViewById(R.id.text);
        button = (Button)this.findViewById(R.id.button);
        btnToast = (Button)this.findViewById(R.id.btnToast);
        
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
    }
    
    
    
    private void updateText(){
    	text.setText("Button pressed "+counter+" times.");
    }
}