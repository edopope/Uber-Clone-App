package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.cloud.CloudService;
import africa.semicolon.uberdeluxe.data.dto.request.RegisterDriverRequest;
import africa.semicolon.uberdeluxe.data.dto.response.RegisterResponse;
import africa.semicolon.uberdeluxe.data.models.AppUser;
import africa.semicolon.uberdeluxe.data.models.Driver;
import africa.semicolon.uberdeluxe.data.repositories.DriverRepository;
import africa.semicolon.uberdeluxe.exception.ImageUploadException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService{

    private final DriverRepository driverRepository;

    private final CloudService cloudService;
    private final ModelMapper modelMapper;

    @Override
    public RegisterResponse register(RegisterDriverRequest request, MultipartFile licenseImage) {
        AppUser driverDetails = modelMapper.map(request, AppUser.class);
        driverDetails.setCreatedAt(LocalDateTime.now().toString());
        //steps
        //1. upload drivers license image
        var imageUrl = cloudService.upload(licenseImage);
        if (imageUrl==null)
            throw new ImageUploadException("Driver Registration failed");
        //2. create driver object
        Driver driver = Driver.builder()
                .userDetails(driverDetails)
                .licenseImage(imageUrl)
                .build();
        //3. save driver
        Driver savedDriver = driverRepository.save(driver);

        return RegisterResponse.builder()
                .code(HttpStatus.CREATED.value())
                .id(savedDriver.getId())
                .isSuccess(true)
                .message("Driver Registration Successful")
                .build();
    }
}
