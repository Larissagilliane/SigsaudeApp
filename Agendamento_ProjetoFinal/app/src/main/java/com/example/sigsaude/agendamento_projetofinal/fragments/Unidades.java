package com.example.sigsaude.agendamento_projetofinal.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sigsaude.agendamento_projetofinal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;


public class Unidades extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_unidades, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());

        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;


                //SEPA
                LatLng sepa = new LatLng(-5.838452, -35.198708);
                googleMap.addMarker(new MarkerOptions().position(sepa).title("SEPA").snippet("Serviço de Psicologia Aplicada").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).draggable(true));

                //Odontolodia
                LatLng odonto = new LatLng(-5.815947, -35.205733);
                googleMap.addMarker(new MarkerOptions().position(odonto).title("Departamento de Odontologia").snippet("Departamento de Odontologia - UFRN").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).draggable(true));

                //DAS
                LatLng das = new LatLng(-5.835340, -35.210758);
                googleMap.addMarker(new MarkerOptions().position(das).title("DAS - UFRN").snippet("Diretoria de Atenção à Saúde do Servidor da UFRN").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).draggable(true));

                //Nutrição
                LatLng nutricao = new LatLng(-5.834211, -35.202591);
                googleMap.addMarker(new MarkerOptions().position(nutricao).title("Nutrição").snippet("Departamento de Nutrição").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).draggable(true));


                CameraPosition cameraPosition = new CameraPosition.Builder().target(sepa).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
