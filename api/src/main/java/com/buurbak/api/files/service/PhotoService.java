package com.buurbak.api.files.service;

import com.buurbak.api.files.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
}
