package org.helmo.HolyD.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.helmo.HolyD.config.StorageProperties;
import org.helmo.HolyD.controlers.exception.*;
import org.helmo.HolyD.repository.DTO.UserDTO;
import org.helmo.HolyD.repository.DTO.VacanceDTO;
import org.helmo.HolyD.repository.VacanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

    private final StorageProperties properties;
    private final VacanceRepository vacanceRepository;

    @Autowired
    public StorageService(StorageProperties properties, VacanceRepository vacanceRepository) {
        this.properties = properties;
        this.vacanceRepository = vacanceRepository;
    }

    private void verifcationAccessAndExist(Long idVacance, Long idActivity){
        UserDTO userConnected = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacanceDTO vacanceDTO = vacanceRepository.findByIdAndParticipantsContains(idVacance, userConnected)
                .orElseThrow(VacanceNotFoundException::new);
        if (idActivity != null) {
            if (vacanceDTO.getActivites().stream().noneMatch((activiteDTO) -> activiteDTO.getId().equals(idActivity))) {
                throw new ActiviteNotFoundException();
            }
        }
    }

    public void store(MultipartFile file, Long idVacance){
        this.storeFile(file, idVacance, null);
    }
    public void store(MultipartFile file, Long idVacance, Long idActivity) {
        this.storeFile(file, idVacance, idActivity);
    }
    private void storeFile(MultipartFile file, Long idVacance, Long idActivity) throws StorageException {
        this.verifcationAccessAndExist(idVacance, idActivity);
        try {
            if (file.isEmpty()) {
                throw new StorageException();
            }
            Path destinationDirectory = idActivity == null ? Paths.get(properties.getLocationVacanceDocuments(idVacance)) : Paths.get(properties.getLocationActivityDocuments(idVacance, idActivity));

            if (!Files.exists(destinationDirectory)) {
                Files.createDirectories(destinationDirectory);
            }

            Path destinationFile = destinationDirectory.resolve(
                            Paths.get(file.getOriginalFilename() == null ? "undefined" + UUID.randomUUID() : file.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException();
        }
    }

    public List<String> listFilesNames(Long idVacance) {
        return this.getAllFilesNames(idVacance, null);
    }

    public List<String> listFilesNames(Long idVacance, Long idActivity) {
        return this.getAllFilesNames(idVacance, idActivity);
    }
    private List<String> getAllFilesNames(Long idVacance, Long idActivity) {
        this.verifcationAccessAndExist(idVacance, idActivity);
        Path filesDirectory = idActivity == null ? Paths.get(properties.getLocationVacanceDocuments(idVacance)) : Paths.get(properties.getLocationActivityDocuments(idVacance, idActivity));
        filesDirectory = filesDirectory.normalize().toAbsolutePath();
        try (Stream<Path> files = Files.list(filesDirectory)) {
            return files.map(path -> path.getFileName().toString()).collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public Resource getFileAsResource(Long idVacance, String filename) {
        return this.getFile(idVacance, null, filename);
    }
    public Resource getFileAsResource(Long idVacance, Long idActivity, String filename) {
        return this.getFile(idVacance, idActivity, filename);
    }
    private Resource getFile(Long idVacance, Long idActivity, String filename){
        this.verifcationAccessAndExist(idVacance, idActivity);
        if (!filename.contains(".")) {
            throw new StorageException();
        }
        Path filesDirectory = idActivity == null ? Paths.get(properties.getLocationVacanceDocuments(idVacance), filename) : Paths.get(properties.getLocationActivityDocuments(idVacance, idActivity), filename);
        filesDirectory = filesDirectory.normalize().toAbsolutePath();
        try {
            Resource resource = new UrlResource(filesDirectory.toUri());
            if (!resource.exists()) {
                throw new DocumentNotFoundException();
            }
            return resource;
        }catch (MalformedURLException e) {
            throw new DocumentNotFoundException();
        }
    }
}
