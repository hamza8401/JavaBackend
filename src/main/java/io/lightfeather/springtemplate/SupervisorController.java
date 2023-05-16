package io.lightfeather.springtemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lightfeather.springtemplate.model.Supervisor;
import io.lightfeather.springtemplate.model.SupervisorNotification;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class SupervisorController {
  Logger log = LoggerFactory.getLogger(SupervisorController.class);

  @RequestMapping("/api/supervisors")
  public String getSupervisors() {
    RestTemplate restTemplate = new RestTemplate();
    String supervisorsUrl
            = "https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers";
    ResponseEntity<Supervisor[]> response
            = restTemplate.getForEntity(supervisorsUrl, Supervisor[].class);
    if(response == null){
      return "No Supervisors found";
    }
    List<Supervisor> list = Arrays.asList(response.getBody());

    list = list.stream()
            .filter(e -> !isNumeric(e.getJurisdiction()))
            .collect(Collectors.toList());


    Comparator<Supervisor> comparator = Comparator
            .comparing(Supervisor::getJurisdiction)
            .thenComparing(Supervisor::getLastName)
            .thenComparing(Supervisor::getFirstName);
    List<Supervisor> sortedSupervisors = list.stream()
            .sorted(comparator).toList();

    ObjectMapper mapper = new ObjectMapper();
    String responseJson = null;
    try {
      responseJson = mapper.writeValueAsString(sortedSupervisors);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return responseJson;
  }

  @PostMapping("/api/submit")
  SupervisorNotification postSupervisor(@Valid @RequestBody SupervisorNotification notification) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    log.info("Request Body: {}",mapper.writeValueAsString(notification));
    return notification;
  }


  public static void main(String[] args) {
    SpringApplication.run(SupervisorController.class, args);
  }

  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch(NumberFormatException e){
      return false;
    }
  }

}
