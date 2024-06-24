package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.CustomerMeasurement;
import com.example.VirtualFitON.Repositories.CustomerMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerMeasurementService {

    @Autowired
    private CustomerMeasurementRepository customerMeasurementRepository;

    public Map<String, Double> getCustomerMeasurementObject(int customerId) {
        // Fetch the measurements for the given customer ID
        System.out.println("ENTERED TO CUS MEASUREMENT");
        CustomerMeasurement customerMeasurement = customerMeasurementRepository.findByCustomerCustomerId(customerId);
        System.out.println("GOT CUS MEASUREMENT");
        // Convert the measurements to a map
        Map<String, Double> measurements = new HashMap<>();
        measurements.put("ankle_circumference", customerMeasurement.getAnkleCircumference());
        measurements.put("arm_length", customerMeasurement.getArmLength());
        measurements.put("bicep_circumference", customerMeasurement.getBicepCircumference());
        measurements.put("calf_circumference", customerMeasurement.getCalfCircumference());
        measurements.put("chest_circumference", customerMeasurement.getChestCircumference());
        measurements.put("forearm_circumference", customerMeasurement.getForearmCircumference());
        measurements.put("head_circumference", customerMeasurement.getHeadCircumference());
        measurements.put("hip_circumference", customerMeasurement.getHipCircumference());
        measurements.put("inside_leg_length", customerMeasurement.getInsideLegLength());
        measurements.put("neck_circumference", customerMeasurement.getNeckCircumference());
        measurements.put("shoulder_breadth", customerMeasurement.getShoulderBreadth());
        measurements.put("shoulder_to_crotch", customerMeasurement.getShoulderToCrotch());
        measurements.put("thigh_circumference", customerMeasurement.getThighCircumference());
        measurements.put("waist_circumference", customerMeasurement.getWaistCircumference());
        measurements.put("wrist_circumference", customerMeasurement.getWristCircumference());

        return measurements;
    }

    public void saveCustomerMeasurements(Map<String,Double> customerMeasurementsDTO){
        CustomerMeasurement customerMeasurement = new CustomerMeasurement();

    }
}
