package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dto.request.RegisterDriverRequest;
import africa.semicolon.uberdeluxe.data.dto.response.RegisterResponse;
import org.springframework.web.multipart.MultipartFile;

public interface DriverService {
    RegisterResponse register(RegisterDriverRequest request, MultipartFile licenseImage);
}
