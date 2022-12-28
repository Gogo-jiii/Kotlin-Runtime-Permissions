package com.example.runtimepermissions

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 100
    private val permissions = arrayOf<String?>(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAskPermissions.setOnClickListener {
            if (!PermissionManager.getInstance(this@MainActivity)!!
                    .checkPermissions(permissions)
            ) {
                PermissionManager.getInstance(this@MainActivity)!!.askPermissions(
                    this@MainActivity,
                    permissions, PERMISSION_REQUEST_CODE
                )
            } else {
                Toast.makeText(
                    this@MainActivity, "Permission already granted!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnCheckPermissions.setOnClickListener {
            if (PermissionManager.getInstance(this@MainActivity)!!.checkPermissions(permissions)) {
                Toast.makeText(
                    this@MainActivity, "Permissions already granted.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@MainActivity, "Please request permissions.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            PermissionManager.getInstance(this@MainActivity)!!.handlePermissionResult(
                this@MainActivity,
                PERMISSION_REQUEST_CODE,
                permissions,
                grantResults
            )
        }
    }
}