package dozedoff.AndroidSandbox;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WallpaperSelectorActivity extends Activity {
	Button btnSelectWp,btnClearWp,btnNudgeWp;
	EditText txtXoffset, txtYoffset;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnSelectWp = (Button)this.findViewById(R.id.btnSelectWp);
        btnClearWp = (Button)this.findViewById(R.id.btnClearWallpaper);
        btnNudgeWp = (Button)this.findViewById(R.id.btnNudge);
        
        txtXoffset = (EditText)this.findViewById(R.id.txtXoffset);
        txtYoffset = (EditText)this.findViewById(R.id.txtYoffset);
        
        btnSelectWp.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent("org.openintents.action.PICK_FILE");
				intent.setData(Uri.parse("file:///sdcard/Images/"));
				intent.putExtra("org.openintents.extra.TITLE", "Please select a Image");
				startActivityForResult(intent, 1);
				}
		});
        
        btnClearWp.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try {
					WallpaperManager.getInstance(getApplicationContext()).clear();
					Toast.makeText(getApplicationContext(), "Wallpaper cleared", Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					Toast.makeText(getApplicationContext(), "Failed to clear Wallpaper", Toast.LENGTH_SHORT).show();
				}
			}
		});
        
        btnNudgeWp.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try{
				float x = Float.parseFloat(txtXoffset.getText().toString());
				float y = Float.parseFloat(txtYoffset.getText().toString());
				WallpaperManager.getInstance(getApplicationContext()).setWallpaperOffsets(v.getApplicationWindowToken(), x, y);
				Toast.makeText(getApplicationContext(), "Set Wallpaper offset", Toast.LENGTH_SHORT).show();
				}catch(NumberFormatException nfe){
					Toast.makeText(getApplicationContext(), "Entry must be a valid float number", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(resultCode == RESULT_OK){
    		String path = data.getDataString().toString();

    		if(path.toLowerCase().endsWith(".jpg") || path.toLowerCase().endsWith(".png")){
    			InputStream in = null;
    			try{
    				
    				in = new BufferedInputStream(new FileInputStream(new File(new URI(data.getData().toString()))));
    				WallpaperManager.getInstance(getApplicationContext()).setStream(in);
    				in.close();
    				Toast.makeText(getApplicationContext(), "Wallpaper set", Toast.LENGTH_SHORT).show();
    			}catch(IOException ioe){
    				Toast.makeText(getApplicationContext(), "failed to set wallpaper: "+ioe.getMessage(), Toast.LENGTH_LONG).show();
    				Log.e("Set WP", ioe.getMessage());
    			} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else{
    			Toast.makeText(getApplicationContext(), "Not an image", Toast.LENGTH_SHORT).show();
    		}
    	}else{
    		Toast toast = Toast.makeText(getApplicationContext(), "No file selected", Toast.LENGTH_SHORT);
    		toast.show();
    	}
		super.onActivityResult(requestCode, resultCode, data);
    }
}