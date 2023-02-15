package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dto.response.RegisterResponse;
import africa.semicolon.uberdeluxe.data.models.AppUser;
import africa.semicolon.uberdeluxe.data.models.Passenger;
import africa.semicolon.uberdeluxe.data.repositories.PassengerRepository;
import africa.semicolon.uberdeluxe.mapper.ParaMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class PassengerServiceImpl implements PassengerService{

    private final PassengerRepository passengerRepository;
    @Override
    public RegisterResponse register(RegisterPassengerRequest registerRequest) {
        AppUser appUser = ParaMapper.map(registerRequest);
        appUser.setCreatedAt(LocalDateTime.now());
        Passenger passenger = new Passenger();
        passenger.setUserDetails(appUser);
        Passenger savedPassenger = passengerRepository.save(passenger);
        RegisterResponse registerResponse = getRegisterResponse(savedPassenger);
        return registerResponse;
    }

    private static RegisterResponse getRegisterResponse(Passenger savedPassenger) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(savedPassenger.getId());
        registerResponse.setCode(HttpStatus.CREATED.value());
        registerResponse.setSuccess(true);
        registerResponse.setMessage("User Registration Successful");
        return registerResponse;
    }
}
