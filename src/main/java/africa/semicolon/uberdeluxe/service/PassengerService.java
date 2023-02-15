package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dto.response.RegisterResponse;

public interface PassengerService {
    RegisterResponse register(RegisterPassengerRequest registerRequest);



}
