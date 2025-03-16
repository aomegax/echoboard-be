package dev.aomegax.echoboard.be.service;

import dev.aomegax.echoboard.be.dto.request.EndUserReq;
import dev.aomegax.echoboard.be.exception.AppError;
import dev.aomegax.echoboard.be.exception.AppException;
import dev.aomegax.echoboard.be.model.EndUser;
import dev.aomegax.echoboard.be.repository.EndUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class EndUserService {

    private final EndUserRepository endUserRepository;

    public EndUserService(EndUserRepository userRepository) {
        this.endUserRepository = userRepository;
    }

    public EndUser saveUser(EndUserReq user) {
        ModelMapper modelMapper = new ModelMapper();
        return endUserRepository.save(modelMapper.map(user, EndUser.class));
    }

    public List<EndUser> getAllEndUsers() {
        return endUserRepository.findAll();
    }

    public void deleteUser(String id) {
        endUserRepository.deleteById(id);
    }

    public String endUserNetwork(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AppException(AppError.INPUT_DATA_ERROR);
        }
        int row = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            List<String[]> data = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(",")); // Simple CSV parsing

                row += 1;
            }
        } catch (Exception e) {
//            return "Error processing file: " + e.getMessage();
            throw new AppException(AppError.CSV_INVALID, row, e);
        }
        return "";
    }
}
