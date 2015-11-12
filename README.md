# PermissionManager - Android-Marshmallow
easy framwork to work with new Android Marshmallow permission logic


#usage:

        PermissionManager.requestPermission(this, new PermissionManager.PermissionsListener() {
                @Override
                public void onDeny() {
                    Toast.makeText(MainActivity.this, "CALENDAR onDeny", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAllow() {
                    Toast.makeText(MainActivity.this, "CALENDAR onAllow", Toast.LENGTH_SHORT).show();
                }
        }, PermissionManager.READ_CALENDAR);
        
        
        
#onRequestPermissionsResult implementation:
        @Override
        public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
            PermissionManager.onResult(requestCode,permissions,grantResults);
        }
