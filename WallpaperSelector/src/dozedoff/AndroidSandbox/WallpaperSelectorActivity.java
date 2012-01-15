package dozedoff.AndroidSandbox;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class WallpaperSelectorActivity extends Activity {
	Button btnSelectWp;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnSelectWp = (Button)this.findViewById(R.id.btnSelectWp);
        btnSelectWp.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent("org.openintents.action.PICK_FILE");
				intent.setData(Uri.parse("file:///sdcard/notepad.csv"));
				intent.putExtra("org.openintents.extra.TITLE", "Please select a file");
				startActivityForResult(intent, 1);
				}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Toast toast = Toast.makeText(getApplicationContext(), data.getData().toString(), Toast.LENGTH_SHORT);
		toast.show();
    	super.onActivityResult(requestCode, resultCode, data);
    }
}