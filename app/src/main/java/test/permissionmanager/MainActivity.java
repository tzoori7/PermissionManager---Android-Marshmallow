package test.permissionmanager;

import android.graphics.Rect;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LocationListener, View.OnClickListener {


    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    boolean canGetLocation = false;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("CALENDAR");
        permissions.add("CAMERA");
        permissions.add("CONTACTS");
        permissions.add("LOCATION");
        permissions.add("MICROPHONE");
        permissions.add("PHONE");
        permissions.add("SENSORS");
        permissions.add("SMS");
        permissions.add("STORAGE");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpacesItemDecoration(50));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MyAdapter(permissions, this));

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onClick(View v) {
        switch (((TextView) v).getText().toString()) {
            case "CALENDAR":
                PermissionManager.requestPermission(this, new PermissionManager.PermissionsListener() {
                    @Override
                    public void onDeny() {
                        Toast.makeText(MainActivity.this, "on CALENDAR onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAllow() {
                        Toast.makeText(MainActivity.this, "on CALENDAR onAllow", Toast.LENGTH_SHORT).show();
                    }
                }, PermissionManager.READ_CALENDAR);
                break;
            case "CAMERA":
                PermissionManager.requestPermission(this, new PermissionManager.PermissionsListener() {
                    @Override
                    public void onDeny() {
                        Toast.makeText(MainActivity.this, "on CAMERA onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAllow() {
                        Toast.makeText(MainActivity.this, "on CAMERA onAllow", Toast.LENGTH_SHORT).show();
                    }
                }, PermissionManager.CAMERA);
                break;
            case "CONTACTS":
                PermissionManager.requestPermission(this, new PermissionManager.PermissionsListener() {
                    @Override
                    public void onDeny() {
                        Toast.makeText(MainActivity.this, "on CONTACTS onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAllow() {
                        Toast.makeText(MainActivity.this, "on CONTACTS onAllow", Toast.LENGTH_SHORT).show();
                    }
                }, PermissionManager.READ_CONTACTS);
                break;
            case "LOCATION":
                PermissionManager.requestPermission(this, new PermissionManager.PermissionsListener() {
                    @Override
                    public void onDeny() {
                        Toast.makeText(MainActivity.this, "on LOCATION onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAllow() {
                        Toast.makeText(MainActivity.this, "on LOCATION onAllow", Toast.LENGTH_SHORT).show();
                    }
                }, PermissionManager.ACCESS_COARSE_LOCATION);
                break;
            case "MICROPHONE":
                PermissionManager.requestPermission(this, new PermissionManager.PermissionsListener() {
                    @Override
                    public void onDeny() {
                        Toast.makeText(MainActivity.this, "on MICROPHONE onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAllow() {
                        Toast.makeText(MainActivity.this, "on MICROPHONE onAllow", Toast.LENGTH_SHORT).show();
                    }
                }, PermissionManager.RECORD_AUDIO);
                break;
            case "PHONE":
                PermissionManager.requestPermission(this, new PermissionManager.PermissionsListener() {
                    @Override
                    public void onDeny() {
                        Toast.makeText(MainActivity.this, "on PHONE onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAllow() {
                        Toast.makeText(MainActivity.this, "on PHONE onAllow", Toast.LENGTH_SHORT).show();
                    }
                }, PermissionManager.READ_PHONE_STATE);
                break;
            case "SENSORS":
                PermissionManager.requestPermission(this, new PermissionManager.PermissionsListener() {
                    @Override
                    public void onDeny() {
                        Toast.makeText(MainActivity.this, "on SENSORS onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAllow() {
                        Toast.makeText(MainActivity.this, "on SENSORS onAllow", Toast.LENGTH_SHORT).show();
                    }
                }, PermissionManager.BODY_SENSORS);
                break;
            case "SMS":
                PermissionManager.requestPermission(this, new PermissionManager.PermissionsListener() {
                    @Override
                    public void onDeny() {
                        Toast.makeText(MainActivity.this, "on SMS onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAllow() {
                        Toast.makeText(MainActivity.this, "on SMS onAllow", Toast.LENGTH_SHORT).show();
                    }
                }, PermissionManager.READ_SMS);
                break;
            case "STORAGE":
                PermissionManager.requestPermission(this, new PermissionManager.PermissionsListener() {
                    @Override
                    public void onDeny() {
                        Toast.makeText(MainActivity.this, "on STORAGE onDeny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAllow() {
                        Toast.makeText(MainActivity.this, "on STORAGE onAllow", Toast.LENGTH_SHORT).show();
                    }
                }, PermissionManager.READ_EXTERNAL_STORAGE);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        PermissionManager.onResult(requestCode,permissions,grantResults);

    }
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = space;

            // Add top margin only for the first item to avoid double space between items
        }
    }

}
