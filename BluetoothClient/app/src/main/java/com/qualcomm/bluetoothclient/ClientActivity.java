package com.qualcomm.bluetoothclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ClientActivity extends Activity implements OnItemClickListener {

	//first part
	private Context mContext;
	private BluetoothAdapter mBluetoothAdapter; // Bluetooth adapter
	private BluetoothDevice device;             //bluetooth
	private ListView mListView;
	private ArrayList<ChatMessage> list;
	private ClientAdapter clientAdapter;        // ListView
	private Button disconnect = null, sendButton = null;
	private EditText editText = null;
	
	private BluetoothSocket socket;     // client socket
	private ClientThread mClientThread; // client running main thread
	private ReadThread mReadThread;     // reading thread

//	private TextView tv1,tv2,tv3,tv4,tv5;
//	private SensorManager sm;
//	private Sensor ac;
//	private HashMap<String, ArrayList<Integer>> mapAxis;
//	private ArrayList listX;
//	private ArrayList listY;
//	private ArrayList listZ;
	boolean chen = false;
	private Button btop,bbottom,bleft,bright,key1,key2,key3;
//	private int[] axi

	public ClientActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();


	}

	//second part
	
	//init
	private void init() {
		// TODO Auto-generated method stub


//		tv1 = (TextView) findViewById(R.id.textView1);
//		tv2 = (TextView) findViewById(R.id.textView2);
//		tv3 = (TextView) findViewById(R.id.textView3);
//		tv4 = (TextView) findViewById(R.id.textView4);
//		tv4.setMovementMethod(ScrollingMovementMethod.getInstance());
//		tv5 = (TextView) findViewById(R.id.textView5);
//
//		sm =(SensorManager)getSystemService(SENSOR_SERVICE);
//		ac = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//		axis = new int[3];
//
//
//
//
//		listX = new ArrayList();
//		listY = new ArrayList();
//		listZ = new ArrayList();
//		mapAxis = new HashMap<String, ArrayList<Integer>>();
//		mapAxis.put("x data",listX);
//		mapAxis.put("y data",listY);
//		mapAxis.put("z data",listZ);

//		SensorEventListener acSensorListener = new SensorEventListener() {
//			@Override
//			public void onSensorChanged(SensorEvent sensorEvent) {

//				float axisX = sensorEvent.values[0];
//				float axisY = sensorEvent.values[1];
//				float axisZ = sensorEvent.values[2];
//
//				BigDecimal bd1= new BigDecimal(axisX);
//				BigDecimal bd2= new BigDecimal(axisY);
//				BigDecimal bd3 = new BigDecimal(axisZ);
//
//				int x = bd1.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
//				int y = bd2.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
//				int z = bd3.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
//
//				listX.add(x);
//				listY.add(y);
//				listZ.add(z);
//
//				axis[0]=x;
//				axis[1]=y;
//				axis[2]=z;
//
//				tv1.setText(""+x);
//				tv2.setText(""+y);
//				tv3.setText(""+z);


//				refreshLogView(x,y,z);
//				tv5.setText(x+","+y+","+z+",");

//				if( chen == true ){
//					sendMessageHandler(Integer.valueOf(x)+"m"+Integer.valueOf(y)+"m"+Integer.valueOf(z)+"m");
//					//sendMessageHandler(tv5.getText().toString());
//				}


//			}
//			void refreshLogView(int x, int y, int z ){
//				//tv4.append("x= "+x+" y= "+y+" z= "+z+"; ");
//				tv4.append(""+x+y+z+",");
//				int offset=tv4.getLineCount()*tv4.getLineHeight();
//				if(offset>tv4.getHeight()){
//					tv4.scrollTo(0,offset-tv4.getHeight());
//				}
//			}

//			@Override
//			public void onAccuracyChanged(Sensor sensor, int i) {
//
//			}

//		};


	//	sm.registerListener(acSensorListener,ac,SensorManager.SENSOR_DELAY_UI);


//		Set<String> keys = mapAxis.keySet();
//
//		for(String key: keys){
//			tv4.setText(""+key);
//				ArrayList<Integer>  contacts = mapAxis.get(key);
//				for(int i= 0; i< contacts.size(); i++){
//					Integer axiscon = contacts.get(i);
//					tv4.setText("\t"+axiscon);
//				}
//
//		}


		mContext = this;
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();//try to get the local bluetooth device
		
		list = new ArrayList<ChatMessage>();// init.. list use listView
		clientAdapter = new ClientAdapter(mContext, list);  //show the listView
		
		mListView = (ListView) findViewById(R.id.list);
		
		mListView.setFastScrollEnabled(true);  //enable fast scroll
		
		mListView.setAdapter(clientAdapter);
		
		mListView.setOnItemClickListener(this);
		

		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(mReceiver, filter);
		

		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
		
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				

				list.add(new ChatMessage(device.getName() + "\n" + device.getAddress(), true));
				
				clientAdapter.notifyDataSetChanged();
				
				mListView.setSelection(list.size() - 1);
			}
		} else {  
			list.add(new ChatMessage("no paired device!", true));
			clientAdapter.notifyDataSetChanged();
			mListView.setSelection(list.size() - 1);
		}
		
		editText = (EditText) findViewById(R.id.edit);
		editText.setEnabled(false);
		editText.clearFocus();
		
		sendButton = (Button) findViewById(R.id.btn_send);

		btop = (Button) findViewById(R.id.top);
		bbottom = (Button) findViewById(R.id.bottom);
		bleft = (Button) findViewById(R.id.left);
		bright = (Button) findViewById(R.id.right);
		key1 = (Button) findViewById(R.id.key1);
		key2 = (Button) findViewById(R.id.key2);
		key3 = (Button) findViewById(R.id.key3);

		sendButton.setEnabled(false);
		btop.setEnabled(false);
		bbottom.setEnabled(false);
		bleft.setEnabled(false);
		bright.setEnabled(false);

		key1.setEnabled(false);
		key2.setEnabled(false);
		key3.setEnabled(false);

		sendButton.setOnClickListener(new OnClickListener() {//设置监听，只有获取到焦点后才能进行此过程...
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msg = editText.getText().toString();
				//String msg1 = tv5.getText().toString();

				
				if (msg.length() > 0 ) {
					sendMessageHandler(msg);

				} else {
					Toast.makeText(mContext, "Empty Message!", Toast.LENGTH_SHORT).show();
				}
			}
		});

		btop.setOnClickListener(new OnClickListener() {//设置btop监听，只有获取到焦点后才能进行此过程...

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msg = "w";


				if (msg.length() > 0 ) {
					sendMessageHandler(msg);

				} else {
					Toast.makeText(mContext, "Empty Message!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		bbottom.setOnClickListener(new OnClickListener() {//设置btop监听，只有获取到焦点后才能进行此过程...

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msg = "s";


				if (msg.length() > 0 ) {
					sendMessageHandler(msg);

				} else {
					Toast.makeText(mContext, "Empty Message!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		bleft.setOnClickListener(new OnClickListener() {//设置btop监听，只有获取到焦点后才能进行此过程...

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msg = "a";


				if (msg.length() > 0 ) {
					sendMessageHandler(msg);


				} else {
					Toast.makeText(mContext, "Empty Message!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		bright.setOnClickListener(new OnClickListener() {//设置btop监听，只有获取到焦点后才能进行此过程...

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msg = "d";

				if (msg.length() > 0 ) {
					sendMessageHandler(msg);

				} else {
					Toast.makeText(mContext, "Empty Message!", Toast.LENGTH_SHORT).show();
				}
			}
		});


		key1.setOnClickListener(new OnClickListener() {//设置btop监听，只有获取到焦点后才能进行此过程...

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msg = "1";


				if (msg.length() > 0 ) {
					sendMessageHandler(msg);

				} else {
					Toast.makeText(mContext, "Empty Message!", Toast.LENGTH_SHORT).show();
				}
			}
		});

		key2.setOnClickListener(new OnClickListener() {//设置btop监听，只有获取到焦点后才能进行此过程...

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msg = "2";


				if (msg.length() > 0 ) {
					sendMessageHandler(msg);

				} else {
					Toast.makeText(mContext, "Empty Message!", Toast.LENGTH_SHORT).show();
				}
			}
		});

		key3.setOnClickListener(new OnClickListener() {//设置btop监听，只有获取到焦点后才能进行此过程...

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msg = "3";


				if (msg.length() > 0 ) {
					sendMessageHandler(msg);
				} else {
					Toast.makeText(mContext, "Empty Message!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		disconnect = (Button) findViewById(R.id.disconnect);
		disconnect.setEnabled(false);
		disconnect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				closeClient();
				BluetoothMsg.isOpen = false;
				
				BluetoothMsg.serviceOrCilent = BluetoothMsg.ServerOrCilent.NONE;
				Toast.makeText(mContext, "Have disconnected!", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if (mBluetoothAdapter != null) {
			if (!mBluetoothAdapter.isEnabled()) {

        		Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        		startActivityForResult(enableIntent, RESULT_FIRST_USER);

        		Intent displayIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        		displayIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
        		startActivity(displayIntent);
        		
        		// open bluetooth directly
        		mBluetoothAdapter.enable();//enable the bluetooth
			}
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// scanning
		scanDevice();
	}


	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          
        	String action = intent.getAction();


            if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (device.getBondState() != BluetoothDevice.BOND_BONDED) 
                {
                	list.add(new ChatMessage(device.getName() + "\n" + device.getAddress(), false));
                	clientAdapter.notifyDataSetChanged();
            		mListView.setSelection(list.size() - 1);
                }
            } 
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                setProgressBarIndeterminateVisibility(false);
                if (mListView.getCount() == 0) 
                {
                	list.add(new ChatMessage("没有发现蓝牙设备", false));
                	clientAdapter.notifyDataSetChanged();
            		mListView.setSelection(list.size() - 1);
                }
            }
        }
    };

    
    private Handler LinkDetectedHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        	if(msg.what==1)
        	{
        		list.add(new ChatMessage((String)msg.obj, true));
        	}
        	else
        	{
        		list.add(new ChatMessage((String)msg.obj, false));
        	}
			clientAdapter.notifyDataSetChanged();
			mListView.setSelection(list.size() - 1);
			
        }
        
    };

    private Handler refreshUI = new Handler() {
    	public void handleMessage(Message msg) {
    		if (msg.what == 0) {

				chen = true;
				btop.setEnabled(true);
				bbottom.setEnabled(true);
				bleft.setEnabled(true);
				bright.setEnabled(true);

				key1.setEnabled(true);
				key2.setEnabled(true);
				key3.setEnabled(true);

    			disconnect.setEnabled(true);
				sendButton.setEnabled(true);
				editText.setEnabled(true);
			}
    	}
    };

    // open the client-side through a new thread
    private class ClientThread extends Thread {
    	@Override
    	public void run() {
    		// TODO Auto-generated method stub
    		if (device != null) {
				try {
					/* createRfcommSocketToServiceRecord(!!!! important)
					 * using default 00001101-0000-1000-8000-00805F9B34FB
					 */
					socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
					// 连接
					Message msg = new Message();
					msg.obj = "Please wait，connect the server: "+ BluetoothMsg.BlueToothAddress;
					msg.what = 0;
					LinkDetectedHandler.sendMessage(msg); //show msg
					
					// build connection through socket(block until...)
					socket.connect();
					//share an RFFCOMM channel
					Message msg2 = new Message();
					msg2.obj = "Have connected the server!";
					msg2.what = 0;
					LinkDetectedHandler.sendMessage(msg2);

					Message uiMessage = new Message();
					uiMessage.what = 0;
					refreshUI.sendMessage(uiMessage);
					
					// right now open the reading channel
					mReadThread = new ReadThread();
					mReadThread.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Message msg = new Message();
					msg.obj = "Got a problem! Please reconnect!";
					msg.what = 0;
					LinkDetectedHandler.sendMessage(msg);
				}
			}
    	}
    }

    private class ReadThread extends Thread {
    	@Override
    	public void run() {
    		// TODO Auto-generated method stub
    
    		byte[] buffer = new byte[1024];
    		int bytes;
    		InputStream is = null;
    		try {
				is = socket.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println("get");
    		while(true) {
    			try {  // data stream!!!!!!
					if ((bytes = is.read(buffer)) > 0) {
						byte[] data = new byte[bytes];
						for (int i = 0; i < data.length; i++) {
							data[i] = buffer[i];
						}
						String s = new String(data);
						Message msg = new Message();
						msg.obj = s;
						msg.what = 1;
						LinkDetectedHandler.sendMessage(msg);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					try {
						is.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
    		}
    	}
    }
	//the process for send data
    private void sendMessageHandler(String msg) {
    	if (socket == null) {
			Toast.makeText(mContext, "null connection", Toast.LENGTH_SHORT).show();
			return;
		}
    	System.out.println("post");

    	try {
			OutputStream os = socket.getOutputStream();
			os.write(msg.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    // stop the server
    private void closeClient() {
    	new Thread() {
    		public void run() {
    			if (mClientThread != null) {
    				mClientThread.interrupt();
    				mClientThread = null;
				}
    			if (mReadThread != null) {
					mReadThread.interrupt();
					mReadThread = null;
				}
    			try {
					if (socket != null) {
						socket.close();
						socket = null;
					}
				} catch (IOException e) {
					// TODO: handle exception
				}
    		}
    	}.start();
    }

    private void scanDevice() {
		// TODO Auto-generated method stub
    	if (mBluetoothAdapter.isDiscovering()) {
			mBluetoothAdapter.cancelDiscovery();
		} else {
			list.clear();
			clientAdapter.notifyDataSetChanged();

			Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
			if (pairedDevices.size() > 0) {
		            for (BluetoothDevice device : pairedDevices) {
		            	list.add(new ChatMessage(device.getName() + "\n" + device.getAddress(), true));
		            	clientAdapter.notifyDataSetChanged();
		        		mListView.setSelection(list.size() - 1);
		            }
		    } else {
		        	list.add(new ChatMessage("No devices have been paired", true));
		        	clientAdapter.notifyDataSetChanged();
		    		mListView.setSelection(list.size() - 1);
     	     }
			mBluetoothAdapter.startDiscovery();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		ChatMessage item = list.get(arg2);
		String info = item.getMessage();
		
		String address = info.substring(info.length() - 17);
		
		
		BluetoothMsg.BlueToothAddress = address;

		mBluetoothAdapter.cancelDiscovery();

		device = mBluetoothAdapter.getRemoteDevice(BluetoothMsg.BlueToothAddress);
		mClientThread = new ClientThread();
		mClientThread.start();
		BluetoothMsg.isOpen = true;
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mBluetoothAdapter != null) {
			mBluetoothAdapter.cancelDiscovery();
			mBluetoothAdapter.disable();
		}
		unregisterReceiver(mReceiver);
		closeClient();
	}



}
