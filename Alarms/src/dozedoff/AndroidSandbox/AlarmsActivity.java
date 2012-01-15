package dozedoff.AndroidSandbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AlarmsActivity extends Activity {
	Button btnSetAlarm;
	Alarm alarm = new Alarm();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnSetAlarm = (Button)findViewById(R.id.btnSetAlarm);
        
        btnSetAlarm.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				alarm.setAlarm(getApplicationContext());
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Toast.makeText(getApplicationContext(), "Alarm triggered", Toast.LENGTH_LONG).show();
    	super.onActivityResult(requestCode, resultCode, data);
    }
}