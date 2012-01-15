package dozedoff.AndroidSandbox;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Alarm triggered", Toast.LENGTH_LONG).show();

	}

	public void setAlarm (Context context){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 5);
		
		Toast.makeText(context, "Alarm set for :"+ cal.getTime(), Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(context,Alarm.class);
		
		PendingIntent operation = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		
		AlarmManager alm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alm.set(AlarmManager.RTC, cal.getTimeInMillis(), operation);
	}
}
