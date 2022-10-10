package com.buurbak.api.files.service;

import com.buurbak.api.files.repository.ProfilePictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfilePictureService {
    private final ProfilePictureRepository profilePictureRepository;
}
