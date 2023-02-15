package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dto.response.RegisterResponse;
import africa.semicolon.uberdeluxe.data.dto.response.UpdatePassengerResponse;
import africa.semicolon.uberdeluxe.data.models.Passenger;
import com.github.fge.jsonpatch.JsonPatch;

public interface PassengerService {
    RegisterResponse register(RegisterPassengerRequest registerRequest);
    Passenger getPassengerById(Long userId);

    Passenger updatePassenger(Long passengerId, JsonPatch updatePayload);

    void deletePassenger(Long id);
}
