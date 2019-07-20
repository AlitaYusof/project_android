package com.example.ttx.appmessagerme;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

public class Utility {

    public static String getAddress(Context context, double lat, double lng) {
        String address = null;
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses.isEmpty()) {
                address = "Waiting for Location";
            } else {
                if (addresses.size() > 0) {
                    String addres = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();

                    address = addres + ", " + postalCode;
                    Toast.makeText(context, "Address:- " + addresses.get(0).getFeatureName() + addresses.get(0).getAdminArea() + addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    public static String makeURL(LatLng origin, LatLng destination, String directionMode) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/directions/json");
        // from
        urlString.append("?origin=" + origin.latitude + "," + origin.longitude);
        // to
        urlString.append("&destination=" + destination.latitude + "," + destination.longitude);
        urlString.append("&sensor=false&mode=" + directionMode);

//        urlString.append("&sensor=false&mode=driving&alternatives=true");

        urlString.append("&key=AIzaSyAod57EwuHl2N7ffZDq_Thfn78xLFiakVQ");
        return urlString.toString();
    }
}
