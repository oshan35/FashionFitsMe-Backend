package com.example.VirtualFitON.Service;

import com.example.VirtualFitON.DTO.CustomerMeasurementDTO;
import com.example.VirtualFitON.Models.Customer;
import com.example.VirtualFitON.Models.CustomerMeasurement;
import com.example.VirtualFitON.Repositories.CustomerMeasurementRepository;
import com.example.VirtualFitON.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerMeasurementService {

    @Autowired
    private CustomerMeasurementRepository customerMeasurementRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Map<String, Double> getCustomerMeasurementObject(int customerId) {
        CustomerMeasurement customerMeasurement = customerMeasurementRepository.findByCustomerCustomerId(customerId);

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

    public void saveCustomerMeasurements(int customerId, Map<String, Object> customerMap, String modelUrl) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));

        CustomerMeasurement customerMeasurement = new CustomerMeasurement();
        customerMeasurement.setCustomer(customer);
        customerMeasurement.setAnkleCircumference((Double) customerMap.get("ankle_circumference"));
        customerMeasurement.setArmLength((Double) customerMap.get("arm_length"));
        customerMeasurement.setBicepCircumference((Double) customerMap.get("bicep_circumference"));
        customerMeasurement.setCalfCircumference((Double) customerMap.get("calf_circumference"));
        customerMeasurement.setChestCircumference((Double) customerMap.get("chest_circumference"));
        customerMeasurement.setForearmCircumference((Double) customerMap.get("forearm_circumference"));
        customerMeasurement.setHeadCircumference((Double) customerMap.get("head_circumference"));
        customerMeasurement.setHipCircumference((Double) customerMap.get("hip_circumference"));
        customerMeasurement.setInsideLegLength((Double) customerMap.get("inside_leg_length"));
        customerMeasurement.setNeckCircumference((Double) customerMap.get("neck_circumference"));
        customerMeasurement.setShoulderBreadth((Double) customerMap.get("shoulder_breadth"));
        customerMeasurement.setShoulderToCrotch((Double) customerMap.get("shoulder_to_crotch"));
        customerMeasurement.setThighCircumference((Double) customerMap.get("thigh_circumference"));
        customerMeasurement.setWaistCircumference((Double) customerMap.get("waist_circumference"));
        customerMeasurement.setWristCircumference((Double) customerMap.get("wrist_circumference"));
        customerMeasurement.setBodyModelUrl(modelUrl);

        CustomerMeasurement savedCustomerMeasurement=customerMeasurementRepository.save(customerMeasurement);
    }

    public void updateCustomerMeasurement(CustomerMeasurementDTO customerMeasurementDTO, String modelUrl){
        Customer customer = customerRepository.findById(customerMeasurementDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));

        CustomerMeasurement customerMeasurement = new CustomerMeasurement();
        customerMeasurement.setCustomer(customer);
        customerMeasurement.setAnkleCircumference(customerMeasurementDTO.getAnkleCircumference());
        customerMeasurement.setArmLength(customerMeasurementDTO.getArmLength());
        customerMeasurement.setBicepCircumference(customerMeasurementDTO.getBicepCircumference());
        customerMeasurement.setCalfCircumference(customerMeasurementDTO.getCalfCircumference());
        customerMeasurement.setChestCircumference(customerMeasurementDTO.getChestCircumference());
        customerMeasurement.setForearmCircumference(customerMeasurementDTO.getForearmCircumference());
        customerMeasurement.setHeadCircumference(customerMeasurementDTO.getHeadCircumference());
        customerMeasurement.setHipCircumference(customerMeasurementDTO.getHipCircumference());
        customerMeasurement.setInsideLegLength(customerMeasurementDTO.getInsideLegLength());
        customerMeasurement.setNeckCircumference(customerMeasurementDTO.getNeckCircumference());
        customerMeasurement.setShoulderBreadth(customerMeasurementDTO.getShoulderBreadth());
        customerMeasurement.setShoulderToCrotch(customerMeasurementDTO.getShoulderToCrotch());
        customerMeasurement.setThighCircumference(customerMeasurementDTO.getThighCircumference());
        customerMeasurement.setWaistCircumference(customerMeasurementDTO.getWaistCircumference());
        customerMeasurement.setWristCircumference(customerMeasurementDTO.getWristCircumference());
        customerMeasurement.setBodyModelUrl(modelUrl);

        CustomerMeasurement savedCustomerMeasurement=customerMeasurementRepository.save(customerMeasurement);
        System.out.println();

    }

    public Map<String, Object> mapCustomerMeasurements(Map<String, Object> measurements) {
        Map<String, Object> meshcapadeMeasurements = new HashMap<>();

        meshcapadeMeasurements.put("ankle_girth", measurements.get("ankle_circumference"));
        meshcapadeMeasurements.put("arm_length", measurements.get("arm_length"));
        meshcapadeMeasurements.put("bicep_girth",measurements.get("bicep_circumference"));
        meshcapadeMeasurements.put("calf_girth", measurements.get("calf_circumference"));
        meshcapadeMeasurements.put("chest_girth", measurements.get("chest_circumference"));
        meshcapadeMeasurements.put("forearm_girth", measurements.get("forearm_circumference"));
        meshcapadeMeasurements.put("head_circumference", measurements.get("head_circumference"));
        meshcapadeMeasurements.put("hip_girth",  measurements.get("hip_circumference"));
        meshcapadeMeasurements.put("inseam", measurements.get("inside_leg_length"));
        meshcapadeMeasurements.put("neck_circumference",measurements.get("neck_circumference"));
        meshcapadeMeasurements.put("shoulder_breadth",  measurements.get("shoulder_breadth"));
        meshcapadeMeasurements.put("shoulder_to_crotch",  measurements.get("shoulder_to_crotch"));
        meshcapadeMeasurements.put("thigh_girth", measurements.get("thigh_circumference"));
        meshcapadeMeasurements.put("waist_girth", measurements.get("waist_circumference"));
        meshcapadeMeasurements.put("wrist_girth", measurements.get("wrist_circumference"));

        return meshcapadeMeasurements;
    }


}
