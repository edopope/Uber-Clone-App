package africa.semicolon.uberdeluxe.controller;

import africa.semicolon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dto.response.RegisterResponse;
import africa.semicolon.uberdeluxe.service.PassengerService;
import africa.semicolon.uberdeluxe.service.PassengerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService=new PassengerServiceImpl();

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterPassengerRequest registerPassengerRequest){
        RegisterResponse registerResponse = passengerService.register(registerPassengerRequest);
        return ResponseEntity.status(registerResponse.getCode()).body(registerResponse);
    }
}
