package com.example.raven;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.util.Locale.getDefault;

public class MessageActivity extends AppCompatActivity {

    private EditText PhoneNumber;
    private EditText msg;
    String sent = "SMS_SENT";
    String delivered = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReciever, smsDeliveredReceiever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        this.PhoneNumber = (EditText)findViewById(R.id.num3);
        this.msg = (EditText)findViewById(R.id.msgtxt);

        sentPI = PendingIntent.getBroadcast(this, 0, new Intent(sent), 0);
        deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(delivered), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        smsSentReciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(MessageActivity.this, "SMS sent", Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(MessageActivity.this, "Generic failure", Toast.LENGTH_SHORT).show();
                        break;

                        case SmsManager.RESULT_ERROR_NO_SERVICE:
                            Toast.makeText(MessageActivity.this, "No service", Toast.LENGTH_SHORT).show();
                            break;

                            case SmsManager.RESULT_ERROR_NULL_PDU:
                                Toast.makeText(MessageActivity.this, "Null PDU", Toast.LENGTH_SHORT).show();
                                break;

                                case SmsManager.RESULT_ERROR_RADIO_OFF:
                                    Toast.makeText(MessageActivity.this, "Radio off", Toast.LENGTH_SHORT).show();
                                    break;
                }
            }
        };

        smsDeliveredReceiever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(MessageActivity.this, "SMS delivered", Toast.LENGTH_SHORT).show();
                        break;

                    case Activity.RESULT_CANCELED:
                        Toast.makeText(MessageActivity.this, "SMS not delivered", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        registerReceiver(smsSentReciever, new IntentFilter(sent));
        registerReceiver(smsDeliveredReceiever, new IntentFilter(delivered));

    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(smsSentReciever);
        unregisterReceiver(smsDeliveredReceiever);
    }

    public void btn_SendSMS_OnClick (View v){
        String message = msg.getText().toString();
        String phoneNo = PhoneNumber.getText().toString();

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.SEND_SMS
            }, 0);
        }
        else {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(this.PhoneNumber.getText().toString(),
                    null, this.msg.getText().toString(), sentPI, deliveredPI);
        }
    }
}
